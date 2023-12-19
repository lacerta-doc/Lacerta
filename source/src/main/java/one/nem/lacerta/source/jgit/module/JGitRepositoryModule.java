package one.nem.lacerta.source.jgit.module;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import one.nem.lacerta.source.jgit.JGitRepository;

@Module
@InstallIn(SingletonComponent.class)
abstract public class JGitRepositoryModule {

    @Binds
    public abstract JGitRepository bindManageRepo(one.nem.lacerta.source.jgit.impl.JGitRepositoryImpl manageRepoImpl);
}
