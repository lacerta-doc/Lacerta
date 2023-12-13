package one.nem.lacerta.source.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

import one.nem.lacerta.data.model.documents.enums.DocumentType;

@Entity
public class Documents {
    @PrimaryKey
    private String id;

    @ColumnInfo(name = "title")
    private String title; // Titleに変更する?

    @ColumnInfo(name = "type")
    private DocumentType type; // DocumentTypeに変更する?

    @ColumnInfo(name = "created")
    private Date created;

    @ColumnInfo(name = "updated")
    private Date updated;

    @ColumnInfo(name = "tags")
    private String[] tags;

    @ColumnInfo(name = "categories")
    private String[] categories;

    // WIP
}
