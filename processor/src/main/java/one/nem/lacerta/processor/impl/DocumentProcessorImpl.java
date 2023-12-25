package one.nem.lacerta.processor.impl;

import android.graphics.Bitmap;

import java.io.File;
import java.nio.file.Path;
import java.util.UUID;

import javax.inject.Inject;

import one.nem.lacerta.processor.DocumentProcessor;

import one.nem.lacerta.model.document.DocumentDetail;
import one.nem.lacerta.model.document.DocumentMeta;

import one.nem.lacerta.source.file.FileManager;
import one.nem.lacerta.source.file.factory.FileManagerFactory;

public class DocumentProcessorImpl implements DocumentProcessor{

    // Magic Numbers
    private static final String DEFAULT_SAVE_DIR = "raw";

    private DocumentDetail documentDetail;

    // Injection
    @Inject
    FileManagerFactory fileManagerFactory;

    // Internal utils

    @Override
    public void addNewPageToLast(Bitmap bitmap) {
        Path path = documentDetail.getPath().getFullPath();
        String fileName = String.format(UUID.randomUUID().toString() + ".png"); // TODO-rca: 対応表をもたせる
        FileManager fileManager = fileManagerFactory.create(path);
        if(fileManager.getList().contains(path.resolve(DEFAULT_SAVE_DIR))) {
            fileManager.changeDir(DEFAULT_SAVE_DIR);
        } else {
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
