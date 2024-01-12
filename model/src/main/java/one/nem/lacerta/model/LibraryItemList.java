package one.nem.lacerta.model;

import java.util.ArrayList;

public class LibraryItemList {

    String pageTitle;
    String pageId;
    ArrayList<ListItem> listItems;

    public LibraryItemList(String pageTitle, String pageId, ArrayList<ListItem> listItems) {
        this.pageTitle = pageTitle;
        this.pageId = pageId;
        this.listItems = listItems;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public String getPageId() {
        return pageId;
    }

    public ArrayList<ListItem> getListItems() {
        return listItems;
    }
}
