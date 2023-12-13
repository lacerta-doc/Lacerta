package one.nem.lacerta.source.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import one.nem.lacerta.source.database.entity.Document;


@Dao
public interface DocumentDao {

    // Select

    @Query("SELECT * FROM document WHERE id = :id")
    Document findById(String id);

    @Query("SELECT * FROM document")
    List<Document> findAll();

    @Query("SELECT * FROM document WHERE id IN (:ids)")
    List<Document> findByIds(List<String> ids);

    // Insert
    @Insert
    void insert(Document document);

    @Insert
    void insertAll(Document... documents);

    @Insert
    void insertAll(List<Document> documents);

    // Update

    @Update
    void update(Document document);

    @Update
    void updateAll(Document... documents);

    @Update
    void updateAll(List<Document> documents);

    // Delete
    @Delete
    void delete(Document document);

    @Delete
    void deleteAll(Document... documents);

    @Delete
    void deleteAll(List<Document> documents);

    @Delete
    void deleteById(String id);

}
