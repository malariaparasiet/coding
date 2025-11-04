package com.alibaba.fastjson2.writer;

import com.alibaba.fastjson2.JSONB;
import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.util.Fnv;
import com.alibaba.fastjson2.util.IOUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.function.Function;
import java.util.zip.GZIPOutputStream;

/* loaded from: classes2.dex */
final class ObjectWriterImplInt8ValueArray extends ObjectWriterPrimitiveImpl {
    private final Function<Object, byte[]> function;
    static final ObjectWriterImplInt8ValueArray INSTANCE = new ObjectWriterImplInt8ValueArray(null);
    static final byte[] JSONB_TYPE_NAME_BYTES = JSONB.toBytes("[B");
    static final long JSONB_TYPE_HASH = Fnv.hashCode64("[B");

    public ObjectWriterImplInt8ValueArray(Function<Object, byte[]> function) {
        this.function = function;
    }

    @Override // com.alibaba.fastjson2.writer.ObjectWriter
    public void writeJSONB(JSONWriter jSONWriter, Object obj, Object obj2, Type type, long j) {
        byte[] bArr;
        if (jSONWriter.isWriteTypeInfo(obj, type)) {
            if (obj == byte[].class) {
                jSONWriter.writeTypeName(JSONB_TYPE_NAME_BYTES, JSONB_TYPE_HASH);
            } else {
                jSONWriter.writeTypeName(obj.getClass().getName());
            }
        }
        Function<Object, byte[]> function = this.function;
        if (function != null && obj != null) {
            bArr = function.apply(obj);
        } else {
            bArr = (byte[]) obj;
        }
        jSONWriter.writeBinary(bArr);
    }

    @Override // com.alibaba.fastjson2.writer.ObjectWriter
    public void write(JSONWriter jSONWriter, Object obj, Object obj2, Type type, long j) {
        byte[] bArr;
        GZIPOutputStream gZIPOutputStream;
        if (obj == null) {
            jSONWriter.writeArrayNull();
            return;
        }
        Function<Object, byte[]> function = this.function;
        if (function != null) {
            bArr = function.apply(obj);
        } else {
            bArr = (byte[]) obj;
        }
        String dateFormat = jSONWriter.context.getDateFormat();
        GZIPOutputStream gZIPOutputStream2 = null;
        if ("millis".equals(dateFormat)) {
            dateFormat = null;
        }
        if ("gzip".equals(dateFormat) || "gzip,base64".equals(dateFormat)) {
            try {
                try {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    if (bArr.length < 512) {
                        gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream, bArr.length);
                    } else {
                        gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                    }
                    gZIPOutputStream2 = gZIPOutputStream;
                    gZIPOutputStream2.write(bArr);
                    gZIPOutputStream2.finish();
                    bArr = byteArrayOutputStream.toByteArray();
                } catch (IOException e) {
                    throw new JSONException("write gzipBytes error", e);
                }
            } finally {
                IOUtils.close(gZIPOutputStream2);
            }
        }
        if ("base64".equals(dateFormat) || "gzip,base64".equals(dateFormat) || (jSONWriter.getFeatures(j) & JSONWriter.Feature.WriteByteArrayAsBase64.mask) != 0) {
            jSONWriter.writeBase64(bArr);
        } else {
            jSONWriter.writeInt8(bArr);
        }
    }
}
