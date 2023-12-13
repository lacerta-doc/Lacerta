package one.nem.lacerta.source.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import one.nem.lacerta.source.database.entity.Tag;

@Dao
public interface TagDao {

    // Select

    @Query("SELECT * FROM tag WHERE id = :id")
    Tag findById(String id);

    @Query("SELECT * FROM tag")
    List<Tag> findAll();

    @Query("SELECT * FROM tag WHERE id IN (:ids)")
    List<Tag> findByIds(List<String> ids);

    // Insert
    @Insert
    void insert(Tag tag);

    @Insert
    void insertAll(Tag... tags);

    @Insert
    void insertAll(List<Tag> tags);

    // Update
    @Update
    void update(Tag tag);

    @Update
    void updateAll(Tag... tags);

    @Update
    void updateAll(List<Tag> tags);

    // Delete
    @Delete
    void delete(Tag tag);

    @Delete
    void deleteAll(Tag... tags);

    @Delete
    void deleteAll(List<Tag> tags);

    @Delete
    void deleteById(String id);

}
