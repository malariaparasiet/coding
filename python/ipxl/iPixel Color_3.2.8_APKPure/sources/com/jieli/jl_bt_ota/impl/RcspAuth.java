package com.jieli.jl_bt_ota.impl;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.jieli.jl_bt_ota.constant.BluetoothConstant;
import com.jieli.jl_bt_ota.interfaces.JieLiLibLoader;
import com.jieli.jl_bt_ota.util.BluetoothUtil;
import com.jieli.jl_bt_ota.util.CHexConver;
import com.jieli.jl_bt_ota.util.CommonUtil;
import com.jieli.jl_bt_ota.util.JL_Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class RcspAuth {
    private static final int AUTH_RETRY_COUNT = 2;
    private static final long DEFAULT_AUTH_TIMEOUT = 3000;
    private static long DELAY_AUTH_WAITING_TIME = 3000;
    public static final int ERR_AUTH_DATA_CHECK = 40980;
    public static final int ERR_AUTH_DATA_SEND = 40979;
    public static final int ERR_AUTH_DEVICE_TIMEOUT = 40977;
    public static final int ERR_AUTH_USER_STOP = 40978;
    public static final int ERR_NONE = 0;
    private static final int MSG_AUTH_DEVICE_TIMEOUT = 18;
    private static final int MSG_SEND_AUTH_DATA_TIMEOUT = 17;
    public static boolean SUPPORT_RESET_FLAG = false;
    private static final String TAG = "RcspAuth";
    private static volatile boolean mIsLibLoaded = false;
    public static final JieLiLibLoader sLocalLibLoader = new JieLiLibLoader() { // from class: com.jieli.jl_bt_ota.impl.RcspAuth$$ExternalSyntheticLambda0
        @Override // com.jieli.jl_bt_ota.interfaces.JieLiLibLoader
        public final void loadLibrary(String str) {
            System.loadLibrary(str);
        }
    };
    private final boolean isLibInit;
    private final Context mContext;
    private final IRcspAuthOp mIRcspAuthOp;
    private final List<OnRcspAuthListener> mOnRcspAuthListeners = Collections.synchronizedList(new ArrayList());
    private final Map<String, AuthTask> mAuthTaskMap = Collections.synchronizedMap(new HashMap());
    private final Handler mHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.jieli.jl_bt_ota.impl.RcspAuth.1
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i = message.what;
            if (i == 17) {
                BluetoothDevice bluetoothDevice = (BluetoothDevice) message.obj;
                AuthTask authTask = (AuthTask) RcspAuth.this.mAuthTaskMap.get(bluetoothDevice.getAddress());
                if (authTask == null) {
                    return false;
                }
                if (authTask.getRetryNum() < 2) {
                    authTask.setRetryNum(authTask.getRetryNum() + 1);
                    RcspAuth.this.sendAuthDataToDevice(authTask.getDevice(), authTask.getRandomData());
                    RcspAuth.this.mHandler.removeMessages(18);
                    RcspAuth.this.mHandler.sendMessageDelayed(RcspAuth.this.mHandler.obtainMessage(17, bluetoothDevice), RcspAuth.DELAY_AUTH_WAITING_TIME);
                } else {
                    RcspAuth.this.onAuthFailed(bluetoothDevice, RcspAuth.ERR_AUTH_DEVICE_TIMEOUT);
                }
            } else if (i == 18) {
                BluetoothDevice bluetoothDevice2 = (BluetoothDevice) message.obj;
                AuthTask authTask2 = (AuthTask) RcspAuth.this.mAuthTaskMap.get(bluetoothDevice2.getAddress());
                if (authTask2 != null && !authTask2.isAuthDevice()) {
                    RcspAuth.this.onAuthFailed(bluetoothDevice2, RcspAuth.ERR_AUTH_DEVICE_TIMEOUT);
                }
            }
            return true;
        }
    });

    /* JADX INFO: Access modifiers changed from: private */
    static class AuthTask {
        private BluetoothDevice a;
        private boolean b;
        private boolean c;
        private byte[] d;
        private int e;

        private AuthTask() {
        }

        public BluetoothDevice getDevice() {
            return this.a;
        }

        public byte[] getRandomData() {
            return this.d;
        }

        public int getRetryNum() {
            return this.e;
        }

        public boolean isAuthDevice() {
            return this.c;
        }

        public boolean isAuthProgressResult() {
            return this.b;
        }

        public void setAuthDevice(boolean z) {
            this.c = z;
        }

        public void setAuthProgressResult(boolean z) {
            this.b = z;
        }

        public AuthTask setDevice(BluetoothDevice bluetoothDevice) {
            this.a = bluetoothDevice;
            return this;
        }

        public AuthTask setRandomData(byte[] bArr) {
            this.d = bArr;
            return this;
        }

        public void setRetryNum(int i) {
            this.e = i;
        }

        public String toString() {
            return "AuthTask{device=" + this.a + ", isAuthProgressResult=" + this.b + ", isAuthDevice=" + this.c + ", randomData=" + CHexConver.byte2HexStr(this.d) + ", retryNum=" + this.e + '}';
        }
    }

    public interface IRcspAuthOp {
        boolean sendAuthDataToDevice(BluetoothDevice bluetoothDevice, byte[] bArr);
    }

    public interface OnRcspAuthListener {
        void onAuthFailed(BluetoothDevice bluetoothDevice, int i, String str);

        void onAuthSuccess(BluetoothDevice bluetoothDevice);

        void onInitResult(boolean z);
    }

    public RcspAuth(Context context, IRcspAuthOp iRcspAuthOp, OnRcspAuthListener onRcspAuthListener) {
        if (iRcspAuthOp == null) {
            throw new IllegalArgumentException("IRcspAuthOp can not be null.");
        }
        loadLibrariesOnce(null);
        this.isLibInit = nativeInit();
        this.mContext = context;
        this.mIRcspAuthOp = iRcspAuthOp;
        addListener(onRcspAuthListener);
    }

    private String getErrorMsg(int i) {
        switch (i) {
            case ERR_AUTH_DEVICE_TIMEOUT /* 40977 */:
                return "Auth device timeout.";
            case ERR_AUTH_USER_STOP /* 40978 */:
                return "User stop auth device.";
            case ERR_AUTH_DATA_SEND /* 40979 */:
                return "Failed to send data.";
            case ERR_AUTH_DATA_CHECK /* 40980 */:
                return "Check auth data error.";
            default:
                return "";
        }
    }

    private byte[] getResetAuthFlagCmdData() {
        return CHexConver.hexStr2Bytes("FEDCBAC00600020001EF");
    }

    private boolean isValidAuthData(byte[] bArr) {
        byte b;
        return (bArr == null || bArr.length == 0 || ((bArr.length != 5 || bArr[0] != 2) && (bArr.length != 17 || ((b = bArr[0]) != 0 && b != 1)))) ? false : true;
    }

    public static void loadLibrariesOnce(JieLiLibLoader jieLiLibLoader) {
        synchronized (RcspAuth.class) {
            if (!mIsLibLoaded) {
                if (jieLiLibLoader == null) {
                    jieLiLibLoader = sLocalLibLoader;
                }
                jieLiLibLoader.loadLibrary(BluetoothConstant.JL_AUTH);
                mIsLibLoaded = true;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAuthFailed(BluetoothDevice bluetoothDevice, int i) {
        onAuthFailed(bluetoothDevice, i, getErrorMsg(i));
    }

    private void onAuthSuccess(final BluetoothDevice bluetoothDevice) {
        JL_Log.w(TAG, "onAuthSuccess", CommonUtil.formatString("device = %s,  auth ok.", printDeviceInfo(bluetoothDevice)));
        this.mHandler.post(new Runnable() { // from class: com.jieli.jl_bt_ota.impl.RcspAuth$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                RcspAuth.this.m2962lambda$onAuthSuccess$2$comjielijl_bt_otaimplRcspAuth(bluetoothDevice);
            }
        });
        if (bluetoothDevice != null) {
            this.mAuthTaskMap.remove(bluetoothDevice.getAddress());
        }
    }

    private void onInitResult(final boolean z) {
        this.mHandler.post(new Runnable() { // from class: com.jieli.jl_bt_ota.impl.RcspAuth$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                RcspAuth.this.m2963lambda$onInitResult$1$comjielijl_bt_otaimplRcspAuth(z);
            }
        });
    }

    private String printDeviceInfo(BluetoothDevice bluetoothDevice) {
        return BluetoothUtil.printBtDeviceInfo(this.mContext, bluetoothDevice);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean sendAuthDataToDevice(BluetoothDevice bluetoothDevice, byte[] bArr) {
        if (bluetoothDevice == null || bArr == null) {
            return false;
        }
        boolean sendAuthDataToDevice = this.mIRcspAuthOp.sendAuthDataToDevice(bluetoothDevice, bArr);
        JL_Log.i(TAG, "sendAuthDataToDevice", CommonUtil.formatString("device : %s, authData : %s", printDeviceInfo(bluetoothDevice), CHexConver.byte2HexStr(bArr)));
        return sendAuthDataToDevice;
    }

    public static boolean setAuthTimeout(long j) {
        if (j < DEFAULT_AUTH_TIMEOUT) {
            JL_Log.d(TAG, "setAuthTimeout", CommonUtil.formatString("The set time[%d] cannot be less than the minimum time[%d].", Long.valueOf(j), Long.valueOf(DEFAULT_AUTH_TIMEOUT)));
            return false;
        }
        DELAY_AUTH_WAITING_TIME = j;
        return true;
    }

    public void addListener(OnRcspAuthListener onRcspAuthListener) {
        if (onRcspAuthListener != null) {
            this.mOnRcspAuthListeners.add(onRcspAuthListener);
            onRcspAuthListener.onInitResult(this.isLibInit);
        }
    }

    public void destroy() {
        this.mHandler.removeCallbacksAndMessages(null);
        this.mAuthTaskMap.clear();
        this.mOnRcspAuthListeners.clear();
        mIsLibLoaded = false;
    }

    public byte[] getAuthData(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        return getEncryptedAuthData(bArr);
    }

    public byte[] getAuthOkData() {
        return new byte[]{2, 112, 97, 115, 115};
    }

    protected native byte[] getEncryptedAuthData(byte[] bArr);

    protected native byte[] getRandomAuthData();

    public byte[] getRandomData() {
        return getRandomAuthData();
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0064, code lost:
    
        if (sendAuthDataToDevice(r9, getAuthOkData()) != false) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x00a9, code lost:
    
        r10 = com.jieli.jl_bt_ota.impl.RcspAuth.ERR_AUTH_DATA_SEND;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00a6, code lost:
    
        if (sendAuthDataToDevice(r9, r10) != false) goto L37;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void handleAuthData(android.bluetooth.BluetoothDevice r9, byte[] r10) {
        /*
            r8 = this;
            if (r9 == 0) goto L100
            boolean r0 = r8.isValidAuthData(r10)
            if (r0 != 0) goto La
            goto L100
        La:
            java.util.Map<java.lang.String, com.jieli.jl_bt_ota.impl.RcspAuth$AuthTask> r0 = r8.mAuthTaskMap
            java.lang.String r1 = r9.getAddress()
            java.lang.Object r0 = r0.get(r1)
            com.jieli.jl_bt_ota.impl.RcspAuth$AuthTask r0 = (com.jieli.jl_bt_ota.impl.RcspAuth.AuthTask) r0
            if (r0 == 0) goto L100
            boolean r1 = r0.isAuthDevice()
            if (r1 == 0) goto L20
            goto L100
        L20:
            java.lang.String r1 = com.jieli.jl_bt_ota.impl.RcspAuth.TAG
            java.lang.String r2 = r8.printDeviceInfo(r9)
            java.lang.String r3 = com.jieli.jl_bt_ota.util.CHexConver.byte2HexStr(r10)
            java.lang.Object[] r2 = new java.lang.Object[]{r2, r3}
            java.lang.String r3 = "device : %s, data : %s"
            java.lang.String r2 = com.jieli.jl_bt_ota.util.CommonUtil.formatString(r3, r2)
            java.lang.String r3 = "handleAuthData"
            com.jieli.jl_bt_ota.util.JL_Log.d(r1, r3, r2)
            boolean r2 = r0.isAuthProgressResult()
            r4 = 1
            r5 = 17
            r6 = 0
            if (r2 != 0) goto L81
            int r2 = r10.length
            if (r2 != r5) goto L100
            r2 = r10[r6]
            if (r2 == r4) goto L4c
            goto L100
        L4c:
            byte[] r2 = r0.getRandomData()
            byte[] r2 = r8.getAuthData(r2)
            if (r2 == 0) goto L67
            boolean r10 = java.util.Arrays.equals(r2, r10)
            if (r10 == 0) goto L67
            byte[] r10 = r8.getAuthOkData()
            boolean r10 = r8.sendAuthDataToDevice(r9, r10)
            if (r10 == 0) goto La9
            goto La8
        L67:
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r7 = "data not match. authData : "
            r10.<init>(r7)
            java.lang.String r2 = com.jieli.jl_bt_ota.util.CHexConver.byte2HexStr(r2)
            java.lang.StringBuilder r10 = r10.append(r2)
            java.lang.String r10 = r10.toString()
            com.jieli.jl_bt_ota.util.JL_Log.w(r1, r3, r10)
            r10 = 40980(0xa014, float:5.7425E-41)
            goto Lcf
        L81:
            int r2 = r10.length
            if (r2 != r5) goto Lad
            r2 = r10[r6]
            if (r2 != 0) goto Lad
            byte[] r10 = r8.getAuthData(r10)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r7 = "devAuthData : "
            r2.<init>(r7)
            java.lang.String r7 = com.jieli.jl_bt_ota.util.CHexConver.byte2HexStr(r10)
            java.lang.StringBuilder r2 = r2.append(r7)
            java.lang.String r2 = r2.toString()
            com.jieli.jl_bt_ota.util.JL_Log.i(r1, r3, r2)
            boolean r10 = r8.sendAuthDataToDevice(r9, r10)
            if (r10 == 0) goto La9
        La8:
            goto Lce
        La9:
            r10 = 40979(0xa013, float:5.7424E-41)
            goto Lcf
        Lad:
            byte[] r2 = r8.getAuthOkData()
            boolean r10 = java.util.Arrays.equals(r10, r2)
            if (r10 == 0) goto L100
            r0.setAuthDevice(r4)
            r8.onAuthSuccess(r9)
            java.lang.String r10 = r8.printDeviceInfo(r9)
            java.lang.Object[] r10 = new java.lang.Object[]{r10}
            java.lang.String r2 = "device : %s, auth ok."
            java.lang.String r10 = com.jieli.jl_bt_ota.util.CommonUtil.formatString(r2, r10)
            com.jieli.jl_bt_ota.util.JL_Log.w(r1, r3, r10)
        Lce:
            r10 = r6
        Lcf:
            if (r10 != 0) goto Ld2
            goto Ld3
        Ld2:
            r4 = r6
        Ld3:
            r0.setAuthProgressResult(r4)
            if (r10 == 0) goto Le3
            r0.setAuthDevice(r6)
            java.lang.String r0 = r8.getErrorMsg(r10)
            r8.onAuthFailed(r9, r10, r0)
            return
        Le3:
            android.os.Handler r10 = r8.mHandler
            r10.removeMessages(r5)
            android.os.Handler r10 = r8.mHandler
            r1 = 18
            r10.removeMessages(r1)
            boolean r10 = r0.isAuthDevice()
            if (r10 != 0) goto L100
            android.os.Handler r10 = r8.mHandler
            android.os.Message r9 = r10.obtainMessage(r1, r9)
            long r0 = com.jieli.jl_bt_ota.impl.RcspAuth.DELAY_AUTH_WAITING_TIME
            r10.sendMessageDelayed(r9, r0)
        L100:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jieli.jl_bt_ota.impl.RcspAuth.handleAuthData(android.bluetooth.BluetoothDevice, byte[]):void");
    }

    /* renamed from: lambda$onAuthFailed$3$com-jieli-jl_bt_ota-impl-RcspAuth, reason: not valid java name */
    /* synthetic */ void m2961lambda$onAuthFailed$3$comjielijl_bt_otaimplRcspAuth(BluetoothDevice bluetoothDevice, int i, String str) {
        if (this.mOnRcspAuthListeners.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList(this.mOnRcspAuthListeners);
        int size = arrayList.size();
        int i2 = 0;
        while (i2 < size) {
            Object obj = arrayList.get(i2);
            i2++;
            ((OnRcspAuthListener) obj).onAuthFailed(bluetoothDevice, i, str);
        }
    }

    /* renamed from: lambda$onAuthSuccess$2$com-jieli-jl_bt_ota-impl-RcspAuth, reason: not valid java name */
    /* synthetic */ void m2962lambda$onAuthSuccess$2$comjielijl_bt_otaimplRcspAuth(BluetoothDevice bluetoothDevice) {
        if (this.mOnRcspAuthListeners.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList(this.mOnRcspAuthListeners);
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            ((OnRcspAuthListener) obj).onAuthSuccess(bluetoothDevice);
        }
    }

    /* renamed from: lambda$onInitResult$1$com-jieli-jl_bt_ota-impl-RcspAuth, reason: not valid java name */
    /* synthetic */ void m2963lambda$onInitResult$1$comjielijl_bt_otaimplRcspAuth(boolean z) {
        if (this.mOnRcspAuthListeners.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList(this.mOnRcspAuthListeners);
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            ((OnRcspAuthListener) obj).onInitResult(z);
        }
    }

    /* renamed from: lambda$startAuth$0$com-jieli-jl_bt_ota-impl-RcspAuth, reason: not valid java name */
    /* synthetic */ void m2964lambda$startAuth$0$comjielijl_bt_otaimplRcspAuth(BluetoothDevice bluetoothDevice, AuthTask authTask) {
        if (!sendAuthDataToDevice(bluetoothDevice, authTask.getRandomData())) {
            onAuthFailed(bluetoothDevice, ERR_AUTH_DATA_SEND, "Failed to send data.");
            return;
        }
        this.mHandler.removeMessages(17);
        Handler handler = this.mHandler;
        handler.sendMessageDelayed(handler.obtainMessage(17, bluetoothDevice), DELAY_AUTH_WAITING_TIME);
    }

    protected native boolean nativeInit();

    public void removeListener(OnRcspAuthListener onRcspAuthListener) {
        if (onRcspAuthListener != null) {
            this.mOnRcspAuthListeners.remove(onRcspAuthListener);
        }
    }

    public int setDeviceConnectionLinkKey(byte[] bArr) {
        return setLinkKey(bArr);
    }

    protected native int setLinkKey(byte[] bArr);

    public boolean startAuth(final BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            return false;
        }
        if (this.mAuthTaskMap.containsKey(bluetoothDevice.getAddress())) {
            AuthTask authTask = this.mAuthTaskMap.get(bluetoothDevice.getAddress());
            if (authTask != null && (authTask.isAuthDevice() || this.mHandler.hasMessages(18))) {
                JL_Log.i(TAG, "startAuth", "The device has been certified or certification of device is in progress.");
                return true;
            }
            this.mAuthTaskMap.remove(bluetoothDevice.getAddress());
        }
        JL_Log.i(TAG, "startAuth", "device = " + printDeviceInfo(bluetoothDevice));
        final AuthTask randomData = new AuthTask().setDevice(bluetoothDevice).setRandomData(getRandomData());
        this.mAuthTaskMap.put(bluetoothDevice.getAddress(), randomData);
        if (SUPPORT_RESET_FLAG) {
            boolean sendAuthDataToDevice = sendAuthDataToDevice(bluetoothDevice, getResetAuthFlagCmdData());
            if (sendAuthDataToDevice) {
                this.mHandler.postDelayed(new Runnable() { // from class: com.jieli.jl_bt_ota.impl.RcspAuth$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        RcspAuth.this.m2964lambda$startAuth$0$comjielijl_bt_otaimplRcspAuth(bluetoothDevice, randomData);
                    }
                }, 500L);
            }
            return sendAuthDataToDevice;
        }
        boolean sendAuthDataToDevice2 = sendAuthDataToDevice(bluetoothDevice, randomData.getRandomData());
        if (sendAuthDataToDevice2) {
            this.mHandler.removeMessages(17);
            Handler handler = this.mHandler;
            handler.sendMessageDelayed(handler.obtainMessage(17, bluetoothDevice), DELAY_AUTH_WAITING_TIME);
        }
        return sendAuthDataToDevice2;
    }

    public void stopAuth(BluetoothDevice bluetoothDevice) {
        stopAuth(bluetoothDevice, true);
    }

    private void onAuthFailed(final BluetoothDevice bluetoothDevice, final int i, final String str) {
        JL_Log.e(TAG, "onAuthFailed", CommonUtil.formatString("device = %s,  code = %d, message = %s.", bluetoothDevice, Integer.valueOf(i), str));
        this.mHandler.removeMessages(17);
        this.mHandler.removeMessages(18);
        this.mHandler.post(new Runnable() { // from class: com.jieli.jl_bt_ota.impl.RcspAuth$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                RcspAuth.this.m2961lambda$onAuthFailed$3$comjielijl_bt_otaimplRcspAuth(bluetoothDevice, i, str);
            }
        });
        if (bluetoothDevice != null) {
            this.mAuthTaskMap.remove(bluetoothDevice.getAddress());
        }
    }

    public void stopAuth(BluetoothDevice bluetoothDevice, boolean z) {
        if (bluetoothDevice == null) {
            return;
        }
        AuthTask remove = this.mAuthTaskMap.remove(bluetoothDevice.getAddress());
        if (z) {
            if (remove != null) {
                onAuthFailed(bluetoothDevice, ERR_AUTH_USER_STOP);
            }
            this.mHandler.removeMessages(17);
            this.mHandler.removeMessages(18);
        }
    }
}
