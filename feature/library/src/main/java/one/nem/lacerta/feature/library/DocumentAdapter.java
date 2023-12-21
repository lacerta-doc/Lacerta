package one.nem.lacerta.feature.library;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import one.nem.lacerta.model.document.DocumentMeta;

public class DocumentAdapter extends RecyclerView.Adapter<DocumentAdapter.DocumentViewHolder> {

    private List<DocumentMeta> documentMetas;

    public DocumentAdapter(List<DocumentMeta> documentMetas) {
        this.documentMetas = documentMetas;
    }

    @NonNull
    @Override
    public DocumentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_library_menu, parent, false);
        return new DocumentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DocumentViewHolder holder, int position) {
        // DocumentMeta から適切な情報を取得してセット
        holder.title.setText(documentMetas.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return documentMetas.size();
    }

    class DocumentViewHolder extends RecyclerView.ViewHolder {
        TextView title;

        DocumentViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.debug_menu_item_title); // 適切な id に変更する
        }
    }
}