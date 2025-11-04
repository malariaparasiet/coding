package com.alibaba.fastjson2.internal.asm;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.alibaba.fastjson.parser.JSONLexer;
import kotlin.text.Typography;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes2.dex */
public final class Type {
    static final int ARRAY = 9;
    static final int BOOLEAN = 1;
    static final int BYTE = 3;
    static final int CHAR = 2;
    static final int DOUBLE = 8;
    static final Type DOUBLE_TYPE;
    static final int FLOAT = 6;
    static final int INT = 5;
    static final int INTERNAL = 12;
    static final int LONG = 7;
    static final Type LONG_TYPE;
    static final int METHOD = 11;
    static final int OBJECT = 10;
    static final int SHORT = 4;
    static final Type[] TYPES_0;
    static final Type[] TYPES_1;
    static final Type[] TYPES_2;
    static final Type[] TYPES_3;
    static final Type[] TYPES_4;
    static final Type TYPE_CLASS;
    static final Type TYPE_JSON_READER;
    static final Type TYPE_JSON_WRITER;
    static final Type TYPE_LIST;
    static final Type TYPE_OBJECT;
    static final Type TYPE_STRING;
    static final Type TYPE_SUPPLIER;
    static final Type TYPE_TYPE;
    static final int VOID = 0;
    final int sort;
    final int valueBegin;
    final String valueBuffer;
    final int valueEnd;
    static final Type VOID_TYPE = new Type(0, "VZCBSIFJD", 0, 1);
    static final Type BOOLEAN_TYPE = new Type(1, "VZCBSIFJD", 1, 2);
    static final Type CHAR_TYPE = new Type(2, "VZCBSIFJD", 2, 3);
    static final Type BYTE_TYPE = new Type(3, "VZCBSIFJD", 3, 4);
    static final Type SHORT_TYPE = new Type(4, "VZCBSIFJD", 4, 5);
    static final Type INT_TYPE = new Type(5, "VZCBSIFJD", 5, 6);
    static final Type FLOAT_TYPE = new Type(6, "VZCBSIFJD", 6, 7);

    static {
        Type type = new Type(7, "VZCBSIFJD", 7, 8);
        LONG_TYPE = type;
        DOUBLE_TYPE = new Type(8, "VZCBSIFJD", 8, 9);
        Type type2 = new Type(10, "Ljava/lang/Class;", 1, 16);
        TYPE_CLASS = type2;
        Type type3 = new Type(10, "Ljava/lang/reflect/Type;", 1, 23);
        TYPE_TYPE = type3;
        Type type4 = new Type(10, "Ljava/lang/Object;", 1, 17);
        TYPE_OBJECT = type4;
        Type type5 = new Type(10, "Ljava/lang/String;", 1, 17);
        TYPE_STRING = type5;
        Type type6 = new Type(10, "Ljava/util/List;", 1, 15);
        TYPE_LIST = type6;
        Type type7 = new Type(10, "Lcom/alibaba/fastjson2/JSONReader;", 1, 33);
        TYPE_JSON_READER = type7;
        Type type8 = new Type(10, "Lcom/alibaba/fastjson2/JSONWriter;", 1, 33);
        TYPE_JSON_WRITER = type8;
        Type type9 = new Type(10, ASMUtils.DESC_SUPPLIER, 1, 28);
        TYPE_SUPPLIER = type9;
        TYPES_0 = new Type[]{type2, type5, type5, type, type6};
        TYPES_1 = new Type[]{type8, type4, type4, type3, type};
        TYPES_2 = new Type[]{type2, type9, type7};
        TYPES_3 = new Type[]{type};
        TYPES_4 = new Type[]{type7, type3, type4, type};
    }

    private Type(int i, String str, int i2, int i3) {
        this.sort = i;
        this.valueBuffer = str;
        this.valueBegin = i2;
        this.valueEnd = i3;
    }

    static Type[] getArgumentTypes(String str) {
        int i;
        str.hashCode();
        i = 0;
        switch (str) {
            case "(Lcom/alibaba/fastjson2/JSONReader;Ljava/lang/reflect/Type;Ljava/lang/Object;J)Ljava/lang/Object;":
                return TYPES_4;
            case "(Lcom/alibaba/fastjson2/JSONWriter;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;J)V":
                return TYPES_1;
            case "(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;JLjava/util/List;)V":
                return TYPES_0;
            case "()V":
                return new Type[0];
            case "(J)Lcom/alibaba/fastjson2/reader/FieldReader;":
            case "(J)Ljava/lang/Object;":
                return TYPES_3;
            case "(Ljava/lang/Class;Ljava/util/function/Supplier;[Lcom/alibaba/fastjson2/reader/FieldReader;)V":
                return TYPES_2;
            default:
                int i2 = 0;
                int i3 = 1;
                while (str.charAt(i3) != ')') {
                    while (str.charAt(i3) == '[') {
                        i3++;
                    }
                    int i4 = i3 + 1;
                    i3 = str.charAt(i3) == 'L' ? Math.max(i4, str.indexOf(59, i4) + 1) : i4;
                    i2++;
                }
                Type[] typeArr = new Type[i2];
                int i5 = 1;
                while (str.charAt(i5) != ')') {
                    int i6 = i5;
                    while (str.charAt(i6) == '[') {
                        i6++;
                    }
                    int i7 = i6 + 1;
                    if (str.charAt(i6) == 'L') {
                        i7 = Math.max(i7, str.indexOf(59, i7) + 1);
                    }
                    typeArr[i] = getTypeInternal(str, i5, i7);
                    i++;
                    i5 = i7;
                }
                return typeArr;
        }
    }

    static Type getTypeInternal(String str, int i, int i2) {
        char charAt = str.charAt(i);
        if (charAt == '(') {
            return new Type(11, str, i, i2);
        }
        if (charAt == 'F') {
            return FLOAT_TYPE;
        }
        if (charAt != 'L') {
            if (charAt == 'S') {
                return SHORT_TYPE;
            }
            if (charAt == 'V') {
                return VOID_TYPE;
            }
            if (charAt == 'I') {
                return INT_TYPE;
            }
            if (charAt == 'J') {
                return LONG_TYPE;
            }
            if (charAt == 'Z') {
                return BOOLEAN_TYPE;
            }
            if (charAt != '[') {
                switch (charAt) {
                    case 'B':
                        return BYTE_TYPE;
                    case 'C':
                        return CHAR_TYPE;
                    case 'D':
                        return DOUBLE_TYPE;
                    default:
                        throw new IllegalArgumentException();
                }
            }
            return new Type(9, str, i, i2);
        }
        int i3 = i2 - i;
        if (i3 == 24) {
            Type type = TYPE_TYPE;
            if (str.regionMatches(i, type.valueBuffer, 0, i3)) {
                return type;
            }
        } else if (i3 == 29) {
            Type type2 = TYPE_SUPPLIER;
            if (str.regionMatches(i, type2.valueBuffer, 0, i3)) {
                return type2;
            }
        } else if (i3 != 34) {
            switch (i3) {
                case 16:
                    Type type3 = TYPE_LIST;
                    if (str.regionMatches(i, type3.valueBuffer, 0, i3)) {
                        return type3;
                    }
                    break;
                case 17:
                    Type type4 = TYPE_CLASS;
                    if (str.regionMatches(i, type4.valueBuffer, 0, i3)) {
                        return type4;
                    }
                    break;
                case 18:
                    Type type5 = TYPE_STRING;
                    if (str.regionMatches(i, type5.valueBuffer, 0, i3)) {
                        return type5;
                    }
                    Type type6 = TYPE_OBJECT;
                    if (str.regionMatches(i, type6.valueBuffer, 0, i3)) {
                        return type6;
                    }
                    break;
            }
        } else {
            Type type7 = TYPE_JSON_WRITER;
            if (str.regionMatches(i, type7.valueBuffer, 0, i3)) {
                return type7;
            }
            Type type8 = TYPE_JSON_READER;
            if (str.regionMatches(i, type8.valueBuffer, 0, i3)) {
                return type8;
            }
        }
        return new Type(10, str, i + 1, i2 - 1);
    }

    public String getDescriptor() {
        int i = this.sort;
        if (i != 10) {
            if (i == 12) {
                return "L" + this.valueBuffer.substring(this.valueBegin, this.valueEnd) + ';';
            }
            String str = this.valueBuffer;
            str.hashCode();
            if (str.equals("VZCBSIFJD")) {
                if (this.valueBegin == 7 && this.valueEnd == 8) {
                    return "J";
                }
            } else if (str.equals("(Ljava/lang/Class;Ljava/util/function/Supplier;[Lcom/alibaba/fastjson2/reader/FieldReader;)V") && this.valueBegin == 47 && this.valueEnd == 90) {
                return "[Lcom/alibaba/fastjson2/reader/FieldReader;";
            }
            return this.valueBuffer.substring(this.valueBegin, this.valueEnd);
        }
        String str2 = this.valueBuffer;
        str2.hashCode();
        switch (str2) {
            case "(Lcom/alibaba/fastjson2/JSONReader;Ljava/lang/reflect/Type;Ljava/lang/Object;J)Ljava/lang/Object;":
                int i2 = this.valueBegin;
                if (i2 == 2 && this.valueEnd == 34) {
                    return "Lcom/alibaba/fastjson2/JSONReader;";
                }
                if (i2 == 36 && this.valueEnd == 58) {
                    return "Ljava/lang/reflect/Type;";
                }
                if (i2 == 60 && this.valueEnd == 76) {
                    return "Ljava/lang/Object;";
                }
                break;
            case "(Lcom/alibaba/fastjson2/JSONWriter;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;J)V":
                int i3 = this.valueBegin;
                if (i3 == 2 && this.valueEnd == 34) {
                    return "Lcom/alibaba/fastjson2/JSONWriter;";
                }
                if (i3 == 36 && this.valueEnd == 52) {
                    return "Ljava/lang/Object;";
                }
                if (i3 == 54 && this.valueEnd == 70) {
                    return "Ljava/lang/Object;";
                }
                if (i3 == 72 && this.valueEnd == 94) {
                    return "Ljava/lang/reflect/Type;";
                }
                break;
            case "(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;JLjava/util/List;)V":
                int i4 = this.valueBegin;
                if (i4 == 2 && this.valueEnd == 17) {
                    return "Ljava/lang/Class;";
                }
                if (i4 == 19 && this.valueEnd == 35) {
                    return "Ljava/lang/String;";
                }
                if (i4 == 37 && this.valueEnd == 53) {
                    return "Ljava/lang/String;";
                }
                if (i4 == 56 && this.valueEnd == 70) {
                    return "Ljava/util/List;";
                }
                break;
            case "(Ljava/lang/Class;Ljava/util/function/Supplier;[Lcom/alibaba/fastjson2/reader/FieldReader;)V":
                int i5 = this.valueBegin;
                if (i5 == 2 && this.valueEnd == 17) {
                    return "Ljava/lang/Class;";
                }
                if (i5 == 19 && this.valueEnd == 46) {
                    return ASMUtils.DESC_SUPPLIER;
                }
                break;
        }
        if (this.valueBegin == 1 && this.valueEnd + 1 == this.valueBuffer.length()) {
            return this.valueBuffer;
        }
        return this.valueBuffer.substring(this.valueBegin - 1, this.valueEnd + 1);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static int getArgumentsAndReturnSizes(String str) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -2043530993:
                if (str.equals("(Ljava/lang/Enum;)V")) {
                    c = 0;
                    break;
                }
                break;
            case -1854475207:
                if (str.equals("(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;JLcom/alibaba/fastjson2/schema/JSONSchema;Ljava/util/function/Supplier;Ljava/util/function/Function;[Lcom/alibaba/fastjson2/reader/FieldReader;)V")) {
                    c = 1;
                    break;
                }
                break;
            case -1714881093:
                if (str.equals("(Lcom/alibaba/fastjson2/JSONReader;)Ljava/lang/Object;")) {
                    c = 2;
                    break;
                }
                break;
            case -1603304977:
                if (str.equals("(Ljava/lang/Object;JLjava/lang/Object;)V")) {
                    c = 3;
                    break;
                }
                break;
            case -1428966913:
                if (str.equals("(Ljava/lang/String;)V")) {
                    c = 4;
                    break;
                }
                break;
            case -1149691069:
                if (str.equals("(Lcom/alibaba/fastjson2/JSONReader;Ljava/lang/Class;J)Lcom/alibaba/fastjson2/reader/ObjectReader;")) {
                    c = 5;
                    break;
                }
                break;
            case -1010649456:
                if (str.equals("(Lcom/alibaba/fastjson2/JSONReader;Ljava/lang/reflect/Type;Ljava/lang/Object;J)Ljava/lang/Object;")) {
                    c = 6;
                    break;
                }
                break;
            case -585659236:
                if (str.equals("(Lcom/alibaba/fastjson2/JSONReader;)Lcom/alibaba/fastjson2/reader/ObjectReader;")) {
                    c = 7;
                    break;
                }
                break;
            case -470836938:
                if (str.equals("(Lcom/alibaba/fastjson2/JSONWriter;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/reflect/Type;J)V")) {
                    c = '\b';
                    break;
                }
                break;
            case -365204099:
                if (str.equals("(Lcom/alibaba/fastjson2/JSONReader;Ljava/lang/Object;Ljava/lang/String;)V")) {
                    c = '\t';
                    break;
                }
                break;
            case -263498853:
                if (str.equals("(Lcom/alibaba/fastjson2/JSONWriter;Ljava/lang/reflect/Type;)Lcom/alibaba/fastjson2/writer/ObjectWriter;")) {
                    c = '\n';
                    break;
                }
                break;
            case -221520171:
                if (str.equals("(Ljava/util/List;Ljava/lang/reflect/Type;)V")) {
                    c = 11;
                    break;
                }
                break;
            case -176321095:
                if (str.equals("(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;JLjava/util/List;)V")) {
                    c = '\f';
                    break;
                }
                break;
            case -126180830:
                if (str.equals("()Ljava/lang/Class;")) {
                    c = '\r';
                    break;
                }
                break;
            case -39224379:
                if (str.equals("(Lcom/alibaba/fastjson2/JSONReader;Ljava/util/List;ILjava/lang/String;)V")) {
                    c = 14;
                    break;
                }
                break;
            case 39784:
                if (str.equals("()I")) {
                    c = 15;
                    break;
                }
                break;
            case 39785:
                if (str.equals("()J")) {
                    c = 16;
                    break;
                }
                break;
            case 39797:
                if (str.equals("()V")) {
                    c = 17;
                    break;
                }
                break;
            case 39801:
                if (str.equals("()Z")) {
                    c = 18;
                    break;
                }
                break;
            case 1257388:
                if (str.equals("(C)Z")) {
                    c = 19;
                    break;
                }
                break;
            case 1263150:
                if (str.equals("(I)V")) {
                    c = 20;
                    break;
                }
                break;
            case 1264111:
                if (str.equals("(J)V")) {
                    c = 21;
                    break;
                }
                break;
            case 1264115:
                if (str.equals("(J)Z")) {
                    c = 22;
                    break;
                }
                break;
            case 149404307:
                if (str.equals("(Lcom/alibaba/fastjson2/writer/FieldWriter;Ljava/lang/Object;)Ljava/lang/String;")) {
                    c = 23;
                    break;
                }
                break;
            case 162211716:
                if (str.equals("(Ljava/lang/Object;Ljava/lang/reflect/Type;)Z")) {
                    c = 24;
                    break;
                }
                break;
            case 204540071:
                if (str.equals("(Lcom/alibaba/fastjson2/JSONWriter;)V")) {
                    c = 25;
                    break;
                }
                break;
            case 204540075:
                if (str.equals("(Lcom/alibaba/fastjson2/JSONWriter;)Z")) {
                    c = JSONLexer.EOI;
                    break;
                }
                break;
            case 390338868:
                if (str.equals("(Lcom/alibaba/fastjson2/JSONReader;Ljava/lang/Class;J)Ljava/lang/Object;")) {
                    c = 27;
                    break;
                }
                break;
            case 438407678:
                if (str.equals("(Lcom/alibaba/fastjson2/JSONWriter;ZLjava/util/List;)V")) {
                    c = 28;
                    break;
                }
                break;
            case 470778755:
                if (str.equals("(J)Lcom/alibaba/fastjson2/reader/FieldReader;")) {
                    c = 29;
                    break;
                }
                break;
            case 835331803:
                if (str.equals("(Ljava/lang/Class;Ljava/util/function/Supplier;[Lcom/alibaba/fastjson2/reader/FieldReader;)V")) {
                    c = 30;
                    break;
                }
                break;
            case 1014040867:
                if (str.equals("(J)Ljava/lang/Object;")) {
                    c = 31;
                    break;
                }
                break;
            case 1192622657:
                if (str.equals("(Lcom/alibaba/fastjson2/JSONWriter;Ljava/lang/Enum;)V")) {
                    c = ' ';
                    break;
                }
                break;
            case 1422865092:
                if (str.equals("(I)Ljava/lang/Object;")) {
                    c = '!';
                    break;
                }
                break;
            case 1546574451:
                if (str.equals("(Lcom/alibaba/fastjson2/JSONReader;Ljava/lang/Object;)V")) {
                    c = Typography.quote;
                    break;
                }
                break;
            case 1565685777:
                if (str.equals("(Ljava/lang/Object;)V")) {
                    c = '#';
                    break;
                }
                break;
            case 1565685781:
                if (str.equals("(Ljava/lang/Object;)Z")) {
                    c = Typography.dollar;
                    break;
                }
                break;
            case 1638725077:
                if (str.equals("(I)Ljava/lang/Integer;")) {
                    c = '%';
                    break;
                }
                break;
            case 1655445243:
                if (str.equals("(Lcom/alibaba/fastjson2/JSONWriter;Ljava/lang/Class;)Lcom/alibaba/fastjson2/writer/ObjectWriter;")) {
                    c = Typography.amp;
                    break;
                }
                break;
            case 1942950347:
                if (str.equals("()Ljava/lang/String;")) {
                    c = '\'';
                    break;
                }
                break;
            case 2045804348:
                if (str.equals("(Lcom/alibaba/fastjson2/JSONWriter;I)V")) {
                    c = '(';
                    break;
                }
                break;
            case 2045805309:
                if (str.equals("(Lcom/alibaba/fastjson2/JSONWriter;J)V")) {
                    c = ')';
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
            case 4:
            case 20:
            case 25:
            case '#':
                return 8;
            case 1:
                return 40;
            case 2:
            case 7:
            case 19:
            case 26:
            case '!':
            case '$':
            case '%':
                return 9;
            case 3:
            case 14:
                return 20;
            case 5:
            case 27:
                return 21;
            case 6:
                return 25;
            case '\b':
            case '\f':
                return 28;
            case '\t':
            case 28:
            case 30:
            case ')':
                return 16;
            case '\n':
            case 22:
            case 23:
            case 24:
            case 29:
            case 31:
            case '&':
                return 13;
            case 11:
            case 21:
            case ' ':
            case '\"':
            case '(':
                return 12;
            case '\r':
            case 15:
            case 18:
            case '\'':
                return 5;
            case 16:
                return 6;
            case 17:
                return 4;
            default:
                char charAt = str.charAt(1);
                int i = 1;
                int i2 = 1;
                while (charAt != ')') {
                    if (charAt == 'J' || charAt == 'D') {
                        i++;
                        i2 += 2;
                    } else {
                        while (str.charAt(i) == '[') {
                            i++;
                        }
                        int i3 = i + 1;
                        if (str.charAt(i) == 'L') {
                            i3 = Math.max(i3, str.indexOf(59, i3) + 1);
                        }
                        i2++;
                        i = i3;
                    }
                    charAt = str.charAt(i);
                }
                char charAt2 = str.charAt(i + 1);
                if (charAt2 == 'V') {
                    return i2 << 2;
                }
                return (i2 << 2) | ((charAt2 == 'J' || charAt2 == 'D') ? 2 : 1);
        }
    }

    public String getClassName() {
        switch (this.sort) {
            case 0:
                return "void";
            case 1:
                return TypedValues.Custom.S_BOOLEAN;
            case 2:
                return "char";
            case 3:
                return "byte";
            case 4:
                return "short";
            case 5:
                return "int";
            case 6:
                return TypedValues.Custom.S_FLOAT;
            case 7:
                return "long";
            case 8:
                return "double";
            case 9:
                StringBuilder sb = new StringBuilder(getTypeInternal(this.valueBuffer, this.valueBegin + getDimensions(), this.valueEnd).getClassName());
                for (int dimensions = getDimensions(); dimensions > 0; dimensions--) {
                    sb.append(_UrlKt.PATH_SEGMENT_ENCODE_SET_URI);
                }
                return sb.toString();
            case 10:
            case 12:
                return this.valueBuffer.substring(this.valueBegin, this.valueEnd).replace('/', '.');
            case 11:
            default:
                throw new AssertionError();
        }
    }

    public int getDimensions() {
        int i = 1;
        while (this.valueBuffer.charAt(this.valueBegin + i) == '[') {
            i++;
        }
        return i;
    }
}
