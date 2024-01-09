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
    Document readXml();

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
    FileManager saveXml(Document document, String fileName) throws IOException;
    FileManager saveXml(Document document) throws IOException;

    // Bitmap
    FileManager saveBitmap(Bitmap bitmap, String fileName) throws IOException; // TODO-rca: パラメータに対応させる
    FileManager saveBitmap(Bitmap bitmap) throws IOException; // TODO-rca: パラメータに対応させる


    // Load
    // XML
    Document loadXml(String fileName) throws IOException;
    Document loadXml() throws IOException;

    // Bitmap
    Bitmap loadBitmap(String fileName) throws IOException;
    Bitmap loadBitmap() throws IOException;

}
