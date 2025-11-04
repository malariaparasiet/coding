package com.wifiled.baselib.retrofit.entity;

import java.util.List;

/* loaded from: classes2.dex */
public class BluetoothFilterVo {
    private List<String> bt_device_filter;
    private String bt_device_update_time;

    public String getBt_device_update_time() {
        return this.bt_device_update_time;
    }

    public void setBt_device_update_time(String str) {
        this.bt_device_update_time = str;
    }

    public List<String> getBt_device_filter() {
        return this.bt_device_filter;
    }

    public void setBt_device_filter(List<String> list) {
        this.bt_device_filter = list;
    }
}
