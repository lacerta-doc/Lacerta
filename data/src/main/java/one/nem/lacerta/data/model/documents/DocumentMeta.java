package one.nem.lacerta.data.model.documents;

import one.nem.lacerta.data.model.documents.enums.DocumentType;
public class DocumentMeta {
    // ドキュメントのメタ情報
    public String id; // ドキュメントの内部ID(UUIDv4?)
    public String name; // ドキュメントの名前
    public String description; // ドキュメントの説明
    public DocumentType type; // ドキュメントの種類
    public String[] tags; // ドキュメントのタグ
    public String[] categories; // ドキュメントのカテゴリ

    public DocumentMeta(String id, String name, String description, DocumentType type, String[] tags, String[] categories) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.tags = tags;
        this.categories = categories;
    }

    public DocumentMeta() {
        this.id = "";
        this.name = "";
        this.description = "";
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

    public String getDescription() {
        return description;
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

    public void setDescription(String description) {
        if (description == null) {
            this.description = "";
        } else {
            this.description = description;
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
