package one.nem.lacerta.source.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;
import java.util.List;

import one.nem.lacerta.source.database.common.DateTypeConverter;

@Entity(tableName = "vcs_rev")
@TypeConverters({DateTypeConverter.class})
public class VcsRevEntity {

    /**
     * リビジョンID
     */
    @PrimaryKey
    @ColumnInfo(name = "id")
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
     * コミットメッセージ
     */
    @ColumnInfo(name = "commit_message")
    public String commitMessage;

    /**
     * コミット日時
     */
    @ColumnInfo(name = "created_at")
    public Date createdAt;

    /**
     * 含まれるLogのID
     */
    @ColumnInfo(name = "log_ids")
    public List<String> logIds;
}
