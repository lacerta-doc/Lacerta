package one.nem.lacerta.model.document;

import java.nio.file.Path;
import java.util.Date;

import one.nem.lacerta.model.document.path.DocumentPath;
import one.nem.lacerta.model.document.DocumentMeta;

/**
 * ドキュメントの詳細データ
 */
public class DocumentDetail {
    // TODO-rca: コンストラクタを追加する

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

    /**
     * ドキュメントのメタデータ(DocumentMetaインスタンス)を取得する
     */
    public DocumentMeta getMeta() {
        return meta;
    }

    /**
     * ドキュメントのパス(DocumentPathインスタンス)を取得する
     */
    public DocumentPath getPath() {
        return path;
    }

    /**
     * ドキュメントの作者(String)を取得する
     */
    public String getAuthor() {
        return author;
    }

    /**
     * ドキュメントのデフォルトブランチ(String)を取得する
     */
    public String getDefaultBranch() {
        return defaultBranch;
    }


    // Setter

    /**
     * ドキュメントのメタデータ(DocumentMetaインスタンス)を設定する
     * @param meta DocumentMetaインスタンス
     */
    public void setMeta(DocumentMeta meta) {
        this.meta = meta;
    }

    /**
     * ドキュメントのパス(DocumentPathインスタンス)を設定する
     * @param path DocumentPathインスタンス
     */
    public void setPath(DocumentPath path) {
        this.path =  path;
    }

    /**
     * ドキュメントの作者(String)を設定する
     * @param author ドキュメントの作者
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * ドキュメントのデフォルトブランチ(String)を設定する
     * @param defaultBranch ドキュメントのデフォルトブランチ
     */
    public void setDefaultBranch(String defaultBranch) {
        this.defaultBranch = defaultBranch;
    }


}
