package one.nem.lacerta.model;

import java.util.ArrayList;

public class LibraryItemPage {

    String pageTitle;
    String pageId;
    ArrayList<ListItem> listItems;

    public LibraryItemPage(String pageTitle, String pageId, ArrayList<ListItem> listItems) {
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

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public void setListItems(ArrayList<ListItem> listItems) {
        this.listItems = listItems;
    }

}
