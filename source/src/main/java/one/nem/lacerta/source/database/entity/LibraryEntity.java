package one.nem.lacerta.source.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "library")
public class LibraryEntity {

    @PrimaryKey
    @ColumnInfo(name = "id")
    @NonNull
    public String id; // ドキュメントID

    @ColumnInfo(name = "root_path")
    public String rootPath; // rootのパス

    @ColumnInfo(name = "path")
    public String path; // パス

}
