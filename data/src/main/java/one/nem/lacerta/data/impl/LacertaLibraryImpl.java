package one.nem.lacerta.data.impl;

import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.inject.Inject;

import one.nem.lacerta.data.LacertaLibrary;
import one.nem.lacerta.model.LibraryItemPage;
import one.nem.lacerta.model.ListItem;
import one.nem.lacerta.model.ListItemType;
import one.nem.lacerta.model.document.DocumentDetail;
import one.nem.lacerta.source.database.LacertaDatabase;
import one.nem.lacerta.source.database.common.DateTypeConverter;
import one.nem.lacerta.source.database.entity.DocumentEntity;
import one.nem.lacerta.source.database.entity.FolderEntity;
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

    // Internal
    private CompletableFuture<List<FolderEntity>> getFolderEntitiesByPublicPath(String publicPath) {
        return CompletableFuture.supplyAsync(() -> {
            return database.folderDao().findByPublicPathWithLimit(publicPath, 10); // TODO-rca: ハードコーディングやめる
        });
    }

    private CompletableFuture<List<DocumentEntity>> getDocumentEntitiesByPublicPath(String publicPath) {
        return CompletableFuture.supplyAsync(() -> {
            return database.documentDao().findByPublicPathWithLimit(publicPath, 10); // TODO-rca: ハードコーディングやめる
        });
    }

    @Override
    public CompletableFuture<LibraryItemPage> getLibraryPage(int limit) {
        return CompletableFuture.supplyAsync(() -> {
            LibraryItemPage libraryItemPage = new LibraryItemPage();

            List<FolderEntity> folderEntities = getFolderEntitiesByPublicPath("/").join();
            logger.debug("LacertaLibraryImpl", "folderEntities.size(): " + folderEntities.size());
            List<DocumentEntity> documentEntities = getDocumentEntitiesByPublicPath("/").join();
            logger.debug("LacertaLibraryImpl", "documentEntities.size(): " + documentEntities.size());

            ArrayList<ListItem> listItems = new ArrayList<>();
            for (FolderEntity folderEntity : folderEntities) {
                logger.debug("LacertaLibraryImpl", "folderEntity.name: " + folderEntity.name);
                ListItem listItem = new ListItem();
                listItem.setItemType(ListItemType.ITEM_TYPE_FOLDER);
                listItem.setTitle(folderEntity.name);
                listItem.setDescription("フォルダ"); // TODO-rca: ハードコーディングやめる
                listItem.setItemId(folderEntity.id);
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

            libraryItemPage.setPageTitle("/");
            libraryItemPage.setPageId("root");
            libraryItemPage.setListItems(listItems);

            logger.debug("LacertaLibraryImpl", "libraryItemPage.getListItems().size(): " + libraryItemPage.getListItems().size());

            return libraryItemPage;
        });
    }

    @Override
    public CompletableFuture<LibraryItemPage> getLibraryPage(int limit, int offset) {
        return CompletableFuture.supplyAsync(() -> {
            return null;
        });
    }

    @Override
    public CompletableFuture<LibraryItemPage> getLibraryPage(String pageId, int limit) {
        return CompletableFuture.supplyAsync(() -> {
            return null;
        });
    }

    @Override
    public CompletableFuture<LibraryItemPage> getLibraryPage(String pageId, int limit, int offset) {
        return CompletableFuture.supplyAsync(() -> {
            return null;
        });
    }
}
