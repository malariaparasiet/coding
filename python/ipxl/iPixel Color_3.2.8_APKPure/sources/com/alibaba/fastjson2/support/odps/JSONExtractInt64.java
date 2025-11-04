package com.alibaba.fastjson2.support.odps;

import com.alibaba.fastjson2.JSONPath;
import com.alibaba.fastjson2.JSONReader;
import com.aliyun.odps.io.LongWritable;
import com.aliyun.odps.io.Text;
import com.aliyun.odps.udf.UDF;
import java.nio.charset.StandardCharsets;

/* loaded from: classes2.dex */
public class JSONExtractInt64 extends UDF {
    private final JSONPath path;
    private final LongWritable result = new LongWritable();

    public JSONExtractInt64(String str) {
        this.path = JSONPath.of(str);
    }

    public LongWritable eval(Text text) {
        JSONReader of = JSONReader.of(text.getBytes(), 0, text.getLength(), StandardCharsets.UTF_8);
        long extractInt64Value = this.path.extractInt64Value(of);
        if (of.wasNull()) {
            return null;
        }
        this.result.set(extractInt64Value);
        return this.result;
    }
}
