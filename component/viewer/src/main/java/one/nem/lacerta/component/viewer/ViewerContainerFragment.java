package one.nem.lacerta.component.viewer;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import one.nem.lacerta.component.common.picker.LacertaFilePickerDialog;
import one.nem.lacerta.data.Document;
import one.nem.lacerta.data.LacertaLibrary;
import one.nem.lacerta.model.document.page.Page;
import one.nem.lacerta.model.pref.ToxiDocumentModel;
import one.nem.lacerta.utils.LacertaLogger;

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

    // Variables
    private String documentId;
    private String documentName;
    private boolean hasCombined = false;

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
        return fragment;
    }

    public static ViewerContainerFragment newInstance(String documentId, String documentName) {
        ViewerContainerFragment fragment = new ViewerContainerFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        args.putString("documentId", documentId);
        args.putString("documentName", documentName);
        args.putBoolean("hasCombined", false);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            documentId = getArguments().getString("documentId");
            documentName = getArguments().getString("documentName");
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
        ViewerViewPagerAdapter viewerViewPagerAdapter = new ViewerViewPagerAdapter(requireActivity());
        viewPager.setAdapter(viewerViewPagerAdapter);

        // Init toolbar
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        initToolbar(toolbar, true, documentName);

        if (this.hasCombined) {
            lacertaLibrary.getCombinedDocumentToxiList(documentId).thenAccept(combinedDocumentToxiList -> {
                for (ToxiDocumentModel toxiDocumentModel : combinedDocumentToxiList) {
                    viewerViewPagerAdapter
                            .addFragment(ViewerBodyFragment.newInstance(toxiDocumentModel.getChildDocumentId(), toxiDocumentModel.getTitleCache()),
                                    toxiDocumentModel.getTitleCache());
                }
            });
        } else {
            TabLayout tabLayout = view.findViewById(R.id.tab_layout);
            tabLayout.setVisibility(View.GONE);
            viewerViewPagerAdapter.addFragment(ViewerBodyFragment.newInstance(documentId, documentName), documentName);
        }
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
                    Toast.makeText(getContext(), "Work in progress", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (item.getItemId() == R.id.action_rename) {
                    renameDocument();
                    return true;
                } else if (item.getItemId() == R.id.action_delete) {
                    Toast.makeText(getContext(), "Work in progress", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (item.getItemId() == R.id.action_move) {
                    Toast.makeText(getContext(), "Work in progress", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (item.getItemId() == R.id.action_combine) {
                    combineDocument();
                    return true;
                } else {
                    return false;
                }
            });
        });
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