package one.nem.lacerta.source.file;

import android.graphics.Bitmap;

import org.w3c.dom.Document;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.util.List;


/** @noinspection unused*/
public interface FileManager {

    File getFileRef();
    boolean isExist(String name) throws IOException;
    boolean isExist() throws IOException;
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

    // Create
    FileManager createFile() throws IOException;
    FileManager createFile(String fileName) throws IOException;
    FileManager createDirectory() throws IOException;
    FileManager createDirectory(String directoryName) throws IOException;
    FileManager createDirectoryIfNotExist() throws IOException;
    FileManager createDirectoryIfNotExist(String directoryName) throws IOException;

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
