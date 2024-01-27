package one.nem.lacerta.component.common;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class LacertaSelectRevDialog extends DialogFragment {

    String title;
    String message;
    String negativeButtonText;

    LacertaSelectRevDialogListener listener;

    public LacertaSelectRevDialog setTitle(String title) {
        this.title = title;
        return this;
    }

    public LacertaSelectRevDialog setMessage(String message) {
        this.message = message;
        return this;
    }

    public LacertaSelectRevDialog setNegativeButtonText(String negativeButtonText) {
        this.negativeButtonText = negativeButtonText;
        return this;
    }

    public LacertaSelectRevDialog setListener(LacertaSelectRevDialogListener listener) {
        this.listener = listener;
        return this;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.lacerta_dialog_select_rev, null);

        int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.4);
        view.setMinimumHeight(height);

        RecyclerView recyclerView = view.findViewById(R.id.select_rev_recycler_view);

        SelectRevDialogItemAdapter adapter = new SelectRevDialogItemAdapter(revId -> {
            if (listener != null) {
                listener.onItemSelected(revId);
            }
            dismiss();
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        builder.setView(view);
        builder.setTitle(title == null ? "Select Rev" : title);
        builder.setMessage(message == null ? "Select Rev" : message);
        builder.setNegativeButton(negativeButtonText, (dialog, which) -> {
            if (listener != null) {
                listener.onDialogCanceled();
                dismiss();
            }
        });

        return builder.create();
    }
}
