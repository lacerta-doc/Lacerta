package one.nem.lacerta.data.module;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import one.nem.lacerta.data.repository.DebugFunc;
import one.nem.lacerta.data.impl.DebugFuncImpl;

@Module
@InstallIn(DebugFuncImpl.class)
abstract class DebugFuncModule {

    @Binds
    public abstract DebugFunc bindDebugFunc(DebugFuncImpl debugFuncImpl);
}
