package one.nem.lacerta.source.jgit.impl;

import one.nem.lacerta.source.jgit.JGitRepository;

import javax.inject.Inject;

import one.nem.lacerta.utils.repository.DeviceInfoUtils;

import org.eclipse.jgit.lib.Repository;

import org.eclipse.jgit.lib.RepositoryBuilder;

public class JGitRepositoryImpl implements JGitRepository {

    private final DeviceInfoUtils deviceInfoUtils;

    @Inject
    public JGitRepositoryImpl(DeviceInfoUtils deviceInfoUtils) {
        this.deviceInfoUtils = deviceInfoUtils;
    }

    @Override
    public Repository getRepository(String id) {
        RepositoryBuilder repositoryBuilder = new RepositoryBuilder();
        repositoryBuilder.setGitDir(deviceInfoUtils.getExternalStorageDirectory().resolve(id).resolve(".git").toFile());
        repositoryBuilder.setMustExist(true);
        try {
            return repositoryBuilder.build();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Repository createRepository(String id) {
        RepositoryBuilder repositoryBuilder = new RepositoryBuilder();
        repositoryBuilder.setGitDir(deviceInfoUtils.getExternalStorageDirectory().resolve(id).resolve(".git").toFile());
        repositoryBuilder.setMustExist(false);
        try {
            return repositoryBuilder.build();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void deleteRepository(String id) {
        // TODO-rca: 未実装
    }

    @Override
    public boolean repositoryExists(String id) {
        return false; // TODO-rca: 未実装
    }
}
