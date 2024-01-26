package one.nem.lacerta.component.common;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class LacertaCreateTagDialog extends DialogFragment {

    private String title;

    private String message;

    private String positiveButtonText;

    private String negativeButtonText;

    private LacertaCreateTagDialogListener listener;

    // Setter

    public LacertaCreateTagDialog setListener(LacertaCreateTagDialogListener listener) {
        this.listener = listener;
        return this;
    }

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

        // TextEdit
        EditText tag_name_edit_text = view.findViewById(R.id.tag_name_edit_text);
        com.google.android.material.textfield.TextInputLayout tag_name_text_input_layout = view.findViewById(R.id.tag_name_text_input_layout);
        tag_name_text_input_layout.setHint("タグの名前");
        EditText tag_color_edit_text = view.findViewById(R.id.tag_color_edit_text);
        com.google.android.material.textfield.TextInputLayout tag_color_text_input_layout = view.findViewById(R.id.tag_color_text_input_layout);
        tag_color_text_input_layout.setHint("タグの色(カラーコード)");

        builder.setTitle(this.title == null ? "Create new tag" : this.title);

        // Button
        builder.setPositiveButton(positiveButtonText == null ? "OK" : positiveButtonText, (dialog, which) -> {
            String tag_name = tag_name_edit_text.getText().toString();
            String tag_color = tag_color_edit_text.getText().toString();
            if (listener != null) {
                listener.onPositiveClick(tag_name, tag_color);
            }
        });
        builder.setNegativeButton(negativeButtonText == null ? "Cancel" : negativeButtonText, (dialog, which) -> {
            if (listener != null) {
                listener.onNegativeClick();
            }
        });

        builder.setView(view);
        return builder.create();
    }


}
