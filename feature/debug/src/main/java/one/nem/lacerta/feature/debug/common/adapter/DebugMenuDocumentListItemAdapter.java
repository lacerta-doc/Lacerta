package one.nem.lacerta.feature.debug.common.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import one.nem.lacerta.feature.debug.R;
import one.nem.lacerta.feature.debug.common.model.DebugMenuDocumentListItem;

public class DebugMenuDocumentListItemAdapter extends RecyclerView.Adapter<DebugMenuDocumentListItemAdapter.DebugMenuDocumentListItemViewHolder> {

    private List<DebugMenuDocumentListItem> debugMenuDocumentListItems;

    public DebugMenuDocumentListItemAdapter(List<DebugMenuDocumentListItem> debugMenuDocumentListItems) {
        this.debugMenuDocumentListItems = debugMenuDocumentListItems;
    }

    @NonNull
    @Override
    public DebugMenuDocumentListItemAdapter.DebugMenuDocumentListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull DebugMenuDocumentListItemAdapter.DebugMenuDocumentListItemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class DebugMenuDocumentListItemViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView description;
        TextView updatedAt;
        public DebugMenuDocumentListItemViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.document_list_item_title);
            description = itemView.findViewById(R.id.document_list_item_description);
            updatedAt = itemView.findViewById(R.id.document_list_item_updated_at);

        }
    }
}
