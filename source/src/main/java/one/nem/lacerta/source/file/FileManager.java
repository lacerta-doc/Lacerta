package one.nem.lacerta.source.file;

import android.graphics.Bitmap;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

public interface FileManager {

    Path getRootDir();
    void changeDir(String dirName); //cd
    void backDir(); //cd ..
    void backRootDir(); //cd /
    List<Path> getList();
    void createDir(String dirName);
    void removeDir(String dirName);

    void saveBitmap(Path path, Bitmap bitmap);
    Bitmap loadBitmap(Path path);
    void removeBitmap(Path path);

}
