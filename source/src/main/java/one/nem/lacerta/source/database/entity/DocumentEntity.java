package one.nem.lacerta.source.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import one.nem.lacerta.source.database.common.DateTypeConverter;
import one.nem.lacerta.source.database.common.TagListConverter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(tableName = "document")
@TypeConverters({DateTypeConverter.class, TagListConverter.class})
public class DocumentEntity {
    @PrimaryKey
    @ColumnInfo(name = "id")
    @NonNull
    public String id; // ドキュメントID

    @ColumnInfo(name = "title")
    public String title; // タイトル

    @ColumnInfo(name = "created_at")
    public Date createdAt; // 作成日時

    @ColumnInfo(name = "updated_at")
    public Date updatedAt; // 更新日時

    @ColumnInfo(name = "author")
    public String author; // 作成者

    @ColumnInfo(name = "parent_id")
    public String parentId; // 親フォルダID

    @ColumnInfo(name = "is_combine_child")
    public boolean isCombineChild; // 結合されたドキュメントの子かどうか

    @ColumnInfo(name = "is_combine_parent")
    public boolean isCombineParent; // 結合されたドキュメントの親かどうか
}
