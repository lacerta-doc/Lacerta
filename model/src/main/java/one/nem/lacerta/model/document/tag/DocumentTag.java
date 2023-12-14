package one.nem.lacerta.model.document.tag;

/**
 * ドキュメントのタグ
 */
public class DocumentTag {
    // TODO-rca: コンストラクタを追加する

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
     * @param id タグのID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * タグの名前(String)を設定する
     * @param name タグの名前
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * タグの色(String)を設定する
     * @param color タグの色
     */
    public void setColor(String color) {
        this.color = color;
    }


}
