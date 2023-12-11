package one.nem.lacerta.data.model.documents;

import java.util.Date;

import one.nem.lacerta.data.model.documents.enums.DocumentType;

// TODO-rca: Dateをデバイスのロケールに合わせてStringに変換するメソッドを実装する?
public class DocumentMeta {
    // ドキュメントのメタ情報
    public String id; // ドキュメントの内部ID(UUIDv4?)
    public String name; // ドキュメントの名前
    public DocumentType type; // ドキュメントの種類
    public Date created; // ドキュメントの作成日時
    public String[] tags; // ドキュメントのタグ
    public String[] categories; // ドキュメントのカテゴリ

    public DocumentMeta(String id, String name, Date created, DocumentType type, String[] tags, String[] categories) {
        this.id = id;
        this.name = name;
        this.created = created;
        this.type = type;
        this.tags = tags;
        this.categories = categories;
    }

    public DocumentMeta() {
        this.id = "";
        this.name = "";
        this.created = new Date();
        this.type = DocumentType.OTHER;
        this.tags = new String[0];
        this.categories = new String[0];
    }

    // TODO-rca: ボイラープレートコードなので削減する
    // Getter

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getCreated() {
        return created;
    }

    public DocumentType getType() {
        return type;
    }

    public String[] getTags() {
        return tags;
    }

    public String[] getCategories() {
        return categories;
    }

    // Setter

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        if (name == null) {
            this.name = "";
        } else {
            this.name = name;
        }
    }

    public void setCreated(Date created) {
        if (created == null) {
            this.created = new Date();
        } else {
            this.created = created;
        }
    }

    public void setType(DocumentType type) {
        if (type == null) {
            this.type = DocumentType.OTHER;
        } else {
            this.type = type;
        }
    }

    public void setTags(String[] tags) {
        if (tags == null) {
            this.tags = new String[0];
        } else {
            this.tags = tags;
        }
    }

    public void setCategories(String[] categories) {
        if (categories == null) {
            this.categories = new String[0];
        } else {
            this.categories = categories;
        }
    }
}
