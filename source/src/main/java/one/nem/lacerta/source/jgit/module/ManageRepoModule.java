package one.nem.lacerta.source.jgit.module;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import one.nem.lacerta.source.jgit.ManageRepo;
import one.nem.lacerta.source.jgit.impl.ManageRepoImpl;

@Module
@InstallIn(SingletonComponent.class)
abstract public class ManageRepoModule {

    @Binds
    public abstract ManageRepo bindManageRepo(ManageRepoImpl manageRepoImpl);
}
