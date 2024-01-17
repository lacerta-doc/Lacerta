package one.nem.lacerta.vcs.factory;

import dagger.assisted.AssistedFactory;
import one.nem.lacerta.vcs.impl.LacertaVcsImpl;

@AssistedFactory
public interface LacertaVcsFactory {

    LacertaVcsImpl create(String documentId);
}
