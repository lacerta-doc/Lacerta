package one.nem.lacerta.source.file.impl;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.w3c.dom.Document;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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
    private final LacertaLogger logger;

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
        if (this.isExist()) {
            return this.path.toFile();
        } else {
            return null;
        }
    }

    @Override
    public boolean isExist() {
        File file = this.path.toFile();
        return file.exists();
    }

    @Override
    public boolean isDirectory() {
        if (this.isExist()) {
            File file = this.path.toFile();
            return file.isDirectory();
        } else {
            return false;
        }
    }

    @Override
    public boolean isFile() {
        if (this.isExist()) {
            File file = this.path.toFile();
            return file.isFile();
        } else {
            return false;
        }
    }

    @Override
    public boolean isReadable() {
        if (this.isExist()) {
            File file = this.path.toFile();
            return file.canRead();
        } else {
            return false;
        }
    }

    @Override
    public boolean isWritable() {
        if (this.isExist()) {
            File file = this.path.toFile();
            return file.canWrite();
        } else {
            return false;
        }
    }

    @Override
    public FileManager getCurrentInstance() {
        return this;
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

    // Internal
    private void saveXmlInternal(Document document) throws IOException {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);

            StreamResult result = new StreamResult(this.path.toFile());

            transformer.transform(source, result);
        } catch (Exception e) {
            logger.error("saveXmlInternal", e.getMessage());
            throw new IOException("Failed to save xml");
        }
    }
    private void saveBitmapInternal(Bitmap bitmap) throws IOException {
        try {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, Files.newOutputStream(this.path));
        } catch (Exception e) {
            logger.error("saveBitmapInternal", e.getMessage());
            throw new IOException("Failed to save bitmap");
        }
    }

    @Override
    public void saveXml(Document document, String fileName) throws IOException {
        this.resolve(fileName);
        this.saveXmlInternal(document);
    }

    @Override
    public void saveXml(Document document) throws IOException {
        this.saveXmlInternal(document);
    }

    @Override
    public void saveBitmap(Bitmap bitmap, String fileName) throws IOException {
        this.resolve(fileName);
        this.saveBitmapInternal(bitmap);
    }

    @Override
    public void saveBitmap(Bitmap bitmap) throws IOException {
        this.saveBitmapInternal(bitmap);
    }

    // Internal
    private Document loadXmlInternal() throws IOException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            return builder.parse(Files.newInputStream(this.path));
        } catch (Exception e) {
            logger.error("loadXmlInternal", e.getMessage());
            throw new IOException("Failed to load xml");
        }
    }
    private Bitmap loadBitmapInternal() throws IOException {
        try {
            return BitmapFactory.decodeFile(this.path.toString());
        } catch (Exception e) {
            logger.error("loadBitmapInternal", e.getMessage());
            throw new IOException("Failed to load bitmap");
        }
    }

    @Override
    public Document loadXml(String fileName) throws IOException {
        this.resolve(fileName);
        return this.loadXmlInternal();
    }

    @Override
    public Document loadXml() throws IOException {
        return this.loadXmlInternal();
    }

    @Override
    public Bitmap loadBitmap(String fileName) throws IOException {
        this.resolve(fileName);
        return this.loadBitmapInternal();
    }

    @Override
    public Bitmap loadBitmap() throws IOException {
        return this.loadBitmapInternal();
    }
}
