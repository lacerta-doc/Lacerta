package one.nem.lacerta.feature.library;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import one.nem.lacerta.component.viewer.ViewerMainActivity;
import one.nem.lacerta.data.LacertaLibrary;
import one.nem.lacerta.model.LibraryItemPage;
import one.nem.lacerta.model.ListItemType;
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
    Toolbar toolbar;


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
        appBarLayout.addOnOffsetChangedListener((appBarLayout1, verticalOffset) -> {
            if (Math.abs(verticalOffset) == appBarLayout1.getTotalScrollRange()) {
                // Collapsed
                getActivity().getWindow().setStatusBarColor(ContextCompat.getColor(getContext(), one.nem.lacerta.shared.ui.R.color.colorSecondaryContainer));
            } else if (verticalOffset == 0) {
                // Expanded
                getActivity().getWindow().setStatusBarColor(ContextCompat.getColor(getContext(), one.nem.lacerta.shared.ui.R.color.colorSurface));
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
        this.toolbar = view.findViewById(R.id.library_toolbar);

        // Toolbar Setup
        toolbarSetup(this.toolbar, this.folderId != null, this.title != null ? this.title : "ライブラリ");
        if(this.folderId == null) {
            updateToolbarSubtitle(this.toolbar, null); //負荷軽減のため+邪魔なので（folderIdがnullの場合は、ルートフォルダを表示しているので）
        } else {
            lacertaLibrary.getPublicPath(this.folderId, ListItemType.ITEM_TYPE_FOLDER).thenAccept(publicPath -> {
                updateToolbarSubtitle(this.toolbar, "/" + publicPath.parent().getStringPath());
            });
        }

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
                Intent intent = new Intent(getContext(), ViewerMainActivity.class);
                logger.debug("LibraryTopFragment", "Document selected! documentId: " + documentId + ", documentName: " + documentName);
                intent.putExtra("documentId", documentId);
                intent.putExtra("documentName", documentName);
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(listItemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Get library page and update RecyclerView items

        updateItem(this.folderId);
    }

    /**
     * Currentにフォルダを作成する
     */
    private void createFolder(String pageId) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(requireContext());
        builder.setTitle("フォルダの作成");
        builder.setMessage("フォルダ名を入力してください");

        View view = LayoutInflater.from(requireContext()).inflate(one.nem.lacerta.shared.ui.R.layout.lacerta_dialog_edit_text_layout, null);
        final com.google.android.material.textfield.TextInputEditText input = view.findViewById(one.nem.lacerta.shared.ui.R.id.custom_edit_text);
        final com.google.android.material.textfield.TextInputLayout inputLayout = view.findViewById(one.nem.lacerta.shared.ui.R.id.custom_text_input_layout);
        inputLayout.setHint("フォルダ名");

        builder.setView(view);

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
     * RecyclerViewのアイテムとUIを更新する
     */
    private void updateItem(String pageId) {
        long startTime = System.currentTimeMillis();
        lacertaLibrary.getLibraryPage(pageId, 10).thenAccept(libraryItemPage -> {
            long endTime = System.currentTimeMillis();
            this.libraryItemPage = libraryItemPage;
            logger.debug("LibraryTopFragment", "Item selected! libraryItemPage.getListItems().size(): " + libraryItemPage.getListItems().size());
            if (endTime - startTime > 500) { // 500ms以上かかった場合は表示アニメーションをする
                getActivity().runOnUiThread(() -> {
                    listItemAdapter.setLibraryItemPage(libraryItemPage);
                    listItemAdapter.notifyItemRangeInserted(0, this.libraryItemPage.getListItems().size());
                });
            } else {
                listItemAdapter.setLibraryItemPage(libraryItemPage);
                getActivity().runOnUiThread(() -> {
                    listItemAdapter.notifyDataSetChanged();
                });
            }
        });
    }

    /**
     * Toolbarのサブタイトルを更新
     * @param subtitle サブタイトル
     */
    private void updateToolbarSubtitle(Toolbar toolbar, String subtitle) {
        getActivity().runOnUiThread(() -> {
            toolbar.setSubtitle(subtitle);
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
