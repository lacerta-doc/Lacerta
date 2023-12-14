package one.nem.lacerta.source.database.dao;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import one.nem.lacerta.source.database.entity.LibraryEntity;

@Dao
public interface LibraryDao {

    @Query("SELECT * FROM LibraryEntity WHERE id = :id")
    LibraryEntity findById(String id);

    @Query("SELECT * FROM LibraryEntity")
    List<LibraryEntity> findAll();

    @Query("SELECT * FROM LibraryEntity WHERE id IN (:ids)")
    List<LibraryEntity> findByIds(List<String> ids);

    // WIP
    // TODO-rca: Insert, Update, Delete
}
