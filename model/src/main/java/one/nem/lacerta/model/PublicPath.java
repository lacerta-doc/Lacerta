package one.nem.lacerta.model;

import java.util.ArrayList;
import java.util.List;

public class PublicPath {
    /*
     * ユーザーが扱うパス(内部パスの代替)
     * (時間がないのでInjectされることは考慮しない)
     *
     * TODO-rca:
     *  - こわれたパスを検知する
     *  - バリデーション
     */

    List<String> path = new ArrayList<String>();

    public PublicPath() {
    }

    public PublicPath(List<String> path) {
        this.path = path;
    }

    private void add(String path) {
        this.path.add(path);
    }

    private void resolveInternal(String path) {
        if (path.startsWith("/")) {
            this.path.clear();
            this.path.add("/");
        } else {
            if (path.equals("..")) {
                this.path.remove(this.path.size() - 1);
            } else if (path.equals(".")) {
                // do nothing
            } else {
                this.path.add(path);
            }
        }
    }

    public PublicPath resolve(String path) {
        resolveInternal(path);
        return this;
    }

    public PublicPath resolve(List<String> path) {
        for (String p : path) {
            resolveInternal(p);
        }
        return this;
    }

    public PublicPath resolve(PublicPath path) {
        for (String p : path.getPath()) {
            resolveInternal(p);
        }
        return this;
    }

    public PublicPath parent() {
        this.path.remove(this.path.size() - 1);
        return this;
    }

    public List<String> getPath() {
        return path;
    }

    public String getStringPath() {
        return String.join("/", path);
    }

    public PublicPath getRoot() {
        PublicPath root = new PublicPath();
        root.add("/");
        return root;
    }
}
