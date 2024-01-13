package one.nem.lacerta.data.impl;

import java.util.ArrayList;

import one.nem.lacerta.data.LacertaSearch;
import one.nem.lacerta.model.ListItem;

public class LacertaSearchStubImpl implements LacertaSearch {

    @Override
    public ArrayList<ListItem> autoSearch(String query, int limit) {
        return null;
    }

    @Override
    public ArrayList<ListItem> autoSearch(String query, int limit, int offset) {
        return null;
    }
}
