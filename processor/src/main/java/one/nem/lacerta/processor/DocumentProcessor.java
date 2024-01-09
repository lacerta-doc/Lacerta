package one.nem.lacerta.processor;

import android.graphics.Bitmap;

public interface DocumentProcessor {

    // ページ操作
    void addNewPageToLast(Bitmap bitmap);
    void addNewPagesToLast(Bitmap[] bitmaps);
    void addNewPageAfterIndex(Bitmap bitmap, int index);
    void addNewPageBeforeIndex(Bitmap bitmap, int index);
    void removePageAtIndex(int index);

    // 更新
    void updatePageAtIndex(Bitmap bitmap, int index);

    // ページ取得
    Bitmap getPageAtIndex(int index);
    int getPageCount();

    void close();
    void init() throws Exception; // TODO-rca: 例外処理

}
