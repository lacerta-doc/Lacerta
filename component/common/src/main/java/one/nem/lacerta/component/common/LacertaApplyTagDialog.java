package one.nem.lacerta.component.common;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import one.nem.lacerta.component.common.model.DocumentTagApplyTagDialogExtendedModel;
import one.nem.lacerta.data.LacertaLibrary;
import one.nem.lacerta.model.document.tag.DocumentTag;
import one.nem.lacerta.utils.LacertaLogger;

@AndroidEntryPoint
public class LacertaApplyTagDialog extends DialogFragment {

    @Inject
    LacertaLogger logger;

    @Inject
    LacertaLibrary lacertaLibrary;

    // Listener
    public interface LacertaApplyTagDialogListener {
        void onDialogPositiveClick(DialogFragment dialog);
        void onDialogNegativeClick(DialogFragment dialog);
    }

    // Variables
    private String title;
    private String message;
    private String positiveButtonText;
    private String negativeButtonText;
    private String documentId;
    private LacertaApplyTagDialogListener listener;

    // Setter

    public LacertaApplyTagDialog setListener(LacertaApplyTagDialogListener listener) {
        this.listener = listener;
        return this;
    }

    public LacertaApplyTagDialog setTitle(String title) {
        this.title = title;
        return this;
    }

    public LacertaApplyTagDialog setMessage(String message) {
        this.message = message;
        return this;
    }

    public LacertaApplyTagDialog setPositiveButtonText(String positiveButtonText) {
        this.positiveButtonText = positiveButtonText;
        return this;
    }

    public LacertaApplyTagDialog setNegativeButtonText(String negativeButtonText) {
        this.negativeButtonText = negativeButtonText;
        return this;
    }

    public LacertaApplyTagDialog setDocumentId(String documentId) {
        this.documentId = documentId;
        return this;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.lacerta_dialog_apply_tag, null);

        // RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.apply_tag_list);

        LacertaApplyTagAdapter lacertaApplyTagAdapter = new LacertaApplyTagAdapter();
        lacertaApplyTagAdapter.setListener(new LacertaApplyTagAdapter.LacertaApplyTagDialogListener() {
            @Override
            public void itemChecked(View view, String tagId) {
                // Do something
                Toast.makeText(view.getContext(), tagId, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void itemUnchecked(View view, String tagId) {
                // Do something
                Toast.makeText(view.getContext(), tagId, Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView.setAdapter(lacertaApplyTagAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        getDocumentTagArrayList(documentId).thenAccept(documentTagArrayList -> {
            lacertaApplyTagAdapter.setDocumentTagArrayList(documentTagArrayList);
            lacertaApplyTagAdapter.notifyDataSetChanged(); // TODO-rca: アニメーション
        });

        // Set the dialog title
        builder.setTitle(title)
                .setMessage(message)
                .setView(view)
                .setPositiveButton(positiveButtonText, (dialog, id) -> {
                    // Send the positive button event back to the host activity
                    listener.onDialogPositiveClick(LacertaApplyTagDialog.this);
                })
                .setNegativeButton(negativeButtonText, (dialog, id) -> {
                    // Send the negative button event back to the host activity
                    listener.onDialogNegativeClick(LacertaApplyTagDialog.this);
                });
        return builder.create();
    }

    private CompletableFuture<ArrayList<DocumentTagApplyTagDialogExtendedModel>> getDocumentTagArrayList(String documentId) {
        return CompletableFuture.supplyAsync(() -> {
            ArrayList<DocumentTagApplyTagDialogExtendedModel> documentTagArrayList = new ArrayList<>();
            lacertaLibrary.getTagList().thenAccept(documentTags -> {
                ArrayList<DocumentTag> appliedTags = lacertaLibrary.getAppliedTagList(documentId).join();
                for (int i = 0; i < documentTags.size(); i++) {
                    boolean isChecked = false;
                    for (int j = 0; j < appliedTags.size(); j++) {
                        if (documentTags.get(i).getId().equals(appliedTags.get(j).getId())) {
                            isChecked = true;
                            break;
                        }
                    }
                    documentTagArrayList.add(new DocumentTagApplyTagDialogExtendedModel(
                            new DocumentTag(documentTags.get(i).getId(), documentTags.get(i).getName(), documentTags.get(i).getColor()), isChecked));
                }
            });

            return documentTagArrayList;
        });
    }

}
