package one.nem.lacerta.source.jgit;

import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

import java.io.File;
import java.nio.file.Path;

import javax.inject.Inject;

import one.nem.lacerta.utils.repository.FileUtils;

public class RepoUtils {
    // Internal Utils

    @Inject // TODO-rca: 実装を切り離す?
    RepoUtils() {
    }

    @Inject
    FileUtils fileUtils;


    public Repository createRepo(String id) {
        Path rootPath = fileUtils.getExternalFilesDirPath();

        try {
            Repository repo = FileRepositoryBuilder.create(
                    new File(rootPath.resolve(id).resolve(".git").toString())
            );
            return repo;
        } catch (Exception e) {
            // TODO-rca: handle exception
            return null;
        }
    }
    public Repository getRepo(String id) {
        Path rootPath = fileUtils.getExternalFilesDirPath();
        try {
            Repository repo = new FileRepositoryBuilder().setGitDir(rootPath.resolve(id).resolve(".git").toFile()).build();
            return repo;
        } catch (Exception e) {
            // TODO-rca: handle exception
            return null;
        }
    }
    public String getRepoName(Repository repo) {
        return repo.getDirectory().getParentFile().getName();
    }


}
