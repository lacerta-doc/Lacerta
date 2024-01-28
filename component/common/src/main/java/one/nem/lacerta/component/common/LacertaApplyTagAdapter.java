package one.nem.lacerta.component.common;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import one.nem.lacerta.component.common.model.DocumentTagApplyTagDialogExtendedModel;
import one.nem.lacerta.model.document.tag.DocumentTag;

public class LacertaApplyTagAdapter extends RecyclerView.Adapter<LacertaApplyTagAdapter.LacertaApplyTagViewHolder>{

    // Listener
    public interface LacertaApplyTagDialogListener {
        void itemChecked(View view, int position);
        void itemUnchecked(View view, int position);
    }

    // Variables
    private ArrayList<DocumentTagApplyTagDialogExtendedModel> documentTagArrayList;
    private LacertaApplyTagDialogListener listener;

    // Setter
    public LacertaApplyTagAdapter setListener(LacertaApplyTagDialogListener listener) {
        this.listener = listener;
        return this;
    }

    public LacertaApplyTagAdapter setDocumentTagArrayList(ArrayList<DocumentTagApplyTagDialogExtendedModel> documentTagArrayList) {
        this.documentTagArrayList = documentTagArrayList;
        return this;
    }

    // Empty constructor
    public LacertaApplyTagAdapter() {
    }

    @NonNull
    @Override
    public LacertaApplyTagAdapter.LacertaApplyTagViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lacerta_dialog_apply_tag, parent, false);
        return new LacertaApplyTagAdapter.LacertaApplyTagViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LacertaApplyTagAdapter.LacertaApplyTagViewHolder holder, int position) {
        DocumentTagApplyTagDialogExtendedModel documentTag = documentTagArrayList.get(position);
        holder.checkBox.setText(documentTag.getName());
        holder.checkBox.setChecked(documentTag.getIsChecked());
        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                listener.itemChecked(buttonView, position);
            } else {
                listener.itemUnchecked(buttonView, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return documentTagArrayList == null ? 0 : documentTagArrayList.size();
    }

    public class LacertaApplyTagViewHolder extends RecyclerView.ViewHolder {

        CheckBox checkBox;

        public LacertaApplyTagViewHolder(@NonNull View itemView) {
            super(itemView);

            checkBox = itemView.findViewById(R.id.checkBox);
        }
    }
}
