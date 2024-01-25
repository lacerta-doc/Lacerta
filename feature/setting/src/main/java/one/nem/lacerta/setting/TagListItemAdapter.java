package one.nem.lacerta.setting;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import one.nem.lacerta.model.document.tag.DocumentTag;

public class TagListItemAdapter extends RecyclerView.Adapter<TagListItemAdapter.TagListItemViewHolder> {

    ArrayList<DocumentTag> documentTags;

    TagListItemSelectListener listener;

    public TagListItemAdapter(TagListItemSelectListener listener) {
        this.listener = listener;
    }

    public void setDocumentTags(ArrayList<DocumentTag> documentTags) {
        this.documentTags = documentTags;
    }

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
        return documentTags == null ? 0 : documentTags.size();
    }

    public static class TagListItemViewHolder extends RecyclerView.ViewHolder {

        public TagListItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
