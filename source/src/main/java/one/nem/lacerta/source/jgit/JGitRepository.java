package one.nem.lacerta.source.jgit;

public interface JGitRepository {

    // リポジトリ取得
    org.eclipse.jgit.lib.Repository getRepository(String id);
    // リポジトリ作成
    org.eclipse.jgit.lib.Repository createRepository(String id);
    // リポジトリ削除
    void deleteRepository(String id);
    // リポジトリ存在確認
    boolean repositoryExists(String id);


}
