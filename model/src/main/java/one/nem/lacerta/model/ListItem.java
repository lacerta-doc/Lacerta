package one.nem.lacerta.model;

import android.graphics.drawable.Drawable;

public class ListItem {

    // Properties

    String title;
    String description;
    ListItemType itemType;
    String itemId;

    // Constructor

    public ListItem(String title, String description, ListItemType itemType, String itemId) {
        this.title = title;
        this.description = description;
        this.itemType = itemType;
        this.itemId = itemId;
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

}
