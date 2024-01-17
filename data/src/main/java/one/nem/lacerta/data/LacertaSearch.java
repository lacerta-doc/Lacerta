package one.nem.lacerta.data;

import java.util.ArrayList;

import one.nem.lacerta.model.ListItem;

public interface LacertaSearch {

    ArrayList<ListItem> autoSearch(String query, int limit);

    ArrayList<ListItem> autoSearch(String query, int limit, int offset);

}
