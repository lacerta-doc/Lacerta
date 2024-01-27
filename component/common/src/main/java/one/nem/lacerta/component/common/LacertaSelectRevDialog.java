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

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import one.nem.lacerta.data.LacertaLibrary;
import one.nem.lacerta.utils.LacertaLogger;
import one.nem.lacerta.vcs.LacertaVcs;
import one.nem.lacerta.vcs.factory.LacertaVcsFactory;


@AndroidEntryPoint
public class LacertaSelectRevDialog extends DialogFragment {

    @Inject
    LacertaVcsFactory lacertaVcsFactory;

    @Inject
    LacertaLogger logger;

    String title;
    String documentId;
    String message;
    String negativeButtonText;

    LacertaSelectRevDialogListener listener;

    public LacertaSelectRevDialog setTitle(String title) {
        this.title = title;
        return this;
    }

    public LacertaSelectRevDialog setDocumentId(String documentId) {
        this.documentId = documentId;
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
                dismiss();
            }
        });
                recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        builder.setView(view);

        if (this.documentId == null) {
            logger.error("SelectRevDialog", "documentId is null");
            logger.e_code("0296fb0c-07a3-4971-a280-bd1a61461bb7");
            Toast.makeText(getContext(), "Sorry, something went wrong", Toast.LENGTH_SHORT).show();
            dismiss();
        }
        LacertaVcs lacertaVcs = lacertaVcsFactory.create(this.documentId);
        lacertaVcs.getRevisionHistory().thenAccept(revList -> {
            adapter.setRevList(revList);
            adapter.notifyDataSetChanged(); // TODO-rca:時間に応じてアニメーションをつける
        });


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
