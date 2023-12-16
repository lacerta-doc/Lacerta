package one.nem.lacerta.source.jgit.impl;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Repository;

import one.nem.lacerta.source.jgit.ActionRepo;

public class ActionRepoImpl implements ActionRepo{

    Repository repository;

    Git git;

    // Internal method
    private Git getGit() {
        if (this.git == null) {
            this.git = new Git(repository);
        }
        return this.git;
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
        Git git = getGit();
        try {
            return git.status().call().getUntracked().toArray(new String[0]);
        } catch (Exception e) { // TODO-rca: エラーハンドリング
            return new String[0];
        }
    }

    @Override
    public String[] getStagedFiles() {
        Git git = getGit();
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
