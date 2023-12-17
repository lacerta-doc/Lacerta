package one.nem.lacerta.feature.debug.common.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import one.nem.lacerta.feature.debug.R;
import one.nem.lacerta.feature.debug.common.model.DebugMenuListItem;

public class DebugMenuListItemAdapter extends RecyclerView.Adapter<DebugMenuListItemAdapter.DebugMenuListItemViewHolder> {
    private List<DebugMenuListItem> debugMenuListItems;

    public DebugMenuListItemAdapter(List<DebugMenuListItem> debugMenuListItems) {
        this.debugMenuListItems = debugMenuListItems;
    }

    @NonNull
    @Override
    public DebugMenuListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_debug_menu, parent, false);
        return new DebugMenuListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DebugMenuListItemViewHolder holder, int position) {
        DebugMenuListItem item = debugMenuListItems.get(position);
        holder.title.setText(item.getTitle());
        holder.description.setText(item.getDescription());

        holder.itemView.setOnClickListener( v -> {
            Navigation.findNavController(v).navigate(item.getDestinationId());
        });
    }

    @Override
    public int getItemCount() {
        return debugMenuListItems.size();
    }

    static class DebugMenuListItemViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView description;

        public DebugMenuListItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}