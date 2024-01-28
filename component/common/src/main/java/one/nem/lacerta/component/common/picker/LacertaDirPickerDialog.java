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
        RecyclerView recyclerView = view.findViewById(R.id.select_dir_recycler_view);
        TextView currentDirTextView = view.findViewById(R.id.current_dir_text_view);

        LacertaFilePickerAdapterBase adapter = new LacertaFilePickerAdapterBase();
        adapter.setListener(new LacertaFilePickerAdapterBase.LacertaFilePickerAdapterListener() {
            @Override
            public void onItemSelected(String dirId) {
                updateList(adapter, dirId);
                currentDirTextView.setText(dirId);
            }

            @Override
            public void onBackSelected(String dirId) {
                updateList(adapter, dirId);
                currentDirTextView.setText(dirId);
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

    private void updateList(LacertaFilePickerAdapterBase adapter, String folderId) {
        lacertaLibrary.getFolderList(folderId).thenAccept(libraryItemPage -> {
            adapter.setListItems(libraryItemPage);
            this.updateList(adapter, libraryItemPage, adapter.getItemCount(), adapter.getCurrentId());
        });
    }
}
