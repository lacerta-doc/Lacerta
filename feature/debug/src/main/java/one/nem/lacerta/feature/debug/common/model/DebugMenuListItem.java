package one.nem.lacerta.feature.debug.common.model;

public class DebugMenuListItem {

    private String title;
    private String description;
    private String destinationId; // Navigation destination ID
    private boolean enabled;

    public DebugMenuListItem(String title, String description, String destinationId, boolean enabled) {
        this.title = title;
        this.description = description;
        this.destinationId = destinationId;
        this.enabled = enabled;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


}
