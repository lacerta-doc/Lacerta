package one.nem.lacerta.source.jgit.impl;

import org.eclipse.jgit.lib.Repository;

import one.nem.lacerta.source.jgit.ActionRepo;

public class ActionRepoImpl implements ActionRepo{

    @Override
    public Repository setRepository(Repository repository) {
        return null;
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
