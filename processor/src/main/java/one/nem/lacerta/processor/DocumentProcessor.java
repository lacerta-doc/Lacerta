package one.nem.lacerta.processor;

import android.graphics.Bitmap;

public interface DocumentProcessor {

    // ページ操作
    void addNewPageToLast(Bitmap bitmap) throws Exception;
    void addNewPagesToLast(Bitmap[] bitmaps) throws Exception;
    void addNewPageAfterIndex(Bitmap bitmap, int index) throws Exception;
    void addNewPageBeforeIndex(Bitmap bitmap, int index) throws Exception;
    void removePageAtIndex(int index) throws Exception;

    // 更新
    void updatePageAtIndex(Bitmap bitmap, int index);

    // ページ取得
    Bitmap getPageAtIndex(int index);
    int getPageCount();

    void close() throws Exception;
    void init() throws Exception; // TODO-rca: 例外処理

}
