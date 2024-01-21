package one.nem.lacerta.model.pref;

public enum FeatureSwitchOverride {

    ENABLE_DEBUG_MENU("enableDebugMenu"),
    ENABLE_SEARCH("enableSearch"),
    SHOW_DISPLAY_MENU("showDisplayMenu"),
    SHOW_DATA_MENU("showDataMenu"),
    SHOW_SCAN_MENU("showScanMenu");

    private final String key;

    FeatureSwitchOverride(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public static FeatureSwitchOverride fromKey(String key) {
        for (FeatureSwitchOverride value : values()) {
            if (value.getKey().equals(key)) {
                return value;
            }
        }
        return null;
    }

}
