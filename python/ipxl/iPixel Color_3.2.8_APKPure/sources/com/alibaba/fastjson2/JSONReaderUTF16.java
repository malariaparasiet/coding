package com.alibaba.fastjson2;

import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson2.JSONB;
import com.alibaba.fastjson2.JSONFactory;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.util.DateUtils;
import com.alibaba.fastjson2.util.Fnv;
import com.alibaba.fastjson2.util.IOUtils;
import com.alibaba.fastjson2.util.JDKUtils;
import com.alibaba.fastjson2.util.TypeUtils;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Arrays;
import kotlin.UByte;
import kotlin.text.Typography;
import okhttp3.internal.ws.WebSocketProtocol;

/* loaded from: classes2.dex */
final class JSONReaderUTF16 extends JSONReader {
    static final long CHAR_MASK;
    private int cacheIndex;
    protected final char[] chars;
    protected final int end;
    private Closeable input;
    protected final int length;
    private int nameBegin;
    private int nameEnd;
    private int nameLength;
    private int referenceBegin;
    protected final int start;
    protected final String str;

    static {
        CHAR_MASK = JDKUtils.BIG_ENDIAN ? 71777214294589695L : -71777214294589696L;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    JSONReaderUTF16(JSONReader.Context context, byte[] bArr, int i, int i2) {
        super(context, false, false);
        int i3 = 0;
        this.cacheIndex = -1;
        this.str = null;
        this.chars = new char[i2 / 2];
        int i4 = i + i2;
        int i5 = i;
        while (i5 < i4) {
            this.chars[i3] = (char) (((bArr[i5] & UByte.MAX_VALUE) << 8) | (bArr[i5 + 1] & UByte.MAX_VALUE));
            i5 += 2;
            i3++;
        }
        this.start = i;
        this.length = i3;
        this.end = i3;
        if (this.offset >= i3) {
            this.ch = JSONLexer.EOI;
            return;
        }
        this.ch = this.chars[this.offset];
        while (this.ch <= ' ' && ((1 << this.ch) & 4294981376L) != 0) {
            this.offset++;
            if (this.offset >= i2) {
                this.ch = JSONLexer.EOI;
                return;
            }
            this.ch = this.chars[this.offset];
        }
        while (this.ch <= ' ' && ((1 << this.ch) & 4294981376L) != 0) {
            this.offset++;
            if (this.offset >= i2) {
                this.ch = JSONLexer.EOI;
                return;
            }
            this.ch = this.chars[this.offset];
        }
        this.offset++;
        if (this.ch == 65534 || this.ch == 65279) {
            next();
        }
        if (this.ch == '/') {
            skipComment();
        }
    }

    JSONReaderUTF16(JSONReader.Context context, Reader reader) {
        super(context, false, false);
        this.cacheIndex = -1;
        this.input = reader;
        this.cacheIndex = System.identityHashCode(Thread.currentThread()) & (JSONFactory.CACHE_ITEMS.length - 1);
        char[] andSet = JSONFactory.CHARS_UPDATER.getAndSet(JSONFactory.CACHE_ITEMS[this.cacheIndex], null);
        andSet = andSet == null ? new char[8192] : andSet;
        int i = 0;
        while (true) {
            try {
                int read = reader.read(andSet, i, andSet.length - i);
                if (read == -1) {
                    break;
                }
                i += read;
                if (i == andSet.length) {
                    int length = andSet.length;
                    andSet = Arrays.copyOf(andSet, length + (length >> 1));
                }
            } catch (IOException e) {
                throw new JSONException("read error", e);
            }
        }
        this.str = null;
        this.chars = andSet;
        this.offset = 0;
        this.length = i;
        this.start = 0;
        this.end = i;
        if (this.offset >= i) {
            this.ch = JSONLexer.EOI;
            return;
        }
        this.ch = andSet[this.offset];
        while (this.ch <= ' ' && ((1 << this.ch) & 4294981376L) != 0) {
            this.offset++;
            if (this.offset >= this.length) {
                this.ch = JSONLexer.EOI;
                return;
            }
            this.ch = andSet[this.offset];
        }
        this.offset++;
        if (this.ch == 65534 || this.ch == 65279) {
            next();
        }
        if (this.ch == '/') {
            skipComment();
        }
    }

    JSONReaderUTF16(JSONReader.Context context, String str, int i, int i2) {
        super(context, false, false);
        this.cacheIndex = -1;
        this.cacheIndex = System.identityHashCode(Thread.currentThread()) & (JSONFactory.CACHE_ITEMS.length - 1);
        char[] andSet = JSONFactory.CHARS_UPDATER.getAndSet(JSONFactory.CACHE_ITEMS[this.cacheIndex], null);
        andSet = (andSet == null || andSet.length < i2) ? new char[Math.max(i2, 8192)] : andSet;
        str.getChars(i, i + i2, andSet, 0);
        this.str = i != 0 ? null : str;
        this.chars = andSet;
        this.offset = 0;
        this.length = i2;
        this.start = 0;
        this.end = i2;
        if (this.offset >= i2) {
            this.ch = JSONLexer.EOI;
            return;
        }
        this.ch = andSet[this.offset];
        while (this.ch <= ' ' && ((1 << this.ch) & 4294981376L) != 0) {
            this.offset++;
            if (this.offset >= this.length) {
                this.ch = JSONLexer.EOI;
                return;
            }
            this.ch = andSet[this.offset];
        }
        this.offset++;
        if (this.ch == 65534 || this.ch == 65279) {
            next();
        }
        if (this.ch == '/') {
            skipComment();
        }
    }

    JSONReaderUTF16(JSONReader.Context context, String str, char[] cArr, int i, int i2) {
        super(context, false, false);
        this.cacheIndex = -1;
        this.str = str;
        this.chars = cArr;
        this.offset = i;
        this.length = i2;
        this.start = i;
        int i3 = i + i2;
        this.end = i3;
        if (this.offset >= i3) {
            this.ch = JSONLexer.EOI;
            return;
        }
        this.ch = cArr[this.offset];
        while (this.ch <= ' ' && ((1 << this.ch) & 4294981376L) != 0) {
            this.offset++;
            if (this.offset >= i2) {
                this.ch = JSONLexer.EOI;
                return;
            }
            this.ch = cArr[this.offset];
        }
        this.offset++;
        if (this.ch == 65534 || this.ch == 65279) {
            next();
        }
        if (this.ch == '/') {
            skipComment();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    JSONReaderUTF16(JSONReader.Context context, InputStream inputStream) {
        super(context, false, false);
        int i = 0;
        this.cacheIndex = -1;
        this.input = inputStream;
        JSONFactory.CacheItem cacheItem = JSONFactory.CACHE_ITEMS[System.identityHashCode(Thread.currentThread()) & (JSONFactory.CACHE_ITEMS.length - 1)];
        byte[] andSet = JSONFactory.BYTES_UPDATER.getAndSet(cacheItem, null);
        int i2 = context.bufferSize;
        andSet = andSet == null ? new byte[i2] : andSet;
        int i3 = 0;
        while (true) {
            try {
                try {
                    int read = inputStream.read(andSet, i3, andSet.length - i3);
                    if (read == -1) {
                        break;
                    }
                    i3 += read;
                    if (i3 == andSet.length) {
                        andSet = Arrays.copyOf(andSet, andSet.length + i2);
                    }
                } catch (IOException e) {
                    throw new JSONException("read error", e);
                }
            } catch (Throwable th) {
                JSONFactory.BYTES_UPDATER.lazySet(cacheItem, andSet);
                throw th;
            }
        }
        if (i3 % 2 == 1) {
            throw new JSONException("illegal input utf16 bytes, length " + i3);
        }
        int i4 = i3 / 2;
        char[] cArr = new char[i4];
        int i5 = 0;
        int i6 = 0;
        while (i5 < i3) {
            cArr[i6] = (char) (((andSet[i5] & UByte.MAX_VALUE) << 8) | (andSet[i5 + 1] & UByte.MAX_VALUE));
            i5 += 2;
            i6++;
        }
        JSONFactory.BYTES_UPDATER.lazySet(cacheItem, andSet);
        this.str = null;
        this.chars = cArr;
        this.offset = 0;
        this.length = i4;
        this.start = 0;
        this.end = i4;
        if (i4 == 0) {
            this.ch = JSONLexer.EOI;
            return;
        }
        char c = cArr[0];
        while (c <= ' ' && ((1 << c) & 4294981376L) != 0) {
            i++;
            if (i >= i4) {
                this.ch = JSONLexer.EOI;
                return;
            }
            c = cArr[i];
        }
        this.ch = c;
        this.offset++;
        if (c == 65534 || c == 65279) {
            next();
        }
        if (this.ch == '/') {
            skipComment();
        }
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX WARN: Removed duplicated region for block: B:21:0x003c A[ADDED_TO_REGION] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x0048 -> B:17:0x0030). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:72:0x00e6 -> B:62:0x00c4). Please report as a decompilation issue!!! */
    @Override // com.alibaba.fastjson2.JSONReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final byte[] readHex() {
        /*
            Method dump skipped, instructions count: 258
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF16.readHex():byte[]");
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean isReference() {
        int i;
        int i2;
        if ((this.context.features & 8589934592L) != 0) {
            return false;
        }
        char[] cArr = this.chars;
        if (this.ch != '{' || (i = this.offset) == (i2 = this.end)) {
            return false;
        }
        char c = cArr[i];
        while (c <= ' ' && ((1 << c) & 4294981376L) != 0) {
            i++;
            if (i >= i2) {
                return false;
            }
            c = cArr[i];
        }
        if (i + 6 < i2 && cArr[i + 1] == '$' && cArr[i + 2] == 'r' && cArr[i + 3] == 'e' && cArr[i + 4] == 'f' && cArr[i + 5] == c) {
            return isReference0(cArr, i, i2, c);
        }
        return false;
    }

    private boolean isReference0(char[] cArr, int i, int i2, char c) {
        int i3;
        int i4;
        char c2;
        int i5 = i + 6;
        char c3 = cArr[i5];
        while (c3 <= ' ' && ((1 << c3) & 4294981376L) != 0) {
            i5++;
            if (i5 >= i2) {
                return false;
            }
            c3 = cArr[i5];
        }
        if (c3 == ':' && (i3 = i5 + 1) < i2) {
            char c4 = cArr[i3];
            while (c4 <= ' ' && ((1 << c4) & 4294981376L) != 0) {
                i3++;
                if (i3 >= i2) {
                    return false;
                }
                c4 = cArr[i3];
            }
            if (c4 == c && ((i4 = i3 + 1) >= i2 || (c2 = cArr[i4]) == '$' || c2 == '.' || c2 == '@')) {
                this.referenceBegin = i3;
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x005f A[LOOP:1: B:27:0x0051->B:32:0x005f, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x004b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x006d  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x005c -> B:20:0x004b). Please report as a decompilation issue!!! */
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
            char[] r1 = r15.chars
            r15.offset = r0
            int r0 = r15.offset
            int r2 = r0 + 1
            r15.offset = r2
            char r0 = r1[r0]
            r15.ch = r0
            java.lang.String r0 = r15.readString()
            char r2 = r15.ch
            int r3 = r15.offset
        L1e:
            r4 = 0
            r6 = 4294981376(0x100003700, double:2.1220027474E-314)
            r8 = 1
            r10 = 32
            r11 = 26
            if (r2 > r10) goto L42
            long r12 = r8 << r2
            long r12 = r12 & r6
            int r12 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1))
            if (r12 == 0) goto L42
            int r2 = r15.end
            if (r3 != r2) goto L3a
            r2 = r11
            goto L1e
        L3a:
            int r2 = r3 + 1
            char r3 = r1[r3]
            r14 = r3
            r3 = r2
            r2 = r14
            goto L1e
        L42:
            r12 = 125(0x7d, float:1.75E-43)
            if (r2 != r12) goto L99
            int r2 = r15.end
            if (r3 != r2) goto L4d
            r2 = r3
        L4b:
            r3 = r11
            goto L51
        L4d:
            int r2 = r3 + 1
            char r3 = r1[r3]
        L51:
            if (r3 > r10) goto L67
            long r12 = r8 << r3
            long r12 = r12 & r6
            int r12 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1))
            if (r12 == 0) goto L67
            int r3 = r15.end
            if (r2 != r3) goto L5f
            goto L4b
        L5f:
            int r3 = r2 + 1
            char r2 = r1[r2]
            r14 = r3
            r3 = r2
            r2 = r14
            goto L51
        L67:
            r12 = 44
            if (r3 != r12) goto L6d
            r12 = 1
            goto L6e
        L6d:
            r12 = 0
        L6e:
            r15.comma = r12
            if (r12 == 0) goto L94
            int r3 = r15.end
            if (r2 != r3) goto L79
            r3 = r2
            r2 = r11
            goto L7d
        L79:
            int r3 = r2 + 1
            char r2 = r1[r2]
        L7d:
            r14 = r3
            r3 = r2
            r2 = r14
        L80:
            if (r3 > r10) goto L94
            long r12 = r8 << r3
            long r12 = r12 & r6
            int r12 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1))
            if (r12 == 0) goto L94
            int r3 = r15.end
            if (r2 != r3) goto L8f
            r3 = r11
            goto L80
        L8f:
            int r3 = r2 + 1
            char r2 = r1[r2]
            goto L7d
        L94:
            r15.ch = r3
            r15.offset = r2
            return r0
        L99:
            com.alibaba.fastjson2.JSONException r1 = new com.alibaba.fastjson2.JSONException
            java.lang.String r2 = "illegal reference : "
            java.lang.String r0 = r2.concat(r0)
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF16.readReference():java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:33:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0057 A[LOOP:1: B:24:0x0039->B:37:0x0057, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0033 A[EDGE_INSN: B:38:0x0033->B:22:0x0033 BREAK  A[LOOP:1: B:24:0x0039->B:37:0x0057], SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:28:0x0054 -> B:17:0x0033). Please report as a decompilation issue!!! */
    @Override // com.alibaba.fastjson2.JSONReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean nextIfMatch(char r15) {
        /*
            r14 = this;
            char[] r0 = r14.chars
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
            char r1 = r0[r1]
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
            char r1 = r0[r1]
        L39:
            if (r1 == 0) goto L52
            if (r1 > r9) goto L45
            long r11 = r7 << r1
            long r11 = r11 & r5
            int r2 = (r11 > r3 ? 1 : (r11 == r3 ? 0 : -1))
            if (r2 == 0) goto L45
            goto L52
        L45:
            r14.offset = r15
            r14.ch = r1
            r15 = 47
            if (r1 != r15) goto L50
            r14.skipComment()
        L50:
            r15 = 1
            return r15
        L52:
            int r1 = r14.end
            if (r15 != r1) goto L57
            goto L33
        L57:
            int r1 = r15 + 1
            char r15 = r0[r15]
            r13 = r1
            r1 = r15
            r15 = r13
            goto L39
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF16.nextIfMatch(char):boolean");
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0041 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x005d A[LOOP:1: B:26:0x003f->B:39:0x005d, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0039 A[EDGE_INSN: B:40:0x0039->B:24:0x0039 BREAK  A[LOOP:1: B:26:0x003f->B:39:0x005d], SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:29:0x005a -> B:18:0x0039). Please report as a decompilation issue!!! */
    @Override // com.alibaba.fastjson2.JSONReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean nextIfComma() {
        /*
            r14 = this;
            char[] r0 = r14.chars
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
            char r1 = r0[r1]
            r13 = r2
            r2 = r1
            r1 = r13
            goto L6
        L2a:
            r11 = 44
            if (r2 == r11) goto L34
            r14.offset = r1
            r14.ch = r2
            r0 = 0
            return r0
        L34:
            int r2 = r14.end
            if (r1 != r2) goto L3b
            r2 = r1
        L39:
            r1 = r10
            goto L3f
        L3b:
            int r2 = r1 + 1
            char r1 = r0[r1]
        L3f:
            if (r1 == 0) goto L58
            if (r1 > r9) goto L4b
            long r11 = r7 << r1
            long r11 = r11 & r5
            int r11 = (r11 > r3 ? 1 : (r11 == r3 ? 0 : -1))
            if (r11 == 0) goto L4b
            goto L58
        L4b:
            r14.offset = r2
            r14.ch = r1
            r0 = 47
            if (r1 != r0) goto L56
            r14.skipComment()
        L56:
            r0 = 1
            return r0
        L58:
            int r1 = r14.end
            if (r2 != r1) goto L5d
            goto L39
        L5d:
            int r1 = r2 + 1
            char r2 = r0[r2]
            r13 = r2
            r2 = r1
            r1 = r13
            goto L3f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF16.nextIfComma():boolean");
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX WARN: Removed duplicated region for block: B:11:0x001b  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0041 A[LOOP:0: B:10:0x0019->B:24:0x0041, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0013 A[EDGE_INSN: B:25:0x0013->B:8:0x0013 BREAK  A[LOOP:0: B:10:0x0019->B:24:0x0041], SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x003e -> B:9:0x0013). Please report as a decompilation issue!!! */
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
            char[] r0 = r9.chars
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
            char r1 = r0[r1]
        L19:
            if (r1 == 0) goto L3c
            r4 = 32
            if (r1 > r4) goto L2f
            r4 = 1
            long r4 = r4 << r1
            r6 = 4294981376(0x100003700, double:2.1220027474E-314)
            long r4 = r4 & r6
            r6 = 0
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 == 0) goto L2f
            goto L3c
        L2f:
            r9.ch = r1
            r9.offset = r2
            r0 = 47
            if (r1 != r0) goto L3a
            r9.skipComment()
        L3a:
            r0 = 1
            return r0
        L3c:
            int r1 = r9.end
            if (r2 != r1) goto L41
            goto L13
        L41:
            int r1 = r2 + 1
            char r2 = r0[r2]
            r8 = r2
            r2 = r1
            r1 = r8
            goto L19
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF16.nextIfArrayStart():boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0070 A[LOOP:0: B:10:0x001b->B:42:0x0070, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0015 A[EDGE_INSN: B:43:0x0015->B:8:0x0015 BREAK  A[LOOP:0: B:10:0x001b->B:42:0x0070], SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:35:0x006d -> B:9:0x0015). Please report as a decompilation issue!!! */
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
            r2 = 93
            if (r1 == r2) goto La
            r1 = 0
            return r1
        La:
            int r1 = r0.offset
            char[] r2 = r0.chars
            int r3 = r0.end
            r4 = 26
            if (r1 != r3) goto L17
            r3 = r1
        L15:
            r1 = r4
            goto L1b
        L17:
            int r3 = r1 + 1
            char r1 = r2[r1]
        L1b:
            if (r1 == 0) goto L6b
            r5 = 0
            r7 = 4294981376(0x100003700, double:2.1220027474E-314)
            r9 = 1
            r11 = 32
            if (r1 > r11) goto L32
            long r12 = r9 << r1
            long r12 = r12 & r7
            int r12 = (r12 > r5 ? 1 : (r12 == r5 ? 0 : -1))
            if (r12 == 0) goto L32
            goto L6b
        L32:
            r12 = 44
            r13 = 1
            if (r1 != r12) goto L5f
            r0.comma = r13
            int r1 = r0.end
            if (r3 != r1) goto L40
            r1 = r3
            r3 = r4
            goto L44
        L40:
            int r1 = r3 + 1
            char r3 = r2[r3]
        L44:
            r16 = r3
            r3 = r1
            r1 = r16
        L49:
            if (r1 == 0) goto L54
            if (r1 > r11) goto L5f
            long r14 = r9 << r1
            long r14 = r14 & r7
            int r12 = (r14 > r5 ? 1 : (r14 == r5 ? 0 : -1))
            if (r12 == 0) goto L5f
        L54:
            int r1 = r0.end
            if (r3 != r1) goto L5a
            r1 = r4
            goto L49
        L5a:
            int r1 = r3 + 1
            char r3 = r2[r3]
            goto L44
        L5f:
            r0.ch = r1
            r0.offset = r3
            r2 = 47
            if (r1 != r2) goto L6a
            r0.skipComment()
        L6a:
            return r13
        L6b:
            int r1 = r0.end
            if (r3 != r1) goto L70
            goto L15
        L70:
            int r1 = r3 + 1
            char r3 = r2[r3]
            r16 = r3
            r3 = r1
            r1 = r16
            goto L1b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF16.nextIfArrayEnd():boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x006d A[LOOP:0: B:17:0x0056->B:22:0x006d, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0050 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0082 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0099 A[SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x006a -> B:15:0x0050). Please report as a decompilation issue!!! */
    @Override // com.alibaba.fastjson2.JSONReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean nextIfNullOrEmptyString() {
        /*
            r18 = this;
            r0 = r18
            char r1 = r0.ch
            int r2 = r0.end
            int r3 = r0.offset
            char[] r4 = r0.chars
            r5 = 110(0x6e, float:1.54E-43)
            r6 = 0
            r7 = 1
            if (r1 != r5) goto L29
            int r5 = r3 + 2
            if (r5 >= r2) goto L29
            char r8 = r4[r3]
            r9 = 117(0x75, float:1.64E-43)
            if (r8 != r9) goto L29
            int r8 = r3 + 1
            char r8 = r4[r8]
            r9 = 108(0x6c, float:1.51E-43)
            if (r8 != r9) goto L29
            char r5 = r4[r5]
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
            char r5 = r4[r3]
            if (r5 != r1) goto L3b
            int r3 = r3 + r7
            goto L4b
        L3b:
            int r5 = r3 + 4
            if (r5 >= r2) goto La3
            boolean r8 = com.alibaba.fastjson2.util.IOUtils.isNULL(r4, r3)
            if (r8 == 0) goto La3
            char r5 = r4[r5]
            if (r5 != r1) goto La3
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
            char r3 = r4[r3]
        L56:
            r8 = 0
            r10 = 4294981376(0x100003700, double:2.1220027474E-314)
            r12 = 1
            r14 = 32
            if (r3 > r14) goto L77
            long r15 = r12 << r3
            long r15 = r15 & r10
            int r15 = (r15 > r8 ? 1 : (r15 == r8 ? 0 : -1))
            if (r15 == 0) goto L77
            if (r5 != r2) goto L6d
            goto L50
        L6d:
            int r3 = r5 + 1
            char r5 = r4[r5]
            r17 = r5
            r5 = r3
            r3 = r17
            goto L56
        L77:
            r15 = 44
            if (r3 != r15) goto L7c
            r6 = r7
        L7c:
            r0.comma = r6
            if (r6 == 0) goto L8d
            if (r5 != r2) goto L84
        L82:
            r3 = r1
            goto L8d
        L84:
            int r3 = r5 + 1
            char r5 = r4[r5]
        L88:
            r17 = r5
            r5 = r3
            r3 = r17
        L8d:
            if (r3 > r14) goto L9e
            long r15 = r12 << r3
            long r15 = r15 & r10
            int r6 = (r15 > r8 ? 1 : (r15 == r8 ? 0 : -1))
            if (r6 == 0) goto L9e
            if (r5 != r2) goto L99
            goto L82
        L99:
            int r3 = r5 + 1
            char r5 = r4[r5]
            goto L88
        L9e:
            r0.offset = r5
            r0.ch = r3
            return r7
        La3:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF16.nextIfNullOrEmptyString():boolean");
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
            char[] r9 = r8.chars
            int r0 = r8.offset
            int r2 = r0 + 1
            int r3 = r8.end
            if (r2 > r3) goto L67
            char r4 = r9[r0]
            if (r4 == r10) goto L15
            goto L67
        L15:
            r10 = 26
            if (r2 != r3) goto L1c
            r0 = r2
        L1a:
            r2 = r10
            goto L20
        L1c:
            int r0 = r0 + 2
            char r2 = r9[r2]
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
            char r0 = r9[r0]
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
            r8.ch = r2
            r9 = 1
            return r9
        L67:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF16.nextIfMatchIdent(char, char):boolean");
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
            char[] r8 = r7.chars
            int r0 = r7.offset
            int r2 = r0 + 2
            int r3 = r7.end
            if (r2 > r3) goto L6c
            char r4 = r8[r0]
            if (r4 != r9) goto L6c
            int r9 = r0 + 1
            char r9 = r8[r9]
            if (r9 == r10) goto L1b
            goto L6c
        L1b:
            r9 = 26
            if (r2 != r3) goto L21
        L1f:
            r10 = r9
            goto L26
        L21:
            int r0 = r0 + 3
            char r10 = r8[r2]
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
            char r0 = r8[r2]
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
            r7.ch = r10
            r8 = 1
            return r8
        L6c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF16.nextIfMatchIdent(char, char, char):boolean");
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
            char[] r8 = r7.chars
            int r0 = r7.offset
            int r2 = r0 + 3
            int r3 = r7.end
            if (r2 > r3) goto L72
            char r4 = r8[r0]
            if (r4 != r9) goto L72
            int r9 = r0 + 1
            char r9 = r8[r9]
            if (r9 != r10) goto L72
            int r9 = r0 + 2
            char r9 = r8[r9]
            if (r9 == r11) goto L21
            goto L72
        L21:
            r9 = 26
            if (r2 != r3) goto L27
        L25:
            r10 = r9
            goto L2c
        L27:
            int r0 = r0 + 4
            char r10 = r8[r2]
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
            char r11 = r8[r2]
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
            r7.ch = r10
            r8 = 1
            return r8
        L72:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF16.nextIfMatchIdent(char, char, char, char):boolean");
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
            char[] r6 = r5.chars
            int r0 = r5.offset
            int r2 = r0 + 4
            int r3 = r5.end
            if (r2 > r3) goto L78
            char r4 = r6[r0]
            if (r4 != r7) goto L78
            int r7 = r0 + 1
            char r7 = r6[r7]
            if (r7 != r8) goto L78
            int r7 = r0 + 2
            char r7 = r6[r7]
            if (r7 != r9) goto L78
            int r7 = r0 + 3
            char r7 = r6[r7]
            if (r7 == r10) goto L27
            goto L78
        L27:
            r7 = 26
            if (r2 != r3) goto L2d
        L2b:
            r8 = r7
            goto L32
        L2d:
            int r0 = r0 + 5
            char r8 = r6[r2]
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
            char r9 = r6[r2]
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
            r5.ch = r8
            r6 = 1
            return r6
        L78:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF16.nextIfMatchIdent(char, char, char, char, char):boolean");
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
            char[] r6 = r5.chars
            int r0 = r5.offset
            int r2 = r0 + 5
            int r3 = r5.end
            if (r2 > r3) goto L7e
            char r4 = r6[r0]
            if (r4 != r7) goto L7e
            int r7 = r0 + 1
            char r7 = r6[r7]
            if (r7 != r8) goto L7e
            int r7 = r0 + 2
            char r7 = r6[r7]
            if (r7 != r9) goto L7e
            int r7 = r0 + 3
            char r7 = r6[r7]
            if (r7 != r10) goto L7e
            int r7 = r0 + 4
            char r7 = r6[r7]
            if (r7 == r11) goto L2d
            goto L7e
        L2d:
            r7 = 26
            if (r2 != r3) goto L33
        L31:
            r8 = r7
            goto L38
        L33:
            int r0 = r0 + 6
            char r8 = r6[r2]
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
            char r9 = r6[r2]
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
            r5.ch = r8
            r6 = 1
            return r6
        L7e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF16.nextIfMatchIdent(char, char, char, char, char, char):boolean");
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
            char[] r0 = r10.chars
            int r1 = r10.offset
            char r2 = r10.ch
            r3 = 83
            if (r2 != r3) goto L4f
            int r2 = r1 + 1
            int r3 = r10.end
            if (r2 >= r3) goto L4f
            char r4 = r0[r1]
            r5 = 101(0x65, float:1.42E-43)
            if (r4 != r5) goto L4f
            char r2 = r0[r2]
            r4 = 116(0x74, float:1.63E-43)
            if (r2 != r4) goto L4f
            int r2 = r1 + 2
            r4 = 26
            if (r2 != r3) goto L25
            r1 = r2
        L23:
            r2 = r4
            goto L29
        L25:
            int r1 = r1 + 3
            char r2 = r0[r2]
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
            char r1 = r0[r1]
            r9 = r2
            r2 = r1
            r1 = r9
            goto L29
        L49:
            r10.offset = r1
            r10.ch = r2
            r0 = 1
            return r0
        L4f:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF16.nextIfSet():boolean");
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
            char[] r0 = r10.chars
            int r1 = r10.offset
            char r2 = r10.ch
            r3 = 73
            if (r2 != r3) goto L73
            int r2 = r1 + 6
            int r3 = r10.end
            if (r2 >= r3) goto L73
            char r4 = r0[r1]
            r5 = 110(0x6e, float:1.54E-43)
            if (r4 != r5) goto L73
            int r4 = r1 + 1
            char r4 = r0[r4]
            r6 = 102(0x66, float:1.43E-43)
            if (r4 != r6) goto L73
            int r4 = r1 + 2
            char r4 = r0[r4]
            r6 = 105(0x69, float:1.47E-43)
            if (r4 != r6) goto L73
            int r4 = r1 + 3
            char r4 = r0[r4]
            if (r4 != r5) goto L73
            int r4 = r1 + 4
            char r4 = r0[r4]
            if (r4 != r6) goto L73
            int r4 = r1 + 5
            char r4 = r0[r4]
            r5 = 116(0x74, float:1.63E-43)
            if (r4 != r5) goto L73
            char r2 = r0[r2]
            r4 = 121(0x79, float:1.7E-43)
            if (r2 != r4) goto L73
            int r2 = r1 + 7
            r4 = 26
            if (r2 != r3) goto L49
            r1 = r2
        L47:
            r2 = r4
            goto L4d
        L49:
            int r1 = r1 + 8
            char r2 = r0[r2]
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
            char r1 = r0[r1]
            r9 = r2
            r2 = r1
            r1 = r9
            goto L4d
        L6d:
            r10.offset = r1
            r10.ch = r2
            r0 = 1
            return r0
        L73:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF16.nextIfInfinity():boolean");
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX WARN: Removed duplicated region for block: B:11:0x001b  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0041 A[LOOP:0: B:10:0x0019->B:24:0x0041, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0013 A[EDGE_INSN: B:25:0x0013->B:8:0x0013 BREAK  A[LOOP:0: B:10:0x0019->B:24:0x0041], SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x003e -> B:9:0x0013). Please report as a decompilation issue!!! */
    @Override // com.alibaba.fastjson2.JSONReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean nextIfObjectStart() {
        /*
            r9 = this;
            char r0 = r9.ch
            r1 = 123(0x7b, float:1.72E-43)
            if (r0 == r1) goto L8
            r0 = 0
            return r0
        L8:
            char[] r0 = r9.chars
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
            char r1 = r0[r1]
        L19:
            if (r1 == 0) goto L3c
            r4 = 32
            if (r1 > r4) goto L2f
            r4 = 1
            long r4 = r4 << r1
            r6 = 4294981376(0x100003700, double:2.1220027474E-314)
            long r4 = r4 & r6
            r6 = 0
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 == 0) goto L2f
            goto L3c
        L2f:
            r9.ch = r1
            r9.offset = r2
            r0 = 47
            if (r1 != r0) goto L3a
            r9.skipComment()
        L3a:
            r0 = 1
            return r0
        L3c:
            int r1 = r9.end
            if (r2 != r1) goto L41
            goto L13
        L41:
            int r1 = r2 + 1
            char r2 = r0[r2]
            r8 = r2
            r2 = r1
            r1 = r8
            goto L19
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF16.nextIfObjectStart():boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0070 A[LOOP:0: B:10:0x001b->B:42:0x0070, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0015 A[EDGE_INSN: B:43:0x0015->B:8:0x0015 BREAK  A[LOOP:0: B:10:0x001b->B:42:0x0070], SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:35:0x006d -> B:9:0x0015). Please report as a decompilation issue!!! */
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
            r2 = 125(0x7d, float:1.75E-43)
            if (r1 == r2) goto La
            r1 = 0
            return r1
        La:
            int r1 = r0.offset
            char[] r2 = r0.chars
            int r3 = r0.end
            r4 = 26
            if (r1 != r3) goto L17
            r3 = r1
        L15:
            r1 = r4
            goto L1b
        L17:
            int r3 = r1 + 1
            char r1 = r2[r1]
        L1b:
            if (r1 == 0) goto L6b
            r5 = 0
            r7 = 4294981376(0x100003700, double:2.1220027474E-314)
            r9 = 1
            r11 = 32
            if (r1 > r11) goto L32
            long r12 = r9 << r1
            long r12 = r12 & r7
            int r12 = (r12 > r5 ? 1 : (r12 == r5 ? 0 : -1))
            if (r12 == 0) goto L32
            goto L6b
        L32:
            r12 = 44
            r13 = 1
            if (r1 != r12) goto L5f
            r0.comma = r13
            int r1 = r0.end
            if (r3 != r1) goto L40
            r1 = r3
            r3 = r4
            goto L44
        L40:
            int r1 = r3 + 1
            char r3 = r2[r3]
        L44:
            r16 = r3
            r3 = r1
            r1 = r16
        L49:
            if (r1 == 0) goto L54
            if (r1 > r11) goto L5f
            long r14 = r9 << r1
            long r14 = r14 & r7
            int r12 = (r14 > r5 ? 1 : (r14 == r5 ? 0 : -1))
            if (r12 == 0) goto L5f
        L54:
            int r1 = r0.end
            if (r3 != r1) goto L5a
            r1 = r4
            goto L49
        L5a:
            int r1 = r3 + 1
            char r3 = r2[r3]
            goto L44
        L5f:
            r0.ch = r1
            r0.offset = r3
            r2 = 47
            if (r1 != r2) goto L6a
            r0.skipComment()
        L6a:
            return r13
        L6b:
            int r1 = r0.end
            if (r3 != r1) goto L70
            goto L15
        L70:
            int r1 = r3 + 1
            char r3 = r2[r3]
            r16 = r3
            r3 = r1
            r1 = r16
            goto L1b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF16.nextIfObjectEnd():boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0038 A[LOOP:0: B:6:0x0011->B:21:0x0038, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x000b A[EDGE_INSN: B:22:0x000b->B:4:0x000b BREAK  A[LOOP:0: B:6:0x0011->B:21:0x0038], SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x0035 -> B:4:0x000b). Please report as a decompilation issue!!! */
    @Override // com.alibaba.fastjson2.JSONReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void next() {
        /*
            r9 = this;
            int r0 = r9.offset
            char[] r1 = r9.chars
            int r2 = r9.end
            r3 = 26
            if (r0 < r2) goto Ld
            r2 = r0
        Lb:
            r0 = r3
            goto L11
        Ld:
            int r2 = r0 + 1
            char r0 = r1[r0]
        L11:
            if (r0 == 0) goto L33
            r4 = 32
            if (r0 > r4) goto L27
            r4 = 1
            long r4 = r4 << r0
            r6 = 4294981376(0x100003700, double:2.1220027474E-314)
            long r4 = r4 & r6
            r6 = 0
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 == 0) goto L27
            goto L33
        L27:
            r9.offset = r2
            r9.ch = r0
            r1 = 47
            if (r0 != r1) goto L32
            r9.skipComment()
        L32:
            return
        L33:
            int r0 = r9.end
            if (r2 != r0) goto L38
            goto Lb
        L38:
            int r0 = r2 + 1
            char r2 = r1[r2]
            r8 = r2
            r2 = r0
            r0 = r8
            goto L11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF16.next():void");
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0031 A[LOOP:0: B:6:0x0011->B:18:0x0031, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x000b A[EDGE_INSN: B:19:0x000b->B:4:0x000b BREAK  A[LOOP:0: B:6:0x0011->B:18:0x0031], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:14:0x002e -> B:4:0x000b). Please report as a decompilation issue!!! */
    @Override // com.alibaba.fastjson2.JSONReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void nextWithoutComment() {
        /*
            r9 = this;
            int r0 = r9.offset
            char[] r1 = r9.chars
            int r2 = r9.end
            r3 = 26
            if (r0 < r2) goto Ld
            r2 = r0
        Lb:
            r0 = r3
            goto L11
        Ld:
            int r2 = r0 + 1
            char r0 = r1[r0]
        L11:
            if (r0 == 0) goto L2c
            r4 = 32
            if (r0 > r4) goto L27
            r4 = 1
            long r4 = r4 << r0
            r6 = 4294981376(0x100003700, double:2.1220027474E-314)
            long r4 = r4 & r6
            r6 = 0
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 == 0) goto L27
            goto L2c
        L27:
            r9.offset = r2
            r9.ch = r0
            return
        L2c:
            int r0 = r9.end
            if (r2 != r0) goto L31
            goto Lb
        L31:
            int r0 = r2 + 1
            char r2 = r1[r2]
            r8 = r2
            r2 = r0
            r0 = r8
            goto L11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF16.nextWithoutComment():void");
    }

    /* JADX WARN: Removed duplicated region for block: B:145:0x022d  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x01a7  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01aa  */
    @Override // com.alibaba.fastjson2.JSONReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final long readFieldNameHashCodeUnquote() {
        /*
            Method dump skipped, instructions count: 746
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF16.readFieldNameHashCodeUnquote():long");
    }

    /* JADX WARN: Code restructure failed: missing block: B:147:0x0364, code lost:
    
        r10 = r28;
     */
    /* JADX WARN: Removed duplicated region for block: B:100:0x03ea  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x03fa  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0427  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x03d1  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x0367  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x02aa  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0370  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x03ce  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x03d9  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x03e8  */
    @Override // com.alibaba.fastjson2.JSONReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final long readFieldNameHashCode() {
        /*
            Method dump skipped, instructions count: 1126
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF16.readFieldNameHashCode():long");
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final long readValueHashCode() {
        boolean z;
        boolean z2;
        char[] cArr;
        char hexDigit4;
        long j;
        long j2;
        char c = this.ch;
        char c2 = Typography.quote;
        if (c != '\"' && c != '\'') {
            return -1L;
        }
        char[] cArr2 = this.chars;
        this.nameEscape = false;
        int i = this.offset;
        this.nameBegin = i;
        int i2 = this.end;
        int i3 = 0;
        long j3 = 0;
        while (true) {
            z = true;
            if (i < i2) {
                char c3 = cArr2[i];
                if (c3 != c) {
                    if (c3 == '\\') {
                        this.nameEscape = true;
                        int i4 = i + 1;
                        char c4 = cArr2[i4];
                        if (c4 == 'u') {
                            c3 = (char) IOUtils.hexDigit4(cArr2, JSONReaderJSONB.check3(i + 2, i2));
                            i += 5;
                        } else if (c4 == 'x') {
                            char c5 = cArr2[i + 2];
                            i += 3;
                            c3 = char2(c5, cArr2[i]);
                        } else {
                            c3 = char1(c4);
                            i = i4;
                        }
                    }
                    if (c3 <= 255 && i3 < 8 && (i3 != 0 || c3 != 0)) {
                        switch (i3) {
                            case 0:
                                j3 = (byte) c3;
                                continue;
                            case 1:
                                j = ((byte) c3) << 8;
                                j2 = 255;
                                break;
                            case 2:
                                j = ((byte) c3) << JSONB.Constants.BC_INT32_NUM_16;
                                j2 = WebSocketProtocol.PAYLOAD_SHORT_MAX;
                                break;
                            case 3:
                                j = ((byte) c3) << 24;
                                j2 = 16777215;
                                break;
                            case 4:
                                j = ((byte) c3) << 32;
                                j2 = 4294967295L;
                                break;
                            case 5:
                                j = ((byte) c3) << 40;
                                j2 = 1099511627775L;
                                break;
                            case 6:
                                j = ((byte) c3) << 48;
                                j2 = 281474976710655L;
                                break;
                            case 7:
                                j = ((byte) c3) << 56;
                                j2 = 72057594037927935L;
                                break;
                        }
                        j3 = (j3 & j2) + j;
                        i++;
                        i3++;
                    }
                } else if (i3 == 0) {
                    i = this.nameBegin;
                } else {
                    this.nameLength = i3;
                    this.nameEnd = i;
                    i++;
                }
            }
        }
        i = this.nameBegin;
        j3 = 0;
        if (j3 != 0) {
            z2 = true;
        } else {
            j3 = -3750763034362895579L;
            int i5 = 0;
            while (true) {
                char c6 = cArr2[i];
                if (c6 == '\\') {
                    this.nameEscape = z;
                    int i6 = i + 1;
                    z2 = z;
                    char c7 = cArr2[i6];
                    if (c7 == 'u') {
                        hexDigit4 = (char) IOUtils.hexDigit4(cArr2, JSONReaderJSONB.check3(i + 2, i2));
                        i6 = i + 5;
                    } else if (c7 == 'x') {
                        i6 = i + 3;
                        hexDigit4 = char2(cArr2[i + 2], cArr2[i6]);
                    } else {
                        hexDigit4 = char1(c7);
                    }
                    cArr = cArr2;
                    j3 = (hexDigit4 ^ j3) * Fnv.MAGIC_PRIME;
                    i = i6 + 1;
                } else {
                    z2 = z;
                    if (c6 == c2) {
                        this.nameLength = i5;
                        this.nameEnd = i;
                        this.stringValue = null;
                        i++;
                    } else {
                        i++;
                        cArr = cArr2;
                        j3 = (c6 ^ j3) * Fnv.MAGIC_PRIME;
                    }
                }
                i5++;
                z = z2;
                cArr2 = cArr;
                c2 = Typography.quote;
            }
        }
        char c8 = JSONLexer.EOI;
        char c9 = i == i2 ? (char) 26 : cArr2[i];
        while (c9 <= ' ' && ((1 << c9) & 4294981376L) != 0) {
            i++;
            c9 = cArr2[i];
        }
        boolean z3 = c9 == ',' ? z2 : false;
        this.comma = z3;
        if (z3) {
            i++;
            if (i != i2) {
                c8 = cArr2[i];
            }
            while (c8 <= ' ' && ((1 << c8) & 4294981376L) != 0) {
                i++;
                c8 = cArr2[i];
            }
            c9 = c8;
        }
        this.offset = i + 1;
        this.ch = c9;
        return j3;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final long getNameHashCodeLCase() {
        char c;
        char c2;
        long j;
        long j2;
        int i = this.nameBegin;
        char[] cArr = this.chars;
        char c3 = Typography.quote;
        char c4 = (i <= 0 || cArr[i + (-1)] != '\'') ? '\"' : '\'';
        int i2 = 0;
        long j3 = 0;
        while (true) {
            int i3 = this.end;
            if (i < i3) {
                char c5 = cArr[i];
                if (c5 == '\\') {
                    int i4 = i + 1;
                    char c6 = cArr[i4];
                    if (c6 == 'u') {
                        c5 = (char) IOUtils.hexDigit4(cArr, JSONReaderJSONB.check3(i + 2, i3));
                        i += 5;
                    } else if (c6 == 'x') {
                        char c7 = cArr[i + 2];
                        i += 3;
                        c5 = char2(c7, cArr[i]);
                    } else {
                        c5 = char1(c6);
                        i = i4;
                    }
                } else if (c5 == c4) {
                }
                if (c5 <= 255 && i2 < 8 && (i2 != 0 || c5 != 0)) {
                    if ((c5 != '_' && c5 != '-' && c5 != ' ') || (c2 = cArr[i + 1]) == '\"' || c2 == '\'' || c2 == c5) {
                        if (c5 >= 'A' && c5 <= 'Z') {
                            c5 = (char) (c5 + ' ');
                        }
                        switch (i2) {
                            case 0:
                                j3 = (byte) c5;
                                break;
                            case 1:
                                j = ((byte) c5) << 8;
                                j2 = 255;
                                j3 = (j3 & j2) + j;
                                break;
                            case 2:
                                j = ((byte) c5) << JSONB.Constants.BC_INT32_NUM_16;
                                j2 = WebSocketProtocol.PAYLOAD_SHORT_MAX;
                                j3 = (j3 & j2) + j;
                                break;
                            case 3:
                                j = ((byte) c5) << 24;
                                j2 = 16777215;
                                j3 = (j3 & j2) + j;
                                break;
                            case 4:
                                j = ((byte) c5) << 32;
                                j2 = 4294967295L;
                                j3 = (j3 & j2) + j;
                                break;
                            case 5:
                                j = ((byte) c5) << 40;
                                j2 = 1099511627775L;
                                j3 = (j3 & j2) + j;
                                break;
                            case 6:
                                j = ((byte) c5) << 48;
                                j2 = 281474976710655L;
                                j3 = (j3 & j2) + j;
                                break;
                            case 7:
                                j = ((byte) c5) << 56;
                                j2 = 72057594037927935L;
                                j3 = (j3 & j2) + j;
                                break;
                        }
                        i2++;
                    }
                    i++;
                }
            }
        }
        i = this.nameBegin;
        j3 = 0;
        if (j3 != 0) {
            return j3;
        }
        long j4 = Fnv.MAGIC_HASH_CODE;
        while (true) {
            int i5 = this.end;
            if (i < i5) {
                char c8 = cArr[i];
                if (c8 == '\\') {
                    int i6 = i + 1;
                    char c9 = cArr[i6];
                    if (c9 == 'u') {
                        c8 = (char) IOUtils.hexDigit4(cArr, JSONReaderJSONB.check3(i + 2, i5));
                        i += 5;
                    } else if (c9 == 'x') {
                        char c10 = cArr[i + 2];
                        i += 3;
                        c8 = char2(c10, cArr[i]);
                    } else {
                        c8 = char1(c9);
                        i = i6;
                    }
                } else if (c8 == c4) {
                }
                i++;
                if (c8 == '_' || c8 == '-' || c8 == ' ') {
                    char c11 = cArr[i];
                    if (c11 == c3 || c11 == '\'' || c11 == c8) {
                        c = 'A';
                    }
                } else {
                    c = 'A';
                }
                if (c8 >= c && c8 <= 'Z') {
                    c8 = (char) (c8 + ' ');
                }
                j4 = Fnv.MAGIC_PRIME * (c8 ^ j4);
                c3 = Typography.quote;
            }
        }
        return j4;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final String getFieldName() {
        if (!this.nameEscape) {
            String str = this.str;
            if (str != null) {
                return str.substring(this.nameBegin, this.nameEnd);
            }
            char[] cArr = this.chars;
            int i = this.nameBegin;
            return new String(cArr, i, this.nameEnd - i);
        }
        char[] cArr2 = new char[this.nameLength];
        char[] cArr3 = this.chars;
        int i2 = this.nameBegin;
        int i3 = 0;
        while (i2 < this.nameEnd) {
            char c = cArr3[i2];
            if (c == '\\') {
                int i4 = i2 + 1;
                char c2 = cArr3[i4];
                if (c2 != '\"' && c2 != ':' && c2 != '@' && c2 != '\\') {
                    if (c2 == 'u') {
                        c = (char) IOUtils.hexDigit4(cArr3, JSONReaderJSONB.check3(i2 + 2, this.end));
                        i2 += 5;
                    } else if (c2 == 'x') {
                        char c3 = cArr3[i2 + 2];
                        i2 += 3;
                        c = char2(c3, cArr3[i2]);
                    } else {
                        switch (c2) {
                            case '*':
                            case '+':
                            case ',':
                            case '-':
                            case '.':
                            case '/':
                                break;
                            default:
                                switch (c2) {
                                    case '<':
                                    case '=':
                                    case '>':
                                        break;
                                    default:
                                        c = char1(c2);
                                        i2 = i4;
                                        continue;
                                }
                        }
                    }
                }
                i2 = i4;
                c = c2;
            } else if (c == '\"') {
                return new String(cArr2);
            }
            cArr2[i3] = c;
            i2++;
            i3++;
        }
        return new String(cArr2);
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x08ba  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x082d  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x08c4  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x08cb  */
    @Override // com.alibaba.fastjson2.JSONReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String readFieldName() {
        /*
            Method dump skipped, instructions count: 2324
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF16.readFieldName():java.lang.String");
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
            Method dump skipped, instructions count: 274
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF16.readInt32Value():int");
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
            Method dump skipped, instructions count: 302
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF16.readInt64Value():long");
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
    public double readDoubleValue() {
        /*
            Method dump skipped, instructions count: 636
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF16.readDoubleValue():double");
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
    public float readFloatValue() {
        /*
            Method dump skipped, instructions count: 635
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF16.readFloatValue():float");
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final String getString() {
        if (this.stringValue != null) {
            return this.stringValue;
        }
        int i = this.nameEnd - this.nameBegin;
        if (!this.nameEscape) {
            return new String(this.chars, this.nameBegin, i);
        }
        char[] cArr = this.chars;
        char[] cArr2 = new char[this.nameLength];
        int i2 = this.nameBegin;
        int i3 = 0;
        while (true) {
            char c = cArr[i2];
            if (c == '\\') {
                int i4 = i2 + 1;
                char c2 = cArr[i4];
                if (c2 == '\"' || c2 == '\\') {
                    i2 = i4;
                    c = c2;
                } else if (c2 == 'u') {
                    c = (char) IOUtils.hexDigit4(cArr, JSONReaderJSONB.check3(i2 + 2, this.end));
                    i2 += 5;
                } else if (c2 == 'x') {
                    char c3 = cArr[i2 + 2];
                    i2 += 3;
                    c = char2(c3, cArr[i2]);
                } else {
                    c = char1(c2);
                    i2 = i4;
                }
            } else if (c == '\"') {
                String str = new String(cArr2);
                this.stringValue = str;
                return str;
            }
            cArr2[i3] = c;
            i2++;
            i3++;
        }
    }

    protected final void readString0() {
        String str;
        char[] cArr = this.chars;
        char c = this.ch;
        int i = this.offset;
        this.valueEscape = false;
        int i2 = i;
        int i3 = 0;
        while (true) {
            char c2 = cArr[i2];
            if (c2 == '\\') {
                this.valueEscape = true;
                char c3 = cArr[i2 + 1];
                i2 += c3 == 'u' ? 6 : c3 == 'x' ? 4 : 2;
            } else if (c2 == c) {
                break;
            } else {
                i2++;
            }
            i3++;
        }
        if (this.valueEscape) {
            char[] cArr2 = new char[i3];
            int i4 = 0;
            while (true) {
                char[] cArr3 = this.chars;
                char c4 = cArr3[i];
                if (c4 == '\\') {
                    int i5 = i + 1;
                    char c5 = cArr3[i5];
                    if (c5 == 'u') {
                        c4 = (char) IOUtils.hexDigit4(cArr, JSONReaderJSONB.check3(i + 2, this.end));
                        i += 5;
                    } else if (c5 == 'x') {
                        char c6 = cArr[i + 2];
                        i += 3;
                        c4 = char2(c6, cArr[i]);
                    } else if (c5 == '\\' || c5 == '\"') {
                        i = i5;
                        c4 = c5;
                    } else {
                        c4 = char1(c5);
                        i = i5;
                    }
                } else if (c4 == '\"') {
                    break;
                }
                cArr2[i4] = c4;
                i++;
                i4++;
            }
            str = new String(cArr2);
            i2 = i;
        } else {
            str = new String(cArr, this.offset, i2 - this.offset);
        }
        int i6 = i2 + 1;
        char c7 = i6 == this.end ? (char) 26 : cArr[i6];
        while (c7 <= ' ' && ((1 << c7) & 4294981376L) != 0) {
            i6++;
            c7 = cArr[i6];
        }
        boolean z = c7 == ',';
        this.comma = z;
        if (z) {
            this.offset = i6 + 1;
            int i7 = this.offset;
            this.offset = i7 + 1;
            this.ch = cArr[i7];
            while (this.ch <= ' ' && ((1 << this.ch) & 4294981376L) != 0) {
                if (this.offset >= this.end) {
                    this.ch = JSONLexer.EOI;
                } else {
                    int i8 = this.offset;
                    this.offset = i8 + 1;
                    this.ch = cArr[i8];
                }
            }
        } else {
            this.offset = i6 + 1;
            this.ch = c7;
        }
        this.stringValue = str;
    }

    /* JADX WARN: Removed duplicated region for block: B:84:0x0159  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0163 A[LOOP:3: B:82:0x014e->B:87:0x0163, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0149 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:92:0x016f  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0174  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:74:0x0160 -> B:69:0x0149). Please report as a decompilation issue!!! */
    @Override // com.alibaba.fastjson2.JSONReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String readString() {
        /*
            Method dump skipped, instructions count: 428
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF16.readString():java.lang.String");
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean skipName() {
        this.offset = skipName(this, this.chars, this.offset, this.end);
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0048 A[LOOP:1: B:19:0x0031->B:24:0x0048, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x002b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0069 A[LOOP:2: B:33:0x005d->B:37:0x0069, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0057 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0074  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x0045 -> B:20:0x002b). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:35:0x0066 -> B:31:0x0057). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int skipName(com.alibaba.fastjson2.JSONReaderUTF16 r12, char[] r13, int r14, int r15) {
        /*
            char r0 = r12.ch
            boolean r1 = r12.checkNameBegin(r0)
            if (r1 == 0) goto Lb
            int r12 = r12.offset
            return r12
        Lb:
            int r1 = r14 + 1
            char r2 = r13[r14]
            r3 = 92
            if (r2 != r3) goto L24
            char r14 = r13[r1]
            r2 = 117(0x75, float:1.64E-43)
            if (r14 != r2) goto L1b
            r14 = 5
            goto L22
        L1b:
            r2 = 120(0x78, float:1.68E-43)
            if (r14 != r2) goto L21
            r14 = 3
            goto L22
        L21:
            r14 = 1
        L22:
            int r14 = r14 + r1
            goto Lb
        L24:
            if (r2 != r0) goto L79
            r0 = 26
            if (r1 != r15) goto L2d
            r14 = r1
        L2b:
            r1 = r0
            goto L31
        L2d:
            int r14 = r14 + 2
            char r1 = r13[r1]
        L31:
            r2 = 0
            r4 = 4294981376(0x100003700, double:2.1220027474E-314)
            r6 = 1
            r8 = 32
            if (r1 > r8) goto L50
            long r9 = r6 << r1
            long r9 = r9 & r4
            int r9 = (r9 > r2 ? 1 : (r9 == r2 ? 0 : -1))
            if (r9 == 0) goto L50
            if (r14 != r15) goto L48
            goto L2b
        L48:
            int r1 = r14 + 1
            char r14 = r13[r14]
            r11 = r1
            r1 = r14
            r14 = r11
            goto L31
        L50:
            r9 = 58
            if (r1 != r9) goto L74
            if (r14 != r15) goto L59
            r1 = r14
        L57:
            r14 = r0
            goto L5d
        L59:
            int r1 = r14 + 1
            char r14 = r13[r14]
        L5d:
            if (r14 > r8) goto L71
            long r9 = r6 << r14
            long r9 = r9 & r4
            int r9 = (r9 > r2 ? 1 : (r9 == r2 ? 0 : -1))
            if (r9 == 0) goto L71
            if (r1 != r15) goto L69
            goto L57
        L69:
            int r14 = r1 + 1
            char r1 = r13[r1]
            r11 = r1
            r1 = r14
            r14 = r11
            goto L5d
        L71:
            r12.ch = r14
            return r1
        L74:
            com.alibaba.fastjson2.JSONException r12 = syntaxError(r1)
            throw r12
        L79:
            r14 = r1
            goto Lb
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF16.skipName(com.alibaba.fastjson2.JSONReaderUTF16, char[], int, int):int");
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
    private static int skipNumber(com.alibaba.fastjson2.JSONReaderUTF16 r20, char[] r21, int r22, int r23) {
        /*
            Method dump skipped, instructions count: 364
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF16.skipNumber(com.alibaba.fastjson2.JSONReaderUTF16, char[], int, int):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x005a A[LOOP:1: B:18:0x0043->B:23:0x005a, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x003d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0099  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x0057 -> B:20:0x003d). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int skipString(com.alibaba.fastjson2.JSONReaderUTF16 r18, char[] r19, int r20, int r21) {
        /*
            Method dump skipped, instructions count: 182
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF16.skipString(com.alibaba.fastjson2.JSONReaderUTF16, char[], int, int):int");
    }

    private static int skipStringEscaped(JSONReaderUTF16 jSONReaderUTF16, char[] cArr, int i, int i2) {
        int i3 = i + 1;
        char c = cArr[i];
        while (true) {
            if (c == '\\') {
                int i4 = i3 + 1;
                char c2 = cArr[i3];
                if (c2 == 'u') {
                    i4 = i3 + 5;
                } else if (c2 == 'x') {
                    i4 = i3 + 3;
                } else if (c2 != '\\' && c2 != '\"') {
                    jSONReaderUTF16.char1(c2);
                }
                i3 = i4 + 1;
                c = cArr[i4];
            } else {
                if (c == i2) {
                    return i3;
                }
                int i5 = i3 + 1;
                char c3 = cArr[i3];
                i3 = i5;
                c = c3;
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
    private static int skipObject(com.alibaba.fastjson2.JSONReaderUTF16 r18, char[] r19, int r20, int r21) {
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
            char r3 = r1[r3]
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
            char r6 = r1[r6]
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
            char r6 = r1[r6]
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
            char r6 = r1[r6]
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
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF16.skipObject(com.alibaba.fastjson2.JSONReaderUTF16, char[], int, int):int");
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0005, code lost:
    
        if (r1 != r10) goto L20;
     */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0034 A[LOOP:0: B:6:0x000b->B:12:0x0034, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0005 A[EDGE_INSN: B:13:0x0005->B:4:0x0005 BREAK  A[LOOP:0: B:6:0x000b->B:12:0x0034], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0028  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0030 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x000d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int next(com.alibaba.fastjson2.JSONReaderUTF16 r7, char[] r8, int r9, int r10) {
        /*
            r0 = 26
            if (r9 != r10) goto L7
            r1 = r9
        L5:
            r9 = r0
            goto Lb
        L7:
            int r1 = r9 + 1
            char r9 = r8[r9]
        Lb:
            if (r9 == 0) goto L31
            r2 = 32
            if (r9 > r2) goto L21
            r2 = 1
            long r2 = r2 << r9
            r4 = 4294981376(0x100003700, double:2.1220027474E-314)
            long r2 = r2 & r4
            r4 = 0
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 == 0) goto L21
            goto L31
        L21:
            char r8 = (char) r9
            r7.ch = r8
            r8 = 47
            if (r9 != r8) goto L30
            r7.offset = r1
            r7.skipComment()
            int r7 = r7.offset
            return r7
        L30:
            return r1
        L31:
            if (r1 != r10) goto L34
            goto L5
        L34:
            int r9 = r1 + 1
            char r1 = r8[r1]
            r6 = r1
            r1 = r9
            r9 = r6
            goto Lb
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF16.next(com.alibaba.fastjson2.JSONReaderUTF16, char[], int, int):int");
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
    private static int skipArray(com.alibaba.fastjson2.JSONReaderUTF16 r18, char[] r19, int r20, int r21) {
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
            char r3 = r1[r3]
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
            char r6 = r1[r6]
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
            char r4 = r1[r6]
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
            char r4 = r1[r6]
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
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF16.skipArray(com.alibaba.fastjson2.JSONReaderUTF16, char[], int, int):int");
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
    private static int skipFalse(com.alibaba.fastjson2.JSONReaderUTF16 r18, char[] r19, int r20, int r21) {
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
            char r2 = r19[r2]
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
            char r4 = r19[r4]
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
            char r4 = r19[r4]
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
            char r4 = r19[r4]
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
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF16.skipFalse(com.alibaba.fastjson2.JSONReaderUTF16, char[], int, int):int");
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
    private static int skipTrue(com.alibaba.fastjson2.JSONReaderUTF16 r19, char[] r20, int r21, int r22) {
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
            char r3 = r1[r3]
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
            char r5 = r1[r5]
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
            char r5 = r1[r5]
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
            char r5 = r1[r5]
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
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF16.skipTrue(com.alibaba.fastjson2.JSONReaderUTF16, char[], int, int):int");
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
    private static int skipNull(com.alibaba.fastjson2.JSONReaderUTF16 r19, char[] r20, int r21, int r22) {
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
            char r3 = r1[r3]
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
            char r5 = r1[r5]
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
            char r5 = r1[r5]
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
            char r5 = r1[r5]
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
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF16.skipNull(com.alibaba.fastjson2.JSONReaderUTF16, char[], int, int):int");
    }

    private static int skipSet(JSONReaderUTF16 jSONReaderUTF16, char[] cArr, int i, int i2) {
        if (nextIfSet(jSONReaderUTF16, cArr, i, i2)) {
            return skipArray(jSONReaderUTF16, cArr, jSONReaderUTF16.offset, i2);
        }
        throw jSONReaderUTF16.error();
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0021  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0033 A[LOOP:0: B:12:0x001d->B:17:0x0033, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0017 A[SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x0030 -> B:10:0x0017). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static boolean nextIfSet(com.alibaba.fastjson2.JSONReaderUTF16 r7, char[] r8, int r9, int r10) {
        /*
            int r0 = r9 + 1
            if (r0 >= r10) goto L41
            char r1 = r8[r9]
            r2 = 101(0x65, float:1.42E-43)
            if (r1 != r2) goto L41
            char r0 = r8[r0]
            r1 = 116(0x74, float:1.63E-43)
            if (r0 != r1) goto L41
            int r0 = r9 + 2
            r1 = 26
            if (r0 != r10) goto L19
            r9 = r0
        L17:
            r0 = r1
            goto L1d
        L19:
            int r9 = r9 + 3
            char r0 = r8[r0]
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
            char r9 = r8[r9]
            r6 = r0
            r0 = r9
            r9 = r6
            goto L1d
        L3b:
            r7.offset = r9
            r7.ch = r0
            r7 = 1
            return r7
        L41:
            r7 = 0
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF16.nextIfSet(com.alibaba.fastjson2.JSONReaderUTF16, char[], int, int):boolean");
    }

    private static int skipValue(JSONReaderUTF16 jSONReaderUTF16, char[] cArr, int i, int i2) {
        char c = jSONReaderUTF16.ch;
        if (c == '\"' || c == '\'') {
            return skipString(jSONReaderUTF16, cArr, i, i2);
        }
        if (c == 'S') {
            return skipSet(jSONReaderUTF16, cArr, i, i2);
        }
        if (c == '[') {
            return skipArray(jSONReaderUTF16, cArr, i, i2);
        }
        if (c == 'f') {
            return skipFalse(jSONReaderUTF16, cArr, i, i2);
        }
        if (c == 'n') {
            return skipNull(jSONReaderUTF16, cArr, i, i2);
        }
        if (c == 't') {
            return skipTrue(jSONReaderUTF16, cArr, i, i2);
        }
        if (c == '{') {
            return skipObject(jSONReaderUTF16, cArr, i, i2);
        }
        return skipNumber(jSONReaderUTF16, cArr, i, i2);
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final void skipValue() {
        this.offset = skipValue(this, this.chars, this.offset, this.end);
    }

    /* JADX WARN: Code restructure failed: missing block: B:45:0x0031, code lost:
    
        if (r1 == '\n') goto L17;
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
            if (r1 >= r2) goto L81
            char[] r2 = r12.chars
            char r3 = r2[r0]
            r4 = 42
            r5 = 0
            r6 = 47
            r7 = 1
            if (r3 != r4) goto L16
            r3 = r7
            goto L19
        L16:
            if (r3 != r6) goto L75
            r3 = r5
        L19:
            int r0 = r0 + 2
            char r1 = r2[r1]
        L1d:
            if (r3 == 0) goto L2f
            if (r1 != r4) goto L2d
            int r1 = r12.end
            if (r0 > r1) goto L2d
            char r1 = r2[r0]
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
            char r1 = r2[r0]
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
            char r1 = r2[r0]
            goto L3f
        L5c:
            r8 = r1
        L5d:
            int r0 = r0 + r7
            goto L63
        L5f:
            int r1 = r12.end
            if (r0 < r1) goto L6d
        L63:
            r12.ch = r8
            r12.offset = r0
            if (r8 != r6) goto L6c
            r12.skipComment()
        L6c:
            return
        L6d:
            int r1 = r0 + 1
            char r0 = r2[r0]
            r11 = r1
            r1 = r0
            r0 = r11
            goto L1d
        L75:
            com.alibaba.fastjson2.JSONException r0 = new com.alibaba.fastjson2.JSONException
            java.lang.String r1 = "parse comment error"
            java.lang.String r1 = r12.info(r1)
            r0.<init>(r1)
            throw r0
        L81:
            com.alibaba.fastjson2.JSONException r0 = new com.alibaba.fastjson2.JSONException
            java.lang.String r1 = r12.info()
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF16.skipComment():void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:228:0x00d7, code lost:
    
        if (r26.mag3 < (-214748364)) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x011b, code lost:
    
        if (r26.mag3 < (-214748364)) goto L60;
     */
    /* JADX WARN: Removed duplicated region for block: B:251:0x0058 A[LOOP:5: B:247:0x004b->B:251:0x0058, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:252:0x0044 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0329  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0311 A[SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:227:0x0055 -> B:223:0x0044). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:87:0x0326 -> B:90:0x0311). Please report as a decompilation issue!!! */
    @Override // com.alibaba.fastjson2.JSONReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void readNumber0() {
        /*
            Method dump skipped, instructions count: 879
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF16.readNumber0():void");
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean readIfNull() {
        int i;
        char c;
        char[] cArr = this.chars;
        int i2 = this.offset;
        if (this.ch != 'n' || cArr[i2] != 'u' || cArr[i2 + 1] != 'l' || cArr[i2 + 2] != 'l') {
            return false;
        }
        int i3 = i2 + 3;
        char c2 = i3 == this.end ? (char) 26 : cArr[i3];
        int i4 = i2 + 4;
        while (c2 <= ' ' && ((1 << c2) & 4294981376L) != 0) {
            if (i4 == this.end) {
                c2 = 26;
            } else {
                c2 = cArr[i4];
                i4++;
            }
        }
        boolean z = c2 == ',';
        this.comma = z;
        if (z) {
            if (i4 == this.end) {
                i = i4;
                c = 26;
            } else {
                i = i4 + 1;
                c = cArr[i4];
            }
            loop1: while (true) {
                int i5 = i;
                c2 = c;
                i4 = i5;
                while (c2 <= ' ' && ((1 << c2) & 4294981376L) != 0) {
                    if (i4 == this.end) {
                        c2 = 26;
                    }
                }
                i = i4 + 1;
                c = cArr[i4];
            }
        }
        this.ch = c2;
        this.offset = i4;
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:134:0x0051, code lost:
    
        r3 = 26;
     */
    /* JADX WARN: Removed duplicated region for block: B:96:0x00a2 A[LOOP:4: B:91:0x0094->B:96:0x00a2, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0090 A[SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:74:0x009f -> B:69:0x0090). Please report as a decompilation issue!!! */
    @Override // com.alibaba.fastjson2.JSONReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.Date readNullOrNewDate() {
        /*
            Method dump skipped, instructions count: 396
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF16.readNullOrNewDate():java.util.Date");
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean isNull() {
        return this.ch == 'n' && this.offset < this.end && this.chars[this.offset] == 'u';
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfNull() {
        if (this.ch != 'n' || this.offset + 2 >= this.end || this.chars[this.offset] != 'u') {
            return false;
        }
        readNull();
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final void readNull() {
        int i;
        char c;
        char[] cArr = this.chars;
        int i2 = this.offset;
        if (cArr[i2] == 'u' && cArr[i2 + 1] == 'l' && cArr[i2 + 2] == 'l') {
            int i3 = i2 + 3;
            char c2 = i3 == this.end ? (char) 26 : cArr[i3];
            int i4 = i2 + 4;
            while (c2 <= ' ' && ((1 << c2) & 4294981376L) != 0) {
                if (i4 == this.end) {
                    c2 = 26;
                } else {
                    c2 = cArr[i4];
                    i4++;
                }
            }
            boolean z = c2 == ',';
            this.comma = z;
            if (z) {
                if (i4 == this.end) {
                    i = i4;
                    c = 26;
                } else {
                    i = i4 + 1;
                    c = cArr[i4];
                }
                loop1: while (true) {
                    int i5 = i;
                    c2 = c;
                    i4 = i5;
                    while (c2 <= ' ' && ((1 << c2) & 4294981376L) != 0) {
                        if (i4 == this.end) {
                            c2 = 26;
                        }
                    }
                    i = i4 + 1;
                    c = cArr[i4];
                }
            }
            this.ch = c2;
            this.offset = i4;
            return;
        }
        throw new JSONException("json syntax error, not match null, offset " + i2);
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
            char[] r0 = r14.chars
            int r1 = r14.offset
            char r2 = r0[r1]
            r3 = 97
            if (r2 != r3) goto L77
            int r2 = r1 + 1
            char r2 = r0[r2]
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
            char r2 = r0[r2]
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
            char r1 = r0[r1]
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
            char r1 = r0[r1]
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
            char r1 = r0[r1]
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
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF16.readNaN():double");
    }

    /* JADX WARN: Code restructure failed: missing block: B:240:0x009f, code lost:
    
        r25 = r13;
     */
    /* JADX WARN: Removed duplicated region for block: B:109:0x02d3  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x02e1  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x02bc A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:120:0x02ea  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x02f0  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0317  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x031c  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x02c6  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x0247  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x0128  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x0159  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x0140 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:234:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0092 A[LOOP:0: B:11:0x0058->B:23:0x0092, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x008b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0162  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x024e  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0282  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:107:0x02de -> B:110:0x02bc). Please report as a decompilation issue!!! */
    @Override // com.alibaba.fastjson2.JSONReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.math.BigDecimal readBigDecimal() {
        /*
            Method dump skipped, instructions count: 808
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF16.readBigDecimal():java.math.BigDecimal");
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00d5 A[LOOP:0: B:29:0x00bd->B:34:0x00d5, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00b6 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00e4  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:32:0x00d2 -> B:27:0x00b6). Please report as a decompilation issue!!! */
    @Override // com.alibaba.fastjson2.JSONReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.UUID readUUID() {
        /*
            Method dump skipped, instructions count: 286
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF16.readUUID():java.util.UUID");
    }

    private static long parse4Nibbles(char[] cArr, int i) {
        byte[] bArr = JSONFactory.NIBBLES;
        char c = cArr[i];
        char c2 = cArr[i + 1];
        char c3 = cArr[i + 2];
        if ((c | c2 | c3 | cArr[i + 3]) > 255) {
            return -1L;
        }
        return bArr[r5] | (bArr[c] << 12) | (bArr[c2] << 8) | (bArr[c3] << 4);
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final int getStringLength() {
        int i;
        if (this.ch != '\"' && this.ch != '\'') {
            throw new JSONException("string length only support string input");
        }
        char c = this.ch;
        int i2 = this.offset;
        char[] cArr = this.chars;
        int i3 = i2 + 8;
        if (i3 >= this.end || i3 >= cArr.length || cArr[i2] == c || cArr[i2 + 1] == c || cArr[i2 + 2] == c || cArr[i2 + 3] == c || cArr[i2 + 4] == c || cArr[i2 + 5] == c || cArr[i2 + 6] == c || cArr[i2 + 7] == c) {
            i = 0;
        } else {
            i = 8;
            i2 = i3;
        }
        while (i2 < this.end && cArr[i2] != c) {
            i2++;
            i++;
        }
        return i;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    protected final LocalDateTime readLocalDateTime14() {
        if (this.ch != '\"' && this.ch != '\'') {
            throw new JSONException("date only support string input");
        }
        LocalDateTime parseLocalDateTime14 = DateUtils.parseLocalDateTime14(this.chars, this.offset);
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
    protected final LocalDateTime readLocalDateTime12() {
        if (this.ch != '\"' && this.ch != '\'') {
            throw new JSONException("date only support string input");
        }
        LocalDateTime parseLocalDateTime12 = DateUtils.parseLocalDateTime12(this.chars, this.offset);
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
    protected final LocalDateTime readLocalDateTime16() {
        if (this.ch != '\"' && this.ch != '\'') {
            throw new JSONException("date only support string input");
        }
        LocalDateTime parseLocalDateTime16 = DateUtils.parseLocalDateTime16(this.chars, this.offset);
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
    protected final LocalDateTime readLocalDateTime17() {
        if (this.ch != '\"' && this.ch != '\'') {
            throw new JSONException("date only support string input");
        }
        LocalDateTime parseLocalDateTime17 = DateUtils.parseLocalDateTime17(this.chars, this.offset);
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
    protected final LocalDateTime readLocalDateTime18() {
        if (this.ch != '\"' && this.ch != '\'') {
            throw new JSONException("date only support string input");
        }
        LocalDateTime parseLocalDateTime18 = DateUtils.parseLocalDateTime18(this.chars, this.offset);
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
    protected final LocalTime readLocalTime5() {
        if (this.ch != '\"' && this.ch != '\'') {
            throw new JSONException("localTime only support string input");
        }
        LocalTime parseLocalTime5 = DateUtils.parseLocalTime5(this.chars, this.offset);
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
        LocalTime parseLocalTime6 = DateUtils.parseLocalTime6(this.chars, this.offset);
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
        LocalTime parseLocalTime7 = DateUtils.parseLocalTime7(this.chars, this.offset);
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
        LocalTime parseLocalTime8 = DateUtils.parseLocalTime8(this.chars, this.offset);
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
        LocalTime parseLocalTime8 = DateUtils.parseLocalTime8(this.chars, this.offset);
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
    public final LocalDate readLocalDate8() {
        if (this.ch != '\"' && this.ch != '\'') {
            throw new JSONException("localDate only support string input");
        }
        try {
            LocalDate parseLocalDate8 = DateUtils.parseLocalDate8(this.chars, this.offset);
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
        if (this.ch != '\"' && this.ch != '\'') {
            throw new JSONException("localDate only support string input");
        }
        try {
            LocalDate parseLocalDate9 = DateUtils.parseLocalDate9(this.chars, this.offset);
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
        if (this.ch != '\"' && this.ch != '\'') {
            throw new JSONException("localDate only support string input");
        }
        try {
            LocalDate parseLocalDate10 = DateUtils.parseLocalDate10(this.chars, this.offset);
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
    public final LocalDate readLocalDate11() {
        if (this.ch != '\"' && this.ch != '\'') {
            throw new JSONException("localDate only support string input");
        }
        LocalDate parseLocalDate11 = DateUtils.parseLocalDate11(this.chars, this.offset);
        if (parseLocalDate11 == null) {
            return null;
        }
        this.offset += 12;
        next();
        boolean z = this.ch == ',';
        this.comma = z;
        if (z) {
            next();
        }
        return parseLocalDate11;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final LocalDate readLocalDate() {
        LocalDate of;
        char[] cArr = this.chars;
        int i = this.offset;
        if ((this.ch == '\"' || this.ch == '\'') && !this.context.formatComplex) {
            char c = this.ch;
            int i2 = i + 10;
            if (i2 < cArr.length && i2 < this.end && cArr[i + 4] == '-' && cArr[i + 7] == '-' && cArr[i2] == c) {
                int digit4 = IOUtils.digit4(cArr, i);
                int digit2 = IOUtils.digit2(cArr, i + 5);
                int digit22 = IOUtils.digit2(cArr, i + 8);
                int i3 = digit4 | digit2 | digit22;
                if (i3 < 0) {
                    throw new JSONException(info("read date error"));
                }
                if (i3 == 0) {
                    of = null;
                } else {
                    try {
                        of = LocalDate.of(digit4, digit2, digit22);
                    } catch (DateTimeException e) {
                        throw new JSONException(info("read date error"), e);
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
            LocalDate readLocalDate0 = readLocalDate0(i, cArr, c);
            if (readLocalDate0 != null) {
                return readLocalDate0;
            }
        }
        return super.readLocalDate();
    }

    private LocalDate readLocalDate0(int i, char[] cArr, char c) {
        int i2;
        int min = Math.min(i + 17, this.end);
        int i3 = -1;
        for (int i4 = i; i4 < min; i4++) {
            if (cArr[i4] == c) {
                i3 = i4;
            }
        }
        if (i3 == -1 || (i2 = i3 - i) <= 10 || cArr[i3 - 6] != '-' || cArr[i3 - 3] != '-') {
            return null;
        }
        LocalDate of = LocalDate.of(TypeUtils.parseInt(cArr, i, i2 - 6), IOUtils.digit2(cArr, i3 - 5), IOUtils.digit2(cArr, i3 - 2));
        this.offset = i3 + 1;
        next();
        boolean z = this.ch == ',';
        this.comma = z;
        if (z) {
            next();
        }
        return of;
    }

    /* JADX WARN: Code restructure failed: missing block: B:91:0x00d9, code lost:
    
        if (r8 != r3) goto L82;
     */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00d9 A[EDGE_INSN: B:66:0x00d9->B:56:0x00d9 BREAK  A[LOOP:1: B:58:0x00e0->B:65:0x00df], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0103  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0119  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0105 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:89:0x00fd  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:71:0x0116 -> B:67:0x0105). Please report as a decompilation issue!!! */
    @Override // com.alibaba.fastjson2.JSONReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.time.OffsetDateTime readOffsetDateTime() {
        /*
            Method dump skipped, instructions count: 321
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF16.readOffsetDateTime():java.time.OffsetDateTime");
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final OffsetTime readOffsetTime() {
        int i;
        ZoneOffset of;
        char[] cArr = this.chars;
        int i2 = this.offset;
        JSONReader.Context context = this.context;
        if ((this.ch == '\"' || this.ch == '\'') && context.dateFormat == null) {
            char c = this.ch;
            int i3 = i2 + 8;
            if (i3 < cArr.length && i3 < this.end && cArr[i2 + 2] == ':' && cArr[i2 + 5] == ':') {
                char c2 = cArr[i2];
                char c3 = cArr[i2 + 1];
                char c4 = cArr[i2 + 3];
                char c5 = cArr[i2 + 4];
                char c6 = cArr[i2 + 6];
                char c7 = cArr[i2 + 7];
                if (c2 < '0' || c2 > '9' || c3 < '0' || c3 > '9') {
                    throw new JSONException(info("illegal offsetTime"));
                }
                int i4 = ((c2 - '0') * 10) + (c3 - '0');
                if (c4 < '0' || c4 > '9' || c5 < '0' || c5 > '9') {
                    throw new JSONException(info("illegal offsetTime"));
                }
                int i5 = ((c4 - '0') * 10) + (c5 - '0');
                if (c6 < '0' || c6 > '9' || c7 < '0' || c7 > '9') {
                    throw new JSONException(info("illegal offsetTime"));
                }
                int i6 = ((c6 - '0') * 10) + (c7 - '0');
                int i7 = i2 + 25;
                int i8 = i3;
                int i9 = -1;
                while (true) {
                    if (i8 >= i7 || i8 >= this.end || i8 >= cArr.length) {
                        break;
                    }
                    char c8 = cArr[i8];
                    if (i9 == -1 && (c8 == 'Z' || c8 == '+' || c8 == '-')) {
                        i9 = (i8 - i3) - 1;
                    }
                    if (c8 == c) {
                        i = i8 - i2;
                        break;
                    }
                    i8++;
                }
                i = 0;
                int readNanos = i9 <= 0 ? 0 : DateUtils.readNanos(cArr, i9, i2 + 9);
                int i10 = (i - 9) - i9;
                if (i10 <= 1) {
                    of = ZoneOffset.UTC;
                } else {
                    of = ZoneOffset.of(new String(cArr, i2 + 9 + i9, i10));
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
        if (this.ch != '\"' && this.ch != '\'') {
            throw new JSONException("date only support string input");
        }
        if (i < 19) {
            return null;
        }
        char[] cArr = this.chars;
        if (i == 30 && cArr[this.offset + 29] == 'Z') {
            parseZonedDateTime = ZonedDateTime.of(DateUtils.parseLocalDateTime29(cArr, this.offset), ZoneOffset.UTC);
        } else if (i == 29 && cArr[this.offset + 28] == 'Z') {
            parseZonedDateTime = ZonedDateTime.of(DateUtils.parseLocalDateTime28(cArr, this.offset), ZoneOffset.UTC);
        } else if (i == 28 && cArr[this.offset + 27] == 'Z') {
            parseZonedDateTime = ZonedDateTime.of(DateUtils.parseLocalDateTime27(cArr, this.offset), ZoneOffset.UTC);
        } else if (i == 27 && cArr[this.offset + 26] == 'Z') {
            parseZonedDateTime = ZonedDateTime.of(DateUtils.parseLocalDateTime26(cArr, this.offset), ZoneOffset.UTC);
        } else {
            parseZonedDateTime = DateUtils.parseZonedDateTime(cArr, this.offset, i, this.context.zoneId);
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
    protected final LocalDateTime readLocalDateTime19() {
        if (this.ch != '\"' && this.ch != '\'') {
            throw new JSONException("date only support string input");
        }
        LocalDateTime parseLocalDateTime19 = DateUtils.parseLocalDateTime19(this.chars, this.offset);
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
        if (this.ch != '\"' && this.ch != '\'') {
            throw new JSONException("date only support string input");
        }
        LocalDateTime parseLocalDateTime20 = DateUtils.parseLocalDateTime20(this.chars, this.offset);
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
        char c = this.ch;
        if (c != '\"' && c != '\'') {
            throw new JSONException("date only support string input");
        }
        if (this.offset + 18 >= this.end) {
            this.wasNull = true;
            return 0L;
        }
        long parseMillis19 = DateUtils.parseMillis19(this.chars, this.offset, this.context.zoneId);
        if (this.chars[this.offset + 19] != c) {
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
    /* JADX WARN: Type inference failed for: r0v10, types: [java.time.LocalDateTime] */
    @Override // com.alibaba.fastjson2.JSONReader
    protected final LocalDateTime readLocalDateTimeX(int i) {
        LocalDateTime localDateTime;
        if (this.ch != '\"' && this.ch != '\'') {
            throw new JSONException("date only support string input");
        }
        if (this.chars[(this.offset + i) - 1] == 'Z') {
            localDateTime = DateUtils.parseZonedDateTime(this.chars, this.offset, i).toInstant().atZone(this.context.getZoneId()).toLocalDateTime();
        } else {
            localDateTime = DateUtils.parseLocalDateTimeX(this.chars, this.offset, i);
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

    @Override // com.alibaba.fastjson2.JSONReader
    protected final LocalTime readLocalTime10() {
        if (this.ch != '\"' && this.ch != '\'') {
            throw new JSONException("localTime only support string input");
        }
        LocalTime parseLocalTime10 = DateUtils.parseLocalTime10(this.chars, this.offset);
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
        if (this.ch != '\"' && this.ch != '\'') {
            throw new JSONException("localTime only support string input");
        }
        LocalTime parseLocalTime11 = DateUtils.parseLocalTime11(this.chars, this.offset);
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
        if (this.ch != '\"' && this.ch != '\'') {
            throw new JSONException("localTime only support string input");
        }
        LocalTime parseLocalTime12 = DateUtils.parseLocalTime12(this.chars, this.offset);
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
        if (this.ch != '\"' && this.ch != '\'') {
            throw new JSONException("localTime only support string input");
        }
        LocalTime parseLocalTime15 = DateUtils.parseLocalTime15(this.chars, this.offset);
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
        if (this.ch != '\"' && this.ch != '\'') {
            throw new JSONException("localTime only support string input");
        }
        LocalTime parseLocalTime18 = DateUtils.parseLocalTime18(this.chars, this.offset);
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

    /* JADX WARN: Removed duplicated region for block: B:17:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0046 A[LOOP:1: B:15:0x002d->B:21:0x0046, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0027 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0054  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:0x0043 -> B:12:0x0027). Please report as a decompilation issue!!! */
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
            if (r0 != r1) goto L80
            char[] r0 = r15.chars
            int r2 = r15.offset
            r3 = r2
        Lb:
            int r4 = r15.end
            if (r3 >= r4) goto L17
            char r4 = r0[r3]
            if (r4 != r1) goto L14
            goto L17
        L14:
            int r3 = r3 + 1
            goto Lb
        L17:
            java.lang.String r1 = new java.lang.String
            int r4 = r3 - r2
            r1.<init>(r0, r2, r4)
            int r2 = r3 + 1
            int r4 = r15.end
            r5 = 26
            if (r2 != r4) goto L29
            r3 = r2
        L27:
            r2 = r5
            goto L2d
        L29:
            int r3 = r3 + 2
            char r2 = r0[r2]
        L2d:
            r6 = 0
            r8 = 4294981376(0x100003700, double:2.1220027474E-314)
            r10 = 1
            r4 = 32
            if (r2 > r4) goto L4e
            long r12 = r10 << r2
            long r12 = r12 & r8
            int r12 = (r12 > r6 ? 1 : (r12 == r6 ? 0 : -1))
            if (r12 == 0) goto L4e
            int r2 = r15.end
            if (r3 != r2) goto L46
            goto L27
        L46:
            int r2 = r3 + 1
            char r3 = r0[r3]
            r14 = r3
            r3 = r2
            r2 = r14
            goto L2d
        L4e:
            r12 = 44
            if (r2 != r12) goto L54
            r12 = 1
            goto L55
        L54:
            r12 = 0
        L55:
            r15.comma = r12
            if (r12 == 0) goto L7b
            int r2 = r15.end
            if (r3 != r2) goto L60
            r2 = r3
            r3 = r5
            goto L64
        L60:
            int r2 = r3 + 1
            char r3 = r0[r3]
        L64:
            r14 = r3
            r3 = r2
            r2 = r14
        L67:
            if (r2 > r4) goto L7b
            long r12 = r10 << r2
            long r12 = r12 & r8
            int r12 = (r12 > r6 ? 1 : (r12 == r6 ? 0 : -1))
            if (r12 == 0) goto L7b
            int r2 = r15.end
            if (r3 != r2) goto L76
            r2 = r5
            goto L67
        L76:
            int r2 = r3 + 1
            char r3 = r0[r3]
            goto L64
        L7b:
            r15.offset = r3
            r15.ch = r2
            return r1
        L80:
            com.alibaba.fastjson2.JSONException r0 = new com.alibaba.fastjson2.JSONException
            java.lang.String r1 = "illegal pattern"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF16.readPattern():java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x011d  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0129 A[LOOP:0: B:18:0x0112->B:24:0x0129, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x010c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x013c  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0155  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0140 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x010e  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x0126 -> B:16:0x010c). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:36:0x0152 -> B:31:0x0140). Please report as a decompilation issue!!! */
    @Override // com.alibaba.fastjson2.JSONReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean readBoolValue() {
        /*
            Method dump skipped, instructions count: 467
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderUTF16.readBoolValue():boolean");
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public String info(String str) {
        int i = 1;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (!(i2 < this.offset) || !(i2 < this.end)) {
                break;
            }
            if (this.chars[i2] == '\n') {
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
        sb.append("offset ").append(this.offset).append(", character ").append(this.ch).append(", line ").append(i).append(", column ").append(i3).append(", fastjson-version 2.0.58").append(i <= 1 ? ' ' : '\n');
        sb.append(this.chars, this.start, Math.min(this.length, 65535));
        return sb.toString();
    }

    @Override // com.alibaba.fastjson2.JSONReader, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.cacheIndex != -1 && this.chars.length < 8388608) {
            JSONFactory.CHARS_UPDATER.lazySet(JSONFactory.CACHE_ITEMS[this.cacheIndex], this.chars);
        }
        Closeable closeable = this.input;
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final int getRawInt() {
        int i = this.offset + 3;
        char[] cArr = this.chars;
        if (i < cArr.length) {
            return getInt(cArr, this.offset - 1);
        }
        return 0;
    }

    static int getInt(char[] cArr, int i) {
        long longUnaligned = IOUtils.getLongUnaligned(cArr, i);
        if ((CHAR_MASK & longUnaligned) != 0) {
            return 0;
        }
        if (JDKUtils.BIG_ENDIAN) {
            longUnaligned >>= 8;
        }
        return (int) (((longUnaligned & 71776119061217280L) >> 24) | ((16711680 & longUnaligned) >> 8) | (255 & longUnaligned) | ((1095216660480L & longUnaligned) >> 16));
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final long getRawLong() {
        int i = this.offset + 7;
        char[] cArr = this.chars;
        if (i < cArr.length) {
            return getLong(cArr, this.offset - 1);
        }
        return 0L;
    }

    static long getLong(char[] cArr, int i) {
        long longUnaligned = IOUtils.getLongUnaligned(cArr, i);
        long longUnaligned2 = IOUtils.getLongUnaligned(cArr, i + 4);
        if (((longUnaligned | longUnaligned2) & CHAR_MASK) != 0) {
            return 0L;
        }
        if (JDKUtils.BIG_ENDIAN) {
            longUnaligned >>= 8;
            longUnaligned2 >>= 8;
        }
        return ((longUnaligned & 71776119061217280L) >> 24) | (longUnaligned & 255) | ((longUnaligned & 16711680) >> 8) | ((longUnaligned & 1095216660480L) >> 16) | ((255 & longUnaligned2) << 32) | ((longUnaligned2 & 16711680) << 24) | ((longUnaligned2 & 1095216660480L) << 16) | ((longUnaligned2 & 71776119061217280L) << 8);
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName8Match0() {
        int i;
        char c;
        char[] cArr = this.chars;
        int i2 = this.offset + 7;
        if (i2 == this.end) {
            this.ch = JSONLexer.EOI;
            return false;
        }
        while (true) {
            i = i2 + 1;
            c = cArr[i2];
            if (c > ' ' || ((1 << c) & 4294981376L) == 0) {
                break;
            }
            i2 = i;
        }
        this.offset = i;
        this.ch = c;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName8Match1() {
        int i;
        char c;
        char[] cArr = this.chars;
        int i2 = this.offset;
        int i3 = i2 + 8;
        if (i3 >= this.end || cArr[i2 + 7] != ':') {
            return false;
        }
        while (true) {
            i = i3 + 1;
            c = cArr[i3];
            if (c > ' ' || ((1 << c) & 4294981376L) == 0) {
                break;
            }
            i3 = i;
        }
        this.offset = i;
        this.ch = c;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName8Match2() {
        int i;
        char c;
        char[] cArr = this.chars;
        int i2 = this.offset;
        int i3 = i2 + 9;
        if (i3 >= this.end || cArr[i2 + 7] != '\"' || cArr[i2 + 8] != ':') {
            return false;
        }
        while (true) {
            i = i3 + 1;
            c = cArr[i3];
            if (c > ' ' || ((1 << c) & 4294981376L) == 0) {
                break;
            }
            i3 = i;
        }
        this.offset = i;
        this.ch = c;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match2() {
        int i;
        char c;
        char[] cArr = this.chars;
        int i2 = this.offset;
        int i3 = i2 + 4;
        if (i3 >= this.end || cArr[i2 + 3] != ':') {
            return false;
        }
        while (true) {
            i = i3 + 1;
            c = cArr[i3];
            if (c > ' ' || ((1 << c) & 4294981376L) == 0) {
                break;
            }
            i3 = i;
        }
        this.offset = i;
        this.ch = c;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match3() {
        char c;
        char[] cArr = this.chars;
        int i = this.offset;
        int i2 = i + 5;
        if (i2 >= this.end || cArr[i + 3] != '\"' || cArr[i + 4] != ':') {
            return false;
        }
        while (true) {
            c = cArr[i2];
            if (c > ' ' || ((1 << c) & 4294981376L) == 0) {
                break;
            }
            i2++;
        }
        this.offset = i2 + 1;
        this.ch = c;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match4(byte b) {
        char c;
        char[] cArr = this.chars;
        int i = this.offset;
        int i2 = i + 6;
        if (i2 >= this.end || cArr[i + 3] != b || cArr[i + 4] != '\"' || cArr[i + 5] != ':') {
            return false;
        }
        while (true) {
            c = cArr[i2];
            if (c > ' ' || ((1 << c) & 4294981376L) == 0) {
                break;
            }
            i2++;
        }
        this.offset = i2 + 1;
        this.ch = c;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match5(int i) {
        int i2;
        char c;
        char[] cArr = this.chars;
        int i3 = this.offset;
        int i4 = i3 + 7;
        if (i4 >= this.end || getInt(cArr, i3 + 3) != i) {
            return false;
        }
        while (true) {
            i2 = i4 + 1;
            c = cArr[i4];
            if (c > ' ' || ((1 << c) & 4294981376L) == 0) {
                break;
            }
            i4 = i2;
        }
        this.offset = i2;
        this.ch = c;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match6(int i) {
        int i2;
        char c;
        char[] cArr = this.chars;
        int i3 = this.offset;
        int i4 = i3 + 8;
        if (i4 >= this.end || getInt(cArr, i3 + 3) != i || cArr[i3 + 7] != ':') {
            return false;
        }
        while (true) {
            i2 = i4 + 1;
            c = cArr[i4];
            if (c > ' ' || ((1 << c) & 4294981376L) == 0) {
                break;
            }
            i4 = i2;
        }
        this.offset = i2;
        this.ch = c;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match7(int i) {
        int i2;
        char c;
        char[] cArr = this.chars;
        int i3 = this.offset;
        int i4 = i3 + 9;
        if (i4 >= this.end || getInt(cArr, i3 + 3) != i || cArr[i3 + 7] != '\"' || cArr[i3 + 8] != ':') {
            return false;
        }
        while (true) {
            i2 = i4 + 1;
            c = cArr[i4];
            if (c > ' ' || ((1 << c) & 4294981376L) == 0) {
                break;
            }
            i4 = i2;
        }
        this.offset = i2;
        this.ch = c;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match8(int i, byte b) {
        int i2;
        char c;
        char[] cArr = this.chars;
        int i3 = this.offset;
        int i4 = i3 + 10;
        if (i4 >= this.end || getInt(cArr, i3 + 3) != i || cArr[i3 + 7] != b || cArr[i3 + 8] != '\"' || cArr[i3 + 9] != ':') {
            return false;
        }
        while (true) {
            i2 = i4 + 1;
            c = cArr[i4];
            if (c > ' ' || ((1 << c) & 4294981376L) == 0) {
                break;
            }
            i4 = i2;
        }
        this.offset = i2;
        this.ch = c;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match9(long j) {
        int i;
        char c;
        char[] cArr = this.chars;
        int i2 = this.offset;
        int i3 = i2 + 11;
        if (i3 >= this.end || getLong(cArr, i2 + 3) != j) {
            return false;
        }
        while (true) {
            i = i3 + 1;
            c = cArr[i3];
            if (c > ' ' || ((1 << c) & 4294981376L) == 0) {
                break;
            }
            i3 = i;
        }
        this.offset = i;
        this.ch = c;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match10(long j) {
        int i;
        char c;
        char[] cArr = this.chars;
        int i2 = this.offset;
        int i3 = i2 + 12;
        if (i3 >= this.end || getLong(cArr, i2 + 3) != j || cArr[i2 + 11] != ':') {
            return false;
        }
        while (true) {
            i = i3 + 1;
            c = cArr[i3];
            if (c > ' ' || ((1 << c) & 4294981376L) == 0) {
                break;
            }
            i3 = i;
        }
        this.offset = i;
        this.ch = c;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match11(long j) {
        int i;
        char c;
        char[] cArr = this.chars;
        int i2 = this.offset;
        int i3 = i2 + 13;
        if (i3 >= this.end || getLong(cArr, i2 + 3) != j || cArr[i2 + 11] != '\"' || cArr[i2 + 12] != ':') {
            return false;
        }
        while (true) {
            i = i3 + 1;
            c = cArr[i3];
            if (c > ' ' || ((1 << c) & 4294981376L) == 0) {
                break;
            }
            i3 = i;
        }
        this.offset = i;
        this.ch = c;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match12(long j, byte b) {
        int i;
        char c;
        char[] cArr = this.chars;
        int i2 = this.offset;
        int i3 = i2 + 14;
        if (i3 >= this.end || getLong(cArr, i2 + 3) != j || cArr[i2 + 11] != b || cArr[i2 + 12] != '\"' || cArr[i2 + 13] != ':') {
            return false;
        }
        while (true) {
            i = i3 + 1;
            c = cArr[i3];
            if (c > ' ' || ((1 << c) & 4294981376L) == 0) {
                break;
            }
            i3 = i;
        }
        this.offset = i;
        this.ch = c;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match13(long j, int i) {
        int i2;
        char c;
        char[] cArr = this.chars;
        int i3 = this.offset;
        int i4 = i3 + 15;
        if (i4 >= this.end || getLong(cArr, i3 + 3) != j || getInt(cArr, i3 + 11) != i) {
            return false;
        }
        while (true) {
            i2 = i4 + 1;
            c = cArr[i4];
            if (c > ' ' || ((1 << c) & 4294981376L) == 0) {
                break;
            }
            i4 = i2;
        }
        this.offset = i2;
        this.ch = c;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match14(long j, int i) {
        int i2;
        char c;
        char[] cArr = this.chars;
        int i3 = this.offset;
        int i4 = i3 + 16;
        if (i4 >= this.end || getLong(cArr, i3 + 3) != j || getInt(cArr, i3 + 11) != i || cArr[i3 + 15] != ':') {
            return false;
        }
        while (true) {
            i2 = i4 + 1;
            c = cArr[i4];
            if (c > ' ' || ((1 << c) & 4294981376L) == 0) {
                break;
            }
            i4 = i2;
        }
        this.offset = i2;
        this.ch = c;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match15(long j, int i) {
        int i2;
        char c;
        char[] cArr = this.chars;
        int i3 = this.offset;
        int i4 = i3 + 17;
        if (i4 >= this.end || getLong(cArr, i3 + 3) != j || getInt(cArr, i3 + 11) != i || cArr[i3 + 15] != '\"' || cArr[i3 + 16] != ':') {
            return false;
        }
        while (true) {
            i2 = i4 + 1;
            c = cArr[i4];
            if (c > ' ' || ((1 << c) & 4294981376L) == 0) {
                break;
            }
            i4 = i2;
        }
        this.offset = i2;
        this.ch = c;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match16(long j, int i, byte b) {
        int i2;
        char c;
        char[] cArr = this.chars;
        int i3 = this.offset;
        int i4 = i3 + 18;
        if (i4 >= this.end || getLong(cArr, i3 + 3) != j || getInt(cArr, i3 + 11) != i || cArr[i3 + 15] != b || cArr[i3 + 16] != '\"' || cArr[i3 + 17] != ':') {
            return false;
        }
        while (true) {
            i2 = i4 + 1;
            c = cArr[i4];
            if (c > ' ' || ((1 << c) & 4294981376L) == 0) {
                break;
            }
            i4 = i2;
        }
        this.offset = i2;
        this.ch = c;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match17(long j, long j2) {
        int i;
        char c;
        char[] cArr = this.chars;
        int i2 = this.offset;
        int i3 = i2 + 19;
        if (i3 >= this.end || getLong(cArr, i2 + 3) != j || getLong(cArr, i2 + 11) != j2) {
            return false;
        }
        while (true) {
            i = i3 + 1;
            c = cArr[i3];
            if (c > ' ' || ((1 << c) & 4294981376L) == 0) {
                break;
            }
            i3 = i;
        }
        this.offset = i;
        this.ch = c;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match18(long j, long j2) {
        int i;
        char c;
        char[] cArr = this.chars;
        int i2 = this.offset;
        int i3 = i2 + 20;
        if (i3 >= this.end || getLong(cArr, i2 + 3) != j || getLong(cArr, i2 + 11) != j2 || cArr[i2 + 19] != ':') {
            return false;
        }
        while (true) {
            i = i3 + 1;
            c = cArr[i3];
            if (c > ' ' || ((1 << c) & 4294981376L) == 0) {
                break;
            }
            i3 = i;
        }
        this.offset = i;
        this.ch = c;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match19(long j, long j2) {
        int i;
        char c;
        char[] cArr = this.chars;
        int i2 = this.offset;
        int i3 = i2 + 21;
        if (i3 >= this.end || getLong(cArr, i2 + 3) != j || getLong(cArr, i2 + 11) != j2 || cArr[i2 + 19] != '\"' || cArr[i2 + 20] != ':') {
            return false;
        }
        while (true) {
            i = i3 + 1;
            c = cArr[i3];
            if (c > ' ' || ((1 << c) & 4294981376L) == 0) {
                break;
            }
            i3 = i;
        }
        this.offset = i;
        this.ch = c;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match20(long j, long j2, byte b) {
        int i;
        char c;
        char[] cArr = this.chars;
        int i2 = this.offset;
        int i3 = i2 + 22;
        if (i3 >= this.end || getLong(cArr, i2 + 3) != j || getLong(cArr, i2 + 11) != j2 || cArr[i2 + 19] != b || cArr[i2 + 20] != '\"' || cArr[i2 + 21] != ':') {
            return false;
        }
        while (true) {
            i = i3 + 1;
            c = cArr[i3];
            if (c > ' ' || ((1 << c) & 4294981376L) == 0) {
                break;
            }
            i3 = i;
        }
        this.offset = i;
        this.ch = c;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match21(long j, long j2, int i) {
        int i2;
        char c;
        char[] cArr = this.chars;
        int i3 = this.offset;
        int i4 = i3 + 23;
        if (i4 >= this.end || getLong(cArr, i3 + 3) != j || getLong(cArr, i3 + 11) != j2 || getInt(cArr, i3 + 19) != i) {
            return false;
        }
        while (true) {
            i2 = i4 + 1;
            c = cArr[i4];
            if (c > ' ' || ((1 << c) & 4294981376L) == 0) {
                break;
            }
            i4 = i2;
        }
        this.offset = i2;
        this.ch = c;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match22(long j, long j2, int i) {
        int i2;
        char c;
        char[] cArr = this.chars;
        int i3 = this.offset;
        int i4 = i3 + 24;
        if (i4 >= this.end || getLong(cArr, i3 + 3) != j || getLong(cArr, i3 + 11) != j2 || getInt(cArr, i3 + 19) != i || cArr[i3 + 23] != ':') {
            return false;
        }
        while (true) {
            i2 = i4 + 1;
            c = cArr[i4];
            if (c > ' ' || ((1 << c) & 4294981376L) == 0) {
                break;
            }
            i4 = i2;
        }
        this.offset = i2;
        this.ch = c;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match23(long j, long j2, int i) {
        int i2;
        char c;
        char[] cArr = this.chars;
        int i3 = this.offset;
        int i4 = i3 + 25;
        if (i4 >= this.end || getLong(cArr, i3 + 3) != j || getLong(cArr, i3 + 11) != j2 || getInt(cArr, i3 + 19) != i || cArr[i3 + 23] != '\"' || cArr[i3 + 24] != ':') {
            return false;
        }
        while (true) {
            i2 = i4 + 1;
            c = cArr[i4];
            if (c > ' ' || ((1 << c) & 4294981376L) == 0) {
                break;
            }
            i4 = i2;
        }
        this.offset = i2;
        this.ch = c;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match24(long j, long j2, int i, byte b) {
        int i2;
        char c;
        char[] cArr = this.chars;
        int i3 = this.offset;
        int i4 = i3 + 26;
        if (i4 >= this.end || getLong(cArr, i3 + 3) != j || getLong(cArr, i3 + 11) != j2 || getInt(cArr, i3 + 19) != i || cArr[i3 + 23] != b || cArr[i3 + 24] != '\"' || cArr[i3 + 25] != ':') {
            return false;
        }
        while (true) {
            i2 = i4 + 1;
            c = cArr[i4];
            if (c > ' ' || ((1 << c) & 4294981376L) == 0) {
                break;
            }
            i4 = i2;
        }
        this.offset = i2;
        this.ch = c;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match25(long j, long j2, long j3) {
        int i;
        char c;
        char[] cArr = this.chars;
        int i2 = this.offset;
        int i3 = i2 + 27;
        if (i3 >= this.end || getLong(cArr, i2 + 3) != j || getLong(cArr, i2 + 11) != j2 || getLong(cArr, i2 + 19) != j3) {
            return false;
        }
        while (true) {
            i = i3 + 1;
            c = cArr[i3];
            if (c > ' ' || ((1 << c) & 4294981376L) == 0) {
                break;
            }
            i3 = i;
        }
        this.offset = i;
        this.ch = c;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match26(long j, long j2, long j3) {
        int i;
        char c;
        char[] cArr = this.chars;
        int i2 = this.offset;
        int i3 = i2 + 28;
        if (i3 >= this.end || getLong(cArr, i2 + 3) != j || getLong(cArr, i2 + 11) != j2 || getLong(cArr, i2 + 19) != j3 || cArr[i2 + 27] != ':') {
            return false;
        }
        while (true) {
            i = i3 + 1;
            c = this.chars[i3];
            if (c > ' ' || ((1 << c) & 4294981376L) == 0) {
                break;
            }
            i3 = i;
        }
        this.offset = i;
        this.ch = c;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match27(long j, long j2, long j3) {
        int i;
        char c;
        char[] cArr = this.chars;
        int i2 = this.offset;
        int i3 = i2 + 29;
        if (i3 >= this.end || getLong(cArr, i2 + 3) != j || getLong(cArr, i2 + 11) != j2 || getLong(cArr, i2 + 19) != j3 || cArr[i2 + 27] != '\"' || cArr[i2 + 28] != ':') {
            return false;
        }
        while (true) {
            i = i3 + 1;
            c = this.chars[i3];
            if (c > ' ' || ((1 << c) & 4294981376L) == 0) {
                break;
            }
            i3 = i;
        }
        this.offset = i;
        this.ch = c;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match28(long j, long j2, long j3, byte b) {
        int i;
        char c;
        char[] cArr = this.chars;
        int i2 = this.offset;
        int i3 = i2 + 30;
        if (i3 >= this.end || getLong(cArr, i2 + 3) != j || getLong(cArr, i2 + 11) != j2 || getLong(cArr, i2 + 19) != j3 || cArr[i2 + 27] != b || cArr[i2 + 28] != '\"' || cArr[i2 + 29] != ':') {
            return false;
        }
        while (true) {
            i = i3 + 1;
            c = cArr[i3];
            if (c > ' ' || ((1 << c) & 4294981376L) == 0) {
                break;
            }
            i3 = i;
        }
        this.offset = i;
        this.ch = c;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match29(long j, long j2, long j3, int i) {
        int i2;
        char c;
        char[] cArr = this.chars;
        int i3 = this.offset;
        int i4 = i3 + 31;
        if (i4 >= this.end || getLong(cArr, i3 + 3) != j || getLong(cArr, i3 + 11) != j2 || getLong(cArr, i3 + 19) != j3 || getInt(cArr, i3 + 27) != i) {
            return false;
        }
        while (true) {
            i2 = i4 + 1;
            c = cArr[i4];
            if (c > ' ' || ((1 << c) & 4294981376L) == 0) {
                break;
            }
            i4 = i2;
        }
        this.offset = i2;
        this.ch = c;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match30(long j, long j2, long j3, int i) {
        int i2;
        char c;
        char[] cArr = this.chars;
        int i3 = this.offset;
        int i4 = i3 + 32;
        if (i4 >= this.end || getLong(cArr, i3 + 3) != j || getLong(cArr, i3 + 11) != j2 || getLong(cArr, i3 + 19) != j3 || getInt(cArr, i3 + 27) != i || cArr[i3 + 31] != ':') {
            return false;
        }
        while (true) {
            i2 = i4 + 1;
            c = cArr[i4];
            if (c > ' ' || ((1 << c) & 4294981376L) == 0) {
                break;
            }
            i4 = i2;
        }
        this.offset = i2;
        this.ch = c;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match31(long j, long j2, long j3, int i) {
        int i2;
        char c;
        char[] cArr = this.chars;
        int i3 = this.offset;
        int i4 = i3 + 33;
        if (i4 >= this.end || getLong(cArr, i3 + 3) != j || getLong(cArr, i3 + 11) != j2 || getLong(cArr, i3 + 19) != j3 || getInt(cArr, i3 + 27) != i || cArr[i3 + 31] != '\"' || cArr[i3 + 32] != ':') {
            return false;
        }
        while (true) {
            i2 = i4 + 1;
            c = cArr[i4];
            if (c > ' ' || ((1 << c) & 4294981376L) == 0) {
                break;
            }
            i4 = i2;
        }
        this.offset = i2;
        this.ch = c;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match32(long j, long j2, long j3, int i, byte b) {
        int i2;
        char c;
        char[] cArr = this.chars;
        int i3 = this.offset;
        int i4 = i3 + 34;
        if (i4 >= this.end || getLong(cArr, i3 + 3) != j || getLong(cArr, i3 + 11) != j2 || getLong(cArr, i3 + 19) != j3 || getInt(cArr, i3 + 27) != i || cArr[i3 + 31] != b || cArr[i3 + 32] != '\"' || cArr[i3 + 33] != ':') {
            return false;
        }
        while (true) {
            i2 = i4 + 1;
            c = cArr[i4];
            if (c > ' ' || ((1 << c) & 4294981376L) == 0) {
                break;
            }
            i4 = i2;
        }
        this.offset = i2;
        this.ch = c;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match33(long j, long j2, long j3, long j4) {
        int i;
        char c;
        char[] cArr = this.chars;
        int i2 = this.offset;
        int i3 = i2 + 35;
        if (i3 >= this.end || getLong(cArr, i2 + 3) != j || getLong(cArr, i2 + 11) != j2 || getLong(cArr, i2 + 19) != j3 || getLong(cArr, i2 + 27) != j4) {
            return false;
        }
        while (true) {
            i = i3 + 1;
            c = cArr[i3];
            if (c > ' ' || ((1 << c) & 4294981376L) == 0) {
                break;
            }
            i3 = i;
        }
        this.offset = i;
        this.ch = c;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match34(long j, long j2, long j3, long j4) {
        int i;
        char c;
        char[] cArr = this.chars;
        int i2 = this.offset;
        int i3 = i2 + 36;
        if (i3 >= this.end || getLong(cArr, i2 + 3) != j || getLong(cArr, i2 + 11) != j2 || getLong(cArr, i2 + 19) != j3 || getLong(cArr, i2 + 27) != j4 || cArr[i2 + 35] != ':') {
            return false;
        }
        while (true) {
            i = i3 + 1;
            c = cArr[i3];
            if (c > ' ' || ((1 << c) & 4294981376L) == 0) {
                break;
            }
            i3 = i;
        }
        this.offset = i;
        this.ch = c;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match35(long j, long j2, long j3, long j4) {
        int i;
        char c;
        char[] cArr = this.chars;
        int i2 = this.offset;
        int i3 = i2 + 37;
        if (i3 >= this.end || getLong(cArr, i2 + 3) != j || getLong(cArr, i2 + 11) != j2 || getLong(cArr, i2 + 19) != j3 || getLong(cArr, i2 + 27) != j4 || cArr[i2 + 35] != '\"' || cArr[i2 + 36] != ':') {
            return false;
        }
        while (true) {
            i = i3 + 1;
            c = cArr[i3];
            if (c > ' ' || ((1 << c) & 4294981376L) == 0) {
                break;
            }
            i3 = i;
        }
        this.offset = i;
        this.ch = c;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match36(long j, long j2, long j3, long j4, byte b) {
        int i;
        char c;
        char[] cArr = this.chars;
        int i2 = this.offset;
        int i3 = i2 + 38;
        if (i3 >= this.end || getLong(cArr, i2 + 3) != j || getLong(cArr, i2 + 11) != j2 || getLong(cArr, i2 + 19) != j3 || getLong(cArr, i2 + 27) != j4 || cArr[i2 + 35] != b || cArr[i2 + 36] != '\"' || cArr[i2 + 37] != ':') {
            return false;
        }
        while (true) {
            i = i3 + 1;
            c = cArr[i3];
            if (c > ' ' || ((1 << c) & 4294981376L) == 0) {
                break;
            }
            i3 = i;
        }
        this.offset = i;
        this.ch = c;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match37(long j, long j2, long j3, long j4, int i) {
        int i2;
        char c;
        char[] cArr = this.chars;
        int i3 = this.offset;
        int i4 = i3 + 39;
        if (i4 >= this.end || getLong(cArr, i3 + 3) != j || getLong(cArr, i3 + 11) != j2 || getLong(cArr, i3 + 19) != j3 || getLong(cArr, i3 + 27) != j4 || getInt(cArr, i3 + 35) != i) {
            return false;
        }
        while (true) {
            i2 = i4 + 1;
            c = cArr[i4];
            if (c > ' ' || ((1 << c) & 4294981376L) == 0) {
                break;
            }
            i4 = i2;
        }
        this.offset = i2;
        this.ch = c;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match38(long j, long j2, long j3, long j4, int i) {
        int i2;
        char c;
        char[] cArr = this.chars;
        int i3 = this.offset;
        int i4 = i3 + 40;
        if (i4 >= this.end || getLong(cArr, i3 + 3) != j || getLong(cArr, i3 + 11) != j2 || getLong(cArr, i3 + 19) != j3 || getLong(cArr, i3 + 27) != j4 || getInt(cArr, i3 + 35) != i || cArr[i3 + 39] != ':') {
            return false;
        }
        while (true) {
            i2 = i4 + 1;
            c = cArr[i4];
            if (c > ' ' || ((1 << c) & 4294981376L) == 0) {
                break;
            }
            i4 = i2;
        }
        this.offset = i2;
        this.ch = c;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match39(long j, long j2, long j3, long j4, int i) {
        int i2;
        char c;
        char[] cArr = this.chars;
        int i3 = this.offset;
        int i4 = i3 + 41;
        if (i4 >= this.end || getLong(cArr, i3 + 3) != j || getLong(cArr, i3 + 11) != j2 || getLong(cArr, i3 + 19) != j3 || getLong(cArr, i3 + 27) != j4 || getInt(cArr, i3 + 35) != i || cArr[i3 + 39] != '\"' || cArr[i3 + 40] != ':') {
            return false;
        }
        while (true) {
            i2 = i4 + 1;
            c = cArr[i4];
            if (c > ' ' || ((1 << c) & 4294981376L) == 0) {
                break;
            }
            i4 = i2;
        }
        this.offset = i2;
        this.ch = c;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match40(long j, long j2, long j3, long j4, int i, byte b) {
        int i2;
        char c;
        char[] cArr = this.chars;
        int i3 = this.offset;
        int i4 = i3 + 42;
        if (i4 >= this.end || getLong(cArr, i3 + 3) != j || getLong(cArr, i3 + 11) != j2 || getLong(cArr, i3 + 19) != j3 || getLong(cArr, i3 + 27) != j4 || getInt(cArr, i3 + 35) != i || cArr[i3 + 39] != b || cArr[i3 + 40] != '\"' || cArr[i3 + 41] != ':') {
            return false;
        }
        while (true) {
            i2 = i4 + 1;
            c = cArr[i4];
            if (c > ' ' || ((1 << c) & 4294981376L) == 0) {
                break;
            }
            i4 = i2;
        }
        this.offset = i2;
        this.ch = c;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match41(long j, long j2, long j3, long j4, long j5) {
        int i;
        char c;
        char[] cArr = this.chars;
        int i2 = this.offset;
        int i3 = i2 + 43;
        if (i3 >= this.end || getLong(cArr, i2 + 3) != j || getLong(cArr, i2 + 11) != j2 || getLong(cArr, i2 + 19) != j3 || getLong(cArr, i2 + 27) != j4 || getLong(cArr, i2 + 35) != j5) {
            return false;
        }
        while (true) {
            i = i3 + 1;
            c = cArr[i3];
            if (c > ' ' || ((1 << c) & 4294981376L) == 0) {
                break;
            }
            i3 = i;
        }
        this.offset = i;
        this.ch = c;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match42(long j, long j2, long j3, long j4, long j5) {
        int i;
        char c;
        char[] cArr = this.chars;
        int i2 = this.offset;
        int i3 = i2 + 44;
        if (i3 >= this.end || getLong(cArr, i2 + 3) != j || getLong(cArr, i2 + 11) != j2 || getLong(cArr, i2 + 19) != j3 || getLong(cArr, i2 + 27) != j4 || getLong(cArr, i2 + 35) != j5 || cArr[i2 + 43] != ':') {
            return false;
        }
        while (true) {
            i = i3 + 1;
            c = cArr[i3];
            if (c > ' ' || ((1 << c) & 4294981376L) == 0) {
                break;
            }
            i3 = i;
        }
        this.offset = i;
        this.ch = c;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfName4Match43(long j, long j2, long j3, long j4, long j5) {
        int i;
        char c;
        char[] cArr = this.chars;
        int i2 = this.offset;
        int i3 = i2 + 45;
        if (i3 >= this.end || getLong(cArr, i2 + 3) != j || getLong(cArr, i2 + 11) != j2 || getLong(cArr, i2 + 19) != j3 || getLong(cArr, i2 + 27) != j4 || getLong(cArr, i2 + 35) != j5 || cArr[i2 + 43] != '\"' || cArr[i2 + 44] != ':') {
            return false;
        }
        while (true) {
            i = i3 + 1;
            c = cArr[i3];
            if (c > ' ' || ((1 << c) & 4294981376L) == 0) {
                break;
            }
            i3 = i;
        }
        this.offset = i;
        this.ch = c;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfValue4Match2() {
        char[] cArr = this.chars;
        int i = this.offset;
        int i2 = i + 3;
        if (i2 >= this.end) {
            return false;
        }
        char c = cArr[i2];
        if (c != ',' && c != '}' && c != ']') {
            return false;
        }
        if (c == ',') {
            this.comma = true;
            i2 = i + 4;
            c = i2 == this.end ? JSONLexer.EOI : cArr[i2];
        }
        while (c <= ' ' && ((1 << c) & 4294981376L) != 0) {
            i2++;
            c = cArr[i2];
        }
        this.offset = i2 + 1;
        this.ch = c;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfValue4Match3() {
        char[] cArr = this.chars;
        int i = this.offset;
        int i2 = i + 4;
        if (i2 >= this.end || cArr[i + 3] != '\"') {
            return false;
        }
        char c = cArr[i2];
        if (c != ',' && c != '}' && c != ']') {
            return false;
        }
        if (c == ',') {
            this.comma = true;
            i2 = i + 5;
            c = i2 == this.end ? JSONLexer.EOI : cArr[i2];
        }
        while (c <= ' ' && ((1 << c) & 4294981376L) != 0) {
            i2++;
            c = cArr[i2];
        }
        this.offset = i2 + 1;
        this.ch = c;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfValue4Match4(byte b) {
        char[] cArr = this.chars;
        int i = this.offset;
        int i2 = i + 5;
        boolean z = false;
        if (i2 >= this.end) {
            return false;
        }
        if (cArr[i + 3] == b && cArr[i + 4] == '\"') {
            char c = cArr[i2];
            if (c != ',' && c != '}' && c != ']') {
                return false;
            }
            z = true;
            if (c == ',') {
                this.comma = true;
                i2 = i + 6;
                c = i2 == this.end ? JSONLexer.EOI : cArr[i2];
            }
            while (c <= ' ' && ((1 << c) & 4294981376L) != 0) {
                i2++;
                c = cArr[i2];
            }
            this.offset = i2 + 1;
            this.ch = c;
        }
        return z;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfValue4Match5(byte b, byte b2) {
        char[] cArr = this.chars;
        int i = this.offset;
        int i2 = i + 6;
        if (i2 >= this.end || cArr[i + 3] != b || cArr[i + 4] != b2 || cArr[i + 5] != '\"') {
            return false;
        }
        char c = cArr[i2];
        if (c != ',' && c != '}' && c != ']') {
            return false;
        }
        if (c == ',') {
            this.comma = true;
            i2 = i + 7;
            c = i2 == this.end ? JSONLexer.EOI : cArr[i2];
        }
        while (c <= ' ' && ((1 << c) & 4294981376L) != 0) {
            i2++;
            c = cArr[i2];
        }
        this.offset = i2 + 1;
        this.ch = c;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfValue4Match6(int i) {
        char[] cArr = this.chars;
        int i2 = this.offset;
        int i3 = i2 + 7;
        if (i3 >= this.end || getInt(cArr, i2 + 3) != i) {
            return false;
        }
        char c = cArr[i3];
        if (c != ',' && c != '}' && c != ']') {
            return false;
        }
        if (c == ',') {
            this.comma = true;
            i3 = i2 + 8;
            c = i3 == this.end ? JSONLexer.EOI : cArr[i3];
        }
        while (c <= ' ' && ((1 << c) & 4294981376L) != 0) {
            i3++;
            c = cArr[i3];
        }
        this.offset = i3 + 1;
        this.ch = c;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfValue4Match7(int i) {
        char[] cArr = this.chars;
        int i2 = this.offset;
        int i3 = i2 + 8;
        boolean z = false;
        if (i3 >= this.end) {
            return false;
        }
        if (getInt(cArr, i2 + 3) == i && cArr[i2 + 7] == '\"') {
            char c = cArr[i3];
            if (c != ',' && c != '}' && c != ']') {
                return false;
            }
            z = true;
            if (c == ',') {
                this.comma = true;
                i3 = i2 + 9;
                c = i3 == this.end ? JSONLexer.EOI : cArr[i3];
            }
            while (c <= ' ' && ((1 << c) & 4294981376L) != 0) {
                i3++;
                c = cArr[i3];
            }
            this.offset = i3 + 1;
            this.ch = c;
        }
        return z;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfValue4Match8(int i, byte b) {
        char[] cArr = this.chars;
        int i2 = this.offset;
        int i3 = i2 + 9;
        if (i3 >= this.end || getInt(cArr, i2 + 3) != i || cArr[i2 + 7] != b || cArr[i2 + 8] != '\"') {
            return false;
        }
        char c = cArr[i3];
        if (c != ',' && c != '}' && c != ']') {
            return false;
        }
        if (c == ',') {
            this.comma = true;
            i3 = i2 + 10;
            c = i3 == this.end ? JSONLexer.EOI : cArr[i3];
        }
        while (c <= ' ' && ((1 << c) & 4294981376L) != 0) {
            i3++;
            c = cArr[i3];
        }
        this.offset = i3 + 1;
        this.ch = c;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfValue4Match9(int i, byte b, byte b2) {
        char[] cArr = this.chars;
        int i2 = this.offset;
        int i3 = i2 + 10;
        if (i3 >= this.end || getInt(cArr, i2 + 3) != i || cArr[i2 + 7] != b || cArr[i2 + 8] != b2 || cArr[i2 + 9] != '\"') {
            return false;
        }
        char c = cArr[i3];
        if (c != ',' && c != '}' && c != ']') {
            return false;
        }
        if (c == ',') {
            this.comma = true;
            i3 = i2 + 11;
            c = i3 == this.end ? JSONLexer.EOI : cArr[i3];
        }
        while (c <= ' ' && ((1 << c) & 4294981376L) != 0) {
            i3++;
            c = cArr[i3];
        }
        this.offset = i3 + 1;
        this.ch = c;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfValue4Match10(long j) {
        char[] cArr = this.chars;
        int i = this.offset;
        int i2 = i + 11;
        if (i2 >= this.end || getLong(cArr, i + 3) != j) {
            return false;
        }
        char c = cArr[i2];
        if (c != ',' && c != '}' && c != ']') {
            return false;
        }
        if (c == ',') {
            this.comma = true;
            i2 = i + 12;
            c = i2 == this.end ? JSONLexer.EOI : cArr[i2];
        }
        while (c <= ' ' && ((1 << c) & 4294981376L) != 0) {
            i2++;
            c = cArr[i2];
        }
        this.offset = i2 + 1;
        this.ch = c;
        return true;
    }

    @Override // com.alibaba.fastjson2.JSONReader
    public final boolean nextIfValue4Match11(long j) {
        char[] cArr = this.chars;
        int i = this.offset;
        int i2 = i + 12;
        if (i2 >= this.end || getLong(cArr, i + 3) != j || cArr[i + 11] != '\"') {
            return false;
        }
        char c = cArr[i2];
        if (c != ',' && c != '}' && c != ']') {
            return false;
        }
        if (c == ',') {
            this.comma = true;
            i2 = i + 13;
            c = i2 == this.end ? JSONLexer.EOI : cArr[i2];
        }
        while (c <= ' ' && ((1 << c) & 4294981376L) != 0) {
            i2++;
            c = cArr[i2];
        }
        this.offset = i2 + 1;
        this.ch = c;
        return true;
    }
}
