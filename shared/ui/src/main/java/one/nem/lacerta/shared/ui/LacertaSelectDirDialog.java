package one.nem.lacerta.shared.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class LacertaSelectDirDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.lacerta_dialog_select_dir, null);

        RecyclerView recyclerView = view.findViewById(R.id.select_dir_recycler_view);
        recyclerView.setHasFixedSize(true);

        SelectDirDialogItemAdapter adapter = new SelectDirDialogItemAdapter((name, itemId) -> {
            Toast.makeText(getContext(), "Called", Toast.LENGTH_SHORT).show();
            dismiss();
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new androidx.recyclerview.widget.LinearLayoutManager(getContext()));

        Toolbar toolbar = view.findViewById(R.id.select_dir_toolbar);
        toolbar.setNavigationOnClickListener(v -> {
            Toast.makeText(getContext(), "Called", Toast.LENGTH_SHORT).show();
            dismiss();
        });

        builder.setView(view);

        builder.setTitle("Select Directory");
        builder.setMessage("Please select a directory.");
        builder.setPositiveButton("OK", null);
        builder.setNegativeButton("Cancel", null);
        return builder.create();
    }
}
