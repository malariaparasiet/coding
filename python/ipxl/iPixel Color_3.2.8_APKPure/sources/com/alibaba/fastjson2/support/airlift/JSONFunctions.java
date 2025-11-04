package com.alibaba.fastjson2.support.airlift;

import com.alibaba.fastjson2.JSONPath;
import com.alibaba.fastjson2.JSONReader;
import io.airlift.slice.Slice;

/* loaded from: classes2.dex */
public class JSONFunctions {
    public static Slice jsonExtract(Slice slice, JSONPath jSONPath) {
        JSONReader of = JSONReader.of(slice.byteArray(), slice.byteArrayOffset(), slice.length());
        SliceValueConsumer sliceValueConsumer = new SliceValueConsumer();
        jSONPath.extract(of, sliceValueConsumer);
        return sliceValueConsumer.slice;
    }
}
