package one.nem.lacerta.data.impl;

import android.content.Context;

import java.nio.file.Path;
import java.util.Objects;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;
import one.nem.lacerta.data.repository.Commons;

import one.nem.lacerta.data.utils.UtilsRepo; // Internal Utils Repo

public class CommonsImpl implements Commons{

    @Inject
    UtilsRepo utilsRepo;

    public Path getExternalFilesDirPath(String type) {
        return utilsRepo.getExternalFilesDirPath(type);
    }

    public Path getExternalFilesDirPath() {
        return utilsRepo.getExternalFilesDirPath();
    }

}
