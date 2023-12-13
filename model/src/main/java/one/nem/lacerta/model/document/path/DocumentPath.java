package one.nem.lacerta.model.document.path;

import java.nio.file.Path;
import java.nio.file.Paths;

public class DocumentPath {

        String rootPath;
        String path;

        // Getter

        public String getRootPath() {
            return rootPath;
        }

        public String getPath() {
            return path;
        }

        public Path getFullPath() {
            // rootPathとpathを結合して返す
            return Paths.get(rootPath, path);
        }

        public String getFullPathString() {
            // rootPathとpathを結合して返す
            return Paths.get(rootPath, path).toString();
        }

        // Setter

        public void setRootPath(String rootPath) {
            this.rootPath = rootPath;
        }

        public void setPath(String path) {
            this.path = path;
        }

}
