package one.nem.lacerta.shared.ui;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class LacertaSelectDirDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
        builder.setTitle("Select Directory");
        builder.setMessage("Please select a directory.");
        builder.setPositiveButton("OK", null);
        builder.setNegativeButton("Cancel", null);
        return builder.create();
    }
}
