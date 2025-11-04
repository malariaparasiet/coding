package com.alibaba.fastjson2.writer;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.alibaba.fastjson2.JSONB;
import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONFactory;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.SymbolTable;
import com.alibaba.fastjson2.annotation.JSONField;
import com.alibaba.fastjson2.codec.FieldInfo;
import com.alibaba.fastjson2.internal.asm.Opcodes;
import com.alibaba.fastjson2.util.BeanUtils;
import com.alibaba.fastjson2.util.Fnv;
import com.alibaba.fastjson2.util.IOUtils;
import com.alibaba.fastjson2.util.JDKUtils;
import com.alibaba.fastjson2.util.JdbcSupport;
import com.alibaba.fastjson2.util.JodaSupport;
import com.alibaba.fastjson2.util.TypeUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.function.Function;
import java.util.zip.GZIPOutputStream;
import kotlin.text.Typography;
import kotlin.time.DurationKt;
import org.bouncycastle.asn1.BERTags;

/* loaded from: classes2.dex */
public abstract class FieldWriter<T> implements Comparable {
    static final AtomicReferenceFieldUpdater<FieldWriter, ObjectWriter> initObjectWriterUpdater = AtomicReferenceFieldUpdater.newUpdater(FieldWriter.class, ObjectWriter.class, "initObjectWriter");
    final boolean backReference;
    public final DecimalFormat decimalFormat;
    Object defaultValue;
    public final long features;
    public final Field field;
    public final Class fieldClass;
    final boolean fieldClassSerializable;
    public final String fieldName;
    protected final long fieldOffset;
    public final Type fieldType;
    public final String format;
    final long hashCode;
    volatile ObjectWriter initObjectWriter;
    public final String label;
    public final Locale locale;
    final boolean managedReference;
    public final Method method;
    final byte[] nameJSONB;
    long nameSymbolCache;
    final char[] nameWithColonUTF16;
    final byte[] nameWithColonUTF8;
    public final int ordinal;
    transient JSONWriter.Path path;
    protected final boolean primitive;
    final boolean raw;
    final JSONWriter.Path rootParentPath;
    final boolean symbol;
    final boolean trim;

    public Function getFunction() {
        return null;
    }

    public ObjectWriter getInitWriter() {
        return null;
    }

    public Class getItemClass() {
        return null;
    }

    public Type getItemType() {
        return null;
    }

    public boolean isDateFormatISO8601() {
        return false;
    }

    public boolean isDateFormatMillis() {
        return false;
    }

    public boolean unwrapped() {
        return false;
    }

    public abstract boolean write(JSONWriter jSONWriter, T t);

    public abstract void writeValue(JSONWriter jSONWriter, T t);

    FieldWriter(String str, int i, long j, String str2, Locale locale, String str3, Type type, Class cls, Field field, Method method) {
        if (TypedValues.Custom.S_STRING.equals(str2) && cls != String.class) {
            j |= JSONWriter.Feature.WriteNonStringValueAsString.mask;
        }
        this.fieldName = str;
        this.ordinal = i;
        this.format = str2;
        this.locale = locale;
        this.label = str3;
        this.hashCode = Fnv.hashCode64(str);
        this.features = j;
        this.fieldType = TypeUtils.intern(type);
        this.fieldClass = cls;
        this.fieldClassSerializable = cls != null && (Serializable.class.isAssignableFrom(cls) || !Modifier.isFinal(cls.getModifiers()));
        this.field = field;
        this.method = method;
        this.primitive = cls.isPrimitive();
        this.nameJSONB = JSONB.toBytes(str);
        this.decimalFormat = (str2 == null || !(cls == Float.TYPE || cls == float[].class || cls == Float.class || cls == Float[].class || cls == Double.TYPE || cls == double[].class || cls == Double.class || cls == Double[].class || cls == BigDecimal.class || cls == BigDecimal[].class)) ? null : new DecimalFormat(str2);
        this.fieldOffset = field != null ? JDKUtils.UNSAFE.objectFieldOffset(field) : -1L;
        this.symbol = "symbol".equals(str2);
        this.trim = "trim".equals(str2);
        this.raw = (FieldInfo.RAW_VALUE_MASK & j) != 0;
        this.managedReference = (JSONWriter.Feature.ReferenceDetection.mask & j) != 0;
        this.backReference = (j & 2305843009213693952L) != 0;
        this.rootParentPath = new JSONWriter.Path(JSONWriter.Path.ROOT, str);
        int length = str.length();
        int i2 = length + 3;
        int i3 = i2;
        for (int i4 = 0; i4 < length; i4++) {
            char charAt = str.charAt(i4);
            if (charAt < 1 || charAt > 127) {
                i3 = charAt > 2047 ? i3 + 2 : i3 + 1;
            }
        }
        byte[] bArr = new byte[i3];
        bArr[0] = 34;
        int i5 = 1;
        for (int i6 = 0; i6 < length; i6++) {
            char charAt2 = str.charAt(i6);
            if (charAt2 >= 1 && charAt2 <= 127) {
                bArr[i5] = (byte) charAt2;
                i5++;
            } else if (charAt2 > 2047) {
                bArr[i5] = (byte) (((charAt2 >> '\f') & 15) | BERTags.FLAGS);
                int i7 = i5 + 2;
                bArr[i5 + 1] = (byte) (((charAt2 >> 6) & 63) | 128);
                i5 += 3;
                bArr[i7] = (byte) ((charAt2 & '?') | 128);
            } else {
                int i8 = i5 + 1;
                bArr[i5] = (byte) (((charAt2 >> 6) & 31) | 192);
                i5 += 2;
                bArr[i8] = (byte) ((charAt2 & '?') | 128);
            }
        }
        bArr[i5] = 34;
        bArr[i5 + 1] = 58;
        this.nameWithColonUTF8 = bArr;
        char[] cArr = new char[i2];
        cArr[0] = Typography.quote;
        str.getChars(0, str.length(), cArr, 1);
        cArr[length + 1] = Typography.quote;
        cArr[length + 2] = ':';
        this.nameWithColonUTF16 = cArr;
    }

    public boolean isFieldClassSerializable() {
        return this.fieldClassSerializable;
    }

    public int writeEnumValueJSONB(byte[] bArr, int i, Enum r3, SymbolTable symbolTable, long j) {
        throw new UnsupportedOperationException();
    }

    public void writeEnumJSONB(JSONWriter jSONWriter, Enum r2) {
        throw new UnsupportedOperationException();
    }

    public final void writeFieldNameJSONB(JSONWriter jSONWriter) {
        SymbolTable symbolTable = jSONWriter.symbolTable;
        if (symbolTable == null || !writeFieldNameSymbol(jSONWriter, symbolTable)) {
            jSONWriter.writeNameRaw(this.nameJSONB, this.hashCode);
        }
    }

    public final int writeFieldNameJSONB(byte[] bArr, int i) {
        byte[] bArr2 = this.nameJSONB;
        System.arraycopy(bArr2, 0, bArr, i, bArr2.length);
        return i + this.nameJSONB.length;
    }

    public final int writeFieldNameJSONB(byte[] bArr, int i, JSONWriter jSONWriter) {
        return JSONB.IO.writeNameRaw(bArr, i, this.nameJSONB, this.hashCode, jSONWriter);
    }

    public final void writeFieldName(JSONWriter jSONWriter) {
        if (jSONWriter.jsonb) {
            SymbolTable symbolTable = jSONWriter.symbolTable;
            if (symbolTable == null || !writeFieldNameSymbol(jSONWriter, symbolTable)) {
                jSONWriter.writeNameRaw(this.nameJSONB, this.hashCode);
                return;
            }
            return;
        }
        if (!jSONWriter.useSingleQuote && (jSONWriter.context.getFeatures() & JSONWriter.Feature.UnquoteFieldName.mask) == 0) {
            if (jSONWriter.utf8) {
                jSONWriter.writeNameRaw(this.nameWithColonUTF8);
                return;
            } else if (jSONWriter.utf16) {
                jSONWriter.writeNameRaw(this.nameWithColonUTF16);
                return;
            }
        }
        jSONWriter.writeName(this.fieldName);
        jSONWriter.writeColon();
    }

    private boolean writeFieldNameSymbol(JSONWriter jSONWriter, SymbolTable symbolTable) {
        int ordinalByHashCode;
        int identityHashCode = System.identityHashCode(symbolTable);
        long j = this.nameSymbolCache;
        if (j == 0) {
            ordinalByHashCode = symbolTable.getOrdinalByHashCode(this.hashCode);
            this.nameSymbolCache = (ordinalByHashCode << 32) | identityHashCode;
        } else if (((int) j) == identityHashCode) {
            ordinalByHashCode = (int) (j >> 32);
        } else {
            ordinalByHashCode = symbolTable.getOrdinalByHashCode(this.hashCode);
            this.nameSymbolCache = (ordinalByHashCode << 32) | identityHashCode;
        }
        if (ordinalByHashCode == -1) {
            return false;
        }
        jSONWriter.writeSymbol(-ordinalByHashCode);
        return true;
    }

    public int writeFieldNameSymbol(SymbolTable symbolTable) {
        int identityHashCode = System.identityHashCode(symbolTable);
        long j = this.nameSymbolCache;
        if (j == 0) {
            int ordinalByHashCode = symbolTable.getOrdinalByHashCode(this.hashCode);
            this.nameSymbolCache = (ordinalByHashCode << 32) | identityHashCode;
            return ordinalByHashCode;
        }
        if (((int) j) == identityHashCode) {
            return (int) (j >> 32);
        }
        int ordinalByHashCode2 = symbolTable.getOrdinalByHashCode(this.hashCode);
        this.nameSymbolCache = (ordinalByHashCode2 << 32) | identityHashCode;
        return ordinalByHashCode2;
    }

    public boolean isRefDetect(Object obj, long j) {
        long j2 = j | this.features;
        return ((JSONWriter.Feature.ReferenceDetection.mask & j2) == 0 || (j2 & FieldInfo.DISABLE_REFERENCE_DETECT) != 0 || obj == null || ObjectWriterProvider.isNotReferenceDetect(obj.getClass())) ? false : true;
    }

    public final JSONWriter.Path getRootParentPath() {
        return this.rootParentPath;
    }

    public final JSONWriter.Path getPath(JSONWriter.Path path) {
        JSONWriter.Path path2 = this.path;
        if (path2 == null) {
            JSONWriter.Path path3 = new JSONWriter.Path(path, this.fieldName);
            this.path = path3;
            return path3;
        }
        if (path2.parent == path) {
            return this.path;
        }
        return new JSONWriter.Path(path, this.fieldName);
    }

    public String toString() {
        return this.fieldName;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x002b  */
    /* JADX WARN: Removed duplicated region for block: B:40:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void setDefaultValue(T r5) {
        /*
            r4 = this;
            java.lang.Class<java.lang.Iterable> r0 = java.lang.Iterable.class
            java.lang.Class r1 = r4.fieldClass
            boolean r0 = r0.isAssignableFrom(r1)
            if (r0 != 0) goto L7f
            java.lang.Class<java.util.Map> r0 = java.util.Map.class
            java.lang.Class r1 = r4.fieldClass
            boolean r0 = r0.isAssignableFrom(r1)
            if (r0 == 0) goto L16
            goto L7f
        L16:
            java.lang.reflect.Field r0 = r4.field
            if (r0 == 0) goto L27
            if (r5 == 0) goto L27
            r1 = 1
            r0.setAccessible(r1)     // Catch: java.lang.Throwable -> L27
            java.lang.reflect.Field r0 = r4.field     // Catch: java.lang.Throwable -> L27
            java.lang.Object r5 = r0.get(r5)     // Catch: java.lang.Throwable -> L27
            goto L28
        L27:
            r5 = 0
        L28:
            if (r5 != 0) goto L2b
            goto L7f
        L2b:
            java.lang.Class r0 = r4.fieldClass
            java.lang.Class r1 = java.lang.Boolean.TYPE
            if (r0 != r1) goto L3a
            java.lang.Boolean r0 = java.lang.Boolean.FALSE
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L7d
            goto L7f
        L3a:
            java.lang.Class r0 = r4.fieldClass
            java.lang.Class r1 = java.lang.Byte.TYPE
            if (r0 == r1) goto L6f
            java.lang.Class r0 = r4.fieldClass
            java.lang.Class r1 = java.lang.Short.TYPE
            if (r0 == r1) goto L6f
            java.lang.Class r0 = r4.fieldClass
            java.lang.Class r1 = java.lang.Integer.TYPE
            if (r0 == r1) goto L6f
            java.lang.Class r0 = r4.fieldClass
            java.lang.Class r1 = java.lang.Long.TYPE
            if (r0 == r1) goto L6f
            java.lang.Class r0 = r4.fieldClass
            java.lang.Class r1 = java.lang.Float.TYPE
            if (r0 == r1) goto L6f
            java.lang.Class r0 = r4.fieldClass
            java.lang.Class r1 = java.lang.Double.TYPE
            if (r0 != r1) goto L5f
            goto L6f
        L5f:
            java.lang.Class r0 = r4.fieldClass
            java.lang.Class r1 = java.lang.Character.TYPE
            if (r0 != r1) goto L7d
            r0 = r5
            java.lang.Character r0 = (java.lang.Character) r0
            char r0 = r0.charValue()
            if (r0 != 0) goto L7d
            goto L7f
        L6f:
            r0 = r5
            java.lang.Number r0 = (java.lang.Number) r0
            double r0 = r0.doubleValue()
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 != 0) goto L7d
            goto L7f
        L7d:
            r4.defaultValue = r5
        L7f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.writer.FieldWriter.setDefaultValue(java.lang.Object):void");
    }

    public Object getFieldValue(T t) {
        if (t == null) {
            throw new JSONException("field.get error, " + this.fieldName);
        }
        Field field = this.field;
        if (field != null) {
            try {
                if (this.fieldOffset != -1 && !this.primitive) {
                    return JDKUtils.UNSAFE.getObject(t, this.fieldOffset);
                }
                return field.get(t);
            } catch (IllegalAccessException | IllegalArgumentException e) {
                throw new JSONException("field.get error, " + this.fieldName, e);
            }
        }
        throw new UnsupportedOperationException();
    }

    @Override // java.lang.Comparable
    public int compareTo(Object obj) {
        Member member;
        Member member2;
        JSONField jSONField;
        JSONField jSONField2;
        Field field;
        Field field2;
        FieldWriter fieldWriter = (FieldWriter) obj;
        int i = this.ordinal;
        int i2 = fieldWriter.ordinal;
        if (i < i2) {
            return -1;
        }
        if (i > i2) {
            return 1;
        }
        int compareTo = this.fieldName.compareTo(fieldWriter.fieldName);
        if (compareTo == 0) {
            if (this.method == null || ((field2 = this.field) != null && Modifier.isPublic(field2.getModifiers()))) {
                member = this.field;
            } else {
                member = this.method;
            }
            if (fieldWriter.method == null || ((field = fieldWriter.field) != null && Modifier.isPublic(field.getModifiers()))) {
                member2 = fieldWriter.field;
            } else {
                member2 = fieldWriter.method;
            }
            if (member != null && member2 != null) {
                Class<?> declaringClass = member2.getDeclaringClass();
                Class<?> declaringClass2 = member.getDeclaringClass();
                if (declaringClass2 != declaringClass) {
                    if (declaringClass2.isAssignableFrom(declaringClass)) {
                        return 1;
                    }
                    if (declaringClass.isAssignableFrom(declaringClass2)) {
                        return -1;
                    }
                }
                if (member instanceof Field) {
                    jSONField = (JSONField) ((Field) member).getAnnotation(JSONField.class);
                } else {
                    jSONField = member instanceof Method ? (JSONField) ((Method) member).getAnnotation(JSONField.class) : null;
                }
                if (member2 instanceof Field) {
                    jSONField2 = (JSONField) ((Field) member2).getAnnotation(JSONField.class);
                } else {
                    jSONField2 = member instanceof Method ? (JSONField) ((Method) member2).getAnnotation(JSONField.class) : null;
                }
                if (jSONField != null && jSONField2 == null) {
                    return -1;
                }
                if (jSONField == null && jSONField2 != null) {
                    return 1;
                }
            }
            if ((member instanceof Field) && (member2 instanceof Method) && ((Field) member).getType() == ((Method) member2).getReturnType()) {
                return -1;
            }
            boolean z = member instanceof Method;
            if (z && (member2 instanceof Field) && ((Method) member).getReturnType() == ((Field) member2).getType()) {
                return 1;
            }
            Class<?> cls = fieldWriter.fieldClass;
            Class<?> cls2 = this.fieldClass;
            if (cls2 != cls && cls2 != null && cls != null) {
                if (cls2.isAssignableFrom(cls)) {
                    return 1;
                }
                if (cls.isAssignableFrom(cls2)) {
                    return -1;
                }
            }
            if (cls2 == Boolean.TYPE && cls != Boolean.TYPE) {
                return 1;
            }
            if (cls2 == Boolean.class && cls == Boolean.class && z && (member2 instanceof Method)) {
                String name = member.getName();
                String name2 = member2.getName();
                if (name.startsWith("is") && name2.startsWith("get")) {
                    return 1;
                }
                if (name.startsWith("get") && name2.startsWith("is")) {
                    return -1;
                }
            }
            if (z && (member2 instanceof Method)) {
                String name3 = member.getName();
                String name4 = member2.getName();
                if (!name3.equals(name4)) {
                    String str = BeanUtils.getterName(name3, (String) null);
                    String str2 = BeanUtils.getterName(name4, (String) null);
                    if (this.fieldName.equals(str) && !fieldWriter.fieldName.equals(str2)) {
                        return 1;
                    }
                    if (this.fieldName.equals(str2) && !fieldWriter.fieldName.equals(str)) {
                        return -1;
                    }
                }
            }
            if (cls2.isPrimitive() && !cls.isPrimitive()) {
                return -1;
            }
            if (!cls2.isPrimitive() && cls.isPrimitive()) {
                return 1;
            }
            if (cls2.getName().startsWith("java.") && !cls.getName().startsWith("java.")) {
                return -1;
            }
            if (!cls2.getName().startsWith("java.") && cls.getName().startsWith("java.")) {
                return 1;
            }
            Method method = this.method;
            if (method != null && fieldWriter.method == null) {
                return -1;
            }
            if (method == null && fieldWriter.method != null) {
                return 1;
            }
        }
        return compareTo;
    }

    public void writeEnum(JSONWriter jSONWriter, Enum r2) {
        writeFieldName(jSONWriter);
        jSONWriter.writeEnum(r2);
    }

    public void writeBinary(JSONWriter jSONWriter, byte[] bArr) {
        GZIPOutputStream gZIPOutputStream;
        if (bArr == null) {
            if (jSONWriter.isWriteNulls()) {
                writeFieldName(jSONWriter);
                jSONWriter.writeArrayNull();
                return;
            }
            return;
        }
        writeFieldName(jSONWriter);
        if ((this.features & JSONWriter.Feature.WriteNonStringValueAsString.mask) != 0) {
            jSONWriter.writeString(bArr);
            return;
        }
        if ("base64".equals(this.format) || (this.format == null && (jSONWriter.getFeatures(this.features) & JSONWriter.Feature.WriteByteArrayAsBase64.mask) != 0)) {
            jSONWriter.writeBase64(bArr);
            return;
        }
        if ("hex".equals(this.format)) {
            jSONWriter.writeHex(bArr);
            return;
        }
        if ("gzip,base64".equals(this.format) || "gzip".equals(this.format)) {
            GZIPOutputStream gZIPOutputStream2 = null;
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
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    IOUtils.close(gZIPOutputStream2);
                    jSONWriter.writeBase64(byteArray);
                    return;
                } catch (IOException e) {
                    throw new JSONException("write gzipBytes error", e);
                }
            } catch (Throwable th) {
                IOUtils.close(gZIPOutputStream2);
                throw th;
            }
        }
        jSONWriter.writeBinary(bArr);
    }

    public void writeInt16(JSONWriter jSONWriter, short[] sArr) {
        if (sArr != null || jSONWriter.isWriteNulls()) {
            writeFieldName(jSONWriter);
            if ((this.features & JSONWriter.Feature.WriteNonStringValueAsString.mask) != 0) {
                jSONWriter.writeString(sArr);
            } else {
                jSONWriter.writeInt16(sArr);
            }
        }
    }

    public void writeInt32(JSONWriter jSONWriter, int i) {
        writeFieldName(jSONWriter);
        jSONWriter.writeInt32(i);
    }

    public void writeInt64(JSONWriter jSONWriter, long j) {
        writeFieldName(jSONWriter);
        if ((this.features & JSONWriter.Feature.WriteNonStringValueAsString.mask) != 0) {
            jSONWriter.writeString(Long.toString(j));
        } else {
            jSONWriter.writeInt64(j);
        }
    }

    public void writeString(JSONWriter jSONWriter, String str) {
        writeFieldName(jSONWriter);
        if (str == null && (this.features & (JSONWriter.Feature.NullAsDefaultValue.mask | JSONWriter.Feature.WriteNullStringAsEmpty.mask)) != 0) {
            jSONWriter.writeString("");
            return;
        }
        if (this.trim && str != null) {
            str = str.trim();
        }
        if (this.symbol && jSONWriter.jsonb) {
            jSONWriter.writeSymbol(str);
        } else if (this.raw) {
            jSONWriter.writeRaw(str);
        } else {
            jSONWriter.writeString(str);
        }
    }

    public void writeString(JSONWriter jSONWriter, char[] cArr) {
        if (cArr != null || jSONWriter.isWriteNulls()) {
            writeFieldName(jSONWriter);
            if (cArr == null) {
                jSONWriter.writeStringNull();
            } else {
                jSONWriter.writeString(cArr, 0, cArr.length);
            }
        }
    }

    public void writeFloat(JSONWriter jSONWriter, float f) {
        writeFieldName(jSONWriter);
        DecimalFormat decimalFormat = this.decimalFormat;
        if (decimalFormat != null) {
            jSONWriter.writeFloat(f, decimalFormat);
        } else if ((this.features & JSONWriter.Feature.WriteNonStringValueAsString.mask) != 0) {
            jSONWriter.writeString(Float.toString(f));
        } else {
            jSONWriter.writeFloat(f);
        }
    }

    public void writeDouble(JSONWriter jSONWriter, double d) {
        writeFieldName(jSONWriter);
        DecimalFormat decimalFormat = this.decimalFormat;
        if (decimalFormat != null) {
            jSONWriter.writeDouble(d, decimalFormat);
        } else if ((this.features & JSONWriter.Feature.WriteNonStringValueAsString.mask) != 0) {
            jSONWriter.writeString(Double.toString(d));
        } else {
            jSONWriter.writeDouble(d);
        }
    }

    public void writeBool(JSONWriter jSONWriter, boolean z) {
        throw new UnsupportedOperationException();
    }

    public void writeBool(JSONWriter jSONWriter, boolean[] zArr) {
        if (zArr != null || jSONWriter.isWriteNulls()) {
            writeFieldName(jSONWriter);
            if ((this.features & JSONWriter.Feature.WriteNonStringValueAsString.mask) != 0) {
                jSONWriter.writeString(zArr);
            } else {
                jSONWriter.writeBool(zArr);
            }
        }
    }

    public void writeFloat(JSONWriter jSONWriter, float[] fArr) {
        if (fArr != null || jSONWriter.isWriteNulls()) {
            writeFieldName(jSONWriter);
            if ((this.features & JSONWriter.Feature.WriteNonStringValueAsString.mask) != 0) {
                jSONWriter.writeString(fArr);
            } else {
                jSONWriter.writeFloat(fArr);
            }
        }
    }

    public void writeDouble(JSONWriter jSONWriter, double[] dArr) {
        if (dArr != null || jSONWriter.isWriteNulls()) {
            writeFieldName(jSONWriter);
            if ((this.features & JSONWriter.Feature.WriteNonStringValueAsString.mask) != 0) {
                jSONWriter.writeString(dArr);
            } else {
                jSONWriter.writeDouble(dArr);
            }
        }
    }

    public void writeDouble(JSONWriter jSONWriter, Double d) {
        if (d == null) {
            long features = jSONWriter.getFeatures(this.features);
            if ((JSONWriter.Feature.WriteNulls.mask & features) == 0 || (features & JSONWriter.Feature.NotWriteDefaultValue.mask) != 0) {
                return;
            }
            writeFieldName(jSONWriter);
            jSONWriter.writeNumberNull();
            return;
        }
        writeFieldName(jSONWriter);
        jSONWriter.writeDouble(d.doubleValue());
    }

    public void writeDate(JSONWriter jSONWriter, boolean z, Date date) {
        if (date == null) {
            if (z) {
                writeFieldName(jSONWriter);
            }
            jSONWriter.writeNull();
            return;
        }
        writeDate(jSONWriter, z, date.getTime());
    }

    public void writeDate(JSONWriter jSONWriter, long j) {
        writeDate(jSONWriter, true, j);
    }

    public void writeDate(JSONWriter jSONWriter, boolean z, long j) {
        long j2;
        if (jSONWriter.jsonb) {
            jSONWriter.writeMillis(j);
            return;
        }
        JSONWriter.Context context = jSONWriter.context;
        if (isDateFormatMillis() || context.isDateFormatMillis()) {
            if (z) {
                writeFieldName(jSONWriter);
            }
            jSONWriter.writeInt64(j);
            return;
        }
        ZoneId zoneId = context.getZoneId();
        if (context.getDateFormat() == null) {
            long epochSecond = Instant.ofEpochMilli(j).getEpochSecond() + zoneId.getRules().getOffset(r1).getTotalSeconds();
            long floorDiv = Math.floorDiv(epochSecond, 86400L);
            int floorMod = (int) Math.floorMod(epochSecond, 86400L);
            long j3 = 719468 + floorDiv;
            if (j3 < 0) {
                long j4 = ((floorDiv + 719469) / 146097) - 1;
                j2 = j4 * 400;
                j3 += (-j4) * 146097;
            } else {
                j2 = 0;
            }
            long j5 = ((j3 * 400) + 591) / 146097;
            long j6 = j3 - ((((j5 * 365) + (j5 / 4)) - (j5 / 100)) + (j5 / 400));
            if (j6 < 0) {
                j5--;
                j6 = j3 - ((((365 * j5) + (j5 / 4)) - (j5 / 100)) + (j5 / 400));
            }
            int i = (int) j6;
            int i2 = ((i * 5) + 2) / Opcodes.IFEQ;
            int i3 = ((i2 + 2) % 12) + 1;
            int i4 = (i - (((i2 * 306) + 5) / 10)) + 1;
            int checkValidIntValue = ChronoField.YEAR.checkValidIntValue(j5 + j2 + (i2 / 10));
            long j7 = floorMod;
            ChronoField.SECOND_OF_DAY.checkValidValue(j7);
            int i5 = (int) (j7 / 3600);
            long j8 = j7 - (i5 * 3600);
            int i6 = (int) (j8 / 60);
            int i7 = (int) (j8 - (i6 * 60));
            if (z) {
                writeFieldName(jSONWriter);
            }
            jSONWriter.writeDateTime19(checkValidIntValue, i3, i4, i5, i6, i7);
            return;
        }
        ZonedDateTime ofInstant = ZonedDateTime.ofInstant(Instant.ofEpochMilli(j), zoneId);
        if (isDateFormatISO8601() || context.isDateFormatISO8601()) {
            jSONWriter.writeDateTimeISO8601(ofInstant.getYear(), ofInstant.getMonthValue(), ofInstant.getDayOfMonth(), ofInstant.getHour(), ofInstant.getMinute(), ofInstant.getSecond(), ofInstant.getNano() / DurationKt.NANOS_IN_MILLIS, ofInstant.getOffset().getTotalSeconds(), true);
            return;
        }
        String format = context.getDateFormatter().format(ofInstant);
        if (z) {
            writeFieldName(jSONWriter);
        }
        jSONWriter.writeString(format);
    }

    public ObjectWriter getItemWriter(JSONWriter jSONWriter, Type type) {
        return jSONWriter.getObjectWriter(type, (Class) null);
    }

    public ObjectWriter getObjectWriter(JSONWriter jSONWriter, Class cls) {
        if (cls == Float[].class) {
            if (this.decimalFormat != null) {
                return new ObjectWriterArrayFinal(Float.class, this.decimalFormat);
            }
            return ObjectWriterArrayFinal.FLOAT_ARRAY;
        }
        if (cls == Double[].class) {
            if (this.decimalFormat != null) {
                return new ObjectWriterArrayFinal(Double.class, this.decimalFormat);
            }
            return ObjectWriterArrayFinal.DOUBLE_ARRAY;
        }
        if (cls == BigDecimal[].class) {
            if (this.decimalFormat != null) {
                return new ObjectWriterArrayFinal(BigDecimal.class, this.decimalFormat);
            }
            return ObjectWriterArrayFinal.DECIMAL_ARRAY;
        }
        return jSONWriter.getObjectWriter(cls);
    }

    public void writeListValueJSONB(JSONWriter jSONWriter, List list) {
        throw new UnsupportedOperationException();
    }

    public void writeListValue(JSONWriter jSONWriter, List list) {
        throw new UnsupportedOperationException();
    }

    public void writeListJSONB(JSONWriter jSONWriter, List list) {
        throw new UnsupportedOperationException();
    }

    public void writeList(JSONWriter jSONWriter, List list) {
        throw new UnsupportedOperationException();
    }

    public void writeListStr(JSONWriter jSONWriter, boolean z, List<String> list) {
        throw new UnsupportedOperationException();
    }

    static ObjectWriter getObjectWriter(Type type, Class cls, String str, Locale locale, Class cls2) {
        if (Map.class.isAssignableFrom(cls2)) {
            if (cls.isAssignableFrom(cls2)) {
                return ObjectWriterImplMap.of(type, str, cls2);
            }
            return ObjectWriterImplMap.of(cls2);
        }
        if (Calendar.class.isAssignableFrom(cls2)) {
            if (str == null || str.isEmpty()) {
                return ObjectWriterImplCalendar.INSTANCE;
            }
            return new ObjectWriterImplCalendar(str, locale);
        }
        if (ZonedDateTime.class.isAssignableFrom(cls2)) {
            if (str == null || str.isEmpty()) {
                return ObjectWriterImplZonedDateTime.INSTANCE;
            }
            return new ObjectWriterImplZonedDateTime(str, locale);
        }
        if (OffsetDateTime.class.isAssignableFrom(cls2)) {
            if (str == null || str.isEmpty()) {
                return ObjectWriterImplOffsetDateTime.INSTANCE;
            }
            return ObjectWriterImplOffsetDateTime.of(str, locale);
        }
        if (LocalDateTime.class.isAssignableFrom(cls2)) {
            ObjectWriter objectWriter = JSONFactory.getDefaultObjectWriterProvider().getObjectWriter(LocalDateTime.class);
            if (objectWriter != null && objectWriter != ObjectWriterImplLocalDateTime.INSTANCE) {
                return objectWriter;
            }
            if (str == null || str.isEmpty()) {
                return ObjectWriterImplLocalDateTime.INSTANCE;
            }
            return new ObjectWriterImplLocalDateTime(str, locale);
        }
        if (LocalDate.class.isAssignableFrom(cls2)) {
            ObjectWriter objectWriter2 = JSONFactory.getDefaultObjectWriterProvider().getObjectWriter(LocalDate.class);
            return (objectWriter2 == null || objectWriter2 == ObjectWriterImplLocalDate.INSTANCE) ? ObjectWriterImplLocalDate.of(str, locale) : objectWriter2;
        }
        if (LocalTime.class.isAssignableFrom(cls2)) {
            ObjectWriter objectWriter3 = JSONFactory.getDefaultObjectWriterProvider().getObjectWriter(LocalTime.class);
            if (objectWriter3 != null && objectWriter3 != ObjectWriterImplLocalTime.INSTANCE) {
                return objectWriter3;
            }
            if (str == null || str.isEmpty()) {
                return ObjectWriterImplLocalTime.INSTANCE;
            }
            return new ObjectWriterImplLocalTime(str, locale);
        }
        if (Instant.class == cls2) {
            if (str == null || str.isEmpty()) {
                return ObjectWriterImplInstant.INSTANCE;
            }
            return new ObjectWriterImplInstant(str, locale);
        }
        if (BigDecimal.class == cls2) {
            if (str == null || str.isEmpty()) {
                return ObjectWriterImplBigDecimal.INSTANCE;
            }
            return new ObjectWriterImplBigDecimal(new DecimalFormat(str), null);
        }
        if (BigDecimal[].class == cls2) {
            if (str == null || str.isEmpty()) {
                return new ObjectWriterArrayFinal(BigDecimal.class, null);
            }
            return new ObjectWriterArrayFinal(BigDecimal.class, new DecimalFormat(str));
        }
        if (Optional.class == cls2) {
            return ObjectWriterImplOptional.of(str, locale);
        }
        String name = cls2.getName();
        name.hashCode();
        switch (name) {
            case "org.joda.time.LocalDate":
                return JodaSupport.createLocalDateWriter(cls2, str);
            case "java.sql.Date":
                return new ObjectWriterImplDate(str, locale);
            case "java.sql.Time":
                return JdbcSupport.createTimeWriter(str);
            case "java.sql.Timestamp":
                return JdbcSupport.createTimestampWriter(cls2, str);
            case "org.joda.time.LocalDateTime":
                return JodaSupport.createLocalDateTimeWriter(cls2, str);
            default:
                return null;
        }
    }
}
