package one.nem.lacerta.source.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "document")
public class Document {
    @PrimaryKey
    public String id; // ドキュメントID

    public String title; // タイトル

    public String createdAt; // 作成日時

    public String updatedAt; // 更新日時

    public String author; // 作成者

    public String defaultBranch; // デフォルトブランチ
}
