package one.nem.lacerta.data.module;

import one.nem.lacerta.data.impl.DeviceMetaImpl;
import one.nem.lacerta.data.repository.DeviceMeta;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
// Singleton
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
abstract class DeviceMetaModule {

    @Binds
    public abstract DeviceMeta bindDeviceMeta(DeviceMetaImpl deviceMetaImpl);

}
