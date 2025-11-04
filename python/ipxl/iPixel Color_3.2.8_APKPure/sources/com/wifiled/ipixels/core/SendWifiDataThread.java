package com.wifiled.ipixels.core;

import android.util.Log;
import com.wifiled.ipixels.AutoSendParaCount;
import com.wifiled.ipixels.core.send.SendResultCallback;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;

/* loaded from: classes3.dex */
public class SendWifiDataThread extends Thread {
    private static final String TAG = "SendWifiDataThread";
    private boolean bTimeout;
    private volatile boolean isAllDataResponse;
    private volatile boolean isDataSend;
    private volatile boolean isPer12kDataResponse;
    private boolean isSendSuc;
    private volatile boolean isThreadWaiting;
    private volatile boolean isWaitingForCallback;
    private WifiSendTask mCurrentTask;
    private OnThreadStateListener mListener;
    private final LinkedBlockingQueue<WifiSendTask> mQueue;
    private final LinkedBlockingQueue<WifiSendTask> mSave12kDataQueue;
    private final LinkedBlockingQueue<WifiSendTask> mSaveTotalDataQueue;
    private WifiSendTask mSendtTask;
    private TimerTask mTask;
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

    public SendWifiDataThread(OnThreadStateListener listener) {
        super(TAG);
        this.mQueue = new LinkedBlockingQueue<>();
        this.mSave12kDataQueue = new LinkedBlockingQueue<>();
        this.mSaveTotalDataQueue = new LinkedBlockingQueue<>();
        this.isDataSend = false;
        this.isThreadWaiting = false;
        this.isWaitingForCallback = false;
        this.isPer12kDataResponse = true;
        this.isAllDataResponse = true;
        this.retryPer12kDataNum = 0;
        this.retryNum = 0;
        this.mSendtTask = null;
        this.isSendSuc = true;
        this.bTimeout = true;
        this.mTask = new TimerTask() { // from class: com.wifiled.ipixels.core.SendWifiDataThread.2
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                SendWifiDataThread.this.aNotify();
            }
        };
        this.mListener = listener;
    }

    public boolean addSendTask(byte[] data, SendResultCallback callback) {
        return addSendData(data, callback);
    }

    public void wakeupSendThread(WifiSendTask sendTask) {
        WifiSendTask wifiSendTask;
        if (sendTask == null && this.mSendtTask != null) {
            this.mSendtTask = null;
        }
        if (sendTask == null || ((wifiSendTask = this.mCurrentTask) != null && wifiSendTask.equals(sendTask))) {
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

    private boolean addSendData(byte[] data, SendResultCallback callback) {
        boolean z;
        if (!this.isDataSend) {
            return false;
        }
        try {
            this.mQueue.put(new WifiSendTask(data, callback));
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

    public void aNotify() {
        this.isSendSuc = true;
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

    @Override // java.lang.Thread
    public synchronized void start() {
        this.isDataSend = true;
        super.start();
    }

    public synchronized void stopThread() {
        this.isDataSend = false;
        wakeupSendThread(null);
        AutoSendParaCount.INSTANCE.reset();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackResult(WifiSendTask task, boolean result) {
        if (task != null && task.getCallback() != null) {
            task.getCallback().onResult(task.mData);
        } else {
            Log.i(TAG, "getCallback is null.");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0087 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x006a A[SYNTHETIC] */
    @Override // java.lang.Thread, java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void run() {
        /*
            Method dump skipped, instructions count: 319
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wifiled.ipixels.core.SendWifiDataThread.run():void");
    }

    public static class WifiSendTask {
        private SendResultCallback mCallback;
        private byte[] mData;
        private int status = -1;

        public WifiSendTask(byte[] data, SendResultCallback callback) {
            this.mData = data;
            this.mCallback = callback;
        }

        public byte[] getData() {
            return this.mData;
        }

        public void setData(byte[] data) {
            this.mData = data;
        }

        public SendResultCallback getCallback() {
            return this.mCallback;
        }

        public void setCallback(SendResultCallback callback) {
            this.mCallback = callback;
        }

        public int getStatus() {
            return this.status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
