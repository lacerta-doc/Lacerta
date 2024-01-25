package one.nem.lacerta.source.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(primaryKeys = {"document_id", "tag_id"}, tableName = "toxi_document_tag")
public class ToxiDocumentTagEntity {

    @NonNull
    @ColumnInfo(name = "document_id")
    public String documentId; // ドキュメントID

    @NonNull
    @ColumnInfo(name = "tag_id")
    public String tagId; // タグID
}
