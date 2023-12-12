package oen.nem.lacerta.utils.repository;

import java.nio.file.Path;

public interface UtilsRepo {

    Path getExternalFilesDirPath(String type);

    Path getExternalFilesDirPath();

}
