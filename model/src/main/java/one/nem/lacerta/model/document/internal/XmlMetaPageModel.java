package one.nem.lacerta.model.document.internal;

public class XmlMetaPageModel {

    String filename;

    // Constructor

    public XmlMetaPageModel() {
    }

    public XmlMetaPageModel(String filename) {
        this.filename = filename;
    }

    // Getter

    public String getFilename() {
        return filename;
    }

    // Setter

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
