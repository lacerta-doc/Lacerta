package one.nem.lacerta.feature.library;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import one.nem.lacerta.model.ListItem;
import one.nem.lacerta.model.document.DocumentMeta;

public class DocumentAdapter extends RecyclerView.Adapter<DocumentAdapter.DocumentViewHolder> {

    private ArrayList<ListItem> documentMetas;

    public DocumentAdapter(ArrayList<ListItem> documentMetas) {
        // nullの場合に例外を発生させる
        if (documentMetas == null) {
            throw new IllegalArgumentException("DocumentMetas list cannot be null or empty");
        }
        this.documentMetas = documentMetas;
    }

    @NonNull
    @Override
    public DocumentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_library_menu, parent, false);// 適切な id に変更する
        return new DocumentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DocumentViewHolder holder, int position) {
        ListItem documentMeta = documentMetas.get(position);
        if (documentMeta != null) {
            holder.title.setText(documentMeta.getTitle());
        }else{
            holder.title.setText("データがありません");
        }
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
