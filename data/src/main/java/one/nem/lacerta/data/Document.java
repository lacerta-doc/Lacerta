package one.nem.lacerta.data;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import one.nem.lacerta.model.document.DocumentMeta;
import one.nem.lacerta.model.document.DocumentDetail;
import one.nem.lacerta.model.document.path.DocumentPath;
import one.nem.lacerta.model.document.tag.DocumentTag;

/**
 * ドキュメントのデータを取得する
 */
public interface Document {

    CompletableFuture<DocumentDetail> createDocument(DocumentMeta meta);

    CompletableFuture<DocumentDetail> createDocument();

    CompletableFuture<Void> deleteDocument(String documentId);

    CompletableFuture<Void> updateDocument(DocumentDetail detail);

    CompletableFuture<DocumentDetail> getDocument(String documentId);
}
