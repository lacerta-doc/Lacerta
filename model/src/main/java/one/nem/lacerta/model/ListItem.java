package one.nem.lacerta.model;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;

import one.nem.lacerta.model.document.tag.DocumentTag;

public class ListItem {

    // Properties

    String title;
    String description;
    ListItemType itemType;
    String itemId;
    boolean hasCombined;
    ArrayList<DocumentTag> tagList;

    // Constructor

    public ListItem(String title, String description, ListItemType itemType, String itemId) {
        this.title = title;
        this.description = description;
        this.itemType = itemType;
        this.itemId = itemId;
    }

    public ListItem(String title, String description, ListItemType itemType, String itemId, boolean hasCombined) {
        this.title = title;
        this.description = description;
        this.itemType = itemType;
        this.itemId = itemId;
        this.hasCombined = hasCombined;
    }

    public ListItem(String title, String description, ListItemType itemType, String itemId,boolean hasCombined, ArrayList<DocumentTag> tagList) {
        this.title = title;
        this.description = description;
        this.itemType = itemType;
        this.itemId = itemId;
        this.hasCombined = hasCombined;
        this.tagList = tagList;
    }

    public ListItem() {
        // Empty constructor
    }

    // Getter

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public ListItemType getItemType() {
        return itemType;
    }

    public String getItemId() {
        return itemId;
    }

    public boolean getHasCombined() {
        return hasCombined;
    }

    public ArrayList<DocumentTag> getTagList() {
        return tagList;
    }

    // Setter

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setItemType(ListItemType itemType) {
        this.itemType = itemType;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public void setHasCombined(boolean hasCombined) {
        this.hasCombined = hasCombined;
    }

    public void setTagList(ArrayList<DocumentTag> tagList) {
        this.tagList = tagList;
    }
}
