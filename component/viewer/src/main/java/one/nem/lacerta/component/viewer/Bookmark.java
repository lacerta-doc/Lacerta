package one.nem.lacerta.component.viewer;

// Bookmark.java
public class Bookmark {
    private String id; // ブックマークの一意の識別子
    private String title;
    private String pageId; // ブックマークが参照するページの識別子
    private long timestamp; // ブックマークが作成された時刻

    public Bookmark(String id, String title, String pageId, long timestamp) {
        this.id = id;
        this.title = title;
        this.pageId = pageId;
        this.timestamp = timestamp;
    }

    public Bookmark() {
        // Empty constructor
    }

    // Getter

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPageId() {
        return pageId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    // Setter

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
