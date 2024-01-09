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
    public void init() throws Exception{
        logger.debug("init", "called");
        // Init Variables
        this.documentRootPath = this.documentDetail.getPath().getFullPath();
        logger.debug("init", "documentRootPath: " + this.documentRootPath);

        this.fileManager = fileManagerFactory.create(this.documentRootPath).enableAutoCreateParent(); //Initialize FileManager

        FileManager initFileManager = this.fileManager.getNewInstance();

        logger.debug("init", "fileManager created");

        // xmlファイルの読み込み
        if (initFileManager.isExist("meta.xml")) {
            logger.debug("init", "meta.xml found");
            try {
                this.xmlMetaModel = xmlMetaParser.deserialize(this.fileManager.loadXml("meta.xml"));
                logger.debug("init", "meta.xml parsed");
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
                initFileManager.createFileIfNotExist("meta.xml").saveXml(xmlMetaParser.serialize(xmlMetaModel), "meta.xml");
                logger.debug("init", "meta.xml saved");
            } catch (Exception e) {
                logger.error("init", "meta.xml save failed");
                logger.trace("init", e.getMessage());
            }
        }

        logger.info("init", "finished");
    }

    @Override
    public void addNewPageToLast(Bitmap bitmap) throws Exception{
        logger.debug("addNewPageToLast", "called");
        String filename = UUID.randomUUID().toString() + ".png"; // TODO-rca: 拡張子を動的にする

        this.fileManager.getNewInstance().createDirectoryIfNotExist(DEFAULT_SAVE_DIR).resolve(DEFAULT_SAVE_DIR).saveBitmap(bitmap, filename);

        XmlMetaPageModel xmlMetaPageModel = new XmlMetaPageModel();
        xmlMetaPageModel.setFilename(filename);
        xmlMetaPageModel.setIndex(xmlMetaModel.getPages().size() + 1);
        xmlMetaModel.getPages().add(xmlMetaPageModel);

        logger.info("addNewPageToLast", "finished");
        logger.info("addNewPageToLast", "filename: " + filename + ", index: " + xmlMetaPageModel.getIndex());
    }

    @Override
    public void addNewPagesToLast(Bitmap[] bitmaps) throws Exception{
        logger.debug("addNewPagesToLast", "called");

        for (Bitmap bitmap : bitmaps) {
            addNewPageToLast(bitmap);
        } // TODO-rca: 効率悪いので改善する
    }

    // Internal
    // Indexを振り直す
    private ArrayList<XmlMetaPageModel> reIndexPages(ArrayList<XmlMetaPageModel> pages) {
        logger.debug("reIndexPages", "called"); // TODO-rca: 効率化
        ArrayList<XmlMetaPageModel> newPages = new ArrayList<>();
        for (int i = 0; i < pages.size(); i++) {
            XmlMetaPageModel xmlMetaPageModel = pages.get(i);
            xmlMetaPageModel.setIndex(i + 1);
            newPages.add(xmlMetaPageModel);
        }
        return newPages;
    }

    @Override
    public void addNewPageAfterIndex(Bitmap bitmap, int index) throws Exception {
        logger.debug("addNewPageAfterIndex", "called");
        String filename = UUID.randomUUID().toString() + ".png"; // TODO-rca: 拡張子を動的にする

        this.fileManager.getNewInstance().createDirectoryIfNotExist(DEFAULT_SAVE_DIR).resolve(DEFAULT_SAVE_DIR).saveBitmap(bitmap, filename);

        XmlMetaPageModel xmlMetaPageModel = new XmlMetaPageModel();
        xmlMetaPageModel.setFilename(filename);
        xmlMetaPageModel.setIndex(index + 1);
        xmlMetaModel.getPages().add(index, xmlMetaPageModel);
        xmlMetaModel.setPages(reIndexPages(xmlMetaModel.getPages()));

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
