package one.nem.lacerta.utils;

import one.nem.lacerta.model.document.internal.XmlMetaModel;

public interface XmlMetaParser {

    // ドキュメントのメタデータをXMLファイルから読み込む
    XmlMetaModel parse(String xml);
    // ドキュメントのメタデータをXMLファイルに書き込む
    String serialize(XmlMetaModel meta);

}
