package one.nem.lacerta.source.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import one.nem.lacerta.source.database.entity.VcsLogEntity;

@Dao
public interface VcsLogDao {

    // Select
    @Query("SELECT * FROM vcs_log WHERE id = :id")
    VcsLogEntity findById(String id);

    @Query("SELECT * FROM vcs_log")
    List<VcsLogEntity> findAll();

    @Query("SELECT * FROM vcs_log WHERE id IN (:ids)")
    List<VcsLogEntity> findByIds(List<String> ids);

    @Query("SELECT * FROM vcs_log WHERE document_id = :documentId")
    List<VcsLogEntity> findByDocumentId(String documentId);

    @Query("SELECT * FROM vcs_log WHERE document_id = :documentId AND is_included = :isIncluded ORDER BY created_at")
    List<VcsLogEntity> findByDocumentIdAndIncluded(String documentId, boolean isIncluded);

    @Query("SELECT * FROM vcs_log WHERE document_id = :documentId AND branch_name = :branchName")
    List<VcsLogEntity> findByDocumentIdAndBranchName(String documentId, String branchName);

    @Query("SELECT * FROM vcs_log WHERE document_id = :documentId AND branch_name = :branchName AND is_included = :isIncluded ORDER BY created_at")
    List<VcsLogEntity> findByDocumentIdAndBranchNameAndIncluded(String documentId, String branchName, boolean isIncluded);
    // Insert

    @Insert
    void insertAll(VcsLogEntity... vcsLogs);

    @Insert
    void insertAll(List<VcsLogEntity> vcsLogs);

    @Insert
    void insert(VcsLogEntity vcsLog);

    @Update
    void update(VcsLogEntity vcsLog);

    @Update
    void updateAll(VcsLogEntity... vcsLogs);

    @Update
    void updateAll(List<VcsLogEntity> vcsLogs);
}
