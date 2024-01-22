package one.nem.lacerta.utils;

public class FeatureSwitch {

    public static class Meta {
        public static boolean canOverrideSwitch = false;
    }

    public static class RecyclerView {
        public static boolean useSimpleNotifyMethod = true;
    }

    public static class Viewer {
        public static boolean showOriginalImage = false;
        public static int maxImageSize = 1024;
        public static boolean showProgressBarWhenLoading = true;
    }

    public static class FeatureMaster {
        public static boolean enableSearch = false;
        public static boolean enableDebugMenu = true;
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
