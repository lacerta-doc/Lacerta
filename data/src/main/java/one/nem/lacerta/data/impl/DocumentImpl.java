package one.nem.lacerta.data.impl;

import java.util.ArrayList;

import javax.inject.Inject;

import one.nem.lacerta.data.Document;

import one.nem.lacerta.model.document.DocumentMeta;
import one.nem.lacerta.model.document.DocumentDetail;
import one.nem.lacerta.model.document.path.DocumentPath;
import one.nem.lacerta.model.document.tag.DocumentTag;

import one.nem.lacerta.source.database.entity.Document;

import one.nem.lacerta.source.database.LacertaDatabase;


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

    }

    @Override
    public DocumentDetail getDocumentDetailByMeta(DocumentMeta meta) {
        return null;
    }
}
