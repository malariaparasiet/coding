package com.alibaba.fastjson2.support.airlift;

import com.alibaba.fastjson2.JSONB;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.reader.ValueConsumer;
import com.alibaba.fastjson2.util.IOUtils;
import io.airlift.slice.Slice;
import io.airlift.slice.Slices;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class SliceValueConsumer implements ValueConsumer {
    public Slice slice;

    @Override // com.alibaba.fastjson2.reader.ValueConsumer
    public void accept(byte[] bArr, int i, int i2) {
        this.slice = Slices.wrappedBuffer(bArr, i, i2);
    }

    @Override // com.alibaba.fastjson2.reader.ValueConsumer
    public void acceptNull() {
        this.slice = null;
    }

    @Override // com.alibaba.fastjson2.reader.ValueConsumer
    public void accept(int i) {
        int stringSize = i < 0 ? IOUtils.stringSize(-i) + 1 : IOUtils.stringSize(i);
        byte[] bArr = new byte[stringSize];
        IOUtils.getChars(i, stringSize, bArr);
        this.slice = Slices.wrappedBuffer(bArr);
    }

    @Override // com.alibaba.fastjson2.reader.ValueConsumer
    public void accept(boolean z) {
        byte[] bArr;
        if (z) {
            bArr = new byte[]{116, 114, 117, 101};
        } else {
            bArr = new byte[]{102, 97, 108, 115, 101};
        }
        this.slice = Slices.wrappedBuffer(bArr);
    }

    @Override // com.alibaba.fastjson2.reader.ValueConsumer
    public void accept(long j) {
        int stringSize = j < 0 ? IOUtils.stringSize(-j) + 1 : IOUtils.stringSize(j);
        byte[] bArr = new byte[stringSize];
        IOUtils.getChars(j, stringSize, bArr);
        this.slice = Slices.wrappedBuffer(bArr);
    }

    @Override // com.alibaba.fastjson2.reader.ValueConsumer
    public void accept(Number number) {
        if (number == null) {
            this.slice = null;
            return;
        }
        if (number instanceof Long) {
            long longValue = number.longValue();
            int stringSize = longValue < 0 ? IOUtils.stringSize(-longValue) + 1 : IOUtils.stringSize(longValue);
            byte[] bArr = new byte[stringSize];
            IOUtils.getChars(longValue, stringSize, bArr);
            this.slice = Slices.wrappedBuffer(bArr);
            return;
        }
        if ((number instanceof Integer) || (number instanceof Short) || (number instanceof Byte)) {
            int intValue = number.intValue();
            int stringSize2 = intValue < 0 ? IOUtils.stringSize(-intValue) + 1 : IOUtils.stringSize(intValue);
            byte[] bArr2 = new byte[stringSize2];
            IOUtils.getChars(intValue, stringSize2, bArr2);
            this.slice = Slices.wrappedBuffer(bArr2);
            return;
        }
        this.slice = Slices.utf8Slice(number.toString());
    }

    @Override // com.alibaba.fastjson2.reader.ValueConsumer
    public void accept(String str) {
        this.slice = Slices.utf8Slice(str);
    }

    @Override // com.alibaba.fastjson2.reader.ValueConsumer
    public void accept(Map map) {
        if (map.isEmpty()) {
            this.slice = Slices.wrappedBuffer(new byte[]{JSONB.Constants.BC_STR_UTF16, JSONB.Constants.BC_STR_UTF16BE});
            return;
        }
        JSONWriter ofUTF8 = JSONWriter.ofUTF8();
        try {
            ofUTF8.write((Map<?, ?>) map);
            this.slice = Slices.wrappedBuffer(ofUTF8.getBytes());
            if (ofUTF8 != null) {
                ofUTF8.close();
            }
        } catch (Throwable th) {
            if (ofUTF8 != null) {
                try {
                    ofUTF8.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    @Override // com.alibaba.fastjson2.reader.ValueConsumer
    public void accept(List list) {
        if (list.isEmpty()) {
            this.slice = Slices.wrappedBuffer(new byte[]{91, 93});
            return;
        }
        JSONWriter ofUTF8 = JSONWriter.ofUTF8();
        try {
            ofUTF8.write(list);
            this.slice = Slices.wrappedBuffer(ofUTF8.getBytes());
            if (ofUTF8 != null) {
                ofUTF8.close();
            }
        } catch (Throwable th) {
            if (ofUTF8 != null) {
                try {
                    ofUTF8.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }
}
