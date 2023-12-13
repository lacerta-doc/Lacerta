package one.nem.lacerta.source.db;

import android.content.Context;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
abstract public class RoomUtilsModule {

    @Binds
    public abstract RoomUtils bindRoomUtils(Context context);
}
