package one.nem.lacerta.source.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import one.nem.lacerta.source.database.entity.ToxiDocumentEntity;

@Dao
public interface ToxiDocumentDao {

    @Query("SELECT * FROM toxi_document WHERE parent_document_id = :parentId AND is_active = 1 ORDER BY `order` ASC")
    List<ToxiDocumentEntity> findByParentId(String parentId);

    @Insert
    void insert(ToxiDocumentEntity toxiDocument);

    @Insert
    void insertAll(ToxiDocumentEntity... toxiDocuments);

    @Update
    void update(ToxiDocumentEntity toxiDocument);

    @Update
    void updateAll(ToxiDocumentEntity... toxiDocuments);

    @Query("DELETE FROM toxi_document WHERE parent_document_id = :parentId")
    void deleteByParentId(String parentId);

    @Query("DELETE FROM toxi_document WHERE parent_document_id = :parentId AND child_document_id = :childId")
    void deleteByParentIdAndChildId(String parentId, String childId);
}
