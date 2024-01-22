package one.nem.lacerta.utils;

public class FeatureSwitch {

    public static class Meta {
        public static boolean canOverrideSwitch = false;
    }

    public static class FeatureMaster {
        public static boolean enableSearch = true;
        public static boolean enableDebugMenu = true;
    }

    public static class Setting {
        public static boolean showDisplayMenu = false;
        public static boolean showDataMenu = false;
        public static boolean showScanMenu = false;
    }
}
