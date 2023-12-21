package one.nem.lacerta.processor.impl;

import android.graphics.Bitmap;

import one.nem.lacerta.processor.DocumentProcessor;

import one.nem.lacerta.model.document.DocumentDetail;
import one.nem.lacerta.model.document.DocumentMeta;

public class DocumentProcessorImpl implements DocumentProcessor{

    private DocumentDetail documentDetail;



    @Override
    public void addNewPageToLast(Bitmap bitmap) {

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
}
