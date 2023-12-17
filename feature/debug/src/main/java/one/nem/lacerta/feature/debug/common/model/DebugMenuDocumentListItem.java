package one.nem.lacerta.feature.debug.common.model;

public class DebugMenuDocumentListItem {

    private String title;
    private String description;
    private String updatedAt;

    public DebugMenuDocumentListItem(String title, String description, String updatedAt) {
        this.title = title;
        this.description = description;
        this.updatedAt = updatedAt;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

}
