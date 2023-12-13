package one.nem.lacerta.utils.impl;

import android.content.Context;

import java.nio.file.Path;

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
        return
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
