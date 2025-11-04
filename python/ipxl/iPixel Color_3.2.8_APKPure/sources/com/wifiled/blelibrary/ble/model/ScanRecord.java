package com.wifiled.blelibrary.ble.model;

import android.os.ParcelUuid;
import android.util.SparseArray;
import com.j256.ormlite.stmt.query.SimpleComparison;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import kotlin.UByte;

/* loaded from: classes2.dex */
public class ScanRecord {
    private static final int DATA_TYPE_FLAGS = 1;
    private static final int DATA_TYPE_LOCAL_NAME_COMPLETE = 9;
    private static final int DATA_TYPE_LOCAL_NAME_SHORT = 8;
    private static final int DATA_TYPE_MANUFACTURER_SPECIFIC_DATA = 255;
    private static final int DATA_TYPE_SERVICE_DATA = 22;
    private static final int DATA_TYPE_SERVICE_UUIDS_128_BIT_COMPLETE = 7;
    private static final int DATA_TYPE_SERVICE_UUIDS_128_BIT_PARTIAL = 6;
    private static final int DATA_TYPE_SERVICE_UUIDS_16_BIT_COMPLETE = 3;
    private static final int DATA_TYPE_SERVICE_UUIDS_16_BIT_PARTIAL = 2;
    private static final int DATA_TYPE_SERVICE_UUIDS_32_BIT_COMPLETE = 5;
    private static final int DATA_TYPE_SERVICE_UUIDS_32_BIT_PARTIAL = 4;
    private static final int DATA_TYPE_TX_POWER_LEVEL = 10;
    private static final String TAG = "ScanRecord";
    private final int mAdvertiseFlags;
    private final byte[] mBytes;
    private final String mDeviceName;
    private final SparseArray<byte[]> mManufacturerSpecificData;
    private final Map<ParcelUuid, byte[]> mServiceData;
    private final List<ParcelUuid> mServiceUuids;
    private final int mTxPowerLevel;
    static final char[] hexArray = "0123456789ABCDEF".toCharArray();
    public static final ParcelUuid BASE_UUID = ParcelUuid.fromString("00000000-0000-1000-8000-00805F9B34FB");

    static String toString(SparseArray<byte[]> sparseArray) {
        if (sparseArray == null) {
            return "null";
        }
        if (sparseArray.size() == 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder("{");
        for (int i = 0; i < sparseArray.size(); i++) {
            sb.append(sparseArray.keyAt(i)).append(SimpleComparison.EQUAL_TO_OPERATION).append(Arrays.toString(sparseArray.valueAt(i)));
        }
        sb.append('}');
        return sb.toString();
    }

    static <T> String toString(Map<T, byte[]> map) {
        if (map == null) {
            return "null";
        }
        if (map.isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder("{");
        Iterator<Map.Entry<T, byte[]>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            T key = it.next().getKey();
            sb.append(key).append(SimpleComparison.EQUAL_TO_OPERATION).append(Arrays.toString(map.get(key)));
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public int getAdvertiseFlags() {
        return this.mAdvertiseFlags;
    }

    public List<ParcelUuid> getServiceUuids() {
        return this.mServiceUuids;
    }

    public SparseArray<byte[]> getManufacturerSpecificData() {
        return this.mManufacturerSpecificData;
    }

    public byte[] getManufacturerSpecificData(int i) {
        return this.mManufacturerSpecificData.get(i);
    }

    public Map<ParcelUuid, byte[]> getServiceData() {
        return this.mServiceData;
    }

    public byte[] getServiceData(ParcelUuid parcelUuid) {
        if (parcelUuid == null) {
            return null;
        }
        return this.mServiceData.get(parcelUuid);
    }

    public int getTxPowerLevel() {
        return this.mTxPowerLevel;
    }

    public String getDeviceName() {
        return this.mDeviceName;
    }

    public byte[] getBytes() {
        return this.mBytes;
    }

    private ScanRecord(List<ParcelUuid> list, SparseArray<byte[]> sparseArray, Map<ParcelUuid, byte[]> map, int i, int i2, String str, byte[] bArr) {
        this.mServiceUuids = list;
        this.mManufacturerSpecificData = sparseArray;
        this.mServiceData = map;
        this.mDeviceName = str;
        this.mAdvertiseFlags = i;
        this.mTxPowerLevel = i2;
        this.mBytes = bArr;
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0092  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.wifiled.blelibrary.ble.model.ScanRecord parseFromBytes(byte[] r15) {
        /*
            r0 = 0
            if (r15 != 0) goto L4
            return r0
        L4:
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            android.util.SparseArray r4 = new android.util.SparseArray
            r4.<init>()
            android.util.ArrayMap r5 = new android.util.ArrayMap
            r5.<init>()
            r2 = 0
            r3 = -1
            r6 = -2147483648(0xffffffff80000000, float:-0.0)
            r8 = r0
            r7 = r6
            r6 = r3
        L1a:
            int r3 = r15.length     // Catch: java.lang.Exception -> L9a
            if (r2 >= r3) goto L8a
            int r3 = r2 + 1
            r9 = r15[r2]     // Catch: java.lang.Exception -> L9a
            r10 = 255(0xff, float:3.57E-43)
            r9 = r9 & r10
            if (r9 != 0) goto L27
            goto L8a
        L27:
            int r11 = r9 + (-1)
            int r12 = r2 + 2
            r3 = r15[r3]     // Catch: java.lang.Exception -> L9a
            r3 = r3 & r10
            r13 = 22
            r14 = 16
            if (r3 == r13) goto L74
            if (r3 == r10) goto L5d
            switch(r3) {
                case 1: goto L58;
                case 2: goto L54;
                case 3: goto L54;
                case 4: goto L4e;
                case 5: goto L4e;
                case 6: goto L48;
                case 7: goto L48;
                case 8: goto L3e;
                case 9: goto L3e;
                case 10: goto L3a;
                default: goto L39;
            }     // Catch: java.lang.Exception -> L9a
        L39:
            goto L87
        L3a:
            r2 = r15[r12]     // Catch: java.lang.Exception -> L9a
            r7 = r2
            goto L87
        L3e:
            java.lang.String r8 = new java.lang.String     // Catch: java.lang.Exception -> L9a
            byte[] r2 = extractBytes(r15, r12, r11)     // Catch: java.lang.Exception -> L9a
            r8.<init>(r2)     // Catch: java.lang.Exception -> L9a
            goto L87
        L48:
            r2 = 128(0x80, float:1.8E-43)
            parseServiceUuid(r15, r12, r11, r2, r1)     // Catch: java.lang.Exception -> L9a
            goto L87
        L4e:
            r2 = 32
            parseServiceUuid(r15, r12, r11, r2, r1)     // Catch: java.lang.Exception -> L9a
            goto L87
        L54:
            parseServiceUuid(r15, r12, r11, r14, r1)     // Catch: java.lang.Exception -> L9a
            goto L87
        L58:
            r2 = r15[r12]     // Catch: java.lang.Exception -> L9a
            r2 = r2 & r10
            r6 = r2
            goto L87
        L5d:
            int r3 = r2 + 3
            r3 = r15[r3]     // Catch: java.lang.Exception -> L9a
            r3 = r3 & r10
            int r3 = r3 << 8
            r13 = r15[r12]     // Catch: java.lang.Exception -> L9a
            r10 = r10 & r13
            int r3 = r3 + r10
            int r2 = r2 + 4
            int r9 = r9 + (-3)
            byte[] r2 = extractBytes(r15, r2, r9)     // Catch: java.lang.Exception -> L9a
            r4.put(r3, r2)     // Catch: java.lang.Exception -> L9a
            goto L87
        L74:
            byte[] r3 = extractBytes(r15, r12, r14)     // Catch: java.lang.Exception -> L9a
            android.os.ParcelUuid r3 = parseUuidFrom(r3)     // Catch: java.lang.Exception -> L9a
            int r2 = r2 + 18
            int r9 = r9 + (-17)
            byte[] r2 = extractBytes(r15, r2, r9)     // Catch: java.lang.Exception -> L9a
            r5.put(r3, r2)     // Catch: java.lang.Exception -> L9a
        L87:
            int r2 = r12 + r11
            goto L1a
        L8a:
            boolean r2 = r1.isEmpty()     // Catch: java.lang.Exception -> L9a
            if (r2 == 0) goto L92
            r3 = r0
            goto L93
        L92:
            r3 = r1
        L93:
            com.wifiled.blelibrary.ble.model.ScanRecord r2 = new com.wifiled.blelibrary.ble.model.ScanRecord     // Catch: java.lang.Exception -> L9a
            r9 = r15
            r2.<init>(r3, r4, r5, r6, r7, r8, r9)     // Catch: java.lang.Exception -> L9b
            return r2
        L9a:
            r9 = r15
        L9b:
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            java.lang.String r0 = "unable to parse scan record: "
            r15.<init>(r0)
            java.lang.String r0 = java.util.Arrays.toString(r9)
            java.lang.StringBuilder r15 = r15.append(r0)
            java.lang.String r15 = r15.toString()
            java.lang.String r0 = "ScanRecord"
            com.wifiled.blelibrary.ble.BleLog.e(r0, r15)
            com.wifiled.blelibrary.ble.model.ScanRecord r2 = new com.wifiled.blelibrary.ble.model.ScanRecord
            r7 = -2147483648(0xffffffff80000000, float:-0.0)
            r8 = 0
            r3 = 0
            r5 = 0
            r6 = -1
            r2.<init>(r3, r4, r5, r6, r7, r8, r9)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wifiled.blelibrary.ble.model.ScanRecord.parseFromBytes(byte[]):com.wifiled.blelibrary.ble.model.ScanRecord");
    }

    public String toString() {
        return "ScanRecord [mAdvertiseFlags=" + this.mAdvertiseFlags + ", mServiceUuids=" + this.mServiceUuids + ", mManufacturerSpecificData=" + toString(this.mManufacturerSpecificData) + ", mServiceData=" + toString(this.mServiceData) + ", mTxPowerLevel=" + this.mTxPowerLevel + ", mDeviceName=" + this.mDeviceName + "]";
    }

    public static String bytesToHex(byte[] bArr) {
        char[] cArr = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            byte b = bArr[i];
            int i2 = b & UByte.MAX_VALUE;
            int i3 = i * 2;
            char[] cArr2 = hexArray;
            cArr[i3] = cArr2[i2 >>> 4];
            cArr[i3 + 1] = cArr2[b & 15];
        }
        return new String(cArr);
    }

    private static int parseServiceUuid(byte[] bArr, int i, int i2, int i3, List<ParcelUuid> list) {
        while (i2 > 0) {
            list.add(parseUuidFrom(extractBytes(bArr, i, i3)));
            i2 -= i3;
            i += i3;
        }
        return i;
    }

    private static byte[] extractBytes(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        return bArr2;
    }

    public static ParcelUuid parseUuidFrom(byte[] bArr) {
        long j;
        if (bArr == null) {
            throw new IllegalArgumentException("uuidBytes cannot be null");
        }
        int length = bArr.length;
        if (length != 16 && length != 32 && length != 128) {
            throw new IllegalArgumentException("uuidBytes length invalid - " + length);
        }
        if (length == 128) {
            ByteBuffer order = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
            return new ParcelUuid(new UUID(order.getLong(8), order.getLong(0)));
        }
        if (length == 16) {
            j = (bArr[0] & UByte.MAX_VALUE) + ((bArr[1] & UByte.MAX_VALUE) << 8);
        } else {
            j = (bArr[0] & UByte.MAX_VALUE) + ((bArr[1] & UByte.MAX_VALUE) << 8) + ((bArr[2] & UByte.MAX_VALUE) << 16) + ((bArr[3] & UByte.MAX_VALUE) << 24);
        }
        ParcelUuid parcelUuid = BASE_UUID;
        return new ParcelUuid(new UUID(parcelUuid.getUuid().getMostSignificantBits() + (j << 32), parcelUuid.getUuid().getLeastSignificantBits()));
    }
}
