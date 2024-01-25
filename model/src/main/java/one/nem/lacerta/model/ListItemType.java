package one.nem.lacerta.model;

public enum ListItemType {

    ITEM_TYPE_FOLDER(one.nem.lacerta.shared.ui.R.drawable.folder_24px),
    ITEM_TYPE_DOCUMENT(one.nem.lacerta.shared.ui.R.drawable.description_24px),
    ITEM_TYPE_ACTION_BACK(one.nem.lacerta.shared.ui.R.drawable.arrow_back_24px);

    private int iconId;

    ListItemType(int iconId) {
        this.iconId = iconId;
    }

    public int getIconId() {
        return iconId;
    }
}
