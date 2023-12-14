package one.nem.lacerta.utils.module;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import one.nem.lacerta.utils.repository.SharedPrefUtils;
import one.nem.lacerta.utils.impl.SharedPrefUtilsImpl;

@Module
@InstallIn(SingletonComponent.class) // TODO-rca: Singletonである必要があるのか検討する?
abstract class SharedPrefUtilsModule {

        @Binds
        public abstract SharedPrefUtils bindSharedPrefUtils(SharedPrefUtilsImpl sharedPrefUtilsImpl);
}
