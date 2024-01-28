package one.nem.lacerta.component.common.picker.base;

import androidx.fragment.app.DialogFragment;

import javax.inject.Inject;

import one.nem.lacerta.model.LibraryItemPage;
import one.nem.lacerta.utils.LacertaLogger;

public class LacertaFilePickerDialogBase extends DialogFragment {
    @Inject
    LacertaLogger logger;

    // Variables
    protected String title;
    protected String message;
    protected String positiveButtonText;
    protected String negativeButtonText;

    // Setter
    public LacertaFilePickerDialogBase setTitle(String title) {
        this.title = title;
        return this;
    }

    public LacertaFilePickerDialogBase setMessage(String message) {
        this.message = message;
        return this;
    }

    public LacertaFilePickerDialogBase setPositiveButtonText(String positiveButtonText) {
        this.positiveButtonText = positiveButtonText;
        return this;
    }

    public LacertaFilePickerDialogBase setNegativeButtonText(String negativeButtonText) {
        this.negativeButtonText = negativeButtonText;
        return this;
    }

    // Methods
    protected void updateListView(LacertaFilePickerAdapterBase adapter, LibraryItemPage libraryItemPage, int currentCount, String currentDirId) {
        if (currentDirId == null) {
            // Rootが関わる推移 (Rootからの推移)
            adapter.setListItems(libraryItemPage);
            adapter.notifyItemRangeRemoved(0, currentCount);
            adapter.notifyItemRangeInserted(0, libraryItemPage.getListItems().size());
        } else if (libraryItemPage.getPageId() == null) {
            // Rootが関わる推移 (Rootへの推移)
            adapter.setListItems(libraryItemPage);
            adapter.notifyItemRangeRemoved(0, currentCount);
            adapter.notifyItemRangeInserted(0, libraryItemPage.getListItems().size());
        } else if (libraryItemPage.getPageId() != null) {
            // Rootが関わらない推移
            adapter.setListItems(libraryItemPage);
            adapter.notifyItemRangeRemoved(1, currentCount);
            adapter.notifyItemRangeInserted(1, libraryItemPage.getListItems().size());
        } else {
            // その他の遷移(安全側に倒すため全アイテム更新)
            logger.warn("FilePickerDialogBase", "Unknown transition.");
            logger.warn("FilePickerDialogBase", "currentDirId: " + currentDirId + ", libraryItemPage.getPageId(): " + libraryItemPage.getPageId());
            adapter.setListItems(libraryItemPage);
            adapter.notifyItemRangeRemoved(0, currentCount);
            adapter.notifyItemRangeInserted(0, libraryItemPage.getListItems().size());
        }
    }
}
