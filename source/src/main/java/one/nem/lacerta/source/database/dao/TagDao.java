package one.nem.lacerta.source.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

import one.nem.lacerta.source.database.entity.TagEntity;

@Dao
public interface TagDao {

    // Select

    @Query("SELECT * FROM TagEntity WHERE id = :id")
    TagEntity findById(String id);

    @Query("SELECT * FROM TagEntity")
    ArrayList<TagEntity> findAll();

    @Query("SELECT * FROM TagEntity WHERE id IN (:ids)")
    ArrayList<TagEntity> findByIds(ArrayList<String> ids);

    // Insert
    @Insert
    void insert(TagEntity tag);

    @Insert
    void insertAll(TagEntity... tags);

    @Insert
    void insertAll(List<TagEntity> tags);

    // Update
    @Update
    void update(TagEntity tag);

    @Update
    void updateAll(TagEntity... tags);

    @Update
    void updateAll(List<TagEntity> tags);

    // Delete
    @Delete
    void delete(TagEntity tag);

    @Delete
    void deleteAll(TagEntity... tags);

    @Delete
    void deleteAll(List<TagEntity> tags);

    @Query("DELETE FROM TagEntity WHERE id = :id")
    void deleteById(String id);
}
