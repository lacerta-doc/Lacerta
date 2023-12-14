package one.nem.lacerta.model.document;

import java.util.ArrayList;
import one.nem.lacerta.model.document.tag.DocumentTag;
import java.util.Date;
import java.util.List;

/**
 * ドキュメントのメタデータ
 */
public class DocumentMeta {
    // TODO-rca: 作成日時なしでインスタンス化できるようにする？

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
}
