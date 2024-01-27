package one.nem.lacerta.source.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "toxi_document")
public class ToxiDocumentEntity {

    @ColumnInfo(name = "parent_document_id")
    public String parentDocumentId;

    @ColumnInfo(name = "child_document_id")
    public String childDocumentId;

    @ColumnInfo(name = "order")
    public int order;

    @ColumnInfo(name = "is_active")
    public boolean isActive;
}
