package one.nem.lacerta.source.file;

import android.graphics.Bitmap;

public interface FileManager {

    void initRepoDir();
    void addImageByIndex(Bitmap bitmap, int index);
    void deleteImageByIndex(int index);

}
