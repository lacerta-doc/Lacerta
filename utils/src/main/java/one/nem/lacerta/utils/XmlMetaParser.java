package one.nem.lacerta.utils;

import org.w3c.dom.Document;

import one.nem.lacerta.model.document.internal.XmlMetaModel;

public interface XmlMetaParser {

    XmlMetaModel deserialize(Document document);

    Document serialize(XmlMetaModel meta);

}
