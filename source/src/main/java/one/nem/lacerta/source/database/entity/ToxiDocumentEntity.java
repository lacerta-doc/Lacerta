package one.nem.lacerta.source.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(primaryKeys = {"parent_document_id", "child_document_id"}, tableName = "toxi_document")
public class ToxiDocumentEntity {

    @ColumnInfo(name = "parent_document_id")
    @NonNull
    public String parentDocumentId;

    @ColumnInfo(name = "child_document_id")
    @NonNull
    public String childDocumentId;

    @ColumnInfo(name = "order")
    public int order;

    @ColumnInfo(name = "is_active")
    public boolean isActive;
}
