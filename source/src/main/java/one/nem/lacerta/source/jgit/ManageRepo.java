package one.nem.lacerta.source.jgit;

import org.eclipse.jgit.lib.Repository;

public interface ManageRepo {

    // リポジトリ取得
    Repository getRepository(String id);
    // リポジトリ作成
    Repository createRepository(String id);
    // リポジトリ削除
    void deleteRepository(String id);
    // リポジトリ存在確認
    boolean repositoryExists(String id);


}
