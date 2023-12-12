package one.nem.lacerta.source.jgit;

import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

public class RepoUtils {
    // Internal Utils

    public Repository createRepo(String id, ) {
        Repository repository = FileRepositoryBuilder.create(

        )
    }
    public Repository getRepo(String id) {
        // WIP
    }
    public String getRepoName(Repository repo) {
        return repo.getDirectory().getParentFile().getName();
    }


}
