package one.nem.lacerta.data.module;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.FragmentComponent;
import dagger.hilt.migration.DisableInstallInCheck;

import one.nem.lacerta.data.LacertaLibrary;
import one.nem.lacerta.data.impl.LacertaLibraryImpl;
import one.nem.lacerta.utils.LacertaLogger;

@Module
// Fragmentにinstall
@InstallIn(FragmentComponent.class)
abstract public class LacertaLibraryModule {

    @Binds
    public abstract LacertaLibrary bindLacertaLibrary(LacertaLibraryImpl impl);

}
