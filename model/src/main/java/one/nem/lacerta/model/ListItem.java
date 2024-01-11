package one.nem.lacerta.model;

import android.graphics.drawable.Drawable;

public class ListItem {

    String title;
    String description;
    Drawable icon;
    String itemId;

    public ListItem(String title, String description, Drawable icon, String itemId) {
        this.title = title;
        this.description = description;
        this.icon = icon;
        this.itemId = itemId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Drawable getIcon() {
        return icon;
    }

    public String getItemId() {
        return itemId;
    }

}
