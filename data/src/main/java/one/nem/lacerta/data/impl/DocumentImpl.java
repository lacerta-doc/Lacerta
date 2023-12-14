package one.nem.lacerta.data.impl;

import java.util.ArrayList;

import javax.inject.Inject;

import one.nem.lacerta.data.Document;

import one.nem.lacerta.model.document.DocumentMeta;
import one.nem.lacerta.model.document.DocumentDetail;

import one.nem.lacerta.source.database.LacertaDatabase;

import one.nem.lacerta.source.database.entity.DocumentEntity;


public class DocumentImpl implements Document{

    private LacertaDatabase database;

    @Inject
    public DocumentImpl(LacertaDatabase database) {
        this.database = database;
    }


    @Override
    public ArrayList<DocumentMeta> getRecentDocumentMetas(int limit) {
//        ArrayList<DocumentMeta> documentMetas = new ArrayList<>();
//        database.documentDao().
        // TODO-rca: 履歴取得するDao実装する
    }

    @Override
    public ArrayList<DocumentMeta> getRecentDocumentMetas(int limit, int offset) {
        return null;
    }

    @Override
    public DocumentDetail getDocumentDetail(String id) {
        DocumentDetail documentDetail = new DocumentDetail();
        DocumentEntity documentEntity = database.documentDao().findById(id);

        // 組み立て処理
        // TODO-rca: 切り出すべきかも?
        DocumentMeta documentMeta = new DocumentMeta();
        documentMeta.setId(documentEntity.id);
        documentMeta.setTitle(documentEntity.title);
        documentMeta.setCreatedAt(documentEntity.createdAt);
        documentMeta.setUpdatedAt(documentEntity.updatedAt);
        documentMeta.setTags(documentEntity.tagIds);

    }

    @Override
    public DocumentDetail getDocumentDetailByMeta(DocumentMeta meta) {
        return null;
    }
}
