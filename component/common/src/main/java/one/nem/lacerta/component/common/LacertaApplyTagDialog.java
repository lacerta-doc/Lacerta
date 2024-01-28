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
import java.util.concurrent.CompletionException;
import java.util.stream.Collectors;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import one.nem.lacerta.component.common.model.DocumentTagApplyTagDialogExtendedModel;
import one.nem.lacerta.data.Document;
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
        void onDialogPositiveClick(ArrayList<DocumentTag> appliedTags);
        void onDialogNegativeClick();
    }

    // Variables
    private String title;
    private String message;
    private String positiveButtonText;
    private String negativeButtonText;
    private String documentId;
    private LacertaApplyTagDialogListener listener;
    private ArrayList<DocumentTag> registeredTags;
    private ArrayList<DocumentTag> appliedTags;

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
                applyChangeToVariable(true, tagId);
            }

            @Override
            public void itemUnchecked(View view, String tagId) {
                applyChangeToVariable(false, tagId);
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
                .setPositiveButton(positiveButtonText == null ? "OK" : positiveButtonText, (dialog, id) -> {
                    // Send the positive button event back to the host activity
                    listener.onDialogPositiveClick(this.appliedTags);
                })
                .setNegativeButton(negativeButtonText == null ? "Cancel" : negativeButtonText, (dialog, id) -> {
                    // Send the negative button event back to the host activity
                    listener.onDialogNegativeClick();
                });
        return builder.create();
    }

    private void applyChangeToVariable(boolean isChecked, String tagId) {
        if (isChecked) {
            this.registeredTags.stream().filter(tag -> tag.getId().equals(tagId)).findFirst().ifPresent(tag -> this.appliedTags.add(tag));
        } else {
            this.appliedTags.stream().filter(tag -> tag.getId().equals(tagId)).findFirst().ifPresent(tag -> this.appliedTags.remove(tag));
        }
    }

    private CompletableFuture<ArrayList<DocumentTagApplyTagDialogExtendedModel>> getDocumentTagArrayList(String documentId) {
        return CompletableFuture.supplyAsync(() -> {
            ArrayList<DocumentTagApplyTagDialogExtendedModel> documentTagArrayList = new ArrayList<>();
            setRegisteredTagList().thenRun(() -> setAppliedTagList(documentId).join()).thenAccept(Void -> {
                logger.debug("getDocumentTagArrayList", "this.registeredTags.size(): " + this.registeredTags.size());
                logger.debug("getDocumentTagArrayList", "this.appliedTags.size(): " + this.appliedTags.size());

                documentTagArrayList.addAll(this.registeredTags.stream().map(tag -> {
                    DocumentTagApplyTagDialogExtendedModel documentTag = new DocumentTagApplyTagDialogExtendedModel(
                            new DocumentTag(tag.getId(), tag.getName(), tag.getColor()),
                            this.appliedTags.stream().anyMatch(appliedTag -> appliedTag.getId().equals(tag.getId()))
                    );
                    return documentTag;
                }).collect(Collectors.toCollection(ArrayList::new)));

            }).join();
            return documentTagArrayList;
        });
    }

    private CompletableFuture<Void> setRegisteredTagList() {
        return CompletableFuture.runAsync(() -> {
            this.registeredTags = new ArrayList<>();
            this.lacertaLibrary.getTagList().thenAccept(documentTagList -> {
                for (int i = 0; i < documentTagList.size(); i++) {
                    this.registeredTags.add(new DocumentTag(documentTagList.get(i).getId(), documentTagList.get(i).getName(), documentTagList.get(i).getColor()));
                }
            }).join();
        });
    }

    private CompletableFuture<Void> setAppliedTagList(String documentId) {
        return CompletableFuture.runAsync(() -> {
            this.appliedTags = new ArrayList<>();
            this.lacertaLibrary.getAppliedTagList(documentId).thenAccept(documentTagList -> {
                for (int i = 0; i < documentTagList.size(); i++) {
                    this.appliedTags.add(new DocumentTag(documentTagList.get(i).getId(), documentTagList.get(i).getName(), documentTagList.get(i).getColor()));
                }
            }).join();
        });
    }
}
