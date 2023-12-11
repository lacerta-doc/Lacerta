package one.nem.lacerta.source.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

import one.nem.lacerta.data.model.documents.enums.DocumentType;

@Entity
public class Documents {
    @PrimaryKey
    private String id;

    private String name; // Titleに変更する?

    private DocumentType type; // DocumentTypeに変更する?

    private Date created;

    private String[] tags;

    private String[] categories;

    private String repositoryId;

    // WIP
}
