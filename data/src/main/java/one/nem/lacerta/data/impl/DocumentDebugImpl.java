package one.nem.lacerta.data.impl;

import java.util.List;

import one.nem.lacerta.data.DocumentDebug;
import one.nem.lacerta.model.document.DocumentDetail;
import one.nem.lacerta.model.document.DocumentMeta;

import one.nem.lacerta.source.database.entity.DocumentEntity;
import one.nem.lacerta.source.database.entity.LibraryEntity;
import one.nem.lacerta.source.database.entity.TagEntity;

public class DocumentDebugImpl implements DocumentDebug{
    public void insertDocument(DocumentMeta meta, DocumentDetail detail) {
        DocumentEntity documentEntity = new DocumentEntity();
        LibraryEntity libraryEntity = new LibraryEntity();

        documentEntity.id = meta.getId();
        documentEntity.title = meta.getTitle();
        documentEntity.createdAt = meta.getCreatedAt();
        documentEntity.updatedAt = meta.getUpdatedAt();
        documentEntity.author = detail.getAuthor();
        documentEntity.defaultBranch = detail.getDefaultBranch();
        // ArrayListからListに変換
        documentEntity.tagIds = meta.getTagIds();

        libraryEntity.id = meta.getId();
        libraryEntity.path = "Placeholder";
        libraryEntity.rootPath = "Placeholder";

    }
}
