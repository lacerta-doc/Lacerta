package one.nem.lacerta.source.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Repositories {

    @PrimaryKey
    private String id;

    private String relativePath;

}
