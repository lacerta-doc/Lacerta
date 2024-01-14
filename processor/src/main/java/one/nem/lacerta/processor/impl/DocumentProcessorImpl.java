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
import one.nem.lacerta.vcs.LacertaVcs;
import one.nem.lacerta.vcs.factory.LacertaVcsFactory;


public class DocumentProcessorImpl implements DocumentProcessor{

    // Magic Numbers
    private static final String DEFAULT_SAVE_DIR = "raw";

    // Variables
    private final DocumentDetail documentDetail;
    private XmlMetaModel xmlMetaModel;
    private Path documentRootPath;
    private FileManager fileManager;
    private LacertaVcs lacertaVcs;

    // Injection
    private final FileManagerFactory fileManagerFactory;
    private final LacertaLogger logger;
    private final XmlMetaParser xmlMetaParser;
    private final LacertaVcsFactory lacertaVcsFactory;
    private final DeviceInfoUtils deviceInfoUtils;
    @AssistedInject
    public DocumentProcessorImpl(FileManagerFactory fileManagerFactory, LacertaLogger logger, XmlMetaParser xmlMetaParser, LacertaVcsFactory lacertaVcsFactory, DeviceInfoUtils deviceInfoUtils, @Assisted DocumentDetail documentDetail) {
        this.fileManagerFactory = fileManagerFactory;
        this.logger = logger;
        this.xmlMetaParser = xmlMetaParser;
        if (documentDetail == null) {
            throw new IllegalArgumentException("documentDetail must not be null");
        }
        this.lacertaVcsFactory = lacertaVcsFactory;
        this.documentDetail = documentDetail;
        this.deviceInfoUtils = deviceInfoUtils;

        // Init
        this.documentRootPath = deviceInfoUtils.getExternalStorageDirectory().resolve(this.documentDetail.getMeta().getId());
        logger.debug("init", "documentRootPath: " + this.documentRootPath);
        this.fileManager = fileManagerFactory.create(this.documentRootPath).enableAutoCreateParent(); //Initialize FileManager
        this.lacertaVcs = lacertaVcsFactory.create(this.documentDetail.getMeta().getId());
    }


    @Override
    public void addNewPageToLast(Bitmap bitmap) throws Exception{
        logger.debug("addNewPageToLast", "called");
        String filename = UUID.randomUUID().toString() + ".png"; // TODO-rca: 拡張子を動的にする

        Page page = new Page();
        page.setFileName(filename);
        page.setBitmap(bitmap);
        this.documentDetail.getPages().add(page);

        lacertaVcs.insertPage(documentDetail.getPages().size(), filename);

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
    public void addNewPageAtIndex(Bitmap bitmap, int index) throws Exception {
        logger.debug("addNewPageAfterIndex", "called");
        String filename = UUID.randomUUID().toString() + ".png"; // TODO-rca: 拡張子を動的にする

        this.fileManager.getNewInstance().createDirectoryIfNotExist(DEFAULT_SAVE_DIR).resolve(DEFAULT_SAVE_DIR).saveBitmap(bitmap, filename);

        Page page = new Page();
        page.setFileName(filename);
        page.setBitmap(bitmap);
        this.documentDetail.getPages().add(index + 1, page);

        lacertaVcs.insertPage(index + 1, filename);
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
    public DocumentDetail getDocumentDetail() {
        return this.documentDetail;
    }
}
