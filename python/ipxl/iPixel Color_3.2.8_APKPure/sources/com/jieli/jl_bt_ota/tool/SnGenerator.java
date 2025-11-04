package com.jieli.jl_bt_ota.tool;

import android.bluetooth.BluetoothDevice;
import com.jieli.jl_bt_ota.interfaces.rcsp.ICmdSnGenerator;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/* loaded from: classes2.dex */
public class SnGenerator implements ICmdSnGenerator {
    private final Map<String, Integer> b = new HashMap();
    private int a = (new Random().nextInt(255) + 1) % 256;

    public synchronized int autoIncSN(BluetoothDevice bluetoothDevice) {
        int cmdSequence = getCmdSequence(bluetoothDevice);
        if (bluetoothDevice == null) {
            int i = cmdSequence + 1;
            this.a = i < 256 ? i : 0;
            return cmdSequence;
        }
        int i2 = cmdSequence + 1;
        if (i2 < 256) {
            r1 = i2;
        }
        this.b.put(bluetoothDevice.getAddress(), Integer.valueOf(r1));
        return cmdSequence;
    }

    public void destroy() {
        this.b.clear();
    }

    public int getCmdSequence(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            return this.a;
        }
        Integer num = this.b.get(bluetoothDevice.getAddress());
        return num == null ? this.a : num.intValue();
    }

    @Override // com.jieli.jl_bt_ota.interfaces.rcsp.ICmdSnGenerator
    public int getRcspCmdSeq(BluetoothDevice bluetoothDevice) {
        return autoIncSN(bluetoothDevice);
    }

    public void removeCmdSequence(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null || this.b.isEmpty()) {
            return;
        }
        this.b.remove(bluetoothDevice.getAddress());
    }
}
