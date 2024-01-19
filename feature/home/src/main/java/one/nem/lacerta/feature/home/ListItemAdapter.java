package one.nem.lacerta.feature.home;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import one.nem.lacerta.model.ListItem;

public class ListItemAdapter extends RecyclerView.Adapter<ListItemAdapter.ListItemViewHolder>{

    ArrayList<ListItem> listItems;

    public ListItemAdapter(ArrayList<ListItem> listItems){
        this.listItems = listItems;
    }

    @NonNull
    @Override
    public ListItemAdapter.ListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(one.nem.lacerta.component.common.R.layout.common_library_list_item, parent, false);
        return new ListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemAdapter.ListItemViewHolder holder, int position) {
        ListItem listItem = listItems.get(position);
        holder.icon.setImageResource(listItem.getItemType().getIconId());
        holder.icon.setColorFilter(one.nem.lacerta.shared.ui.R.color.colorOnBackground);
        holder.title.setText(listItem.getTitle());
        holder.description.setText(listItem.getDescription());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public static class ListItemViewHolder extends RecyclerView.ViewHolder{

        ImageView icon;
        TextView title;
        TextView description;
        public ListItemViewHolder(@NonNull View itemView) {
            super(itemView);

            icon = itemView.findViewById(one.nem.lacerta.component.common.R.id.item_icon);
            title = itemView.findViewById(one.nem.lacerta.component.common.R.id.item_title);
            description = itemView.findViewById(one.nem.lacerta.component.common.R.id.item_description);
        }
    }
}
