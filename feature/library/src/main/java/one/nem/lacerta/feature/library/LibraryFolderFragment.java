package one.nem.lacerta.feature.library;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import one.nem.lacerta.model.ListItem;

//フォルダ管理機能
public class LibraryFolderFragment extends Fragment {

    private Map<String, List<ListItem>> folderMap;

    public LibraryFolderFragment() {
        folderMap = new HashMap<>();
    }

    public void addDocumentToFolder(String folderName, ListItem documentMeta) {
        if (!folderMap.containsKey(folderName)) {
            folderMap.put(folderName, new ArrayList<>());
        }

        List<ListItem> documents = folderMap.get(folderName);
        documents.add(documentMeta);
    }

    //フォルダに関連するドキュメントを取得するためのメソッド
    public List<ListItem> getDocumentsInFolder(String folderName) {
        return folderMap.getOrDefault(folderName, new ArrayList<>());
    }

    public List<ListItem> getDocumentInFolder(String default_folder) {
        return null;
    }
}
