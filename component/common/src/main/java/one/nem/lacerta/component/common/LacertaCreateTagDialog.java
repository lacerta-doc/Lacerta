package one.nem.lacerta.component.common;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.fragment.app.DialogFragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class LacertaCreateTagDialog extends DialogFragment {

    private String title;

    private String message;

    private String positiveButtonText;

    private String negativeButtonText;



    public LacertaCreateTagDialog setTitle(String title) {
        this.title = title;
        return this;
    }

    public LacertaCreateTagDialog setMessage(String message) {
        this.message = message;
        return this;
    }

    public LacertaCreateTagDialog setPositiveButtonText(String positiveButtonText) {
        this.positiveButtonText = positiveButtonText;
        return this;
    }

    public LacertaCreateTagDialog setNegativeButtonText(String negativeButtonText) {
        this.negativeButtonText = negativeButtonText;
        return this;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.lacerta_dialog_create_tag, null);
        builder.setView(view);
        return builder.create();
    }


}
