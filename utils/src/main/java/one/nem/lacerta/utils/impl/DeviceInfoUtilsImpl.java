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
        // TODO-rca: 結果がnullだった場合の処理を追加する？
        return Objects.requireNonNull(applicationContext.getExternalFilesDir(null)).toPath();
    }

    @Override
    public Path getExternalStorageDirectory(String type) {
        // TODO-rca: 結果がnullだった場合の処理を追加する？
        return Objects.requireNonNull(applicationContext.getExternalFilesDir(type)).toPath();
    }

    @Override
    public boolean hasPermission(String permission) {
        return null
    }

    @Override
    public boolean hasPermissions(String[] permissions) {
        return false;
    }
}
