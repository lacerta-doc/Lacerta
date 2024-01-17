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

//RecyclerView 用のアダプタークラス
//アダプターはリストのデータを管理し、それを RecyclerView に表示する
public class DocumentAdapter extends RecyclerView.Adapter<DocumentAdapter.DocumentViewHolder> {
    // アダプターが保持するデータ
    private ArrayList<ListItem> documentMetas;

    //documentMetas アダプターが表示するデータ
    public DocumentAdapter(ArrayList<ListItem> documentMetas) {
        // nullの場合でも例外を発生させない
        this.documentMetas = documentMetas != null ? documentMetas : new ArrayList<>();
    }

    @NonNull
    @Override
    public DocumentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_library_menu, parent, false);
        return new DocumentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DocumentViewHolder holder, int position) {
        ListItem documentMeta = documentMetas.get(position);
        if (documentMeta != null) {
            holder.title.setText(documentMeta.getTitle());
        } else {
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
