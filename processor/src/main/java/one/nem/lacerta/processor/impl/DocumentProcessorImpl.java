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
import one.nem.lacerta.source.file.FileManager;
import one.nem.lacerta.source.file.factory.FileManagerFactory;

import one.nem.lacerta.utils.LacertaLogger;

import one.nem.lacerta.utils.XmlMetaParser;


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
        this.documentRootPath = this.documentDetail.getPath().getFullPath();
        logger.debug("init", "documentRootPath: " + this.documentRootPath);

        this.fileManager = fileManagerFactory.create(this.documentRootPath); //Initialize FileManager
        logger.debug("init", "fileManager created");

        this.fileManager.autoCreateDir(this.documentRootPath);

        // rawディレクトリInit
        this.fileManager.autoCreateDir(DEFAULT_SAVE_DIR);

        // xmlファイルの読み込み
        if (fileManager.isExist("meta.xml")) {
            logger.debug("init", "meta.xml found");
            try {
                xmlMetaModel = xmlMetaParser.deserialize(this.fileManager.loadDocument("meta.xml"));
            } catch (Exception e) {
                logger.debug("init", "meta.xml parse failed");
                logger.trace("init", e.getMessage());
            }
        } else {
            logger.debug("init", "meta.xml not found");
            xmlMetaModel = new XmlMetaModel();

            xmlMetaModel.setTitle(this.documentDetail.getMeta().getTitle());
            xmlMetaModel.setAuthor(this.documentDetail.getAuthor());
            xmlMetaModel.setDescription(""); // FIXME-rca:
            xmlMetaModel.setDefaultBranch(this.documentDetail.getDefaultBranch());
            xmlMetaModel.setPages(new ArrayList<>());

            try {
                this.fileManager.saveDocument(xmlMetaParser.serialize(xmlMetaModel), "meta.xml");
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
        String filename = UUID.randomUUID().toString() + ".png"; // TODO-rca: 拡張子を動的にする

        // FileManager
        this.fileManager.autoCreateDir(DEFAULT_SAVE_DIR);

        // Save file
        this.fileManager.saveBitmapAtCurrent(bitmap, filename);

        // Update meta
        XmlMetaPageModel page = new XmlMetaPageModel();
        page.setIndex(xmlMetaModel.getPages().size());
        page.setFilename(filename);
        xmlMetaModel.addPage(page);
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
