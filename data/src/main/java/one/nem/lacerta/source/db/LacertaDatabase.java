package one.nem.lacerta.source.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import one.nem.lacerta.source.db.dao.DocumentsDao;
import one.nem.lacerta.source.db.dao.RepositoriesDao;
import one.nem.lacerta.source.db.entity.Documents;
import one.nem.lacerta.source.db.entity.Repositories;

@Database(entities = {Documents.class, Repositories.class}, version = 1)
public abstract class LacertaDatabase extends RoomDatabase {
    public abstract DocumentsDao documentsDao();
    public abstract RepositoriesDao repositoriesDao();
}
