package one.nem.lacerta.source.file.factory;

import java.nio.file.Path;

import dagger.assisted.AssistedFactory;
import one.nem.lacerta.source.file.impl.FileManagerImpl;

@AssistedFactory
public interface FileManagerFactory {
    FileManagerImpl create(Path rootDir);
}
