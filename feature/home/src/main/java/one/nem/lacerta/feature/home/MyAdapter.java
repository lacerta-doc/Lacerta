package one.nem.lacerta.feature.home;

import android.view.View;
import android.view.ViewGroup;

import one.nem.lacerta.model.document.DocumentMeta;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyAdapterViewHolder> {
    public MyAdapter(List<DocumentMeta> documentMeta) {
    }

    @NonNull
    @Override
    public MyAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 100;
    }

    public class MyAdapterViewHolder extends RecyclerView.ViewHolder {
        public MyAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
