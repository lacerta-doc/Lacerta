package one.nem.lacerta.data.module;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.migration.DisableInstallInCheck;

import one.nem.lacerta.data.LacertaLibrary;
import one.nem.lacerta.data.impl.LacertaLibraryStubImpl;

@Module
@DisableInstallInCheck
abstract public class LacertaLibraryModule {

    @Binds
    public abstract LacertaLibrary bindLacertaLibrary(LacertaLibraryStubImpl impl);

}
