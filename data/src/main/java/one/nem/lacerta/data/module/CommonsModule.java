package one.nem.lacerta.data.module;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import one.nem.lacerta.data.repository.Commons;
import one.nem.lacerta.data.impl.CommonsImpl;

@Module
@InstallIn(SingletonComponent.class)
abstract class CommonsModule {

    @Binds
    public abstract Commons bindCommons(CommonsImpl commonsImpl);
}
