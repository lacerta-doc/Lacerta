package one.nem.lacerta.component.common;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import one.nem.lacerta.data.LacertaLibrary;

@AndroidEntryPoint
public class LacertaSelectDirDialog extends DialogFragment {

    @Inject
    LacertaLibrary lacertaLibrary;

    private SelectDirDialogItemAdapter adapter;

    private RecyclerView recyclerView;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        super.onCreateDialog(savedInstanceState);

        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.lacerta_dialog_select_dir, null);

        this.recyclerView = view.findViewById(R.id.select_dir_recycler_view);

        this.adapter = new SelectDirDialogItemAdapter((name, itemId) -> {
            Toast.makeText(getContext(), "Called:" + name, Toast.LENGTH_SHORT).show();
            showRecyclerViewItem(itemId);
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

    private void showRecyclerViewItem(String parent) {
        lacertaLibrary.getFolderList(parent).thenAccept(listItems -> {
            getActivity().runOnUiThread(() -> {
                int currentCount = adapter.getItemCount();
                adapter.notifyItemRangeRemoved(0, currentCount);
                adapter.setListItems(listItems);
                adapter.notifyItemRangeInserted(0, listItems.size());
            });
        });
    }
}
