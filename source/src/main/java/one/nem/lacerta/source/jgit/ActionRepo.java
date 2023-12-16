package one.nem.lacerta.source.jgit;

import org.eclipse.jgit.lib.Repository;

public interface ActionRepo {

    // リポジトリをインスタンス変数に格納
    Repository setRepository(Repository repository);

    // リポジトリ取得
    Repository getRepository();

    // リポジトリ名取得
    String getRepositoryName();

    // ステージングされていないファイルの一覧を取得
    String[] getUnstagedFiles();

    // ステージングされているファイルの一覧を取得
    String[] getStagedFiles();

    // ファイルをステージング
    void stageFile(String path);

    // ファイルをアンステージング
    void unstageFile(String path);

    // ステージングされているファイルをコミット
    void commit(String message);

}
