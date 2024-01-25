package one.nem.lacerta.component.common;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import one.nem.lacerta.model.ListItem;

public class SelectDirDialogItemAdapter extends RecyclerView.Adapter<SelectDirDialogItemAdapter.SelectDirDialogItemViewHolder> {

    ArrayList<ListItem> listItems;
    LacertaSelectDirDialogEventListener listener;

    public SelectDirDialogItemAdapter(LacertaSelectDirDialogEventListener listener) {
        this.listener = listener;
    }

    public void setListItems(ArrayList<ListItem> listItems) {
        this.listItems = listItems;
    }

    @NonNull
    @Override
    public SelectDirDialogItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(one.nem.lacerta.shared.ui.R.layout.common_list_item, parent, false);
        return new SelectDirDialogItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SelectDirDialogItemViewHolder holder, int position) {
        ListItem listItem = listItems.get(position);
        holder.title.setText(listItem.getTitle());
        holder.description.setText(listItem.getDescription());
        holder.itemView.setOnClickListener(v -> {
            listener.onDirSelected(listItem.getTitle(), listItem.getItemId());
        });
    }

    @Override
    public int getItemCount() {
        return listItems == null ? 0 : listItems.size();
    }

    public static class SelectDirDialogItemViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView description;


        public SelectDirDialogItemViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(one.nem.lacerta.shared.ui.R.id.item_title);
            description = itemView.findViewById(one.nem.lacerta.shared.ui.R.id.item_description);

        }
    }
}
