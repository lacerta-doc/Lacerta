package one.nem.lacerta.source.db.dao;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import one.nem.lacerta.source.db.entity.Repositories;

@Dao
public interface RepositoriesDao {

    @Query("SELECT * FROM repositories")
    List<Repositories> getAll();

    @Query("SELECT * FROM repositories WHERE id IN (:ids)")
    List<Repositories> loadAllByIds(int[] ids);

    @Query("SELECT * FROM repositories WHERE relative_path LIKE :relativePath LIMIT 1")
    Repositories findByRelativePath(String relativePath);

    @Query("SELECT * FROM repositories WHERE id LIKE :id LIMIT 1")
    Repositories findById(String id);

}
