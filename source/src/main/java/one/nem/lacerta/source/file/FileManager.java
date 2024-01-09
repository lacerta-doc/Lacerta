package one.nem.lacerta.source.file;

import android.graphics.Bitmap;

import org.w3c.dom.Document;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;


/** @noinspection unused*/
public interface FileManager {

    File getFileRef();
    boolean isExist();
    boolean isDirectory();
    boolean isFile();
    boolean isWritable();
    boolean isReadable();
    // Get current instance
    FileManager getCurrentInstance();

    // Configure
    FileManager enableAutoCreateParent();
    FileManager disableRootDirCheck();

    FileManager setRootDir(Path rootDir);
    FileManager setPath(Path path);
    FileManager resolve(String path) throws IOException;

    // Save
    // XML
    void saveXml(Document document, String fileName) throws IOException;
    void saveXml(Document document) throws IOException;

    // Bitmap
    void saveBitmap(Bitmap bitmap, String fileName) throws IOException; // TODO-rca: パラメータに対応させる
    void saveBitmap(Bitmap bitmap) throws IOException; // TODO-rca: パラメータに対応させる


    // Load
    // XML
    Document loadXml(String fileName) throws IOException;
    Document loadXml() throws IOException;

    // Bitmap
    Bitmap loadBitmap(String fileName) throws IOException;
    Bitmap loadBitmap() throws IOException;

}
