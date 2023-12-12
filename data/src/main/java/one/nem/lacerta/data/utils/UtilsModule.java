package one.nem.lacerta.data.utils;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import one.nem.lacerta.data.utils.UtilsRepo;
import one.nem.lacerta.data.utils.UtilsImpl;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
abstract public class UtilsModule {

    @Binds
    public abstract UtilsRepo bindUtils(UtilsImpl utilsImpl);
}
