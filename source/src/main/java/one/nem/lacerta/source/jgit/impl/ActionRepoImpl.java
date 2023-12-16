package one.nem.lacerta.source.jgit.impl;

import org.eclipse.jgit.lib.Repository;

import one.nem.lacerta.source.jgit.ActionRepo;

public class ActionRepoImpl implements ActionRepo{

    @Override
    public String[] getUnstagedFiles(Repository repository) {
        return new String[0];
    }

    @Override
    public String[] getStagedFiles(Repository repository) {
        return new String[0];
    }

    @Override
    public void stageFile(Repository repository, String path) {

    }

    @Override
    public void unstageFile(Repository repository, String path) {

    }

    @Override
    public void commitFile(Repository repository, String path, String message) {

    }
}
