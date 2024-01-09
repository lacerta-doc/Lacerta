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

    // Configure
    FileManager enableAutoCreateParent();
    FileManager disableRootDirCheck();

    FileManager setRootDir(Path rootDir);
    FileManager setPath(Path path);
    FileManager resolve(String path) throws IOException;
}
