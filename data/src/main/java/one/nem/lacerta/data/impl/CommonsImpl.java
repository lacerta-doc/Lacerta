package one.nem.lacerta.data.impl;

import android.content.Context;

import java.nio.file.Path;
import java.util.Objects;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;
import one.nem.lacerta.data.repository.Commons;

import one.nem.lacerta.utils.repository.FileUtils;

public class CommonsImpl implements Commons{

    @Inject
    public CommonsImpl() {
    }

    @Inject
    FileUtils fileUtils;

    public Path getExternalFilesDirPath(String type) {
        return fileUtils.getExternalFilesDirPath(type);
    }

    public Path getExternalFilesDirPath() {
        return fileUtils.getExternalFilesDirPath();
    }

}
