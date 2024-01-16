package one.nem.lacerta.model.document;

import java.util.ArrayList;

import one.nem.lacerta.model.PublicPath;
import one.nem.lacerta.model.document.tag.DocumentTag;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * ドキュメントのメタデータ
 */
public class DocumentMeta { // TODO-rca: JavaDoc対応
    /**
     * ドキュメントのID(String)
     */
    String id;

    /**
     * ドキュメントのタイトル(String)
     */
    String title;

    /**
     * ドキュメントの更新日時(Date)
     */
    Date updatedAt;

    /**
     * ドキュメントの作成日時(Date)
     */
    Date createdAt;

    /**
     * ドキュメントのタグ(DocumentTagインスタンスのリスト)
     */
    List<DocumentTag> tags;

    PublicPath path;

    String author;

    String defaultBranch;

    // Constructor

    public DocumentMeta() {
    }

    public DocumentMeta(String title) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.tags = new ArrayList<>();
        this.author = ""; // TODO-rca: 作者のデフォルト値を設定できるようにする
        this.defaultBranch = "main"; // TODO-rca: デフォルトブランチのデフォルト値を設定できるようにする
        this.path = new PublicPath().getRoot();
        this.updatedAt = new Date();
        this.createdAt = new Date();
    }

    public DocumentMeta(String title, List<DocumentTag> tags, String author, String defaultBranch) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.tags = tags;
        this.author = author;
        this.defaultBranch = defaultBranch;
    }

    public DocumentMeta(String id, String title, List<DocumentTag> tags, String author, String defaultBranch) {
        this.id = id;
        this.title = title;
        this.tags = tags;
        this.author = author;
        this.defaultBranch = defaultBranch;
    }

    public DocumentMeta(String id, String title, Date updatedAt, Date createdAt, List<DocumentTag> tags, String author, String defaultBranch) {
        this.id = id;
        this.title = title;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.tags = tags;
        this.author = author;
        this.defaultBranch = defaultBranch;
    }

    public DocumentMeta(String id, String title, Date updatedAt, Date createdAt, List<DocumentTag> tags, PublicPath path, String author, String defaultBranch) {
        this.id = id;
        this.title = title;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.tags = tags;
        this.path = path;
        this.author = author;
        this.defaultBranch = defaultBranch;
    }

    // Getter

    /**
     * ドキュメントのID(String)を取得する
     */
    public String getId() {
        return id;
    }

    /**
     * ドキュメントのタイトル(String)を取得する
     */
    public String getTitle() {
        return title;
    }

    /**
     * ドキュメントの更新日時(Date)を取得する
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * ドキュメントの作成日時(Date)を取得する
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * ドキュメントのタグ(DocumentTagインスタンスのリスト)を取得する
     */
    public List<DocumentTag> getTags() {
        return tags;
    }

    /**
     * ドキュメントのタグ(DocumentTagインスタンスのリスト)のID(String)を取得する
     */
    public List<String> getTagIds() {
        List<String> tagIds = new ArrayList<>();
        for (DocumentTag tag : tags) {
            tagIds.add(tag.getId());
        }
        return tagIds;
    }

    /**
     * PublicPathを取得する
     */
    public PublicPath getPath() {
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
     * ドキュメントのID(String)を設定する
     * @param id ドキュメントのID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * ドキュメントのタイトル(String)を設定する
     * @param title ドキュメントのタイトル
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * ドキュメントの更新日時(Date)を設定する
     * @param updatedAt ドキュメントの更新日時
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * ドキュメントの作成日時(Date)を設定する
     * @param createdAt ドキュメントの作成日時
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * ドキュメントのタグ(DocumentTagインスタンスのリスト)を設定する
     * @param tags ドキュメントのタグ(DocumentTagインスタンスのリスト)
     */
    public void setTags(List<DocumentTag> tags) {
        this.tags = tags;
    }

    /**
     * PublicPathを設定する
     * @param path PublicPath
     */
    public void setPath(PublicPath path) {
        this.path = path;
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
     * updatedAtを現在時刻に設定する
     */
    public void setUpdatedAtNow() {
        this.updatedAt = new Date();
    }

    /**
     * createdAtを現在時刻に設定する
     */
    public void setCreatedAtNow() {
        this.createdAt = new Date();
    }
}
