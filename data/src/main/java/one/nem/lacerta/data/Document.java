package one.nem.lacerta.data;

import java.lang.reflect.Array;
import java.util.ArrayList;

import one.nem.lacerta.model.document.DocumentMeta;
import one.nem.lacerta.model.document.DocumentDetail;
import one.nem.lacerta.model.document.path.DocumentPath;
import one.nem.lacerta.model.document.tag.DocumentTag;

/**
 * ドキュメントのデータを取得する
 */
public interface Document {

    DocumentDetail createDocument(DocumentMeta meta);

    DocumentDetail createDocument();

    void deleteDocument(String documentId);

    void updateDocument(DocumentMeta meta, DocumentDetail detail);

    DocumentDetail getDocument(String documentId);
}
