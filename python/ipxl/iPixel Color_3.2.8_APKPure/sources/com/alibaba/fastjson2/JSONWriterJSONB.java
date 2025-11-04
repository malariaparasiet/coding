package com.alibaba.fastjson2;

import com.alibaba.fastjson2.JSONB;
import com.alibaba.fastjson2.JSONFactory;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.internal.trove.map.hash.TLongIntHashMap;
import com.alibaba.fastjson2.util.DateUtils;
import com.alibaba.fastjson2.util.Fnv;
import com.alibaba.fastjson2.util.IOUtils;
import com.alibaba.fastjson2.util.JDKUtils;
import com.alibaba.fastjson2.util.TypeUtils;
import com.alibaba.fastjson2.writer.ObjectWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/* loaded from: classes2.dex */
final class JSONWriterJSONB extends JSONWriter {
    byte[] bytes;
    private final JSONFactory.CacheItem cacheItem;
    private long rootTypeNameHash;
    int symbolIndex;
    TLongIntHashMap symbols;
    static final byte[] SHANGHAI_ZONE_ID_NAME_BYTES = JSONB.toBytes(DateUtils.SHANGHAI_ZONE_ID_NAME);
    static final byte[] OFFSET_8_ZONE_ID_NAME_BYTES = JSONB.toBytes(DateUtils.OFFSET_8_ZONE_ID_NAME);

    static int sizeOfInt(int i) {
        if (i >= -16 && i <= 47) {
            return 1;
        }
        if (i < -2048 || i > 2047) {
            return (i < -262144 || i > 262143) ? 5 : 3;
        }
        return 2;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void endArray() {
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void println() {
    }

    JSONWriterJSONB(JSONWriter.Context context, SymbolTable symbolTable) {
        super(context, symbolTable, true, StandardCharsets.UTF_8);
        JSONFactory.CacheItem cacheItem = JSONFactory.CACHE_ITEMS[System.identityHashCode(Thread.currentThread()) & (JSONFactory.CACHE_ITEMS.length - 1)];
        this.cacheItem = cacheItem;
        byte[] andSet = JSONFactory.BYTES_UPDATER.getAndSet(cacheItem, null);
        this.bytes = andSet == null ? new byte[8192] : andSet;
    }

    @Override // com.alibaba.fastjson2.JSONWriter, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        byte[] bArr = this.bytes;
        if (bArr.length < 8388608) {
            JSONFactory.BYTES_UPDATER.lazySet(this.cacheItem, bArr);
        }
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeAny(Object obj) {
        if (obj == null) {
            writeNull();
            return;
        }
        boolean z = (this.context.features & JSONWriter.Feature.FieldBased.mask) != 0;
        Class<?> cls = obj.getClass();
        ObjectWriter objectWriter = this.context.provider.getObjectWriter(cls, cls, z);
        if (isBeanToArray()) {
            objectWriter.writeArrayMappingJSONB(this, obj, null, null, 0L);
        } else {
            objectWriter.writeJSONB(this, obj, null, null, 0L);
        }
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void startObject() {
        int i = this.level + 1;
        this.level = i;
        if (i > this.context.maxLevel) {
            overflowLevel();
        }
        writeRaw(JSONB.Constants.BC_OBJECT);
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void endObject() {
        this.level--;
        writeRaw(JSONB.Constants.BC_OBJECT_END);
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void startArray() {
        throw new JSONException("unsupported operation");
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void startArray(Object obj, int i) {
        if (isWriteTypeInfo(obj)) {
            writeTypeName(obj.getClass().getName());
        }
        startArray(i);
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void startArray(int i) {
        int i2 = this.off;
        byte[] bArr = this.bytes;
        int i3 = i2 + 6;
        if (i3 > bArr.length) {
            bArr = grow(i3);
        }
        this.off = JSONB.IO.startArray(bArr, i2, i);
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void startArray0() {
        writeRaw((byte) -108);
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void startArray1() {
        writeRaw((byte) -107);
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void startArray2() {
        writeRaw((byte) -106);
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void startArray3() {
        writeRaw((byte) -105);
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void startArray4() {
        writeRaw((byte) -104);
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void startArray5() {
        writeRaw((byte) -103);
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void startArray6() {
        writeRaw((byte) -102);
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void startArray7() {
        writeRaw((byte) -101);
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void startArray8() {
        writeRaw((byte) -100);
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void startArray9() {
        writeRaw((byte) -99);
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void startArray10() {
        writeRaw((byte) -98);
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void startArray11() {
        writeRaw((byte) -97);
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void startArray12() {
        writeRaw((byte) -96);
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void startArray13() {
        writeRaw((byte) -95);
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void startArray14() {
        writeRaw((byte) -94);
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void startArray15() {
        writeRaw(JSONB.Constants.BC_ARRAY_FIX_MAX);
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeRaw(byte b) {
        int i = this.off;
        grow1(i)[i] = b;
        this.off = i + 1;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeChar(char c) {
        int i = this.off;
        byte[] bArr = this.bytes;
        int i2 = i + 6;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        bArr[i] = JSONB.Constants.BC_CHAR;
        this.off = JSONB.IO.writeInt32(bArr, i + 1, c);
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeName(String str) {
        writeString(str);
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeNull() {
        writeRaw(JSONB.Constants.BC_NULL);
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeStringNull() {
        writeRaw(JSONB.Constants.BC_NULL);
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeComma() {
        throw new JSONException("unsupported operation");
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    protected void write0(char c) {
        throw new JSONException("unsupported operation");
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeString(boolean z) {
        writeString(Boolean.toString(z));
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeString(byte b) {
        writeString(Integer.toString(b));
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeString(short s) {
        writeString(Integer.toString(s));
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeString(int i) {
        writeString(Integer.toString(i));
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeString(long j) {
        writeString(Long.toString(j));
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeString(boolean[] zArr) {
        if (zArr == null) {
            writeArrayNull();
            return;
        }
        startArray(zArr.length);
        for (boolean z : zArr) {
            writeString(z);
        }
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeString(byte[] bArr) {
        if (bArr == null) {
            writeArrayNull();
            return;
        }
        startArray(bArr.length);
        for (byte b : bArr) {
            writeString(b);
        }
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeString(short[] sArr) {
        if (sArr == null) {
            writeArrayNull();
            return;
        }
        startArray(sArr.length);
        for (short s : sArr) {
            writeString(s);
        }
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeString(int[] iArr) {
        if (iArr == null) {
            writeArrayNull();
            return;
        }
        startArray(iArr.length);
        for (int i : iArr) {
            writeString(i);
        }
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeString(long[] jArr) {
        if (jArr == null) {
            writeArrayNull();
            return;
        }
        startArray(jArr.length);
        for (long j : jArr) {
            writeString(j);
        }
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeString(float[] fArr) {
        if (fArr == null) {
            writeArrayNull();
            return;
        }
        startArray(fArr.length);
        for (float f : fArr) {
            writeString(f);
        }
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeString(double[] dArr) {
        if (dArr == null) {
            writeArrayNull();
            return;
        }
        startArray(dArr.length);
        for (double d : dArr) {
            writeString(d);
        }
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeString(char[] cArr, int i, int i2, boolean z) {
        int writeInt32;
        if (cArr == null) {
            writeNull();
            return;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            if (cArr[i4 + i] > 255) {
                writeString(new String(cArr, i, i2));
                return;
            }
        }
        int i5 = this.off;
        byte[] bArr = this.bytes;
        if (i2 <= 47) {
            writeInt32 = i5 + 1;
            bArr[i5] = (byte) (i2 + 73);
        } else {
            bArr[i5] = JSONB.Constants.BC_STR_ASCII;
            writeInt32 = JSONB.IO.writeInt32(bArr, i5 + 1, i2);
        }
        while (i3 < i2) {
            bArr[writeInt32] = (byte) cArr[i + i3];
            i3++;
            writeInt32++;
        }
        this.off = writeInt32;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeStringLatin1(byte[] bArr) {
        byte[] bArr2 = this.bytes;
        int i = this.off;
        int length = bArr.length + i + 6;
        if (length - bArr2.length > 0) {
            bArr2 = grow(length);
        }
        this.off = JSONB.IO.writeStringLatin1(bArr2, i, bArr);
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeString(char[] cArr) {
        if (cArr == null) {
            writeNull();
        } else {
            writeString0(cArr, 0, cArr.length);
        }
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeString(char[] cArr, int i, int i2) {
        if (cArr == null) {
            writeNull();
        } else {
            writeString0(cArr, i, i2);
        }
    }

    private void writeString0(char[] cArr, int i, int i2) {
        int i3;
        boolean isLatin1;
        int writeUTF8;
        int i4 = this.off;
        byte[] bArr = this.bytes;
        if (i2 < 47) {
            int i5 = i4 + 1;
            int i6 = i5 + i2;
            if (i6 > bArr.length) {
                bArr = grow(i6);
            }
            bArr[i4] = (byte) (i2 + 73);
            int i7 = i + i2;
            int i8 = i;
            while (true) {
                if (i8 >= i7) {
                    isLatin1 = true;
                    break;
                }
                char c = cArr[i8];
                if (c > 255) {
                    isLatin1 = false;
                    break;
                } else {
                    bArr[i5] = (byte) c;
                    i8++;
                    i5++;
                }
            }
            if (isLatin1) {
                this.off = i5;
                return;
            }
            i3 = this.off;
        } else {
            i3 = i4;
            isLatin1 = IOUtils.isLatin1(cArr, i, i2);
        }
        int i9 = (isLatin1 ? i2 : i2 * 3) + i3 + 6;
        if (i9 > bArr.length) {
            bArr = grow(i9);
        }
        if (isLatin1) {
            writeUTF8 = writeStringLatin1(bArr, i3, cArr, i, i2);
        } else {
            writeUTF8 = writeUTF8(bArr, i3, cArr, i, i2);
        }
        this.off = writeUTF8;
    }

    private static int writeStringLatin1(byte[] bArr, int i, char[] cArr, int i2, int i3) {
        int writeInt32;
        if (i3 <= 47) {
            writeInt32 = i + 1;
            bArr[i] = (byte) (i3 + 73);
        } else {
            bArr[i] = JSONB.Constants.BC_STR_ASCII;
            if (i3 <= 2047) {
                IOUtils.putShortBE(bArr, i + 1, (short) (i3 + 14336));
                writeInt32 = i + 3;
            } else {
                writeInt32 = JSONB.IO.writeInt32(bArr, i + 1, i3);
            }
        }
        int i4 = 0;
        while (i4 < i3) {
            bArr[writeInt32] = (byte) cArr[i2 + i4];
            i4++;
            writeInt32++;
        }
        return writeInt32;
    }

    private static int writeUTF8(byte[] bArr, int i, char[] cArr, int i2, int i3) {
        int sizeOfInt = sizeOfInt(i3 * 3);
        int i4 = i + sizeOfInt + 1;
        int encodeUTF8 = ((IOUtils.encodeUTF8(cArr, i2, i3, bArr, i4) - i) - sizeOfInt) - 1;
        int sizeOfInt2 = sizeOfInt(encodeUTF8);
        if (sizeOfInt != sizeOfInt2) {
            System.arraycopy(bArr, i4, bArr, sizeOfInt2 + i + 1, encodeUTF8);
        }
        bArr[i] = JSONB.Constants.BC_STR_UTF8;
        return JSONB.IO.writeInt32(bArr, i + 1, encodeUTF8) + encodeUTF8;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeString(String[] strArr) {
        if (strArr == null) {
            writeArrayNull();
            return;
        }
        startArray(strArr.length);
        for (String str : strArr) {
            if (str == null) {
                writeStringNull();
            } else {
                writeString(str);
            }
        }
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeSymbol(String str) {
        int ordinal;
        if (str == null) {
            writeNull();
        } else if (this.symbolTable != null && (ordinal = this.symbolTable.getOrdinal(str)) >= 0) {
            writeRaw(Byte.MAX_VALUE);
            writeInt32(-ordinal);
        } else {
            writeString(str);
        }
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeTypeName(String str) {
        int i;
        TLongIntHashMap tLongIntHashMap;
        int i2 = this.off;
        byte[] grow1 = grow1(i2);
        int i3 = i2 + 1;
        grow1[i2] = JSONB.Constants.BC_TYPED_ANY;
        long hashCode64 = Fnv.hashCode64(str);
        if (this.symbolTable != null) {
            i = this.symbolTable.getOrdinalByHashCode(hashCode64);
            if (i == -1 && (tLongIntHashMap = this.symbols) != null) {
                i = tLongIntHashMap.get(hashCode64);
            }
        } else {
            TLongIntHashMap tLongIntHashMap2 = this.symbols;
            i = tLongIntHashMap2 != null ? tLongIntHashMap2.get(hashCode64) : -1;
        }
        if (i == -1) {
            if (this.symbols == null) {
                this.symbols = new TLongIntHashMap();
            }
            TLongIntHashMap tLongIntHashMap3 = this.symbols;
            int i4 = this.symbolIndex;
            this.symbolIndex = i4 + 1;
            tLongIntHashMap3.put(hashCode64, i4);
            this.off = i3;
            writeString(str);
            writeInt32(i4);
            return;
        }
        if (i3 == grow1.length) {
            grow1 = grow(i2 + 2);
        }
        this.off = JSONB.IO.writeInt32(grow1, i3, i);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x005c  */
    @Override // com.alibaba.fastjson2.JSONWriter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean writeTypeName(byte[] r8, long r9) {
        /*
            r7 = this;
            com.alibaba.fastjson2.SymbolTable r0 = r7.symbolTable
            if (r0 == 0) goto L12
            com.alibaba.fastjson2.SymbolTable r0 = r7.symbolTable
            int r0 = r0.getOrdinalByHashCode(r9)
            r1 = -1
            if (r0 == r1) goto L12
            boolean r8 = r7.writeTypeNameSymbol(r0)
            return r8
        L12:
            long r0 = r7.rootTypeNameHash
            int r0 = (r0 > r9 ? 1 : (r0 == r9 ? 0 : -1))
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L1d
            r9 = r1
        L1b:
            r10 = r2
            goto L55
        L1d:
            com.alibaba.fastjson2.internal.trove.map.hash.TLongIntHashMap r0 = r7.symbols
            if (r0 == 0) goto L31
            int r3 = r7.symbolIndex
            int r9 = r0.putIfAbsent(r9, r3)
            int r10 = r7.symbolIndex
            if (r9 == r10) goto L2c
            goto L1b
        L2c:
            int r10 = r10 + r2
            r7.symbolIndex = r10
        L2f:
            r10 = r1
            goto L55
        L31:
            int r0 = r7.symbolIndex
            int r3 = r0 + 1
            r7.symbolIndex = r3
            if (r0 != 0) goto L3b
            r7.rootTypeNameHash = r9
        L3b:
            if (r0 != 0) goto L4c
            com.alibaba.fastjson2.JSONWriter$Context r3 = r7.context
            long r3 = r3.features
            com.alibaba.fastjson2.JSONWriter$Feature r5 = com.alibaba.fastjson2.JSONWriter.Feature.WriteNameAsSymbol
            long r5 = r5.mask
            long r3 = r3 & r5
            r5 = 0
            int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r3 == 0) goto L53
        L4c:
            com.alibaba.fastjson2.internal.trove.map.hash.TLongIntHashMap r3 = new com.alibaba.fastjson2.internal.trove.map.hash.TLongIntHashMap
            r3.<init>(r9, r0)
            r7.symbols = r3
        L53:
            r9 = r0
            goto L2f
        L55:
            if (r10 == 0) goto L5c
            int r8 = -r9
            r7.writeTypeNameSymbol(r8)
            return r1
        L5c:
            byte[] r10 = r7.bytes
            int r0 = r7.off
            int r3 = r0 + 2
            int r4 = r8.length
            int r3 = r3 + r4
            int r4 = r10.length
            if (r3 <= r4) goto L6b
            byte[] r10 = r7.grow(r3)
        L6b:
            r3 = -110(0xffffffffffffff92, float:NaN)
            r10[r0] = r3
            int r3 = r0 + 1
            int r4 = r8.length
            java.lang.System.arraycopy(r8, r1, r10, r3, r4)
            int r8 = r8.length
            int r8 = r8 + r2
            int r0 = r0 + r8
            r8 = -16
            if (r9 < r8) goto L86
            r8 = 47
            if (r9 > r8) goto L86
            int r8 = r0 + 1
            byte r9 = (byte) r9
            r10[r0] = r9
            goto L8a
        L86:
            int r8 = com.alibaba.fastjson2.JSONB.IO.writeInt32(r10, r0, r9)
        L8a:
            r7.off = r8
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONWriterJSONB.writeTypeName(byte[], long):boolean");
    }

    private boolean writeTypeNameSymbol(int i) {
        int i2 = this.off;
        byte[] bArr = this.bytes;
        int i3 = i2 + 7;
        if (i3 > bArr.length) {
            bArr = grow(i3);
        }
        bArr[i2] = JSONB.Constants.BC_TYPED_ANY;
        this.off = JSONB.IO.writeInt32(bArr, i2 + 1, -i);
        return false;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeString(List<String> list) {
        if (list == null) {
            writeArrayNull();
            return;
        }
        int size = list.size();
        startArray(size);
        if (JDKUtils.STRING_VALUE != null && JDKUtils.STRING_CODER != null) {
            int i = this.off;
            byte[] bArr = this.bytes;
            for (int i2 = 0; i2 < size; i2++) {
                String str = list.get(i2);
                if (str == null) {
                    if (i == bArr.length) {
                        bArr = grow(i + 1);
                    }
                    bArr[i] = JSONB.Constants.BC_NULL;
                    i++;
                } else if (JDKUtils.STRING_CODER.applyAsInt(str) == 0) {
                    byte[] apply = JDKUtils.STRING_VALUE.apply(str);
                    if (apply.length + i + 6 >= bArr.length) {
                        bArr = grow(apply.length + i + 6);
                    }
                    i = JSONB.IO.writeStringLatin1(bArr, i, apply);
                }
            }
            this.off = i;
            return;
        }
        for (int i3 = 0; i3 < size; i3++) {
            writeString(list.get(i3));
        }
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeString(String str) {
        if (str == null) {
            writeNull();
            return;
        }
        if (JDKUtils.STRING_VALUE != null) {
            byte[] apply = JDKUtils.STRING_VALUE.apply(str);
            if (JDKUtils.STRING_CODER.applyAsInt(str) == 0) {
                writeStringLatin1(apply);
                return;
            } else if (tryWriteStringUTF16(apply)) {
                return;
            }
        }
        writeString(JDKUtils.getCharArray(str));
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeStringUTF16(byte[] bArr) {
        int i = this.off;
        int length = bArr.length + i + 6;
        byte[] bArr2 = this.bytes;
        if (length > bArr2.length) {
            bArr2 = grow(length);
        }
        this.off = JSONB.IO.writeStringUTF16(bArr2, i, bArr);
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0076 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    boolean tryWriteStringUTF16(byte[] r11) {
        /*
            r10 = this;
            int r0 = r11.length
            r1 = 128(0x80, float:1.8E-43)
            if (r1 <= r0) goto L6
            int r1 = r11.length
        L6:
            r0 = r1 & 1
            r2 = 1
            if (r0 != r2) goto Ld
            int r1 = r1 + (-1)
        Ld:
            r0 = 0
            r3 = r0
            r8 = r3
        L10:
            int r4 = r3 + 2
            if (r4 > r1) goto L22
            r5 = r11[r3]
            int r3 = r3 + 1
            r3 = r11[r3]
            if (r5 == 0) goto L1e
            if (r3 != 0) goto L20
        L1e:
            int r8 = r8 + 1
        L20:
            r3 = r4
            goto L10
        L22:
            int r3 = r11.length
            if (r3 == 0) goto L2e
            if (r8 == 0) goto L2c
            int r1 = r1 >> r2
            int r1 = r1 / r8
            r3 = 3
            if (r1 < r3) goto L2e
        L2c:
            r1 = r2
            goto L2f
        L2e:
            r1 = r0
        L2f:
            int r5 = r10.off
            int r3 = r5 + 6
            int r4 = r11.length
            int r4 = r4 * 2
            int r3 = r3 + r4
            int r3 = r3 + r2
            byte[] r4 = r10.bytes
            int r6 = r4.length
            if (r3 <= r6) goto L41
            byte[] r4 = r10.grow(r3)
        L41:
            if (r1 != 0) goto L6b
            int r3 = r11.length
            int r6 = r11.length
            int r6 = r6 >> 2
            int r3 = r3 + r6
            int r9 = sizeOfInt(r3)
            int r3 = r11.length
            int r6 = r5 + r9
            int r6 = r6 + r2
            int r3 = com.alibaba.fastjson2.util.IOUtils.encodeUTF8(r11, r0, r3, r4, r6)
            int r6 = r3 - r5
            int r6 = r6 - r9
            int r7 = r6 + (-1)
            int r6 = r11.length
            if (r7 <= r6) goto L5f
            r6 = r11
            r1 = r2
            goto L6c
        L5f:
            r6 = -1
            if (r3 == r6) goto L6b
            r6 = r11
            int r11 = writeUTF8(r4, r5, r6, r7, r8, r9)
            int r5 = r5 + r11
            r10.off = r5
            return r2
        L6b:
            r6 = r11
        L6c:
            if (r1 == 0) goto L76
            int r11 = writeUTF16(r4, r5, r6)
            int r5 = r5 + r11
            r10.off = r5
            return r2
        L76:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONWriterJSONB.tryWriteStringUTF16(byte[]):boolean");
    }

    private static int writeUTF8(byte[] bArr, int i, byte[] bArr2, int i2, int i3, int i4) {
        byte b;
        if (i2 * 2 != bArr2.length) {
            b = JSONB.Constants.BC_STR_UTF8;
        } else {
            if (i3 <= 47) {
                bArr[i] = (byte) (i2 + 73);
                int i5 = i + 1;
                System.arraycopy(bArr, i4 + i5, bArr, i5, i2);
                return i2 + 1;
            }
            b = JSONB.Constants.BC_STR_ASCII;
        }
        int sizeOfInt = sizeOfInt(i2);
        if (i4 != sizeOfInt) {
            System.arraycopy(bArr, i4 + i + 1, bArr, sizeOfInt + i + 1, i2);
        }
        bArr[i] = b;
        return (JSONB.IO.writeInt32(bArr, i + 1, i2) - i) + i2;
    }

    private static int writeUTF16(byte[] bArr, int i, byte[] bArr2) {
        bArr[i] = JDKUtils.BIG_ENDIAN ? JSONB.Constants.BC_STR_UTF16BE : JSONB.Constants.BC_STR_UTF16LE;
        int writeInt32 = JSONB.IO.writeInt32(bArr, i + 1, bArr2.length);
        System.arraycopy(bArr2, 0, bArr, writeInt32, bArr2.length);
        return (bArr2.length + writeInt32) - i;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final Object ensureCapacity(int i) {
        byte[] bArr = this.bytes;
        if (i < bArr.length) {
            return bArr;
        }
        byte[] copyOf = Arrays.copyOf(bArr, newCapacity(i, bArr.length));
        this.bytes = copyOf;
        return copyOf;
    }

    private byte[] grow1(int i) {
        byte[] bArr = this.bytes;
        return i == bArr.length ? grow(i + 1) : bArr;
    }

    private byte[] grow(int i) {
        grow0(i);
        return this.bytes;
    }

    private void grow0(int i) {
        byte[] bArr = this.bytes;
        this.bytes = Arrays.copyOf(bArr, newCapacity(i, bArr.length));
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeMillis(long j) {
        int i = this.off;
        int i2 = i + 9;
        byte[] bArr = this.bytes;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        if (j % 1000 == 0) {
            long j2 = j / 1000;
            if (j2 >= -2147483648L && j2 <= 2147483647L) {
                bArr[i] = JSONB.Constants.BC_TIMESTAMP_SECONDS;
                IOUtils.putIntBE(bArr, i + 1, (int) j2);
                this.off = i + 5;
                return;
            }
            if (j2 % 60 == 0) {
                long j3 = j2 / 60;
                if (j3 >= -2147483648L && j3 <= 2147483647L) {
                    bArr[i] = JSONB.Constants.BC_TIMESTAMP_MINUTES;
                    IOUtils.putIntBE(bArr, i + 1, (int) j3);
                    this.off = i + 5;
                    return;
                }
            }
        }
        bArr[i] = JSONB.Constants.BC_TIMESTAMP_MILLIS;
        IOUtils.putLongBE(bArr, i + 1, j);
        this.off = i2;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeInt64(Long l) {
        int i = this.off + 9;
        byte[] bArr = this.bytes;
        if (i > bArr.length) {
            bArr = grow(i);
        }
        this.off = JSONB.IO.writeInt64(bArr, this.off, l, this.context.features);
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeInt64(long j) {
        int i = this.off + 9;
        byte[] bArr = this.bytes;
        if (i > bArr.length) {
            bArr = grow(i);
        }
        this.off = JSONB.IO.writeInt64(bArr, this.off, j);
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeInt64(long[] jArr) {
        int writeInt32;
        if (jArr == null) {
            writeArrayNull();
            return;
        }
        int length = jArr.length;
        int i = this.off;
        int i2 = (length * 9) + i + 5;
        byte[] bArr = this.bytes;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        if (length <= 15) {
            writeInt32 = i + 1;
            bArr[i] = (byte) (length - 108);
        } else {
            bArr[i] = JSONB.Constants.BC_ARRAY;
            writeInt32 = JSONB.IO.writeInt32(bArr, i + 1, length);
        }
        for (long j : jArr) {
            writeInt32 = JSONB.IO.writeInt64(bArr, writeInt32, j);
        }
        this.off = writeInt32;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeListInt64(List<Long> list) {
        int writeInt32;
        if (list == null) {
            writeArrayNull();
            return;
        }
        int size = list.size();
        int i = this.off;
        int i2 = (size * 9) + i + 5;
        byte[] bArr = this.bytes;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        if (size <= 15) {
            writeInt32 = i + 1;
            bArr[i] = (byte) (size - 108);
        } else {
            bArr[i] = JSONB.Constants.BC_ARRAY;
            writeInt32 = JSONB.IO.writeInt32(bArr, i + 1, size);
        }
        for (int i3 = 0; i3 < size; i3++) {
            Long l = list.get(i3);
            if (l == null) {
                bArr[writeInt32] = JSONB.Constants.BC_NULL;
                writeInt32++;
            } else {
                writeInt32 = JSONB.IO.writeInt64(bArr, writeInt32, l.longValue());
            }
        }
        this.off = writeInt32;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeFloat(float f) {
        int i = this.off;
        int i2 = i + 5;
        byte[] bArr = this.bytes;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        this.off = JSONB.IO.writeFloat(bArr, i, f);
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeFloat(float[] fArr) {
        int i = this.off;
        byte[] bArr = this.bytes;
        int length = (fArr == null ? 1 : (fArr.length * 5) + 5) + i;
        if (length > bArr.length) {
            bArr = grow(length);
        }
        this.off = JSONB.IO.writeFloat(bArr, i, fArr);
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeDouble(double d) {
        int i = this.off;
        byte[] bArr = this.bytes;
        int i2 = i + 9;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        this.off = JSONB.IO.writeDouble(bArr, i, d);
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeDouble(double[] dArr) {
        int i = this.off;
        byte[] bArr = this.bytes;
        int length = (dArr == null ? 1 : (dArr.length * 9) + 5) + i;
        if (length > bArr.length) {
            bArr = grow(length);
        }
        this.off = JSONB.IO.writeDouble(bArr, i, dArr);
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeInt16(short[] sArr) {
        if (sArr == null) {
            writeNull();
            return;
        }
        startArray(sArr.length);
        for (short s : sArr) {
            writeInt32(s);
        }
        endArray();
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeInt32(int[] iArr) {
        int writeInt32;
        if (iArr == null) {
            writeArrayNull();
            return;
        }
        int i = this.off;
        byte[] bArr = this.bytes;
        int length = iArr.length;
        int length2 = i + 6 + (iArr.length * 5);
        if (length2 > bArr.length) {
            bArr = grow(length2);
        }
        if (length <= 15) {
            writeInt32 = i + 1;
            bArr[i] = (byte) (length - 108);
        } else {
            bArr[i] = JSONB.Constants.BC_ARRAY;
            writeInt32 = JSONB.IO.writeInt32(bArr, i + 1, length);
        }
        for (int i2 : iArr) {
            writeInt32 = JSONB.IO.writeInt32(bArr, writeInt32, i2);
        }
        this.off = writeInt32;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeInt8(byte[] bArr) {
        int writeInt32;
        if (bArr == null) {
            writeArrayNull();
            return;
        }
        int i = this.off;
        int length = bArr.length;
        int length2 = i + 6 + (bArr.length * 2);
        byte[] bArr2 = this.bytes;
        if (length2 > bArr2.length) {
            bArr2 = grow(length2);
        }
        if (length <= 15) {
            writeInt32 = i + 1;
            bArr2[i] = (byte) (length - 108);
        } else {
            bArr2[i] = JSONB.Constants.BC_ARRAY;
            writeInt32 = JSONB.IO.writeInt32(bArr2, i + 1, length);
        }
        int length3 = bArr.length;
        int i2 = 0;
        while (i2 < length3) {
            byte b = bArr[i2];
            if (b < -16 || b > 47) {
                bArr2[writeInt32] = (byte) ((b >> 8) + 56);
                writeInt32++;
            }
            bArr2[writeInt32] = b;
            i2++;
            writeInt32++;
        }
        this.off = writeInt32;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeInt8(byte b) {
        int i = this.off;
        byte[] bArr = this.bytes;
        int i2 = i + 2;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        this.off = JSONB.IO.writeInt8(bArr, i, b);
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeInt16(short s) {
        int i = this.off;
        byte[] bArr = this.bytes;
        int i2 = i + 3;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        this.off = JSONB.IO.writeInt16(bArr, i, s);
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeEnum(Enum r7) {
        String name;
        if (r7 == null) {
            writeNull();
            return;
        }
        if ((this.context.features & 24576) != 0) {
            if ((this.context.features & JSONWriter.Feature.WriteEnumUsingToString.mask) != 0) {
                name = r7.toString();
            } else {
                name = r7.name();
            }
            writeString(name);
            return;
        }
        int ordinal = r7.ordinal();
        byte[] bArr = this.bytes;
        int i = this.off;
        int i2 = i + 5;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        this.off = JSONB.IO.writeInt32(bArr, i, ordinal);
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeInt32(Integer num) {
        int i = this.off;
        int i2 = i + 5;
        byte[] bArr = this.bytes;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        this.off = JSONB.IO.writeInt32(bArr, i, num, this.context.features);
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeInt32(int i) {
        int i2 = this.off;
        int i3 = i2 + 5;
        byte[] bArr = this.bytes;
        if (i3 > bArr.length) {
            bArr = grow(i3);
        }
        this.off = JSONB.IO.writeInt32(bArr, i2, i);
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeListInt32(List<Integer> list) {
        int writeInt32;
        if (list == null) {
            writeArrayNull();
            return;
        }
        int size = list.size();
        int i = this.off;
        int i2 = (size * 5) + i + 5;
        byte[] bArr = this.bytes;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        if (size <= 15) {
            writeInt32 = i + 1;
            bArr[i] = (byte) (size - 108);
        } else {
            bArr[i] = JSONB.Constants.BC_ARRAY;
            writeInt32 = JSONB.IO.writeInt32(bArr, i + 1, size);
        }
        for (int i3 = 0; i3 < size; i3++) {
            Integer num = list.get(i3);
            if (num == null) {
                bArr[writeInt32] = JSONB.Constants.BC_NULL;
                writeInt32++;
            } else {
                writeInt32 = JSONB.IO.writeInt32(bArr, writeInt32, num.intValue());
            }
        }
        this.off = writeInt32;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeArrayNull() {
        writeRaw((this.context.features & WRITE_ARRAY_NULL_MASK) != 0 ? (byte) -108 : JSONB.Constants.BC_NULL);
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeArrayNull(long j) {
        writeRaw((j & WRITE_ARRAY_NULL_MASK) != 0 ? (byte) -108 : JSONB.Constants.BC_NULL);
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeRaw(String str) {
        throw new JSONException("unsupported operation");
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeRaw(byte[] bArr) {
        int i = this.off;
        int length = bArr.length + i;
        byte[] bArr2 = this.bytes;
        if (length > bArr2.length) {
            bArr2 = grow(length);
        }
        System.arraycopy(bArr, 0, bArr2, i, bArr.length);
        this.off = i + bArr.length;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeSymbol(int i) {
        int writeInt32;
        int i2 = this.off;
        int i3 = i2 + 6;
        byte[] bArr = this.bytes;
        if (i3 > bArr.length) {
            bArr = grow(i3);
        }
        int i4 = i2 + 1;
        bArr[i2] = Byte.MAX_VALUE;
        if (i >= -16 && i <= 47) {
            writeInt32 = i2 + 2;
            bArr[i4] = (byte) i;
        } else if (i >= -2048 && i <= 2047) {
            IOUtils.putShortBE(bArr, i4, (short) (i + 14336));
            writeInt32 = i2 + 3;
        } else {
            writeInt32 = JSONB.IO.writeInt32(bArr, i4, i);
        }
        this.off = writeInt32;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeNameRaw(byte[] bArr, long j) {
        boolean z;
        int i;
        int i2;
        int writeInt32;
        int writeInt322;
        int i3 = this.off;
        int length = i3 + 6 + bArr.length;
        byte[] bArr2 = this.bytes;
        if (length > bArr2.length) {
            bArr2 = grow(length);
        }
        if (this.symbolTable == null || (i2 = this.symbolTable.getOrdinalByHashCode(j)) == -1) {
            if ((this.context.features & JSONWriter.Feature.WriteNameAsSymbol.mask) == 0) {
                System.arraycopy(bArr, 0, bArr2, i3, bArr.length);
                this.off = i3 + bArr.length;
                return;
            }
            TLongIntHashMap tLongIntHashMap = this.symbols;
            if (tLongIntHashMap != null) {
                i = tLongIntHashMap.putIfAbsent(j, this.symbolIndex);
                int i4 = this.symbolIndex;
                z = true;
                if (i == i4) {
                    this.symbolIndex = i4 + 1;
                    z = false;
                }
            } else {
                TLongIntHashMap tLongIntHashMap2 = new TLongIntHashMap();
                this.symbols = tLongIntHashMap2;
                int i5 = this.symbolIndex;
                this.symbolIndex = i5 + 1;
                tLongIntHashMap2.put(j, i5);
                z = false;
                i = i5;
            }
            if (!z) {
                int i6 = i3 + 1;
                bArr2[i3] = Byte.MAX_VALUE;
                System.arraycopy(bArr, 0, bArr2, i6, bArr.length);
                int length2 = i6 + bArr.length;
                if (i >= -16 && i <= 47) {
                    writeInt32 = length2 + 1;
                    bArr2[length2] = (byte) i;
                } else {
                    writeInt32 = JSONB.IO.writeInt32(bArr2, length2, i);
                }
                this.off = writeInt32;
                return;
            }
            i2 = -i;
        }
        int i7 = i3 + 1;
        bArr2[i3] = Byte.MAX_VALUE;
        int i8 = -i2;
        if (i8 >= -16 && i8 <= 47) {
            writeInt322 = i3 + 2;
            bArr2[i7] = (byte) i8;
        } else {
            writeInt322 = JSONB.IO.writeInt32(bArr2, i7, i8);
        }
        this.off = writeInt322;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeLocalDate(LocalDate localDate) {
        int i = this.off;
        byte[] bArr = this.bytes;
        int i2 = i + 5;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        this.off = JSONB.IO.writeLocalDate(bArr, i, localDate);
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeLocalTime(LocalTime localTime) {
        int i = this.off;
        byte[] bArr = this.bytes;
        int i2 = i + 9;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        this.off = JSONB.IO.writeLocalTime(bArr, i, localTime);
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeLocalDateTime(LocalDateTime localDateTime) {
        int i = this.off;
        byte[] bArr = this.bytes;
        int i2 = i + 13;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        this.off = JSONB.IO.writeLocalDateTime(bArr, i, localDateTime);
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeZonedDateTime(ZonedDateTime zonedDateTime) {
        if (zonedDateTime == null) {
            writeNull();
            return;
        }
        int i = this.off;
        byte[] bArr = this.bytes;
        int i2 = i + 13;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        IOUtils.putIntBE(bArr, i, (zonedDateTime.getYear() << 8) | (-1442840576) | zonedDateTime.getMonthValue());
        IOUtils.putIntBE(bArr, i + 4, (zonedDateTime.getDayOfMonth() << 24) | (zonedDateTime.getHour() << 16) | (zonedDateTime.getMinute() << 8) | zonedDateTime.getSecond());
        this.off = JSONB.IO.writeInt32(bArr, i + 8, zonedDateTime.getNano());
        String id = zonedDateTime.getZone().getId();
        if (id.equals(DateUtils.SHANGHAI_ZONE_ID_NAME)) {
            writeRaw(SHANGHAI_ZONE_ID_NAME_BYTES);
        } else {
            writeString(id);
        }
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeOffsetDateTime(OffsetDateTime offsetDateTime) {
        int i = this.off;
        byte[] bArr = this.bytes;
        int i2 = i + 21;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        this.off = JSONB.IO.writeOffsetDateTime(bArr, i, offsetDateTime);
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeOffsetTime(OffsetTime offsetTime) {
        int i = this.off;
        byte[] bArr = this.bytes;
        int i2 = i + 21;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        this.off = JSONB.IO.writeOffsetTime(bArr, i, offsetTime);
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeInstant(Instant instant) {
        int i = this.off;
        byte[] bArr = this.bytes;
        int i2 = i + 15;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        this.off = JSONB.IO.writeInstant(bArr, i, instant);
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeUUID(UUID uuid) {
        int i = this.off;
        byte[] bArr = this.bytes;
        int i2 = i + 18;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        this.off = JSONB.IO.writeUUID(bArr, i, uuid);
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeBigInt(BigInteger bigInteger, long j) {
        if (bigInteger == null) {
            writeNull();
            return;
        }
        int i = this.off;
        byte[] bArr = this.bytes;
        if (TypeUtils.isInt64(bigInteger)) {
            int i2 = i + 10;
            if (i2 > bArr.length) {
                bArr = grow(i2);
            }
            bArr[i] = -70;
            this.off = JSONB.IO.writeInt64(bArr, i + 1, bigInteger.longValue());
            return;
        }
        byte[] byteArray = bigInteger.toByteArray();
        int length = i + 5 + byteArray.length;
        if (length > bArr.length) {
            bArr = grow(length);
        }
        bArr[i] = JSONB.Constants.BC_BIGINT;
        int writeInt32 = JSONB.IO.writeInt32(bArr, i + 1, byteArray.length);
        System.arraycopy(byteArray, 0, bArr, writeInt32, byteArray.length);
        this.off = writeInt32 + byteArray.length;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeBinary(byte[] bArr) {
        if (bArr == null) {
            writeNull();
            return;
        }
        int i = this.off;
        int length = bArr.length;
        int i2 = i + 6 + length;
        byte[] bArr2 = this.bytes;
        if (i2 > bArr2.length) {
            bArr2 = grow(i2);
        }
        bArr2[i] = JSONB.Constants.BC_BINARY;
        int writeInt32 = JSONB.IO.writeInt32(bArr2, i + 1, length);
        System.arraycopy(bArr, 0, bArr2, writeInt32, length);
        this.off = writeInt32 + length;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeDecimal(BigDecimal bigDecimal, long j, DecimalFormat decimalFormat) {
        int writeInt64;
        int writeInt642;
        if (bigDecimal == null) {
            writeNull();
            return;
        }
        int precision = bigDecimal.precision();
        int scale = bigDecimal.scale();
        int i = this.off;
        byte[] bArr = this.bytes;
        int i2 = i + 15;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        if (precision < 19 && JDKUtils.FIELD_DECIMAL_INT_COMPACT_OFFSET != -1) {
            long j2 = JDKUtils.UNSAFE.getLong(bigDecimal, JDKUtils.FIELD_DECIMAL_INT_COMPACT_OFFSET);
            if (scale == 0) {
                bArr[i] = JSONB.Constants.BC_DECIMAL_LONG;
                this.off = JSONB.IO.writeInt64(bArr, i + 1, j2);
                return;
            }
            bArr[i] = JSONB.Constants.BC_DECIMAL;
            int writeInt32 = JSONB.IO.writeInt32(bArr, i + 1, scale);
            if (j2 >= -2147483648L && j2 <= 2147483647L) {
                writeInt642 = JSONB.IO.writeInt32(bArr, writeInt32, (int) j2);
            } else {
                writeInt642 = JSONB.IO.writeInt64(bArr, writeInt32, j2);
            }
            this.off = writeInt642;
            return;
        }
        BigInteger unscaledValue = bigDecimal.unscaledValue();
        if (scale == 0 && TypeUtils.isInt64(unscaledValue)) {
            bArr[i] = JSONB.Constants.BC_DECIMAL_LONG;
            this.off = JSONB.IO.writeInt64(bArr, i + 1, unscaledValue.longValue());
            return;
        }
        bArr[i] = JSONB.Constants.BC_DECIMAL;
        int writeInt322 = JSONB.IO.writeInt32(bArr, i + 1, scale);
        if (TypeUtils.isInt32(unscaledValue)) {
            writeInt64 = JSONB.IO.writeInt32(bArr, writeInt322, unscaledValue.intValue());
        } else if (TypeUtils.isInt64(unscaledValue)) {
            writeInt64 = JSONB.IO.writeInt64(bArr, writeInt322, unscaledValue.longValue());
        } else {
            this.off = writeInt322;
            writeBigInt(unscaledValue, 0L);
            return;
        }
        this.off = writeInt64;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeBool(boolean z) {
        writeRaw(z ? JSONB.Constants.BC_TRUE : JSONB.Constants.BC_FALSE);
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeBool(boolean[] zArr) {
        int i = this.off;
        byte[] bArr = this.bytes;
        int length = (zArr == null ? 1 : zArr.length + 5) + i;
        if (length > bArr.length) {
            bArr = grow(length);
        }
        this.off = JSONB.IO.writeBoolean(bArr, i, zArr);
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeReference(String str) {
        int i = this.off;
        grow1(i)[i] = JSONB.Constants.BC_REFERENCE;
        this.off = i + 1;
        writeString(str == this.lastReference ? "#-1" : str);
        this.lastReference = str;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeDateTime14(int i, int i2, int i3, int i4, int i5, int i6) {
        int i7 = this.off;
        byte[] bArr = this.bytes;
        int i8 = i7 + 9;
        if (i8 > bArr.length) {
            bArr = grow(i8);
        }
        IOUtils.putIntBE(bArr, i7, ((i & 65535) << 8) | (-1476395008) | i2);
        IOUtils.putIntBE(bArr, i7 + 4, (i3 << 24) | (i4 << 16) | (i5 << 8) | i6);
        bArr[i7 + 8] = 0;
        this.off = i8;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeDateTime19(int i, int i2, int i3, int i4, int i5, int i6) {
        writeDateTime14(i, i2, i3, i4, i5, i6);
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeDateTimeISO8601(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z) {
        throw new JSONException("unsupported operation");
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeDateYYYMMDD8(int i, int i2, int i3) {
        int i4 = this.off;
        byte[] bArr = this.bytes;
        int i5 = i4 + 5;
        if (i5 > bArr.length) {
            bArr = grow(i5);
        }
        bArr[i4] = JSONB.Constants.BC_LOCAL_DATE;
        IOUtils.putIntBE(bArr, i4 + 1, (i << 16) | (i2 << 8) | i3);
        this.off = i5;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeDateYYYMMDD10(int i, int i2, int i3) {
        writeDateYYYMMDD8(i, i2, i3);
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeTimeHHMMSS8(int i, int i2, int i3) {
        throw new JSONException("unsupported operation");
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeBase64(byte[] bArr) {
        throw new JSONException("UnsupportedOperation");
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeHex(byte[] bArr) {
        writeBinary(bArr);
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeRaw(char c) {
        throw new JSONException("UnsupportedOperation");
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeNameRaw(byte[] bArr) {
        writeRaw(bArr);
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeName2Raw(long j) {
        int i = this.off;
        byte[] bArr = this.bytes;
        int i2 = i + 8;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        JDKUtils.UNSAFE.putLong(bArr, JDKUtils.ARRAY_BYTE_BASE_OFFSET + i, j);
        this.off = i + 2;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeName3Raw(long j) {
        int i = this.off;
        byte[] bArr = this.bytes;
        int i2 = i + 8;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        JDKUtils.UNSAFE.putLong(bArr, JDKUtils.ARRAY_BYTE_BASE_OFFSET + i, j);
        this.off = i + 3;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeName4Raw(long j) {
        int i = this.off;
        byte[] bArr = this.bytes;
        int i2 = i + 8;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        JDKUtils.UNSAFE.putLong(bArr, JDKUtils.ARRAY_BYTE_BASE_OFFSET + i, j);
        this.off = i + 4;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeName5Raw(long j) {
        int i = this.off;
        byte[] bArr = this.bytes;
        int i2 = i + 8;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        JDKUtils.UNSAFE.putLong(bArr, JDKUtils.ARRAY_BYTE_BASE_OFFSET + i, j);
        this.off = i + 5;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeName6Raw(long j) {
        int i = this.off;
        byte[] bArr = this.bytes;
        int i2 = i + 8;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        JDKUtils.UNSAFE.putLong(bArr, JDKUtils.ARRAY_BYTE_BASE_OFFSET + i, j);
        this.off = i + 6;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeName7Raw(long j) {
        int i = this.off;
        byte[] bArr = this.bytes;
        int i2 = i + 8;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        JDKUtils.UNSAFE.putLong(bArr, JDKUtils.ARRAY_BYTE_BASE_OFFSET + i, j);
        this.off = i + 7;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeName8Raw(long j) {
        int i = this.off;
        byte[] bArr = this.bytes;
        int i2 = i + 8;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        JDKUtils.UNSAFE.putLong(bArr, JDKUtils.ARRAY_BYTE_BASE_OFFSET + i, j);
        this.off = i2;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeName9Raw(long j, int i) {
        int i2 = this.off;
        byte[] bArr = this.bytes;
        int i3 = i2 + 12;
        if (i3 > bArr.length) {
            bArr = grow(i3);
        }
        byte[] bArr2 = bArr;
        long j2 = i2;
        JDKUtils.UNSAFE.putLong(bArr2, JDKUtils.ARRAY_BYTE_BASE_OFFSET + j2, j);
        JDKUtils.UNSAFE.putInt(bArr2, JDKUtils.ARRAY_BYTE_BASE_OFFSET + j2 + 8, i);
        this.off = i2 + 9;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeName10Raw(long j, long j2) {
        int i = this.off;
        byte[] bArr = this.bytes;
        int i2 = i + 16;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        byte[] bArr2 = bArr;
        long j3 = i;
        JDKUtils.UNSAFE.putLong(bArr2, JDKUtils.ARRAY_BYTE_BASE_OFFSET + j3, j);
        JDKUtils.UNSAFE.putLong(bArr2, 8 + JDKUtils.ARRAY_BYTE_BASE_OFFSET + j3, j2);
        this.off = i + 10;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeName11Raw(long j, long j2) {
        int i = this.off;
        byte[] bArr = this.bytes;
        int i2 = i + 16;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        byte[] bArr2 = bArr;
        long j3 = i;
        JDKUtils.UNSAFE.putLong(bArr2, JDKUtils.ARRAY_BYTE_BASE_OFFSET + j3, j);
        JDKUtils.UNSAFE.putLong(bArr2, 8 + JDKUtils.ARRAY_BYTE_BASE_OFFSET + j3, j2);
        this.off = i + 11;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeName12Raw(long j, long j2) {
        int i = this.off;
        byte[] bArr = this.bytes;
        int i2 = i + 16;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        byte[] bArr2 = bArr;
        long j3 = i;
        JDKUtils.UNSAFE.putLong(bArr2, JDKUtils.ARRAY_BYTE_BASE_OFFSET + j3, j);
        JDKUtils.UNSAFE.putLong(bArr2, 8 + JDKUtils.ARRAY_BYTE_BASE_OFFSET + j3, j2);
        this.off = i + 12;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeName13Raw(long j, long j2) {
        int i = this.off;
        byte[] bArr = this.bytes;
        int i2 = i + 16;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        byte[] bArr2 = bArr;
        long j3 = i;
        JDKUtils.UNSAFE.putLong(bArr2, JDKUtils.ARRAY_BYTE_BASE_OFFSET + j3, j);
        JDKUtils.UNSAFE.putLong(bArr2, 8 + JDKUtils.ARRAY_BYTE_BASE_OFFSET + j3, j2);
        this.off = i + 13;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeName14Raw(long j, long j2) {
        int i = this.off;
        byte[] bArr = this.bytes;
        int i2 = i + 16;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        byte[] bArr2 = bArr;
        long j3 = i;
        JDKUtils.UNSAFE.putLong(bArr2, JDKUtils.ARRAY_BYTE_BASE_OFFSET + j3, j);
        JDKUtils.UNSAFE.putLong(bArr2, 8 + JDKUtils.ARRAY_BYTE_BASE_OFFSET + j3, j2);
        this.off = i + 14;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeName15Raw(long j, long j2) {
        int i = this.off;
        byte[] bArr = this.bytes;
        int i2 = i + 16;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        byte[] bArr2 = bArr;
        long j3 = i;
        JDKUtils.UNSAFE.putLong(bArr2, JDKUtils.ARRAY_BYTE_BASE_OFFSET + j3, j);
        JDKUtils.UNSAFE.putLong(bArr2, 8 + JDKUtils.ARRAY_BYTE_BASE_OFFSET + j3, j2);
        this.off = i + 15;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeName16Raw(long j, long j2) {
        int i = this.off;
        byte[] bArr = this.bytes;
        int i2 = i + 16;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        byte[] bArr2 = bArr;
        long j3 = i;
        JDKUtils.UNSAFE.putLong(bArr2, JDKUtils.ARRAY_BYTE_BASE_OFFSET + j3, j);
        JDKUtils.UNSAFE.putLong(bArr2, JDKUtils.ARRAY_BYTE_BASE_OFFSET + j3 + 8, j2);
        this.off = i2;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeNameRaw(char[] cArr) {
        throw new JSONException("UnsupportedOperation");
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeNameRaw(char[] cArr, int i, int i2) {
        throw new JSONException("UnsupportedOperation");
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeColon() {
        throw new JSONException("UnsupportedOperation");
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void write(List list) {
        if (list == null) {
            writeArrayNull();
            return;
        }
        startArray(list.size());
        for (int i = 0; i < list.size(); i++) {
            writeAny(list.get(i));
        }
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void write(Map map) {
        if (map == null) {
            writeNull();
            return;
        }
        startObject();
        for (Map.Entry entry : map.entrySet()) {
            writeAny(entry.getKey());
            writeAny(entry.getValue());
        }
        endObject();
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public byte[] getBytes() {
        return Arrays.copyOf(this.bytes, this.off);
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public int size() {
        return this.off;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public byte[] getBytes(Charset charset) {
        throw new JSONException("not support operator");
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public int flushTo(OutputStream outputStream) throws IOException {
        int i = this.off;
        outputStream.write(this.bytes, 0, this.off);
        this.off = 0;
        return i;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public int flushTo(OutputStream outputStream, Charset charset) {
        throw new JSONException("UnsupportedOperation");
    }

    public String toString() {
        if (this.off == 0) {
            return "<empty>";
        }
        JSONReader ofJSONB = JSONReader.ofJSONB(getBytes());
        JSONWriter of = JSONWriter.of();
        try {
            of.writeAny(ofJSONB.readAny());
            return of.toString();
        } catch (Exception unused) {
            return JSONB.typeName(this.bytes[0]) + ", bytes length " + this.off;
        }
    }
}
