package one.nem.lacerta.data;

import one.nem.lacerta.model.document.DocumentMeta;
import one.nem.lacerta.model.document.DocumentDetail;
import one.nem.lacerta.model.document.path.DocumentPath;
import one.nem.lacerta.model.document.tag.DocumentTag;

public interface DocumentDebug {

    void insertDocument(DocumentMeta meta, DocumentDetail detail);

}
