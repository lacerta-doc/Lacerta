package com.example.lacerta.data.module;

import android.app.Application;

import com.example.lacerta.data.impl.DeviceMetaImpl;
import com.example.lacerta.data.repository.DeviceMeta;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
// Singleton
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
abstract class DeviceMetaModule {

    @Binds
    public abstract DeviceMeta bindDeviceMeta(DeviceMetaImpl deviceMetaImpl);

}
