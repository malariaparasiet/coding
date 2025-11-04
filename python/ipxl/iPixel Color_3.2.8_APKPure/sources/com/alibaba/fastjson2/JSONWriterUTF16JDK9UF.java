package com.alibaba.fastjson2;

import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.util.IOUtils;
import com.alibaba.fastjson2.util.JDKUtils;

/* loaded from: classes2.dex */
final class JSONWriterUTF16JDK9UF extends JSONWriterUTF16 {
    JSONWriterUTF16JDK9UF(JSONWriter.Context context) {
        super(context);
    }

    @Override // com.alibaba.fastjson2.JSONWriterUTF16, com.alibaba.fastjson2.JSONWriter
    public void writeString(String str) {
        if (str == null) {
            writeStringNull();
            return;
        }
        byte[] apply = JDKUtils.STRING_VALUE.apply(str);
        if (JDKUtils.STRING_CODER.applyAsInt(str) == 0) {
            writeStringLatin1(apply);
        } else {
            writeStringUTF16(apply);
        }
    }

    @Override // com.alibaba.fastjson2.JSONWriterUTF16, com.alibaba.fastjson2.JSONWriter
    public void writeBool(boolean z) {
        int putBoolean;
        int i = this.off + 5;
        if (i >= this.chars.length) {
            grow0(i);
        }
        char[] cArr = this.chars;
        int i2 = this.off;
        if ((this.context.features & JSONWriter.Feature.WriteBooleanAsNumber.mask) != 0) {
            putBoolean = i2 + 1;
            cArr[i2] = z ? '1' : '0';
        } else {
            putBoolean = IOUtils.putBoolean(cArr, i2, z);
        }
        this.off = putBoolean;
    }
}
