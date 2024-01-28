package one.nem.lacerta.component.common.picker;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import one.nem.lacerta.component.common.R;
import one.nem.lacerta.component.common.picker.base.LacertaFilePickerAdapterBase;
import one.nem.lacerta.component.common.picker.base.LacertaFilePickerDialogBase;
import one.nem.lacerta.data.LacertaLibrary;
import one.nem.lacerta.model.ListItemType;
import one.nem.lacerta.model.PublicPath;

@AndroidEntryPoint
public class LacertaDirPickerDialog extends LacertaFilePickerDialogBase {

    @Inject
    LacertaLibrary lacertaLibrary;

    // Listener
    public interface LacertaDirPickerDialogListener {
        void onDirSelected(String name, String dirId);
    }

    // Variables
    LacertaDirPickerDialogListener listener;

    // Setter
    public LacertaDirPickerDialog setListener(LacertaDirPickerDialogListener listener) {
        this.listener = listener;
        return this;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.lacerta_dialog_select_dir, null);

        // 高さを画面の40%にする
        int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.4);
        view.setMinimumHeight(height);

        RecyclerView recyclerView = view.findViewById(R.id.select_dir_recycler_view);
        TextView currentDirTextView = view.findViewById(R.id.current_dir_text_view);

        LacertaFilePickerAdapterBase adapter = new LacertaFilePickerAdapterBase();
        adapter.setListener(new LacertaFilePickerAdapterBase.LacertaFilePickerAdapterListener() {
            @Override
            public void onItemSelected(String dirId) {
                updateList(adapter, dirId);
                updatePublicPath(currentDirTextView, dirId);
            }

            @Override
            public void onBackSelected(String dirId) {
                updateList(adapter, dirId);
                updatePublicPath(currentDirTextView, dirId);
            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        this.updateList(adapter, null); // ルートディレクトリのリストを表示

        // Init dialog
        builder.setTitle(this.title == null ? "フォルダを選択" : this.title);
        builder.setMessage(this.message == null ? "フォルダを選択してください" : this.message);
        builder.setView(view);
        builder.setPositiveButton(this.positiveButtonText == null ? "OK" : this.positiveButtonText, (dialog, which) -> {
            if (listener != null) {
                listener.onDirSelected(
                        adapter.getCurrentPageTitle(),
                        adapter.getCurrentId());
            }
        });
        builder.setNegativeButton(this.negativeButtonText == null ? "キャンセル" : this.negativeButtonText, (dialog, which) -> {
            if (listener != null) {
                dismiss();
            }
        });

        return builder.create();
    }

    private void updatePublicPath(TextView currentDirTextView, String folderId) {
        lacertaLibrary.getPublicPath(folderId, ListItemType.ITEM_TYPE_FOLDER).thenAccept(publicPath -> {
            this.updatePathTextView(currentDirTextView, publicPath, ListItemType.ITEM_TYPE_FOLDER);
        });
    }

    private void updateList(LacertaFilePickerAdapterBase adapter, String folderId) {
        lacertaLibrary.getFolderList(folderId).thenAccept(libraryItemPage -> {
            int currentCount = adapter.getItemCount();
            String currentId = adapter.getCurrentId();
//            adapter.setListItems(libraryItemPage);
            getActivity().runOnUiThread(() -> {
                this.updateListView(adapter, libraryItemPage, currentCount, currentId);
            });
        });
    }
}
