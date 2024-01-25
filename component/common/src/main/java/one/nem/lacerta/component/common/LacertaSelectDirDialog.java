package one.nem.lacerta.component.common;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
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

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.lacerta_dialog_select_dir, null);

        RecyclerView recyclerView = view.findViewById(R.id.select_dir_recycler_view);
        recyclerView.setHasFixedSize(true);

        showRecyclerViewItem(null); // get root folder list

        this.adapter = new SelectDirDialogItemAdapter((name, itemId) -> {
            Toast.makeText(getContext(), "Called: name", Toast.LENGTH_SHORT).show();
            showRecyclerViewItem(itemId);
            dismiss();
        });

        recyclerView.setAdapter(this.adapter);
        recyclerView.setLayoutManager(new androidx.recyclerview.widget.LinearLayoutManager(getContext()));

        builder.setView(view);

        builder.setTitle("Select Directory");
        builder.setMessage("Please select a directory.");
        builder.setPositiveButton("OK", null);
        builder.setNegativeButton("Cancel", null);
        return builder.create();
    }

    private void showRecyclerViewItem(String parent) {
        lacertaLibrary.getFolderList(parent).thenAccept(listItems -> {
            adapter.setListItems(listItems);
            adapter.notifyDataSetChanged();
        });

    }
}
