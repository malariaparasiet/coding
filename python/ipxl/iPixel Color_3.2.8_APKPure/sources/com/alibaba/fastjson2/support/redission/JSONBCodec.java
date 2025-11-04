package com.alibaba.fastjson2.support.redission;

import com.alibaba.fastjson2.JSONFactory;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.JSONWriter;
import java.lang.reflect.Type;
import org.redisson.client.codec.BaseCodec;
import org.redisson.client.protocol.Decoder;
import org.redisson.client.protocol.Encoder;

/* loaded from: classes2.dex */
public class JSONBCodec extends BaseCodec {
    final JSONBDecoder decoder;
    final JSONBEncoder encoder;

    public JSONBCodec(Type type) {
        this(JSONFactory.createWriteContext(JSONWriter.Feature.FieldBased), JSONFactory.createReadContext(JSONReader.Feature.FieldBased), type);
    }

    public JSONBCodec(String... strArr) {
        this(JSONFactory.createWriteContext(JSONWriter.Feature.FieldBased, JSONWriter.Feature.WriteClassName), JSONFactory.createReadContext(JSONReader.autoTypeFilter(strArr), JSONReader.Feature.FieldBased), null);
    }

    public JSONBCodec(JSONWriter.Context context, JSONReader.Context context2) {
        this(context, context2, null);
    }

    public JSONBCodec(JSONWriter.Context context, JSONReader.Context context2, Type type) {
        this(new JSONBEncoder(context), new JSONBDecoder(context2, type));
    }

    protected JSONBCodec(JSONBEncoder jSONBEncoder, JSONBDecoder jSONBDecoder) {
        this.encoder = jSONBEncoder;
        this.decoder = jSONBDecoder;
    }

    public Decoder<Object> getValueDecoder() {
        return this.decoder;
    }

    public Encoder getValueEncoder() {
        return this.encoder;
    }

    static final class JSONBEncoder implements Encoder {
        final JSONWriter.Context context;

        public JSONBEncoder(JSONWriter.Context context) {
            this.context = context;
        }

        /* JADX WARN: Removed duplicated region for block: B:9:0x0051 A[Catch: NumberFormatException -> 0x0065, NullPointerException -> 0x0067, TRY_ENTER, TRY_LEAVE, TryCatch #7 {NullPointerException -> 0x0067, NumberFormatException -> 0x0065, blocks: (B:9:0x0051, B:22:0x0064, B:21:0x0061, B:17:0x005c), top: B:3:0x0006, inners: #0 }] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.netty.buffer.ByteBuf encode(java.lang.Object r9) throws java.io.IOException {
            /*
                r8 = this;
                com.alibaba.fastjson2.JSONWriter$Context r0 = r8.context     // Catch: java.lang.NumberFormatException -> L69 java.lang.NullPointerException -> L6b
                com.alibaba.fastjson2.JSONWriter r2 = com.alibaba.fastjson2.JSONWriter.ofJSONB(r0)     // Catch: java.lang.NumberFormatException -> L69 java.lang.NullPointerException -> L6b
                if (r9 != 0) goto Ld
                r2.writeNull()     // Catch: java.lang.Throwable -> L57
            Lb:
                r3 = r9
                goto L39
            Ld:
                r2.setRootObject(r9)     // Catch: java.lang.Throwable -> L57
                java.lang.Class r0 = r9.getClass()     // Catch: java.lang.Throwable -> L57
                java.lang.Class<com.alibaba.fastjson2.JSONObject> r1 = com.alibaba.fastjson2.JSONObject.class
                if (r0 != r1) goto L2b
                com.alibaba.fastjson2.JSONWriter$Context r1 = r8.context     // Catch: java.lang.Throwable -> L57
                long r3 = r1.getFeatures()     // Catch: java.lang.Throwable -> L57
                r5 = 0
                int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                if (r1 != 0) goto L2b
                r0 = r9
                com.alibaba.fastjson2.JSONObject r0 = (com.alibaba.fastjson2.JSONObject) r0     // Catch: java.lang.Throwable -> L57
                r2.write(r0)     // Catch: java.lang.Throwable -> L57
                goto Lb
            L2b:
                com.alibaba.fastjson2.JSONWriter$Context r1 = r8.context     // Catch: java.lang.Throwable -> L57
                com.alibaba.fastjson2.writer.ObjectWriter r1 = r1.getObjectWriter(r0, r0)     // Catch: java.lang.Throwable -> L57
                r5 = 0
                r6 = 0
                r4 = 0
                r3 = r9
                r1.write(r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L55
            L39:
                int r9 = r2.size()     // Catch: java.lang.Throwable -> L55
                io.netty.buffer.ByteBufAllocator r0 = io.netty.buffer.ByteBufAllocator.DEFAULT     // Catch: java.lang.Throwable -> L55
                io.netty.buffer.ByteBuf r9 = r0.buffer(r9)     // Catch: java.lang.Throwable -> L55
                io.netty.buffer.ByteBufOutputStream r0 = new io.netty.buffer.ByteBufOutputStream     // Catch: java.lang.Throwable -> L55
                r0.<init>(r9)     // Catch: java.lang.Throwable -> L55
                r2.flushTo(r0)     // Catch: java.lang.Throwable -> L55
                io.netty.buffer.ByteBuf r9 = r0.buffer()     // Catch: java.lang.Throwable -> L55
                if (r2 == 0) goto L54
                r2.close()     // Catch: java.lang.NumberFormatException -> L65 java.lang.NullPointerException -> L67
            L54:
                return r9
            L55:
                r0 = move-exception
                goto L59
            L57:
                r0 = move-exception
                r3 = r9
            L59:
                r9 = r0
                if (r2 == 0) goto L64
                r2.close()     // Catch: java.lang.Throwable -> L60
                goto L64
            L60:
                r0 = move-exception
                r9.addSuppressed(r0)     // Catch: java.lang.NumberFormatException -> L65 java.lang.NullPointerException -> L67
            L64:
                throw r9     // Catch: java.lang.NumberFormatException -> L65 java.lang.NullPointerException -> L67
            L65:
                r0 = move-exception
                goto L6d
            L67:
                r0 = move-exception
                goto L6d
            L69:
                r0 = move-exception
                goto L6c
            L6b:
                r0 = move-exception
            L6c:
                r3 = r9
            L6d:
                r9 = r0
                com.alibaba.fastjson2.JSONException r0 = new com.alibaba.fastjson2.JSONException
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                java.lang.String r2 = "JSON#toJSONString cannot serialize '"
                r1.<init>(r2)
                java.lang.StringBuilder r1 = r1.append(r3)
                java.lang.String r2 = "'"
                java.lang.StringBuilder r1 = r1.append(r2)
                java.lang.String r1 = r1.toString()
                r0.<init>(r1, r9)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.support.redission.JSONBCodec.JSONBEncoder.encode(java.lang.Object):io.netty.buffer.ByteBuf");
        }
    }

    protected static final class JSONBDecoder implements Decoder<Object> {
        final JSONReader.Context context;
        final Type valueType;

        public JSONBDecoder(JSONReader.Context context, Type type) {
            this.context = context;
            this.valueType = type;
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x001f  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public java.lang.Object decode(io.netty.buffer.ByteBuf r2, org.redisson.client.handler.State r3) throws java.io.IOException {
            /*
                r1 = this;
                io.netty.buffer.ByteBufInputStream r3 = new io.netty.buffer.ByteBufInputStream
                r3.<init>(r2)
                com.alibaba.fastjson2.JSONReader$Context r2 = r1.context
                com.alibaba.fastjson2.JSONReader r2 = com.alibaba.fastjson2.JSONReader.ofJSONB(r3, r2)
                java.lang.reflect.Type r3 = r1.valueType     // Catch: java.lang.Throwable -> L23
                if (r3 == 0) goto L19
                java.lang.Class<java.lang.Object> r0 = java.lang.Object.class
                if (r3 != r0) goto L14
                goto L19
            L14:
                java.lang.Object r3 = r2.read(r3)     // Catch: java.lang.Throwable -> L23
                goto L1d
            L19:
                java.lang.Object r3 = r2.readAny()     // Catch: java.lang.Throwable -> L23
            L1d:
                if (r2 == 0) goto L22
                r2.close()
            L22:
                return r3
            L23:
                r3 = move-exception
                if (r2 == 0) goto L2e
                r2.close()     // Catch: java.lang.Throwable -> L2a
                goto L2e
            L2a:
                r2 = move-exception
                r3.addSuppressed(r2)
            L2e:
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.support.redission.JSONBCodec.JSONBDecoder.decode(io.netty.buffer.ByteBuf, org.redisson.client.handler.State):java.lang.Object");
        }
    }
}
