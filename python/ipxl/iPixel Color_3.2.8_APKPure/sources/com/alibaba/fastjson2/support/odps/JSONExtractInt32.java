package com.alibaba.fastjson2.support.odps;

import com.alibaba.fastjson2.JSONPath;
import com.alibaba.fastjson2.JSONReader;
import com.aliyun.odps.io.IntWritable;
import com.aliyun.odps.io.Text;
import com.aliyun.odps.udf.UDF;
import java.nio.charset.StandardCharsets;

/* loaded from: classes2.dex */
public class JSONExtractInt32 extends UDF {
    private final JSONPath path;
    private final IntWritable result = new IntWritable();

    public JSONExtractInt32(String str) {
        this.path = JSONPath.of(str);
    }

    public IntWritable eval(Text text) {
        JSONReader of = JSONReader.of(text.getBytes(), 0, text.getLength(), StandardCharsets.UTF_8);
        int extractInt32Value = this.path.extractInt32Value(of);
        if (of.wasNull()) {
            return null;
        }
        this.result.set(extractInt32Value);
        return this.result;
    }
}
