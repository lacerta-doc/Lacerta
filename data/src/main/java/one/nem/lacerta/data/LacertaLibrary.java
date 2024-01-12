package one.nem.lacerta.data;

import one.nem.lacerta.model.ListItemType;

import one.nem.lacerta.model.LibraryItemPage;

public interface LacertaLibrary {

    // Get History

    LibraryItemPage getRecentDocument(int limit);
    LibraryItemPage getRecentDocument(int limit, int offset);
    LibraryItemPage getRecentDocument(int limit, int offset, ListItemType type);

    // Get List

    LibraryItemPage getDocumentList(int limit);


}
