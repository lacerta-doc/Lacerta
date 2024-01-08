package one.nem.lacerta.source.file.impl;

import android.graphics.Bitmap;

import org.w3c.dom.Document;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
    public void changeDir(Path path) {
        if (path.startsWith(rootDir)) {
            this.currentDir = path;
        }
        else {
            logger.debug("changeDir", "invalid path: " + path);
            // TODO-rca: 例外を投げる
        }
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
        logger.debug("createDir", "called");

        Path path = currentDir.resolve(dirName);
        logger.debug("createDir", "path: " + path);
        try {
            Files.createDirectory(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createDir(Path path) {
        logger.debug("createDir", "called");
        try {
            Files.createDirectory(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeDir(String dirName) {
        logger.debug("removeDir", "called");
        currentDir.resolve(dirName).toFile().delete(); // TODO-rca: エラーハンドリング
    }

    @Override
    public void removeDir(Path path) {
        logger.debug("removeDir", "called");
        path.toFile().delete(); // TODO-rca: エラーハンドリング
    }


    @Override
    public File createFile(String fileName) {
        logger.debug("createFile", "called");
        return currentDir.resolve(fileName).toFile();
    }

    @Override
    public void removeFile(String fileName) {
        logger.debug("removeFile", "called");
        currentDir.resolve(fileName).toFile().delete(); // TODO-rca: エラーハンドリング
    }

    @Override
    public File getFile(String fileName) {
        logger.debug("getFile", "called");
        return currentDir.resolve(fileName).toFile();
    }

    @Override
    public File getFile(Path path) {
        logger.debug("getFile", "called");
        return path.toFile();
    }

    @Override
    public String loadText(String fileName) { // TODO-rca: 統合
        try(FileInputStream fileInputStream = new FileInputStream(currentDir.resolve(fileName).toFile())) {
            byte[] bytes = new byte[fileInputStream.available()];
            fileInputStream.read(bytes); // TODO-rca: エラーハンドリング
            return new String(bytes);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String loadText(Path path) {
        try(FileInputStream fileInputStream = new FileInputStream(path.toFile())) {
            byte[] bytes = new byte[fileInputStream.available()];
            fileInputStream.read(bytes); // TODO-rca: エラーハンドリング
            return new String(bytes);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void saveText(String text, String fileName) { // TODO-rca: リファクタリング // TODO-rca: 統合
        if (isExist(fileName)) {
            logger.debug("saveText", "file already exists");
            // Overwrite
            try {
                Files.write(currentDir.resolve(fileName), text.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                Files.createFile(currentDir.resolve(fileName));
                Files.write(currentDir.resolve(fileName), text.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void saveText(String text, Path path) {
        if (isExist(path)) {
            logger.debug("saveText", "file already exists");
            // Overwrite
            try {
                Files.write(path, text.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                Files.createFile(path);
                Files.write(path, text.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void saveDocument(Document document, String fileName) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            File file = createFile(fileName);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveDocument(Document document, Path path) {
        // TODO-rca 実装する
    }

    @Override
    public Document loadDocument(String fileName) {
        try {
            File file = getFile(fileName);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);
            return document;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Document loadDocument(Path path) {
        // TODO-rca 実装する
        return null;
    }

    @Override
    public boolean isExist(Path path) {
        logger.debug("isExist", "called");
        return Files.exists(path);
    }

    @Override
    public boolean isExist(String fileName) {
        logger.debug("isExist", "called");
        return Files.exists(currentDir.resolve(fileName));
    }

    @Override
    public void autoCreateDir(Path path) {
        logger.debug("autoCreateDir", "called");
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void autoCreateDir(String dirName) {
        logger.debug("autoCreateDir", "called");
        if (!Files.exists(currentDir.resolve(dirName))) {
            try {
                Files.createDirectories(currentDir.resolve(dirName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void autoCreateToCurrentDir() {
        logger.debug("autoGenerateToCurrentDir", "called");
        if (isExist(currentDir)) {
            logger.debug("autoGenerateToCurrentDir", "currentDir already exists");
            return;
        }
        else {
            try {
                Files.createDirectories(currentDir);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void saveBitmapAtCurrent(Bitmap bitmap, String fileName) { // TODO-rca: ファイル形式を変更できるようにする？
        logger.debug("saveBitmapAtCurrent", "called");
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
