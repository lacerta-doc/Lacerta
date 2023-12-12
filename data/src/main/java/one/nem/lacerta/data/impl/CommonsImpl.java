package one.nem.lacerta.data.impl;

import android.content.Context;

import java.nio.file.Path;
import java.util.Objects;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;
import one.nem.lacerta.data.repository.Commons;

public class CommonsImpl implements Commons{

    private final Context applicationContext;

    @Inject
    public CommonsImpl(@ApplicationContext Context applicationContext) {
        this.applicationContext = applicationContext;
    }


    public Path getExternalFilesDirPath(String type) {
        return Objects.requireNonNull(applicationContext.getExternalFilesDir(type)).toPath();
    }

    public Path getExternalFilesDirPath() {
        return Objects.requireNonNull(applicationContext.getExternalFilesDir(null)).toPath();
    }

}
