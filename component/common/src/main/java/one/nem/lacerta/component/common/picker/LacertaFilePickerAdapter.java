package one.nem.lacerta.component.common.picker;

import one.nem.lacerta.component.common.picker.base.LacertaFilePickerAdapterBase;
import one.nem.lacerta.model.ListItem;
import one.nem.lacerta.model.ListItemType;

public class LacertaFilePickerAdapter extends LacertaFilePickerAdapterBase {

    // Listener
    public interface LacertaFilePickerAdapterListener extends LacertaFilePickerAdapterBase.LacertaFilePickerAdapterListener {
        void onDocumentSelected(String documentId);
    }

    // Variables
    LacertaFilePickerAdapterListener listener;

    // Setter
    public LacertaFilePickerAdapter setListener(LacertaFilePickerAdapterListener listener) {
        this.listener = listener;
        return this;
    }

    @Override
    public void onBindViewHolder(LacertaFilePickerViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (libraryItemPage.getListItems().get(position).getItemType() == ListItemType.ITEM_TYPE_DOCUMENT) {
            holder.itemView.setOnClickListener(v -> {
                ListItem listItem = libraryItemPage.getListItems().get(position);
                listener.onDocumentSelected(listItem.getItemId());
            });
        }
    }
}
