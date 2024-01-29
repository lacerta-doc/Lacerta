package one.nem.lacerta.feature.library;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import one.nem.lacerta.model.LibraryItemPage;
import one.nem.lacerta.model.ListItem;
import one.nem.lacerta.model.ListItemType;

public class ListItemAdapter extends RecyclerView.Adapter<ListItemAdapter.ListItemViewHolder>{

    LibraryItemPage libraryItemPage;

    DocumentSelectListener listener;


    public ListItemAdapter(DocumentSelectListener listener) {
        this.listener = listener;
    }

    public void setLibraryItemPage(LibraryItemPage libraryItemPage) {
        this.libraryItemPage = libraryItemPage;
    }

    @NonNull
    @Override
    public ListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(one.nem.lacerta.shared.ui.R.layout.common_list_item_with_tag, parent, false);
        return new ListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemViewHolder holder, int position) {
        ListItem listItem = libraryItemPage.getListItems().get(position);
        holder.icon.setImageResource(listItem.getItemType().getIconId());
        holder.icon.setColorFilter(one.nem.lacerta.shared.ui.R.color.colorOnSurface);
        holder.title.setText(listItem.getTitle());
        holder.description.setText(listItem.getDescription());

        if (listItem.getTagList() != null && !listItem.getTagList().isEmpty()) {
            if (holder.tagGroup.getChildCount() > 0) { // ごまかし
                holder.tagGroup.removeAllViews();
            }
            for (int i = 0; i < listItem.getTagList().size(); i++) {
                ChipGroup chipGroup = holder.tagGroup;
                Chip chip = new Chip(chipGroup.getContext());
                chip.setText(listItem.getTagList().get(i).getName());
                chip.setBackgroundColor(Color.parseColor(listItem.getTagList().get(i).getColor()));
                chipGroup.addView(chip);
            }
            holder.tagGroup.setVisibility(View.VISIBLE);
        } else {
            holder.tagGroup.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener( v -> {
            if (listItem.getItemType() == ListItemType.ITEM_TYPE_DOCUMENT) {
                listener.onDocumentSelected(listItem.getItemId(), listItem.getTitle(), listItem.getHasCombined());
            }
            else if (listItem.getItemType() == ListItemType.ITEM_TYPE_FOLDER) {
                listener.onFolderSelected(listItem.getItemId(), listItem.getTitle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.libraryItemPage == null ? 0
                : this.libraryItemPage.getListItems() == null ? 0 : this.libraryItemPage.getListItems().size();
    }

    public static class ListItemViewHolder extends RecyclerView.ViewHolder{

        ImageView icon;
        TextView title;
        TextView description;
        ChipGroup tagGroup;
        public ListItemViewHolder(@NonNull View itemView) {
            super(itemView);

            icon = itemView.findViewById(one.nem.lacerta.shared.ui.R.id.item_icon);
            title = itemView.findViewById(one.nem.lacerta.shared.ui.R.id.item_title);
            description = itemView.findViewById(one.nem.lacerta.shared.ui.R.id.item_description);
            tagGroup = itemView.findViewById(one.nem.lacerta.shared.ui.R.id.item_tags);
        }
    }
}
