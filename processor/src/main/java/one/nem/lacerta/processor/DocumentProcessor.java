package one.nem.lacerta.processor;

import android.graphics.Bitmap;

import one.nem.lacerta.model.document.DocumentDetail;

public interface DocumentProcessor {

    // ページ操作
    DocumentProcessor addNewPageToLast(Bitmap bitmap) throws Exception;
    DocumentProcessor addNewPagesToLast(Bitmap[] bitmaps) throws Exception;
    DocumentProcessor insertPageAtIndex(Bitmap bitmap, int index) throws Exception;
    DocumentProcessor removePageAtIndex(int index) throws Exception;

    // 更新
    DocumentProcessor updatePageAtIndex(Bitmap bitmap, int index);

    // ページ取得
    Bitmap getPageAtIndex(int index);
    int getPageCount();

    DocumentDetail getDocumentDetail();
}
