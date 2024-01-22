package one.nem.lacerta.data.impl;

import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import javax.inject.Inject;

import one.nem.lacerta.data.LacertaLibrary;
import one.nem.lacerta.model.LibraryItemPage;
import one.nem.lacerta.model.ListItem;
import one.nem.lacerta.model.ListItemType;
import one.nem.lacerta.model.PublicPath;
import one.nem.lacerta.model.document.DocumentDetail;
import one.nem.lacerta.source.database.LacertaDatabase;
import one.nem.lacerta.source.database.common.DateTypeConverter;
import one.nem.lacerta.source.database.entity.DocumentEntity;
import one.nem.lacerta.source.database.entity.FolderEntity;
import one.nem.lacerta.utils.FeatureSwitch;
import one.nem.lacerta.utils.LacertaLogger;

public class LacertaLibraryImpl implements LacertaLibrary {

    LacertaLogger logger;
    LacertaDatabase database;

    @Inject
    public LacertaLibraryImpl(LacertaLogger logger, LacertaDatabase database) {
        this.logger = logger;
        this.database = database;
    }

    @Override
    public CompletableFuture<ArrayList<ListItem>> getRecentDocument(int limit) {
        return CompletableFuture.supplyAsync(() -> {
            // 5秒フリーズさせる
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            List<DocumentEntity> documentEntities = database.documentDao().getRecentDocument(limit);

            ArrayList<ListItem> listItems = new ArrayList<>();
            for (DocumentEntity documentEntity : documentEntities) {
                ListItem listItem = new ListItem();
                listItem.setItemType(ListItemType.ITEM_TYPE_DOCUMENT);
                listItem.setTitle(documentEntity.title);
                listItem.setDescription(DateFormat.getDateInstance().format(documentEntity.updatedAt));
                listItem.setItemId(documentEntity.id);
                listItems.add(listItem);
            }

            return listItems;
        });
    }

    @Override
    public CompletableFuture<ArrayList<ListItem>> getRecentDocument(int limit, int offset) {
        return null; // TODO-rca: Implement
    }

    @Override
    public CompletableFuture<LibraryItemPage> getLibraryPage(String pageId, int limit) {
        return CompletableFuture.supplyAsync(() -> {
            LibraryItemPage libraryItemPage = new LibraryItemPage();

            FolderEntity folderEntity = database.folderDao().findById(pageId);
            if (folderEntity == null) {
                logger.warn("LacertaLibraryImpl", pageId + " is not found.");
                return null;
            }

            List<FolderEntity> folderEntities = database.folderDao().findByParentId(pageId);
            logger.debug("LacertaLibraryImpl", "folderEntities.size(): " + folderEntities.size());
            List<DocumentEntity> documentEntities = database.documentDao().findByParentId(pageId);
            logger.debug("LacertaLibraryImpl", "documentEntities.size(): " + documentEntities.size());

            ArrayList<ListItem> listItems = new ArrayList<>();
            for (FolderEntity childFolderEntity : folderEntities) {
                logger.debug("LacertaLibraryImpl", "childFolderEntity.name: " + childFolderEntity.name);
                ListItem listItem = new ListItem();
                listItem.setItemType(ListItemType.ITEM_TYPE_FOLDER);
                listItem.setTitle(childFolderEntity.name);
                listItem.setDescription("フォルダ"); // TODO-rca: ハードコーディングやめる
                listItem.setItemId(childFolderEntity.id);
                listItems.add(listItem);
            }

            for (DocumentEntity documentEntity : documentEntities) {
                logger.debug("LacertaLibraryImpl", "documentEntity.title: " + documentEntity.title);
                ListItem listItem = new ListItem();
                listItem.setItemType(ListItemType.ITEM_TYPE_DOCUMENT);
                listItem.setTitle(documentEntity.title);
//                listItem.setDescription(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm").format(documentEntity.updatedAt.toInstant()));
                listItem.setItemId(documentEntity.id);
                listItems.add(listItem);
            }

            libraryItemPage.setPageTitle(folderEntity.name);
            libraryItemPage.setPageId(folderEntity.id);
            libraryItemPage.setParentId(folderEntity.parentId);
            libraryItemPage.setListItems(listItems);

            logger.debug("LacertaLibraryImpl", "libraryItemPage.getListItems().size(): " + libraryItemPage.getListItems().size());

            return libraryItemPage;
        });
    }

    @Override
    public CompletableFuture<LibraryItemPage> getLibraryPage(String pageId, int limit, int offset) {
        return CompletableFuture.supplyAsync(() -> {
            return null;
        });
    }

    @Override
    public CompletableFuture<String> createFolder(String parentId, String name) {
        return CompletableFuture.supplyAsync(() -> {

            FolderEntity parentFolderEntity = database.folderDao().findById(parentId);
            PublicPath publicPath;
            if (parentFolderEntity == null) {
                publicPath = new PublicPath().resolve("/");
            } else {
                publicPath = new PublicPath().resolve(parentFolderEntity.publicPath);
            }

            FolderEntity folderEntity = new FolderEntity();
            folderEntity.id = UUID.randomUUID().toString();
            folderEntity.name = name;
            folderEntity.publicPath = publicPath.getStringPath();
            database.folderDao().insert(folderEntity);
            return folderEntity.id;
        });
    }

    @Override
    public CompletableFuture<PublicPath> getPublicPath(String itemId, ListItemType itemType) {
        return CompletableFuture.supplyAsync(() -> {
            if (itemType == ListItemType.ITEM_TYPE_DOCUMENT) {
                DocumentEntity documentEntity = database.documentDao().findById(itemId);
                if (documentEntity == null) {
                    return null;
                }
                return new PublicPath().resolve(documentEntity.publicPath);
            } else if (itemType == ListItemType.ITEM_TYPE_FOLDER) {
                FolderEntity folderEntity = database.folderDao().findById(itemId);
                if (folderEntity == null) {
                    return null;
                }
                return new PublicPath().resolve(folderEntity.publicPath);
            } else {
                return null;
            }
        });
    }

    /**
     * 再帰的にパスを解決する
     *
     * @param folderId
     * @return
     */
    private PublicPath recursiveResolve(String folderId) {
        String current = folderId;
        boolean continueFlag = true;
        ArrayList<String> folderNames = new ArrayList<>();
        while (continueFlag) {
            FolderEntity folderEntity = database.folderDao().findById(current);
            if (folderEntity == null) { // 存在しないフォルダIDが指定された場合
                continueFlag = false;
            } else {
                folderNames.add(folderEntity.name);
                current = folderEntity.parentId;
                if (current == null) { // ルートフォルダに到達した場合
                    continueFlag = false;
                }
            }
        }

        // フォルダ名を逆順にしてListに変換
        Collections.reverse(folderNames);
        List<String> folderNamesReversed = new ArrayList<>(folderNames);

        return new PublicPath(folderNamesReversed);
    }
}
