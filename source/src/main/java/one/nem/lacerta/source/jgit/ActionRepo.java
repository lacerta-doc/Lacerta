package one.nem.lacerta.source.jgit;

import org.eclipse.jgit.lib.Repository;

public interface ActionRepo {

    // 未ステージングのファイルリスト
    String[] getUnstagedFiles(Repository repository);

    // ステージング済みのファイルリスト
    String[] getStagedFiles(Repository repository);

    // ファイルをステージングする
    void stageFile(Repository repository, String path);

    // ファイルをアンステージングする
    void unstageFile(Repository repository, String path);

    // ファイルをコミットする
    void commitFile(Repository repository, String path, String message);

    // ファイルを削除する
//    void deleteFile(Repository repository, String path); // TODO-rca:

}
