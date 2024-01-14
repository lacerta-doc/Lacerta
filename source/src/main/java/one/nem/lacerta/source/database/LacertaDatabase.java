package one.nem.lacerta.source.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

// Entities
import one.nem.lacerta.source.database.entity.TagEntity;
import one.nem.lacerta.source.database.entity.DocumentEntity;
import one.nem.lacerta.source.database.entity.LibraryEntity;
import one.nem.lacerta.source.database.entity.VcsRevEntity;
import one.nem.lacerta.source.database.entity.VcsLogEntity;

// Daos
import one.nem.lacerta.source.database.dao.TagDao;
import one.nem.lacerta.source.database.dao.DocumentDao;
import one.nem.lacerta.source.database.dao.LibraryDao;

@Database(entities = {TagEntity.class, DocumentEntity.class, LibraryEntity.class, VcsRevEntity.class, VcsLogEntity.class}, version = 1)
public abstract class LacertaDatabase extends RoomDatabase {
    public abstract TagDao tagDao();
    public abstract DocumentDao documentDao();
    public abstract LibraryDao libraryDao();
}
