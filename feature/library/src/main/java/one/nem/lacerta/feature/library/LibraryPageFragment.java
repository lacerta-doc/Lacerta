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
import one.nem.lacerta.model.PublicPath;
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
    private String title;
    private String publicPath;
    private PublicPath currentPublicPath;

    @Inject
    LacertaLibrary lacertaLibrary;

    @Inject
    LacertaLogger logger;

    ListItemAdapter listItemAdapter;

    int currentTotalItemCount = 0;

    public LibraryPageFragment() {
        // Required empty public constructor
    }

    public static LibraryPageFragment newInstance(String folderId, String title, String publicPath) {
        LibraryPageFragment fragment = new LibraryPageFragment();
        Bundle args = new Bundle();
        args.putString("folderId", folderId);
        args.putString("title", title);
        args.putString("publicPath", publicPath);
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
                Toast.makeText(getContext(), "Folder selected! folderId: " + folderId + ", folderName: " + folderName, Toast.LENGTH_SHORT).show();
                FragmentNavigation fragmentNavigation = (FragmentNavigation) getActivity();
                assert fragmentNavigation != null;
                fragmentNavigation.navigateToFragment(LibraryPageFragment.newInstance(folderId, folderName, currentPublicPath.resolve(folderName).getStringPath()));
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
            this.title = getArguments().getString("title");
            this.publicPath = getArguments().getString("publicPath");
            // Log
            logger.debug("LibraryTopFragment", "args"
                    + ", folderId: " + this.folderId
                    + ", title: " + this.title
                    + ", publicPath: " + this.publicPath);
        }

        if (this.folderId == null) { // Root
            toolbarSetup(view.findViewById(R.id.library_toolbar), false, "ライブラリ", "Placeholder");
            lacertaLibrary.getLibraryPage(10).thenAccept(libraryItemPage -> {
                this.currentPublicPath = new PublicPath().resolve("/");
                logger.debug("LibraryInit(ROOT)",currentPublicPath.getStringPath());
                logger.debug("LibraryTopFragment", "Item selected! libraryItemPage.getListItems().size(): " + libraryItemPage.getListItems().size());
                listItemAdapter.setLibraryItemPage(libraryItemPage);
                getActivity().runOnUiThread(() -> {
                    listItemAdapter.notifyItemRangeInserted(0, libraryItemPage.getListItems().size() - 1);
                });
                this.currentTotalItemCount = libraryItemPage.getListItems().size();
            });
        } else { // Root以外
            toolbarSetup(view.findViewById(R.id.library_toolbar), true, this.title, "Placeholder");
            lacertaLibrary.getLibraryPage(this.folderId, 10).thenAccept(libraryItemPage -> {
                this.currentPublicPath = new PublicPath().parse(this.publicPath);
                logger.debug("LibraryInit(NON_ROOT)",currentPublicPath.getStringPath());
                logger.debug("LibraryTopFragment", "Item selected! libraryItemPage.getListItems().size(): " + libraryItemPage.getListItems().size());
                listItemAdapter.setLibraryItemPage(libraryItemPage);
                getActivity().runOnUiThread(() -> {
                    listItemAdapter.notifyItemRangeInserted(0, libraryItemPage.getListItems().size() - 1);
                });
                this.currentTotalItemCount = libraryItemPage.getListItems().size();
            });
        }


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

    }

    private void toolbarSetup(Toolbar toolbar, boolean showBackButton, String title, String subtitle) {
        getActivity().runOnUiThread(() -> {
            if (showBackButton) {
                toolbar.setNavigationIcon(one.nem.lacerta.shared.ui.R.drawable.arrow_back_24px);
                toolbar.setNavigationOnClickListener(v -> {
                    getParentFragmentManager().popBackStack();
                });
            } else {
                toolbar.setNavigationIcon(null);
            }
            toolbar.setTitle(title);
            toolbar.inflateMenu(R.menu.dir_menu);
            toolbar.setOnMenuItemClickListener(item -> {
                if (item.getItemId() == R.id.menu_item_create_new_folder) {
                    createFolder();
                    return true;
                } else {
                    return false;
                }
            });
        });
    }

    private void createFolder() {
        // TODO-rca: デザインをMaterial Design 3に合わせたカスタムダイアログにする
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
