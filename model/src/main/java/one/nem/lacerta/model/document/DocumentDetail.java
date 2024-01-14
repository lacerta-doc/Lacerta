package one.nem.lacerta.model.document;

import org.eclipse.jgit.lib.Repository;

import java.nio.file.Path;
import java.util.Date;

import one.nem.lacerta.model.document.path.DocumentPath;
import one.nem.lacerta.model.PublicPath;
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
     * ユーザーが扱うパス
     */
    PublicPath publicPath;

    /**
     * ドキュメントの作者(String)
     */
    String author;

    /**
     * ドキュメントのデフォルトブランチ(String)
     */
    String defaultBranch;

    Repository repository;

    // Constructor
    public DocumentDetail() {
    }

    public DocumentDetail(DocumentMeta meta, PublicPath publicPath, String author, String defaultBranch) {
        this.meta = meta;
        this.publicPath = publicPath;
        this.author = author;
        this.defaultBranch = defaultBranch;
    }

    // Getter

    /**
     * ドキュメントのメタデータ(DocumentMetaインスタンス)を取得する
     */
    public DocumentMeta getMeta() {
        return meta;
    }

    /**
     * ユーザーが扱うパスを取得する
     */
    public PublicPath getPublicPath() {
        return publicPath;
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

    /**
     * ドキュメントのリポジトリを取得する
     */
    public Repository getRepository() {
        return repository;
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
     * ユーザーが扱うパスを設定する
     * @param publicPath ユーザーが扱うパス
     */
    public void setPublicPath(PublicPath publicPath) {
        this.publicPath = publicPath;
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

    /**
     * ドキュメントのリポジトリを設定する
     */
    public void setRepository(Repository repository) {
        this.repository = repository;
    }

}
