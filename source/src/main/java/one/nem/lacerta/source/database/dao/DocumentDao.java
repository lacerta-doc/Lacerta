package one.nem.lacerta.source.database.dao;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import one.nem.lacerta.source.database.entity.Document;


@Dao
public interface DocumentDao {

    @Query("SELECT * FROM document WHERE id = :id")
    Document findById(String id);

    @Query("SELECT * FROM document")
    List<Document> findAll();

    @Query("SELECT * FROM document WHERE id IN (:ids)")

    List<Document> findByIds(List<String> ids);

    // WIP
    // TODO-rca: Insert, Update, Delete

}
