package one.nem.lacerta.data;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import one.nem.lacerta.model.ListItem;
import one.nem.lacerta.model.ListItemType;

import one.nem.lacerta.model.LibraryItemPage;
import one.nem.lacerta.model.document.DocumentDetail;

public interface LacertaLibrary {

    // Get History
    CompletableFuture<ArrayList<ListItem>> getRecentDocument(int limit);
    CompletableFuture<ArrayList<ListItem>> getRecentDocument(int limit, int offset);

    // Get Library page
    CompletableFuture<LibraryItemPage> getLibraryPage(int limit);
    CompletableFuture<LibraryItemPage> getLibraryPage(int limit, int offset);
    CompletableFuture<LibraryItemPage> getLibraryPage(String pageId, int limit);
    CompletableFuture<LibraryItemPage> getLibraryPage(String pageId, int limit, int offset);

    // Create Folder
    CompletableFuture<String> createFolder(String path, String name);

}
