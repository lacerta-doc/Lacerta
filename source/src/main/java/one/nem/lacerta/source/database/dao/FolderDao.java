package one.nem.lacerta.source.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

import one.nem.lacerta.source.database.entity.FolderEntity;

@Dao
public interface FolderDao {

    @Query("SELECT * FROM Folder WHERE id = :id")
    FolderEntity findById(String id);

    @Query("SELECT * FROM Folder WHERE parent_id = :parentId")
    List<FolderEntity> findByParentId(String parentId);

    @Insert
    void insert(FolderEntity folderEntity);

    @Insert
    void insertAll(FolderEntity... folderEntities);
}
