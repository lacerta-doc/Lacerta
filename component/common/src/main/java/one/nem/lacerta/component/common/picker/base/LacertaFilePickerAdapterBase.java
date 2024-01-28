package one.nem.lacerta.component.common.picker.base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import one.nem.lacerta.model.LibraryItemPage;
import one.nem.lacerta.model.ListItem;
import one.nem.lacerta.model.ListItemType;

public class LacertaFilePickerAdapterBase extends RecyclerView.Adapter<LacertaFilePickerAdapterBase.LacertaFilePickerViewHolder> {

    // Listener
    public interface LacertaFilePickerAdapterListener {
        void onItemSelected(String dirId);
        void onBackSelected(String dirId);
    }

    protected LibraryItemPage libraryItemPage;

    protected LacertaFilePickerAdapterListener listener;

    // Empty constructor
    public LacertaFilePickerAdapterBase() {
    }

    public void setListItems(LibraryItemPage libraryItemPage) {
        this.libraryItemPage = libraryItemPage;
        if (this.libraryItemPage.getPageId() != null) { // ルートディレクトリの場合は戻るボタンを表示しない
            this.libraryItemPage.getListItems().add(0, new ListItem("戻る", " ", ListItemType.ITEM_TYPE_ACTION_BACK, null));
        }
    }

    public void setListener(LacertaFilePickerAdapterListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public LacertaFilePickerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(one.nem.lacerta.shared.ui.R.layout.common_list_item, parent, false);
        return new LacertaFilePickerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LacertaFilePickerViewHolder holder, int position) {
        ListItem listItem = libraryItemPage.getListItems().get(position);
        holder.title.setText(listItem.getTitle());
        holder.description.setText(listItem.getDescription());
        holder.icon.setImageResource(listItem.getItemType().getIconId());
        if(listItem.getItemType() == ListItemType.ITEM_TYPE_ACTION_BACK) {
            holder.itemView.setOnClickListener(v -> listener.onBackSelected(this.libraryItemPage.getParentId()));
        } else {
            holder.itemView.setOnClickListener(v -> listener.onItemSelected(listItem.getItemId()));
        }
    }

    @Override
    public int getItemCount() {
        return this.libraryItemPage == null ? 0 : this.libraryItemPage.getListItems().size();
    }

    public String getCurrentId() {
        if (this.libraryItemPage == null) {
            return null;
        } else {
            if (this.libraryItemPage.getPageId() == null) {
                return null;
            } else {
                return this.libraryItemPage.getPageId();
            }
        }
    }

    public String getCurrentPageTitle() {
        if (this.libraryItemPage == null) {
            return null;
        } else {
            if (this.libraryItemPage.getPageId() == null) {
                return null;
            } else {
                return this.libraryItemPage.getPageTitle();
            }
        }
    }

    public static class LacertaFilePickerViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView description;

        ImageView icon;


        public LacertaFilePickerViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(one.nem.lacerta.shared.ui.R.id.item_title);
            description = itemView.findViewById(one.nem.lacerta.shared.ui.R.id.item_description);
            icon = itemView.findViewById(one.nem.lacerta.shared.ui.R.id.item_icon);
            description.setVisibility(View.GONE); // 暫定

        }
    }
}
