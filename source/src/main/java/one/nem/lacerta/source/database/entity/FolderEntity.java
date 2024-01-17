package one.nem.lacerta.source.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "folder")
public class FolderEntity {

    @PrimaryKey
    @ColumnInfo(name = "id")
    @NonNull
    public String id; // フォルダID

    @ColumnInfo(name = "title")
    public String name; // フォルダ名

}
