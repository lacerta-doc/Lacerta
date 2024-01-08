package one.nem.lacerta.source.file.module;

import java.nio.file.Path;

import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;

public class FileManagerModule {

    private final Path rootDir;

    @AssistedInject
    public FileManagerModule(@Assisted Path rootDir) {
        this.rootDir = rootDir;
    }

    public Path getRootDir() {
        return rootDir;
    }
}
