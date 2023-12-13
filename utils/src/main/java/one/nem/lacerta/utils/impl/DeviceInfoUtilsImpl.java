package one.nem.lacerta.utils.impl;

import java.nio.file.Path;

import one.nem.lacerta.utils.repository.DeviceInfoUtils;
public class DeviceInfoUtilsImpl implements DeviceInfoUtils {


    @Override
    public Path getExternalStorageDirectory() {
        return null;
    }

    @Override
    public Path getExternalStorageDirectory(String type) {
        return null;
    }

    @Override
    public boolean hasPermission(String permission) {
        return false;
    }

    @Override
    public boolean hasPermissions(String[] permissions) {
        return false;
    }
}
