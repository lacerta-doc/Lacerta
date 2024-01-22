package one.nem.lacerta.vcs;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import one.nem.lacerta.model.VcsRevModel;

public interface LacertaVcs {

    // Actions
    public void updatePage(int index, String fileName);

    public void insertPage(int index, String fileName);

    public void deletePage(int index);

    public void createDocument(String documentId);

    public void generateRevisionAtCurrent(String message);

    public CompletableFuture<ArrayList<VcsRevModel>> getRevisionHistory();


    // debug
    public void printLog();

    public void printRev();

}
