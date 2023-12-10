package one.nem.lacerta.data.model;

public class DeviceMetaModel {
    private String DeviceManufacturer;
    private String DeviceModel;
    private String AndroidVersion;
    private int AndroidApiLevel;

    public DeviceMetaModel(String DeviceManufacturer, String DeviceModel, String AndroidVersion, int AndroidApiLevel) {
        this.DeviceManufacturer = DeviceManufacturer;
        this.DeviceModel = DeviceModel;
        this.AndroidVersion = AndroidVersion;
        this.AndroidApiLevel = AndroidApiLevel;
    }

    // Getters
    // TODO-rca: ボイラープレートコードなので削減する

    public String getDeviceManufacturer() {
        return DeviceManufacturer;
    }

    public String getDeviceModel() {
        return DeviceModel;
    }

    public String getAndroidVersion() {
        return AndroidVersion;
    }

    public int getAndroidApiLevel() {
        return AndroidApiLevel;
    }
}
