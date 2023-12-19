package one.nem.lacerta.utils.module;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

import one.nem.lacerta.utils.LacertaLogger;
import one.nem.lacerta.utils.impl.LacertaLoggerImpl;

@Module
@InstallIn(SingletonComponent.class) // ログの取得であまり負荷をかけたくないので
abstract public class LacertaLoggerModule {

    @Binds
    public abstract LacertaLogger bindLacertaLogger(LacertaLoggerImpl lacertaLoggerImpl);
}
