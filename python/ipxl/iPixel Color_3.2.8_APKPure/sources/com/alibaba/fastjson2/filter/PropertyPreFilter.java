package com.alibaba.fastjson2.filter;

import com.alibaba.fastjson2.JSONWriter;

/* loaded from: classes2.dex */
public interface PropertyPreFilter extends Filter {
    boolean process(JSONWriter jSONWriter, Object obj, String str);
}
