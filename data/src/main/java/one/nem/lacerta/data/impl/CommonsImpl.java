package one.nem.lacerta.data.impl;

import java.nio.file.Path;

import javax.inject.Inject;

import one.nem.lacerta.data.repository.Commons;

import one.nem.lacerta.utils.repository.DeviceInfoUtils;

public class CommonsImpl implements Commons{

    @Inject
    public CommonsImpl() {
    }

    @Inject
    DeviceInfoUtils fileUtils;

    public Path getExternalFilesDirPath(String type) {
        return fileUtils.getExternalFilesDirPath(type);
    }

    public Path getExternalFilesDirPath() {
        return fileUtils.getExternalFilesDirPath();
    }

}
