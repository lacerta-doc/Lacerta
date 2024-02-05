package one.nem.lacerta.component.common.picker;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import one.nem.lacerta.component.common.R;
import one.nem.lacerta.data.LacertaLibrary;
import one.nem.lacerta.model.ListItem;
import one.nem.lacerta.model.ListItemType;
import one.nem.lacerta.model.pref.ToxiDocumentModel;

@AndroidEntryPoint
public class LacertaFilePickerSelectDocumentDialog extends DialogFragment {

    @Inject
    LacertaLibrary lacertaLibrary;

    // Listener
    public interface LacertaFilePickerSelectDocumentDialogListener {
        void onDocumentSelected(String documentId);
    }

    // Variables
    LacertaFilePickerSelectDocumentDialogListener listener;

    String documentId;

    // Setter
    public LacertaFilePickerSelectDocumentDialog setListener(LacertaFilePickerSelectDocumentDialogListener listener) {
        this.listener = listener;
        return this;
    }

    public LacertaFilePickerSelectDocumentDialog setDocumentId(String documentId) {
        this.documentId = documentId;
        return this;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.lacerta_dialog_select_doc, null);

        // 高さを画面の40%にする
        int height = (int) (getResources().getDisplayMetrics().heightPixels * 0.4);
        view.setMinimumHeight(height);

        RecyclerView recyclerView = view.findViewById(R.id.document_list_recycler_view);

        LacertaFilePickerSelectDocumentAdapter adapter = new LacertaFilePickerSelectDocumentAdapter();

        adapter.setListener(documentId -> listener.onDocumentSelected(documentId));

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        builder.setTitle("追加先を選択");

        builder.setView(view);

        lacertaLibrary.getCombinedDocumentToxiList(this.documentId).thenAccept(toxiDocumentModels -> {
            ArrayList<ListItem> listItems = new ArrayList<>();
            for (ToxiDocumentModel toxiDocumentModel : toxiDocumentModels) {
                listItems.add(new ListItem(toxiDocumentModel.titleCache, null, ListItemType.ITEM_TYPE_DOCUMENT, toxiDocumentModel.childDocumentId));
            }
            adapter.setListItems(listItems);
            adapter.notifyDataSetChanged();
        });

        return builder.create();
    }


}
