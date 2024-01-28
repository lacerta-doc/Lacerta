package one.nem.lacerta.component.common.picker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import one.nem.lacerta.component.common.R;
import one.nem.lacerta.model.ListItem;
import one.nem.lacerta.model.document.DocumentMeta;

public class LacertaFilePickerSelectDocumentAdapter extends RecyclerView.Adapter<LacertaFilePickerSelectDocumentAdapter.LacertaFilePickerSelectDocumentViewHolder>{

    // Listener
    public interface LacertaFilePickerSelectDocumentAdapterListener {
        void onDocumentSelected(String documentId);
    }

    // Variables
    LacertaFilePickerSelectDocumentAdapterListener listener;

    ArrayList<ListItem> listItems;

    // Setter
    public LacertaFilePickerSelectDocumentAdapter setListener(LacertaFilePickerSelectDocumentAdapterListener listener) {
        this.listener = listener;
        return this;
    }

    public void setListItems(ArrayList<ListItem> listItems) {
        this.listItems = listItems;
    }


    @NonNull
    @Override
    public LacertaFilePickerSelectDocumentAdapter.LacertaFilePickerSelectDocumentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(one.nem.lacerta.shared.ui.R.layout.common_list_item, parent, false);
        return new LacertaFilePickerSelectDocumentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LacertaFilePickerSelectDocumentAdapter.LacertaFilePickerSelectDocumentViewHolder holder, int position) {
        ListItem listItem = listItems.get(position);
        holder.title.setText(listItem.getTitle());
        holder.icon.setImageResource(listItem.getItemType().getIconId());
        holder.itemView.setOnClickListener(v -> listener.onDocumentSelected(listItem.getItemId()));
    }

    @Override
    public int getItemCount() {
        return listItems == null ? 0 : listItems.size();
    }

    public class LacertaFilePickerSelectDocumentViewHolder extends RecyclerView.ViewHolder {

        ImageView icon;
        TextView title;
        TextView description;

        public LacertaFilePickerSelectDocumentViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(one.nem.lacerta.shared.ui.R.id.item_icon);
            title = itemView.findViewById(one.nem.lacerta.shared.ui.R.id.item_title);
            description = itemView.findViewById(one.nem.lacerta.shared.ui.R.id.item_description);
        }
    }
}
