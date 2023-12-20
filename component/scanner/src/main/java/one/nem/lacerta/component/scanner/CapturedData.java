package one.nem.lacerta.component.scanner;

import android.graphics.Bitmap;

public class CapturedData {

    private String path;
    private String resolutionHeight;
    private String resolutionWidth;
    private String size;
    private Bitmap bitmap;

    // Constructor

    public CapturedData(String path, String resolutionHeight, String resolutionWidth, String size, Bitmap bitmap) {
        this.path = path;
        this.resolutionHeight = resolutionHeight;
        this.resolutionWidth = resolutionWidth;
        this.size = size;
        this.bitmap = bitmap;
    }

    // Getters

    public String getPath() {
        return path;
    }

    public String getResolutionHeight() {
        return resolutionHeight;
    }

    public String getResolutionWidth() {
        return resolutionWidth;
    }

    public String getSize() {
        return size;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}
