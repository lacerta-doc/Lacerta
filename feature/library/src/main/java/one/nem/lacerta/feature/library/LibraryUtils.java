package one.nem.lacerta.feature.library;

import java.util.ArrayList;
import java.util.List;

import one.nem.lacerta.model.ListItem;
import one.nem.lacerta.model.document.DocumentMeta;

public class LibraryUtils {

        public static ArrayList<ListItem> convertToLibraryItems(List<DocumentMeta> metas) {
            ArrayList<ListItem> libraryItems = new ArrayList<>();
            for (DocumentMeta meta : metas) {
                ListItem listItem = new ListItem();
                listItem.setTitle(meta.getTitle());
                listItem.setDescription(meta.getTitle());
                // 他の必要な情報もListItemに設定する
                libraryItems.add(listItem);
            }
            return libraryItems;
        }
    }
