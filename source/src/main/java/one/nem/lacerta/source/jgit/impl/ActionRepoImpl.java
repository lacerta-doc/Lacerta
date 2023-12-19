package one.nem.lacerta.source.jgit.impl;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Repository;

import javax.inject.Inject;

import one.nem.lacerta.source.jgit.ActionRepo;
import one.nem.lacerta.utils.LacertaLogger;

public class ActionRepoImpl implements ActionRepo{

    @Inject // Inject logger
    LacertaLogger logger;

    private final String TAG = "ActionRepoImpl";

    Repository repository;

    Git git;

    // Internal method
    private Git getGit() {
        if (this.git == null) {
            logger.debug(TAG, "getGit: git is null. Creating new Git instance");
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
            logger.warn(TAG, "getRepository: repository is null. Throwing RuntimeException");
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
            logger.error(TAG, "getUnstagedFiles: " + e.getMessage());
            return new String[0];
        }
    }

    @Override
    public String[] getStagedFiles() {
        Git git = getGit();
        try {
            return git.status().call().getAdded().toArray(new String[0]);
        } catch (Exception e) { // TODO-rca: エラーハンドリング
            logger.error(TAG, "getStagedFiles: " + e.getMessage());
            return new String[0];
        }
    }

    @Override
    public void stageFile(String path) {
        Git git = getGit();
        try {
            git.add().addFilepattern(path).call();
        } catch (Exception e) { // TODO-rca: エラーハンドリング
            logger.error(TAG, "stageFile: " + e.getMessage());
        }
    }

    @Override
    public void unstageFile(String path) {
        Git git = getGit();
        try {
            git.reset().addPath(path).call();
        } catch (Exception e) { // TODO-rca: エラーハンドリング
            logger.error(TAG, "unstageFile: " + e.getMessage());
        }
    }

    @Override
    public void commit(String message) {
        Git git = getGit();
        try {
            git.commit().setMessage(message).call();
        } catch (Exception e) { // TODO-rca: エラーハンドリング
            logger.error(TAG, "commit: " + e.getMessage());
        }
    }
}
