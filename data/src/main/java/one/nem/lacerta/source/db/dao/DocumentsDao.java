package one.nem.lacerta.source.db.dao;


import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import one.nem.lacerta.source.db.entity.Documents;

@Dao
public interface DocumentsDao {

    @Query("SELECT * FROM documents")
    List<Documents> getAll();

    @Query("SELECT * FROM documents WHERE id IN (:ids)")
    List<Documents> loadAllByIds(int[] ids);

    @Query("SELECT * FROM documents WHERE title LIKE :title LIMIT 1")
    Documents findByTitle(String title);

    @Query("SELECT * FROM documents WHERE id LIKE :id LIMIT 1")
    Documents findById(String id);

}
