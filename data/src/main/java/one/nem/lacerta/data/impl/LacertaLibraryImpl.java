package one.nem.lacerta.data.impl;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import one.nem.lacerta.data.LacertaLibrary;
import one.nem.lacerta.model.LibraryItemPage;
import one.nem.lacerta.model.ListItem;
import one.nem.lacerta.model.ListItemType;
import one.nem.lacerta.model.document.DocumentDetail;
import one.nem.lacerta.source.database.LacertaDatabase;
import one.nem.lacerta.source.database.common.DateTypeConverter;
import one.nem.lacerta.source.database.entity.DocumentEntity;
import one.nem.lacerta.utils.LacertaLogger;

public class LacertaLibraryImpl implements LacertaLibrary {

    @Inject
    LacertaLogger logger;

    @Inject
    LacertaDatabase database;

    @Override
    public ArrayList<ListItem> getRecentDocument(int limit) {
        List<DocumentEntity> documentEntities = database.documentDao().getRecentDocument(limit);

        ArrayList<ListItem> listItems = new ArrayList<>();
        for (DocumentEntity documentEntity : documentEntities) {
            ListItem listItem = new ListItem();
            listItem.setItemType(ListItemType.ITEM_TYPE_DOCUMENT);
            listItem.setTitle(documentEntity.title);
            listItem.setDescription(DateFormat.getDateInstance().format(documentEntity.updatedAt));
            listItem.setItemId(documentEntity.id);
            listItems.add(listItem);
        }
        return listItems;
    }

    @Override
    public ArrayList<ListItem> getRecentDocument(int limit, int offset) {
        return null; // TODO-rca: Implement
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
