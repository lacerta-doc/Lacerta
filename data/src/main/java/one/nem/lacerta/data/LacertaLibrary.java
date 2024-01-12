package one.nem.lacerta.data;

import java.util.ArrayList;

import one.nem.lacerta.model.ListItem;
import one.nem.lacerta.model.ListItemType;

public interface LacertaLibrary {

    // Get History

    ArrayList<ListItem> getRecentDocument(int limit);
    ArrayList<ListItem> getRecentDocument(int limit, int offset);
    ArrayList<ListItem> getRecentDocument(int limit, int offset, ListItemType type);

    // Get List

    ArrayList<ListItem> getDocumentList(int limit);


}
