package com.wifiled.blelibrary.ble.request;

import com.wifiled.blelibrary.ble.BleRequestImpl;
import com.wifiled.blelibrary.ble.annotation.Implement;
import com.wifiled.blelibrary.ble.callback.BleReadRssiCallback;
import com.wifiled.blelibrary.ble.callback.wrapper.ReadRssiWrapperCallback;
import com.wifiled.blelibrary.ble.model.BleDevice;

@Implement(ReadRssiRequest.class)
/* loaded from: classes2.dex */
public class ReadRssiRequest<T extends BleDevice> implements ReadRssiWrapperCallback<T> {
    private final BleRequestImpl<T> bleRequest = BleRequestImpl.getBleRequest();
    private BleReadRssiCallback<T> readRssiCallback;

    public boolean readRssi(T t, BleReadRssiCallback<T> bleReadRssiCallback) {
        this.readRssiCallback = bleReadRssiCallback;
        BleRequestImpl<T> bleRequestImpl = this.bleRequest;
        if (bleRequestImpl != null) {
            return bleRequestImpl.readRssi(t.getBleAddress());
        }
        return false;
    }

    @Override // com.wifiled.blelibrary.ble.callback.wrapper.ReadRssiWrapperCallback
    public void onReadRssiSuccess(T t, int i) {
        BleReadRssiCallback<T> bleReadRssiCallback = this.readRssiCallback;
        if (bleReadRssiCallback != null) {
            bleReadRssiCallback.onReadRssiSuccess(t, i);
        }
    }
}
