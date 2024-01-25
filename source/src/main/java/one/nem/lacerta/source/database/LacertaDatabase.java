package one.nem.lacerta.source.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

// Entities
import one.nem.lacerta.source.database.dao.FolderDao;
import one.nem.lacerta.source.database.entity.FolderEntity;
import one.nem.lacerta.source.database.entity.TagEntity;
import one.nem.lacerta.source.database.entity.DocumentEntity;
import one.nem.lacerta.source.database.entity.LibraryEntity;
import one.nem.lacerta.source.database.entity.ToxiDocumentTagEntity;
import one.nem.lacerta.source.database.entity.VcsRevEntity;
import one.nem.lacerta.source.database.entity.VcsLogEntity;

// Daos
import one.nem.lacerta.source.database.dao.TagDao;
import one.nem.lacerta.source.database.dao.DocumentDao;
import one.nem.lacerta.source.database.dao.LibraryDao;
import one.nem.lacerta.source.database.dao.VcsRevDao;
import one.nem.lacerta.source.database.dao.VcsLogDao;

@Database(entities = {TagEntity.class, DocumentEntity.class, LibraryEntity.class, VcsRevEntity.class, VcsLogEntity.class, FolderEntity.class, ToxiDocumentTagEntity.class}, version = 6)
public abstract class LacertaDatabase extends RoomDatabase {
    public abstract TagDao tagDao();
    public abstract DocumentDao documentDao();
    public abstract LibraryDao libraryDao();
    public abstract VcsRevDao vcsRevDao();
    public abstract VcsLogDao vcsLogDao();
    public abstract FolderDao folderDao();
}
