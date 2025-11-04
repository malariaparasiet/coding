package com.alibaba.fastjson2.writer;

import com.alibaba.fastjson2.JSONB;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.SymbolTable;
import com.alibaba.fastjson2.util.Fnv;
import com.alibaba.fastjson2.util.IOUtils;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import kotlin.text.Typography;

/* loaded from: classes2.dex */
class FieldWriterEnum extends FieldWriter {
    final Enum[] enumConstants;
    final Class enumType;
    final long[] hashCodes;
    final long[] hashCodesSymbolCache;
    final char[][] utf16ValueCache;
    final byte[][] utf8ValueCache;
    final char[][] valueNameCacheUTF16;
    final byte[][] valueNameCacheUTF8;

    protected FieldWriterEnum(String str, int i, long j, String str2, String str3, Type type, Class<? extends Enum> cls, Field field, Method method) {
        super(str, i, j, str2, null, str3, type, cls, field, method);
        this.enumType = cls;
        Enum[] enumArr = (Enum[]) cls.getEnumConstants();
        this.enumConstants = enumArr;
        this.hashCodes = new long[enumArr.length];
        this.hashCodesSymbolCache = new long[enumArr.length];
        int i2 = 0;
        while (true) {
            Enum[] enumArr2 = this.enumConstants;
            if (i2 < enumArr2.length) {
                this.hashCodes[i2] = Fnv.hashCode64(enumArr2[i2].name());
                i2++;
            } else {
                this.valueNameCacheUTF8 = new byte[enumArr2.length][];
                this.valueNameCacheUTF16 = new char[enumArr2.length][];
                this.utf8ValueCache = new byte[enumArr2.length][];
                this.utf16ValueCache = new char[enumArr2.length][];
                return;
            }
        }
    }

    @Override // com.alibaba.fastjson2.writer.FieldWriter
    public final int writeEnumValueJSONB(byte[] bArr, int i, Enum r8, SymbolTable symbolTable, long j) {
        if (r8 == null) {
            bArr[i] = JSONB.Constants.BC_NULL;
            return i + 1;
        }
        long j2 = j | this.features;
        boolean z = ((JSONWriter.Feature.WriteEnumUsingToString.mask | JSONWriter.Feature.WriteEnumsUsingName.mask) & j2) == 0;
        String str = (j2 & JSONWriter.Feature.WriteEnumUsingToString.mask) != 0 ? r8.toString() : r8.name();
        if (IOUtils.isASCII(str)) {
            return JSONB.IO.writeSymbol(bArr, i, str, symbolTable);
        }
        if (z) {
            return JSONB.IO.writeInt32(bArr, i, r8.ordinal());
        }
        return JSONB.IO.writeString(bArr, i, str);
    }

    @Override // com.alibaba.fastjson2.writer.FieldWriter
    public final void writeEnumJSONB(JSONWriter jSONWriter, Enum r11) {
        int i;
        if (r11 == null) {
            return;
        }
        long features = jSONWriter.getFeatures(this.features);
        boolean z = ((JSONWriter.Feature.WriteEnumUsingToString.mask | JSONWriter.Feature.WriteEnumsUsingName.mask) & features) == 0;
        boolean z2 = (features & JSONWriter.Feature.WriteEnumUsingToString.mask) != 0;
        int ordinal = r11.ordinal();
        SymbolTable symbolTable = jSONWriter.symbolTable;
        if (symbolTable == null || !z || z2 || !writeSymbolNameOrdinal(jSONWriter, ordinal, symbolTable)) {
            if (z2) {
                writeJSONBToString(jSONWriter, r11, symbolTable);
                return;
            }
            if (z) {
                if (symbolTable != null) {
                    int identityHashCode = System.identityHashCode(symbolTable);
                    if (this.nameSymbolCache == 0) {
                        i = symbolTable.getOrdinalByHashCode(this.hashCode);
                        this.nameSymbolCache = (i << 32) | identityHashCode;
                    } else if (((int) this.nameSymbolCache) == identityHashCode) {
                        i = (int) (this.nameSymbolCache >> 32);
                    } else {
                        i = symbolTable.getOrdinalByHashCode(this.hashCode);
                        this.nameSymbolCache = (i << 32) | identityHashCode;
                    }
                } else {
                    i = -1;
                }
                if (i != -1) {
                    jSONWriter.writeSymbol(-i);
                } else {
                    jSONWriter.writeNameRaw(this.nameJSONB, this.hashCode);
                }
                jSONWriter.writeInt32(ordinal);
                return;
            }
            writeFieldName(jSONWriter);
            jSONWriter.writeString(r11.name());
        }
    }

    private boolean writeSymbolNameOrdinal(JSONWriter jSONWriter, int i, SymbolTable symbolTable) {
        int ordinalByHashCode;
        int ordinalByHashCode2;
        int identityHashCode = System.identityHashCode(symbolTable);
        long j = this.hashCodesSymbolCache[i];
        if (j == 0) {
            ordinalByHashCode = symbolTable.getOrdinalByHashCode(this.hashCodes[i]);
            this.hashCodesSymbolCache[i] = (ordinalByHashCode << 32) | identityHashCode;
        } else if (((int) j) == identityHashCode) {
            ordinalByHashCode = (int) (j >> 32);
        } else {
            ordinalByHashCode = symbolTable.getOrdinalByHashCode(this.hashCodes[i]);
            this.hashCodesSymbolCache[i] = (ordinalByHashCode << 32) | identityHashCode;
        }
        if (ordinalByHashCode < 0) {
            return false;
        }
        if (this.nameSymbolCache == 0) {
            ordinalByHashCode2 = symbolTable.getOrdinalByHashCode(this.hashCode);
            if (ordinalByHashCode2 != -1) {
                this.nameSymbolCache = (ordinalByHashCode2 << 32) | identityHashCode;
            }
        } else if (((int) this.nameSymbolCache) == identityHashCode) {
            ordinalByHashCode2 = (int) (this.nameSymbolCache >> 32);
        } else {
            ordinalByHashCode2 = symbolTable.getOrdinalByHashCode(this.hashCode);
            this.nameSymbolCache = (ordinalByHashCode2 << 32) | identityHashCode;
        }
        if (ordinalByHashCode2 != -1) {
            jSONWriter.writeSymbol(-ordinalByHashCode2);
        } else {
            jSONWriter.writeNameRaw(this.nameJSONB, this.hashCode);
        }
        jSONWriter.writeRaw(JSONB.Constants.BC_STR_ASCII);
        jSONWriter.writeInt32(-ordinalByHashCode);
        return true;
    }

    private void writeJSONBToString(JSONWriter jSONWriter, Enum r8, SymbolTable symbolTable) {
        int i;
        if (symbolTable != null) {
            int identityHashCode = System.identityHashCode(symbolTable);
            if (this.nameSymbolCache == 0) {
                i = symbolTable.getOrdinalByHashCode(this.hashCode);
                this.nameSymbolCache = (i << 32) | identityHashCode;
            } else if (((int) this.nameSymbolCache) == identityHashCode) {
                i = (int) (this.nameSymbolCache >> 32);
            } else {
                i = symbolTable.getOrdinalByHashCode(this.hashCode);
                this.nameSymbolCache = (i << 32) | identityHashCode;
            }
        } else {
            i = -1;
        }
        if (i != -1) {
            jSONWriter.writeSymbol(-i);
        } else {
            jSONWriter.writeNameRaw(this.nameJSONB, this.hashCode);
        }
        jSONWriter.writeString(r8.toString());
    }

    @Override // com.alibaba.fastjson2.writer.FieldWriter
    public final void writeEnum(JSONWriter jSONWriter, Enum r10) {
        long features = jSONWriter.getFeatures(this.features);
        if ((JSONWriter.Feature.WriteEnumUsingToString.mask & features) == 0) {
            if (jSONWriter.jsonb) {
                writeEnumJSONB(jSONWriter, r10);
                return;
            }
            int ordinal = r10.ordinal();
            if ((JSONWriter.Feature.WriteEnumUsingOrdinal.mask & features) != 0) {
                writeEnumUsingOrdinal(jSONWriter, ordinal);
                return;
            }
            if ((features & JSONWriter.Feature.UnquoteFieldName.mask) == 0) {
                if (jSONWriter.utf8) {
                    byte[][] bArr = this.valueNameCacheUTF8;
                    byte[] bArr2 = bArr[ordinal];
                    if (bArr2 == null) {
                        bArr2 = getNameBytes(ordinal);
                        bArr[ordinal] = bArr2;
                    }
                    jSONWriter.writeNameRaw(bArr2);
                    return;
                }
                if (jSONWriter.utf16) {
                    char[][] cArr = this.valueNameCacheUTF16;
                    char[] cArr2 = cArr[ordinal];
                    if (cArr2 == null) {
                        cArr2 = getNameChars(ordinal);
                        cArr[ordinal] = cArr2;
                    }
                    jSONWriter.writeNameRaw(cArr2);
                    return;
                }
            }
        }
        writeFieldName(jSONWriter);
        jSONWriter.writeString(r10.toString());
    }

    private void writeEnumUsingOrdinal(JSONWriter jSONWriter, int i) {
        if ((this.features & JSONWriter.Feature.UnquoteFieldName.mask) == 0) {
            if (jSONWriter.utf8) {
                byte[][] bArr = this.utf8ValueCache;
                byte[] bArr2 = bArr[i];
                if (bArr2 == null) {
                    bArr2 = getBytes(i);
                    bArr[i] = bArr2;
                }
                jSONWriter.writeNameRaw(bArr2);
                return;
            }
            if (jSONWriter.utf16) {
                char[][] cArr = this.utf16ValueCache;
                char[] cArr2 = cArr[i];
                if (cArr2 == null) {
                    cArr2 = getChars(i);
                    cArr[i] = cArr2;
                }
                jSONWriter.writeNameRaw(cArr2);
                return;
            }
        }
        writeFieldName(jSONWriter);
        jSONWriter.writeInt32(i);
    }

    private char[] getNameChars(int i) {
        String name = this.enumConstants[i].name();
        char[] copyOf = Arrays.copyOf(this.nameWithColonUTF16, this.nameWithColonUTF16.length + name.length() + 2);
        copyOf[this.nameWithColonUTF16.length] = Typography.quote;
        name.getChars(0, name.length(), copyOf, this.nameWithColonUTF16.length + 1);
        copyOf[copyOf.length - 1] = Typography.quote;
        return copyOf;
    }

    private byte[] getNameBytes(int i) {
        byte[] bytes = this.enumConstants[i].name().getBytes(StandardCharsets.UTF_8);
        byte[] copyOf = Arrays.copyOf(this.nameWithColonUTF8, this.nameWithColonUTF8.length + bytes.length + 2);
        copyOf[this.nameWithColonUTF8.length] = 34;
        int length = this.nameWithColonUTF8.length + 1;
        int length2 = bytes.length;
        int i2 = 0;
        while (i2 < length2) {
            copyOf[length] = bytes[i2];
            i2++;
            length++;
        }
        copyOf[copyOf.length - 1] = 34;
        return copyOf;
    }

    private char[] getChars(int i) {
        char[] copyOf = Arrays.copyOf(this.nameWithColonUTF16, this.nameWithColonUTF16.length + IOUtils.stringSize(i));
        char[] copyOf2 = Arrays.copyOf(copyOf, copyOf.length);
        IOUtils.getChars(i, copyOf2.length, copyOf2);
        return copyOf2;
    }

    private byte[] getBytes(int i) {
        byte[] copyOf = Arrays.copyOf(this.nameWithColonUTF8, this.nameWithColonUTF8.length + IOUtils.stringSize(i));
        byte[] copyOf2 = Arrays.copyOf(copyOf, copyOf.length);
        IOUtils.getChars(i, copyOf2.length, copyOf2);
        return copyOf2;
    }

    @Override // com.alibaba.fastjson2.writer.FieldWriter
    public final void writeValue(JSONWriter jSONWriter, Object obj) {
        jSONWriter.writeEnum((Enum) getFieldValue(obj));
    }

    @Override // com.alibaba.fastjson2.writer.FieldWriter
    public boolean write(JSONWriter jSONWriter, Object obj) {
        Enum r7 = (Enum) getFieldValue(obj);
        if (r7 != null) {
            if (jSONWriter.jsonb) {
                writeEnumJSONB(jSONWriter, r7);
            } else {
                writeEnum(jSONWriter, r7);
            }
            return true;
        }
        if (((this.features | jSONWriter.getFeatures()) & JSONWriter.Feature.WriteNulls.mask) == 0) {
            return false;
        }
        writeFieldName(jSONWriter);
        jSONWriter.writeNull();
        return true;
    }
}
