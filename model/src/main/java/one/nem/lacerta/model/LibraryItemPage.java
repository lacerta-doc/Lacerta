package one.nem.lacerta.model;

import java.util.ArrayList;

public class LibraryItemPage {

    String pageTitle;
    String pageId;
    String parentId;
    ArrayList<ListItem> listItems;

    // Constructor

    public LibraryItemPage(String pageTitle, String pageId, ArrayList<ListItem> listItems, String parentId) {
        this.pageTitle = pageTitle;
        this.pageId = pageId;
        this.listItems = listItems;
        this.parentId = parentId;
    }

    public LibraryItemPage() {
        // Empty constructor
    }

    // Getter

    public String getPageTitle() {
        return pageTitle;
    }

    public String getPageId() {
        return pageId;
    }

    public String getParentId() {
        return parentId;
    }

    public ArrayList<ListItem> getListItems() {
        return listItems;
    }

    // Setter

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public void setListItems(ArrayList<ListItem> listItems) {
        this.listItems = listItems;
    }

}
