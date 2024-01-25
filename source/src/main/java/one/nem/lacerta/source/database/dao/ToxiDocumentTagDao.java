package one.nem.lacerta.source.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import one.nem.lacerta.source.database.entity.ToxiDocumentTagEntity;

@Dao
public interface ToxiDocumentTagDao {

    @Query("SELECT * FROM toxi_document_tag WHERE document_id = :documentId")
    List<ToxiDocumentTagEntity> findByDocumentId(String documentId);

    @Query("SELECT * FROM toxi_document_tag WHERE tag_id = :tagId")
    List<ToxiDocumentTagEntity> findByTagId(String tagId);

    @Insert
    void insert(ToxiDocumentTagEntity toxiDocumentTag);

    @Insert
    void insertAll(ToxiDocumentTagEntity... toxiDocumentTags);

    @Insert
    void insertAll(List<ToxiDocumentTagEntity> toxiDocumentTags);

    @Query("DELETE FROM toxi_document_tag WHERE document_id = :documentId")
    void deleteByDocumentId(String documentId);

    @Query("DELETE FROM toxi_document_tag WHERE tag_id = :tagId")
    void deleteByTagId(String tagId);

    @Query("DELETE FROM toxi_document_tag WHERE document_id = :documentId AND tag_id = :tagId")
    void deleteByDocumentIdAndTagId(String documentId, String tagId);
}
