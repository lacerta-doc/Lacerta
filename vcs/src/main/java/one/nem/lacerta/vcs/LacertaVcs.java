package one.nem.lacerta.vcs;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import one.nem.lacerta.model.VcsLogModel;
import one.nem.lacerta.model.VcsRevModel;
import one.nem.lacerta.model.document.DocumentDetail;

public interface LacertaVcs {

    // Actions
    public void updatePage(int index, String fileName);

    public void insertPage(int index, String fileName);

    public void deletePage(int index);

    public void undo();

    public void createDocument(String documentId);

    public void generateRevisionAtCurrent(String message);

    public CompletableFuture<ArrayList<VcsRevModel>> getRevisionHistory();

    public CompletableFuture<ArrayList<VcsLogModel>> getLogHistory();

    public CompletableFuture<ArrayList<VcsLogModel>> getLogHistoryInRev(String revId);

    public CompletableFuture<ArrayList<String>> getDocumentPagePathListRev(String revId);


    // debug
    public void printLog();

    public void printRev();

}
