package com.alibaba.fastjson2;

import com.alibaba.fastjson2.JSONB;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.util.Fnv;
import com.alibaba.fastjson2.util.IOUtils;
import com.alibaba.fastjson2.util.JDKUtils;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import kotlin.UByte;
import okhttp3.internal.ws.WebSocketProtocol;

/* loaded from: classes2.dex */
final class JSONReaderASCII extends JSONReaderUTF8 {
    final String str;

    JSONReaderASCII(JSONReader.Context context, String str, byte[] bArr, int i, int i2) {
        super(context, bArr, i, i2);
        this.str = str;
        this.nameAscii = true;
    }

    JSONReaderASCII(JSONReader.Context context, InputStream inputStream) {
        super(context, inputStream);
        this.nameAscii = true;
        this.str = null;
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX WARN: Removed duplicated region for block: B:23:0x003d A[LOOP:0: B:6:0x0011->B:23:0x003d, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x000b A[EDGE_INSN: B:24:0x000b->B:4:0x000b BREAK  A[LOOP:0: B:6:0x0011->B:23:0x003d], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013 A[ADDED_TO_REGION] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x003a -> B:4:0x000b). Please report as a decompilation issue!!! */
    @Override // com.alibaba.fastjson2.JSONReaderUTF8, com.alibaba.fastjson2.JSONReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void next() {
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
            if (r1 == 0) goto L38
            if (r1 <= 0) goto L29
            r4 = 32
            if (r1 > r4) goto L29
            r4 = 1
            long r4 = r4 << r1
            r6 = 4294981376(0x100003700, double:2.1220027474E-314)
            long r4 = r4 & r6
            r6 = 0
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 == 0) goto L29
            goto L38
        L29:
            r9.offset = r2
            r0 = r1 & 255(0xff, float:3.57E-43)
            char r0 = (char) r0
            r9.ch = r0
            r0 = 47
            if (r1 != r0) goto L37
            r9.skipComment()
        L37:
            return
        L38:
            int r1 = r9.end
            if (r2 != r1) goto L3d
            goto Lb
        L3d:
            int r1 = r2 + 1
            r2 = r0[r2]
            r8 = r2
            r2 = r1
            r1 = r8
            goto L11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderASCII.next():void");
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:20:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0044 A[LOOP:0: B:10:0x0019->B:24:0x0044, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0013 A[EDGE_INSN: B:25:0x0013->B:8:0x0013 BREAK  A[LOOP:0: B:10:0x0019->B:24:0x0044], SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x0041 -> B:9:0x0013). Please report as a decompilation issue!!! */
    @Override // com.alibaba.fastjson2.JSONReaderUTF8, com.alibaba.fastjson2.JSONReader
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
            if (r1 == 0) goto L3f
            r4 = 32
            if (r1 > r4) goto L2f
            r4 = 1
            long r4 = r4 << r1
            r6 = 4294981376(0x100003700, double:2.1220027474E-314)
            long r4 = r4 & r6
            r6 = 0
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 == 0) goto L2f
            goto L3f
        L2f:
            r0 = r1 & 255(0xff, float:3.57E-43)
            char r0 = (char) r0
            r9.ch = r0
            r9.offset = r2
            r0 = 47
            if (r1 != r0) goto L3d
            r9.skipComment()
        L3d:
            r0 = 1
            return r0
        L3f:
            int r1 = r9.end
            if (r2 != r1) goto L44
            goto L13
        L44:
            int r1 = r2 + 1
            r2 = r0[r2]
            r8 = r2
            r2 = r1
            r1 = r8
            goto L19
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderASCII.nextIfObjectStart():boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0063 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x006f A[LOOP:0: B:17:0x0056->B:23:0x006f, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0050 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0084 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x009d A[SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x006c -> B:15:0x0050). Please report as a decompilation issue!!! */
    @Override // com.alibaba.fastjson2.JSONReaderUTF8, com.alibaba.fastjson2.JSONReader
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
            if (r5 >= r2) goto Laa
            boolean r8 = com.alibaba.fastjson2.util.IOUtils.isNULL(r4, r3)
            if (r8 == 0) goto Laa
            r5 = r4[r5]
            if (r5 != r1) goto Laa
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
            r0.offset = r5
            r1 = r3 & 255(0xff, float:3.57E-43)
            char r1 = (char) r1
            r0.ch = r1
            return r7
        Laa:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderASCII.nextIfNullOrEmptyString():boolean");
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:127:0x02c9. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:104:0x03b4  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x03e1  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x038e  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0251  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x029a  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0331  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x038c  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0397  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x03a6  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x03a8  */
    @Override // com.alibaba.fastjson2.JSONReaderUTF8, com.alibaba.fastjson2.JSONReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final long readFieldNameHashCode() {
        /*
            Method dump skipped, instructions count: 1056
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderASCII.readFieldNameHashCode():long");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:145:0x0237  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0136  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x016f A[FALL_THROUGH, PHI: r9
      0x016f: PHI (r9v12 int) = (r9v4 int), (r9v4 int), (r9v4 int), (r9v13 int) binds: [B:67:0x0163, B:68:0x0165, B:69:0x0168, B:70:0x016b] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x01a8  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01ab  */
    @Override // com.alibaba.fastjson2.JSONReaderUTF8, com.alibaba.fastjson2.JSONReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final long readFieldNameHashCodeUnquote() {
        /*
            Method dump skipped, instructions count: 764
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderASCII.readFieldNameHashCodeUnquote():long");
    }

    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r4v6 */
    @Override // com.alibaba.fastjson2.JSONReaderUTF8, com.alibaba.fastjson2.JSONReader
    public final long readValueHashCode() {
        byte b;
        boolean z;
        long j;
        int i;
        int i2;
        int i3;
        byte b2;
        long j2;
        long j3;
        char c = this.ch;
        if (c != '\"' && c != '\'') {
            return -1L;
        }
        byte[] bArr = this.bytes;
        ?? r4 = 1;
        this.nameAscii = true;
        this.nameEscape = false;
        int i4 = this.offset;
        this.nameBegin = i4;
        int i5 = 0;
        long j4 = 0;
        while (true) {
            if (i4 < this.end) {
                byte b3 = bArr[i4];
                int i6 = b3 & UByte.MAX_VALUE;
                if (i6 != c) {
                    if (i6 == 92) {
                        this.nameEscape = true;
                        int i7 = i4 + 1;
                        byte b4 = bArr[i7];
                        if (b4 == 117) {
                            i6 = IOUtils.hexDigit4(bArr, JSONReaderJSONB.check3(i4 + 2, this.end));
                            i4 += 5;
                        } else if (b4 == 120) {
                            byte b5 = bArr[i4 + 2];
                            i4 += 3;
                            i6 = char2(b5, bArr[i4]);
                        } else {
                            i6 = char1(b4);
                            i4 = i7;
                        }
                        b = 32;
                    } else {
                        b = 32;
                        if (i6 == -61 || i6 == -62) {
                            i4++;
                            i6 = (char) (((b3 & 31) << 6) | (bArr[i4] & 63));
                        }
                    }
                    if (i6 <= 255 && i6 >= 0 && i5 < 8 && (i5 != 0 || i6 != 0)) {
                        switch (i5) {
                            case 0:
                                j4 = (byte) i6;
                                continue;
                            case 1:
                                j2 = ((byte) i6) << 8;
                                j3 = 255;
                                break;
                            case 2:
                                j2 = ((byte) i6) << JSONB.Constants.BC_INT32_NUM_16;
                                j3 = WebSocketProtocol.PAYLOAD_SHORT_MAX;
                                break;
                            case 3:
                                j2 = ((byte) i6) << 24;
                                j3 = 16777215;
                                break;
                            case 4:
                                j2 = ((byte) i6) << b;
                                j3 = 4294967295L;
                                break;
                            case 5:
                                j2 = ((byte) i6) << 40;
                                j3 = 1099511627775L;
                                break;
                            case 6:
                                j2 = ((byte) i6) << 48;
                                j3 = 281474976710655L;
                                break;
                            case 7:
                                j2 = ((byte) i6) << 56;
                                j3 = 72057594037927935L;
                                break;
                        }
                        j4 = (j4 & j3) + j2;
                        i4++;
                        i5++;
                    }
                } else if (i5 == 0) {
                    i4 = this.nameBegin;
                    j4 = 0;
                } else {
                    this.nameLength = i5;
                    this.nameEnd = i4;
                    i4++;
                }
            }
        }
        b = 32;
        if (j4 != 0) {
            z = true;
        } else {
            j4 = -3750763034362895579L;
            int i8 = 0;
            while (true) {
                byte b6 = bArr[i4];
                if (b6 == 92) {
                    this.nameEscape = r4;
                    int i9 = i4 + 1;
                    byte b7 = bArr[i9];
                    if (b7 == 117) {
                        int hexDigit4 = IOUtils.hexDigit4(bArr, JSONReaderJSONB.check3(i4 + 2, this.end));
                        i = i4 + 5;
                        i2 = hexDigit4;
                    } else if (b7 == 120) {
                        byte b8 = bArr[i4 + 2];
                        i = i4 + 3;
                        i2 = char2(b8, bArr[i]);
                    } else {
                        i3 = char1(b7);
                        int i10 = i9 + r4;
                        z = r4;
                        j = (i3 ^ j4) * Fnv.MAGIC_PRIME;
                        i4 = i10;
                    }
                    int i11 = i;
                    i3 = i2;
                    i9 = i11;
                    int i102 = i9 + r4;
                    z = r4;
                    j = (i3 ^ j4) * Fnv.MAGIC_PRIME;
                    i4 = i102;
                } else {
                    z = r4;
                    if (b6 == 34) {
                        this.nameLength = i8;
                        this.nameEnd = i4;
                        i4 += z ? 1 : 0;
                    } else {
                        i4++;
                        j = (b6 ^ j4) * Fnv.MAGIC_PRIME;
                    }
                }
                j4 = j;
                i8++;
                r4 = z;
            }
        }
        if (i4 == this.end) {
            b2 = 26;
        } else {
            b2 = bArr[i4];
            i4++;
        }
        while (b2 <= b && ((1 << b2) & 4294981376L) != 0) {
            if (i4 == this.end) {
                b2 = 26;
            } else {
                b2 = bArr[i4];
                i4++;
            }
            b = 32;
        }
        if (b2 != 44) {
            z = false;
        }
        this.comma = z;
        if (z) {
            if (i4 == this.end) {
                b2 = 26;
            } else {
                b2 = bArr[i4];
                i4++;
            }
            while (b2 <= 32 && ((1 << b2) & 4294981376L) != 0) {
                if (i4 == this.end) {
                    b2 = 26;
                } else {
                    byte b9 = bArr[i4];
                    i4++;
                    b2 = b9;
                }
            }
        }
        this.offset = i4;
        this.ch = (char) (b2 & UByte.MAX_VALUE);
        return j4;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x00e4 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00e5  */
    @Override // com.alibaba.fastjson2.JSONReaderUTF8, com.alibaba.fastjson2.JSONReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final long getNameHashCodeLCase() {
        /*
            Method dump skipped, instructions count: 362
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderASCII.getNameHashCodeLCase():long");
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x00a7  */
    @Override // com.alibaba.fastjson2.JSONReaderUTF8, com.alibaba.fastjson2.JSONReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String getFieldName() {
        /*
            Method dump skipped, instructions count: 316
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderASCII.getFieldName():java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:68:0x055a  */
    @Override // com.alibaba.fastjson2.JSONReaderUTF8, com.alibaba.fastjson2.JSONReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String readFieldName() {
        /*
            Method dump skipped, instructions count: 1588
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderASCII.readFieldName():java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00a6 A[LOOP:0: B:37:0x0091->B:42:0x00a6, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x008b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00b6  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:40:0x00a3 -> B:35:0x008b). Please report as a decompilation issue!!! */
    @Override // com.alibaba.fastjson2.JSONReaderUTF8, com.alibaba.fastjson2.JSONReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String readString() {
        /*
            Method dump skipped, instructions count: 245
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderASCII.readString():java.lang.String");
    }

    @Override // com.alibaba.fastjson2.JSONReaderUTF8
    protected final void readString0() {
        String str;
        byte[] bArr = this.bytes;
        byte b = this.ch;
        int i = this.offset;
        int i2 = this.offset;
        this.valueEscape = false;
        int i3 = 0;
        while (true) {
            byte b2 = bArr[i2];
            if (b2 == 92) {
                this.valueEscape = true;
                byte b3 = bArr[i2 + 1];
                i2 += b3 == 117 ? 6 : b3 == 120 ? 4 : 2;
            } else if (b2 == b) {
                break;
            } else {
                i2++;
            }
            i3++;
        }
        if (this.valueEscape) {
            char[] cArr = new char[i3];
            int i4 = 0;
            while (true) {
                char c = (char) (bArr[i] & UByte.MAX_VALUE);
                if (c == '\\') {
                    int i5 = i + 1;
                    char c2 = (char) bArr[i5];
                    if (c2 == '\"' || c2 == '\\') {
                        i = i5;
                        c = c2;
                    } else if (c2 == 'u') {
                        c = (char) IOUtils.hexDigit4(bArr, JSONReaderJSONB.check3(i + 2, this.end));
                        i += 5;
                    } else if (c2 == 'x') {
                        byte b4 = bArr[i + 2];
                        i += 3;
                        c = char2(b4, bArr[i]);
                    } else {
                        c = char1(c2);
                        i = i5;
                    }
                } else if (c == '\"') {
                    break;
                }
                cArr[i4] = c;
                i++;
                i4++;
            }
            str = new String(cArr);
            i2 = i;
        } else if (JDKUtils.STRING_CREATOR_JDK11 != null) {
            str = JDKUtils.STRING_CREATOR_JDK11.apply(Arrays.copyOfRange(bArr, i, i2), JDKUtils.LATIN1);
        } else {
            str = new String(bArr, i, i2 - i, StandardCharsets.ISO_8859_1);
        }
        int i6 = i2 + 1;
        byte b5 = bArr[i6];
        while (b5 > 0 && b5 <= 32 && ((1 << b5) & 4294981376L) != 0) {
            i6++;
            b5 = bArr[i6];
        }
        this.offset = i6 + 1;
        boolean z = b5 == 44;
        this.comma = z;
        if (z) {
            next();
        } else {
            this.ch = (char) b5;
        }
        this.stringValue = str;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x006a A[LOOP:1: B:19:0x0055->B:24:0x006a, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0050 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x007a  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x0067 -> B:19:0x0050). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    final java.lang.String readEscaped(byte[] r18, int r19, int r20, int r21, int r22, int r23) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r2 = r21
            r3 = r23
            r4 = r19
            r5 = r22
        Lc:
            if (r4 >= r2) goto Laf
            r6 = r1[r4]
            r7 = 92
            r8 = 2
            if (r6 != r7) goto L28
            int r5 = r5 + 1
            int r6 = r4 + 1
            r6 = r1[r6]
            r7 = 117(0x75, float:1.64E-43)
            if (r6 != r7) goto L21
            r8 = 6
            goto L26
        L21:
            r7 = 120(0x78, float:1.68E-43)
            if (r6 != r7) goto L26
            r8 = 4
        L26:
            int r4 = r4 + r8
            goto Lc
        L28:
            if (r6 != r3) goto La7
            char[] r4 = new char[r5]
            r6 = r20
            int r3 = r0.readEscaped(r1, r6, r3, r4)
            java.lang.String r5 = new java.lang.String
            r5.<init>(r4)
            com.alibaba.fastjson2.JSONReader$Context r4 = r0.context
            long r6 = r4.features
            r9 = 134234112(0x8004000, double:6.6320463E-316)
            long r9 = r9 & r6
            r11 = 0
            int r4 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r4 == 0) goto L49
            java.lang.String r5 = stringValue(r5, r6)
        L49:
            int r4 = r3 + 1
            r6 = 26
            if (r4 != r2) goto L52
            r3 = r4
        L50:
            r4 = r6
            goto L55
        L52:
            int r3 = r3 + r8
            r4 = r1[r4]
        L55:
            r7 = 4294981376(0x100003700, double:2.1220027474E-314)
            r9 = 1
            r13 = 32
            if (r4 > r13) goto L74
            long r14 = r9 << r4
            long r14 = r14 & r7
            int r14 = (r14 > r11 ? 1 : (r14 == r11 ? 0 : -1))
            if (r14 == 0) goto L74
            if (r3 != r2) goto L6a
            goto L50
        L6a:
            int r4 = r3 + 1
            r3 = r1[r3]
            r16 = r4
            r4 = r3
            r3 = r16
            goto L55
        L74:
            r14 = 44
            if (r4 != r14) goto L7a
            r14 = 1
            goto L7b
        L7a:
            r14 = 0
        L7b:
            r0.comma = r14
            if (r14 == 0) goto L9f
            if (r3 != r2) goto L84
            r4 = r3
            r3 = r6
            goto L88
        L84:
            int r4 = r3 + 1
            r3 = r1[r3]
        L88:
            r16 = r4
            r4 = r3
            r3 = r16
        L8d:
            if (r4 > r13) goto L9f
            long r14 = r9 << r4
            long r14 = r14 & r7
            int r14 = (r14 > r11 ? 1 : (r14 == r11 ? 0 : -1))
            if (r14 == 0) goto L9f
            if (r3 != r2) goto L9a
            r4 = r6
            goto L8d
        L9a:
            int r4 = r3 + 1
            r3 = r1[r3]
            goto L88
        L9f:
            r1 = r4 & 255(0xff, float:3.57E-43)
            char r1 = (char) r1
            r0.ch = r1
            r0.offset = r3
            return r5
        La7:
            r6 = r20
            int r4 = r4 + 1
            int r5 = r5 + 1
            goto Lc
        Laf:
            java.lang.String r1 = "invalid escape character EOI"
            com.alibaba.fastjson2.JSONException r1 = r0.error(r1)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson2.JSONReaderASCII.readEscaped(byte[], int, int, int, int, int):java.lang.String");
    }

    protected final int readEscaped(byte[] bArr, int i, int i2, char[] cArr) {
        char c;
        int i3 = 0;
        while (true) {
            char c2 = (char) (bArr[i] & UByte.MAX_VALUE);
            if (c2 == '\\') {
                int i4 = i + 1;
                char c3 = (char) bArr[i4];
                if (c3 == '\"' || c3 == '\\') {
                    i = i4;
                    c2 = c3;
                } else {
                    if (c3 == 'b') {
                        c = '\b';
                    } else if (c3 == 'f') {
                        c = '\f';
                    } else if (c3 == 'n') {
                        c = '\n';
                    } else if (c3 == 'r') {
                        c = '\r';
                    } else if (c3 == 'x') {
                        byte b = bArr[i + 2];
                        i += 3;
                        c2 = char2(b, bArr[i]);
                    } else if (c3 == 't') {
                        c = '\t';
                    } else if (c3 == 'u') {
                        c2 = (char) IOUtils.hexDigit4(bArr, JSONReaderJSONB.check3(i + 2, this.end));
                        i += 5;
                    } else {
                        c = char1(c3);
                    }
                    c2 = c;
                    i = i4;
                }
            } else if (c2 == i2) {
                return i;
            }
            cArr[i3] = c2;
            i++;
            i3++;
        }
    }

    static JSONReaderASCII of(JSONReader.Context context, String str, byte[] bArr, int i, int i2) {
        return new JSONReaderASCII(context, str, bArr, i, i2);
    }

    static JSONReaderASCII of(JSONReader.Context context, InputStream inputStream) {
        return new JSONReaderASCII(context, inputStream);
    }
}
