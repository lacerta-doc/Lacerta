package one.nem.lacerta.component.viewer;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import one.nem.lacerta.component.common.LacertaApplyTagDialog;
import one.nem.lacerta.component.common.LacertaSelectRevDialog;
import one.nem.lacerta.component.common.LacertaSelectRevDialogListener;
import one.nem.lacerta.component.common.picker.LacertaDirPickerDialog;
import one.nem.lacerta.component.common.picker.LacertaFilePickerDialog;
import one.nem.lacerta.data.Document;
import one.nem.lacerta.data.LacertaLibrary;
import one.nem.lacerta.model.ListItemType;
import one.nem.lacerta.model.document.page.Page;
import one.nem.lacerta.model.document.tag.DocumentTag;
import one.nem.lacerta.model.pref.ToxiDocumentModel;
import one.nem.lacerta.utils.LacertaLogger;
import one.nem.lacerta.vcs.LacertaVcs;
import one.nem.lacerta.vcs.factory.LacertaVcsFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewerContainerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
public class ViewerContainerFragment extends Fragment {

    // Inject

    @Inject
    LacertaLogger logger;

    @Inject
    LacertaLibrary lacertaLibrary;

    @Inject
    Document document;

    @Inject
    LacertaVcsFactory lacertaVcsFactory;

    // Variables
    private String documentId;
    private String documentName;
    private boolean hasCombined = false;
    private String revId;
    private ViewerViewPagerAdapter viewerViewPagerAdapter;

    public ViewerContainerFragment() {
        // Required empty public constructor
    }

    public static ViewerContainerFragment newInstance(String documentId, String documentName, boolean hasCombined) {
        ViewerContainerFragment fragment = new ViewerContainerFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        args.putString("documentId", documentId);
        args.putString("documentName", documentName);
        args.putBoolean("hasCombined", hasCombined);
        args.putString("revId", null);
        return fragment;
    }

    public static ViewerContainerFragment newInstance(String documentId, String documentName) {
        ViewerContainerFragment fragment = new ViewerContainerFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        args.putString("documentId", documentId);
        args.putString("documentName", documentName);
        args.putBoolean("hasCombined", false);
        args.putString("revId", null);
        return fragment;
    }

    public static ViewerContainerFragment newInstance(String documentId, String documentName, String revId) {
        ViewerContainerFragment fragment = new ViewerContainerFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        args.putString("documentId", documentId);
        args.putString("documentName", documentName);
        args.putBoolean("hasCombined", false);
        args.putString("revId", revId);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            documentId = getArguments().getString("documentId");
            documentName = getArguments().getString("documentName");
            hasCombined = getArguments().getBoolean("hasCombined");
            revId = getArguments().getString("revId");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_viewer_container, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Init view pager
        ViewPager2 viewPager = view.findViewById(R.id.view_pager);

        // Init view pager adapter
        this.viewerViewPagerAdapter = new ViewerViewPagerAdapter(requireActivity());
        viewPager.setAdapter(viewerViewPagerAdapter);

        // Init tab layout
        TabLayout tabLayout = view.findViewById(R.id.tab_layout);

        // Init toolbar
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        initToolbar(toolbar, true, documentName);

        if (this.revId != null) { // Revが指定されている場合
            LacertaVcs lacertaVcs = lacertaVcsFactory.create(documentId);
            lacertaVcs.getDocumentPagePathListRev(this.revId).thenApply(pagePathList -> {
                logger.debug("ViewerContainerFragment", "pagePathList: " + pagePathList.size());
                document.getDocumentPageListByFileNameList(this.documentId, pagePathList).thenApply(pageList -> {
                    logger.debug("ViewerContainerFragment", "pageList: " + pageList.size());
                    // 暫定: 履歴を遡って表示している場合は結合を無視する(処理自体は単純だけどUI側の対応をする時間がないので)
                    tabLayout.setVisibility(View.GONE);
                    viewerViewPagerAdapter.setFragmentTargetIdList(new ArrayList<String>(){{add(documentId);}}); // TODO-rca: 読みにくいので直接追加できるようにする
                    viewerViewPagerAdapter.setFragmentTitleList(new ArrayList<String>(){{add(documentName);}}); // TODO-rca: 読みにくいので直接追加できるようにする

                    viewerViewPagerAdapter.setFragmentRevisionList(new ArrayList<String>(){{add(revId);}}); // TODO-rca: 読みにくいので直接追加できるようにする
                    viewerViewPagerAdapter.notifyItemRangeChanged(0, pageList.size());
                    toolbar.setSubtitle("リビジョン: " + revId);
                    return null;
                });
                return null;
            });
        }

        // Get document page
        if (this.hasCombined) { // 結合親の場合
            // バージョンを遡る操作を非表示
            toolbar.getMenu().findItem(R.id.action_open_vcs_rev_list).setVisible(false);
            logger.debug("ViewerContainerFragment", "hasCombined: " + hasCombined);
            lacertaLibrary.getCombinedDocumentToxiList(documentId).thenAccept(combinedDocumentToxiList -> {
                logger.debug("ViewerContainerFragment", "combinedDocumentToxiList: " + combinedDocumentToxiList.size());

                viewerViewPagerAdapter.setFragmentTargetIdList(
                        combinedDocumentToxiList.stream().map(ToxiDocumentModel::getChildDocumentId).collect(Collectors.toCollection(ArrayList::new)));
                viewerViewPagerAdapter.setFragmentTitleList(
                        combinedDocumentToxiList.stream().map(ToxiDocumentModel::getTitleCache).collect(Collectors.toCollection(ArrayList::new)));

                viewerViewPagerAdapter.notifyItemRangeChanged(0, combinedDocumentToxiList.size());
                toolbar.setSubtitle("結合ドキュメント");
            });
        } else { // それ以外の場合
            logger.debug("ViewerContainerFragment", "hasCombined: " + hasCombined);
            tabLayout.setVisibility(View.GONE);
            viewerViewPagerAdapter.setFragmentTargetIdList(new ArrayList<String>(){{add(documentId);}}); // TODO-rca: 読みにくいので直接追加できるようにする
            viewerViewPagerAdapter.setFragmentTitleList(new ArrayList<String>(){{add(documentName);}});
            viewerViewPagerAdapter.notifyItemRangeChanged(0, 1);
        }

        // サブタイトルとしてパスを表示(暫定)
        lacertaLibrary.getPublicPath(documentId, ListItemType.ITEM_TYPE_DOCUMENT).thenAccept(publicPath -> {
            logger.debug("ViewerContainerFragment", "publicPath: " + publicPath);
            toolbar.setSubtitle("/" + publicPath.parent().getStringPath());
        });

        // Attach tab layout to view pager
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            View customView = LayoutInflater.from(getContext()).inflate(R.layout.viewer_custom_tab, null);

            TextView textView = customView.findViewById(R.id.tab_title);
            textView.setText(viewerViewPagerAdapter.getTabTitle(position));

            ImageButton imageButton = customView.findViewById(R.id.tab_modify);
            imageButton.setOnClickListener(v -> {
                PopupMenu popupMenu = new PopupMenu(getContext(), v);
                popupMenu.inflate(R.menu.viewer_tab_menu);
                popupMenu.setOnMenuItemClickListener(item -> {
                    if (item.getItemId() == R.id.action_rename) {
                        renameCombinedDocument(
                                documentId,
                                viewerViewPagerAdapter.getFragmentTargetIdList().get(position),
                                viewerViewPagerAdapter.getFragmentTitle(position),
                                position);
                        return true;
                    } else if (item.getItemId() == R.id.action_delete) {
                        Toast.makeText(getContext(), "Work in progress", Toast.LENGTH_SHORT).show();
                        return true;
                    } else {
                        return false;
                    }
                });
                popupMenu.show();
            });

            tab.setCustomView(customView);
        }).attach();
    }

    private void renameCombinedDocument(String parentId, String childId, String current, int position) { // TODO-rca: 無理やりpositionを渡してるのでなんとかする
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getContext());
        builder.setTitle("アイテム名の変更");
        builder.setMessage("アイテム名を入力してください");

        View view = LayoutInflater.from(getContext()).inflate(one.nem.lacerta.shared.ui.R.layout.lacerta_dialog_edit_text_layout, null);
        TextInputEditText textInputEditText = view.findViewById(one.nem.lacerta.shared.ui.R.id.custom_edit_text);
        TextInputLayout textInputLayout = view.findViewById(one.nem.lacerta.shared.ui.R.id.custom_text_input_layout);
        textInputEditText.setText(current);
        textInputLayout.setHint("アイテム名");
        builder.setView(view);

        builder.setPositiveButton("変更", (dialog, which) -> {
            document.renameDocument(childId, textInputEditText.getText().toString()).thenCombine(
                lacertaLibrary.updateTitleCache(parentId, childId, textInputEditText.getText().toString()), (aVoid, aVoid2) -> {
                    getActivity().runOnUiThread(() -> {
                        Toast.makeText(getContext(), "変更しました", Toast.LENGTH_SHORT).show();
                        updateTabTitle(position, textInputEditText.getText().toString());
                        dialog.dismiss();
                    });
                    return null;
                });
        });
        builder.setNegativeButton("キャンセル", (dialog, which) -> {
            dialog.cancel();
        });

        builder.show();
    }

    private void updateTabTitle(int position, String title) {
        ArrayList<String> fragmentTitleList = viewerViewPagerAdapter.getFragmentTitleList();
        fragmentTitleList.set(position, title);
        viewerViewPagerAdapter.setFragmentTitleList(fragmentTitleList);
        viewerViewPagerAdapter.notifyItemChanged(position);
    }

    /**
     * Toolbarをinitする
     *
     * @param toolbar Toolbar
     * @param showCloseButton Closeボタンを表示するかどうか
     * @param title タイトル
     */
    private void initToolbar(Toolbar toolbar, boolean showCloseButton, String title) {
        getActivity().runOnUiThread(() -> {
            // Close button
            if (showCloseButton) {
                toolbar.setNavigationIcon(one.nem.lacerta.shared.ui.R.drawable.close_24px);
                toolbar.setNavigationOnClickListener(v -> {
                    getActivity().finish();
                });
            } else {
                toolbar.setNavigationIcon(null);
            }

            // Title
            toolbar.setTitle(title);

            // Menu
            toolbar.inflateMenu(R.menu.viewer_menu);
            toolbar.setOnMenuItemClickListener(item -> {
                if (item.getItemId() == R.id.action_open_vcs_rev_list) {
                    showRevList();
                    return true;
                } else if (item.getItemId() == R.id.action_rename) {
                    renameDocument();
                    return true;
                } else if (item.getItemId() == R.id.action_delete) {
                    deleteDocument();
                    return true;
                } else if (item.getItemId() == R.id.action_move) {
                    moveDocument();
                    return true;
                } else if (item.getItemId() == R.id.action_combine) {
                    combineDocument();
                    return true;
                } else if (item.getItemId() == R.id.action_apply_tag) {
                    applyTag();
                    return true;
                } else {
                    return false;
                }
            });
        });
    }

    private void showRevList() {
        LacertaSelectRevDialog lacertaSelectRevDialog = new LacertaSelectRevDialog();
        lacertaSelectRevDialog.setDocumentId(this.documentId).setTitle("リビジョンの選択").setMessage("リビジョンを選択してください。").setNegativeButtonText("キャンセル");
        lacertaSelectRevDialog.setListener(new LacertaSelectRevDialogListener() {
            @Override
            public void onItemSelected(String revId) {
                logger.debug("ViewerContainerFragment", "Dialog Result: revId: " + revId);
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment, ViewerContainerFragment.newInstance(documentId, documentName, revId))
                        .commit();
            }

            @Override
            public void onDialogCanceled() {
            }
        });
        lacertaSelectRevDialog.show(getParentFragmentManager(), "select_rev_dialog");
    }

    private void moveDocument() {
        LacertaDirPickerDialog lacertaDirPickerDialog = new LacertaDirPickerDialog();
        lacertaDirPickerDialog.setListener((name, dirId) -> {
            logger.debug("MoveDocument", "Selected dir: " + name + ", " + dirId);
            document.moveDocument(documentId, dirId).thenAccept(aVoid -> {
                getActivity().runOnUiThread(() -> {
                    // Stop Activity
                    getActivity().finish(); // TODO-rca: ファイル移動後に終了するべきかは検討
                });
            });
        });
        lacertaDirPickerDialog.setTitle("ファイルの移動")
                .setMessage("ファイルを移動するフォルダを選択してください。")
                .setPositiveButtonText("移動")
                .setNegativeButtonText("キャンセル");
        lacertaDirPickerDialog.show(getParentFragmentManager(), "select_dir_dialog");
    }

    private void deleteDocument() {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getContext());
        builder.setTitle("ファイルの削除");
        builder.setMessage("ファイルを削除しますか？");

        builder.setPositiveButton("削除", (dialog, which) -> {
            document.deleteDocument(documentId).thenAccept(aVoid -> {
                getActivity().runOnUiThread(() -> {
                    Toast.makeText(getContext(), "削除しました", Toast.LENGTH_SHORT).show();
                    getActivity().finish(); // TODO-rca: 終了させずにUIを更新したい
                });
            });
        });
        builder.setNegativeButton("キャンセル", (dialog, which) -> {
            dialog.cancel();
        });

        builder.show();
    }

    private void applyTag() {
        LacertaApplyTagDialog lacertaApplyTagDialog = new LacertaApplyTagDialog();
        lacertaApplyTagDialog
                .setTitle("タグの適用")
                .setMessage("タグを適用するファイルを選択してください")
                .setNegativeButtonText("キャンセル")
                .setDocumentId(documentId)
                .setListener(new LacertaApplyTagDialog.LacertaApplyTagDialogListener() {
                    @Override
                    public void onDialogPositiveClick(ArrayList<DocumentTag> appliedTags) {
                        logger.debug("ViewerContainerFragment", "Dialog Result: appliedTags: " + appliedTags.size());
                        lacertaLibrary.applyTagListToDocument(documentId, appliedTags).thenAccept(aVoid -> {
                            getActivity().runOnUiThread(() -> {
                                Toast.makeText(getContext(), "タグを適用しました", Toast.LENGTH_SHORT).show();
                                lacertaApplyTagDialog.dismiss();
                            });
                        });
                    }

                    @Override
                    public void onDialogNegativeClick() {
                        lacertaApplyTagDialog.dismiss();
                    }
                })
                .show(getChildFragmentManager(), "LacertaApplyTagDialog");
    }

    private void combineDocument() {
        LacertaFilePickerDialog lacertaFilePickerDialog = new LacertaFilePickerDialog();
        lacertaFilePickerDialog.setListener((fileName, selectedId) -> {
            lacertaLibrary.combineDocument(documentId, selectedId).thenAccept(aVoid -> {
                getActivity().runOnUiThread(() -> {
                    Toast.makeText(getContext(), "結合しました", Toast.LENGTH_SHORT).show();
                    getActivity().finish(); // TODO-rca: 終了させずにUIを更新したい
                });
            });
        });
        lacertaFilePickerDialog
                .setTitle("ファイルの結合")
                .setMessage("結合するファイルを選択してください")
                .setNegativeButtonText("キャンセル")
                .show(getChildFragmentManager(), "LacertaFilePickerDialog");
    }

    /**
     * ドキュメント名を変更する
     */
    private void renameDocument() {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getContext());
        builder.setTitle("ファイル名の変更");
        builder.setMessage("ファイル名を入力してください");

        View view = LayoutInflater.from(getContext()).inflate(one.nem.lacerta.shared.ui.R.layout.lacerta_dialog_edit_text_layout, null);
        TextInputEditText textInputEditText = view.findViewById(one.nem.lacerta.shared.ui.R.id.custom_edit_text);
        TextInputLayout textInputLayout = view.findViewById(one.nem.lacerta.shared.ui.R.id.custom_text_input_layout);
        textInputEditText.setText(documentName);
        textInputLayout.setHint("ファイル名");
        builder.setView(view);

        builder.setPositiveButton("変更", (dialog, which) -> {
            document.renameDocument(documentId, textInputEditText.getText().toString()).thenAccept(aVoid -> {
                getActivity().runOnUiThread(() -> {
                    this.documentName = textInputEditText.getText().toString();
                    // TODO-rca: Toolbarのタイトルも変更する
                });
            });
        });
        builder.setNegativeButton("キャンセル", (dialog, which) -> {
            dialog.cancel();
        });

        builder.show();
    }
}