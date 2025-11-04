package com.jieli.jl_bt_ota.impl;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.jieli.jl_bt_ota.constant.ErrorCode;
import com.jieli.jl_bt_ota.interfaces.CommandCallback;
import com.jieli.jl_bt_ota.interfaces.IBluetoothCallback;
import com.jieli.jl_bt_ota.interfaces.IBluetoothManager;
import com.jieli.jl_bt_ota.interfaces.IUpgradeManager;
import com.jieli.jl_bt_ota.interfaces.rcsp.ICmdSnGenerator;
import com.jieli.jl_bt_ota.model.BluetoothOTAConfigure;
import com.jieli.jl_bt_ota.model.DataInfo;
import com.jieli.jl_bt_ota.model.OTAError;
import com.jieli.jl_bt_ota.model.base.BaseError;
import com.jieli.jl_bt_ota.model.base.BasePacket;
import com.jieli.jl_bt_ota.model.base.CommandBase;
import com.jieli.jl_bt_ota.tool.BtEventCallbackHelper;
import com.jieli.jl_bt_ota.tool.CommandCache;
import com.jieli.jl_bt_ota.tool.DeviceStatusCache;
import com.jieli.jl_bt_ota.tool.IDataHandler;
import com.jieli.jl_bt_ota.tool.ParseHelper;
import com.jieli.jl_bt_ota.tool.SnGenerator;
import com.jieli.jl_bt_ota.util.BluetoothUtil;
import com.jieli.jl_bt_ota.util.CommonUtil;
import com.jieli.jl_bt_ota.util.JL_Log;

/* loaded from: classes2.dex */
public abstract class BluetoothBase implements IBluetoothManager, IUpgradeManager {
    private volatile BluetoothDevice a;
    private ICmdSnGenerator b;
    private BluetoothAdapterReceiver c;
    protected final Context context;
    private final boolean d;
    protected volatile IDataHandler dataHandler;
    protected final BluetoothAdapter mBluetoothAdapter;
    protected final BtEventCallbackHelper mBtEventCbHelper;
    protected final CommandCache mCommandCache;
    protected final DeviceStatusCache mDeviceStatusCache;
    protected String TAG = getClass().getSimpleName();
    protected BluetoothOTAConfigure mBluetoothOption = BluetoothOTAConfigure.createDefault();

    private class BluetoothAdapterReceiver extends BroadcastReceiver {
        private BluetoothAdapterReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            BluetoothAdapter bluetoothAdapter;
            if (intent == null || !"android.bluetooth.adapter.action.STATE_CHANGED".equals(intent.getAction()) || (bluetoothAdapter = BluetoothBase.this.mBluetoothAdapter) == null) {
                return;
            }
            int state = bluetoothAdapter.getState();
            JL_Log.i(BluetoothBase.this.TAG, "ACTION_STATE_CHANGED", "state : " + state);
            if (10 == state) {
                BluetoothBase bluetoothBase = BluetoothBase.this;
                bluetoothBase.mBtEventCbHelper.onAdapterStatus(false, bluetoothBase.d);
            } else if (12 == state) {
                BluetoothBase bluetoothBase2 = BluetoothBase.this;
                bluetoothBase2.mBtEventCbHelper.onAdapterStatus(true, bluetoothBase2.d);
            }
        }
    }

    public BluetoothBase(Context context) {
        this.context = (Context) CommonUtil.checkNotNull(context, "Context can not be null.");
        CommonUtil.setMainContext(context);
        this.mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        this.mDeviceStatusCache = new DeviceStatusCache();
        this.mCommandCache = new CommandCache();
        this.d = BluetoothUtil.hasBle(context);
        this.mBtEventCbHelper = new BtEventCallbackHelper();
        a();
    }

    private void b() {
        Context context;
        BluetoothAdapterReceiver bluetoothAdapterReceiver = this.c;
        if (bluetoothAdapterReceiver == null || (context = this.context) == null) {
            return;
        }
        context.unregisterReceiver(bluetoothAdapterReceiver);
        this.c = null;
    }

    public boolean checkDeviceIsCertify(BluetoothDevice bluetoothDevice) {
        return !this.mBluetoothOption.isUseAuthDevice() || this.mDeviceStatusCache.isAuthBtDevice(bluetoothDevice);
    }

    @Override // com.jieli.jl_bt_ota.interfaces.IUpgradeManager
    public void configure(BluetoothOTAConfigure bluetoothOTAConfigure) {
        this.mBluetoothOption = (BluetoothOTAConfigure) CommonUtil.checkNotNull(bluetoothOTAConfigure, "configure must not null.");
        JL_Log.d(this.TAG, "configure", "" + bluetoothOTAConfigure);
        this.b = bluetoothOTAConfigure.getSnGenerator();
    }

    public BluetoothOTAConfigure getBluetoothOption() {
        return this.mBluetoothOption;
    }

    public CommandBase getCacheCommand(BluetoothDevice bluetoothDevice, BasePacket basePacket) {
        if (basePacket == null) {
            return null;
        }
        return this.mCommandCache.getCommand(bluetoothDevice, basePacket.getOpCode(), basePacket.getOpCodeSn());
    }

    protected BluetoothDevice getConnectedBtDevice() {
        BluetoothDevice connectedDevice = getConnectedDevice();
        if (this.a == null) {
            this.a = connectedDevice;
        } else if (connectedDevice != null && !BluetoothUtil.deviceEquals(connectedDevice, this.a)) {
            this.a = connectedDevice;
        }
        return this.a;
    }

    public boolean isConnectedDevice() {
        return getConnectedBtDevice() != null;
    }

    protected void onA2dpStatus(BluetoothDevice bluetoothDevice, int i) {
        this.mBtEventCbHelper.onA2dpStatus(bluetoothDevice, i);
    }

    protected void onAdapterStatus(boolean z, boolean z2) {
        this.mBtEventCbHelper.onAdapterStatus(z, z2);
    }

    protected void onBleDataBlockChanged(BluetoothDevice bluetoothDevice, int i, int i2) {
        this.mBtEventCbHelper.onBleDataBlockChanged(bluetoothDevice, i, i2);
    }

    @Override // com.jieli.jl_bt_ota.interfaces.IBluetoothManager
    public void onBtDeviceConnection(BluetoothDevice bluetoothDevice, int i) {
        this.mBtEventCbHelper.onBtDeviceConnection(bluetoothDevice, i);
    }

    protected void onConnection(BluetoothDevice bluetoothDevice, int i) {
        this.mBtEventCbHelper.onConnection(bluetoothDevice, i);
    }

    @Override // com.jieli.jl_bt_ota.interfaces.IBluetoothManager
    public void onError(BaseError baseError) {
        this.mBtEventCbHelper.onError(baseError);
    }

    protected void onHfpStatus(BluetoothDevice bluetoothDevice, int i) {
        this.mBtEventCbHelper.onHfpStatus(bluetoothDevice, i);
    }

    protected void onReceiveCommand(BluetoothDevice bluetoothDevice, CommandBase commandBase) {
        this.mBtEventCbHelper.onReceiveCommand(bluetoothDevice, commandBase);
    }

    protected String printBtDeviceInfo(BluetoothDevice bluetoothDevice) {
        return BluetoothUtil.printBtDeviceInfo(this.context, bluetoothDevice);
    }

    public boolean registerBluetoothCallback(IBluetoothCallback iBluetoothCallback) {
        return this.mBtEventCbHelper.addCallback(iBluetoothCallback);
    }

    @Override // com.jieli.jl_bt_ota.interfaces.IUpgradeManager
    public void release() {
        JL_Log.i(this.TAG, "release", ">>>>>>");
        b();
        this.mDeviceStatusCache.clear();
        this.mCommandCache.release();
        this.mBtEventCbHelper.release();
        ICmdSnGenerator iCmdSnGenerator = this.b;
        if (iCmdSnGenerator != null) {
            if (iCmdSnGenerator instanceof SnGenerator) {
                ((SnGenerator) iCmdSnGenerator).destroy();
            }
            this.b = null;
        }
    }

    public void removeCacheCommand(BluetoothDevice bluetoothDevice, BasePacket basePacket) {
        this.mCommandCache.removeCommandBase(bluetoothDevice, basePacket);
    }

    public void sendCommandAsync(CommandBase commandBase, CommandCallback commandCallback) {
        sendCommandAsync(getConnectedBtDevice(), commandBase, getBluetoothOption().getTimeoutMs(), commandCallback);
    }

    public void sendCommandResponse(CommandBase commandBase) {
        sendCommandResponse(getConnectedBtDevice(), commandBase);
    }

    protected void setConnectedBtDevice(BluetoothDevice bluetoothDevice) {
        this.a = bluetoothDevice;
    }

    public boolean unregisterBluetoothCallback(IBluetoothCallback iBluetoothCallback) {
        return this.mBtEventCbHelper.removeCallback(iBluetoothCallback);
    }

    private void a() {
        if (this.c != null || this.context == null) {
            return;
        }
        this.c = new BluetoothAdapterReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        this.context.registerReceiver(this.c, intentFilter);
    }

    public boolean isConnectedDevice(BluetoothDevice bluetoothDevice) {
        return BluetoothUtil.deviceEquals(getConnectedBtDevice(), bluetoothDevice);
    }

    public void sendCommandAsync(CommandBase commandBase, int i, CommandCallback commandCallback) {
        sendCommandAsync(getConnectedBtDevice(), commandBase, i, commandCallback);
    }

    public void sendCommandResponse(BluetoothDevice bluetoothDevice, CommandBase commandBase) {
        a(bluetoothDevice, commandBase, 0, getBluetoothOption().getTimeoutMs(), null);
    }

    public void sendCommandAsync(BluetoothDevice bluetoothDevice, CommandBase commandBase, int i, CommandCallback commandCallback) {
        a(bluetoothDevice, commandBase, 1, i, commandCallback);
    }

    private void a(BaseError baseError, CommandCallback commandCallback) {
        JL_Log.w(this.TAG, "cbCommandError", "callback : " + commandCallback + ", " + baseError);
        if (commandCallback != null) {
            commandCallback.onErrCode(baseError);
        } else {
            errorEventCallback(baseError);
        }
    }

    private void a(BluetoothDevice bluetoothDevice, CommandBase commandBase, int i, int i2, CommandCallback commandCallback) {
        if (bluetoothDevice == null) {
            bluetoothDevice = getConnectedBtDevice();
        }
        if (bluetoothDevice == null) {
            a(OTAError.buildError(ErrorCode.SUB_ERR_REMOTE_NOT_CONNECTED), commandCallback);
            return;
        }
        if (commandBase == null) {
            a(OTAError.buildError(4097), commandCallback);
            return;
        }
        if (!checkDeviceIsCertify(bluetoothDevice)) {
            a(OTAError.buildError(ErrorCode.SUB_ERR_AUTH_DEVICE), commandCallback);
            return;
        }
        if (this.dataHandler == null) {
            JL_Log.d(this.TAG, "sendCommandAsync", "No data processor.");
            a(OTAError.buildError(ErrorCode.SUB_ERR_REMOTE_NOT_CONNECTED, "No data processor."), commandCallback);
            return;
        }
        if (i == 1) {
            commandBase.setOpCodeSn(a(bluetoothDevice));
            this.mCommandCache.putCommandBase(bluetoothDevice, commandBase);
        }
        BasePacket convert2BasePacket = ParseHelper.convert2BasePacket(commandBase, i);
        if (convert2BasePacket == null) {
            a(OTAError.buildError(ErrorCode.SUB_ERR_PARSE_DATA), commandCallback);
            return;
        }
        DataInfo callback = new DataInfo().setType(0).setDevice(bluetoothDevice).setBasePacket(convert2BasePacket).setTimeoutMs(i2).setCallback(commandCallback);
        JL_Log.d(this.TAG, "sendRcspCommand", "addSendData :: cmdType : " + i + ", " + callback);
        this.dataHandler.addSendData(callback);
    }

    private int a(BluetoothDevice bluetoothDevice) {
        if (this.b == null) {
            this.b = new SnGenerator();
        }
        return this.b.getRcspCmdSeq(bluetoothDevice);
    }
}
