package one.nem.lacerta.data.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import one.nem.lacerta.data.model.documents.DocumentMeta;
import one.nem.lacerta.data.repository.Documents;

public class DocumentsImpl implements Documents {

    @Inject
    public DocumentsImpl() {
    }

    public ArrayList<DocumentMeta> getRecentDocuments(int limit) {
        return null; // TODO-rca:
    }
}
