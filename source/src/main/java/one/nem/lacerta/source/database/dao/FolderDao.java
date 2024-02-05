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

    @Query("SELECT * FROM Folder WHERE parent_id IS NULL")
    List<FolderEntity> findRootFolders();

    @Insert
    void insert(FolderEntity folderEntity);

    @Insert
    void insertAll(FolderEntity... folderEntities);

    @Query("DELETE FROM Folder WHERE id = :id")
    void deleteById(String id);
}
