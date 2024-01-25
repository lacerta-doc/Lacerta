package one.nem.lacerta.setting.model;

public class TagListItem {

    String tagId;
    String tagName;
    String tagColor;

    public TagListItem(String tagId, String tagName, String tagColor) {
        this.tagId = tagId;
        this.tagName = tagName;
        this.tagColor = tagColor;
    }

    public TagListItem() {
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTagColor() {
        return tagColor;
    }

    public void setTagColor(String tagColor) {
        this.tagColor = tagColor;
    }
}
