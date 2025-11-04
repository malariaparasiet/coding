package com.wifiled.blelibrary.ble.utils;

import android.util.Log;
import com.alibaba.fastjson2.internal.asm.Opcodes;
import kotlin.UByte;
import org.bouncycastle.asn1.BERTags;

/* loaded from: classes2.dex */
public class CrcUtils {

    public static class CRC8 {
        public static int CRC8(byte[] bArr, int i, int i2) {
            int i3 = i2 + i;
            int i4 = 0;
            while (i < i3) {
                for (int i5 = 0; i5 < 8; i5++) {
                    boolean z = ((bArr[i] >> (7 - i5)) & 1) == 1;
                    boolean z2 = ((i4 >> 7) & 1) == 1;
                    i4 <<= 1;
                    if (z ^ z2) {
                        i4 ^= 7;
                    }
                }
                i++;
            }
            return i4 & 255;
        }

        public static int CRC8_DARC(byte[] bArr, int i, int i2) {
            int i3 = i2 + i;
            int i4 = 0;
            while (i < i3) {
                i4 = (int) (i4 ^ (bArr[i] & 255));
                for (int i5 = 0; i5 < 8; i5++) {
                    i4 = (i4 & 1) != 0 ? (i4 >> 1) ^ Opcodes.IFGE : i4 >> 1;
                }
                i++;
            }
            return i4;
        }

        public static int CRC8_ITU(byte[] bArr, int i, int i2) {
            int i3 = i2 + i;
            int i4 = 0;
            while (i < i3) {
                for (int i5 = 0; i5 < 8; i5++) {
                    boolean z = ((bArr[i] >> (7 - i5)) & 1) == 1;
                    boolean z2 = ((i4 >> 7) & 1) == 1;
                    i4 <<= 1;
                    if (z ^ z2) {
                        i4 ^= 7;
                    }
                }
                i++;
            }
            return (i4 & 255) ^ 85;
        }

        public static int CRC8_MAXIM(byte[] bArr, int i, int i2) {
            int i3 = i2 + i;
            int i4 = 0;
            while (i < i3) {
                i4 = (int) (i4 ^ (bArr[i] & 255));
                for (int i5 = 0; i5 < 8; i5++) {
                    i4 = (i4 & 1) != 0 ? (i4 >> 1) ^ Opcodes.F2L : i4 >> 1;
                }
                i++;
            }
            return i4;
        }

        public static int CRC8_ROHC(byte[] bArr, int i, int i2) {
            int i3 = i2 + i;
            int i4 = 255;
            while (i < i3) {
                i4 = (int) (i4 ^ (bArr[i] & 255));
                for (int i5 = 0; i5 < 8; i5++) {
                    i4 = (i4 & 1) != 0 ? (i4 >> 1) ^ BERTags.FLAGS : i4 >> 1;
                }
                i++;
            }
            return i4;
        }
    }

    public static class CRC16 {
        public static int CRC16_IBM(byte[] bArr, int i, int i2) {
            int i3 = i2 + i;
            int i4 = 0;
            while (i < i3) {
                i4 ^= bArr[i] & UByte.MAX_VALUE;
                for (int i5 = 0; i5 < 8; i5++) {
                    i4 = (i4 & 1) != 0 ? (i4 >> 1) ^ 40961 : i4 >> 1;
                }
                i++;
            }
            return i4;
        }

        public static int CRC16_CCITT(byte[] bArr, int i, int i2) {
            int i3 = i2 + i;
            int i4 = 0;
            while (i < i3) {
                i4 ^= bArr[i] & UByte.MAX_VALUE;
                for (int i5 = 0; i5 < 8; i5++) {
                    i4 = (i4 & 1) != 0 ? (i4 >> 1) ^ 33800 : i4 >> 1;
                }
                i++;
            }
            return i4;
        }

        public static int CRC16_CCITT_FALSE(byte[] bArr, int i, int i2) {
            int i3 = i2 + i;
            int i4 = 65535;
            while (i < i3) {
                for (int i5 = 0; i5 < 8; i5++) {
                    boolean z = ((bArr[i] >> (7 - i5)) & 1) == 1;
                    boolean z2 = ((i4 >> 15) & 1) == 1;
                    i4 <<= 1;
                    if (z ^ z2) {
                        i4 ^= 4129;
                    }
                }
                i++;
            }
            return i4 & 65535;
        }

        public static int CRC16_DECT_R(byte[] bArr, int i, int i2) {
            int i3 = i2 + i;
            int i4 = 0;
            while (i < i3) {
                for (int i5 = 0; i5 < 8; i5++) {
                    boolean z = ((bArr[i] >> (7 - i5)) & 1) == 1;
                    boolean z2 = ((i4 >> 15) & 1) == 1;
                    i4 <<= 1;
                    if (z ^ z2) {
                        i4 ^= 1417;
                    }
                }
                i++;
            }
            return (65535 & i4) ^ 1;
        }

        public static int CRC16_DECT_X(byte[] bArr, int i, int i2) {
            int i3 = i2 + i;
            int i4 = 0;
            while (i < i3) {
                for (int i5 = 0; i5 < 8; i5++) {
                    boolean z = ((bArr[i] >> (7 - i5)) & 1) == 1;
                    boolean z2 = ((i4 >> 15) & 1) == 1;
                    i4 <<= 1;
                    if (z ^ z2) {
                        i4 ^= 1417;
                    }
                }
                i++;
            }
            return 65535 & i4;
        }

        public static int CRC16_DNP(byte[] bArr, int i, int i2) {
            int i3 = i2 + i;
            int i4 = 0;
            while (i < i3) {
                i4 ^= bArr[i] & UByte.MAX_VALUE;
                for (int i5 = 0; i5 < 8; i5++) {
                    i4 = (i4 & 1) != 0 ? (i4 >> 1) ^ 42684 : i4 >> 1;
                }
                i++;
            }
            return 65535 ^ i4;
        }

        public static int CRC16_GENIBUS(byte[] bArr, int i, int i2) {
            int i3 = i2 + i;
            int i4 = 65535;
            while (i < i3) {
                for (int i5 = 0; i5 < 8; i5++) {
                    boolean z = ((bArr[i] >> (7 - i5)) & 1) == 1;
                    boolean z2 = ((i4 >> 15) & 1) == 1;
                    i4 <<= 1;
                    if (z ^ z2) {
                        i4 ^= 4129;
                    }
                }
                i++;
            }
            return (i4 & 65535) ^ 65535;
        }

        public static int CRC16_MAXIM(byte[] bArr, int i, int i2) {
            int i3 = i2 + i;
            int i4 = 0;
            while (i < i3) {
                i4 ^= bArr[i] & UByte.MAX_VALUE;
                for (int i5 = 0; i5 < 8; i5++) {
                    i4 = (i4 & 1) != 0 ? (i4 >> 1) ^ 40961 : i4 >> 1;
                }
                i++;
            }
            return 65535 ^ i4;
        }

        public static int CRC16_MODBUS(byte[] bArr, int i, int i2) {
            int i3 = i2 + i;
            int i4 = 65535;
            while (i < i3) {
                i4 ^= bArr[i] & UByte.MAX_VALUE;
                for (int i5 = 0; i5 < 8; i5++) {
                    i4 = (i4 & 1) != 0 ? (i4 >> 1) ^ 40961 : i4 >> 1;
                }
                i++;
            }
            return i4;
        }

        public static int CRC16_USB(byte[] bArr, int i, int i2) {
            int i3 = i2 + i;
            int i4 = 65535;
            while (i < i3) {
                i4 ^= bArr[i] & UByte.MAX_VALUE;
                for (int i5 = 0; i5 < 8; i5++) {
                    i4 = (i4 & 1) != 0 ? (i4 >> 1) ^ 40961 : i4 >> 1;
                }
                i++;
            }
            return i4 ^ 65535;
        }

        public static int CRC16_X25(byte[] bArr, int i, int i2) {
            int i3 = i2 + i;
            int i4 = 65535;
            while (i < i3) {
                i4 ^= bArr[i] & UByte.MAX_VALUE;
                for (int i5 = 0; i5 < 8; i5++) {
                    i4 = (i4 & 1) != 0 ? (i4 >> 1) ^ 33800 : i4 >> 1;
                }
                i++;
            }
            return i4 ^ 65535;
        }

        public static int CRC16_XMODEM(byte[] bArr, int i, int i2) {
            int i3 = i2 + i;
            int i4 = 0;
            while (i < i3) {
                for (int i5 = 0; i5 < 8; i5++) {
                    boolean z = ((bArr[i] >> (7 - i5)) & 1) == 1;
                    boolean z2 = ((i4 >> 15) & 1) == 1;
                    i4 <<= 1;
                    if (z ^ z2) {
                        i4 ^= 4129;
                    }
                }
                i++;
            }
            return 65535 & i4;
        }
    }

    public static class CRC32 {
        public static long CRC32(byte[] bArr, int i, int i2) {
            java.util.zip.CRC32 crc32 = new java.util.zip.CRC32();
            crc32.update(bArr);
            Log.d("#1.1# CRC32 src: ", String.valueOf(crc32.getValue()));
            return crc32.getValue();
        }

        public static long CRC32_B(byte[] bArr, int i, int i2) {
            int i3 = i2 + i;
            long j = 4294967295L;
            while (i < i3) {
                for (int i4 = 0; i4 < 8; i4++) {
                    boolean z = ((bArr[i] >> (7 - i4)) & 1) == 1;
                    boolean z2 = ((j >> 31) & 1) == 1;
                    j <<= 1;
                    if (z ^ z2) {
                        j ^= 79764919;
                    }
                }
                i++;
            }
            return (j & 4294967295L) ^ 4294967295L;
        }

        public static long CRC32_C(byte[] bArr, int i, int i2) {
            int i3 = i2 + i;
            long j = 4294967295L;
            while (i < i3) {
                j ^= bArr[i] & 255;
                for (int i4 = 0; i4 < 8; i4++) {
                    j = (1 & j) != 0 ? (j >> 1) ^ 2197175160L : j >> 1;
                }
                i++;
            }
            return j ^ 4294967295L;
        }

        public static long CRC32_D(byte[] bArr, int i, int i2) {
            int i3 = i2 + i;
            long j = 4294967295L;
            while (i < i3) {
                j ^= bArr[i] & 255;
                for (int i4 = 0; i4 < 8; i4++) {
                    j = (1 & j) != 0 ? (j >> 1) ^ 3558460437L : j >> 1;
                }
                i++;
            }
            return j ^ 4294967295L;
        }

        public static long CRC32_MPEG_2(byte[] bArr, int i, int i2) {
            int i3 = i2 + i;
            long j = 4294967295L;
            while (i < i3) {
                for (int i4 = 0; i4 < 8; i4++) {
                    boolean z = ((bArr[i] >> (7 - i4)) & 1) == 1;
                    boolean z2 = ((j >> 31) & 1) == 1;
                    j <<= 1;
                    if (z ^ z2) {
                        j ^= 79764919;
                    }
                }
                i++;
            }
            return j & 4294967295L;
        }

        public static long CRC32_POSIX(byte[] bArr, int i, int i2) {
            int i3 = i2 + i;
            long j = 0;
            while (i < i3) {
                for (int i4 = 0; i4 < 8; i4++) {
                    boolean z = ((bArr[i] >> (7 - i4)) & 1) == 1;
                    boolean z2 = ((j >> 31) & 1) == 1;
                    j <<= 1;
                    if (z ^ z2) {
                        j ^= 79764919;
                    }
                }
                i++;
            }
            return 4294967295L ^ (j & 4294967295L);
        }
    }
}
