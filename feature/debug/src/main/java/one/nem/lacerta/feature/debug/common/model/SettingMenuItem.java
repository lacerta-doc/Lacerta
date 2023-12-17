package one.nem.lacerta.feature.debug.common.model;

import android.content.Intent;

import androidx.navigation.ActivityNavigator;

public class SettingMenuItem {
    private String title;
    private String description;
    private String destinationId;

    public SettingMenuItem(String title, String description, String destinationId) {
        this.title = title;
        this.description = description;
        this.destinationId = destinationId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDestinationId() {
        return destinationId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDestinationId(String destinationId) {
        this.destinationId = destinationId;
    }

}
