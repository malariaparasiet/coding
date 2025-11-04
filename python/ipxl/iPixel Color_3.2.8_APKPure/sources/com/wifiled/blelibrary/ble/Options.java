package com.wifiled.blelibrary.ble;

import android.bluetooth.le.ScanFilter;
import android.content.Context;
import com.wifiled.blelibrary.ble.Ble;
import com.wifiled.blelibrary.ble.callback.wrapper.BleWrapperCallback;
import com.wifiled.blelibrary.ble.model.BleDevice;
import com.wifiled.blelibrary.ble.model.BleFactory;
import com.wifiled.ipixels.view.video_clip.VideoTrimmerUtil;
import java.util.UUID;

/* loaded from: classes2.dex */
public class Options {
    private BleWrapperCallback bleWrapperCallback;
    private BleFactory factory;
    public ScanFilter scanFilter;
    public boolean logBleEnable = true;
    public String logTAG = "AndroidBLE";
    public boolean throwBleException = true;
    public boolean autoConnect = false;
    public long connectTimeout = VideoTrimmerUtil.MAX_SHOOT_DURATION;
    public long scanPeriod = VideoTrimmerUtil.MAX_SHOOT_DURATION;
    public int serviceBindFailedRetryCount = 3;
    public int connectFailedRetryCount = 3;
    public int maxConnectNum = 7;
    public boolean isIgnoreRepeat = false;
    public boolean isParseScanData = false;
    public int manufacturerId = 65520;
    UUID[] uuid_services_extra = new UUID[0];
    UUID uuid_service = UUID.fromString("0000fee9-0000-1000-8000-00805f9b34fb");
    UUID uuid_wifi_service = UUID.fromString("000000fa-0000-1000-8000-00805f9b34fb");
    UUID uuid_write_cha = UUID.fromString("d44bc439-abfd-45a2-b575-925416129600");
    UUID uuid_read_cha = UUID.fromString("d44bc439-abfd-45a2-b575-925416129600");
    UUID uuid_notify_cha = UUID.fromString("d44bc439-abfd-45a2-b575-925416129601");
    UUID uuid_notify_desc = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb");
    UUID uuid_ota_service = UUID.fromString("0000fee8-0000-1000-8000-00805f9b34fb");
    UUID uuid_ota_notify_cha = UUID.fromString("003784cf-f7e3-55b4-6c4c-9fd140100a16");
    UUID uuid_ota_write_cha = UUID.fromString("013784cf-f7e3-55b4-6c4c-9fd140100a16");
    UUID uuid_jlota_service = UUID.fromString("0000ae00-0000-1000-8000-00805f9b34fb");

    public Options setScanPeriod(long j) {
        this.scanPeriod = j;
        return this;
    }

    public String getLogTAG() {
        return this.logTAG;
    }

    public Options setLogTAG(String str) {
        this.logTAG = str;
        return this;
    }

    public boolean isLogBleEnable() {
        return this.logBleEnable;
    }

    public Options setLogBleEnable(boolean z) {
        this.logBleEnable = z;
        return this;
    }

    public boolean isThrowBleException() {
        return this.throwBleException;
    }

    public Options setThrowBleException(boolean z) {
        this.throwBleException = z;
        return this;
    }

    public boolean isAutoConnect() {
        return this.autoConnect;
    }

    public Options setAutoConnect(boolean z) {
        this.autoConnect = z;
        return this;
    }

    public long getConnectTimeout() {
        return this.connectTimeout;
    }

    public Options setConnectTimeout(long j) {
        this.connectTimeout = j;
        return this;
    }

    public long getScanPeriod() {
        return this.scanPeriod;
    }

    public int getServiceBindFailedRetryCount() {
        return this.serviceBindFailedRetryCount;
    }

    public Options setServiceBindFailedRetryCount(int i) {
        this.serviceBindFailedRetryCount = i;
        return this;
    }

    public int getConnectFailedRetryCount() {
        return this.connectFailedRetryCount;
    }

    public Options setConnectFailedRetryCount(int i) {
        this.connectFailedRetryCount = i;
        return this;
    }

    public int getMaxConnectNum() {
        return this.maxConnectNum;
    }

    public Options setMaxConnectNum(int i) {
        this.maxConnectNum = i;
        return this;
    }

    public boolean isIgnoreRepeat() {
        return this.isIgnoreRepeat;
    }

    public Options setIgnoreRepeat(boolean z) {
        this.isIgnoreRepeat = z;
        return this;
    }

    public ScanFilter getScanFilter() {
        return this.scanFilter;
    }

    public Options setScanFilter(ScanFilter scanFilter) {
        this.scanFilter = scanFilter;
        return this;
    }

    public boolean isParseScanData() {
        return this.isParseScanData;
    }

    public Options setParseScanData(boolean z) {
        this.isParseScanData = z;
        return this;
    }

    public int getManufacturerId() {
        return this.manufacturerId;
    }

    public Options setManufacturerId(int i) {
        this.manufacturerId = i;
        return this;
    }

    public BleWrapperCallback getBleWrapperCallback() {
        return this.bleWrapperCallback;
    }

    public Options setBleWrapperCallback(BleWrapperCallback bleWrapperCallback) {
        this.bleWrapperCallback = bleWrapperCallback;
        return this;
    }

    public BleFactory getFactory() {
        if (this.factory == null) {
            this.factory = new BleFactory() { // from class: com.wifiled.blelibrary.ble.Options.1
                @Override // com.wifiled.blelibrary.ble.model.BleFactory
                public BleDevice create(String str, String str2) {
                    return super.create(str, str2);
                }
            };
        }
        return this.factory;
    }

    public Options setFactory(BleFactory bleFactory) {
        this.factory = bleFactory;
        return this;
    }

    public UUID[] getUuidServicesExtra() {
        return this.uuid_services_extra;
    }

    public Options setUuidServicesExtra(UUID[] uuidArr) {
        this.uuid_services_extra = uuidArr;
        return this;
    }

    public UUID getUuidService() {
        return this.uuid_service;
    }

    public Options setUuidService(UUID uuid) {
        this.uuid_service = uuid;
        return this;
    }

    public UUID getUuidWriteCha() {
        return this.uuid_write_cha;
    }

    public Options setUuidWriteCha(UUID uuid) {
        this.uuid_write_cha = uuid;
        return this;
    }

    public UUID getUuidReadCha() {
        return this.uuid_read_cha;
    }

    public Options setUuidReadCha(UUID uuid) {
        this.uuid_read_cha = uuid;
        return this;
    }

    public UUID getUuidNotifyCha() {
        return this.uuid_notify_cha;
    }

    public Options setUuidNotifyCha(UUID uuid) {
        this.uuid_notify_cha = uuid;
        return this;
    }

    public UUID getUuidNotifyDesc() {
        return this.uuid_notify_desc;
    }

    public Options setUuidNotifyDesc(UUID uuid) {
        this.uuid_notify_desc = uuid;
        return this;
    }

    public UUID getUuidOtaService() {
        return this.uuid_ota_service;
    }

    public Options setUuidOtaService(UUID uuid) {
        this.uuid_ota_service = uuid;
        return this;
    }

    public UUID getUuidOtaNotifyCha() {
        return this.uuid_ota_notify_cha;
    }

    public Options setUuidOtaNotifyCha(UUID uuid) {
        this.uuid_ota_notify_cha = uuid;
        return this;
    }

    public UUID getUuidOtaWriteCha() {
        return this.uuid_ota_write_cha;
    }

    public Options setUuidOtaWriteCha(UUID uuid) {
        this.uuid_ota_write_cha = uuid;
        return this;
    }

    public <T extends BleDevice> Ble<T> create(Context context) {
        return create(context, null);
    }

    public <T extends BleDevice> Ble<T> create(Context context, Ble.InitCallback initCallback) {
        return Ble.create(context, initCallback);
    }
}
