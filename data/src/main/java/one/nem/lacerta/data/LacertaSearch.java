package one.nem.lacerta.data;

import one.nem.lacerta.model.ListItem;

public interface LacertaSearch {

    ListItem autoSearch(String query, int limit);

    ListItem autoSearch(String query, int limit, int offset);

}
