package com.alibaba.fastjson2;

import androidx.collection.ScatterMapKt;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson2.JSONB;
import com.alibaba.fastjson2.JSONFactory;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.reader.ValueConsumer;
import com.alibaba.fastjson2.util.DateUtils;
import com.alibaba.fastjson2.util.Fnv;
import com.alibaba.fastjson2.util.IOUtils;
import com.alibaba.fastjson2.util.JDKUtils;
import com.alibaba.fastjson2.util.TypeUtils;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Base64;
import java.util.UUID;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;
import okhttp3.internal.ws.WebSocketProtocol;
import okio.Utf8;

/* loaded from: classes2.dex */
class JSONReaderUTF8 extends JSONReader {
    static final int ESCAPE_INDEX_NOT_SET = -2;
    static final int REF;
    protected final byte[] bytes;
    protected JSONFactory.CacheItem cacheItem;
    protected char[] charBuf;
    protected final int end;
    protected final InputStream in;
    protected final int length;
    protected boolean nameAscii;
    protected int nameBegin;
    protected int nameEnd;
    protected int nameLength;
    protected int nextEscapeIndex;
    protected int referenceBegin;
    protected final int start;

    static boolean containsSlashOrQuote(long j, long j2) {
        long j3 = j2 ^ j;
        long j4 = j ^ 6655295901103053916L;
        return ((((~j4) & (j4 - ScatterMapKt.BitmaskLsb)) | ((~j3) & (j3 - ScatterMapKt.BitmaskLsb))) & (-9187201950435737472L)) != 0;
    }

    static {
        REF = JDKUtils.BIG_ENDIAN ? 611476838 : 1717924388;
    }

    JSONReaderUTF8(JSONReader.Context context, InputStream inputStream) {
        super(context, false, true);
        this.nextEscapeIndex = -2;
        this.cacheItem = JSONFactory.CACHE_ITEMS[System.identityHashCode(Thread.currentThread()) & (JSONFactory.CACHE_ITEMS.length - 1)];
        byte[] andSet = JSONFactory.BYTES_UPDATER.getAndSet(this.cacheItem, null);
        int i = context.bufferSize;
        andSet = andSet == null ? new byte[i] : andSet;
        int i2 = 0;
        while (true) {
            try {
                int read = inputStream.read(andSet, i2, andSet.length - i2);
                if (read == -1) {
                    break;
                }
                i2 += read;
                if (i2 == andSet.length) {
                    andSet = Arrays.copyOf(andSet, andSet.length + i);
                }
            } catch (IOException e) {
                throw new JSONException("read error", e);
            }
        }
        this.bytes = andSet;
        this.offset = 0;
        this.length = i2;
        this.in = inputStream;
        this.start = 0;
        this.end = i2;
        next();
        if (this.ch == '/') {
            skipComment();
        }
    }

    JSONReaderUTF8(JSONReader.Context context, ByteBuffer byteBuffer) {
        super(context, false, true);
        this.nextEscapeIndex = -2;
        this.cacheItem = JSONFactory.CACHE_ITEMS[System.identityHashCode(Thread.currentThread()) & (JSONFactory.CACHE_ITEMS.length - 1)];
        byte[] andSet = JSONFactory.BYTES_UPDATER.getAndSet(this.cacheItem, null);
        int remaining = byteBuffer.remaining();
        andSet = (andSet == null || andSet.length < remaining) ? new byte[remaining] : andSet;
        byteBuffer.get(andSet, 0, remaining);
        this.bytes = andSet;
        this.offset = 0;
        this.length = remaining;
        this.in = null;
        this.start = 0;
        this.end = remaining;
        next();
        if (this.ch == '/') {
            skipComment();
        }
    }

    JSONReaderUTF8(JSONReader.Context context, byte[] bArr, int i, int i2) {
        super(context, false, true);
        this.nextEscapeIndex = -2;
        this.bytes = bArr;
        this.offset = i;
        this.length = i2;
        this.in = null;
        this.start = i;
        this.end = i + i2;
        this.cacheItem = null;
        next();
    }

    static int indexOfSlash(JSONReaderUTF8 jSONReaderUTF8, byte[] bArr, int i, int i2) {
        int i3 = jSONReaderUTF8.nextEscapeIndex;
        if (i3 != -2 && (i3 == -1 || i3 >= i)) {
            return i3;
        }
        int indexOfSlash = IOUtils.indexOfSlash(bArr, i, i2);
        jSONReaderUTF8.nextEscapeIndex = indexOfSlash;
        return indexOfSlash;
    }

    private void char_utf8(int i, int i2) {
        int i3;
        int i4;
        byte[] bArr = this.bytes;
        int i5 = i & 255;
        switch (i5 >> 4) {
            case 12:
            case 13:
                int i6 = i2 + 1;
                int char2_utf8 = char2_utf8(i5, bArr[i2], i6);
                i3 = i6;
                i4 = char2_utf8;
                break;
            case 14:
                i4 = char2_utf8(i5, bArr[i2], bArr[i2 + 1], i2);
                i3 = i2 + 2;
                break;
            default:
                if ((i >> 3) == -2) {
                    i4 = (((i << 18) ^ (bArr[i2] << 12)) ^ (bArr[i2 + 1] << 6)) ^ (bArr[i2 + 2] ^ ByteCompanionObject.MIN_VALUE);
                    i3 = i2 + 3;
                    break;
                } else {
                    throw new JSONException("malformed input around byte " + i2);
                }
        }
        this.ch = (char) i4;
        this.offset = i3;
    }

    static int char2_utf8(int i, int i2, int i3) {
        if ((i2 & 192) == 128) {
            return ((i & 31) << 6) | (i2 & 63);
        }
        throw new JSONException("malformed input around byte " + i3);
    }

    static int char2_utf8(int i, int i2, int i3, int i4) {
        if ((i2 & 192) == 128 && (i3 & 192) == 128) {
            return ((i & 15) << 12) | ((i2 & 63) << 6) | (i3 & 63);
        }
        throw new JSONException("malformed input around byte " + i4);
    }

    static void char2_utf8(byte[] bArr, int i, int i2, char[] cArr, int i3) {
        if ((i2 >> 3) == -2) {
            int i4 = bArr[i + 1];
            int i5 = bArr[i + 2];
            int i6 = bArr[i + 3];
            int i7 = (((i2 << 18) ^ (i4 << 12)) ^ (i5 << 6)) ^ (3678080 ^ i6);
            if ((i4 & 192) != 128 || (i5 & 192) != 128 || (i6 & 192) != 128 || i7 < 65536 || i7 >= 1114112) {
                throw new JSONException("malformed input around byte " + i);
            }
            cArr[i3] = (char) ((i7 >>> 10) + Utf8.HIGH_SURROGATE_HEADER);
            cArr[i3 + 1] = (char) ((i7 & 1023) + Utf8.LOG_SURROGATE_HEADER);
            return;
        }
        throw new JSONException("malformed input around byte " + i);
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x005e A[LOOP:1: B:24:0x0039->B:40:0x005e, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0033 A[EDGE_INSN: B:41:0x0033->B:22:0x0033 BREAK  A[LOOP:1: B:24:0x0039->B:40:0x005e], SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:31:0x005b -> B:17:0x0033). Please report as a decompilation issue!!! */
    @Override // com.alibaba.fastjson2.JSONReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean nextIfMatch(char r15) {
        /*
            r14 = this;
            byte[] r0 = r14.bytes
            int r1 = r14.offset
            char r2 = r14.ch
        L6:
            r3 = 0
            r5 = 4294981376(0x100003700, double:2.1220027474E-314)
            r7 = 1
            r9 = 32
            r10 = 26
            if (r2 > r9) goto L2a
            long r11 = r7 << r2
            long r11 = r11 & r5
            int r11 = (r11 > r3 ? 1 : (r11 == r3 ? 0 : -1))
            if (r11 == 0) goto L2a
            int r2 = r14.end
            if (r1 != r2) goto L22
            r2 = r10
            goto L6
        L22:
            int r2 = r1 + 1
            r1 = r0[r1]
            r13 = r2
            r2 = r1
            r1 = r13
            goto L6
        L2a:
            if (r2 == r15) goto L2e
            r15 = 0
            return r15
        L2e:
            int r15 = r14.end
            if (r1 != r15) goto L35
            r15 = r1
        L33:
            r1 = r10
            goto L39
        L35:
            int r15 = r1 + 1
            r1 = r0[r1]
        L39:
            if (r1 == 0) goto L59
            if (r1 > r9) goto L45
            long r11 = r7 << r1
            long r11 = r11 & r5
            int r2 = (r11 > r3 ? 1 : (r11 == r3 ? 0 : -1))
            if (r2 == 0) goto L45
            goto L59
        L45:
            r0 = 1
            if (r1 >= 0) goto L4c
            r14.char_utf8(r1, r15)
            return r0
        L4c:
            r14.offset = r15
            char r15 = (char) r1
            r14.ch = r15
            r15 = 47
            if (r1 != r15) goto L58
            r14.skipComment()
        L58:
            return r0
        L59:
            int r1 = r14.end
            if (r15 != r1) goto L5e
            goto L33
        L5e:
            int r1 = r15 + 1
            r15 = r0[r15]
            r13 = r1
            r1 = r15
            r15 = r13
            goto L39
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF8.nextIfMatch(char):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0065 A[LOOP:1: B:26:0x0040->B:42:0x0065, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x003a A[EDGE_INSN: B:43:0x003a->B:24:0x003a BREAK  A[LOOP:1: B:26:0x0040->B:42:0x0065], SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:32:0x0062 -> B:18:0x003a). Please report as a decompilation issue!!! */
    @Override // com.alibaba.fastjson2.JSONReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean nextIfComma() {
        /*
            r14 = this;
            byte[] r0 = r14.bytes
            int r1 = r14.offset
            char r2 = r14.ch
        L6:
            r3 = 0
            r5 = 4294981376(0x100003700, double:2.1220027474E-314)
            r7 = 1
            r9 = 32
            r10 = 26
            if (r2 > r9) goto L2a
            long r11 = r7 << r2
            long r11 = r11 & r5
            int r11 = (r11 > r3 ? 1 : (r11 == r3 ? 0 : -1))
            if (r11 == 0) goto L2a
            int r2 = r14.end
            if (r1 != r2) goto L22
            r2 = r10
            goto L6
        L22:
            int r2 = r1 + 1
            r1 = r0[r1]
            r13 = r2
            r2 = r1
            r1 = r13
            goto L6
        L2a:
            r11 = 44
            if (r2 == r11) goto L35
            r14.offset = r1
            char r0 = (char) r2
            r14.ch = r0
            r0 = 0
            return r0
        L35:
            int r2 = r14.end
            if (r1 != r2) goto L3c
            r2 = r1
        L3a:
            r1 = r10
            goto L40
        L3c:
            int r2 = r1 + 1
            r1 = r0[r1]
        L40:
            if (r1 == 0) goto L60
            if (r1 > r9) goto L4c
            long r11 = r7 << r1
            long r11 = r11 & r5
            int r11 = (r11 > r3 ? 1 : (r11 == r3 ? 0 : -1))
            if (r11 == 0) goto L4c
            goto L60
        L4c:
            r0 = 1
            if (r1 >= 0) goto L53
            r14.char_utf8(r1, r2)
            return r0
        L53:
            r14.offset = r2
            char r2 = (char) r1
            r14.ch = r2
            r2 = 47
            if (r1 != r2) goto L5f
            r14.skipComment()
        L5f:
            return r0
        L60:
            int r1 = r14.end
            if (r2 != r1) goto L65
            goto L3a
        L65:
            int r1 = r2 + 1
            r2 = r0[r2]
            r13 = r2
            r2 = r1
            r1 = r13
            goto L40
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF8.nextIfComma():boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0048 A[LOOP:0: B:10:0x0019->B:27:0x0048, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0013 A[EDGE_INSN: B:28:0x0013->B:8:0x0013 BREAK  A[LOOP:0: B:10:0x0019->B:27:0x0048], SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x0045 -> B:9:0x0013). Please report as a decompilation issue!!! */
    @Override // com.alibaba.fastjson2.JSONReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean nextIfArrayStart() {
        /*
            r9 = this;
            char r0 = r9.ch
            r1 = 91
            if (r0 == r1) goto L8
            r0 = 0
            return r0
        L8:
            byte[] r0 = r9.bytes
            int r1 = r9.offset
            int r2 = r9.end
            r3 = 26
            if (r1 != r2) goto L15
            r2 = r1
        L13:
            r1 = r3
            goto L19
        L15:
            int r2 = r1 + 1
            r1 = r0[r1]
        L19:
            if (r1 == 0) goto L43
            r4 = 32
            if (r1 > r4) goto L2f
            r4 = 1
            long r4 = r4 << r1
            r6 = 4294981376(0x100003700, double:2.1220027474E-314)
            long r4 = r4 & r6
            r6 = 0
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 == 0) goto L2f
            goto L43
        L2f:
            r0 = 1
            if (r1 >= 0) goto L36
            r9.char_utf8(r1, r2)
            return r0
        L36:
            char r3 = (char) r1
            r9.ch = r3
            r9.offset = r2
            r2 = 47
            if (r1 != r2) goto L42
            r9.skipComment()
        L42:
            return r0
        L43:
            int r1 = r9.end
            if (r2 != r1) goto L48
            goto L13
        L48:
            int r1 = r2 + 1
            r2 = r0[r2]
            r8 = r2
            r2 = r1
            r1 = r8
            goto L19
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF8.nextIfArrayStart():boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0077 A[LOOP:0: B:10:0x001b->B:45:0x0077, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0015 A[EDGE_INSN: B:46:0x0015->B:8:0x0015 BREAK  A[LOOP:0: B:10:0x001b->B:45:0x0077], SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:38:0x0074 -> B:9:0x0015). Please report as a decompilation issue!!! */
    @Override // com.alibaba.fastjson2.JSONReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean nextIfArrayEnd() {
        /*
            r17 = this;
            r0 = r17
            char r1 = r0.ch
            byte[] r2 = r0.bytes
            int r3 = r0.offset
            r4 = 93
            if (r1 == r4) goto Le
            r1 = 0
            return r1
        Le:
            int r1 = r0.end
            r4 = 26
            if (r3 != r1) goto L17
            r1 = r3
        L15:
            r3 = r4
            goto L1b
        L17:
            int r1 = r3 + 1
            r3 = r2[r3]
        L1b:
            if (r3 == 0) goto L72
            r5 = 0
            r7 = 4294981376(0x100003700, double:2.1220027474E-314)
            r9 = 1
            r11 = 32
            if (r3 > r11) goto L32
            long r12 = r9 << r3
            long r12 = r12 & r7
            int r12 = (r12 > r5 ? 1 : (r12 == r5 ? 0 : -1))
            if (r12 == 0) goto L32
            goto L72
        L32:
            r12 = 44
            r13 = 1
            if (r3 != r12) goto L5f
            r0.comma = r13
            int r3 = r0.end
            if (r1 != r3) goto L40
            r3 = r1
            r1 = r4
            goto L44
        L40:
            int r3 = r1 + 1
            r1 = r2[r1]
        L44:
            r16 = r3
            r3 = r1
            r1 = r16
        L49:
            if (r3 == 0) goto L54
            if (r3 > r11) goto L5f
            long r14 = r9 << r3
            long r14 = r14 & r7
            int r12 = (r14 > r5 ? 1 : (r14 == r5 ? 0 : -1))
            if (r12 == 0) goto L5f
        L54:
            int r3 = r0.end
            if (r1 != r3) goto L5a
            r3 = r4
            goto L49
        L5a:
            int r3 = r1 + 1
            r1 = r2[r1]
            goto L44
        L5f:
            if (r3 >= 0) goto L65
            r0.char_utf8(r3, r1)
            return r13
        L65:
            char r2 = (char) r3
            r0.ch = r2
            r0.offset = r1
            r1 = 47
            if (r3 != r1) goto L71
            r0.skipComment()
        L71:
            return r13
        L72:
            int r3 = r0.end
            if (r1 != r3) goto L77
            goto L15
        L77:
            int r3 = r1 + 1
            r1 = r2[r1]
            r16 = r3
            r3 = r1
            r1 = r16
            goto L1b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF8.nextIfArrayEnd():boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0041 A[LOOP:0: B:14:0x0029->B:20:0x0041, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0023 A[SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:0x003e -> B:12:0x0023). Please report as a decompilation issue!!! */
    @Override // com.alibaba.fastjson2.JSONReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean nextIfSet() {
        /*
            r10 = this;
            byte[] r0 = r10.bytes
            int r1 = r10.offset
            char r2 = r10.ch
            r3 = 83
            if (r2 != r3) goto L50
            int r2 = r1 + 1
            int r3 = r10.end
            if (r2 >= r3) goto L50
            r4 = r0[r1]
            r5 = 101(0x65, float:1.42E-43)
            if (r4 != r5) goto L50
            r2 = r0[r2]
            r4 = 116(0x74, float:1.63E-43)
            if (r2 != r4) goto L50
            int r2 = r1 + 2
            r4 = 26
            if (r2 != r3) goto L25
            r1 = r2
        L23:
            r2 = r4
            goto L29
        L25:
            int r1 = r1 + 3
            r2 = r0[r2]
        L29:
            r3 = 32
            if (r2 > r3) goto L49
            r5 = 1
            long r5 = r5 << r2
            r7 = 4294981376(0x100003700, double:2.1220027474E-314)
            long r5 = r5 & r7
            r7 = 0
            int r3 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r3 == 0) goto L49
            int r2 = r10.end
            if (r1 != r2) goto L41
            goto L23
        L41:
            int r2 = r1 + 1
            r1 = r0[r1]
            r9 = r2
            r2 = r1
            r1 = r9
            goto L29
        L49:
            r10.offset = r1
            char r0 = (char) r2
            r10.ch = r0
            r0 = 1
            return r0
        L50:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF8.nextIfSet():boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0065 A[LOOP:0: B:24:0x004d->B:30:0x0065, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0047 A[SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:28:0x0062 -> B:22:0x0047). Please report as a decompilation issue!!! */
    @Override // com.alibaba.fastjson2.JSONReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean nextIfInfinity() {
        /*
            r10 = this;
            byte[] r0 = r10.bytes
            int r1 = r10.offset
            char r2 = r10.ch
            r3 = 73
            if (r2 != r3) goto L74
            int r2 = r1 + 6
            int r3 = r10.end
            if (r2 >= r3) goto L74
            r4 = r0[r1]
            r5 = 110(0x6e, float:1.54E-43)
            if (r4 != r5) goto L74
            int r4 = r1 + 1
            r4 = r0[r4]
            r6 = 102(0x66, float:1.43E-43)
            if (r4 != r6) goto L74
            int r4 = r1 + 2
            r4 = r0[r4]
            r6 = 105(0x69, float:1.47E-43)
            if (r4 != r6) goto L74
            int r4 = r1 + 3
            r4 = r0[r4]
            if (r4 != r5) goto L74
            int r4 = r1 + 4
            r4 = r0[r4]
            if (r4 != r6) goto L74
            int r4 = r1 + 5
            r4 = r0[r4]
            r5 = 116(0x74, float:1.63E-43)
            if (r4 != r5) goto L74
            r2 = r0[r2]
            r4 = 121(0x79, float:1.7E-43)
            if (r2 != r4) goto L74
            int r2 = r1 + 7
            r4 = 26
            if (r2 != r3) goto L49
            r1 = r2
        L47:
            r2 = r4
            goto L4d
        L49:
            int r1 = r1 + 8
            r2 = r0[r2]
        L4d:
            r3 = 32
            if (r2 > r3) goto L6d
            r5 = 1
            long r5 = r5 << r2
            r7 = 4294981376(0x100003700, double:2.1220027474E-314)
            long r5 = r5 & r7
            r7 = 0
            int r3 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r3 == 0) goto L6d
            int r2 = r10.end
            if (r1 != r2) goto L65
            goto L47
        L65:
            int r2 = r1 + 1
            r1 = r0[r1]
            r9 = r2
            r2 = r1
            r1 = r9
            goto L4d
        L6d:
            r10.offset = r1
            char r0 = (char) r2
            r10.ch = r0
            r0 = 1
            return r0
        L74:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF8.nextIfInfinity():boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0048 A[LOOP:0: B:10:0x0019->B:27:0x0048, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0013 A[EDGE_INSN: B:28:0x0013->B:8:0x0013 BREAK  A[LOOP:0: B:10:0x0019->B:27:0x0048], SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x0045 -> B:9:0x0013). Please report as a decompilation issue!!! */
    @Override // com.alibaba.fastjson2.JSONReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean nextIfObjectStart() {
        /*
            r9 = this;
            char r0 = r9.ch
            r1 = 123(0x7b, float:1.72E-43)
            if (r0 == r1) goto L8
            r0 = 0
            return r0
        L8:
            byte[] r0 = r9.bytes
            int r1 = r9.offset
            int r2 = r9.end
            r3 = 26
            if (r1 != r2) goto L15
            r2 = r1
        L13:
            r1 = r3
            goto L19
        L15:
            int r2 = r1 + 1
            r1 = r0[r1]
        L19:
            if (r1 == 0) goto L43
            r4 = 32
            if (r1 > r4) goto L2f
            r4 = 1
            long r4 = r4 << r1
            r6 = 4294981376(0x100003700, double:2.1220027474E-314)
            long r4 = r4 & r6
            r6 = 0
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 == 0) goto L2f
            goto L43
        L2f:
            r0 = 1
            if (r1 >= 0) goto L36
            r9.char_utf8(r1, r2)
            return r0
        L36:
            char r3 = (char) r1
            r9.ch = r3
            r9.offset = r2
            r2 = 47
            if (r1 != r2) goto L42
            r9.skipComment()
        L42:
            return r0
        L43:
            int r1 = r9.end
            if (r2 != r1) goto L48
            goto L13
        L48:
            int r1 = r2 + 1
            r2 = r0[r2]
            r8 = r2
            r2 = r1
            r1 = r8
            goto L19
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF8.nextIfObjectStart():boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0077 A[LOOP:0: B:10:0x001b->B:45:0x0077, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0015 A[EDGE_INSN: B:46:0x0015->B:8:0x0015 BREAK  A[LOOP:0: B:10:0x001b->B:45:0x0077], SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:38:0x0074 -> B:9:0x0015). Please report as a decompilation issue!!! */
    @Override // com.alibaba.fastjson2.JSONReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean nextIfObjectEnd() {
        /*
            r17 = this;
            r0 = r17
            char r1 = r0.ch
            byte[] r2 = r0.bytes
            int r3 = r0.offset
            r4 = 125(0x7d, float:1.75E-43)
            if (r1 == r4) goto Le
            r1 = 0
            return r1
        Le:
            int r1 = r0.end
            r4 = 26
            if (r3 != r1) goto L17
            r1 = r3
        L15:
            r3 = r4
            goto L1b
        L17:
            int r1 = r3 + 1
            r3 = r2[r3]
        L1b:
            if (r3 == 0) goto L72
            r5 = 0
            r7 = 4294981376(0x100003700, double:2.1220027474E-314)
            r9 = 1
            r11 = 32
            if (r3 > r11) goto L32
            long r12 = r9 << r3
            long r12 = r12 & r7
            int r12 = (r12 > r5 ? 1 : (r12 == r5 ? 0 : -1))
            if (r12 == 0) goto L32
            goto L72
        L32:
            r12 = 44
            r13 = 1
            if (r3 != r12) goto L5f
            r0.comma = r13
            int r3 = r0.end
            if (r1 != r3) goto L40
            r3 = r1
            r1 = r4
            goto L44
        L40:
            int r3 = r1 + 1
            r1 = r2[r1]
        L44:
            r16 = r3
            r3 = r1
            r1 = r16
        L49:
            if (r3 == 0) goto L54
            if (r3 > r11) goto L5f
            long r14 = r9 << r3
            long r14 = r14 & r7
            int r12 = (r14 > r5 ? 1 : (r14 == r5 ? 0 : -1))
            if (r12 == 0) goto L5f
        L54:
            int r3 = r0.end
            if (r1 != r3) goto L5a
            r3 = r4
            goto L49
        L5a:
            int r3 = r1 + 1
            r1 = r2[r1]
            goto L44
        L5f:
            if (r3 >= 0) goto L65
            r0.char_utf8(r3, r1)
            return r13
        L65:
            char r2 = (char) r3
            r0.ch = r2
            r0.offset = r1
            r1 = 47
            if (r3 != r1) goto L71
            r0.skipComment()
        L71:
            return r13
        L72:
            int r3 = r0.end
            if (r1 != r3) goto L77
            goto L15
        L77:
            int r3 = r1 + 1
            r1 = r2[r1]
            r16 = r3
            r3 = r1
            r1 = r16
            goto L1b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF8.nextIfObjectEnd():boolean");
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX WARN: Removed duplicated region for block: B:24:0x003f A[LOOP:0: B:6:0x0011->B:24:0x003f, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x000b A[EDGE_INSN: B:25:0x000b->B:4:0x000b BREAK  A[LOOP:0: B:6:0x0011->B:24:0x003f], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x003c -> B:4:0x000b). Please report as a decompilation issue!!! */
    @Override // com.alibaba.fastjson2.JSONReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void next() {
        /*
            r9 = this;
            byte[] r0 = r9.bytes
            int r1 = r9.offset
            int r2 = r9.end
            r3 = 26
            if (r1 < r2) goto Ld
            r2 = r1
        Lb:
            r1 = r3
            goto L11
        Ld:
            int r2 = r1 + 1
            r1 = r0[r1]
        L11:
            if (r1 == 0) goto L3a
            r4 = 32
            if (r1 > r4) goto L27
            r4 = 1
            long r4 = r4 << r1
            r6 = 4294981376(0x100003700, double:2.1220027474E-314)
            long r4 = r4 & r6
            r6 = 0
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 == 0) goto L27
            goto L3a
        L27:
            if (r1 >= 0) goto L2d
            r9.char_utf8(r1, r2)
            return
        L2d:
            r9.offset = r2
            char r0 = (char) r1
            r9.ch = r0
            r0 = 47
            if (r1 != r0) goto L39
            r9.skipComment()
        L39:
            return
        L3a:
            int r1 = r9.end
            if (r2 != r1) goto L3f
            goto Lb
        L3f:
            int r1 = r2 + 1
            r2 = r0[r2]
            r8 = r2
            r2 = r1
            r1 = r8
            goto L11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF8.next():void");
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0029  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0038 A[LOOP:0: B:6:0x0011->B:21:0x0038, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x000b A[EDGE_INSN: B:22:0x000b->B:4:0x000b BREAK  A[LOOP:0: B:6:0x0011->B:21:0x0038], SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x0035 -> B:4:0x000b). Please report as a decompilation issue!!! */
    @Override // com.alibaba.fastjson2.JSONReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void nextWithoutComment() {
        /*
            r9 = this;
            byte[] r0 = r9.bytes
            int r1 = r9.offset
            int r2 = r9.end
            r3 = 26
            if (r1 < r2) goto Ld
            r2 = r1
        Lb:
            r1 = r3
            goto L11
        Ld:
            int r2 = r1 + 1
            r1 = r0[r1]
        L11:
            if (r1 == 0) goto L33
            r4 = 32
            if (r1 > r4) goto L27
            r4 = 1
            long r4 = r4 << r1
            r6 = 4294981376(0x100003700, double:2.1220027474E-314)
            long r4 = r4 & r6
            r6 = 0
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 == 0) goto L27
            goto L33
        L27:
            if (r1 >= 0) goto L2d
            r9.char_utf8(r1, r2)
            return
        L2d:
            r9.offset = r2
            char r0 = (char) r1
            r9.ch = r0
            return
        L33:
            int r1 = r9.end
            if (r2 != r1) goto L38
            goto Lb
        L38:
            int r1 = r2 + 1
            r2 = r0[r2]
            r8 = r2
            r2 = r1
            r1 = r8
            goto L11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF8.nextWithoutComment():void");
    }

    /* JADX WARN: Removed duplicated region for block: B:169:0x02d5  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0182  */
    @Override // com.alibaba.fastjson2.JSONReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public long readFieldNameHashCodeUnquote() {
        /*
            Method dump skipped, instructions count: 936
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF8.readFieldNameHashCodeUnquote():long");
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final int getRawInt() {
        if (this.offset + 3 < this.bytes.length) {
            return JDKUtils.UNSAFE.getInt(this.bytes, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + this.offset) - 1);
        }
        return 0;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final long getRawLong() {
        if (this.offset + 8 < this.bytes.length) {
            return JDKUtils.UNSAFE.getLong(this.bytes, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + this.offset) - 1);
        }
        return 0L;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName8Match2() {
        int i;
        byte[] bArr = this.bytes;
        int i2 = this.offset;
        int i3 = i2 + 9;
        if (i3 >= this.end || bArr[i2 + 7] != 34 || bArr[i2 + 8] != 58) {
            return false;
        }
        while (true) {
            i = bArr[i3] & UByte.MAX_VALUE;
            if (i > 32 || ((1 << i) & 4294981376L) == 0) {
                break;
            }
            i3++;
        }
        this.offset = i3 + 1;
        this.ch = (char) i;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName8Match1() {
        int i;
        byte[] bArr = this.bytes;
        int i2 = this.offset;
        int i3 = i2 + 8;
        if (i3 >= this.end || bArr[i2 + 7] != 58) {
            return false;
        }
        while (true) {
            i = bArr[i3] & UByte.MAX_VALUE;
            if (i > 32 || ((1 << i) & 4294981376L) == 0) {
                break;
            }
            i3++;
        }
        this.offset = i3 + 1;
        this.ch = (char) i;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName8Match0() {
        int i;
        byte[] bArr = this.bytes;
        int i2 = this.offset + 7;
        if (i2 == this.end) {
            this.ch = JSONLexer.EOI;
            return false;
        }
        while (true) {
            i = bArr[i2] & UByte.MAX_VALUE;
            if (i > 32 || ((1 << i) & 4294981376L) == 0) {
                break;
            }
            i2++;
        }
        this.offset = i2 + 1;
        this.ch = (char) i;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match2() {
        int i;
        int i2;
        byte[] bArr = this.bytes;
        int i3 = this.offset;
        int i4 = i3 + 4;
        if (i4 >= this.end || bArr[i3 + 3] != 58) {
            return false;
        }
        while (true) {
            i = i4 + 1;
            i2 = bArr[i4] & UByte.MAX_VALUE;
            if (i2 > 32 || ((1 << i2) & 4294981376L) == 0) {
                break;
            }
            i4 = i;
        }
        this.offset = i;
        this.ch = (char) i2;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match3() {
        int i;
        byte[] bArr = this.bytes;
        int i2 = this.offset;
        int i3 = i2 + 5;
        if (i3 >= this.end || bArr[i2 + 3] != 34 || bArr[i2 + 4] != 58) {
            return false;
        }
        while (true) {
            i = bArr[i3] & UByte.MAX_VALUE;
            if (i > 32 || ((1 << i) & 4294981376L) == 0) {
                break;
            }
            i3++;
        }
        this.offset = i3 + 1;
        this.ch = (char) i;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match4(byte b) {
        int i;
        int i2;
        byte[] bArr = this.bytes;
        int i3 = this.offset;
        int i4 = i3 + 6;
        if (i4 >= this.end || bArr[i3 + 3] != b || bArr[i3 + 4] != 34 || bArr[i3 + 5] != 58) {
            return false;
        }
        while (true) {
            i = i4 + 1;
            i2 = bArr[i4] & UByte.MAX_VALUE;
            if (i2 > 32 || ((1 << i2) & 4294981376L) == 0) {
                break;
            }
            i4 = i;
        }
        this.offset = i;
        this.ch = (char) i2;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match5(int i) {
        int i2;
        int i3;
        byte[] bArr = this.bytes;
        int i4 = this.offset + 7;
        if (i4 >= this.end || JDKUtils.UNSAFE.getInt(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + i4) - 4) != i) {
            return false;
        }
        while (true) {
            i2 = i4 + 1;
            i3 = bArr[i4] & UByte.MAX_VALUE;
            if (i3 > 32 || ((1 << i3) & 4294981376L) == 0) {
                break;
            }
            i4 = i2;
        }
        this.offset = i2;
        this.ch = (char) i3;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match6(int i) {
        int i2;
        int i3;
        byte[] bArr = this.bytes;
        int i4 = this.offset;
        int i5 = i4 + 8;
        if (i5 >= this.end || JDKUtils.UNSAFE.getInt(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + i5) - 5) != i || bArr[i4 + 7] != 58) {
            return false;
        }
        while (true) {
            i2 = i5 + 1;
            i3 = bArr[i5] & UByte.MAX_VALUE;
            if (i3 > 32 || ((1 << i3) & 4294981376L) == 0) {
                break;
            }
            i5 = i2;
        }
        this.offset = i2;
        this.ch = (char) i3;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match7(int i) {
        int i2;
        int i3;
        byte[] bArr = this.bytes;
        int i4 = this.offset;
        int i5 = i4 + 9;
        if (i5 >= this.end || JDKUtils.UNSAFE.getInt(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + i5) - 6) != i || bArr[i4 + 7] != 34 || bArr[i4 + 8] != 58) {
            return false;
        }
        while (true) {
            i2 = i5 + 1;
            i3 = bArr[i5] & UByte.MAX_VALUE;
            if (i3 > 32 || ((1 << i3) & 4294981376L) == 0) {
                break;
            }
            i5 = i2;
        }
        this.offset = i2;
        this.ch = (char) i3;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match8(int i, byte b) {
        int i2;
        int i3;
        int i4 = this.offset;
        int i5 = i4 + 10;
        byte[] bArr = this.bytes;
        if (i5 >= this.end || JDKUtils.UNSAFE.getInt(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + i5) - 7) != i || bArr[i4 + 7] != b || bArr[i4 + 8] != 34 || bArr[i4 + 9] != 58) {
            return false;
        }
        while (true) {
            i2 = i5 + 1;
            i3 = bArr[i5] & UByte.MAX_VALUE;
            if (i3 > 32 || ((1 << i3) & 4294981376L) == 0) {
                break;
            }
            i5 = i2;
        }
        this.offset = i2;
        this.ch = (char) i3;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match9(long j) {
        int i;
        int i2;
        byte[] bArr = this.bytes;
        int i3 = this.offset + 11;
        if (i3 >= this.end || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + i3) - 8) != j) {
            return false;
        }
        while (true) {
            i = i3 + 1;
            i2 = bArr[i3] & UByte.MAX_VALUE;
            if (i2 > 32 || ((1 << i2) & 4294981376L) == 0) {
                break;
            }
            i3 = i;
        }
        this.offset = i;
        this.ch = (char) i2;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match10(long j) {
        int i;
        int i2;
        byte[] bArr = this.bytes;
        int i3 = this.offset;
        int i4 = i3 + 12;
        if (i4 >= this.end || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + i4) - 9) != j || bArr[i3 + 11] != 58) {
            return false;
        }
        while (true) {
            i = i4 + 1;
            i2 = bArr[i4] & UByte.MAX_VALUE;
            if (i2 > 32 || ((1 << i2) & 4294981376L) == 0) {
                break;
            }
            i4 = i;
        }
        this.offset = i;
        this.ch = (char) i2;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match11(long j) {
        int i;
        int i2;
        byte[] bArr = this.bytes;
        int i3 = this.offset;
        int i4 = i3 + 13;
        if (i4 >= this.end || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + i4) - 10) != j || bArr[i3 + 11] != 34 || bArr[i3 + 12] != 58) {
            return false;
        }
        while (true) {
            i = i4 + 1;
            i2 = bArr[i4] & UByte.MAX_VALUE;
            if (i2 > 32 || ((1 << i2) & 4294981376L) == 0) {
                break;
            }
            i4 = i;
        }
        this.offset = i;
        this.ch = (char) i2;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match12(long j, byte b) {
        int i;
        int i2;
        byte[] bArr = this.bytes;
        int i3 = this.offset;
        int i4 = i3 + 14;
        if (i4 >= this.end || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + i4) - 11) != j || bArr[i3 + 11] != b || bArr[i3 + 12] != 34 || bArr[i3 + 13] != 58) {
            return false;
        }
        while (true) {
            i = i4 + 1;
            i2 = bArr[i4] & UByte.MAX_VALUE;
            if (i2 > 32 || ((1 << i2) & 4294981376L) == 0) {
                break;
            }
            i4 = i;
        }
        this.offset = i;
        this.ch = (char) i2;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match13(long j, int i) {
        int i2;
        int i3;
        byte[] bArr = this.bytes;
        int i4 = this.offset + 15;
        if (i4 >= this.end) {
            return false;
        }
        long j2 = i4;
        if (JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j2) - 12) != j || JDKUtils.UNSAFE.getInt(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j2) - 4) != i) {
            return false;
        }
        while (true) {
            i2 = i4 + 1;
            i3 = bArr[i4] & UByte.MAX_VALUE;
            if (i3 > 32 || ((1 << i3) & 4294981376L) == 0) {
                break;
            }
            i4 = i2;
        }
        this.offset = i2;
        this.ch = (char) i3;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match14(long j, int i) {
        int i2;
        int i3;
        byte[] bArr = this.bytes;
        int i4 = this.offset;
        int i5 = i4 + 16;
        if (i5 >= this.end) {
            return false;
        }
        long j2 = i5;
        if (JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j2) - 13) != j || JDKUtils.UNSAFE.getInt(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j2) - 5) != i || bArr[i4 + 15] != 58) {
            return false;
        }
        while (true) {
            i2 = i5 + 1;
            i3 = bArr[i5] & UByte.MAX_VALUE;
            if (i3 > 32 || ((1 << i3) & 4294981376L) == 0) {
                break;
            }
            i5 = i2;
        }
        this.offset = i2;
        this.ch = (char) i3;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match15(long j, int i) {
        int i2;
        int i3;
        byte[] bArr = this.bytes;
        int i4 = this.offset;
        int i5 = i4 + 17;
        if (i5 >= this.end) {
            return false;
        }
        long j2 = i5;
        if (JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j2) - 14) != j || JDKUtils.UNSAFE.getInt(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j2) - 6) != i || bArr[i4 + 15] != 34 || bArr[i4 + 16] != 58) {
            return false;
        }
        while (true) {
            i2 = i5 + 1;
            i3 = bArr[i5] & UByte.MAX_VALUE;
            if (i3 > 32 || ((1 << i3) & 4294981376L) == 0) {
                break;
            }
            i5 = i2;
        }
        this.offset = i2;
        this.ch = (char) i3;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match16(long j, int i, byte b) {
        int i2;
        int i3;
        byte[] bArr = this.bytes;
        int i4 = this.offset;
        int i5 = i4 + 18;
        if (i5 >= this.end) {
            return false;
        }
        long j2 = i5;
        if (JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j2) - 15) != j || JDKUtils.UNSAFE.getInt(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j2) - 7) != i || bArr[i4 + 15] != b || bArr[i4 + 16] != 34 || bArr[i4 + 17] != 58) {
            return false;
        }
        while (true) {
            i2 = i5 + 1;
            i3 = bArr[i5] & UByte.MAX_VALUE;
            if (i3 > 32 || ((1 << i3) & 4294981376L) == 0) {
                break;
            }
            i5 = i2;
        }
        this.offset = i2;
        this.ch = (char) i3;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match17(long j, long j2) {
        int i;
        int i2;
        byte[] bArr = this.bytes;
        int i3 = this.offset + 19;
        if (i3 >= this.end) {
            return false;
        }
        long j3 = i3;
        if (JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j3) - 16) != j || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j3) - 8) != j2) {
            return false;
        }
        while (true) {
            i = i3 + 1;
            i2 = bArr[i3] & UByte.MAX_VALUE;
            if (i2 > 32 || ((1 << i2) & 4294981376L) == 0) {
                break;
            }
            i3 = i;
        }
        this.offset = i;
        this.ch = (char) i2;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match18(long j, long j2) {
        int i;
        int i2;
        byte[] bArr = this.bytes;
        int i3 = this.offset;
        int i4 = i3 + 20;
        if (i4 >= this.end) {
            return false;
        }
        long j3 = i4;
        if (JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j3) - 17) != j || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j3) - 9) != j2 || bArr[i3 + 19] != 58) {
            return false;
        }
        while (true) {
            i = i4 + 1;
            i2 = bArr[i4] & UByte.MAX_VALUE;
            if (i2 > 32 || ((1 << i2) & 4294981376L) == 0) {
                break;
            }
            i4 = i;
        }
        this.offset = i;
        this.ch = (char) i2;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match19(long j, long j2) {
        int i;
        int i2;
        byte[] bArr = this.bytes;
        int i3 = this.offset;
        int i4 = i3 + 21;
        if (i4 >= this.end) {
            return false;
        }
        long j3 = i4;
        if (JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j3) - 18) != j || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j3) - 10) != j2 || bArr[i3 + 19] != 34 || bArr[i3 + 20] != 58) {
            return false;
        }
        while (true) {
            i = i4 + 1;
            i2 = bArr[i4] & UByte.MAX_VALUE;
            if (i2 > 32 || ((1 << i2) & 4294981376L) == 0) {
                break;
            }
            i4 = i;
        }
        this.offset = i;
        this.ch = (char) i2;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match20(long j, long j2, byte b) {
        int i;
        int i2;
        byte[] bArr = this.bytes;
        int i3 = this.offset;
        int i4 = i3 + 22;
        if (i4 >= this.end) {
            return false;
        }
        long j3 = i4;
        if (JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j3) - 19) != j || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j3) - 11) != j2 || bArr[i3 + 19] != b || bArr[i3 + 20] != 34 || bArr[i3 + 21] != 58) {
            return false;
        }
        while (true) {
            i = i4 + 1;
            i2 = bArr[i4] & UByte.MAX_VALUE;
            if (i2 > 32 || ((1 << i2) & 4294981376L) == 0) {
                break;
            }
            i4 = i;
        }
        this.offset = i;
        this.ch = (char) i2;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match21(long j, long j2, int i) {
        int i2;
        int i3;
        byte[] bArr = this.bytes;
        int i4 = this.offset + 23;
        if (i4 >= this.end) {
            return false;
        }
        long j3 = i4;
        if (JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j3) - 20) != j || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j3) - 12) != j2 || JDKUtils.UNSAFE.getInt(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j3) - 4) != i) {
            return false;
        }
        while (true) {
            i2 = i4 + 1;
            i3 = bArr[i4] & UByte.MAX_VALUE;
            if (i3 > 32 || ((1 << i3) & 4294981376L) == 0) {
                break;
            }
            i4 = i2;
        }
        this.offset = i2;
        this.ch = (char) i3;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match22(long j, long j2, int i) {
        int i2;
        int i3;
        byte[] bArr = this.bytes;
        int i4 = this.offset;
        int i5 = i4 + 24;
        if (i5 >= this.end) {
            return false;
        }
        long j3 = i5;
        if (JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j3) - 21) != j || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j3) - 13) != j2 || JDKUtils.UNSAFE.getInt(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j3) - 5) != i || bArr[i4 + 23] != 58) {
            return false;
        }
        while (true) {
            i2 = i5 + 1;
            i3 = bArr[i5] & UByte.MAX_VALUE;
            if (i3 > 32 || ((1 << i3) & 4294981376L) == 0) {
                break;
            }
            i5 = i2;
        }
        this.offset = i2;
        this.ch = (char) i3;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match23(long j, long j2, int i) {
        int i2;
        int i3;
        byte[] bArr = this.bytes;
        int i4 = this.offset;
        int i5 = i4 + 25;
        if (i5 >= this.end) {
            return false;
        }
        long j3 = i5;
        if (JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j3) - 22) != j || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j3) - 14) != j2 || JDKUtils.UNSAFE.getInt(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j3) - 6) != i || bArr[i4 + 23] != 34 || bArr[i4 + 24] != 58) {
            return false;
        }
        while (true) {
            i2 = i5 + 1;
            i3 = bArr[i5] & UByte.MAX_VALUE;
            if (i3 > 32 || ((1 << i3) & 4294981376L) == 0) {
                break;
            }
            i5 = i2;
        }
        this.offset = i2;
        this.ch = (char) i3;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match24(long j, long j2, int i, byte b) {
        int i2;
        int i3;
        byte[] bArr = this.bytes;
        int i4 = this.offset;
        int i5 = i4 + 26;
        if (i5 >= this.end) {
            return false;
        }
        long j3 = i5;
        if (JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j3) - 23) != j || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j3) - 15) != j2 || JDKUtils.UNSAFE.getInt(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j3) - 7) != i || bArr[i4 + 23] != b || bArr[i4 + 24] != 34 || bArr[i4 + 25] != 58) {
            return false;
        }
        while (true) {
            i2 = i5 + 1;
            i3 = bArr[i5] & UByte.MAX_VALUE;
            if (i3 > 32 || ((1 << i3) & 4294981376L) == 0) {
                break;
            }
            i5 = i2;
        }
        this.offset = i2;
        this.ch = (char) i3;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match25(long j, long j2, long j3) {
        int i;
        int i2;
        byte[] bArr = this.bytes;
        int i3 = this.offset + 27;
        if (i3 >= this.end) {
            return false;
        }
        long j4 = i3;
        if (JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j4) - 24) != j || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j4) - 16) != j2 || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j4) - 8) != j3) {
            return false;
        }
        while (true) {
            i = i3 + 1;
            i2 = bArr[i3] & UByte.MAX_VALUE;
            if (i2 > 32 || ((1 << i2) & 4294981376L) == 0) {
                break;
            }
            i3 = i;
        }
        this.offset = i;
        this.ch = (char) i2;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match26(long j, long j2, long j3) {
        int i;
        int i2;
        byte[] bArr = this.bytes;
        int i3 = this.offset;
        int i4 = i3 + 28;
        if (i4 >= this.end) {
            return false;
        }
        long j4 = i4;
        if (JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j4) - 25) != j || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j4) - 17) != j2 || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j4) - 9) != j3 || bArr[i3 + 27] != 58) {
            return false;
        }
        while (true) {
            i = i4 + 1;
            i2 = bArr[i4] & UByte.MAX_VALUE;
            if (i2 > 32 || ((1 << i2) & 4294981376L) == 0) {
                break;
            }
            i4 = i;
        }
        this.offset = i;
        this.ch = (char) i2;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match27(long j, long j2, long j3) {
        int i;
        int i2;
        byte[] bArr = this.bytes;
        int i3 = this.offset;
        int i4 = i3 + 29;
        if (i4 >= this.end) {
            return false;
        }
        long j4 = i4;
        if (JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j4) - 26) != j || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j4) - 18) != j2 || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j4) - 10) != j3 || bArr[i3 + 27] != 34 || bArr[i3 + 28] != 58) {
            return false;
        }
        while (true) {
            i = i4 + 1;
            i2 = bArr[i4] & UByte.MAX_VALUE;
            if (i2 > 32 || ((1 << i2) & 4294981376L) == 0) {
                break;
            }
            i4 = i;
        }
        this.offset = i;
        this.ch = (char) i2;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match28(long j, long j2, long j3, byte b) {
        int i;
        int i2;
        byte[] bArr = this.bytes;
        int i3 = this.offset;
        int i4 = i3 + 30;
        if (i4 >= this.end) {
            return false;
        }
        long j4 = i4;
        if (JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j4) - 27) != j || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j4) - 19) != j2 || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j4) - 11) != j3 || bArr[i3 + 27] != b || bArr[i3 + 28] != 34 || bArr[i3 + 29] != 58) {
            return false;
        }
        while (true) {
            i = i4 + 1;
            i2 = bArr[i4] & UByte.MAX_VALUE;
            if (i2 > 32 || ((1 << i2) & 4294981376L) == 0) {
                break;
            }
            i4 = i;
        }
        this.offset = i;
        this.ch = (char) i2;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match29(long j, long j2, long j3, int i) {
        int i2;
        int i3;
        byte[] bArr = this.bytes;
        int i4 = this.offset + 31;
        if (i4 >= this.end) {
            return false;
        }
        long j4 = i4;
        if (JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j4) - 28) != j || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j4) - 20) != j2 || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j4) - 12) != j3 || JDKUtils.UNSAFE.getInt(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j4) - 4) != i) {
            return false;
        }
        while (true) {
            i2 = i4 + 1;
            i3 = bArr[i4] & UByte.MAX_VALUE;
            if (i3 > 32 || ((1 << i3) & 4294981376L) == 0) {
                break;
            }
            i4 = i2;
        }
        this.offset = i2;
        this.ch = (char) i3;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match30(long j, long j2, long j3, int i) {
        int i2;
        int i3;
        byte[] bArr = this.bytes;
        int i4 = this.offset;
        int i5 = i4 + 32;
        if (i5 >= this.end) {
            return false;
        }
        long j4 = i5;
        if (JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j4) - 29) != j || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j4) - 21) != j2 || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j4) - 13) != j3 || JDKUtils.UNSAFE.getInt(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j4) - 5) != i || bArr[i4 + 31] != 58) {
            return false;
        }
        while (true) {
            i2 = i5 + 1;
            i3 = bArr[i5] & UByte.MAX_VALUE;
            if (i3 > 32 || ((1 << i3) & 4294981376L) == 0) {
                break;
            }
            i5 = i2;
        }
        this.offset = i2;
        this.ch = (char) i3;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match31(long j, long j2, long j3, int i) {
        int i2;
        int i3;
        byte[] bArr = this.bytes;
        int i4 = this.offset;
        int i5 = i4 + 33;
        if (i5 >= this.end || bArr[i4 + 31] != 34 || bArr[i4 + 32] != 58) {
            return false;
        }
        long j4 = i5;
        if (JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j4) - 30) != j || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j4) - 22) != j2 || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j4) - 14) != j3 || JDKUtils.UNSAFE.getInt(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j4) - 6) != i) {
            return false;
        }
        while (true) {
            i2 = i5 + 1;
            i3 = bArr[i5] & UByte.MAX_VALUE;
            if (i3 > 32 || ((1 << i3) & 4294981376L) == 0) {
                break;
            }
            i5 = i2;
        }
        this.offset = i2;
        this.ch = (char) i3;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match32(long j, long j2, long j3, int i, byte b) {
        int i2;
        int i3;
        byte[] bArr = this.bytes;
        int i4 = this.offset;
        int i5 = i4 + 34;
        if (i5 >= this.end) {
            return false;
        }
        long j4 = i5;
        if (JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j4) - 31) != j || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j4) - 23) != j2 || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j4) - 15) != j3 || JDKUtils.UNSAFE.getInt(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j4) - 7) != i || bArr[i4 + 31] != b || bArr[i4 + 32] != 34 || bArr[i4 + 33] != 58) {
            return false;
        }
        while (true) {
            i2 = i5 + 1;
            i3 = bArr[i5] & UByte.MAX_VALUE;
            if (i3 > 32 || ((1 << i3) & 4294981376L) == 0) {
                break;
            }
            i5 = i2;
        }
        this.offset = i2;
        this.ch = (char) i3;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match33(long j, long j2, long j3, long j4) {
        int i;
        int i2;
        byte[] bArr = this.bytes;
        int i3 = this.offset + 35;
        if (i3 >= this.end) {
            return false;
        }
        long j5 = i3;
        if (JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j5) - 32) != j || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j5) - 24) != j2 || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j5) - 16) != j3 || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j5) - 8) != j4) {
            return false;
        }
        while (true) {
            i = i3 + 1;
            i2 = bArr[i3] & UByte.MAX_VALUE;
            if (i2 > 32 || ((1 << i2) & 4294981376L) == 0) {
                break;
            }
            i3 = i;
        }
        this.offset = i;
        this.ch = (char) i2;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match34(long j, long j2, long j3, long j4) {
        int i;
        int i2;
        byte[] bArr = this.bytes;
        int i3 = this.offset;
        int i4 = i3 + 36;
        if (i4 >= this.end || bArr[i3 + 35] != 58) {
            return false;
        }
        long j5 = i4;
        if (JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j5) - 33) != j || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j5) - 25) != j2 || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j5) - 17) != j3 || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j5) - 9) != j4) {
            return false;
        }
        while (true) {
            i = i4 + 1;
            i2 = bArr[i4] & UByte.MAX_VALUE;
            if (i2 > 32 || ((1 << i2) & 4294981376L) == 0) {
                break;
            }
            i4 = i;
        }
        this.offset = i;
        this.ch = (char) i2;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match35(long j, long j2, long j3, long j4) {
        int i;
        int i2;
        byte[] bArr = this.bytes;
        int i3 = this.offset;
        int i4 = i3 + 37;
        if (i4 >= this.end) {
            return false;
        }
        long j5 = i4;
        if (JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j5) - 34) != j || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j5) - 26) != j2 || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j5) - 18) != j3 || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j5) - 10) != j4 || bArr[i3 + 35] != 34 || bArr[i3 + 36] != 58) {
            return false;
        }
        while (true) {
            i = i4 + 1;
            i2 = bArr[i4] & UByte.MAX_VALUE;
            if (i2 > 32 || ((1 << i2) & 4294981376L) == 0) {
                break;
            }
            i4 = i;
        }
        this.offset = i;
        this.ch = (char) i2;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match36(long j, long j2, long j3, long j4, byte b) {
        int i;
        int i2;
        byte[] bArr = this.bytes;
        int i3 = this.offset;
        int i4 = i3 + 38;
        if (i4 >= this.end) {
            return false;
        }
        long j5 = i4;
        if (JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j5) - 35) != j || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j5) - 27) != j2 || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j5) - 19) != j3 || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j5) - 11) != j4 || bArr[i3 + 35] != b || bArr[i3 + 36] != 34 || bArr[i3 + 37] != 58) {
            return false;
        }
        while (true) {
            i = i4 + 1;
            i2 = bArr[i4] & UByte.MAX_VALUE;
            if (i2 > 32 || ((1 << i2) & 4294981376L) == 0) {
                break;
            }
            i4 = i;
        }
        this.offset = i;
        this.ch = (char) i2;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match37(long j, long j2, long j3, long j4, int i) {
        int i2;
        int i3;
        byte[] bArr = this.bytes;
        int i4 = this.offset + 39;
        if (i4 >= this.end) {
            return false;
        }
        long j5 = i4;
        if (JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j5) - 36) != j || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j5) - 28) != j2 || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j5) - 20) != j3 || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j5) - 12) != j4 || JDKUtils.UNSAFE.getInt(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j5) - 4) != i) {
            return false;
        }
        while (true) {
            i2 = i4 + 1;
            i3 = bArr[i4] & UByte.MAX_VALUE;
            if (i3 > 32 || ((1 << i3) & 4294981376L) == 0) {
                break;
            }
            i4 = i2;
        }
        this.offset = i2;
        this.ch = (char) i3;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match38(long j, long j2, long j3, long j4, int i) {
        int i2;
        int i3;
        byte[] bArr = this.bytes;
        int i4 = this.offset;
        int i5 = i4 + 40;
        if (i5 >= this.end) {
            return false;
        }
        long j5 = i5;
        if (JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j5) - 37) != j || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j5) - 29) != j2 || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j5) - 21) != j3 || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j5) - 13) != j4 || JDKUtils.UNSAFE.getInt(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j5) - 5) != i || bArr[i4 + 39] != 58) {
            return false;
        }
        while (true) {
            i2 = i5 + 1;
            i3 = bArr[i5] & UByte.MAX_VALUE;
            if (i3 > 32 || ((1 << i3) & 4294981376L) == 0) {
                break;
            }
            i5 = i2;
        }
        this.offset = i2;
        this.ch = (char) i3;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match39(long j, long j2, long j3, long j4, int i) {
        int i2;
        int i3;
        byte[] bArr = this.bytes;
        int i4 = this.offset;
        int i5 = i4 + 41;
        if (i5 >= this.end) {
            return false;
        }
        long j5 = i5;
        if (JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j5) - 38) != j || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j5) - 30) != j2 || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j5) - 22) != j3 || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j5) - 14) != j4 || JDKUtils.UNSAFE.getInt(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j5) - 6) != i || bArr[i4 + 39] != 34 || bArr[i4 + 40] != 58) {
            return false;
        }
        while (true) {
            i2 = i5 + 1;
            i3 = bArr[i5] & UByte.MAX_VALUE;
            if (i3 > 32 || ((1 << i3) & 4294981376L) == 0) {
                break;
            }
            i5 = i2;
        }
        this.offset = i2;
        this.ch = (char) i3;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match40(long j, long j2, long j3, long j4, int i, byte b) {
        int i2;
        int i3;
        byte[] bArr = this.bytes;
        int i4 = this.offset;
        int i5 = i4 + 42;
        if (i5 >= this.end) {
            return false;
        }
        long j5 = i5;
        if (JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j5) - 39) != j || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j5) - 31) != j2 || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j5) - 23) != j3 || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j5) - 15) != j4 || JDKUtils.UNSAFE.getInt(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j5) - 7) != i || bArr[i4 + 39] != b || bArr[i4 + 40] != 34 || bArr[i4 + 41] != 58) {
            return false;
        }
        while (true) {
            i2 = i5 + 1;
            i3 = bArr[i5] & UByte.MAX_VALUE;
            if (i3 > 32 || ((1 << i3) & 4294981376L) == 0) {
                break;
            }
            i5 = i2;
        }
        this.offset = i2;
        this.ch = (char) i3;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match41(long j, long j2, long j3, long j4, long j5) {
        int i;
        int i2;
        byte[] bArr = this.bytes;
        int i3 = this.offset + 43;
        if (i3 >= this.end) {
            return false;
        }
        long j6 = i3;
        if (JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j6) - 40) != j || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j6) - 32) != j2 || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j6) - 24) != j3 || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j6) - 16) != j4 || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j6) - 8) != j5) {
            return false;
        }
        while (true) {
            i = i3 + 1;
            i2 = bArr[i3] & UByte.MAX_VALUE;
            if (i2 > 32 || ((1 << i2) & 4294981376L) == 0) {
                break;
            }
            i3 = i;
        }
        this.offset = i;
        this.ch = (char) i2;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match42(long j, long j2, long j3, long j4, long j5) {
        int i;
        int i2;
        byte[] bArr = this.bytes;
        int i3 = this.offset;
        int i4 = i3 + 44;
        if (i4 >= this.end) {
            return false;
        }
        long j6 = i4;
        if (JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j6) - 41) != j || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j6) - 33) != j2 || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j6) - 25) != j3 || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j6) - 17) != j4 || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j6) - 9) != j5 || bArr[i3 + 43] != 58) {
            return false;
        }
        while (true) {
            i = i4 + 1;
            i2 = bArr[i4] & UByte.MAX_VALUE;
            if (i2 > 32 || ((1 << i2) & 4294981376L) == 0) {
                break;
            }
            i4 = i;
        }
        this.offset = i;
        this.ch = (char) i2;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match43(long j, long j2, long j3, long j4, long j5) {
        int i;
        int i2;
        byte[] bArr = this.bytes;
        int i3 = this.offset;
        int i4 = i3 + 45;
        if (i4 >= this.end) {
            return false;
        }
        long j6 = i4;
        if (JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j6) - 42) != j || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j6) - 34) != j2 || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j6) - 26) != j3 || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j6) - 18) != j4 || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + j6) - 10) != j5 || bArr[i3 + 43] != 34 || bArr[i3 + 44] != 58) {
            return false;
        }
        while (true) {
            i = i4 + 1;
            i2 = bArr[i4] & UByte.MAX_VALUE;
            if (i2 > 32 || ((1 << i2) & 4294981376L) == 0) {
                break;
            }
            i4 = i;
        }
        this.offset = i;
        this.ch = (char) i2;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfValue4Match2() {
        byte[] bArr = this.bytes;
        int i = this.offset;
        int i2 = i + 3;
        if (i2 >= this.end) {
            return false;
        }
        int i3 = i + 4;
        int i4 = bArr[i2] & UByte.MAX_VALUE;
        if (i4 != 44 && i4 != 125 && i4 != 93) {
            return false;
        }
        if (i4 == 44) {
            this.comma = true;
            if (i3 == this.end) {
                i4 = 26;
            } else {
                int i5 = i + 5;
                i4 = bArr[i3] & UByte.MAX_VALUE;
                i3 = i5;
            }
        }
        while (i4 <= 32 && ((1 << i4) & 4294981376L) != 0) {
            int i6 = i3 + 1;
            i4 = bArr[i3] & UByte.MAX_VALUE;
            i3 = i6;
        }
        this.offset = i3;
        this.ch = (char) i4;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfValue4Match3() {
        byte[] bArr = this.bytes;
        int i = this.offset;
        int i2 = i + 4;
        if (i2 >= this.end || bArr[i + 3] != 34) {
            return false;
        }
        int i3 = bArr[i2] & UByte.MAX_VALUE;
        if (i3 != 44 && i3 != 125 && i3 != 93) {
            return false;
        }
        if (i3 == 44) {
            this.comma = true;
            i2 = i + 5;
            i3 = i2 == this.end ? 26 : bArr[i2] & UByte.MAX_VALUE;
        }
        while (i3 <= 32 && ((1 << i3) & 4294981376L) != 0) {
            i2++;
            i3 = bArr[i2] & UByte.MAX_VALUE;
        }
        this.offset = i2 + 1;
        this.ch = (char) i3;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfValue4Match4(byte b) {
        byte[] bArr = this.bytes;
        int i = this.offset;
        int i2 = i + 5;
        boolean z = false;
        if (i2 >= this.end) {
            return false;
        }
        if (bArr[i + 3] == b && bArr[i + 4] == 34) {
            int i3 = bArr[i2] & UByte.MAX_VALUE;
            if (i3 != 44 && i3 != 125 && i3 != 93) {
                return false;
            }
            z = true;
            if (i3 == 44) {
                this.comma = true;
                i2 = i + 6;
                i3 = i2 == this.end ? 26 : bArr[i2] & UByte.MAX_VALUE;
            }
            while (i3 <= 32 && ((1 << i3) & 4294981376L) != 0) {
                i2++;
                i3 = bArr[i2] & UByte.MAX_VALUE;
            }
            this.offset = i2 + 1;
            this.ch = (char) i3;
        }
        return z;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfValue4Match5(byte b, byte b2) {
        byte[] bArr = this.bytes;
        int i = this.offset;
        int i2 = i + 6;
        if (i2 >= this.end || bArr[i + 3] != b || bArr[i + 4] != b2 || bArr[i + 5] != 34) {
            return false;
        }
        int i3 = bArr[i2] & UByte.MAX_VALUE;
        if (i3 != 44 && i3 != 125 && i3 != 93) {
            return false;
        }
        if (i3 == 44) {
            this.comma = true;
            i2 = i + 7;
            i3 = i2 == this.end ? 26 : bArr[i2] & UByte.MAX_VALUE;
        }
        while (i3 <= 32 && ((1 << i3) & 4294981376L) != 0) {
            i2++;
            i3 = bArr[i2] & UByte.MAX_VALUE;
        }
        this.offset = i2 + 1;
        this.ch = (char) i3;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfValue4Match6(int i) {
        byte[] bArr = this.bytes;
        int i2 = this.offset;
        int i3 = i2 + 7;
        if (i3 >= this.end || JDKUtils.UNSAFE.getInt(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + i3) - 4) != i) {
            return false;
        }
        int i4 = bArr[i3] & UByte.MAX_VALUE;
        if (i4 != 44 && i4 != 125 && i4 != 93) {
            return false;
        }
        if (i4 == 44) {
            this.comma = true;
            i3 = i2 + 8;
            i4 = i3 == this.end ? 26 : bArr[i3] & UByte.MAX_VALUE;
        }
        while (i4 <= 32 && ((1 << i4) & 4294981376L) != 0) {
            i3++;
            i4 = bArr[i3] & UByte.MAX_VALUE;
        }
        this.offset = i3 + 1;
        this.ch = (char) i4;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfValue4Match7(int i) {
        byte[] bArr = this.bytes;
        int i2 = this.offset;
        int i3 = i2 + 8;
        boolean z = false;
        if (i3 >= this.end) {
            return false;
        }
        if (JDKUtils.UNSAFE.getInt(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + i3) - 5) == i && bArr[i2 + 7] == 34) {
            int i4 = bArr[i3] & UByte.MAX_VALUE;
            if (i4 != 44 && i4 != 125 && i4 != 93) {
                return false;
            }
            z = true;
            if (i4 == 44) {
                this.comma = true;
                i3 = i2 + 9;
                i4 = i3 == this.end ? 26 : bArr[i3] & UByte.MAX_VALUE;
            }
            while (i4 <= 32 && ((1 << i4) & 4294981376L) != 0) {
                i3++;
                i4 = bArr[i3] & UByte.MAX_VALUE;
            }
            this.offset = i3 + 1;
            this.ch = (char) i4;
        }
        return z;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfValue4Match8(int i, byte b) {
        byte[] bArr = this.bytes;
        int i2 = this.offset;
        int i3 = i2 + 9;
        if (i3 >= this.end || JDKUtils.UNSAFE.getInt(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + i3) - 6) != i || bArr[i2 + 7] != b || bArr[i2 + 8] != 34) {
            return false;
        }
        int i4 = bArr[i3] & UByte.MAX_VALUE;
        if (i4 != 44 && i4 != 125 && i4 != 93) {
            return false;
        }
        if (i4 == 44) {
            this.comma = true;
            i3 = i2 + 10;
            i4 = i3 == this.end ? 26 : bArr[i3] & UByte.MAX_VALUE;
        }
        while (i4 <= 32 && ((1 << i4) & 4294981376L) != 0) {
            i3++;
            i4 = bArr[i3] & UByte.MAX_VALUE;
        }
        this.offset = i3 + 1;
        this.ch = (char) i4;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfValue4Match9(int i, byte b, byte b2) {
        byte[] bArr = this.bytes;
        int i2 = this.offset;
        int i3 = i2 + 10;
        if (i3 >= this.end || JDKUtils.UNSAFE.getInt(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + i3) - 7) != i || bArr[i2 + 7] != b || bArr[i2 + 8] != b2 || bArr[i2 + 9] != 34) {
            return false;
        }
        int i4 = bArr[i3] & UByte.MAX_VALUE;
        if (i4 != 44 && i4 != 125 && i4 != 93) {
            return false;
        }
        if (i4 == 44) {
            this.comma = true;
            i3 = i2 + 11;
            i4 = i3 == this.end ? 26 : bArr[i3] & UByte.MAX_VALUE;
        }
        while (i4 <= 32 && ((1 << i4) & 4294981376L) != 0) {
            i3++;
            i4 = bArr[i3] & UByte.MAX_VALUE;
        }
        this.offset = i3 + 1;
        this.ch = (char) i4;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfValue4Match10(long j) {
        byte[] bArr = this.bytes;
        int i = this.offset;
        int i2 = i + 11;
        if (i2 >= this.end || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + i2) - 8) != j) {
            return false;
        }
        int i3 = bArr[i2] & UByte.MAX_VALUE;
        if (i3 != 44 && i3 != 125 && i3 != 93) {
            return false;
        }
        if (i3 == 44) {
            this.comma = true;
            i2 = i + 12;
            i3 = i2 == this.end ? 26 : bArr[i2] & UByte.MAX_VALUE;
        }
        while (i3 <= 32 && ((1 << i3) & 4294981376L) != 0) {
            i2++;
            i3 = bArr[i2] & UByte.MAX_VALUE;
        }
        this.offset = i2 + 1;
        this.ch = (char) i3;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfValue4Match11(long j) {
        byte[] bArr = this.bytes;
        int i = this.offset;
        int i2 = i + 12;
        if (i2 >= this.end || JDKUtils.UNSAFE.getLong(bArr, (JDKUtils.ARRAY_BYTE_BASE_OFFSET + i2) - 9) != j || bArr[i + 11] != 34) {
            return false;
        }
        int i3 = bArr[i2] & UByte.MAX_VALUE;
        if (i3 != 44 && i3 != 125 && i3 != 93) {
            return false;
        }
        if (i3 == 44) {
            this.comma = true;
            i2 = i + 13;
            i3 = i2 == this.end ? 26 : bArr[i2] & UByte.MAX_VALUE;
        }
        while (i3 <= 32 && ((1 << i3) & 4294981376L) != 0) {
            i2++;
            i3 = bArr[i2] & UByte.MAX_VALUE;
        }
        this.offset = i2 + 1;
        this.ch = (char) i3;
        return true;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:66:0x02e5. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:130:0x03a4  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x03af  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x03be  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x03c0  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x03cc  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x03f6  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x03a6  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x026c  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x034b  */
    @Override // com.alibaba.fastjson2.JSONReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public long readFieldNameHashCode() {
        /*
            Method dump skipped, instructions count: 1150
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF8.readFieldNameHashCode():long");
    }

    /* JADX WARN: Code restructure failed: missing block: B:104:0x0235, code lost:
    
        throw new com.alibaba.fastjson2.JSONException("malformed input around byte " + r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0034, code lost:
    
        r11 = 0;
     */
    /* JADX WARN: Removed duplicated region for block: B:39:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0142 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0166  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x016e  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0170 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0169  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:58:0x015a -> B:53:0x0142). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:71:0x0181 -> B:67:0x0170). Please report as a decompilation issue!!! */
    @Override // com.alibaba.fastjson2.JSONReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public long readValueHashCode() {
        /*
            Method dump skipped, instructions count: 616
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF8.readValueHashCode():long");
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public long getNameHashCodeLCase() {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int hexDigit4;
        int i6;
        int i7;
        int i8;
        long j;
        long j2;
        int i9 = this.nameBegin;
        int i10 = this.end;
        byte[] bArr = this.bytes;
        boolean z = (i9 <= 0 || bArr[i9 + (-1)] != '\'') ? 34 : 39;
        int i11 = 0;
        long j3 = 0;
        while (true) {
            i = 95;
            i2 = 32;
            if (i9 < i10) {
                int i12 = bArr[i9];
                if (i12 == 92) {
                    int i13 = i9 + 1;
                    int i14 = bArr[i13];
                    if (i14 == 117) {
                        i12 = IOUtils.hexDigit4(bArr, JSONReaderJSONB.check3(i9 + 2, i10));
                        i9 += 5;
                    } else if (i14 == 120) {
                        int i15 = bArr[i9 + 2];
                        i9 += 3;
                        i12 = char2(i15, bArr[i9]);
                    } else {
                        i12 = char1(i14);
                        i9 = i13;
                    }
                } else if (i12 == -61 || i12 == -62) {
                    i9++;
                    i12 = ((i12 & 31) << 6) | (bArr[i9] & '?');
                } else if (i12 == z) {
                }
                if (i11 < 8 && i12 <= 255 && i12 >= 0 && (i11 != 0 || i12 != 0)) {
                    if ((i12 != 95 && i12 != 45 && i12 != 32) || (i8 = bArr[i9 + 1]) == 34 || i8 == 39 || i8 == i12) {
                        if (i12 >= 65 && i12 <= 90) {
                            i12 = (char) (i12 + 32);
                        }
                        switch (i11) {
                            case 0:
                                j3 = (byte) i12;
                                break;
                            case 1:
                                j = ((byte) i12) << 8;
                                j2 = 255;
                                j3 = (j3 & j2) + j;
                                break;
                            case 2:
                                j = ((byte) i12) << JSONB.Constants.BC_INT32_NUM_16;
                                j2 = WebSocketProtocol.PAYLOAD_SHORT_MAX;
                                j3 = (j3 & j2) + j;
                                break;
                            case 3:
                                j = ((byte) i12) << 24;
                                j2 = 16777215;
                                j3 = (j3 & j2) + j;
                                break;
                            case 4:
                                j = ((byte) i12) << 32;
                                j2 = 4294967295L;
                                j3 = (j3 & j2) + j;
                                break;
                            case 5:
                                j = ((byte) i12) << 40;
                                j2 = 1099511627775L;
                                j3 = (j3 & j2) + j;
                                break;
                            case 6:
                                j = ((byte) i12) << 48;
                                j2 = 281474976710655L;
                                j3 = (j3 & j2) + j;
                                break;
                            case 7:
                                j = ((byte) i12) << 56;
                                j2 = 72057594037927935L;
                                j3 = (j3 & j2) + j;
                                break;
                        }
                        i11++;
                    }
                    i9++;
                }
            }
        }
        i9 = this.nameBegin;
        j3 = 0;
        if (j3 != 0) {
            return j3;
        }
        boolean z2 = this.nameAscii;
        long j4 = Fnv.MAGIC_HASH_CODE;
        if (z2 && !this.nameEscape) {
            for (int i16 = this.nameBegin; i16 < this.nameEnd; i16++) {
                int i17 = bArr[i16];
                if (i17 >= 65 && i17 <= 90) {
                    i17 += 32;
                }
                if ((i17 == 95 || i17 == 45 || i17 == 32) && (i7 = bArr[i16 + 1]) != 34) {
                    if (i7 != 39 && i7 != i17) {
                    }
                }
                j4 = (j4 ^ i17) * Fnv.MAGIC_PRIME;
            }
            return j4;
        }
        while (true) {
            int i18 = bArr[i9];
            if (i18 == 92) {
                int i19 = i9 + 1;
                int i20 = bArr[i19];
                if (i20 == 117) {
                    hexDigit4 = IOUtils.hexDigit4(bArr, JSONReaderJSONB.check3(i9 + 2, i10));
                    i6 = i9 + 5;
                } else if (i20 == 120) {
                    int i21 = bArr[i9 + 2];
                    i6 = i9 + 3;
                    hexDigit4 = char2(i21, bArr[i6]);
                } else {
                    i5 = char1(i20);
                    i4 = i19 + 1;
                }
                int i22 = hexDigit4;
                i19 = i6;
                i5 = i22;
                i4 = i19 + 1;
            } else {
                if (i18 == z) {
                    return j4;
                }
                if (i18 >= 0) {
                    if (i18 >= 65 && i18 <= 90) {
                        i18 += 32;
                    }
                    i3 = i9 + 1;
                } else {
                    switch ((i18 & 255) >> 4) {
                        case 12:
                        case 13:
                            i18 = ((i18 & 31) << 6) | (bArr[i9 + 1] & '?');
                            i3 = i9 + 2;
                            break;
                        case 14:
                            i18 = ((i18 & 15) << 12) | ((bArr[i9 + 1] & '?') << 6) | (bArr[i9 + 2] & '?');
                            i3 = i9 + 3;
                            break;
                        default:
                            throw new JSONException("malformed input around byte " + i9);
                    }
                }
                int i23 = i18;
                i4 = i3;
                i5 = i23;
            }
            if (i5 != i && i5 != 45 && i5 != i2) {
                j4 = (j4 ^ i5) * Fnv.MAGIC_PRIME;
            }
            i9 = i4;
            i = 95;
            i2 = 32;
        }
    }

    final String getLatin1String(int i, int i2) {
        if (JDKUtils.ANDROID_SDK_INT >= 34) {
            return new String(this.bytes, i, i2, StandardCharsets.ISO_8859_1);
        }
        char[] cArr = this.charBuf;
        if (cArr == null) {
            if (this.cacheItem == null) {
                this.cacheItem = JSONFactory.CACHE_ITEMS[System.identityHashCode(Thread.currentThread()) & (JSONFactory.CACHE_ITEMS.length - 1)];
            }
            cArr = JSONFactory.CHARS_UPDATER.getAndSet(this.cacheItem, null);
            this.charBuf = cArr;
        }
        if (cArr == null || cArr.length < i2) {
            cArr = new char[i2];
            this.charBuf = cArr;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            cArr[i3] = (char) (this.bytes[i + i3] & UByte.MAX_VALUE);
        }
        return new String(cArr, 0, i2);
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public String getFieldName() {
        int char2_utf8;
        byte[] bArr = this.bytes;
        int i = this.nameBegin;
        int i2 = this.nameEnd - i;
        if (!this.nameEscape) {
            if (this.nameAscii) {
                if (JDKUtils.STRING_CREATOR_JDK8 != null) {
                    return JDKUtils.asciiStringJDK8(bArr, i, i2);
                }
                if (JDKUtils.STRING_CREATOR_JDK11 != null) {
                    return JDKUtils.STRING_CREATOR_JDK11.apply(Arrays.copyOfRange(bArr, i, this.nameEnd), JDKUtils.LATIN1);
                }
                if (JDKUtils.ANDROID) {
                    return getLatin1String(i, i2);
                }
            }
            return new String(bArr, i, i2, this.nameAscii ? StandardCharsets.ISO_8859_1 : StandardCharsets.UTF_8);
        }
        char[] cArr = new char[this.nameLength];
        int i3 = this.end;
        int i4 = 0;
        while (i < this.nameEnd) {
            int i5 = bArr[i];
            if (i5 < 0) {
                int i6 = i5 & 255;
                switch (i6 >> 4) {
                    case 12:
                    case 13:
                        char2_utf8 = char2_utf8(i6, bArr[i + 1], i);
                        i += 2;
                        break;
                    case 14:
                        char2_utf8 = char2_utf8(i6, bArr[i + 1], bArr[i + 2], i);
                        i += 3;
                        break;
                    default:
                        throw new JSONException("malformed input around byte " + i);
                }
                cArr[i4] = (char) char2_utf8;
            } else {
                if (i5 == 92) {
                    int i7 = i + 1;
                    char c = (char) bArr[i7];
                    if (c != '\"' && c != ':' && c != '@' && c != '\\') {
                        if (c == 'u') {
                            i5 = IOUtils.hexDigit4(bArr, JSONReaderJSONB.check3(i + 2, i3));
                            i += 5;
                        } else if (c == 'x') {
                            byte b = bArr[i + 2];
                            i += 3;
                            i5 = char2(b, bArr[i]);
                        } else if (c != '*' && c != '+') {
                            switch (c) {
                                default:
                                    switch (c) {
                                        case '<':
                                        case '=':
                                        case '>':
                                            break;
                                        default:
                                            i5 = char1(c);
                                            i = i7;
                                            break;
                                    }
                                case '-':
                                case '.':
                                case '/':
                                    i = i7;
                                    i5 = c;
                                    break;
                            }
                        }
                    }
                    i = i7;
                    i5 = c;
                } else if (i5 == 34) {
                    return new String(cArr);
                }
                cArr[i4] = (char) i5;
                i++;
            }
            i4++;
        }
        return new String(cArr);
    }

    /* JADX WARN: Removed duplicated region for block: B:126:0x0551  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0556  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x04c3  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:41:0x0091 -> B:37:0x007c). Please report as a decompilation issue!!! */
    @Override // com.alibaba.fastjson2.JSONReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String readFieldName() {
        /*
            Method dump skipped, instructions count: 1486
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF8.readFieldName():java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:69:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00b7 A[SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:67:0x00d3 -> B:70:0x00b7). Please report as a decompilation issue!!! */
    @Override // com.alibaba.fastjson2.JSONReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int readInt32Value() {
        /*
            Method dump skipped, instructions count: 276
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF8.readInt32Value():int");
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final Integer readInt32() {
        char c = this.ch;
        if ((c == '\"' || c == '\'' || c == 'n') && nextIfNullOrEmptyString()) {
            return null;
        }
        return Integer.valueOf(readInt32Value());
    }

    /* JADX WARN: Removed duplicated region for block: B:71:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00d5 A[SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:69:0x00ee -> B:72:0x00d5). Please report as a decompilation issue!!! */
    @Override // com.alibaba.fastjson2.JSONReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final long readInt64Value() {
        /*
            Method dump skipped, instructions count: 304
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF8.readInt64Value():long");
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final Long readInt64() {
        char c = this.ch;
        if ((c == '\"' || c == '\'' || c == 'n') && nextIfNullOrEmptyString()) {
            return null;
        }
        return Long.valueOf(readInt64Value());
    }

    /* JADX WARN: Code restructure failed: missing block: B:104:0x00de, code lost:
    
        if (r5 >= r4) goto L75;
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x00e0, code lost:
    
        r12 = r1[r5];
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x00e6, code lost:
    
        if (com.alibaba.fastjson2.util.IOUtils.isDigit(r12) == false) goto L75;
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x00ea, code lost:
    
        if (okio.internal.Buffer.OVERFLOW_ZONE > r9) goto L73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x00ec, code lost:
    
        r9 = ((r9 * 10) + 48) - r12;
        r5 = r5 + 1;
        r13 = r13 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x00f7, code lost:
    
        r9 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:198:0x0217, code lost:
    
        r14 = -r14;
     */
    /* JADX WARN: Removed duplicated region for block: B:185:0x01f7  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x020e  */
    @Override // com.alibaba.fastjson2.JSONReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final double readDoubleValue() {
        /*
            Method dump skipped, instructions count: 636
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF8.readDoubleValue():double");
    }

    /* JADX WARN: Code restructure failed: missing block: B:104:0x00e0, code lost:
    
        if (r5 >= r4) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x00e2, code lost:
    
        r7 = r1[r5];
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x00e8, code lost:
    
        if (com.alibaba.fastjson2.util.IOUtils.isDigit(r7) == false) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x00ec, code lost:
    
        if (okio.internal.Buffer.OVERFLOW_ZONE > r9) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x00ee, code lost:
    
        r9 = ((r9 * 10) + 48) - r7;
        r5 = r5 + 1;
        r13 = r13 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x00f9, code lost:
    
        r9 = r16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:198:0x0217, code lost:
    
        r14 = -r11;
     */
    /* JADX WARN: Removed duplicated region for block: B:184:0x01f5  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x020e  */
    @Override // com.alibaba.fastjson2.JSONReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final float readFloatValue() {
        /*
            Method dump skipped, instructions count: 635
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF8.readFloatValue():float");
    }

    /* JADX WARN: Code restructure failed: missing block: B:51:0x0145, code lost:
    
        throw new com.alibaba.fastjson2.JSONException("malformed input around byte " + r3);
     */
    @Override // com.alibaba.fastjson2.JSONReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void readString(com.alibaba.fastjson2.reader.ValueConsumer r21, boolean r22) {
        /*
            Method dump skipped, instructions count: 516
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF8.readString(com.alibaba.fastjson2.reader.ValueConsumer, boolean):void");
    }

    protected void readString0() {
        boolean z;
        String str;
        byte b = this.ch;
        int i = this.offset;
        int i2 = this.end;
        this.valueEscape = false;
        byte[] bArr = this.bytes;
        boolean z2 = true;
        int i3 = i;
        int i4 = 0;
        boolean z3 = true;
        while (true) {
            byte b2 = bArr[i3];
            int i5 = 120;
            int i6 = 117;
            if (b2 == 92) {
                this.valueEscape = z2;
                byte b3 = bArr[i3 + 1];
                i3 += b3 != 117 ? b3 == 120 ? 4 : 2 : 6;
                z = z2;
            } else {
                z = z2;
                if (b2 < 0) {
                    switch ((b2 & UByte.MAX_VALUE) >> 4) {
                        case 12:
                        case 13:
                            i3 += 2;
                            break;
                        case 14:
                            i3 += 3;
                            break;
                        default:
                            if ((b2 >> 3) != -2) {
                                throw new JSONException("malformed input around byte " + i3);
                            }
                            i3 += 4;
                            i4++;
                            break;
                    }
                    z3 = false;
                } else {
                    if (b2 == b) {
                        if (this.valueEscape) {
                            char[] cArr = new char[i4];
                            int i7 = 0;
                            while (true) {
                                byte b4 = bArr[i];
                                if (b4 != 92) {
                                    if (b4 != 34) {
                                        if (b4 >= 0) {
                                            cArr[i7] = (char) b4;
                                            i++;
                                        } else {
                                            switch ((b4 & UByte.MAX_VALUE) >> 4) {
                                                case 12:
                                                case 13:
                                                    int i8 = i + 1;
                                                    i += 2;
                                                    cArr[i7] = (char) (((b4 & 31) << 6) | (bArr[i8] & 63));
                                                    break;
                                                case 14:
                                                    int i9 = i + 2;
                                                    byte b5 = bArr[i + 1];
                                                    i += 3;
                                                    cArr[i7] = (char) (((b4 & 15) << 12) | ((b5 & 63) << 6) | (bArr[i9] & 63));
                                                    break;
                                                default:
                                                    if ((b4 >> 3) == -2) {
                                                        byte b6 = bArr[i + 1];
                                                        int i10 = i + 3;
                                                        byte b7 = bArr[i + 2];
                                                        i += 4;
                                                        byte b8 = bArr[i10];
                                                        int i11 = (((b4 << 18) ^ (b6 << 12)) ^ (b7 << 6)) ^ (b8 ^ ByteCompanionObject.MIN_VALUE);
                                                        if ((b6 & JSONB.Constants.BC_INT64_SHORT_MIN) == 128 && (b7 & JSONB.Constants.BC_INT64_SHORT_MIN) == 128 && (b8 & JSONB.Constants.BC_INT64_SHORT_MIN) == 128 && i11 >= 65536 && i11 < 1114112) {
                                                            int i12 = i7 + 1;
                                                            cArr[i7] = (char) ((i11 >>> 10) + Utf8.HIGH_SURROGATE_HEADER);
                                                            cArr[i12] = (char) ((i11 & 1023) + Utf8.LOG_SURROGATE_HEADER);
                                                            i7 = i12;
                                                            break;
                                                        }
                                                    } else {
                                                        throw new JSONException("malformed input around byte " + i);
                                                    }
                                                    break;
                                            }
                                        }
                                    } else {
                                        str = new String(cArr);
                                        i3 = i;
                                    }
                                } else {
                                    int i13 = i + 1;
                                    int i14 = bArr[i13];
                                    if (i14 != 34 && i14 != 92) {
                                        if (i14 == i6) {
                                            i14 = IOUtils.hexDigit4(bArr, JSONReaderJSONB.check3(i + 2, i2));
                                            i13 = i + 5;
                                        } else if (i14 == i5) {
                                            byte b9 = bArr[i + 2];
                                            int i15 = i + 3;
                                            i14 = char2(b9, bArr[i15]);
                                            i13 = i15;
                                        } else {
                                            i14 = char1(i14);
                                        }
                                    }
                                    cArr[i7] = (char) i14;
                                    i = i13 + 1;
                                }
                                i7++;
                                i5 = 120;
                                i6 = 117;
                            }
                            throw new JSONException("malformed input around byte " + i);
                        }
                        if (z3) {
                            int i16 = i3 - this.offset;
                            if (JDKUtils.ANDROID) {
                                str = getLatin1String(this.offset, i16);
                            } else {
                                str = new String(bArr, this.offset, i16, StandardCharsets.ISO_8859_1);
                            }
                        } else {
                            str = new String(bArr, this.offset, i3 - this.offset, StandardCharsets.UTF_8);
                        }
                        int i17 = i3 + 1;
                        byte b10 = bArr[i17];
                        while (b10 <= 32 && ((1 << b10) & 4294981376L) != 0) {
                            i17++;
                            b10 = bArr[i17];
                        }
                        this.comma = b10 == 44 ? z : false;
                        this.offset = i17 + 1;
                        if (b10 == 44) {
                            next();
                        } else {
                            this.ch = (char) b10;
                        }
                        this.stringValue = str;
                        return;
                    }
                    i3++;
                }
            }
            i4++;
            z2 = z;
        }
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean skipName() {
        this.offset = skipName(this, this.bytes, this.offset, this.end);
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x004c A[LOOP:0: B:16:0x0035->B:21:0x004c, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0024 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x006d A[LOOP:1: B:30:0x0061->B:34:0x006d, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x005b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0079  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x0049 -> B:14:0x0024). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:29:0x006a -> B:25:0x005b). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int skipName(com.alibaba.fastjson2.JSONReaderUTF8 r12, byte[] r13, int r14, int r15) {
        /*
            char r0 = r12.ch
            boolean r1 = r12.checkNameBegin(r0)
            if (r1 == 0) goto Lb
            int r12 = r12.offset
            return r12
        Lb:
            int r1 = com.alibaba.fastjson2.util.IOUtils.indexOfQuote(r13, r0, r14, r15)
            r2 = -1
            if (r1 == r2) goto L7e
            int r14 = indexOfSlash(r12, r13, r14, r15)
            r3 = 26
            if (r14 == r2) goto L2b
            if (r14 <= r1) goto L1d
            goto L2b
        L1d:
            int r14 = skipStringEscaped(r12, r13, r14, r0)
            if (r14 != r15) goto L26
        L23:
            r0 = r14
        L24:
            r14 = r3
            goto L35
        L26:
            int r0 = r14 + 1
            r14 = r13[r14]
            goto L35
        L2b:
            int r14 = r1 + 1
            if (r14 != r15) goto L30
            goto L23
        L30:
            int r1 = r1 + 2
            r14 = r13[r14]
            r0 = r1
        L35:
            r1 = 0
            r4 = 4294981376(0x100003700, double:2.1220027474E-314)
            r6 = 1
            r8 = 32
            if (r14 > r8) goto L54
            long r9 = r6 << r14
            long r9 = r9 & r4
            int r9 = (r9 > r1 ? 1 : (r9 == r1 ? 0 : -1))
            if (r9 == 0) goto L54
            if (r0 != r15) goto L4c
            goto L24
        L4c:
            int r14 = r0 + 1
            r0 = r13[r0]
            r11 = r0
            r0 = r14
            r14 = r11
            goto L35
        L54:
            r9 = 58
            if (r14 != r9) goto L79
            if (r0 != r15) goto L5d
            r14 = r0
        L5b:
            r0 = r3
            goto L61
        L5d:
            int r14 = r0 + 1
            r0 = r13[r0]
        L61:
            if (r0 > r8) goto L75
            long r9 = r6 << r0
            long r9 = r9 & r4
            int r9 = (r9 > r1 ? 1 : (r9 == r1 ? 0 : -1))
            if (r9 == 0) goto L75
            if (r14 != r15) goto L6d
            goto L5b
        L6d:
            int r0 = r14 + 1
            r14 = r13[r14]
            r11 = r0
            r0 = r14
            r14 = r11
            goto L61
        L75:
            char r13 = (char) r0
            r12.ch = r13
            return r14
        L79:
            com.alibaba.fastjson2.JSONException r12 = syntaxError(r14)
            throw r12
        L7e:
            java.lang.String r13 = "invalid escape character EOI"
            com.alibaba.fastjson2.JSONException r12 = r12.error(r13)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF8.skipName(com.alibaba.fastjson2.JSONReaderUTF8, byte[], int, int):int");
    }

    /* JADX WARN: Code restructure failed: missing block: B:119:0x00f8, code lost:
    
        r2 = 26;
     */
    /* JADX WARN: Removed duplicated region for block: B:117:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00ed A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x010f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x011c  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x014a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int skipNumber(com.alibaba.fastjson2.JSONReaderUTF8 r20, byte[] r21, int r22, int r23) {
        /*
            Method dump skipped, instructions count: 364
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF8.skipNumber(com.alibaba.fastjson2.JSONReaderUTF8, byte[], int, int):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x004b A[LOOP:0: B:11:0x0034->B:16:0x004b, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0023 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x008b  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:14:0x0048 -> B:9:0x0023). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int skipString(com.alibaba.fastjson2.JSONReaderUTF8 r19, byte[] r20, int r21, int r22) {
        /*
            r0 = r19
            r1 = r20
            r2 = r22
            char r3 = r0.ch
            r4 = r21
            int r5 = com.alibaba.fastjson2.util.IOUtils.indexOfQuote(r1, r3, r4, r2)
            r6 = -1
            if (r5 == r6) goto L9e
            int r4 = indexOfSlash(r19, r20, r21, r22)
            r7 = 26
            if (r4 == r6) goto L2a
            if (r4 <= r5) goto L1c
            goto L2a
        L1c:
            int r3 = skipStringEscaped(r0, r1, r4, r3)
            if (r3 != r2) goto L25
        L22:
            r4 = r3
        L23:
            r3 = r7
            goto L34
        L25:
            int r4 = r3 + 1
            r3 = r1[r3]
            goto L34
        L2a:
            int r3 = r5 + 1
            if (r3 != r2) goto L2f
            goto L22
        L2f:
            int r5 = r5 + 2
            r3 = r1[r3]
            r4 = r5
        L34:
            r5 = 0
            r8 = 4294981376(0x100003700, double:2.1220027474E-314)
            r10 = 1
            r12 = 32
            if (r3 > r12) goto L55
            long r13 = r10 << r3
            long r13 = r13 & r8
            int r13 = (r13 > r5 ? 1 : (r13 == r5 ? 0 : -1))
            if (r13 == 0) goto L55
            if (r4 != r2) goto L4b
            goto L23
        L4b:
            int r3 = r4 + 1
            r4 = r1[r4]
            r18 = r4
            r4 = r3
            r3 = r18
            goto L34
        L55:
            r13 = 44
            r14 = 93
            r15 = 125(0x7d, float:1.75E-43)
            if (r3 != r13) goto L8b
            if (r4 != r2) goto L62
            r3 = r4
            r4 = r7
            goto L66
        L62:
            int r3 = r4 + 1
            r4 = r1[r4]
        L66:
            r18 = r4
            r4 = r3
            r3 = r18
        L6b:
            if (r3 > r12) goto L7e
            long r16 = r10 << r3
            long r16 = r16 & r8
            int r13 = (r16 > r5 ? 1 : (r16 == r5 ? 0 : -1))
            if (r13 == 0) goto L7e
            if (r4 != r2) goto L79
            r3 = r7
            goto L6b
        L79:
            int r3 = r4 + 1
            r4 = r1[r4]
            goto L66
        L7e:
            if (r3 == r15) goto L86
            if (r3 == r14) goto L86
            if (r3 == r7) goto L86
            r1 = 1
            goto L98
        L86:
            com.alibaba.fastjson2.JSONException r0 = r0.error(r4, r3)
            throw r0
        L8b:
            if (r3 == r15) goto L97
            if (r3 == r14) goto L97
            if (r3 != r7) goto L92
            goto L97
        L92:
            com.alibaba.fastjson2.JSONException r0 = r0.error(r4, r3)
            throw r0
        L97:
            r1 = 0
        L98:
            r0.comma = r1
            char r1 = (char) r3
            r0.ch = r1
            return r4
        L9e:
            java.lang.String r1 = "invalid escape character EOI"
            com.alibaba.fastjson2.JSONException r0 = r0.error(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF8.skipString(com.alibaba.fastjson2.JSONReaderUTF8, byte[], int, int):int");
    }

    private static int skipStringEscaped(JSONReaderUTF8 jSONReaderUTF8, byte[] bArr, int i, int i2) {
        int i3 = i + 1;
        int i4 = bArr[i];
        while (true) {
            if (i4 == 92) {
                int i5 = i3 + 1;
                byte b = bArr[i3];
                if (b == 117) {
                    i5 = i3 + 5;
                } else if (b == 120) {
                    i5 = i3 + 3;
                } else if (b != 92 && b != 34) {
                    jSONReaderUTF8.char1(b);
                }
                i3 = i5 + 1;
                i4 = bArr[i5];
            } else {
                if (i4 == i2) {
                    return i3;
                }
                int i6 = i3 + 1;
                int i7 = bArr[i3];
                i3 = i6;
                i4 = i7;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0029  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0033 A[LOOP:1: B:20:0x001c->B:25:0x0033, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0016 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0070  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:12:0x0030 -> B:7:0x0016). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int skipObject(com.alibaba.fastjson2.JSONReaderUTF8 r18, byte[] r19, int r20, int r21) {
        /*
            r0 = r18
            r1 = r19
            r2 = r21
            int r3 = next(r18, r19, r20, r21)
            r5 = 0
        Lb:
            char r6 = r0.ch
            r7 = 125(0x7d, float:1.75E-43)
            if (r6 != r7) goto L83
            r5 = 26
            if (r3 != r2) goto L18
            r6 = r3
        L16:
            r3 = r5
            goto L1c
        L18:
            int r6 = r3 + 1
            r3 = r1[r3]
        L1c:
            r8 = 0
            r10 = 4294981376(0x100003700, double:2.1220027474E-314)
            r12 = 1
            r14 = 32
            if (r3 > r14) goto L3d
            long r15 = r12 << r3
            long r15 = r15 & r10
            int r15 = (r15 > r8 ? 1 : (r15 == r8 ? 0 : -1))
            if (r15 == 0) goto L3d
            if (r6 != r2) goto L33
            goto L16
        L33:
            int r3 = r6 + 1
            r6 = r1[r6]
            r17 = r6
            r6 = r3
            r3 = r17
            goto L1c
        L3d:
            r15 = 44
            r4 = 93
            if (r3 != r15) goto L70
            if (r6 != r2) goto L48
            r3 = r6
            r6 = r5
            goto L4c
        L48:
            int r3 = r6 + 1
            r6 = r1[r6]
        L4c:
            r17 = r6
            r6 = r3
            r3 = r17
        L51:
            if (r3 > r14) goto L63
            long r15 = r12 << r3
            long r15 = r15 & r10
            int r15 = (r15 > r8 ? 1 : (r15 == r8 ? 0 : -1))
            if (r15 == 0) goto L63
            if (r6 != r2) goto L5e
            r3 = r5
            goto L51
        L5e:
            int r3 = r6 + 1
            r6 = r1[r6]
            goto L4c
        L63:
            if (r3 == r7) goto L6b
            if (r3 == r4) goto L6b
            if (r3 == r5) goto L6b
            r4 = 1
            goto L7d
        L6b:
            com.alibaba.fastjson2.JSONException r0 = r0.error(r6, r3)
            throw r0
        L70:
            if (r3 == r7) goto L7c
            if (r3 == r4) goto L7c
            if (r3 != r5) goto L77
            goto L7c
        L77:
            com.alibaba.fastjson2.JSONException r0 = r0.error(r6, r3)
            throw r0
        L7c:
            r4 = 0
        L7d:
            r0.comma = r4
            char r1 = (char) r3
            r0.ch = r1
            return r6
        L83:
            if (r5 == 0) goto L8f
            boolean r4 = r0.comma
            if (r4 == 0) goto L8a
            goto L8f
        L8a:
            com.alibaba.fastjson2.JSONException r0 = r0.valueError()
            throw r0
        L8f:
            int r3 = skipName(r0, r1, r3, r2)
            int r3 = skipValue(r0, r1, r3, r2)
            int r5 = r5 + 1
            goto Lb
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF8.skipObject(com.alibaba.fastjson2.JSONReaderUTF8, byte[], int, int):int");
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x0005, code lost:
    
        if (r1 != r10) goto L23;
     */
    /* JADX WARN: Removed duplicated region for block: B:12:0x003c A[LOOP:0: B:6:0x000b->B:12:0x003c, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0005 A[EDGE_INSN: B:13:0x0005->B:4:0x0005 BREAK  A[LOOP:0: B:6:0x000b->B:12:0x003c], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0029  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x000d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int next(com.alibaba.fastjson2.JSONReaderUTF8 r7, byte[] r8, int r9, int r10) {
        /*
            r0 = 26
            if (r9 != r10) goto L7
            r1 = r9
        L5:
            r9 = r0
            goto Lb
        L7:
            int r1 = r9 + 1
            r9 = r8[r9]
        Lb:
            if (r9 == 0) goto L39
            r2 = 32
            if (r9 > r2) goto L21
            r2 = 1
            long r2 = r2 << r9
            r4 = 4294981376(0x100003700, double:2.1220027474E-314)
            long r2 = r2 & r4
            r4 = 0
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 == 0) goto L21
            goto L39
        L21:
            if (r9 >= 0) goto L29
            r7.char_utf8(r9, r1)
            int r7 = r7.offset
            return r7
        L29:
            char r8 = (char) r9
            r7.ch = r8
            r8 = 47
            if (r9 != r8) goto L38
            r7.offset = r1
            r7.skipComment()
            int r7 = r7.offset
            return r7
        L38:
            return r1
        L39:
            if (r1 != r10) goto L3c
            goto L5
        L3c:
            int r9 = r1 + 1
            r1 = r8[r1]
            r6 = r1
            r1 = r9
            r9 = r6
            goto Lb
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF8.next(com.alibaba.fastjson2.JSONReaderUTF8, byte[], int, int):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x002a  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0034 A[LOOP:1: B:20:0x001d->B:25:0x0034, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0017 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0042  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:12:0x0031 -> B:7:0x0017). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int skipArray(com.alibaba.fastjson2.JSONReaderUTF8 r18, byte[] r19, int r20, int r21) {
        /*
            r0 = r18
            r1 = r19
            r2 = r21
            int r3 = next(r18, r19, r20, r21)
            r4 = 0
            r5 = r4
        Lc:
            char r6 = r0.ch
            r7 = 93
            if (r6 != r7) goto L84
            r5 = 26
            if (r3 != r2) goto L19
            r6 = r3
        L17:
            r3 = r5
            goto L1d
        L19:
            int r6 = r3 + 1
            r3 = r1[r3]
        L1d:
            r8 = 0
            r10 = 4294981376(0x100003700, double:2.1220027474E-314)
            r12 = 1
            r14 = 32
            if (r3 > r14) goto L3e
            long r15 = r12 << r3
            long r15 = r15 & r10
            int r15 = (r15 > r8 ? 1 : (r15 == r8 ? 0 : -1))
            if (r15 == 0) goto L3e
            if (r6 != r2) goto L34
            goto L17
        L34:
            int r3 = r6 + 1
            r6 = r1[r6]
            r17 = r6
            r6 = r3
            r3 = r17
            goto L1d
        L3e:
            r15 = 44
            if (r3 != r15) goto L60
            if (r6 != r2) goto L46
            r4 = r5
            goto L4b
        L46:
            int r3 = r6 + 1
            r4 = r1[r6]
            goto L5d
        L4b:
            r3 = r4
        L4c:
            if (r3 > r14) goto L5f
            long r15 = r12 << r3
            long r15 = r15 & r10
            int r4 = (r15 > r8 ? 1 : (r15 == r8 ? 0 : -1))
            if (r4 == 0) goto L5f
            if (r6 != r2) goto L59
            r3 = r5
            goto L4c
        L59:
            int r3 = r6 + 1
            r4 = r1[r6]
        L5d:
            r6 = r3
            goto L4b
        L5f:
            r4 = 1
        L60:
            r1 = 125(0x7d, float:1.75E-43)
            if (r4 != 0) goto L70
            if (r3 == r1) goto L70
            if (r3 == r7) goto L70
            if (r3 != r5) goto L6b
            goto L70
        L6b:
            com.alibaba.fastjson2.JSONException r0 = r0.error(r6, r3)
            throw r0
        L70:
            if (r4 == 0) goto L7e
            if (r3 == r1) goto L79
            if (r3 == r7) goto L79
            if (r3 == r5) goto L79
            goto L7e
        L79:
            com.alibaba.fastjson2.JSONException r0 = r0.error(r6, r3)
            throw r0
        L7e:
            r0.comma = r4
            char r1 = (char) r3
            r0.ch = r1
            return r6
        L84:
            if (r5 == 0) goto L90
            boolean r6 = r0.comma
            if (r6 == 0) goto L8b
            goto L90
        L8b:
            com.alibaba.fastjson2.JSONException r0 = r0.valueError()
            throw r0
        L90:
            int r3 = skipValue(r0, r1, r3, r2)
            int r5 = r5 + 1
            goto Lc
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF8.skipArray(com.alibaba.fastjson2.JSONReaderUTF8, byte[], int, int):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0026  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0030 A[LOOP:0: B:10:0x0019->B:15:0x0030, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0013 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x006f  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:13:0x002d -> B:8:0x0013). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int skipFalse(com.alibaba.fastjson2.JSONReaderUTF8 r18, byte[] r19, int r20, int r21) {
        /*
            r0 = r18
            r1 = r21
            int r2 = r20 + 4
            if (r2 > r1) goto L82
            boolean r3 = com.alibaba.fastjson2.util.IOUtils.notALSE(r19, r20)
            if (r3 != 0) goto L82
            r3 = 26
            if (r2 != r1) goto L15
            r4 = r2
        L13:
            r2 = r3
            goto L19
        L15:
            int r4 = r20 + 5
            r2 = r19[r2]
        L19:
            r5 = 0
            r7 = 4294981376(0x100003700, double:2.1220027474E-314)
            r9 = 1
            r11 = 32
            if (r2 > r11) goto L3a
            long r12 = r9 << r2
            long r12 = r12 & r7
            int r12 = (r12 > r5 ? 1 : (r12 == r5 ? 0 : -1))
            if (r12 == 0) goto L3a
            if (r4 != r1) goto L30
            goto L13
        L30:
            int r2 = r4 + 1
            r4 = r19[r4]
            r17 = r4
            r4 = r2
            r2 = r17
            goto L19
        L3a:
            r12 = 44
            r13 = 93
            r14 = 125(0x7d, float:1.75E-43)
            if (r2 != r12) goto L6f
            if (r4 != r1) goto L47
            r2 = r4
            r4 = r3
            goto L4b
        L47:
            int r2 = r4 + 1
            r4 = r19[r4]
        L4b:
            r17 = r4
            r4 = r2
            r2 = r17
        L50:
            if (r2 > r11) goto L62
            long r15 = r9 << r2
            long r15 = r15 & r7
            int r12 = (r15 > r5 ? 1 : (r15 == r5 ? 0 : -1))
            if (r12 == 0) goto L62
            if (r4 != r1) goto L5d
            r2 = r3
            goto L50
        L5d:
            int r2 = r4 + 1
            r4 = r19[r4]
            goto L4b
        L62:
            if (r2 == r14) goto L6a
            if (r2 == r13) goto L6a
            if (r2 == r3) goto L6a
            r1 = 1
            goto L7c
        L6a:
            com.alibaba.fastjson2.JSONException r0 = r0.error(r4, r2)
            throw r0
        L6f:
            if (r2 == r14) goto L7b
            if (r2 == r13) goto L7b
            if (r2 != r3) goto L76
            goto L7b
        L76:
            com.alibaba.fastjson2.JSONException r0 = r0.error(r4, r2)
            throw r0
        L7b:
            r1 = 0
        L7c:
            r0.comma = r1
            char r1 = (char) r2
            r0.ch = r1
            return r4
        L82:
            com.alibaba.fastjson2.JSONException r0 = r0.error()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF8.skipFalse(com.alibaba.fastjson2.JSONReaderUTF8, byte[], int, int):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x002a  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0034 A[LOOP:0: B:10:0x001d->B:15:0x0034, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0017 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0074  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:13:0x0031 -> B:8:0x0017). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int skipTrue(com.alibaba.fastjson2.JSONReaderUTF8 r19, byte[] r20, int r21, int r22) {
        /*
            r0 = r19
            r1 = r20
            r2 = r22
            int r3 = r21 + 3
            if (r3 > r2) goto L87
            int r4 = r21 + (-1)
            boolean r4 = com.alibaba.fastjson2.util.IOUtils.notTRUE(r1, r4)
            if (r4 != 0) goto L87
            r4 = 26
            if (r3 != r2) goto L19
            r5 = r3
        L17:
            r3 = r4
            goto L1d
        L19:
            int r5 = r21 + 4
            r3 = r1[r3]
        L1d:
            r6 = 0
            r8 = 4294981376(0x100003700, double:2.1220027474E-314)
            r10 = 1
            r12 = 32
            if (r3 > r12) goto L3e
            long r13 = r10 << r3
            long r13 = r13 & r8
            int r13 = (r13 > r6 ? 1 : (r13 == r6 ? 0 : -1))
            if (r13 == 0) goto L3e
            if (r5 != r2) goto L34
            goto L17
        L34:
            int r3 = r5 + 1
            r5 = r1[r5]
            r18 = r5
            r5 = r3
            r3 = r18
            goto L1d
        L3e:
            r13 = 44
            r14 = 93
            r15 = 125(0x7d, float:1.75E-43)
            if (r3 != r13) goto L74
            if (r5 != r2) goto L4b
            r3 = r5
            r5 = r4
            goto L4f
        L4b:
            int r3 = r5 + 1
            r5 = r1[r5]
        L4f:
            r18 = r5
            r5 = r3
            r3 = r18
        L54:
            if (r3 > r12) goto L67
            long r16 = r10 << r3
            long r16 = r16 & r8
            int r13 = (r16 > r6 ? 1 : (r16 == r6 ? 0 : -1))
            if (r13 == 0) goto L67
            if (r5 != r2) goto L62
            r3 = r4
            goto L54
        L62:
            int r3 = r5 + 1
            r5 = r1[r5]
            goto L4f
        L67:
            if (r3 == r15) goto L6f
            if (r3 == r14) goto L6f
            if (r3 == r4) goto L6f
            r1 = 1
            goto L81
        L6f:
            com.alibaba.fastjson2.JSONException r0 = r0.error(r5, r3)
            throw r0
        L74:
            if (r3 == r15) goto L80
            if (r3 == r14) goto L80
            if (r3 != r4) goto L7b
            goto L80
        L7b:
            com.alibaba.fastjson2.JSONException r0 = r0.error(r5, r3)
            throw r0
        L80:
            r1 = 0
        L81:
            r0.comma = r1
            char r1 = (char) r3
            r0.ch = r1
            return r5
        L87:
            com.alibaba.fastjson2.JSONException r0 = r0.error()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF8.skipTrue(com.alibaba.fastjson2.JSONReaderUTF8, byte[], int, int):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x002a  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0034 A[LOOP:0: B:10:0x001d->B:15:0x0034, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0017 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0074  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:13:0x0031 -> B:8:0x0017). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int skipNull(com.alibaba.fastjson2.JSONReaderUTF8 r19, byte[] r20, int r21, int r22) {
        /*
            r0 = r19
            r1 = r20
            r2 = r22
            int r3 = r21 + 3
            if (r3 > r2) goto L87
            int r4 = r21 + (-1)
            boolean r4 = com.alibaba.fastjson2.util.IOUtils.notNULL(r1, r4)
            if (r4 != 0) goto L87
            r4 = 26
            if (r3 != r2) goto L19
            r5 = r3
        L17:
            r3 = r4
            goto L1d
        L19:
            int r5 = r21 + 4
            r3 = r1[r3]
        L1d:
            r6 = 0
            r8 = 4294981376(0x100003700, double:2.1220027474E-314)
            r10 = 1
            r12 = 32
            if (r3 > r12) goto L3e
            long r13 = r10 << r3
            long r13 = r13 & r8
            int r13 = (r13 > r6 ? 1 : (r13 == r6 ? 0 : -1))
            if (r13 == 0) goto L3e
            if (r5 != r2) goto L34
            goto L17
        L34:
            int r3 = r5 + 1
            r5 = r1[r5]
            r18 = r5
            r5 = r3
            r3 = r18
            goto L1d
        L3e:
            r13 = 44
            r14 = 93
            r15 = 125(0x7d, float:1.75E-43)
            if (r3 != r13) goto L74
            if (r5 != r2) goto L4b
            r3 = r5
            r5 = r4
            goto L4f
        L4b:
            int r3 = r5 + 1
            r5 = r1[r5]
        L4f:
            r18 = r5
            r5 = r3
            r3 = r18
        L54:
            if (r3 > r12) goto L67
            long r16 = r10 << r3
            long r16 = r16 & r8
            int r13 = (r16 > r6 ? 1 : (r16 == r6 ? 0 : -1))
            if (r13 == 0) goto L67
            if (r5 != r2) goto L62
            r3 = r4
            goto L54
        L62:
            int r3 = r5 + 1
            r5 = r1[r5]
            goto L4f
        L67:
            if (r3 == r15) goto L6f
            if (r3 == r14) goto L6f
            if (r3 == r4) goto L6f
            r1 = 1
            goto L81
        L6f:
            com.alibaba.fastjson2.JSONException r0 = r0.error(r5, r3)
            throw r0
        L74:
            if (r3 == r15) goto L80
            if (r3 == r14) goto L80
            if (r3 != r4) goto L7b
            goto L80
        L7b:
            com.alibaba.fastjson2.JSONException r0 = r0.error(r5, r3)
            throw r0
        L80:
            r1 = 0
        L81:
            r0.comma = r1
            char r1 = (char) r3
            r0.ch = r1
            return r5
        L87:
            com.alibaba.fastjson2.JSONException r0 = r0.error()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF8.skipNull(com.alibaba.fastjson2.JSONReaderUTF8, byte[], int, int):int");
    }

    private static int skipSet(JSONReaderUTF8 jSONReaderUTF8, byte[] bArr, int i, int i2) {
        if (nextIfSet(jSONReaderUTF8, bArr, i, i2)) {
            return skipArray(jSONReaderUTF8, bArr, jSONReaderUTF8.offset, i2);
        }
        throw jSONReaderUTF8.error();
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0021  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0033 A[LOOP:0: B:12:0x001d->B:17:0x0033, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0017 A[SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x0030 -> B:10:0x0017). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static boolean nextIfSet(com.alibaba.fastjson2.JSONReaderUTF8 r7, byte[] r8, int r9, int r10) {
        /*
            int r0 = r9 + 1
            if (r0 >= r10) goto L42
            r1 = r8[r9]
            r2 = 101(0x65, float:1.42E-43)
            if (r1 != r2) goto L42
            r0 = r8[r0]
            r1 = 116(0x74, float:1.63E-43)
            if (r0 != r1) goto L42
            int r0 = r9 + 2
            r1 = 26
            if (r0 != r10) goto L19
            r9 = r0
        L17:
            r0 = r1
            goto L1d
        L19:
            int r9 = r9 + 3
            r0 = r8[r0]
        L1d:
            r2 = 32
            if (r0 > r2) goto L3b
            r2 = 1
            long r2 = r2 << r0
            r4 = 4294981376(0x100003700, double:2.1220027474E-314)
            long r2 = r2 & r4
            r4 = 0
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 == 0) goto L3b
            if (r9 != r10) goto L33
            goto L17
        L33:
            int r0 = r9 + 1
            r9 = r8[r9]
            r6 = r0
            r0 = r9
            r9 = r6
            goto L1d
        L3b:
            r7.offset = r9
            char r8 = (char) r0
            r7.ch = r8
            r7 = 1
            return r7
        L42:
            r7 = 0
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF8.nextIfSet(com.alibaba.fastjson2.JSONReaderUTF8, byte[], int, int):boolean");
    }

    private static int skipValue(JSONReaderUTF8 jSONReaderUTF8, byte[] bArr, int i, int i2) {
        char c = jSONReaderUTF8.ch;
        if (c == '\"' || c == '\'') {
            return skipString(jSONReaderUTF8, bArr, i, i2);
        }
        if (c == 'S') {
            return skipSet(jSONReaderUTF8, bArr, i, i2);
        }
        if (c == '[') {
            return skipArray(jSONReaderUTF8, bArr, i, i2);
        }
        if (c == 'f') {
            return skipFalse(jSONReaderUTF8, bArr, i, i2);
        }
        if (c == 'n') {
            return skipNull(jSONReaderUTF8, bArr, i, i2);
        }
        if (c == 't') {
            return skipTrue(jSONReaderUTF8, bArr, i, i2);
        }
        if (c == '{') {
            return skipObject(jSONReaderUTF8, bArr, i, i2);
        }
        return skipNumber(jSONReaderUTF8, bArr, i, i2);
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final void skipValue() {
        this.offset = skipValue(this, this.bytes, this.offset, this.end);
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x00f4, code lost:
    
        throw new com.alibaba.fastjson2.JSONException("malformed input around byte " + r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00bc, code lost:
    
        throw new com.alibaba.fastjson2.JSONException("malformed input around byte " + r7);
     */
    @Override // com.alibaba.fastjson2.JSONReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String getString() {
        /*
            Method dump skipped, instructions count: 340
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF8.getString():java.lang.String");
    }

    /* JADX WARN: Code restructure failed: missing block: B:45:0x0031, code lost:
    
        if (r1 == 10) goto L17;
     */
    @Override // com.alibaba.fastjson2.JSONReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void skipComment() {
        /*
            r12 = this;
            int r0 = r12.offset
            int r1 = r0 + 1
            int r2 = r12.end
            if (r1 >= r2) goto L82
            byte[] r2 = r12.bytes
            r3 = r2[r0]
            r4 = 42
            r5 = 0
            r6 = 47
            r7 = 1
            if (r3 != r4) goto L16
            r3 = r7
            goto L19
        L16:
            if (r3 != r6) goto L76
            r3 = r5
        L19:
            int r0 = r0 + 2
            r1 = r2[r1]
        L1d:
            if (r3 == 0) goto L2f
            if (r1 != r4) goto L2d
            int r1 = r12.end
            if (r0 > r1) goto L2d
            r1 = r2[r0]
            if (r1 != r6) goto L2d
            int r0 = r0 + 1
        L2b:
            r1 = r7
            goto L34
        L2d:
            r1 = r5
            goto L34
        L2f:
            r8 = 10
            if (r1 != r8) goto L2d
            goto L2b
        L34:
            r8 = 26
            if (r1 == 0) goto L5f
            int r1 = r12.end
            if (r0 < r1) goto L3d
            goto L63
        L3d:
            r1 = r2[r0]
        L3f:
            r3 = 32
            if (r1 > r3) goto L5c
            r3 = 1
            long r3 = r3 << r1
            r9 = 4294981376(0x100003700, double:2.1220027474E-314)
            long r3 = r3 & r9
            r9 = 0
            int r3 = (r3 > r9 ? 1 : (r3 == r9 ? 0 : -1))
            if (r3 == 0) goto L5c
            int r0 = r0 + 1
            int r1 = r12.end
            if (r0 < r1) goto L59
            goto L5d
        L59:
            r1 = r2[r0]
            goto L3f
        L5c:
            r8 = r1
        L5d:
            int r0 = r0 + r7
            goto L63
        L5f:
            int r1 = r12.end
            if (r0 < r1) goto L6e
        L63:
            char r1 = (char) r8
            r12.ch = r1
            r12.offset = r0
            if (r8 != r6) goto L6d
            r12.skipComment()
        L6d:
            return
        L6e:
            int r1 = r0 + 1
            r0 = r2[r0]
            r11 = r1
            r1 = r0
            r0 = r11
            goto L1d
        L76:
            com.alibaba.fastjson2.JSONException r0 = new com.alibaba.fastjson2.JSONException
            java.lang.String r1 = "parse comment error"
            java.lang.String r1 = r12.info(r1)
            r0.<init>(r1)
            throw r0
        L82:
            com.alibaba.fastjson2.JSONException r0 = new com.alibaba.fastjson2.JSONException
            java.lang.String r1 = r12.info()
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF8.skipComment():void");
    }

    /*  JADX ERROR: Type inference failed
        jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:77)
        */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:78:0x0225 -> B:73:0x020c). Please report as a decompilation issue!!! */
    @Override // com.alibaba.fastjson2.JSONReader
    public java.lang.String readString() {
        /*
            Method dump skipped, instructions count: 702
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF8.readString():java.lang.String");
    }

    /* JADX WARN: Code restructure failed: missing block: B:231:0x00d4, code lost:
    
        if (r26.mag3 < (-214748364)) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0118, code lost:
    
        if (r26.mag3 < (-214748364)) goto L60;
     */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0336  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x033d  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0361  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0367  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0338  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x02d3  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x0318  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x0308  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x029f  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x0178  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00e2 A[LOOP:0: B:12:0x00ad->B:20:0x00e2, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:210:0x0197  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x01c1  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x01aa A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00db A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:222:0x017f  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x0165  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x0059 A[LOOP:5: B:249:0x004c->B:253:0x0059, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:254:0x0045 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x01cb  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x02a4  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0322  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x032d  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0315 A[SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:229:0x0056 -> B:225:0x0045). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:89:0x032a -> B:92:0x0315). Please report as a decompilation issue!!! */
    @Override // com.alibaba.fastjson2.JSONReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void readNumber0() {
        /*
            Method dump skipped, instructions count: 883
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF8.readNumber0():void");
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final void readNumber(ValueConsumer valueConsumer, boolean z) {
        char c;
        boolean z2;
        int i;
        this.wasNull = false;
        this.boolValue = false;
        this.mag0 = 0;
        this.mag1 = 0;
        this.mag2 = 0;
        this.mag3 = 0;
        this.negative = false;
        this.exponent = (short) 0;
        this.scale = (short) 0;
        int i2 = this.end;
        byte[] bArr = this.bytes;
        if (this.ch == '\"' || this.ch == '\'') {
            c = this.ch;
            int i3 = this.offset;
            this.offset = i3 + 1;
            this.ch = (char) bArr[i3];
        } else {
            c = 0;
        }
        int i4 = this.offset;
        if (this.ch == '-') {
            this.negative = true;
            int i5 = this.offset;
            this.offset = i5 + 1;
            this.ch = (char) bArr[i5];
        }
        this.valueType = (byte) 1;
        boolean z3 = false;
        boolean z4 = false;
        while (this.ch >= '0' && this.ch <= '9') {
            if (!z3) {
                int i6 = (this.mag3 * 10) + (this.ch - '0');
                if (i6 < this.mag3) {
                    z3 = true;
                } else {
                    this.mag3 = i6;
                }
            }
            int i7 = this.offset;
            this.offset = i7 + 1;
            this.ch = (char) bArr[i7];
            z4 = true;
        }
        if (this.ch == '.') {
            this.valueType = (byte) 2;
            int i8 = this.offset;
            this.offset = i8 + 1;
            this.ch = (char) bArr[i8];
            while (this.ch >= '0' && this.ch <= '9') {
                if (!z3) {
                    int i9 = (this.mag3 * 10) + (this.ch - '0');
                    if (i9 < this.mag3) {
                        z3 = true;
                    } else {
                        this.mag3 = i9;
                    }
                }
                this.scale = (short) (this.scale + 1);
                int i10 = this.offset;
                this.offset = i10 + 1;
                this.ch = (char) bArr[i10];
                z4 = true;
            }
        }
        if (z3) {
            bigInt(bArr, this.negative ? i4 : i4 - 1, this.offset - 1);
        }
        if (this.ch == 'e' || this.ch == 'E') {
            int i11 = this.offset;
            this.offset = i11 + 1;
            this.ch = (char) bArr[i11];
            if (this.ch == '-') {
                int i12 = this.offset;
                this.offset = i12 + 1;
                this.ch = (char) bArr[i12];
                i = 0;
                z2 = true;
            } else {
                if (this.ch == '+') {
                    int i13 = this.offset;
                    this.offset = i13 + 1;
                    this.ch = (char) bArr[i13];
                }
                z2 = false;
                i = 0;
            }
            while (this.ch >= '0' && this.ch <= '9') {
                i = (i * 10) + (this.ch - '0');
                if (i > 2047) {
                    throw new JSONException("too large exp value : " + i);
                }
                int i14 = this.offset;
                this.offset = i14 + 1;
                this.ch = (char) bArr[i14];
                z4 = true;
            }
            if (z2) {
                i = -i;
            }
            this.exponent = (short) i;
            this.valueType = (byte) 2;
        }
        int i15 = this.offset - i4;
        if (this.offset == i4) {
            if (this.ch == 'n') {
                int i16 = this.offset;
                this.offset = i16 + 1;
                if (bArr[i16] == 117) {
                    int i17 = this.offset;
                    this.offset = i17 + 1;
                    if (bArr[i17] == 108) {
                        int i18 = this.offset;
                        this.offset = i18 + 1;
                        if (bArr[i18] == 108) {
                            this.wasNull = true;
                            this.valueType = (byte) 5;
                            int i19 = this.offset;
                            this.offset = i19 + 1;
                            this.ch = (char) bArr[i19];
                            z4 = true;
                        }
                    }
                }
            } else if (this.ch == 't') {
                int i20 = this.offset;
                this.offset = i20 + 1;
                if (bArr[i20] == 114) {
                    int i21 = this.offset;
                    this.offset = i21 + 1;
                    if (bArr[i21] == 117) {
                        int i22 = this.offset;
                        this.offset = i22 + 1;
                        if (bArr[i22] == 101) {
                            this.boolValue = true;
                            this.valueType = (byte) 4;
                            int i23 = this.offset;
                            this.offset = i23 + 1;
                            this.ch = (char) bArr[i23];
                            z4 = true;
                        }
                    }
                }
            } else if (this.ch == 'f' && this.offset + 3 < i2 && IOUtils.isALSE(bArr, this.offset)) {
                this.offset += 4;
                this.boolValue = false;
                this.valueType = (byte) 4;
                int i24 = this.offset;
                this.offset = i24 + 1;
                this.ch = (char) bArr[i24];
                z4 = true;
            } else if (this.ch == '{' && c == 0) {
                this.complex = readObject();
                this.valueType = (byte) 6;
                return;
            } else if (this.ch == '[' && c == 0) {
                this.complex = readArray();
                this.valueType = (byte) 7;
                return;
            }
        }
        if (c != 0) {
            if (this.ch != c) {
                this.offset--;
                this.ch = c;
                readString0();
                this.valueType = (byte) 3;
                return;
            }
            int i25 = this.offset;
            this.offset = i25 + 1;
            this.ch = (char) bArr[i25];
        }
        while (this.ch <= ' ' && ((1 << this.ch) & 4294981376L) != 0) {
            if (this.offset >= i2) {
                this.ch = JSONLexer.EOI;
            } else {
                int i26 = this.offset;
                this.offset = i26 + 1;
                this.ch = (char) bArr[i26];
            }
        }
        boolean z5 = this.ch == ',';
        this.comma = z5;
        if (z5) {
            int i27 = this.offset;
            this.offset = i27 + 1;
            this.ch = (char) bArr[i27];
            if (this.offset >= i2) {
                this.ch = JSONLexer.EOI;
            } else {
                while (this.ch <= ' ' && ((1 << this.ch) & 4294981376L) != 0) {
                    if (this.offset >= i2) {
                        this.ch = JSONLexer.EOI;
                    } else {
                        int i28 = this.offset;
                        this.offset = i28 + 1;
                        this.ch = (char) bArr[i28];
                    }
                }
            }
        }
        if (!z && (this.valueType == 1 || this.valueType == 2)) {
            valueConsumer.accept(bArr, i4 - 1, i15);
            return;
        }
        if (this.valueType == 1) {
            if (this.mag0 == 0 && this.mag1 == 0 && this.mag2 == 0 && this.mag3 != Integer.MIN_VALUE) {
                valueConsumer.accept(this.negative ? -this.mag3 : this.mag3);
                return;
            }
            if (this.mag0 == 0 && this.mag1 == 0) {
                long j = this.mag3 & 4294967295L;
                long j2 = 4294967295L & this.mag2;
                if (j2 <= 2147483647L) {
                    long j3 = (j2 << 32) + j;
                    if (this.negative) {
                        j3 = -j3;
                    }
                    valueConsumer.accept(j3);
                    return;
                }
            }
        }
        valueConsumer.accept(getNumber());
        if (!z4) {
            throw new JSONException(info("illegal input error"));
        }
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean readIfNull() {
        int i;
        byte b;
        byte[] bArr = this.bytes;
        char c = this.ch;
        int i2 = this.offset;
        if (c != 'n' || bArr[i2] != 117 || bArr[i2 + 1] != 108 || bArr[i2 + 2] != 108) {
            return false;
        }
        int i3 = i2 + 3;
        byte b2 = i3 == this.end ? (byte) 26 : (char) bArr[i3];
        int i4 = i2 + 4;
        while (b2 <= 32 && ((1 << b2) & 4294981376L) != 0) {
            if (i4 == this.end) {
                b2 = 26;
            } else {
                int i5 = i4 + 1;
                byte b3 = bArr[i4];
                i4 = i5;
                b2 = b3;
            }
        }
        boolean z = b2 == 44;
        this.comma = z;
        if (z) {
            if (i4 == this.end) {
                i = i4;
                b = 26;
            } else {
                i = i4 + 1;
                b = (char) bArr[i4];
            }
            loop1: while (true) {
                byte b4 = b;
                i4 = i;
                b2 = b4;
                while (b2 <= 32 && ((1 << b2) & 4294981376L) != 0) {
                    if (i4 == this.end) {
                        b2 = 26;
                    }
                }
                i = i4 + 1;
                b = bArr[i4];
            }
        }
        this.offset = i4;
        this.ch = (char) b2;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean isNull() {
        return this.ch == 'n' && this.offset < this.end && this.bytes[this.offset] == 117;
    }

    /* JADX WARN: Removed duplicated region for block: B:69:0x0060 A[LOOP:3: B:64:0x0052->B:69:0x0060, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x004e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x00a2 A[LOOP:4: B:86:0x0094->B:91:0x00a2, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0090 A[SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:55:0x005d -> B:50:0x004e). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:74:0x009f -> B:69:0x0090). Please report as a decompilation issue!!! */
    @Override // com.alibaba.fastjson2.JSONReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.Date readNullOrNewDate() {
        /*
            Method dump skipped, instructions count: 398
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF8.readNullOrNewDate():java.util.Date");
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfNull() {
        int i = this.offset;
        if (this.ch != 'n' || i + 2 >= this.end || this.bytes[i] != 117) {
            return false;
        }
        readNull();
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0040 A[LOOP:0: B:12:0x0027->B:18:0x0040, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0021 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x004e  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x003d -> B:10:0x0021). Please report as a decompilation issue!!! */
    @Override // com.alibaba.fastjson2.JSONReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void readNull() {
        /*
            r14 = this;
            byte[] r0 = r14.bytes
            int r1 = r14.offset
            r2 = r0[r1]
            r3 = 117(0x75, float:1.64E-43)
            if (r2 != r3) goto L7b
            int r2 = r1 + 1
            r2 = r0[r2]
            r3 = 108(0x6c, float:1.51E-43)
            if (r2 != r3) goto L7b
            int r2 = r1 + 2
            r2 = r0[r2]
            if (r2 != r3) goto L7b
            int r2 = r1 + 3
            int r3 = r14.end
            r4 = 26
            if (r2 != r3) goto L23
            r1 = r2
        L21:
            r2 = r4
            goto L27
        L23:
            int r1 = r1 + 4
            r2 = r0[r2]
        L27:
            r5 = 0
            r7 = 4294981376(0x100003700, double:2.1220027474E-314)
            r9 = 1
            r3 = 32
            if (r2 > r3) goto L48
            long r11 = r9 << r2
            long r11 = r11 & r7
            int r11 = (r11 > r5 ? 1 : (r11 == r5 ? 0 : -1))
            if (r11 == 0) goto L48
            int r2 = r14.end
            if (r1 < r2) goto L40
            goto L21
        L40:
            int r2 = r1 + 1
            r1 = r0[r1]
            r13 = r2
            r2 = r1
            r1 = r13
            goto L27
        L48:
            r11 = 44
            if (r2 != r11) goto L4e
            r11 = 1
            goto L4f
        L4e:
            r11 = 0
        L4f:
            r14.comma = r11
            if (r11 == 0) goto L75
            int r2 = r14.end
            if (r1 < r2) goto L5a
            r2 = r1
            r1 = r4
            goto L5e
        L5a:
            int r2 = r1 + 1
            r1 = r0[r1]
        L5e:
            r13 = r2
            r2 = r1
            r1 = r13
        L61:
            if (r2 > r3) goto L75
            long r11 = r9 << r2
            long r11 = r11 & r7
            int r11 = (r11 > r5 ? 1 : (r11 == r5 ? 0 : -1))
            if (r11 == 0) goto L75
            int r2 = r14.end
            if (r1 < r2) goto L70
            r2 = r4
            goto L61
        L70:
            int r2 = r1 + 1
            r1 = r0[r1]
            goto L5e
        L75:
            char r0 = (char) r2
            r14.ch = r0
            r14.offset = r1
            return
        L7b:
            com.alibaba.fastjson2.JSONException r0 = new com.alibaba.fastjson2.JSONException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "json syntax error, not match null"
            r2.<init>(r3)
            java.lang.StringBuilder r1 = r2.append(r1)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF8.readNull():void");
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x003a A[LOOP:0: B:10:0x0021->B:16:0x003a, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x001b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0048  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:14:0x0037 -> B:8:0x001b). Please report as a decompilation issue!!! */
    @Override // com.alibaba.fastjson2.JSONReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final double readNaN() {
        /*
            r14 = this;
            byte[] r0 = r14.bytes
            int r1 = r14.offset
            r2 = r0[r1]
            r3 = 97
            if (r2 != r3) goto L77
            int r2 = r1 + 1
            r2 = r0[r2]
            r3 = 78
            if (r2 != r3) goto L77
            int r2 = r1 + 2
            int r3 = r14.end
            r4 = 26
            if (r2 != r3) goto L1d
            r1 = r2
        L1b:
            r2 = r4
            goto L21
        L1d:
            int r1 = r1 + 3
            r2 = r0[r2]
        L21:
            r5 = 0
            r7 = 4294981376(0x100003700, double:2.1220027474E-314)
            r9 = 1
            r3 = 32
            if (r2 > r3) goto L42
            long r11 = r9 << r2
            long r11 = r11 & r7
            int r11 = (r11 > r5 ? 1 : (r11 == r5 ? 0 : -1))
            if (r11 == 0) goto L42
            int r2 = r14.end
            if (r1 < r2) goto L3a
            goto L1b
        L3a:
            int r2 = r1 + 1
            r1 = r0[r1]
            r13 = r2
            r2 = r1
            r1 = r13
            goto L21
        L42:
            r11 = 44
            if (r2 != r11) goto L48
            r11 = 1
            goto L49
        L48:
            r11 = 0
        L49:
            r14.comma = r11
            if (r11 == 0) goto L6f
            int r2 = r14.end
            if (r1 < r2) goto L54
            r2 = r1
            r1 = r4
            goto L58
        L54:
            int r2 = r1 + 1
            r1 = r0[r1]
        L58:
            r13 = r2
            r2 = r1
            r1 = r13
        L5b:
            if (r2 > r3) goto L6f
            long r11 = r9 << r2
            long r11 = r11 & r7
            int r11 = (r11 > r5 ? 1 : (r11 == r5 ? 0 : -1))
            if (r11 == 0) goto L6f
            int r2 = r14.end
            if (r1 < r2) goto L6a
            r2 = r4
            goto L5b
        L6a:
            int r2 = r1 + 1
            r1 = r0[r1]
            goto L58
        L6f:
            char r0 = (char) r2
            r14.ch = r0
            r14.offset = r1
            r0 = 9221120237041090560(0x7ff8000000000000, double:NaN)
            return r0
        L77:
            com.alibaba.fastjson2.JSONException r0 = new com.alibaba.fastjson2.JSONException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "json syntax error, not NaN "
            r2.<init>(r3)
            java.lang.StringBuilder r1 = r2.append(r1)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF8.readNaN():double");
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final int getStringLength() {
        int i;
        if (this.ch != '\"' && this.ch != '\'') {
            throw new JSONException("string length only support string input " + this.ch);
        }
        byte b = this.ch;
        int i2 = this.offset;
        byte[] bArr = this.bytes;
        int i3 = i2 + 8;
        if (i3 >= this.end || i3 >= bArr.length || bArr[i2] == b || bArr[i2 + 1] == b || bArr[i2 + 2] == b || bArr[i2 + 3] == b || bArr[i2 + 4] == b || bArr[i2 + 5] == b || bArr[i2 + 6] == b || bArr[i2 + 7] == b) {
            i = 0;
        } else {
            i = 8;
            i2 = i3;
        }
        while (i2 < this.end && bArr[i2] != b) {
            i2++;
            i++;
        }
        return i;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final LocalDate readLocalDate() {
        int yy;
        LocalDate of;
        byte[] bArr = this.bytes;
        int i = this.offset;
        char c = this.ch;
        if ((c == '\"' || c == '\'') && !this.context.formatComplex) {
            int i2 = i + 10;
            if (i2 < bArr.length && i2 < this.end && (yy = DateUtils.yy(bArr, i)) != -1) {
                long ymd = DateUtils.ymd(bArr, i + 2);
                if (ymd != -1 && bArr[i2] == c) {
                    int i3 = yy + (((int) ymd) & 255);
                    int i4 = ((int) (ymd >> 24)) & 255;
                    int i5 = ((int) (ymd >> 48)) & 255;
                    if ((i3 | i4 | i5) == 0) {
                        of = null;
                    } else {
                        try {
                            of = LocalDate.of(i3, i4, i5);
                        } catch (DateTimeException e) {
                            throw error("read date error", e);
                        }
                    }
                    this.offset = i + 11;
                    next();
                    boolean z = this.ch == ',';
                    this.comma = z;
                    if (z) {
                        next();
                    }
                    return of;
                }
            }
            LocalDate readLocalDate0 = readLocalDate0(i, bArr, c);
            if (readLocalDate0 != null) {
                return readLocalDate0;
            }
        }
        return super.readLocalDate();
    }

    private LocalDate readLocalDate0(int i, byte[] bArr, char c) {
        int i2;
        int min = Math.min(i + 17, this.end);
        int i3 = -1;
        for (int i4 = i; i4 < min; i4++) {
            if (bArr[i4] == c) {
                i3 = i4;
            }
        }
        if (i3 == -1 || (i2 = i3 - i) <= 10 || bArr[i3 - 6] != 45 || bArr[i3 - 3] != 45) {
            return null;
        }
        LocalDate of = LocalDate.of(TypeUtils.parseInt(bArr, i, i2 - 6), IOUtils.digit2(bArr, i3 - 5), IOUtils.digit2(bArr, i3 - 2));
        this.offset = i3 + 1;
        next();
        boolean z = this.ch == ',';
        this.comma = z;
        if (z) {
            next();
        }
        return of;
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0138 A[LOOP:1: B:48:0x00b5->B:84:0x0138, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:85:0x00ae A[EDGE_INSN: B:85:0x00ae->B:46:0x00ae BREAK  A[LOOP:1: B:48:0x00b5->B:84:0x0138], SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:73:0x0134 -> B:46:0x00ae). Please report as a decompilation issue!!! */
    @Override // com.alibaba.fastjson2.JSONReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.time.OffsetDateTime readOffsetDateTime() {
        /*
            Method dump skipped, instructions count: 333
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF8.readOffsetDateTime():java.time.OffsetDateTime");
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final OffsetTime readOffsetTime() {
        int i;
        ZoneOffset of;
        byte[] bArr = this.bytes;
        int i2 = this.offset;
        JSONReader.Context context = this.context;
        if ((this.ch == '\"' || this.ch == '\'') && context.dateFormat == null) {
            byte b = this.ch;
            int i3 = i2 + 8;
            if (i3 < bArr.length && i3 < this.end && bArr[i2 + 2] == 58 && bArr[i2 + 5] == 58) {
                byte b2 = bArr[i2];
                byte b3 = bArr[i2 + 1];
                byte b4 = bArr[i2 + 3];
                byte b5 = bArr[i2 + 4];
                byte b6 = bArr[i2 + 6];
                byte b7 = bArr[i2 + 7];
                if (b2 < 48 || b2 > 57 || b3 < 48 || b3 > 57) {
                    throw new JSONException(info("illegal offsetTime"));
                }
                int i4 = ((b2 - JSONB.Constants.BC_INT32_BYTE_MIN) * 10) + (b3 - JSONB.Constants.BC_INT32_BYTE_MIN);
                if (b4 < 48 || b4 > 57 || b5 < 48 || b5 > 57) {
                    throw new JSONException(info("illegal offsetTime"));
                }
                int i5 = ((b4 - JSONB.Constants.BC_INT32_BYTE_MIN) * 10) + (b5 - JSONB.Constants.BC_INT32_BYTE_MIN);
                if (b6 < 48 || b6 > 57 || b7 < 48 || b7 > 57) {
                    throw new JSONException(info("illegal offsetTime"));
                }
                int i6 = ((b6 - JSONB.Constants.BC_INT32_BYTE_MIN) * 10) + (b7 - JSONB.Constants.BC_INT32_BYTE_MIN);
                int i7 = i2 + 25;
                int i8 = i3;
                int i9 = -1;
                while (true) {
                    if (i8 >= i7 || i8 >= this.end || i8 >= bArr.length) {
                        break;
                    }
                    byte b8 = bArr[i8];
                    if (i9 == -1 && (b8 == 90 || b8 == 43 || b8 == 45)) {
                        i9 = (i8 - i3) - 1;
                    }
                    if (b8 == b) {
                        i = i8 - i2;
                        break;
                    }
                    i8++;
                }
                i = 0;
                int readNanos = i9 <= 0 ? 0 : DateUtils.readNanos(bArr, i9, i2 + 9);
                int i10 = (i - 9) - i9;
                if (i10 <= 1) {
                    of = ZoneOffset.UTC;
                } else {
                    of = ZoneOffset.of(new String(bArr, i2 + 9 + i9, i10));
                }
                OffsetTime of2 = OffsetTime.of(LocalTime.of(i4, i5, i6, readNanos), of);
                this.offset += i + 1;
                next();
                boolean z = this.ch == ',';
                this.comma = z;
                if (z) {
                    next();
                }
                return of2;
            }
        }
        throw new JSONException(info("illegal offsetTime"));
    }

    @Override // com.alibaba.fastjson2.JSONReader
    protected final ZonedDateTime readZonedDateTimeX(int i) {
        ZonedDateTime parseZonedDateTime;
        if (!isString()) {
            throw new JSONException("date only support string input");
        }
        if (i < 19) {
            return null;
        }
        if (i == 30 && this.bytes[this.offset + 29] == 90) {
            parseZonedDateTime = ZonedDateTime.of(DateUtils.parseLocalDateTime29(this.bytes, this.offset), ZoneOffset.UTC);
        } else if (i == 29 && this.bytes[this.offset + 28] == 90) {
            parseZonedDateTime = ZonedDateTime.of(DateUtils.parseLocalDateTime28(this.bytes, this.offset), ZoneOffset.UTC);
        } else if (i == 28 && this.bytes[this.offset + 27] == 90) {
            parseZonedDateTime = ZonedDateTime.of(DateUtils.parseLocalDateTime27(this.bytes, this.offset), ZoneOffset.UTC);
        } else if (i == 27 && this.bytes[this.offset + 26] == 90) {
            parseZonedDateTime = ZonedDateTime.of(DateUtils.parseLocalDateTime26(this.bytes, this.offset), ZoneOffset.UTC);
        } else {
            parseZonedDateTime = DateUtils.parseZonedDateTime(this.bytes, this.offset, i, this.context.zoneId);
        }
        if (parseZonedDateTime == null) {
            return null;
        }
        this.offset += i + 1;
        next();
        boolean z = this.ch == ',';
        this.comma = z;
        if (z) {
            next();
        }
        return parseZonedDateTime;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final LocalDate readLocalDate8() {
        if (!isString()) {
            throw new JSONException("localDate only support string input");
        }
        try {
            LocalDate parseLocalDate8 = DateUtils.parseLocalDate8(this.bytes, this.offset);
            this.offset += 9;
            next();
            boolean z = this.ch == ',';
            this.comma = z;
            if (z) {
                next();
            }
            return parseLocalDate8;
        } catch (DateTimeException e) {
            throw new JSONException(info("read date error"), e);
        }
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final LocalDate readLocalDate9() {
        if (!isString()) {
            throw new JSONException("localDate only support string input");
        }
        try {
            LocalDate parseLocalDate9 = DateUtils.parseLocalDate9(this.bytes, this.offset);
            this.offset += 10;
            next();
            boolean z = this.ch == ',';
            this.comma = z;
            if (z) {
                next();
            }
            return parseLocalDate9;
        } catch (DateTimeException e) {
            throw new JSONException(info("read date error"), e);
        }
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final LocalDate readLocalDate10() {
        if (!isString()) {
            throw new JSONException("localDate only support string input");
        }
        try {
            LocalDate parseLocalDate10 = DateUtils.parseLocalDate10(this.bytes, this.offset);
            if (parseLocalDate10 == null) {
                return null;
            }
            this.offset += 11;
            next();
            boolean z = this.ch == ',';
            this.comma = z;
            if (z) {
                next();
            }
            return parseLocalDate10;
        } catch (DateTimeException e) {
            throw new JSONException(info("read date error"), e);
        }
    }

    @Override // com.alibaba.fastjson2.JSONReader
    protected final LocalDate readLocalDate11() {
        if (!isString()) {
            throw new JSONException("localDate only support string input");
        }
        LocalDate parseLocalDate11 = DateUtils.parseLocalDate11(this.bytes, this.offset);
        if (parseLocalDate11 == null) {
            return null;
        }
        this.offset += 11;
        next();
        boolean z = this.ch == ',';
        this.comma = z;
        if (z) {
            next();
        }
        return parseLocalDate11;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    protected final LocalDateTime readLocalDateTime17() {
        if (!isString()) {
            throw new JSONException("date only support string input");
        }
        LocalDateTime parseLocalDateTime17 = DateUtils.parseLocalDateTime17(this.bytes, this.offset);
        if (parseLocalDateTime17 == null) {
            return null;
        }
        this.offset += 18;
        next();
        boolean z = this.ch == ',';
        this.comma = z;
        if (z) {
            next();
        }
        return parseLocalDateTime17;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    protected final LocalTime readLocalTime5() {
        if (this.ch != '\"' && this.ch != '\'') {
            throw new JSONException("localTime only support string input");
        }
        LocalTime parseLocalTime5 = DateUtils.parseLocalTime5(this.bytes, this.offset);
        if (parseLocalTime5 == null) {
            return null;
        }
        this.offset += 6;
        next();
        boolean z = this.ch == ',';
        this.comma = z;
        if (z) {
            next();
        }
        return parseLocalTime5;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    protected final LocalTime readLocalTime6() {
        if (this.ch != '\"' && this.ch != '\'') {
            throw new JSONException("localTime only support string input");
        }
        LocalTime parseLocalTime6 = DateUtils.parseLocalTime6(this.bytes, this.offset);
        if (parseLocalTime6 == null) {
            return null;
        }
        this.offset += 7;
        next();
        boolean z = this.ch == ',';
        this.comma = z;
        if (z) {
            next();
        }
        return parseLocalTime6;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    protected final LocalTime readLocalTime7() {
        if (this.ch != '\"' && this.ch != '\'') {
            throw new JSONException("localTime only support string input");
        }
        LocalTime parseLocalTime7 = DateUtils.parseLocalTime7(this.bytes, this.offset);
        if (parseLocalTime7 == null) {
            return null;
        }
        this.offset += 8;
        next();
        boolean z = this.ch == ',';
        this.comma = z;
        if (z) {
            next();
        }
        return parseLocalTime7;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    protected final LocalTime readLocalTime8() {
        if (this.ch != '\"' && this.ch != '\'') {
            throw new JSONException("localTime only support string input");
        }
        LocalTime parseLocalTime8 = DateUtils.parseLocalTime8(this.bytes, this.offset);
        if (parseLocalTime8 == null) {
            return null;
        }
        this.offset += 9;
        next();
        boolean z = this.ch == ',';
        this.comma = z;
        if (z) {
            next();
        }
        return parseLocalTime8;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    protected final LocalTime readLocalTime9() {
        if (this.ch != '\"' && this.ch != '\'') {
            throw new JSONException("localTime only support string input");
        }
        LocalTime parseLocalTime8 = DateUtils.parseLocalTime8(this.bytes, this.offset);
        if (parseLocalTime8 == null) {
            return null;
        }
        this.offset += 10;
        next();
        boolean z = this.ch == ',';
        this.comma = z;
        if (z) {
            next();
        }
        return parseLocalTime8;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    protected final LocalTime readLocalTime10() {
        if (!isString()) {
            throw new JSONException("localTime only support string input");
        }
        LocalTime parseLocalTime10 = DateUtils.parseLocalTime10(this.bytes, this.offset);
        if (parseLocalTime10 == null) {
            return null;
        }
        this.offset += 11;
        next();
        boolean z = this.ch == ',';
        this.comma = z;
        if (z) {
            next();
        }
        return parseLocalTime10;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    protected final LocalTime readLocalTime11() {
        if (!isString()) {
            throw new JSONException("localTime only support string input");
        }
        LocalTime parseLocalTime11 = DateUtils.parseLocalTime11(this.bytes, this.offset);
        if (parseLocalTime11 == null) {
            return null;
        }
        this.offset += 12;
        next();
        boolean z = this.ch == ',';
        this.comma = z;
        if (z) {
            next();
        }
        return parseLocalTime11;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    protected final LocalTime readLocalTime12() {
        if (!isString()) {
            throw new JSONException("localTime only support string input");
        }
        LocalTime parseLocalTime12 = DateUtils.parseLocalTime12(this.bytes, this.offset);
        if (parseLocalTime12 == null) {
            return null;
        }
        this.offset += 13;
        next();
        boolean z = this.ch == ',';
        this.comma = z;
        if (z) {
            next();
        }
        return parseLocalTime12;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    protected final LocalTime readLocalTime15() {
        if (!isString()) {
            throw new JSONException("localTime only support string input");
        }
        LocalTime parseLocalTime15 = DateUtils.parseLocalTime15(this.bytes, this.offset);
        if (parseLocalTime15 == null) {
            return null;
        }
        this.offset += 16;
        next();
        boolean z = this.ch == ',';
        this.comma = z;
        if (z) {
            next();
        }
        return parseLocalTime15;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    protected final LocalTime readLocalTime18() {
        if (!isString()) {
            throw new JSONException("localTime only support string input");
        }
        LocalTime parseLocalTime18 = DateUtils.parseLocalTime18(this.bytes, this.offset);
        if (parseLocalTime18 == null) {
            return null;
        }
        this.offset += 19;
        next();
        boolean z = this.ch == ',';
        this.comma = z;
        if (z) {
            next();
        }
        return parseLocalTime18;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    protected final LocalDateTime readLocalDateTime12() {
        if (!isString()) {
            throw new JSONException("date only support string input");
        }
        LocalDateTime parseLocalDateTime12 = DateUtils.parseLocalDateTime12(this.bytes, this.offset);
        if (parseLocalDateTime12 == null) {
            return null;
        }
        this.offset += 13;
        next();
        boolean z = this.ch == ',';
        this.comma = z;
        if (z) {
            next();
        }
        return parseLocalDateTime12;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    protected final LocalDateTime readLocalDateTime14() {
        if (!isString()) {
            throw new JSONException("date only support string input");
        }
        LocalDateTime parseLocalDateTime14 = DateUtils.parseLocalDateTime14(this.bytes, this.offset);
        if (parseLocalDateTime14 == null) {
            return null;
        }
        this.offset += 15;
        next();
        boolean z = this.ch == ',';
        this.comma = z;
        if (z) {
            next();
        }
        return parseLocalDateTime14;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    protected final LocalDateTime readLocalDateTime16() {
        if (!isString()) {
            throw new JSONException("date only support string input");
        }
        LocalDateTime parseLocalDateTime16 = DateUtils.parseLocalDateTime16(this.bytes, this.offset);
        if (parseLocalDateTime16 == null) {
            return null;
        }
        this.offset += 17;
        next();
        boolean z = this.ch == ',';
        this.comma = z;
        if (z) {
            next();
        }
        return parseLocalDateTime16;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    protected final LocalDateTime readLocalDateTime18() {
        if (!isString()) {
            throw new JSONException("date only support string input");
        }
        LocalDateTime parseLocalDateTime18 = DateUtils.parseLocalDateTime18(this.bytes, this.offset);
        this.offset += 19;
        next();
        boolean z = this.ch == ',';
        this.comma = z;
        if (z) {
            next();
        }
        return parseLocalDateTime18;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    protected final LocalDateTime readLocalDateTime19() {
        if (!isString()) {
            throw new JSONException("date only support string input");
        }
        LocalDateTime parseLocalDateTime19 = DateUtils.parseLocalDateTime19(this.bytes, this.offset);
        if (parseLocalDateTime19 == null) {
            return null;
        }
        this.offset += 20;
        next();
        boolean z = this.ch == ',';
        this.comma = z;
        if (z) {
            next();
        }
        return parseLocalDateTime19;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    protected final LocalDateTime readLocalDateTime20() {
        if (!isString()) {
            throw new JSONException("date only support string input");
        }
        LocalDateTime parseLocalDateTime20 = DateUtils.parseLocalDateTime20(this.bytes, this.offset);
        if (parseLocalDateTime20 == null) {
            return null;
        }
        this.offset += 21;
        next();
        boolean z = this.ch == ',';
        this.comma = z;
        if (z) {
            next();
        }
        return parseLocalDateTime20;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final long readMillis19() {
        byte b = this.ch;
        if (b != 34 && b != 39) {
            throw new JSONException("date only support string input");
        }
        if (this.offset + 18 >= this.end) {
            this.wasNull = true;
            return 0L;
        }
        long parseMillis19 = DateUtils.parseMillis19(this.bytes, this.offset, this.context.zoneId);
        if (this.bytes[this.offset + 19] != b) {
            throw new JSONException(info("illegal date input"));
        }
        this.offset += 20;
        next();
        boolean z = this.ch == ',';
        this.comma = z;
        if (z) {
            next();
        }
        return parseMillis19;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v11, types: [java.time.LocalDateTime] */
    @Override // com.alibaba.fastjson2.JSONReader
    protected final LocalDateTime readLocalDateTimeX(int i) {
        LocalDateTime localDateTime;
        if (!isString()) {
            throw new JSONException("date only support string input");
        }
        if (this.bytes[(this.offset + i) - 1] == 90) {
            localDateTime = DateUtils.parseZonedDateTime(this.bytes, this.offset, i).toInstant().atZone(this.context.getZoneId()).toLocalDateTime();
        } else {
            localDateTime = DateUtils.parseLocalDateTimeX(this.bytes, this.offset, i);
        }
        if (localDateTime == null) {
            return null;
        }
        this.offset += i + 1;
        next();
        boolean z = this.ch == ',';
        this.comma = z;
        if (z) {
            next();
        }
        return localDateTime;
    }

    /* JADX WARN: Code restructure failed: missing block: B:226:0x00a0, code lost:
    
        r26 = r14;
     */
    /* JADX WARN: Removed duplicated region for block: B:106:0x02a3  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x02af  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x028d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:116:0x02b9  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x02be  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x02e1  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x02e7  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0297  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x0228  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x010a  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x0129  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x0158  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x013f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:220:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0093 A[LOOP:0: B:11:0x005a->B:22:0x0093, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x008c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0161  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0230  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0257  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:103:0x02ac -> B:106:0x028d). Please report as a decompilation issue!!! */
    @Override // com.alibaba.fastjson2.JSONReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.math.BigDecimal readBigDecimal() {
        /*
            Method dump skipped, instructions count: 755
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF8.readBigDecimal():java.math.BigDecimal");
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0079 A[LOOP:0: B:30:0x0063->B:35:0x0079, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x005d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0089  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:33:0x0076 -> B:28:0x005d). Please report as a decompilation issue!!! */
    @Override // com.alibaba.fastjson2.JSONReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.UUID readUUID() {
        /*
            r11 = this;
            char r0 = r11.ch
            int r1 = r11.end
            r2 = 110(0x6e, float:1.54E-43)
            r3 = 0
            if (r0 != r2) goto Ld
            r11.readNull()
            return r3
        Ld:
            r2 = 34
            if (r0 == r2) goto L1d
            r2 = 39
            if (r0 != r2) goto L16
            goto L1d
        L16:
            java.lang.String r0 = "syntax error, can not read uuid"
            com.alibaba.fastjson2.JSONException r0 = r11.error(r0)
            throw r0
        L1d:
            byte[] r2 = r11.bytes
            int r4 = r11.offset
            int r5 = r4 + 36
            if (r5 >= r1) goto L4a
            r5 = r2[r5]
            if (r5 != r0) goto L4a
            int r5 = r4 + 8
            r5 = r2[r5]
            r6 = 45
            if (r5 != r6) goto L4a
            int r5 = r4 + 13
            r5 = r2[r5]
            if (r5 != r6) goto L4a
            int r5 = r4 + 18
            r5 = r2[r5]
            if (r5 != r6) goto L4a
            int r5 = r4 + 23
            r5 = r2[r5]
            if (r5 != r6) goto L4a
            java.util.UUID r0 = readUUID36(r2, r4)
            int r4 = r4 + 37
            goto L58
        L4a:
            int r5 = r4 + 32
            if (r5 >= r1) goto L96
            r5 = r2[r5]
            if (r5 != r0) goto L96
            java.util.UUID r0 = readUUID32(r2, r4)
            int r4 = r4 + 33
        L58:
            r3 = 26
            if (r4 != r1) goto L5f
            r5 = r4
        L5d:
            r4 = r3
            goto L63
        L5f:
            int r5 = r4 + 1
            r4 = r2[r4]
        L63:
            r6 = 32
            if (r4 > r6) goto L81
            r6 = 1
            long r6 = r6 << r4
            r8 = 4294981376(0x100003700, double:2.1220027474E-314)
            long r6 = r6 & r8
            r8 = 0
            int r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r6 == 0) goto L81
            if (r5 != r1) goto L79
            goto L5d
        L79:
            int r4 = r5 + 1
            r5 = r2[r5]
            r10 = r5
            r5 = r4
            r4 = r10
            goto L63
        L81:
            r11.offset = r5
            r1 = 44
            if (r4 != r1) goto L89
            r1 = 1
            goto L8a
        L89:
            r1 = 0
        L8a:
            r11.comma = r1
            if (r1 == 0) goto L92
            r11.next()
            return r0
        L92:
            char r1 = (char) r4
            r11.ch = r1
            return r0
        L96:
            java.lang.String r0 = r11.readString()
            boolean r1 = r0.isEmpty()
            if (r1 == 0) goto La1
            return r3
        La1:
            java.util.UUID r0 = java.util.UUID.fromString(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF8.readUUID():java.util.UUID");
    }

    static UUID readUUID32(byte[] bArr, int i) {
        long parse4Nibbles = parse4Nibbles(bArr, i);
        long parse4Nibbles2 = parse4Nibbles(bArr, i + 4);
        long parse4Nibbles3 = parse4Nibbles(bArr, i + 8);
        long parse4Nibbles4 = parse4Nibbles(bArr, i + 12);
        long parse4Nibbles5 = parse4Nibbles(bArr, i + 16);
        long parse4Nibbles6 = parse4Nibbles(bArr, i + 20);
        long parse4Nibbles7 = parse4Nibbles(bArr, i + 24);
        long parse4Nibbles8 = parse4Nibbles(bArr, i + 28);
        if ((parse4Nibbles | parse4Nibbles2 | parse4Nibbles3 | parse4Nibbles4 | parse4Nibbles5 | parse4Nibbles6 | parse4Nibbles7 | parse4Nibbles8) < 0) {
            throw new JSONException("Invalid UUID string:  ".concat(new String(bArr, i, 32, StandardCharsets.ISO_8859_1)));
        }
        return new UUID((parse4Nibbles << 48) | (parse4Nibbles2 << 32) | (parse4Nibbles3 << 16) | parse4Nibbles4, (parse4Nibbles6 << 32) | (parse4Nibbles5 << 48) | (parse4Nibbles7 << 16) | parse4Nibbles8);
    }

    static UUID readUUID36(byte[] bArr, int i) {
        long parse4Nibbles = parse4Nibbles(bArr, i);
        long parse4Nibbles2 = parse4Nibbles(bArr, i + 4);
        long parse4Nibbles3 = parse4Nibbles(bArr, i + 9);
        long parse4Nibbles4 = parse4Nibbles(bArr, i + 14);
        long parse4Nibbles5 = parse4Nibbles(bArr, i + 19);
        long parse4Nibbles6 = parse4Nibbles(bArr, i + 24);
        long parse4Nibbles7 = parse4Nibbles(bArr, i + 28);
        long parse4Nibbles8 = parse4Nibbles(bArr, i + 32);
        if ((parse4Nibbles | parse4Nibbles2 | parse4Nibbles3 | parse4Nibbles4 | parse4Nibbles5 | parse4Nibbles6 | parse4Nibbles7 | parse4Nibbles8) < 0) {
            throw new JSONException("Invalid UUID string:  ".concat(new String(bArr, i, 36, StandardCharsets.ISO_8859_1)));
        }
        return new UUID((parse4Nibbles << 48) | (parse4Nibbles2 << 32) | (parse4Nibbles3 << 16) | parse4Nibbles4, (parse4Nibbles5 << 48) | (parse4Nibbles6 << 32) | (parse4Nibbles7 << 16) | parse4Nibbles8);
    }

    static long parse4Nibbles(byte[] bArr, int i) {
        int i2 = JDKUtils.UNSAFE.getInt(bArr, JDKUtils.ARRAY_BYTE_BASE_OFFSET + i);
        if (JDKUtils.BIG_ENDIAN) {
            i2 = Integer.reverseBytes(i2);
        }
        byte[] bArr2 = JSONFactory.NIBBLES;
        return bArr2[(i2 >> 24) & 255] | (bArr2[i2 & 255] << 12) | (bArr2[(i2 >> 8) & 255] << 8) | (bArr2[(i2 >> 16) & 255] << 4);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0048 A[LOOP:1: B:15:0x002f->B:21:0x0048, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0029 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0056  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:0x0045 -> B:12:0x0029). Please report as a decompilation issue!!! */
    @Override // com.alibaba.fastjson2.JSONReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String readPattern() {
        /*
            r15 = this;
            char r0 = r15.ch
            r1 = 47
            if (r0 != r1) goto L83
            byte[] r0 = r15.bytes
            int r2 = r15.offset
            r3 = r2
        Lb:
            int r4 = r15.end
            if (r3 >= r4) goto L17
            r4 = r0[r3]
            if (r4 != r1) goto L14
            goto L17
        L14:
            int r3 = r3 + 1
            goto Lb
        L17:
            java.lang.String r1 = new java.lang.String
            int r4 = r3 - r2
            java.nio.charset.Charset r5 = java.nio.charset.StandardCharsets.UTF_8
            r1.<init>(r0, r2, r4, r5)
            int r2 = r3 + 1
            int r4 = r15.end
            r5 = 26
            if (r2 != r4) goto L2b
            r3 = r2
        L29:
            r2 = r5
            goto L2f
        L2b:
            int r3 = r3 + 2
            r2 = r0[r2]
        L2f:
            r6 = 0
            r8 = 4294981376(0x100003700, double:2.1220027474E-314)
            r10 = 1
            r4 = 32
            if (r2 > r4) goto L50
            long r12 = r10 << r2
            long r12 = r12 & r8
            int r12 = (r12 > r6 ? 1 : (r12 == r6 ? 0 : -1))
            if (r12 == 0) goto L50
            int r2 = r15.end
            if (r3 != r2) goto L48
            goto L29
        L48:
            int r2 = r3 + 1
            r3 = r0[r3]
            r14 = r3
            r3 = r2
            r2 = r14
            goto L2f
        L50:
            r12 = 44
            if (r2 != r12) goto L56
            r12 = 1
            goto L57
        L56:
            r12 = 0
        L57:
            r15.comma = r12
            if (r12 == 0) goto L7d
            int r2 = r15.end
            if (r3 != r2) goto L62
            r2 = r3
            r3 = r5
            goto L66
        L62:
            int r2 = r3 + 1
            r3 = r0[r3]
        L66:
            r14 = r3
            r3 = r2
            r2 = r14
        L69:
            if (r2 > r4) goto L7d
            long r12 = r10 << r2
            long r12 = r12 & r8
            int r12 = (r12 > r6 ? 1 : (r12 == r6 ? 0 : -1))
            if (r12 == 0) goto L7d
            int r2 = r15.end
            if (r3 != r2) goto L78
            r2 = r5
            goto L69
        L78:
            int r2 = r3 + 1
            r3 = r0[r3]
            goto L66
        L7d:
            r15.offset = r3
            char r0 = (char) r2
            r15.ch = r0
            return r1
        L83:
            com.alibaba.fastjson2.JSONException r0 = new com.alibaba.fastjson2.JSONException
            java.lang.String r1 = "illegal pattern"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF8.readPattern():java.lang.String");
    }

    /* JADX WARN: Code restructure failed: missing block: B:49:0x00a4, code lost:
    
        char_utf8(r3, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00a7, code lost:
    
        return true;
     */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0063 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x006f A[LOOP:0: B:17:0x0056->B:23:0x006f, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0050 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0084 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x009d A[SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x006c -> B:15:0x0050). Please report as a decompilation issue!!! */
    @Override // com.alibaba.fastjson2.JSONReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean nextIfNullOrEmptyString() {
        /*
            r18 = this;
            r0 = r18
            char r1 = r0.ch
            int r2 = r0.end
            int r3 = r0.offset
            byte[] r4 = r0.bytes
            r5 = 110(0x6e, float:1.54E-43)
            r6 = 0
            r7 = 1
            if (r1 != r5) goto L29
            int r5 = r3 + 2
            if (r5 >= r2) goto L29
            r8 = r4[r3]
            r9 = 117(0x75, float:1.64E-43)
            if (r8 != r9) goto L29
            int r8 = r3 + 1
            r8 = r4[r8]
            r9 = 108(0x6c, float:1.51E-43)
            if (r8 != r9) goto L29
            r5 = r4[r5]
            if (r5 != r9) goto L29
            int r3 = r3 + 3
            goto L4b
        L29:
            r5 = 34
            if (r1 == r5) goto L33
            r5 = 39
            if (r1 != r5) goto L32
            goto L33
        L32:
            return r6
        L33:
            if (r3 >= r2) goto L3b
            r5 = r4[r3]
            if (r5 != r1) goto L3b
            int r3 = r3 + r7
            goto L4b
        L3b:
            int r5 = r3 + 4
            if (r5 >= r2) goto Lae
            boolean r8 = com.alibaba.fastjson2.util.IOUtils.isNULL(r4, r3)
            if (r8 == 0) goto Lae
            r5 = r4[r5]
            if (r5 != r1) goto Lae
            int r3 = r3 + 5
        L4b:
            r1 = 26
            if (r3 != r2) goto L52
            r5 = r3
        L50:
            r3 = r1
            goto L56
        L52:
            int r5 = r3 + 1
            r3 = r4[r3]
        L56:
            r8 = 0
            r10 = 4294981376(0x100003700, double:2.1220027474E-314)
            r12 = 1
            r14 = 32
            if (r3 < 0) goto L79
            if (r3 > r14) goto L79
            long r15 = r12 << r3
            long r15 = r15 & r10
            int r15 = (r15 > r8 ? 1 : (r15 == r8 ? 0 : -1))
            if (r15 == 0) goto L79
            if (r5 != r2) goto L6f
            goto L50
        L6f:
            int r3 = r5 + 1
            r5 = r4[r5]
            r17 = r5
            r5 = r3
            r3 = r17
            goto L56
        L79:
            r15 = 44
            if (r3 != r15) goto L7e
            r6 = r7
        L7e:
            r0.comma = r6
            if (r6 == 0) goto L8f
            if (r5 != r2) goto L86
        L84:
            r3 = r1
            goto L8f
        L86:
            int r3 = r5 + 1
            r5 = r4[r5]
        L8a:
            r17 = r5
            r5 = r3
            r3 = r17
        L8f:
            if (r3 < 0) goto La2
            if (r3 > r14) goto La2
            long r15 = r12 << r3
            long r15 = r15 & r10
            int r6 = (r15 > r8 ? 1 : (r15 == r8 ? 0 : -1))
            if (r6 == 0) goto La2
            if (r5 != r2) goto L9d
            goto L84
        L9d:
            int r3 = r5 + 1
            r5 = r4[r5]
            goto L8a
        La2:
            if (r3 >= 0) goto La8
            r0.char_utf8(r3, r5)
            return r7
        La8:
            r0.offset = r5
            char r1 = (char) r3
            r0.ch = r1
            return r7
        Lae:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF8.nextIfNullOrEmptyString():boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0038 A[LOOP:0: B:14:0x0020->B:20:0x0038, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x001a A[SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:0x0035 -> B:12:0x001a). Please report as a decompilation issue!!! */
    @Override // com.alibaba.fastjson2.JSONReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean nextIfMatchIdent(char r9, char r10) {
        /*
            r8 = this;
            char r0 = r8.ch
            r1 = 0
            if (r0 == r9) goto L6
            return r1
        L6:
            byte[] r9 = r8.bytes
            int r0 = r8.offset
            int r2 = r0 + 1
            int r3 = r8.end
            if (r2 > r3) goto L68
            r4 = r9[r0]
            if (r4 == r10) goto L15
            goto L68
        L15:
            r10 = 26
            if (r2 != r3) goto L1c
            r0 = r2
        L1a:
            r2 = r10
            goto L20
        L1c:
            int r0 = r0 + 2
            r2 = r9[r2]
        L20:
            r3 = 32
            if (r2 > r3) goto L40
            r3 = 1
            long r3 = r3 << r2
            r5 = 4294981376(0x100003700, double:2.1220027474E-314)
            long r3 = r3 & r5
            r5 = 0
            int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r3 == 0) goto L40
            int r2 = r8.end
            if (r0 != r2) goto L38
            goto L1a
        L38:
            int r2 = r0 + 1
            r0 = r9[r0]
            r7 = r2
            r2 = r0
            r0 = r7
            goto L20
        L40:
            int r9 = r8.offset
            int r9 = r9 + 2
            if (r0 != r9) goto L61
            if (r2 == r10) goto L61
            r9 = 40
            if (r2 == r9) goto L61
            r9 = 91
            if (r2 == r9) goto L61
            r9 = 93
            if (r2 == r9) goto L61
            r9 = 41
            if (r2 == r9) goto L61
            r9 = 58
            if (r2 == r9) goto L61
            r9 = 44
            if (r2 == r9) goto L61
            return r1
        L61:
            r8.offset = r0
            char r9 = (char) r2
            r8.ch = r9
            r9 = 1
            return r9
        L68:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF8.nextIfMatchIdent(char, char):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x003e A[LOOP:0: B:15:0x0026->B:21:0x003e, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x001f A[SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x003b -> B:13:0x001f). Please report as a decompilation issue!!! */
    @Override // com.alibaba.fastjson2.JSONReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean nextIfMatchIdent(char r8, char r9, char r10) {
        /*
            r7 = this;
            char r0 = r7.ch
            r1 = 0
            if (r0 == r8) goto L6
            return r1
        L6:
            byte[] r8 = r7.bytes
            int r0 = r7.offset
            int r2 = r0 + 2
            int r3 = r7.end
            if (r2 > r3) goto L6d
            r4 = r8[r0]
            if (r4 != r9) goto L6d
            int r9 = r0 + 1
            r9 = r8[r9]
            if (r9 == r10) goto L1b
            goto L6d
        L1b:
            r9 = 26
            if (r2 != r3) goto L21
        L1f:
            r10 = r9
            goto L26
        L21:
            int r0 = r0 + 3
            r10 = r8[r2]
            r2 = r0
        L26:
            r0 = 32
            if (r10 > r0) goto L45
            r3 = 1
            long r3 = r3 << r10
            r5 = 4294981376(0x100003700, double:2.1220027474E-314)
            long r3 = r3 & r5
            r5 = 0
            int r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r0 == 0) goto L45
            int r10 = r7.end
            if (r2 != r10) goto L3e
            goto L1f
        L3e:
            int r10 = r2 + 1
            r0 = r8[r2]
            r2 = r10
            r10 = r0
            goto L26
        L45:
            int r8 = r7.offset
            int r8 = r8 + 3
            if (r2 != r8) goto L66
            if (r10 == r9) goto L66
            r8 = 40
            if (r10 == r8) goto L66
            r8 = 91
            if (r10 == r8) goto L66
            r8 = 93
            if (r10 == r8) goto L66
            r8 = 41
            if (r10 == r8) goto L66
            r8 = 58
            if (r10 == r8) goto L66
            r8 = 44
            if (r10 == r8) goto L66
            return r1
        L66:
            r7.offset = r2
            char r8 = (char) r10
            r7.ch = r8
            r8 = 1
            return r8
        L6d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF8.nextIfMatchIdent(char, char, char):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0044 A[LOOP:0: B:17:0x002c->B:23:0x0044, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0025 A[SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x0041 -> B:15:0x0025). Please report as a decompilation issue!!! */
    @Override // com.alibaba.fastjson2.JSONReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean nextIfMatchIdent(char r8, char r9, char r10, char r11) {
        /*
            r7 = this;
            char r0 = r7.ch
            r1 = 0
            if (r0 == r8) goto L6
            return r1
        L6:
            byte[] r8 = r7.bytes
            int r0 = r7.offset
            int r2 = r0 + 3
            int r3 = r7.end
            if (r2 > r3) goto L73
            r4 = r8[r0]
            if (r4 != r9) goto L73
            int r9 = r0 + 1
            r9 = r8[r9]
            if (r9 != r10) goto L73
            int r9 = r0 + 2
            r9 = r8[r9]
            if (r9 == r11) goto L21
            goto L73
        L21:
            r9 = 26
            if (r2 != r3) goto L27
        L25:
            r10 = r9
            goto L2c
        L27:
            int r0 = r0 + 4
            r10 = r8[r2]
            r2 = r0
        L2c:
            r11 = 32
            if (r10 > r11) goto L4b
            r3 = 1
            long r3 = r3 << r10
            r5 = 4294981376(0x100003700, double:2.1220027474E-314)
            long r3 = r3 & r5
            r5 = 0
            int r11 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r11 == 0) goto L4b
            int r10 = r7.end
            if (r2 != r10) goto L44
            goto L25
        L44:
            int r10 = r2 + 1
            r11 = r8[r2]
            r2 = r10
            r10 = r11
            goto L2c
        L4b:
            int r8 = r7.offset
            int r8 = r8 + 4
            if (r2 != r8) goto L6c
            if (r10 == r9) goto L6c
            r8 = 40
            if (r10 == r8) goto L6c
            r8 = 91
            if (r10 == r8) goto L6c
            r8 = 93
            if (r10 == r8) goto L6c
            r8 = 41
            if (r10 == r8) goto L6c
            r8 = 58
            if (r10 == r8) goto L6c
            r8 = 44
            if (r10 == r8) goto L6c
            return r1
        L6c:
            r7.offset = r2
            char r8 = (char) r10
            r7.ch = r8
            r8 = 1
            return r8
        L73:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF8.nextIfMatchIdent(char, char, char, char):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x004a A[LOOP:0: B:19:0x0032->B:25:0x004a, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x002b A[SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x0047 -> B:17:0x002b). Please report as a decompilation issue!!! */
    @Override // com.alibaba.fastjson2.JSONReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean nextIfMatchIdent(char r6, char r7, char r8, char r9, char r10) {
        /*
            r5 = this;
            char r0 = r5.ch
            r1 = 0
            if (r0 == r6) goto L6
            return r1
        L6:
            byte[] r6 = r5.bytes
            int r0 = r5.offset
            int r2 = r0 + 4
            int r3 = r5.end
            if (r2 > r3) goto L79
            r4 = r6[r0]
            if (r4 != r7) goto L79
            int r7 = r0 + 1
            r7 = r6[r7]
            if (r7 != r8) goto L79
            int r7 = r0 + 2
            r7 = r6[r7]
            if (r7 != r9) goto L79
            int r7 = r0 + 3
            r7 = r6[r7]
            if (r7 == r10) goto L27
            goto L79
        L27:
            r7 = 26
            if (r2 != r3) goto L2d
        L2b:
            r8 = r7
            goto L32
        L2d:
            int r0 = r0 + 5
            r8 = r6[r2]
            r2 = r0
        L32:
            r9 = 32
            if (r8 > r9) goto L51
            r9 = 1
            long r9 = r9 << r8
            r3 = 4294981376(0x100003700, double:2.1220027474E-314)
            long r9 = r9 & r3
            r3 = 0
            int r9 = (r9 > r3 ? 1 : (r9 == r3 ? 0 : -1))
            if (r9 == 0) goto L51
            int r8 = r5.end
            if (r2 != r8) goto L4a
            goto L2b
        L4a:
            int r8 = r2 + 1
            r9 = r6[r2]
            r2 = r8
            r8 = r9
            goto L32
        L51:
            int r6 = r5.offset
            int r6 = r6 + 5
            if (r2 != r6) goto L72
            if (r8 == r7) goto L72
            r6 = 40
            if (r8 == r6) goto L72
            r6 = 91
            if (r8 == r6) goto L72
            r6 = 93
            if (r8 == r6) goto L72
            r6 = 41
            if (r8 == r6) goto L72
            r6 = 58
            if (r8 == r6) goto L72
            r6 = 44
            if (r8 == r6) goto L72
            return r1
        L72:
            r5.offset = r2
            char r6 = (char) r8
            r5.ch = r6
            r6 = 1
            return r6
        L79:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF8.nextIfMatchIdent(char, char, char, char, char):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0050 A[LOOP:0: B:21:0x0038->B:27:0x0050, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0031 A[SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x004d -> B:19:0x0031). Please report as a decompilation issue!!! */
    @Override // com.alibaba.fastjson2.JSONReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean nextIfMatchIdent(char r6, char r7, char r8, char r9, char r10, char r11) {
        /*
            r5 = this;
            char r0 = r5.ch
            r1 = 0
            if (r0 == r6) goto L6
            return r1
        L6:
            byte[] r6 = r5.bytes
            int r0 = r5.offset
            int r2 = r0 + 5
            int r3 = r5.end
            if (r2 > r3) goto L7f
            r4 = r6[r0]
            if (r4 != r7) goto L7f
            int r7 = r0 + 1
            r7 = r6[r7]
            if (r7 != r8) goto L7f
            int r7 = r0 + 2
            r7 = r6[r7]
            if (r7 != r9) goto L7f
            int r7 = r0 + 3
            r7 = r6[r7]
            if (r7 != r10) goto L7f
            int r7 = r0 + 4
            r7 = r6[r7]
            if (r7 == r11) goto L2d
            goto L7f
        L2d:
            r7 = 26
            if (r2 != r3) goto L33
        L31:
            r8 = r7
            goto L38
        L33:
            int r0 = r0 + 6
            r8 = r6[r2]
            r2 = r0
        L38:
            r9 = 32
            if (r8 > r9) goto L57
            r9 = 1
            long r9 = r9 << r8
            r3 = 4294981376(0x100003700, double:2.1220027474E-314)
            long r9 = r9 & r3
            r3 = 0
            int r9 = (r9 > r3 ? 1 : (r9 == r3 ? 0 : -1))
            if (r9 == 0) goto L57
            int r8 = r5.end
            if (r2 != r8) goto L50
            goto L31
        L50:
            int r8 = r2 + 1
            r9 = r6[r2]
            r2 = r8
            r8 = r9
            goto L38
        L57:
            int r6 = r5.offset
            int r6 = r6 + 6
            if (r2 != r6) goto L78
            if (r8 == r7) goto L78
            r6 = 40
            if (r8 == r6) goto L78
            r6 = 91
            if (r8 == r6) goto L78
            r6 = 93
            if (r8 == r6) goto L78
            r6 = 41
            if (r8 == r6) goto L78
            r6 = 58
            if (r8 == r6) goto L78
            r6 = 44
            if (r8 == r6) goto L78
            return r1
        L78:
            r5.offset = r2
            char r6 = (char) r8
            r5.ch = r6
            r6 = 1
            return r6
        L7f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF8.nextIfMatchIdent(char, char, char, char, char, char):boolean");
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX WARN: Removed duplicated region for block: B:21:0x003c A[ADDED_TO_REGION] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x0048 -> B:17:0x0030). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:72:0x00e7 -> B:62:0x00c4). Please report as a decompilation issue!!! */
    @Override // com.alibaba.fastjson2.JSONReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final byte[] readHex() {
        /*
            Method dump skipped, instructions count: 260
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF8.readHex():byte[]");
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean isReference() {
        int i;
        int i2;
        if ((this.context.features & 8589934592L) != 0) {
            return false;
        }
        byte[] bArr = this.bytes;
        if (this.ch != '{' || (i = this.offset) == (i2 = this.end)) {
            return false;
        }
        byte b = bArr[i];
        while (b <= 32 && ((1 << b) & 4294981376L) != 0) {
            i++;
            if (i >= i2) {
                return false;
            }
            b = bArr[i];
        }
        if (i + 6 < i2 && bArr[i + 5] == b && JDKUtils.UNSAFE.getInt(bArr, JDKUtils.ARRAY_BYTE_BASE_OFFSET + i + 1) == REF) {
            return isReference0(bArr, i, i2, b);
        }
        return false;
    }

    private boolean isReference0(byte[] bArr, int i, int i2, int i3) {
        int i4;
        int i5;
        byte b;
        int i6 = i + 6;
        byte b2 = bArr[i6];
        while (b2 >= 0 && b2 <= 32 && ((1 << b2) & 4294981376L) != 0) {
            i6++;
            if (i6 >= i2) {
                return false;
            }
            b2 = bArr[i6];
        }
        if (b2 == 58 && (i4 = i6 + 1) < i2) {
            int i7 = bArr[i4];
            while (i7 >= 0 && i7 <= 32 && ((1 << i7) & 4294981376L) != 0) {
                i4++;
                if (i4 >= i2) {
                    return false;
                }
                i7 = bArr[i4];
            }
            if (i7 == i3 && ((i5 = i4 + 1) >= i2 || (b = bArr[i5]) == 36 || b == 46 || b == 64)) {
                this.referenceBegin = i4;
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0060 A[LOOP:1: B:27:0x0052->B:32:0x0060, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x004c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x006e  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x005d -> B:20:0x004c). Please report as a decompilation issue!!! */
    @Override // com.alibaba.fastjson2.JSONReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String readReference() {
        /*
            r15 = this;
            int r0 = r15.referenceBegin
            int r1 = r15.end
            if (r0 != r1) goto L8
            r0 = 0
            return r0
        L8:
            byte[] r1 = r15.bytes
            r15.offset = r0
            int r0 = r15.offset
            int r2 = r0 + 1
            r15.offset = r2
            r0 = r1[r0]
            char r0 = (char) r0
            r15.ch = r0
            java.lang.String r0 = r15.readString()
            char r2 = r15.ch
            int r3 = r15.offset
        L1f:
            r4 = 0
            r6 = 4294981376(0x100003700, double:2.1220027474E-314)
            r8 = 1
            r10 = 32
            r11 = 26
            if (r2 > r10) goto L43
            long r12 = r8 << r2
            long r12 = r12 & r6
            int r12 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1))
            if (r12 == 0) goto L43
            int r2 = r15.end
            if (r3 != r2) goto L3b
            r2 = r11
            goto L1f
        L3b:
            int r2 = r3 + 1
            r3 = r1[r3]
            r14 = r3
            r3 = r2
            r2 = r14
            goto L1f
        L43:
            r12 = 125(0x7d, float:1.75E-43)
            if (r2 != r12) goto L9b
            int r2 = r15.end
            if (r3 != r2) goto L4e
            r2 = r3
        L4c:
            r3 = r11
            goto L52
        L4e:
            int r2 = r3 + 1
            r3 = r1[r3]
        L52:
            if (r3 > r10) goto L68
            long r12 = r8 << r3
            long r12 = r12 & r6
            int r12 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1))
            if (r12 == 0) goto L68
            int r3 = r15.end
            if (r2 != r3) goto L60
            goto L4c
        L60:
            int r3 = r2 + 1
            r2 = r1[r2]
            r14 = r3
            r3 = r2
            r2 = r14
            goto L52
        L68:
            r12 = 44
            if (r3 != r12) goto L6e
            r12 = 1
            goto L6f
        L6e:
            r12 = 0
        L6f:
            r15.comma = r12
            if (r12 == 0) goto L95
            int r3 = r15.end
            if (r2 != r3) goto L7a
            r3 = r2
            r2 = r11
            goto L7e
        L7a:
            int r3 = r2 + 1
            r2 = r1[r2]
        L7e:
            r14 = r3
            r3 = r2
            r2 = r14
        L81:
            if (r3 > r10) goto L95
            long r12 = r8 << r3
            long r12 = r12 & r6
            int r12 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1))
            if (r12 == 0) goto L95
            int r3 = r15.end
            if (r2 != r3) goto L90
            r3 = r11
            goto L81
        L90:
            int r3 = r2 + 1
            r2 = r1[r2]
            goto L7e
        L95:
            char r1 = (char) r3
            r15.ch = r1
            r15.offset = r2
            return r0
        L9b:
            com.alibaba.fastjson2.JSONException r1 = new com.alibaba.fastjson2.JSONException
            java.lang.String r2 = "illegal reference : "
            java.lang.String r0 = r2.concat(r0)
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF8.readReference():java.lang.String");
    }

    /* JADX WARN: Code restructure failed: missing block: B:68:0x004f, code lost:
    
        if (r4 == '1') goto L13;
     */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0075 A[LOOP:0: B:17:0x005d->B:22:0x0075, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0055 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0058  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x0072 -> B:15:0x0055). Please report as a decompilation issue!!! */
    @Override // com.alibaba.fastjson2.JSONReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean readBoolValue() {
        /*
            r19 = this;
            r0 = r19
            int r1 = r0.end
            byte[] r2 = r0.bytes
            int r3 = r0.offset
            char r4 = r0.ch
            r5 = 116(0x74, float:1.63E-43)
            r6 = 1
            r7 = 0
            if (r4 != r5) goto L2d
            int r5 = r3 + 2
            int r8 = r2.length
            if (r5 >= r8) goto L2d
            r8 = r2[r3]
            r9 = 114(0x72, float:1.6E-43)
            if (r8 != r9) goto L2d
            int r8 = r3 + 1
            r8 = r2[r8]
            r9 = 117(0x75, float:1.64E-43)
            if (r8 != r9) goto L2d
            r5 = r2[r5]
            r8 = 101(0x65, float:1.42E-43)
            if (r5 != r8) goto L2d
            int r3 = r3 + 3
        L2b:
            r4 = r6
            goto L52
        L2d:
            r5 = 102(0x66, float:1.43E-43)
            if (r4 != r5) goto L3f
            int r5 = r3 + 3
            if (r5 >= r1) goto L3f
            boolean r5 = com.alibaba.fastjson2.util.IOUtils.isALSE(r2, r3)
            if (r5 == 0) goto L3f
            int r3 = r3 + 4
        L3d:
            r4 = r7
            goto L52
        L3f:
            r5 = 49
            if (r4 == r5) goto L47
            r8 = 48
            if (r4 != r8) goto Lae
        L47:
            if (r3 >= r1) goto Lae
            boolean r8 = com.alibaba.fastjson2.util.IOUtils.isDigit(r4)
            if (r8 != 0) goto Lae
            if (r4 != r5) goto L3d
            goto L2b
        L52:
            if (r3 != r1) goto L58
            r8 = r3
        L55:
            r3 = 26
            goto L5d
        L58:
            int r8 = r3 + 1
            r3 = r2[r3]
            char r3 = (char) r3
        L5d:
            r9 = 0
            r11 = 4294981376(0x100003700, double:2.1220027474E-314)
            r13 = 1
            r15 = 32
            if (r3 > r15) goto L7f
            long r16 = r13 << r3
            long r16 = r16 & r11
            int r16 = (r16 > r9 ? 1 : (r16 == r9 ? 0 : -1))
            if (r16 == 0) goto L7f
            if (r8 < r1) goto L75
            goto L55
        L75:
            int r3 = r8 + 1
            r8 = r2[r8]
            r18 = r8
            r8 = r3
            r3 = r18
            goto L5d
        L7f:
            r5 = 44
            if (r3 != r5) goto L84
            goto L85
        L84:
            r6 = r7
        L85:
            r0.comma = r6
            if (r6 == 0) goto La8
            if (r8 < r1) goto L8e
            r5 = 26
            goto L93
        L8e:
            int r3 = r8 + 1
            r5 = r2[r8]
            goto La6
        L93:
            r3 = r5
        L94:
            if (r3 > r15) goto La8
            long r5 = r13 << r3
            long r5 = r5 & r11
            int r5 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            if (r5 == 0) goto La8
            if (r8 < r1) goto La2
            r3 = 26
            goto L94
        La2:
            int r3 = r8 + 1
            r5 = r2[r8]
        La6:
            r8 = r3
            goto L93
        La8:
            r0.offset = r8
            char r1 = (char) r3
            r0.ch = r1
            return r4
        Lae:
            boolean r1 = r0.readBoolValue0()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF8.readBoolValue():boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x012b A[LOOP:0: B:18:0x0114->B:24:0x012b, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x010d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x013e  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0157  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0142 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x010f  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x0128 -> B:16:0x010d). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:36:0x0154 -> B:31:0x0142). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean readBoolValue0() {
        /*
            Method dump skipped, instructions count: 470
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF8.readBoolValue0():boolean");
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final byte[] readBase64() {
        byte[] bArr;
        int i;
        byte b;
        int i2;
        byte b2;
        byte[] bArr2 = this.bytes;
        int i3 = this.offset;
        int i4 = this.end;
        int indexOfQuote = IOUtils.indexOfQuote(bArr2, this.ch, i3, i4);
        if (indexOfQuote == -1) {
            throw error("invalid escape character EOI");
        }
        if (indexOfSlash(this, bArr2, i3, i4) != -1) {
            throw error("invalid base64 string");
        }
        if (indexOfQuote != i3) {
            int i5 = 0;
            while (true) {
                if (i5 < "data:image/jpeg;base64,".length()) {
                    if (bArr2[i3 + i5] != "data:image/jpeg;base64,".charAt(i5)) {
                        break;
                    }
                    i5++;
                } else {
                    i3 += "data:image/jpeg;base64,".length();
                    break;
                }
            }
            bArr = Base64.getDecoder().decode(Arrays.copyOfRange(bArr2, i3, indexOfQuote));
        } else {
            bArr = new byte[0];
        }
        int i6 = indexOfQuote + 1;
        if (i6 == i4) {
            i = i6;
            b = 26;
        } else {
            i = indexOfQuote + 2;
            b = (char) bArr2[i6];
        }
        boolean z = b == 44;
        this.comma = z;
        if (z) {
            if (i == i4) {
                i2 = i;
                b2 = 26;
            } else {
                i2 = i + 1;
                b2 = bArr2[i];
            }
            loop1: while (true) {
                b = b2;
                i = i2;
                while (b <= 32 && ((1 << b) & 4294981376L) != 0) {
                    if (i == i4) {
                        b = 26;
                    }
                }
                i2 = i + 1;
                b2 = bArr2[i];
            }
        }
        this.ch = (char) b;
        this.offset = i;
        return bArr;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final String info(String str) {
        int i = 1;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i2 >= this.offset || i2 >= this.end) {
                break;
            }
            if (this.bytes[i2] == 10) {
                i++;
                i3 = 0;
            }
            i2++;
            i3++;
        }
        StringBuilder sb = new StringBuilder();
        if (str != null && !str.isEmpty()) {
            sb.append(str).append(", ");
        }
        return sb.append("offset ").append(this.offset).append(", character ").append(this.ch).append(", line ").append(i).append(", column ").append(i3).append(", fastjson-version 2.0.58").append(i <= 1 ? ' ' : '\n').append(new String(this.bytes, this.start, Math.min(this.length, 65535))).toString();
    }

    @Override // com.alibaba.fastjson2.JSONReader, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.cacheItem != null) {
            if (this.bytes.length < 8388608) {
                JSONFactory.BYTES_UPDATER.lazySet(this.cacheItem, this.bytes);
            }
            char[] cArr = this.charBuf;
            if (cArr != null && cArr.length < 8388608) {
                JSONFactory.CHARS_UPDATER.lazySet(this.cacheItem, this.charBuf);
            }
        }
        InputStream inputStream = this.in;
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException unused) {
            }
        }
    }

    public static JSONReaderUTF8 of(byte[] bArr, int i, int i2, JSONReader.Context context) {
        boolean z;
        if (JDKUtils.METHOD_HANDLE_HAS_NEGATIVE != null) {
            try {
                z = !(boolean) JDKUtils.METHOD_HANDLE_HAS_NEGATIVE.invoke(bArr, i, i2);
            } catch (Throwable unused) {
                z = false;
            }
        } else {
            z = IOUtils.isASCII(bArr, i, i2);
        }
        if (z) {
            return new JSONReaderASCII(context, null, bArr, i, i2);
        }
        return new JSONReaderUTF8(context, bArr, i, i2);
    }
}
