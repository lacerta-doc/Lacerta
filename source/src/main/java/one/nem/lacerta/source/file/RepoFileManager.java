package one.nem.lacerta.source.file;

import android.graphics.Bitmap;

import org.eclipse.jgit.lib.Repository;

public interface RepoFileManager {

    void initRepoDir();
    void addImageByIndex(Bitmap bitmap, int index);
    void deleteImageByIndex(int index);

}
