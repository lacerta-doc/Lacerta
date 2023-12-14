package one.nem.lacerta.data;

import java.util.ArrayList;

import one.nem.lacerta.model.document.DocumentMeta;
import one.nem.lacerta.model.document.DocumentDetail;
import one.nem.lacerta.model.document.path.DocumentPath;
import one.nem.lacerta.model.document.tag.DocumentTag;

public interface Document {

    ArrayList<DocumentMeta> getRecentDocumentMetas(int limit);

    ArrayList<DocumentMeta> getRecentDocumentMetas(int limit, int offset);

    // ドキュメント詳細取得
    DocumentDetail getDocumentDetail(String id);
    DocumentDetail getDocumentDetailByMeta(DocumentMeta meta); // 簡単に使えるように



}
