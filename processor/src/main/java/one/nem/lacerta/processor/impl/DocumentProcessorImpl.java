package one.nem.lacerta.processor.impl;

import android.graphics.Bitmap;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.UUID;

import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;
import one.nem.lacerta.model.document.internal.XmlMetaPageModel;
import one.nem.lacerta.processor.DocumentProcessor;

import one.nem.lacerta.model.document.DocumentDetail;

import one.nem.lacerta.model.document.internal.XmlMetaModel;

import one.nem.lacerta.model.document.page.Page;

import one.nem.lacerta.source.file.FileManager;
import one.nem.lacerta.source.file.factory.FileManagerFactory;

import one.nem.lacerta.utils.LacertaLogger;

import one.nem.lacerta.utils.XmlMetaParser;
import one.nem.lacerta.utils.repository.DeviceInfoUtils;


public class DocumentProcessorImpl implements DocumentProcessor{

    // Magic Numbers
    private static final String DEFAULT_SAVE_DIR = "raw";

    // Variables
    private final DocumentDetail documentDetail;
    private XmlMetaModel xmlMetaModel;
    private Path documentRootPath;
    private FileManager fileManager;

    // Injection
    private final FileManagerFactory fileManagerFactory;
    private final LacertaLogger logger;
    private final XmlMetaParser xmlMetaParser;

    private final DeviceInfoUtils deviceInfoUtils;
    @AssistedInject
    public DocumentProcessorImpl(FileManagerFactory fileManagerFactory, LacertaLogger logger, XmlMetaParser xmlMetaParser, DeviceInfoUtils deviceInfoUtils, @Assisted DocumentDetail documentDetail) {
        this.fileManagerFactory = fileManagerFactory;
        this.logger = logger;
        this.xmlMetaParser = xmlMetaParser;
        if (documentDetail == null) {
            throw new IllegalArgumentException("documentDetail must not be null");
        }
        this.documentDetail = documentDetail;
        this.deviceInfoUtils = deviceInfoUtils;
    }

    @Override
    public void init() throws Exception{
        logger.debug("init", "called");
        // Init Variables
        this.documentRootPath = deviceInfoUtils.getExternalStorageDirectory().resolve(this.documentDetail.getMeta().getId());
        logger.debug("init", "documentRootPath: " + this.documentRootPath);

        this.fileManager = fileManagerFactory.create(this.documentRootPath).enableAutoCreateParent(); //Initialize FileManager


        logger.debug("init", "fileManager created");
    }

    @Override
    public void addNewPageToLast(Bitmap bitmap) throws Exception{
        logger.debug("addNewPageToLast", "called");
        String filename = UUID.randomUUID().toString() + ".png"; // TODO-rca: 拡張子を動的にする

        Page page = new Page();
        page.setFileName(filename);
        page.setBitmap(bitmap);
        this.documentDetail.getPages().add(page);

        this.fileManager.getNewInstance().createDirectoryIfNotExist(DEFAULT_SAVE_DIR).resolve(DEFAULT_SAVE_DIR).saveBitmap(bitmap, filename);

        logger.info("addNewPageToLast", "finished");
        logger.info("addNewPageToLast", "filename: " + filename);
    }

    @Override
    public void addNewPagesToLast(Bitmap[] bitmaps) throws Exception{
        logger.debug("addNewPagesToLast", "called");

        for (Bitmap bitmap : bitmaps) {
            addNewPageToLast(bitmap);
        } // TODO-rca: 効率悪いので改善する
    }

    @Override
    public void addNewPageAfterIndex(Bitmap bitmap, int index) throws Exception {
        logger.debug("addNewPageAfterIndex", "called");
        String filename = UUID.randomUUID().toString() + ".png"; // TODO-rca: 拡張子を動的にする

        this.fileManager.getNewInstance().createDirectoryIfNotExist(DEFAULT_SAVE_DIR).resolve(DEFAULT_SAVE_DIR).saveBitmap(bitmap, filename);

        Page page = new Page();
        page.setFileName(filename);
        page.setBitmap(bitmap);
        this.documentDetail.getPages().add(index + 1, page);
    }

    @Override
    public void addNewPageBeforeIndex(Bitmap bitmap, int index) throws Exception {
        logger.debug("addNewPageBeforeIndex", "called");
        String filename = UUID.randomUUID().toString() + ".png"; // TODO-rca: 拡張子を動的にする

        this.fileManager.getNewInstance().createDirectoryIfNotExist(DEFAULT_SAVE_DIR).resolve(DEFAULT_SAVE_DIR).saveBitmap(bitmap, filename);

        Page page = new Page();
        page.setFileName(filename);
        page.setBitmap(bitmap);
        this.documentDetail.getPages().add(index, page);

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
    public void close() throws Exception{
        logger.debug("close", "called");
        try {
            this.fileManager.getNewInstance().createFileIfNotExist("meta.xml").saveXml(xmlMetaParser.serialize(xmlMetaModel), "meta.xml");
            logger.debug("close", "meta.xml saved");
        } catch (Exception e) {
            logger.error("close", "meta.xml save failed");
            logger.trace("close", e.getMessage());
        }
    }
}
