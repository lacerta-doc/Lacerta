package one.nem.lacerta.data.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import one.nem.lacerta.data.Document;

import one.nem.lacerta.model.document.DocumentMeta;
import one.nem.lacerta.model.document.DocumentDetail;

import one.nem.lacerta.model.document.path.DocumentPath;
import one.nem.lacerta.model.document.tag.DocumentTag;
import one.nem.lacerta.source.database.LacertaDatabase;

import one.nem.lacerta.source.database.entity.DocumentEntity;
import one.nem.lacerta.source.database.entity.LibraryEntity;
import one.nem.lacerta.source.database.entity.TagEntity;

import one.nem.lacerta.source.jgit.JGitRepository;
import one.nem.lacerta.utils.repository.DeviceInfoUtils;


public class DocumentImpl implements Document {

    @Override
    public DocumentDetail createDocument(DocumentMeta meta) {
        return null;
    }

    @Override
    public DocumentDetail createDocument() {
        return null;
    }

    @Override
    public void deleteDocument(String documentId) {

    }

    @Override
    public void updateDocument(DocumentMeta meta, DocumentDetail detail) {

    }

    @Override
    public DocumentDetail getDocument(String documentId) {
        return null;
    }
}
