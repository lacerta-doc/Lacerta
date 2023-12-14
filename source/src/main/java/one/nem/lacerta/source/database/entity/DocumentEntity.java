package one.nem.lacerta.source.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import one.nem.lacerta.source.database.common.DateTypeConverter;

import java.util.ArrayList;
import java.util.Date;

@Entity(tableName = "document")
@TypeConverters(DateTypeConverter.class)
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

    @ColumnInfo(name = "default_branch")
    public String defaultBranch; // デフォルトブランチ

    @ColumnInfo(name = "tag_ids
    public ArrayList<String> tagIds; // タグ
}
