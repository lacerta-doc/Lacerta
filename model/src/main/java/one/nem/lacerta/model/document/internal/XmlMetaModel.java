package one.nem.lacerta.model.document.internal;

import java.util.ArrayList;

public class XmlMetaModel {

    String revisionId;

    ArrayList<XmlMetaPageModel> pages;

    // Constructor

    public XmlMetaModel() {
    }

    public XmlMetaModel(String revisionId, ArrayList<XmlMetaPageModel> pages) {
        this.revisionId = revisionId;
        this.pages = pages;
    }

    // Getter

    public String getRevisionId() {
        return revisionId;
    }

    public ArrayList<XmlMetaPageModel> getPages() {
        return pages;
    }

    // Setter

    public void setRevisionId(String revisionId) {
        this.revisionId = revisionId;
    }

    public void setPages(ArrayList<XmlMetaPageModel> pages) {
        this.pages = pages;
    }
}
