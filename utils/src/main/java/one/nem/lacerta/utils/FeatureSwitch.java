package one.nem.lacerta.utils;

public class FeatureSwitch {

    public static class Meta {
        public static boolean canOverrideSwitch = false;
    }

    public static class FeatureMaster {
        public static boolean enableSearch = false;
        public static boolean enableDebugMenu = false;
    }

    public static class Vcs {
        public static boolean disableBranchDisplay = true;
    }

    public static class Setting {
        public static boolean showDisplayMenu = false;
        public static boolean showDataMenu = false;
        public static boolean showScanMenu = false;
    }
}
