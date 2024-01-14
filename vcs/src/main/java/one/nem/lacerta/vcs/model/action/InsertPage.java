package one.nem.lacerta.vcs.model.action;

public class InsertPage {

    private int index;
    private String fileName;

    public InsertPage() {
    }

    public InsertPage(int index, String fileName) {
        this.index = index;
        this.fileName = fileName;
    }

    public int getIndex() {
        return index;
    }

    public String getFileName() {
        return fileName;
    }
}
