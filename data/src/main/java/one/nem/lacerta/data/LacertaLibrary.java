package one.nem.lacerta.data;

import one.nem.lacerta.model.ListItemType;

import one.nem.lacerta.model.LibraryItemPage;
import one.nem.lacerta.model.document.DocumentDetail;

public interface LacertaLibrary {

    // Get History
    LibraryItemPage getRecentDocument(int limit);
    LibraryItemPage getRecentDocument(int limit, int offset);

    // Get Library page
    LibraryItemPage getLibraryPage(int limit);
    LibraryItemPage getLibraryPage(int limit, int offset);
    LibraryItemPage getLibraryPage(String pageId, int limit);
    LibraryItemPage getLibraryPage(String pageId, int limit, int offset);

    // GetDocument
    DocumentDetail getDocumentDetailById(String id); // TODO-rca: Documentに統合する
}
