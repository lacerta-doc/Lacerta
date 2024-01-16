package one.nem.lacerta.vcs;

public interface LacertaVcs {

    // Actions
    public void updatePage(int index, String fileName);

    public void insertPage(int index, String fileName);

    public void deletePage(int index);

    public void createDocument(String documentId);

    // debug
    public void printLog();

}
