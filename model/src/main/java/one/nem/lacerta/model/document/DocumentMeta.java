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

    String parentId;

    String author;

    boolean isCombineChild;

    boolean isCombineParent;

    // Constructor

    public DocumentMeta() {
    }

    public DocumentMeta(String title) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.author = ""; // TODO-rca: 作者のデフォルト値を指定できるように
        this.parentId = null;
        this.updatedAt = new Date();
        this.createdAt = new Date();
        this.isCombineChild = false;
        this.isCombineParent = false;
    }

    public DocumentMeta(String title, String author) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.author = author;
        this.isCombineChild = false;
        this.isCombineParent = false;
    }

    public DocumentMeta(String id, String title, Date updatedAt, Date createdAt, String author) {
        this.id = id;
        this.title = title;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.author = author;
        this.isCombineChild = false;
        this.isCombineParent = false;
    }

    public DocumentMeta(String id, String title, Date updatedAt, Date createdAt, String parentId, String author) {
        this.id = id;
        this.title = title;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.parentId = parentId;
        this.author = author;
        this.isCombineChild = false;
        this.isCombineParent = false;
    }

    public DocumentMeta(String id, String title, Date updatedAt, Date createdAt, String parentId, String author, boolean isCombineChild, boolean isCombineParent) {
        this.id = id;
        this.title = title;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.parentId = parentId;
        this.author = author;
        this.isCombineChild = isCombineChild;
        this.isCombineParent = isCombineParent;
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
     * ドキュメントの親フォルダのID(String)を取得する
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * ドキュメントの作者(String)を取得する
     */
    public String getAuthor() {
        return author;
    }

    /**
     * ドキュメントの結合子フラグ(boolean)を取得する
     */
    public boolean getIsCombineChild() {
        return isCombineChild;
    }

    /**
     * ドキュメントの結合親フラグ(boolean)を取得する
     */
    public boolean getIsCombineParent() {
        return isCombineParent;
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
     * ドキュメントの親フォルダのID(String)を設定する
     * @param parentId ドキュメントの親フォルダのID
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * ドキュメントの作者(String)を設定する
     * @param author ドキュメントの作者
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * ドキュメントの結合子フラグ(boolean)を設定する
     * @param isCombineChild ドキュメントの結合子フラグ
     */
    public void setIsCombineChild(boolean isCombineChild) {
        this.isCombineChild = isCombineChild;
    }

    /**
     * ドキュメントの結合親フラグ(boolean)を設定する
     * @param isCombineParent ドキュメントの結合親フラグ
     */
    public void setIsCombineParent(boolean isCombineParent) {
        this.isCombineParent = isCombineParent;
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
