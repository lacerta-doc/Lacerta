package one.nem.lacerta.model.document;

import java.nio.file.Path;
import java.util.Date;

import one.nem.lacerta.model.document.path.DocumentPath;
import one.nem.lacerta.model.document.DocumentMeta;

/**
 * ドキュメントの詳細データ
 */
public class DocumentDetail {

    /**
     * ドキュメントのメタデータ(DocumentMetaインスタンス)
     */
    DocumentMeta meta;

    /**
     * ドキュメントのパス(DocumentPathインスタンス)
     */
    DocumentPath path;

    /**
     * ドキュメントの作者(String)
     */
    String author;

    /**
     * ドキュメントのデフォルトブランチ(String)
     */
    String defaultBranch;


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


    // Setter

    public void setMeta(DocumentMeta meta) {
        this.meta = meta;
    }

    public void setPath(DocumentPath path) {
        this.path =  path;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDefaultBranch(String defaultBranch) {
        this.defaultBranch = defaultBranch;
    }


}
