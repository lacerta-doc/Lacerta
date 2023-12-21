package one.nem.lacerta.source.file.factory;

import java.nio.file.Path;

import dagger.assisted.AssistedFactory;
import one.nem.lacerta.source.file.FileManager;

@AssistedFactory
public interface FileManagerFactory {
    FileManager create(Path rootDir);
}
