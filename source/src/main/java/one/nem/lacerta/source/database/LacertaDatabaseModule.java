package one.nem.lacerta.source.database;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class LacertaDatabaseModule {

    @Provides
    public LacertaDatabase provideLacertaDatabase() {
        return null;
    }
}
