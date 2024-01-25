package one.nem.lacerta.setting;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
        View view = LayoutInflater.from(parent.getContext()).inflate(one.nem.lacerta.shared.ui.R.layout.tag_list_full_item, parent, false);
        return new TagListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TagListItemViewHolder holder, int position) {
        DocumentTag documentTag = documentTags.get(position);

        holder.tag_name.setText(documentTag.getName());
        try {
            holder.tag_icon.setImageResource(one.nem.lacerta.shared.ui.R.drawable.class.getField(documentTag.getIcon()).getInt(null));
        } catch (Exception e) {
            holder.tag_name.setText("Sorry, Parse Error occurred");
        }
    }

    @Override
    public int getItemCount() {
        return documentTags == null ? 0 : documentTags.size();
    }

    public static class TagListItemViewHolder extends RecyclerView.ViewHolder {

        ImageView tag_icon;
        TextView tag_name;

        public TagListItemViewHolder(@NonNull View itemView) {
            super(itemView);

            tag_icon = itemView.findViewById(one.nem.lacerta.shared.ui.R.id.tag_icon);
            tag_name = itemView.findViewById(one.nem.lacerta.shared.ui.R.id.tag_name);

        }
    }
}
