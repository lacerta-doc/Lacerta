package one.nem.lacerta.source.file;

import android.graphics.Bitmap;

import java.io.File;
import java.nio.file.Path;
import java.util.List;


public interface FileManager {

    Path getRootDir();
    void changeDir(String dirName); //cd
    void changeDir(Path path); //cd
    void backDir(); //cd ..
    void backRootDir(); //cd /
    List<Path> getList();
    void createDir(String dirName);
    void createDir(Path path);
    void removeDir(String dirName);
    void removeDir(Path path);

    File createFile(String fileName);
    void removeFile(String fileName);

    File getFile(String fileName);
    File getFile(Path path);

    String loadText(String fileName);
    String loadText(Path path);

    void saveText(String text, String fileName);
    void saveText(String text, Path path);

    boolean isExist(Path path);
    boolean isExist(String fileName);

    void autoCreateDir(Path path);
    void autoCreateDir(String dirName);

    void autoCreateToCurrentDir();

    void saveBitmapAtCurrent(Bitmap bitmap, String fileName);
    Bitmap loadBitmap(Path path);
    void removeBitmap(Path path);

}
