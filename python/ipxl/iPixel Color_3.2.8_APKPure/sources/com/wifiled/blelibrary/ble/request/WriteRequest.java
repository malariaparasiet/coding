package com.wifiled.blelibrary.ble.request;

import android.bluetooth.BluetoothGattCharacteristic;
import com.wifiled.blelibrary.ble.Ble;
import com.wifiled.blelibrary.ble.BleRequestImpl;
import com.wifiled.blelibrary.ble.annotation.Implement;
import com.wifiled.blelibrary.ble.callback.BleWriteCallback;
import com.wifiled.blelibrary.ble.callback.BleWriteEntityCallback;
import com.wifiled.blelibrary.ble.callback.wrapper.BleWrapperCallback;
import com.wifiled.blelibrary.ble.callback.wrapper.WriteWrapperCallback;
import com.wifiled.blelibrary.ble.exception.BleWriteException;
import com.wifiled.blelibrary.ble.model.BleDevice;
import com.wifiled.blelibrary.ble.model.EntityData;
import com.wifiled.blelibrary.ble.utils.ThreadUtils;
import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.Callable;

@Implement(WriteRequest.class)
/* loaded from: classes2.dex */
public class WriteRequest<T extends BleDevice> implements WriteWrapperCallback<T> {
    private BleWriteCallback<T> bleWriteCallback;
    private BleWriteEntityCallback<T> bleWriteEntityCallback;
    private boolean isWritingEntity;
    private boolean isAutoWriteMode = false;
    private final Object lock = new Object();
    private BleWrapperCallback<T> bleWrapperCallback = Ble.options().getBleWrapperCallback();

    protected WriteRequest() {
    }

    public boolean write(T t, byte[] bArr, BleWriteCallback<T> bleWriteCallback) {
        this.bleWriteCallback = bleWriteCallback;
        return BleRequestImpl.getBleRequest().writeCharacteristic(t.getBleAddress(), bArr);
    }

    public boolean writeByUuid(T t, byte[] bArr, UUID uuid, UUID uuid2, BleWriteCallback<T> bleWriteCallback) {
        this.bleWriteCallback = bleWriteCallback;
        return BleRequestImpl.getBleRequest().writeCharacteristicByUuid(t.getBleAddress(), bArr, uuid, uuid2);
    }

    public void cancelWriteEntity() {
        if (this.isWritingEntity) {
            this.isWritingEntity = false;
            this.isAutoWriteMode = false;
        }
    }

    public void writeEntity(EntityData entityData, BleWriteEntityCallback<T> bleWriteEntityCallback) {
        EntityData.validParms(entityData);
        this.bleWriteEntityCallback = bleWriteEntityCallback;
        executeEntity(entityData);
    }

    public void writeEntity(T t, byte[] bArr, int i, int i2, BleWriteEntityCallback<T> bleWriteEntityCallback) {
        this.bleWriteEntityCallback = bleWriteEntityCallback;
        if (bArr == null || bArr.length == 0) {
            throw new BleWriteException("Send Entity cannot be empty");
        }
        if (i <= 0) {
            throw new BleWriteException("The data length per packet cannot be less than 0");
        }
        executeEntity(new EntityData(t.getBleAddress(), bArr, i, i2));
    }

    private void executeEntity(EntityData entityData) {
        final boolean isAutoWriteMode = entityData.isAutoWriteMode();
        final byte[] data = entityData.getData();
        final int packLength = entityData.getPackLength();
        final String address = entityData.getAddress();
        final long delay = entityData.getDelay();
        final boolean isLastPackComplete = entityData.isLastPackComplete();
        final BleRequestImpl bleRequest = BleRequestImpl.getBleRequest();
        ThreadUtils.submit(new Callable<Boolean>() { // from class: com.wifiled.blelibrary.ble.request.WriteRequest.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Boolean call() throws Exception {
                WriteRequest.this.isWritingEntity = true;
                WriteRequest.this.isAutoWriteMode = isAutoWriteMode;
                int length = data.length;
                int i = length;
                int i2 = 0;
                while (i2 < length) {
                    if (!WriteRequest.this.isWritingEntity) {
                        if (WriteRequest.this.bleWriteEntityCallback != null) {
                            WriteRequest.this.bleWriteEntityCallback.onWriteCancel();
                            WriteRequest.this.isAutoWriteMode = false;
                        }
                        return false;
                    }
                    int i3 = packLength;
                    if (!isLastPackComplete && i < i3) {
                        i3 = i;
                    }
                    byte[] bArr = new byte[i3];
                    for (int i4 = 0; i4 < i3; i4++) {
                        if (i2 < length) {
                            bArr[i4] = data[i2];
                            i2++;
                        }
                    }
                    i -= i3;
                    if (!bleRequest.writeCharacteristic(address, bArr)) {
                        if (WriteRequest.this.bleWriteEntityCallback != null) {
                            WriteRequest.this.bleWriteEntityCallback.onWriteFailed();
                            WriteRequest.this.isWritingEntity = false;
                            WriteRequest.this.isAutoWriteMode = false;
                            return false;
                        }
                    } else if (WriteRequest.this.bleWriteEntityCallback != null) {
                        WriteRequest.this.bleWriteEntityCallback.onWriteProgress(new BigDecimal(i2 / length).setScale(2, 4).doubleValue());
                    }
                    if (isAutoWriteMode) {
                        synchronized (WriteRequest.this.lock) {
                            WriteRequest.this.lock.wait(500L);
                        }
                    } else {
                        try {
                            Thread.sleep(delay);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                if (WriteRequest.this.bleWriteEntityCallback != null) {
                    WriteRequest.this.bleWriteEntityCallback.onWriteSuccess();
                    WriteRequest.this.isWritingEntity = false;
                    WriteRequest.this.isAutoWriteMode = false;
                }
                return true;
            }
        });
    }

    @Override // com.wifiled.blelibrary.ble.callback.wrapper.WriteWrapperCallback
    public void onWriteSuccess(T t, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        BleWriteCallback<T> bleWriteCallback = this.bleWriteCallback;
        if (bleWriteCallback != null) {
            bleWriteCallback.onWriteSuccess(t, bluetoothGattCharacteristic);
        }
        BleWrapperCallback<T> bleWrapperCallback = this.bleWrapperCallback;
        if (bleWrapperCallback != null) {
            bleWrapperCallback.onWriteSuccess((BleWrapperCallback<T>) t, bluetoothGattCharacteristic);
        }
        if (this.isAutoWriteMode) {
            synchronized (this.lock) {
                this.lock.notify();
            }
        }
    }

    @Override // com.wifiled.blelibrary.ble.callback.wrapper.WriteWrapperCallback
    public void onWriteFailed(T t, int i) {
        BleWriteCallback<T> bleWriteCallback = this.bleWriteCallback;
        if (bleWriteCallback != null) {
            bleWriteCallback.onWriteFailed(t, i);
        }
        BleWrapperCallback<T> bleWrapperCallback = this.bleWrapperCallback;
        if (bleWrapperCallback != null) {
            bleWrapperCallback.onWriteFailed((BleWrapperCallback<T>) t, i);
        }
    }
}
