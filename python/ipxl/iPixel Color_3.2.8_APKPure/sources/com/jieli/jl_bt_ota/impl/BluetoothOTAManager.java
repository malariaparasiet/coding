package com.jieli.jl_bt_ota.impl;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.jieli.jl_bt_ota.constant.ErrorCode;
import com.jieli.jl_bt_ota.constant.JL_Constant;
import com.jieli.jl_bt_ota.constant.StateCode;
import com.jieli.jl_bt_ota.impl.RcspAuth;
import com.jieli.jl_bt_ota.interfaces.IActionCallback;
import com.jieli.jl_bt_ota.interfaces.IUpgradeCallback;
import com.jieli.jl_bt_ota.model.DataInfo;
import com.jieli.jl_bt_ota.model.FileOffset;
import com.jieli.jl_bt_ota.model.OTAError;
import com.jieli.jl_bt_ota.model.ReConnectDevMsg;
import com.jieli.jl_bt_ota.model.ReconnectParam;
import com.jieli.jl_bt_ota.model.base.BaseError;
import com.jieli.jl_bt_ota.model.base.BasePacket;
import com.jieli.jl_bt_ota.model.base.CommandBase;
import com.jieli.jl_bt_ota.model.command.EnterUpdateModeCmd;
import com.jieli.jl_bt_ota.model.command.ExitUpdateModeCmd;
import com.jieli.jl_bt_ota.model.command.FirmwareUpdateBlockCmd;
import com.jieli.jl_bt_ota.model.command.NotifyUpdateContentSizeCmd;
import com.jieli.jl_bt_ota.model.command.SettingsMtuCmd;
import com.jieli.jl_bt_ota.model.parameter.FirmwareUpdateBlockParam;
import com.jieli.jl_bt_ota.model.parameter.FirmwareUpdateBlockResponseParam;
import com.jieli.jl_bt_ota.model.parameter.NotifyUpdateContentSizeParam;
import com.jieli.jl_bt_ota.model.parameter.SettingsMtuParam;
import com.jieli.jl_bt_ota.model.response.EnterUpdateModeResponse;
import com.jieli.jl_bt_ota.model.response.ExitUpdateModeResponse;
import com.jieli.jl_bt_ota.model.response.SettingsMtuResponse;
import com.jieli.jl_bt_ota.model.response.TargetInfoResponse;
import com.jieli.jl_bt_ota.thread.ReadFileThread;
import com.jieli.jl_bt_ota.tool.DataHandler;
import com.jieli.jl_bt_ota.tool.DataHandlerModify;
import com.jieli.jl_bt_ota.tool.DeviceReConnectManager;
import com.jieli.jl_bt_ota.tool.ParseHelper;
import com.jieli.jl_bt_ota.tool.RcspOTA;
import com.jieli.jl_bt_ota.tool.UpgradeCbHelper;
import com.jieli.jl_bt_ota.util.BluetoothUtil;
import com.jieli.jl_bt_ota.util.CHexConver;
import com.jieli.jl_bt_ota.util.CommonUtil;
import com.jieli.jl_bt_ota.util.FileUtil;
import com.jieli.jl_bt_ota.util.JL_Log;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes2.dex */
public abstract class BluetoothOTAManager extends BluetoothBreProfiles implements RcspAuth.IRcspAuthOp {
    private static final int A = 4663;
    private static final int B = 4664;
    private static final int C = 4665;
    private static final int D = 4672;
    private static final int E = 4673;
    private static final int F = 4674;
    public static long FILE_CACHE_DATA_LIMIT = 2097152;
    public static boolean IS_SUPPORT_NEW_RECONNECT_WAY = true;
    public static boolean IS_USE_MODIFY_DATA_HANDLER = true;
    private static final long u = 6000;
    private static final long v = 1000;
    private static final long w = 5000;
    private static final int x = 4660;
    private static final int y = 4661;
    private static final int z = 4662;
    private final RcspOTA G;
    private final DeviceReConnectManager H;
    private final RcspAuth I;
    private final UpgradeCbHelper J;
    private ExecutorService K;
    private volatile boolean L;
    private volatile byte[] M;
    private volatile RandomAccessFile N;
    private long O;
    private long P;
    private long Q;
    private int R;
    private int S;
    private ReconnectParam T;
    private final Handler U;
    private final RcspAuth.OnRcspAuthListener V;

    public BluetoothOTAManager(Context context) {
        super(context);
        this.L = false;
        this.O = 20000L;
        this.P = 0L;
        this.Q = 0L;
        this.R = 0;
        this.S = 0;
        this.U = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.jieli.jl_bt_ota.impl.BluetoothOTAManager.1
            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message message) {
                int i = message.what;
                if (i == BluetoothOTAManager.B) {
                    BluetoothDevice bluetoothDevice = (BluetoothDevice) message.obj;
                    JL_Log.w(BluetoothOTAManager.this.TAG, "MSG_WAIT_EDR_DISCONNECT", "sppDevice :" + BluetoothOTAManager.this.printBtDeviceInfo(bluetoothDevice));
                    BluetoothOTAManager.this.h(bluetoothDevice);
                } else if (i != BluetoothOTAManager.C) {
                    switch (i) {
                        case BluetoothOTAManager.x /* 4660 */:
                            BluetoothOTAManager.this.a("RECEIVE_CMD_TIMEOUT", OTAError.buildError(ErrorCode.SUB_ERR_WAITING_COMMAND_TIMEOUT));
                            break;
                        case BluetoothOTAManager.y /* 4661 */:
                            BluetoothDevice bluetoothDevice2 = (BluetoothDevice) message.obj;
                            JL_Log.i(BluetoothOTAManager.this.TAG, "MSG_CHANGE_BLE_MTU_TIMEOUT", "device : " + BluetoothOTAManager.this.printBtDeviceInfo(bluetoothDevice2));
                            if (!BluetoothOTAManager.this.i(bluetoothDevice2)) {
                                BluetoothOTAManager.this.c(bluetoothDevice2, 2);
                                break;
                            } else {
                                BluetoothOTAManager.this.b(bluetoothDevice2, 0);
                                break;
                            }
                        case BluetoothOTAManager.z /* 4662 */:
                            boolean isOTA = BluetoothOTAManager.this.isOTA();
                            BluetoothDevice connectedBtDevice = BluetoothOTAManager.this.getConnectedBtDevice();
                            boolean isConnectedDevice = BluetoothOTAManager.this.isConnectedDevice(connectedBtDevice);
                            boolean isWaitingForUpdate = BluetoothOTAManager.this.H.isWaitingForUpdate();
                            ReconnectParam reconnectParam = BluetoothOTAManager.this.T;
                            BluetoothOTAManager bluetoothOTAManager = BluetoothOTAManager.this;
                            JL_Log.i(bluetoothOTAManager.TAG, "MSG_OTA_RECONNECT_DEVICE", CommonUtil.formatString("device : %s, isOTA = %s, isWaitingForUpdate = %s, isConnectedDevice = %s\n %s", bluetoothOTAManager.printBtDeviceInfo(connectedBtDevice), Boolean.valueOf(isOTA), Boolean.valueOf(isWaitingForUpdate), Boolean.valueOf(isConnectedDevice), reconnectParam));
                            if (isOTA && isWaitingForUpdate) {
                                if (!isConnectedDevice && reconnectParam != null) {
                                    BluetoothOTAManager bluetoothOTAManager2 = BluetoothOTAManager.this;
                                    bluetoothOTAManager2.b(bluetoothOTAManager2.H.getReconnectAddress(), reconnectParam.getFlag() == 1);
                                    if (BluetoothOTAManager.this.getBluetoothOption().isUseReconnect()) {
                                        BluetoothOTAManager.this.U.sendEmptyMessageDelayed(BluetoothOTAManager.E, DeviceReConnectManager.RECONNECT_TIMEOUT);
                                    } else {
                                        BluetoothOTAManager.this.H.startReconnectTask();
                                    }
                                    BluetoothOTAManager.this.a((ReconnectParam) null);
                                    break;
                                } else {
                                    JL_Log.w(BluetoothOTAManager.this.TAG, "MSG_OTA_RECONNECT_DEVICE", "Is the implementation of getConnectedBtDevice() incorrect?");
                                    BluetoothOTAManager.this.onBtDeviceConnection(connectedBtDevice, 1);
                                    break;
                                }
                            }
                            break;
                        default:
                            switch (i) {
                                case BluetoothOTAManager.D /* 4672 */:
                                    JL_Log.d(BluetoothOTAManager.this.TAG, "MSG_CALLBACK_OTA_FINISH", "---->");
                                    BluetoothOTAManager.this.g();
                                    break;
                                case BluetoothOTAManager.E /* 4673 */:
                                    BluetoothOTAManager.this.a("OTA_RECONNECT_DEVICE_TIMEOUT", OTAError.buildError(ErrorCode.SUB_ERR_RECONNECT_TIMEOUT));
                                    break;
                                case BluetoothOTAManager.F /* 4674 */:
                                    Object obj = message.obj;
                                    if (!(obj instanceof BluetoothDevice)) {
                                        return false;
                                    }
                                    BluetoothDevice bluetoothDevice3 = (BluetoothDevice) obj;
                                    JL_Log.d(BluetoothOTAManager.this.TAG, "MSG_DISCONNECT_DEVICE_TIMEOUT", "device : " + bluetoothDevice3);
                                    BluetoothOTAManager.this.onBtDeviceConnection(bluetoothDevice3, 0);
                                    break;
                            }
                    }
                } else {
                    JL_Log.w(BluetoothOTAManager.this.TAG, "MSG_WAIT_DEVICE_DISCONNECT", "---->");
                    BluetoothOTAManager bluetoothOTAManager3 = BluetoothOTAManager.this;
                    bluetoothOTAManager3.a(bluetoothOTAManager3.getConnectedBtDevice(), BluetoothOTAManager.this.T);
                }
                return true;
            }
        });
        RcspAuth.OnRcspAuthListener onRcspAuthListener = new RcspAuth.OnRcspAuthListener() { // from class: com.jieli.jl_bt_ota.impl.BluetoothOTAManager.14
            @Override // com.jieli.jl_bt_ota.impl.RcspAuth.OnRcspAuthListener
            public void onAuthFailed(BluetoothDevice bluetoothDevice, int i, String str) {
                BluetoothOTAManager bluetoothOTAManager = BluetoothOTAManager.this;
                JL_Log.w(bluetoothOTAManager.TAG, "onAuthFailed", CommonUtil.formatString("device : %s, code : %d, message : %s", bluetoothOTAManager.printBtDeviceInfo(bluetoothDevice), Integer.valueOf(i), str));
                BluetoothOTAManager.this.mDeviceStatusCache.updateDeviceIsAuth(bluetoothDevice, false);
                if (BluetoothOTAManager.this.H.isDeviceReconnecting()) {
                    BluetoothOTAManager.this.c(bluetoothDevice, 2);
                } else {
                    BluetoothOTAManager.this.a(bluetoothDevice, OTAError.buildError(ErrorCode.SUB_ERR_AUTH_DEVICE, i, str));
                }
            }

            @Override // com.jieli.jl_bt_ota.impl.RcspAuth.OnRcspAuthListener
            public void onAuthSuccess(BluetoothDevice bluetoothDevice) {
                BluetoothOTAManager.this.mDeviceStatusCache.updateDeviceIsAuth(bluetoothDevice, true);
                boolean i = BluetoothOTAManager.this.i(bluetoothDevice);
                BluetoothOTAManager bluetoothOTAManager = BluetoothOTAManager.this;
                JL_Log.w(bluetoothOTAManager.TAG, "onAuthSuccess", CommonUtil.formatString("device[%s] auth ok, isBleConnected = %s", bluetoothOTAManager.printBtDeviceInfo(bluetoothDevice), Boolean.valueOf(i)));
                if (i) {
                    BluetoothOTAManager.this.l(bluetoothDevice);
                } else {
                    BluetoothOTAManager.this.b(bluetoothDevice, 1);
                }
            }

            @Override // com.jieli.jl_bt_ota.impl.RcspAuth.OnRcspAuthListener
            public void onInitResult(boolean z2) {
                JL_Log.d(BluetoothOTAManager.this.TAG, "onInitResult", "result : " + z2);
            }
        };
        this.V = onRcspAuthListener;
        JL_Log.i(this.TAG, "init", String.format(Locale.ENGLISH, "Lib version name = %s(%d)", JL_Constant.getLibVersionName(), Integer.valueOf(JL_Constant.getLibVersionCode())));
        this.G = new RcspOTA(this);
        this.H = new DeviceReConnectManager(context, this);
        this.I = new RcspAuth(context, this, onRcspAuthListener);
        this.J = new UpgradeCbHelper();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            return;
        }
        if (this.U.hasMessages(y)) {
            JL_Log.w(this.TAG, "startChangeMtu", "Adjusting the MTU for BLE");
            return;
        }
        boolean d = (!this.mBluetoothOption.isNeedChangeMtu() || this.mBluetoothOption.getMtu() <= 20) ? false : d(bluetoothDevice, this.mBluetoothOption.getMtu());
        JL_Log.d(this.TAG, "startChangeMtu", "requestBleMtu : " + d);
        if (!d) {
            b(bluetoothDevice, 0);
        } else {
            Handler handler = this.U;
            handler.sendMessageDelayed(handler.obtainMessage(y, bluetoothDevice), 5000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void m() {
        this.J.setUpgradeCallback(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void n() {
        a("startReadFileThread", OTAError.buildError(ErrorCode.SUB_ERR_FILE_NOT_FOUND));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        if (this.U.hasMessages(D)) {
            this.U.removeMessages(D);
            this.U.sendEmptyMessage(D);
        }
    }

    private void p() {
        JL_Log.w(this.TAG, "releaseDataHandler", "---->");
        if (this.dataHandler != null) {
            this.dataHandler.release();
            this.dataHandler = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        if (this.H.isWaitingForUpdate()) {
            this.H.setReConnectDevMsg(null);
            this.H.stopReconnectTask();
        }
    }

    private void r() {
        c(false);
        u();
        s();
        d(true);
        a((ReconnectParam) null);
        if (this.M != null) {
            this.M = null;
            System.gc();
        }
        if (this.N != null) {
            try {
                this.N.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                this.N = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        this.S = 0;
        this.R = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t() {
        if (!b("startReceiveCmdTimeout") && this.O > 0) {
            this.U.removeMessages(x);
            this.U.sendEmptyMessageDelayed(x, this.O);
        }
    }

    private void u() {
        this.U.removeMessages(x);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v() {
        if (b("upgradeStep03")) {
            return;
        }
        this.G.enterUpdateMode(new IActionCallback<Integer>() { // from class: com.jieli.jl_bt_ota.impl.BluetoothOTAManager.10
            @Override // com.jieli.jl_bt_ota.interfaces.IActionCallback
            public void onError(BaseError baseError) {
                BluetoothOTAManager.this.a("upgradeStep03", baseError);
            }

            @Override // com.jieli.jl_bt_ota.interfaces.IActionCallback
            public void onSuccess(Integer num) {
                JL_Log.i(BluetoothOTAManager.this.TAG, "Step3.请求进入升级模式, 结果码: " + num);
                if (num.intValue() == 0) {
                    BluetoothOTAManager.this.t();
                } else {
                    onError(OTAError.buildError(ErrorCode.SUB_ERR_OTA_FAILED, "Device is not allowed to enter the upgrade mode : " + num));
                }
            }
        });
    }

    private void w() {
        if (b("upgradeStep05")) {
            return;
        }
        this.G.queryUpdateResult(new IActionCallback<Integer>() { // from class: com.jieli.jl_bt_ota.impl.BluetoothOTAManager.12
            @Override // com.jieli.jl_bt_ota.interfaces.IActionCallback
            public void onError(BaseError baseError) {
                BluetoothOTAManager.this.a("upgradeStep05", baseError);
            }

            @Override // com.jieli.jl_bt_ota.interfaces.IActionCallback
            public void onSuccess(Integer num) {
                BaseError buildError;
                JL_Log.i(BluetoothOTAManager.this.TAG, "upgradeStep05", "Step05.询问升级状态, 结果码: " + num);
                boolean z2 = false;
                if (num.intValue() == 0) {
                    BluetoothOTAManager.this.c(false);
                    if (BluetoothOTAManager.this.mBluetoothOption.isPriorityCallbackOtaFinish()) {
                        BluetoothOTAManager.this.g();
                    } else {
                        BluetoothOTAManager.this.U.removeMessages(BluetoothOTAManager.D);
                        BluetoothOTAManager.this.U.sendEmptyMessageDelayed(BluetoothOTAManager.D, 500L);
                    }
                    BluetoothOTAManager.this.G.rebootDevice(new IActionCallback<Boolean>() { // from class: com.jieli.jl_bt_ota.impl.BluetoothOTAManager.12.1
                        @Override // com.jieli.jl_bt_ota.interfaces.IActionCallback
                        public void onError(BaseError baseError) {
                        }

                        @Override // com.jieli.jl_bt_ota.interfaces.IActionCallback
                        public void onSuccess(Boolean bool) {
                            BluetoothOTAManager.this.o();
                        }
                    });
                    return;
                }
                if (num.intValue() == 128) {
                    BluetoothOTAManager.this.resetTotalTime();
                    BluetoothOTAManager.this.s();
                    TargetInfoResponse deviceInfo = BluetoothOTAManager.this.getDeviceInfo();
                    if (deviceInfo != null && deviceInfo.isSupportDoubleBackup()) {
                        z2 = true;
                    }
                    JL_Log.i(BluetoothOTAManager.this.TAG, "upgradeStep05", "download resource finish. isSupportDoubleBackup : " + z2);
                    if (z2) {
                        BluetoothOTAManager.this.v();
                        return;
                    } else {
                        BluetoothOTAManager bluetoothOTAManager = BluetoothOTAManager.this;
                        bluetoothOTAManager.k(bluetoothOTAManager.getConnectedBtDevice());
                        return;
                    }
                }
                switch (num.intValue()) {
                    case 1:
                        buildError = OTAError.buildError(ErrorCode.SUB_ERR_CHECK_RECEIVED_DATA_FAILED);
                        break;
                    case 2:
                        buildError = OTAError.buildError(ErrorCode.SUB_ERR_OTA_FAILED, "Device return update failed.");
                        break;
                    case 3:
                        buildError = OTAError.buildError(ErrorCode.SUB_ERR_UPGRADE_KEY_NOT_MATCH);
                        break;
                    case 4:
                        buildError = OTAError.buildError(ErrorCode.SUB_ERR_CHECK_UPGRADE_FILE);
                        break;
                    case 5:
                        buildError = OTAError.buildError(ErrorCode.SUB_ERR_UPGRADE_TYPE_NOT_MATCH);
                        break;
                    case 6:
                        buildError = OTAError.buildError(ErrorCode.SUB_ERR_UPGRADE_DATA_LEN);
                        break;
                    case 7:
                        buildError = OTAError.buildError(ErrorCode.SUB_ERR_UPGRADE_FLASH_READ);
                        break;
                    case 8:
                        buildError = OTAError.buildError(ErrorCode.SUB_ERR_UPGRADE_CMD_TIMEOUT);
                        break;
                    case 9:
                        buildError = OTAError.buildError(ErrorCode.SUB_ERR_UPGRADE_SAME_FILE);
                        break;
                    default:
                        buildError = OTAError.buildError(ErrorCode.SUB_ERR_UPGRADE_UNKNOWN, num.intValue(), "Device returned to an unknown code : " + num);
                        break;
                }
                onError(buildError);
            }
        });
    }

    @Override // com.jieli.jl_bt_ota.interfaces.IUpgradeManager
    public void cancelOTA() {
        i();
    }

    @Override // com.jieli.jl_bt_ota.interfaces.IBluetoothManager
    public void errorEventCallback(BaseError baseError) {
        this.mBtEventCbHelper.onError(baseError);
        a("errorEventCallback", baseError);
    }

    public int getCommunicationMtu(BluetoothDevice bluetoothDevice) {
        return this.mDeviceStatusCache.getMaxCommunicationMtu(bluetoothDevice);
    }

    public TargetInfoResponse getDeviceInfo(BluetoothDevice bluetoothDevice) {
        return this.mDeviceStatusCache.getDeviceInfo(bluetoothDevice);
    }

    public int getReceiveMtu(BluetoothDevice bluetoothDevice) {
        return this.mDeviceStatusCache.getMaxReceiveMtu(bluetoothDevice);
    }

    public long getTimeout_ms() {
        return this.O;
    }

    public long getTotalTime() {
        return this.Q;
    }

    public int getUpdateContentSize() {
        return this.R;
    }

    public boolean isOTA() {
        return this.L;
    }

    @Override // com.jieli.jl_bt_ota.impl.BluetoothBase
    public void onA2dpStatus(BluetoothDevice bluetoothDevice, int i) {
        super.onA2dpStatus(bluetoothDevice, i);
        a(bluetoothDevice, 2, i);
    }

    @Override // com.jieli.jl_bt_ota.impl.BluetoothDiscovery, com.jieli.jl_bt_ota.impl.BluetoothBase
    public void onAdapterStatus(boolean z2, boolean z3) {
        super.onAdapterStatus(z2, z3);
        if (z2) {
            return;
        }
        a("onAdapterStatus", OTAError.buildError(4099));
        BluetoothDevice connectedBtDevice = getConnectedBtDevice();
        if (connectedBtDevice != null) {
            c(connectedBtDevice, 0);
        }
    }

    @Override // com.jieli.jl_bt_ota.impl.BluetoothBase
    public void onBleDataBlockChanged(BluetoothDevice bluetoothDevice, int i, int i2) {
        super.onBleDataBlockChanged(bluetoothDevice, i, i2);
        JL_Log.i(this.TAG, "onBleDataBlockChanged", CommonUtil.formatString("device : %s, block : %d, status : %d", printBtDeviceInfo(bluetoothDevice), Integer.valueOf(i), Integer.valueOf(i2)));
        if (this.U.hasMessages(y)) {
            this.U.removeMessages(y);
            JL_Log.i(this.TAG, "onBleDataBlockChanged", "handleConnectedEvent >>>");
            b(bluetoothDevice, 0);
        }
    }

    @Override // com.jieli.jl_bt_ota.impl.BluetoothBase, com.jieli.jl_bt_ota.interfaces.IBluetoothManager
    public void onBtDeviceConnection(BluetoothDevice bluetoothDevice, int i) {
        super.onBtDeviceConnection(bluetoothDevice, i);
        JL_Log.i(this.TAG, "onBtDeviceConnection", "device : " + printBtDeviceInfo(bluetoothDevice) + ", " + StateCode.printConnectionState(i));
        if (i != 3) {
            this.U.removeMessages(E);
        }
        if (i != 1) {
            if (BluetoothUtil.deviceEquals(bluetoothDevice, getConnectedBtDevice())) {
                setConnectedBtDevice(null);
            }
            c(bluetoothDevice, i);
            return;
        }
        if (this.dataHandler == null) {
            this.dataHandler = IS_USE_MODIFY_DATA_HANDLER ? new DataHandlerModify(this) : new DataHandler(this);
        }
        if (checkDeviceIsCertify(bluetoothDevice)) {
            if (i(bluetoothDevice)) {
                l(bluetoothDevice);
                return;
            } else {
                b(bluetoothDevice, 1);
                return;
            }
        }
        this.I.stopAuth(bluetoothDevice, false);
        if (this.I.startAuth(bluetoothDevice)) {
            return;
        }
        a(bluetoothDevice, OTAError.buildError(ErrorCode.SUB_ERR_AUTH_DEVICE));
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x0034, code lost:
    
        if (r9 != 2) goto L25;
     */
    @Override // com.jieli.jl_bt_ota.impl.BluetoothBase
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onConnection(android.bluetooth.BluetoothDevice r8, int r9) {
        /*
            r7 = this;
            r0 = 3
            if (r9 == r0) goto Ld0
            boolean r0 = r7.isOTA()
            com.jieli.jl_bt_ota.tool.DeviceReConnectManager r1 = r7.H
            boolean r1 = r1.isWaitingForUpdate()
            java.lang.String r2 = r7.TAG
            java.lang.String r3 = r7.printBtDeviceInfo(r8)
            java.lang.String r4 = com.jieli.jl_bt_ota.constant.StateCode.printConnectionState(r9)
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r0)
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r1)
            java.lang.Object[] r3 = new java.lang.Object[]{r3, r4, r5, r6}
            java.lang.String r4 = "device : %s, status : %s, isOTA = %s, isWaitingForUpdate = %s"
            java.lang.String r3 = com.jieli.jl_bt_ota.util.CommonUtil.formatString(r4, r3)
            java.lang.String r4 = "onConnection"
            com.jieli.jl_bt_ota.util.JL_Log.i(r2, r4, r3)
            if (r9 == 0) goto L6d
            r2 = 1
            if (r9 == r2) goto L38
            r2 = 2
            if (r9 == r2) goto L6d
            goto Ld0
        L38:
            com.jieli.jl_bt_ota.tool.DeviceStatusCache r2 = r7.mDeviceStatusCache
            boolean r2 = r2.isMandatoryUpgrade(r8)
            java.lang.String r3 = r7.TAG
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r2)
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r1)
            java.lang.Object[] r5 = new java.lang.Object[]{r5, r6}
            java.lang.String r6 = "connect success, isMandatoryUpgrade = %s, isWaitingForUpdate = %s"
            java.lang.String r5 = com.jieli.jl_bt_ota.util.CommonUtil.formatString(r6, r5)
            com.jieli.jl_bt_ota.util.JL_Log.w(r3, r4, r5)
            if (r0 == 0) goto Ld0
            if (r1 == 0) goto Ld0
            r7.q()
            if (r2 == 0) goto Ld0
            java.lang.String r0 = r7.TAG
            java.lang.String r1 = "Reconnect device successfully, continue ..."
            com.jieli.jl_bt_ota.util.JL_Log.e(r0, r4, r1)
            r0 = 0
            r7.a(r8, r0)
            r7.v()
            goto Ld0
        L6d:
            android.os.Handler r2 = r7.U
            r3 = 4663(0x1237, float:6.534E-42)
            r2.removeMessages(r3)
            android.os.Handler r2 = r7.U
            r3 = 4674(0x1242, float:6.55E-42)
            r2.removeMessages(r3)
            if (r0 == 0) goto Ld0
            if (r1 == 0) goto Lc0
            java.lang.String r0 = r7.TAG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "device state = "
            r1.<init>(r2)
            java.lang.String r2 = com.jieli.jl_bt_ota.constant.StateCode.printConnectionState(r9)
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r2 = ", "
            java.lang.StringBuilder r1 = r1.append(r2)
            com.jieli.jl_bt_ota.model.ReconnectParam r2 = r7.T
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.jieli.jl_bt_ota.util.JL_Log.i(r0, r4, r1)
            android.os.Handler r0 = r7.U
            r1 = 4664(0x1238, float:6.536E-42)
            r0.removeMessages(r1)
            android.os.Handler r0 = r7.U
            r1 = 4665(0x1239, float:6.537E-42)
            r0.removeMessages(r1)
            com.jieli.jl_bt_ota.model.ReconnectParam r0 = r7.T
            if (r0 == 0) goto Ld0
            java.lang.String r8 = r7.TAG
            java.lang.String r9 = "device communication channel is disconnect. ready reconnect task. "
            com.jieli.jl_bt_ota.util.JL_Log.i(r8, r4, r9)
            r7.h()
            return
        Lc0:
            java.lang.String r0 = r7.TAG
            java.lang.String r1 = "ota failed."
            com.jieli.jl_bt_ota.util.JL_Log.i(r0, r4, r1)
            r0 = 4114(0x1012, float:5.765E-42)
            com.jieli.jl_bt_ota.model.base.BaseError r0 = com.jieli.jl_bt_ota.model.OTAError.buildError(r0)
            r7.a(r4, r0)
        Ld0:
            super.onConnection(r8, r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jieli.jl_bt_ota.impl.BluetoothOTAManager.onConnection(android.bluetooth.BluetoothDevice, int):void");
    }

    @Override // com.jieli.jl_bt_ota.impl.BluetoothBase
    public void onHfpStatus(BluetoothDevice bluetoothDevice, int i) {
        super.onHfpStatus(bluetoothDevice, i);
        a(bluetoothDevice, 1, i);
    }

    @Override // com.jieli.jl_bt_ota.interfaces.IBluetoothManager
    public void onMtuChanged(BluetoothGatt bluetoothGatt, int i, int i2) {
        if (bluetoothGatt == null) {
            return;
        }
        int i3 = i2 == 0 ? i - 3 : 20;
        JL_Log.e(this.TAG, "onMtuChanged", "bleMtu : " + i3);
        onBleDataBlockChanged(bluetoothGatt.getDevice(), i3, i2);
    }

    @Override // com.jieli.jl_bt_ota.interfaces.IBluetoothManager
    public void onReceiveDeviceData(BluetoothDevice bluetoothDevice, byte[] bArr) {
        if (bluetoothDevice == null || bArr == null) {
            return;
        }
        JL_Log.d(this.TAG, "onReceiveDeviceData", "device : " + printBtDeviceInfo(bluetoothDevice) + ", recv data : " + CHexConver.byte2HexStr(bArr));
        if (!checkDeviceIsCertify(bluetoothDevice)) {
            JL_Log.i(this.TAG, "onReceiveDeviceData", "handleAuthData ");
            this.I.handleAuthData(bluetoothDevice, bArr);
        } else {
            if (this.dataHandler == null) {
                JL_Log.i(this.TAG, "onReceiveDeviceData", "No data processor.");
                return;
            }
            DataInfo recvData = new DataInfo().setType(1).setDevice(bluetoothDevice).setRecvData(bArr);
            this.dataHandler.addRecvData(recvData);
            JL_Log.d(this.TAG, "onReceiveDeviceData", "addRecvData : " + recvData);
        }
    }

    @Override // com.jieli.jl_bt_ota.interfaces.IBluetoothManager
    public void queryMandatoryUpdate(final IActionCallback<TargetInfoResponse> iActionCallback) {
        final BluetoothDevice connectedBtDevice = getConnectedBtDevice();
        if (connectedBtDevice == null) {
            JL_Log.w(this.TAG, "queryMandatoryUpdate", "Bluetooth device is disconnected.");
            if (iActionCallback != null) {
                iActionCallback.onError(OTAError.buildError(ErrorCode.SUB_ERR_REMOTE_NOT_CONNECTED));
                return;
            }
            return;
        }
        TargetInfoResponse deviceInfo = getDeviceInfo(connectedBtDevice);
        JL_Log.i(this.TAG, "queryMandatoryUpdate", "cache deviceInfo : " + deviceInfo);
        if (deviceInfo == null) {
            this.G.getDeviceInfo(new IActionCallback<TargetInfoResponse>() { // from class: com.jieli.jl_bt_ota.impl.BluetoothOTAManager.2
                @Override // com.jieli.jl_bt_ota.interfaces.IActionCallback
                public void onError(BaseError baseError) {
                    IActionCallback iActionCallback2 = iActionCallback;
                    if (iActionCallback2 != null) {
                        iActionCallback2.onError(baseError);
                    }
                }

                @Override // com.jieli.jl_bt_ota.interfaces.IActionCallback
                public void onSuccess(TargetInfoResponse targetInfoResponse) {
                    BluetoothOTAManager.this.mDeviceStatusCache.updateDeviceTargetInfo(connectedBtDevice, targetInfoResponse);
                    BluetoothOTAManager.this.queryMandatoryUpdate(iActionCallback);
                }
            });
            return;
        }
        if (deviceInfo.isMandatoryUpgrade() || deviceInfo.getRequestOtaFlag() == 1) {
            if (iActionCallback != null) {
                iActionCallback.onSuccess(deviceInfo);
            }
            this.mBtEventCbHelper.onMandatoryUpgrade(connectedBtDevice);
        } else if (iActionCallback != null) {
            iActionCallback.onError(OTAError.buildError(0, "Device is connected."));
        }
    }

    @Override // com.jieli.jl_bt_ota.interfaces.IBluetoothManager
    public void receiveDataFromDevice(BluetoothDevice bluetoothDevice, byte[] bArr) {
        ArrayList<BasePacket> findPacketData = ParseHelper.findPacketData(bluetoothDevice, getReceiveMtu(bluetoothDevice), bArr);
        if (findPacketData == null || findPacketData.isEmpty()) {
            JL_Log.w(this.TAG, "receiveDataFromDevice", "No ota command found.");
            return;
        }
        int size = findPacketData.size();
        int i = 0;
        while (i < size) {
            BasePacket basePacket = findPacketData.get(i);
            i++;
            BasePacket basePacket2 = basePacket;
            CommandBase convert2Command = ParseHelper.convert2Command(basePacket2, getCacheCommand(bluetoothDevice, basePacket2));
            if (convert2Command == null) {
                JL_Log.w(this.TAG, "receiveDataFromDevice", "Failed to parse command");
            } else {
                JL_Log.d(this.TAG, "receiveDataFromDevice", "" + convert2Command);
                if (basePacket2.getType() == 1) {
                    onReceiveCommand(bluetoothDevice, convert2Command);
                    a(bluetoothDevice, convert2Command, basePacket2.getHasResponse() == 1);
                } else {
                    a(bluetoothDevice, convert2Command);
                }
            }
        }
    }

    @Override // com.jieli.jl_bt_ota.impl.BluetoothBreProfiles, com.jieli.jl_bt_ota.impl.BluetoothDiscovery, com.jieli.jl_bt_ota.impl.BluetoothBase, com.jieli.jl_bt_ota.interfaces.IUpgradeManager
    public void release() {
        super.release();
        cancelOTA();
        r();
        this.H.release();
        ExecutorService executorService = this.K;
        if (executorService != null && !executorService.isShutdown()) {
            this.K.shutdownNow();
            this.K = null;
        }
        this.I.removeListener(this.V);
        this.I.destroy();
        this.J.release();
        this.U.removeCallbacksAndMessages(null);
        p();
    }

    public void resetTotalTime() {
        this.Q = 0L;
    }

    @Override // com.jieli.jl_bt_ota.impl.RcspAuth.IRcspAuthOp
    public boolean sendAuthDataToDevice(BluetoothDevice bluetoothDevice, byte[] bArr) {
        return sendDataToDevice(bluetoothDevice, bArr);
    }

    public void setReconnectAddress(String str) {
        if (this.H.isWaitingForUpdate() && BluetoothAdapter.checkBluetoothAddress(str)) {
            this.H.setReconnectAddress(str);
        }
    }

    public void setTimeout_ms(long j) {
        this.O = j;
    }

    @Override // com.jieli.jl_bt_ota.interfaces.IUpgradeManager
    public void startOTA(IUpgradeCallback iUpgradeCallback) {
        BluetoothDevice connectedBtDevice = getConnectedBtDevice();
        if (connectedBtDevice == null) {
            JL_Log.w(this.TAG, "startOTA", "Bluetooth device is disconnected.");
            if (iUpgradeCallback != null) {
                iUpgradeCallback.onError(OTAError.buildError(ErrorCode.SUB_ERR_REMOTE_NOT_CONNECTED));
                return;
            }
            return;
        }
        if (isOTA()) {
            JL_Log.w(this.TAG, "startOTA", "OTA in progress.");
            if (iUpgradeCallback != null) {
                iUpgradeCallback.onError(OTAError.buildError(ErrorCode.SUB_ERR_OTA_IN_HANDLE));
                return;
            }
            return;
        }
        if (!getBluetoothOption().isUseAuthDevice()) {
            this.mDeviceStatusCache.updateDeviceIsAuth(connectedBtDevice, true);
        }
        c(true);
        this.J.setUpgradeCallback(iUpgradeCallback);
        if (FileUtil.checkFileExist(getBluetoothOption().getFirmwareFilePath())) {
            a(connectedBtDevice, getBluetoothOption().getFirmwareFilePath());
            return;
        }
        if (getBluetoothOption().getFirmwareFileData() == null || getBluetoothOption().getFirmwareFileData().length <= 0) {
            a("startOTA", OTAError.buildError(ErrorCode.SUB_ERR_DATA_NOT_FOUND));
            return;
        }
        this.M = getBluetoothOption().getFirmwareFileData();
        JL_Log.d(this.TAG, "startOTA", "data size = " + (this.M == null ? 0 : this.M.length));
        f();
        m(connectedBtDevice);
    }

    private boolean j() {
        return getConnectedBtDevice() != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void k() {
        this.J.setUpgradeCallback(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m(final BluetoothDevice bluetoothDevice) {
        if (b("upgradePrepare")) {
            return;
        }
        if (getDeviceInfo(bluetoothDevice) == null) {
            this.G.getDeviceInfo(new IActionCallback<TargetInfoResponse>() { // from class: com.jieli.jl_bt_ota.impl.BluetoothOTAManager.5
                @Override // com.jieli.jl_bt_ota.interfaces.IActionCallback
                public void onError(BaseError baseError) {
                    BluetoothOTAManager.this.a("upgradePrepare", baseError);
                }

                @Override // com.jieli.jl_bt_ota.interfaces.IActionCallback
                public void onSuccess(TargetInfoResponse targetInfoResponse) {
                    BluetoothOTAManager.this.mDeviceStatusCache.updateDeviceTargetInfo(bluetoothDevice, targetInfoResponse);
                    BluetoothOTAManager.this.n(bluetoothDevice);
                }
            });
        } else {
            n(bluetoothDevice);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n(final BluetoothDevice bluetoothDevice) {
        if (b("upgradeStep01")) {
            return;
        }
        this.G.readUpgradeFileFlag(new IActionCallback<FileOffset>() { // from class: com.jieli.jl_bt_ota.impl.BluetoothOTAManager.6
            @Override // com.jieli.jl_bt_ota.interfaces.IActionCallback
            public void onError(BaseError baseError) {
                BluetoothOTAManager.this.a("upgradeStep01", baseError);
            }

            @Override // com.jieli.jl_bt_ota.interfaces.IActionCallback
            public void onSuccess(FileOffset fileOffset) {
                JL_Log.i(BluetoothOTAManager.this.TAG, CommonUtil.formatString("Step01.获取升级文件信息的偏移地址, %s", fileOffset));
                BluetoothOTAManager.this.a(bluetoothDevice, 0.0f);
                BluetoothOTAManager.this.b(bluetoothDevice, fileOffset.getOffset(), fileOffset.getLen());
            }
        });
    }

    @Override // com.jieli.jl_bt_ota.interfaces.IUpgradeManager
    public TargetInfoResponse getDeviceInfo() {
        return getDeviceInfo(getConnectedBtDevice());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(boolean z2) {
        this.L = z2;
    }

    private boolean d(BluetoothDevice bluetoothDevice, int i) {
        if (!CommonUtil.checkHasConnectPermission(this.context)) {
            JL_Log.e(this.TAG, "requestBleMtu", "Missing connect permission");
            return false;
        }
        BluetoothGatt bluetoothGatt = getBluetoothGatt();
        if (bluetoothGatt != null && BluetoothUtil.deviceEquals(bluetoothGatt.getDevice(), bluetoothDevice)) {
            JL_Log.e(this.TAG, "requestBleMtu", "requestMtu is started.");
            if (bluetoothGatt.requestMtu(i + 3)) {
                return true;
            }
            JL_Log.e(this.TAG, "requestBleMtu", "requestMtu failed. callback old mtu.");
            onBleDataBlockChanged(bluetoothDevice, 20, ErrorCode.SUB_ERR_CHANGE_BLE_MTU);
            return false;
        }
        JL_Log.e(this.TAG, "requestBleMtu", "Device is disconnected.");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        JL_Log.w(this.TAG, "callbackCancelOTA : ");
        r();
        this.J.onCancelOTA();
        this.U.postDelayed(new Runnable() { // from class: com.jieli.jl_bt_ota.impl.BluetoothOTAManager$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                BluetoothOTAManager.this.k();
            }
        }, 100L);
    }

    private int f(BluetoothDevice bluetoothDevice) {
        int priority = this.mBluetoothOption.getPriority();
        TargetInfoResponse deviceInfo = getDeviceInfo(bluetoothDevice);
        if (deviceInfo != null && !deviceInfo.isSupportDoubleBackup()) {
            int singleBackupOtaWay = deviceInfo.getSingleBackupOtaWay();
            if (singleBackupOtaWay == 1) {
                return 0;
            }
            if (singleBackupOtaWay == 2) {
                return 1;
            }
            if (deviceInfo.getSdkType() >= 2) {
                return 0;
            }
        }
        return priority;
    }

    private void g(final BluetoothDevice bluetoothDevice) {
        JL_Log.d(this.TAG, "getDeviceInfoWithConnection", "---->");
        this.G.getDeviceInfo(new IActionCallback<TargetInfoResponse>() { // from class: com.jieli.jl_bt_ota.impl.BluetoothOTAManager.4
            @Override // com.jieli.jl_bt_ota.interfaces.IActionCallback
            public void onError(BaseError baseError) {
                BluetoothOTAManager.this.a(bluetoothDevice, baseError);
            }

            @Override // com.jieli.jl_bt_ota.interfaces.IActionCallback
            public void onSuccess(TargetInfoResponse targetInfoResponse) {
                BluetoothOTAManager.this.mDeviceStatusCache.updateDeviceTargetInfo(bluetoothDevice, targetInfoResponse);
                if (targetInfoResponse.isSupportMD5()) {
                    BluetoothOTAManager.this.G.getMD5(new IActionCallback<String>() { // from class: com.jieli.jl_bt_ota.impl.BluetoothOTAManager.4.1
                        @Override // com.jieli.jl_bt_ota.interfaces.IActionCallback
                        public void onError(BaseError baseError) {
                            JL_Log.i(BluetoothOTAManager.this.TAG, "getDeviceInfoWithConnection#getDevMD5", "onError, " + baseError);
                        }

                        @Override // com.jieli.jl_bt_ota.interfaces.IActionCallback
                        public void onSuccess(String str) {
                            JL_Log.i(BluetoothOTAManager.this.TAG, "getDeviceInfoWithConnection#getDevMD5", "onSuccess, MD5 : " + str);
                            AnonymousClass4 anonymousClass4 = AnonymousClass4.this;
                            BluetoothOTAManager.this.mDeviceStatusCache.updateDeviceMD5(bluetoothDevice, str);
                        }
                    });
                }
                if (targetInfoResponse.isMandatoryUpgrade()) {
                    JL_Log.w(BluetoothOTAManager.this.TAG, "getDeviceInfoWithConnection", "sdkType : " + targetInfoResponse.getSdkType());
                    BluetoothOTAManager.this.d(bluetoothDevice);
                    if (targetInfoResponse.getSdkType() >= 2) {
                        BluetoothGatt bluetoothGatt = BluetoothOTAManager.this.getBluetoothGatt();
                        if (CommonUtil.checkHasConnectPermission(BluetoothOTAManager.this.context) && bluetoothGatt != null) {
                            JL_Log.w(BluetoothOTAManager.this.TAG, "getDeviceInfoWithConnection", "requestConnectionPriority :: ret : " + bluetoothGatt.requestConnectionPriority(1));
                        }
                    }
                } else {
                    BluetoothOTAManager.this.q();
                    if (BluetoothOTAManager.this.isOTA()) {
                        JL_Log.d(BluetoothOTAManager.this.TAG, "getDeviceInfoWithConnection", "The device is functioning properly. However, the cache status is currently being upgraded, so the upgrade call is complete.");
                        BluetoothOTAManager.this.g();
                    }
                }
                BluetoothOTAManager.this.c(bluetoothDevice);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h(BluetoothDevice bluetoothDevice) {
        boolean isWaitingForUpdate = this.H.isWaitingForUpdate();
        JL_Log.i(this.TAG, "handleBrEdrDisconnect", "isWaitingForUpdate : " + isWaitingForUpdate);
        if (isWaitingForUpdate) {
            this.U.removeMessages(B);
            if (isConnectedDevice(bluetoothDevice)) {
                this.U.removeMessages(F);
                Handler handler = this.U;
                handler.sendMessageDelayed(handler.obtainMessage(F, bluetoothDevice), 6000L);
                disconnectBluetoothDevice(bluetoothDevice);
                return;
            }
            h();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean i(BluetoothDevice bluetoothDevice) {
        return isConnectedDevice(bluetoothDevice) && this.mBluetoothOption.getPriority() == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void j(BluetoothDevice bluetoothDevice) {
        JL_Log.i(this.TAG, "startUpgradeReConnect", "removeBond >>> " + BluetoothUtil.removeBond(this.context, bluetoothDevice));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k(final BluetoothDevice bluetoothDevice) {
        if (b("readyToReconnectDevice")) {
            return;
        }
        int f = f(bluetoothDevice);
        String a = a(bluetoothDevice, f);
        boolean z2 = IS_SUPPORT_NEW_RECONNECT_WAY;
        ReConnectDevMsg reConnectDevMsg = new ReConnectDevMsg(f, a);
        this.H.setReConnectDevMsg(reConnectDevMsg);
        JL_Log.d(this.TAG, "readyToReconnectDevice", "flag = " + (z2 ? 1 : 0) + ", " + reConnectDevMsg);
        a(new ReconnectParam(bluetoothDevice.getAddress(), f, a));
        this.U.removeMessages(C);
        this.U.sendEmptyMessageDelayed(C, 6000L);
        this.G.changeCommunicationWay(f, z2 ? 1 : 0, new IActionCallback<Integer>() { // from class: com.jieli.jl_bt_ota.impl.BluetoothOTAManager.9
            @Override // com.jieli.jl_bt_ota.interfaces.IActionCallback
            public void onError(BaseError baseError) {
                BluetoothOTAManager.this.a("readyToReconnectDevice", baseError);
            }

            @Override // com.jieli.jl_bt_ota.interfaces.IActionCallback
            public void onSuccess(Integer num) {
                BluetoothOTAManager.this.U.removeMessages(BluetoothOTAManager.C);
                if (BluetoothOTAManager.this.H.isWaitingForUpdate()) {
                    BluetoothOTAManager.this.H.setReconnectUseADV(num.intValue() == 1);
                }
                if (BluetoothOTAManager.this.T != null) {
                    BluetoothOTAManager.this.T.setFlag(num.intValue());
                    BluetoothOTAManager bluetoothOTAManager = BluetoothOTAManager.this;
                    bluetoothOTAManager.a(bluetoothDevice, bluetoothOTAManager.T);
                }
            }
        });
    }

    private void b(final int i, final int i2, final IActionCallback<byte[]> iActionCallback) {
        ExecutorService executorService;
        if (i >= 0 && i2 >= 0) {
            if (this.M != null && this.M.length > 0) {
                byte[] bArr = new byte[i2];
                if (i + i2 > this.M.length) {
                    if (iActionCallback != null) {
                        iActionCallback.onError(OTAError.buildError(ErrorCode.SUB_ERR_OFFSET_OVER, CommonUtil.formatString("readBlockData :: Can not read file data by Buffer. offset = %d, len = %d, file data length = %d.", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(this.M.length))));
                        return;
                    }
                    return;
                } else {
                    System.arraycopy(this.M, i, bArr, 0, i2);
                    if (iActionCallback != null) {
                        iActionCallback.onSuccess(bArr);
                        return;
                    }
                    return;
                }
            }
            if (this.N != null && (executorService = this.K) != null && !executorService.isShutdown()) {
                this.K.execute(new Runnable() { // from class: com.jieli.jl_bt_ota.impl.BluetoothOTAManager$$ExternalSyntheticLambda5
                    @Override // java.lang.Runnable
                    public final void run() {
                        BluetoothOTAManager.this.a(i2, i, iActionCallback);
                    }
                });
                return;
            }
        }
        if (iActionCallback != null) {
            iActionCallback.onError(OTAError.buildError(4097, CommonUtil.formatString("readBlockData :: Can not read file data. offset = %d, len = %d.", Integer.valueOf(i), Integer.valueOf(i2))));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(BluetoothDevice bluetoothDevice, int i) {
        JL_Log.i(this.TAG, "notifyConnectionStatus", "device : " + printBtDeviceInfo(bluetoothDevice) + ", status : " + StateCode.printConnectionState(i));
        if (i != 3) {
            if (i == 1 || i == 4) {
                JL_Log.i(this.TAG, "notifyConnectionStatus", "handle connected event.");
            } else if (i == 2 || i == 0) {
                JL_Log.w(this.TAG, "notifyConnectionStatus", "handle disconnect event.");
                p();
                u();
                this.mDeviceStatusCache.removeDeviceStatus(bluetoothDevice);
                o();
            }
        }
        onConnection(bluetoothDevice, i);
    }

    private void i() {
        if (b("exitUpdateMode")) {
            return;
        }
        TargetInfoResponse deviceInfo = getDeviceInfo();
        if (deviceInfo == null || !deviceInfo.isSupportDoubleBackup()) {
            JL_Log.i(this.TAG, "exitUpdateMode", CommonUtil.formatString("device[%s] is single flash ota, so ota progress cannot be interrupted.", getConnectedBtDevice()));
        } else {
            c(false);
            this.G.exitUpdateMode(new IActionCallback<Integer>() { // from class: com.jieli.jl_bt_ota.impl.BluetoothOTAManager.13
                @Override // com.jieli.jl_bt_ota.interfaces.IActionCallback
                public void onError(BaseError baseError) {
                    BluetoothOTAManager.this.c(true);
                    BluetoothOTAManager.this.a("exitUpdateMode", baseError);
                }

                @Override // com.jieli.jl_bt_ota.interfaces.IActionCallback
                public void onSuccess(Integer num) {
                    JL_Log.d(BluetoothOTAManager.this.TAG, "exitUpdateMode", "onSuccess >>> " + num);
                    if (num.intValue() == 0) {
                        BluetoothOTAManager.this.e();
                    } else {
                        onError(OTAError.buildError(ErrorCode.SUB_ERR_OTA_FAILED, num.intValue(), "Device return a bad code : " + num));
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        JL_Log.i(this.TAG, "callbackStopOTA : ");
        r();
        this.J.onStopOTA();
        this.U.postDelayed(new Runnable() { // from class: com.jieli.jl_bt_ota.impl.BluetoothOTAManager$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                BluetoothOTAManager.this.m();
            }
        }, 100L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(BluetoothDevice bluetoothDevice) {
        if (b("checkUpgradeEnvironment")) {
            return;
        }
        TargetInfoResponse deviceInfo = getDeviceInfo(bluetoothDevice);
        JL_Log.d(this.TAG, "checkUpgradeEnvironment", CommonUtil.formatString("device : %s, deviceInfo ：%s", printBtDeviceInfo(bluetoothDevice), deviceInfo));
        if (deviceInfo == null) {
            a("checkUpgradeEnvironment", OTAError.buildError(ErrorCode.SUB_ERR_REMOTE_NOT_CONNECTED));
            return;
        }
        if (deviceInfo.isSupportDoubleBackup()) {
            q();
            v();
        } else if (deviceInfo.isNeedBootLoader()) {
            d(bluetoothDevice);
            t();
        } else if (deviceInfo.isMandatoryUpgrade()) {
            v();
        } else {
            k(bluetoothDevice);
        }
    }

    private void f() {
        JL_Log.i(this.TAG, "callbackStartOTA : ");
        resetTotalTime();
        this.J.onStartOTA();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(ReconnectParam reconnectParam) {
        this.T = reconnectParam;
    }

    private void h() {
        this.U.removeMessages(z);
        this.U.sendEmptyMessageDelayed(z, 1000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void l() {
        this.J.setUpgradeCallback(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(final int i, final int i2, final IActionCallback iActionCallback) {
        final byte[] bArr;
        final int read;
        try {
            bArr = new byte[i];
            this.N.seek(i2);
            read = this.N.read(bArr);
        } catch (IOException e) {
            e = e;
        }
        try {
            if (read != i) {
                final long length = this.N.length();
                this.U.post(new Runnable() { // from class: com.jieli.jl_bt_ota.impl.BluetoothOTAManager$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        BluetoothOTAManager.a(IActionCallback.this, i2, i, read, length);
                    }
                });
                return;
            }
            this.U.post(new Runnable() { // from class: com.jieli.jl_bt_ota.impl.BluetoothOTAManager$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    BluetoothOTAManager.a(IActionCallback.this, bArr, read);
                }
            });
        } catch (IOException e2) {
            e = e2;
            final IOException iOException = e;
            iOException.printStackTrace();
            this.U.post(new Runnable() { // from class: com.jieli.jl_bt_ota.impl.BluetoothOTAManager$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    BluetoothOTAManager.a(IActionCallback.this, iOException);
                }
            });
        }
    }

    private void d(boolean z2) {
        if (this.P > 0) {
            this.Q = CommonUtil.getCurrentTime() - this.P;
            if (z2) {
                this.P = 0L;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(BluetoothDevice bluetoothDevice) {
        c(bluetoothDevice, 1);
        TargetInfoResponse deviceInfo = getDeviceInfo(bluetoothDevice);
        if (deviceInfo == null || isOTA()) {
            return;
        }
        if (deviceInfo.isMandatoryUpgrade() || deviceInfo.getRequestOtaFlag() == 1) {
            this.mBtEventCbHelper.onMandatoryUpgrade(bluetoothDevice);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(BluetoothDevice bluetoothDevice) {
        if (this.mDeviceStatusCache.getMaxCommunicationMtu(bluetoothDevice) < 530) {
            this.mDeviceStatusCache.updateDeviceMaxCommunicationMtu(bluetoothDevice, 530);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void a(IActionCallback iActionCallback, int i, int i2, int i3, long j) {
        if (iActionCallback != null) {
            iActionCallback.onError(OTAError.buildError(ErrorCode.SUB_ERR_OFFSET_OVER, CommonUtil.formatString("readBlockData :: Can not read file data by RandomAccessFile. offset = %d, len = %d, read data size = %d, file data length = %d.", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Long.valueOf(j))));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void a(IActionCallback iActionCallback, byte[] bArr, int i) {
        if (iActionCallback != null) {
            iActionCallback.onSuccess(Arrays.copyOfRange(bArr, 0, i));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void a(IActionCallback iActionCallback, IOException iOException) {
        if (iActionCallback != null) {
            iActionCallback.onError(OTAError.buildError(ErrorCode.SUB_ERR_IO_EXCEPTION, "readBlockData :: failed. " + iOException.getMessage()));
        }
    }

    private void a(final BluetoothDevice bluetoothDevice, String str) {
        if (!j()) {
            a("startReadFileThread", OTAError.buildError(ErrorCode.SUB_ERR_REMOTE_NOT_CONNECTED));
            return;
        }
        f();
        final File file = new File(str);
        if (file.exists() && file.isFile()) {
            ExecutorService executorService = this.K;
            if (executorService == null || executorService.isShutdown()) {
                this.K = Executors.newSingleThreadExecutor();
            }
            if (file.length() <= FILE_CACHE_DATA_LIMIT) {
                this.K.execute(new ReadFileThread(str, new IActionCallback<byte[]>() { // from class: com.jieli.jl_bt_ota.impl.BluetoothOTAManager.3
                    @Override // com.jieli.jl_bt_ota.interfaces.IActionCallback
                    public void onError(BaseError baseError) {
                        BluetoothOTAManager.this.a("ReadFileThread", baseError);
                    }

                    @Override // com.jieli.jl_bt_ota.interfaces.IActionCallback
                    public void onSuccess(byte[] bArr) {
                        JL_Log.i(BluetoothOTAManager.this.TAG, "ReadFileThread", "onSuccess, length = " + (bArr == null ? 0 : bArr.length));
                        BluetoothOTAManager.this.M = bArr;
                        BluetoothOTAManager.this.m(bluetoothDevice);
                    }
                }));
                return;
            } else {
                this.K.execute(new Runnable() { // from class: com.jieli.jl_bt_ota.impl.BluetoothOTAManager$$ExternalSyntheticLambda7
                    @Override // java.lang.Runnable
                    public final void run() {
                        BluetoothOTAManager.this.a(file, bluetoothDevice);
                    }
                });
                return;
            }
        }
        a("startReadFileThread", OTAError.buildError(ErrorCode.SUB_ERR_FILE_NOT_FOUND));
    }

    private float b(int i) {
        int i2 = this.R;
        if (i2 <= 0) {
            return 0.0f;
        }
        float f = (i * 100.0f) / i2;
        if (f >= 100.0f) {
            return 99.9f;
        }
        return f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(BluetoothDevice bluetoothDevice, int i) {
        if (bluetoothDevice == null) {
            JL_Log.e(this.TAG, "handleConnectedEvent", "device is null.");
            return;
        }
        JL_Log.d(this.TAG, "handleConnectedEvent", CommonUtil.formatString("device : %s, way = %d", printBtDeviceInfo(bluetoothDevice), Integer.valueOf(i)));
        if (i == 0) {
            this.U.removeMessages(y);
        }
        setConnectedBtDevice(bluetoothDevice);
        g(bluetoothDevice);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str, boolean z2) {
        if (b("callbackReconnectEvent")) {
            return;
        }
        JL_Log.i(this.TAG, "callbackReconnectEvent : " + str + ", " + z2);
        this.J.onNeedReconnect(str, z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final BluetoothDevice bluetoothDevice, int i, int i2) {
        if (b("upgradeStep02")) {
            return;
        }
        if (i2 < 0 || i < 0) {
            a("upgradeStep02", OTAError.buildError(4097, CommonUtil.formatString("upgradeStep02: offset = %d, len = %d", Integer.valueOf(i), Integer.valueOf(i2))));
        } else if (i == 0 && i2 == 0) {
            a(bluetoothDevice, new byte[]{CHexConver.intToByte(this.mBluetoothOption.getPriority())});
        } else {
            b(i, i2, new IActionCallback<byte[]>() { // from class: com.jieli.jl_bt_ota.impl.BluetoothOTAManager.7
                @Override // com.jieli.jl_bt_ota.interfaces.IActionCallback
                public void onError(BaseError baseError) {
                    BluetoothOTAManager.this.a("upgradeStep02", baseError);
                }

                @Override // com.jieli.jl_bt_ota.interfaces.IActionCallback
                public void onSuccess(byte[] bArr) {
                    BluetoothOTAManager.this.a(bluetoothDevice, bArr);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(File file, BluetoothDevice bluetoothDevice) {
        try {
            this.N = new RandomAccessFile(file, "r");
            m(bluetoothDevice);
        } catch (Exception e) {
            e.printStackTrace();
            this.U.post(new Runnable() { // from class: com.jieli.jl_bt_ota.impl.BluetoothOTAManager$$ExternalSyntheticLambda9
                @Override // java.lang.Runnable
                public final void run() {
                    BluetoothOTAManager.this.n();
                }
            });
        }
    }

    private String a(BluetoothDevice bluetoothDevice, int i) {
        String address = bluetoothDevice.getAddress();
        TargetInfoResponse deviceInfo = getDeviceInfo(bluetoothDevice);
        if (deviceInfo != null && !deviceInfo.isSupportDoubleBackup()) {
            String edrAddr = i == 1 ? deviceInfo.getEdrAddr() : deviceInfo.getBleAddr();
            if (BluetoothAdapter.checkBluetoothAddress(edrAddr) && !edrAddr.equals(address)) {
                return edrAddr;
            }
        }
        return address;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final BluetoothDevice bluetoothDevice, ReconnectParam reconnectParam) {
        JL_Log.d(this.TAG, "startUpgradeReConnect", CommonUtil.formatString("device : %s, ReconnectParam = %s", printBtDeviceInfo(bluetoothDevice), reconnectParam));
        if (bluetoothDevice == null || reconnectParam == null) {
            return;
        }
        boolean isConnectedDevice = isConnectedDevice(bluetoothDevice);
        JL_Log.i(this.TAG, "startUpgradeReConnect", "isConnectedDevice = " + isConnectedDevice);
        if (!isConnectedDevice) {
            h();
            return;
        }
        boolean i = i(bluetoothDevice);
        JL_Log.i(this.TAG, "startUpgradeReConnect", "isBLEConnected = " + i);
        if (i) {
            JL_Log.d(this.TAG, "startUpgradeReConnect", "Waiting for ble disconnect... ");
            h();
            return;
        }
        boolean z2 = isConnectedByProfile(bluetoothDevice) == 2;
        JL_Log.d(this.TAG, "startUpgradeReConnect", "isEDRConnected : " + z2);
        if (!z2) {
            JL_Log.d(this.TAG, "startUpgradeReConnect", "handleBrEdrDisconnect >>> ");
            h(bluetoothDevice);
            return;
        }
        this.U.removeMessages(B);
        Handler handler = this.U;
        handler.sendMessageDelayed(handler.obtainMessage(B, bluetoothDevice), 5000L);
        boolean disconnectByProfiles = disconnectByProfiles(bluetoothDevice);
        JL_Log.i(this.TAG, "startUpgradeReConnect", "disconnectEdrRet : " + disconnectByProfiles);
        if (disconnectByProfiles) {
            return;
        }
        JL_Log.i(this.TAG, "startUpgradeReConnect", "disconnect edr failed.");
        this.U.removeMessages(B);
        this.U.postDelayed(new Runnable() { // from class: com.jieli.jl_bt_ota.impl.BluetoothOTAManager$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                BluetoothOTAManager.this.j(bluetoothDevice);
            }
        }, 300L);
        h(bluetoothDevice);
    }

    private boolean b(String str) {
        if (isOTA()) {
            return false;
        }
        JL_Log.w(this.TAG, str, "OTA process has exited.");
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(BluetoothDevice bluetoothDevice, float f) {
        if (b("callbackProgress")) {
            return;
        }
        TargetInfoResponse deviceInfo = getDeviceInfo(bluetoothDevice);
        a((deviceInfo == null || deviceInfo.isNeedBootLoader()) ? 0 : 1, f);
    }

    private void a(int i, float f) {
        if (b("callbackProgress")) {
            return;
        }
        JL_Log.d(this.TAG, "callbackProgress : type = " + i + ", progress = " + f);
        d(false);
        this.J.onProgress(i, f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, BaseError baseError) {
        if (b("callbackError") || baseError == null) {
            return;
        }
        JL_Log.e(this.TAG, "callbackError", CommonUtil.formatString("%s ----> %s", str, baseError));
        r();
        this.J.onError(baseError);
        this.U.postDelayed(new Runnable() { // from class: com.jieli.jl_bt_ota.impl.BluetoothOTAManager$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() {
                BluetoothOTAManager.this.l();
            }
        }, 100L);
    }

    private void a(BluetoothDevice bluetoothDevice, int i, int i2) {
        if (bluetoothDevice == null || i2 != 0) {
            return;
        }
        boolean z2 = true;
        if (i != 1 ? !(i != 2 || isConnectedByHfp(bluetoothDevice) == 0) : isConnectedByA2dp(bluetoothDevice) != 0) {
            z2 = false;
        }
        boolean hasMessages = this.U.hasMessages(B);
        JL_Log.d(this.TAG, "handleBrEdrProfileStatus", "isBrEdrDisconnect : " + z2 + ", isWaitingEdrDisconnectTask : " + hasMessages);
        if (z2 && hasMessages) {
            h(bluetoothDevice);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final BluetoothDevice bluetoothDevice, byte[] bArr) {
        this.G.inquiryDeviceCanOTA(bArr, new IActionCallback<Integer>() { // from class: com.jieli.jl_bt_ota.impl.BluetoothOTAManager.8
            @Override // com.jieli.jl_bt_ota.interfaces.IActionCallback
            public void onError(BaseError baseError) {
                BluetoothOTAManager.this.a("upgradeStep02", baseError);
            }

            @Override // com.jieli.jl_bt_ota.interfaces.IActionCallback
            public void onSuccess(Integer num) {
                BaseError buildError;
                JL_Log.i(BluetoothOTAManager.this.TAG, CommonUtil.formatString("Step2.发送升级文件校验信息，确认是否可以升级, 结果: %d", num));
                if (num.intValue() == 0) {
                    BluetoothOTAManager.this.e(bluetoothDevice);
                    return;
                }
                switch (num.intValue()) {
                    case 1:
                        buildError = OTAError.buildError(16386);
                        break;
                    case 2:
                        buildError = OTAError.buildError(ErrorCode.SUB_ERR_CHECK_UPGRADE_FILE, "Command E2, result = " + num);
                        break;
                    case 3:
                        buildError = OTAError.buildError(ErrorCode.SUB_ERR_UPGRADE_FILE_VERSION_SAME);
                        break;
                    case 4:
                        buildError = OTAError.buildError(ErrorCode.SUB_ERR_TWS_NOT_CONNECT);
                        break;
                    case 5:
                        buildError = OTAError.buildError(ErrorCode.SUB_ERR_HEADSET_NOT_IN_CHARGING_BIN);
                        break;
                    case 6:
                        buildError = OTAError.buildError(ErrorCode.SUB_ERR_OTA_IN_PROGRESS);
                        break;
                    case 7:
                        buildError = OTAError.buildError(ErrorCode.SUB_ERR_DEVICE_IN_DOUBLE_CONNECTION);
                        break;
                    default:
                        buildError = OTAError.buildError(ErrorCode.SUB_ERR_OTA_FAILED, num.intValue(), "upgradeStep2 :: Unknown error : " + num);
                        break;
                }
                onError(buildError);
            }
        });
    }

    private void a(final FirmwareUpdateBlockCmd firmwareUpdateBlockCmd, final int i, final int i2) {
        if (b("upgradeStep04")) {
            return;
        }
        u();
        if (i == 0 && i2 == 0) {
            JL_Log.i(this.TAG, "upgradeStep04", "read data over.");
            firmwareUpdateBlockCmd.setParam(null);
            firmwareUpdateBlockCmd.setStatus(0);
            sendCommandResponse(firmwareUpdateBlockCmd);
            w();
            return;
        }
        b(i, i2, new IActionCallback<byte[]>() { // from class: com.jieli.jl_bt_ota.impl.BluetoothOTAManager.11
            @Override // com.jieli.jl_bt_ota.interfaces.IActionCallback
            public void onError(BaseError baseError) {
                BluetoothOTAManager.this.a("upgradeStep04", baseError);
            }

            @Override // com.jieli.jl_bt_ota.interfaces.IActionCallback
            public void onSuccess(byte[] bArr) {
                JL_Log.i(BluetoothOTAManager.this.TAG, "upgradeStep04", "read data, offset = " + i + ", length = " + i2 + ", data len = " + bArr.length);
                if (bArr.length <= 0) {
                    BluetoothOTAManager.this.a("upgradeStep04", OTAError.buildError(ErrorCode.SUB_ERR_OFFSET_OVER, "offset = " + i + ", length = " + i2));
                    return;
                }
                firmwareUpdateBlockCmd.setParam(new FirmwareUpdateBlockResponseParam(bArr));
                firmwareUpdateBlockCmd.setStatus(0);
                BluetoothOTAManager.this.sendCommandResponse(firmwareUpdateBlockCmd);
                BluetoothOTAManager.this.t();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(BluetoothDevice bluetoothDevice, BaseError baseError) {
        c(bluetoothDevice, 2);
        a("callbackConnectFailed", baseError);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void a(BluetoothDevice bluetoothDevice, CommandBase commandBase, boolean z2) {
        int id = commandBase.getId();
        if (id == 194) {
            boolean isOTA = isOTA();
            boolean hasMessages = this.U.hasMessages(A);
            JL_Log.d(this.TAG, "Receive Command", "0xC2 : isOTA = " + isOTA + ", hasStopAdvNotify = " + hasMessages);
            if (!isOTA || hasMessages) {
                return;
            }
            this.U.sendEmptyMessageDelayed(A, 3000L);
            this.G.stopADVInfo(null);
            return;
        }
        if (id == 209) {
            SettingsMtuCmd settingsMtuCmd = (SettingsMtuCmd) commandBase;
            SettingsMtuParam settingsMtuParam = (SettingsMtuParam) settingsMtuCmd.getParam();
            if (settingsMtuParam == null) {
                JL_Log.d(this.TAG, "Receive Command", "0xD1 : command is error.");
                if (z2) {
                    settingsMtuCmd.setStatus(1);
                    sendCommandResponse(settingsMtuCmd);
                    return;
                }
                return;
            }
            int mtu = settingsMtuParam.getMtu();
            int maxCommunicationMtu = this.mDeviceStatusCache.getMaxCommunicationMtu(bluetoothDevice);
            if (mtu >= 530) {
                this.mDeviceStatusCache.updateDeviceMaxCommunicationMtu(bluetoothDevice, mtu);
            } else {
                mtu = maxCommunicationMtu;
            }
            if (z2) {
                settingsMtuParam.setMtu(mtu);
                settingsMtuCmd.setStatus(0);
                sendCommandResponse(settingsMtuCmd);
                return;
            }
            return;
        }
        if (id == 232) {
            NotifyUpdateContentSizeCmd notifyUpdateContentSizeCmd = (NotifyUpdateContentSizeCmd) commandBase;
            if (b("Receive E8 command ")) {
                notifyUpdateContentSizeCmd.setParam(null);
                notifyUpdateContentSizeCmd.setStatus(1);
                sendCommandResponse(notifyUpdateContentSizeCmd);
                return;
            }
            JL_Log.w(this.TAG, "Receive Command", "0xE8 : " + notifyUpdateContentSizeCmd);
            NotifyUpdateContentSizeParam notifyUpdateContentSizeParam = (NotifyUpdateContentSizeParam) notifyUpdateContentSizeCmd.getParam();
            if (notifyUpdateContentSizeParam == null) {
                JL_Log.d(this.TAG, "Receive Command", "0xE8 : command is error.");
                notifyUpdateContentSizeCmd.setStatus(1);
                sendCommandResponse(notifyUpdateContentSizeCmd);
                a("Receive E8 command", OTAError.buildError(ErrorCode.SUB_ERR_PARSE_DATA, "E8 command"));
                return;
            }
            int contentSize = notifyUpdateContentSizeParam.getContentSize();
            if (contentSize >= 0) {
                this.P = CommonUtil.getCurrentTime();
                int currentProgress = notifyUpdateContentSizeParam.getCurrentProgress();
                this.S = currentProgress;
                this.R = contentSize;
                a(bluetoothDevice, b(currentProgress));
                notifyUpdateContentSizeCmd.setStatus(0);
                notifyUpdateContentSizeCmd.setParam(null);
                sendCommandResponse(notifyUpdateContentSizeCmd);
                return;
            }
            JL_Log.w(this.TAG, "Receive Command", "0xE8 : length = " + contentSize);
            a("Receive E8 command", OTAError.buildError(4097, "Update content size is error. " + contentSize));
            return;
        }
        if (id == 228) {
            ExitUpdateModeCmd exitUpdateModeCmd = (ExitUpdateModeCmd) commandBase;
            boolean isOTA2 = isOTA();
            if (isOTA2) {
                e();
            }
            exitUpdateModeCmd.setResponse(new ExitUpdateModeResponse(!isOTA2 ? 1 : 0));
            exitUpdateModeCmd.setStatus(0);
            sendCommandResponse(exitUpdateModeCmd);
            return;
        }
        if (id != 229) {
            return;
        }
        u();
        FirmwareUpdateBlockCmd firmwareUpdateBlockCmd = (FirmwareUpdateBlockCmd) commandBase;
        if (b("Receive E5 command")) {
            firmwareUpdateBlockCmd.setParam(null);
            firmwareUpdateBlockCmd.setStatus(1);
            sendCommandResponse(firmwareUpdateBlockCmd);
            return;
        }
        FirmwareUpdateBlockParam firmwareUpdateBlockParam = (FirmwareUpdateBlockParam) firmwareUpdateBlockCmd.getParam();
        if (firmwareUpdateBlockParam == null) {
            JL_Log.d(this.TAG, "Receive Command", "0xE5 : command is error.");
            firmwareUpdateBlockCmd.setStatus(1);
            sendCommandResponse(firmwareUpdateBlockCmd);
            a("Receive E5 command", OTAError.buildError(ErrorCode.SUB_ERR_PARSE_DATA, "E5 command"));
            return;
        }
        int nextUpdateBlockOffsetAddr = firmwareUpdateBlockParam.getNextUpdateBlockOffsetAddr();
        int nextUpdateBlockLen = firmwareUpdateBlockParam.getNextUpdateBlockLen();
        if (this.R > 0) {
            int i = this.S + nextUpdateBlockLen;
            this.S = i;
            a(bluetoothDevice, b(i));
        }
        a(firmwareUpdateBlockCmd, nextUpdateBlockOffsetAddr, nextUpdateBlockLen);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void a(BluetoothDevice bluetoothDevice, CommandBase commandBase) {
        if (commandBase.getStatus() != 0) {
            return;
        }
        int id = commandBase.getId();
        if (id == 209) {
            SettingsMtuResponse settingsMtuResponse = (SettingsMtuResponse) ((SettingsMtuCmd) commandBase).getResponse();
            if (settingsMtuResponse != null) {
                this.mDeviceStatusCache.updateDeviceMaxCommunicationMtu(bluetoothDevice, settingsMtuResponse.getRealMtu());
                return;
            }
            return;
        }
        if (id != 227) {
            if (id != 231) {
                return;
            }
            JL_Log.e(this.TAG, "handleResponseCommand", "reboot >>> ");
            disconnectBluetoothDevice(bluetoothDevice);
            return;
        }
        EnterUpdateModeResponse enterUpdateModeResponse = (EnterUpdateModeResponse) ((EnterUpdateModeCmd) commandBase).getResponse();
        if (enterUpdateModeResponse == null || enterUpdateModeResponse.getCanUpdateFlag() != 0) {
            return;
        }
        d(bluetoothDevice);
    }
}
