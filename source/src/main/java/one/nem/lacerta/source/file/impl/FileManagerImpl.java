package one.nem.lacerta.source.file.impl;

import android.graphics.Bitmap;

import java.nio.file.Path;
import java.util.List;

import javax.inject.Inject;

import one.nem.lacerta.source.file.FileManager;

public class FileManagerImpl implements FileManager {

    // RootDir
    private Path rootDir;

    // CurrentDir
    private Path currentDir;

    // Internal Methods
    private Path convertPath(String path) {
        return null;
    }

    @Inject
    public FileManagerImpl(Path rootDir) {
        // TODO-rca: 未実装
    }

    @Override
    public Path getRootDir() {
        return null;
    }

    @Override
    public void changeDir(String dirName) {

    }

    @Override
    public void backDir() {

    }

    @Override
    public void backRootDir() {

    }

    @Override
    public List<String> getDirList() {
        return null;
    }

    @Override
    public List<String> getFileList() {
        return null;
    }

    @Override
    public void createDir() {

    }

    @Override
    public void removeDir() {

    }
}
