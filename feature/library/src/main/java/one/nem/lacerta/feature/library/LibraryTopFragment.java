package one.nem.lacerta.feature.library;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import one.nem.lacerta.data.Document;
import one.nem.lacerta.model.document.DocumentMeta;
import one.nem.lacerta.model.document.tag.DocumentTag;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LibraryTopFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
public class LibraryTopFragment extends Fragment {

    @Inject
    Document document;

    private LibraryFolderFragment folderManager;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LibraryTopFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LibraryTopFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LibraryTopFragment newInstance(String param1, String param2) {
        LibraryTopFragment fragment = new LibraryTopFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        folderManager = new LibraryFolderFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // レイアウトファイル（fragment_library_top.xml）をインフレートしてビューを作成
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_library_top, container, false);

        // レイアウトファイル内のRecyclerView要素を取得
        // Use view.findViewById instead of findViewById
        RecyclerView documentRecyclerView = view.findViewById(R.id.document_list);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        documentRecyclerView.setLayoutManager(layoutManager);

        // ドキュメントのメタデータを取得
        List<DocumentMeta> metas = document.getAllDocumentMetas(100);

        // ドキュメントをデフォルトフォルダに追加
        // フォルダごとにドキュメントを管理する
        for (DocumentMeta meta : metas) {
            folderManager.addDocumentToFolder("Default Folder", meta);
        }

        // 特定のフォルダのドキュメントを取得
        List<DocumentMeta> folderDocuments = folderManager.getDocumentsInFolder("Default Folder");

        // トーストメッセージでドキュメントの数を表示
        Toast.makeText(getContext(), "ドキュメント数: " + Integer.toString(metas.size()), Toast.LENGTH_LONG).show();

// Create and set the adapter
        DocumentAdapter adapter = new DocumentAdapter(metas);
        documentRecyclerView.setAdapter(adapter);

// Use a LinearLayoutManager to specify the layout
        return view;
    }
}
