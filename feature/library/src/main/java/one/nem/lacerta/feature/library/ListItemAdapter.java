package one.nem.lacerta.feature.library;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import one.nem.lacerta.model.LibraryItemPage;
import one.nem.lacerta.model.ListItem;

public class ListItemAdapter extends RecyclerView.Adapter<ListItemAdapter.ListItemViewHolder>{

    LibraryItemPage libraryItemPage;

    DocumentSelectListener listener;

    public ListItemAdapter(DocumentSelectListener listener){
        this.libraryItemPage = new LibraryItemPage();
        this.listener = listener;
    }

    public void setLibraryItemPage(LibraryItemPage libraryItemPage) {
        this.libraryItemPage = libraryItemPage;
    }

    @NonNull
    @Override
    public ListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(one.nem.lacerta.shared.ui.R.layout.common_list_item, parent, false);
        return new ListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemViewHolder holder, int position) {
        ListItem listItem = libraryItemPage.getListItems().get(position);
        holder.icon.setImageResource(listItem.getItemType().getIconId());
        holder.icon.setColorFilter(one.nem.lacerta.shared.ui.R.color.colorOnSurface);
        holder.title.setText(listItem.getTitle());
        holder.description.setText(listItem.getDescription());

        holder.itemView.setOnClickListener( v -> {
//            Intent intent = new Intent(v.getContext(), ViewerMainActivity.class);
//            intent.putExtra("documentId", listItem.getItemId());
//            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return libraryItemPage.getListItems().size();
    }

    public static class ListItemViewHolder extends RecyclerView.ViewHolder{

        ImageView icon;
        TextView title;
        TextView description;
        public ListItemViewHolder(@NonNull View itemView) {
            super(itemView);

            icon = itemView.findViewById(one.nem.lacerta.shared.ui.R.id.item_icon);
            title = itemView.findViewById(one.nem.lacerta.shared.ui.R.id.item_title);
            description = itemView.findViewById(one.nem.lacerta.shared.ui.R.id.item_description);
        }
    }
}
