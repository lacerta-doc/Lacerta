package one.nem.lacerta.source.jgit.impl;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Repository;

import one.nem.lacerta.source.jgit.ActionRepo;

public class ActionRepoImpl implements ActionRepo{

    Repository repository;

    // Internal method
    private Git getGit() {
        return new Git(repository);
    }

    @Override
    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    @Override
    public Repository getRepository() {
        if (repository == null) {
            throw new RuntimeException("リポジトリが設定されていません");
        }
        return repository;
    }

    @Override
    public String getRepositoryName() {
        return repository.getDirectory().getParentFile().getName();
    }

    @Override
    public String[] getUnstagedFiles() {
        Git git = new Git(repository);
        try {
            return git.status().call().getUntracked().toArray(new String[0]);
        } catch (Exception e) { // TODO-rca: エラーハンドリング
            return new String[0];
        }
    }

    @Override
    public String[] getStagedFiles() {
        Git git = new Git(repository);
        try {
            return git.status().call().getAdded().toArray(new String[0]);
        } catch (Exception e) { // TODO-rca: エラーハンドリング
            return new String[0];
        }
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
