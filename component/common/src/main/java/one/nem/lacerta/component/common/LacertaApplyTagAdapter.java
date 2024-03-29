package one.nem.lacerta.component.common;

import android.util.Log;
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
        void itemChecked(View view, String tagId);
        void itemUnchecked(View view, String tagId);
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.apply_tag_list_item, parent, false);
        return new LacertaApplyTagAdapter.LacertaApplyTagViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LacertaApplyTagAdapter.LacertaApplyTagViewHolder holder, int position) {
        DocumentTagApplyTagDialogExtendedModel documentTag = documentTagArrayList.get(position);
        if (holder.checkBox == null) {
            Log.d("LacertaApplyTagAdapter", "onBindViewHolder: holder.checkBox is null");
        }
        holder.checkBox.setText(documentTag.getName());
        holder.checkBox.setChecked(documentTag.getIsChecked());
        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                listener.itemChecked(buttonView, documentTag.getId());
            } else {
                listener.itemUnchecked(buttonView, documentTag.getId());
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

            checkBox = itemView.findViewById(R.id.tag_check_box);
        }
    }
}
