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

    // for generate new instance
    public FileManagerImpl(LacertaLogger logger, Path rootDir, Path path, boolean autoCreateParent, boolean disableRootDirCheck) {
        this.logger = logger;
        this.rootDir = rootDir;
        this.path = path;
        this.autoCreateParent = autoCreateParent;
        this.disableRootDirCheck = disableRootDirCheck;
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

    private FileManager newInstance(Path rootDir, Path path, boolean autoCreateParent, boolean disableRootDirCheck) {
        logger.debug("newInstance", "Generating new instance");
        return new FileManagerImpl(this.logger, rootDir, path, autoCreateParent, disableRootDirCheck);
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
    public boolean isExist(String name) throws IOException {
        Path resolvedPath = this.resolveStringPath(name);
        return Files.exists(resolvedPath);
    }

    @Override
    public boolean isExist(){
        return Files.exists(this.path);
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
        return this.newInstance(rootDir, this.path, this.autoCreateParent, this.disableRootDirCheck);
    }

    @Override
    public FileManager setPath(Path path) {
        Path resolvedPath;
        if (this.disableRootDirCheck) {
            resolvedPath = path;
        } else {
            if (path.startsWith(this.rootDir)) {
                resolvedPath = path;
            } else {
                throw new IllegalArgumentException("path must be in rootDir");
            }
        }
        return this.newInstance(this.rootDir, resolvedPath, this.autoCreateParent, this.disableRootDirCheck);
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
    private void createFileInternal(Path path) throws IOException {
        try {
            if (this.autoCreateParent) {
                if (!path.getParent().toFile().exists()) {
                    Files.createDirectories(path.getParent());
                }
            }
            Files.createFile(path);
        } catch (Exception e) {
            logger.error("createFileInternal", e.getMessage());
            throw new IOException("Failed to create file");
        }
    }

    @Override
    public FileManager createFile() throws IOException {
        this.createFileInternal(this.path);
        return this;
    }

    @Override
    public FileManager createFile(String fileName) throws IOException { // pathが書き換わってしまうのは想像できない挙動かも？
        this.createFileInternal(this.resolveStringPath(fileName));
        return this;
    }

    @Override
    public FileManager createFileIfNotExist() throws IOException {
        if (!this.isExist()) {
            this.createFile();
        }
        return this;
    }

    @Override
    public FileManager createFileIfNotExist(String fileName) throws IOException {
        if (!this.isExist(fileName)) {
            this.createFile(fileName);
        }
        return this;
    }

    // Internal
    private void createDirectoryInternal(Path path) throws IOException {
        try {
            if (this.autoCreateParent) {
                if (!path.getParent().toFile().exists()) {
                    Files.createDirectories(path.getParent());
                }
            }
            Files.createDirectory(path);
        } catch (Exception e) {
            logger.error("createDirectoryInternal", e.getMessage());
            throw new IOException("Failed to create directory");
        }
    }

    @Override
    public FileManager createDirectory() throws IOException {
        this.createDirectoryInternal(this.path);
        return this;
    }

    @Override
    public FileManager createDirectory(String directoryName) throws IOException {
        this.createDirectoryInternal(this.resolveStringPath(directoryName));
        return this;
    }

    @Override
    public FileManager createDirectoryIfNotExist() throws IOException {
        if (!this.isExist()) {
            this.createDirectory();
        }
        return this;
    }

    @Override
    public FileManager createDirectoryIfNotExist(String directoryName) throws IOException {
        if (!this.isExist(directoryName)) {
            this.createDirectory(directoryName);
        }
        return this;
    }

    // Internal
    private void saveXmlInternal(Document document, Path path) throws IOException {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);

            StreamResult result = new StreamResult(path.toFile());

            transformer.transform(source, result);
        } catch (Exception e) {
            logger.error("saveXmlInternal", e.getMessage());
            e.printStackTrace();
            throw new IOException("Failed to save xml");
        }
    }
    private void saveBitmapInternal(Bitmap bitmap, Path path) throws IOException {
        try {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, Files.newOutputStream(path));
        } catch (Exception e) {
            logger.error("saveBitmapInternal", e.getMessage());
            throw new IOException("Failed to save bitmap");
        }
    }

    @Override
    public void saveXml(Document document, String fileName) throws IOException {
        this.saveXmlInternal(document, this.resolveStringPath(fileName));
    }

    @Override
    public void saveXml(Document document) throws IOException {
        this.saveXmlInternal(document, this.path);
    }

    @Override
    public void saveBitmap(Bitmap bitmap, String fileName) throws IOException {
        this.saveBitmapInternal(bitmap, this.resolveStringPath(fileName));
    }

    @Override
    public void saveBitmap(Bitmap bitmap) throws IOException {
        this.saveBitmapInternal(bitmap, this.path);
    }

    // Internal
    private Document loadXmlInternal(Path path) throws IOException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            return builder.parse(Files.newInputStream(path));
        } catch (Exception e) {
            logger.error("loadXmlInternal", e.getMessage());
            throw new IOException("Failed to load xml");
        }
    }
    private Bitmap loadBitmapInternal(Path path) throws IOException {
        try {
            return BitmapFactory.decodeFile(path.toString());
        } catch (Exception e) {
            logger.error("loadBitmapInternal", e.getMessage());
            throw new IOException("Failed to load bitmap");
        }
    }

    @Override
    public Document loadXml(String fileName) throws IOException {
        return this.loadXmlInternal(this.resolveStringPath(fileName));
    }

    @Override
    public Document loadXml() throws IOException {
        return this.loadXmlInternal(this.path);
    }

    @Override
    public Bitmap loadBitmap(String fileName) throws IOException {
        return this.loadBitmapInternal(this.resolveStringPath(fileName));
    }

    @Override
    public Bitmap loadBitmap() throws IOException {
        return this.loadBitmapInternal(this.path);
    }
}
