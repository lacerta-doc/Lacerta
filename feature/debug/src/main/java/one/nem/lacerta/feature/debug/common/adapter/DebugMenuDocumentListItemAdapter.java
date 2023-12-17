package one.nem.lacerta.feature.debug.common.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import one.nem.lacerta.feature.debug.common.model.DebugMenuDocumentListItem;

public class DebugMenuDocumentListItemAdapter extends RecyclerView.Adapter<DebugMenuDocumentListItemAdapter.DebugMenuDocumentListItemViewHolder> {

    private List<DebugMenuDocumentListItem> debugMenuDocumentListItems;

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
    }
}
