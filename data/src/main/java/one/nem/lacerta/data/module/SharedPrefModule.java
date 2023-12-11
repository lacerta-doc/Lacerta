package one.nem.lacerta.data.module;

import android.content.Context;
import android.content.SharedPreferences;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
abstract public class SharedPrefModule {

    @Binds
    abstract SharedPreferences bindSharedPreferences(Context applicationContext);
}
