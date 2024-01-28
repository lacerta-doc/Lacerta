package one.nem.lacerta.component.common.model;

import one.nem.lacerta.model.document.tag.DocumentTag;

/**
 * DocumentTagを設定するダイアログで使うための拡張モデル
 * チェックボックスの状態を保持するように
 */
public class DocumentTagApplyTagDialogExtendedModel extends DocumentTag {

    private boolean isChecked;

    public DocumentTagApplyTagDialogExtendedModel(DocumentTag documentTag) {
        super(documentTag.getId(), documentTag.getName(), documentTag.getColor());
    }

    public boolean getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean checked) {
        isChecked = checked;
    }
}
