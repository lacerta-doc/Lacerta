package one.nem.lacerta.data.impl;
import one.nem.lacerta.data.LacertaLibrary;
import one.nem.lacerta.model.LibraryItemPage;
import one.nem.lacerta.model.document.DocumentDetail;

public class LacertaLibraryStubImpl implements LacertaLibrary {
    @Override
    public LibraryItemPage getRecentDocument(int limit) {
        return null;
    }

    @Override
    public LibraryItemPage getRecentDocument(int limit, int offset) {
        return null;
    }

    @Override
    public LibraryItemPage getLibraryPage(int limit) {
        return null;
    }

    @Override
    public LibraryItemPage getLibraryPage(int limit, int offset) {
        return null;
    }

    @Override
    public LibraryItemPage getLibraryPage(String pageId, int limit) {
        return null;
    }

    @Override
    public LibraryItemPage getLibraryPage(String pageId, int limit, int offset) {
        return null;
    }

    @Override
    public DocumentDetail getDocumentDetailById(String id) {
        return null;
    }
}
