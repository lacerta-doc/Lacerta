package one.nem.lacerta.feature.library;

import java.util.ArrayList;
import java.util.List;

import one.nem.lacerta.model.ListItem;
import one.nem.lacerta.model.document.DocumentMeta;

// DocumentMeta クラスのリストを ListItem クラスのリストに変換するためのユーティリティクラスの一部
public class LibraryUtils {

    public static ArrayList<ListItem> convertToLibraryItems(List<DocumentMeta> metas) {
        ArrayList<ListItem> libraryItems = new ArrayList<>();
        for (DocumentMeta meta : metas) {
            ListItem listItem = new ListItem();
            listItem.setTitle(meta.getTitle());

            listItem.setDescription(meta.getId());

            libraryItems.add(listItem);
        }
        return libraryItems;
    }
}
