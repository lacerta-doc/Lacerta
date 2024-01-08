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
    private Path documentRootPath;

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
        // Init Variables
        this.documentRootPath = documentDetail.getPath().getFullPath();
        logger.debug("init", "documentRootPath: " + this.documentRootPath);

        FileManager fileManager = fileManagerFactory.create(this.documentRootPath); //Initialize FileManager
        logger.debug("init", "fileManager created");

        fileManager.autoCreateDir(this.documentRootPath);

        // rawディレクトリInit
        fileManager.autoCreateDir(DEFAULT_SAVE_DIR);

        // xmlファイルの読み込み
        if (fileManager.isExist("meta.xml")) {
            logger.debug("init", "meta.xml found");
            try {
                xmlMetaModel = xmlMetaParser.deserialize(fileManager.loadDocument("meta.xml"));
            } catch (Exception e) {
                logger.debug("init", "meta.xml parse failed");
                logger.trace("init", e.getMessage());
            }
        } else {
            logger.debug("init", "meta.xml not found");
            xmlMetaModel = new XmlMetaModel();

            xmlMetaModel.setTitle(documentDetail.getMeta().getTitle());
            xmlMetaModel.setAuthor(documentDetail.getAuthor());
            xmlMetaModel.setDescription(""); // FIXME-rca:
            xmlMetaModel.setDefaultBranch(documentDetail.getDefaultBranch());
            xmlMetaModel.setPages(new ArrayList<>());

            try {
                fileManager.saveDocument(xmlMetaParser.serialize(xmlMetaModel), "meta.xml");
                logger.debug("init", "meta.xml saved");
            } catch (Exception e) {
                logger.error("init", "meta.xml save failed");
                logger.trace("init", e.getMessage());
            }
        }

        logger.info("init", "finished");
    }

    @Override
    public void addNewPageToLast(Bitmap bitmap) {
        logger.debug("addNewPageToLast", "called");
        String fileName = String.format(UUID.randomUUID().toString() + ".png"); // TODO-rca: 対応表をもたせる
        logger.debug("addNewPageToLast", "fileName: " + fileName);
        FileManager fileManager = fileManagerFactory.create(this.documentRootPath);
        if(fileManager.getList().contains(this.documentRootPath.resolve(DEFAULT_SAVE_DIR))) {
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
