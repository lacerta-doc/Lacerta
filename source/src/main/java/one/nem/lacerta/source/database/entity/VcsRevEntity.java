package one.nem.lacerta.source.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "vcs_rev")
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
    @ColumnInfo(name = "commit_date")
    public String commitDate;

    /**
     * 含まれるLogのID
     */
    @ColumnInfo(name = "log_ids")
    public List<String> logIds;
}
