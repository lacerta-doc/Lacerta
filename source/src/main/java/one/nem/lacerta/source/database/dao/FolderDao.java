package one.nem.lacerta.source.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import one.nem.lacerta.source.database.entity.FolderEntity;

@Dao
public interface FolderDao {

    @Query("SELECT * FROM Folder WHERE id = :id")
    FolderEntity findById(String id);

    @Query("SELECT * FROM Folder WHERE public_path = :publicPath")
    FolderEntity findByPublicPath(String publicPath);

    @Insert
    void insert(FolderEntity folderEntity);

    @Insert
    void insertAll(FolderEntity... folderEntities);
}
