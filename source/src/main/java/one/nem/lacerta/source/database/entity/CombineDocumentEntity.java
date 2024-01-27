package one.nem.lacerta.source.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "combine_document")
public class CombineDocumentEntity {

    @PrimaryKey
    @ColumnInfo(name = "id")
    @NonNull
    public String id; // ドキュメントID

    @ColumnInfo(name = "title")
    public String title; // タイトル

    @ColumnInfo(name = "created_at")
    public String createdAt; // 作成日時

    @ColumnInfo(name = "updated_at")
    public String updatedAt; // 更新日時

    @ColumnInfo(name = "author")
    public String author; // 作成者

    @ColumnInfo(name = "parent_id")
    public String parentId; // 親フォルダID
}
