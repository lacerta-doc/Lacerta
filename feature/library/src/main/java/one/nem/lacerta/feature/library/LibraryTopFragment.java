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
import one.nem.lacerta.data.impl.LacertaLibraryImpl;
import one.nem.lacerta.model.document.DocumentMeta;
import one.nem.lacerta.model.document.tag.DocumentTag;

import one.nem.lacerta.data.LacertaLibrary;
import one.nem.lacerta.model.LibraryItemPage;
import one.nem.lacerta.model.ListItem;
import one.nem.lacerta.model.ListItemType;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LibraryTopFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
public class LibraryTopFragment extends Fragment {

    @Inject
    Document document;

    @Inject
    LacertaLibrary lacertaLibrary;

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

        try {
            LacertaLibrary lacertaLibrary = new LacertaLibraryImpl();
            // ドキュメントのメタデータを取得
            LibraryItemPage libraryItemPage = lacertaLibrary.getLibraryPage(100);


            if (libraryItemPage != null) {
                // ドキュメントのメタデータが取得できた場合の処理
                List<ListItem> metas = libraryItemPage.getListItems();

                // ドキュメントをデフォルトフォルダに追加
                // フォルダごとにドキュメントを管理する
                for (ListItem meta : metas) {
                    folderManager.addDocumentToFolder("Default Folder", meta);
                }

                // 特定のフォルダのドキュメントを取得
                List<ListItem> folderDocuments = folderManager.getDocumentInFolder("Default Folder");

                // トーストメッセージでドキュメントの数を表示
                Toast.makeText(getActivity(), "ドキュメント数: " + Integer.toString(metas.size()), Toast.LENGTH_LONG).show();

                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                documentRecyclerView.setLayoutManager(layoutManager);

                // LibraryItemPageを使用してadapterを設定
                DocumentAdapter adapter = new DocumentAdapter((ArrayList<ListItem>) metas);
                documentRecyclerView.setAdapter(adapter);
                //RecyclerView の再描画
                documentRecyclerView.invalidate();
            } else {
                // ドキュメントのメタデータが null の場合の処理
                Toast.makeText(getContext(), "ドキュメントメタデータが取得できませんでした", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            // 例外処理
            e.printStackTrace();
            Toast.makeText(getContext(), "エラーが発生しました: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
            return view;
        }
}
