package one.nem.lacerta.component.common;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import org.w3c.dom.Text;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import one.nem.lacerta.data.LacertaLibrary;
import one.nem.lacerta.model.ListItemType;

@AndroidEntryPoint
public class LacertaSelectDirDialog extends DialogFragment {

    @Inject
    LacertaLibrary lacertaLibrary;

    private SelectDirDialogItemAdapter adapter;

    private RecyclerView recyclerView;

    private TextView current_dir_text_view;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        super.onCreateDialog(savedInstanceState);

        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.lacerta_dialog_select_dir, null);

        this.recyclerView = view.findViewById(R.id.select_dir_recycler_view);
        this.current_dir_text_view = view.findViewById(R.id.current_dir_text_view);

        this.adapter = new SelectDirDialogItemAdapter(new LacertaSelectDirDialogEventListener() {
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

        builder.setTitle("Select Directory");
        builder.setMessage("Please select a directory.");
        builder.setPositiveButton("OK", null);
        builder.setNegativeButton("Cancel", null);
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
                adapter.notifyItemRangeRemoved(1, currentCount); // Backボタンを除くすべてのアイテムを削除
                adapter.setListItems(libraryItemPage);
                adapter.notifyItemRangeInserted(1, libraryItemPage.getListItems().size());
            });
        });
    }
}
