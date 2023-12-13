package one.nem.lacerta.data.impl;

import org.eclipse.jgit.lib.Repository;

import javax.inject.Inject;

import one.nem.lacerta.data.repository.DebugFunc;
import one.nem.lacerta.source.jgit.RepoUtils;

public class DebugFuncImpl implements DebugFunc {

    @Inject
    public DebugFuncImpl() {
    }

    @Inject
    RepoUtils repoUtils;

    public Repository getOrCreateRepositoryById(String id) {
        Repository repo = repoUtils.getRepo(id); // TODO-rca: リポジトリの存在確認をもうすこしなんとかする
        if (repo == null) {
            repo = repoUtils.createRepo(id);
        }
//        Repository repo = repoUtils.createRepo(id); // debug
        return repo;
    }
}
