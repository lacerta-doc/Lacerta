package one.nem.lacerta.feature.library;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import one.nem.lacerta.model.document.DocumentMeta;

//フォルダ管理機能
public class LibraryFolderFragment extends Fragment {

    private Map<String, List<DocumentMeta>> folderMap;

    public LibraryFolderFragment() {
        folderMap = new HashMap<>();
    }

    public void addDocumentToFolder(String folderName, DocumentMeta documentMeta) {
        if (!folderMap.containsKey(folderName)) {
            folderMap.put(folderName, new ArrayList<>());
        }

        List<DocumentMeta> documents = folderMap.get(folderName);
        documents.add(documentMeta);
    }

    //フォルダに関連するドキュメントを取得するためのメソッド
    public List<DocumentMeta> getDocumentsInFolder(String folderName) {
        return folderMap.getOrDefault(folderName, new ArrayList<>());
    }
}
