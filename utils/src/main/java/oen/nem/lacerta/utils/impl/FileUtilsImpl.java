package oen.nem.lacerta.utils.impl;

import android.content.Context;

import java.nio.file.Path;
import java.util.Objects;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;

import oen.nem.lacerta.utils.repository.FileUtils;

public class FileUtilsImpl implements FileUtils {

    private final Context applicationContext;

    @Inject
    public FileUtilsImpl(@ApplicationContext Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    public Path getExternalFilesDirPath(String type) {
        return Objects.requireNonNull(applicationContext.getExternalFilesDir(type)).toPath();
    }

    public Path getExternalFilesDirPath() {
        return Objects.requireNonNull(applicationContext.getExternalFilesDir(null)).toPath();
    }
}
