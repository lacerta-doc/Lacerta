package one.nem.lacerta.utils.repository;

import java.nio.file.Path;

public interface DeviceInfoUtils {

    // External storage directory
    Path getExternalStorageDirectory();

    String getExternalStorageDirectoryString();

    Path getExternalStorageDirectory(String type);

    String getExternalStorageDirectoryString(String type);

    // Permission
    boolean hasPermission(String permission);

    boolean hasPermissions(String[] permissions);

}
