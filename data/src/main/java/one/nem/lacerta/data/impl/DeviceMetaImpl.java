package one.nem.lacerta.data.impl;

import android.os.Build;

import one.nem.lacerta.data.repository.DeviceMeta;
import one.nem.lacerta.data.model.DeviceMetaModel;

import javax.inject.Inject;

public class DeviceMetaImpl implements DeviceMeta{

    @Inject
    public DeviceMetaImpl() {
    }

    public DeviceMetaModel getDeviceMeta() {
        DeviceMetaModel deviceMetaModel = new DeviceMetaModel(
                Build.MANUFACTURER,
                Build.MODEL,
                Build.VERSION.RELEASE,
                Build.VERSION.SDK_INT
        );
        return deviceMetaModel;
    }

}
