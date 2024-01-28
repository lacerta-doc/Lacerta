package one.nem.lacerta.component.common.model;

import one.nem.lacerta.model.document.tag.DocumentTag;

// TODO-rca: クラス名が長すぎ

/**
 * DocumentTagを設定するダイアログで使うための拡張モデル
 * チェックボックスの状態を保持するように
 */
public class DocumentTagApplyTagDialogExtendedModel extends DocumentTag {

    private boolean isChecked;

    public DocumentTagApplyTagDialogExtendedModel(DocumentTag documentTag) {
        super(documentTag.getId(), documentTag.getName(), documentTag.getColor());
    }

    public DocumentTagApplyTagDialogExtendedModel(DocumentTag documentTag, boolean isChecked) {
        super(documentTag.getId(), documentTag.getName(), documentTag.getColor());
        this.isChecked = isChecked;
    }

    public boolean getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean checked) {
        isChecked = checked;
    }
}
