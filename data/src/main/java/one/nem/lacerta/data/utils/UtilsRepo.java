package one.nem.lacerta.data.utils;

import java.nio.file.Path;

public interface UtilsRepo {

    Path getExternalFilesDirPath(String type);

    Path getExternalFilesDirPath();
}
