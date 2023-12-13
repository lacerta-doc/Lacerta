package one.nem.lacerta.source.database.dao;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import one.nem.lacerta.source.database.entity.Library;

@Dao
public interface LibraryDao {

    @Query("SELECT * FROM library WHERE id = :id")
    Library findById(String id);

    @Query("SELECT * FROM library")
    List<Library> findAll();

    @Query("SELECT * FROM library WHERE id IN (:ids)")
    List<Library> findByIds(List<String> ids);

    // WIP
    // TODO-rca: Insert, Update, Delete
}
