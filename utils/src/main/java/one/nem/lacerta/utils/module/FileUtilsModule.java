package one.nem.lacerta.utils.module;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import one.nem.lacerta.utils.repository.FileUtils;
import one.nem.lacerta.utils.impl.FileUtilsImpl;

@Module
@InstallIn(SingletonComponent.class)
abstract public class FileUtilsModule {

    @Binds
    public abstract FileUtils bindFileUtils(FileUtilsImpl fileUtilsImpl);

}
