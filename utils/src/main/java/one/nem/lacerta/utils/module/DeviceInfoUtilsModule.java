package one.nem.lacerta.utils.module;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import one.nem.lacerta.utils.repository.DeviceInfoUtils;
import one.nem.lacerta.utils.impl.DeviceInfoUtilsImpl;

@Module
@InstallIn(SingletonComponent.class) // TODO-rca: Singletonである必要があるのか検討する?
abstract public class DeviceInfoUtilsModule {

    @Binds
    public abstract DeviceInfoUtils bindDeviceInfoUtils(DeviceInfoUtilsImpl deviceInfoUtilsImpl);
}
