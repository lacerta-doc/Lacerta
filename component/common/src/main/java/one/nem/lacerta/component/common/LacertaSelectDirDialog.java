package one.nem.lacerta.component.common;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import one.nem.lacerta.data.LacertaLibrary;
import one.nem.lacerta.model.ListItemType;
import one.nem.lacerta.utils.LacertaLogger;

@AndroidEntryPoint
public class LacertaSelectDirDialog extends DialogFragment {

    @Inject
    LacertaLibrary lacertaLibrary;

    @Inject
    LacertaLogger logger;

    private LacertaSelectDirDialogListener listener;

    private String title;

    private String message;

    private String positiveButtonText;

    private String negativeButtonText;

    private SelectDirDialogItemAdapter adapter;

    private RecyclerView recyclerView;

    private TextView current_dir_text_view;

    // Setter

    public LacertaSelectDirDialog setListener(LacertaSelectDirDialogListener listener) {
        this.listener = listener;
        return this;
    }

    public LacertaSelectDirDialog setTitle(String title) {
        this.title = title;
        return this;
    }

    public LacertaSelectDirDialog setMessage(String message) {
        this.message = message;
        return this;
    }

    public LacertaSelectDirDialog setPositiveButtonText(String positiveButtonText) {
        this.positiveButtonText = positiveButtonText;
        return this;
    }

    public LacertaSelectDirDialog setNegativeButtonText(String negativeButtonText) {
        this.negativeButtonText = negativeButtonText;
        return this;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        super.onCreateDialog(savedInstanceState);

        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.lacerta_dialog_select_dir, null);

        // 高さを画面の60%にする
        int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.6);
        view.setMinimumHeight(height);

        this.recyclerView = view.findViewById(R.id.select_dir_recycler_view);
        this.current_dir_text_view = view.findViewById(R.id.current_dir_text_view);

        this.adapter = new SelectDirDialogItemAdapter(new LacertaSelectDirDialogInternalEventListener() {
            @Override
            public void onDirSelected(String name, String itemId) {
                showRecyclerViewItem(itemId);
            }

            @Override
            public void onBackSelected(String parentId) {
                showRecyclerViewItem(parentId);
            }
        });

        this.recyclerView.setAdapter(this.adapter);
        builder.setView(view);

        showRecyclerViewItem(null); // get root folder

        builder.setTitle(this.title == null ? "Select a directory" : this.title);
        builder.setMessage(this.message == null ? "Select a directory" : this.message);
        builder.setPositiveButton(this.positiveButtonText == null ? "OK" : this.positiveButtonText, (dialog, which) -> {
            if (listener != null) {
                listener.onDirSelected(
                        adapter.getCurrentPageTitle() == null ? null : adapter.getCurrentPageTitle(),
                        adapter.getCurrentId() == null ? null : adapter.getCurrentId());
            }
        });
        builder.setNegativeButton(this.negativeButtonText == null ? "Cancel" : this.negativeButtonText, (dialog, which) -> {
            if (listener != null) {
                listener.onCanceled();
            }
        });
        return builder.create();
    }

    private void showRecyclerViewItem(String targetDirId) {
        lacertaLibrary.getPublicPath(targetDirId, ListItemType.ITEM_TYPE_FOLDER).thenAccept(publicPath -> {
            getActivity().runOnUiThread(() -> {
                if (publicPath != null) {
                    current_dir_text_view.setText("/" + publicPath.getStringPath()); // TODO-rca: PublicPathの実装を修正する
                } else {
                    current_dir_text_view.setText("/");
                }
            });
        });
        lacertaLibrary.getFolderList(targetDirId).thenAccept(libraryItemPage -> {
            getActivity().runOnUiThread(() -> {
                int currentCount = adapter.getItemCount();
                String currentDirId = adapter.getCurrentId();
                if (currentDirId == null) {
                    // Rootが関わる推移 (Rootからの推移)
                    adapter.setListItems(libraryItemPage);
                    adapter.notifyItemRangeRemoved(0, currentCount);
                    adapter.notifyItemRangeInserted(0, libraryItemPage.getListItems().size());
                } else if (libraryItemPage.getPageId() == null) {
                    // Rootが関わる推移 (Rootへの推移)
                    adapter.setListItems(libraryItemPage);
                    adapter.notifyItemRangeRemoved(0, currentCount);
                    adapter.notifyItemRangeInserted(0, libraryItemPage.getListItems().size());
                } else if (libraryItemPage.getPageId() != null) {
                    // Rootが関わらない推移
                    adapter.setListItems(libraryItemPage);
                    adapter.notifyItemRangeRemoved(1, currentCount);
                    adapter.notifyItemRangeInserted(1, libraryItemPage.getListItems().size());
                } else {
                    // その他の遷移(安全側に倒すため全アイテム更新)
                    logger.warn("LacertaSelectDirDialog", "Unknown transition.");
                    logger.warn("LacertaSelectDirDialog", "currentDirId: " + currentDirId + ", libraryItemPage.getPageId(): " + libraryItemPage.getPageId());
                    adapter.setListItems(libraryItemPage);
                    adapter.notifyItemRangeRemoved(0, currentCount);
                    adapter.notifyItemRangeInserted(0, libraryItemPage.getListItems().size());
                }
            });
        });
    }
}
