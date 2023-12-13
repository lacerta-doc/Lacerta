package one.nem.lacerta.source.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "library")
public class Library {

    @PrimaryKey
    public String id; // ドキュメントID

    public String rootPath; // rootのパス

    public String path; // パス
}
