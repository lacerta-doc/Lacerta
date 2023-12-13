package one.nem.lacerta.model.document;

import java.nio.file.Path;
import java.util.Date;

import one.nem.lacerta.model.document.path.DocumentPath;
import one.nem.lacerta.model.document.DocumentMeta;

public class DocumentDetail {

    DocumentMeta meta;
    DocumentPath path;
    String author;
    String defaultBranch;
    Path fullPath;

    // Getter

    public DocumentMeta getMeta() {
        return meta;
    }

    public DocumentPath getPath() {
        return path;
    }

    public String getAuthor() {
        return author;
    }

    public String getDefaultBranch() {
        return defaultBranch;
    }

    public Path getFullPath() {
        return fullPath;
    }

    // Setter

    public void setMeta(DocumentMeta meta) {
        this.meta = meta;
    }

    public void setPath(DocumentPath path) {
        this.path = path;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDefaultBranch(String defaultBranch) {
        this.defaultBranch = defaultBranch;
    }

    public void setFullPath(Path fullPath) {
        this.fullPath = fullPath;
    }

}
