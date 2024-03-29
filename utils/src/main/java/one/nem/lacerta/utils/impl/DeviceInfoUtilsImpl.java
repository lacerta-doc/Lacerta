package one.nem.lacerta.utils.impl;

import android.content.Context;

import java.nio.file.Path;
import java.util.Objects;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;
import one.nem.lacerta.utils.repository.DeviceInfoUtils;
public class DeviceInfoUtilsImpl implements DeviceInfoUtils {

    private final Context applicationContext;

    @Inject
    public DeviceInfoUtilsImpl(@ApplicationContext Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Path getExternalStorageDirectory() {
        if (applicationContext.getExternalFilesDir(null) == null) {
            throw new RuntimeException("applicationContext.getExternalFilesDir(null) is null");
        }
        return Objects.requireNonNull(applicationContext.getExternalFilesDir(null)).toPath();
    }

    @Override
    public String getExternalStorageDirectoryString() {
        return getExternalStorageDirectory().toString();
    }

    @Override
    public Path getExternalStorageDirectory(String type) {
        if(applicationContext.getExternalFilesDir(type) == null) {
            throw new RuntimeException("applicationContext.getExternalFilesDir(" + type + ") is null");
        }
        return Objects.requireNonNull(applicationContext.getExternalFilesDir(type)).toPath();
    }

    @Override
    public String getExternalStorageDirectoryString(String type) {
        return getExternalStorageDirectory(type).toString();
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
