package one.nem.lacerta.processor.model;

public class XmlMetaPageModel {

    int index;
    String filename;

    // Constructor

    public XmlMetaPageModel() {
    }

    public XmlMetaPageModel(int index, String filename) {
        this.index = index;
        this.filename = filename;
    }

    // Getter

    public int getIndex() {
        return index;
    }

    public String getFilename() {
        return filename;
    }

    // Setter

    public void setIndex(int index) {
        this.index = index;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
