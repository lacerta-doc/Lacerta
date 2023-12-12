package one.nem.lacerta.data.repository;

import org.eclipse.jgit.lib.Repository;

public interface DebugFunc {

    Repository getOrCreateRepositoryById(String id);

}
