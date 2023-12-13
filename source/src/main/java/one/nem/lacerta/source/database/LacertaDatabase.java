package one.nem.lacerta.source.database;

import androidx.room.Database;

// Entities
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import one.nem.lacerta.source.database.entity.Tag;
import one.nem.lacerta.source.database.entity.Document;
import one.nem.lacerta.source.database.entity.Library;

// Daos
import one.nem.lacerta.source.database.dao.TagDao;
import one.nem.lacerta.source.database.dao.DocumentDao;
import one.nem.lacerta.source.database.dao.LibraryDao;

@Database(entities = {Tag.class, Document.class, Library.class}, version = 1)
@InstallIn(SingletonComponent.class)
public abstract class LacertaDatabase {
    public abstract TagDao tagDao();
    public abstract DocumentDao documentDao();
    public abstract LibraryDao libraryDao();
}
