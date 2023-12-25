package one.nem.lacerta.processor.impl;

import android.graphics.Bitmap;

import java.nio.file.Path;
import java.util.UUID;

import javax.inject.Inject;

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
    @Inject
    FileManagerFactory fileManagerFactory;

    @Inject
    LacertaLogger logger;

    @Inject
    XmlMetaParser xmlMetaParser;

    @Override
    public void init() {
        logger.debug("init", "called");
        // XMLメタデータの取得/生成
        FileManager fileManager = fileManagerFactory.create(documentDetail.getPath().getFullPath());
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
