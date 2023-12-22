package one.nem.lacerta.feature.library;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DocumentAdapter extends RecyclerView.Adapter<DocumentAdapter.DocumentViewHolder> {

    private List<String> documentList;

    public DocumentAdapter(List<String> documentList) {
        this.documentList = documentList;
    }

    @NonNull
    @Override
    public DocumentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_library_menu, parent, false);
        return new DocumentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DocumentViewHolder holder, int position) {
        holder.title.setText(documentList.get(position));
    }

    @Override
    public int getItemCount() {
        return documentList.size();
    }

    class DocumentViewHolder extends RecyclerView.ViewHolder {
        TextView title;

        DocumentViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.debug_menu_item_title);
        }
    }
}