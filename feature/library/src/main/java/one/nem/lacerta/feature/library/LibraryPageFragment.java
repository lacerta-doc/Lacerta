package one.nem.lacerta.feature.library;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
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
import java.util.Objects;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import one.nem.lacerta.data.Document;
import one.nem.lacerta.data.LacertaLibrary;
import one.nem.lacerta.model.FragmentNavigation;
import one.nem.lacerta.model.LibraryItemPage;
import one.nem.lacerta.model.PublicPath;
import one.nem.lacerta.utils.FeatureSwitch;
import one.nem.lacerta.utils.LacertaLogger;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LibraryPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
public class LibraryPageFragment extends Fragment {

    // Variables
    // このインスタンスで表示されているページ
    LibraryItemPage libraryItemPage;

    // Arguments
    String folderId;
    String title;
    String parentId;


    @Inject
    LacertaLibrary lacertaLibrary;

    @Inject
    LacertaLogger logger;

    ListItemAdapter listItemAdapter;

    public LibraryPageFragment() {
        // Required empty public constructor
    }

    public static LibraryPageFragment newInstance(String folderId, String title, String parentId) {
        LibraryPageFragment fragment = new LibraryPageFragment();
        Bundle args = new Bundle();
        args.putString("folderId", folderId);
        args.putString("title", title);
        args.putString("publicPath", parentId);
        fragment.setArguments(args);
        return fragment;
    }

    public static LibraryPageFragment newInstance(String folderId) { // Back action
        LibraryPageFragment fragment = new LibraryPageFragment();
        Bundle args = new Bundle();
        args.putString("folderId", folderId);
        args.putString("title", null);
        args.putString("publicPath", null);
        fragment.setArguments(args);
        return fragment;
    }

    public static LibraryPageFragment newInstance() {
        LibraryPageFragment fragment = new LibraryPageFragment();
        Bundle args = new Bundle();
        args.putString("folderId", null);
        args.putString("title", null);
        args.putString("publicPath", null);
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

        // Set status bar color
        AppBarLayout appBarLayout = view.findViewById(R.id.app_bar_layout);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (Math.abs(verticalOffset) == appBarLayout.getTotalScrollRange()) {
                    // Collapsed
                    getActivity().getWindow().setStatusBarColor(ContextCompat.getColor(getContext(), one.nem.lacerta.shared.ui.R.color.colorSecondaryContainer));
                } else if (verticalOffset == 0) {
                    // Expanded
                    getActivity().getWindow().setStatusBarColor(ContextCompat.getColor(getContext(), one.nem.lacerta.shared.ui.R.color.colorSurface));
                } else {
                    // Somewhere in between
                    // Here you can add a color transition if you want
                }
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            this.folderId = getArguments().getString("folderId"); // Required
            if (getArguments().getString("title") == null && getArguments().getString("publicPath") == null) {
                this.libraryItemPage = new LibraryItemPage();
            } else {
                this.title = getArguments().getString("title");
                this.parentId = getArguments().getString("publicPath");
            }
        } else {
            logger.debug("LibraryTopFragment", "getArguments() is null(maybe root)");
            this.libraryItemPage = new LibraryItemPage();
        }

        // Toolbar Setup
        toolbarSetup(view.findViewById(R.id.library_toolbar), this.folderId != null, this.title != null ? this.title : "ライブラリ");

        // RecyclerView Setup

        RecyclerView recyclerView = view.findViewById(R.id.library_item_recycler_view);
        this.listItemAdapter = new ListItemAdapter(new DocumentSelectListener() {
            @Override
            public void onFolderSelected(String folderId, String folderName) {
                logger.debug("LibraryTopFragment", "Folder selected! folderId: " + folderId + ", folderName: " + folderName);
//                // 画面遷移
//                FragmentNavigation fragmentNavigation = (FragmentNavigation) getActivity();
//                // folderId: 推移先で表示するフォルダのID, folderName: 推移先で表示するフォルダの名前, parentId: このフラグメントで表示しているフォルダのID(推移先の親)
//                fragmentNavigation.navigateToFragment(LibraryPageFragment.newInstance(folderId, folderName, libraryItemPage != null ? libraryItemPage.getParentId() : null), false);
                Bundle bundle = new Bundle();
                bundle.putString("folderId", folderId);
                bundle.putString("title", folderName);
                bundle.putString("publicPath", libraryItemPage != null ? libraryItemPage.getParentId() : null);
                try {
                    Navigation.findNavController(requireView()).navigate(R.id.action_feature_library_top_fragment_self, bundle);
                } catch (IllegalStateException e) {
                    logger.error("LibraryTopFragment", "IllegalStateException: " + e.getMessage());
                }
            }

            @Override
            public void onDocumentSelected(String documentId, String documentName) {
                Toast.makeText(getContext(), "Document selected! documentId: " + documentId + ", documentName: " + documentName, Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView.setAdapter(listItemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Get library page and update RecyclerView items
        lacertaLibrary.getLibraryPage(this.folderId, 10).thenAccept(libraryItemPage -> {
            this.libraryItemPage = libraryItemPage;

            if (this.parentId == null) {
                this.parentId = libraryItemPage.getParentId();
            }
            if (this.title == null) {
                this.title = libraryItemPage.getPageTitle();
                // Toolbar init again
                toolbarSetup(view.findViewById(R.id.library_toolbar), this.folderId != null, this.title != null ? this.title : "ライブラリ");
            }

            logger.debug("LibraryTopFragment", "Item selected! Total item page: " + this.libraryItemPage.getListItems().size());
            if (!FeatureSwitch.RecyclerView.useSimpleNotifyMethod) {
                getActivity().runOnUiThread(() -> { // TODO-rca: 実行条件を考える？
                    listItemAdapter.notifyItemRangeRemoved(0, this.libraryItemPage.getListItems().size() - 1);
                });
            }
            listItemAdapter.setLibraryItemPage(this.libraryItemPage);
            getActivity().runOnUiThread(() -> {
                if (FeatureSwitch.RecyclerView.useSimpleNotifyMethod) {
                    listItemAdapter.notifyDataSetChanged();
                } else {
                    listItemAdapter.notifyItemRangeInserted(0, this.libraryItemPage.getListItems().size() - 1);
                }
            });
        });
    }

    /**
     * Currentにフォルダを作成する
     */
    private void createFolder(String pageId) {
        // TODO-rca: デザインをMaterial Design 3に合わせたカスタムダイアログにする
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("フォルダの作成");
        builder.setMessage("フォルダ名を入力してください");
        final android.widget.EditText input = new android.widget.EditText(getContext());
        input.setText("フォルダ名");
        builder.setView(input);
        builder.setPositiveButton("作成", (dialog, which) -> {
            lacertaLibrary.createFolder(pageId, input.getText().toString()).thenAccept(folderId -> {
                // Refresh
                updateItem(pageId);
            });
        });
        builder.setNegativeButton("キャンセル", (dialog, which) -> {
            dialog.cancel();
        });
        builder.show();
    }

    /**
     * RecyclerViewのアイテムを更新する
     */
    private void updateItem(String pageId) {
        lacertaLibrary.getLibraryPage(pageId, 10).thenAccept(libraryItemPage -> {
            this.libraryItemPage = libraryItemPage;
            logger.debug("LibraryTopFragment", "Item selected! libraryItemPage.getListItems().size(): " + libraryItemPage.getListItems().size());
            getActivity().runOnUiThread(() -> {
                listItemAdapter.notifyItemRangeRemoved(0, libraryItemPage.getListItems().size() - 1);
            });
            listItemAdapter.setLibraryItemPage(libraryItemPage);
            getActivity().runOnUiThread(() -> {
                listItemAdapter.notifyItemRangeInserted(0, libraryItemPage.getListItems().size() - 1);
            });
        });
    }

    /**
     * ToolbarをInitする
     *
     * @param toolbar Toolbar
     * @param showBackButton 戻るボタンを表示するか
     * @param title タイトル
     */
    private void toolbarSetup(Toolbar toolbar, boolean showBackButton, String title) {
        getActivity().runOnUiThread(() -> {
            if (showBackButton) {
                toolbar.setNavigationIcon(one.nem.lacerta.shared.ui.R.drawable.arrow_back_24px);
                toolbar.setNavigationOnClickListener(v -> {
                    //this.libraryItemPage = lacertaLibrary.getLibraryPage(this.libraryItemPage.getParentId(), 10).join();
                    // Back
                    Navigation.findNavController(requireView()).popBackStack();
                });
            } else {
                toolbar.setNavigationIcon(null);
            }
            toolbar.setTitle(title);
            toolbar.getMenu().clear();
            toolbar.inflateMenu(R.menu.dir_menu);
            toolbar.setOnMenuItemClickListener(item -> {
                if (item.getItemId() == R.id.menu_item_create_new_folder) {
                    createFolder(this.folderId);
                    return true;
                } else {
                    return false;
                }
            });
        });
    }
}
