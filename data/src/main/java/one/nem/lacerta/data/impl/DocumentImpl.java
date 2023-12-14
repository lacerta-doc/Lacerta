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

        // タグ取得
        // TODO-rca: 切り出すべきかも？
        List<TagEntity> tagEntities = database.tagDao().findByIds(documentEntity.tagIds);
        ArrayList<DocumentTag> documentTags = new ArrayList<>();
        for (TagEntity tagEntity : tagEntities) {
            DocumentTag documentTag = new DocumentTag();
            documentTag.setId(tagEntity.id);
            documentTag.setName(tagEntity.tagName);
            documentTag.setColor(tagEntity.color);
            documentTags.add(documentTag);
        }

        // パス取得
        // TODO-rca: 切り出すべきかも？
        LibraryEntity libraryEntity = database.libraryDao().findById(id);
        DocumentPath documentPath = new DocumentPath(libraryEntity.rootPath, libraryEntity.path);

        // 組み立て処理
        // TODO-rca: 切り出すべきかも?
        DocumentMeta documentMeta = new DocumentMeta();
        documentMeta.setId(documentEntity.id);
        documentMeta.setTitle(documentEntity.title);
        documentMeta.setCreatedAt(documentEntity.createdAt);
        documentMeta.setUpdatedAt(documentEntity.updatedAt);
        documentMeta.setTags(documentTags);
        documentDetail.setMeta(documentMeta);
        documentDetail.setAuthor(documentEntity.author);
        documentDetail.setPath(documentPath);
        documentDetail.setDefaultBranch(documentEntity.defaultBranch);

        return documentDetail;
    }

    @Override
    public DocumentDetail getDocumentDetailByMeta(DocumentMeta meta) {
        return null;
    }
}
