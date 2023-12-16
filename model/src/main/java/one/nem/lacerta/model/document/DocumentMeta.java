package one.nem.lacerta.model.document;

import java.util.ArrayList;
import one.nem.lacerta.model.document.tag.DocumentTag;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * ドキュメントのメタデータ
 */
public class DocumentMeta {
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

    // Constructor
    public DocumentMeta() {
    }

    public DocumentMeta(String id, String title, Date updatedAt, Date createdAt, List<DocumentTag> tags) { // With all
        this.id = id;
        this.title = title;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.tags = tags;
    }

    public DocumentMeta(String id, String title, Date updatedAt, Date createdAt) { // Without tags
        this.id = id;
        this.title = title;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.tags = new ArrayList<>();
    }

    public DocumentMeta(String id, String title) { // Without tags, updatedAt, createdAt
        this.id = id;
        this.title = title;
    }

    public DocumentMeta(String title) { // title only
        this.id = UUID.randomUUID().toString(); // 新規作成時想定なのでコンストラクタで生成してしまう(使う人が楽なので)
        this.title = title;
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
