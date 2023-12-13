package one.nem.lacerta.source.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "library")
public class Library {

    @PrimaryKey
    @ColumnInfo(name = "id")
    public String id; // ドキュメントID

    @ColumnInfo(name = "root_path")
    public String rootPath; // rootのパス

    @ColumnInfo(name = "path")
    public String path; // パス
}
