package com.alibaba.fastjson2;

import com.alibaba.fastjson2.JSONFactory;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.filter.AfterFilter;
import com.alibaba.fastjson2.filter.BeforeFilter;
import com.alibaba.fastjson2.filter.ContextNameFilter;
import com.alibaba.fastjson2.filter.ContextValueFilter;
import com.alibaba.fastjson2.filter.Filter;
import com.alibaba.fastjson2.filter.LabelFilter;
import com.alibaba.fastjson2.filter.NameFilter;
import com.alibaba.fastjson2.filter.PropertyFilter;
import com.alibaba.fastjson2.filter.PropertyPreFilter;
import com.alibaba.fastjson2.filter.ValueFilter;
import com.alibaba.fastjson2.modules.ObjectReaderModule;
import com.alibaba.fastjson2.modules.ObjectWriterModule;
import com.alibaba.fastjson2.reader.FieldReader;
import com.alibaba.fastjson2.reader.ObjectReader;
import com.alibaba.fastjson2.reader.ObjectReaderBean;
import com.alibaba.fastjson2.reader.ObjectReaderNoneDefaultConstructor;
import com.alibaba.fastjson2.util.DateUtils;
import com.alibaba.fastjson2.util.MultiType;
import com.alibaba.fastjson2.util.TypeUtils;
import com.alibaba.fastjson2.writer.FieldWriter;
import com.alibaba.fastjson2.writer.ObjectWriter;
import com.alibaba.fastjson2.writer.ObjectWriterAdapter;
import com.alibaba.fastjson2.writer.ObjectWriterProvider;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/* loaded from: classes2.dex */
public interface JSON {
    public static final String VERSION = "2.0.58";

    /* JADX WARN: Code restructure failed: missing block: B:28:0x007e, code lost:
    
        r3.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static java.lang.Object parse(java.lang.String r10) {
        /*
            if (r10 == 0) goto L8f
            boolean r0 = r10.isEmpty()
            if (r0 == 0) goto La
            goto L8f
        La:
            com.alibaba.fastjson2.reader.ObjectReaderProvider r0 = com.alibaba.fastjson2.JSONFactory.getDefaultObjectReaderProvider()
            com.alibaba.fastjson2.JSONReader$Context r1 = new com.alibaba.fastjson2.JSONReader$Context
            r1.<init>(r0)
            com.alibaba.fastjson2.JSONReader r3 = com.alibaba.fastjson2.JSONReader.of(r10, r1)
            char r10 = r3.current()     // Catch: java.lang.Throwable -> L82
            java.util.function.Supplier<java.util.Map> r2 = r1.objectSupplier     // Catch: java.lang.Throwable -> L82
            r8 = 0
            if (r2 != 0) goto L4f
            long r4 = r1.features     // Catch: java.lang.Throwable -> L82
            com.alibaba.fastjson2.JSONReader$Feature r2 = com.alibaba.fastjson2.JSONReader.Feature.UseNativeObject     // Catch: java.lang.Throwable -> L82
            long r6 = r2.mask     // Catch: java.lang.Throwable -> L82
            long r4 = r4 & r6
            int r2 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r2 != 0) goto L4f
            r2 = 123(0x7b, float:1.72E-43)
            if (r10 == r2) goto L34
            r4 = 91
            if (r10 != r4) goto L4f
        L34:
            if (r10 != r2) goto L3f
            com.alibaba.fastjson2.JSONObject r10 = new com.alibaba.fastjson2.JSONObject     // Catch: java.lang.Throwable -> L82
            r10.<init>()     // Catch: java.lang.Throwable -> L82
            r3.read(r10, r8)     // Catch: java.lang.Throwable -> L82
            goto L47
        L3f:
            com.alibaba.fastjson2.JSONArray r10 = new com.alibaba.fastjson2.JSONArray     // Catch: java.lang.Throwable -> L82
            r10.<init>()     // Catch: java.lang.Throwable -> L82
            r3.read(r10)     // Catch: java.lang.Throwable -> L82
        L47:
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r0 = r3.resolveTasks     // Catch: java.lang.Throwable -> L82
            if (r0 == 0) goto L5e
            r3.handleResolveTasks(r10)     // Catch: java.lang.Throwable -> L82
            goto L5e
        L4f:
            java.lang.Class<java.lang.Object> r10 = java.lang.Object.class
            r2 = 0
            com.alibaba.fastjson2.reader.ObjectReader r2 = r0.getObjectReader(r10, r2)     // Catch: java.lang.Throwable -> L82
            r5 = 0
            r6 = 0
            r4 = 0
            java.lang.Object r10 = r2.readObject(r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L82
        L5e:
            char r0 = r3.ch     // Catch: java.lang.Throwable -> L82
            r2 = 26
            if (r0 == r2) goto L7c
            long r0 = r1.features     // Catch: java.lang.Throwable -> L82
            com.alibaba.fastjson2.JSONReader$Feature r2 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L82
            long r4 = r2.mask     // Catch: java.lang.Throwable -> L82
            long r0 = r0 & r4
            int r0 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r0 == 0) goto L70
            goto L7c
        L70:
            com.alibaba.fastjson2.JSONException r10 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L82
            java.lang.String r0 = "input not end"
            java.lang.String r0 = r3.info(r0)     // Catch: java.lang.Throwable -> L82
            r10.<init>(r0)     // Catch: java.lang.Throwable -> L82
            throw r10     // Catch: java.lang.Throwable -> L82
        L7c:
            if (r3 == 0) goto L81
            r3.close()
        L81:
            return r10
        L82:
            r0 = move-exception
            r10 = r0
            if (r3 == 0) goto L8e
            r3.close()     // Catch: java.lang.Throwable -> L8a
            goto L8e
        L8a:
            r0 = move-exception
            r10.addSuppressed(r0)
        L8e:
            throw r10
        L8f:
            r10 = 0
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parse(java.lang.String):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x004a, code lost:
    
        r5.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static java.lang.Object parse(java.lang.String r10, com.alibaba.fastjson2.JSONReader.Feature... r11) {
        /*
            if (r10 == 0) goto L5c
            boolean r0 = r10.isEmpty()
            if (r0 == 0) goto L9
            goto L5c
        L9:
            com.alibaba.fastjson2.reader.ObjectReaderProvider r0 = com.alibaba.fastjson2.JSONFactory.getDefaultObjectReaderProvider()
            com.alibaba.fastjson2.JSONReader$Context r1 = new com.alibaba.fastjson2.JSONReader$Context
            r1.<init>(r0, r11)
            java.lang.Class<java.lang.Object> r2 = java.lang.Object.class
            r3 = 0
            com.alibaba.fastjson2.reader.ObjectReader r4 = r0.getObjectReader(r2, r3)
            com.alibaba.fastjson2.JSONReader r5 = com.alibaba.fastjson2.JSONReader.of(r10, r1)
            r1.config(r11)     // Catch: java.lang.Throwable -> L4e
            r7 = 0
            r8 = 0
            r6 = 0
            java.lang.Object r10 = r4.readObject(r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L4e
            char r11 = r5.ch     // Catch: java.lang.Throwable -> L4e
            r0 = 26
            if (r11 == r0) goto L48
            long r0 = r1.features     // Catch: java.lang.Throwable -> L4e
            com.alibaba.fastjson2.JSONReader$Feature r11 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L4e
            long r2 = r11.mask     // Catch: java.lang.Throwable -> L4e
            long r0 = r0 & r2
            r2 = 0
            int r11 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r11 == 0) goto L3c
            goto L48
        L3c:
            com.alibaba.fastjson2.JSONException r10 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L4e
            java.lang.String r11 = "input not end"
            java.lang.String r11 = r5.info(r11)     // Catch: java.lang.Throwable -> L4e
            r10.<init>(r11)     // Catch: java.lang.Throwable -> L4e
            throw r10     // Catch: java.lang.Throwable -> L4e
        L48:
            if (r5 == 0) goto L4d
            r5.close()
        L4d:
            return r10
        L4e:
            r0 = move-exception
            r10 = r0
            if (r5 == 0) goto L5b
            r5.close()     // Catch: java.lang.Throwable -> L56
            goto L5b
        L56:
            r0 = move-exception
            r11 = r0
            r10.addSuppressed(r11)
        L5b:
            throw r10
        L5c:
            r10 = 0
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parse(java.lang.String, com.alibaba.fastjson2.JSONReader$Feature[]):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0049, code lost:
    
        r4.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static java.lang.Object parse(java.lang.String r9, int r10, int r11, com.alibaba.fastjson2.JSONReader.Feature... r12) {
        /*
            if (r9 == 0) goto L5b
            boolean r0 = r9.isEmpty()
            if (r0 != 0) goto L5b
            if (r11 != 0) goto Lb
            goto L5b
        Lb:
            com.alibaba.fastjson2.reader.ObjectReaderProvider r0 = com.alibaba.fastjson2.JSONFactory.getDefaultObjectReaderProvider()
            com.alibaba.fastjson2.JSONReader$Context r1 = new com.alibaba.fastjson2.JSONReader$Context
            r1.<init>(r0, r12)
            java.lang.Class<java.lang.Object> r12 = java.lang.Object.class
            r2 = 0
            com.alibaba.fastjson2.reader.ObjectReader r3 = r0.getObjectReader(r12, r2)
            com.alibaba.fastjson2.JSONReader r4 = com.alibaba.fastjson2.JSONReader.of(r9, r10, r11, r1)
            r6 = 0
            r7 = 0
            r5 = 0
            java.lang.Object r9 = r3.readObject(r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L4d
            char r10 = r4.ch     // Catch: java.lang.Throwable -> L4d
            r11 = 26
            if (r10 == r11) goto L47
            long r10 = r1.features     // Catch: java.lang.Throwable -> L4d
            com.alibaba.fastjson2.JSONReader$Feature r12 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L4d
            long r0 = r12.mask     // Catch: java.lang.Throwable -> L4d
            long r10 = r10 & r0
            r0 = 0
            int r10 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r10 == 0) goto L3b
            goto L47
        L3b:
            com.alibaba.fastjson2.JSONException r9 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L4d
            java.lang.String r10 = "input not end"
            java.lang.String r10 = r4.info(r10)     // Catch: java.lang.Throwable -> L4d
            r9.<init>(r10)     // Catch: java.lang.Throwable -> L4d
            throw r9     // Catch: java.lang.Throwable -> L4d
        L47:
            if (r4 == 0) goto L4c
            r4.close()
        L4c:
            return r9
        L4d:
            r0 = move-exception
            r9 = r0
            if (r4 == 0) goto L5a
            r4.close()     // Catch: java.lang.Throwable -> L55
            goto L5a
        L55:
            r0 = move-exception
            r10 = r0
            r9.addSuppressed(r10)
        L5a:
            throw r9
        L5b:
            r9 = 0
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parse(java.lang.String, int, int, com.alibaba.fastjson2.JSONReader$Feature[]):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0040, code lost:
    
        r4.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static java.lang.Object parse(java.lang.String r9, com.alibaba.fastjson2.JSONReader.Context r10) {
        /*
            if (r9 == 0) goto L52
            boolean r0 = r9.isEmpty()
            if (r0 == 0) goto L9
            goto L52
        L9:
            com.alibaba.fastjson2.reader.ObjectReaderProvider r0 = r10.provider
            java.lang.Class<java.lang.Object> r1 = java.lang.Object.class
            r2 = 0
            com.alibaba.fastjson2.reader.ObjectReader r3 = r0.getObjectReader(r1, r2)
            com.alibaba.fastjson2.JSONReader r4 = com.alibaba.fastjson2.JSONReader.of(r9, r10)
            r6 = 0
            r7 = 0
            r5 = 0
            java.lang.Object r9 = r3.readObject(r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L44
            char r0 = r4.ch     // Catch: java.lang.Throwable -> L44
            r1 = 26
            if (r0 == r1) goto L3e
            long r0 = r10.features     // Catch: java.lang.Throwable -> L44
            com.alibaba.fastjson2.JSONReader$Feature r10 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L44
            long r2 = r10.mask     // Catch: java.lang.Throwable -> L44
            long r0 = r0 & r2
            r2 = 0
            int r10 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r10 == 0) goto L32
            goto L3e
        L32:
            com.alibaba.fastjson2.JSONException r9 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L44
            java.lang.String r10 = "input not end"
            java.lang.String r10 = r4.info(r10)     // Catch: java.lang.Throwable -> L44
            r9.<init>(r10)     // Catch: java.lang.Throwable -> L44
            throw r9     // Catch: java.lang.Throwable -> L44
        L3e:
            if (r4 == 0) goto L43
            r4.close()
        L43:
            return r9
        L44:
            r0 = move-exception
            r9 = r0
            if (r4 == 0) goto L51
            r4.close()     // Catch: java.lang.Throwable -> L4c
            goto L51
        L4c:
            r0 = move-exception
            r10 = r0
            r9.addSuppressed(r10)
        L51:
            throw r9
        L52:
            r9 = 0
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parse(java.lang.String, com.alibaba.fastjson2.JSONReader$Context):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0044, code lost:
    
        r4.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static java.lang.Object parse(byte[] r9, com.alibaba.fastjson2.JSONReader.Feature... r10) {
        /*
            if (r9 == 0) goto L56
            int r0 = r9.length
            if (r0 != 0) goto L6
            goto L56
        L6:
            com.alibaba.fastjson2.reader.ObjectReaderProvider r0 = com.alibaba.fastjson2.JSONFactory.getDefaultObjectReaderProvider()
            com.alibaba.fastjson2.JSONReader$Context r1 = new com.alibaba.fastjson2.JSONReader$Context
            r1.<init>(r0, r10)
            java.lang.Class<java.lang.Object> r10 = java.lang.Object.class
            r2 = 0
            com.alibaba.fastjson2.reader.ObjectReader r3 = r0.getObjectReader(r10, r2)
            com.alibaba.fastjson2.JSONReader r4 = com.alibaba.fastjson2.JSONReader.of(r9, r1)
            r6 = 0
            r7 = 0
            r5 = 0
            java.lang.Object r9 = r3.readObject(r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L48
            char r10 = r4.ch     // Catch: java.lang.Throwable -> L48
            r0 = 26
            if (r10 == r0) goto L42
            long r0 = r1.features     // Catch: java.lang.Throwable -> L48
            com.alibaba.fastjson2.JSONReader$Feature r10 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L48
            long r2 = r10.mask     // Catch: java.lang.Throwable -> L48
            long r0 = r0 & r2
            r2 = 0
            int r10 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r10 == 0) goto L36
            goto L42
        L36:
            com.alibaba.fastjson2.JSONException r9 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L48
            java.lang.String r10 = "input not end"
            java.lang.String r10 = r4.info(r10)     // Catch: java.lang.Throwable -> L48
            r9.<init>(r10)     // Catch: java.lang.Throwable -> L48
            throw r9     // Catch: java.lang.Throwable -> L48
        L42:
            if (r4 == 0) goto L47
            r4.close()
        L47:
            return r9
        L48:
            r0 = move-exception
            r9 = r0
            if (r4 == 0) goto L55
            r4.close()     // Catch: java.lang.Throwable -> L50
            goto L55
        L50:
            r0 = move-exception
            r10 = r0
            r9.addSuppressed(r10)
        L55:
            throw r9
        L56:
            r9 = 0
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parse(byte[], com.alibaba.fastjson2.JSONReader$Feature[]):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x003a, code lost:
    
        r2.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static java.lang.Object parse(byte[] r7, com.alibaba.fastjson2.JSONReader.Context r8) {
        /*
            if (r7 == 0) goto L4c
            int r0 = r7.length
            if (r0 != 0) goto L6
            goto L4c
        L6:
            java.lang.Class<java.lang.Object> r0 = java.lang.Object.class
            com.alibaba.fastjson2.reader.ObjectReader r1 = r8.getObjectReader(r0)
            com.alibaba.fastjson2.JSONReader r2 = com.alibaba.fastjson2.JSONReader.of(r7, r8)
            r4 = 0
            r5 = 0
            r3 = 0
            java.lang.Object r7 = r1.readObject(r2, r3, r4, r5)     // Catch: java.lang.Throwable -> L3e
            char r0 = r2.ch     // Catch: java.lang.Throwable -> L3e
            r1 = 26
            if (r0 == r1) goto L38
            long r0 = r8.features     // Catch: java.lang.Throwable -> L3e
            com.alibaba.fastjson2.JSONReader$Feature r8 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L3e
            long r3 = r8.mask     // Catch: java.lang.Throwable -> L3e
            long r0 = r0 & r3
            r3 = 0
            int r8 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r8 == 0) goto L2c
            goto L38
        L2c:
            com.alibaba.fastjson2.JSONException r7 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L3e
            java.lang.String r8 = "input not end"
            java.lang.String r8 = r2.info(r8)     // Catch: java.lang.Throwable -> L3e
            r7.<init>(r8)     // Catch: java.lang.Throwable -> L3e
            throw r7     // Catch: java.lang.Throwable -> L3e
        L38:
            if (r2 == 0) goto L3d
            r2.close()
        L3d:
            return r7
        L3e:
            r0 = move-exception
            r7 = r0
            if (r2 == 0) goto L4b
            r2.close()     // Catch: java.lang.Throwable -> L46
            goto L4b
        L46:
            r0 = move-exception
            r8 = r0
            r7.addSuppressed(r8)
        L4b:
            throw r7
        L4c:
            r7 = 0
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parse(byte[], com.alibaba.fastjson2.JSONReader$Context):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x003a, code lost:
    
        r2.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static java.lang.Object parse(byte[] r7, int r8, int r9, java.nio.charset.Charset r10, com.alibaba.fastjson2.JSONReader.Context r11) {
        /*
            if (r7 == 0) goto L4c
            int r0 = r7.length
            if (r0 != 0) goto L6
            goto L4c
        L6:
            java.lang.Class<java.lang.Object> r0 = java.lang.Object.class
            com.alibaba.fastjson2.reader.ObjectReader r1 = r11.getObjectReader(r0)
            com.alibaba.fastjson2.JSONReader r2 = com.alibaba.fastjson2.JSONReader.of(r7, r8, r9, r10, r11)
            r4 = 0
            r5 = 0
            r3 = 0
            java.lang.Object r7 = r1.readObject(r2, r3, r4, r5)     // Catch: java.lang.Throwable -> L3e
            char r8 = r2.ch     // Catch: java.lang.Throwable -> L3e
            r9 = 26
            if (r8 == r9) goto L38
            long r8 = r11.features     // Catch: java.lang.Throwable -> L3e
            com.alibaba.fastjson2.JSONReader$Feature r10 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L3e
            long r10 = r10.mask     // Catch: java.lang.Throwable -> L3e
            long r8 = r8 & r10
            r10 = 0
            int r8 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r8 == 0) goto L2c
            goto L38
        L2c:
            com.alibaba.fastjson2.JSONException r7 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L3e
            java.lang.String r8 = "input not end"
            java.lang.String r8 = r2.info(r8)     // Catch: java.lang.Throwable -> L3e
            r7.<init>(r8)     // Catch: java.lang.Throwable -> L3e
            throw r7     // Catch: java.lang.Throwable -> L3e
        L38:
            if (r2 == 0) goto L3d
            r2.close()
        L3d:
            return r7
        L3e:
            r0 = move-exception
            r7 = r0
            if (r2 == 0) goto L4b
            r2.close()     // Catch: java.lang.Throwable -> L46
            goto L4b
        L46:
            r0 = move-exception
            r8 = r0
            r7.addSuppressed(r8)
        L4b:
            throw r7
        L4c:
            r7 = 0
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parse(byte[], int, int, java.nio.charset.Charset, com.alibaba.fastjson2.JSONReader$Context):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0044, code lost:
    
        r4.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static java.lang.Object parse(char[] r9, com.alibaba.fastjson2.JSONReader.Feature... r10) {
        /*
            if (r9 == 0) goto L56
            int r0 = r9.length
            if (r0 != 0) goto L6
            goto L56
        L6:
            com.alibaba.fastjson2.reader.ObjectReaderProvider r0 = com.alibaba.fastjson2.JSONFactory.getDefaultObjectReaderProvider()
            com.alibaba.fastjson2.JSONReader$Context r1 = new com.alibaba.fastjson2.JSONReader$Context
            r1.<init>(r0, r10)
            java.lang.Class<java.lang.Object> r10 = java.lang.Object.class
            r2 = 0
            com.alibaba.fastjson2.reader.ObjectReader r3 = r0.getObjectReader(r10, r2)
            com.alibaba.fastjson2.JSONReader r4 = com.alibaba.fastjson2.JSONReader.of(r9, r1)
            r6 = 0
            r7 = 0
            r5 = 0
            java.lang.Object r9 = r3.readObject(r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L48
            char r10 = r4.ch     // Catch: java.lang.Throwable -> L48
            r0 = 26
            if (r10 == r0) goto L42
            long r0 = r1.features     // Catch: java.lang.Throwable -> L48
            com.alibaba.fastjson2.JSONReader$Feature r10 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L48
            long r2 = r10.mask     // Catch: java.lang.Throwable -> L48
            long r0 = r0 & r2
            r2 = 0
            int r10 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r10 == 0) goto L36
            goto L42
        L36:
            com.alibaba.fastjson2.JSONException r9 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L48
            java.lang.String r10 = "input not end"
            java.lang.String r10 = r4.info(r10)     // Catch: java.lang.Throwable -> L48
            r9.<init>(r10)     // Catch: java.lang.Throwable -> L48
            throw r9     // Catch: java.lang.Throwable -> L48
        L42:
            if (r4 == 0) goto L47
            r4.close()
        L47:
            return r9
        L48:
            r0 = move-exception
            r9 = r0
            if (r4 == 0) goto L55
            r4.close()     // Catch: java.lang.Throwable -> L50
            goto L55
        L50:
            r0 = move-exception
            r10 = r0
            r9.addSuppressed(r10)
        L55:
            throw r9
        L56:
            r9 = 0
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parse(char[], com.alibaba.fastjson2.JSONReader$Feature[]):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x003a, code lost:
    
        r2.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static java.lang.Object parse(char[] r7, com.alibaba.fastjson2.JSONReader.Context r8) {
        /*
            if (r7 == 0) goto L4c
            int r0 = r7.length
            if (r0 != 0) goto L6
            goto L4c
        L6:
            java.lang.Class<java.lang.Object> r0 = java.lang.Object.class
            com.alibaba.fastjson2.reader.ObjectReader r1 = r8.getObjectReader(r0)
            com.alibaba.fastjson2.JSONReader r2 = com.alibaba.fastjson2.JSONReader.of(r7, r8)
            r4 = 0
            r5 = 0
            r3 = 0
            java.lang.Object r7 = r1.readObject(r2, r3, r4, r5)     // Catch: java.lang.Throwable -> L3e
            char r0 = r2.ch     // Catch: java.lang.Throwable -> L3e
            r1 = 26
            if (r0 == r1) goto L38
            long r0 = r8.features     // Catch: java.lang.Throwable -> L3e
            com.alibaba.fastjson2.JSONReader$Feature r8 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L3e
            long r3 = r8.mask     // Catch: java.lang.Throwable -> L3e
            long r0 = r0 & r3
            r3 = 0
            int r8 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r8 == 0) goto L2c
            goto L38
        L2c:
            com.alibaba.fastjson2.JSONException r7 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L3e
            java.lang.String r8 = "input not end"
            java.lang.String r8 = r2.info(r8)     // Catch: java.lang.Throwable -> L3e
            r7.<init>(r8)     // Catch: java.lang.Throwable -> L3e
            throw r7     // Catch: java.lang.Throwable -> L3e
        L38:
            if (r2 == 0) goto L3d
            r2.close()
        L3d:
            return r7
        L3e:
            r0 = move-exception
            r7 = r0
            if (r2 == 0) goto L4b
            r2.close()     // Catch: java.lang.Throwable -> L46
            goto L4b
        L46:
            r0 = move-exception
            r8 = r0
            r7.addSuppressed(r8)
        L4b:
            throw r7
        L4c:
            r7 = 0
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parse(char[], com.alibaba.fastjson2.JSONReader$Context):java.lang.Object");
    }

    static Object parse(InputStream inputStream, JSONReader.Context context) {
        if (inputStream == null) {
            return null;
        }
        ObjectReader objectReader = context.getObjectReader(Object.class);
        JSONReaderUTF8 jSONReaderUTF8 = new JSONReaderUTF8(context, inputStream);
        try {
            Object readObject = objectReader.readObject(jSONReaderUTF8, null, null, 0L);
            if (jSONReaderUTF8.ch != 26 && (context.features & JSONReader.Feature.IgnoreCheckClose.mask) == 0) {
                throw new JSONException(jSONReaderUTF8.info("input not end"));
            }
            jSONReaderUTF8.close();
            return readObject;
        } finally {
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x004f, code lost:
    
        r8.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static com.alibaba.fastjson2.JSONObject parseObject(java.lang.String r8) {
        /*
            r0 = 0
            if (r8 == 0) goto L5f
            boolean r1 = r8.isEmpty()
            if (r1 == 0) goto La
            goto L5f
        La:
            com.alibaba.fastjson2.JSONReader$Context r1 = com.alibaba.fastjson2.JSONFactory.createReadContext()
            com.alibaba.fastjson2.JSONReader r8 = com.alibaba.fastjson2.JSONReader.of(r8, r1)
            boolean r2 = r8.nextIfNull()     // Catch: java.lang.Throwable -> L53
            if (r2 == 0) goto L1e
            if (r8 == 0) goto L1d
            r8.close()
        L1d:
            return r0
        L1e:
            com.alibaba.fastjson2.JSONObject r0 = new com.alibaba.fastjson2.JSONObject     // Catch: java.lang.Throwable -> L53
            r0.<init>()     // Catch: java.lang.Throwable -> L53
            r2 = 0
            r8.read(r0, r2)     // Catch: java.lang.Throwable -> L53
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r4 = r8.resolveTasks     // Catch: java.lang.Throwable -> L53
            if (r4 == 0) goto L2f
            r8.handleResolveTasks(r0)     // Catch: java.lang.Throwable -> L53
        L2f:
            char r4 = r8.ch     // Catch: java.lang.Throwable -> L53
            r5 = 26
            if (r4 == r5) goto L4d
            long r4 = r1.features     // Catch: java.lang.Throwable -> L53
            com.alibaba.fastjson2.JSONReader$Feature r1 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L53
            long r6 = r1.mask     // Catch: java.lang.Throwable -> L53
            long r4 = r4 & r6
            int r1 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r1 == 0) goto L41
            goto L4d
        L41:
            com.alibaba.fastjson2.JSONException r0 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L53
            java.lang.String r1 = "input not end"
            java.lang.String r1 = r8.info(r1)     // Catch: java.lang.Throwable -> L53
            r0.<init>(r1)     // Catch: java.lang.Throwable -> L53
            throw r0     // Catch: java.lang.Throwable -> L53
        L4d:
            if (r8 == 0) goto L52
            r8.close()
        L52:
            return r0
        L53:
            r0 = move-exception
            if (r8 == 0) goto L5e
            r8.close()     // Catch: java.lang.Throwable -> L5a
            goto L5e
        L5a:
            r8 = move-exception
            r0.addSuppressed(r8)
        L5e:
            throw r0
        L5f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseObject(java.lang.String):com.alibaba.fastjson2.JSONObject");
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x004f, code lost:
    
        r7.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static com.alibaba.fastjson2.JSONObject parseObject(java.lang.String r7, com.alibaba.fastjson2.JSONReader.Feature... r8) {
        /*
            r0 = 0
            if (r7 == 0) goto L5f
            boolean r1 = r7.isEmpty()
            if (r1 == 0) goto La
            goto L5f
        La:
            com.alibaba.fastjson2.JSONReader$Context r8 = com.alibaba.fastjson2.JSONFactory.createReadContext(r8)
            com.alibaba.fastjson2.JSONReader r7 = com.alibaba.fastjson2.JSONReader.of(r7, r8)
            boolean r1 = r7.nextIfNull()     // Catch: java.lang.Throwable -> L53
            if (r1 == 0) goto L1e
            if (r7 == 0) goto L1d
            r7.close()
        L1d:
            return r0
        L1e:
            com.alibaba.fastjson2.JSONObject r0 = new com.alibaba.fastjson2.JSONObject     // Catch: java.lang.Throwable -> L53
            r0.<init>()     // Catch: java.lang.Throwable -> L53
            r1 = 0
            r7.read(r0, r1)     // Catch: java.lang.Throwable -> L53
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r3 = r7.resolveTasks     // Catch: java.lang.Throwable -> L53
            if (r3 == 0) goto L2f
            r7.handleResolveTasks(r0)     // Catch: java.lang.Throwable -> L53
        L2f:
            char r3 = r7.ch     // Catch: java.lang.Throwable -> L53
            r4 = 26
            if (r3 == r4) goto L4d
            long r3 = r8.features     // Catch: java.lang.Throwable -> L53
            com.alibaba.fastjson2.JSONReader$Feature r8 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L53
            long r5 = r8.mask     // Catch: java.lang.Throwable -> L53
            long r3 = r3 & r5
            int r8 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r8 == 0) goto L41
            goto L4d
        L41:
            com.alibaba.fastjson2.JSONException r8 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L53
            java.lang.String r0 = "input not end"
            java.lang.String r0 = r7.info(r0)     // Catch: java.lang.Throwable -> L53
            r8.<init>(r0)     // Catch: java.lang.Throwable -> L53
            throw r8     // Catch: java.lang.Throwable -> L53
        L4d:
            if (r7 == 0) goto L52
            r7.close()
        L52:
            return r0
        L53:
            r8 = move-exception
            if (r7 == 0) goto L5e
            r7.close()     // Catch: java.lang.Throwable -> L5a
            goto L5e
        L5a:
            r7 = move-exception
            r8.addSuppressed(r7)
        L5e:
            throw r8
        L5f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseObject(java.lang.String, com.alibaba.fastjson2.JSONReader$Feature[]):com.alibaba.fastjson2.JSONObject");
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0051, code lost:
    
        r4.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static com.alibaba.fastjson2.JSONObject parseObject(java.lang.String r4, int r5, int r6, com.alibaba.fastjson2.JSONReader.Feature... r7) {
        /*
            r0 = 0
            if (r4 == 0) goto L61
            boolean r1 = r4.isEmpty()
            if (r1 != 0) goto L61
            if (r6 != 0) goto Lc
            goto L61
        Lc:
            com.alibaba.fastjson2.JSONReader$Context r7 = com.alibaba.fastjson2.JSONFactory.createReadContext(r7)
            com.alibaba.fastjson2.JSONReader r4 = com.alibaba.fastjson2.JSONReader.of(r4, r5, r6, r7)
            boolean r5 = r4.nextIfNull()     // Catch: java.lang.Throwable -> L55
            if (r5 == 0) goto L20
            if (r4 == 0) goto L1f
            r4.close()
        L1f:
            return r0
        L20:
            com.alibaba.fastjson2.JSONObject r5 = new com.alibaba.fastjson2.JSONObject     // Catch: java.lang.Throwable -> L55
            r5.<init>()     // Catch: java.lang.Throwable -> L55
            r0 = 0
            r4.read(r5, r0)     // Catch: java.lang.Throwable -> L55
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r6 = r4.resolveTasks     // Catch: java.lang.Throwable -> L55
            if (r6 == 0) goto L31
            r4.handleResolveTasks(r5)     // Catch: java.lang.Throwable -> L55
        L31:
            char r6 = r4.ch     // Catch: java.lang.Throwable -> L55
            r2 = 26
            if (r6 == r2) goto L4f
            long r6 = r7.features     // Catch: java.lang.Throwable -> L55
            com.alibaba.fastjson2.JSONReader$Feature r2 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L55
            long r2 = r2.mask     // Catch: java.lang.Throwable -> L55
            long r6 = r6 & r2
            int r6 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r6 == 0) goto L43
            goto L4f
        L43:
            com.alibaba.fastjson2.JSONException r5 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L55
            java.lang.String r6 = "input not end"
            java.lang.String r6 = r4.info(r6)     // Catch: java.lang.Throwable -> L55
            r5.<init>(r6)     // Catch: java.lang.Throwable -> L55
            throw r5     // Catch: java.lang.Throwable -> L55
        L4f:
            if (r4 == 0) goto L54
            r4.close()
        L54:
            return r5
        L55:
            r5 = move-exception
            if (r4 == 0) goto L60
            r4.close()     // Catch: java.lang.Throwable -> L5c
            goto L60
        L5c:
            r4 = move-exception
            r5.addSuppressed(r4)
        L60:
            throw r5
        L61:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseObject(java.lang.String, int, int, com.alibaba.fastjson2.JSONReader$Feature[]):com.alibaba.fastjson2.JSONObject");
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x004d, code lost:
    
        r4.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static com.alibaba.fastjson2.JSONObject parseObject(java.lang.String r4, int r5, int r6, com.alibaba.fastjson2.JSONReader.Context r7) {
        /*
            r0 = 0
            if (r4 == 0) goto L5d
            boolean r1 = r4.isEmpty()
            if (r1 != 0) goto L5d
            if (r6 != 0) goto Lc
            goto L5d
        Lc:
            com.alibaba.fastjson2.JSONReader r4 = com.alibaba.fastjson2.JSONReader.of(r4, r5, r6, r7)
            boolean r5 = r4.nextIfNull()     // Catch: java.lang.Throwable -> L51
            if (r5 == 0) goto L1c
            if (r4 == 0) goto L1b
            r4.close()
        L1b:
            return r0
        L1c:
            com.alibaba.fastjson2.JSONObject r5 = new com.alibaba.fastjson2.JSONObject     // Catch: java.lang.Throwable -> L51
            r5.<init>()     // Catch: java.lang.Throwable -> L51
            r0 = 0
            r4.read(r5, r0)     // Catch: java.lang.Throwable -> L51
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r6 = r4.resolveTasks     // Catch: java.lang.Throwable -> L51
            if (r6 == 0) goto L2d
            r4.handleResolveTasks(r5)     // Catch: java.lang.Throwable -> L51
        L2d:
            char r6 = r4.ch     // Catch: java.lang.Throwable -> L51
            r2 = 26
            if (r6 == r2) goto L4b
            long r6 = r7.features     // Catch: java.lang.Throwable -> L51
            com.alibaba.fastjson2.JSONReader$Feature r2 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L51
            long r2 = r2.mask     // Catch: java.lang.Throwable -> L51
            long r6 = r6 & r2
            int r6 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r6 == 0) goto L3f
            goto L4b
        L3f:
            com.alibaba.fastjson2.JSONException r5 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L51
            java.lang.String r6 = "input not end"
            java.lang.String r6 = r4.info(r6)     // Catch: java.lang.Throwable -> L51
            r5.<init>(r6)     // Catch: java.lang.Throwable -> L51
            throw r5     // Catch: java.lang.Throwable -> L51
        L4b:
            if (r4 == 0) goto L50
            r4.close()
        L50:
            return r5
        L51:
            r5 = move-exception
            if (r4 == 0) goto L5c
            r4.close()     // Catch: java.lang.Throwable -> L58
            goto L5c
        L58:
            r4 = move-exception
            r5.addSuppressed(r4)
        L5c:
            throw r5
        L5d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseObject(java.lang.String, int, int, com.alibaba.fastjson2.JSONReader$Context):com.alibaba.fastjson2.JSONObject");
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x004b, code lost:
    
        r7.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static com.alibaba.fastjson2.JSONObject parseObject(java.lang.String r7, com.alibaba.fastjson2.JSONReader.Context r8) {
        /*
            r0 = 0
            if (r7 == 0) goto L5b
            boolean r1 = r7.isEmpty()
            if (r1 == 0) goto La
            goto L5b
        La:
            com.alibaba.fastjson2.JSONReader r7 = com.alibaba.fastjson2.JSONReader.of(r7, r8)
            boolean r1 = r7.nextIfNull()     // Catch: java.lang.Throwable -> L4f
            if (r1 == 0) goto L1a
            if (r7 == 0) goto L19
            r7.close()
        L19:
            return r0
        L1a:
            com.alibaba.fastjson2.JSONObject r0 = new com.alibaba.fastjson2.JSONObject     // Catch: java.lang.Throwable -> L4f
            r0.<init>()     // Catch: java.lang.Throwable -> L4f
            r1 = 0
            r7.read(r0, r1)     // Catch: java.lang.Throwable -> L4f
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r3 = r7.resolveTasks     // Catch: java.lang.Throwable -> L4f
            if (r3 == 0) goto L2b
            r7.handleResolveTasks(r0)     // Catch: java.lang.Throwable -> L4f
        L2b:
            char r3 = r7.ch     // Catch: java.lang.Throwable -> L4f
            r4 = 26
            if (r3 == r4) goto L49
            long r3 = r8.features     // Catch: java.lang.Throwable -> L4f
            com.alibaba.fastjson2.JSONReader$Feature r8 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L4f
            long r5 = r8.mask     // Catch: java.lang.Throwable -> L4f
            long r3 = r3 & r5
            int r8 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r8 == 0) goto L3d
            goto L49
        L3d:
            com.alibaba.fastjson2.JSONException r8 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L4f
            java.lang.String r0 = "input not end"
            java.lang.String r0 = r7.info(r0)     // Catch: java.lang.Throwable -> L4f
            r8.<init>(r0)     // Catch: java.lang.Throwable -> L4f
            throw r8     // Catch: java.lang.Throwable -> L4f
        L49:
            if (r7 == 0) goto L4e
            r7.close()
        L4e:
            return r0
        L4f:
            r8 = move-exception
            if (r7 == 0) goto L5a
            r7.close()     // Catch: java.lang.Throwable -> L56
            goto L5a
        L56:
            r7 = move-exception
            r8.addSuppressed(r7)
        L5a:
            throw r8
        L5b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseObject(java.lang.String, com.alibaba.fastjson2.JSONReader$Context):com.alibaba.fastjson2.JSONObject");
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0049, code lost:
    
        r7.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static com.alibaba.fastjson2.JSONObject parseObject(java.io.Reader r7, com.alibaba.fastjson2.JSONReader.Feature... r8) {
        /*
            r0 = 0
            if (r7 != 0) goto L4
            return r0
        L4:
            com.alibaba.fastjson2.JSONReader$Context r8 = com.alibaba.fastjson2.JSONFactory.createReadContext(r8)
            com.alibaba.fastjson2.JSONReader r7 = com.alibaba.fastjson2.JSONReader.of(r7, r8)
            boolean r1 = r7.isEnd()     // Catch: java.lang.Throwable -> L4d
            if (r1 == 0) goto L18
            if (r7 == 0) goto L17
            r7.close()
        L17:
            return r0
        L18:
            com.alibaba.fastjson2.JSONObject r0 = new com.alibaba.fastjson2.JSONObject     // Catch: java.lang.Throwable -> L4d
            r0.<init>()     // Catch: java.lang.Throwable -> L4d
            r1 = 0
            r7.read(r0, r1)     // Catch: java.lang.Throwable -> L4d
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r3 = r7.resolveTasks     // Catch: java.lang.Throwable -> L4d
            if (r3 == 0) goto L29
            r7.handleResolveTasks(r0)     // Catch: java.lang.Throwable -> L4d
        L29:
            char r3 = r7.ch     // Catch: java.lang.Throwable -> L4d
            r4 = 26
            if (r3 == r4) goto L47
            long r3 = r8.features     // Catch: java.lang.Throwable -> L4d
            com.alibaba.fastjson2.JSONReader$Feature r8 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L4d
            long r5 = r8.mask     // Catch: java.lang.Throwable -> L4d
            long r3 = r3 & r5
            int r8 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r8 == 0) goto L3b
            goto L47
        L3b:
            com.alibaba.fastjson2.JSONException r8 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L4d
            java.lang.String r0 = "input not end"
            java.lang.String r0 = r7.info(r0)     // Catch: java.lang.Throwable -> L4d
            r8.<init>(r0)     // Catch: java.lang.Throwable -> L4d
            throw r8     // Catch: java.lang.Throwable -> L4d
        L47:
            if (r7 == 0) goto L4c
            r7.close()
        L4c:
            return r0
        L4d:
            r8 = move-exception
            if (r7 == 0) goto L58
            r7.close()     // Catch: java.lang.Throwable -> L54
            goto L58
        L54:
            r7 = move-exception
            r8.addSuppressed(r7)
        L58:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseObject(java.io.Reader, com.alibaba.fastjson2.JSONReader$Feature[]):com.alibaba.fastjson2.JSONObject");
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x004b, code lost:
    
        r7.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static com.alibaba.fastjson2.JSONObject parseObject(java.io.InputStream r7, com.alibaba.fastjson2.JSONReader.Feature... r8) {
        /*
            r0 = 0
            if (r7 != 0) goto L4
            return r0
        L4:
            com.alibaba.fastjson2.JSONReader$Context r8 = com.alibaba.fastjson2.JSONFactory.createReadContext(r8)
            java.nio.charset.Charset r1 = java.nio.charset.StandardCharsets.UTF_8
            com.alibaba.fastjson2.JSONReader r7 = com.alibaba.fastjson2.JSONReader.of(r7, r1, r8)
            boolean r1 = r7.isEnd()     // Catch: java.lang.Throwable -> L4f
            if (r1 == 0) goto L1a
            if (r7 == 0) goto L19
            r7.close()
        L19:
            return r0
        L1a:
            com.alibaba.fastjson2.JSONObject r0 = new com.alibaba.fastjson2.JSONObject     // Catch: java.lang.Throwable -> L4f
            r0.<init>()     // Catch: java.lang.Throwable -> L4f
            r1 = 0
            r7.read(r0, r1)     // Catch: java.lang.Throwable -> L4f
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r3 = r7.resolveTasks     // Catch: java.lang.Throwable -> L4f
            if (r3 == 0) goto L2b
            r7.handleResolveTasks(r0)     // Catch: java.lang.Throwable -> L4f
        L2b:
            char r3 = r7.ch     // Catch: java.lang.Throwable -> L4f
            r4 = 26
            if (r3 == r4) goto L49
            long r3 = r8.features     // Catch: java.lang.Throwable -> L4f
            com.alibaba.fastjson2.JSONReader$Feature r8 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L4f
            long r5 = r8.mask     // Catch: java.lang.Throwable -> L4f
            long r3 = r3 & r5
            int r8 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r8 == 0) goto L3d
            goto L49
        L3d:
            com.alibaba.fastjson2.JSONException r8 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L4f
            java.lang.String r0 = "input not end"
            java.lang.String r0 = r7.info(r0)     // Catch: java.lang.Throwable -> L4f
            r8.<init>(r0)     // Catch: java.lang.Throwable -> L4f
            throw r8     // Catch: java.lang.Throwable -> L4f
        L49:
            if (r7 == 0) goto L4e
            r7.close()
        L4e:
            return r0
        L4f:
            r8 = move-exception
            if (r7 == 0) goto L5a
            r7.close()     // Catch: java.lang.Throwable -> L56
            goto L5a
        L56:
            r7 = move-exception
            r8.addSuppressed(r7)
        L5a:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseObject(java.io.InputStream, com.alibaba.fastjson2.JSONReader$Feature[]):com.alibaba.fastjson2.JSONObject");
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x004c, code lost:
    
        r8.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static com.alibaba.fastjson2.JSONObject parseObject(byte[] r8) {
        /*
            r0 = 0
            if (r8 == 0) goto L5c
            int r1 = r8.length
            if (r1 != 0) goto L7
            goto L5c
        L7:
            com.alibaba.fastjson2.JSONReader$Context r1 = com.alibaba.fastjson2.JSONFactory.createReadContext()
            com.alibaba.fastjson2.JSONReader r8 = com.alibaba.fastjson2.JSONReader.of(r8, r1)
            boolean r2 = r8.nextIfNull()     // Catch: java.lang.Throwable -> L50
            if (r2 == 0) goto L1b
            if (r8 == 0) goto L1a
            r8.close()
        L1a:
            return r0
        L1b:
            com.alibaba.fastjson2.JSONObject r0 = new com.alibaba.fastjson2.JSONObject     // Catch: java.lang.Throwable -> L50
            r0.<init>()     // Catch: java.lang.Throwable -> L50
            r2 = 0
            r8.read(r0, r2)     // Catch: java.lang.Throwable -> L50
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r4 = r8.resolveTasks     // Catch: java.lang.Throwable -> L50
            if (r4 == 0) goto L2c
            r8.handleResolveTasks(r0)     // Catch: java.lang.Throwable -> L50
        L2c:
            char r4 = r8.ch     // Catch: java.lang.Throwable -> L50
            r5 = 26
            if (r4 == r5) goto L4a
            long r4 = r1.features     // Catch: java.lang.Throwable -> L50
            com.alibaba.fastjson2.JSONReader$Feature r1 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L50
            long r6 = r1.mask     // Catch: java.lang.Throwable -> L50
            long r4 = r4 & r6
            int r1 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r1 == 0) goto L3e
            goto L4a
        L3e:
            com.alibaba.fastjson2.JSONException r0 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L50
            java.lang.String r1 = "input not end"
            java.lang.String r1 = r8.info(r1)     // Catch: java.lang.Throwable -> L50
            r0.<init>(r1)     // Catch: java.lang.Throwable -> L50
            throw r0     // Catch: java.lang.Throwable -> L50
        L4a:
            if (r8 == 0) goto L4f
            r8.close()
        L4f:
            return r0
        L50:
            r0 = move-exception
            if (r8 == 0) goto L5b
            r8.close()     // Catch: java.lang.Throwable -> L57
            goto L5b
        L57:
            r8 = move-exception
            r0.addSuppressed(r8)
        L5b:
            throw r0
        L5c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseObject(byte[]):com.alibaba.fastjson2.JSONObject");
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x004c, code lost:
    
        r8.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static com.alibaba.fastjson2.JSONObject parseObject(char[] r8) {
        /*
            r0 = 0
            if (r8 == 0) goto L5c
            int r1 = r8.length
            if (r1 != 0) goto L7
            goto L5c
        L7:
            com.alibaba.fastjson2.JSONReader$Context r1 = com.alibaba.fastjson2.JSONFactory.createReadContext()
            com.alibaba.fastjson2.JSONReader r8 = com.alibaba.fastjson2.JSONReader.of(r8, r1)
            boolean r2 = r8.nextIfNull()     // Catch: java.lang.Throwable -> L50
            if (r2 == 0) goto L1b
            if (r8 == 0) goto L1a
            r8.close()
        L1a:
            return r0
        L1b:
            com.alibaba.fastjson2.JSONObject r0 = new com.alibaba.fastjson2.JSONObject     // Catch: java.lang.Throwable -> L50
            r0.<init>()     // Catch: java.lang.Throwable -> L50
            r2 = 0
            r8.read(r0, r2)     // Catch: java.lang.Throwable -> L50
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r4 = r8.resolveTasks     // Catch: java.lang.Throwable -> L50
            if (r4 == 0) goto L2c
            r8.handleResolveTasks(r0)     // Catch: java.lang.Throwable -> L50
        L2c:
            char r4 = r8.ch     // Catch: java.lang.Throwable -> L50
            r5 = 26
            if (r4 == r5) goto L4a
            long r4 = r1.features     // Catch: java.lang.Throwable -> L50
            com.alibaba.fastjson2.JSONReader$Feature r1 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L50
            long r6 = r1.mask     // Catch: java.lang.Throwable -> L50
            long r4 = r4 & r6
            int r1 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r1 == 0) goto L3e
            goto L4a
        L3e:
            com.alibaba.fastjson2.JSONException r0 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L50
            java.lang.String r1 = "input not end"
            java.lang.String r1 = r8.info(r1)     // Catch: java.lang.Throwable -> L50
            r0.<init>(r1)     // Catch: java.lang.Throwable -> L50
            throw r0     // Catch: java.lang.Throwable -> L50
        L4a:
            if (r8 == 0) goto L4f
            r8.close()
        L4f:
            return r0
        L50:
            r0 = move-exception
            if (r8 == 0) goto L5b
            r8.close()     // Catch: java.lang.Throwable -> L57
            goto L5b
        L57:
            r8 = move-exception
            r0.addSuppressed(r8)
        L5b:
            throw r0
        L5c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseObject(char[]):com.alibaba.fastjson2.JSONObject");
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0049, code lost:
    
        r6.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static com.alibaba.fastjson2.JSONObject parseObject(java.io.InputStream r6, java.nio.charset.Charset r7) {
        /*
            r0 = 0
            if (r6 != 0) goto L4
            return r0
        L4:
            com.alibaba.fastjson2.JSONReader$Context r1 = com.alibaba.fastjson2.JSONFactory.createReadContext()
            com.alibaba.fastjson2.JSONReader r6 = com.alibaba.fastjson2.JSONReader.of(r6, r7, r1)
            boolean r7 = r6.nextIfNull()     // Catch: java.lang.Throwable -> L4d
            if (r7 == 0) goto L18
            if (r6 == 0) goto L17
            r6.close()
        L17:
            return r0
        L18:
            com.alibaba.fastjson2.JSONObject r7 = new com.alibaba.fastjson2.JSONObject     // Catch: java.lang.Throwable -> L4d
            r7.<init>()     // Catch: java.lang.Throwable -> L4d
            r2 = 0
            r6.read(r7, r2)     // Catch: java.lang.Throwable -> L4d
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r0 = r6.resolveTasks     // Catch: java.lang.Throwable -> L4d
            if (r0 == 0) goto L29
            r6.handleResolveTasks(r7)     // Catch: java.lang.Throwable -> L4d
        L29:
            char r0 = r6.ch     // Catch: java.lang.Throwable -> L4d
            r4 = 26
            if (r0 == r4) goto L47
            long r0 = r1.features     // Catch: java.lang.Throwable -> L4d
            com.alibaba.fastjson2.JSONReader$Feature r4 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L4d
            long r4 = r4.mask     // Catch: java.lang.Throwable -> L4d
            long r0 = r0 & r4
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 == 0) goto L3b
            goto L47
        L3b:
            com.alibaba.fastjson2.JSONException r7 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L4d
            java.lang.String r0 = "input not end"
            java.lang.String r0 = r6.info(r0)     // Catch: java.lang.Throwable -> L4d
            r7.<init>(r0)     // Catch: java.lang.Throwable -> L4d
            throw r7     // Catch: java.lang.Throwable -> L4d
        L47:
            if (r6 == 0) goto L4c
            r6.close()
        L4c:
            return r7
        L4d:
            r7 = move-exception
            if (r6 == 0) goto L58
            r6.close()     // Catch: java.lang.Throwable -> L54
            goto L58
        L54:
            r6 = move-exception
            r7.addSuppressed(r6)
        L58:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseObject(java.io.InputStream, java.nio.charset.Charset):com.alibaba.fastjson2.JSONObject");
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0045, code lost:
    
        r6.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static com.alibaba.fastjson2.JSONObject parseObject(java.io.InputStream r6, java.nio.charset.Charset r7, com.alibaba.fastjson2.JSONReader.Context r8) {
        /*
            r0 = 0
            if (r6 != 0) goto L4
            return r0
        L4:
            com.alibaba.fastjson2.JSONReader r6 = com.alibaba.fastjson2.JSONReader.of(r6, r7, r8)
            boolean r7 = r6.isEnd()     // Catch: java.lang.Throwable -> L49
            if (r7 == 0) goto L14
            if (r6 == 0) goto L13
            r6.close()
        L13:
            return r0
        L14:
            com.alibaba.fastjson2.JSONObject r7 = new com.alibaba.fastjson2.JSONObject     // Catch: java.lang.Throwable -> L49
            r7.<init>()     // Catch: java.lang.Throwable -> L49
            r0 = 0
            r6.read(r7, r0)     // Catch: java.lang.Throwable -> L49
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r2 = r6.resolveTasks     // Catch: java.lang.Throwable -> L49
            if (r2 == 0) goto L25
            r6.handleResolveTasks(r7)     // Catch: java.lang.Throwable -> L49
        L25:
            char r2 = r6.ch     // Catch: java.lang.Throwable -> L49
            r3 = 26
            if (r2 == r3) goto L43
            long r2 = r8.features     // Catch: java.lang.Throwable -> L49
            com.alibaba.fastjson2.JSONReader$Feature r8 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L49
            long r4 = r8.mask     // Catch: java.lang.Throwable -> L49
            long r2 = r2 & r4
            int r8 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r8 == 0) goto L37
            goto L43
        L37:
            com.alibaba.fastjson2.JSONException r7 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L49
            java.lang.String r8 = "input not end"
            java.lang.String r8 = r6.info(r8)     // Catch: java.lang.Throwable -> L49
            r7.<init>(r8)     // Catch: java.lang.Throwable -> L49
            throw r7     // Catch: java.lang.Throwable -> L49
        L43:
            if (r6 == 0) goto L48
            r6.close()
        L48:
            return r7
        L49:
            r7 = move-exception
            if (r6 == 0) goto L54
            r6.close()     // Catch: java.lang.Throwable -> L50
            goto L54
        L50:
            r6 = move-exception
            r7.addSuppressed(r6)
        L54:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseObject(java.io.InputStream, java.nio.charset.Charset, com.alibaba.fastjson2.JSONReader$Context):com.alibaba.fastjson2.JSONObject");
    }

    static JSONObject parseObject(URL url) {
        if (url == null) {
            return null;
        }
        try {
            InputStream openStream = url.openStream();
            try {
                JSONObject parseObject = parseObject(openStream, StandardCharsets.UTF_8);
                if (openStream != null) {
                    openStream.close();
                }
                return parseObject;
            } finally {
            }
        } catch (IOException e) {
            throw new JSONException("JSON#parseObject cannot parse '" + url + "'", e);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x004c, code lost:
    
        r7.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static com.alibaba.fastjson2.JSONObject parseObject(byte[] r7, com.alibaba.fastjson2.JSONReader.Feature... r8) {
        /*
            r0 = 0
            if (r7 == 0) goto L5c
            int r1 = r7.length
            if (r1 != 0) goto L7
            goto L5c
        L7:
            com.alibaba.fastjson2.JSONReader$Context r8 = com.alibaba.fastjson2.JSONFactory.createReadContext(r8)
            com.alibaba.fastjson2.JSONReader r7 = com.alibaba.fastjson2.JSONReader.of(r7, r8)
            boolean r1 = r7.nextIfNull()     // Catch: java.lang.Throwable -> L50
            if (r1 == 0) goto L1b
            if (r7 == 0) goto L1a
            r7.close()
        L1a:
            return r0
        L1b:
            com.alibaba.fastjson2.JSONObject r0 = new com.alibaba.fastjson2.JSONObject     // Catch: java.lang.Throwable -> L50
            r0.<init>()     // Catch: java.lang.Throwable -> L50
            r1 = 0
            r7.read(r0, r1)     // Catch: java.lang.Throwable -> L50
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r3 = r7.resolveTasks     // Catch: java.lang.Throwable -> L50
            if (r3 == 0) goto L2c
            r7.handleResolveTasks(r0)     // Catch: java.lang.Throwable -> L50
        L2c:
            char r3 = r7.ch     // Catch: java.lang.Throwable -> L50
            r4 = 26
            if (r3 == r4) goto L4a
            long r3 = r8.features     // Catch: java.lang.Throwable -> L50
            com.alibaba.fastjson2.JSONReader$Feature r8 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L50
            long r5 = r8.mask     // Catch: java.lang.Throwable -> L50
            long r3 = r3 & r5
            int r8 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r8 == 0) goto L3e
            goto L4a
        L3e:
            com.alibaba.fastjson2.JSONException r8 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L50
            java.lang.String r0 = "input not end"
            java.lang.String r0 = r7.info(r0)     // Catch: java.lang.Throwable -> L50
            r8.<init>(r0)     // Catch: java.lang.Throwable -> L50
            throw r8     // Catch: java.lang.Throwable -> L50
        L4a:
            if (r7 == 0) goto L4f
            r7.close()
        L4f:
            return r0
        L50:
            r8 = move-exception
            if (r7 == 0) goto L5b
            r7.close()     // Catch: java.lang.Throwable -> L57
            goto L5b
        L57:
            r7 = move-exception
            r8.addSuppressed(r7)
        L5b:
            throw r8
        L5c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseObject(byte[], com.alibaba.fastjson2.JSONReader$Feature[]):com.alibaba.fastjson2.JSONObject");
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x004e, code lost:
    
        r4.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static com.alibaba.fastjson2.JSONObject parseObject(byte[] r4, int r5, int r6, com.alibaba.fastjson2.JSONReader.Feature... r7) {
        /*
            r0 = 0
            if (r4 == 0) goto L5e
            int r1 = r4.length
            if (r1 == 0) goto L5e
            if (r6 != 0) goto L9
            goto L5e
        L9:
            com.alibaba.fastjson2.JSONReader$Context r7 = com.alibaba.fastjson2.JSONFactory.createReadContext(r7)
            com.alibaba.fastjson2.JSONReader r4 = com.alibaba.fastjson2.JSONReader.of(r4, r5, r6, r7)
            boolean r5 = r4.nextIfNull()     // Catch: java.lang.Throwable -> L52
            if (r5 == 0) goto L1d
            if (r4 == 0) goto L1c
            r4.close()
        L1c:
            return r0
        L1d:
            com.alibaba.fastjson2.JSONObject r5 = new com.alibaba.fastjson2.JSONObject     // Catch: java.lang.Throwable -> L52
            r5.<init>()     // Catch: java.lang.Throwable -> L52
            r0 = 0
            r4.read(r5, r0)     // Catch: java.lang.Throwable -> L52
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r6 = r4.resolveTasks     // Catch: java.lang.Throwable -> L52
            if (r6 == 0) goto L2e
            r4.handleResolveTasks(r5)     // Catch: java.lang.Throwable -> L52
        L2e:
            char r6 = r4.ch     // Catch: java.lang.Throwable -> L52
            r2 = 26
            if (r6 == r2) goto L4c
            long r6 = r7.features     // Catch: java.lang.Throwable -> L52
            com.alibaba.fastjson2.JSONReader$Feature r2 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L52
            long r2 = r2.mask     // Catch: java.lang.Throwable -> L52
            long r6 = r6 & r2
            int r6 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r6 == 0) goto L40
            goto L4c
        L40:
            com.alibaba.fastjson2.JSONException r5 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L52
            java.lang.String r6 = "input not end"
            java.lang.String r6 = r4.info(r6)     // Catch: java.lang.Throwable -> L52
            r5.<init>(r6)     // Catch: java.lang.Throwable -> L52
            throw r5     // Catch: java.lang.Throwable -> L52
        L4c:
            if (r4 == 0) goto L51
            r4.close()
        L51:
            return r5
        L52:
            r5 = move-exception
            if (r4 == 0) goto L5d
            r4.close()     // Catch: java.lang.Throwable -> L59
            goto L5d
        L59:
            r4 = move-exception
            r5.addSuppressed(r4)
        L5d:
            throw r5
        L5e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseObject(byte[], int, int, com.alibaba.fastjson2.JSONReader$Feature[]):com.alibaba.fastjson2.JSONObject");
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x004e, code lost:
    
        r4.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static com.alibaba.fastjson2.JSONObject parseObject(char[] r4, int r5, int r6, com.alibaba.fastjson2.JSONReader.Feature... r7) {
        /*
            r0 = 0
            if (r4 == 0) goto L5e
            int r1 = r4.length
            if (r1 == 0) goto L5e
            if (r6 != 0) goto L9
            goto L5e
        L9:
            com.alibaba.fastjson2.JSONReader$Context r7 = com.alibaba.fastjson2.JSONFactory.createReadContext(r7)
            com.alibaba.fastjson2.JSONReader r4 = com.alibaba.fastjson2.JSONReader.of(r4, r5, r6, r7)
            boolean r5 = r4.nextIfNull()     // Catch: java.lang.Throwable -> L52
            if (r5 == 0) goto L1d
            if (r4 == 0) goto L1c
            r4.close()
        L1c:
            return r0
        L1d:
            com.alibaba.fastjson2.JSONObject r5 = new com.alibaba.fastjson2.JSONObject     // Catch: java.lang.Throwable -> L52
            r5.<init>()     // Catch: java.lang.Throwable -> L52
            r0 = 0
            r4.read(r5, r0)     // Catch: java.lang.Throwable -> L52
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r6 = r4.resolveTasks     // Catch: java.lang.Throwable -> L52
            if (r6 == 0) goto L2e
            r4.handleResolveTasks(r5)     // Catch: java.lang.Throwable -> L52
        L2e:
            char r6 = r4.ch     // Catch: java.lang.Throwable -> L52
            r2 = 26
            if (r6 == r2) goto L4c
            long r6 = r7.features     // Catch: java.lang.Throwable -> L52
            com.alibaba.fastjson2.JSONReader$Feature r2 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L52
            long r2 = r2.mask     // Catch: java.lang.Throwable -> L52
            long r6 = r6 & r2
            int r6 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r6 == 0) goto L40
            goto L4c
        L40:
            com.alibaba.fastjson2.JSONException r5 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L52
            java.lang.String r6 = "input not end"
            java.lang.String r6 = r4.info(r6)     // Catch: java.lang.Throwable -> L52
            r5.<init>(r6)     // Catch: java.lang.Throwable -> L52
            throw r5     // Catch: java.lang.Throwable -> L52
        L4c:
            if (r4 == 0) goto L51
            r4.close()
        L51:
            return r5
        L52:
            r5 = move-exception
            if (r4 == 0) goto L5d
            r4.close()     // Catch: java.lang.Throwable -> L59
            goto L5d
        L59:
            r4 = move-exception
            r5.addSuppressed(r4)
        L5d:
            throw r5
        L5e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseObject(char[], int, int, com.alibaba.fastjson2.JSONReader$Feature[]):com.alibaba.fastjson2.JSONObject");
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x004e, code lost:
    
        r4.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static com.alibaba.fastjson2.JSONObject parseObject(byte[] r4, int r5, int r6, java.nio.charset.Charset r7, com.alibaba.fastjson2.JSONReader.Feature... r8) {
        /*
            r0 = 0
            if (r4 == 0) goto L5e
            int r1 = r4.length
            if (r1 == 0) goto L5e
            if (r6 != 0) goto L9
            goto L5e
        L9:
            com.alibaba.fastjson2.JSONReader$Context r8 = com.alibaba.fastjson2.JSONFactory.createReadContext(r8)
            com.alibaba.fastjson2.JSONReader r4 = com.alibaba.fastjson2.JSONReader.of(r4, r5, r6, r7, r8)
            boolean r5 = r4.nextIfNull()     // Catch: java.lang.Throwable -> L52
            if (r5 == 0) goto L1d
            if (r4 == 0) goto L1c
            r4.close()
        L1c:
            return r0
        L1d:
            com.alibaba.fastjson2.JSONObject r5 = new com.alibaba.fastjson2.JSONObject     // Catch: java.lang.Throwable -> L52
            r5.<init>()     // Catch: java.lang.Throwable -> L52
            r6 = 0
            r4.read(r5, r6)     // Catch: java.lang.Throwable -> L52
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r0 = r4.resolveTasks     // Catch: java.lang.Throwable -> L52
            if (r0 == 0) goto L2e
            r4.handleResolveTasks(r5)     // Catch: java.lang.Throwable -> L52
        L2e:
            char r0 = r4.ch     // Catch: java.lang.Throwable -> L52
            r1 = 26
            if (r0 == r1) goto L4c
            long r0 = r8.features     // Catch: java.lang.Throwable -> L52
            com.alibaba.fastjson2.JSONReader$Feature r8 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L52
            long r2 = r8.mask     // Catch: java.lang.Throwable -> L52
            long r0 = r0 & r2
            int r6 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r6 == 0) goto L40
            goto L4c
        L40:
            com.alibaba.fastjson2.JSONException r5 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L52
            java.lang.String r6 = "input not end"
            java.lang.String r6 = r4.info(r6)     // Catch: java.lang.Throwable -> L52
            r5.<init>(r6)     // Catch: java.lang.Throwable -> L52
            throw r5     // Catch: java.lang.Throwable -> L52
        L4c:
            if (r4 == 0) goto L51
            r4.close()
        L51:
            return r5
        L52:
            r5 = move-exception
            if (r4 == 0) goto L5d
            r4.close()     // Catch: java.lang.Throwable -> L59
            goto L5d
        L59:
            r4 = move-exception
            r5.addSuppressed(r4)
        L5d:
            throw r5
        L5e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseObject(byte[], int, int, java.nio.charset.Charset, com.alibaba.fastjson2.JSONReader$Feature[]):com.alibaba.fastjson2.JSONObject");
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0059, code lost:
    
        r7.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static <T> T parseObject(java.lang.String r12, java.lang.Class<T> r13) {
        /*
            if (r12 == 0) goto L6b
            boolean r0 = r12.isEmpty()
            if (r0 == 0) goto L9
            goto L6b
        L9:
            com.alibaba.fastjson2.reader.ObjectReaderProvider r0 = com.alibaba.fastjson2.JSONFactory.getDefaultObjectReaderProvider()
            com.alibaba.fastjson2.JSONReader$Context r1 = new com.alibaba.fastjson2.JSONReader$Context
            r1.<init>(r0)
            long r2 = com.alibaba.fastjson2.JSONFactory.defaultReaderFeatures
            com.alibaba.fastjson2.JSONReader$Feature r4 = com.alibaba.fastjson2.JSONReader.Feature.FieldBased
            long r4 = r4.mask
            long r2 = r2 & r4
            r4 = 0
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 == 0) goto L21
            r2 = 1
            goto L22
        L21:
            r2 = 0
        L22:
            com.alibaba.fastjson2.reader.ObjectReader r6 = r0.getObjectReader(r13, r2)
            com.alibaba.fastjson2.JSONReader r7 = com.alibaba.fastjson2.JSONReader.of(r12, r1)
            r9 = 0
            r10 = 0
            r8 = r13
            java.lang.Object r12 = r6.readObject(r7, r8, r9, r10)     // Catch: java.lang.Throwable -> L5d
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r13 = r7.resolveTasks     // Catch: java.lang.Throwable -> L5d
            if (r13 == 0) goto L39
            r7.handleResolveTasks(r12)     // Catch: java.lang.Throwable -> L5d
        L39:
            char r13 = r7.ch     // Catch: java.lang.Throwable -> L5d
            r0 = 26
            if (r13 == r0) goto L57
            long r0 = r1.features     // Catch: java.lang.Throwable -> L5d
            com.alibaba.fastjson2.JSONReader$Feature r13 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L5d
            long r2 = r13.mask     // Catch: java.lang.Throwable -> L5d
            long r0 = r0 & r2
            int r13 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r13 == 0) goto L4b
            goto L57
        L4b:
            com.alibaba.fastjson2.JSONException r12 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L5d
            java.lang.String r13 = "input not end"
            java.lang.String r13 = r7.info(r13)     // Catch: java.lang.Throwable -> L5d
            r12.<init>(r13)     // Catch: java.lang.Throwable -> L5d
            throw r12     // Catch: java.lang.Throwable -> L5d
        L57:
            if (r7 == 0) goto L5c
            r7.close()
        L5c:
            return r12
        L5d:
            r0 = move-exception
            r12 = r0
            if (r7 == 0) goto L6a
            r7.close()     // Catch: java.lang.Throwable -> L65
            goto L6a
        L65:
            r0 = move-exception
            r13 = r0
            r12.addSuppressed(r13)
        L6a:
            throw r12
        L6b:
            r12 = 0
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseObject(java.lang.String, java.lang.Class):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x0064, code lost:
    
        r6.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static <T> T parseObject(java.lang.String r11, java.lang.Class<T> r12, com.alibaba.fastjson2.filter.Filter r13, com.alibaba.fastjson2.JSONReader.Feature... r14) {
        /*
            r0 = 0
            if (r11 == 0) goto L76
            boolean r1 = r11.isEmpty()
            if (r1 == 0) goto Lb
            goto L76
        Lb:
            com.alibaba.fastjson2.JSONReader$Context r13 = com.alibaba.fastjson2.JSONFactory.createReadContext(r13, r14)
            long r1 = r13.features
            com.alibaba.fastjson2.JSONReader$Feature r14 = com.alibaba.fastjson2.JSONReader.Feature.FieldBased
            long r3 = r14.mask
            long r1 = r1 & r3
            r3 = 0
            int r14 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r14 == 0) goto L1e
            r14 = 1
            goto L1f
        L1e:
            r14 = 0
        L1f:
            com.alibaba.fastjson2.reader.ObjectReaderProvider r1 = r13.provider
            com.alibaba.fastjson2.reader.ObjectReader r5 = r1.getObjectReader(r12, r14)
            com.alibaba.fastjson2.JSONReader r6 = com.alibaba.fastjson2.JSONReader.of(r11, r13)
            boolean r11 = r6.nextIfNull()     // Catch: java.lang.Throwable -> L68
            if (r11 == 0) goto L35
            if (r6 == 0) goto L34
            r6.close()
        L34:
            return r0
        L35:
            r8 = 0
            r9 = 0
            r7 = r12
            java.lang.Object r11 = r5.readObject(r6, r7, r8, r9)     // Catch: java.lang.Throwable -> L68
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r12 = r6.resolveTasks     // Catch: java.lang.Throwable -> L68
            if (r12 == 0) goto L44
            r6.handleResolveTasks(r11)     // Catch: java.lang.Throwable -> L68
        L44:
            char r12 = r6.ch     // Catch: java.lang.Throwable -> L68
            r14 = 26
            if (r12 == r14) goto L62
            long r12 = r13.features     // Catch: java.lang.Throwable -> L68
            com.alibaba.fastjson2.JSONReader$Feature r14 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L68
            long r0 = r14.mask     // Catch: java.lang.Throwable -> L68
            long r12 = r12 & r0
            int r12 = (r12 > r3 ? 1 : (r12 == r3 ? 0 : -1))
            if (r12 == 0) goto L56
            goto L62
        L56:
            com.alibaba.fastjson2.JSONException r11 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L68
            java.lang.String r12 = "input not end"
            java.lang.String r12 = r6.info(r12)     // Catch: java.lang.Throwable -> L68
            r11.<init>(r12)     // Catch: java.lang.Throwable -> L68
            throw r11     // Catch: java.lang.Throwable -> L68
        L62:
            if (r6 == 0) goto L67
            r6.close()
        L67:
            return r11
        L68:
            r0 = move-exception
            r11 = r0
            if (r6 == 0) goto L75
            r6.close()     // Catch: java.lang.Throwable -> L70
            goto L75
        L70:
            r0 = move-exception
            r12 = r0
            r11.addSuppressed(r12)
        L75:
            throw r11
        L76:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseObject(java.lang.String, java.lang.Class, com.alibaba.fastjson2.filter.Filter, com.alibaba.fastjson2.JSONReader$Feature[]):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x006c, code lost:
    
        r5.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static <T> T parseObject(java.lang.String r10, java.lang.reflect.Type r11, java.lang.String r12, com.alibaba.fastjson2.filter.Filter[] r13, com.alibaba.fastjson2.JSONReader.Feature... r14) {
        /*
            r0 = 0
            if (r10 == 0) goto L7e
            boolean r1 = r10.isEmpty()
            if (r1 == 0) goto Lb
            goto L7e
        Lb:
            com.alibaba.fastjson2.JSONReader$Context r1 = new com.alibaba.fastjson2.JSONReader$Context
            com.alibaba.fastjson2.reader.ObjectReaderProvider r2 = com.alibaba.fastjson2.JSONFactory.getDefaultObjectReaderProvider()
            r1.<init>(r2, r0, r13, r14)
            r1.setDateFormat(r12)
            long r12 = r1.features
            com.alibaba.fastjson2.JSONReader$Feature r14 = com.alibaba.fastjson2.JSONReader.Feature.FieldBased
            long r2 = r14.mask
            long r12 = r12 & r2
            r2 = 0
            int r12 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1))
            if (r12 == 0) goto L26
            r12 = 1
            goto L27
        L26:
            r12 = 0
        L27:
            com.alibaba.fastjson2.reader.ObjectReaderProvider r13 = r1.provider
            com.alibaba.fastjson2.reader.ObjectReader r4 = r13.getObjectReader(r11, r12)
            com.alibaba.fastjson2.JSONReader r5 = com.alibaba.fastjson2.JSONReader.of(r10, r1)
            boolean r10 = r5.nextIfNull()     // Catch: java.lang.Throwable -> L70
            if (r10 == 0) goto L3d
            if (r5 == 0) goto L3c
            r5.close()
        L3c:
            return r0
        L3d:
            r7 = 0
            r8 = 0
            r6 = r11
            java.lang.Object r10 = r4.readObject(r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L70
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r11 = r5.resolveTasks     // Catch: java.lang.Throwable -> L70
            if (r11 == 0) goto L4c
            r5.handleResolveTasks(r10)     // Catch: java.lang.Throwable -> L70
        L4c:
            char r11 = r5.ch     // Catch: java.lang.Throwable -> L70
            r12 = 26
            if (r11 == r12) goto L6a
            long r11 = r1.features     // Catch: java.lang.Throwable -> L70
            com.alibaba.fastjson2.JSONReader$Feature r13 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L70
            long r13 = r13.mask     // Catch: java.lang.Throwable -> L70
            long r11 = r11 & r13
            int r11 = (r11 > r2 ? 1 : (r11 == r2 ? 0 : -1))
            if (r11 == 0) goto L5e
            goto L6a
        L5e:
            com.alibaba.fastjson2.JSONException r10 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L70
            java.lang.String r11 = "input not end"
            java.lang.String r11 = r5.info(r11)     // Catch: java.lang.Throwable -> L70
            r10.<init>(r11)     // Catch: java.lang.Throwable -> L70
            throw r10     // Catch: java.lang.Throwable -> L70
        L6a:
            if (r5 == 0) goto L6f
            r5.close()
        L6f:
            return r10
        L70:
            r0 = move-exception
            r10 = r0
            if (r5 == 0) goto L7d
            r5.close()     // Catch: java.lang.Throwable -> L78
            goto L7d
        L78:
            r0 = move-exception
            r11 = r0
            r10.addSuppressed(r11)
        L7d:
            throw r10
        L7e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseObject(java.lang.String, java.lang.reflect.Type, java.lang.String, com.alibaba.fastjson2.filter.Filter[], com.alibaba.fastjson2.JSONReader$Feature[]):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0059, code lost:
    
        r7.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static <T> T parseObject(java.lang.String r12, java.lang.reflect.Type r13) {
        /*
            if (r12 == 0) goto L6b
            boolean r0 = r12.isEmpty()
            if (r0 == 0) goto L9
            goto L6b
        L9:
            com.alibaba.fastjson2.reader.ObjectReaderProvider r0 = com.alibaba.fastjson2.JSONFactory.getDefaultObjectReaderProvider()
            com.alibaba.fastjson2.JSONReader$Context r1 = new com.alibaba.fastjson2.JSONReader$Context
            r1.<init>(r0)
            long r2 = com.alibaba.fastjson2.JSONFactory.defaultReaderFeatures
            com.alibaba.fastjson2.JSONReader$Feature r4 = com.alibaba.fastjson2.JSONReader.Feature.FieldBased
            long r4 = r4.mask
            long r2 = r2 & r4
            r4 = 0
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 == 0) goto L21
            r2 = 1
            goto L22
        L21:
            r2 = 0
        L22:
            com.alibaba.fastjson2.reader.ObjectReader r6 = r0.getObjectReader(r13, r2)
            com.alibaba.fastjson2.JSONReader r7 = com.alibaba.fastjson2.JSONReader.of(r12, r1)
            r9 = 0
            r10 = 0
            r8 = r13
            java.lang.Object r12 = r6.readObject(r7, r8, r9, r10)     // Catch: java.lang.Throwable -> L5d
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r13 = r7.resolveTasks     // Catch: java.lang.Throwable -> L5d
            if (r13 == 0) goto L39
            r7.handleResolveTasks(r12)     // Catch: java.lang.Throwable -> L5d
        L39:
            char r13 = r7.ch     // Catch: java.lang.Throwable -> L5d
            r0 = 26
            if (r13 == r0) goto L57
            long r0 = r1.features     // Catch: java.lang.Throwable -> L5d
            com.alibaba.fastjson2.JSONReader$Feature r13 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L5d
            long r2 = r13.mask     // Catch: java.lang.Throwable -> L5d
            long r0 = r0 & r2
            int r13 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r13 == 0) goto L4b
            goto L57
        L4b:
            com.alibaba.fastjson2.JSONException r12 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L5d
            java.lang.String r13 = "input not end"
            java.lang.String r13 = r7.info(r13)     // Catch: java.lang.Throwable -> L5d
            r12.<init>(r13)     // Catch: java.lang.Throwable -> L5d
            throw r12     // Catch: java.lang.Throwable -> L5d
        L57:
            if (r7 == 0) goto L5c
            r7.close()
        L5c:
            return r12
        L5d:
            r0 = move-exception
            r12 = r0
            if (r7 == 0) goto L6a
            r7.close()     // Catch: java.lang.Throwable -> L65
            goto L6a
        L65:
            r0 = move-exception
            r13 = r0
            r12.addSuppressed(r13)
        L6a:
            throw r12
        L6b:
            r12 = 0
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseObject(java.lang.String, java.lang.reflect.Type):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0042, code lost:
    
        r2.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static <T> T parseObject(java.lang.String r7, java.lang.reflect.Type r8, com.alibaba.fastjson2.JSONReader.Context r9) {
        /*
            if (r7 == 0) goto L54
            boolean r0 = r7.isEmpty()
            if (r0 == 0) goto L9
            goto L54
        L9:
            com.alibaba.fastjson2.reader.ObjectReader r1 = r9.getObjectReader(r8)
            com.alibaba.fastjson2.JSONReader r2 = com.alibaba.fastjson2.JSONReader.of(r7, r9)
            r4 = 0
            r5 = 0
            r3 = r8
            java.lang.Object r7 = r1.readObject(r2, r3, r4, r5)     // Catch: java.lang.Throwable -> L46
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r8 = r2.resolveTasks     // Catch: java.lang.Throwable -> L46
            if (r8 == 0) goto L20
            r2.handleResolveTasks(r7)     // Catch: java.lang.Throwable -> L46
        L20:
            char r8 = r2.ch     // Catch: java.lang.Throwable -> L46
            r0 = 26
            if (r8 == r0) goto L40
            long r8 = r9.features     // Catch: java.lang.Throwable -> L46
            com.alibaba.fastjson2.JSONReader$Feature r0 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L46
            long r0 = r0.mask     // Catch: java.lang.Throwable -> L46
            long r8 = r8 & r0
            r0 = 0
            int r8 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            if (r8 == 0) goto L34
            goto L40
        L34:
            com.alibaba.fastjson2.JSONException r7 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L46
            java.lang.String r8 = "input not end"
            java.lang.String r8 = r2.info(r8)     // Catch: java.lang.Throwable -> L46
            r7.<init>(r8)     // Catch: java.lang.Throwable -> L46
            throw r7     // Catch: java.lang.Throwable -> L46
        L40:
            if (r2 == 0) goto L45
            r2.close()
        L45:
            return r7
        L46:
            r0 = move-exception
            r7 = r0
            if (r2 == 0) goto L53
            r2.close()     // Catch: java.lang.Throwable -> L4e
            goto L53
        L4e:
            r0 = move-exception
            r8 = r0
            r7.addSuppressed(r8)
        L53:
            throw r7
        L54:
            r7 = 0
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseObject(java.lang.String, java.lang.reflect.Type, com.alibaba.fastjson2.JSONReader$Context):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0048, code lost:
    
        r2.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static <T extends java.util.Map<java.lang.String, java.lang.Object>> T parseObject(java.lang.String r7, com.alibaba.fastjson2.util.MapMultiValueType<T> r8) {
        /*
            if (r7 == 0) goto L5a
            boolean r0 = r7.isEmpty()
            if (r0 == 0) goto L9
            goto L5a
        L9:
            com.alibaba.fastjson2.JSONReader$Context r0 = com.alibaba.fastjson2.JSONFactory.createReadContext()
            com.alibaba.fastjson2.reader.ObjectReader r1 = r0.getObjectReader(r8)
            com.alibaba.fastjson2.JSONReader r2 = com.alibaba.fastjson2.JSONReader.of(r7, r0)
            r4 = 0
            r5 = 0
            r3 = r8
            java.lang.Object r7 = r1.readObject(r2, r3, r4, r5)     // Catch: java.lang.Throwable -> L4c
            java.util.Map r7 = (java.util.Map) r7     // Catch: java.lang.Throwable -> L4c
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r8 = r2.resolveTasks     // Catch: java.lang.Throwable -> L4c
            if (r8 == 0) goto L26
            r2.handleResolveTasks(r7)     // Catch: java.lang.Throwable -> L4c
        L26:
            char r8 = r2.ch     // Catch: java.lang.Throwable -> L4c
            r1 = 26
            if (r8 == r1) goto L46
            long r0 = r0.features     // Catch: java.lang.Throwable -> L4c
            com.alibaba.fastjson2.JSONReader$Feature r8 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L4c
            long r3 = r8.mask     // Catch: java.lang.Throwable -> L4c
            long r0 = r0 & r3
            r3 = 0
            int r8 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r8 == 0) goto L3a
            goto L46
        L3a:
            com.alibaba.fastjson2.JSONException r7 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L4c
            java.lang.String r8 = "input not end"
            java.lang.String r8 = r2.info(r8)     // Catch: java.lang.Throwable -> L4c
            r7.<init>(r8)     // Catch: java.lang.Throwable -> L4c
            throw r7     // Catch: java.lang.Throwable -> L4c
        L46:
            if (r2 == 0) goto L4b
            r2.close()
        L4b:
            return r7
        L4c:
            r0 = move-exception
            r7 = r0
            if (r2 == 0) goto L59
            r2.close()     // Catch: java.lang.Throwable -> L54
            goto L59
        L54:
            r0 = move-exception
            r8 = r0
            r7.addSuppressed(r8)
        L59:
            throw r7
        L5a:
            r7 = 0
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseObject(java.lang.String, com.alibaba.fastjson2.util.MapMultiValueType):java.util.Map");
    }

    static <T> T parseObject(String str, Type... typeArr) {
        return (T) parseObject(str, new MultiType(typeArr));
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0059, code lost:
    
        r1.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static <T> T parseObject(java.lang.String r8, com.alibaba.fastjson2.TypeReference<T> r9, com.alibaba.fastjson2.JSONReader.Feature... r10) {
        /*
            if (r8 == 0) goto L6b
            boolean r0 = r8.isEmpty()
            if (r0 == 0) goto L9
            goto L6b
        L9:
            com.alibaba.fastjson2.JSONReader$Context r10 = com.alibaba.fastjson2.JSONFactory.createReadContext(r10)
            java.lang.reflect.Type r2 = r9.getType()
            long r0 = r10.features
            com.alibaba.fastjson2.JSONReader$Feature r9 = com.alibaba.fastjson2.JSONReader.Feature.FieldBased
            long r3 = r9.mask
            long r0 = r0 & r3
            r6 = 0
            int r9 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r9 == 0) goto L20
            r9 = 1
            goto L21
        L20:
            r9 = 0
        L21:
            com.alibaba.fastjson2.reader.ObjectReaderProvider r0 = r10.provider
            com.alibaba.fastjson2.reader.ObjectReader r0 = r0.getObjectReader(r2, r9)
            com.alibaba.fastjson2.JSONReader r1 = com.alibaba.fastjson2.JSONReader.of(r8, r10)
            r3 = 0
            r4 = 0
            java.lang.Object r8 = r0.readObject(r1, r2, r3, r4)     // Catch: java.lang.Throwable -> L5d
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r9 = r1.resolveTasks     // Catch: java.lang.Throwable -> L5d
            if (r9 == 0) goto L39
            r1.handleResolveTasks(r8)     // Catch: java.lang.Throwable -> L5d
        L39:
            char r9 = r1.ch     // Catch: java.lang.Throwable -> L5d
            r0 = 26
            if (r9 == r0) goto L57
            long r9 = r10.features     // Catch: java.lang.Throwable -> L5d
            com.alibaba.fastjson2.JSONReader$Feature r0 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L5d
            long r2 = r0.mask     // Catch: java.lang.Throwable -> L5d
            long r9 = r9 & r2
            int r9 = (r9 > r6 ? 1 : (r9 == r6 ? 0 : -1))
            if (r9 == 0) goto L4b
            goto L57
        L4b:
            com.alibaba.fastjson2.JSONException r8 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L5d
            java.lang.String r9 = "input not end"
            java.lang.String r9 = r1.info(r9)     // Catch: java.lang.Throwable -> L5d
            r8.<init>(r9)     // Catch: java.lang.Throwable -> L5d
            throw r8     // Catch: java.lang.Throwable -> L5d
        L57:
            if (r1 == 0) goto L5c
            r1.close()
        L5c:
            return r8
        L5d:
            r0 = move-exception
            r8 = r0
            if (r1 == 0) goto L6a
            r1.close()     // Catch: java.lang.Throwable -> L65
            goto L6a
        L65:
            r0 = move-exception
            r9 = r0
            r8.addSuppressed(r9)
        L6a:
            throw r8
        L6b:
            r8 = 0
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseObject(java.lang.String, com.alibaba.fastjson2.TypeReference, com.alibaba.fastjson2.JSONReader$Feature[]):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0059, code lost:
    
        r1.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static <T> T parseObject(java.lang.String r8, com.alibaba.fastjson2.TypeReference<T> r9, com.alibaba.fastjson2.filter.Filter r10, com.alibaba.fastjson2.JSONReader.Feature... r11) {
        /*
            if (r8 == 0) goto L6b
            boolean r0 = r8.isEmpty()
            if (r0 == 0) goto L9
            goto L6b
        L9:
            com.alibaba.fastjson2.JSONReader$Context r10 = com.alibaba.fastjson2.JSONFactory.createReadContext(r10, r11)
            java.lang.reflect.Type r2 = r9.getType()
            long r0 = r10.features
            com.alibaba.fastjson2.JSONReader$Feature r9 = com.alibaba.fastjson2.JSONReader.Feature.FieldBased
            long r3 = r9.mask
            long r0 = r0 & r3
            r6 = 0
            int r9 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r9 == 0) goto L20
            r9 = 1
            goto L21
        L20:
            r9 = 0
        L21:
            com.alibaba.fastjson2.reader.ObjectReaderProvider r11 = r10.provider
            com.alibaba.fastjson2.reader.ObjectReader r0 = r11.getObjectReader(r2, r9)
            com.alibaba.fastjson2.JSONReader r1 = com.alibaba.fastjson2.JSONReader.of(r8, r10)
            r3 = 0
            r4 = 0
            java.lang.Object r8 = r0.readObject(r1, r2, r3, r4)     // Catch: java.lang.Throwable -> L5d
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r9 = r1.resolveTasks     // Catch: java.lang.Throwable -> L5d
            if (r9 == 0) goto L39
            r1.handleResolveTasks(r8)     // Catch: java.lang.Throwable -> L5d
        L39:
            char r9 = r1.ch     // Catch: java.lang.Throwable -> L5d
            r11 = 26
            if (r9 == r11) goto L57
            long r9 = r10.features     // Catch: java.lang.Throwable -> L5d
            com.alibaba.fastjson2.JSONReader$Feature r11 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L5d
            long r2 = r11.mask     // Catch: java.lang.Throwable -> L5d
            long r9 = r9 & r2
            int r9 = (r9 > r6 ? 1 : (r9 == r6 ? 0 : -1))
            if (r9 == 0) goto L4b
            goto L57
        L4b:
            com.alibaba.fastjson2.JSONException r8 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L5d
            java.lang.String r9 = "input not end"
            java.lang.String r9 = r1.info(r9)     // Catch: java.lang.Throwable -> L5d
            r8.<init>(r9)     // Catch: java.lang.Throwable -> L5d
            throw r8     // Catch: java.lang.Throwable -> L5d
        L57:
            if (r1 == 0) goto L5c
            r1.close()
        L5c:
            return r8
        L5d:
            r0 = move-exception
            r8 = r0
            if (r1 == 0) goto L6a
            r1.close()     // Catch: java.lang.Throwable -> L65
            goto L6a
        L65:
            r0 = move-exception
            r9 = r0
            r8.addSuppressed(r9)
        L6a:
            throw r8
        L6b:
            r8 = 0
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseObject(java.lang.String, com.alibaba.fastjson2.TypeReference, com.alibaba.fastjson2.filter.Filter, com.alibaba.fastjson2.JSONReader$Feature[]):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0056, code lost:
    
        r5.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static <T> T parseObject(java.lang.String r10, java.lang.Class<T> r11, com.alibaba.fastjson2.JSONReader.Feature... r12) {
        /*
            if (r10 == 0) goto L68
            boolean r0 = r10.isEmpty()
            if (r0 == 0) goto L9
            goto L68
        L9:
            com.alibaba.fastjson2.JSONReader$Context r12 = com.alibaba.fastjson2.JSONFactory.createReadContext(r12)
            long r0 = r12.features
            com.alibaba.fastjson2.JSONReader$Feature r2 = com.alibaba.fastjson2.JSONReader.Feature.FieldBased
            long r2 = r2.mask
            long r0 = r0 & r2
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 == 0) goto L1c
            r0 = 1
            goto L1d
        L1c:
            r0 = 0
        L1d:
            com.alibaba.fastjson2.reader.ObjectReaderProvider r1 = r12.provider
            com.alibaba.fastjson2.reader.ObjectReader r4 = r1.getObjectReader(r11, r0)
            com.alibaba.fastjson2.JSONReader r5 = com.alibaba.fastjson2.JSONReader.of(r10, r12)
            r7 = 0
            r8 = 0
            r6 = r11
            java.lang.Object r10 = r4.readObject(r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L5a
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r11 = r5.resolveTasks     // Catch: java.lang.Throwable -> L5a
            if (r11 == 0) goto L36
            r5.handleResolveTasks(r10)     // Catch: java.lang.Throwable -> L5a
        L36:
            char r11 = r5.ch     // Catch: java.lang.Throwable -> L5a
            r0 = 26
            if (r11 == r0) goto L54
            long r11 = r12.features     // Catch: java.lang.Throwable -> L5a
            com.alibaba.fastjson2.JSONReader$Feature r0 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L5a
            long r0 = r0.mask     // Catch: java.lang.Throwable -> L5a
            long r11 = r11 & r0
            int r11 = (r11 > r2 ? 1 : (r11 == r2 ? 0 : -1))
            if (r11 == 0) goto L48
            goto L54
        L48:
            com.alibaba.fastjson2.JSONException r10 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L5a
            java.lang.String r11 = "input not end"
            java.lang.String r11 = r5.info(r11)     // Catch: java.lang.Throwable -> L5a
            r10.<init>(r11)     // Catch: java.lang.Throwable -> L5a
            throw r10     // Catch: java.lang.Throwable -> L5a
        L54:
            if (r5 == 0) goto L59
            r5.close()
        L59:
            return r10
        L5a:
            r0 = move-exception
            r10 = r0
            if (r5 == 0) goto L67
            r5.close()     // Catch: java.lang.Throwable -> L62
            goto L67
        L62:
            r0 = move-exception
            r11 = r0
            r10.addSuppressed(r11)
        L67:
            throw r10
        L68:
            r10 = 0
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseObject(java.lang.String, java.lang.Class, com.alibaba.fastjson2.JSONReader$Feature[]):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0058, code lost:
    
        r5.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static <T> T parseObject(java.lang.String r10, int r11, int r12, java.lang.Class<T> r13, com.alibaba.fastjson2.JSONReader.Feature... r14) {
        /*
            if (r10 == 0) goto L6a
            boolean r0 = r10.isEmpty()
            if (r0 != 0) goto L6a
            if (r12 != 0) goto Lb
            goto L6a
        Lb:
            com.alibaba.fastjson2.JSONReader$Context r14 = com.alibaba.fastjson2.JSONFactory.createReadContext(r14)
            long r0 = r14.features
            com.alibaba.fastjson2.JSONReader$Feature r2 = com.alibaba.fastjson2.JSONReader.Feature.FieldBased
            long r2 = r2.mask
            long r0 = r0 & r2
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 == 0) goto L1e
            r0 = 1
            goto L1f
        L1e:
            r0 = 0
        L1f:
            com.alibaba.fastjson2.reader.ObjectReaderProvider r1 = r14.provider
            com.alibaba.fastjson2.reader.ObjectReader r4 = r1.getObjectReader(r13, r0)
            com.alibaba.fastjson2.JSONReader r5 = com.alibaba.fastjson2.JSONReader.of(r10, r11, r12, r14)
            r7 = 0
            r8 = 0
            r6 = r13
            java.lang.Object r10 = r4.readObject(r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L5c
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r11 = r5.resolveTasks     // Catch: java.lang.Throwable -> L5c
            if (r11 == 0) goto L38
            r5.handleResolveTasks(r10)     // Catch: java.lang.Throwable -> L5c
        L38:
            char r11 = r5.ch     // Catch: java.lang.Throwable -> L5c
            r12 = 26
            if (r11 == r12) goto L56
            long r11 = r14.features     // Catch: java.lang.Throwable -> L5c
            com.alibaba.fastjson2.JSONReader$Feature r13 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L5c
            long r13 = r13.mask     // Catch: java.lang.Throwable -> L5c
            long r11 = r11 & r13
            int r11 = (r11 > r2 ? 1 : (r11 == r2 ? 0 : -1))
            if (r11 == 0) goto L4a
            goto L56
        L4a:
            com.alibaba.fastjson2.JSONException r10 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L5c
            java.lang.String r11 = "input not end"
            java.lang.String r11 = r5.info(r11)     // Catch: java.lang.Throwable -> L5c
            r10.<init>(r11)     // Catch: java.lang.Throwable -> L5c
            throw r10     // Catch: java.lang.Throwable -> L5c
        L56:
            if (r5 == 0) goto L5b
            r5.close()
        L5b:
            return r10
        L5c:
            r0 = move-exception
            r10 = r0
            if (r5 == 0) goto L69
            r5.close()     // Catch: java.lang.Throwable -> L64
            goto L69
        L64:
            r0 = move-exception
            r11 = r0
            r10.addSuppressed(r11)
        L69:
            throw r10
        L6a:
            r10 = 0
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseObject(java.lang.String, int, int, java.lang.Class, com.alibaba.fastjson2.JSONReader$Feature[]):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0052, code lost:
    
        r5.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static <T> T parseObject(java.lang.String r10, java.lang.Class<T> r11, com.alibaba.fastjson2.JSONReader.Context r12) {
        /*
            if (r10 == 0) goto L64
            boolean r0 = r10.isEmpty()
            if (r0 == 0) goto L9
            goto L64
        L9:
            long r0 = r12.features
            com.alibaba.fastjson2.JSONReader$Feature r2 = com.alibaba.fastjson2.JSONReader.Feature.FieldBased
            long r2 = r2.mask
            long r0 = r0 & r2
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 == 0) goto L18
            r0 = 1
            goto L19
        L18:
            r0 = 0
        L19:
            com.alibaba.fastjson2.reader.ObjectReaderProvider r1 = r12.provider
            com.alibaba.fastjson2.reader.ObjectReader r4 = r1.getObjectReader(r11, r0)
            com.alibaba.fastjson2.JSONReader r5 = com.alibaba.fastjson2.JSONReader.of(r10, r12)
            r7 = 0
            r8 = 0
            r6 = r11
            java.lang.Object r10 = r4.readObject(r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L56
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r11 = r5.resolveTasks     // Catch: java.lang.Throwable -> L56
            if (r11 == 0) goto L32
            r5.handleResolveTasks(r10)     // Catch: java.lang.Throwable -> L56
        L32:
            char r11 = r5.ch     // Catch: java.lang.Throwable -> L56
            r0 = 26
            if (r11 == r0) goto L50
            long r11 = r12.features     // Catch: java.lang.Throwable -> L56
            com.alibaba.fastjson2.JSONReader$Feature r0 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L56
            long r0 = r0.mask     // Catch: java.lang.Throwable -> L56
            long r11 = r11 & r0
            int r11 = (r11 > r2 ? 1 : (r11 == r2 ? 0 : -1))
            if (r11 == 0) goto L44
            goto L50
        L44:
            com.alibaba.fastjson2.JSONException r10 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L56
            java.lang.String r11 = "input not end"
            java.lang.String r11 = r5.info(r11)     // Catch: java.lang.Throwable -> L56
            r10.<init>(r11)     // Catch: java.lang.Throwable -> L56
            throw r10     // Catch: java.lang.Throwable -> L56
        L50:
            if (r5 == 0) goto L55
            r5.close()
        L55:
            return r10
        L56:
            r0 = move-exception
            r10 = r0
            if (r5 == 0) goto L63
            r5.close()     // Catch: java.lang.Throwable -> L5e
            goto L63
        L5e:
            r0 = move-exception
            r11 = r0
            r10.addSuppressed(r11)
        L63:
            throw r10
        L64:
            r10 = 0
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseObject(java.lang.String, java.lang.Class, com.alibaba.fastjson2.JSONReader$Context):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0062, code lost:
    
        r5.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static <T> T parseObject(java.lang.String r10, java.lang.Class<T> r11, java.lang.String r12, com.alibaba.fastjson2.JSONReader.Feature... r13) {
        /*
            if (r10 == 0) goto L74
            boolean r0 = r10.isEmpty()
            if (r0 == 0) goto La
            goto L74
        La:
            com.alibaba.fastjson2.JSONReader$Context r13 = com.alibaba.fastjson2.JSONFactory.createReadContext(r13)
            if (r12 == 0) goto L19
            boolean r0 = r12.isEmpty()
            if (r0 != 0) goto L19
            r13.setDateFormat(r12)
        L19:
            long r0 = r13.features
            com.alibaba.fastjson2.JSONReader$Feature r12 = com.alibaba.fastjson2.JSONReader.Feature.FieldBased
            long r2 = r12.mask
            long r0 = r0 & r2
            r2 = 0
            int r12 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r12 == 0) goto L28
            r12 = 1
            goto L29
        L28:
            r12 = 0
        L29:
            com.alibaba.fastjson2.reader.ObjectReaderProvider r0 = r13.provider
            com.alibaba.fastjson2.reader.ObjectReader r4 = r0.getObjectReader(r11, r12)
            com.alibaba.fastjson2.JSONReader r5 = com.alibaba.fastjson2.JSONReader.of(r10, r13)
            r7 = 0
            r8 = 0
            r6 = r11
            java.lang.Object r10 = r4.readObject(r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L66
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r11 = r5.resolveTasks     // Catch: java.lang.Throwable -> L66
            if (r11 == 0) goto L42
            r5.handleResolveTasks(r10)     // Catch: java.lang.Throwable -> L66
        L42:
            char r11 = r5.ch     // Catch: java.lang.Throwable -> L66
            r12 = 26
            if (r11 == r12) goto L60
            long r11 = r13.features     // Catch: java.lang.Throwable -> L66
            com.alibaba.fastjson2.JSONReader$Feature r13 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L66
            long r0 = r13.mask     // Catch: java.lang.Throwable -> L66
            long r11 = r11 & r0
            int r11 = (r11 > r2 ? 1 : (r11 == r2 ? 0 : -1))
            if (r11 == 0) goto L54
            goto L60
        L54:
            com.alibaba.fastjson2.JSONException r10 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L66
            java.lang.String r11 = "input not end"
            java.lang.String r11 = r5.info(r11)     // Catch: java.lang.Throwable -> L66
            r10.<init>(r11)     // Catch: java.lang.Throwable -> L66
            throw r10     // Catch: java.lang.Throwable -> L66
        L60:
            if (r5 == 0) goto L65
            r5.close()
        L65:
            return r10
        L66:
            r0 = move-exception
            r10 = r0
            if (r5 == 0) goto L73
            r5.close()     // Catch: java.lang.Throwable -> L6e
            goto L73
        L6e:
            r0 = move-exception
            r11 = r0
            r10.addSuppressed(r11)
        L73:
            throw r10
        L74:
            r10 = 0
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseObject(java.lang.String, java.lang.Class, java.lang.String, com.alibaba.fastjson2.JSONReader$Feature[]):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0046, code lost:
    
        r1.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static <T> T parseObject(java.lang.String r6, java.lang.reflect.Type r7, com.alibaba.fastjson2.JSONReader.Feature... r8) {
        /*
            if (r6 == 0) goto L58
            boolean r0 = r6.isEmpty()
            if (r0 == 0) goto L9
            goto L58
        L9:
            com.alibaba.fastjson2.JSONReader$Context r8 = com.alibaba.fastjson2.JSONFactory.createReadContext(r8)
            com.alibaba.fastjson2.reader.ObjectReader r0 = r8.getObjectReader(r7)
            com.alibaba.fastjson2.JSONReader r1 = com.alibaba.fastjson2.JSONReader.of(r6, r8)
            r3 = 0
            r4 = 0
            r2 = r7
            java.lang.Object r6 = r0.readObject(r1, r2, r3, r4)     // Catch: java.lang.Throwable -> L4a
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r7 = r1.resolveTasks     // Catch: java.lang.Throwable -> L4a
            if (r7 == 0) goto L24
            r1.handleResolveTasks(r6)     // Catch: java.lang.Throwable -> L4a
        L24:
            char r7 = r1.ch     // Catch: java.lang.Throwable -> L4a
            r0 = 26
            if (r7 == r0) goto L44
            long r7 = r8.features     // Catch: java.lang.Throwable -> L4a
            com.alibaba.fastjson2.JSONReader$Feature r0 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L4a
            long r2 = r0.mask     // Catch: java.lang.Throwable -> L4a
            long r7 = r7 & r2
            r2 = 0
            int r7 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r7 == 0) goto L38
            goto L44
        L38:
            com.alibaba.fastjson2.JSONException r6 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L4a
            java.lang.String r7 = "input not end"
            java.lang.String r7 = r1.info(r7)     // Catch: java.lang.Throwable -> L4a
            r6.<init>(r7)     // Catch: java.lang.Throwable -> L4a
            throw r6     // Catch: java.lang.Throwable -> L4a
        L44:
            if (r1 == 0) goto L49
            r1.close()
        L49:
            return r6
        L4a:
            r0 = move-exception
            r6 = r0
            if (r1 == 0) goto L57
            r1.close()     // Catch: java.lang.Throwable -> L52
            goto L57
        L52:
            r0 = move-exception
            r7 = r0
            r6.addSuppressed(r7)
        L57:
            throw r6
        L58:
            r6 = 0
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseObject(java.lang.String, java.lang.reflect.Type, com.alibaba.fastjson2.JSONReader$Feature[]):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0046, code lost:
    
        r1.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static <T> T parseObject(java.lang.String r6, java.lang.reflect.Type r7, com.alibaba.fastjson2.filter.Filter r8, com.alibaba.fastjson2.JSONReader.Feature... r9) {
        /*
            if (r6 == 0) goto L58
            boolean r0 = r6.isEmpty()
            if (r0 == 0) goto L9
            goto L58
        L9:
            com.alibaba.fastjson2.JSONReader$Context r8 = com.alibaba.fastjson2.JSONFactory.createReadContext(r8, r9)
            com.alibaba.fastjson2.reader.ObjectReader r0 = r8.getObjectReader(r7)
            com.alibaba.fastjson2.JSONReader r1 = com.alibaba.fastjson2.JSONReader.of(r6, r8)
            r3 = 0
            r4 = 0
            r2 = r7
            java.lang.Object r6 = r0.readObject(r1, r2, r3, r4)     // Catch: java.lang.Throwable -> L4a
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r7 = r1.resolveTasks     // Catch: java.lang.Throwable -> L4a
            if (r7 == 0) goto L24
            r1.handleResolveTasks(r6)     // Catch: java.lang.Throwable -> L4a
        L24:
            char r7 = r1.ch     // Catch: java.lang.Throwable -> L4a
            r9 = 26
            if (r7 == r9) goto L44
            long r7 = r8.features     // Catch: java.lang.Throwable -> L4a
            com.alibaba.fastjson2.JSONReader$Feature r9 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L4a
            long r2 = r9.mask     // Catch: java.lang.Throwable -> L4a
            long r7 = r7 & r2
            r2 = 0
            int r7 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r7 == 0) goto L38
            goto L44
        L38:
            com.alibaba.fastjson2.JSONException r6 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L4a
            java.lang.String r7 = "input not end"
            java.lang.String r7 = r1.info(r7)     // Catch: java.lang.Throwable -> L4a
            r6.<init>(r7)     // Catch: java.lang.Throwable -> L4a
            throw r6     // Catch: java.lang.Throwable -> L4a
        L44:
            if (r1 == 0) goto L49
            r1.close()
        L49:
            return r6
        L4a:
            r0 = move-exception
            r6 = r0
            if (r1 == 0) goto L57
            r1.close()     // Catch: java.lang.Throwable -> L52
            goto L57
        L52:
            r0 = move-exception
            r7 = r0
            r6.addSuppressed(r7)
        L57:
            throw r6
        L58:
            r6 = 0
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseObject(java.lang.String, java.lang.reflect.Type, com.alibaba.fastjson2.filter.Filter, com.alibaba.fastjson2.JSONReader$Feature[]):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0051, code lost:
    
        r2.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static <T> T parseObject(java.lang.String r7, java.lang.reflect.Type r8, java.lang.String r9, com.alibaba.fastjson2.JSONReader.Feature... r10) {
        /*
            if (r7 == 0) goto L63
            boolean r0 = r7.isEmpty()
            if (r0 == 0) goto L9
            goto L63
        L9:
            com.alibaba.fastjson2.JSONReader$Context r10 = com.alibaba.fastjson2.JSONFactory.createReadContext(r10)
            if (r9 == 0) goto L18
            boolean r0 = r9.isEmpty()
            if (r0 != 0) goto L18
            r10.setDateFormat(r9)
        L18:
            com.alibaba.fastjson2.JSONReader r2 = com.alibaba.fastjson2.JSONReader.of(r7, r10)
            com.alibaba.fastjson2.reader.ObjectReader r1 = r10.getObjectReader(r8)     // Catch: java.lang.Throwable -> L55
            r4 = 0
            r5 = 0
            r3 = r8
            java.lang.Object r7 = r1.readObject(r2, r3, r4, r5)     // Catch: java.lang.Throwable -> L55
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r8 = r2.resolveTasks     // Catch: java.lang.Throwable -> L55
            if (r8 == 0) goto L2f
            r2.handleResolveTasks(r7)     // Catch: java.lang.Throwable -> L55
        L2f:
            char r8 = r2.ch     // Catch: java.lang.Throwable -> L55
            r9 = 26
            if (r8 == r9) goto L4f
            long r8 = r10.features     // Catch: java.lang.Throwable -> L55
            com.alibaba.fastjson2.JSONReader$Feature r10 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L55
            long r0 = r10.mask     // Catch: java.lang.Throwable -> L55
            long r8 = r8 & r0
            r0 = 0
            int r8 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            if (r8 == 0) goto L43
            goto L4f
        L43:
            com.alibaba.fastjson2.JSONException r7 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L55
            java.lang.String r8 = "input not end"
            java.lang.String r8 = r2.info(r8)     // Catch: java.lang.Throwable -> L55
            r7.<init>(r8)     // Catch: java.lang.Throwable -> L55
            throw r7     // Catch: java.lang.Throwable -> L55
        L4f:
            if (r2 == 0) goto L54
            r2.close()
        L54:
            return r7
        L55:
            r0 = move-exception
            r7 = r0
            if (r2 == 0) goto L62
            r2.close()     // Catch: java.lang.Throwable -> L5d
            goto L62
        L5d:
            r0 = move-exception
            r8 = r0
            r7.addSuppressed(r8)
        L62:
            throw r7
        L63:
            r7 = 0
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseObject(java.lang.String, java.lang.reflect.Type, java.lang.String, com.alibaba.fastjson2.JSONReader$Feature[]):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0045, code lost:
    
        r1.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static <T> T parseObject(char[] r6, int r7, int r8, java.lang.reflect.Type r9, com.alibaba.fastjson2.JSONReader.Feature... r10) {
        /*
            if (r6 == 0) goto L57
            int r0 = r6.length
            if (r0 == 0) goto L57
            if (r8 != 0) goto L8
            goto L57
        L8:
            com.alibaba.fastjson2.JSONReader$Context r10 = com.alibaba.fastjson2.JSONFactory.createReadContext(r10)
            com.alibaba.fastjson2.reader.ObjectReader r0 = r10.getObjectReader(r9)
            com.alibaba.fastjson2.JSONReader r1 = com.alibaba.fastjson2.JSONReader.of(r6, r7, r8, r10)
            r3 = 0
            r4 = 0
            r2 = r9
            java.lang.Object r6 = r0.readObject(r1, r2, r3, r4)     // Catch: java.lang.Throwable -> L49
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r7 = r1.resolveTasks     // Catch: java.lang.Throwable -> L49
            if (r7 == 0) goto L23
            r1.handleResolveTasks(r6)     // Catch: java.lang.Throwable -> L49
        L23:
            char r7 = r1.ch     // Catch: java.lang.Throwable -> L49
            r8 = 26
            if (r7 == r8) goto L43
            long r7 = r10.features     // Catch: java.lang.Throwable -> L49
            com.alibaba.fastjson2.JSONReader$Feature r9 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L49
            long r9 = r9.mask     // Catch: java.lang.Throwable -> L49
            long r7 = r7 & r9
            r9 = 0
            int r7 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r7 == 0) goto L37
            goto L43
        L37:
            com.alibaba.fastjson2.JSONException r6 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L49
            java.lang.String r7 = "input not end"
            java.lang.String r7 = r1.info(r7)     // Catch: java.lang.Throwable -> L49
            r6.<init>(r7)     // Catch: java.lang.Throwable -> L49
            throw r6     // Catch: java.lang.Throwable -> L49
        L43:
            if (r1 == 0) goto L48
            r1.close()
        L48:
            return r6
        L49:
            r0 = move-exception
            r6 = r0
            if (r1 == 0) goto L56
            r1.close()     // Catch: java.lang.Throwable -> L51
            goto L56
        L51:
            r0 = move-exception
            r7 = r0
            r6.addSuppressed(r7)
        L56:
            throw r6
        L57:
            r6 = 0
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseObject(char[], int, int, java.lang.reflect.Type, com.alibaba.fastjson2.JSONReader$Feature[]):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0043, code lost:
    
        r2.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static <T> T parseObject(char[] r7, java.lang.Class<T> r8) {
        /*
            if (r7 == 0) goto L55
            int r0 = r7.length
            if (r0 != 0) goto L6
            goto L55
        L6:
            com.alibaba.fastjson2.JSONReader$Context r0 = com.alibaba.fastjson2.JSONFactory.createReadContext()
            com.alibaba.fastjson2.reader.ObjectReader r1 = r0.getObjectReader(r8)
            com.alibaba.fastjson2.JSONReader r2 = com.alibaba.fastjson2.JSONReader.of(r7, r0)
            r4 = 0
            r5 = 0
            r3 = r8
            java.lang.Object r7 = r1.readObject(r2, r3, r4, r5)     // Catch: java.lang.Throwable -> L47
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r8 = r2.resolveTasks     // Catch: java.lang.Throwable -> L47
            if (r8 == 0) goto L21
            r2.handleResolveTasks(r7)     // Catch: java.lang.Throwable -> L47
        L21:
            char r8 = r2.ch     // Catch: java.lang.Throwable -> L47
            r1 = 26
            if (r8 == r1) goto L41
            long r0 = r0.features     // Catch: java.lang.Throwable -> L47
            com.alibaba.fastjson2.JSONReader$Feature r8 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L47
            long r3 = r8.mask     // Catch: java.lang.Throwable -> L47
            long r0 = r0 & r3
            r3 = 0
            int r8 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r8 == 0) goto L35
            goto L41
        L35:
            com.alibaba.fastjson2.JSONException r7 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L47
            java.lang.String r8 = "input not end"
            java.lang.String r8 = r2.info(r8)     // Catch: java.lang.Throwable -> L47
            r7.<init>(r8)     // Catch: java.lang.Throwable -> L47
            throw r7     // Catch: java.lang.Throwable -> L47
        L41:
            if (r2 == 0) goto L46
            r2.close()
        L46:
            return r7
        L47:
            r0 = move-exception
            r7 = r0
            if (r2 == 0) goto L54
            r2.close()     // Catch: java.lang.Throwable -> L4f
            goto L54
        L4f:
            r0 = move-exception
            r8 = r0
            r7.addSuppressed(r8)
        L54:
            throw r7
        L55:
            r7 = 0
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseObject(char[], java.lang.Class):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0045, code lost:
    
        r1.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static <T> T parseObject(byte[] r6, int r7, int r8, java.lang.reflect.Type r9, com.alibaba.fastjson2.JSONReader.Feature... r10) {
        /*
            if (r6 == 0) goto L57
            int r0 = r6.length
            if (r0 == 0) goto L57
            if (r8 != 0) goto L8
            goto L57
        L8:
            com.alibaba.fastjson2.JSONReader$Context r10 = com.alibaba.fastjson2.JSONFactory.createReadContext(r10)
            com.alibaba.fastjson2.reader.ObjectReader r0 = r10.getObjectReader(r9)
            com.alibaba.fastjson2.JSONReader r1 = com.alibaba.fastjson2.JSONReader.of(r6, r7, r8, r10)
            r3 = 0
            r4 = 0
            r2 = r9
            java.lang.Object r6 = r0.readObject(r1, r2, r3, r4)     // Catch: java.lang.Throwable -> L49
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r7 = r1.resolveTasks     // Catch: java.lang.Throwable -> L49
            if (r7 == 0) goto L23
            r1.handleResolveTasks(r6)     // Catch: java.lang.Throwable -> L49
        L23:
            char r7 = r1.ch     // Catch: java.lang.Throwable -> L49
            r8 = 26
            if (r7 == r8) goto L43
            long r7 = r10.features     // Catch: java.lang.Throwable -> L49
            com.alibaba.fastjson2.JSONReader$Feature r9 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L49
            long r9 = r9.mask     // Catch: java.lang.Throwable -> L49
            long r7 = r7 & r9
            r9 = 0
            int r7 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r7 == 0) goto L37
            goto L43
        L37:
            com.alibaba.fastjson2.JSONException r6 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L49
            java.lang.String r7 = "input not end"
            java.lang.String r7 = r1.info(r7)     // Catch: java.lang.Throwable -> L49
            r6.<init>(r7)     // Catch: java.lang.Throwable -> L49
            throw r6     // Catch: java.lang.Throwable -> L49
        L43:
            if (r1 == 0) goto L48
            r1.close()
        L48:
            return r6
        L49:
            r0 = move-exception
            r6 = r0
            if (r1 == 0) goto L56
            r1.close()     // Catch: java.lang.Throwable -> L51
            goto L56
        L51:
            r0 = move-exception
            r7 = r0
            r6.addSuppressed(r7)
        L56:
            throw r6
        L57:
            r6 = 0
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseObject(byte[], int, int, java.lang.reflect.Type, com.alibaba.fastjson2.JSONReader$Feature[]):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0043, code lost:
    
        r2.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static <T> T parseObject(byte[] r7, java.lang.reflect.Type r8) {
        /*
            if (r7 == 0) goto L55
            int r0 = r7.length
            if (r0 != 0) goto L6
            goto L55
        L6:
            com.alibaba.fastjson2.JSONReader$Context r0 = com.alibaba.fastjson2.JSONFactory.createReadContext()
            com.alibaba.fastjson2.reader.ObjectReader r1 = r0.getObjectReader(r8)
            com.alibaba.fastjson2.JSONReader r2 = com.alibaba.fastjson2.JSONReader.of(r7, r0)
            r4 = 0
            r5 = 0
            r3 = r8
            java.lang.Object r7 = r1.readObject(r2, r3, r4, r5)     // Catch: java.lang.Throwable -> L47
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r8 = r2.resolveTasks     // Catch: java.lang.Throwable -> L47
            if (r8 == 0) goto L21
            r2.handleResolveTasks(r7)     // Catch: java.lang.Throwable -> L47
        L21:
            char r8 = r2.ch     // Catch: java.lang.Throwable -> L47
            r1 = 26
            if (r8 == r1) goto L41
            long r0 = r0.features     // Catch: java.lang.Throwable -> L47
            com.alibaba.fastjson2.JSONReader$Feature r8 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L47
            long r3 = r8.mask     // Catch: java.lang.Throwable -> L47
            long r0 = r0 & r3
            r3 = 0
            int r8 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r8 == 0) goto L35
            goto L41
        L35:
            com.alibaba.fastjson2.JSONException r7 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L47
            java.lang.String r8 = "input not end"
            java.lang.String r8 = r2.info(r8)     // Catch: java.lang.Throwable -> L47
            r7.<init>(r8)     // Catch: java.lang.Throwable -> L47
            throw r7     // Catch: java.lang.Throwable -> L47
        L41:
            if (r2 == 0) goto L46
            r2.close()
        L46:
            return r7
        L47:
            r0 = move-exception
            r7 = r0
            if (r2 == 0) goto L54
            r2.close()     // Catch: java.lang.Throwable -> L4f
            goto L54
        L4f:
            r0 = move-exception
            r8 = r0
            r7.addSuppressed(r8)
        L54:
            throw r7
        L55:
            r7 = 0
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseObject(byte[], java.lang.reflect.Type):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0056, code lost:
    
        r7.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static <T> T parseObject(byte[] r12, java.lang.Class<T> r13) {
        /*
            if (r12 == 0) goto L68
            int r0 = r12.length
            if (r0 != 0) goto L6
            goto L68
        L6:
            com.alibaba.fastjson2.reader.ObjectReaderProvider r0 = com.alibaba.fastjson2.JSONFactory.getDefaultObjectReaderProvider()
            com.alibaba.fastjson2.JSONReader$Context r1 = new com.alibaba.fastjson2.JSONReader$Context
            r1.<init>(r0)
            long r2 = com.alibaba.fastjson2.JSONFactory.defaultReaderFeatures
            com.alibaba.fastjson2.JSONReader$Feature r4 = com.alibaba.fastjson2.JSONReader.Feature.FieldBased
            long r4 = r4.mask
            long r2 = r2 & r4
            r4 = 0
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 == 0) goto L1e
            r2 = 1
            goto L1f
        L1e:
            r2 = 0
        L1f:
            com.alibaba.fastjson2.reader.ObjectReader r6 = r0.getObjectReader(r13, r2)
            com.alibaba.fastjson2.JSONReader r7 = com.alibaba.fastjson2.JSONReader.of(r12, r1)
            r9 = 0
            r10 = 0
            r8 = r13
            java.lang.Object r12 = r6.readObject(r7, r8, r9, r10)     // Catch: java.lang.Throwable -> L5a
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r13 = r7.resolveTasks     // Catch: java.lang.Throwable -> L5a
            if (r13 == 0) goto L36
            r7.handleResolveTasks(r12)     // Catch: java.lang.Throwable -> L5a
        L36:
            char r13 = r7.ch     // Catch: java.lang.Throwable -> L5a
            r0 = 26
            if (r13 == r0) goto L54
            long r0 = r1.features     // Catch: java.lang.Throwable -> L5a
            com.alibaba.fastjson2.JSONReader$Feature r13 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L5a
            long r2 = r13.mask     // Catch: java.lang.Throwable -> L5a
            long r0 = r0 & r2
            int r13 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r13 == 0) goto L48
            goto L54
        L48:
            com.alibaba.fastjson2.JSONException r12 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L5a
            java.lang.String r13 = "input not end"
            java.lang.String r13 = r7.info(r13)     // Catch: java.lang.Throwable -> L5a
            r12.<init>(r13)     // Catch: java.lang.Throwable -> L5a
            throw r12     // Catch: java.lang.Throwable -> L5a
        L54:
            if (r7 == 0) goto L59
            r7.close()
        L59:
            return r12
        L5a:
            r0 = move-exception
            r12 = r0
            if (r7 == 0) goto L67
            r7.close()     // Catch: java.lang.Throwable -> L62
            goto L67
        L62:
            r0 = move-exception
            r13 = r0
            r12.addSuppressed(r13)
        L67:
            throw r12
        L68:
            r12 = 0
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseObject(byte[], java.lang.Class):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0053, code lost:
    
        r5.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static <T> T parseObject(byte[] r10, java.lang.Class<T> r11, com.alibaba.fastjson2.filter.Filter r12, com.alibaba.fastjson2.JSONReader.Feature... r13) {
        /*
            if (r10 == 0) goto L65
            int r0 = r10.length
            if (r0 != 0) goto L6
            goto L65
        L6:
            com.alibaba.fastjson2.JSONReader$Context r12 = com.alibaba.fastjson2.JSONFactory.createReadContext(r12, r13)
            long r0 = r12.features
            com.alibaba.fastjson2.JSONReader$Feature r13 = com.alibaba.fastjson2.JSONReader.Feature.FieldBased
            long r2 = r13.mask
            long r0 = r0 & r2
            r2 = 0
            int r13 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r13 == 0) goto L19
            r13 = 1
            goto L1a
        L19:
            r13 = 0
        L1a:
            com.alibaba.fastjson2.reader.ObjectReaderProvider r0 = r12.provider
            com.alibaba.fastjson2.reader.ObjectReader r4 = r0.getObjectReader(r11, r13)
            com.alibaba.fastjson2.JSONReader r5 = com.alibaba.fastjson2.JSONReader.of(r10, r12)
            r7 = 0
            r8 = 0
            r6 = r11
            java.lang.Object r10 = r4.readObject(r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L57
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r11 = r5.resolveTasks     // Catch: java.lang.Throwable -> L57
            if (r11 == 0) goto L33
            r5.handleResolveTasks(r10)     // Catch: java.lang.Throwable -> L57
        L33:
            char r11 = r5.ch     // Catch: java.lang.Throwable -> L57
            r13 = 26
            if (r11 == r13) goto L51
            long r11 = r12.features     // Catch: java.lang.Throwable -> L57
            com.alibaba.fastjson2.JSONReader$Feature r13 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L57
            long r0 = r13.mask     // Catch: java.lang.Throwable -> L57
            long r11 = r11 & r0
            int r11 = (r11 > r2 ? 1 : (r11 == r2 ? 0 : -1))
            if (r11 == 0) goto L45
            goto L51
        L45:
            com.alibaba.fastjson2.JSONException r10 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L57
            java.lang.String r11 = "input not end"
            java.lang.String r11 = r5.info(r11)     // Catch: java.lang.Throwable -> L57
            r10.<init>(r11)     // Catch: java.lang.Throwable -> L57
            throw r10     // Catch: java.lang.Throwable -> L57
        L51:
            if (r5 == 0) goto L56
            r5.close()
        L56:
            return r10
        L57:
            r0 = move-exception
            r10 = r0
            if (r5 == 0) goto L64
            r5.close()     // Catch: java.lang.Throwable -> L5f
            goto L64
        L5f:
            r0 = move-exception
            r11 = r0
            r10.addSuppressed(r11)
        L64:
            throw r10
        L65:
            r10 = 0
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseObject(byte[], java.lang.Class, com.alibaba.fastjson2.filter.Filter, com.alibaba.fastjson2.JSONReader$Feature[]):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x004f, code lost:
    
        r5.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static <T> T parseObject(byte[] r10, java.lang.Class<T> r11, com.alibaba.fastjson2.JSONReader.Context r12) {
        /*
            if (r10 == 0) goto L61
            int r0 = r10.length
            if (r0 != 0) goto L6
            goto L61
        L6:
            long r0 = r12.features
            com.alibaba.fastjson2.JSONReader$Feature r2 = com.alibaba.fastjson2.JSONReader.Feature.FieldBased
            long r2 = r2.mask
            long r0 = r0 & r2
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 == 0) goto L15
            r0 = 1
            goto L16
        L15:
            r0 = 0
        L16:
            com.alibaba.fastjson2.reader.ObjectReaderProvider r1 = r12.provider
            com.alibaba.fastjson2.reader.ObjectReader r4 = r1.getObjectReader(r11, r0)
            com.alibaba.fastjson2.JSONReader r5 = com.alibaba.fastjson2.JSONReader.of(r10, r12)
            r7 = 0
            r8 = 0
            r6 = r11
            java.lang.Object r10 = r4.readObject(r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L53
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r11 = r5.resolveTasks     // Catch: java.lang.Throwable -> L53
            if (r11 == 0) goto L2f
            r5.handleResolveTasks(r10)     // Catch: java.lang.Throwable -> L53
        L2f:
            char r11 = r5.ch     // Catch: java.lang.Throwable -> L53
            r0 = 26
            if (r11 == r0) goto L4d
            long r11 = r12.features     // Catch: java.lang.Throwable -> L53
            com.alibaba.fastjson2.JSONReader$Feature r0 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L53
            long r0 = r0.mask     // Catch: java.lang.Throwable -> L53
            long r11 = r11 & r0
            int r11 = (r11 > r2 ? 1 : (r11 == r2 ? 0 : -1))
            if (r11 == 0) goto L41
            goto L4d
        L41:
            com.alibaba.fastjson2.JSONException r10 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L53
            java.lang.String r11 = "input not end"
            java.lang.String r11 = r5.info(r11)     // Catch: java.lang.Throwable -> L53
            r10.<init>(r11)     // Catch: java.lang.Throwable -> L53
            throw r10     // Catch: java.lang.Throwable -> L53
        L4d:
            if (r5 == 0) goto L52
            r5.close()
        L52:
            return r10
        L53:
            r0 = move-exception
            r10 = r0
            if (r5 == 0) goto L60
            r5.close()     // Catch: java.lang.Throwable -> L5b
            goto L60
        L5b:
            r0 = move-exception
            r11 = r0
            r10.addSuppressed(r11)
        L60:
            throw r10
        L61:
            r10 = 0
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseObject(byte[], java.lang.Class, com.alibaba.fastjson2.JSONReader$Context):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x005c, code lost:
    
        r5.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static <T> T parseObject(byte[] r10, java.lang.reflect.Type r11, java.lang.String r12, com.alibaba.fastjson2.filter.Filter[] r13, com.alibaba.fastjson2.JSONReader.Feature... r14) {
        /*
            r0 = 0
            if (r10 == 0) goto L6e
            int r1 = r10.length
            if (r1 != 0) goto L7
            goto L6e
        L7:
            com.alibaba.fastjson2.JSONReader$Context r1 = new com.alibaba.fastjson2.JSONReader$Context
            com.alibaba.fastjson2.reader.ObjectReaderProvider r2 = com.alibaba.fastjson2.JSONFactory.getDefaultObjectReaderProvider()
            r1.<init>(r2, r0, r13, r14)
            r1.setDateFormat(r12)
            long r12 = r1.features
            com.alibaba.fastjson2.JSONReader$Feature r14 = com.alibaba.fastjson2.JSONReader.Feature.FieldBased
            long r2 = r14.mask
            long r12 = r12 & r2
            r2 = 0
            int r12 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1))
            if (r12 == 0) goto L22
            r12 = 1
            goto L23
        L22:
            r12 = 0
        L23:
            com.alibaba.fastjson2.reader.ObjectReaderProvider r13 = r1.provider
            com.alibaba.fastjson2.reader.ObjectReader r4 = r13.getObjectReader(r11, r12)
            com.alibaba.fastjson2.JSONReader r5 = com.alibaba.fastjson2.JSONReader.of(r10, r1)
            r7 = 0
            r8 = 0
            r6 = r11
            java.lang.Object r10 = r4.readObject(r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L60
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r11 = r5.resolveTasks     // Catch: java.lang.Throwable -> L60
            if (r11 == 0) goto L3c
            r5.handleResolveTasks(r10)     // Catch: java.lang.Throwable -> L60
        L3c:
            char r11 = r5.ch     // Catch: java.lang.Throwable -> L60
            r12 = 26
            if (r11 == r12) goto L5a
            long r11 = r1.features     // Catch: java.lang.Throwable -> L60
            com.alibaba.fastjson2.JSONReader$Feature r13 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L60
            long r13 = r13.mask     // Catch: java.lang.Throwable -> L60
            long r11 = r11 & r13
            int r11 = (r11 > r2 ? 1 : (r11 == r2 ? 0 : -1))
            if (r11 == 0) goto L4e
            goto L5a
        L4e:
            com.alibaba.fastjson2.JSONException r10 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L60
            java.lang.String r11 = "input not end"
            java.lang.String r11 = r5.info(r11)     // Catch: java.lang.Throwable -> L60
            r10.<init>(r11)     // Catch: java.lang.Throwable -> L60
            throw r10     // Catch: java.lang.Throwable -> L60
        L5a:
            if (r5 == 0) goto L5f
            r5.close()
        L5f:
            return r10
        L60:
            r0 = move-exception
            r10 = r0
            if (r5 == 0) goto L6d
            r5.close()     // Catch: java.lang.Throwable -> L68
            goto L6d
        L68:
            r0 = move-exception
            r11 = r0
            r10.addSuppressed(r11)
        L6d:
            throw r10
        L6e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseObject(byte[], java.lang.reflect.Type, java.lang.String, com.alibaba.fastjson2.filter.Filter[], com.alibaba.fastjson2.JSONReader$Feature[]):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0043, code lost:
    
        r1.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static <T> T parseObject(byte[] r6, java.lang.Class<T> r7, com.alibaba.fastjson2.JSONReader.Feature... r8) {
        /*
            if (r6 == 0) goto L55
            int r0 = r6.length
            if (r0 != 0) goto L6
            goto L55
        L6:
            com.alibaba.fastjson2.JSONReader$Context r8 = com.alibaba.fastjson2.JSONFactory.createReadContext(r8)
            com.alibaba.fastjson2.reader.ObjectReader r0 = r8.getObjectReader(r7)
            com.alibaba.fastjson2.JSONReader r1 = com.alibaba.fastjson2.JSONReader.of(r6, r8)
            r3 = 0
            r4 = 0
            r2 = r7
            java.lang.Object r6 = r0.readObject(r1, r2, r3, r4)     // Catch: java.lang.Throwable -> L47
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r7 = r1.resolveTasks     // Catch: java.lang.Throwable -> L47
            if (r7 == 0) goto L21
            r1.handleResolveTasks(r6)     // Catch: java.lang.Throwable -> L47
        L21:
            char r7 = r1.ch     // Catch: java.lang.Throwable -> L47
            r0 = 26
            if (r7 == r0) goto L41
            long r7 = r8.features     // Catch: java.lang.Throwable -> L47
            com.alibaba.fastjson2.JSONReader$Feature r0 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L47
            long r2 = r0.mask     // Catch: java.lang.Throwable -> L47
            long r7 = r7 & r2
            r2 = 0
            int r7 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r7 == 0) goto L35
            goto L41
        L35:
            com.alibaba.fastjson2.JSONException r6 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L47
            java.lang.String r7 = "input not end"
            java.lang.String r7 = r1.info(r7)     // Catch: java.lang.Throwable -> L47
            r6.<init>(r7)     // Catch: java.lang.Throwable -> L47
            throw r6     // Catch: java.lang.Throwable -> L47
        L41:
            if (r1 == 0) goto L46
            r1.close()
        L46:
            return r6
        L47:
            r0 = move-exception
            r6 = r0
            if (r1 == 0) goto L54
            r1.close()     // Catch: java.lang.Throwable -> L4f
            goto L54
        L4f:
            r0 = move-exception
            r7 = r0
            r6.addSuppressed(r7)
        L54:
            throw r6
        L55:
            r6 = 0
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseObject(byte[], java.lang.Class, com.alibaba.fastjson2.JSONReader$Feature[]):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0043, code lost:
    
        r1.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static <T> T parseObject(byte[] r6, java.lang.reflect.Type r7, com.alibaba.fastjson2.JSONReader.Feature... r8) {
        /*
            if (r6 == 0) goto L55
            int r0 = r6.length
            if (r0 != 0) goto L6
            goto L55
        L6:
            com.alibaba.fastjson2.JSONReader$Context r8 = com.alibaba.fastjson2.JSONFactory.createReadContext(r8)
            com.alibaba.fastjson2.reader.ObjectReader r0 = r8.getObjectReader(r7)
            com.alibaba.fastjson2.JSONReader r1 = com.alibaba.fastjson2.JSONReader.of(r6, r8)
            r3 = 0
            r4 = 0
            r2 = r7
            java.lang.Object r6 = r0.readObject(r1, r2, r3, r4)     // Catch: java.lang.Throwable -> L47
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r7 = r1.resolveTasks     // Catch: java.lang.Throwable -> L47
            if (r7 == 0) goto L21
            r1.handleResolveTasks(r6)     // Catch: java.lang.Throwable -> L47
        L21:
            char r7 = r1.ch     // Catch: java.lang.Throwable -> L47
            r0 = 26
            if (r7 == r0) goto L41
            long r7 = r8.features     // Catch: java.lang.Throwable -> L47
            com.alibaba.fastjson2.JSONReader$Feature r0 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L47
            long r2 = r0.mask     // Catch: java.lang.Throwable -> L47
            long r7 = r7 & r2
            r2 = 0
            int r7 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r7 == 0) goto L35
            goto L41
        L35:
            com.alibaba.fastjson2.JSONException r6 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L47
            java.lang.String r7 = "input not end"
            java.lang.String r7 = r1.info(r7)     // Catch: java.lang.Throwable -> L47
            r6.<init>(r7)     // Catch: java.lang.Throwable -> L47
            throw r6     // Catch: java.lang.Throwable -> L47
        L41:
            if (r1 == 0) goto L46
            r1.close()
        L46:
            return r6
        L47:
            r0 = move-exception
            r6 = r0
            if (r1 == 0) goto L54
            r1.close()     // Catch: java.lang.Throwable -> L4f
            goto L54
        L4f:
            r0 = move-exception
            r7 = r0
            r6.addSuppressed(r7)
        L54:
            throw r6
        L55:
            r6 = 0
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseObject(byte[], java.lang.reflect.Type, com.alibaba.fastjson2.JSONReader$Feature[]):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0043, code lost:
    
        r1.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static <T> T parseObject(char[] r6, java.lang.Class<T> r7, com.alibaba.fastjson2.JSONReader.Feature... r8) {
        /*
            if (r6 == 0) goto L55
            int r0 = r6.length
            if (r0 != 0) goto L6
            goto L55
        L6:
            com.alibaba.fastjson2.JSONReader$Context r8 = com.alibaba.fastjson2.JSONFactory.createReadContext(r8)
            com.alibaba.fastjson2.reader.ObjectReader r0 = r8.getObjectReader(r7)
            com.alibaba.fastjson2.JSONReader r1 = com.alibaba.fastjson2.JSONReader.of(r6, r8)
            r3 = 0
            r4 = 0
            r2 = r7
            java.lang.Object r6 = r0.readObject(r1, r2, r3, r4)     // Catch: java.lang.Throwable -> L47
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r7 = r1.resolveTasks     // Catch: java.lang.Throwable -> L47
            if (r7 == 0) goto L21
            r1.handleResolveTasks(r6)     // Catch: java.lang.Throwable -> L47
        L21:
            char r7 = r1.ch     // Catch: java.lang.Throwable -> L47
            r0 = 26
            if (r7 == r0) goto L41
            long r7 = r8.features     // Catch: java.lang.Throwable -> L47
            com.alibaba.fastjson2.JSONReader$Feature r0 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L47
            long r2 = r0.mask     // Catch: java.lang.Throwable -> L47
            long r7 = r7 & r2
            r2 = 0
            int r7 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r7 == 0) goto L35
            goto L41
        L35:
            com.alibaba.fastjson2.JSONException r6 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L47
            java.lang.String r7 = "input not end"
            java.lang.String r7 = r1.info(r7)     // Catch: java.lang.Throwable -> L47
            r6.<init>(r7)     // Catch: java.lang.Throwable -> L47
            throw r6     // Catch: java.lang.Throwable -> L47
        L41:
            if (r1 == 0) goto L46
            r1.close()
        L46:
            return r6
        L47:
            r0 = move-exception
            r6 = r0
            if (r1 == 0) goto L54
            r1.close()     // Catch: java.lang.Throwable -> L4f
            goto L54
        L4f:
            r0 = move-exception
            r7 = r0
            r6.addSuppressed(r7)
        L54:
            throw r6
        L55:
            r6 = 0
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseObject(char[], java.lang.Class, com.alibaba.fastjson2.JSONReader$Feature[]):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0043, code lost:
    
        r1.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static <T> T parseObject(char[] r6, java.lang.reflect.Type r7, com.alibaba.fastjson2.JSONReader.Feature... r8) {
        /*
            if (r6 == 0) goto L55
            int r0 = r6.length
            if (r0 != 0) goto L6
            goto L55
        L6:
            com.alibaba.fastjson2.JSONReader$Context r8 = com.alibaba.fastjson2.JSONFactory.createReadContext(r8)
            com.alibaba.fastjson2.reader.ObjectReader r0 = r8.getObjectReader(r7)
            com.alibaba.fastjson2.JSONReader r1 = com.alibaba.fastjson2.JSONReader.of(r6, r8)
            r3 = 0
            r4 = 0
            r2 = r7
            java.lang.Object r6 = r0.readObject(r1, r2, r3, r4)     // Catch: java.lang.Throwable -> L47
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r7 = r1.resolveTasks     // Catch: java.lang.Throwable -> L47
            if (r7 == 0) goto L21
            r1.handleResolveTasks(r6)     // Catch: java.lang.Throwable -> L47
        L21:
            char r7 = r1.ch     // Catch: java.lang.Throwable -> L47
            r0 = 26
            if (r7 == r0) goto L41
            long r7 = r8.features     // Catch: java.lang.Throwable -> L47
            com.alibaba.fastjson2.JSONReader$Feature r0 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L47
            long r2 = r0.mask     // Catch: java.lang.Throwable -> L47
            long r7 = r7 & r2
            r2 = 0
            int r7 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r7 == 0) goto L35
            goto L41
        L35:
            com.alibaba.fastjson2.JSONException r6 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L47
            java.lang.String r7 = "input not end"
            java.lang.String r7 = r1.info(r7)     // Catch: java.lang.Throwable -> L47
            r6.<init>(r7)     // Catch: java.lang.Throwable -> L47
            throw r6     // Catch: java.lang.Throwable -> L47
        L41:
            if (r1 == 0) goto L46
            r1.close()
        L46:
            return r6
        L47:
            r0 = move-exception
            r6 = r0
            if (r1 == 0) goto L54
            r1.close()     // Catch: java.lang.Throwable -> L4f
            goto L54
        L4f:
            r0 = move-exception
            r7 = r0
            r6.addSuppressed(r7)
        L54:
            throw r6
        L55:
            r6 = 0
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseObject(char[], java.lang.reflect.Type, com.alibaba.fastjson2.JSONReader$Feature[]):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0043, code lost:
    
        r1.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static <T> T parseObject(byte[] r6, java.lang.reflect.Type r7, com.alibaba.fastjson2.filter.Filter r8, com.alibaba.fastjson2.JSONReader.Feature... r9) {
        /*
            if (r6 == 0) goto L55
            int r0 = r6.length
            if (r0 != 0) goto L6
            goto L55
        L6:
            com.alibaba.fastjson2.JSONReader$Context r8 = com.alibaba.fastjson2.JSONFactory.createReadContext(r8, r9)
            com.alibaba.fastjson2.reader.ObjectReader r0 = r8.getObjectReader(r7)
            com.alibaba.fastjson2.JSONReader r1 = com.alibaba.fastjson2.JSONReader.of(r6, r8)
            r3 = 0
            r4 = 0
            r2 = r7
            java.lang.Object r6 = r0.readObject(r1, r2, r3, r4)     // Catch: java.lang.Throwable -> L47
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r7 = r1.resolveTasks     // Catch: java.lang.Throwable -> L47
            if (r7 == 0) goto L21
            r1.handleResolveTasks(r6)     // Catch: java.lang.Throwable -> L47
        L21:
            char r7 = r1.ch     // Catch: java.lang.Throwable -> L47
            r9 = 26
            if (r7 == r9) goto L41
            long r7 = r8.features     // Catch: java.lang.Throwable -> L47
            com.alibaba.fastjson2.JSONReader$Feature r9 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L47
            long r2 = r9.mask     // Catch: java.lang.Throwable -> L47
            long r7 = r7 & r2
            r2 = 0
            int r7 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r7 == 0) goto L35
            goto L41
        L35:
            com.alibaba.fastjson2.JSONException r6 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L47
            java.lang.String r7 = "input not end"
            java.lang.String r7 = r1.info(r7)     // Catch: java.lang.Throwable -> L47
            r6.<init>(r7)     // Catch: java.lang.Throwable -> L47
            throw r6     // Catch: java.lang.Throwable -> L47
        L41:
            if (r1 == 0) goto L46
            r1.close()
        L46:
            return r6
        L47:
            r0 = move-exception
            r6 = r0
            if (r1 == 0) goto L54
            r1.close()     // Catch: java.lang.Throwable -> L4f
            goto L54
        L4f:
            r0 = move-exception
            r7 = r0
            r6.addSuppressed(r7)
        L54:
            throw r6
        L55:
            r6 = 0
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseObject(byte[], java.lang.reflect.Type, com.alibaba.fastjson2.filter.Filter, com.alibaba.fastjson2.JSONReader$Feature[]):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x004e, code lost:
    
        r2.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static <T> T parseObject(byte[] r7, java.lang.reflect.Type r8, java.lang.String r9, com.alibaba.fastjson2.JSONReader.Feature... r10) {
        /*
            if (r7 == 0) goto L60
            int r0 = r7.length
            if (r0 != 0) goto L6
            goto L60
        L6:
            com.alibaba.fastjson2.JSONReader$Context r10 = com.alibaba.fastjson2.JSONFactory.createReadContext(r10)
            if (r9 == 0) goto L15
            boolean r0 = r9.isEmpty()
            if (r0 != 0) goto L15
            r10.setDateFormat(r9)
        L15:
            com.alibaba.fastjson2.reader.ObjectReader r1 = r10.getObjectReader(r8)
            com.alibaba.fastjson2.JSONReader r2 = com.alibaba.fastjson2.JSONReader.of(r7, r10)
            r4 = 0
            r5 = 0
            r3 = r8
            java.lang.Object r7 = r1.readObject(r2, r3, r4, r5)     // Catch: java.lang.Throwable -> L52
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r8 = r2.resolveTasks     // Catch: java.lang.Throwable -> L52
            if (r8 == 0) goto L2c
            r2.handleResolveTasks(r7)     // Catch: java.lang.Throwable -> L52
        L2c:
            char r8 = r2.ch     // Catch: java.lang.Throwable -> L52
            r9 = 26
            if (r8 == r9) goto L4c
            long r8 = r10.features     // Catch: java.lang.Throwable -> L52
            com.alibaba.fastjson2.JSONReader$Feature r10 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L52
            long r0 = r10.mask     // Catch: java.lang.Throwable -> L52
            long r8 = r8 & r0
            r0 = 0
            int r8 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            if (r8 == 0) goto L40
            goto L4c
        L40:
            com.alibaba.fastjson2.JSONException r7 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L52
            java.lang.String r8 = "input not end"
            java.lang.String r8 = r2.info(r8)     // Catch: java.lang.Throwable -> L52
            r7.<init>(r8)     // Catch: java.lang.Throwable -> L52
            throw r7     // Catch: java.lang.Throwable -> L52
        L4c:
            if (r2 == 0) goto L51
            r2.close()
        L51:
            return r7
        L52:
            r0 = move-exception
            r7 = r0
            if (r2 == 0) goto L5f
            r2.close()     // Catch: java.lang.Throwable -> L5a
            goto L5f
        L5a:
            r0 = move-exception
            r8 = r0
            r7.addSuppressed(r8)
        L5f:
            throw r7
        L60:
            r7 = 0
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseObject(byte[], java.lang.reflect.Type, java.lang.String, com.alibaba.fastjson2.JSONReader$Feature[]):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0041, code lost:
    
        r3.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static <T> T parseObject(java.nio.ByteBuffer r8, java.lang.Class<T> r9) {
        /*
            r0 = 0
            if (r8 != 0) goto L4
            return r0
        L4:
            com.alibaba.fastjson2.JSONReader$Context r1 = com.alibaba.fastjson2.JSONFactory.createReadContext()
            com.alibaba.fastjson2.reader.ObjectReader r2 = r1.getObjectReader(r9)
            com.alibaba.fastjson2.JSONReader r3 = com.alibaba.fastjson2.JSONReader.of(r8, r0, r1)
            r5 = 0
            r6 = 0
            r4 = r9
            java.lang.Object r8 = r2.readObject(r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L45
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r9 = r3.resolveTasks     // Catch: java.lang.Throwable -> L45
            if (r9 == 0) goto L1f
            r3.handleResolveTasks(r8)     // Catch: java.lang.Throwable -> L45
        L1f:
            char r9 = r3.ch     // Catch: java.lang.Throwable -> L45
            r0 = 26
            if (r9 == r0) goto L3f
            long r0 = r1.features     // Catch: java.lang.Throwable -> L45
            com.alibaba.fastjson2.JSONReader$Feature r9 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L45
            long r4 = r9.mask     // Catch: java.lang.Throwable -> L45
            long r0 = r0 & r4
            r4 = 0
            int r9 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r9 == 0) goto L33
            goto L3f
        L33:
            com.alibaba.fastjson2.JSONException r8 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L45
            java.lang.String r9 = "input not end"
            java.lang.String r9 = r3.info(r9)     // Catch: java.lang.Throwable -> L45
            r8.<init>(r9)     // Catch: java.lang.Throwable -> L45
            throw r8     // Catch: java.lang.Throwable -> L45
        L3f:
            if (r3 == 0) goto L44
            r3.close()
        L44:
            return r8
        L45:
            r0 = move-exception
            r8 = r0
            if (r3 == 0) goto L52
            r3.close()     // Catch: java.lang.Throwable -> L4d
            goto L52
        L4d:
            r0 = move-exception
            r9 = r0
            r8.addSuppressed(r9)
        L52:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseObject(java.nio.ByteBuffer, java.lang.Class):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x004d, code lost:
    
        r2.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static <T> T parseObject(java.io.Reader r7, java.lang.reflect.Type r8, com.alibaba.fastjson2.JSONReader.Feature... r9) {
        /*
            r0 = 0
            if (r7 != 0) goto L4
            return r0
        L4:
            com.alibaba.fastjson2.JSONReader$Context r9 = com.alibaba.fastjson2.JSONFactory.createReadContext(r9)
            com.alibaba.fastjson2.reader.ObjectReader r1 = r9.getObjectReader(r8)
            com.alibaba.fastjson2.JSONReader r2 = com.alibaba.fastjson2.JSONReader.of(r7, r9)
            boolean r7 = r2.isEnd()     // Catch: java.lang.Throwable -> L51
            if (r7 == 0) goto L1c
            if (r2 == 0) goto L1b
            r2.close()
        L1b:
            return r0
        L1c:
            r4 = 0
            r5 = 0
            r3 = r8
            java.lang.Object r7 = r1.readObject(r2, r3, r4, r5)     // Catch: java.lang.Throwable -> L51
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r8 = r2.resolveTasks     // Catch: java.lang.Throwable -> L51
            if (r8 == 0) goto L2b
            r2.handleResolveTasks(r7)     // Catch: java.lang.Throwable -> L51
        L2b:
            char r8 = r2.ch     // Catch: java.lang.Throwable -> L51
            r0 = 26
            if (r8 == r0) goto L4b
            long r8 = r9.features     // Catch: java.lang.Throwable -> L51
            com.alibaba.fastjson2.JSONReader$Feature r0 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L51
            long r0 = r0.mask     // Catch: java.lang.Throwable -> L51
            long r8 = r8 & r0
            r0 = 0
            int r8 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            if (r8 == 0) goto L3f
            goto L4b
        L3f:
            com.alibaba.fastjson2.JSONException r7 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L51
            java.lang.String r8 = "input not end"
            java.lang.String r8 = r2.info(r8)     // Catch: java.lang.Throwable -> L51
            r7.<init>(r8)     // Catch: java.lang.Throwable -> L51
            throw r7     // Catch: java.lang.Throwable -> L51
        L4b:
            if (r2 == 0) goto L50
            r2.close()
        L50:
            return r7
        L51:
            r0 = move-exception
            r7 = r0
            if (r2 == 0) goto L5e
            r2.close()     // Catch: java.lang.Throwable -> L59
            goto L5e
        L59:
            r0 = move-exception
            r8 = r0
            r7.addSuppressed(r8)
        L5e:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseObject(java.io.Reader, java.lang.reflect.Type, com.alibaba.fastjson2.JSONReader$Feature[]):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x0052, code lost:
    
        r3.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static <T> T parseObject(java.io.InputStream r8, java.lang.reflect.Type r9, com.alibaba.fastjson2.JSONReader.Feature... r10) {
        /*
            r0 = 0
            if (r8 != 0) goto L4
            return r0
        L4:
            com.alibaba.fastjson2.JSONReader$Context r1 = com.alibaba.fastjson2.JSONFactory.createReadContext()
            r1.config(r10)
            com.alibaba.fastjson2.reader.ObjectReader r2 = r1.getObjectReader(r9)
            java.nio.charset.Charset r10 = java.nio.charset.StandardCharsets.UTF_8
            com.alibaba.fastjson2.JSONReader r3 = com.alibaba.fastjson2.JSONReader.of(r8, r10, r1)
            boolean r8 = r3.isEnd()     // Catch: java.lang.Throwable -> L56
            if (r8 == 0) goto L21
            if (r3 == 0) goto L20
            r3.close()
        L20:
            return r0
        L21:
            r5 = 0
            r6 = 0
            r4 = r9
            java.lang.Object r8 = r2.readObject(r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L56
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r9 = r3.resolveTasks     // Catch: java.lang.Throwable -> L56
            if (r9 == 0) goto L30
            r3.handleResolveTasks(r8)     // Catch: java.lang.Throwable -> L56
        L30:
            char r9 = r3.ch     // Catch: java.lang.Throwable -> L56
            r10 = 26
            if (r9 == r10) goto L50
            long r9 = r1.features     // Catch: java.lang.Throwable -> L56
            com.alibaba.fastjson2.JSONReader$Feature r0 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L56
            long r0 = r0.mask     // Catch: java.lang.Throwable -> L56
            long r9 = r9 & r0
            r0 = 0
            int r9 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r9 == 0) goto L44
            goto L50
        L44:
            com.alibaba.fastjson2.JSONException r8 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L56
            java.lang.String r9 = "input not end"
            java.lang.String r9 = r3.info(r9)     // Catch: java.lang.Throwable -> L56
            r8.<init>(r9)     // Catch: java.lang.Throwable -> L56
            throw r8     // Catch: java.lang.Throwable -> L56
        L50:
            if (r3 == 0) goto L55
            r3.close()
        L55:
            return r8
        L56:
            r0 = move-exception
            r8 = r0
            if (r3 == 0) goto L63
            r3.close()     // Catch: java.lang.Throwable -> L5e
            goto L63
        L5e:
            r0 = move-exception
            r9 = r0
            r8.addSuppressed(r9)
        L63:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseObject(java.io.InputStream, java.lang.reflect.Type, com.alibaba.fastjson2.JSONReader$Feature[]):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x0059, code lost:
    
        r6.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static <T> T parseObject(java.io.InputStream r11, java.nio.charset.Charset r12, java.lang.reflect.Type r13, com.alibaba.fastjson2.JSONReader.Context r14) {
        /*
            r0 = 0
            if (r11 != 0) goto L4
            return r0
        L4:
            long r1 = r14.features
            com.alibaba.fastjson2.JSONReader$Feature r3 = com.alibaba.fastjson2.JSONReader.Feature.FieldBased
            long r3 = r3.mask
            long r1 = r1 & r3
            r3 = 0
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 == 0) goto L13
            r1 = 1
            goto L14
        L13:
            r1 = 0
        L14:
            com.alibaba.fastjson2.reader.ObjectReaderProvider r2 = r14.provider
            com.alibaba.fastjson2.reader.ObjectReader r5 = r2.getObjectReader(r13, r1)
            com.alibaba.fastjson2.JSONReader r6 = com.alibaba.fastjson2.JSONReader.of(r11, r12, r14)
            boolean r11 = r6.isEnd()     // Catch: java.lang.Throwable -> L5d
            if (r11 == 0) goto L2a
            if (r6 == 0) goto L29
            r6.close()
        L29:
            return r0
        L2a:
            r8 = 0
            r9 = 0
            r7 = r13
            java.lang.Object r11 = r5.readObject(r6, r7, r8, r9)     // Catch: java.lang.Throwable -> L5d
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r12 = r6.resolveTasks     // Catch: java.lang.Throwable -> L5d
            if (r12 == 0) goto L39
            r6.handleResolveTasks(r11)     // Catch: java.lang.Throwable -> L5d
        L39:
            char r12 = r6.ch     // Catch: java.lang.Throwable -> L5d
            r13 = 26
            if (r12 == r13) goto L57
            long r12 = r14.features     // Catch: java.lang.Throwable -> L5d
            com.alibaba.fastjson2.JSONReader$Feature r14 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L5d
            long r0 = r14.mask     // Catch: java.lang.Throwable -> L5d
            long r12 = r12 & r0
            int r12 = (r12 > r3 ? 1 : (r12 == r3 ? 0 : -1))
            if (r12 == 0) goto L4b
            goto L57
        L4b:
            com.alibaba.fastjson2.JSONException r11 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L5d
            java.lang.String r12 = "input not end"
            java.lang.String r12 = r6.info(r12)     // Catch: java.lang.Throwable -> L5d
            r11.<init>(r12)     // Catch: java.lang.Throwable -> L5d
            throw r11     // Catch: java.lang.Throwable -> L5d
        L57:
            if (r6 == 0) goto L5c
            r6.close()
        L5c:
            return r11
        L5d:
            r0 = move-exception
            r11 = r0
            if (r6 == 0) goto L6a
            r6.close()     // Catch: java.lang.Throwable -> L65
            goto L6a
        L65:
            r0 = move-exception
            r12 = r0
            r11.addSuppressed(r12)
        L6a:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseObject(java.io.InputStream, java.nio.charset.Charset, java.lang.reflect.Type, com.alibaba.fastjson2.JSONReader$Context):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x0059, code lost:
    
        r6.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static <T> T parseObject(java.io.InputStream r11, java.nio.charset.Charset r12, java.lang.Class<T> r13, com.alibaba.fastjson2.JSONReader.Context r14) {
        /*
            r0 = 0
            if (r11 != 0) goto L4
            return r0
        L4:
            long r1 = r14.features
            com.alibaba.fastjson2.JSONReader$Feature r3 = com.alibaba.fastjson2.JSONReader.Feature.FieldBased
            long r3 = r3.mask
            long r1 = r1 & r3
            r3 = 0
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 == 0) goto L13
            r1 = 1
            goto L14
        L13:
            r1 = 0
        L14:
            com.alibaba.fastjson2.reader.ObjectReaderProvider r2 = r14.provider
            com.alibaba.fastjson2.reader.ObjectReader r5 = r2.getObjectReader(r13, r1)
            com.alibaba.fastjson2.JSONReader r6 = com.alibaba.fastjson2.JSONReader.of(r11, r12, r14)
            boolean r11 = r6.isEnd()     // Catch: java.lang.Throwable -> L5d
            if (r11 == 0) goto L2a
            if (r6 == 0) goto L29
            r6.close()
        L29:
            return r0
        L2a:
            r8 = 0
            r9 = 0
            r7 = r13
            java.lang.Object r11 = r5.readObject(r6, r7, r8, r9)     // Catch: java.lang.Throwable -> L5d
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r12 = r6.resolveTasks     // Catch: java.lang.Throwable -> L5d
            if (r12 == 0) goto L39
            r6.handleResolveTasks(r11)     // Catch: java.lang.Throwable -> L5d
        L39:
            char r12 = r6.ch     // Catch: java.lang.Throwable -> L5d
            r13 = 26
            if (r12 == r13) goto L57
            long r12 = r14.features     // Catch: java.lang.Throwable -> L5d
            com.alibaba.fastjson2.JSONReader$Feature r14 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L5d
            long r0 = r14.mask     // Catch: java.lang.Throwable -> L5d
            long r12 = r12 & r0
            int r12 = (r12 > r3 ? 1 : (r12 == r3 ? 0 : -1))
            if (r12 == 0) goto L4b
            goto L57
        L4b:
            com.alibaba.fastjson2.JSONException r11 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L5d
            java.lang.String r12 = "input not end"
            java.lang.String r12 = r6.info(r12)     // Catch: java.lang.Throwable -> L5d
            r11.<init>(r12)     // Catch: java.lang.Throwable -> L5d
            throw r11     // Catch: java.lang.Throwable -> L5d
        L57:
            if (r6 == 0) goto L5c
            r6.close()
        L5c:
            return r11
        L5d:
            r0 = move-exception
            r11 = r0
            if (r6 == 0) goto L6a
            r6.close()     // Catch: java.lang.Throwable -> L65
            goto L6a
        L65:
            r0 = move-exception
            r12 = r0
            r11.addSuppressed(r12)
        L6a:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseObject(java.io.InputStream, java.nio.charset.Charset, java.lang.Class, com.alibaba.fastjson2.JSONReader$Context):java.lang.Object");
    }

    static <T> T parseObject(URL url, Type type, JSONReader.Feature... featureArr) {
        if (url == null) {
            return null;
        }
        try {
            InputStream openStream = url.openStream();
            try {
                T t = (T) parseObject(openStream, type, featureArr);
                if (openStream != null) {
                    openStream.close();
                }
                return t;
            } finally {
            }
        } catch (IOException e) {
            throw new JSONException("parseObject error", e);
        }
    }

    static <T> T parseObject(URL url, Class<T> cls, JSONReader.Feature... featureArr) {
        if (url == null) {
            return null;
        }
        try {
            InputStream openStream = url.openStream();
            try {
                T t = (T) parseObject(openStream, cls, featureArr);
                if (openStream != null) {
                    openStream.close();
                }
                return t;
            } finally {
            }
        } catch (IOException e) {
            throw new JSONException("JSON#parseObject cannot parse '" + url + "' to '" + cls + "'", e);
        }
    }

    static <T> T parseObject(URL url, Function<JSONObject, T> function, JSONReader.Feature... featureArr) {
        if (url == null) {
            return null;
        }
        try {
            InputStream openStream = url.openStream();
            try {
                JSONObject parseObject = parseObject(openStream, featureArr);
                if (parseObject == null) {
                    if (openStream != null) {
                        openStream.close();
                    }
                    return null;
                }
                T apply = function.apply(parseObject);
                if (openStream != null) {
                    openStream.close();
                }
                return apply;
            } finally {
            }
        } catch (IOException e) {
            throw new JSONException("JSON#parseObject cannot parse '" + url + "'", e);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x004e, code lost:
    
        r2.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static <T> T parseObject(java.io.InputStream r7, java.lang.reflect.Type r8, java.lang.String r9, com.alibaba.fastjson2.JSONReader.Feature... r10) {
        /*
            if (r7 != 0) goto L4
            r7 = 0
            return r7
        L4:
            com.alibaba.fastjson2.JSONReader$Context r10 = com.alibaba.fastjson2.JSONFactory.createReadContext(r10)
            if (r9 == 0) goto L13
            boolean r0 = r9.isEmpty()
            if (r0 != 0) goto L13
            r10.setDateFormat(r9)
        L13:
            com.alibaba.fastjson2.reader.ObjectReader r1 = r10.getObjectReader(r8)
            java.nio.charset.Charset r9 = java.nio.charset.StandardCharsets.UTF_8
            com.alibaba.fastjson2.JSONReader r2 = com.alibaba.fastjson2.JSONReader.of(r7, r9, r10)
            r4 = 0
            r5 = 0
            r3 = r8
            java.lang.Object r7 = r1.readObject(r2, r3, r4, r5)     // Catch: java.lang.Throwable -> L52
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r8 = r2.resolveTasks     // Catch: java.lang.Throwable -> L52
            if (r8 == 0) goto L2c
            r2.handleResolveTasks(r7)     // Catch: java.lang.Throwable -> L52
        L2c:
            char r8 = r2.ch     // Catch: java.lang.Throwable -> L52
            r9 = 26
            if (r8 == r9) goto L4c
            long r8 = r10.features     // Catch: java.lang.Throwable -> L52
            com.alibaba.fastjson2.JSONReader$Feature r10 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L52
            long r0 = r10.mask     // Catch: java.lang.Throwable -> L52
            long r8 = r8 & r0
            r0 = 0
            int r8 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            if (r8 == 0) goto L40
            goto L4c
        L40:
            com.alibaba.fastjson2.JSONException r7 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L52
            java.lang.String r8 = "input not end"
            java.lang.String r8 = r2.info(r8)     // Catch: java.lang.Throwable -> L52
            r7.<init>(r8)     // Catch: java.lang.Throwable -> L52
            throw r7     // Catch: java.lang.Throwable -> L52
        L4c:
            if (r2 == 0) goto L51
            r2.close()
        L51:
            return r7
        L52:
            r0 = move-exception
            r7 = r0
            if (r2 == 0) goto L5f
            r2.close()     // Catch: java.lang.Throwable -> L5a
            goto L5f
        L5a:
            r0 = move-exception
            r8 = r0
            r7.addSuppressed(r8)
        L5f:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseObject(java.io.InputStream, java.lang.reflect.Type, java.lang.String, com.alibaba.fastjson2.JSONReader$Feature[]):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0041, code lost:
    
        r1.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static <T> T parseObject(java.io.InputStream r6, java.nio.charset.Charset r7, java.lang.reflect.Type r8, com.alibaba.fastjson2.JSONReader.Feature... r9) {
        /*
            if (r6 != 0) goto L4
            r6 = 0
            return r6
        L4:
            com.alibaba.fastjson2.JSONReader$Context r9 = com.alibaba.fastjson2.JSONFactory.createReadContext(r9)
            com.alibaba.fastjson2.reader.ObjectReader r0 = r9.getObjectReader(r8)
            com.alibaba.fastjson2.JSONReader r1 = com.alibaba.fastjson2.JSONReader.of(r6, r7, r9)
            r3 = 0
            r4 = 0
            r2 = r8
            java.lang.Object r6 = r0.readObject(r1, r2, r3, r4)     // Catch: java.lang.Throwable -> L45
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r7 = r1.resolveTasks     // Catch: java.lang.Throwable -> L45
            if (r7 == 0) goto L1f
            r1.handleResolveTasks(r6)     // Catch: java.lang.Throwable -> L45
        L1f:
            char r7 = r1.ch     // Catch: java.lang.Throwable -> L45
            r8 = 26
            if (r7 == r8) goto L3f
            long r7 = r9.features     // Catch: java.lang.Throwable -> L45
            com.alibaba.fastjson2.JSONReader$Feature r9 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L45
            long r2 = r9.mask     // Catch: java.lang.Throwable -> L45
            long r7 = r7 & r2
            r2 = 0
            int r7 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r7 == 0) goto L33
            goto L3f
        L33:
            com.alibaba.fastjson2.JSONException r6 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L45
            java.lang.String r7 = "input not end"
            java.lang.String r7 = r1.info(r7)     // Catch: java.lang.Throwable -> L45
            r6.<init>(r7)     // Catch: java.lang.Throwable -> L45
            throw r6     // Catch: java.lang.Throwable -> L45
        L3f:
            if (r1 == 0) goto L44
            r1.close()
        L44:
            return r6
        L45:
            r0 = move-exception
            r6 = r0
            if (r1 == 0) goto L52
            r1.close()     // Catch: java.lang.Throwable -> L4d
            goto L52
        L4d:
            r0 = move-exception
            r7 = r0
            r6.addSuppressed(r7)
        L52:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseObject(java.io.InputStream, java.nio.charset.Charset, java.lang.reflect.Type, com.alibaba.fastjson2.JSONReader$Feature[]):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0045, code lost:
    
        r2.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static <T> T parseObject(byte[] r7, int r8, int r9, java.nio.charset.Charset r10, java.lang.reflect.Type r11) {
        /*
            if (r7 == 0) goto L57
            int r0 = r7.length
            if (r0 == 0) goto L57
            if (r9 != 0) goto L8
            goto L57
        L8:
            com.alibaba.fastjson2.JSONReader$Context r0 = com.alibaba.fastjson2.JSONFactory.createReadContext()
            com.alibaba.fastjson2.reader.ObjectReader r1 = r0.getObjectReader(r11)
            com.alibaba.fastjson2.JSONReader r2 = com.alibaba.fastjson2.JSONReader.of(r7, r8, r9, r10, r0)
            r4 = 0
            r5 = 0
            r3 = r11
            java.lang.Object r7 = r1.readObject(r2, r3, r4, r5)     // Catch: java.lang.Throwable -> L49
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r8 = r2.resolveTasks     // Catch: java.lang.Throwable -> L49
            if (r8 == 0) goto L23
            r2.handleResolveTasks(r7)     // Catch: java.lang.Throwable -> L49
        L23:
            char r8 = r2.ch     // Catch: java.lang.Throwable -> L49
            r9 = 26
            if (r8 == r9) goto L43
            long r8 = r0.features     // Catch: java.lang.Throwable -> L49
            com.alibaba.fastjson2.JSONReader$Feature r10 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L49
            long r10 = r10.mask     // Catch: java.lang.Throwable -> L49
            long r8 = r8 & r10
            r10 = 0
            int r8 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r8 == 0) goto L37
            goto L43
        L37:
            com.alibaba.fastjson2.JSONException r7 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L49
            java.lang.String r8 = "input not end"
            java.lang.String r8 = r2.info(r8)     // Catch: java.lang.Throwable -> L49
            r7.<init>(r8)     // Catch: java.lang.Throwable -> L49
            throw r7     // Catch: java.lang.Throwable -> L49
        L43:
            if (r2 == 0) goto L48
            r2.close()
        L48:
            return r7
        L49:
            r0 = move-exception
            r7 = r0
            if (r2 == 0) goto L56
            r2.close()     // Catch: java.lang.Throwable -> L51
            goto L56
        L51:
            r0 = move-exception
            r8 = r0
            r7.addSuppressed(r8)
        L56:
            throw r7
        L57:
            r7 = 0
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseObject(byte[], int, int, java.nio.charset.Charset, java.lang.reflect.Type):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0045, code lost:
    
        r2.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static <T> T parseObject(byte[] r7, int r8, int r9, java.nio.charset.Charset r10, java.lang.Class<T> r11) {
        /*
            if (r7 == 0) goto L57
            int r0 = r7.length
            if (r0 == 0) goto L57
            if (r9 != 0) goto L8
            goto L57
        L8:
            com.alibaba.fastjson2.JSONReader$Context r0 = com.alibaba.fastjson2.JSONFactory.createReadContext()
            com.alibaba.fastjson2.reader.ObjectReader r1 = r0.getObjectReader(r11)
            com.alibaba.fastjson2.JSONReader r2 = com.alibaba.fastjson2.JSONReader.of(r7, r8, r9, r10, r0)
            r4 = 0
            r5 = 0
            r3 = r11
            java.lang.Object r7 = r1.readObject(r2, r3, r4, r5)     // Catch: java.lang.Throwable -> L49
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r8 = r2.resolveTasks     // Catch: java.lang.Throwable -> L49
            if (r8 == 0) goto L23
            r2.handleResolveTasks(r7)     // Catch: java.lang.Throwable -> L49
        L23:
            char r8 = r2.ch     // Catch: java.lang.Throwable -> L49
            r9 = 26
            if (r8 == r9) goto L43
            long r8 = r0.features     // Catch: java.lang.Throwable -> L49
            com.alibaba.fastjson2.JSONReader$Feature r10 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L49
            long r10 = r10.mask     // Catch: java.lang.Throwable -> L49
            long r8 = r8 & r10
            r10 = 0
            int r8 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r8 == 0) goto L37
            goto L43
        L37:
            com.alibaba.fastjson2.JSONException r7 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L49
            java.lang.String r8 = "input not end"
            java.lang.String r8 = r2.info(r8)     // Catch: java.lang.Throwable -> L49
            r7.<init>(r8)     // Catch: java.lang.Throwable -> L49
            throw r7     // Catch: java.lang.Throwable -> L49
        L43:
            if (r2 == 0) goto L48
            r2.close()
        L48:
            return r7
        L49:
            r0 = move-exception
            r7 = r0
            if (r2 == 0) goto L56
            r2.close()     // Catch: java.lang.Throwable -> L51
            goto L56
        L51:
            r0 = move-exception
            r8 = r0
            r7.addSuppressed(r8)
        L56:
            throw r7
        L57:
            r7 = 0
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseObject(byte[], int, int, java.nio.charset.Charset, java.lang.Class):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0045, code lost:
    
        r1.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static <T> T parseObject(byte[] r6, int r7, int r8, java.nio.charset.Charset r9, java.lang.Class<T> r10, com.alibaba.fastjson2.JSONReader.Feature... r11) {
        /*
            if (r6 == 0) goto L57
            int r0 = r6.length
            if (r0 == 0) goto L57
            if (r8 != 0) goto L8
            goto L57
        L8:
            com.alibaba.fastjson2.JSONReader$Context r11 = com.alibaba.fastjson2.JSONFactory.createReadContext(r11)
            com.alibaba.fastjson2.reader.ObjectReader r0 = r11.getObjectReader(r10)
            com.alibaba.fastjson2.JSONReader r1 = com.alibaba.fastjson2.JSONReader.of(r6, r7, r8, r9, r11)
            r3 = 0
            r4 = 0
            r2 = r10
            java.lang.Object r6 = r0.readObject(r1, r2, r3, r4)     // Catch: java.lang.Throwable -> L49
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r7 = r1.resolveTasks     // Catch: java.lang.Throwable -> L49
            if (r7 == 0) goto L23
            r1.handleResolveTasks(r6)     // Catch: java.lang.Throwable -> L49
        L23:
            char r7 = r1.ch     // Catch: java.lang.Throwable -> L49
            r8 = 26
            if (r7 == r8) goto L43
            long r7 = r11.features     // Catch: java.lang.Throwable -> L49
            com.alibaba.fastjson2.JSONReader$Feature r9 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L49
            long r9 = r9.mask     // Catch: java.lang.Throwable -> L49
            long r7 = r7 & r9
            r9 = 0
            int r7 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r7 == 0) goto L37
            goto L43
        L37:
            com.alibaba.fastjson2.JSONException r6 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L49
            java.lang.String r7 = "input not end"
            java.lang.String r7 = r1.info(r7)     // Catch: java.lang.Throwable -> L49
            r6.<init>(r7)     // Catch: java.lang.Throwable -> L49
            throw r6     // Catch: java.lang.Throwable -> L49
        L43:
            if (r1 == 0) goto L48
            r1.close()
        L48:
            return r6
        L49:
            r0 = move-exception
            r6 = r0
            if (r1 == 0) goto L56
            r1.close()     // Catch: java.lang.Throwable -> L51
            goto L56
        L51:
            r0 = move-exception
            r7 = r0
            r6.addSuppressed(r7)
        L56:
            throw r6
        L57:
            r6 = 0
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseObject(byte[], int, int, java.nio.charset.Charset, java.lang.Class, com.alibaba.fastjson2.JSONReader$Feature[]):java.lang.Object");
    }

    static <T> void parseObject(InputStream inputStream, Type type, Consumer<T> consumer, JSONReader.Feature... featureArr) {
        parseObject(inputStream, StandardCharsets.UTF_8, '\n', type, (Consumer) consumer, featureArr);
    }

    /* JADX WARN: Multi-variable type inference failed */
    static <T> void parseObject(InputStream inputStream, Charset charset, char c, Type type, Consumer<T> consumer, JSONReader.Feature... featureArr) {
        int i;
        JSONReader.Context context;
        JSONFactory.CacheItem cacheItem = JSONFactory.CACHE_ITEMS[System.identityHashCode(Thread.currentThread()) & (JSONFactory.CACHE_ITEMS.length - 1)];
        ObjectReader objectReader = null;
        byte[] andSet = JSONFactory.BYTES_UPDATER.getAndSet(cacheItem, null);
        int i2 = 524288;
        if (andSet == null) {
            andSet = new byte[524288];
        }
        JSONReader.Context createReadContext = JSONFactory.createReadContext(featureArr);
        byte[] bArr = andSet;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            try {
                try {
                    int read = inputStream.read(bArr, i3, bArr.length - i3);
                    if (read == -1) {
                        return;
                    }
                    int i5 = i3 + read;
                    int i6 = i3;
                    boolean z = false;
                    while (i6 < i5) {
                        if (bArr[i6] == c) {
                            JSONReader of = JSONReader.of(bArr, i4, i6 - i4, charset, createReadContext);
                            if (objectReader == null) {
                                objectReader = createReadContext.getObjectReader(type);
                            }
                            ObjectReader objectReader2 = objectReader;
                            Object readObject = objectReader2.readObject(of, type, null, 0L);
                            if (of.resolveTasks != null) {
                                of.handleResolveTasks(readObject);
                            }
                            if (of.ch != 26) {
                                i = i2;
                                context = createReadContext;
                                if ((createReadContext.features & JSONReader.Feature.IgnoreCheckClose.mask) == 0) {
                                    throw new JSONException(of.info("input not end"));
                                }
                            } else {
                                i = i2;
                                context = createReadContext;
                            }
                            consumer.accept(readObject);
                            i4 = i6 + 1;
                            objectReader = objectReader2;
                            z = true;
                        } else {
                            i = i2;
                            context = createReadContext;
                        }
                        i6++;
                        createReadContext = context;
                        i2 = i;
                    }
                    int i7 = i2;
                    JSONReader.Context context2 = createReadContext;
                    if (i5 == bArr.length) {
                        if (z) {
                            i3 = bArr.length - i4;
                            System.arraycopy(bArr, i4, bArr, 0, i3);
                            i4 = 0;
                            createReadContext = context2;
                            i2 = i7;
                        } else {
                            bArr = Arrays.copyOf(bArr, bArr.length + i7);
                        }
                    }
                    i3 = i5;
                    createReadContext = context2;
                    i2 = i7;
                } catch (IOException e) {
                    throw new JSONException("JSON#parseObject cannot parse the 'InputStream' to '" + type + "'", e);
                }
            } finally {
                JSONFactory.BYTES_UPDATER.lazySet(cacheItem, bArr);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    static <T> void parseObject(Reader reader, char c, Type type, Consumer<T> consumer) {
        JSONFactory.CacheItem cacheItem = JSONFactory.CACHE_ITEMS[System.identityHashCode(Thread.currentThread()) & (JSONFactory.CACHE_ITEMS.length - 1)];
        ObjectReader objectReader = null;
        char[] andSet = JSONFactory.CHARS_UPDATER.getAndSet(cacheItem, null);
        if (andSet == null) {
            andSet = new char[8192];
        }
        JSONReader.Context createReadContext = JSONFactory.createReadContext();
        char[] cArr = andSet;
        int i = 0;
        int i2 = 0;
        while (true) {
            try {
                try {
                    int read = reader.read(cArr, i, cArr.length - i);
                    if (read == -1) {
                        return;
                    }
                    int i3 = i + read;
                    boolean z = false;
                    for (int i4 = i; i4 < i3; i4++) {
                        if (cArr[i4] == c) {
                            JSONReader of = JSONReader.of(cArr, i2, i4 - i2, createReadContext);
                            if (objectReader == null) {
                                objectReader = createReadContext.getObjectReader(type);
                            }
                            ObjectReader objectReader2 = objectReader;
                            consumer.accept(objectReader2.readObject(of, type, null, 0L));
                            i2 = i4 + 1;
                            objectReader = objectReader2;
                            z = true;
                        }
                    }
                    if (i3 == cArr.length) {
                        if (z) {
                            i = cArr.length - i2;
                            System.arraycopy(cArr, i2, cArr, 0, i);
                            i2 = 0;
                        } else {
                            cArr = Arrays.copyOf(cArr, cArr.length + 8192);
                        }
                    }
                    i = i3;
                } catch (IOException e) {
                    throw new JSONException("JSON#parseObject cannot parse the 'Reader' to '" + type + "'", e);
                }
            } finally {
                JSONFactory.CHARS_UPDATER.lazySet(cacheItem, cArr);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x004f, code lost:
    
        r5.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static com.alibaba.fastjson2.JSONArray parseArray(java.lang.String r5) {
        /*
            r0 = 0
            if (r5 == 0) goto L5f
            boolean r1 = r5.isEmpty()
            if (r1 == 0) goto La
            goto L5f
        La:
            com.alibaba.fastjson2.JSONReader$Context r1 = com.alibaba.fastjson2.JSONFactory.createReadContext()
            com.alibaba.fastjson2.JSONReader r5 = com.alibaba.fastjson2.JSONReader.of(r5, r1)
            boolean r2 = r5.nextIfNull()     // Catch: java.lang.Throwable -> L53
            if (r2 == 0) goto L1e
            if (r5 == 0) goto L1d
            r5.close()
        L1d:
            return r0
        L1e:
            com.alibaba.fastjson2.JSONArray r0 = new com.alibaba.fastjson2.JSONArray     // Catch: java.lang.Throwable -> L53
            r0.<init>()     // Catch: java.lang.Throwable -> L53
            r5.read(r0)     // Catch: java.lang.Throwable -> L53
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r2 = r5.resolveTasks     // Catch: java.lang.Throwable -> L53
            if (r2 == 0) goto L2d
            r5.handleResolveTasks(r0)     // Catch: java.lang.Throwable -> L53
        L2d:
            char r2 = r5.ch     // Catch: java.lang.Throwable -> L53
            r3 = 26
            if (r2 == r3) goto L4d
            long r1 = r1.features     // Catch: java.lang.Throwable -> L53
            com.alibaba.fastjson2.JSONReader$Feature r3 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L53
            long r3 = r3.mask     // Catch: java.lang.Throwable -> L53
            long r1 = r1 & r3
            r3 = 0
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 == 0) goto L41
            goto L4d
        L41:
            com.alibaba.fastjson2.JSONException r0 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L53
            java.lang.String r1 = "input not end"
            java.lang.String r1 = r5.info(r1)     // Catch: java.lang.Throwable -> L53
            r0.<init>(r1)     // Catch: java.lang.Throwable -> L53
            throw r0     // Catch: java.lang.Throwable -> L53
        L4d:
            if (r5 == 0) goto L52
            r5.close()
        L52:
            return r0
        L53:
            r0 = move-exception
            if (r5 == 0) goto L5e
            r5.close()     // Catch: java.lang.Throwable -> L5a
            goto L5e
        L5a:
            r5 = move-exception
            r0.addSuppressed(r5)
        L5e:
            throw r0
        L5f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseArray(java.lang.String):com.alibaba.fastjson2.JSONArray");
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x004c, code lost:
    
        r5.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static com.alibaba.fastjson2.JSONArray parseArray(byte[] r5) {
        /*
            r0 = 0
            if (r5 == 0) goto L5c
            int r1 = r5.length
            if (r1 != 0) goto L7
            goto L5c
        L7:
            com.alibaba.fastjson2.JSONReader$Context r1 = com.alibaba.fastjson2.JSONFactory.createReadContext()
            com.alibaba.fastjson2.JSONReader r5 = com.alibaba.fastjson2.JSONReader.of(r5, r1)
            boolean r2 = r5.nextIfNull()     // Catch: java.lang.Throwable -> L50
            if (r2 == 0) goto L1b
            if (r5 == 0) goto L1a
            r5.close()
        L1a:
            return r0
        L1b:
            com.alibaba.fastjson2.JSONArray r0 = new com.alibaba.fastjson2.JSONArray     // Catch: java.lang.Throwable -> L50
            r0.<init>()     // Catch: java.lang.Throwable -> L50
            r5.read(r0)     // Catch: java.lang.Throwable -> L50
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r2 = r5.resolveTasks     // Catch: java.lang.Throwable -> L50
            if (r2 == 0) goto L2a
            r5.handleResolveTasks(r0)     // Catch: java.lang.Throwable -> L50
        L2a:
            char r2 = r5.ch     // Catch: java.lang.Throwable -> L50
            r3 = 26
            if (r2 == r3) goto L4a
            long r1 = r1.features     // Catch: java.lang.Throwable -> L50
            com.alibaba.fastjson2.JSONReader$Feature r3 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L50
            long r3 = r3.mask     // Catch: java.lang.Throwable -> L50
            long r1 = r1 & r3
            r3 = 0
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 == 0) goto L3e
            goto L4a
        L3e:
            com.alibaba.fastjson2.JSONException r0 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L50
            java.lang.String r1 = "input not end"
            java.lang.String r1 = r5.info(r1)     // Catch: java.lang.Throwable -> L50
            r0.<init>(r1)     // Catch: java.lang.Throwable -> L50
            throw r0     // Catch: java.lang.Throwable -> L50
        L4a:
            if (r5 == 0) goto L4f
            r5.close()
        L4f:
            return r0
        L50:
            r0 = move-exception
            if (r5 == 0) goto L5b
            r5.close()     // Catch: java.lang.Throwable -> L57
            goto L5b
        L57:
            r5 = move-exception
            r0.addSuppressed(r5)
        L5b:
            throw r0
        L5c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseArray(byte[]):com.alibaba.fastjson2.JSONArray");
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x004e, code lost:
    
        r2.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static com.alibaba.fastjson2.JSONArray parseArray(byte[] r2, int r3, int r4, java.nio.charset.Charset r5) {
        /*
            r0 = 0
            if (r2 == 0) goto L5e
            int r1 = r2.length
            if (r1 == 0) goto L5e
            if (r4 != 0) goto L9
            goto L5e
        L9:
            com.alibaba.fastjson2.JSONReader$Context r1 = com.alibaba.fastjson2.JSONFactory.createReadContext()
            com.alibaba.fastjson2.JSONReader r2 = com.alibaba.fastjson2.JSONReader.of(r2, r3, r4, r5, r1)
            boolean r3 = r2.nextIfNull()     // Catch: java.lang.Throwable -> L52
            if (r3 == 0) goto L1d
            if (r2 == 0) goto L1c
            r2.close()
        L1c:
            return r0
        L1d:
            com.alibaba.fastjson2.JSONArray r3 = new com.alibaba.fastjson2.JSONArray     // Catch: java.lang.Throwable -> L52
            r3.<init>()     // Catch: java.lang.Throwable -> L52
            r2.read(r3)     // Catch: java.lang.Throwable -> L52
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r4 = r2.resolveTasks     // Catch: java.lang.Throwable -> L52
            if (r4 == 0) goto L2c
            r2.handleResolveTasks(r3)     // Catch: java.lang.Throwable -> L52
        L2c:
            char r4 = r2.ch     // Catch: java.lang.Throwable -> L52
            r5 = 26
            if (r4 == r5) goto L4c
            long r4 = r1.features     // Catch: java.lang.Throwable -> L52
            com.alibaba.fastjson2.JSONReader$Feature r0 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L52
            long r0 = r0.mask     // Catch: java.lang.Throwable -> L52
            long r4 = r4 & r0
            r0 = 0
            int r4 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r4 == 0) goto L40
            goto L4c
        L40:
            com.alibaba.fastjson2.JSONException r3 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L52
            java.lang.String r4 = "input not end"
            java.lang.String r4 = r2.info(r4)     // Catch: java.lang.Throwable -> L52
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L52
            throw r3     // Catch: java.lang.Throwable -> L52
        L4c:
            if (r2 == 0) goto L51
            r2.close()
        L51:
            return r3
        L52:
            r3 = move-exception
            if (r2 == 0) goto L5d
            r2.close()     // Catch: java.lang.Throwable -> L59
            goto L5d
        L59:
            r2 = move-exception
            r3.addSuppressed(r2)
        L5d:
            throw r3
        L5e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseArray(byte[], int, int, java.nio.charset.Charset):com.alibaba.fastjson2.JSONArray");
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x004c, code lost:
    
        r5.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static com.alibaba.fastjson2.JSONArray parseArray(char[] r5) {
        /*
            r0 = 0
            if (r5 == 0) goto L5c
            int r1 = r5.length
            if (r1 != 0) goto L7
            goto L5c
        L7:
            com.alibaba.fastjson2.JSONReader$Context r1 = com.alibaba.fastjson2.JSONFactory.createReadContext()
            com.alibaba.fastjson2.JSONReader r5 = com.alibaba.fastjson2.JSONReader.of(r5, r1)
            boolean r2 = r5.nextIfNull()     // Catch: java.lang.Throwable -> L50
            if (r2 == 0) goto L1b
            if (r5 == 0) goto L1a
            r5.close()
        L1a:
            return r0
        L1b:
            com.alibaba.fastjson2.JSONArray r0 = new com.alibaba.fastjson2.JSONArray     // Catch: java.lang.Throwable -> L50
            r0.<init>()     // Catch: java.lang.Throwable -> L50
            r5.read(r0)     // Catch: java.lang.Throwable -> L50
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r2 = r5.resolveTasks     // Catch: java.lang.Throwable -> L50
            if (r2 == 0) goto L2a
            r5.handleResolveTasks(r0)     // Catch: java.lang.Throwable -> L50
        L2a:
            char r2 = r5.ch     // Catch: java.lang.Throwable -> L50
            r3 = 26
            if (r2 == r3) goto L4a
            long r1 = r1.features     // Catch: java.lang.Throwable -> L50
            com.alibaba.fastjson2.JSONReader$Feature r3 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L50
            long r3 = r3.mask     // Catch: java.lang.Throwable -> L50
            long r1 = r1 & r3
            r3 = 0
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 == 0) goto L3e
            goto L4a
        L3e:
            com.alibaba.fastjson2.JSONException r0 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L50
            java.lang.String r1 = "input not end"
            java.lang.String r1 = r5.info(r1)     // Catch: java.lang.Throwable -> L50
            r0.<init>(r1)     // Catch: java.lang.Throwable -> L50
            throw r0     // Catch: java.lang.Throwable -> L50
        L4a:
            if (r5 == 0) goto L4f
            r5.close()
        L4f:
            return r0
        L50:
            r0 = move-exception
            if (r5 == 0) goto L5b
            r5.close()     // Catch: java.lang.Throwable -> L57
            goto L5b
        L57:
            r5 = move-exception
            r0.addSuppressed(r5)
        L5b:
            throw r0
        L5c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseArray(char[]):com.alibaba.fastjson2.JSONArray");
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x004f, code lost:
    
        r5.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static com.alibaba.fastjson2.JSONArray parseArray(java.lang.String r5, com.alibaba.fastjson2.JSONReader.Feature... r6) {
        /*
            r0 = 0
            if (r5 == 0) goto L5f
            boolean r1 = r5.isEmpty()
            if (r1 == 0) goto La
            goto L5f
        La:
            com.alibaba.fastjson2.JSONReader$Context r6 = com.alibaba.fastjson2.JSONFactory.createReadContext(r6)
            com.alibaba.fastjson2.JSONReader r5 = com.alibaba.fastjson2.JSONReader.of(r5, r6)
            boolean r1 = r5.nextIfNull()     // Catch: java.lang.Throwable -> L53
            if (r1 == 0) goto L1e
            if (r5 == 0) goto L1d
            r5.close()
        L1d:
            return r0
        L1e:
            com.alibaba.fastjson2.JSONArray r0 = new com.alibaba.fastjson2.JSONArray     // Catch: java.lang.Throwable -> L53
            r0.<init>()     // Catch: java.lang.Throwable -> L53
            r5.read(r0)     // Catch: java.lang.Throwable -> L53
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r1 = r5.resolveTasks     // Catch: java.lang.Throwable -> L53
            if (r1 == 0) goto L2d
            r5.handleResolveTasks(r0)     // Catch: java.lang.Throwable -> L53
        L2d:
            char r1 = r5.ch     // Catch: java.lang.Throwable -> L53
            r2 = 26
            if (r1 == r2) goto L4d
            long r1 = r6.features     // Catch: java.lang.Throwable -> L53
            com.alibaba.fastjson2.JSONReader$Feature r6 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L53
            long r3 = r6.mask     // Catch: java.lang.Throwable -> L53
            long r1 = r1 & r3
            r3 = 0
            int r6 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r6 == 0) goto L41
            goto L4d
        L41:
            com.alibaba.fastjson2.JSONException r6 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L53
            java.lang.String r0 = "input not end"
            java.lang.String r0 = r5.info(r0)     // Catch: java.lang.Throwable -> L53
            r6.<init>(r0)     // Catch: java.lang.Throwable -> L53
            throw r6     // Catch: java.lang.Throwable -> L53
        L4d:
            if (r5 == 0) goto L52
            r5.close()
        L52:
            return r0
        L53:
            r6 = move-exception
            if (r5 == 0) goto L5e
            r5.close()     // Catch: java.lang.Throwable -> L5a
            goto L5e
        L5a:
            r5 = move-exception
            r6.addSuppressed(r5)
        L5e:
            throw r6
        L5f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseArray(java.lang.String, com.alibaba.fastjson2.JSONReader$Feature[]):com.alibaba.fastjson2.JSONArray");
    }

    static JSONArray parseArray(URL url, JSONReader.Feature... featureArr) {
        if (url == null) {
            return null;
        }
        try {
            InputStream openStream = url.openStream();
            try {
                JSONArray parseArray = parseArray(openStream, featureArr);
                if (openStream != null) {
                    openStream.close();
                }
                return parseArray;
            } finally {
            }
        } catch (IOException e) {
            throw new JSONException("JSON#parseArray cannot parse '" + url + "' to '" + JSONArray.class + "'", e);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0049, code lost:
    
        r5.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static com.alibaba.fastjson2.JSONArray parseArray(java.io.Reader r5, com.alibaba.fastjson2.JSONReader.Feature... r6) {
        /*
            r0 = 0
            if (r5 != 0) goto L4
            return r0
        L4:
            com.alibaba.fastjson2.JSONReader$Context r6 = com.alibaba.fastjson2.JSONFactory.createReadContext(r6)
            com.alibaba.fastjson2.JSONReader r5 = com.alibaba.fastjson2.JSONReader.of(r5, r6)
            boolean r1 = r5.nextIfNull()     // Catch: java.lang.Throwable -> L4d
            if (r1 == 0) goto L18
            if (r5 == 0) goto L17
            r5.close()
        L17:
            return r0
        L18:
            com.alibaba.fastjson2.JSONArray r0 = new com.alibaba.fastjson2.JSONArray     // Catch: java.lang.Throwable -> L4d
            r0.<init>()     // Catch: java.lang.Throwable -> L4d
            r5.read(r0)     // Catch: java.lang.Throwable -> L4d
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r1 = r5.resolveTasks     // Catch: java.lang.Throwable -> L4d
            if (r1 == 0) goto L27
            r5.handleResolveTasks(r0)     // Catch: java.lang.Throwable -> L4d
        L27:
            char r1 = r5.ch     // Catch: java.lang.Throwable -> L4d
            r2 = 26
            if (r1 == r2) goto L47
            long r1 = r6.features     // Catch: java.lang.Throwable -> L4d
            com.alibaba.fastjson2.JSONReader$Feature r6 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L4d
            long r3 = r6.mask     // Catch: java.lang.Throwable -> L4d
            long r1 = r1 & r3
            r3 = 0
            int r6 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r6 == 0) goto L3b
            goto L47
        L3b:
            com.alibaba.fastjson2.JSONException r6 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L4d
            java.lang.String r0 = "input not end"
            java.lang.String r0 = r5.info(r0)     // Catch: java.lang.Throwable -> L4d
            r6.<init>(r0)     // Catch: java.lang.Throwable -> L4d
            throw r6     // Catch: java.lang.Throwable -> L4d
        L47:
            if (r5 == 0) goto L4c
            r5.close()
        L4c:
            return r0
        L4d:
            r6 = move-exception
            if (r5 == 0) goto L58
            r5.close()     // Catch: java.lang.Throwable -> L54
            goto L58
        L54:
            r5 = move-exception
            r6.addSuppressed(r5)
        L58:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseArray(java.io.Reader, com.alibaba.fastjson2.JSONReader$Feature[]):com.alibaba.fastjson2.JSONArray");
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x004b, code lost:
    
        r5.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static com.alibaba.fastjson2.JSONArray parseArray(java.io.InputStream r5, com.alibaba.fastjson2.JSONReader.Feature... r6) {
        /*
            r0 = 0
            if (r5 != 0) goto L4
            return r0
        L4:
            com.alibaba.fastjson2.JSONReader$Context r6 = com.alibaba.fastjson2.JSONFactory.createReadContext(r6)
            java.nio.charset.Charset r1 = java.nio.charset.StandardCharsets.UTF_8
            com.alibaba.fastjson2.JSONReader r5 = com.alibaba.fastjson2.JSONReader.of(r5, r1, r6)
            boolean r1 = r5.nextIfNull()     // Catch: java.lang.Throwable -> L4f
            if (r1 == 0) goto L1a
            if (r5 == 0) goto L19
            r5.close()
        L19:
            return r0
        L1a:
            com.alibaba.fastjson2.JSONArray r0 = new com.alibaba.fastjson2.JSONArray     // Catch: java.lang.Throwable -> L4f
            r0.<init>()     // Catch: java.lang.Throwable -> L4f
            r5.read(r0)     // Catch: java.lang.Throwable -> L4f
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r1 = r5.resolveTasks     // Catch: java.lang.Throwable -> L4f
            if (r1 == 0) goto L29
            r5.handleResolveTasks(r0)     // Catch: java.lang.Throwable -> L4f
        L29:
            char r1 = r5.ch     // Catch: java.lang.Throwable -> L4f
            r2 = 26
            if (r1 == r2) goto L49
            long r1 = r6.features     // Catch: java.lang.Throwable -> L4f
            com.alibaba.fastjson2.JSONReader$Feature r6 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L4f
            long r3 = r6.mask     // Catch: java.lang.Throwable -> L4f
            long r1 = r1 & r3
            r3 = 0
            int r6 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r6 == 0) goto L3d
            goto L49
        L3d:
            com.alibaba.fastjson2.JSONException r6 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L4f
            java.lang.String r0 = "input not end"
            java.lang.String r0 = r5.info(r0)     // Catch: java.lang.Throwable -> L4f
            r6.<init>(r0)     // Catch: java.lang.Throwable -> L4f
            throw r6     // Catch: java.lang.Throwable -> L4f
        L49:
            if (r5 == 0) goto L4e
            r5.close()
        L4e:
            return r0
        L4f:
            r6 = move-exception
            if (r5 == 0) goto L5a
            r5.close()     // Catch: java.lang.Throwable -> L56
            goto L5a
        L56:
            r5 = move-exception
            r6.addSuppressed(r5)
        L5a:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseArray(java.io.InputStream, com.alibaba.fastjson2.JSONReader$Feature[]):com.alibaba.fastjson2.JSONArray");
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0045, code lost:
    
        r4.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static com.alibaba.fastjson2.JSONArray parseArray(java.io.InputStream r4, java.nio.charset.Charset r5, com.alibaba.fastjson2.JSONReader.Context r6) {
        /*
            r0 = 0
            if (r4 != 0) goto L4
            return r0
        L4:
            com.alibaba.fastjson2.JSONReader r4 = com.alibaba.fastjson2.JSONReader.of(r4, r5, r6)
            boolean r5 = r4.nextIfNull()     // Catch: java.lang.Throwable -> L49
            if (r5 == 0) goto L14
            if (r4 == 0) goto L13
            r4.close()
        L13:
            return r0
        L14:
            com.alibaba.fastjson2.JSONArray r5 = new com.alibaba.fastjson2.JSONArray     // Catch: java.lang.Throwable -> L49
            r5.<init>()     // Catch: java.lang.Throwable -> L49
            r4.read(r5)     // Catch: java.lang.Throwable -> L49
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r0 = r4.resolveTasks     // Catch: java.lang.Throwable -> L49
            if (r0 == 0) goto L23
            r4.handleResolveTasks(r5)     // Catch: java.lang.Throwable -> L49
        L23:
            char r0 = r4.ch     // Catch: java.lang.Throwable -> L49
            r1 = 26
            if (r0 == r1) goto L43
            long r0 = r6.features     // Catch: java.lang.Throwable -> L49
            com.alibaba.fastjson2.JSONReader$Feature r6 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L49
            long r2 = r6.mask     // Catch: java.lang.Throwable -> L49
            long r0 = r0 & r2
            r2 = 0
            int r6 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r6 == 0) goto L37
            goto L43
        L37:
            com.alibaba.fastjson2.JSONException r5 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L49
            java.lang.String r6 = "input not end"
            java.lang.String r6 = r4.info(r6)     // Catch: java.lang.Throwable -> L49
            r5.<init>(r6)     // Catch: java.lang.Throwable -> L49
            throw r5     // Catch: java.lang.Throwable -> L49
        L43:
            if (r4 == 0) goto L48
            r4.close()
        L48:
            return r5
        L49:
            r5 = move-exception
            if (r4 == 0) goto L54
            r4.close()     // Catch: java.lang.Throwable -> L50
            goto L54
        L50:
            r4 = move-exception
            r5.addSuppressed(r4)
        L54:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseArray(java.io.InputStream, java.nio.charset.Charset, com.alibaba.fastjson2.JSONReader$Context):com.alibaba.fastjson2.JSONArray");
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x003e, code lost:
    
        r4.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static <T> java.util.List<T> parseArray(java.lang.String r4, java.lang.reflect.Type r5, com.alibaba.fastjson2.JSONReader.Feature... r6) {
        /*
            if (r4 == 0) goto L4e
            boolean r0 = r4.isEmpty()
            if (r0 == 0) goto L9
            goto L4e
        L9:
            com.alibaba.fastjson2.JSONReader$Context r6 = com.alibaba.fastjson2.JSONFactory.createReadContext(r6)
            com.alibaba.fastjson2.JSONReader r4 = com.alibaba.fastjson2.JSONReader.of(r4, r6)
            java.util.List r5 = r4.readArray(r5)     // Catch: java.lang.Throwable -> L42
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r0 = r4.resolveTasks     // Catch: java.lang.Throwable -> L42
            if (r0 == 0) goto L1c
            r4.handleResolveTasks(r5)     // Catch: java.lang.Throwable -> L42
        L1c:
            char r0 = r4.ch     // Catch: java.lang.Throwable -> L42
            r1 = 26
            if (r0 == r1) goto L3c
            long r0 = r6.features     // Catch: java.lang.Throwable -> L42
            com.alibaba.fastjson2.JSONReader$Feature r6 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L42
            long r2 = r6.mask     // Catch: java.lang.Throwable -> L42
            long r0 = r0 & r2
            r2 = 0
            int r6 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r6 == 0) goto L30
            goto L3c
        L30:
            com.alibaba.fastjson2.JSONException r5 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L42
            java.lang.String r6 = "input not end"
            java.lang.String r6 = r4.info(r6)     // Catch: java.lang.Throwable -> L42
            r5.<init>(r6)     // Catch: java.lang.Throwable -> L42
            throw r5     // Catch: java.lang.Throwable -> L42
        L3c:
            if (r4 == 0) goto L41
            r4.close()
        L41:
            return r5
        L42:
            r5 = move-exception
            if (r4 == 0) goto L4d
            r4.close()     // Catch: java.lang.Throwable -> L49
            goto L4d
        L49:
            r4 = move-exception
            r5.addSuppressed(r4)
        L4d:
            throw r5
        L4e:
            r4 = 0
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseArray(java.lang.String, java.lang.reflect.Type, com.alibaba.fastjson2.JSONReader$Feature[]):java.util.List");
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x003e, code lost:
    
        r4.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static <T> java.util.List<T> parseArray(java.lang.String r4, java.lang.reflect.Type r5) {
        /*
            if (r4 == 0) goto L4e
            boolean r0 = r4.isEmpty()
            if (r0 == 0) goto L9
            goto L4e
        L9:
            com.alibaba.fastjson2.JSONReader$Context r0 = com.alibaba.fastjson2.JSONFactory.createReadContext()
            com.alibaba.fastjson2.JSONReader r4 = com.alibaba.fastjson2.JSONReader.of(r4, r0)
            java.util.List r5 = r4.readArray(r5)     // Catch: java.lang.Throwable -> L42
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r1 = r4.resolveTasks     // Catch: java.lang.Throwable -> L42
            if (r1 == 0) goto L1c
            r4.handleResolveTasks(r5)     // Catch: java.lang.Throwable -> L42
        L1c:
            char r1 = r4.ch     // Catch: java.lang.Throwable -> L42
            r2 = 26
            if (r1 == r2) goto L3c
            long r0 = r0.features     // Catch: java.lang.Throwable -> L42
            com.alibaba.fastjson2.JSONReader$Feature r2 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L42
            long r2 = r2.mask     // Catch: java.lang.Throwable -> L42
            long r0 = r0 & r2
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 == 0) goto L30
            goto L3c
        L30:
            com.alibaba.fastjson2.JSONException r5 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L42
            java.lang.String r0 = "input not end"
            java.lang.String r0 = r4.info(r0)     // Catch: java.lang.Throwable -> L42
            r5.<init>(r0)     // Catch: java.lang.Throwable -> L42
            throw r5     // Catch: java.lang.Throwable -> L42
        L3c:
            if (r4 == 0) goto L41
            r4.close()
        L41:
            return r5
        L42:
            r5 = move-exception
            if (r4 == 0) goto L4d
            r4.close()     // Catch: java.lang.Throwable -> L49
            goto L4d
        L49:
            r4 = move-exception
            r5.addSuppressed(r4)
        L4d:
            throw r5
        L4e:
            r4 = 0
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseArray(java.lang.String, java.lang.reflect.Type):java.util.List");
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x003e, code lost:
    
        r4.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static <T> java.util.List<T> parseArray(java.lang.String r4, java.lang.Class<T> r5) {
        /*
            if (r4 == 0) goto L4e
            boolean r0 = r4.isEmpty()
            if (r0 == 0) goto L9
            goto L4e
        L9:
            com.alibaba.fastjson2.JSONReader$Context r0 = com.alibaba.fastjson2.JSONFactory.createReadContext()
            com.alibaba.fastjson2.JSONReader r4 = com.alibaba.fastjson2.JSONReader.of(r4, r0)
            java.util.List r5 = r4.readArray(r5)     // Catch: java.lang.Throwable -> L42
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r1 = r4.resolveTasks     // Catch: java.lang.Throwable -> L42
            if (r1 == 0) goto L1c
            r4.handleResolveTasks(r5)     // Catch: java.lang.Throwable -> L42
        L1c:
            char r1 = r4.ch     // Catch: java.lang.Throwable -> L42
            r2 = 26
            if (r1 == r2) goto L3c
            long r0 = r0.features     // Catch: java.lang.Throwable -> L42
            com.alibaba.fastjson2.JSONReader$Feature r2 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L42
            long r2 = r2.mask     // Catch: java.lang.Throwable -> L42
            long r0 = r0 & r2
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 == 0) goto L30
            goto L3c
        L30:
            com.alibaba.fastjson2.JSONException r5 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L42
            java.lang.String r0 = "input not end"
            java.lang.String r0 = r4.info(r0)     // Catch: java.lang.Throwable -> L42
            r5.<init>(r0)     // Catch: java.lang.Throwable -> L42
            throw r5     // Catch: java.lang.Throwable -> L42
        L3c:
            if (r4 == 0) goto L41
            r4.close()
        L41:
            return r5
        L42:
            r5 = move-exception
            if (r4 == 0) goto L4d
            r4.close()     // Catch: java.lang.Throwable -> L49
            goto L4d
        L49:
            r4 = move-exception
            r5.addSuppressed(r4)
        L4d:
            throw r5
        L4e:
            r4 = 0
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseArray(java.lang.String, java.lang.Class):java.util.List");
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x003e, code lost:
    
        r4.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static <T> java.util.List<T> parseArray(java.lang.String r4, java.lang.reflect.Type... r5) {
        /*
            if (r4 == 0) goto L4e
            boolean r0 = r4.isEmpty()
            if (r0 == 0) goto L9
            goto L4e
        L9:
            com.alibaba.fastjson2.JSONReader$Context r0 = com.alibaba.fastjson2.JSONFactory.createReadContext()
            com.alibaba.fastjson2.JSONReader r4 = com.alibaba.fastjson2.JSONReader.of(r4, r0)
            java.util.List r5 = r4.readList(r5)     // Catch: java.lang.Throwable -> L42
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r1 = r4.resolveTasks     // Catch: java.lang.Throwable -> L42
            if (r1 == 0) goto L1c
            r4.handleResolveTasks(r5)     // Catch: java.lang.Throwable -> L42
        L1c:
            char r1 = r4.ch     // Catch: java.lang.Throwable -> L42
            r2 = 26
            if (r1 == r2) goto L3c
            long r0 = r0.features     // Catch: java.lang.Throwable -> L42
            com.alibaba.fastjson2.JSONReader$Feature r2 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L42
            long r2 = r2.mask     // Catch: java.lang.Throwable -> L42
            long r0 = r0 & r2
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 == 0) goto L30
            goto L3c
        L30:
            com.alibaba.fastjson2.JSONException r5 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L42
            java.lang.String r0 = "input not end"
            java.lang.String r0 = r4.info(r0)     // Catch: java.lang.Throwable -> L42
            r5.<init>(r0)     // Catch: java.lang.Throwable -> L42
            throw r5     // Catch: java.lang.Throwable -> L42
        L3c:
            if (r4 == 0) goto L41
            r4.close()
        L41:
            return r5
        L42:
            r5 = move-exception
            if (r4 == 0) goto L4d
            r4.close()     // Catch: java.lang.Throwable -> L49
            goto L4d
        L49:
            r4 = move-exception
            r5.addSuppressed(r4)
        L4d:
            throw r5
        L4e:
            r4 = 0
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseArray(java.lang.String, java.lang.reflect.Type[]):java.util.List");
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x003e, code lost:
    
        r4.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static <T> java.util.List<T> parseArray(java.lang.String r4, java.lang.Class<T> r5, com.alibaba.fastjson2.JSONReader.Feature... r6) {
        /*
            if (r4 == 0) goto L4e
            boolean r0 = r4.isEmpty()
            if (r0 == 0) goto L9
            goto L4e
        L9:
            com.alibaba.fastjson2.JSONReader$Context r6 = com.alibaba.fastjson2.JSONFactory.createReadContext(r6)
            com.alibaba.fastjson2.JSONReader r4 = com.alibaba.fastjson2.JSONReader.of(r4, r6)
            java.util.List r5 = r4.readArray(r5)     // Catch: java.lang.Throwable -> L42
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r0 = r4.resolveTasks     // Catch: java.lang.Throwable -> L42
            if (r0 == 0) goto L1c
            r4.handleResolveTasks(r5)     // Catch: java.lang.Throwable -> L42
        L1c:
            char r0 = r4.ch     // Catch: java.lang.Throwable -> L42
            r1 = 26
            if (r0 == r1) goto L3c
            long r0 = r6.features     // Catch: java.lang.Throwable -> L42
            com.alibaba.fastjson2.JSONReader$Feature r6 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L42
            long r2 = r6.mask     // Catch: java.lang.Throwable -> L42
            long r0 = r0 & r2
            r2 = 0
            int r6 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r6 == 0) goto L30
            goto L3c
        L30:
            com.alibaba.fastjson2.JSONException r5 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L42
            java.lang.String r6 = "input not end"
            java.lang.String r6 = r4.info(r6)     // Catch: java.lang.Throwable -> L42
            r5.<init>(r6)     // Catch: java.lang.Throwable -> L42
            throw r5     // Catch: java.lang.Throwable -> L42
        L3c:
            if (r4 == 0) goto L41
            r4.close()
        L41:
            return r5
        L42:
            r5 = move-exception
            if (r4 == 0) goto L4d
            r4.close()     // Catch: java.lang.Throwable -> L49
            goto L4d
        L49:
            r4 = move-exception
            r5.addSuppressed(r4)
        L4d:
            throw r5
        L4e:
            r4 = 0
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseArray(java.lang.String, java.lang.Class, com.alibaba.fastjson2.JSONReader$Feature[]):java.util.List");
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x003b, code lost:
    
        r4.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static <T> java.util.List<T> parseArray(char[] r4, java.lang.Class<T> r5, com.alibaba.fastjson2.JSONReader.Feature... r6) {
        /*
            if (r4 == 0) goto L4b
            int r0 = r4.length
            if (r0 != 0) goto L6
            goto L4b
        L6:
            com.alibaba.fastjson2.JSONReader$Context r6 = com.alibaba.fastjson2.JSONFactory.createReadContext(r6)
            com.alibaba.fastjson2.JSONReader r4 = com.alibaba.fastjson2.JSONReader.of(r4, r6)
            java.util.List r5 = r4.readArray(r5)     // Catch: java.lang.Throwable -> L3f
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r0 = r4.resolveTasks     // Catch: java.lang.Throwable -> L3f
            if (r0 == 0) goto L19
            r4.handleResolveTasks(r5)     // Catch: java.lang.Throwable -> L3f
        L19:
            char r0 = r4.ch     // Catch: java.lang.Throwable -> L3f
            r1 = 26
            if (r0 == r1) goto L39
            long r0 = r6.features     // Catch: java.lang.Throwable -> L3f
            com.alibaba.fastjson2.JSONReader$Feature r6 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L3f
            long r2 = r6.mask     // Catch: java.lang.Throwable -> L3f
            long r0 = r0 & r2
            r2 = 0
            int r6 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r6 == 0) goto L2d
            goto L39
        L2d:
            com.alibaba.fastjson2.JSONException r5 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L3f
            java.lang.String r6 = "input not end"
            java.lang.String r6 = r4.info(r6)     // Catch: java.lang.Throwable -> L3f
            r5.<init>(r6)     // Catch: java.lang.Throwable -> L3f
            throw r5     // Catch: java.lang.Throwable -> L3f
        L39:
            if (r4 == 0) goto L3e
            r4.close()
        L3e:
            return r5
        L3f:
            r5 = move-exception
            if (r4 == 0) goto L4a
            r4.close()     // Catch: java.lang.Throwable -> L46
            goto L4a
        L46:
            r4 = move-exception
            r5.addSuppressed(r4)
        L4a:
            throw r5
        L4b:
            r4 = 0
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseArray(char[], java.lang.Class, com.alibaba.fastjson2.JSONReader$Feature[]):java.util.List");
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x0063, code lost:
    
        r3.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static <T> java.util.List<T> parseArray(java.lang.String r3, java.lang.reflect.Type[] r4, com.alibaba.fastjson2.JSONReader.Feature... r5) {
        /*
            r0 = 0
            if (r3 == 0) goto L73
            boolean r1 = r3.isEmpty()
            if (r1 == 0) goto La
            goto L73
        La:
            com.alibaba.fastjson2.JSONReader$Context r5 = com.alibaba.fastjson2.JSONFactory.createReadContext(r5)
            com.alibaba.fastjson2.JSONReader r3 = com.alibaba.fastjson2.JSONReader.of(r3, r5)
            boolean r1 = r3.nextIfNull()     // Catch: java.lang.Throwable -> L67
            if (r1 == 0) goto L1e
            if (r3 == 0) goto L1d
            r3.close()
        L1d:
            return r0
        L1e:
            r3.startArray()     // Catch: java.lang.Throwable -> L67
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch: java.lang.Throwable -> L67
            int r1 = r4.length     // Catch: java.lang.Throwable -> L67
            r0.<init>(r1)     // Catch: java.lang.Throwable -> L67
            r1 = 0
        L28:
            int r2 = r4.length     // Catch: java.lang.Throwable -> L67
            if (r1 >= r2) goto L37
            r2 = r4[r1]     // Catch: java.lang.Throwable -> L67
            java.lang.Object r2 = r3.read(r2)     // Catch: java.lang.Throwable -> L67
            r0.add(r2)     // Catch: java.lang.Throwable -> L67
            int r1 = r1 + 1
            goto L28
        L37:
            r3.endArray()     // Catch: java.lang.Throwable -> L67
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r4 = r3.resolveTasks     // Catch: java.lang.Throwable -> L67
            if (r4 == 0) goto L41
            r3.handleResolveTasks(r0)     // Catch: java.lang.Throwable -> L67
        L41:
            char r4 = r3.ch     // Catch: java.lang.Throwable -> L67
            r1 = 26
            if (r4 == r1) goto L61
            long r4 = r5.features     // Catch: java.lang.Throwable -> L67
            com.alibaba.fastjson2.JSONReader$Feature r1 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L67
            long r1 = r1.mask     // Catch: java.lang.Throwable -> L67
            long r4 = r4 & r1
            r1 = 0
            int r4 = (r4 > r1 ? 1 : (r4 == r1 ? 0 : -1))
            if (r4 == 0) goto L55
            goto L61
        L55:
            com.alibaba.fastjson2.JSONException r4 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L67
            java.lang.String r5 = "input not end"
            java.lang.String r5 = r3.info(r5)     // Catch: java.lang.Throwable -> L67
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L67
            throw r4     // Catch: java.lang.Throwable -> L67
        L61:
            if (r3 == 0) goto L66
            r3.close()
        L66:
            return r0
        L67:
            r4 = move-exception
            if (r3 == 0) goto L72
            r3.close()     // Catch: java.lang.Throwable -> L6e
            goto L72
        L6e:
            r3 = move-exception
            r4.addSuppressed(r3)
        L72:
            throw r4
        L73:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseArray(java.lang.String, java.lang.reflect.Type[], com.alibaba.fastjson2.JSONReader$Feature[]):java.util.List");
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0039, code lost:
    
        r4.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static <T> java.util.List<T> parseArray(java.io.Reader r4, java.lang.reflect.Type r5, com.alibaba.fastjson2.JSONReader.Feature... r6) {
        /*
            if (r4 != 0) goto L4
            r4 = 0
            return r4
        L4:
            com.alibaba.fastjson2.JSONReader$Context r6 = com.alibaba.fastjson2.JSONFactory.createReadContext(r6)
            com.alibaba.fastjson2.JSONReader r4 = com.alibaba.fastjson2.JSONReader.of(r4, r6)
            java.util.List r5 = r4.readArray(r5)     // Catch: java.lang.Throwable -> L3d
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r0 = r4.resolveTasks     // Catch: java.lang.Throwable -> L3d
            if (r0 == 0) goto L17
            r4.handleResolveTasks(r5)     // Catch: java.lang.Throwable -> L3d
        L17:
            char r0 = r4.ch     // Catch: java.lang.Throwable -> L3d
            r1 = 26
            if (r0 == r1) goto L37
            long r0 = r6.features     // Catch: java.lang.Throwable -> L3d
            com.alibaba.fastjson2.JSONReader$Feature r6 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L3d
            long r2 = r6.mask     // Catch: java.lang.Throwable -> L3d
            long r0 = r0 & r2
            r2 = 0
            int r6 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r6 == 0) goto L2b
            goto L37
        L2b:
            com.alibaba.fastjson2.JSONException r5 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L3d
            java.lang.String r6 = "input not end"
            java.lang.String r6 = r4.info(r6)     // Catch: java.lang.Throwable -> L3d
            r5.<init>(r6)     // Catch: java.lang.Throwable -> L3d
            throw r5     // Catch: java.lang.Throwable -> L3d
        L37:
            if (r4 == 0) goto L3c
            r4.close()
        L3c:
            return r5
        L3d:
            r5 = move-exception
            if (r4 == 0) goto L48
            r4.close()     // Catch: java.lang.Throwable -> L44
            goto L48
        L44:
            r4 = move-exception
            r5.addSuppressed(r4)
        L48:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseArray(java.io.Reader, java.lang.reflect.Type, com.alibaba.fastjson2.JSONReader$Feature[]):java.util.List");
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x003b, code lost:
    
        r4.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static <T> java.util.List<T> parseArray(byte[] r4, java.lang.reflect.Type r5, com.alibaba.fastjson2.JSONReader.Feature... r6) {
        /*
            if (r4 == 0) goto L4b
            int r0 = r4.length
            if (r0 != 0) goto L6
            goto L4b
        L6:
            com.alibaba.fastjson2.JSONReader$Context r6 = com.alibaba.fastjson2.JSONFactory.createReadContext(r6)
            com.alibaba.fastjson2.JSONReader r4 = com.alibaba.fastjson2.JSONReader.of(r4, r6)
            java.util.List r5 = r4.readArray(r5)     // Catch: java.lang.Throwable -> L3f
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r0 = r4.resolveTasks     // Catch: java.lang.Throwable -> L3f
            if (r0 == 0) goto L19
            r4.handleResolveTasks(r5)     // Catch: java.lang.Throwable -> L3f
        L19:
            char r0 = r4.ch     // Catch: java.lang.Throwable -> L3f
            r1 = 26
            if (r0 == r1) goto L39
            long r0 = r6.features     // Catch: java.lang.Throwable -> L3f
            com.alibaba.fastjson2.JSONReader$Feature r6 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L3f
            long r2 = r6.mask     // Catch: java.lang.Throwable -> L3f
            long r0 = r0 & r2
            r2 = 0
            int r6 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r6 == 0) goto L2d
            goto L39
        L2d:
            com.alibaba.fastjson2.JSONException r5 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L3f
            java.lang.String r6 = "input not end"
            java.lang.String r6 = r4.info(r6)     // Catch: java.lang.Throwable -> L3f
            r5.<init>(r6)     // Catch: java.lang.Throwable -> L3f
            throw r5     // Catch: java.lang.Throwable -> L3f
        L39:
            if (r4 == 0) goto L3e
            r4.close()
        L3e:
            return r5
        L3f:
            r5 = move-exception
            if (r4 == 0) goto L4a
            r4.close()     // Catch: java.lang.Throwable -> L46
            goto L4a
        L46:
            r4 = move-exception
            r5.addSuppressed(r4)
        L4a:
            throw r5
        L4b:
            r4 = 0
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseArray(byte[], java.lang.reflect.Type, com.alibaba.fastjson2.JSONReader$Feature[]):java.util.List");
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x003b, code lost:
    
        r4.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static <T> java.util.List<T> parseArray(byte[] r4, java.lang.Class<T> r5, com.alibaba.fastjson2.JSONReader.Feature... r6) {
        /*
            if (r4 == 0) goto L4b
            int r0 = r4.length
            if (r0 != 0) goto L6
            goto L4b
        L6:
            com.alibaba.fastjson2.JSONReader$Context r6 = com.alibaba.fastjson2.JSONFactory.createReadContext(r6)
            com.alibaba.fastjson2.JSONReader r4 = com.alibaba.fastjson2.JSONReader.of(r4, r6)
            java.util.List r5 = r4.readArray(r5)     // Catch: java.lang.Throwable -> L3f
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r0 = r4.resolveTasks     // Catch: java.lang.Throwable -> L3f
            if (r0 == 0) goto L19
            r4.handleResolveTasks(r5)     // Catch: java.lang.Throwable -> L3f
        L19:
            char r0 = r4.ch     // Catch: java.lang.Throwable -> L3f
            r1 = 26
            if (r0 == r1) goto L39
            long r0 = r6.features     // Catch: java.lang.Throwable -> L3f
            com.alibaba.fastjson2.JSONReader$Feature r6 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L3f
            long r2 = r6.mask     // Catch: java.lang.Throwable -> L3f
            long r0 = r0 & r2
            r2 = 0
            int r6 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r6 == 0) goto L2d
            goto L39
        L2d:
            com.alibaba.fastjson2.JSONException r5 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L3f
            java.lang.String r6 = "input not end"
            java.lang.String r6 = r4.info(r6)     // Catch: java.lang.Throwable -> L3f
            r5.<init>(r6)     // Catch: java.lang.Throwable -> L3f
            throw r5     // Catch: java.lang.Throwable -> L3f
        L39:
            if (r4 == 0) goto L3e
            r4.close()
        L3e:
            return r5
        L3f:
            r5 = move-exception
            if (r4 == 0) goto L4a
            r4.close()     // Catch: java.lang.Throwable -> L46
            goto L4a
        L46:
            r4 = move-exception
            r5.addSuppressed(r4)
        L4a:
            throw r5
        L4b:
            r4 = 0
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseArray(byte[], java.lang.Class, com.alibaba.fastjson2.JSONReader$Feature[]):java.util.List");
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x003d, code lost:
    
        r1.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static <T> java.util.List<T> parseArray(byte[] r1, int r2, int r3, java.nio.charset.Charset r4, java.lang.Class<T> r5, com.alibaba.fastjson2.JSONReader.Feature... r6) {
        /*
            if (r1 == 0) goto L4d
            int r0 = r1.length
            if (r0 == 0) goto L4d
            if (r3 != 0) goto L8
            goto L4d
        L8:
            com.alibaba.fastjson2.JSONReader$Context r6 = com.alibaba.fastjson2.JSONFactory.createReadContext(r6)
            com.alibaba.fastjson2.JSONReader r1 = com.alibaba.fastjson2.JSONReader.of(r1, r2, r3, r4, r6)
            java.util.List r2 = r1.readArray(r5)     // Catch: java.lang.Throwable -> L41
            java.util.List<com.alibaba.fastjson2.JSONReader$ResolveTask> r3 = r1.resolveTasks     // Catch: java.lang.Throwable -> L41
            if (r3 == 0) goto L1b
            r1.handleResolveTasks(r2)     // Catch: java.lang.Throwable -> L41
        L1b:
            char r3 = r1.ch     // Catch: java.lang.Throwable -> L41
            r4 = 26
            if (r3 == r4) goto L3b
            long r3 = r6.features     // Catch: java.lang.Throwable -> L41
            com.alibaba.fastjson2.JSONReader$Feature r5 = com.alibaba.fastjson2.JSONReader.Feature.IgnoreCheckClose     // Catch: java.lang.Throwable -> L41
            long r5 = r5.mask     // Catch: java.lang.Throwable -> L41
            long r3 = r3 & r5
            r5 = 0
            int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r3 == 0) goto L2f
            goto L3b
        L2f:
            com.alibaba.fastjson2.JSONException r2 = new com.alibaba.fastjson2.JSONException     // Catch: java.lang.Throwable -> L41
            java.lang.String r3 = "input not end"
            java.lang.String r3 = r1.info(r3)     // Catch: java.lang.Throwable -> L41
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L41
            throw r2     // Catch: java.lang.Throwable -> L41
        L3b:
            if (r1 == 0) goto L40
            r1.close()
        L40:
            return r2
        L41:
            r2 = move-exception
            if (r1 == 0) goto L4c
            r1.close()     // Catch: java.lang.Throwable -> L48
            goto L4c
        L48:
            r1 = move-exception
            r2.addSuppressed(r1)
        L4c:
            throw r2
        L4d:
            r1 = 0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.parseArray(byte[], int, int, java.nio.charset.Charset, java.lang.Class, com.alibaba.fastjson2.JSONReader$Feature[]):java.util.List");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x004f A[Catch: NumberFormatException -> 0x0063, NullPointerException -> 0x0065, TRY_ENTER, TRY_LEAVE, TryCatch #6 {NullPointerException -> 0x0065, NumberFormatException -> 0x0063, blocks: (B:10:0x004f, B:23:0x0062, B:22:0x005f, B:18:0x005a), top: B:4:0x000b, inners: #6 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static java.lang.String toJSONString(java.lang.Object r11) {
        /*
            com.alibaba.fastjson2.writer.ObjectWriterProvider r0 = com.alibaba.fastjson2.JSONFactory.defaultObjectWriterProvider
            com.alibaba.fastjson2.JSONWriter$Context r1 = new com.alibaba.fastjson2.JSONWriter$Context
            r1.<init>(r0)
            com.alibaba.fastjson2.JSONWriter r3 = com.alibaba.fastjson2.JSONWriter.of(r1)     // Catch: java.lang.NumberFormatException -> L67 java.lang.NullPointerException -> L69
            if (r11 != 0) goto L12
            r3.writeNull()     // Catch: java.lang.Throwable -> L55
        L10:
            r4 = r11
            goto L49
        L12:
            r3.rootObject = r11     // Catch: java.lang.Throwable -> L55
            com.alibaba.fastjson2.JSONWriter$Path r2 = com.alibaba.fastjson2.JSONWriter.Path.ROOT     // Catch: java.lang.Throwable -> L55
            r3.path = r2     // Catch: java.lang.Throwable -> L55
            java.lang.Class r2 = r11.getClass()     // Catch: java.lang.Throwable -> L55
            java.lang.Class<com.alibaba.fastjson2.JSONObject> r4 = com.alibaba.fastjson2.JSONObject.class
            r5 = 0
            if (r2 != r4) goto L2f
            long r7 = r1.features     // Catch: java.lang.Throwable -> L55
            int r1 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r1 != 0) goto L2f
            r0 = r11
            com.alibaba.fastjson2.JSONObject r0 = (com.alibaba.fastjson2.JSONObject) r0     // Catch: java.lang.Throwable -> L55
            r3.write(r0)     // Catch: java.lang.Throwable -> L55
            goto L10
        L2f:
            long r7 = com.alibaba.fastjson2.JSONFactory.defaultWriterFeatures     // Catch: java.lang.Throwable -> L55
            com.alibaba.fastjson2.JSONWriter$Feature r1 = com.alibaba.fastjson2.JSONWriter.Feature.FieldBased     // Catch: java.lang.Throwable -> L55
            long r9 = r1.mask     // Catch: java.lang.Throwable -> L55
            long r7 = r7 & r9
            int r1 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r1 == 0) goto L3c
            r1 = 1
            goto L3d
        L3c:
            r1 = 0
        L3d:
            com.alibaba.fastjson2.writer.ObjectWriter r2 = r0.getObjectWriter(r2, r2, r1)     // Catch: java.lang.Throwable -> L55
            r6 = 0
            r7 = 0
            r5 = 0
            r4 = r11
            r2.write(r3, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L53
        L49:
            java.lang.String r11 = r3.toString()     // Catch: java.lang.Throwable -> L53
            if (r3 == 0) goto L52
            r3.close()     // Catch: java.lang.NumberFormatException -> L63 java.lang.NullPointerException -> L65
        L52:
            return r11
        L53:
            r0 = move-exception
            goto L57
        L55:
            r0 = move-exception
            r4 = r11
        L57:
            r11 = r0
            if (r3 == 0) goto L62
            r3.close()     // Catch: java.lang.Throwable -> L5e
            goto L62
        L5e:
            r0 = move-exception
            r11.addSuppressed(r0)     // Catch: java.lang.NumberFormatException -> L63 java.lang.NullPointerException -> L65
        L62:
            throw r11     // Catch: java.lang.NumberFormatException -> L63 java.lang.NullPointerException -> L65
        L63:
            r0 = move-exception
            goto L6b
        L65:
            r0 = move-exception
            goto L6b
        L67:
            r0 = move-exception
            goto L6a
        L69:
            r0 = move-exception
        L6a:
            r4 = r11
        L6b:
            r11 = r0
            com.alibaba.fastjson2.JSONException r0 = new com.alibaba.fastjson2.JSONException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "JSON#toJSONString cannot serialize '"
            r1.<init>(r2)
            java.lang.StringBuilder r1 = r1.append(r4)
            java.lang.String r2 = "'"
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1, r11)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.toJSONString(java.lang.Object):java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x003c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:27:? A[Catch: NumberFormatException -> 0x0046, NullPointerException -> 0x0048, SYNTHETIC, TRY_LEAVE, TryCatch #8 {NullPointerException -> 0x0048, NumberFormatException -> 0x0046, blocks: (B:12:0x0031, B:26:0x0045, B:25:0x0042, B:20:0x003c), top: B:5:0x000a, inners: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static java.lang.String toJSONString(java.lang.Object r7, com.alibaba.fastjson2.JSONWriter.Context r8) {
        /*
            if (r8 != 0) goto L6
            com.alibaba.fastjson2.JSONWriter$Context r8 = com.alibaba.fastjson2.JSONFactory.createWriteContext()
        L6:
            com.alibaba.fastjson2.JSONWriter r1 = com.alibaba.fastjson2.JSONWriter.of(r8)     // Catch: java.lang.NumberFormatException -> L4a java.lang.NullPointerException -> L4c
            if (r7 != 0) goto L15
            r1.writeNull()     // Catch: java.lang.Throwable -> L11
            r2 = r7
            goto L2b
        L11:
            r0 = move-exception
            r8 = r0
            r2 = r7
            goto L3a
        L15:
            r1.rootObject = r7     // Catch: java.lang.Throwable -> L37
            com.alibaba.fastjson2.JSONWriter$Path r0 = com.alibaba.fastjson2.JSONWriter.Path.ROOT     // Catch: java.lang.Throwable -> L37
            r1.path = r0     // Catch: java.lang.Throwable -> L37
            java.lang.Class r0 = r7.getClass()     // Catch: java.lang.Throwable -> L37
            com.alibaba.fastjson2.writer.ObjectWriter r0 = r8.getObjectWriter(r0, r0)     // Catch: java.lang.Throwable -> L37
            r4 = 0
            r5 = 0
            r3 = 0
            r2 = r7
            r0.write(r1, r2, r3, r4, r5)     // Catch: java.lang.Throwable -> L35
        L2b:
            java.lang.String r7 = r1.toString()     // Catch: java.lang.Throwable -> L35
            if (r1 == 0) goto L34
            r1.close()     // Catch: java.lang.NumberFormatException -> L46 java.lang.NullPointerException -> L48
        L34:
            return r7
        L35:
            r0 = move-exception
            goto L39
        L37:
            r0 = move-exception
            r2 = r7
        L39:
            r8 = r0
        L3a:
            if (r1 == 0) goto L45
            r1.close()     // Catch: java.lang.Throwable -> L40
            goto L45
        L40:
            r0 = move-exception
            r7 = r0
            r8.addSuppressed(r7)     // Catch: java.lang.NumberFormatException -> L46 java.lang.NullPointerException -> L48
        L45:
            throw r8     // Catch: java.lang.NumberFormatException -> L46 java.lang.NullPointerException -> L48
        L46:
            r0 = move-exception
            goto L4e
        L48:
            r0 = move-exception
            goto L4e
        L4a:
            r0 = move-exception
            goto L4d
        L4c:
            r0 = move-exception
        L4d:
            r2 = r7
        L4e:
            r7 = r0
            com.alibaba.fastjson2.JSONException r8 = new com.alibaba.fastjson2.JSONException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "JSON#toJSONString cannot serialize '"
            r0.<init>(r1)
            java.lang.StringBuilder r0 = r0.append(r2)
            java.lang.String r1 = "'"
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            r8.<init>(r0, r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.toJSONString(java.lang.Object, com.alibaba.fastjson2.JSONWriter$Context):java.lang.String");
    }

    static String toJSONString(Object obj, JSONWriter.Feature... featureArr) {
        JSONWriter.Context context = new JSONWriter.Context(JSONFactory.defaultObjectWriterProvider, featureArr);
        JSONWriter of = JSONWriter.of(context);
        try {
            if (obj == null) {
                of.writeNull();
            } else {
                of.rootObject = obj;
                of.path = JSONWriter.Path.ROOT;
                Class<?> cls = obj.getClass();
                context.provider.getObjectWriter(cls, cls, (context.features & JSONWriter.Feature.FieldBased.mask) != 0).write(of, obj, null, null, 0L);
            }
            String obj2 = of.toString();
            if (of != null) {
                of.close();
            }
            return obj2;
        } catch (Throwable th) {
            if (of == null) {
                throw th;
            }
            try {
                of.close();
                throw th;
            } catch (Throwable th2) {
                th.addSuppressed(th2);
                throw th;
            }
        }
    }

    static String toJSONString(Object obj, Filter filter, JSONWriter.Feature... featureArr) {
        JSONWriter.Context context = new JSONWriter.Context(JSONFactory.defaultObjectWriterProvider, featureArr);
        JSONWriter of = JSONWriter.of(context);
        try {
            if (obj == null) {
                of.writeNull();
            } else {
                of.rootObject = obj;
                of.path = JSONWriter.Path.ROOT;
                if (filter != null) {
                    of.context.configFilter(filter);
                }
                Class<?> cls = obj.getClass();
                context.getObjectWriter(cls, cls).write(of, obj, null, null, 0L);
            }
            String obj2 = of.toString();
            if (of != null) {
                of.close();
            }
            return obj2;
        } catch (Throwable th) {
            if (of == null) {
                throw th;
            }
            try {
                of.close();
                throw th;
            } catch (Throwable th2) {
                th.addSuppressed(th2);
                throw th;
            }
        }
    }

    static String toJSONString(Object obj, Filter[] filterArr, JSONWriter.Feature... featureArr) {
        JSONWriter.Context context = new JSONWriter.Context(JSONFactory.defaultObjectWriterProvider, featureArr);
        if (filterArr != null && filterArr.length != 0) {
            context.configFilter(filterArr);
        }
        JSONWriter of = JSONWriter.of(context);
        try {
            if (obj == null) {
                of.writeNull();
            } else {
                of.rootObject = obj;
                of.path = JSONWriter.Path.ROOT;
                Class<?> cls = obj.getClass();
                context.getObjectWriter(cls, cls).write(of, obj, null, null, 0L);
            }
            String obj2 = of.toString();
            if (of != null) {
                of.close();
            }
            return obj2;
        } finally {
        }
    }

    static String toJSONString(Object obj, String str, JSONWriter.Feature... featureArr) {
        JSONWriter.Context context = new JSONWriter.Context(JSONFactory.defaultObjectWriterProvider, featureArr);
        if (str != null && !str.isEmpty()) {
            context.setDateFormat(str);
        }
        JSONWriter of = JSONWriter.of(context);
        try {
            if (obj == null) {
                of.writeNull();
            } else {
                of.rootObject = obj;
                of.path = JSONWriter.Path.ROOT;
                Class<?> cls = obj.getClass();
                context.getObjectWriter(cls, cls).write(of, obj, null, null, 0L);
            }
            String obj2 = of.toString();
            if (of != null) {
                of.close();
            }
            return obj2;
        } finally {
        }
    }

    static String toJSONString(Object obj, String str, Filter[] filterArr, JSONWriter.Feature... featureArr) {
        JSONWriter.Context context = new JSONWriter.Context(JSONFactory.defaultObjectWriterProvider, featureArr);
        if (str != null && !str.isEmpty()) {
            context.setDateFormat(str);
        }
        if (filterArr != null && filterArr.length != 0) {
            context.configFilter(filterArr);
        }
        JSONWriter of = JSONWriter.of(context);
        try {
            if (obj == null) {
                of.writeNull();
            } else {
                of.rootObject = obj;
                of.path = JSONWriter.Path.ROOT;
                Class<?> cls = obj.getClass();
                context.getObjectWriter(cls, cls).write(of, obj, null, null, 0L);
            }
            String obj2 = of.toString();
            if (of != null) {
                of.close();
            }
            return obj2;
        } finally {
        }
    }

    static byte[] toJSONBytes(Object obj) {
        ObjectWriterProvider objectWriterProvider = JSONFactory.defaultObjectWriterProvider;
        JSONWriter ofUTF8 = JSONWriter.ofUTF8(new JSONWriter.Context(objectWriterProvider));
        try {
            if (obj == null) {
                ofUTF8.writeNull();
            } else {
                ofUTF8.rootObject = obj;
                ofUTF8.path = JSONWriter.Path.ROOT;
                Class<?> cls = obj.getClass();
                if (cls == JSONObject.class && ofUTF8.context.features == 0) {
                    ofUTF8.write((JSONObject) obj);
                } else {
                    objectWriterProvider.getObjectWriter(cls, cls, (JSONFactory.defaultWriterFeatures & JSONWriter.Feature.FieldBased.mask) != 0).write(ofUTF8, obj, null, null, 0L);
                }
            }
            byte[] bytes = ofUTF8.getBytes();
            if (ofUTF8 != null) {
                ofUTF8.close();
            }
            return bytes;
        } catch (Throwable th) {
            if (ofUTF8 == null) {
                throw th;
            }
            try {
                ofUTF8.close();
                throw th;
            } catch (Throwable th2) {
                th.addSuppressed(th2);
                throw th;
            }
        }
    }

    static byte[] toJSONBytes(Object obj, Charset charset, JSONWriter.Feature... featureArr) {
        ObjectWriterProvider objectWriterProvider = JSONFactory.defaultObjectWriterProvider;
        JSONWriter ofUTF8 = JSONWriter.ofUTF8(new JSONWriter.Context(objectWriterProvider, featureArr));
        try {
            if (obj == null) {
                ofUTF8.writeNull();
            } else {
                ofUTF8.rootObject = obj;
                ofUTF8.path = JSONWriter.Path.ROOT;
                Class<?> cls = obj.getClass();
                if (cls == JSONObject.class && ofUTF8.context.features == 0) {
                    ofUTF8.write((JSONObject) obj);
                } else {
                    objectWriterProvider.getObjectWriter(cls, cls, (JSONFactory.defaultWriterFeatures & JSONWriter.Feature.FieldBased.mask) != 0).write(ofUTF8, obj, null, null, 0L);
                }
            }
            byte[] bytes = ofUTF8.getBytes(charset);
            if (ofUTF8 != null) {
                ofUTF8.close();
            }
            return bytes;
        } catch (Throwable th) {
            if (ofUTF8 == null) {
                throw th;
            }
            try {
                ofUTF8.close();
                throw th;
            } catch (Throwable th2) {
                th.addSuppressed(th2);
                throw th;
            }
        }
    }

    static byte[] toJSONBytes(Object obj, Charset charset, JSONWriter.Context context) {
        ObjectWriterProvider objectWriterProvider = context.provider;
        JSONWriter ofUTF8 = JSONWriter.ofUTF8(context);
        try {
            if (obj == null) {
                ofUTF8.writeNull();
            } else {
                ofUTF8.rootObject = obj;
                ofUTF8.path = JSONWriter.Path.ROOT;
                Class<?> cls = obj.getClass();
                if (cls == JSONObject.class && ofUTF8.context.features == 0) {
                    ofUTF8.write((JSONObject) obj);
                } else {
                    objectWriterProvider.getObjectWriter(cls, cls, (JSONFactory.defaultWriterFeatures & JSONWriter.Feature.FieldBased.mask) != 0).write(ofUTF8, obj, null, null, 0L);
                }
            }
            byte[] bytes = ofUTF8.getBytes(charset);
            if (ofUTF8 != null) {
                ofUTF8.close();
            }
            return bytes;
        } catch (Throwable th) {
            if (ofUTF8 == null) {
                throw th;
            }
            try {
                ofUTF8.close();
                throw th;
            } catch (Throwable th2) {
                th.addSuppressed(th2);
                throw th;
            }
        }
    }

    static byte[] toJSONBytes(Object obj, String str, JSONWriter.Feature... featureArr) {
        JSONWriter.Context context = new JSONWriter.Context(JSONFactory.defaultObjectWriterProvider, featureArr);
        if (str != null && !str.isEmpty()) {
            context.setDateFormat(str);
        }
        JSONWriter ofUTF8 = JSONWriter.ofUTF8(context);
        try {
            if (obj == null) {
                ofUTF8.writeNull();
            } else {
                ofUTF8.rootObject = obj;
                ofUTF8.path = JSONWriter.Path.ROOT;
                Class<?> cls = obj.getClass();
                context.getObjectWriter(cls, cls).write(ofUTF8, obj, null, null, 0L);
            }
            byte[] bytes = ofUTF8.getBytes();
            if (ofUTF8 != null) {
                ofUTF8.close();
            }
            return bytes;
        } finally {
        }
    }

    static byte[] toJSONBytes(Object obj, Filter... filterArr) {
        JSONWriter.Context context = new JSONWriter.Context(JSONFactory.defaultObjectWriterProvider);
        if (filterArr != null && filterArr.length != 0) {
            context.configFilter(filterArr);
        }
        JSONWriter ofUTF8 = JSONWriter.ofUTF8(context);
        try {
            if (obj == null) {
                ofUTF8.writeNull();
            } else {
                ofUTF8.rootObject = obj;
                ofUTF8.path = JSONWriter.Path.ROOT;
                Class<?> cls = obj.getClass();
                context.getObjectWriter(cls, cls).write(ofUTF8, obj, null, null, 0L);
            }
            byte[] bytes = ofUTF8.getBytes();
            if (ofUTF8 != null) {
                ofUTF8.close();
            }
            return bytes;
        } finally {
        }
    }

    static byte[] toJSONBytes(Object obj, JSONWriter.Feature... featureArr) {
        JSONWriter.Context context = new JSONWriter.Context(JSONFactory.defaultObjectWriterProvider, featureArr);
        JSONWriter ofUTF8 = JSONWriter.ofUTF8(context);
        try {
            if (obj == null) {
                ofUTF8.writeNull();
            } else {
                ofUTF8.rootObject = obj;
                ofUTF8.path = JSONWriter.Path.ROOT;
                Class<?> cls = obj.getClass();
                context.getObjectWriter(cls, cls).write(ofUTF8, obj, null, null, 0L);
            }
            byte[] bytes = ofUTF8.getBytes();
            if (ofUTF8 != null) {
                ofUTF8.close();
            }
            return bytes;
        } catch (Throwable th) {
            if (ofUTF8 == null) {
                throw th;
            }
            try {
                ofUTF8.close();
                throw th;
            } catch (Throwable th2) {
                th.addSuppressed(th2);
                throw th;
            }
        }
    }

    static byte[] toJSONBytes(Object obj, Filter[] filterArr, JSONWriter.Feature... featureArr) {
        JSONWriter.Context context = new JSONWriter.Context(JSONFactory.defaultObjectWriterProvider, featureArr);
        if (filterArr != null && filterArr.length != 0) {
            context.configFilter(filterArr);
        }
        JSONWriter ofUTF8 = JSONWriter.ofUTF8(context);
        try {
            if (obj == null) {
                ofUTF8.writeNull();
            } else {
                ofUTF8.rootObject = obj;
                ofUTF8.path = JSONWriter.Path.ROOT;
                Class<?> cls = obj.getClass();
                context.getObjectWriter(cls, cls).write(ofUTF8, obj, null, null, 0L);
            }
            byte[] bytes = ofUTF8.getBytes();
            if (ofUTF8 != null) {
                ofUTF8.close();
            }
            return bytes;
        } finally {
        }
    }

    static byte[] toJSONBytes(Object obj, String str, Filter[] filterArr, JSONWriter.Feature... featureArr) {
        JSONWriter.Context context = new JSONWriter.Context(JSONFactory.defaultObjectWriterProvider, featureArr);
        if (str != null && !str.isEmpty()) {
            context.setDateFormat(str);
        }
        if (filterArr != null && filterArr.length != 0) {
            context.configFilter(filterArr);
        }
        JSONWriter ofUTF8 = JSONWriter.ofUTF8(context);
        try {
            if (obj == null) {
                ofUTF8.writeNull();
            } else {
                ofUTF8.rootObject = obj;
                ofUTF8.path = JSONWriter.Path.ROOT;
                Class<?> cls = obj.getClass();
                context.getObjectWriter(cls, cls).write(ofUTF8, obj, null, null, 0L);
            }
            byte[] bytes = ofUTF8.getBytes();
            if (ofUTF8 != null) {
                ofUTF8.close();
            }
            return bytes;
        } finally {
        }
    }

    static int writeTo(OutputStream outputStream, Object obj) {
        JSONWriter.Context context = new JSONWriter.Context(JSONFactory.defaultObjectWriterProvider);
        try {
            JSONWriter ofUTF8 = JSONWriter.ofUTF8(context);
            try {
                if (obj == null) {
                    ofUTF8.writeNull();
                } else {
                    ofUTF8.rootObject = obj;
                    ofUTF8.path = JSONWriter.Path.ROOT;
                    Class<?> cls = obj.getClass();
                    context.getObjectWriter(cls, cls).write(ofUTF8, obj, null, null, 0L);
                }
                int flushTo = ofUTF8.flushTo(outputStream);
                if (ofUTF8 != null) {
                    ofUTF8.close();
                }
                return flushTo;
            } finally {
            }
        } catch (Exception e) {
            throw new JSONException(e.getMessage(), e);
        }
    }

    static int writeTo(OutputStream outputStream, Object obj, JSONWriter.Context context) {
        try {
            JSONWriter ofUTF8 = JSONWriter.ofUTF8(context);
            try {
                if (obj == null) {
                    ofUTF8.writeNull();
                } else {
                    ofUTF8.rootObject = obj;
                    ofUTF8.path = JSONWriter.Path.ROOT;
                    Class<?> cls = obj.getClass();
                    context.getObjectWriter(cls, cls).write(ofUTF8, obj, null, null, 0L);
                }
                int flushTo = ofUTF8.flushTo(outputStream);
                if (ofUTF8 != null) {
                    ofUTF8.close();
                }
                return flushTo;
            } finally {
            }
        } catch (Exception e) {
            throw new JSONException(e.getMessage(), e);
        }
    }

    static int writeTo(OutputStream outputStream, Object obj, JSONWriter.Feature... featureArr) {
        JSONWriter.Context context = new JSONWriter.Context(JSONFactory.defaultObjectWriterProvider, featureArr);
        try {
            JSONWriter ofUTF8 = JSONWriter.ofUTF8(context);
            try {
                if (obj == null) {
                    ofUTF8.writeNull();
                } else {
                    ofUTF8.rootObject = obj;
                    ofUTF8.path = JSONWriter.Path.ROOT;
                    Class<?> cls = obj.getClass();
                    context.getObjectWriter(cls, cls).write(ofUTF8, obj, null, null, 0L);
                }
                int flushTo = ofUTF8.flushTo(outputStream);
                if (ofUTF8 != null) {
                    ofUTF8.close();
                }
                return flushTo;
            } finally {
            }
        } catch (Exception e) {
            throw new JSONException(e.getMessage(), e);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0045 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:32:? A[Catch: Exception -> 0x004f, SYNTHETIC, TRY_LEAVE, TryCatch #1 {Exception -> 0x004f, blocks: (B:17:0x003a, B:31:0x004e, B:30:0x004b, B:25:0x0045), top: B:8:0x0013, inners: #2 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static int writeTo(java.io.OutputStream r8, java.lang.Object r9, com.alibaba.fastjson2.filter.Filter[] r10, com.alibaba.fastjson2.JSONWriter.Feature... r11) {
        /*
            com.alibaba.fastjson2.JSONWriter$Context r0 = new com.alibaba.fastjson2.JSONWriter$Context
            com.alibaba.fastjson2.writer.ObjectWriterProvider r1 = com.alibaba.fastjson2.JSONFactory.defaultObjectWriterProvider
            r0.<init>(r1, r11)
            if (r10 == 0) goto Lf
            int r11 = r10.length
            if (r11 == 0) goto Lf
            r0.configFilter(r10)
        Lf:
            com.alibaba.fastjson2.JSONWriter r2 = com.alibaba.fastjson2.JSONWriter.ofUTF8(r0)     // Catch: java.lang.Exception -> L51
            if (r9 != 0) goto L1e
            r2.writeNull()     // Catch: java.lang.Throwable -> L1a
            r3 = r9
            goto L34
        L1a:
            r0 = move-exception
            r8 = r0
            r3 = r9
            goto L43
        L1e:
            r2.rootObject = r9     // Catch: java.lang.Throwable -> L40
            com.alibaba.fastjson2.JSONWriter$Path r10 = com.alibaba.fastjson2.JSONWriter.Path.ROOT     // Catch: java.lang.Throwable -> L40
            r2.path = r10     // Catch: java.lang.Throwable -> L40
            java.lang.Class r10 = r9.getClass()     // Catch: java.lang.Throwable -> L40
            com.alibaba.fastjson2.writer.ObjectWriter r1 = r0.getObjectWriter(r10, r10)     // Catch: java.lang.Throwable -> L40
            r5 = 0
            r6 = 0
            r4 = 0
            r3 = r9
            r1.write(r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L3e
        L34:
            int r8 = r2.flushTo(r8)     // Catch: java.lang.Throwable -> L3e
            if (r2 == 0) goto L3d
            r2.close()     // Catch: java.lang.Exception -> L4f
        L3d:
            return r8
        L3e:
            r0 = move-exception
            goto L42
        L40:
            r0 = move-exception
            r3 = r9
        L42:
            r8 = r0
        L43:
            if (r2 == 0) goto L4e
            r2.close()     // Catch: java.lang.Throwable -> L49
            goto L4e
        L49:
            r0 = move-exception
            r9 = r0
            r8.addSuppressed(r9)     // Catch: java.lang.Exception -> L4f
        L4e:
            throw r8     // Catch: java.lang.Exception -> L4f
        L4f:
            r0 = move-exception
            goto L53
        L51:
            r0 = move-exception
            r3 = r9
        L53:
            r8 = r0
            com.alibaba.fastjson2.JSONException r9 = new com.alibaba.fastjson2.JSONException
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r11 = "JSON#writeTo cannot serialize '"
            r10.<init>(r11)
            java.lang.StringBuilder r10 = r10.append(r3)
            java.lang.String r11 = "' to 'OutputStream'"
            java.lang.StringBuilder r10 = r10.append(r11)
            java.lang.String r10 = r10.toString()
            r9.<init>(r10, r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.writeTo(java.io.OutputStream, java.lang.Object, com.alibaba.fastjson2.filter.Filter[], com.alibaba.fastjson2.JSONWriter$Feature[]):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0050 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:35:? A[Catch: Exception -> 0x005a, SYNTHETIC, TRY_LEAVE, TryCatch #3 {Exception -> 0x005a, blocks: (B:20:0x0045, B:34:0x0059, B:33:0x0056, B:28:0x0050), top: B:13:0x001e, inners: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static int writeTo(java.io.OutputStream r8, java.lang.Object r9, java.lang.String r10, com.alibaba.fastjson2.filter.Filter[] r11, com.alibaba.fastjson2.JSONWriter.Feature... r12) {
        /*
            com.alibaba.fastjson2.JSONWriter$Context r0 = new com.alibaba.fastjson2.JSONWriter$Context
            com.alibaba.fastjson2.writer.ObjectWriterProvider r1 = com.alibaba.fastjson2.JSONFactory.defaultObjectWriterProvider
            r0.<init>(r1, r12)
            if (r10 == 0) goto L12
            boolean r12 = r10.isEmpty()
            if (r12 != 0) goto L12
            r0.setDateFormat(r10)
        L12:
            if (r11 == 0) goto L1a
            int r10 = r11.length
            if (r10 == 0) goto L1a
            r0.configFilter(r11)
        L1a:
            com.alibaba.fastjson2.JSONWriter r2 = com.alibaba.fastjson2.JSONWriter.ofUTF8(r0)     // Catch: java.lang.Exception -> L5c
            if (r9 != 0) goto L29
            r2.writeNull()     // Catch: java.lang.Throwable -> L25
            r3 = r9
            goto L3f
        L25:
            r0 = move-exception
            r8 = r0
            r3 = r9
            goto L4e
        L29:
            r2.rootObject = r9     // Catch: java.lang.Throwable -> L4b
            com.alibaba.fastjson2.JSONWriter$Path r10 = com.alibaba.fastjson2.JSONWriter.Path.ROOT     // Catch: java.lang.Throwable -> L4b
            r2.path = r10     // Catch: java.lang.Throwable -> L4b
            java.lang.Class r10 = r9.getClass()     // Catch: java.lang.Throwable -> L4b
            com.alibaba.fastjson2.writer.ObjectWriter r1 = r0.getObjectWriter(r10, r10)     // Catch: java.lang.Throwable -> L4b
            r5 = 0
            r6 = 0
            r4 = 0
            r3 = r9
            r1.write(r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L49
        L3f:
            int r8 = r2.flushTo(r8)     // Catch: java.lang.Throwable -> L49
            if (r2 == 0) goto L48
            r2.close()     // Catch: java.lang.Exception -> L5a
        L48:
            return r8
        L49:
            r0 = move-exception
            goto L4d
        L4b:
            r0 = move-exception
            r3 = r9
        L4d:
            r8 = r0
        L4e:
            if (r2 == 0) goto L59
            r2.close()     // Catch: java.lang.Throwable -> L54
            goto L59
        L54:
            r0 = move-exception
            r9 = r0
            r8.addSuppressed(r9)     // Catch: java.lang.Exception -> L5a
        L59:
            throw r8     // Catch: java.lang.Exception -> L5a
        L5a:
            r0 = move-exception
            goto L5e
        L5c:
            r0 = move-exception
            r3 = r9
        L5e:
            r8 = r0
            com.alibaba.fastjson2.JSONException r9 = new com.alibaba.fastjson2.JSONException
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r11 = "JSON#writeTo cannot serialize '"
            r10.<init>(r11)
            java.lang.StringBuilder r10 = r10.append(r3)
            java.lang.String r11 = "' to 'OutputStream'"
            java.lang.StringBuilder r10 = r10.append(r11)
            java.lang.String r10 = r10.toString()
            r9.<init>(r10, r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.writeTo(java.io.OutputStream, java.lang.Object, java.lang.String, com.alibaba.fastjson2.filter.Filter[], com.alibaba.fastjson2.JSONWriter$Feature[]):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0020 A[Catch: JSONException | ArrayIndexOutOfBoundsException -> 0x0030, JSONException | ArrayIndexOutOfBoundsException -> 0x0030, TRY_ENTER, TRY_LEAVE, TryCatch #2 {JSONException | ArrayIndexOutOfBoundsException -> 0x0030, blocks: (B:6:0x000a, B:14:0x0020, B:14:0x0020, B:21:0x002f, B:21:0x002f, B:26:0x002c, B:26:0x002c), top: B:5:0x000a }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static boolean isValid(java.lang.String r2) {
        /*
            r0 = 0
            if (r2 == 0) goto L30
            boolean r1 = r2.isEmpty()
            if (r1 == 0) goto La
            goto L30
        La:
            com.alibaba.fastjson2.JSONReader r2 = com.alibaba.fastjson2.JSONReader.of(r2)     // Catch: java.lang.Throwable -> L30
            r2.skipValue()     // Catch: java.lang.Throwable -> L24
            boolean r1 = r2.isEnd()     // Catch: java.lang.Throwable -> L24
            if (r1 == 0) goto L1d
            boolean r1 = r2.comma     // Catch: java.lang.Throwable -> L24
            if (r1 != 0) goto L1d
            r1 = 1
            goto L1e
        L1d:
            r1 = r0
        L1e:
            if (r2 == 0) goto L23
            r2.close()     // Catch: java.lang.Throwable -> L30 java.lang.Throwable -> L30
        L23:
            return r1
        L24:
            r1 = move-exception
            if (r2 == 0) goto L2f
            r2.close()     // Catch: java.lang.Throwable -> L2b
            goto L2f
        L2b:
            r2 = move-exception
            r1.addSuppressed(r2)     // Catch: java.lang.Throwable -> L30 java.lang.Throwable -> L30
        L2f:
            throw r1     // Catch: java.lang.Throwable -> L30 java.lang.Throwable -> L30
        L30:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.isValid(java.lang.String):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0024 A[Catch: JSONException | ArrayIndexOutOfBoundsException -> 0x0034, JSONException | ArrayIndexOutOfBoundsException -> 0x0034, TRY_ENTER, TRY_LEAVE, TryCatch #0 {JSONException | ArrayIndexOutOfBoundsException -> 0x0034, blocks: (B:6:0x000a, B:14:0x0024, B:14:0x0024, B:21:0x0033, B:21:0x0033, B:26:0x0030, B:26:0x0030), top: B:5:0x000a }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static boolean isValid(java.lang.String r2, com.alibaba.fastjson2.JSONReader.Feature... r3) {
        /*
            r0 = 0
            if (r2 == 0) goto L34
            boolean r1 = r2.isEmpty()
            if (r1 == 0) goto La
            goto L34
        La:
            com.alibaba.fastjson2.JSONReader$Context r3 = com.alibaba.fastjson2.JSONFactory.createReadContext(r3)     // Catch: java.lang.Throwable -> L34
            com.alibaba.fastjson2.JSONReader r2 = com.alibaba.fastjson2.JSONReader.of(r2, r3)     // Catch: java.lang.Throwable -> L34
            r2.skipValue()     // Catch: java.lang.Throwable -> L28
            boolean r3 = r2.isEnd()     // Catch: java.lang.Throwable -> L28
            if (r3 == 0) goto L21
            boolean r3 = r2.comma     // Catch: java.lang.Throwable -> L28
            if (r3 != 0) goto L21
            r3 = 1
            goto L22
        L21:
            r3 = r0
        L22:
            if (r2 == 0) goto L27
            r2.close()     // Catch: java.lang.Throwable -> L34 java.lang.Throwable -> L34
        L27:
            return r3
        L28:
            r3 = move-exception
            if (r2 == 0) goto L33
            r2.close()     // Catch: java.lang.Throwable -> L2f
            goto L33
        L2f:
            r2 = move-exception
            r3.addSuppressed(r2)     // Catch: java.lang.Throwable -> L34 java.lang.Throwable -> L34
        L33:
            throw r3     // Catch: java.lang.Throwable -> L34 java.lang.Throwable -> L34
        L34:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.isValid(java.lang.String, com.alibaba.fastjson2.JSONReader$Feature[]):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x001d A[Catch: JSONException | ArrayIndexOutOfBoundsException -> 0x002d, JSONException | ArrayIndexOutOfBoundsException -> 0x002d, TRY_ENTER, TRY_LEAVE, TryCatch #2 {JSONException | ArrayIndexOutOfBoundsException -> 0x002d, blocks: (B:6:0x0007, B:14:0x001d, B:14:0x001d, B:21:0x002c, B:21:0x002c, B:26:0x0029, B:26:0x0029), top: B:5:0x0007 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static boolean isValid(char[] r2) {
        /*
            r0 = 0
            if (r2 == 0) goto L2d
            int r1 = r2.length
            if (r1 != 0) goto L7
            goto L2d
        L7:
            com.alibaba.fastjson2.JSONReader r2 = com.alibaba.fastjson2.JSONReader.of(r2)     // Catch: java.lang.Throwable -> L2d
            r2.skipValue()     // Catch: java.lang.Throwable -> L21
            boolean r1 = r2.isEnd()     // Catch: java.lang.Throwable -> L21
            if (r1 == 0) goto L1a
            boolean r1 = r2.comma     // Catch: java.lang.Throwable -> L21
            if (r1 != 0) goto L1a
            r1 = 1
            goto L1b
        L1a:
            r1 = r0
        L1b:
            if (r2 == 0) goto L20
            r2.close()     // Catch: java.lang.Throwable -> L2d java.lang.Throwable -> L2d
        L20:
            return r1
        L21:
            r1 = move-exception
            if (r2 == 0) goto L2c
            r2.close()     // Catch: java.lang.Throwable -> L28
            goto L2c
        L28:
            r2 = move-exception
            r1.addSuppressed(r2)     // Catch: java.lang.Throwable -> L2d java.lang.Throwable -> L2d
        L2c:
            throw r1     // Catch: java.lang.Throwable -> L2d java.lang.Throwable -> L2d
        L2d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.isValid(char[]):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x002c A[Catch: JSONException | ArrayIndexOutOfBoundsException -> 0x003c, JSONException | ArrayIndexOutOfBoundsException -> 0x003c, TRY_ENTER, TRY_LEAVE, TryCatch #2 {JSONException | ArrayIndexOutOfBoundsException -> 0x003c, blocks: (B:6:0x000a, B:11:0x0016, B:11:0x0016, B:20:0x002c, B:20:0x002c, B:26:0x003b, B:26:0x003b, B:31:0x0038, B:31:0x0038), top: B:5:0x000a }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static boolean isValidObject(java.lang.String r2) {
        /*
            r0 = 0
            if (r2 == 0) goto L3c
            boolean r1 = r2.isEmpty()
            if (r1 == 0) goto La
            goto L3c
        La:
            com.alibaba.fastjson2.JSONReader r2 = com.alibaba.fastjson2.JSONReader.of(r2)     // Catch: java.lang.Throwable -> L3c
            boolean r1 = r2.isObject()     // Catch: java.lang.Throwable -> L30
            if (r1 != 0) goto L1a
            if (r2 == 0) goto L19
            r2.close()     // Catch: java.lang.Throwable -> L3c java.lang.Throwable -> L3c
        L19:
            return r0
        L1a:
            r2.skipValue()     // Catch: java.lang.Throwable -> L30
            boolean r1 = r2.isEnd()     // Catch: java.lang.Throwable -> L30
            if (r1 == 0) goto L29
            boolean r1 = r2.comma     // Catch: java.lang.Throwable -> L30
            if (r1 != 0) goto L29
            r1 = 1
            goto L2a
        L29:
            r1 = r0
        L2a:
            if (r2 == 0) goto L2f
            r2.close()     // Catch: java.lang.Throwable -> L3c java.lang.Throwable -> L3c
        L2f:
            return r1
        L30:
            r1 = move-exception
            if (r2 == 0) goto L3b
            r2.close()     // Catch: java.lang.Throwable -> L37
            goto L3b
        L37:
            r2 = move-exception
            r1.addSuppressed(r2)     // Catch: java.lang.Throwable -> L3c java.lang.Throwable -> L3c
        L3b:
            throw r1     // Catch: java.lang.Throwable -> L3c java.lang.Throwable -> L3c
        L3c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.isValidObject(java.lang.String):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0029 A[Catch: JSONException | ArrayIndexOutOfBoundsException -> 0x0039, JSONException | ArrayIndexOutOfBoundsException -> 0x0039, TRY_ENTER, TRY_LEAVE, TryCatch #1 {JSONException | ArrayIndexOutOfBoundsException -> 0x0039, blocks: (B:6:0x0007, B:11:0x0013, B:11:0x0013, B:20:0x0029, B:20:0x0029, B:26:0x0038, B:26:0x0038, B:31:0x0035, B:31:0x0035), top: B:5:0x0007 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static boolean isValidObject(byte[] r2) {
        /*
            r0 = 0
            if (r2 == 0) goto L39
            int r1 = r2.length
            if (r1 != 0) goto L7
            goto L39
        L7:
            com.alibaba.fastjson2.JSONReader r2 = com.alibaba.fastjson2.JSONReader.of(r2)     // Catch: java.lang.Throwable -> L39
            boolean r1 = r2.isObject()     // Catch: java.lang.Throwable -> L2d
            if (r1 != 0) goto L17
            if (r2 == 0) goto L16
            r2.close()     // Catch: java.lang.Throwable -> L39 java.lang.Throwable -> L39
        L16:
            return r0
        L17:
            r2.skipValue()     // Catch: java.lang.Throwable -> L2d
            boolean r1 = r2.isEnd()     // Catch: java.lang.Throwable -> L2d
            if (r1 == 0) goto L26
            boolean r1 = r2.comma     // Catch: java.lang.Throwable -> L2d
            if (r1 != 0) goto L26
            r1 = 1
            goto L27
        L26:
            r1 = r0
        L27:
            if (r2 == 0) goto L2c
            r2.close()     // Catch: java.lang.Throwable -> L39 java.lang.Throwable -> L39
        L2c:
            return r1
        L2d:
            r1 = move-exception
            if (r2 == 0) goto L38
            r2.close()     // Catch: java.lang.Throwable -> L34
            goto L38
        L34:
            r2 = move-exception
            r1.addSuppressed(r2)     // Catch: java.lang.Throwable -> L39 java.lang.Throwable -> L39
        L38:
            throw r1     // Catch: java.lang.Throwable -> L39 java.lang.Throwable -> L39
        L39:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.isValidObject(byte[]):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x002c A[Catch: JSONException | ArrayIndexOutOfBoundsException -> 0x003c, JSONException | ArrayIndexOutOfBoundsException -> 0x003c, TRY_ENTER, TRY_LEAVE, TryCatch #2 {JSONException | ArrayIndexOutOfBoundsException -> 0x003c, blocks: (B:6:0x000a, B:11:0x0016, B:11:0x0016, B:20:0x002c, B:20:0x002c, B:26:0x003b, B:26:0x003b, B:31:0x0038, B:31:0x0038), top: B:5:0x000a }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static boolean isValidArray(java.lang.String r2) {
        /*
            r0 = 0
            if (r2 == 0) goto L3c
            boolean r1 = r2.isEmpty()
            if (r1 == 0) goto La
            goto L3c
        La:
            com.alibaba.fastjson2.JSONReader r2 = com.alibaba.fastjson2.JSONReader.of(r2)     // Catch: java.lang.Throwable -> L3c
            boolean r1 = r2.isArray()     // Catch: java.lang.Throwable -> L30
            if (r1 != 0) goto L1a
            if (r2 == 0) goto L19
            r2.close()     // Catch: java.lang.Throwable -> L3c java.lang.Throwable -> L3c
        L19:
            return r0
        L1a:
            r2.skipValue()     // Catch: java.lang.Throwable -> L30
            boolean r1 = r2.isEnd()     // Catch: java.lang.Throwable -> L30
            if (r1 == 0) goto L29
            boolean r1 = r2.comma     // Catch: java.lang.Throwable -> L30
            if (r1 != 0) goto L29
            r1 = 1
            goto L2a
        L29:
            r1 = r0
        L2a:
            if (r2 == 0) goto L2f
            r2.close()     // Catch: java.lang.Throwable -> L3c java.lang.Throwable -> L3c
        L2f:
            return r1
        L30:
            r1 = move-exception
            if (r2 == 0) goto L3b
            r2.close()     // Catch: java.lang.Throwable -> L37
            goto L3b
        L37:
            r2 = move-exception
            r1.addSuppressed(r2)     // Catch: java.lang.Throwable -> L3c java.lang.Throwable -> L3c
        L3b:
            throw r1     // Catch: java.lang.Throwable -> L3c java.lang.Throwable -> L3c
        L3c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.isValidArray(java.lang.String):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x001d A[Catch: JSONException | ArrayIndexOutOfBoundsException -> 0x002d, JSONException | ArrayIndexOutOfBoundsException -> 0x002d, TRY_ENTER, TRY_LEAVE, TryCatch #2 {JSONException | ArrayIndexOutOfBoundsException -> 0x002d, blocks: (B:6:0x0007, B:14:0x001d, B:14:0x001d, B:21:0x002c, B:21:0x002c, B:26:0x0029, B:26:0x0029), top: B:5:0x0007 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static boolean isValid(byte[] r2) {
        /*
            r0 = 0
            if (r2 == 0) goto L2d
            int r1 = r2.length
            if (r1 != 0) goto L7
            goto L2d
        L7:
            com.alibaba.fastjson2.JSONReader r2 = com.alibaba.fastjson2.JSONReader.of(r2)     // Catch: java.lang.Throwable -> L2d
            r2.skipValue()     // Catch: java.lang.Throwable -> L21
            boolean r1 = r2.isEnd()     // Catch: java.lang.Throwable -> L21
            if (r1 == 0) goto L1a
            boolean r1 = r2.comma     // Catch: java.lang.Throwable -> L21
            if (r1 != 0) goto L1a
            r1 = 1
            goto L1b
        L1a:
            r1 = r0
        L1b:
            if (r2 == 0) goto L20
            r2.close()     // Catch: java.lang.Throwable -> L2d java.lang.Throwable -> L2d
        L20:
            return r1
        L21:
            r1 = move-exception
            if (r2 == 0) goto L2c
            r2.close()     // Catch: java.lang.Throwable -> L28
            goto L2c
        L28:
            r2 = move-exception
            r1.addSuppressed(r2)     // Catch: java.lang.Throwable -> L2d java.lang.Throwable -> L2d
        L2c:
            throw r1     // Catch: java.lang.Throwable -> L2d java.lang.Throwable -> L2d
        L2d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.isValid(byte[]):boolean");
    }

    static boolean isValid(byte[] bArr, Charset charset) {
        if (bArr == null || bArr.length == 0) {
            return false;
        }
        return isValid(bArr, 0, bArr.length, charset);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0029 A[Catch: JSONException | ArrayIndexOutOfBoundsException -> 0x0039, JSONException | ArrayIndexOutOfBoundsException -> 0x0039, TRY_ENTER, TRY_LEAVE, TryCatch #1 {JSONException | ArrayIndexOutOfBoundsException -> 0x0039, blocks: (B:6:0x0007, B:11:0x0013, B:11:0x0013, B:20:0x0029, B:20:0x0029, B:26:0x0038, B:26:0x0038, B:31:0x0035, B:31:0x0035), top: B:5:0x0007 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static boolean isValidArray(byte[] r2) {
        /*
            r0 = 0
            if (r2 == 0) goto L39
            int r1 = r2.length
            if (r1 != 0) goto L7
            goto L39
        L7:
            com.alibaba.fastjson2.JSONReader r2 = com.alibaba.fastjson2.JSONReader.of(r2)     // Catch: java.lang.Throwable -> L39
            boolean r1 = r2.isArray()     // Catch: java.lang.Throwable -> L2d
            if (r1 != 0) goto L17
            if (r2 == 0) goto L16
            r2.close()     // Catch: java.lang.Throwable -> L39 java.lang.Throwable -> L39
        L16:
            return r0
        L17:
            r2.skipValue()     // Catch: java.lang.Throwable -> L2d
            boolean r1 = r2.isEnd()     // Catch: java.lang.Throwable -> L2d
            if (r1 == 0) goto L26
            boolean r1 = r2.comma     // Catch: java.lang.Throwable -> L2d
            if (r1 != 0) goto L26
            r1 = 1
            goto L27
        L26:
            r1 = r0
        L27:
            if (r2 == 0) goto L2c
            r2.close()     // Catch: java.lang.Throwable -> L39 java.lang.Throwable -> L39
        L2c:
            return r1
        L2d:
            r1 = move-exception
            if (r2 == 0) goto L38
            r2.close()     // Catch: java.lang.Throwable -> L34
            goto L38
        L34:
            r2 = move-exception
            r1.addSuppressed(r2)     // Catch: java.lang.Throwable -> L39 java.lang.Throwable -> L39
        L38:
            throw r1     // Catch: java.lang.Throwable -> L39 java.lang.Throwable -> L39
        L39:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.isValidArray(byte[]):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x001f A[Catch: JSONException | ArrayIndexOutOfBoundsException -> 0x002f, JSONException | ArrayIndexOutOfBoundsException -> 0x002f, TRY_ENTER, TRY_LEAVE, TryCatch #2 {JSONException | ArrayIndexOutOfBoundsException -> 0x002f, blocks: (B:7:0x0009, B:15:0x001f, B:15:0x001f, B:22:0x002e, B:22:0x002e, B:27:0x002b, B:27:0x002b), top: B:6:0x0009 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static boolean isValid(byte[] r2, int r3, int r4, java.nio.charset.Charset r5) {
        /*
            r0 = 0
            if (r2 == 0) goto L2f
            int r1 = r2.length
            if (r1 == 0) goto L2f
            if (r4 != 0) goto L9
            goto L2f
        L9:
            com.alibaba.fastjson2.JSONReader r2 = com.alibaba.fastjson2.JSONReader.of(r2, r3, r4, r5)     // Catch: java.lang.Throwable -> L2f
            r2.skipValue()     // Catch: java.lang.Throwable -> L23
            boolean r3 = r2.isEnd()     // Catch: java.lang.Throwable -> L23
            if (r3 == 0) goto L1c
            boolean r3 = r2.comma     // Catch: java.lang.Throwable -> L23
            if (r3 != 0) goto L1c
            r3 = 1
            goto L1d
        L1c:
            r3 = r0
        L1d:
            if (r2 == 0) goto L22
            r2.close()     // Catch: java.lang.Throwable -> L2f java.lang.Throwable -> L2f
        L22:
            return r3
        L23:
            r3 = move-exception
            if (r2 == 0) goto L2e
            r2.close()     // Catch: java.lang.Throwable -> L2a
            goto L2e
        L2a:
            r2 = move-exception
            r3.addSuppressed(r2)     // Catch: java.lang.Throwable -> L2f java.lang.Throwable -> L2f
        L2e:
            throw r3     // Catch: java.lang.Throwable -> L2f java.lang.Throwable -> L2f
        L2f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.isValid(byte[], int, int, java.nio.charset.Charset):boolean");
    }

    static Object toJSON(Object obj) {
        return toJSON(obj, null);
    }

    static Object toJSON(Object obj, JSONWriter.Feature... featureArr) {
        if (obj == null) {
            return null;
        }
        if ((obj instanceof JSONObject) || (obj instanceof JSONArray)) {
            return obj;
        }
        JSONWriter.Context createWriteContext = featureArr == null ? JSONFactory.createWriteContext() : JSONFactory.createWriteContext(featureArr);
        Class<?> cls = obj.getClass();
        ObjectWriter objectWriter = createWriteContext.getObjectWriter(cls, cls);
        if ((objectWriter instanceof ObjectWriterAdapter) && !createWriteContext.isEnabled(JSONWriter.Feature.ReferenceDetection) && (objectWriter.getFeatures() & JSONWriter.Feature.WriteClassName.mask) == 0) {
            return ((ObjectWriterAdapter) objectWriter).toJSONObject(obj, createWriteContext.features);
        }
        try {
            JSONWriter of = JSONWriter.of(createWriteContext);
            try {
                objectWriter.write(of, obj, null, null, createWriteContext.features);
                String obj2 = of.toString();
                if (of != null) {
                    of.close();
                }
                return parse(obj2);
            } finally {
            }
        } catch (NullPointerException | NumberFormatException e) {
            throw new JSONException("toJSONString error", e);
        }
    }

    static <T> T to(Class<T> cls, Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof JSONObject) {
            return (T) ((JSONObject) obj).to((Class) cls, new JSONReader.Feature[0]);
        }
        return (T) TypeUtils.cast(obj, (Class) cls, JSONFactory.getDefaultObjectReaderProvider());
    }

    static <T> T toJavaObject(Object obj, Class<T> cls) {
        return (T) to(cls, obj);
    }

    static void mixIn(Class<?> cls, Class<?> cls2) {
        JSONFactory.defaultObjectWriterProvider.mixIn(cls, cls2);
        JSONFactory.getDefaultObjectReaderProvider().mixIn(cls, cls2);
    }

    static ObjectReader<?> register(Type type, ObjectReader<?> objectReader) {
        return JSONFactory.getDefaultObjectReaderProvider().register(type, objectReader);
    }

    static ObjectReader<?> register(Type type, ObjectReader<?> objectReader, boolean z) {
        return JSONFactory.getDefaultObjectReaderProvider().register(type, objectReader, z);
    }

    static ObjectReader<?> registerIfAbsent(Type type, ObjectReader<?> objectReader) {
        return JSONFactory.getDefaultObjectReaderProvider().registerIfAbsent(type, objectReader);
    }

    static ObjectReader<?> registerIfAbsent(Type type, ObjectReader<?> objectReader, boolean z) {
        return JSONFactory.getDefaultObjectReaderProvider().registerIfAbsent(type, objectReader, z);
    }

    static boolean register(ObjectReaderModule objectReaderModule) {
        return JSONFactory.getDefaultObjectReaderProvider().register(objectReaderModule);
    }

    static void registerSeeAlsoSubType(Class cls) {
        registerSeeAlsoSubType(cls, null);
    }

    static void registerSeeAlsoSubType(Class cls, String str) {
        JSONFactory.getDefaultObjectReaderProvider().registerSeeAlsoSubType(cls, str);
    }

    static boolean register(ObjectWriterModule objectWriterModule) {
        return JSONFactory.getDefaultObjectWriterProvider().register(objectWriterModule);
    }

    static ObjectWriter<?> register(Type type, ObjectWriter<?> objectWriter) {
        return JSONFactory.getDefaultObjectWriterProvider().register(type, objectWriter);
    }

    static ObjectWriter<?> register(Type type, ObjectWriter<?> objectWriter, boolean z) {
        return JSONFactory.getDefaultObjectWriterProvider().register(type, objectWriter, z);
    }

    static ObjectWriter<?> registerIfAbsent(Type type, ObjectWriter<?> objectWriter) {
        return JSONFactory.getDefaultObjectWriterProvider().registerIfAbsent(type, objectWriter);
    }

    static ObjectWriter<?> registerIfAbsent(Type type, ObjectWriter<?> objectWriter, boolean z) {
        return JSONFactory.getDefaultObjectWriterProvider().registerIfAbsent(type, objectWriter, z);
    }

    static void register(Class cls, Filter filter) {
        if ((filter instanceof AfterFilter) || (filter instanceof BeforeFilter) || (filter instanceof ContextNameFilter) || (filter instanceof ContextValueFilter) || (filter instanceof LabelFilter) || (filter instanceof NameFilter) || (filter instanceof PropertyFilter) || (filter instanceof PropertyPreFilter) || (filter instanceof ValueFilter)) {
            JSONFactory.getDefaultObjectWriterProvider().getObjectWriter(cls).setFilter(filter);
        }
    }

    static void config(JSONReader.Feature... featureArr) {
        for (JSONReader.Feature feature : featureArr) {
            if (feature == JSONReader.Feature.SupportAutoType) {
                throw new JSONException("not support config global autotype support");
            }
            JSONFactory.defaultReaderFeatures |= feature.mask;
        }
    }

    static void config(JSONReader.Feature feature, boolean z) {
        if (feature == JSONReader.Feature.SupportAutoType && z) {
            throw new JSONException("not support config global autotype support");
        }
        if (z) {
            JSONFactory.defaultReaderFeatures = feature.mask | JSONFactory.defaultReaderFeatures;
        } else {
            JSONFactory.defaultReaderFeatures = (~feature.mask) & JSONFactory.defaultReaderFeatures;
        }
    }

    static boolean isEnabled(JSONReader.Feature feature) {
        return (JSONFactory.defaultReaderFeatures & feature.mask) != 0;
    }

    static void configReaderDateFormat(String str) {
        JSONFactory.defaultReaderFormat = str;
    }

    static void configWriterDateFormat(String str) {
        JSONFactory.defaultWriterFormat = str;
    }

    static void configReaderZoneId(ZoneId zoneId) {
        JSONFactory.defaultReaderZoneId = zoneId;
    }

    static void configWriterZoneId(ZoneId zoneId) {
        JSONFactory.defaultWriterZoneId = zoneId;
    }

    static void config(JSONWriter.Feature... featureArr) {
        for (JSONWriter.Feature feature : featureArr) {
            JSONFactory.defaultWriterFeatures |= feature.mask;
        }
    }

    static void config(JSONWriter.Feature feature, boolean z) {
        if (z) {
            JSONFactory.defaultWriterFeatures = feature.mask | JSONFactory.defaultWriterFeatures;
        } else {
            JSONFactory.defaultWriterFeatures = (~feature.mask) & JSONFactory.defaultWriterFeatures;
        }
    }

    static boolean isEnabled(JSONWriter.Feature feature) {
        return (JSONFactory.defaultWriterFeatures & feature.mask) != 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:60:0x00e5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:67:? A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static <T> T copy(T r14, com.alibaba.fastjson2.JSONWriter.Feature... r15) {
        /*
            Method dump skipped, instructions count: 251
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSON.copy(java.lang.Object, com.alibaba.fastjson2.JSONWriter$Feature[]):java.lang.Object");
    }

    static <T> T copyTo(Object obj, Class<T> cls, JSONWriter.Feature... featureArr) {
        JSONWriter jSONWriter;
        if (obj == null) {
            return null;
        }
        Class<?> cls2 = obj.getClass();
        long j = JSONFactory.defaultReaderFeatures;
        boolean z = false;
        boolean z2 = false;
        for (JSONWriter.Feature feature : featureArr) {
            j |= feature.mask;
            if (feature == JSONWriter.Feature.FieldBased) {
                z = true;
            } else if (feature == JSONWriter.Feature.BeanToArray) {
                z2 = true;
            }
        }
        ObjectWriter objectWriter = JSONFactory.defaultObjectWriterProvider.getObjectWriter(cls2, cls2, z);
        ObjectReader objectReader = JSONFactory.defaultObjectReaderProvider.getObjectReader(cls, z);
        if ((objectWriter instanceof ObjectWriterAdapter) && (objectReader instanceof ObjectReaderBean)) {
            List<FieldWriter> fieldWriters = objectWriter.getFieldWriters();
            if (objectReader instanceof ObjectReaderNoneDefaultConstructor) {
                HashMap hashMap = new HashMap(fieldWriters.size(), 1.0f);
                for (int i = 0; i < fieldWriters.size(); i++) {
                    FieldWriter fieldWriter = fieldWriters.get(i);
                    hashMap.put(fieldWriter.fieldName, fieldWriter.getFieldValue(obj));
                }
                return (T) objectReader.createInstance(hashMap, j);
            }
            T t = (T) objectReader.createInstance(j);
            for (int i2 = 0; i2 < fieldWriters.size(); i2++) {
                FieldWriter fieldWriter2 = fieldWriters.get(i2);
                FieldReader fieldReader = objectReader.getFieldReader(fieldWriter2.fieldName);
                if (fieldReader != null) {
                    Object fieldValue = fieldWriter2.getFieldValue(obj);
                    if (fieldWriter2.fieldClass == Date.class && fieldReader.fieldClass == String.class) {
                        fieldValue = DateUtils.format((Date) fieldValue, fieldWriter2.format);
                    } else if (fieldWriter2.fieldClass == LocalDate.class && fieldReader.fieldClass == String.class) {
                        fieldValue = DateUtils.format((LocalDate) fieldValue, fieldWriter2.format);
                    } else if (fieldValue != null && !fieldReader.supportAcceptType(fieldValue.getClass())) {
                        fieldValue = copy(fieldValue, new JSONWriter.Feature[0]);
                    }
                    fieldReader.accept((FieldReader) t, fieldValue);
                }
            }
            return t;
        }
        JSONWriter ofJSONB = JSONWriter.ofJSONB(featureArr);
        try {
            ofJSONB.config(JSONWriter.Feature.WriteClassName);
            jSONWriter = ofJSONB;
            try {
                objectWriter.writeJSONB(jSONWriter, obj, null, null, 0L);
                byte[] bytes = jSONWriter.getBytes();
                if (jSONWriter != null) {
                    jSONWriter.close();
                }
                JSONReader ofJSONB2 = JSONReader.ofJSONB(bytes, JSONReader.Feature.SupportAutoType, JSONReader.Feature.SupportClassForName);
                if (z2) {
                    try {
                        ofJSONB2.context.config(JSONReader.Feature.SupportArrayToBean);
                    } finally {
                    }
                }
                T t2 = (T) objectReader.readJSONBObject(ofJSONB2, null, null, 0L);
                if (ofJSONB2 != null) {
                    ofJSONB2.close();
                }
                return t2;
            } catch (Throwable th) {
                th = th;
                Throwable th2 = th;
                if (jSONWriter == null) {
                    throw th2;
                }
                try {
                    jSONWriter.close();
                    throw th2;
                } catch (Throwable th3) {
                    th2.addSuppressed(th3);
                    throw th2;
                }
            }
        } catch (Throwable th4) {
            th = th4;
            jSONWriter = ofJSONB;
        }
    }

    @SafeVarargs
    static void configEnumAsJavaBean(Class<? extends Enum>... clsArr) {
        JSONFactory.getDefaultObjectWriterProvider().configEnumAsJavaBean(clsArr);
    }
}
