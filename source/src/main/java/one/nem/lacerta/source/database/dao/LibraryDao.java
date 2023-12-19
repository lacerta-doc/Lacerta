package one.nem.lacerta.source.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import one.nem.lacerta.source.database.entity.LibraryEntity;

@Dao
public interface LibraryDao {

    // Select
    @Query("SELECT * FROM Library WHERE id = :id")
    LibraryEntity findById(String id);

    @Query("SELECT * FROM Library")
    List<LibraryEntity> findAll();

    @Query("SELECT * FROM Library WHERE id IN (:ids)")
    List<LibraryEntity> findByIds(List<String> ids);


    // Insert
    @Insert
    void insert(LibraryEntity libraryEntity);

    @Insert
    void insertAll(LibraryEntity... libraryEntities);

    @Insert
    void insertAll(List<LibraryEntity> libraryEntities);

    // Update
    // TODO-rca: 未実装
}
