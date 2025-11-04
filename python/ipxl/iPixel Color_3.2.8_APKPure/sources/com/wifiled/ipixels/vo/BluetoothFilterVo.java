package com.wifiled.ipixels.vo;

import java.util.List;

/* loaded from: classes3.dex */
public class BluetoothFilterVo {
    private List<String> bt_device_filter;
    private String bt_device_update_time;

    public String getBt_device_update_time() {
        return this.bt_device_update_time;
    }

    public void setBt_device_update_time(String bt_device_update_time) {
        this.bt_device_update_time = bt_device_update_time;
    }

    public List<String> getBt_device_filter() {
        return this.bt_device_filter;
    }

    public void setBt_device_filter(List<String> bt_device_filter) {
        this.bt_device_filter = bt_device_filter;
    }
}
