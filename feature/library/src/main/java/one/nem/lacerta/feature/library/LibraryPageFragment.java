package one.nem.lacerta.feature.library;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import one.nem.lacerta.data.Document;
import one.nem.lacerta.data.LacertaLibrary;
import one.nem.lacerta.model.FragmentNavigation;
import one.nem.lacerta.utils.LacertaLogger;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LibraryPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
public class LibraryPageFragment extends Fragment {

    // Param
    private String folderId;

    @Inject
    LacertaLibrary lacertaLibrary;

    @Inject
    LacertaLogger logger;

    ListItemAdapter listItemAdapter;

    int currentTotalItemCount = 0;

    public LibraryPageFragment() {
        // Required empty public constructor
    }
    public static LibraryPageFragment newInstance(String folderId) {
        LibraryPageFragment fragment = new LibraryPageFragment();
        Bundle args = new Bundle();
        args.putString("folderId", folderId);
        fragment.setArguments(args);
        return fragment;
    }

    public static LibraryPageFragment newInstance() {
        LibraryPageFragment fragment = new LibraryPageFragment();
        Bundle args = new Bundle();
        args.putString("folderId", null);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_library_top, container, false);

        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.library_item_recycler_view);

        this.listItemAdapter = new ListItemAdapter(new DocumentSelectListener() {
            @Override
            public void onFolderSelected(String folderId, String folderName) {
                logger.debug("LibraryTopFragment", "Folder selected! folderId: " + folderId + ", folderName: " + folderName);
                // 画面遷移
                FragmentNavigation fragmentNavigation = (FragmentNavigation) getActivity();
                assert fragmentNavigation != null;
                fragmentNavigation.navigateToFragment(LibraryPageFragment.newInstance(folderId));
            }

            @Override
            public void onDocumentSelected(String documentId, String documentName) {
                Toast.makeText(getContext(), "Document selected! documentId: " + documentId + ", documentName: " + documentName, Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(listItemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        if (getArguments() != null) {
            this.folderId = getArguments().getString("folderId");
        }

        if (this.folderId == null) { // Root
            lacertaLibrary.getLibraryPage(10).thenAccept(libraryItemPage -> {
                logger.debug("LibraryTopFragment", "Item selected! libraryItemPage.getListItems().size(): " + libraryItemPage.getListItems().size());
                listItemAdapter.setLibraryItemPage(libraryItemPage);
                getActivity().runOnUiThread(() -> {
                    // ActionBarのタイトルを変更する
                    getActivity().setTitle("ライブラリ");
                    // ActionBarに戻るボタンを非表示にする
//                    getActivity().getActionBar().setDisplayHomeAsUpEnabled(false);
                    listItemAdapter.notifyItemRangeInserted(0, libraryItemPage.getListItems().size() - 1);
                });
                this.currentTotalItemCount = libraryItemPage.getListItems().size();
            });
        } else { // Root以外
            lacertaLibrary.getLibraryPage(this.folderId, 10).thenAccept(libraryItemPage -> {
                logger.debug("LibraryTopFragment", "Item selected! libraryItemPage.getListItems().size(): " + libraryItemPage.getListItems().size());
                listItemAdapter.setLibraryItemPage(libraryItemPage);
                getActivity().runOnUiThread(() -> {
                    // ActionBarのタイトルを変更する
                    getActivity().setTitle(libraryItemPage.getPageTitle());
                    // ActionBarに戻るボタンを表示する
//                    getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
                    listItemAdapter.notifyItemRangeInserted(0, libraryItemPage.getListItems().size() - 1);
                });
                this.currentTotalItemCount = libraryItemPage.getListItems().size();
            });
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.dir_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    // Selected
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_item_create_new_folder) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("フォルダの作成");
            builder.setMessage("フォルダ名を入力してください");
            final android.widget.EditText input = new android.widget.EditText(getContext());
            input.setText("フォルダ名");
            builder.setView(input);
            builder.setPositiveButton("作成", (dialog, which) -> {
                lacertaLibrary.createFolder(null, input.getText().toString()).thenAccept(folderId -> {
                    logger.debug("LibraryTopFragment", "folderId: " + folderId);
                });
                // Refresh
                updateItem();
            });
            builder.setNegativeButton("キャンセル", (dialog, which) -> {
                dialog.cancel();
            });
            builder.show();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void updateItem() {
        lacertaLibrary.getLibraryPage(10).thenAccept(libraryItemPage -> {
            logger.debug("LibraryTopFragment", "Item selected! libraryItemPage.getListItems().size(): " + libraryItemPage.getListItems().size());
            getActivity().runOnUiThread(() -> {
                listItemAdapter.notifyItemRangeRemoved(0, this.currentTotalItemCount - 1);
            });
            listItemAdapter.setLibraryItemPage(libraryItemPage);
            getActivity().runOnUiThread(() -> {
                listItemAdapter.notifyItemRangeInserted(0, libraryItemPage.getListItems().size() - 1);
            });
            this.currentTotalItemCount = libraryItemPage.getListItems().size();
        });
    }
}
