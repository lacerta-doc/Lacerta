package one.nem.lacerta.component.viewer.model;

import android.graphics.Bitmap;

public class ViewerBodyListItem {

    Bitmap image;

    public ViewerBodyListItem(Bitmap image) {
        this.image = image;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
