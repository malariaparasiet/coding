package com.wifiled.blelibrary.ble.model;

import android.bluetooth.BluetoothDevice;
import android.os.Parcel;
import android.os.Parcelable;
import com.wifiled.blelibrary.ble.Ble;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public class BleDevice implements Parcelable {
    public static final int CONNECTED = 2;
    public static final int CONNECTING = 1;
    public static final Parcelable.Creator<BleDevice> CREATOR = new Parcelable.Creator<BleDevice>() { // from class: com.wifiled.blelibrary.ble.model.BleDevice.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BleDevice createFromParcel(Parcel parcel) {
            return new BleDevice(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BleDevice[] newArray(int i) {
            return new BleDevice[i];
        }
    };
    public static final int DISCONNECT = 0;
    public static final String TAG = "BleDevice";
    private static final long serialVersionUID = -2576082824642358033L;
    private int around;
    private BluetoothDevice bluetoothDevice;
    private String cid;
    private boolean isAutoConnecting;
    private boolean mAutoConnect;
    private String mBleAddress;
    private String mBleAlias;
    private String mBleName;
    private int mConnectionState;
    private int mDeviceType;
    private Map<String, Object> mPropertyMap;
    private String pid;
    private ScanRecord scanRecord;
    private int size;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getAround() {
        return this.around;
    }

    public void setAround(int i) {
        this.around = i;
    }

    protected BleDevice(String str, String str2) {
        this.mConnectionState = 0;
        this.mBleAddress = "";
        this.mBleName = "";
        this.mBleAlias = "";
        this.mDeviceType = 2;
        this.mAutoConnect = Ble.options().autoConnect;
        this.isAutoConnecting = false;
        this.cid = "";
        this.pid = "";
        this.size = 0;
        this.around = 0;
        this.mBleAddress = str;
        this.mBleName = str2;
    }

    public BleDevice(BluetoothDevice bluetoothDevice) {
        this.mConnectionState = 0;
        this.mBleAddress = "";
        this.mBleName = "";
        this.mBleAlias = "";
        this.mDeviceType = 2;
        this.mAutoConnect = Ble.options().autoConnect;
        this.isAutoConnecting = false;
        this.cid = "";
        this.pid = "";
        this.size = 0;
        this.around = 0;
        this.bluetoothDevice = bluetoothDevice;
        this.mBleAddress = bluetoothDevice.getAddress();
        this.mBleName = bluetoothDevice.getName();
    }

    public BluetoothDevice getBluetoothDevice() {
        return this.bluetoothDevice;
    }

    public BluetoothDevice setBluetoothDevice(BluetoothDevice bluetoothDevice) {
        this.bluetoothDevice = bluetoothDevice;
        return bluetoothDevice;
    }

    protected BleDevice(Parcel parcel) {
        this.mConnectionState = 0;
        this.mBleAddress = "";
        this.mBleName = "";
        this.mBleAlias = "";
        this.mDeviceType = 2;
        this.mAutoConnect = Ble.options().autoConnect;
        this.isAutoConnecting = false;
        this.cid = "";
        this.pid = "";
        this.size = 0;
        this.around = 0;
        this.mConnectionState = parcel.readInt();
        this.mDeviceType = parcel.readInt();
        this.mBleAddress = parcel.readString();
        this.mBleName = parcel.readString();
        this.mBleAlias = parcel.readString();
        this.mAutoConnect = parcel.readByte() != 0;
        this.isAutoConnecting = parcel.readByte() != 0;
        HashMap hashMap = new HashMap();
        this.mPropertyMap = hashMap;
        parcel.readMap(hashMap, getClass().getClassLoader());
    }

    public String getCid() {
        return this.cid;
    }

    public void setCid(String str) {
        this.cid = str;
    }

    public String getPid() {
        return this.pid;
    }

    public void setPid(String str) {
        this.pid = str;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int i) {
        this.size = i;
    }

    public boolean isConnected() {
        return this.mConnectionState == 2;
    }

    public boolean isConnecting() {
        return this.mConnectionState == 1;
    }

    public boolean isDisconnected() {
        return this.mConnectionState == 0;
    }

    public boolean isAutoConnect() {
        return this.mAutoConnect;
    }

    public void setAutoConnect(boolean z) {
        this.mAutoConnect = z;
    }

    public boolean isAutoConnecting() {
        return this.isAutoConnecting;
    }

    public void setAutoConnecting(boolean z) {
        this.isAutoConnecting = z;
    }

    public int getConnectionState() {
        return this.mConnectionState;
    }

    public void setConnectionState(int i) {
        this.mConnectionState = i;
    }

    public String getBleAddress() {
        return this.mBleAddress;
    }

    public void setBleAddress(String str) {
        this.mBleAddress = str;
    }

    public String getBleName() {
        return this.mBleName;
    }

    public void setBleName(String str) {
        this.mBleName = str;
    }

    public String getBleAlias() {
        return this.mBleAlias;
    }

    public void setBleAlias(String str) {
        this.mBleAlias = str;
    }

    public ScanRecord getScanRecord() {
        return this.scanRecord;
    }

    public void setScanRecord(ScanRecord scanRecord) {
        this.scanRecord = scanRecord;
    }

    public int getDeviceType() {
        return this.mDeviceType;
    }

    public void setDeviceType(int i) {
        this.mDeviceType = i;
    }

    public void put(String str, Object obj) {
        if (this.mPropertyMap == null) {
            this.mPropertyMap = new HashMap();
        }
        this.mPropertyMap.put(str, obj);
    }

    public Object get(String str) {
        Map<String, Object> map = this.mPropertyMap;
        if (map == null) {
            return null;
        }
        return map.get(str);
    }

    public String toString() {
        return "BleDevice{mConnectionState=" + this.mConnectionState + ", mDeviceType=" + this.mDeviceType + ", mBleAddress='" + this.mBleAddress + "', mBleName='" + this.mBleName + "', mBleAlias='" + this.mBleAlias + "', mAutoConnect=" + this.mAutoConnect + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mConnectionState);
        parcel.writeInt(this.mDeviceType);
        parcel.writeString(this.mBleAddress);
        parcel.writeString(this.mBleName);
        parcel.writeString(this.mBleAlias);
        parcel.writeByte(this.mAutoConnect ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isAutoConnecting ? (byte) 1 : (byte) 0);
        parcel.writeMap(this.mPropertyMap);
    }
}
