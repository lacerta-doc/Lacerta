package one.nem.lacerta.utils.repository;

import java.nio.file.Path;

public interface FileUtils {

    Path getExternalFilesDirPath(String type);

    Path getExternalFilesDirPath();

}
