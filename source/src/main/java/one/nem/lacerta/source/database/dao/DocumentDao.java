package one.nem.lacerta.source.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import one.nem.lacerta.source.database.entity.DocumentEntity;


@Dao
public interface DocumentDao {

    // Select

    @Query("SELECT * FROM Document WHERE id = :id")
    DocumentEntity findById(String id);

    @Query("SELECT * FROM Document")
    List<DocumentEntity> findAll();

    @Query("SELECT * FROM Document LIMIT :limit")
    List<DocumentEntity> getAllWithLimit(int limit);

    @Query("SELECT * FROM Document WHERE id IN (:ids)")
    List<DocumentEntity> findByIds(List<String> ids);

    @Query("SELECT * FROM Document WHERE public_path = :publicPath LIMIT :limit")
    List<DocumentEntity> findByPublicPathWithLimit(String publicPath, int limit);

    @Query("SELECT * FROM Document ORDER BY created_at DESC LIMIT :limit")
    List<DocumentEntity> getRecentDocument(int limit);

    // Insert
    @Insert
    void insert(DocumentEntity document);

    @Insert
    void insertAll(DocumentEntity... documents);

    @Insert
    void insertAll(List<DocumentEntity> documents);

    // Update

    @Update
    void update(DocumentEntity document);

    @Update
    void updateAll(DocumentEntity... documents);

    @Update
    void updateAll(List<DocumentEntity> documents);

    // Delete
    @Delete
    void delete(DocumentEntity document);

    @Delete
    void deleteAll(DocumentEntity... documents);

    @Delete
    void deleteAll(List<DocumentEntity> documents);

    @Query("DELETE FROM Document WHERE id = :id")
    void deleteById(String id);
}
