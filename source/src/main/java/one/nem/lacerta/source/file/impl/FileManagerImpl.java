package one.nem.lacerta.source.file.impl;

import android.graphics.Bitmap;

import org.w3c.dom.Document;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;
import one.nem.lacerta.source.file.FileManager;

import one.nem.lacerta.utils.LacertaLogger;

public class FileManagerImpl implements FileManager {

    // variables
    private Path rootDir;
    private Path path;
    private boolean autoCreateParent = false;
    private boolean disableRootDirCheck = false;


    // Injection
    private LacertaLogger logger;

    @AssistedInject
    public FileManagerImpl(LacertaLogger logger, @Assisted Path rootDir) {
        this.logger = logger;
        this.rootDir = rootDir;
    }

    // Internal
    private Path resolveStringPath(String path) throws IOException{
        String[] pathArray = path.split("/");
        Path resolvedPath = this.rootDir;
        for (String pathPart : pathArray) {
            if (pathPart.equals("..")) {
                resolvedPath = resolvedPath.getParent();
                continue;
            }

            try {
                resolvedPath = resolvedPath.resolve(pathPart);
            } catch (Exception e) {
                throw new IOException("Invalid path: " + path);
            }
        }
        logger.debug("resolveStringPath", "resolvedPath: " + resolvedPath);
        return resolvedPath;
    }

    @Override
    public File getFileRef() {
        return null;
    }

    @Override
    public boolean isExist() {
        return false;
    }

    @Override
    public boolean isDirectory() {
        return false;
    }

    @Override
    public boolean isFile() {
        return false;
    }

    @Override
    public boolean isReadable() {
        File file = this.path.toFile();
        return file.canRead();
    }

    @Override
    public boolean isWritable() {
        File file = this.path.toFile();
        return file.canWrite();
    }

    @Override
    public Document readXml() {
        return null;
    }

    @Override
    public FileManager enableAutoCreateParent() {
        this.autoCreateParent = true;
        return this;
    }

    @Override
    public FileManager disableRootDirCheck() {
        this.disableRootDirCheck = true;
        return this;
    }

    @Override
    public FileManager setRootDir(Path rootDir) {
        this.rootDir = rootDir;
        return this;
    }

    @Override
    public FileManager setPath(Path path) {
        if (this.disableRootDirCheck) {
            this.path = path;
        } else {
            if (path.startsWith(this.rootDir)) {
                this.path = path;
            } else {
                throw new IllegalArgumentException("path must be in rootDir");
            }
        }
        return this;
    }

    @Override
    public FileManager resolve(String path) throws IOException{
        Path resolvedPath;
        try {
            resolvedPath = resolveStringPath(path);
        } catch (IOException e) {
            logger.error("resolve", e.getMessage());
            throw new IOException("Invalid path: " + path);
        }
        return this.setPath(resolvedPath);
    }
}
