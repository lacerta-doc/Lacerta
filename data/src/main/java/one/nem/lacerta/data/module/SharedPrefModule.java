package one.nem.lacerta.data.module;

import android.content.Context;
import android.content.SharedPreferences;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import one.nem.lacerta.data.impl.SharedPrefImpl;
import one.nem.lacerta.data.repository.SharedPref;

@Module
@InstallIn(SingletonComponent.class)
abstract public class SharedPrefModule {

    @Binds
    public abstract SharedPref bindSharedPref(SharedPrefImpl sharedPrefImpl);
}
