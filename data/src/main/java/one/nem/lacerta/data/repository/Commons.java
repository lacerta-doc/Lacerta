package one.nem.lacerta.data.repository;

import java.nio.file.Path;

public interface Commons {

    Path getExternalFilesDirPath(String type);

    Path getExternalFilesDirPath();

}