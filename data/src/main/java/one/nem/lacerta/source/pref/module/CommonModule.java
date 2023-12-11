package one.nem.lacerta.source.pref.module;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import one.nem.lacerta.source.pref.impl.CommonImpl;
import one.nem.lacerta.source.pref.repository.Common;

@Module
@InstallIn(SingletonComponent.class)
abstract public class CommonModule {

    @Binds
    public abstract Common bindCommon(CommonImpl commonImpl);
}
