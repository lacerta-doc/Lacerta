package one.nem.lacerta.setting.model;

import android.graphics.drawable.Drawable;

public class SettingListItem {

    String title;
    String description;
    String destination;
    Drawable icon;

    public SettingListItem(String title, String description, String destination, Drawable icon) {
        this.title = title;
        this.description = description;
        this.destination = destination;
        this.icon = icon;
    }

    public SettingListItem() {
        // Empty constructor
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDestination() {
        return destination;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

}
