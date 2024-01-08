package one.nem.lacerta.source.file.impl;

import android.graphics.Bitmap;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;
import one.nem.lacerta.source.file.FileManager;

import one.nem.lacerta.utils.LacertaLogger;

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

    // Injection
    private LacertaLogger logger;

    @AssistedInject
    public FileManagerImpl(LacertaLogger logger, @Assisted Path rootDir) {
        this.logger = logger;
        this.rootDir = rootDir;
        this.currentDir = rootDir;
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
    public List<Path> getList() {
        List<Path> list = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(currentDir)) {
            for (Path entry : stream) { // TODO-rca: エラーハンドリング, 効率化
                list.add(entry);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void createDir(String dirName) {
        //ディレクトリ作成
        currentDir.resolve(dirName).toFile().mkdir(); // TODO-rca: エラーハンドリング
    }

    @Override
    public void removeDir(String dirName) {
        currentDir.resolve(dirName).toFile().delete(); // TODO-rca: エラーハンドリング
    }

    @Override
    public File createFile(String fileName) {
        return currentDir.resolve(fileName).toFile();
    }

    @Override
    public void removeFile(String fileName) {
        currentDir.resolve(fileName).toFile().delete(); // TODO-rca: エラーハンドリング
    }


    @Override
    public void saveBitmapAtCurrent(Bitmap bitmap, String fileName) { // TODO-rca: ファイル形式を変更できるようにする？
        try {
            File file = currentDir.resolve(fileName).toFile();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, Files.newOutputStream(file.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Bitmap loadBitmap(Path path) {
        return null;
    }

    @Override
    public void removeBitmap(Path path) {

    }

}
