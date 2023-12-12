package one.nem.lacerta.data.impl;

import android.content.Context;

import java.nio.file.Path;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;
import one.nem.lacerta.data.repository.Commons;

public class CommonsImpl implements Commons{

    private Context applicationContext;

    @Inject
    public CommonsImpl(@ApplicationContext Context applicationContext) {
        this.applicationContext = applicationContext;
    }


    public Path getExternalFilesDirPath(String type) {
        return applicationContext.getExternalFilesDir(type).toPath();
    }

    public Path getExternalFilesDirPath() {
        return applicationContext.getExternalFilesDir(null).toPath();
    }

}
