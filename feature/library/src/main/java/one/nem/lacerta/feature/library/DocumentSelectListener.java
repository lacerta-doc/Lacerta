package one.nem.lacerta.feature.library;

public interface DocumentSelectListener {
    void onFolderSelected(String folderId, String folderName);
    void onDocumentSelected(String documentId, String documentName);
}
