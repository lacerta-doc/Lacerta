package one.nem.lacerta.source.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "toxi_document_tag")
public class ToxiDocumentTagEntity {

    // 複合主キー
    @PrimaryKey
    @ColumnInfo(name = "document_id")
    public String documentId; // ドキュメントID

    @PrimaryKey
    @ColumnInfo(name = "tag_id")
    public String tagId; // タグID
}
