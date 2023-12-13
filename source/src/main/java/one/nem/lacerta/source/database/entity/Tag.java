package one.nem.lacerta.source.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tag")
public class Tag {

    @PrimaryKey
    @ColumnInfo(name = "id")
    public String id; // タグID

    @ColumnInfo(name = "tag_name")
    public String tagName; // タグ名

    @ColumnInfo(name = "color")
    public String color; // タグの色
}
