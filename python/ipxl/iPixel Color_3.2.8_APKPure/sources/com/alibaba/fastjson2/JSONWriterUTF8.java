package com.alibaba.fastjson2;

import androidx.core.view.InputDeviceCompat;
import com.alibaba.fastjson2.JSONB;
import com.alibaba.fastjson2.JSONFactory;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.util.IOUtils;
import com.alibaba.fastjson2.util.JDKUtils;
import com.alibaba.fastjson2.util.NumberUtils;
import com.alibaba.fastjson2.util.StringUtils;
import com.alibaba.fastjson2.util.TypeUtils;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import kotlin.UByte;
import kotlin.io.encoding.Base64;
import org.bouncycastle.asn1.BERTags;

/* loaded from: classes2.dex */
final class JSONWriterUTF8 extends JSONWriter {
    static final short QUOTE2_COLON;
    static final short QUOTE_COLON;
    static final long REF;
    private final long byteVectorQuote;
    byte[] bytes;
    final JSONFactory.CacheItem cacheItem;

    private static long expand(long j) {
        long j2 = (j & (-281470681743361L)) | ((j << 16) & 281470681743360L);
        long j3 = (j2 & (-71776119077928961L)) | ((j2 << 8) & 71776119077928960L);
        return ((j3 & (-1080880403494997761L)) | ((j3 << 4) & 1080880403494997760L)) & 1085102592571150095L;
    }

    static {
        byte[] bArr = {JSONB.Constants.BC_STR_UTF16, 34, 36, 114, 101, 102, 34, 58};
        REF = JDKUtils.UNSAFE.getLong(bArr, JDKUtils.ARRAY_CHAR_BASE_OFFSET);
        QUOTE2_COLON = JDKUtils.UNSAFE.getShort(bArr, JDKUtils.ARRAY_CHAR_BASE_OFFSET + 6);
        bArr[6] = 39;
        QUOTE_COLON = JDKUtils.UNSAFE.getShort(bArr, JDKUtils.ARRAY_CHAR_BASE_OFFSET + 6);
    }

    JSONWriterUTF8(JSONWriter.Context context) {
        super(context, null, false, StandardCharsets.UTF_8);
        JSONFactory.CacheItem cacheItem = JSONFactory.CACHE_ITEMS[System.identityHashCode(Thread.currentThread()) & (JSONFactory.CACHE_ITEMS.length - 1)];
        this.cacheItem = cacheItem;
        byte[] andSet = JSONFactory.BYTES_UPDATER.getAndSet(cacheItem, null);
        this.bytes = andSet == null ? new byte[8192] : andSet;
        this.byteVectorQuote = this.useSingleQuote ? -2821266740684990248L : -2459565876494606883L;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeNull() {
        int i = this.off;
        byte[] bArr = this.bytes;
        int i2 = i + 4;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        IOUtils.putNULL(bArr, i);
        this.off = i2;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeReference(String str) {
        this.lastReference = str;
        int i = this.off;
        byte[] bArr = this.bytes;
        int i2 = i + 8;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        JDKUtils.UNSAFE.putLong(bArr, JDKUtils.ARRAY_BYTE_BASE_OFFSET + i, REF);
        this.off = i2;
        writeString(str);
        writeRaw(JSONB.Constants.BC_STR_UTF16BE);
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeBase64(byte[] bArr) {
        int i = this.off;
        int length = ((((bArr.length - 1) / 3) + 1) << 2) + i + 2;
        byte[] bArr2 = this.bytes;
        if (length > bArr2.length) {
            bArr2 = grow(length);
        }
        int i2 = i + 1;
        bArr2[i] = (byte) this.quote;
        int length2 = (bArr.length / 3) * 3;
        int i3 = 0;
        while (i3 < length2) {
            int i4 = i3 + 2;
            int i5 = ((bArr[i3 + 1] & UByte.MAX_VALUE) << 8) | ((bArr[i3] & UByte.MAX_VALUE) << 16);
            i3 += 3;
            int i6 = i5 | (bArr[i4] & UByte.MAX_VALUE);
            bArr2[i2] = (byte) JSONFactory.CA[(i6 >>> 18) & 63];
            bArr2[i2 + 1] = (byte) JSONFactory.CA[(i6 >>> 12) & 63];
            bArr2[i2 + 2] = (byte) JSONFactory.CA[(i6 >>> 6) & 63];
            bArr2[i2 + 3] = (byte) JSONFactory.CA[i6 & 63];
            i2 += 4;
        }
        int length3 = bArr.length - length2;
        if (length3 > 0) {
            int i7 = ((bArr[length2] & UByte.MAX_VALUE) << 10) | (length3 == 2 ? (bArr[bArr.length - 1] & UByte.MAX_VALUE) << 2 : 0);
            bArr2[i2] = (byte) JSONFactory.CA[i7 >> 12];
            bArr2[i2 + 1] = (byte) JSONFactory.CA[(i7 >>> 6) & 63];
            bArr2[i2 + 2] = length3 == 2 ? (byte) JSONFactory.CA[i7 & 63] : (byte) 61;
            bArr2[i2 + 3] = Base64.padSymbol;
            i2 += 4;
        }
        bArr2[i2] = (byte) this.quote;
        this.off = i2 + 1;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeHex(byte[] bArr) {
        if (bArr == null) {
            writeNull();
            return;
        }
        int length = (bArr.length * 2) + 3;
        int i = this.off;
        int i2 = length + i + 2;
        byte[] bArr2 = this.bytes;
        if (i2 > bArr2.length) {
            bArr2 = grow(i2);
        }
        IOUtils.putShortLE(bArr2, i, (short) 10104);
        int i3 = i + 2;
        for (byte b : bArr) {
            IOUtils.putShortLE(bArr2, i3, IOUtils.hex2U(b));
            i3 += 2;
        }
        bArr2[i3] = 39;
        this.off = i3 + 1;
    }

    @Override // com.alibaba.fastjson2.JSONWriter, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        byte[] bArr = this.bytes;
        if (bArr.length > 8388608) {
            return;
        }
        JSONFactory.BYTES_UPDATER.lazySet(this.cacheItem, bArr);
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final int size() {
        return this.off;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final byte[] getBytes() {
        return Arrays.copyOf(this.bytes, this.off);
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final byte[] getBytes(Charset charset) {
        if (charset == StandardCharsets.UTF_8) {
            return Arrays.copyOf(this.bytes, this.off);
        }
        return toString().getBytes(charset);
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final int flushTo(OutputStream outputStream) throws IOException {
        int i = this.off;
        if (i > 0) {
            outputStream.write(this.bytes, 0, i);
            this.off = 0;
        }
        return i;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    protected final void write0(char c) {
        int i = this.off;
        if (i == this.bytes.length) {
            grow0(i + 1);
        }
        this.bytes[i] = (byte) c;
        this.off = i + 1;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeColon() {
        int i = this.off;
        grow1(i)[i] = 58;
        this.off = i + 1;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void startObject() {
        int i = this.level + 1;
        this.level = i;
        if (i > this.context.maxLevel) {
            overflowLevel();
        }
        this.startObject = true;
        int i2 = this.off;
        int i3 = i2 + 3 + (this.pretty * this.level);
        byte[] bArr = this.bytes;
        if (i3 > bArr.length) {
            bArr = grow(i3);
        }
        int i4 = i2 + 1;
        bArr[i2] = JSONB.Constants.BC_STR_UTF16;
        if (this.pretty != 0) {
            i4 = indent(bArr, i4);
        }
        this.off = i4;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void endObject() {
        this.level--;
        int i = this.off;
        int i2 = i + 1 + (this.pretty == 0 ? 0 : (this.pretty * this.level) + 1);
        byte[] bArr = this.bytes;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        if (this.pretty != 0) {
            i = indent(bArr, i);
        }
        bArr[i] = JSONB.Constants.BC_STR_UTF16BE;
        this.off = i + 1;
        this.startObject = false;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeComma() {
        this.startObject = false;
        int i = this.off;
        int i2 = i + 2 + (this.pretty * this.level);
        byte[] bArr = this.bytes;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        int i3 = i + 1;
        bArr[i] = 44;
        if (this.pretty != 0) {
            i3 = indent(bArr, i3);
        }
        this.off = i3;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void startArray() {
        int i = this.level + 1;
        this.level = i;
        if (i > this.context.maxLevel) {
            overflowLevel();
        }
        int i2 = this.off;
        int i3 = i2 + 3 + (this.pretty * this.level);
        byte[] bArr = this.bytes;
        if (i3 > bArr.length) {
            bArr = grow(i3);
        }
        int i4 = i2 + 1;
        bArr[i2] = 91;
        if (this.pretty != 0) {
            i4 = indent(bArr, i4);
        }
        this.off = i4;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void endArray() {
        this.level--;
        int i = this.off;
        int i2 = i + 1 + (this.pretty == 0 ? 0 : (this.pretty * this.level) + 1);
        byte[] bArr = this.bytes;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        if (this.pretty != 0) {
            i = indent(bArr, i);
        }
        bArr[i] = 93;
        this.off = i + 1;
        this.startObject = false;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeString(String[] strArr) {
        if (this.pretty != 0 || strArr == null) {
            super.writeString(strArr);
            return;
        }
        int i = this.off;
        grow1(i)[i] = 91;
        this.off = i + 1;
        for (int i2 = 0; i2 < strArr.length; i2++) {
            if (i2 != 0) {
                int i3 = this.off;
                grow1(i3)[i3] = 44;
                this.off = i3 + 1;
            }
            writeString(strArr[i2]);
        }
        int i4 = this.off;
        grow1(i4)[i4] = 93;
        this.off = i4 + 1;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeString(List<String> list) {
        if (this.pretty != 0) {
            super.writeString(list);
            return;
        }
        int i = this.off;
        grow1(i)[i] = 91;
        this.off = i + 1;
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (i2 != 0) {
                int i3 = this.off;
                grow1(i3)[i3] = 44;
                this.off = i3 + 1;
            }
            String str = list.get(i2);
            if (str == null) {
                writeStringNull();
            } else if (JDKUtils.STRING_VALUE != null) {
                byte[] apply = JDKUtils.STRING_VALUE.apply(str);
                if (JDKUtils.STRING_CODER.applyAsInt(str) == 0) {
                    writeStringLatin1(apply);
                } else {
                    writeStringUTF16(apply);
                }
            } else {
                writeStringJDK8(str);
            }
        }
        int i4 = this.off;
        grow1(i4)[i4] = 93;
        this.off = i4 + 1;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeString(boolean z) {
        byte b = (byte) this.quote;
        byte[] bArr = this.bytes;
        int i = this.off;
        this.off = i + 1;
        bArr[i] = b;
        writeBool(z);
        byte[] bArr2 = this.bytes;
        int i2 = this.off;
        this.off = i2 + 1;
        bArr2[i2] = b;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeString(byte b) {
        boolean z = (this.context.features & JSONWriter.Feature.WriteNonStringValueAsString.mask) == 0;
        if (z) {
            writeQuote();
        }
        writeInt8(b);
        if (z) {
            writeQuote();
        }
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeString(short s) {
        boolean z = (this.context.features & JSONWriter.Feature.WriteNonStringValueAsString.mask) == 0;
        if (z) {
            writeQuote();
        }
        writeInt16(s);
        if (z) {
            writeQuote();
        }
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeString(int i) {
        boolean z = (this.context.features & JSONWriter.Feature.WriteNonStringValueAsString.mask) == 0;
        if (z) {
            writeQuote();
        }
        writeInt32(i);
        if (z) {
            writeQuote();
        }
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeString(long j) {
        boolean z = (this.context.features & (JSONWriter.Feature.WriteNonStringValueAsString.mask | JSONWriter.Feature.WriteLongAsString.mask)) == 0;
        if (z) {
            writeQuote();
        }
        writeInt64(j);
        if (z) {
            writeQuote();
        }
    }

    private void writeQuote() {
        writeRaw((byte) this.quote);
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeString(String str) {
        if (str == null) {
            writeStringNull();
            return;
        }
        if (JDKUtils.STRING_VALUE != null) {
            byte[] apply = JDKUtils.STRING_VALUE.apply(str);
            if (JDKUtils.STRING_CODER.applyAsInt(str) == 0) {
                writeStringLatin1(apply);
                return;
            } else {
                writeStringUTF16(apply);
                return;
            }
        }
        writeStringJDK8(str);
    }

    private void writeStringJDK8(String str) {
        JSONWriterUTF8 jSONWriterUTF8;
        char c;
        char[] charArray = JDKUtils.getCharArray(str);
        boolean z = (this.context.features & JSONWriter.Feature.BrowserSecure.mask) != 0;
        boolean z2 = (this.context.features & JSONWriter.Feature.EscapeNoneAscii.mask) != 0;
        int i = this.off;
        int length = (charArray.length * 3) + i + 2;
        if (z2 || z) {
            length += charArray.length * 3;
        }
        byte[] bArr = this.bytes;
        if (length > bArr.length) {
            bArr = grow(length);
        }
        bArr[i] = (byte) this.quote;
        int i2 = i + 1;
        int i3 = 0;
        while (i3 < charArray.length && (c = charArray[i3]) != this.quote && c != '\\' && c >= ' ' && c <= 127 && (!z || (c != '<' && c != '>' && c != '(' && c != ')'))) {
            bArr[i2] = (byte) c;
            i3++;
            i2++;
        }
        if (i3 == charArray.length) {
            bArr[i2] = (byte) this.quote;
            this.off = i2 + 1;
            return;
        }
        this.off = i2;
        if (i3 < charArray.length) {
            jSONWriterUTF8 = this;
            jSONWriterUTF8.writeStringEscapedRest(charArray, charArray.length, z, z2, i3);
        } else {
            jSONWriterUTF8 = this;
        }
        byte[] bArr2 = jSONWriterUTF8.bytes;
        int i4 = jSONWriterUTF8.off;
        jSONWriterUTF8.off = i4 + 1;
        bArr2[i4] = (byte) jSONWriterUTF8.quote;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeStringLatin1(byte[] bArr) {
        if ((this.context.features & 34359738368L) != 0) {
            writeStringLatin1BrowserSecure(bArr);
            return;
        }
        byte b = (byte) this.quote;
        if (StringUtils.escaped(bArr, b, this.byteVectorQuote)) {
            writeStringEscaped(bArr);
            return;
        }
        int i = this.off;
        int length = bArr.length + i + 2;
        byte[] bArr2 = this.bytes;
        if (length > bArr2.length) {
            bArr2 = grow(length);
        }
        this.off = StringUtils.writeLatin1(bArr2, i, bArr, b);
    }

    protected final void writeStringLatin1BrowserSecure(byte[] bArr) {
        byte b;
        byte b2 = (byte) this.quote;
        int i = 0;
        while (i < bArr.length && (b = bArr[i]) != b2 && b != 92 && b >= 32 && b != 60 && b != 62 && b != 40 && b != 41) {
            i++;
        }
        int i2 = this.off;
        if (i == bArr.length) {
            int length = bArr.length + i2 + 2;
            byte[] bArr2 = this.bytes;
            if (length > bArr2.length) {
                bArr2 = grow(length);
            }
            bArr2[i2] = b2;
            System.arraycopy(bArr, 0, bArr2, i2 + 1, bArr.length);
            int length2 = i2 + bArr.length + 1;
            bArr2[length2] = b2;
            this.off = length2 + 1;
            return;
        }
        writeStringEscaped(bArr);
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeStringUTF16(byte[] bArr) {
        if (bArr == null) {
            writeStringNull();
            return;
        }
        int i = this.off;
        byte[] bArr2 = this.bytes;
        int length = (bArr.length * 6) + i + 2;
        if (length > bArr2.length) {
            bArr2 = grow(length);
        }
        this.off = StringUtils.writeUTF16(bArr2, i, bArr, (byte) this.quote, this.context.features);
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeString(char[] cArr) {
        JSONWriterUTF8 jSONWriterUTF8;
        char c;
        if (cArr == null) {
            writeStringNull();
            return;
        }
        boolean z = (this.context.features & JSONWriter.Feature.BrowserSecure.mask) != 0;
        boolean z2 = (this.context.features & JSONWriter.Feature.EscapeNoneAscii.mask) != 0;
        int i = this.off;
        int length = (cArr.length * 3) + i + 2;
        if (z2 || z) {
            length += cArr.length * 3;
        }
        byte[] bArr = this.bytes;
        if (length > bArr.length) {
            bArr = grow(length);
        }
        int i2 = i + 1;
        bArr[i] = (byte) this.quote;
        int i3 = 0;
        while (i3 < cArr.length && (c = cArr[i3]) != this.quote && c != '\\' && c >= ' ' && c <= 127 && (!z || (c != '<' && c != '>' && c != '(' && c != ')'))) {
            bArr[i2] = (byte) c;
            i3++;
            i2++;
        }
        this.off = i2;
        if (i3 < cArr.length) {
            jSONWriterUTF8 = this;
            jSONWriterUTF8.writeStringEscapedRest(cArr, cArr.length, z, z2, i3);
        } else {
            jSONWriterUTF8 = this;
        }
        byte[] bArr2 = jSONWriterUTF8.bytes;
        int i4 = jSONWriterUTF8.off;
        jSONWriterUTF8.off = i4 + 1;
        bArr2[i4] = (byte) jSONWriterUTF8.quote;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeString(char[] cArr, int i, int i2) {
        if (cArr == null) {
            if (isEnabled(JSONWriter.Feature.NullAsDefaultValue.mask | JSONWriter.Feature.WriteNullStringAsEmpty.mask)) {
                writeString("");
                return;
            } else {
                writeNull();
                return;
            }
        }
        int i3 = i + i2;
        boolean z = (this.context.features & JSONWriter.Feature.BrowserSecure.mask) != 0;
        boolean z2 = (this.context.features & JSONWriter.Feature.EscapeNoneAscii.mask) != 0;
        int i4 = this.off;
        int i5 = i2 * 3;
        int i6 = i4 + i5 + 2;
        if (z2 || z) {
            i6 += i5;
        }
        byte[] bArr = this.bytes;
        if (i6 > bArr.length) {
            bArr = grow(i6);
        }
        int i7 = i4 + 1;
        bArr[i4] = (byte) this.quote;
        int i8 = i;
        while (i8 < i3) {
            char c = cArr[i8];
            if (c == this.quote || c == '\\' || c < ' ' || c > 127 || (z && (c == '<' || c == '>' || c == '(' || c == ')'))) {
                break;
            }
            bArr[i7] = (byte) c;
            i8++;
            i7++;
        }
        this.off = i7;
        if (i8 < i3) {
            writeStringEscapedRest(cArr, i3, z, z2, i8);
        }
        byte[] bArr2 = this.bytes;
        int i9 = this.off;
        this.off = i9 + 1;
        bArr2[i9] = (byte) this.quote;
    }

    protected final void writeStringEscaped(byte[] bArr) {
        int length = this.off + (bArr.length * 6) + 2;
        byte[] bArr2 = this.bytes;
        if (length > bArr2.length) {
            bArr2 = grow(length);
        }
        this.off = StringUtils.writeLatin1Escaped(bArr2, this.off, bArr, (byte) this.quote, this.context.features);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:18:0x0030. Please report as an issue. */
    protected final void writeStringEscapedRest(char[] cArr, int i, boolean z, boolean z2, int i2) {
        int i3;
        int i4;
        int i5;
        int length = this.off + ((cArr.length - i2) * 6) + 2;
        byte[] bArr = this.bytes;
        if (length > bArr.length) {
            bArr = grow(length);
        }
        int i6 = this.off;
        while (i2 < i) {
            char c = cArr[i2];
            if (c <= 127) {
                if (c != '(' && c != ')' && c != '<' && c != '>') {
                    if (c != '\\') {
                        switch (c) {
                            case 0:
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                            case 7:
                            case 11:
                            case 14:
                            case 15:
                            case 16:
                            case 17:
                            case 18:
                            case 19:
                            case 20:
                            case 21:
                            case 22:
                            case 23:
                            case 24:
                            case 25:
                            case 26:
                            case 27:
                            case 28:
                            case 29:
                            case 30:
                            case 31:
                                StringUtils.writeU4Hex2(bArr, i6, c);
                                i6 += 6;
                                break;
                            case '\b':
                            case '\t':
                            case '\n':
                            case '\f':
                            case '\r':
                                break;
                            default:
                                if (c == this.quote) {
                                    bArr[i6] = 92;
                                    bArr[i6 + 1] = (byte) this.quote;
                                    i6 += 2;
                                    break;
                                } else {
                                    i5 = i6 + 1;
                                    bArr[i6] = (byte) c;
                                    i6 = i5;
                                    break;
                                }
                        }
                    }
                    StringUtils.writeEscapedChar(bArr, i6, c);
                    i6 += 2;
                } else if (z) {
                    StringUtils.writeU4HexU(bArr, i6, c);
                    i6 += 6;
                } else {
                    i5 = i6 + 1;
                    bArr[i6] = (byte) c;
                    i6 = i5;
                }
            } else if (z2) {
                StringUtils.writeU4HexU(bArr, i6, c);
                i6 += 6;
            } else if (c >= 55296 && c < 57344) {
                if (c < 56320) {
                    if (cArr.length - i2 < 2) {
                        i4 = -1;
                    } else {
                        char c2 = cArr[i2 + 1];
                        if (c2 < 56320 || c2 >= 57344) {
                            i3 = i6 + 1;
                            bArr[i6] = 63;
                        } else {
                            i4 = ((c << '\n') + c2) - 56613888;
                        }
                    }
                    if (i4 < 0) {
                        i3 = i6 + 1;
                        bArr[i6] = 63;
                    } else {
                        bArr[i6] = (byte) ((i4 >> 18) | 240);
                        bArr[i6 + 1] = (byte) (((i4 >> 12) & 63) | 128);
                        bArr[i6 + 2] = (byte) ((63 & (i4 >> 6)) | 128);
                        bArr[i6 + 3] = (byte) ((i4 & 63) | 128);
                        i3 = i6 + 4;
                        i2++;
                    }
                } else {
                    i3 = i6 + 1;
                    bArr[i6] = 63;
                }
                i6 = i3;
            } else if (c > 2047) {
                bArr[i6] = (byte) (((c >> '\f') & 15) | BERTags.FLAGS);
                bArr[i6 + 1] = (byte) ((63 & (c >> 6)) | 128);
                bArr[i6 + 2] = (byte) ((c & '?') | 128);
                i6 += 3;
            } else {
                bArr[i6] = (byte) (((c >> 6) & 31) | 192);
                bArr[i6 + 1] = (byte) ((c & '?') | 128);
                i6 += 2;
            }
            i2++;
        }
        this.off = i6;
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x0060, code lost:
    
        if (r20 == false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0062, code lost:
    
        r7[r9] = (byte) r16.quote;
        r9 = r9 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x006a, code lost:
    
        r16.off = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x006c, code lost:
    
        return;
     */
    @Override // com.alibaba.fastjson2.JSONWriter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void writeString(char[] r17, int r18, int r19, boolean r20) {
        /*
            Method dump skipped, instructions count: 398
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONWriterUTF8.writeString(char[], int, int, boolean):void");
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeChar(char c) {
        int i;
        int i2 = this.off;
        byte[] bArr = this.bytes;
        int i3 = i2 + 8;
        if (i3 > bArr.length) {
            bArr = grow(i3);
        }
        int i4 = i2 + 1;
        bArr[i2] = (byte) this.quote;
        if (c > 127) {
            if (c >= 55296 && c < 57344) {
                throw new JSONException("illegal char " + c);
            }
            if (c > 2047) {
                bArr[i4] = (byte) (((c >> '\f') & 15) | BERTags.FLAGS);
                bArr[i2 + 2] = (byte) (((c >> 6) & 63) | 128);
                bArr[i2 + 3] = (byte) ((c & '?') | 128);
                i = i2 + 4;
                bArr[i] = (byte) this.quote;
                this.off = i + 1;
            }
            bArr[i4] = (byte) (((c >> 6) & 31) | 192);
            bArr[i2 + 2] = (byte) ((c & '?') | 128);
            i = i2 + 3;
            bArr[i] = (byte) this.quote;
            this.off = i + 1;
        }
        if (c != '\\') {
            switch (c) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 11:
                case 14:
                case 15:
                case 16:
                case 17:
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                    StringUtils.writeU4Hex2(bArr, i4, c);
                    i = i2 + 7;
                    break;
                case '\b':
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                    break;
                default:
                    if (c == this.quote) {
                        bArr[i4] = 92;
                        bArr[i2 + 2] = (byte) this.quote;
                        i = i2 + 3;
                        break;
                    } else {
                        i = i2 + 2;
                        bArr[i4] = (byte) c;
                        break;
                    }
            }
            bArr[i] = (byte) this.quote;
            this.off = i + 1;
        }
        StringUtils.writeEscapedChar(bArr, i4, c);
        i = i2 + 3;
        bArr[i] = (byte) this.quote;
        this.off = i + 1;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeUUID(UUID uuid) {
        if (uuid == null) {
            writeNull();
            return;
        }
        int i = this.off;
        int i2 = i + 38;
        byte[] bArr = this.bytes;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        byte[] bArr2 = bArr;
        byte b = (byte) this.quote;
        long j = JDKUtils.ARRAY_BYTE_BASE_OFFSET + i;
        JDKUtils.UNSAFE.putByte(bArr2, j, b);
        JDKUtils.UNSAFE.putByte(bArr2, 9 + j, (byte) 45);
        JDKUtils.UNSAFE.putByte(bArr2, 14 + j, (byte) 45);
        JDKUtils.UNSAFE.putByte(bArr2, 19 + j, (byte) 45);
        JDKUtils.UNSAFE.putByte(bArr2, 24 + j, (byte) 45);
        JDKUtils.UNSAFE.putByte(bArr2, 37 + j, b);
        long mostSignificantBits = uuid.getMostSignificantBits();
        long leastSignificantBits = uuid.getLeastSignificantBits();
        long hex8 = hex8(mostSignificantBits >>> 32);
        long hex82 = hex8(mostSignificantBits);
        JDKUtils.UNSAFE.putLong(bArr2, 1 + j, hex8);
        JDKUtils.UNSAFE.putInt(bArr2, 10 + j, (int) hex82);
        JDKUtils.UNSAFE.putInt(bArr2, 15 + j, (int) (hex82 >>> 32));
        long hex83 = hex8(leastSignificantBits >>> 32);
        long hex84 = hex8(leastSignificantBits);
        JDKUtils.UNSAFE.putInt(bArr2, 20 + j, (int) hex83);
        JDKUtils.UNSAFE.putInt(bArr2, 25 + j, (int) (hex83 >>> 32));
        JDKUtils.UNSAFE.putLong(bArr2, j + 29, hex84);
        this.off += 38;
    }

    private static long hex8(long j) {
        long expand = expand(j);
        long j2 = (434041037028460038L + expand) & 1157442765409226768L;
        long j3 = (((j2 << 1) + (j2 >> 1)) - (j2 >> 4)) + 3472328296227680304L + expand;
        return !JDKUtils.BIG_ENDIAN ? Long.reverseBytes(j3) : j3;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeRaw(String str) {
        char[] charArray = JDKUtils.getCharArray(str);
        int i = this.off;
        int length = (charArray.length * 3) + i;
        byte[] bArr = this.bytes;
        if (length > bArr.length) {
            bArr = grow(length);
        }
        for (char c : charArray) {
            if (c >= 1 && c <= 127) {
                bArr[i] = (byte) c;
                i++;
            } else if (c > 2047) {
                bArr[i] = (byte) (((c >> '\f') & 15) | BERTags.FLAGS);
                bArr[i + 1] = (byte) (((c >> 6) & 63) | 128);
                bArr[i + 2] = (byte) ((c & '?') | 128);
                i += 3;
            } else {
                bArr[i] = (byte) (((c >> 6) & 31) | 192);
                bArr[i + 1] = (byte) ((c & '?') | 128);
                i += 2;
            }
        }
        this.off = i;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeRaw(byte[] bArr) {
        int i = this.off;
        int length = bArr.length + i;
        if (length > this.bytes.length) {
            grow(length);
        }
        System.arraycopy(bArr, 0, this.bytes, i, bArr.length);
        this.off = i + bArr.length;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeNameRaw(byte[] bArr) {
        int i = this.off;
        int length = bArr.length + i + 2 + (this.pretty * this.level);
        byte[] bArr2 = this.bytes;
        if (length > bArr2.length) {
            bArr2 = grow(length);
        }
        if (this.startObject) {
            this.startObject = false;
        } else {
            int i2 = i + 1;
            bArr2[i] = 44;
            i = this.pretty != 0 ? indent(bArr2, i2) : i2;
        }
        System.arraycopy(bArr, 0, bArr2, i, bArr.length);
        this.off = i + bArr.length;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeName2Raw(long j) {
        int i = this.off;
        int i2 = i + 10 + (this.pretty * this.level);
        byte[] bArr = this.bytes;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        byte[] bArr2 = bArr;
        if (this.startObject) {
            this.startObject = false;
        } else {
            int i3 = i + 1;
            bArr2[i] = 44;
            i = this.pretty != 0 ? indent(bArr2, i3) : i3;
        }
        JDKUtils.UNSAFE.putLong(bArr2, i + JDKUtils.ARRAY_BYTE_BASE_OFFSET, j);
        this.off = i + 5;
    }

    private int indent(byte[] bArr, int i) {
        bArr[i] = 10;
        int i2 = i + 1;
        int i3 = (this.pretty * this.level) + i2;
        Arrays.fill(bArr, i2, i3, this.pretty == 1 ? (byte) 9 : (byte) 32);
        return i3;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeName3Raw(long j) {
        int i = this.off;
        int i2 = i + 10 + (this.pretty * this.level);
        byte[] bArr = this.bytes;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        byte[] bArr2 = bArr;
        if (this.startObject) {
            this.startObject = false;
        } else {
            int i3 = i + 1;
            bArr2[i] = 44;
            i = this.pretty != 0 ? indent(bArr2, i3) : i3;
        }
        JDKUtils.UNSAFE.putLong(bArr2, i + JDKUtils.ARRAY_BYTE_BASE_OFFSET, j);
        this.off = i + 6;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeName4Raw(long j) {
        int i = this.off;
        int i2 = i + 10 + (this.pretty * this.level);
        byte[] bArr = this.bytes;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        byte[] bArr2 = bArr;
        if (this.startObject) {
            this.startObject = false;
        } else {
            int i3 = i + 1;
            bArr2[i] = 44;
            i = this.pretty != 0 ? indent(bArr2, i3) : i3;
        }
        JDKUtils.UNSAFE.putLong(bArr2, i + JDKUtils.ARRAY_BYTE_BASE_OFFSET, j);
        this.off = i + 7;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeName5Raw(long j) {
        int i = this.off;
        int i2 = i + 10 + (this.pretty * this.level);
        byte[] bArr = this.bytes;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        byte[] bArr2 = bArr;
        if (this.startObject) {
            this.startObject = false;
        } else {
            int i3 = i + 1;
            bArr2[i] = 44;
            i = this.pretty != 0 ? indent(bArr2, i3) : i3;
        }
        JDKUtils.UNSAFE.putLong(bArr2, i + JDKUtils.ARRAY_BYTE_BASE_OFFSET, j);
        this.off = i + 8;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeName6Raw(long j) {
        int i = this.off;
        int i2 = i + 11 + (this.pretty * this.level);
        byte[] bArr = this.bytes;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        byte[] bArr2 = bArr;
        if (this.startObject) {
            this.startObject = false;
        } else {
            int i3 = i + 1;
            bArr2[i] = 44;
            i = this.pretty != 0 ? indent(bArr2, i3) : i3;
        }
        JDKUtils.UNSAFE.putLong(bArr2, i + JDKUtils.ARRAY_BYTE_BASE_OFFSET, j);
        bArr2[i + 8] = 58;
        this.off = i + 9;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeName7Raw(long j) {
        int i = this.off;
        int i2 = i + 12 + (this.pretty * this.level);
        byte[] bArr = this.bytes;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        byte[] bArr2 = bArr;
        if (this.startObject) {
            this.startObject = false;
        } else {
            int i3 = i + 1;
            bArr2[i] = 44;
            i = this.pretty != 0 ? indent(bArr2, i3) : i3;
        }
        JDKUtils.UNSAFE.putLong(bArr2, i + JDKUtils.ARRAY_BYTE_BASE_OFFSET, j);
        bArr2[i + 8] = (byte) this.quote;
        bArr2[i + 9] = 58;
        this.off = i + 10;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeName8Raw(long j) {
        int i = this.off;
        int i2 = i + 13 + (this.pretty * this.level);
        byte[] bArr = this.bytes;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        byte[] bArr2 = bArr;
        if (this.startObject) {
            this.startObject = false;
        } else {
            int i3 = i + 1;
            bArr2[i] = 44;
            i = this.pretty != 0 ? indent(bArr2, i3) : i3;
        }
        bArr2[i] = (byte) this.quote;
        long j2 = i;
        JDKUtils.UNSAFE.putLong(bArr2, 1 + JDKUtils.ARRAY_BYTE_BASE_OFFSET + j2, j);
        JDKUtils.UNSAFE.putShort(bArr2, JDKUtils.ARRAY_BYTE_BASE_OFFSET + j2 + 9, this.useSingleQuote ? QUOTE_COLON : QUOTE2_COLON);
        this.off = i + 11;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeName9Raw(long j, int i) {
        int i2 = this.off;
        int i3 = i2 + 14 + (this.pretty * this.level);
        byte[] bArr = this.bytes;
        if (i3 > bArr.length) {
            bArr = grow(i3);
        }
        byte[] bArr2 = bArr;
        if (this.startObject) {
            this.startObject = false;
        } else {
            int i4 = i2 + 1;
            bArr2[i2] = 44;
            i2 = this.pretty != 0 ? indent(bArr2, i4) : i4;
        }
        long j2 = i2;
        JDKUtils.UNSAFE.putLong(bArr2, JDKUtils.ARRAY_BYTE_BASE_OFFSET + j2, j);
        JDKUtils.UNSAFE.putInt(bArr2, JDKUtils.ARRAY_BYTE_BASE_OFFSET + j2 + 8, i);
        this.off = i2 + 12;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeName10Raw(long j, long j2) {
        int i = this.off;
        int i2 = i + 18 + (this.pretty * this.level);
        byte[] bArr = this.bytes;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        byte[] bArr2 = bArr;
        if (this.startObject) {
            this.startObject = false;
        } else {
            int i3 = i + 1;
            bArr2[i] = 44;
            i = this.pretty != 0 ? indent(bArr2, i3) : i3;
        }
        long j3 = i;
        JDKUtils.UNSAFE.putLong(bArr2, JDKUtils.ARRAY_BYTE_BASE_OFFSET + j3, j);
        JDKUtils.UNSAFE.putLong(bArr2, JDKUtils.ARRAY_BYTE_BASE_OFFSET + j3 + 8, j2);
        this.off = i + 13;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeName11Raw(long j, long j2) {
        int i = this.off;
        int i2 = i + 18 + (this.pretty * this.level);
        byte[] bArr = this.bytes;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        byte[] bArr2 = bArr;
        if (this.startObject) {
            this.startObject = false;
        } else {
            int i3 = i + 1;
            bArr2[i] = 44;
            i = this.pretty != 0 ? indent(bArr2, i3) : i3;
        }
        long j3 = i;
        JDKUtils.UNSAFE.putLong(bArr2, JDKUtils.ARRAY_BYTE_BASE_OFFSET + j3, j);
        JDKUtils.UNSAFE.putLong(bArr2, JDKUtils.ARRAY_BYTE_BASE_OFFSET + j3 + 8, j2);
        this.off = i + 14;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeName12Raw(long j, long j2) {
        int i = this.off;
        int i2 = i + 18 + (this.pretty * this.level);
        byte[] bArr = this.bytes;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        byte[] bArr2 = bArr;
        if (this.startObject) {
            this.startObject = false;
        } else {
            int i3 = i + 1;
            bArr2[i] = 44;
            i = this.pretty != 0 ? indent(bArr2, i3) : i3;
        }
        long j3 = i;
        JDKUtils.UNSAFE.putLong(bArr2, JDKUtils.ARRAY_BYTE_BASE_OFFSET + j3, j);
        JDKUtils.UNSAFE.putLong(bArr2, JDKUtils.ARRAY_BYTE_BASE_OFFSET + j3 + 8, j2);
        this.off = i + 15;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeName13Raw(long j, long j2) {
        int i = this.off;
        int i2 = i + 18 + (this.pretty * this.level);
        byte[] bArr = this.bytes;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        byte[] bArr2 = bArr;
        if (this.startObject) {
            this.startObject = false;
        } else {
            int i3 = i + 1;
            bArr2[i] = 44;
            i = this.pretty != 0 ? indent(bArr2, i3) : i3;
        }
        long j3 = i;
        JDKUtils.UNSAFE.putLong(bArr2, JDKUtils.ARRAY_BYTE_BASE_OFFSET + j3, j);
        JDKUtils.UNSAFE.putLong(bArr2, JDKUtils.ARRAY_BYTE_BASE_OFFSET + j3 + 8, j2);
        this.off = i + 16;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeName14Raw(long j, long j2) {
        int i = this.off;
        int i2 = i + 19 + (this.pretty * this.level);
        byte[] bArr = this.bytes;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        byte[] bArr2 = bArr;
        if (this.startObject) {
            this.startObject = false;
        } else {
            int i3 = i + 1;
            bArr2[i] = 44;
            i = this.pretty != 0 ? indent(bArr2, i3) : i3;
        }
        long j3 = i;
        JDKUtils.UNSAFE.putLong(bArr2, JDKUtils.ARRAY_BYTE_BASE_OFFSET + j3, j);
        JDKUtils.UNSAFE.putLong(bArr2, JDKUtils.ARRAY_BYTE_BASE_OFFSET + j3 + 8, j2);
        bArr2[i + 16] = 58;
        this.off = i + 17;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeName15Raw(long j, long j2) {
        int i = this.off;
        int i2 = i + 20 + (this.pretty * this.level);
        byte[] bArr = this.bytes;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        byte[] bArr2 = bArr;
        if (this.startObject) {
            this.startObject = false;
        } else {
            int i3 = i + 1;
            bArr2[i] = 44;
            i = this.pretty != 0 ? indent(bArr2, i3) : i3;
        }
        long j3 = i;
        JDKUtils.UNSAFE.putLong(bArr2, JDKUtils.ARRAY_BYTE_BASE_OFFSET + j3, j);
        JDKUtils.UNSAFE.putLong(bArr2, JDKUtils.ARRAY_BYTE_BASE_OFFSET + j3 + 8, j2);
        JDKUtils.UNSAFE.putShort(bArr2, JDKUtils.ARRAY_BYTE_BASE_OFFSET + j3 + 16, this.useSingleQuote ? QUOTE_COLON : QUOTE2_COLON);
        this.off = i + 18;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeName16Raw(long j, long j2) {
        int i = this.off;
        int i2 = i + 21 + (this.pretty * this.level);
        byte[] bArr = this.bytes;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        byte[] bArr2 = bArr;
        if (this.startObject) {
            this.startObject = false;
        } else {
            int i3 = i + 1;
            bArr2[i] = 44;
            i = this.pretty != 0 ? indent(bArr2, i3) : i3;
        }
        bArr2[i] = (byte) this.quote;
        long j3 = i + 1;
        JDKUtils.UNSAFE.putLong(bArr2, JDKUtils.ARRAY_BYTE_BASE_OFFSET + j3, j);
        JDKUtils.UNSAFE.putLong(bArr2, 8 + JDKUtils.ARRAY_BYTE_BASE_OFFSET + j3, j2);
        JDKUtils.UNSAFE.putShort(bArr2, JDKUtils.ARRAY_BYTE_BASE_OFFSET + j3 + 16, this.useSingleQuote ? QUOTE_COLON : QUOTE2_COLON);
        this.off = i + 19;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeRaw(byte b) {
        int i = this.off;
        grow1(i)[i] = b;
        this.off = i + 1;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeRaw(char c) {
        if (c > 128) {
            throw new JSONException("not support " + c);
        }
        if (this.off == this.bytes.length) {
            grow0(this.off + 1);
        }
        byte[] bArr = this.bytes;
        int i = this.off;
        this.off = i + 1;
        bArr[i] = (byte) c;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeRaw(char c, char c2) {
        if (c > 128 || c2 > 128) {
            throw new JSONException("not support " + c + ", " + c2);
        }
        int i = this.off;
        byte[] bArr = this.bytes;
        int i2 = i + 2;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        bArr[i] = (byte) c;
        bArr[i + 1] = (byte) c2;
        this.off = i2;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeNameRaw(byte[] bArr, int i, int i2) {
        int i3 = this.off;
        int i4 = i3 + i2 + 2 + (this.pretty * this.level);
        byte[] bArr2 = this.bytes;
        if (i4 > bArr2.length) {
            bArr2 = grow(i4);
        }
        if (!this.startObject) {
            int i5 = i3 + 1;
            bArr2[i3] = 44;
            i3 = this.pretty != 0 ? indent(bArr2, i5) : i5;
        }
        this.startObject = false;
        System.arraycopy(bArr, i, bArr2, i3, i2);
        this.off = i3 + i2;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final Object ensureCapacity(int i) {
        byte[] bArr = this.bytes;
        if (i < bArr.length) {
            return bArr;
        }
        byte[] copyOf = Arrays.copyOf(bArr, newCapacity(i, bArr.length));
        this.bytes = copyOf;
        return copyOf;
    }

    private byte[] grow(int i) {
        grow0(i);
        return this.bytes;
    }

    private byte[] grow1(int i) {
        byte[] bArr = this.bytes;
        return i == bArr.length ? grow(i + 1) : bArr;
    }

    private void grow0(int i) {
        byte[] bArr = this.bytes;
        this.bytes = Arrays.copyOf(bArr, newCapacity(i, bArr.length));
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeInt32(int[] iArr) {
        if (iArr == null) {
            writeNull();
            return;
        }
        boolean z = (this.context.features & 256) != 0;
        int i = this.off;
        int length = (iArr.length * 13) + i + 2;
        byte[] bArr = this.bytes;
        if (length > bArr.length) {
            bArr = grow(length);
        }
        int i2 = i + 1;
        bArr[i] = 91;
        for (int i3 = 0; i3 < iArr.length; i3++) {
            if (i3 != 0) {
                bArr[i2] = 44;
                i2++;
            }
            if (z) {
                bArr[i2] = (byte) this.quote;
                i2++;
            }
            int writeInt32 = IOUtils.writeInt32(bArr, i2, iArr[i3]);
            if (z) {
                i2 = writeInt32 + 1;
                bArr[writeInt32] = (byte) this.quote;
            } else {
                i2 = writeInt32;
            }
        }
        bArr[i2] = 93;
        this.off = i2 + 1;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeInt8(byte b) {
        boolean z = (this.context.features & 256) != 0;
        int i = this.off;
        int i2 = i + 5;
        byte[] bArr = this.bytes;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        if (z) {
            bArr[i] = (byte) this.quote;
            i++;
        }
        int writeInt8 = IOUtils.writeInt8(bArr, i, b);
        if (z) {
            bArr[writeInt8] = (byte) this.quote;
            writeInt8++;
        }
        this.off = writeInt8;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeInt8(byte[] bArr) {
        if (bArr == null) {
            writeNull();
            return;
        }
        boolean z = (this.context.features & 256) != 0;
        int i = this.off;
        int length = (bArr.length * 5) + i + 2;
        byte[] bArr2 = this.bytes;
        if (length > bArr2.length) {
            bArr2 = grow(length);
        }
        int i2 = i + 1;
        bArr2[i] = 91;
        for (int i3 = 0; i3 < bArr.length; i3++) {
            if (i3 != 0) {
                bArr2[i2] = 44;
                i2++;
            }
            if (z) {
                bArr2[i2] = (byte) this.quote;
                i2++;
            }
            int writeInt8 = IOUtils.writeInt8(bArr2, i2, bArr[i3]);
            if (z) {
                i2 = writeInt8 + 1;
                bArr2[writeInt8] = (byte) this.quote;
            } else {
                i2 = writeInt8;
            }
        }
        bArr2[i2] = 93;
        this.off = i2 + 1;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeInt16(short s) {
        boolean z = (this.context.features & JSONWriter.Feature.WriteNonStringValueAsString.mask) != 0;
        int i = this.off;
        int i2 = i + 7;
        byte[] bArr = this.bytes;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        if (z) {
            bArr[i] = (byte) this.quote;
            i++;
        }
        int writeInt16 = IOUtils.writeInt16(bArr, i, s);
        if (z) {
            bArr[writeInt16] = (byte) this.quote;
            writeInt16++;
        }
        this.off = writeInt16;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeInt32(Integer num) {
        if (num == null) {
            writeNumberNull();
        } else {
            writeInt32(num.intValue());
        }
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeInt32(int i) {
        boolean z = (this.context.features & 256) != 0;
        int i2 = this.off;
        int i3 = i2 + 13;
        byte[] bArr = this.bytes;
        if (i3 > bArr.length) {
            bArr = grow(i3);
        }
        if (z) {
            bArr[i2] = (byte) this.quote;
            i2++;
        }
        int writeInt32 = IOUtils.writeInt32(bArr, i2, i);
        if (z) {
            bArr[writeInt32] = (byte) this.quote;
            writeInt32++;
        }
        this.off = writeInt32;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeListInt32(List<Integer> list) {
        if (list == null) {
            writeNull();
            return;
        }
        int size = list.size();
        boolean z = (this.context.features & 256) != 0;
        int i = this.off;
        int i2 = i + 2 + (size * 23);
        byte[] bArr = this.bytes;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        int i3 = i + 1;
        bArr[i] = 91;
        for (int i4 = 0; i4 < size; i4++) {
            if (i4 != 0) {
                bArr[i3] = 44;
                i3++;
            }
            Integer num = list.get(i4);
            if (num == null) {
                IOUtils.putNULL(bArr, i3);
                i3 += 4;
            } else {
                int intValue = num.intValue();
                if (z) {
                    bArr[i3] = (byte) this.quote;
                    i3++;
                }
                int writeInt32 = IOUtils.writeInt32(bArr, i3, intValue);
                if (z) {
                    i3 = writeInt32 + 1;
                    bArr[writeInt32] = (byte) this.quote;
                } else {
                    i3 = writeInt32;
                }
            }
        }
        bArr[i3] = 93;
        this.off = i3 + 1;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeInt64(long[] jArr) {
        if (jArr == null) {
            writeNull();
            return;
        }
        int i = this.off;
        int length = i + 2 + (jArr.length * 23);
        byte[] bArr = this.bytes;
        if (length > bArr.length) {
            bArr = grow(length);
        }
        int i2 = i + 1;
        bArr[i] = 91;
        for (int i3 = 0; i3 < jArr.length; i3++) {
            if (i3 != 0) {
                bArr[i2] = 44;
                i2++;
            }
            long j = jArr[i3];
            boolean isWriteAsString = isWriteAsString(j, this.context.features);
            if (isWriteAsString) {
                bArr[i2] = (byte) this.quote;
                i2++;
            }
            i2 = IOUtils.writeInt64(bArr, i2, j);
            if (isWriteAsString) {
                bArr[i2] = (byte) this.quote;
                i2++;
            }
        }
        bArr[i2] = 93;
        this.off = i2 + 1;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeListInt64(List<Long> list) {
        if (list == null) {
            writeNull();
            return;
        }
        int size = list.size();
        int i = this.off;
        int i2 = i + 2 + (size * 23);
        byte[] bArr = this.bytes;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        int i3 = i + 1;
        bArr[i] = 91;
        for (int i4 = 0; i4 < size; i4++) {
            if (i4 != 0) {
                bArr[i3] = 44;
                i3++;
            }
            Long l = list.get(i4);
            if (l == null) {
                IOUtils.putNULL(bArr, i3);
                i3 += 4;
            } else {
                long longValue = l.longValue();
                boolean isWriteAsString = isWriteAsString(longValue, this.context.features);
                if (isWriteAsString) {
                    bArr[i3] = (byte) this.quote;
                    i3++;
                }
                i3 = IOUtils.writeInt64(bArr, i3, longValue);
                if (isWriteAsString) {
                    bArr[i3] = (byte) this.quote;
                    i3++;
                }
            }
        }
        bArr[i3] = 93;
        this.off = i3 + 1;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeInt64(long j) {
        int i;
        long j2 = this.context.features;
        int i2 = this.off;
        int i3 = i2 + 23;
        byte[] bArr = this.bytes;
        if (i3 > bArr.length) {
            bArr = grow(i3);
        }
        boolean isWriteAsString = isWriteAsString(j, j2);
        if (isWriteAsString) {
            bArr[i2] = (byte) this.quote;
            i2++;
        }
        int writeInt64 = IOUtils.writeInt64(bArr, i2, j);
        if (isWriteAsString) {
            i = writeInt64 + 1;
            bArr[writeInt64] = (byte) this.quote;
        } else {
            if ((512 & j2) != 0 && (j2 & 1099511627776L) == 0 && j >= -2147483648L && j <= 2147483647L) {
                i = writeInt64 + 1;
                bArr[writeInt64] = 76;
            }
            this.off = writeInt64;
        }
        writeInt64 = i;
        this.off = writeInt64;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeInt64(Long l) {
        if (l == null) {
            writeNumberNull();
        } else {
            writeInt64(l.longValue());
        }
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeFloat(float f) {
        boolean z = (this.context.features & 256) != 0;
        int i = this.off;
        int i2 = i + 17;
        byte[] bArr = this.bytes;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        if (z) {
            bArr[i] = 34;
            i++;
        }
        int writeFloat = NumberUtils.writeFloat(bArr, i, f, true);
        if (z) {
            bArr[writeFloat] = 34;
            writeFloat++;
        }
        this.off = writeFloat;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeDouble(double d) {
        boolean z = (this.context.features & 256) != 0;
        int i = this.off;
        int i2 = i + 26;
        byte[] bArr = this.bytes;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        if (z) {
            bArr[i] = 34;
            i++;
        }
        int writeDouble = NumberUtils.writeDouble(bArr, i, d, true);
        if (z) {
            bArr[writeDouble] = 34;
            writeDouble++;
        }
        this.off = writeDouble;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeFloat(float[] fArr) {
        if (fArr == null) {
            writeArrayNull();
            return;
        }
        boolean z = (this.context.features & 256) != 0;
        int i = this.off;
        int length = (fArr.length * (z ? 16 : 18)) + i + 1;
        byte[] bArr = this.bytes;
        if (length > bArr.length) {
            bArr = grow(length);
        }
        int i2 = i + 1;
        bArr[i] = 91;
        for (int i3 = 0; i3 < fArr.length; i3++) {
            if (i3 != 0) {
                bArr[i2] = 44;
                i2++;
            }
            if (z) {
                bArr[i2] = 34;
                i2++;
            }
            i2 = NumberUtils.writeFloat(bArr, i2, fArr[i3], true);
            if (z) {
                bArr[i2] = 34;
                i2++;
            }
        }
        bArr[i2] = 93;
        this.off = i2 + 1;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeDouble(double[] dArr) {
        if (dArr == null) {
            writeNull();
            return;
        }
        boolean z = (this.context.features & JSONWriter.Feature.WriteNonStringValueAsString.mask) != 0;
        int i = this.off;
        int length = (dArr.length * 27) + i + 1;
        byte[] bArr = this.bytes;
        if (length > bArr.length) {
            bArr = grow(length);
        }
        int i2 = i + 1;
        bArr[i] = 91;
        for (int i3 = 0; i3 < dArr.length; i3++) {
            if (i3 != 0) {
                bArr[i2] = 44;
                i2++;
            }
            if (z) {
                bArr[i2] = 34;
                i2++;
            }
            i2 = NumberUtils.writeDouble(bArr, i2, dArr[i3], true);
            if (z) {
                bArr[i2] = 34;
                i2++;
            }
        }
        bArr[i2] = 93;
        this.off = i2 + 1;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeDateTime14(int i, int i2, int i3, int i4, int i5, int i6) {
        int i7 = this.off;
        int i8 = i7 + 16;
        byte[] bArr = this.bytes;
        if (i8 > bArr.length) {
            bArr = grow(i8);
        }
        bArr[i7] = (byte) this.quote;
        if (i < 0 || i > 9999) {
            throw illegalYear(i);
        }
        int i9 = i / 100;
        IOUtils.writeDigitPair(bArr, i7 + 1, i9);
        IOUtils.writeDigitPair(bArr, i7 + 3, i - (i9 * 100));
        IOUtils.writeDigitPair(bArr, i7 + 5, i2);
        IOUtils.writeDigitPair(bArr, i7 + 7, i3);
        IOUtils.writeDigitPair(bArr, i7 + 9, i4);
        IOUtils.writeDigitPair(bArr, i7 + 11, i5);
        IOUtils.writeDigitPair(bArr, i7 + 13, i6);
        bArr[i7 + 15] = (byte) this.quote;
        this.off = i8;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeDateTime19(int i, int i2, int i3, int i4, int i5, int i6) {
        int i7 = this.off;
        int i8 = i7 + 21;
        byte[] bArr = this.bytes;
        if (i8 > bArr.length) {
            bArr = grow(i8);
        }
        bArr[i7] = (byte) this.quote;
        int writeLocalDate = IOUtils.writeLocalDate(bArr, i7 + 1, i, i2, i3);
        bArr[writeLocalDate] = 32;
        IOUtils.writeLocalTime(bArr, writeLocalDate + 1, i4, i5, i6);
        bArr[writeLocalDate + 9] = (byte) this.quote;
        this.off = writeLocalDate + 10;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeLocalDate(LocalDate localDate) {
        if (localDate == null) {
            writeNull();
            return;
        }
        if (this.context.dateFormat == null || !writeLocalDateWithFormat(localDate)) {
            int i = this.off;
            int i2 = i + 18;
            byte[] bArr = this.bytes;
            if (i2 > bArr.length) {
                bArr = grow(i2);
            }
            bArr[i] = (byte) this.quote;
            int writeLocalDate = IOUtils.writeLocalDate(bArr, i + 1, localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth());
            bArr[writeLocalDate] = (byte) this.quote;
            this.off = writeLocalDate + 1;
        }
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeLocalDateTime(LocalDateTime localDateTime) {
        int i = this.off;
        int i2 = i + 38;
        byte[] bArr = this.bytes;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        int i3 = i + 1;
        bArr[i] = (byte) this.quote;
        LocalDate localDate = localDateTime.toLocalDate();
        int writeLocalDate = IOUtils.writeLocalDate(bArr, i3, localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth());
        bArr[writeLocalDate] = 32;
        int writeLocalTime = IOUtils.writeLocalTime(bArr, writeLocalDate + 1, localDateTime.toLocalTime());
        bArr[writeLocalTime] = (byte) this.quote;
        this.off = writeLocalTime + 1;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeDateYYYMMDD8(int i, int i2, int i3) {
        int i4 = this.off;
        int i5 = i4 + 10;
        byte[] bArr = this.bytes;
        if (i5 > bArr.length) {
            bArr = grow(i5);
        }
        bArr[i4] = (byte) this.quote;
        if (i < 0 || i > 9999) {
            throw illegalYear(i);
        }
        int i6 = i / 100;
        IOUtils.writeDigitPair(bArr, i4 + 1, i6);
        IOUtils.writeDigitPair(bArr, i4 + 3, i - (i6 * 100));
        IOUtils.writeDigitPair(bArr, i4 + 5, i2);
        IOUtils.writeDigitPair(bArr, i4 + 7, i3);
        bArr[i4 + 9] = (byte) this.quote;
        this.off = i5;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeDateYYYMMDD10(int i, int i2, int i3) {
        int i4 = this.off;
        int i5 = i4 + 13;
        byte[] bArr = this.bytes;
        if (i5 > bArr.length) {
            bArr = grow(i5);
        }
        bArr[i4] = (byte) this.quote;
        int writeLocalDate = IOUtils.writeLocalDate(bArr, i4 + 1, i, i2, i3);
        bArr[writeLocalDate] = (byte) this.quote;
        this.off = writeLocalDate + 1;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeTimeHHMMSS8(int i, int i2, int i3) {
        int i4 = this.off;
        int i5 = i4 + 10;
        byte[] bArr = this.bytes;
        if (i5 > bArr.length) {
            bArr = grow(i5);
        }
        bArr[i4] = (byte) this.quote;
        IOUtils.writeLocalTime(bArr, i4 + 1, i, i2, i3);
        bArr[i4 + 9] = (byte) this.quote;
        this.off = i5;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeLocalTime(LocalTime localTime) {
        int i = this.off;
        int i2 = i + 20;
        byte[] bArr = this.bytes;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        bArr[i] = (byte) this.quote;
        int writeLocalTime = IOUtils.writeLocalTime(bArr, i + 1, localTime);
        bArr[writeLocalTime] = (byte) this.quote;
        this.off = writeLocalTime + 1;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeZonedDateTime(ZonedDateTime zonedDateTime) {
        char c;
        int i;
        int i2;
        if (zonedDateTime == null) {
            writeNull();
            return;
        }
        ZoneId zone = zonedDateTime.getZone();
        String id = zone.getId();
        int length = id.length();
        if (ZoneOffset.UTC == zone || (length <= 3 && ("UTC".equals(id) || "Z".equals(id)))) {
            id = "Z";
            c = 0;
            i = 1;
        } else {
            if (length != 0) {
                c = id.charAt(0);
                if (c == '+' || c == '-') {
                    i = length;
                }
            } else {
                c = 0;
            }
            i = length + 2;
        }
        int i3 = this.off;
        int i4 = i3 + i + 38;
        byte[] bArr = this.bytes;
        if (i4 > bArr.length) {
            bArr = grow(i4);
        }
        bArr[i3] = (byte) this.quote;
        LocalDate localDate = zonedDateTime.toLocalDate();
        int writeLocalDate = IOUtils.writeLocalDate(bArr, i3 + 1, localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth());
        bArr[writeLocalDate] = 84;
        int writeLocalTime = IOUtils.writeLocalTime(bArr, writeLocalDate + 1, zonedDateTime.toLocalTime());
        if (i == 1) {
            i2 = writeLocalTime + 1;
            bArr[writeLocalTime] = 90;
        } else if (c == '+' || c == '-') {
            id.getBytes(0, length, bArr, writeLocalTime);
            i2 = writeLocalTime + length;
        } else {
            int i5 = writeLocalTime + 1;
            bArr[writeLocalTime] = 91;
            id.getBytes(0, length, bArr, i5);
            int i6 = i5 + length;
            bArr[i6] = 93;
            i2 = i6 + 1;
        }
        bArr[i2] = (byte) this.quote;
        this.off = i2 + 1;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeOffsetDateTime(OffsetDateTime offsetDateTime) {
        int length;
        if (offsetDateTime == null) {
            writeNull();
            return;
        }
        int i = this.off;
        int i2 = i + 45;
        byte[] bArr = this.bytes;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        bArr[i] = (byte) this.quote;
        LocalDateTime localDateTime = offsetDateTime.toLocalDateTime();
        LocalDate localDate = localDateTime.toLocalDate();
        int writeLocalDate = IOUtils.writeLocalDate(bArr, i + 1, localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth());
        bArr[writeLocalDate] = 84;
        int writeLocalTime = IOUtils.writeLocalTime(bArr, writeLocalDate + 1, localDateTime.toLocalTime());
        ZoneOffset offset = offsetDateTime.getOffset();
        if (offset.getTotalSeconds() == 0) {
            length = writeLocalTime + 1;
            bArr[writeLocalTime] = 90;
        } else {
            String id = offset.getId();
            id.getBytes(0, id.length(), bArr, writeLocalTime);
            length = id.length() + writeLocalTime;
        }
        bArr[length] = (byte) this.quote;
        this.off = length + 1;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeOffsetTime(OffsetTime offsetTime) {
        int length;
        if (offsetTime == null) {
            writeNull();
            return;
        }
        int i = this.off;
        int i2 = i + 28;
        byte[] bArr = this.bytes;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        bArr[i] = (byte) this.quote;
        int writeLocalTime = IOUtils.writeLocalTime(bArr, i + 1, offsetTime.toLocalTime());
        ZoneOffset offset = offsetTime.getOffset();
        if (offset.getTotalSeconds() == 0) {
            length = writeLocalTime + 1;
            bArr[writeLocalTime] = 90;
        } else {
            String id = offset.getId();
            id.getBytes(0, id.length(), bArr, writeLocalTime);
            length = id.length() + writeLocalTime;
        }
        bArr[length] = (byte) this.quote;
        this.off = length + 1;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeBigInt(BigInteger bigInteger, long j) {
        if (bigInteger == null) {
            writeNumberNull();
            return;
        }
        if (TypeUtils.isInt64(bigInteger) && j == 0) {
            writeInt64(bigInteger.longValue());
            return;
        }
        String bigInteger2 = bigInteger.toString(10);
        boolean isWriteAsString = isWriteAsString(bigInteger, j | this.context.features);
        int i = this.off;
        int length = bigInteger2.length();
        int i2 = i + length + (isWriteAsString ? 2 : 0);
        byte[] bArr = this.bytes;
        if (i2 > bArr.length) {
            bArr = grow(i2);
        }
        if (isWriteAsString) {
            bArr[i] = 34;
            i++;
        }
        bigInteger2.getBytes(0, length, bArr, i);
        int i3 = i + length;
        if (isWriteAsString) {
            bArr[i3] = 34;
            i3++;
        }
        this.off = i3;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeDateTimeISO8601(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z) {
        int i9 = z ? i8 == 0 ? 1 : 6 : 0;
        int i10 = this.off;
        int i11 = i10 + 25 + i9;
        byte[] bArr = this.bytes;
        if (i11 > bArr.length) {
            bArr = grow(i11);
        }
        bArr[i10] = (byte) this.quote;
        int writeLocalDate = IOUtils.writeLocalDate(bArr, i10 + 1, i, i2, i3);
        bArr[writeLocalDate] = (byte) (z ? 84 : 32);
        IOUtils.writeLocalTime(bArr, writeLocalDate + 1, i4, i5, i6);
        int i12 = writeLocalDate + 9;
        if (i7 > 0) {
            int i13 = i7 / 10;
            int i14 = i13 / 10;
            if (i7 - (i13 * 10) != 0) {
                IOUtils.putIntLE(bArr, i12, (IOUtils.DIGITS_K_32[i7 & 1023] & InputDeviceCompat.SOURCE_ANY) | 46);
                i12 = writeLocalDate + 13;
            } else {
                int i15 = writeLocalDate + 10;
                bArr[i12] = 46;
                if (i13 - (i14 * 10) != 0) {
                    IOUtils.writeDigitPair(bArr, i15, i13);
                    i12 = writeLocalDate + 12;
                } else {
                    i12 = writeLocalDate + 11;
                    bArr[i15] = (byte) (i14 + 48);
                }
            }
        }
        if (z) {
            int i16 = i8 / 3600;
            if (i8 == 0) {
                bArr[i12] = 90;
                i12++;
            } else {
                int abs = Math.abs(i16);
                bArr[i12] = i16 >= 0 ? (byte) 43 : (byte) 45;
                IOUtils.writeDigitPair(bArr, i12 + 1, abs);
                bArr[i12 + 3] = 58;
                int i17 = (i8 - (i16 * 3600)) / 60;
                if (i17 < 0) {
                    i17 = -i17;
                }
                IOUtils.writeDigitPair(bArr, i12 + 4, i17);
                i12 += 6;
            }
        }
        bArr[i12] = (byte) this.quote;
        this.off = i12 + 1;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x008d  */
    @Override // com.alibaba.fastjson2.JSONWriter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void writeDecimal(java.math.BigDecimal r9, long r10, java.text.DecimalFormat r12) {
        /*
            r8 = this;
            if (r9 != 0) goto L6
            r8.writeDecimalNull()
            return
        L6:
            if (r12 == 0) goto L10
            java.lang.String r9 = r12.format(r9)
            r8.writeRaw(r9)
            return
        L10:
            com.alibaba.fastjson2.JSONWriter$Context r12 = r8.context
            long r0 = r12.features
            long r10 = r10 | r0
            int r12 = r9.precision()
            boolean r0 = isWriteAsString(r9, r10)
            int r1 = r8.off
            int r2 = r1 + r12
            int r3 = r9.scale()
            int r3 = java.lang.Math.abs(r3)
            int r2 = r2 + r3
            int r2 = r2 + 7
            byte[] r3 = r8.bytes
            int r4 = r3.length
            if (r2 <= r4) goto L35
            byte[] r3 = r8.grow(r2)
        L35:
            r2 = 34
            if (r0 == 0) goto L3e
            int r4 = r1 + 1
            r3[r1] = r2
            r1 = r4
        L3e:
            com.alibaba.fastjson2.JSONWriter$Feature r4 = com.alibaba.fastjson2.JSONWriter.Feature.WriteBigDecimalAsPlain
            long r4 = r4.mask
            long r10 = r10 & r4
            r4 = 0
            int r10 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            r11 = 0
            if (r10 == 0) goto L4c
            r10 = 1
            goto L4d
        L4c:
            r10 = r11
        L4d:
            r4 = 19
            if (r12 >= r4) goto L74
            int r12 = r9.scale()
            if (r12 < 0) goto L74
            long r4 = com.alibaba.fastjson2.util.JDKUtils.FIELD_DECIMAL_INT_COMPACT_OFFSET
            r6 = -1
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 == 0) goto L74
            sun.misc.Unsafe r4 = com.alibaba.fastjson2.util.JDKUtils.UNSAFE
            long r5 = com.alibaba.fastjson2.util.JDKUtils.FIELD_DECIMAL_INT_COMPACT_OFFSET
            long r4 = r4.getLong(r9, r5)
            r6 = -9223372036854775808
            int r6 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r6 == 0) goto L74
            if (r10 != 0) goto L74
            int r9 = com.alibaba.fastjson2.util.IOUtils.writeDecimal(r3, r1, r4, r12)
            goto L8b
        L74:
            if (r10 == 0) goto L7b
            java.lang.String r9 = r9.toPlainString()
            goto L7f
        L7b:
            java.lang.String r9 = r9.toString()
        L7f:
            int r10 = r9.length()
            r9.getBytes(r11, r10, r3, r1)
            int r9 = r9.length()
            int r9 = r9 + r1
        L8b:
            if (r0 == 0) goto L92
            int r10 = r9 + 1
            r3[r9] = r2
            r9 = r10
        L92:
            r8.off = r9
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONWriterUTF8.writeDecimal(java.math.BigDecimal, long, java.text.DecimalFormat):void");
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeNameRaw(char[] cArr) {
        throw new JSONException("UnsupportedOperation");
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeNameRaw(char[] cArr, int i, int i2) {
        throw new JSONException("UnsupportedOperation");
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void write(Map<?, ?> map) {
        if (this.pretty != 0) {
            super.write(map);
            return;
        }
        if (map == null) {
            writeNull();
            return;
        }
        long j = this.context.features;
        if ((NONE_DIRECT_FEATURES & j) != 0) {
            this.context.getObjectWriter(map.getClass()).write(this, map, null, null, 0L);
            return;
        }
        if (this.off == this.bytes.length) {
            grow(this.off + 1);
        }
        byte[] bArr = this.bytes;
        int i = this.off;
        this.off = i + 1;
        bArr[i] = JSONB.Constants.BC_STR_UTF16;
        boolean z = true;
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            Object value = entry.getValue();
            if (value != null || (16 & j) != 0) {
                if (!z) {
                    if (this.off == this.bytes.length) {
                        grow0(this.off + 1);
                    }
                    byte[] bArr2 = this.bytes;
                    int i2 = this.off;
                    this.off = i2 + 1;
                    bArr2[i2] = 44;
                }
                Object key = entry.getKey();
                if (key instanceof String) {
                    writeString((String) key);
                } else {
                    writeAny(key);
                }
                if (this.off == this.bytes.length) {
                    grow0(this.off + 1);
                }
                byte[] bArr3 = this.bytes;
                int i3 = this.off;
                this.off = i3 + 1;
                bArr3[i3] = 58;
                if (value == null) {
                    writeNull();
                } else {
                    Class<?> cls = value.getClass();
                    if (cls == String.class) {
                        writeString((String) value);
                    } else if (cls == Integer.class) {
                        writeInt32((Integer) value);
                    } else if (cls == Long.class) {
                        writeInt64((Long) value);
                    } else if (cls == Boolean.class) {
                        writeBool(((Boolean) value).booleanValue());
                    } else if (cls == BigDecimal.class) {
                        writeDecimal((BigDecimal) value, 0L, null);
                    } else if (cls == JSONArray.class) {
                        write((JSONArray) value);
                    } else if (cls == JSONObject.class) {
                        write((JSONObject) value);
                    } else {
                        this.context.getObjectWriter(cls, cls).write(this, value, null, null, 0L);
                    }
                }
                z = false;
            }
        }
        if (this.off == this.bytes.length) {
            grow(this.off + 1);
        }
        byte[] bArr4 = this.bytes;
        int i4 = this.off;
        this.off = i4 + 1;
        bArr4[i4] = JSONB.Constants.BC_STR_UTF16BE;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void write(List list) {
        if (list == null) {
            writeArrayNull();
            return;
        }
        if ((this.context.features & 67309568) != 0) {
            this.context.getObjectWriter(list.getClass()).write(this, list, null, null, 0L);
            return;
        }
        if (this.off == this.bytes.length) {
            grow(this.off + 1);
        }
        byte[] bArr = this.bytes;
        int i = this.off;
        this.off = i + 1;
        bArr[i] = 91;
        boolean z = true;
        int i2 = 0;
        while (i2 < list.size()) {
            Object obj = list.get(i2);
            if (!z) {
                if (this.off == this.bytes.length) {
                    grow(this.off + 1);
                }
                byte[] bArr2 = this.bytes;
                int i3 = this.off;
                this.off = i3 + 1;
                bArr2[i3] = 44;
            }
            if (obj == null) {
                writeNull();
            } else {
                Class<?> cls = obj.getClass();
                if (cls == String.class) {
                    writeString((String) obj);
                } else if (cls == Integer.class) {
                    writeInt32((Integer) obj);
                } else if (cls == Long.class) {
                    writeInt64((Long) obj);
                } else if (cls == Boolean.class) {
                    writeBool(((Boolean) obj).booleanValue());
                } else if (cls == BigDecimal.class) {
                    writeDecimal((BigDecimal) obj, 0L, null);
                } else if (cls == JSONArray.class) {
                    write((JSONArray) obj);
                } else if (cls == JSONObject.class) {
                    write((JSONObject) obj);
                } else {
                    this.context.getObjectWriter(cls, cls).write(this, obj, null, null, 0L);
                }
            }
            i2++;
            z = false;
        }
        if (this.off == this.bytes.length) {
            grow(this.off + 1);
        }
        byte[] bArr3 = this.bytes;
        int i4 = this.off;
        this.off = i4 + 1;
        bArr3[i4] = 93;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeBool(boolean z) {
        int putBoolean;
        int i = this.off + 5;
        byte[] bArr = this.bytes;
        if (i > bArr.length) {
            bArr = grow(i);
        }
        int i2 = this.off;
        if ((this.context.features & 128) != 0) {
            putBoolean = i2 + 1;
            bArr[i2] = (byte) (z ? 49 : 48);
        } else {
            putBoolean = IOUtils.putBoolean(bArr, i2, z);
        }
        this.off = putBoolean;
    }

    public final String toString() {
        return new String(this.bytes, 0, this.off, StandardCharsets.UTF_8);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x002b  */
    @Override // com.alibaba.fastjson2.JSONWriter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int flushTo(java.io.OutputStream r5, java.nio.charset.Charset r6) throws java.io.IOException {
        /*
            r4 = this;
            int r0 = r4.off
            r1 = 0
            if (r0 != 0) goto L6
            return r1
        L6:
            if (r6 == 0) goto L49
            java.nio.charset.Charset r0 = java.nio.charset.StandardCharsets.UTF_8
            if (r6 == r0) goto L49
            java.nio.charset.Charset r0 = java.nio.charset.StandardCharsets.US_ASCII
            if (r6 != r0) goto L11
            goto L49
        L11:
            java.nio.charset.Charset r0 = java.nio.charset.StandardCharsets.ISO_8859_1
            if (r6 != r0) goto L37
            java.lang.invoke.MethodHandle r0 = com.alibaba.fastjson2.util.JDKUtils.METHOD_HANDLE_HAS_NEGATIVE
            if (r0 == 0) goto L28
            java.lang.invoke.MethodHandle r0 = com.alibaba.fastjson2.util.JDKUtils.METHOD_HANDLE_HAS_NEGATIVE     // Catch: java.lang.Throwable -> L28
            byte[] r2 = r4.bytes     // Catch: java.lang.Throwable -> L28
            int r3 = r2.length     // Catch: java.lang.Throwable -> L28
            java.lang.Boolean r0 = (java.lang.Boolean) r0.invoke(r2, r1, r3)     // Catch: java.lang.Throwable -> L28
            boolean r0 = r0.booleanValue()     // Catch: java.lang.Throwable -> L28
            goto L29
        L28:
            r0 = r1
        L29:
            if (r0 != 0) goto L37
            int r6 = r4.off
            byte[] r0 = r4.bytes
            int r2 = r4.off
            r5.write(r0, r1, r2)
            r4.off = r1
            return r6
        L37:
            java.lang.String r0 = new java.lang.String
            byte[] r2 = r4.bytes
            int r3 = r4.off
            r0.<init>(r2, r1, r3)
            byte[] r6 = r0.getBytes(r6)
            r5.write(r6)
            int r5 = r6.length
            return r5
        L49:
            int r6 = r4.off
            byte[] r0 = r4.bytes
            int r2 = r4.off
            r5.write(r0, r1, r2)
            r4.off = r1
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONWriterUTF8.flushTo(java.io.OutputStream, java.nio.charset.Charset):int");
    }
}
