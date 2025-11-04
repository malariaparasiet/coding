package com.alibaba.fastjson2;

import com.alibaba.fastjson2.JSONFactory;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.util.IOUtils;
import com.alibaba.fastjson2.util.JDKUtils;
import com.alibaba.fastjson2.util.NumberUtils;
import com.alibaba.fastjson2.util.StringUtils;
import com.alibaba.fastjson2.util.TypeUtils;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
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
import kotlin.text.Typography;
import org.bouncycastle.pqc.legacy.math.linearalgebra.Matrix;
import sun.misc.Unsafe;

/* loaded from: classes2.dex */
class JSONWriterUTF16 extends JSONWriter {
    static final long BYTE_VEC_64_DOUBLE_QUOTE = 9570295239278626L;
    static final long BYTE_VEC_64_SINGLE_QUOTE = 10977691597996071L;
    static final int[] HEX256;
    static final int QUOTE2_COLON;
    static final int QUOTE_COLON;
    static final long REF_0;
    static final long REF_1;
    protected final long byteVectorQuote;
    final JSONFactory.CacheItem cacheItem;
    protected char[] chars;

    static long expand(long j) {
        return ((j & 4278190080L) << 24) | (255 & j) | ((65280 & j) << 8) | ((16711680 & j) << 16);
    }

    static {
        int[] iArr = new int[256];
        int i = 0;
        while (i < 16) {
            short s = (short) (i < 10 ? i + 48 : i + 87);
            int i2 = 0;
            while (i2 < 16) {
                iArr[(i << 4) + i2] = (((short) (i2 < 10 ? i2 + 48 : i2 + 87)) << 16) | s;
                i2++;
            }
            i++;
        }
        if (JDKUtils.BIG_ENDIAN) {
            for (int i3 = 0; i3 < 256; i3++) {
                iArr[i3] = Integer.reverseBytes(iArr[i3] << 8);
            }
        }
        HEX256 = iArr;
        char[] cArr = {'{', Typography.quote, Typography.dollar, 'r', 'e', 'f', Typography.quote, ':'};
        REF_0 = JDKUtils.UNSAFE.getLong(cArr, JDKUtils.ARRAY_CHAR_BASE_OFFSET);
        REF_1 = JDKUtils.UNSAFE.getLong(cArr, JDKUtils.ARRAY_CHAR_BASE_OFFSET + 8);
        QUOTE2_COLON = JDKUtils.UNSAFE.getInt(cArr, JDKUtils.ARRAY_CHAR_BASE_OFFSET + 12);
        cArr[6] = '\'';
        QUOTE_COLON = JDKUtils.UNSAFE.getInt(cArr, JDKUtils.ARRAY_CHAR_BASE_OFFSET + 12);
    }

    JSONWriterUTF16(JSONWriter.Context context) {
        super(context, null, false, StandardCharsets.UTF_16);
        JSONFactory.CacheItem cacheItem = JSONFactory.CACHE_ITEMS[System.identityHashCode(Thread.currentThread()) & (JSONFactory.CACHE_ITEMS.length - 1)];
        this.cacheItem = cacheItem;
        char[] andSet = JSONFactory.CHARS_UPDATER.getAndSet(cacheItem, null);
        this.chars = andSet == null ? new char[8192] : andSet;
        this.byteVectorQuote = this.useSingleQuote ? -2821266740684990248L : -2459565876494606883L;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeNull() {
        int i = this.off;
        int i2 = i + 4;
        char[] cArr = this.chars;
        if (i2 > cArr.length) {
            cArr = grow(i2);
        }
        IOUtils.putNULL(cArr, i);
        this.off = i2;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void flushTo(Writer writer) {
        try {
            int i = this.off;
            if (i > 0) {
                writer.write(this.chars, 0, i);
                this.off = 0;
            }
        } catch (IOException e) {
            throw new JSONException("flushTo error", e);
        }
    }

    @Override // com.alibaba.fastjson2.JSONWriter, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        char[] cArr = this.chars;
        if (cArr.length > 8388608) {
            return;
        }
        JSONFactory.CHARS_UPDATER.lazySet(this.cacheItem, cArr);
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    protected final void write0(char c) {
        int i = this.off;
        char[] cArr = this.chars;
        if (i == cArr.length) {
            cArr = grow(i + 1);
        }
        cArr[i] = c;
        this.off = i + 1;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeColon() {
        int i = this.off;
        char[] cArr = this.chars;
        if (i == cArr.length) {
            cArr = grow(i + 1);
        }
        cArr[i] = ':';
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
        char[] cArr = this.chars;
        int i3 = i2 + 3 + (this.pretty * this.level);
        if (i3 > cArr.length) {
            cArr = grow(i3);
        }
        int i4 = i2 + 1;
        cArr[i2] = '{';
        if (this.pretty != 0) {
            i4 = indent(cArr, i4);
        }
        this.off = i4;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void endObject() {
        this.level--;
        int i = this.off;
        int i2 = i + 1 + (this.pretty == 0 ? 0 : (this.pretty * this.level) + 1);
        char[] cArr = this.chars;
        if (i2 > cArr.length) {
            cArr = grow(i2);
        }
        if (this.pretty != 0) {
            i = indent(cArr, i);
        }
        cArr[i] = '}';
        this.off = i + 1;
        this.startObject = false;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeComma() {
        this.startObject = false;
        int i = this.off;
        int i2 = i + 2 + (this.pretty * this.level);
        char[] cArr = this.chars;
        if (i2 > cArr.length) {
            cArr = grow(i2);
        }
        int i3 = i + 1;
        cArr[i] = ',';
        if (this.pretty != 0) {
            i3 = indent(cArr, i3);
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
        char[] cArr = this.chars;
        if (i3 > cArr.length) {
            cArr = grow(i3);
        }
        int i4 = i2 + 1;
        cArr[i2] = '[';
        if (this.pretty != 0) {
            i4 = indent(cArr, i4);
        }
        this.off = i4;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void endArray() {
        this.level--;
        int i = this.off;
        int i2 = i + 1 + (this.pretty == 0 ? 0 : (this.pretty * this.level) + 1);
        char[] cArr = this.chars;
        if (i2 > cArr.length) {
            cArr = grow(i2);
        }
        if (this.pretty != 0) {
            i = indent(cArr, i);
        }
        cArr[i] = ']';
        this.off = i + 1;
        this.startObject = false;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeString(List<String> list) {
        if (this.pretty != 0) {
            super.writeString(list);
            return;
        }
        if (this.off == this.chars.length) {
            grow(this.off + 1);
        }
        char[] cArr = this.chars;
        int i = this.off;
        this.off = i + 1;
        cArr[i] = '[';
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (i2 != 0) {
                if (this.off == this.chars.length) {
                    grow(this.off + 1);
                }
                char[] cArr2 = this.chars;
                int i3 = this.off;
                this.off = i3 + 1;
                cArr2[i3] = ',';
            }
            writeString(list.get(i2));
        }
        if (this.off == this.chars.length) {
            grow(this.off + 1);
        }
        char[] cArr3 = this.chars;
        int i4 = this.off;
        this.off = i4 + 1;
        cArr3[i4] = ']';
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeStringLatin1(byte[] bArr) {
        int i;
        if ((this.context.features & 34359738368L) != 0) {
            writeStringLatin1BrowserSecure(bArr);
            return;
        }
        int i2 = this.off;
        char[] cArr = this.chars;
        int length = bArr.length + i2 + 2;
        if (length >= cArr.length) {
            cArr = grow(length);
        }
        int i3 = i2 + 1;
        cArr[i2] = this.quote;
        long j = this.byteVectorQuote;
        int length2 = bArr.length & (-8);
        int i4 = 0;
        while (i4 < length2) {
            long longLE = IOUtils.getLongLE(bArr, i4);
            if (!StringUtils.noneEscaped(longLE, j)) {
                break;
            }
            IOUtils.putLongLE(cArr, i3, expand(longLE));
            IOUtils.putLongLE(cArr, i3 + 4, expand(longLE >>> 32));
            i3 += 8;
            i4 += 8;
        }
        int i5 = i4;
        while (true) {
            i = i3;
            if (i5 < bArr.length) {
                byte b = bArr[i5];
                if (b == 92 || b == this.quote || b < 32) {
                    break;
                }
                i3 = i + 1;
                cArr[i] = (char) b;
                i5++;
            } else {
                cArr[i] = this.quote;
                this.off = i + 1;
                return;
            }
        }
        int length3 = length + (bArr.length * 5);
        if (length3 >= cArr.length) {
            cArr = grow(length3);
        }
        this.off = StringUtils.writeLatin1EscapedRest(cArr, i, bArr, i5, this.quote, this.context.features);
    }

    protected final void writeStringLatin1BrowserSecure(byte[] bArr) {
        int i = this.off;
        int length = bArr.length + i + 2;
        if (length >= this.chars.length) {
            grow(length);
        }
        char[] cArr = this.chars;
        int i2 = i + 1;
        cArr[i] = this.quote;
        int length2 = bArr.length;
        int i3 = 0;
        while (i3 < length2) {
            byte b = bArr[i3];
            if (b != 92 && b != this.quote && b >= 32 && b != 60 && b != 62 && b != 40 && b != 41) {
                cArr[i2] = (char) b;
                i3++;
                i2++;
            } else {
                this.off = i;
                writeStringEscape(bArr);
                return;
            }
        }
        cArr[i2] = this.quote;
        this.off = i2 + 1;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeStringUTF16(byte[] bArr) {
        if (bArr == null) {
            writeStringNull();
            return;
        }
        long j = 0;
        if ((this.context.features & (JSONWriter.Feature.BrowserSecure.mask | JSONWriter.Feature.EscapeNoneAscii.mask)) != 0) {
            writeStringUTF16BrowserSecure(bArr);
            return;
        }
        int i = this.off;
        int length = bArr.length + i + 2;
        if (length >= this.chars.length) {
            grow(length);
        }
        long j2 = this.byteVectorQuote;
        char[] cArr = this.chars;
        int i2 = i + 1;
        cArr[i] = this.quote;
        int length2 = bArr.length >> 1;
        int i3 = 0;
        while (i3 < length2) {
            int i4 = i3 + 8;
            if (i4 < length2) {
                long longLE = IOUtils.getLongLE(bArr, i3 << 1);
                long longLE2 = IOUtils.getLongLE(bArr, (i3 + 4) << 1);
                if (((longLE | longLE2) & (-71777214294589696L)) == j && StringUtils.noneEscaped((longLE << 8) | longLE2, j2)) {
                    IOUtils.putLongLE(cArr, i2, longLE);
                    IOUtils.putLongLE(cArr, i2 + 4, longLE2);
                    i2 += 8;
                    i3 = i4;
                    j = 0;
                }
            }
            int i5 = i3 + 1;
            char c = IOUtils.getChar(bArr, i3);
            if (c != '\\' && c != this.quote && c >= ' ') {
                cArr[i2] = c;
                i2++;
                i3 = i5;
                j = 0;
            } else {
                writeStringEscapeUTF16(bArr);
                return;
            }
        }
        cArr[i2] = this.quote;
        this.off = i2 + 1;
    }

    final void writeStringBrowserSecure(char[] cArr) {
        int i = 0;
        boolean z = (this.context.features & JSONWriter.Feature.EscapeNoneAscii.mask) != 0;
        int i2 = this.off;
        int length = cArr.length + i2 + 2;
        if (length >= this.chars.length) {
            grow(length);
        }
        char[] cArr2 = this.chars;
        int i3 = i2 + 1;
        cArr2[i2] = this.quote;
        int length2 = cArr.length;
        while (i < length2) {
            char c = IOUtils.getChar(cArr, i);
            if (c != '\\' && c != this.quote && c >= ' ' && c != '<' && c != '>' && c != '(' && c != ')' && (!z || c <= 127)) {
                cArr2[i3] = c;
                i++;
                i3++;
            } else {
                writeStringEscape(cArr);
                return;
            }
        }
        cArr2[i3] = this.quote;
        this.off = i3 + 1;
    }

    final void writeStringUTF16BrowserSecure(byte[] bArr) {
        int i = 0;
        boolean z = (this.context.features & JSONWriter.Feature.EscapeNoneAscii.mask) != 0;
        int i2 = this.off;
        int length = bArr.length + i2 + 2;
        if (length >= this.chars.length) {
            grow(length);
        }
        char[] cArr = this.chars;
        int i3 = i2 + 1;
        cArr[i2] = this.quote;
        int length2 = bArr.length >> 1;
        while (i < length2) {
            char c = IOUtils.getChar(bArr, i);
            if (c != '\\' && c != this.quote && c >= ' ' && c != '<' && c != '>' && c != '(' && c != ')' && (!z || c <= 127)) {
                cArr[i3] = c;
                i++;
                i3++;
            } else {
                writeStringEscapeUTF16(bArr);
                return;
            }
        }
        cArr[i3] = this.quote;
        this.off = i3 + 1;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeString(String str) {
        if (str == null) {
            writeStringNull();
            return;
        }
        boolean z = (this.context.features & JSONWriter.Feature.EscapeNoneAscii.mask) != 0;
        boolean z2 = (this.context.features & JSONWriter.Feature.BrowserSecure.mask) != 0;
        char c = this.quote;
        int length = str.length();
        int i = this.off + length + 2;
        if (i >= this.chars.length) {
            grow(i);
        }
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str.charAt(i2);
            if (charAt == '\\' || charAt == c || charAt < ' ' || ((z2 && (charAt == '<' || charAt == '>' || charAt == '(' || charAt == ')')) || (z && charAt > 127))) {
                writeStringEscape(str);
                return;
            }
        }
        int i3 = this.off;
        char[] cArr = this.chars;
        int i4 = i3 + 1;
        cArr[i3] = c;
        str.getChars(0, length, cArr, i4);
        int i5 = i4 + length;
        cArr[i5] = c;
        this.off = i5 + 1;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:17:0x0053. Please report as an issue. */
    protected final void writeStringEscape(String str) {
        int i;
        int length = str.length();
        char c = this.quote;
        boolean z = (this.context.features & JSONWriter.Feature.EscapeNoneAscii.mask) != 0;
        boolean z2 = (this.context.features & JSONWriter.Feature.BrowserSecure.mask) != 0;
        int i2 = this.off;
        ensureCapacityInternal((length * 6) + i2 + 2);
        char[] cArr = this.chars;
        int i3 = i2 + 1;
        cArr[i2] = c;
        for (int i4 = 0; i4 < length; i4++) {
            char charAt = str.charAt(i4);
            if (charAt != '\"') {
                if (charAt != '<' && charAt != '>') {
                    if (charAt != '\\') {
                        switch (charAt) {
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
                                StringUtils.writeU4Hex2(cArr, i3, charAt);
                                i3 += 6;
                                break;
                            case '\b':
                            case '\t':
                            case '\n':
                            case '\f':
                            case '\r':
                                break;
                            default:
                                switch (charAt) {
                                    case '\'':
                                        break;
                                    case '(':
                                    case ')':
                                        break;
                                    default:
                                        if (z && charAt > 127) {
                                            StringUtils.writeU4HexU(cArr, i3, charAt);
                                            i3 += 6;
                                            break;
                                        } else {
                                            i = i3 + 1;
                                            cArr[i3] = charAt;
                                            i3 = i;
                                            break;
                                        }
                                        break;
                                }
                        }
                    }
                    StringUtils.writeEscapedChar(cArr, i3, charAt);
                    i3 += 2;
                }
                if (z2) {
                    StringUtils.writeU4HexU(cArr, i3, charAt);
                    i3 += 6;
                } else {
                    i = i3 + 1;
                    cArr[i3] = charAt;
                    i3 = i;
                }
            }
            if (charAt == c) {
                cArr[i3] = '\\';
                i3++;
            }
            i = i3 + 1;
            cArr[i3] = charAt;
            i3 = i;
        }
        cArr[i3] = c;
        this.off = i3 + 1;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:17:0x0057. Please report as an issue. */
    protected final void writeStringEscapeUTF16(byte[] bArr) {
        int i;
        int length = bArr.length;
        char c = this.quote;
        boolean z = (this.context.features & JSONWriter.Feature.EscapeNoneAscii.mask) != 0;
        boolean z2 = (this.context.features & JSONWriter.Feature.BrowserSecure.mask) != 0;
        int i2 = this.off;
        ensureCapacityInternal((length * 6) + i2 + 2);
        char[] cArr = this.chars;
        int i3 = i2 + 1;
        cArr[i2] = c;
        for (int i4 = 0; i4 < length; i4 += 2) {
            char c2 = JDKUtils.UNSAFE.getChar(bArr, Unsafe.ARRAY_BYTE_BASE_OFFSET + i4);
            if (c2 != '\"') {
                if (c2 != '<' && c2 != '>') {
                    if (c2 != '\\') {
                        switch (c2) {
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
                                StringUtils.writeU4Hex2(cArr, i3, c2);
                                i3 += 6;
                                break;
                            case '\b':
                            case '\t':
                            case '\n':
                            case '\f':
                            case '\r':
                                break;
                            default:
                                switch (c2) {
                                    case '\'':
                                        break;
                                    case '(':
                                    case ')':
                                        break;
                                    default:
                                        if (z && c2 > 127) {
                                            StringUtils.writeU4HexU(cArr, i3, c2);
                                            i3 += 6;
                                            break;
                                        } else {
                                            i = i3 + 1;
                                            cArr[i3] = c2;
                                            i3 = i;
                                            break;
                                        }
                                        break;
                                }
                        }
                    }
                    StringUtils.writeEscapedChar(cArr, i3, c2);
                    i3 += 2;
                }
                if (z2) {
                    StringUtils.writeU4HexU(cArr, i3, c2);
                    i3 += 6;
                } else {
                    i = i3 + 1;
                    cArr[i3] = c2;
                    i3 = i;
                }
            }
            if (c2 == c) {
                cArr[i3] = '\\';
                i3++;
            }
            i = i3 + 1;
            cArr[i3] = c2;
            i3 = i;
        }
        cArr[i3] = c;
        this.off = i3 + 1;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:18:0x004f. Please report as an issue. */
    protected final void writeStringEscape(char[] cArr) {
        int i;
        int length = cArr.length;
        char c = this.quote;
        boolean z = (this.context.features & JSONWriter.Feature.EscapeNoneAscii.mask) != 0;
        boolean z2 = (this.context.features & JSONWriter.Feature.BrowserSecure.mask) != 0;
        int i2 = this.off;
        ensureCapacityInternal((length * 6) + i2 + 2);
        char[] cArr2 = this.chars;
        int i3 = i2 + 1;
        cArr2[i2] = c;
        for (char c2 : cArr) {
            if (c2 != '\"') {
                if (c2 != '<' && c2 != '>') {
                    if (c2 != '\\') {
                        switch (c2) {
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
                                StringUtils.writeU4Hex2(cArr2, i3, c2);
                                i3 += 6;
                                break;
                            case '\b':
                            case '\t':
                            case '\n':
                            case '\f':
                            case '\r':
                                break;
                            default:
                                switch (c2) {
                                    case '\'':
                                        break;
                                    case '(':
                                    case ')':
                                        break;
                                    default:
                                        if (z && c2 > 127) {
                                            StringUtils.writeU4HexU(cArr2, i3, c2);
                                            i3 += 6;
                                            break;
                                        } else {
                                            i = i3 + 1;
                                            cArr2[i3] = c2;
                                            i3 = i;
                                            break;
                                        }
                                        break;
                                }
                        }
                    }
                    StringUtils.writeEscapedChar(cArr2, i3, c2);
                    i3 += 2;
                }
                if (z2) {
                    StringUtils.writeU4HexU(cArr2, i3, c2);
                    i3 += 6;
                } else {
                    i = i3 + 1;
                    cArr2[i3] = c2;
                    i3 = i;
                }
            }
            if (c2 == c) {
                cArr2[i3] = '\\';
                i3++;
            }
            i = i3 + 1;
            cArr2[i3] = c2;
            i3 = i;
        }
        cArr2[i3] = c;
        this.off = i3 + 1;
    }

    protected final void writeStringEscape(byte[] bArr) {
        int i = this.off;
        char[] cArr = this.chars;
        int length = (bArr.length * 6) + i + 2;
        if (length >= cArr.length) {
            cArr = grow(length);
        }
        char[] cArr2 = cArr;
        char c = this.quote;
        cArr2[i] = c;
        this.off = StringUtils.writeLatin1EscapedRest(cArr2, i + 1, bArr, 0, c, this.context.features);
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeString(char[] cArr, int i, int i2, boolean z) {
        int i3;
        boolean z2 = (this.context.features & JSONWriter.Feature.EscapeNoneAscii.mask) != 0;
        char c = this.quote;
        int i4 = this.off;
        int i5 = (z ? i4 + 2 : i4) + (z2 ? i2 * 6 : i2 * 2);
        char[] cArr2 = this.chars;
        if (i5 - cArr2.length > 0) {
            cArr2 = grow(i5);
        }
        if (z) {
            cArr2[i4] = c;
            i4++;
        }
        while (i < i2) {
            char c2 = cArr[i];
            if (c2 == '\"' || c2 == '\'') {
                if (c2 == c) {
                    cArr2[i4] = '\\';
                    i4++;
                }
                i3 = i4 + 1;
                cArr2[i4] = c2;
            } else {
                if (c2 != '\\') {
                    switch (c2) {
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
                            StringUtils.writeU4Hex2(cArr2, i4, c2);
                            break;
                        case '\b':
                        case '\t':
                        case '\n':
                        case '\f':
                        case '\r':
                            break;
                        default:
                            if (z2 && c2 > 127) {
                                StringUtils.writeU4HexU(cArr2, i4, c2);
                                break;
                            } else {
                                i3 = i4 + 1;
                                cArr2[i4] = c2;
                                break;
                            }
                            break;
                    }
                    i4 += 6;
                    i++;
                }
                StringUtils.writeEscapedChar(cArr2, i4, c2);
                i4 += 2;
                i++;
            }
            i4 = i3;
            i++;
        }
        if (z) {
            cArr2[i4] = c;
            i4++;
        }
        this.off = i4;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeString(String[] strArr) {
        if (this.pretty != 0 || strArr == null) {
            super.writeString(strArr);
            return;
        }
        if (this.off == this.chars.length) {
            grow(this.off + 1);
        }
        char[] cArr = this.chars;
        int i = this.off;
        this.off = i + 1;
        cArr[i] = '[';
        for (int i2 = 0; i2 < strArr.length; i2++) {
            if (i2 != 0) {
                if (this.off == this.chars.length) {
                    grow(this.off + 1);
                }
                char[] cArr2 = this.chars;
                int i3 = this.off;
                this.off = i3 + 1;
                cArr2[i3] = ',';
            }
            writeString(strArr[i2]);
        }
        if (this.off == this.chars.length) {
            grow(this.off + 1);
        }
        char[] cArr3 = this.chars;
        int i4 = this.off;
        this.off = i4 + 1;
        cArr3[i4] = ']';
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeReference(String str) {
        this.lastReference = str;
        int i = this.off;
        char[] cArr = this.chars;
        int i2 = i + 9;
        if (i2 > cArr.length) {
            cArr = grow(i2);
        }
        char[] cArr2 = cArr;
        long j = (i << 1) + JDKUtils.ARRAY_BYTE_BASE_OFFSET;
        JDKUtils.UNSAFE.putLong(cArr2, j, REF_0);
        JDKUtils.UNSAFE.putLong(cArr2, j + 8, REF_1);
        this.off = i + 8;
        writeString(str);
        int i3 = this.off;
        char[] cArr3 = this.chars;
        if (i3 == cArr3.length) {
            cArr3 = grow(i3 + 1);
        }
        cArr3[i3] = '}';
        this.off = i3 + 1;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeBase64(byte[] bArr) {
        if (bArr == null) {
            writeArrayNull();
            return;
        }
        int i = this.off;
        ensureCapacityInternal(((((bArr.length - 1) / 3) + 1) << 2) + i + 2);
        char[] cArr = this.chars;
        int i2 = i + 1;
        cArr[i] = this.quote;
        int length = (bArr.length / 3) * 3;
        int i3 = 0;
        while (i3 < length) {
            int i4 = i3 + 2;
            int i5 = ((bArr[i3 + 1] & UByte.MAX_VALUE) << 8) | ((bArr[i3] & UByte.MAX_VALUE) << 16);
            i3 += 3;
            int i6 = i5 | (bArr[i4] & UByte.MAX_VALUE);
            cArr[i2] = JSONFactory.CA[(i6 >>> 18) & 63];
            cArr[i2 + 1] = JSONFactory.CA[(i6 >>> 12) & 63];
            cArr[i2 + 2] = JSONFactory.CA[(i6 >>> 6) & 63];
            cArr[i2 + 3] = JSONFactory.CA[i6 & 63];
            i2 += 4;
        }
        int length2 = bArr.length - length;
        if (length2 > 0) {
            int i7 = ((bArr[length] & UByte.MAX_VALUE) << 10) | (length2 == 2 ? (bArr[bArr.length - 1] & UByte.MAX_VALUE) << 2 : 0);
            cArr[i2] = JSONFactory.CA[i7 >> 12];
            cArr[i2 + 1] = JSONFactory.CA[(i7 >>> 6) & 63];
            cArr[i2 + 2] = length2 == 2 ? JSONFactory.CA[i7 & 63] : '=';
            cArr[i2 + 3] = '=';
            i2 += 4;
        }
        cArr[i2] = this.quote;
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
        char[] cArr = this.chars;
        int i2 = length + i + 2;
        if (i2 > cArr.length) {
            cArr = grow(i2);
        }
        cArr[i] = 'x';
        cArr[i + 1] = '\'';
        int i3 = i + 2;
        for (byte b : bArr) {
            int i4 = (b & UByte.MAX_VALUE) >> 4;
            int i5 = b & 15;
            int i6 = 48;
            cArr[i3] = (char) (i4 + (i4 < 10 ? 48 : 55));
            int i7 = i3 + 1;
            if (i5 >= 10) {
                i6 = 55;
            }
            cArr[i7] = (char) (i5 + i6);
            i3 += 2;
        }
        cArr[i3] = '\'';
        this.off = i3 + 1;
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
        char[] cArr = this.chars;
        if (i2 > cArr.length) {
            cArr = grow(i2);
        }
        if (isWriteAsString) {
            cArr[i] = Typography.quote;
            i++;
        }
        bigInteger2.getChars(0, length, cArr, i);
        int i3 = i + length;
        if (isWriteAsString) {
            cArr[i3] = Typography.quote;
            i3++;
        }
        this.off = i3;
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
            char[] r3 = r8.chars
            int r4 = r3.length
            if (r2 <= r4) goto L35
            char[] r3 = r8.grow(r2)
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
            r9.getChars(r11, r10, r3, r1)
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
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONWriterUTF16.writeDecimal(java.math.BigDecimal, long, java.text.DecimalFormat):void");
    }

    static void putLong(char[] cArr, int i, int i2, int i3) {
        int[] iArr = HEX256;
        long j = (iArr[i3 & 255] << 32) | iArr[i2 & 255];
        Unsafe unsafe = JDKUtils.UNSAFE;
        long j2 = JDKUtils.ARRAY_CHAR_BASE_OFFSET + (i << 1);
        if (JDKUtils.BIG_ENDIAN) {
            j = Long.reverseBytes(j << 8);
        }
        unsafe.putLong(cArr, j2, j);
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeUUID(UUID uuid) {
        if (uuid == null) {
            writeNull();
            return;
        }
        long mostSignificantBits = uuid.getMostSignificantBits();
        long leastSignificantBits = uuid.getLeastSignificantBits();
        int i = this.off + 38;
        char[] cArr = this.chars;
        if (i > cArr.length) {
            cArr = grow(i);
        }
        int i2 = this.off;
        cArr[i2] = Typography.quote;
        putLong(cArr, i2 + 1, (int) (mostSignificantBits >> 56), (int) (mostSignificantBits >> 48));
        putLong(cArr, i2 + 5, (int) (mostSignificantBits >> 40), (int) (mostSignificantBits >> 32));
        cArr[i2 + 9] = '-';
        int i3 = (int) mostSignificantBits;
        putLong(cArr, i2 + 10, i3 >> 24, i3 >> 16);
        cArr[i2 + 14] = '-';
        putLong(cArr, i2 + 15, i3 >> 8, i3);
        cArr[i2 + 19] = '-';
        putLong(cArr, i2 + 20, (int) (leastSignificantBits >> 56), (int) (leastSignificantBits >> 48));
        cArr[i2 + 24] = '-';
        putLong(cArr, i2 + 25, (int) (leastSignificantBits >> 40), (int) (leastSignificantBits >> 32));
        int i4 = (int) leastSignificantBits;
        putLong(cArr, i2 + 29, i4 >> 24, i4 >> 16);
        putLong(cArr, i2 + 33, i4 >> 8, i4);
        cArr[i2 + 37] = Typography.quote;
        this.off += 38;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeRaw(String str) {
        int length = str.length();
        int i = this.off;
        char[] cArr = this.chars;
        int i2 = i + length;
        if (i2 > cArr.length) {
            cArr = grow(i2);
        }
        str.getChars(0, length, cArr, i);
        this.off = i2;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeRaw(char[] cArr, int i, int i2) {
        int i3 = this.off;
        char[] cArr2 = this.chars;
        int i4 = i3 + i2;
        if (i4 > cArr2.length) {
            cArr2 = grow(i4);
        }
        System.arraycopy(cArr, i, cArr2, i3, i2);
        this.off = i4;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeChar(char c) {
        int i;
        int i2 = this.off;
        char[] cArr = this.chars;
        int i3 = i2 + 8;
        if (i3 > cArr.length) {
            cArr = grow(i3);
        }
        int i4 = i2 + 1;
        cArr[i2] = this.quote;
        if (c == '\"' || c == '\'') {
            if (c == this.quote) {
                cArr[i4] = '\\';
                i4 = i2 + 2;
            }
            i = i4 + 1;
            cArr[i4] = c;
        } else {
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
                        cArr[i4] = '\\';
                        cArr[i2 + 2] = 'u';
                        cArr[i2 + 3] = '0';
                        cArr[i2 + 4] = '0';
                        cArr[i2 + 5] = '0';
                        cArr[i2 + 6] = (char) (c + '0');
                        i = i2 + 7;
                        break;
                    case '\b':
                    case '\t':
                    case '\n':
                    case '\f':
                    case '\r':
                        break;
                    case 11:
                    case 14:
                    case 15:
                        cArr[i4] = '\\';
                        cArr[i2 + 2] = 'u';
                        cArr[i2 + 3] = '0';
                        cArr[i2 + 4] = '0';
                        cArr[i2 + 5] = '0';
                        cArr[i2 + 6] = (char) (c + 'W');
                        i = i2 + 7;
                        break;
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
                        cArr[i4] = '\\';
                        cArr[i2 + 2] = 'u';
                        cArr[i2 + 3] = '0';
                        cArr[i2 + 4] = '0';
                        cArr[i2 + 5] = '1';
                        cArr[i2 + 6] = (char) (c + ' ');
                        i = i2 + 7;
                        break;
                    case 26:
                    case 27:
                    case 28:
                    case 29:
                    case 30:
                    case 31:
                        cArr[i4] = '\\';
                        cArr[i2 + 2] = 'u';
                        cArr[i2 + 3] = '0';
                        cArr[i2 + 4] = '0';
                        cArr[i2 + 5] = '1';
                        cArr[i2 + 6] = (char) (c + 'G');
                        i = i2 + 7;
                        break;
                    default:
                        i = i2 + 2;
                        cArr[i4] = c;
                        break;
                }
            }
            StringUtils.writeEscapedChar(cArr, i4, c);
            i = i2 + 3;
        }
        cArr[i] = this.quote;
        this.off = i + 1;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeRaw(char c) {
        if (this.off == this.chars.length) {
            grow0(this.off + 1);
        }
        char[] cArr = this.chars;
        int i = this.off;
        this.off = i + 1;
        cArr[i] = c;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeRaw(char c, char c2) {
        int i = this.off;
        char[] cArr = this.chars;
        int i2 = i + 2;
        if (i2 > cArr.length) {
            cArr = grow(i2);
        }
        cArr[i] = c;
        cArr[i + 1] = c2;
        this.off = i2;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeNameRaw(char[] cArr) {
        int i = this.off;
        int length = cArr.length + i + 2 + (this.pretty * this.level);
        char[] cArr2 = this.chars;
        if (length > cArr2.length) {
            cArr2 = grow(length);
        }
        if (this.startObject) {
            this.startObject = false;
        } else {
            int i2 = i + 1;
            cArr2[i] = ',';
            i = this.pretty != 0 ? indent(cArr2, i2) : i2;
        }
        System.arraycopy(cArr, 0, cArr2, i, cArr.length);
        this.off = i + cArr.length;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeName2Raw(long j) {
        int i = this.off;
        int i2 = i + 10 + (this.pretty * this.level);
        char[] cArr = this.chars;
        if (i2 > cArr.length) {
            cArr = grow(i2);
        }
        if (this.startObject) {
            this.startObject = false;
        } else {
            int i3 = i + 1;
            cArr[i] = ',';
            i = this.pretty != 0 ? indent(cArr, i3) : i3;
        }
        putLong(cArr, i, j);
        this.off = i + 5;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeName3Raw(long j) {
        int i = this.off;
        int i2 = i + 10 + (this.pretty * this.level);
        char[] cArr = this.chars;
        if (i2 > cArr.length) {
            cArr = grow(i2);
        }
        if (this.startObject) {
            this.startObject = false;
        } else {
            int i3 = i + 1;
            cArr[i] = ',';
            i = this.pretty != 0 ? indent(cArr, i3) : i3;
        }
        putLong(cArr, i, j);
        this.off = i + 6;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeName4Raw(long j) {
        int i = this.off;
        int i2 = i + 10 + (this.pretty * this.level);
        char[] cArr = this.chars;
        if (i2 > cArr.length) {
            cArr = grow(i2);
        }
        if (this.startObject) {
            this.startObject = false;
        } else {
            int i3 = i + 1;
            cArr[i] = ',';
            i = this.pretty != 0 ? indent(cArr, i3) : i3;
        }
        putLong(cArr, i, j);
        this.off = i + 7;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeName5Raw(long j) {
        int i = this.off;
        int i2 = i + 10 + (this.pretty * this.level);
        char[] cArr = this.chars;
        if (i2 > cArr.length) {
            cArr = grow(i2);
        }
        if (this.startObject) {
            this.startObject = false;
        } else {
            int i3 = i + 1;
            cArr[i] = ',';
            i = this.pretty != 0 ? indent(cArr, i3) : i3;
        }
        putLong(cArr, i, j);
        this.off = i + 8;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeName6Raw(long j) {
        int i = this.off;
        int i2 = i + 11 + (this.pretty * this.level);
        char[] cArr = this.chars;
        if (i2 > cArr.length) {
            cArr = grow(i2);
        }
        if (this.startObject) {
            this.startObject = false;
        } else {
            int i3 = i + 1;
            cArr[i] = ',';
            i = this.pretty != 0 ? indent(cArr, i3) : i3;
        }
        putLong(cArr, i, j);
        cArr[i + 8] = ':';
        this.off = i + 9;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeName7Raw(long j) {
        int i = this.off;
        int i2 = i + 12 + (this.pretty * this.level);
        char[] cArr = this.chars;
        if (i2 > cArr.length) {
            cArr = grow(i2);
        }
        if (this.startObject) {
            this.startObject = false;
        } else {
            int i3 = i + 1;
            cArr[i] = ',';
            i = this.pretty != 0 ? indent(cArr, i3) : i3;
        }
        putLong(cArr, i, j);
        IOUtils.putIntUnaligned(cArr, i + 8, this.useSingleQuote ? QUOTE_COLON : QUOTE2_COLON);
        this.off = i + 10;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeName8Raw(long j) {
        int i = this.off;
        int i2 = i + 13 + (this.pretty * this.level);
        char[] cArr = this.chars;
        if (i2 > cArr.length) {
            cArr = grow(i2);
        }
        if (this.startObject) {
            this.startObject = false;
        } else {
            int i3 = i + 1;
            cArr[i] = ',';
            i = this.pretty != 0 ? indent(cArr, i3) : i3;
        }
        cArr[i] = this.quote;
        putLong(cArr, i + 1, j);
        IOUtils.putIntUnaligned(cArr, i + 9, this.useSingleQuote ? QUOTE_COLON : QUOTE2_COLON);
        this.off = i + 11;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeName9Raw(long j, int i) {
        int i2 = this.off;
        int i3 = i2 + 14 + (this.pretty * this.level);
        char[] cArr = this.chars;
        if (i3 > cArr.length) {
            cArr = grow(i3);
        }
        if (this.startObject) {
            this.startObject = false;
        } else {
            int i4 = i2 + 1;
            cArr[i2] = ',';
            i2 = this.pretty != 0 ? indent(cArr, i4) : i4;
        }
        putLong(cArr, i2, j, i);
        this.off = i2 + 12;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeName10Raw(long j, long j2) {
        long j3;
        long j4;
        int i;
        int i2 = this.off;
        int i3 = i2 + 18 + (this.pretty * this.level);
        char[] cArr = this.chars;
        if (i3 > cArr.length) {
            cArr = grow(i3);
        }
        char[] cArr2 = cArr;
        if (this.startObject) {
            this.startObject = false;
        } else {
            int i4 = i2 + 1;
            cArr2[i2] = ',';
            if (this.pretty != 0) {
                i2 = indent(cArr2, i4);
            } else {
                j3 = j;
                j4 = j2;
                i = i4;
                putLong(cArr2, i, j3, j4);
                this.off = i + 13;
            }
        }
        j3 = j;
        j4 = j2;
        i = i2;
        putLong(cArr2, i, j3, j4);
        this.off = i + 13;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeName11Raw(long j, long j2) {
        long j3;
        long j4;
        int i;
        int i2 = this.off;
        int i3 = i2 + 18 + (this.pretty * this.level);
        char[] cArr = this.chars;
        if (i3 > cArr.length) {
            cArr = grow(i3);
        }
        char[] cArr2 = cArr;
        if (this.startObject) {
            this.startObject = false;
        } else {
            int i4 = i2 + 1;
            cArr2[i2] = ',';
            if (this.pretty != 0) {
                i2 = indent(cArr2, i4);
            } else {
                j3 = j;
                j4 = j2;
                i = i4;
                putLong(cArr2, i, j3, j4);
                this.off = i + 14;
            }
        }
        j3 = j;
        j4 = j2;
        i = i2;
        putLong(cArr2, i, j3, j4);
        this.off = i + 14;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeName12Raw(long j, long j2) {
        long j3;
        long j4;
        int i;
        int i2 = this.off;
        int i3 = i2 + 18 + (this.pretty * this.level);
        char[] cArr = this.chars;
        if (i3 > cArr.length) {
            cArr = grow(i3);
        }
        char[] cArr2 = cArr;
        if (this.startObject) {
            this.startObject = false;
        } else {
            int i4 = i2 + 1;
            cArr2[i2] = ',';
            if (this.pretty != 0) {
                i2 = indent(cArr2, i4);
            } else {
                j3 = j;
                j4 = j2;
                i = i4;
                putLong(cArr2, i, j3, j4);
                this.off = i + 15;
            }
        }
        j3 = j;
        j4 = j2;
        i = i2;
        putLong(cArr2, i, j3, j4);
        this.off = i + 15;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeName13Raw(long j, long j2) {
        long j3;
        long j4;
        int i;
        int i2 = this.off;
        int i3 = i2 + 18 + (this.pretty * this.level);
        char[] cArr = this.chars;
        if (i3 > cArr.length) {
            cArr = grow(i3);
        }
        char[] cArr2 = cArr;
        if (this.startObject) {
            this.startObject = false;
        } else {
            int i4 = i2 + 1;
            cArr2[i2] = ',';
            if (this.pretty != 0) {
                i2 = indent(cArr2, i4);
            } else {
                j3 = j;
                j4 = j2;
                i = i4;
                putLong(cArr2, i, j3, j4);
                this.off = i + 16;
            }
        }
        j3 = j;
        j4 = j2;
        i = i2;
        putLong(cArr2, i, j3, j4);
        this.off = i + 16;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeName14Raw(long j, long j2) {
        long j3;
        long j4;
        int i;
        int i2 = this.off;
        int i3 = i2 + 19 + (this.pretty * this.level);
        char[] cArr = this.chars;
        if (i3 > cArr.length) {
            cArr = grow(i3);
        }
        char[] cArr2 = cArr;
        if (this.startObject) {
            this.startObject = false;
        } else {
            int i4 = i2 + 1;
            cArr2[i2] = ',';
            if (this.pretty != 0) {
                i2 = indent(cArr2, i4);
            } else {
                j3 = j;
                j4 = j2;
                i = i4;
                putLong(cArr2, i, j3, j4);
                int i5 = i;
                cArr2[i5 + 16] = ':';
                this.off = i5 + 17;
            }
        }
        j3 = j;
        j4 = j2;
        i = i2;
        putLong(cArr2, i, j3, j4);
        int i52 = i;
        cArr2[i52 + 16] = ':';
        this.off = i52 + 17;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x003e  */
    @Override // com.alibaba.fastjson2.JSONWriter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void writeName15Raw(long r10, long r12) {
        /*
            r9 = this;
            int r0 = r9.off
            int r1 = r0 + 20
            byte r2 = r9.pretty
            int r3 = r9.level
            int r2 = r2 * r3
            int r1 = r1 + r2
            char[] r2 = r9.chars
            int r3 = r2.length
            if (r1 <= r3) goto L13
            char[] r2 = r9.grow(r1)
        L13:
            r3 = r2
            boolean r1 = r9.startObject
            if (r1 == 0) goto L1f
            r1 = 0
            r9.startObject = r1
        L1b:
            r5 = r10
            r7 = r12
            r4 = r0
            goto L31
        L1f:
            int r1 = r0 + 1
            r2 = 44
            r3[r0] = r2
            byte r0 = r9.pretty
            if (r0 == 0) goto L2e
            int r0 = r9.indent(r3, r1)
            goto L1b
        L2e:
            r5 = r10
            r7 = r12
            r4 = r1
        L31:
            putLong(r3, r4, r5, r7)
            r0 = r4
            int r4 = r0 + 16
            boolean r10 = r9.useSingleQuote
            if (r10 == 0) goto L3e
            int r10 = com.alibaba.fastjson2.JSONWriterUTF16.QUOTE_COLON
            goto L40
        L3e:
            int r10 = com.alibaba.fastjson2.JSONWriterUTF16.QUOTE2_COLON
        L40:
            com.alibaba.fastjson2.util.IOUtils.putIntUnaligned(r3, r4, r10)
            int r4 = r0 + 18
            r9.off = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONWriterUTF16.writeName15Raw(long, long):void");
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeName16Raw(long j, long j2) {
        int i = this.off;
        int i2 = i + 21 + (this.pretty * this.level);
        char[] cArr = this.chars;
        if (i2 > cArr.length) {
            cArr = grow(i2);
        }
        char[] cArr2 = cArr;
        if (this.startObject) {
            this.startObject = false;
        } else {
            int i3 = i + 1;
            cArr2[i] = ',';
            i = this.pretty != 0 ? indent(cArr2, i3) : i3;
        }
        cArr2[i] = this.quote;
        putLong(cArr2, i + 1, j, j2);
        IOUtils.putIntUnaligned(cArr2, i + 17, this.useSingleQuote ? QUOTE_COLON : QUOTE2_COLON);
        this.off = i + 19;
    }

    private static void putLong(char[] cArr, int i, long j) {
        long j2 = JDKUtils.ARRAY_CHAR_BASE_OFFSET + (i << 1);
        JDKUtils.UNSAFE.putLong(cArr, j2, (j & 255) | ((j & 65280) << 8) | ((j & 16711680) << 16) | ((j & 4278190080L) << 24));
        JDKUtils.UNSAFE.putLong(cArr, j2 + 8, ((j & 1095216660480L) >> 32) | ((j & 280375465082880L) >> 24) | ((j & 71776119061217280L) >> 16) | ((j & (-72057594037927936L)) >> 8));
    }

    private static void putLong(char[] cArr, int i, long j, int i2) {
        long j2 = JDKUtils.ARRAY_CHAR_BASE_OFFSET + (i << 1);
        JDKUtils.UNSAFE.putLong(cArr, j2, ((j & 4278190080L) << 24) | (j & 255) | ((j & 65280) << 8) | ((j & 16711680) << 16));
        JDKUtils.UNSAFE.putLong(cArr, j2 + 8, ((j & 1095216660480L) >> 32) | ((j & 280375465082880L) >> 24) | ((j & 71776119061217280L) >> 16) | ((j & (-72057594037927936L)) >> 8));
        long j3 = i2;
        JDKUtils.UNSAFE.putLong(cArr, j2 + 16, (255 & j3) | ((j3 & 65280) << 8) | ((j3 & 16711680) << 16) | ((j3 & 4278190080L) << 24));
    }

    private static void putLong(char[] cArr, int i, long j, long j2) {
        long j3 = JDKUtils.ARRAY_CHAR_BASE_OFFSET + (i << 1);
        JDKUtils.UNSAFE.putLong(cArr, j3, ((j & 4278190080L) << 24) | (j & 255) | ((j & 65280) << 8) | ((j & 16711680) << 16));
        JDKUtils.UNSAFE.putLong(cArr, j3 + 8, ((j & 1095216660480L) >> 32) | ((j & 280375465082880L) >> 24) | ((j & 71776119061217280L) >> 16) | ((j & (-72057594037927936L)) >> 8));
        JDKUtils.UNSAFE.putLong(cArr, j3 + 16, (j2 & 255) | ((j2 & 65280) << 8) | ((j2 & 16711680) << 16) | ((j2 & 4278190080L) << 24));
        JDKUtils.UNSAFE.putLong(cArr, j3 + 24, ((j2 & 1095216660480L) >> 32) | ((j2 & 280375465082880L) >> 24) | ((j2 & 71776119061217280L) >> 16) | ((j2 & (-72057594037927936L)) >> 8));
    }

    private int indent(char[] cArr, int i) {
        cArr[i] = '\n';
        int i2 = i + 1;
        int i3 = (this.pretty * this.level) + i2;
        Arrays.fill(cArr, i2, i3, this.pretty == 1 ? '\t' : ' ');
        return i3;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeNameRaw(char[] cArr, int i, int i2) {
        int i3 = this.off;
        int i4 = i3 + i2 + 2 + (this.pretty * this.level);
        char[] cArr2 = this.chars;
        if (i4 > cArr2.length) {
            cArr2 = grow(i4);
        }
        if (this.startObject) {
            this.startObject = false;
        } else {
            cArr2[i3] = ',';
            i3++;
        }
        System.arraycopy(cArr, i, cArr2, i3, i2);
        this.off = i3 + i2;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final Object ensureCapacity(int i) {
        char[] cArr = this.chars;
        if (i < cArr.length) {
            return cArr;
        }
        char[] copyOf = Arrays.copyOf(cArr, newCapacity(i, cArr.length));
        this.chars = copyOf;
        return copyOf;
    }

    final void ensureCapacityInternal(int i) {
        if (i > this.chars.length) {
            grow0(i);
        }
    }

    private char[] grow(int i) {
        grow0(i);
        return this.chars;
    }

    protected final void grow0(int i) {
        char[] cArr = this.chars;
        this.chars = Arrays.copyOf(cArr, newCapacity(i, cArr.length));
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeInt32(int[] iArr) {
        if (iArr == null) {
            writeNull();
            return;
        }
        boolean z = (this.context.features & JSONWriter.Feature.WriteNonStringValueAsString.mask) != 0;
        int i = this.off;
        int length = (iArr.length * 13) + i + 2;
        char[] cArr = this.chars;
        if (length > cArr.length) {
            cArr = grow(length);
        }
        int i2 = i + 1;
        cArr[i] = '[';
        for (int i3 = 0; i3 < iArr.length; i3++) {
            if (i3 != 0) {
                cArr[i2] = ',';
                i2++;
            }
            if (z) {
                cArr[i2] = this.quote;
                i2++;
            }
            int writeInt32 = IOUtils.writeInt32(cArr, i2, iArr[i3]);
            if (z) {
                i2 = writeInt32 + 1;
                cArr[writeInt32] = this.quote;
            } else {
                i2 = writeInt32;
            }
        }
        cArr[i2] = ']';
        this.off = i2 + 1;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeInt8(byte b) {
        boolean z = (this.context.features & JSONWriter.Feature.WriteNonStringValueAsString.mask) != 0;
        int i = this.off;
        int i2 = i + 7;
        char[] cArr = this.chars;
        if (i2 > cArr.length) {
            cArr = grow(i2);
        }
        if (z) {
            cArr[i] = this.quote;
            i++;
        }
        int writeInt8 = IOUtils.writeInt8(cArr, i, b);
        if (z) {
            cArr[writeInt8] = this.quote;
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
        boolean z = (this.context.features & JSONWriter.Feature.WriteNonStringValueAsString.mask) != 0;
        int i = this.off;
        int length = (bArr.length * 5) + i + 2;
        char[] cArr = this.chars;
        if (length > cArr.length) {
            cArr = grow(length);
        }
        int i2 = i + 1;
        cArr[i] = '[';
        for (int i3 = 0; i3 < bArr.length; i3++) {
            if (i3 != 0) {
                cArr[i2] = ',';
                i2++;
            }
            if (z) {
                cArr[i2] = this.quote;
                i2++;
            }
            int writeInt8 = IOUtils.writeInt8(cArr, i2, bArr[i3]);
            if (z) {
                i2 = writeInt8 + 1;
                cArr[writeInt8] = this.quote;
            } else {
                i2 = writeInt8;
            }
        }
        cArr[i2] = ']';
        this.off = i2 + 1;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeInt16(short s) {
        boolean z = (this.context.features & JSONWriter.Feature.WriteNonStringValueAsString.mask) != 0;
        int i = this.off;
        int i2 = i + 7;
        char[] cArr = this.chars;
        if (i2 > cArr.length) {
            cArr = grow(i2);
        }
        if (z) {
            cArr[i] = this.quote;
            i++;
        }
        int writeInt16 = IOUtils.writeInt16(cArr, i, s);
        if (z) {
            cArr[writeInt16] = this.quote;
            writeInt16++;
        }
        this.off = writeInt16;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeInt32(int i) {
        boolean z = (this.context.features & 256) != 0;
        int i2 = this.off;
        int i3 = i2 + 13;
        char[] cArr = this.chars;
        if (i3 > cArr.length) {
            cArr = grow(i3);
        }
        if (z) {
            cArr[i2] = this.quote;
            i2++;
        }
        int writeInt32 = IOUtils.writeInt32(cArr, i2, i);
        if (z) {
            cArr[writeInt32] = this.quote;
            writeInt32++;
        }
        this.off = writeInt32;
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
    public final void writeInt64(long[] jArr) {
        if (jArr == null) {
            writeNull();
            return;
        }
        int i = this.off;
        int length = i + 2 + (jArr.length * 23);
        char[] cArr = this.chars;
        if (length > cArr.length) {
            cArr = grow(length);
        }
        int i2 = i + 1;
        cArr[i] = '[';
        for (int i3 = 0; i3 < jArr.length; i3++) {
            if (i3 != 0) {
                cArr[i2] = ',';
                i2++;
            }
            long j = jArr[i3];
            boolean isWriteAsString = isWriteAsString(j, this.context.features);
            if (isWriteAsString) {
                cArr[i2] = this.quote;
                i2++;
            }
            i2 = IOUtils.writeInt64(cArr, i2, j);
            if (isWriteAsString) {
                cArr[i2] = this.quote;
                i2++;
            }
        }
        cArr[i2] = ']';
        this.off = i2 + 1;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeListInt32(List<Integer> list) {
        if (list == null) {
            writeNull();
            return;
        }
        int size = list.size();
        boolean z = (this.context.features & JSONWriter.Feature.WriteNonStringValueAsString.mask) != 0;
        int i = this.off;
        int i2 = i + 2 + (size * 23);
        if (i2 >= this.chars.length) {
            grow0(i2);
        }
        char[] cArr = this.chars;
        int i3 = i + 1;
        cArr[i] = '[';
        for (int i4 = 0; i4 < size; i4++) {
            if (i4 != 0) {
                cArr[i3] = ',';
                i3++;
            }
            Integer num = list.get(i4);
            if (num == null) {
                cArr[i3] = 'n';
                cArr[i3 + 1] = 'u';
                cArr[i3 + 2] = 'l';
                cArr[i3 + 3] = 'l';
                i3 += 4;
            } else {
                int intValue = num.intValue();
                if (z) {
                    cArr[i3] = this.quote;
                    i3++;
                }
                int writeInt32 = IOUtils.writeInt32(cArr, i3, intValue);
                if (z) {
                    i3 = writeInt32 + 1;
                    cArr[writeInt32] = this.quote;
                } else {
                    i3 = writeInt32;
                }
            }
        }
        cArr[i3] = ']';
        this.off = i3 + 1;
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
        char[] cArr = this.chars;
        if (i2 > cArr.length) {
            cArr = grow(i2);
        }
        int i3 = i + 1;
        cArr[i] = '[';
        for (int i4 = 0; i4 < size; i4++) {
            if (i4 != 0) {
                cArr[i3] = ',';
                i3++;
            }
            Long l = list.get(i4);
            if (l == null) {
                cArr[i3] = 'n';
                cArr[i3 + 1] = 'u';
                cArr[i3 + 2] = 'l';
                cArr[i3 + 3] = 'l';
                i3 += 4;
            } else {
                long longValue = l.longValue();
                boolean isWriteAsString = isWriteAsString(longValue, this.context.features);
                if (isWriteAsString) {
                    cArr[i3] = this.quote;
                    i3++;
                }
                i3 = IOUtils.writeInt64(cArr, i3, longValue);
                if (isWriteAsString) {
                    cArr[i3] = this.quote;
                    i3++;
                }
            }
        }
        cArr[i3] = ']';
        this.off = i3 + 1;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeInt64(long j) {
        int i;
        long j2 = this.context.features;
        int i2 = this.off;
        int i3 = i2 + 23;
        char[] cArr = this.chars;
        if (i3 > cArr.length) {
            cArr = grow(i3);
        }
        boolean isWriteAsString = isWriteAsString(j, j2);
        if (isWriteAsString) {
            cArr[i2] = this.quote;
            i2++;
        }
        int writeInt64 = IOUtils.writeInt64(cArr, i2, j);
        if (isWriteAsString) {
            i = writeInt64 + 1;
            cArr[writeInt64] = this.quote;
        } else {
            if ((512 & j2) != 0 && (j2 & 1099511627776L) == 0 && j >= -2147483648L && j <= 2147483647L) {
                i = writeInt64 + 1;
                cArr[writeInt64] = Matrix.MATRIX_TYPE_RANDOM_LT;
            }
            this.off = writeInt64;
        }
        writeInt64 = i;
        this.off = writeInt64;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeInt64(Long l) {
        if (l == null) {
            writeInt64Null();
        } else {
            writeInt64(l.longValue());
        }
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeFloat(float f) {
        boolean z = (this.context.features & JSONWriter.Feature.WriteNonStringValueAsString.mask) != 0;
        int i = this.off;
        int i2 = i + 15;
        if (z) {
            i2 = i + 17;
        }
        char[] cArr = this.chars;
        if (i2 > cArr.length) {
            cArr = grow(i2);
        }
        if (z) {
            cArr[i] = Typography.quote;
            i++;
        }
        int writeFloat = NumberUtils.writeFloat(cArr, i, f, true);
        if (z) {
            cArr[writeFloat] = Typography.quote;
            writeFloat++;
        }
        this.off = writeFloat;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeFloat(float[] fArr) {
        if (fArr == null) {
            writeArrayNull();
            return;
        }
        boolean z = (this.context.features & JSONWriter.Feature.WriteNonStringValueAsString.mask) != 0;
        int i = this.off;
        int length = (fArr.length * (z ? 16 : 18)) + i + 1;
        char[] cArr = this.chars;
        if (length > cArr.length) {
            cArr = grow(length);
        }
        int i2 = i + 1;
        cArr[i] = '[';
        for (int i3 = 0; i3 < fArr.length; i3++) {
            if (i3 != 0) {
                cArr[i2] = ',';
                i2++;
            }
            if (z) {
                cArr[i2] = Typography.quote;
                i2++;
            }
            i2 = NumberUtils.writeFloat(cArr, i2, fArr[i3], true);
            if (z) {
                cArr[i2] = Typography.quote;
                i2++;
            }
        }
        cArr[i2] = ']';
        this.off = i2 + 1;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeDouble(double d) {
        boolean z = (this.context.features & JSONWriter.Feature.WriteNonStringValueAsString.mask) != 0;
        int i = this.off;
        int i2 = i + 24;
        if (z) {
            i2 = i + 26;
        }
        char[] cArr = this.chars;
        if (i2 > cArr.length) {
            cArr = grow(i2);
        }
        if (z) {
            cArr[i] = Typography.quote;
            i++;
        }
        int writeDouble = NumberUtils.writeDouble(cArr, i, d, true);
        if (z) {
            cArr[writeDouble] = Typography.quote;
            writeDouble++;
        }
        this.off = writeDouble;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeDoubleArray(double d, double d2) {
        boolean z = (this.context.features & JSONWriter.Feature.WriteNonStringValueAsString.mask) != 0;
        int i = this.off;
        int i2 = i + 51;
        if (z) {
            i2 = i + 53;
        }
        char[] cArr = this.chars;
        if (i2 > cArr.length) {
            cArr = grow(i2);
        }
        int i3 = i + 1;
        cArr[i] = '[';
        if (z) {
            cArr[i3] = Typography.quote;
            i3 = i + 2;
        }
        int writeDouble = NumberUtils.writeDouble(cArr, i3, d, true);
        if (z) {
            cArr[writeDouble] = Typography.quote;
            writeDouble++;
        }
        int i4 = writeDouble + 1;
        cArr[writeDouble] = ',';
        if (z) {
            cArr[i4] = Typography.quote;
            i4 = writeDouble + 2;
        }
        int writeDouble2 = NumberUtils.writeDouble(cArr, i4, d2, true);
        if (z) {
            cArr[writeDouble2] = Typography.quote;
            writeDouble2++;
        }
        cArr[writeDouble2] = ']';
        this.off = writeDouble2 + 1;
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
        char[] cArr = this.chars;
        if (length > cArr.length) {
            cArr = grow(length);
        }
        int i2 = i + 1;
        cArr[i] = '[';
        for (int i3 = 0; i3 < dArr.length; i3++) {
            if (i3 != 0) {
                cArr[i2] = ',';
                i2++;
            }
            if (z) {
                cArr[i2] = Typography.quote;
                i2++;
            }
            i2 = NumberUtils.writeDouble(cArr, i2, dArr[i3], true);
            if (z) {
                cArr[i2] = Typography.quote;
                i2++;
            }
        }
        cArr[i2] = ']';
        this.off = i2 + 1;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeDateTime14(int i, int i2, int i3, int i4, int i5, int i6) {
        int i7 = this.off;
        int i8 = i7 + 16;
        char[] cArr = this.chars;
        if (i8 > cArr.length) {
            cArr = grow(i8);
        }
        cArr[i7] = this.quote;
        if (i < 0 || i > 9999) {
            throw illegalYear(i);
        }
        int i9 = i / 100;
        IOUtils.writeDigitPair(cArr, i7 + 1, i9);
        IOUtils.writeDigitPair(cArr, i7 + 3, i - (i9 * 100));
        IOUtils.writeDigitPair(cArr, i7 + 5, i2);
        IOUtils.writeDigitPair(cArr, i7 + 7, i3);
        IOUtils.writeDigitPair(cArr, i7 + 9, i4);
        IOUtils.writeDigitPair(cArr, i7 + 11, i5);
        IOUtils.writeDigitPair(cArr, i7 + 13, i6);
        cArr[i7 + 15] = this.quote;
        this.off = i8;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeDateTime19(int i, int i2, int i3, int i4, int i5, int i6) {
        char[] cArr = this.chars;
        if (this.off + 21 > cArr.length) {
            cArr = grow(this.off + 21);
        }
        int i7 = this.off;
        cArr[i7] = this.quote;
        if (i < 0 || i > 9999) {
            throw illegalYear(i);
        }
        int writeLocalDate = IOUtils.writeLocalDate(cArr, i7 + 1, i, i2, i3);
        cArr[writeLocalDate] = ' ';
        IOUtils.writeLocalTime(cArr, writeLocalDate + 1, i4, i5, i6);
        cArr[writeLocalDate + 9] = this.quote;
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
            char[] cArr = this.chars;
            if (i2 > cArr.length) {
                cArr = grow(i2);
            }
            cArr[i] = this.quote;
            int writeLocalDate = IOUtils.writeLocalDate(cArr, i + 1, localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth());
            cArr[writeLocalDate] = this.quote;
            this.off = writeLocalDate + 1;
        }
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeLocalDateTime(LocalDateTime localDateTime) {
        int i = this.off;
        int i2 = i + 38;
        char[] cArr = this.chars;
        if (i2 > cArr.length) {
            cArr = grow(i2);
        }
        int i3 = i + 1;
        cArr[i] = this.quote;
        LocalDate localDate = localDateTime.toLocalDate();
        int writeLocalDate = IOUtils.writeLocalDate(cArr, i3, localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth());
        cArr[writeLocalDate] = ' ';
        int writeLocalTime = IOUtils.writeLocalTime(cArr, writeLocalDate + 1, localDateTime.toLocalTime());
        cArr[writeLocalTime] = this.quote;
        this.off = writeLocalTime + 1;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeDateTimeISO8601(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z) {
        int i9 = z ? i8 == 0 ? 1 : 6 : 0;
        int i10 = this.off;
        int i11 = i10 + 25 + i9;
        char[] cArr = this.chars;
        if (i11 > cArr.length) {
            cArr = grow(i11);
        }
        cArr[i10] = this.quote;
        int writeLocalDate = IOUtils.writeLocalDate(cArr, i10 + 1, i, i2, i3);
        cArr[writeLocalDate] = z ? 'T' : ' ';
        IOUtils.writeLocalTime(cArr, writeLocalDate + 1, i4, i5, i6);
        int i12 = writeLocalDate + 9;
        if (i7 > 0) {
            int i13 = i7 / 10;
            int i14 = i13 / 10;
            if (i7 - (i13 * 10) != 0) {
                IOUtils.putLongLE(cArr, i12, (IOUtils.DIGITS_K_64[i7 & 1023] & (-65536)) | IOUtils.DOT_X0);
                i12 = writeLocalDate + 13;
            } else {
                int i15 = writeLocalDate + 10;
                cArr[i12] = '.';
                if (i13 - (i14 * 10) != 0) {
                    IOUtils.writeDigitPair(cArr, i15, i13);
                    i12 = writeLocalDate + 12;
                } else {
                    i12 = writeLocalDate + 11;
                    cArr[i15] = (char) ((byte) (i14 + 48));
                }
            }
        }
        if (z) {
            int i16 = i8 / 3600;
            if (i8 == 0) {
                cArr[i12] = Matrix.MATRIX_TYPE_ZERO;
                i12++;
            } else {
                int abs = Math.abs(i16);
                cArr[i12] = i16 >= 0 ? '+' : '-';
                IOUtils.writeDigitPair(cArr, i12 + 1, abs);
                cArr[i12 + 3] = ':';
                int i17 = (i8 - (i16 * 3600)) / 60;
                if (i17 < 0) {
                    i17 = -i17;
                }
                IOUtils.writeDigitPair(cArr, i12 + 4, i17);
                i12 += 6;
            }
        }
        cArr[i12] = this.quote;
        this.off = i12 + 1;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeDateYYYMMDD8(int i, int i2, int i3) {
        int i4 = this.off;
        int i5 = i4 + 10;
        char[] cArr = this.chars;
        if (i5 > cArr.length) {
            cArr = grow(i5);
        }
        cArr[i4] = this.quote;
        if (i < 0 || i > 9999) {
            throw illegalYear(i);
        }
        int i6 = i / 100;
        IOUtils.writeDigitPair(cArr, i4 + 1, i6);
        IOUtils.writeDigitPair(cArr, i4 + 3, i - (i6 * 100));
        IOUtils.writeDigitPair(cArr, i4 + 5, i2);
        IOUtils.writeDigitPair(cArr, i4 + 7, i3);
        cArr[i4 + 9] = this.quote;
        this.off = i5;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeDateYYYMMDD10(int i, int i2, int i3) {
        int i4 = this.off;
        int i5 = i4 + 13;
        char[] cArr = this.chars;
        if (i5 > cArr.length) {
            cArr = grow(i5);
        }
        cArr[i4] = this.quote;
        int writeLocalDate = IOUtils.writeLocalDate(cArr, i4 + 1, i, i2, i3);
        cArr[writeLocalDate] = this.quote;
        this.off = writeLocalDate + 1;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeTimeHHMMSS8(int i, int i2, int i3) {
        int i4 = this.off;
        int i5 = i4 + 10;
        char[] cArr = this.chars;
        if (i5 > cArr.length) {
            cArr = grow(i5);
        }
        cArr[i4] = (char) ((byte) this.quote);
        IOUtils.writeDigitPair(cArr, i4 + 1, i);
        cArr[i4 + 3] = ':';
        IOUtils.writeDigitPair(cArr, i4 + 4, i2);
        cArr[i4 + 6] = ':';
        IOUtils.writeDigitPair(cArr, i4 + 7, i3);
        cArr[i4 + 9] = (char) ((byte) this.quote);
        this.off = i5;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeLocalTime(LocalTime localTime) {
        int i = this.off;
        int i2 = i + 20;
        char[] cArr = this.chars;
        if (i2 > cArr.length) {
            cArr = grow(i2);
        }
        cArr[i] = this.quote;
        int writeLocalTime = IOUtils.writeLocalTime(cArr, i + 1, localTime);
        cArr[writeLocalTime] = this.quote;
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
        char[] cArr = this.chars;
        if (i4 > cArr.length) {
            cArr = grow(i4);
        }
        cArr[i3] = this.quote;
        LocalDate localDate = zonedDateTime.toLocalDate();
        int writeLocalDate = IOUtils.writeLocalDate(cArr, i3 + 1, localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth());
        cArr[writeLocalDate] = 'T';
        int writeLocalTime = IOUtils.writeLocalTime(cArr, writeLocalDate + 1, zonedDateTime.toLocalTime());
        if (i == 1) {
            i2 = writeLocalTime + 1;
            cArr[writeLocalTime] = Matrix.MATRIX_TYPE_ZERO;
        } else if (c == '+' || c == '-') {
            id.getChars(0, length, cArr, writeLocalTime);
            i2 = writeLocalTime + length;
        } else {
            int i5 = writeLocalTime + 1;
            cArr[writeLocalTime] = '[';
            id.getChars(0, length, cArr, i5);
            int i6 = i5 + length;
            cArr[i6] = ']';
            i2 = i6 + 1;
        }
        cArr[i2] = this.quote;
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
        char[] cArr = this.chars;
        if (i2 > cArr.length) {
            cArr = grow(i2);
        }
        cArr[i] = this.quote;
        LocalDateTime localDateTime = offsetDateTime.toLocalDateTime();
        LocalDate localDate = localDateTime.toLocalDate();
        int writeLocalDate = IOUtils.writeLocalDate(cArr, i + 1, localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth());
        cArr[writeLocalDate] = 'T';
        int writeLocalTime = IOUtils.writeLocalTime(cArr, writeLocalDate + 1, localDateTime.toLocalTime());
        ZoneOffset offset = offsetDateTime.getOffset();
        if (offset.getTotalSeconds() == 0) {
            length = writeLocalTime + 1;
            cArr[writeLocalTime] = Matrix.MATRIX_TYPE_ZERO;
        } else {
            String id = offset.getId();
            id.getChars(0, id.length(), cArr, writeLocalTime);
            length = id.length() + writeLocalTime;
        }
        cArr[length] = this.quote;
        this.off = length + 1;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeOffsetTime(OffsetTime offsetTime) {
        int length;
        if (offsetTime == null) {
            writeNull();
            return;
        }
        ZoneOffset offset = offsetTime.getOffset();
        int i = this.off;
        int i2 = i + 28;
        char[] cArr = this.chars;
        if (i2 > cArr.length) {
            cArr = grow(i2);
        }
        cArr[i] = this.quote;
        int writeLocalTime = IOUtils.writeLocalTime(cArr, i + 1, offsetTime.toLocalTime());
        if (offset.getTotalSeconds() == 0) {
            length = writeLocalTime + 1;
            cArr[writeLocalTime] = Matrix.MATRIX_TYPE_ZERO;
        } else {
            String id = offset.getId();
            id.getChars(0, id.length(), cArr, writeLocalTime);
            length = id.length() + writeLocalTime;
        }
        cArr[length] = this.quote;
        this.off = length + 1;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeNameRaw(byte[] bArr) {
        throw new JSONException("UnsupportedOperation");
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final int flushTo(OutputStream outputStream) throws IOException {
        if (outputStream == null) {
            throw new JSONException("out is nulll");
        }
        for (int i = 0; i < this.off; i++) {
            if (this.chars[i] >= 128) {
                byte[] bArr = new byte[this.off * 3];
                int encodeUTF8 = IOUtils.encodeUTF8(this.chars, 0, this.off, bArr, 0);
                outputStream.write(bArr, 0, encodeUTF8);
                this.off = 0;
                return encodeUTF8;
            }
        }
        int i2 = this.off;
        byte[] bArr2 = new byte[i2];
        for (int i3 = 0; i3 < this.off; i3++) {
            bArr2[i3] = (byte) this.chars[i3];
        }
        outputStream.write(bArr2);
        this.off = 0;
        return i2;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final int flushTo(OutputStream outputStream, Charset charset) throws IOException {
        if (this.off == 0) {
            return 0;
        }
        if (outputStream == null) {
            throw new JSONException("out is null");
        }
        byte[] bytes = getBytes(charset);
        outputStream.write(bytes);
        this.off = 0;
        return bytes.length;
    }

    public final String toString() {
        return new String(this.chars, 0, this.off);
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final byte[] getBytes() {
        for (int i = 0; i < this.off; i++) {
            if (this.chars[i] >= 128) {
                byte[] bArr = new byte[this.off * 3];
                return Arrays.copyOf(bArr, IOUtils.encodeUTF8(this.chars, 0, this.off, bArr, 0));
            }
        }
        byte[] bArr2 = new byte[this.off];
        for (int i2 = 0; i2 < this.off; i2++) {
            bArr2[i2] = (byte) this.chars[i2];
        }
        return bArr2;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final int size() {
        return this.off;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final byte[] getBytes(Charset charset) {
        int i = 0;
        while (true) {
            if (i < this.off) {
                if (this.chars[i] >= 128) {
                    break;
                }
                i++;
            } else if (charset == StandardCharsets.UTF_8 || charset == StandardCharsets.ISO_8859_1 || charset == StandardCharsets.US_ASCII) {
                byte[] bArr = new byte[this.off];
                for (int i2 = 0; i2 < this.off; i2++) {
                    bArr[i2] = (byte) this.chars[i2];
                }
                return bArr;
            }
        }
        String str = new String(this.chars, 0, this.off);
        if (charset == null) {
            charset = StandardCharsets.UTF_8;
        }
        return str.getBytes(charset);
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeRaw(byte[] bArr) {
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
        if ((this.context.features & NONE_DIRECT_FEATURES) != 0) {
            this.context.getObjectWriter(map.getClass()).write(this, map, null, null, 0L);
            return;
        }
        writeRaw('{');
        boolean z = true;
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            Object value = entry.getValue();
            if (value != null || (this.context.features & JSONWriter.Feature.WriteMapNullValue.mask) != 0) {
                if (!z) {
                    writeRaw(',');
                }
                Object key = entry.getKey();
                if (key instanceof String) {
                    writeString((String) key);
                } else {
                    writeAny(key);
                }
                writeRaw(':');
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
        writeRaw('}');
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void write(List list) {
        if (list == null) {
            writeArrayNull();
            return;
        }
        if (((JSONWriter.Feature.ReferenceDetection.mask | JSONWriter.Feature.PrettyFormat.mask | JSONWriter.Feature.NotWriteEmptyArray.mask | JSONWriter.Feature.NotWriteDefaultValue.mask) & this.context.features) != 0) {
            this.context.getObjectWriter(list.getClass()).write(this, list, null, null, 0L);
            return;
        }
        if (this.off == this.chars.length) {
            grow0(this.off + 1);
        }
        char[] cArr = this.chars;
        int i = this.off;
        this.off = i + 1;
        cArr[i] = '[';
        boolean z = true;
        int i2 = 0;
        while (i2 < list.size()) {
            Object obj = list.get(i2);
            if (!z) {
                if (this.off == this.chars.length) {
                    grow(this.off + 1);
                }
                char[] cArr2 = this.chars;
                int i3 = this.off;
                this.off = i3 + 1;
                cArr2[i3] = ',';
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
        if (this.off == this.chars.length) {
            grow(this.off + 1);
        }
        char[] cArr3 = this.chars;
        int i4 = this.off;
        this.off = i4 + 1;
        cArr3[i4] = ']';
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeString(boolean z) {
        char[] cArr = this.chars;
        int i = this.off;
        this.off = i + 1;
        cArr[i] = this.quote;
        writeBool(z);
        char[] cArr2 = this.chars;
        int i2 = this.off;
        this.off = i2 + 1;
        cArr2[i2] = this.quote;
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
        if (this.off == this.chars.length) {
            grow(this.off + 1);
        }
        char[] cArr = this.chars;
        int i = this.off;
        this.off = i + 1;
        cArr[i] = this.quote;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeString(char[] cArr) {
        if (cArr == null) {
            writeStringNull();
            return;
        }
        long j = 0;
        if ((this.context.features & (JSONWriter.Feature.BrowserSecure.mask | JSONWriter.Feature.EscapeNoneAscii.mask)) != 0) {
            writeStringBrowserSecure(cArr);
            return;
        }
        int i = this.off;
        int length = cArr.length + i + 2;
        if (length >= this.chars.length) {
            grow(length);
        }
        long j2 = this.byteVectorQuote;
        char[] cArr2 = this.chars;
        int i2 = i + 1;
        cArr2[i] = this.quote;
        int length2 = cArr.length;
        int i3 = 0;
        while (i3 < length2) {
            int i4 = i3 + 8;
            if (i4 < length2) {
                long longLE = IOUtils.getLongLE(cArr, i3);
                long longLE2 = IOUtils.getLongLE(cArr, i3 + 4);
                if (((longLE | longLE2) & (-71777214294589696L)) == j && StringUtils.noneEscaped((longLE << 8) | longLE2, j2)) {
                    IOUtils.putLongLE(cArr2, i2, longLE);
                    IOUtils.putLongLE(cArr2, i2 + 4, longLE2);
                    i2 += 8;
                    i3 = i4;
                    j = 0;
                }
            }
            int i5 = i3 + 1;
            char c = IOUtils.getChar(cArr, i3);
            if (c != '\\' && c != this.quote && c >= ' ') {
                cArr2[i2] = c;
                i2++;
                i3 = i5;
                j = 0;
            } else {
                writeStringEscape(cArr);
                return;
            }
        }
        cArr2[i2] = this.quote;
        this.off = i2 + 1;
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public final void writeString(char[] cArr, int i, int i2) {
        if (cArr == null) {
            writeStringNull();
            return;
        }
        boolean z = (this.context.features & JSONWriter.Feature.EscapeNoneAscii.mask) != 0;
        for (int i3 = i; i3 < i2; i3++) {
            char c = cArr[i3];
            if (c == '\\' || c == this.quote || c < ' ') {
                z = true;
                break;
            }
        }
        if (!z) {
            int i4 = this.off;
            int i5 = i4 + i2 + 2;
            char[] cArr2 = this.chars;
            if (i5 > cArr2.length) {
                cArr2 = grow(i5);
            }
            int i6 = i4 + 1;
            cArr2[i4] = this.quote;
            System.arraycopy(cArr, i, cArr2, i6, i2);
            int i7 = i6 + i2;
            cArr2[i7] = this.quote;
            this.off = i7 + 1;
            return;
        }
        writeStringEscape(new String(cArr, i, i2));
    }

    @Override // com.alibaba.fastjson2.JSONWriter
    public void writeBool(boolean z) {
        int i;
        int i2 = this.off + 5;
        char[] cArr = this.chars;
        if (i2 > cArr.length) {
            cArr = grow(i2);
        }
        int i3 = this.off;
        if ((this.context.features & JSONWriter.Feature.WriteBooleanAsNumber.mask) != 0) {
            i = i3 + 1;
            cArr[i3] = z ? '1' : '0';
        } else if (!z) {
            cArr[i3] = 'f';
            cArr[i3 + 1] = 'a';
            cArr[i3 + 2] = 'l';
            cArr[i3 + 3] = 's';
            cArr[i3 + 4] = 'e';
            i = i3 + 5;
        } else {
            cArr[i3] = 't';
            cArr[i3 + 1] = 'r';
            cArr[i3 + 2] = 'u';
            cArr[i3 + 3] = 'e';
            i = i3 + 4;
        }
        this.off = i;
    }
}
