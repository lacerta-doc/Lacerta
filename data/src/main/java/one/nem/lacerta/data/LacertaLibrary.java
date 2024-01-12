package one.nem.lacerta.data;

import java.util.ArrayList;

import one.nem.lacerta.model.ListItem;
import one.nem.lacerta.model.ListItemType;

import one.nem.lacerta.model.LibraryItemList;

public interface LacertaLibrary {

    // Get History

    LibraryItemList getRecentDocument(int limit);
    LibraryItemList getRecentDocument(int limit, int offset);
    LibraryItemList getRecentDocument(int limit, int offset, ListItemType type);

    // Get List

    LibraryItemList getDocumentList(int limit);


}
