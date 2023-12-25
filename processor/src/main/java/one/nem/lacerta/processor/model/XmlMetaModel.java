package one.nem.lacerta.processor.model;

import java.util.ArrayList;
import java.util.Date;

public class XmlMetaModel {

    String title;
    String author;
    String description;
//    Date created;
//    Date updated;
    String defaultBranch;
    ArrayList<XmlMetaPageModel> pages;

    // Constructor

    public XmlMetaModel() {
    }

    public XmlMetaModel(String title, String author, String description, String defaultBranch, ArrayList<XmlMetaPageModel> pages) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.defaultBranch = defaultBranch;
        this.pages = pages;
    }


    // Getter

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public String getDefaultBranch() {
        return defaultBranch;
    }

    public ArrayList<XmlMetaPageModel> getPages() {
        return pages;
    }

    // Setter

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDefaultBranch(String defaultBranch) {
        this.defaultBranch = defaultBranch;
    }

    public void setPages(ArrayList<XmlMetaPageModel> pages) {
        this.pages = pages;
    }

    // Public Methods

    public void addPage(XmlMetaPageModel page) {
        this.pages.add(page);
    }

    public void addPageAfterIndex(int index, XmlMetaPageModel page) {
        this.pages.add(index, page);

        //Update index
        this.updateIndex();
    }

    public void removePage(XmlMetaPageModel page) {
        this.pages.remove(page);
    }

    public void removePageAtIndex(int index) {
        this.pages.remove(index);

        //Update index
        this.updateIndex();
    }

    // Internal Methods
    private void updateIndex() { // TODO-rca: 効率悪そう
        for (int i = 0; i < this.pages.size(); i++) {
            this.pages.get(i).setIndex(i);
        }
    }

}
