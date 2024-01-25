package one.nem.lacerta.setting;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TagListItemAdapter extends RecyclerView.Adapter<TagListItemAdapter.TagListItemViewHolder> {

        @NonNull
        @Override
        public TagListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull TagListItemViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }

        public static class TagListItemViewHolder extends RecyclerView.ViewHolder {

            public TagListItemViewHolder(@NonNull View itemView) {
                super(itemView);
            }
        }
}
