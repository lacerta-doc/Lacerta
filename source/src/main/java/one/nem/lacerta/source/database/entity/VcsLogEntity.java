package one.nem.lacerta.source.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

import one.nem.lacerta.source.database.common.DateTypeConverter;

@Entity(tableName = "vcs_log")
@TypeConverters({DateTypeConverter.class})
public class VcsLogEntity {

    /**
     * イベントID
     */
    @PrimaryKey
    @ColumnInfo(name = "id")
    @NonNull
    public String id;

    /**
     * ドキュメントID
     */
    @ColumnInfo(name = "document_id")
    public String documentId;

    /**
     * ブランチ名
     */
    @ColumnInfo(name = "branch_name")
    public String branchName;

    /**
     * 発生日時
     */
    @ColumnInfo(name = "created_at")
    public Date createdAt;

    /**
     * 発生アクション
     */
    @ColumnInfo(name = "action")
    public String action;
}
