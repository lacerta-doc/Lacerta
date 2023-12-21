package one.nem.lacerta.source.file.impl;

import android.graphics.Bitmap;

import java.nio.file.Path;
import java.util.Arrays;
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
        Path convertedPath = currentDir.resolve(path);
        if (convertedPath.startsWith(rootDir)) { // 異常なパスの場合はnullを返す // TODO-rca: エラーハンドリング
            return convertedPath;
        } else {
            return null;
        }
    }

    @Inject
    public FileManagerImpl(Path rootDir) {
        // TODO-rca: 未実装
    }

    @Override
    public Path getRootDir() {
        return rootDir;
    }

    @Override
    public void changeDir(String dirName) {
        this.currentDir = rootDir.resolve(dirName);
    }

    @Override
    public void backDir() {
        this.currentDir = currentDir.getParent();
    }

    @Override
    public void backRootDir() {
        this.currentDir = rootDir;
    }

    @Override
    public List<String> getDirList() {
        // currentDirにあるディレクトリの一覧を返す
        return Arrays.asList(currentDir.toFile().list());
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
