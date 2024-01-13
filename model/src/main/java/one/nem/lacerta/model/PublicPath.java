package one.nem.lacerta.model;

import java.util.ArrayList;
import java.util.List;

public class PublicPath {
    /*
     * ユーザーが扱うパス(内部パスの代替)
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

    public PublicPath resolve(String path) {
        if (path.equals("..")) {
            this.path.remove(this.path.size() - 1);
        } else {
            add(path);
        }
        return this;
    }

    public PublicPath resolve(List<String> path) {
        for (String p : path) {
            resolve(p);
        }
        return this;
    }

    public List<String> getPath() {
        return path;
    }

    public String getStringPath() {
        return String.join("/", path);
    }
}
