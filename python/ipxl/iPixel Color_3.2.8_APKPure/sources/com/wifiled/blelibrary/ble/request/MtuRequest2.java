package com.wifiled.blelibrary.ble.request;

import com.wifiled.blelibrary.ble.Ble2;
import com.wifiled.blelibrary.ble.BleRequestImpl2;
import com.wifiled.blelibrary.ble.annotation.Implement;
import com.wifiled.blelibrary.ble.callback.BleMtuCallback;
import com.wifiled.blelibrary.ble.callback.wrapper.BleWrapperCallback;
import com.wifiled.blelibrary.ble.callback.wrapper.MtuWrapperCallback;
import com.wifiled.blelibrary.ble.model.BleDevice;

@Implement(MtuRequest2.class)
/* loaded from: classes2.dex */
public class MtuRequest2<T extends BleDevice> implements MtuWrapperCallback<T> {
    private BleMtuCallback<T> bleMtuCallback;
    private final BleWrapperCallback<T> bleWrapperCallback = Ble2.options().getBleWrapperCallback();
    private final BleRequestImpl2<T> bleRequest = BleRequestImpl2.getBleRequest();

    public boolean setMtu(String str, int i, BleMtuCallback<T> bleMtuCallback) {
        this.bleMtuCallback = bleMtuCallback;
        return this.bleRequest.setMtu(str, i);
    }

    @Override // com.wifiled.blelibrary.ble.callback.wrapper.MtuWrapperCallback
    public void onMtuChanged(T t, int i, int i2) {
        BleMtuCallback<T> bleMtuCallback = this.bleMtuCallback;
        if (bleMtuCallback != null) {
            bleMtuCallback.onMtuChanged(t, i, i2);
        }
        BleWrapperCallback<T> bleWrapperCallback = this.bleWrapperCallback;
        if (bleWrapperCallback != null) {
            bleWrapperCallback.onMtuChanged((BleWrapperCallback<T>) t, i, i2);
        }
    }
}
