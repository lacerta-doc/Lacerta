package one.nem.lacerta.source.database.dao;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import one.nem.lacerta.source.database.entity.Tag;

@Dao
public interface TagDao {

    @Query("SELECT * FROM tag WHERE id = :id")
    Tag findById(String id);

    @Query("SELECT * FROM tag")
    List<Tag> findAll();

    @Query("SELECT * FROM tag WHERE id IN (:ids)")
    List<Tag> findByIds(List<String> ids);

    // WIP
    // TODO-rca: Insert, Update, Delete
}
