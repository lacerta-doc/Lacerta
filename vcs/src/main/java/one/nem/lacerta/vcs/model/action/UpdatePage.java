package one.nem.lacerta.vcs.model.action;

public class UpdatePage {

    private int index;
    private String fileName;

    public UpdatePage() {
    }

    public UpdatePage(int index, String fileName) {
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
