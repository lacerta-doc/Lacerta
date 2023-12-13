package one.nem.lacerta.source.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Repositories {

    @PrimaryKey
    private String id;

    @ColumnInfo(name = "relative_path")
    private String relativePath;

}
