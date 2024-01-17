package one.nem.lacerta.data.module;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.FragmentComponent;
import one.nem.lacerta.data.LacertaSearch;
import one.nem.lacerta.data.impl.LacertaSearchStubImpl;

@Module
@InstallIn(FragmentComponent.class)
abstract public class LacertaSearchModule {

    @Binds
    public abstract LacertaSearch bindLacertaSearch(LacertaSearchStubImpl impl);
}
