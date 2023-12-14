package one.nem.lacerta.model.document.tag;

/**
 * ドキュメントのタグ
 */
public class DocumentTag {

    /**
     * タグのID(String)
     */
    String id;

    /**
     * タグの名前(String)
     */
    String name;

    /**
     * タグの色(String)
     */
    String color;

    // Getter

    /**
     * タグのID(String)を取得する
     */
    public String getId() {
        return id;
    }

    /**
     * タグの名前(String)を取得する
     */
    public String getName() {
        return name;
    }

    /**
     * タグの色(String)を取得する
     */
    public String getColor() {
        return color;
    }

    // Setter

    /**
     * タグのID(String)を設定する
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * タグの名前(String)を設定する
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * タグの色(String)を設定する
     */
    public void setColor(String color) {
        this.color = color;
    }


}
