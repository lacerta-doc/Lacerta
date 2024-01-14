package one.nem.lacerta.source.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import one.nem.lacerta.source.database.entity.VcsRevEntity;

@Dao
public interface VcsRevDao {

    // Select
    @Query("SELECT * FROM vcs_rev WHERE id = :id")
    VcsRevEntity findById(String id);

    @Query("SELECT * FROM vcs_rev")
    List<VcsRevEntity> findAll();

    @Query("SELECT * FROM vcs_rev WHERE id IN (:ids)")
    List<VcsRevEntity> findByIds(List<String> ids);

    @Query("SELECT * FROM vcs_rev WHERE document_id = :documentId")
    List<VcsRevEntity> findByDocumentId(String documentId);

    @Query("SELECT * FROM vcs_rev WHERE document_id = :documentId ORDER BY created_at DESC LIMIT 1")
    VcsRevEntity findLatestByDocumentId(String documentId);

    @Query("SELECT * FROM vcs_rev WHERE document_id = :documentId AND branch_name = :branchName ORDER BY created_at DESC LIMIT 1")
    VcsRevEntity findLatestByDocumentIdAndBranchName(String documentId, String branchName);

    @Insert
    void insertAll(VcsRevEntity... vcsRevs);

    @Insert
    void insertAll(List<VcsRevEntity> vcsRevs);

    @Insert
    void insert(VcsRevEntity vcsRev);

    // TODO-rca: Update, Deleteが必要か検討

}
