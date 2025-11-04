package com.alibaba.fastjson2.util;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import kotlin.UByte;
import sun.misc.Unsafe;

/* loaded from: classes2.dex */
public class JDKUtils {
    public static final boolean ANDROID;
    public static final int ANDROID_SDK_INT;
    public static final long ARRAY_BYTE_BASE_OFFSET;
    public static final long ARRAY_CHAR_BASE_OFFSET;
    public static final boolean BIG_ENDIAN;
    static final Class<?> CLASS_SQL_DATASOURCE;
    static final Class<?> CLASS_SQL_ROW_SET;
    public static final Class CLASS_TRANSIENT;
    static volatile MethodHandle CONSTRUCTOR_LOOKUP;
    static volatile boolean CONSTRUCTOR_LOOKUP_ERROR;
    public static final long FIELD_BIGINTEGER_MAG_OFFSET;
    public static final long FIELD_DECIMAL_INT_COMPACT_OFFSET;
    public static final Field FIELD_STRING_CODER;
    public static volatile boolean FIELD_STRING_CODER_ERROR;
    public static final long FIELD_STRING_CODER_OFFSET;
    public static final Field FIELD_STRING_VALUE;
    public static volatile boolean FIELD_STRING_VALUE_ERROR;
    public static final long FIELD_STRING_VALUE_OFFSET;
    public static final boolean GRAAL;
    public static final boolean HAS_SQL;
    static final MethodHandles.Lookup IMPL_LOOKUP;
    public static final MethodHandle INDEX_OF_CHAR_LATIN1;
    public static final int JVM_VERSION;
    public static final MethodHandle METHOD_HANDLE_HAS_NEGATIVE;
    public static final boolean OPENJ9;
    public static final Predicate<byte[]> PREDICATE_IS_ASCII;
    public static final ToIntFunction<String> STRING_CODER;
    public static final BiFunction<byte[], Byte, String> STRING_CREATOR_JDK11;
    public static final BiFunction<char[], Boolean, String> STRING_CREATOR_JDK8;
    public static final Function<String, byte[]> STRING_VALUE;
    public static final Unsafe UNSAFE;
    public static final int VECTOR_BIT_LENGTH;
    public static final boolean VECTOR_SUPPORT;
    static volatile Throwable initErrorLast;
    static volatile Throwable reflectErrorLast;
    public static final Byte LATIN1 = (byte) 0;
    public static final Byte UTF16 = (byte) 1;
    static final AtomicInteger reflectErrorCount = new AtomicInteger();

    static /* synthetic */ int lambda$static$0(String str) {
        return 1;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(54:8|(12:9|10|11|12|13|14|(1:16)(1:239)|(2:237|238)|20|(1:22)|23|(2:25|26)(1:236))|(52:228|229|29|30|31|33|34|35|36|(43:220|221|39|(4:41|(5:184|185|186|187|45)(1:43)|44|45)(7:193|(8:211|212|213|214|197|(5:202|203|205|206|201)(1:199)|200|201)(1:195)|196|197|(0)(0)|200|201)|46|(2:48|(1:54)(1:52))|182|183|55|56|57|58|(1:60)(1:178)|61|(7:165|166|167|168|169|(1:171)|172)(1:63)|64|65|66|(2:68|(26:155|156|157|71|(4:73|74|75|76)(1:154)|77|(2:81|82)|86|(18:146|147|89|(15:141|142|92|(12:137|138|95|96|97|(1:99)(1:134)|100|(3:103|104|(10:108|109|110|111|112|113|114|(1:116)|117|118))|132|(0)|117|118)|94|95|96|97|(0)(0)|100|(3:103|104|(11:106|108|109|110|111|112|113|114|(0)|117|118))|132|(0)|117|118)|91|92|(0)|94|95|96|97|(0)(0)|100|(0)|132|(0)|117|118)|88|89|(0)|91|92|(0)|94|95|96|97|(0)(0)|100|(0)|132|(0)|117|118))(1:162)|70|71|(0)(0)|77|(3:79|81|82)|86|(0)|88|89|(0)|91|92|(0)|94|95|96|97|(0)(0)|100|(0)|132|(0)|117|118)|38|39|(0)(0)|46|(0)|182|183|55|56|57|58|(0)(0)|61|(0)(0)|64|65|66|(0)(0)|70|71|(0)(0)|77|(0)|86|(0)|88|89|(0)|91|92|(0)|94|95|96|97|(0)(0)|100|(0)|132|(0)|117|118)|28|29|30|31|33|34|35|36|(0)|38|39|(0)(0)|46|(0)|182|183|55|56|57|58|(0)(0)|61|(0)(0)|64|65|66|(0)(0)|70|71|(0)(0)|77|(0)|86|(0)|88|89|(0)|91|92|(0)|94|95|96|97|(0)(0)|100|(0)|132|(0)|117|118) */
    /* JADX WARN: Can't wrap try/catch for region: R(65:8|9|10|11|12|13|14|(1:16)(1:239)|(2:237|238)|20|(1:22)|23|(2:25|26)(1:236)|(52:228|229|29|30|31|33|34|35|36|(43:220|221|39|(4:41|(5:184|185|186|187|45)(1:43)|44|45)(7:193|(8:211|212|213|214|197|(5:202|203|205|206|201)(1:199)|200|201)(1:195)|196|197|(0)(0)|200|201)|46|(2:48|(1:54)(1:52))|182|183|55|56|57|58|(1:60)(1:178)|61|(7:165|166|167|168|169|(1:171)|172)(1:63)|64|65|66|(2:68|(26:155|156|157|71|(4:73|74|75|76)(1:154)|77|(2:81|82)|86|(18:146|147|89|(15:141|142|92|(12:137|138|95|96|97|(1:99)(1:134)|100|(3:103|104|(10:108|109|110|111|112|113|114|(1:116)|117|118))|132|(0)|117|118)|94|95|96|97|(0)(0)|100|(3:103|104|(11:106|108|109|110|111|112|113|114|(0)|117|118))|132|(0)|117|118)|91|92|(0)|94|95|96|97|(0)(0)|100|(0)|132|(0)|117|118)|88|89|(0)|91|92|(0)|94|95|96|97|(0)(0)|100|(0)|132|(0)|117|118))(1:162)|70|71|(0)(0)|77|(3:79|81|82)|86|(0)|88|89|(0)|91|92|(0)|94|95|96|97|(0)(0)|100|(0)|132|(0)|117|118)|38|39|(0)(0)|46|(0)|182|183|55|56|57|58|(0)(0)|61|(0)(0)|64|65|66|(0)(0)|70|71|(0)(0)|77|(0)|86|(0)|88|89|(0)|91|92|(0)|94|95|96|97|(0)(0)|100|(0)|132|(0)|117|118)|28|29|30|31|33|34|35|36|(0)|38|39|(0)(0)|46|(0)|182|183|55|56|57|58|(0)(0)|61|(0)(0)|64|65|66|(0)(0)|70|71|(0)(0)|77|(0)|86|(0)|88|89|(0)|91|92|(0)|94|95|96|97|(0)(0)|100|(0)|132|(0)|117|118) */
    /* JADX WARN: Code restructure failed: missing block: B:135:0x042d, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:136:0x042e, code lost:
    
        r1 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:163:0x0226, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:164:0x0227, code lost:
    
        r13 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:180:0x0183, code lost:
    
        r10 = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:226:0x00c9, code lost:
    
        r12 = false;
        r8 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:227:0x00c8, code lost:
    
        r0 = null;
     */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0345 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0439  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0340  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x02c9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:141:0x02a2 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:146:0x0270 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:154:0x0250  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0224  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x0198 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:171:0x01b2  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x0191  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x013c  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x012b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:220:0x00d3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x018f  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x01b8  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x01c2 A[Catch: all -> 0x0226, TRY_LEAVE, TryCatch #8 {all -> 0x0226, blocks: (B:66:0x01be, B:68:0x01c2), top: B:65:0x01be }] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0238  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0256  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x02f6 A[Catch: all -> 0x042d, TRY_LEAVE, TryCatch #13 {all -> 0x042d, blocks: (B:97:0x02f2, B:99:0x02f6), top: B:96:0x02f2 }] */
    /* JADX WARN: Unreachable blocks removed: 2, instructions: 2 */
    static {
        /*
            Method dump skipped, instructions count: 1114
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.util.JDKUtils.<clinit>():void");
    }

    public static boolean isSQLDataSourceOrRowSet(Class<?> cls) {
        Class<?> cls2 = CLASS_SQL_DATASOURCE;
        if (cls2 != null && cls2.isAssignableFrom(cls)) {
            return true;
        }
        Class<?> cls3 = CLASS_SQL_ROW_SET;
        return cls3 != null && cls3.isAssignableFrom(cls);
    }

    public static void setReflectErrorLast(Throwable th) {
        reflectErrorCount.incrementAndGet();
        reflectErrorLast = th;
    }

    public static char[] getCharArray(String str) {
        if (!FIELD_STRING_VALUE_ERROR) {
            try {
                return (char[]) UNSAFE.getObject(str, FIELD_STRING_VALUE_OFFSET);
            } catch (Exception unused) {
                FIELD_STRING_VALUE_ERROR = true;
            }
        }
        return str.toCharArray();
    }

    public static MethodHandles.Lookup trustedLookup(Class cls) {
        if (!CONSTRUCTOR_LOOKUP_ERROR) {
            try {
                MethodHandle methodHandle = CONSTRUCTOR_LOOKUP;
                if (JVM_VERSION < 15) {
                    if (methodHandle == null) {
                        methodHandle = IMPL_LOOKUP.findConstructor(MethodHandles.Lookup.class, MethodType.methodType(Void.TYPE, Class.class, Integer.TYPE));
                        CONSTRUCTOR_LOOKUP = methodHandle;
                    }
                    return (MethodHandles.Lookup) methodHandle.invoke(cls, OPENJ9 ? 31 : -1);
                }
                if (methodHandle == null) {
                    methodHandle = IMPL_LOOKUP.findConstructor(MethodHandles.Lookup.class, MethodType.methodType(Void.TYPE, Class.class, Class.class, Integer.TYPE));
                    CONSTRUCTOR_LOOKUP = methodHandle;
                }
                return (MethodHandles.Lookup) methodHandle.invoke(cls, null, -1);
            } catch (Throwable unused) {
                CONSTRUCTOR_LOOKUP_ERROR = true;
            }
        }
        return IMPL_LOOKUP.in(cls);
    }

    public static String asciiStringJDK8(byte[] bArr, int i, int i2) {
        char[] cArr = new char[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            cArr[i3] = (char) bArr[i + i3];
        }
        return STRING_CREATOR_JDK8.apply(cArr, Boolean.TRUE);
    }

    public static String latin1StringJDK8(byte[] bArr, int i, int i2) {
        char[] cArr = new char[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            cArr[i3] = (char) (bArr[i + i3] & UByte.MAX_VALUE);
        }
        return STRING_CREATOR_JDK8.apply(cArr, Boolean.TRUE);
    }
}
