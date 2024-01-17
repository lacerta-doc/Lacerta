package one.nem.lacerta.data.impl;

import javax.inject.Inject;

import one.nem.lacerta.data.LacertaLibrary;
import one.nem.lacerta.model.LibraryItemPage;
import one.nem.lacerta.model.document.DocumentDetail;
import one.nem.lacerta.source.database.LacertaDatabase;
import one.nem.lacerta.utils.LacertaLogger;

public class LacertaLibraryImpl implements LacertaLibrary {

    @Inject
    LacertaLogger logger;

    @Inject
    LacertaDatabase database;

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
