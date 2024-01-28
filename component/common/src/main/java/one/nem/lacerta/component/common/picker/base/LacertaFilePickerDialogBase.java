package one.nem.lacerta.component.common.picker.base;

import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import javax.inject.Inject;

import one.nem.lacerta.component.common.LacertaSelectDirDialog;
import one.nem.lacerta.component.common.R;
import one.nem.lacerta.component.common.SelectDirDialogItemAdapter;
import one.nem.lacerta.data.LacertaLibrary;
import one.nem.lacerta.model.LibraryItemPage;
import one.nem.lacerta.model.ListItemType;
import one.nem.lacerta.utils.FeatureSwitch;
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
    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPositiveButtonText(String positiveButtonText) {
        this.positiveButtonText = positiveButtonText;
    }

    public void setNegativeButtonText(String negativeButtonText) {
        this.negativeButtonText = negativeButtonText;
    }

    // Methods
    protected void updateList(LacertaFilePickerAdapterBase adapter, LibraryItemPage libraryItemPage, int currentCount, String currentDirId) {
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
