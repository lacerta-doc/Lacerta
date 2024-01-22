package one.nem.lacerta.utils;

public class FeatureSwitch {

    public static class Meta {
        public static boolean canOverrideSwitch = true;
    }

    public static class FeatureMaster {
        public static boolean enableSearch = true;
        public static boolean enableDebugMenu = false;
    }

    public static class Setting {
        public static boolean showDisplayMenu = false;
        public static boolean showDataMenu = false;
        public static boolean showScanMenu = false;
    }
}