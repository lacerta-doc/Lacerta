package one.nem.lacerta.model.document;

import java.util.Date;

public class DocumentDetail {

    String id;
    String title;
    Date createdAt;
    Date updatedAt;
    String author;
    String defaultBranch;

    // Getter

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public String getAuthor() {
        return author;
    }

    public String getDefaultBranch() {
        return defaultBranch;
    }

    // Setter

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDefaultBranch(String defaultBranch) {
        this.defaultBranch = defaultBranch;
    }


}
