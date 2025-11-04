package com.alibaba.fastjson2;

import androidx.camera.video.AudioStats;
import com.alibaba.fastjson2.JSONB;
import com.alibaba.fastjson2.reader.ObjectReader;
import com.alibaba.fastjson2.util.IOUtils;
import com.alibaba.fastjson2.util.JDKUtils;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import kotlin.UByte;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes2.dex */
final class JSONBDump {
    static Charset GB18030;
    final byte[] bytes;
    final JSONWriter jsonWriter;
    String lastReference;
    int offset;
    final boolean raw;
    int strBegin;
    int strlen;
    byte strtype;
    final SymbolTable symbolTable;
    final Map<Integer, String> symbols;
    byte type;

    public JSONBDump(byte[] bArr, boolean z) {
        this.symbols = new HashMap();
        this.bytes = bArr;
        this.raw = z;
        this.jsonWriter = JSONWriter.ofPretty();
        this.symbolTable = null;
        dumpAny();
    }

    public JSONBDump(byte[] bArr, SymbolTable symbolTable, boolean z) {
        this.symbols = new HashMap();
        this.bytes = bArr;
        this.raw = z;
        this.symbolTable = symbolTable;
        this.jsonWriter = JSONWriter.ofPretty();
        dumpAny();
    }

    private void dumpAny() {
        int readInt32Value;
        BigInteger valueOf;
        BigDecimal bigDecimal;
        String str;
        String str2;
        int i = this.offset;
        byte[] bArr = this.bytes;
        if (i >= bArr.length) {
            return;
        }
        int i2 = i + 1;
        this.offset = i2;
        byte b = bArr[i];
        this.type = b;
        String str3 = null;
        if (b != -90) {
            if (b != 72) {
                if (b == -88) {
                    int i3 = i + 2;
                    this.offset = i3;
                    int i4 = bArr[i2] << 8;
                    int i5 = i + 3;
                    this.offset = i5;
                    int i6 = i4 + (bArr[i3] & UByte.MAX_VALUE);
                    int i7 = i + 4;
                    this.offset = i7;
                    byte b2 = bArr[i5];
                    int i8 = i + 5;
                    this.offset = i8;
                    byte b3 = bArr[i7];
                    int i9 = i + 6;
                    this.offset = i9;
                    byte b4 = bArr[i8];
                    int i10 = i + 7;
                    this.offset = i10;
                    byte b5 = bArr[i9];
                    this.offset = i + 8;
                    this.jsonWriter.writeLocalDateTime(LocalDateTime.of(i6, b2, b3, b4, b5, bArr[i10], readInt32Value()));
                    return;
                }
                if (b != -87) {
                    switch (b) {
                        case -112:
                            this.jsonWriter.writeChar((char) readInt32Value());
                            return;
                        case -111:
                            int readInt32Value2 = readInt32Value();
                            byte[] bArr2 = new byte[readInt32Value2];
                            System.arraycopy(this.bytes, this.offset, bArr2, 0, readInt32Value2);
                            this.offset += readInt32Value2;
                            this.jsonWriter.writeBinary(bArr2);
                            return;
                        case -110:
                            if (isInt()) {
                                readInt32Value = readInt32Value();
                            } else {
                                str3 = readString();
                                readInt32Value = readInt32Value();
                                this.symbols.put(Integer.valueOf(readInt32Value), str3);
                            }
                            if (!this.raw && this.bytes[this.offset] == -90) {
                                if (str3 == null) {
                                    str3 = getString(readInt32Value);
                                }
                                this.offset++;
                                dumpObject(str3);
                                return;
                            }
                            this.jsonWriter.startObject();
                            this.jsonWriter.writeName("@type");
                            this.jsonWriter.writeColon();
                            if (str3 == null) {
                                if (readInt32Value >= 0) {
                                    this.jsonWriter.writeString("#" + readInt32Value);
                                } else if (this.raw) {
                                    this.jsonWriter.writeString("#" + readInt32Value);
                                } else {
                                    this.jsonWriter.writeString(this.symbolTable.getName(-readInt32Value));
                                }
                            } else if (this.raw) {
                                this.jsonWriter.writeString(str3 + "#" + readInt32Value);
                            } else {
                                this.jsonWriter.writeString(str3);
                            }
                            this.jsonWriter.writeName(ObjectReader.VALUE_NAME);
                            this.jsonWriter.writeColon();
                            dumpAny();
                            this.jsonWriter.endObject();
                            return;
                        case -109:
                            dumpReference();
                            return;
                        default:
                            switch (b) {
                                case -85:
                                case -66:
                                    this.offset = i + 9;
                                    this.jsonWriter.writeInt64((bArr[i + 8] & 255) + ((bArr[i + 7] & 255) << 8) + ((bArr[i + 6] & 255) << 16) + ((bArr[i + 5] & 255) << 24) + ((bArr[i + 4] & 255) << 32) + ((bArr[i + 3] & 255) << 40) + ((bArr[i + 2] & 255) << 48) + (bArr[i2] << 56));
                                    return;
                                case -84:
                                case -83:
                                    break;
                                case -82:
                                    this.jsonWriter.writeInstant(Instant.ofEpochSecond(readInt64Value(), readInt32Value()));
                                    return;
                                case -81:
                                    this.jsonWriter.writeNull();
                                    return;
                                case -80:
                                    this.jsonWriter.writeBool(false);
                                    return;
                                case -79:
                                    this.jsonWriter.writeBool(true);
                                    return;
                                case -78:
                                    this.jsonWriter.writeDouble(AudioStats.AUDIO_AMPLITUDE_NONE);
                                    return;
                                case -77:
                                    this.jsonWriter.writeDouble(1.0d);
                                    return;
                                case -76:
                                    this.jsonWriter.writeDouble(readInt64Value());
                                    return;
                                case -75:
                                    this.offset = i + 9;
                                    this.jsonWriter.writeDouble(Double.longBitsToDouble((bArr[i + 8] & 255) + ((bArr[i + 7] & 255) << 8) + ((bArr[i + 6] & 255) << 16) + ((bArr[i + 5] & 255) << 24) + ((bArr[i + 4] & 255) << 32) + ((bArr[i + 3] & 255) << 40) + ((bArr[i + 2] & 255) << 48) + (bArr[i2] << 56)));
                                    return;
                                case -74:
                                    this.jsonWriter.writeFloat(readInt32Value());
                                    return;
                                case -73:
                                    int i11 = (bArr[i + 4] & UByte.MAX_VALUE) + ((bArr[i + 3] & UByte.MAX_VALUE) << 8) + ((bArr[i + 2] & UByte.MAX_VALUE) << 16) + (bArr[i2] << 24);
                                    this.offset = i + 5;
                                    this.jsonWriter.writeFloat(Float.intBitsToFloat(i11));
                                    return;
                                case -72:
                                    this.jsonWriter.writeDecimal(BigDecimal.valueOf(readInt64Value()), 0L, null);
                                    return;
                                case -71:
                                    int readInt32Value3 = readInt32Value();
                                    byte[] bArr3 = this.bytes;
                                    int i12 = this.offset;
                                    int i13 = i12 + 1;
                                    this.offset = i13;
                                    byte b6 = bArr3[i12];
                                    if (b6 == -70) {
                                        valueOf = BigInteger.valueOf(readInt64Value());
                                    } else if (b6 == -66) {
                                        valueOf = BigInteger.valueOf(IOUtils.getLongBE(bArr3, i13));
                                        this.offset += 8;
                                    } else if (b6 == 72) {
                                        valueOf = BigInteger.valueOf(readInt32Value());
                                    } else if (b6 >= -16 && b6 <= 47) {
                                        valueOf = BigInteger.valueOf(b6);
                                    } else if (b6 >= 48 && b6 <= 63) {
                                        int i14 = (b6 - JSONB.Constants.BC_INT32_BYTE_ZERO) << 8;
                                        this.offset = i12 + 2;
                                        valueOf = BigInteger.valueOf(i14 + (bArr3[i13] & UByte.MAX_VALUE));
                                    } else if (b6 >= 64 && b6 <= 71) {
                                        this.offset = i12 + 2;
                                        int i15 = ((b6 - 68) << 16) + ((bArr3[i13] & UByte.MAX_VALUE) << 8);
                                        this.offset = i12 + 3;
                                        valueOf = BigInteger.valueOf(i15 + (bArr3[r7] & UByte.MAX_VALUE));
                                    } else {
                                        int readInt32Value4 = readInt32Value();
                                        byte[] bArr4 = new byte[readInt32Value4];
                                        System.arraycopy(this.bytes, this.offset, bArr4, 0, readInt32Value4);
                                        this.offset += readInt32Value4;
                                        valueOf = new BigInteger(bArr4);
                                    }
                                    if (readInt32Value3 == 0) {
                                        bigDecimal = new BigDecimal(valueOf);
                                    } else {
                                        bigDecimal = new BigDecimal(valueOf, readInt32Value3);
                                    }
                                    this.jsonWriter.writeDecimal(bigDecimal, 0L, null);
                                    return;
                                case -70:
                                    this.jsonWriter.writeInt64(readInt64Value());
                                    return;
                                case -69:
                                    int readInt32Value5 = readInt32Value();
                                    byte[] bArr5 = new byte[readInt32Value5];
                                    System.arraycopy(this.bytes, this.offset, bArr5, 0, readInt32Value5);
                                    this.offset += readInt32Value5;
                                    this.jsonWriter.writeBigInt(new BigInteger(bArr5));
                                    return;
                                case -68:
                                    JSONWriter jSONWriter = this.jsonWriter;
                                    int i16 = i + 2;
                                    this.offset = i16;
                                    int i17 = bArr[i2] << 8;
                                    this.offset = i + 3;
                                    jSONWriter.writeInt16((short) (i17 + (bArr[i16] & UByte.MAX_VALUE)));
                                    return;
                                case -67:
                                    JSONWriter jSONWriter2 = this.jsonWriter;
                                    this.offset = i + 2;
                                    jSONWriter2.writeInt8(bArr[i2]);
                                    return;
                                case -65:
                                    int i18 = (bArr[i + 4] & UByte.MAX_VALUE) + ((bArr[i + 3] & UByte.MAX_VALUE) << 8) + ((bArr[i + 2] & UByte.MAX_VALUE) << 16) + (bArr[i2] << 24);
                                    this.offset = i + 5;
                                    this.jsonWriter.writeInt64(i18);
                                    return;
                                default:
                                    switch (b) {
                                        case 122:
                                            int readLength = readLength();
                                            String str4 = new String(this.bytes, this.offset, readLength, StandardCharsets.UTF_8);
                                            this.offset += readLength;
                                            this.jsonWriter.writeString(str4);
                                            return;
                                        case 123:
                                            int readLength2 = readLength();
                                            String str5 = new String(this.bytes, this.offset, readLength2, StandardCharsets.UTF_16);
                                            this.offset += readLength2;
                                            this.jsonWriter.writeString(str5);
                                            return;
                                        case 124:
                                            int readLength3 = readLength();
                                            if (JDKUtils.STRING_CREATOR_JDK11 != null && !JDKUtils.BIG_ENDIAN) {
                                                byte[] bArr6 = new byte[readLength3];
                                                System.arraycopy(this.bytes, this.offset, bArr6, 0, readLength3);
                                                str = JDKUtils.STRING_CREATOR_JDK11.apply(bArr6, JDKUtils.UTF16);
                                            } else {
                                                str = new String(this.bytes, this.offset, readLength3, StandardCharsets.UTF_16LE);
                                            }
                                            this.offset += readLength3;
                                            this.jsonWriter.writeString(str);
                                            return;
                                        case 125:
                                            int readLength4 = readLength();
                                            if (JDKUtils.STRING_CREATOR_JDK11 != null && JDKUtils.BIG_ENDIAN) {
                                                byte[] bArr7 = new byte[readLength4];
                                                System.arraycopy(this.bytes, this.offset, bArr7, 0, readLength4);
                                                str2 = JDKUtils.STRING_CREATOR_JDK11.apply(bArr7, JDKUtils.UTF16);
                                            } else {
                                                str2 = new String(this.bytes, this.offset, readLength4, StandardCharsets.UTF_16BE);
                                            }
                                            this.offset += readLength4;
                                            this.jsonWriter.writeString(str2);
                                            return;
                                        case 126:
                                            if (GB18030 == null) {
                                                GB18030 = Charset.forName("GB18030");
                                            }
                                            int readLength5 = readLength();
                                            String str6 = new String(this.bytes, this.offset, readLength5, GB18030);
                                            this.offset += readLength5;
                                            this.jsonWriter.writeString(str6);
                                            return;
                                        case Byte.MAX_VALUE:
                                            if (isInt()) {
                                                int readInt32Value6 = readInt32Value();
                                                if (this.raw) {
                                                    this.jsonWriter.writeString("#" + readInt32Value6);
                                                    return;
                                                } else {
                                                    this.jsonWriter.writeString(getString(readInt32Value6));
                                                    return;
                                                }
                                            }
                                            String readString = readString();
                                            int readInt32Value7 = readInt32Value();
                                            this.symbols.put(Integer.valueOf(readInt32Value7), readString);
                                            if (this.raw) {
                                                this.jsonWriter.writeString(readString + "#" + readInt32Value7);
                                                return;
                                            } else {
                                                this.jsonWriter.writeString(readString);
                                                return;
                                            }
                                        default:
                                            if (b >= -16 && b <= 47) {
                                                this.jsonWriter.writeInt32(b);
                                                return;
                                            }
                                            if (b >= -40 && b <= -17) {
                                                this.jsonWriter.writeInt64(b + 32);
                                                return;
                                            }
                                            if (b >= 48 && b <= 63) {
                                                int i19 = (b - JSONB.Constants.BC_INT32_BYTE_ZERO) << 8;
                                                this.offset = i + 2;
                                                this.jsonWriter.writeInt32(i19 + (bArr[i2] & UByte.MAX_VALUE));
                                                return;
                                            }
                                            if (b >= 64 && b <= 71) {
                                                int i20 = i + 2;
                                                this.offset = i20;
                                                int i21 = ((b - 68) << 16) + ((bArr[i2] & UByte.MAX_VALUE) << 8);
                                                this.offset = i + 3;
                                                this.jsonWriter.writeInt32(i21 + (bArr[i20] & UByte.MAX_VALUE));
                                                return;
                                            }
                                            if (b >= -56 && b <= -41) {
                                                int i22 = (b + JSONB.Constants.BC_INT32_BYTE_MIN) << 8;
                                                this.offset = i + 2;
                                                this.jsonWriter.writeInt32(i22 + (bArr[i2] & UByte.MAX_VALUE));
                                                return;
                                            }
                                            if (b >= -64 && b <= -57) {
                                                this.offset = i + 2;
                                                int i23 = ((b + 60) << 16) + ((bArr[i2] & UByte.MAX_VALUE) << 8);
                                                this.offset = i + 3;
                                                this.jsonWriter.writeInt64(i23 + (bArr[r5] & UByte.MAX_VALUE));
                                                return;
                                            }
                                            if (b >= -108 && b <= -92) {
                                                dumpArray();
                                                return;
                                            }
                                            if (b >= 73) {
                                                int readLength6 = b == 121 ? readLength() : b + JSONB.Constants.BC_FLOAT;
                                                this.strlen = readLength6;
                                                if (readLength6 < 0) {
                                                    this.jsonWriter.writeRaw("{\"$symbol\":");
                                                    this.jsonWriter.writeInt32(this.strlen);
                                                    this.jsonWriter.writeRaw("}");
                                                    return;
                                                } else {
                                                    String str7 = new String(this.bytes, this.offset, this.strlen, StandardCharsets.ISO_8859_1);
                                                    this.offset += this.strlen;
                                                    this.jsonWriter.writeString(str7);
                                                    return;
                                                }
                                            }
                                            throw new JSONException("not support type : " + JSONB.typeName(this.type) + ", offset " + this.offset);
                                    }
                            }
                    }
                } else {
                    int i24 = i + 2;
                    this.offset = i24;
                    int i25 = bArr[i2] << 8;
                    int i26 = i + 3;
                    this.offset = i26;
                    int i27 = i25 + (bArr[i24] & UByte.MAX_VALUE);
                    int i28 = i + 4;
                    this.offset = i28;
                    byte b7 = bArr[i26];
                    this.offset = i + 5;
                    this.jsonWriter.writeLocalDate(LocalDate.of(i27, b7, bArr[i28]));
                    return;
                }
            }
            int i29 = (bArr[i + 4] & UByte.MAX_VALUE) + ((bArr[i + 3] & UByte.MAX_VALUE) << 8) + ((bArr[i + 2] & UByte.MAX_VALUE) << 16) + (bArr[i2] << 24);
            this.offset = i + 5;
            this.jsonWriter.writeInt32(i29);
            return;
        }
        dumpObject(null);
    }

    private void dumpArray() {
        byte b;
        byte b2 = this.type;
        int readLength = b2 == -92 ? readLength() : b2 + 108;
        if (readLength == 0) {
            this.jsonWriter.writeRaw(_UrlKt.PATH_SEGMENT_ENCODE_SET_URI);
            return;
        }
        if (readLength == 1) {
            this.type = this.bytes[this.offset];
            if (isInt() || (b = this.type) == -81 || (b >= 73 && b <= 120)) {
                this.jsonWriter.writeRaw("[");
                dumpAny();
                this.jsonWriter.writeRaw("]");
                return;
            }
        }
        this.jsonWriter.startArray();
        for (int i = 0; i < readLength; i++) {
            if (i != 0) {
                this.jsonWriter.writeComma();
            }
            if (isReference()) {
                dumpReference();
            } else {
                dumpAny();
            }
        }
        this.jsonWriter.endArray();
    }

    private void dumpObject(String str) {
        if (str != null) {
            this.jsonWriter.startObject();
            this.jsonWriter.writeName("@type");
            this.jsonWriter.writeColon();
            this.jsonWriter.writeString(str);
        } else {
            if (this.bytes[this.offset] == -91) {
                this.jsonWriter.writeRaw("{}");
                this.offset++;
                return;
            }
            this.jsonWriter.startObject();
        }
        int i = 0;
        while (true) {
            byte[] bArr = this.bytes;
            int i2 = this.offset;
            byte b = bArr[i2];
            if (b == -109) {
                dumpReference();
            } else {
                if (b == -91) {
                    this.offset = i2 + 1;
                    this.jsonWriter.endObject();
                    return;
                }
                if (b == Byte.MAX_VALUE) {
                    this.offset = i2 + 1;
                    if (isInt()) {
                        int readInt32Value = readInt32Value();
                        if (this.raw) {
                            this.jsonWriter.writeName("#" + readInt32Value);
                        } else {
                            this.jsonWriter.writeName(getString(readInt32Value));
                        }
                    } else {
                        String readString = readString();
                        int readInt32Value2 = readInt32Value();
                        this.symbols.put(Integer.valueOf(readInt32Value2), readString);
                        if (this.raw) {
                            this.jsonWriter.writeName(readString + "#" + readInt32Value2);
                        } else {
                            this.jsonWriter.writeName(readString);
                        }
                    }
                } else if (isString()) {
                    this.jsonWriter.writeName(readString());
                } else if (b >= -16 && b <= 72) {
                    this.jsonWriter.writeName(readInt32Value());
                } else if ((b >= -40 && b <= -17) || b == -66) {
                    this.jsonWriter.writeName(readInt64Value());
                } else {
                    if (i != 0) {
                        this.jsonWriter.writeComma();
                    }
                    dumpAny();
                }
            }
            i++;
            this.jsonWriter.writeColon();
            if (isReference()) {
                dumpReference();
            } else {
                dumpAny();
            }
        }
    }

    private void dumpReference() {
        this.jsonWriter.writeRaw("{\"$ref\":");
        String readReference = readReference();
        this.jsonWriter.writeString(readReference);
        if (!"#-1".equals(readReference)) {
            this.lastReference = readReference;
        }
        this.jsonWriter.writeRaw("}");
    }

    int readInt32Value() {
        byte[] bArr = this.bytes;
        int i = this.offset;
        int i2 = i + 1;
        this.offset = i2;
        byte b = bArr[i];
        if (b >= -16 && b <= 47) {
            return b;
        }
        if (b >= 48 && b <= 63) {
            int i3 = (b + JSONB.Constants.BC_INT64_BYTE_MIN) << 8;
            this.offset = i + 2;
            return i3 + (bArr[i2] & UByte.MAX_VALUE);
        }
        if (b >= 64 && b <= 71) {
            int i4 = i + 2;
            this.offset = i4;
            int i5 = ((b - 68) << 16) + ((bArr[i2] & UByte.MAX_VALUE) << 8);
            this.offset = i + 3;
            return i5 + (bArr[i4] & UByte.MAX_VALUE);
        }
        if (b == -84 || b == -83 || b == 72) {
            int i6 = (bArr[i + 4] & UByte.MAX_VALUE) + ((bArr[i + 3] & UByte.MAX_VALUE) << 8) + ((bArr[i + 2] & UByte.MAX_VALUE) << 16) + (bArr[i2] << 24);
            this.offset = i + 5;
            return i6;
        }
        throw new JSONException("readInt32Value not support " + JSONB.typeName(b) + ", offset " + this.offset + "/" + this.bytes.length);
    }

    long readInt64Value() {
        byte[] bArr = this.bytes;
        int i = this.offset;
        int i2 = i + 1;
        this.offset = i2;
        byte b = bArr[i];
        if (b >= -16 && b <= 47) {
            return b;
        }
        if (b >= 48 && b <= 63) {
            int i3 = (b - JSONB.Constants.BC_INT32_BYTE_ZERO) << 8;
            this.offset = i + 2;
            return i3 + (bArr[i2] & UByte.MAX_VALUE);
        }
        if (b >= 64 && b <= 71) {
            this.offset = i + 2;
            int i4 = ((b - 68) << 16) + ((bArr[i2] & UByte.MAX_VALUE) << 8);
            this.offset = i + 3;
            return i4 + (bArr[r5] & UByte.MAX_VALUE);
        }
        if (b >= -40 && b <= -17) {
            return (b - JSONB.Constants.BC_INT64_NUM_MIN) - 8;
        }
        if (b >= -56 && b <= -41) {
            int i5 = (b + JSONB.Constants.BC_INT32_BYTE_MIN) << 8;
            this.offset = i + 2;
            return i5 + (bArr[i2] & UByte.MAX_VALUE);
        }
        if (b >= -64 && b <= -57) {
            this.offset = i + 2;
            int i6 = ((b + 60) << 16) + ((bArr[i2] & UByte.MAX_VALUE) << 8);
            this.offset = i + 3;
            return i6 + (bArr[r5] & UByte.MAX_VALUE);
        }
        if (b != -85) {
            if (b != 72) {
                switch (b) {
                    case -68:
                        int i7 = (bArr[i + 2] & UByte.MAX_VALUE) + (bArr[i2] << 8);
                        this.offset = i + 3;
                        return i7;
                    case -67:
                        this.offset = i + 2;
                        return bArr[i2];
                    case -66:
                        break;
                    case -65:
                        break;
                    default:
                        throw new JSONException("readInt64Value not support " + JSONB.typeName(b) + ", offset " + this.offset + "/" + this.bytes.length);
                }
            }
            int i8 = (bArr[i + 4] & UByte.MAX_VALUE) + ((bArr[i + 3] & UByte.MAX_VALUE) << 8) + ((bArr[i + 2] & UByte.MAX_VALUE) << 16) + (bArr[i2] << 24);
            this.offset = i + 5;
            return i8;
        }
        long j = (bArr[i + 8] & 255) + ((bArr[i + 7] & 255) << 8) + ((bArr[i + 6] & 255) << 16) + ((bArr[i + 5] & 255) << 24) + ((bArr[i + 4] & 255) << 32) + ((bArr[i + 3] & 255) << 40) + ((bArr[i + 2] & 255) << 48) + (bArr[i2] << 56);
        this.offset = i + 9;
        return j;
    }

    int readLength() {
        int i;
        byte b;
        byte[] bArr = this.bytes;
        int i2 = this.offset;
        int i3 = i2 + 1;
        this.offset = i3;
        byte b2 = bArr[i2];
        if (b2 >= -16 && b2 <= 47) {
            return b2;
        }
        if (b2 >= 64 && b2 <= 71) {
            int i4 = i2 + 2;
            this.offset = i4;
            i = ((b2 - 68) << 16) + ((bArr[i3] & UByte.MAX_VALUE) << 8);
            this.offset = i2 + 3;
            b = bArr[i4];
        } else {
            if (b2 < 48 || b2 > 63) {
                if (b2 == 72) {
                    int i5 = i2 + 2;
                    this.offset = i5;
                    int i6 = bArr[i3] << 24;
                    int i7 = i2 + 3;
                    this.offset = i7;
                    int i8 = i6 + ((bArr[i5] & UByte.MAX_VALUE) << 16);
                    int i9 = i2 + 4;
                    this.offset = i9;
                    int i10 = i8 + ((bArr[i7] & UByte.MAX_VALUE) << 8);
                    this.offset = i2 + 5;
                    return i10 + (bArr[i9] & UByte.MAX_VALUE);
                }
                throw new JSONException("not support length type : " + ((int) b2));
            }
            i = (b2 + JSONB.Constants.BC_INT64_BYTE_MIN) << 8;
            this.offset = i2 + 2;
            b = bArr[i3];
        }
        return i + (b & UByte.MAX_VALUE);
    }

    boolean isReference() {
        int i = this.offset;
        byte[] bArr = this.bytes;
        return i < bArr.length && bArr[i] == -109;
    }

    boolean isString() {
        byte b = this.bytes[this.offset];
        return b >= 73 && b <= 125;
    }

    String readString() {
        Charset charset;
        byte[] bArr = this.bytes;
        int i = this.offset;
        int i2 = i + 1;
        this.offset = i2;
        byte b = bArr[i];
        this.strtype = b;
        this.strBegin = i2;
        if (b >= 73 && b <= 121) {
            if (b == 121) {
                this.strlen = readLength();
                this.strBegin = this.offset;
            } else {
                this.strlen = b - 73;
            }
            charset = StandardCharsets.ISO_8859_1;
        } else if (b == 122) {
            this.strlen = readLength();
            this.strBegin = this.offset;
            charset = StandardCharsets.UTF_8;
        } else if (b == 123) {
            this.strlen = readLength();
            this.strBegin = this.offset;
            charset = StandardCharsets.UTF_16;
        } else if (b == 124) {
            this.strlen = readLength();
            this.strBegin = this.offset;
            if (JDKUtils.STRING_CREATOR_JDK11 != null && !JDKUtils.BIG_ENDIAN) {
                int i3 = this.strlen;
                byte[] bArr2 = new byte[i3];
                System.arraycopy(this.bytes, this.offset, bArr2, 0, i3);
                String apply = JDKUtils.STRING_CREATOR_JDK11.apply(bArr2, JDKUtils.UTF16);
                this.offset += this.strlen;
                return apply;
            }
            charset = StandardCharsets.UTF_16LE;
        } else if (b == 125) {
            this.strlen = readLength();
            this.strBegin = this.offset;
            if (JDKUtils.STRING_CREATOR_JDK11 != null && JDKUtils.BIG_ENDIAN) {
                int i4 = this.strlen;
                byte[] bArr3 = new byte[i4];
                System.arraycopy(this.bytes, this.offset, bArr3, 0, i4);
                String apply2 = JDKUtils.STRING_CREATOR_JDK11.apply(bArr3, JDKUtils.UTF16);
                this.offset += this.strlen;
                return apply2;
            }
            charset = StandardCharsets.UTF_16BE;
        } else {
            throw new JSONException("readString not support type " + JSONB.typeName(this.strtype) + ", offset " + this.offset + "/" + this.bytes.length);
        }
        int i5 = this.strlen;
        if (i5 < 0) {
            return this.symbolTable.getName(-i5);
        }
        String str = new String(this.bytes, this.offset, this.strlen, charset);
        this.offset += this.strlen;
        return str;
    }

    String readReference() {
        byte[] bArr = this.bytes;
        int i = this.offset;
        if (bArr[i] != -109) {
            return null;
        }
        this.offset = i + 1;
        if (isString()) {
            return readString();
        }
        throw new JSONException("reference not support input " + JSONB.typeName(this.type));
    }

    public String toString() {
        return this.jsonWriter.toString();
    }

    boolean isInt() {
        byte b = this.bytes[this.offset];
        return (b >= -70 && b <= 72) || b == -83 || b == -84 || b == -85;
    }

    public String getString(int i) {
        String str;
        if (i < 0) {
            str = this.symbolTable.getName(-i);
        } else {
            str = this.symbols.get(Integer.valueOf(i));
        }
        if (str != null) {
            return str;
        }
        throw new JSONException("symbol not found : " + i);
    }
}
