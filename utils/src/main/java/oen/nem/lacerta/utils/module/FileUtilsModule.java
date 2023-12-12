package oen.nem.lacerta.utils.module;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import one.nem.lacerta.data.utils.UtilsImpl;
import one.nem.lacerta.data.utils.UtilsRepo;

@Module
@InstallIn(SingletonComponent.class)
abstract public class FileUtilsModule {

    @Binds
    public abstract UtilsRepo bindUtils(UtilsImpl utilsImpl);
}
