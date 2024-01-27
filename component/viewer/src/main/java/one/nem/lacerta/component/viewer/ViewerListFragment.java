package one.nem.lacerta.component.viewer;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import one.nem.lacerta.component.common.LacertaSelectDirDialog;
import one.nem.lacerta.component.common.LacertaSelectDirDialogListener;
import one.nem.lacerta.component.common.LacertaSelectRevDialog;
import one.nem.lacerta.component.common.LacertaSelectRevDialogListener;
import one.nem.lacerta.data.Document;
import one.nem.lacerta.data.LacertaLibrary;
import one.nem.lacerta.model.ListItemType;
import one.nem.lacerta.model.document.page.Page;
import one.nem.lacerta.utils.FeatureSwitch;
import one.nem.lacerta.utils.LacertaLogger;
import one.nem.lacerta.vcs.LacertaVcs;
import one.nem.lacerta.vcs.factory.LacertaVcsFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewerListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
public class ViewerListFragment extends Fragment {

    @Inject
    Document document;

    @Inject
    LacertaLibrary lacertaLibrary;

    @Inject
    LacertaLogger logger;

    @Inject
    LacertaVcsFactory lacertaVcsFactory;

    private static final String TAG = "ComponentViewerTopFragment";

    private String documentId;
    private String documentName;
    private String revisionId;

    public ViewerListFragment() {
        // Required empty public constructor
    }

    public static ViewerListFragment newInstance(String documentId, String documentName) {
        ViewerListFragment fragment = new ViewerListFragment();
        Bundle args = new Bundle();
        args.putString("documentId", documentId);
        args.putString("documentName", documentName);
        fragment.setArguments(args);
        return fragment;
    }

    public static ViewerListFragment newInstance(String documentId, String documentName, String revisionId) {
        ViewerListFragment fragment = new ViewerListFragment();
        Bundle args = new Bundle();
        args.putString("documentId", documentId);
        args.putString("documentName", documentName);
        args.putString("revisionId", revisionId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            documentId = getArguments().getString("documentId");
            documentName = getArguments().getString("documentName");
            revisionId = getArguments().getString("revisionId");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_component_viewer_top, container, false);

        // Toolbar
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbarSetup(toolbar, true, this.documentName == null ? "Document" : this.documentName, null);

        RecyclerView recyclerView = view.findViewById(R.id.body_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ViewerBodyAdapter viewerBodyAdapter = new ViewerBodyAdapter(fileName -> {
            Toast.makeText(getContext(), fileName, Toast.LENGTH_SHORT).show();
        });
        recyclerView.setAdapter(viewerBodyAdapter);

        if (revisionId == null) {
            logger.debug(TAG, "revisionId is empty, loading latest revision");
            if (FeatureSwitch.Viewer.showProgressBarWhenLoading) view.findViewById(R.id.loading_progress_bar).setVisibility(View.VISIBLE);
            document.getDocument(documentId).thenAccept(documentDetail -> {
                ArrayList<Page> pages = documentDetail.getPages();
                logger.debug(TAG, "pages.size(): " + pages.size());
                viewerBodyAdapter.setPages(pages);
                getActivity().runOnUiThread(() -> {
                    viewerBodyAdapter.notifyItemRangeChanged(0, pages.size());
                    if (FeatureSwitch.Viewer.showProgressBarWhenLoading) view.findViewById(R.id.loading_progress_bar).setVisibility(View.GONE);
                });
            }).thenCompose(aVoid -> lacertaLibrary.getPublicPath(documentId, ListItemType.ITEM_TYPE_DOCUMENT)).thenAccept(publicPath -> {
                updateToolbarSubtitle(toolbar, "/" + publicPath.parent().getStringPath());
            });
        } else {
            logger.debug(TAG, "revisionId: " + revisionId);
            if (FeatureSwitch.Viewer.showProgressBarWhenLoading) view.findViewById(R.id.loading_progress_bar).setVisibility(View.VISIBLE);
            LacertaVcs lacertaVcs = lacertaVcsFactory.create(documentId);
            lacertaVcs.getDocumentPagePathListRev(revisionId).thenAccept(documentPathList -> {
                logger.debug(TAG, "documentPathList.size(): " + documentPathList.size());
                document.getDocumentPageListByFileNameList(documentId, documentPathList).thenAccept(pages -> {
                    logger.debug(TAG, "pages.size(): " + pages.size());
                    viewerBodyAdapter.setPages(pages);
                    getActivity().runOnUiThread(() -> {
                        viewerBodyAdapter.notifyItemRangeChanged(0, pages.size());
                        if (FeatureSwitch.Viewer.showProgressBarWhenLoading) view.findViewById(R.id.loading_progress_bar).setVisibility(View.GONE);
                    });
                });
            }).thenCompose(aVoid -> lacertaLibrary.getPublicPath(documentId, ListItemType.ITEM_TYPE_DOCUMENT)).thenAccept(publicPath -> {
                updateToolbarSubtitle(toolbar, "/" + publicPath.parent().getStringPath()); // TODO-rca: リビジョンの場合はリビジョン名を表示する?
            });
        }

        return view;
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
    private void toolbarSetup(Toolbar toolbar, boolean showBackButton, String title, String Subtitle) {
        getActivity().runOnUiThread(() -> {
            if (showBackButton) {
                toolbar.setNavigationIcon(one.nem.lacerta.shared.ui.R.drawable.close_24px);
                toolbar.setNavigationOnClickListener(v -> {
                    // Stop Activity
                    getActivity().finish();
                });
            } else {
                toolbar.setNavigationIcon(null);
            }
            toolbar.setTitle(title);
            if (Subtitle != null) toolbar.setSubtitle(Subtitle);
            toolbar.inflateMenu(R.menu.viewer_menu);
            toolbar.setOnMenuItemClickListener(item -> {
                if (item.getItemId() == R.id.action_open_vcs_rev_list) {
                    LacertaSelectRevDialog lacertaSelectRevDialog = new LacertaSelectRevDialog();
                    lacertaSelectRevDialog.setDocumentId(this.documentId).setTitle("リビジョンの選択").setMessage("リビジョンを選択してください。").setNegativeButtonText("キャンセル");
                    lacertaSelectRevDialog.setListener(new LacertaSelectRevDialogListener() {
                        @Override
                        public void onItemSelected(String revId) {
                            getParentFragmentManager().beginTransaction()
                                    .replace(R.id.nav_host_fragment, ViewerListFragment.newInstance(documentId, documentName, revisionId))
                                    .commit();
                        }

                        @Override
                        public void onDialogCanceled() {
                            logger.debug(TAG, "Canceled");
                        }
                    });
                    lacertaSelectRevDialog.show(getParentFragmentManager(), "select_rev_dialog");
                    return true;
                } else if (item.getItemId() == R.id.action_rename) {

                    MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(requireContext());
                    builder.setTitle("ファイル名の変更");

                    View view = LayoutInflater.from(requireContext()).inflate(one.nem.lacerta.shared.ui.R.layout.lacerta_dialog_edit_text_layout, null);
                    final com.google.android.material.textfield.TextInputEditText input = view.findViewById(one.nem.lacerta.shared.ui.R.id.custom_edit_text);
                    final com.google.android.material.textfield.TextInputLayout inputLayout = view.findViewById(one.nem.lacerta.shared.ui.R.id.custom_text_input_layout);
                    inputLayout.setHint("ファイル名");

                    builder.setView(view);

                    builder.setPositiveButton("変更", (dialog, which) -> {
                        document.renameDocument(documentId, input.getText().toString()).thenAccept(aVoid -> {
                            getActivity().runOnUiThread(() -> {
                                toolbar.setTitle(input.getText().toString());
                                documentName = input.getText().toString();
                            });
                        });
                    });
                    builder.setNegativeButton("キャンセル", (dialog, which) -> {
                        dialog.cancel();
                    });

                    builder.show();
                    return true;
                } else if (item.getItemId() == R.id.action_delete) {
                    // TODO-rca: デザインをMaterial Design 3に合わせたカスタムダイアログにする
                    MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(requireContext());
                    builder.setTitle("ファイルの削除");
                    builder.setMessage("ファイルを削除しますか？");
                    builder.setPositiveButton("削除", (dialog, which) -> {
                        document.deleteDocument(documentId).thenAccept(aVoid -> {
                            getActivity().runOnUiThread(() -> {
                                // Stop Activity
                                getActivity().finish();
                            });
                        });
                    });
                    builder.setNegativeButton("キャンセル", (dialog, which) -> {
                        dialog.cancel();
                    });
                    builder.show();
                    return true;
                } else if (item.getItemId() == R.id.action_move) {
                    LacertaSelectDirDialog lacertaSelectDirDialog = new LacertaSelectDirDialog();
                    lacertaSelectDirDialog.setListener(new LacertaSelectDirDialogListener() {
                        @Override
                        public void onDirSelected(String name, String itemId) {
                            logger.debug(TAG, "Selected dir: " + name + ", " + itemId);
                            document.moveDocument(documentId, itemId).thenAccept(aVoid -> {
                                getActivity().runOnUiThread(() -> {
                                    // Stop Activity
                                    getActivity().finish(); // TODO-rca: ファイル移動後に終了するべきかは検討
                                });
                            });
                        }

                        @Override
                        public void onCanceled() {
                            logger.debug(TAG, "Canceled");
                        }
                    });
                    lacertaSelectDirDialog.setTitle("ファイルの移動")
                                    .setMessage("ファイルを移動するフォルダを選択してください。")
                                    .setPositiveButtonText("移動")
                                    .setNegativeButtonText("キャンセル");
                    lacertaSelectDirDialog.show(getParentFragmentManager(), "select_dir_dialog");
                    return true;
                } else {
                    return false;
                }
            });
        });
    }
}