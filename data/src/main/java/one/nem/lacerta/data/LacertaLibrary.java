package one.nem.lacerta.data;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import one.nem.lacerta.model.ListItem;
import one.nem.lacerta.model.ListItemType;

import one.nem.lacerta.model.LibraryItemPage;
import one.nem.lacerta.model.PublicPath;
import one.nem.lacerta.model.document.DocumentDetail;

public interface LacertaLibrary {

    // Get History
    CompletableFuture<ArrayList<ListItem>> getRecentDocument(int limit);
    CompletableFuture<ArrayList<ListItem>> getRecentDocument(int limit, int offset);

    // Get Library Page
    CompletableFuture<LibraryItemPage> getLibraryPage(String pageId, int limit);
    CompletableFuture<LibraryItemPage> getLibraryPage(String pageId, int limit, int offset);

    // Get Folder List
    CompletableFuture<ArrayList<ListItem>> getFolderList(String parentId);

    // Create Folder
    CompletableFuture<String> createFolder(String parentId, String name);

    // Get Public Path
    CompletableFuture<PublicPath> getPublicPath(String itemId, ListItemType itemType);

}
