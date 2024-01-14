package one.nem.lacerta.model.document.page;

import android.graphics.Bitmap;

public class Page {

    String fileName;
    Bitmap bitmap;

    public Page() {
    }

    public Page(String fileName, Bitmap bitmap) {
        this.fileName = fileName;
        this.bitmap = bitmap;
    }

    public String getFileName() {
        return fileName;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
