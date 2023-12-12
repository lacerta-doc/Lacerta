package one.nem.lacerta.source.jgit;

import org.eclipse.jgit.lib.Repository;

public class RepoUtils {
    // Internal Utils
    public static String getRepoName(Repository repo) {
        return repo.getDirectory().getParentFile().getName();
    }


}
