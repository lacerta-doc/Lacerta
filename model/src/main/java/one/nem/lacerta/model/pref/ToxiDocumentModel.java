package one.nem.lacerta.model.pref;

public class ToxiDocumentModel {

    // Field
    public String parentDocumentId;

    public String childDocumentId;

    public int order;

    public boolean isActive;

    public String titleCache;

    // Constructor
    public ToxiDocumentModel() {
    }

    public ToxiDocumentModel(String parentDocumentId, String childDocumentId, int order, boolean isActive, String titleCache) {
        this.parentDocumentId = parentDocumentId;
        this.childDocumentId = childDocumentId;
        this.order = order;
        this.isActive = isActive;
        this.titleCache = titleCache;
    }

    // Getter / Setter
    public String getParentDocumentId() {
        return parentDocumentId;
    }

    public void setParentDocumentId(String parentDocumentId) {
        this.parentDocumentId = parentDocumentId;
    }

    public String getChildDocumentId() {
        return childDocumentId;
    }

    public void setChildDocumentId(String childDocumentId) {
        this.childDocumentId = childDocumentId;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getTitleCache() {
        return titleCache;
    }

    public void setTitleCache(String titleCache) {
        this.titleCache = titleCache;
    }
}
