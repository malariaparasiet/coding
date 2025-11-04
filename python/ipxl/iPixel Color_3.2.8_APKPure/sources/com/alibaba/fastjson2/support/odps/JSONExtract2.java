package com.alibaba.fastjson2.support.odps;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONPath;
import com.alibaba.fastjson2.JSONReader;
import com.aliyun.odps.udf.UDF;

/* loaded from: classes2.dex */
public class JSONExtract2 extends UDF {
    public String evaluate(String str, String str2) {
        Object obj;
        JSONReader of;
        if (str != null && !str.isEmpty()) {
            try {
                of = JSONReader.of(str);
            } catch (Throwable unused) {
                obj = null;
            }
            try {
                obj = JSONPath.of(str2).extract(of);
                if (of != null) {
                    try {
                        of.close();
                    } catch (Throwable unused2) {
                    }
                }
                if (obj == null) {
                    return null;
                }
                try {
                    return JSON.toJSONString(obj);
                } catch (Exception unused3) {
                }
            } finally {
            }
        }
        return null;
    }
}
