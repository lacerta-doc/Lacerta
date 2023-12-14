package one.nem.lacerta.source.jgit.impl;

import one.nem.lacerta.source.jgit.ManageRepo;

import javax.inject.Inject;

import org.eclipse.jgit.lib.Repository;
public class ManageRepoImpl implements ManageRepo {

    @Override
    public Repository getRepository(String id) {
        return null;
    }

    @Override
    public Repository createRepository(String id) {
        return null;
    }

    @Override
    public void deleteRepository(String id) {

    }

    @Override
    public boolean repositoryExists(String id) {
        return false;
    }
}
