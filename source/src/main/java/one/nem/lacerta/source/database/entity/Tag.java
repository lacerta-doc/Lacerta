package one.nem.lacerta.source.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tag")
public class Tag {

    @PrimaryKey
    public String id; // タグID

    public String tagName; // タグ名

    public String color; // タグの色
}
