package one.nem.lacerta.data.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

// Hilt
import javax.inject.Inject;

// Lacerta/data
import one.nem.lacerta.data.Document;

// Lacerta/model
import one.nem.lacerta.model.PublicPath;
import one.nem.lacerta.model.document.DocumentMeta;
import one.nem.lacerta.model.document.DocumentDetail;

// Lacerta/source
import one.nem.lacerta.source.database.LacertaDatabase;
import one.nem.lacerta.source.database.entity.DocumentEntity;

// Lacerta/utils
import one.nem.lacerta.utils.LacertaLogger;

// Lacerta/vcs
import one.nem.lacerta.vcs.LacertaVcs;


public class DocumentImpl implements Document {

    String TAG = getClass().getSimpleName();

    @Inject
    LacertaLogger logger;

    @Inject
    LacertaDatabase database;

//    @Inject
//    LacertaVcs vcs;


    @Inject
    public DocumentImpl() {
        // Init
        logger.debug(TAG, "called");
    }


    @Override
    public DocumentDetail createDocument(DocumentMeta meta) {
        DocumentDetail detail = new DocumentDetail();
        detail.setMeta(meta);
        detail.setPages(new ArrayList<>());

        // TODO-rca: UIスレッドから追い出す

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
//        vcs.createDocument(meta.getId());

        return detail;
    }

    @Override
    public DocumentDetail createDocument() {
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
    public void deleteDocument(String documentId) {

    }

    @Override
    public void updateDocument(DocumentMeta meta, DocumentDetail detail) {

    }

    @Override
    public DocumentDetail getDocument(String documentId) {
        return null;
    }
}
