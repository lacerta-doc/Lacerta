package one.nem.lacerta.setting.model;

import android.graphics.drawable.Drawable;

public class SettingListItem {

    String title;
    String description;
    Drawable icon;
    int destination;

    public SettingListItem(String title, String description, Drawable icon, int destination) {
        this.title = title;
        this.description = description;
        this.icon = icon;
        this.destination = destination;
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

    public int getDestination() {
        return destination;
    }
}
