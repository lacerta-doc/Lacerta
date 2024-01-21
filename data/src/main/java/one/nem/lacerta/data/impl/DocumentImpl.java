package one.nem.lacerta.data.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

// Hilt
import javax.inject.Inject;

// Lacerta/data
import one.nem.lacerta.data.Document;

// Lacerta/model
import one.nem.lacerta.model.PublicPath;
import one.nem.lacerta.model.document.DocumentMeta;
import one.nem.lacerta.model.document.DocumentDetail;

// Lacerta/source
import one.nem.lacerta.model.document.internal.XmlMetaModel;
import one.nem.lacerta.model.document.internal.XmlMetaPageModel;
import one.nem.lacerta.model.document.page.Page;
import one.nem.lacerta.source.database.LacertaDatabase;
import one.nem.lacerta.source.database.entity.DocumentEntity;

// Lacerta/utils
import one.nem.lacerta.source.file.FileManager;
import one.nem.lacerta.source.file.factory.FileManagerFactory;
import one.nem.lacerta.utils.LacertaLogger;

// Lacerta/vcs
import one.nem.lacerta.utils.XmlMetaParser;
import one.nem.lacerta.utils.repository.DeviceInfoUtils;
import one.nem.lacerta.vcs.LacertaVcs;
import one.nem.lacerta.vcs.factory.LacertaVcsFactory;


public class DocumentImpl implements Document {

    String TAG = getClass().getSimpleName();

    @Inject
    LacertaLogger logger;

    @Inject
    LacertaDatabase database;

    @Inject
    LacertaVcsFactory vcsFactory;

    @Inject
    FileManagerFactory fileManagerFactory;

    @Inject
    DeviceInfoUtils deviceInfoUtils;

    @Inject
    XmlMetaParser xmlMetaParser;

    @Inject
    public DocumentImpl() {
        // Init
//        logger.debug(TAG, "called");
    }


    @Override
    public CompletableFuture<DocumentDetail> createDocument(DocumentMeta meta) {
        return CompletableFuture.supplyAsync(() -> {
            DocumentDetail detail = new DocumentDetail();
            detail.setMeta(meta);
            detail.setPages(new ArrayList<>());
            // Create DocumentEntity
            DocumentEntity documentEntity = new DocumentEntity();
            documentEntity.id = meta.getId();
            documentEntity.title = meta.getTitle();
            documentEntity.author = meta.getAuthor();
            documentEntity.defaultBranch = meta.getDefaultBranch();
            documentEntity.updatedAt = meta.getUpdatedAt();
            documentEntity.createdAt = meta.getCreatedAt();
            documentEntity.publicPath = meta.getPath().getStringPath();
            documentEntity.tagIds = meta.getTagIds();

            database.documentDao().insert(documentEntity);

            // Vcs
            LacertaVcs vcs = vcsFactory.create(meta.getId());
            vcs.createDocument(meta.getId());

            // XmlMeta
            updateXmlMeta(detail).join();

            return detail;
        });
    }

    @Override
    public CompletableFuture<DocumentDetail> createDocument() {
        DocumentMeta meta = new DocumentMeta();
        meta.setId(UUID.randomUUID().toString());
        meta.setTitle("New Document");
        meta.setAuthor("author");
        meta.setDefaultBranch("master");
        meta.setUpdatedAt(new Date());
        meta.setCreatedAt(new Date());
        meta.setPath(new PublicPath().getRoot()); // TODO-rca: 2回インスタンスを生成していて無駄なのでなんとかする
        meta.setTags(new ArrayList<>());
        return createDocument(meta);
    }

    @Override
    public CompletableFuture<Void> deleteDocument(String documentId) {
        return CompletableFuture.supplyAsync(() -> {
            return null;
        });
    }

    @Override
    public CompletableFuture<Void> updateDocument(DocumentDetail detail) {
        return CompletableFuture.supplyAsync(() -> {
            updateXmlMeta(detail).join();
            return null;
        });
    }

    @Override
    public CompletableFuture<DocumentDetail> getDocument(String documentId) {
        return CompletableFuture.supplyAsync(() -> {
            DocumentEntity documentEntity = database.documentDao().findById(documentId);
            if (documentEntity == null) {
                throw new IllegalArgumentException("documentId is not found");
            }
            DocumentMeta meta = new DocumentMeta();
            meta.setId(documentEntity.id);
            meta.setTitle(documentEntity.title);
            meta.setAuthor(documentEntity.author);
            meta.setDefaultBranch(documentEntity.defaultBranch);
            meta.setUpdatedAt(documentEntity.updatedAt);
            meta.setCreatedAt(documentEntity.createdAt);
            meta.setPath(new PublicPath().resolve(documentEntity.publicPath));
            meta.setTags(new ArrayList<>()); // TODO-rca: タグを取得する

            DocumentDetail detail = new DocumentDetail();

            getPagesByXmlMeta(documentId).thenCompose(xmlMetaPageModels -> getPagesByXmlMetaPageModel(documentId, xmlMetaPageModels)).thenAccept(pages -> {
                logger.debug(TAG, "pages: " + pages.size());
                detail.setMeta(meta);
                detail.setPages(pages);
            }).join();

            return detail;
        });
    }

    private CompletableFuture<ArrayList<XmlMetaPageModel>> getPagesByXmlMeta(String documentId) {
        return CompletableFuture.supplyAsync(() -> {
            FileManager fileManager = fileManagerFactory.create(deviceInfoUtils.getExternalStorageDirectory());
            try {
                ArrayList<XmlMetaPageModel> xmlMetaPageModels = xmlMetaParser.deserialize(fileManager.resolve(documentId).loadXml("meta.xml")).getPages();
                // Debug
                logger.debug(TAG, "xmlMetaPageModels: " + xmlMetaPageModels.size());
                for (XmlMetaPageModel xmlMetaPageModel : xmlMetaPageModels) {
                    logger.debug(TAG, "\txmlMetaPageModel: " + xmlMetaPageModel.getFilename());
                }
                return xmlMetaPageModels;
            } catch (IOException e) {
                logger.error(TAG, "DocumentMeta parse error");
                logger.trace(TAG, e.getMessage());
                logger.e_code("db18c04a-1625-4871-9e4e-918d805568ac");
            }
            return null;
        });
    }

    private CompletableFuture<ArrayList<Page>> getPagesByXmlMetaPageModel(String documentId, ArrayList<XmlMetaPageModel> xmlMetaPageModels) {
        return CompletableFuture.supplyAsync(() -> {
            ArrayList<Page> pages = new ArrayList<>();
            FileManager fileManager;
            try {
                fileManager = fileManagerFactory.create(deviceInfoUtils.getExternalStorageDirectory()).resolve(documentId);
            } catch (IOException e) {
                logger.error(TAG, "FileManager resolve error");
                logger.trace(TAG, e.getMessage());
                logger.e_code("2235e8ee-4177-46f8-af6b-084381b202e6");
                return null;
            }
            for (XmlMetaPageModel xmlMetaPageModel : xmlMetaPageModels) {
                try {
                    pages.add(new Page(xmlMetaPageModel.getFilename(), fileManager.resolve("raw").loadBitmap(xmlMetaPageModel.getFilename())));
                } catch (IOException e) {
                    logger.error(TAG, "Bitmap decode error");
                    logger.trace(TAG, e.getMessage());
                    logger.e_code("3bba8c8f-90fb-4a24-a726-7ea201929f8b");
                    return null;
                }
            }
            return pages;
        });
    }

    private CompletableFuture<Void> updateXmlMeta(DocumentDetail documentDetail) {
        return CompletableFuture.supplyAsync(() -> {
            // TODO-rca: リビジョンIDを検証する, 挿入する
            FileManager fileManager = fileManagerFactory.create(deviceInfoUtils.getExternalStorageDirectory());
            ArrayList<XmlMetaPageModel> xmlMetaPageModels = new ArrayList<>();
            for (Page page : documentDetail.getPages()) {
                xmlMetaPageModels.add(new XmlMetaPageModel(page.getFileName()));
            }
            try {
                fileManager.createDirectoryIfNotExist(documentDetail.getMeta().getId()).resolve(documentDetail.getMeta().getId())
                        .createFileIfNotExist("meta.xml").resolve("meta.xml").saveXml(xmlMetaParser.serialize(new XmlMetaModel("revisionId_PLACEHOLDER", xmlMetaPageModels)));
            } catch (IOException e) {
                logger.error(TAG, "DocumentMeta serialize error");
                logger.trace(TAG, e.getMessage());
                logger.e_code("e3b4d0c9-5b7e-4b7e-9e9a-5b8b8b8b8b8b");
            }
            return null;
        });
    }
}
