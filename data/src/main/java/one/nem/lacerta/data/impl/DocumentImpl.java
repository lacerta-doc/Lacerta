package one.nem.lacerta.data.impl;

import java.util.ArrayList;

import one.nem.lacerta.data.Document;

import one.nem.lacerta.model.document.DocumentMeta;
import one.nem.lacerta.model.document.DocumentDetail;
import one.nem.lacerta.model.document.path.DocumentPath;
import one.nem.lacerta.model.document.tag.DocumentTag;


public class DocumentImpl implements Document{


    @Override
    public ArrayList<DocumentMeta> getRecentDocumentMetas(int limit) {
        return null;
    }

    @Override
    public ArrayList<DocumentMeta> getRecentDocumentMetas(int limit, int offset) {
        return null;
    }

    @Override
    public DocumentDetail getDocumentDetail(String id) {
        return null;
    }

    @Override
    public DocumentDetail getDocumentDetailByMeta(DocumentMeta meta) {
        return null;
    }
}
