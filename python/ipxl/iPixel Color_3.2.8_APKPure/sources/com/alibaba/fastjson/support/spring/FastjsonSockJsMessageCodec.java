package com.alibaba.fastjson.support.spring;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.support.config.FastJsonConfig;
import java.io.InputStream;
import org.springframework.web.socket.sockjs.frame.AbstractSockJsMessageCodec;

/* loaded from: classes2.dex */
public class FastjsonSockJsMessageCodec extends AbstractSockJsMessageCodec {
    private FastJsonConfig fastJsonConfig = new FastJsonConfig();

    public FastJsonConfig getFastJsonConfig() {
        return this.fastJsonConfig;
    }

    public void setFastJsonConfig(FastJsonConfig fastJsonConfig) {
        this.fastJsonConfig = fastJsonConfig;
    }

    public String[] decode(String str) {
        return (String[]) JSON.parseObject(str, String[].class);
    }

    public String[] decodeInputStream(InputStream inputStream) {
        return (String[]) JSON.parseObject(inputStream, String[].class, new JSONReader.Feature[0]);
    }

    protected char[] applyJsonQuoting(String str) {
        return str.toCharArray();
    }

    public String encode(String... strArr) {
        JSONWriter of = JSONWriter.of(this.fastJsonConfig.getWriterFeatures());
        if (of.utf8) {
            of.writeRaw(new byte[]{97});
        } else {
            of.writeRaw(new char[]{'a'});
        }
        of.startArray();
        for (int i = 0; i < strArr.length; i++) {
            if (i != 0) {
                of.writeComma();
            }
            of.writeString(strArr[i]);
        }
        of.endArray();
        return of.toString();
    }
}
