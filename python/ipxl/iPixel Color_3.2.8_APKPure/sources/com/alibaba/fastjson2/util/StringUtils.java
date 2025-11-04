package com.alibaba.fastjson2.util;

import androidx.collection.ScatterMapKt;
import com.alibaba.fastjson2.JSONWriter;
import java.nio.charset.StandardCharsets;
import kotlin.UByte;
import org.bouncycastle.asn1.BERTags;

/* loaded from: classes2.dex */
public class StringUtils {
    protected static final long MASK_ESCAPE_NONE_ASCII = JSONWriter.Feature.EscapeNoneAscii.mask;
    protected static final long MASK_BROWSER_SECURE = JSONWriter.Feature.BrowserSecure.mask;

    public static boolean noneEscaped(long j, long j2) {
        return ((6944656592455360608L + j) & (-9187201950435737472L)) == -9187201950435737472L && (((j2 ^ j) + ScatterMapKt.BitmaskLsb) & (-9187201950435737472L)) == -9187201950435737472L && (((j ^ (-6655295901103053917L)) + ScatterMapKt.BitmaskLsb) & (-9187201950435737472L)) == -9187201950435737472L;
    }

    public static int writeLatin1(byte[] bArr, int i, byte[] bArr2, byte b) {
        int length = bArr2.length;
        bArr[i] = b;
        System.arraycopy(bArr2, 0, bArr, i + 1, length);
        int i2 = i + length;
        bArr[i2 + 1] = b;
        return i2 + 2;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:17:0x002b. Please report as an issue. */
    public static int writeLatin1Escaped(byte[] bArr, int i, byte[] bArr2, byte b, long j) {
        int i2;
        boolean z = (j & MASK_BROWSER_SECURE) != 0;
        int i3 = i + 1;
        bArr[i] = b;
        for (byte b2 : bArr2) {
            if (b2 != 40 && b2 != 41 && b2 != 60 && b2 != 62) {
                if (b2 != 92) {
                    switch (b2) {
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
                            writeU4Hex2(bArr, i3, b2);
                            i3 += 6;
                            break;
                        case 8:
                        case 9:
                        case 10:
                        case 12:
                        case 13:
                            break;
                        default:
                            if (b2 == b) {
                                bArr[i3] = 92;
                                bArr[i3 + 1] = b;
                            } else if (b2 < 0) {
                                bArr[i3] = (byte) (((b2 & UByte.MAX_VALUE) >> 6) | 192);
                                bArr[i3 + 1] = (byte) ((b2 & 63) | 128);
                            } else {
                                i2 = i3 + 1;
                                bArr[i3] = b2;
                                i3 = i2;
                                break;
                            }
                            i3 += 2;
                            break;
                    }
                }
                writeEscapedChar(bArr, i3, b2);
                i3 += 2;
            } else if (z) {
                writeU4HexU(bArr, i3, b2);
                i3 += 6;
            } else {
                i2 = i3 + 1;
                bArr[i3] = b2;
                i3 = i2;
            }
        }
        bArr[i3] = b;
        return i3 + 1;
    }

    public static int writeLatin1EscapedRest(char[] cArr, int i, byte[] bArr, int i2, char c, long j) {
        int i3;
        boolean z = (JSONWriter.Feature.EscapeNoneAscii.mask & j) != 0;
        boolean z2 = (j & JSONWriter.Feature.BrowserSecure.mask) != 0;
        while (i2 < bArr.length) {
            char c2 = (char) (bArr[i2] & UByte.MAX_VALUE);
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
                                writeU4Hex2(cArr, i, c2);
                                i += 6;
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
                                            writeU4HexU(cArr, i, c2);
                                            break;
                                        } else {
                                            i3 = i + 1;
                                            cArr[i] = c2;
                                            i = i3;
                                            break;
                                        }
                                        break;
                                }
                                i += 6;
                                break;
                        }
                        i2++;
                    }
                    writeEscapedChar(cArr, i, c2);
                    i += 2;
                    i2++;
                }
                if (z2) {
                    writeU4HexU(cArr, i, c2);
                    i += 6;
                    i2++;
                } else {
                    i3 = i + 1;
                    cArr[i] = c2;
                    i = i3;
                    i2++;
                }
            }
            if (c2 == c) {
                cArr[i] = '\\';
                i++;
            }
            i3 = i + 1;
            cArr[i] = c2;
            i = i3;
            i2++;
        }
        cArr[i] = c;
        return i + 1;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:21:0x0046. Please report as an issue. */
    public static int writeUTF16(byte[] bArr, int i, byte[] bArr2, byte b, long j) {
        int i2;
        int i3;
        int i4 = 0;
        boolean z = (j & MASK_ESCAPE_NONE_ASCII) != 0;
        boolean z2 = (j & MASK_BROWSER_SECURE) != 0;
        int i5 = i + 1;
        bArr[i] = b;
        int length = bArr2.length >> 1;
        while (i4 < length) {
            int i6 = i4 + 1;
            byte b2 = IOUtils.getChar(bArr2, i4);
            if (b2 < 128) {
                if (b2 != 40 && b2 != 41 && b2 != 60 && b2 != 62) {
                    if (b2 != 92) {
                        switch (b2) {
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
                                writeU4Hex2(bArr, i5, b2);
                                i5 += 6;
                                break;
                            case 8:
                            case 9:
                            case 10:
                            case 12:
                            case 13:
                                break;
                            default:
                                if (b2 == b) {
                                    bArr[i5] = 92;
                                    bArr[i5 + 1] = b;
                                    i5 += 2;
                                    break;
                                } else {
                                    i2 = i5 + 1;
                                    bArr[i5] = b2;
                                    i5 = i2;
                                    break;
                                }
                        }
                    }
                    writeEscapedChar(bArr, i5, b2);
                    i5 += 2;
                } else if (z2) {
                    writeU4HexU(bArr, i5, b2);
                    i5 += 6;
                } else {
                    i2 = i5 + 1;
                    bArr[i5] = b2;
                    i5 = i2;
                }
            } else if (b2 < 2048) {
                bArr[i5] = (byte) ((b2 >> 6) | 192);
                bArr[i5 + 1] = (byte) ((b2 & 63) | 128);
                i5 += 2;
            } else if (z) {
                writeU4HexU(bArr, i5, b2);
                i5 += 6;
            } else if (b2 < 55296 || b2 >= 57344) {
                bArr[i5] = (byte) ((b2 >> 12) | BERTags.FLAGS);
                bArr[i5 + 1] = (byte) (((b2 >> 6) & 63) | 128);
                bArr[i5 + 2] = (byte) ((b2 & 63) | 128);
                i5 += 3;
            } else {
                if (b2 < 56320) {
                    int i7 = i4 + 2;
                    if (i7 > length) {
                        i3 = -1;
                    } else {
                        char c = IOUtils.getChar(bArr2, i6);
                        if (c < 56320 || c >= 57344) {
                            i2 = i5 + 1;
                            bArr[i5] = 63;
                        } else {
                            i6 = i7;
                            i3 = ((b2 << 10) + c) - 56613888;
                        }
                    }
                    if (i3 < 0) {
                        i2 = i5 + 1;
                        bArr[i5] = 63;
                    } else {
                        bArr[i5] = (byte) ((i3 >> 18) | 240);
                        bArr[i5 + 1] = (byte) (((i3 >> 12) & 63) | 128);
                        bArr[i5 + 2] = (byte) (((i3 >> 6) & 63) | 128);
                        bArr[i5 + 3] = (byte) ((i3 & 63) | 128);
                        i2 = i5 + 4;
                    }
                } else {
                    i2 = i5 + 1;
                    bArr[i5] = 63;
                }
                i5 = i2;
            }
            i4 = i6;
        }
        bArr[i5] = b;
        return i5 + 1;
    }

    public static void writeEscapedChar(byte[] bArr, int i, int i2) {
        IOUtils.putShortLE(bArr, i, LATIN1.ESCAPED_CHARS[i2 & 127]);
    }

    public static void writeU4Hex2(byte[] bArr, int i, int i2) {
        IOUtils.putIntUnaligned(bArr, i, LATIN1.U4);
        IOUtils.putShortLE(bArr, i + 4, IOUtils.hex2(i2));
    }

    public static void writeU4HexU(byte[] bArr, int i, int i2) {
        IOUtils.putShortUnaligned(bArr, i, LATIN1.U2);
        IOUtils.putIntLE(bArr, i + 2, IOUtils.hex4U(i2));
    }

    public static void writeEscapedChar(char[] cArr, int i, int i2) {
        IOUtils.putIntUnaligned(cArr, i, UTF16.ESCAPED_CHARS[i2 & 127]);
    }

    public static void writeU4Hex2(char[] cArr, int i, int i2) {
        IOUtils.putLongUnaligned(cArr, i, UTF16.U4);
        IOUtils.putIntLE(cArr, i + 4, IOUtils.utf16Hex2(i2));
    }

    public static void writeU4HexU(char[] cArr, int i, int i2) {
        IOUtils.putIntUnaligned(cArr, i, UTF16.U2);
        IOUtils.putLongLE(cArr, i + 2, IOUtils.utf16Hex4U(i2));
    }

    public static boolean escaped(byte[] bArr, byte b, long j) {
        int length = bArr.length & (-8);
        int i = 0;
        while (i < length) {
            if (!noneEscaped(IOUtils.getLongUnaligned(bArr, i), j)) {
                return true;
            }
            i += 8;
        }
        while (i < bArr.length) {
            byte b2 = bArr[i];
            if (b2 == b || b2 == 92 || b2 < 32) {
                return true;
            }
            i++;
        }
        return false;
    }

    public static final class LATIN1 {
        private static final short[] ESCAPED_CHARS;
        private static final short U2;
        private static final int U4;

        static {
            byte[] bytes = "\\u00".getBytes(StandardCharsets.UTF_8);
            U2 = JDKUtils.UNSAFE.getShort(bytes, JDKUtils.ARRAY_BYTE_BASE_OFFSET);
            U4 = JDKUtils.UNSAFE.getInt(bytes, JDKUtils.ARRAY_BYTE_BASE_OFFSET);
            short[] sArr = new short[128];
            sArr[92] = (short) 23644;
            sArr[10] = (short) 28252;
            sArr[13] = (short) 29276;
            sArr[12] = (short) 26204;
            sArr[8] = (short) 25180;
            sArr[9] = (short) 29788;
            ESCAPED_CHARS = sArr;
        }
    }

    public static final class UTF16 {
        private static final int[] ESCAPED_CHARS;
        private static final int U2;
        private static final long U4;

        static {
            char[] charArray = "\\u00".toCharArray();
            U2 = JDKUtils.UNSAFE.getInt(charArray, JDKUtils.ARRAY_BYTE_BASE_OFFSET);
            U4 = JDKUtils.UNSAFE.getLong(charArray, JDKUtils.ARRAY_BYTE_BASE_OFFSET);
            char[] cArr = {'\\', '\\', '\n', 'n', '\r', 'r', '\f', 'f', '\b', 'b', '\t', 't'};
            char[] cArr2 = {'\\', 0};
            int[] iArr = new int[128];
            for (int i = 0; i < 12; i += 2) {
                cArr2[1] = cArr[i + 1];
                iArr[cArr[i]] = IOUtils.getIntUnaligned(cArr2, 0);
            }
            ESCAPED_CHARS = iArr;
        }
    }
}
