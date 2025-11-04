package com.squareup.gifencoder;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
final class LzwEncoder {
    private static final List<Integer> CLEAR_CODE = Collections.singletonList(-1);
    private static final List<Integer> END_OF_INFO = Collections.singletonList(-2);
    private static final int MAX_CODE_TABLE_SIZE = 4096;
    private int codeSize;
    private Map<List<Integer>, Integer> codeTable;
    private final int minimumCodeSize;
    private final BitSet outputBits = new BitSet();
    private int position = 0;
    private List<Integer> indexBuffer = new ArrayList();

    private static int computeMinimumCodeSize(int i) {
        int i2 = 2;
        while (i > (1 << i2)) {
            i2++;
        }
        return i2;
    }

    LzwEncoder(int i) {
        if (!GifMath.isPowerOfTwo(i)) {
            throw new IllegalArgumentException("Color table size must be a power of 2");
        }
        this.minimumCodeSize = computeMinimumCodeSize(i);
        resetCodeTableAndCodeSize();
    }

    int getMinimumCodeSize() {
        return this.minimumCodeSize;
    }

    byte[] encode(int[] iArr) {
        writeCode(this.codeTable.get(CLEAR_CODE).intValue());
        for (int i : iArr) {
            processIndex(i);
        }
        writeCode(this.codeTable.get(this.indexBuffer).intValue());
        writeCode(this.codeTable.get(END_OF_INFO).intValue());
        return toBytes();
    }

    private void processIndex(int i) {
        List<Integer> append = append(this.indexBuffer, Integer.valueOf(i));
        if (this.codeTable.containsKey(append)) {
            this.indexBuffer = append;
            return;
        }
        writeCode(this.codeTable.get(this.indexBuffer).intValue());
        if (this.codeTable.size() == 4096) {
            writeCode(this.codeTable.get(CLEAR_CODE).intValue());
            resetCodeTableAndCodeSize();
        } else {
            addCodeToTable(append);
        }
        this.indexBuffer = Collections.singletonList(Integer.valueOf(i));
    }

    private void writeCode(int i) {
        for (int i2 = 0; i2 < this.codeSize; i2++) {
            boolean z = true;
            if (((i >>> i2) & 1) == 0) {
                z = false;
            }
            BitSet bitSet = this.outputBits;
            int i3 = this.position;
            this.position = i3 + 1;
            bitSet.set(i3, z);
        }
    }

    private byte[] toBytes() {
        int i = this.position;
        byte[] bArr = new byte[(i + 7) / 8];
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = i2 / 8;
            bArr[i3] = (byte) (((this.outputBits.get(i2) ? 1 : 0) << (i2 % 8)) | bArr[i3]);
        }
        return bArr;
    }

    private void addCodeToTable(List<Integer> list) {
        int size = this.codeTable.size();
        this.codeTable.put(list, Integer.valueOf(size));
        int i = this.codeSize;
        if (size == (1 << i)) {
            this.codeSize = i + 1;
        }
    }

    private void resetCodeTableAndCodeSize() {
        this.codeTable = defaultCodeTable();
        this.codeSize = this.minimumCodeSize + 1;
    }

    private Map<List<Integer>, Integer> defaultCodeTable() {
        HashMap hashMap = new HashMap();
        int i = 1 << this.minimumCodeSize;
        for (int i2 = 0; i2 < i; i2++) {
            hashMap.put(Collections.singletonList(Integer.valueOf(i2)), Integer.valueOf(i2));
        }
        hashMap.put(CLEAR_CODE, Integer.valueOf(hashMap.size()));
        hashMap.put(END_OF_INFO, Integer.valueOf(hashMap.size()));
        return hashMap;
    }

    private static <T> List<T> append(List<T> list, T t) {
        ArrayList arrayList = new ArrayList(list);
        arrayList.add(t);
        return arrayList;
    }
}
