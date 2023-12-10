package one.nem.lacerta.data.impl;

import one.nem.lacerta.data.repository.DeviceMeta;

import javax.inject.Inject;

public class DeviceMetaImpl implements DeviceMeta{

    @Inject
    public DeviceMetaImpl() {
    }

    public String getDeviceId() {
        return "device_id"; // DEBUG
    }

    public String getDeviceApiLevel() {
        return "device_api_level"; // DEBUG
    }

}
