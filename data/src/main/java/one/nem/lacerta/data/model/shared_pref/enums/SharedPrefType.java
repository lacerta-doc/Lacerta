package one.nem.lacerta.data.model.shared_pref.enums;

public enum SharedPrefType {

    COMMON("common"),
    USERDATA("userdata");

    private String tag;

    SharedPrefType(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }
}
