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

    ArrayList<DocumentMeta> getAllDocumentMetas(String path, int limit);

    ArrayList<DocumentMeta> getAllDocumentMetas(String path, int limit, int offset);

    /**
     * 更新の新しいドキュメントから順に並べてlimit件取得する
     * @param limit 取得する上限数
     */
    ArrayList<DocumentMeta> getRecentDocumentMetas(int limit);

    /**
     * 更新の新しいドキュメントから順に並べてoffset位置からlimit件取得する
     * @param limit 取得する上限数
     * @param offset 取得するオフセット
     */
    ArrayList<DocumentMeta> getRecentDocumentMetas(int limit, int offset);

    /**
     * ドキュメントIDからDocumentDetailを取得する
     * @param id ドキュメントID
     */
    DocumentDetail getDocumentDetail(String id);

    /**
     * DocumentMetaからDocumentDetailを取得する
     * @param meta DocumentMeta
     */
    DocumentDetail getDocumentDetailByMeta(DocumentMeta meta); // 簡単に使えるように

    DocumentDetail createDocumentByMeta(DocumentMeta meta);
}
