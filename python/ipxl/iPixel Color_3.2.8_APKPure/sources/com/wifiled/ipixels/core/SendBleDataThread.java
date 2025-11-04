package com.wifiled.ipixels.core;

import android.bluetooth.BluetoothDevice;
import android.util.Log;
import com.blankj.utilcode.util.LogUtils;
import com.easysocket.utils.LogUtil;
import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;

/* loaded from: classes3.dex */
public class SendBleDataThread extends Thread {
    private static final String TAG = "SendBleDataThread";
    private boolean bTimeout;
    private volatile boolean isAllDataResponse;
    private volatile boolean isDataSend;
    private volatile boolean isPer12kDataResponse;
    private volatile boolean isThreadWaiting;
    private volatile boolean isWaitingForCallback;
    private BleManager mBleManager;
    private BleSendTask mCurrentTask;
    private OnThreadStateListener mListener;
    private final LinkedBlockingQueue<BleSendTask> mQueue;
    private final LinkedBlockingQueue<BleSendTask> mSave12kDataQueue;
    private final LinkedBlockingQueue<BleSendTask> mSaveTotalDataQueue;
    private BleSendTask mSendtTask;
    private volatile int retryNum;
    private volatile int retryPer12kDataNum;

    public void setPer12kDataResponse(boolean per12kDataResponse) {
        this.isPer12kDataResponse = per12kDataResponse;
    }

    public void setAllDataResponse(boolean allDataResponse) {
        this.isAllDataResponse = allDataResponse;
    }

    public void clear12kDataQueue() {
        this.mSave12kDataQueue.clear();
    }

    public void clearTotalDataQueue() {
        this.mSaveTotalDataQueue.clear();
    }

    public SendBleDataThread(BleManager manager, OnThreadStateListener listener) {
        super(TAG);
        this.mQueue = new LinkedBlockingQueue<>();
        this.mSave12kDataQueue = new LinkedBlockingQueue<>();
        this.mSaveTotalDataQueue = new LinkedBlockingQueue<>();
        this.isDataSend = false;
        this.isThreadWaiting = false;
        this.isWaitingForCallback = false;
        this.isPer12kDataResponse = true;
        this.isAllDataResponse = true;
        this.retryNum = 0;
        this.retryPer12kDataNum = 0;
        this.mSendtTask = null;
        this.bTimeout = true;
        this.mBleManager = manager;
        this.mListener = listener;
    }

    public boolean addSendTask(BluetoothDevice device, UUID serviceUUID, UUID characteristicUUID, byte[] data, OnWriteDataCallback callback) {
        if (this.mBleManager == null || device == null || 1 == device.getType() || serviceUUID == null || characteristicUUID == null || data == null || data.length == 0) {
            LogUtils.vTag("ruis", " return false");
            return false;
        }
        int mBleMtu = this.mBleManager.getMBleMtu();
        int length = data.length;
        int i = length / mBleMtu;
        boolean z = false;
        int i2 = 0;
        while (i2 < i) {
            byte[] bArr = new byte[mBleMtu];
            System.arraycopy(data, i2 * mBleMtu, bArr, 0, mBleMtu);
            i2++;
            z = addSendData(device, serviceUUID, characteristicUUID, bArr, callback);
        }
        int i3 = length % mBleMtu;
        if (i3 == 0) {
            return z;
        }
        byte[] bArr2 = new byte[i3];
        System.arraycopy(data, length - i3, bArr2, 0, i3);
        return addSendData(device, serviceUUID, characteristicUUID, bArr2, callback);
    }

    public void wakeupSendThread(BleSendTask sendTask) {
        BleSendTask bleSendTask;
        if (sendTask == null || ((bleSendTask = this.mCurrentTask) != null && bleSendTask.equals(sendTask))) {
            if (sendTask != null) {
                sendTask.setCallback(this.mCurrentTask.getCallback());
                this.mCurrentTask = sendTask;
            }
            synchronized (this.mQueue) {
                if (this.isThreadWaiting) {
                    if (this.isWaitingForCallback) {
                        this.mQueue.notifyAll();
                    } else {
                        this.mQueue.notify();
                    }
                } else if (this.isWaitingForCallback) {
                    this.mQueue.notify();
                }
            }
        }
    }

    private boolean addSendData(BluetoothDevice device, UUID serviceUUID, UUID characteristicUUID, byte[] data, OnWriteDataCallback callback) {
        boolean z;
        if (!this.isDataSend) {
            return false;
        }
        try {
            this.mQueue.put(new BleSendTask(device, serviceUUID, characteristicUUID, data, callback));
            z = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
            z = false;
        }
        if (z && this.isThreadWaiting && !this.isWaitingForCallback) {
            this.isThreadWaiting = false;
            synchronized (this.mQueue) {
                this.mQueue.notify();
            }
        }
        return z;
    }

    @Override // java.lang.Thread
    public synchronized void start() {
        this.isDataSend = true;
        super.start();
    }

    public synchronized void stopThread() {
        this.isDataSend = false;
        wakeupSendThread(null);
        this.mSendtTask = null;
    }

    private void callbackResult(BleSendTask task, boolean result) {
        if (task != null && task.getCallback() != null) {
            task.getCallback().onBleResult(task.getDevice(), task.getServiceUUID(), task.getCharacteristicUUID(), result, task.getData());
        } else {
            Log.i(TAG, "getCallback is null.");
        }
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        Log.d(TAG, "send ble data thread is started.");
        OnThreadStateListener onThreadStateListener = this.mListener;
        if (onThreadStateListener != null) {
            onThreadStateListener.onStart(getId(), getName());
        }
        if (this.mBleManager != null) {
            synchronized (this.mQueue) {
                while (this.isDataSend) {
                    this.mCurrentTask = null;
                    this.isThreadWaiting = false;
                    this.isWaitingForCallback = false;
                    if (this.mQueue.isEmpty()) {
                        this.isThreadWaiting = true;
                        Log.d(TAG, "queue is empty, so waiting for data");
                        try {
                            this.mQueue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Log.d(TAG, e.toString());
                        }
                    } else {
                        this.mCurrentTask = this.mQueue.peek();
                        if (this.mSendtTask == null) {
                            this.mSendtTask = new BleSendTask(this.mCurrentTask.mDevice, this.mCurrentTask.serviceUUID, this.mCurrentTask.characteristicUUID, null, this.mCurrentTask.getCallback());
                        }
                        BleSendTask bleSendTask = this.mCurrentTask;
                        if (bleSendTask != null) {
                            this.isWaitingForCallback = this.mBleManager.writeDataByBleSync(bleSendTask.mDevice, this.mCurrentTask.getServiceUUID(), this.mCurrentTask.getCharacteristicUUID(), this.mCurrentTask.getData());
                            Log.e(TAG, "data send isWaitingForCallback :" + this.isWaitingForCallback);
                            if (this.isWaitingForCallback) {
                                try {
                                    this.mQueue.wait(BleManager.get().getSEND_DATA_MAX_TIMEOUT());
                                    Log.e(TAG, "#1.0#data send 发送数据最大超时 - 8 秒 :");
                                } catch (InterruptedException e2) {
                                    e2.printStackTrace();
                                    Log.e(TAG, "#1.1#data send 发送数据最大超时 - 8 秒 :" + e2.toString());
                                }
                            } else {
                                this.mCurrentTask.setStatus(-1);
                            }
                            Log.d(TAG, "data send ret :" + this.mCurrentTask.getStatus());
                            if (this.mCurrentTask.getStatus() != 0) {
                                this.retryNum++;
                                if (this.retryNum >= 3) {
                                    callbackResult(this.mCurrentTask, false);
                                    this.retryNum = 0;
                                    this.mQueue.clear();
                                } else if (this.mCurrentTask.getStatus() != -1) {
                                    this.mCurrentTask.setStatus(-1);
                                    try {
                                        sleep(10L);
                                    } catch (InterruptedException e3) {
                                        e3.printStackTrace();
                                    }
                                }
                            } else {
                                LogUtil.d("发送成功");
                                callbackResult(this.mCurrentTask, true);
                            }
                        }
                        BleSendTask poll = this.mQueue.poll();
                        if (poll != null) {
                            this.mSave12kDataQueue.add(poll);
                            this.mSaveTotalDataQueue.add(poll);
                        }
                    }
                }
            }
            this.isWaitingForCallback = false;
            this.isThreadWaiting = false;
            this.mQueue.clear();
            OnThreadStateListener onThreadStateListener2 = this.mListener;
            if (onThreadStateListener2 != null) {
                onThreadStateListener2.onEnd(getId(), getName());
            }
            Log.d(TAG, "send ble data thread exit.");
        }
    }

    public void aNotify() {
        synchronized (this.mQueue) {
            try {
                this.mQueue.notifyAll();
                this.bTimeout = false;
                Log.d(SocketManager.TAG, "#1.1# notifyAll");
            } catch (Exception unused) {
            }
        }
    }

    private boolean aWait() {
        this.bTimeout = true;
        synchronized (this.mQueue) {
            try {
                this.mQueue.wait();
            } catch (Exception unused) {
            }
        }
        return this.bTimeout;
    }

    public static class BleSendTask {
        private UUID characteristicUUID;
        private byte[] data;
        private OnWriteDataCallback mCallback;
        private BluetoothDevice mDevice;
        private UUID serviceUUID;
        private int status = -1;

        public BleSendTask(BluetoothDevice device, UUID serviceUUID, UUID characteristicUUID, byte[] data, OnWriteDataCallback callback) {
            this.mDevice = device;
            this.serviceUUID = serviceUUID;
            this.characteristicUUID = characteristicUUID;
            this.data = data;
            this.mCallback = callback;
        }

        public BluetoothDevice getDevice() {
            return this.mDevice;
        }

        public void setDevice(BluetoothDevice device) {
            this.mDevice = device;
        }

        public UUID getServiceUUID() {
            return this.serviceUUID;
        }

        public void setServiceUUID(UUID serviceUUID) {
            this.serviceUUID = serviceUUID;
        }

        public UUID getCharacteristicUUID() {
            return this.characteristicUUID;
        }

        public void setCharacteristicUUID(UUID characteristicUUID) {
            this.characteristicUUID = characteristicUUID;
        }

        public byte[] getData() {
            return this.data;
        }

        public void setData(byte[] data) {
            this.data = data;
        }

        public OnWriteDataCallback getCallback() {
            return this.mCallback;
        }

        public void setCallback(OnWriteDataCallback callback) {
            this.mCallback = callback;
        }

        public int getStatus() {
            return this.status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String toString() {
            return "BleSendTask{mDevice=" + this.mDevice + ", serviceUUID=" + this.serviceUUID + ", characteristicUUID=" + this.characteristicUUID + ", data=" + Arrays.toString(this.data) + ", status=" + this.status + ", mCallback=" + this.mCallback + '}';
        }

        public int hashCode() {
            BluetoothDevice bluetoothDevice = this.mDevice;
            if (bluetoothDevice != null && this.serviceUUID != null && this.characteristicUUID != null) {
                return bluetoothDevice.hashCode() + this.serviceUUID.hashCode() + this.characteristicUUID.hashCode();
            }
            return super.hashCode();
        }

        public boolean equals(Object obj) {
            if (obj instanceof BleSendTask) {
                BleSendTask bleSendTask = (BleSendTask) obj;
                BluetoothDevice bluetoothDevice = this.mDevice;
                if (bluetoothDevice != null && this.serviceUUID != null && this.characteristicUUID != null && bluetoothDevice.equals(bleSendTask.getDevice()) && this.serviceUUID.equals(bleSendTask.getServiceUUID()) && this.characteristicUUID.equals(bleSendTask.getCharacteristicUUID())) {
                    return true;
                }
            }
            return false;
        }
    }
}
