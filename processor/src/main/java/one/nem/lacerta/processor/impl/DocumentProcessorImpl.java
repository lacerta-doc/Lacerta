package one.nem.lacerta.processor.impl;

import android.graphics.Bitmap;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.UUID;

import javax.inject.Inject;

import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;
import one.nem.lacerta.processor.DocumentProcessor;

import one.nem.lacerta.model.document.DocumentDetail;

import one.nem.lacerta.model.document.internal.XmlMetaModel;
import one.nem.lacerta.source.file.FileManager;
import one.nem.lacerta.source.file.factory.FileManagerFactory;

import one.nem.lacerta.utils.LacertaLogger;

import one.nem.lacerta.utils.XmlMetaParser;


public class DocumentProcessorImpl implements DocumentProcessor{

    // Magic Numbers
    private static final String DEFAULT_SAVE_DIR = "raw";

    private DocumentDetail documentDetail;

    private XmlMetaModel xmlMetaModel;

    // Injection
    private FileManagerFactory fileManagerFactory;

    private LacertaLogger logger;

    private XmlMetaParser xmlMetaParser;

    @AssistedInject
    public DocumentProcessorImpl(FileManagerFactory fileManagerFactory, LacertaLogger logger, XmlMetaParser xmlMetaParser, @Assisted DocumentDetail documentDetail) {
        this.fileManagerFactory = fileManagerFactory;
        this.logger = logger;
        this.xmlMetaParser = xmlMetaParser;
        if (documentDetail == null) {
            throw new IllegalArgumentException("documentDetail must not be null");
        }
        this.documentDetail = documentDetail;
    }

    @Override
    public void init() {
        logger.debug("init", "called");
        // XMLメタデータの取得/生成
        FileManager fileManager = fileManagerFactory.create(documentDetail.getPath().getFullPath());
        if(documentDetail.getPath().getFullPath().resolve("meta.xml").toFile().exists()) {
            logger.debug("init", "meta.xml found");
            try {
                xmlMetaModel = xmlMetaParser.parse(new String(Files.readAllBytes(documentDetail.getPath().getFullPath().resolve("meta.xml"))));
                logger.debug("init", "parsed");
            } catch (Exception e) {
                logger.debug("init", "parse failed");
                e.printStackTrace();
            }
        } else {
            logger.debug("init", "meta.xml not found");
            // Create new
            xmlMetaModel = new XmlMetaModel();
            xmlMetaModel.setTitle(this.documentDetail.getMeta().getTitle());
            xmlMetaModel.setAuthor(this.documentDetail.getAuthor());
            xmlMetaModel.setDescription("");
            xmlMetaModel.setDefaultBranch("master");
            xmlMetaModel.setPages(new ArrayList<>());

            // Save
            try {
                // ファイルの新規作成を行う
                File file = documentDetail.getPath().getFullPath().resolve("meta.xml").toFile();
                file.createNewFile();

                // ファイルに書き込む
                Files.write(documentDetail.getPath().getFullPath().resolve("meta.xml"), xmlMetaParser.serialize(xmlMetaModel).getBytes());
                logger.debug("init", "saved");
            } catch (Exception e) {
                logger.debug("init", "save failed");
                e.printStackTrace();
            }
        }
    }

    @Override
    public void addNewPageToLast(Bitmap bitmap) {
        logger.debug("addNewPageToLast", "called");
        Path path = documentDetail.getPath().getFullPath();
        String fileName = String.format(UUID.randomUUID().toString() + ".png"); // TODO-rca: 対応表をもたせる
        logger.debug("addNewPageToLast", "fileName: " + fileName);
        FileManager fileManager = fileManagerFactory.create(path);
        if(fileManager.getList().contains(path.resolve(DEFAULT_SAVE_DIR))) {
            logger.debug("addNewPageToLast", "raw dir found");
            fileManager.changeDir(DEFAULT_SAVE_DIR);
        } else {
            logger.debug("addNewPageToLast", "raw dir not found");
            fileManager.createDir(DEFAULT_SAVE_DIR);
            fileManager.changeDir(DEFAULT_SAVE_DIR);
        }
        fileManager.saveBitmapAtCurrent(bitmap, fileName);
    }

    @Override
    public void addNewPagesToLast(Bitmap[] bitmaps) {
        logger.debug("addNewPagesToLast(List)", "called");
        for(Bitmap bitmap : bitmaps) {
            addNewPageToLast(bitmap);
        } // TODO-rca: 保存処理をまとめて行う？
    }

    @Override
    public void addNewPageAfterIndex(Bitmap bitmap, int index) {

    }

    @Override
    public void addNewPageBeforeIndex(Bitmap bitmap, int index) {

    }

    @Override
    public void removePageAtIndex(int index) {

    }

    @Override
    public void updatePageAtIndex(Bitmap bitmap, int index) {

    }

    @Override
    public Bitmap getPageAtIndex(int index) {
        return null;
    }

    @Override
    public int getPageCount() {
        return 0;
    }

    @Override
    public void close() {

    }
}
