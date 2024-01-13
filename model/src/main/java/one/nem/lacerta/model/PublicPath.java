package one.nem.lacerta.model;

public class PublicPath {
    /*
     * ユーザーが扱うパス(内部パスの代替)
     */

    String[] path;

    public PublicPath() {
        this.path = new String[0];
    }

    public PublicPath load(String[] path) {
        this.path = path;
        return this;
    }

    public PublicPath load(String path) {
        this.path = path.split("/");
        return this;
    }
}
