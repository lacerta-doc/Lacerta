package one.nem.lacerta.model.document.path;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * ドキュメントのパス
 */
public class DocumentPath {
    // TODO-rca: コンストラクタを追加する

    /**
     * ドキュメントのルートパス(String)
     */
    String rootPath;

    /**
     * ドキュメントのパス(String)
     */
    String path;

    // Constructor

    public DocumentPath() {
    }

    public DocumentPath(String rootPath, String path) {
        this.rootPath = rootPath;
        this.path = path;
    }

    // Getter

    /**
     * ドキュメントのルートパス(String)を取得する
     */
    public String getRootPath() {
        return rootPath;
    }

    /**
     * ドキュメントのパス(String)を取得する
     */
    public String getPath() {
        return path;
    }

    /**
     * ドキュメントのフルパス(Path)を取得する
     */
    public Path getFullPath() {
        // rootPathとpathを結合して返す
        return Paths.get(rootPath, path);
    }

    /**
     * ドキュメントのフルパス(String)を取得する
     */
    public String getFullPathString() {
        // rootPathとpathを結合して返す
        return Paths.get(rootPath, path).toString();
    }

    // Setter

    /**
     * ドキュメントのルートパス(String)を設定する
     * @param rootPath ドキュメントのルートパス
     */
    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    /**
     * ドキュメントのパス(String)を設定する
     * @param path ドキュメントのパス
     */
    public void setPath(String path) {
        this.path = path;
    }

}
