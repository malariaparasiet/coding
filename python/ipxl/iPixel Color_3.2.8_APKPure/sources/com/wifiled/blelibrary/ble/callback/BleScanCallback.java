package com.wifiled.blelibrary.ble.callback;

import com.wifiled.blelibrary.ble.model.ScanRecord;

/* loaded from: classes2.dex */
public abstract class BleScanCallback<T> {
    public abstract void onLeScan(T t, int i, byte[] bArr);

    public void onParsedData(T t, ScanRecord scanRecord) {
    }

    public void onScanFailed(int i) {
    }

    public void onStart() {
    }

    public void onStop() {
    }
}
