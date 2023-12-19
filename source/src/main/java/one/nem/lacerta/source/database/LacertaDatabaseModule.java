package one.nem.lacerta.source.database;

import android.content.Context;

import androidx.room.Room;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class LacertaDatabaseModule {

    @Provides
    public static LacertaDatabase provideLacertaDatabase(@ApplicationContext Context context) {
        return Room.databaseBuilder(context,
                LacertaDatabase.class,
                "lacerta.db")
                .allowMainThreadQueries() // Debug
                .build();
    }
}
