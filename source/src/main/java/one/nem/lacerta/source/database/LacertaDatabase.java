package one.nem.lacerta.source.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

// Entities
import one.nem.lacerta.source.database.entity.TagEntity;
import one.nem.lacerta.source.database.entity.DocumentEntity;
import one.nem.lacerta.source.database.entity.LibraryEntity;

// Daos
import one.nem.lacerta.source.database.dao.TagDao;
import one.nem.lacerta.source.database.dao.DocumentDao;
import one.nem.lacerta.source.database.dao.LibraryDao;

@Database(entities = {TagEntity.class, DocumentEntity.class, LibraryEntity.class}, version = 1)
public abstract class LacertaDatabase extends RoomDatabase {
    public abstract TagDao tagDao();
    public abstract DocumentDao documentDao();
    public abstract LibraryDao libraryDao();
}
