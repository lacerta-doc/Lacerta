package one.nem.lacerta.processor.model;

import java.util.ArrayList;
import java.util.Date;

public class XmlMetaModel {

    String title;
    String author;
    String description;
    Date created;
    Date updated;
    String defaultBranch;
    ArrayList<XmlMetaPageModel> pages;
}
