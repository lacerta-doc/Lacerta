package one.nem.lacerta.data.impl;

import java.util.ArrayList;

import javax.inject.Inject;

import one.nem.lacerta.data.LacertaSearch;
import one.nem.lacerta.model.ListItem;

import one.nem.lacerta.data.LacertaLibrary;

public class LacertaSearchStubImpl implements LacertaSearch {

    private LacertaLibrary library;


    @Inject
    public LacertaSearchStubImpl(LacertaLibrary library) {
        this.library = library;
    }

    @Override
    public ArrayList<ListItem> autoSearch(String query, int limit) {
        return library.getLibraryPage(limit).getListItems();
    }

    @Override
    public ArrayList<ListItem> autoSearch(String query, int limit, int offset) {
        return library.getLibraryPage(limit, offset).getListItems();
    }
}
