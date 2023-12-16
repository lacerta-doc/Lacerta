package one.nem.lacerta.source.jgit.impl;

import org.eclipse.jgit.lib.Repository;

import one.nem.lacerta.source.jgit.ActionRepo;

public class ActionRepoImpl implements ActionRepo{

    Repository repository;

    @Override
    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    @Override
    public Repository getRepository() {
        return null;
    }

    @Override
    public String getRepositoryName() {
        return null;
    }

    @Override
    public String[] getUnstagedFiles() {
        return new String[0];
    }

    @Override
    public String[] getStagedFiles() {
        return new String[0];
    }

    @Override
    public void stageFile(String path) {

    }

    @Override
    public void unstageFile(String path) {

    }

    @Override
    public void commit(String message) {

    }
}
