package org.bouncycastle.crypto.digests;

import androidx.compose.runtime.ComposerKt;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.alibaba.fastjson2.internal.asm.Opcodes;
import com.jieli.jl_bt_ota.constant.Command;
import kotlin.jvm.internal.ByteCompanionObject;
import org.bouncycastle.asn1.BERTags;
import org.bouncycastle.crypto.CryptoServicePurpose;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.math.Primes;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Memoable;
import org.bouncycastle.util.Pack;

/* loaded from: classes3.dex */
public final class WhirlpoolDigest implements ExtendedDigest, Memoable {
    private static final int BITCOUNT_ARRAY_SIZE = 32;
    private static final int BYTE_LENGTH = 64;
    private static final int DIGEST_LENGTH_BYTES = 64;
    private static final short[] EIGHT;
    private static final int REDUCTION_POLYNOMIAL = 285;
    private static final int ROUNDS = 10;
    private long[] _K;
    private long[] _L;
    private short[] _bitCount;
    private long[] _block;
    private byte[] _buffer;
    private int _bufferPos;
    private long[] _hash;
    private final long[] _rc;
    private long[] _state;
    private final CryptoServicePurpose purpose;
    private static final int[] SBOX = {24, 35, Opcodes.IFNULL, Command.CMD_OTA_NOTIFY_UPDATE_CONTENT_SIZE, Opcodes.I2D, Opcodes.INVOKESTATIC, 1, 79, 54, Opcodes.IF_ACMPNE, 210, 245, 121, 111, Opcodes.I2B, 82, 96, 188, Opcodes.IFLT, Opcodes.D2I, Opcodes.IF_ICMPGT, 12, 123, 53, 29, BERTags.FLAGS, 215, 194, 46, 75, 254, 87, 21, 119, 55, Command.CMD_OTA_SEND_FIRMWARE_UPDATE_BLOCK, Opcodes.IF_ICMPEQ, 240, 74, 218, 88, ComposerKt.providerKey, 41, 10, Opcodes.RETURN, Opcodes.IF_ICMPNE, 107, Opcodes.I2L, Opcodes.ANEWARRAY, 93, 16, 244, ComposerKt.providerValuesKey, 62, 5, 103, Command.CMD_OTA_EXIT_UPDATE_MODE, 39, 65, Opcodes.F2I, Opcodes.GOTO, 125, Opcodes.FCMPL, 216, 251, 238, 124, 102, 221, 23, 71, Opcodes.IFLE, ComposerKt.compositionLocalMapKey, 45, Opcodes.ATHROW, 7, Opcodes.LRETURN, 90, Opcodes.LXOR, 51, 99, 2, Opcodes.TABLESWITCH, 113, 200, 25, 73, 217, 242, Command.CMD_OTA_ENTER_UPDATE_MODE, 91, Opcodes.L2I, Opcodes.IFNE, 38, 50, Opcodes.ARETURN, 233, 15, 213, 128, Opcodes.ARRAYLENGTH, 205, 52, 72, 255, 122, 144, 95, 32, 104, 26, Opcodes.FRETURN, Opcodes.GETFIELD, 84, Opcodes.I2S, 34, 100, 241, 115, 18, 64, 8, 195, 236, 219, Opcodes.IF_ICMPLT, 141, 61, Opcodes.DCMPL, 0, ComposerKt.reuseKey, 43, 118, Opcodes.IXOR, 214, 27, Opcodes.PUTFIELD, Opcodes.DRETURN, 106, 80, 69, 243, 48, 239, 63, 85, Opcodes.IF_ICMPGE, 234, 101, 186, 47, 192, 222, 28, 253, 77, Opcodes.I2C, 117, 6, 138, Opcodes.GETSTATIC, Command.CMD_OTA_GET_DEVICE_REFRESH_FIRMWARE_STATUS, 14, 31, 98, Command.CMD_GET_DEV_MD5, Opcodes.JSR, Opcodes.FCMPG, 249, 197, 37, 89, Opcodes.IINC, 114, 57, 76, 94, 120, 56, Opcodes.F2L, Command.CMD_SETTINGS_COMMUNICATION_MTU, Opcodes.IF_ACMPEQ, Command.CMD_OTA_INQUIRE_DEVICE_IF_CAN_UPDATE, 97, Opcodes.PUTSTATIC, 33, Opcodes.IFGE, 30, 67, Opcodes.IFNONNULL, 252, 4, 81, Opcodes.IFEQ, 109, 13, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 223, 126, 36, 59, Opcodes.LOOKUPSWITCH, ComposerKt.referenceKey, 17, Opcodes.D2L, 78, Opcodes.INVOKESPECIAL, 235, 60, Opcodes.LOR, Opcodes.LCMP, 247, Opcodes.INVOKEINTERFACE, 19, 44, Primes.SMALL_FACTOR_LIMIT, Command.CMD_REBOOT_DEVICE, 110, Command.CMD_ADV_DEV_REQUEST_OPERATION, 3, 86, 68, 127, Opcodes.RET, 42, Opcodes.NEW, Opcodes.INSTANCEOF, 83, 220, 11, Opcodes.IFGT, 108, 49, 116, 246, 70, Opcodes.IRETURN, 137, 20, Command.CMD_OTA_GET_DEVICE_UPDATE_FILE_INFO_OFFSET, 22, 58, 105, 9, 112, Opcodes.INVOKEVIRTUAL, 208, 237, ComposerKt.providerMapsKey, 66, Opcodes.DCMPG, Opcodes.IF_ICMPLE, 40, 92, 248, Opcodes.I2F};
    private static final long[] C0 = new long[256];
    private static final long[] C1 = new long[256];
    private static final long[] C2 = new long[256];
    private static final long[] C3 = new long[256];
    private static final long[] C4 = new long[256];
    private static final long[] C5 = new long[256];
    private static final long[] C6 = new long[256];
    private static final long[] C7 = new long[256];

    static {
        short[] sArr = new short[32];
        EIGHT = sArr;
        sArr[31] = 8;
        for (int i = 0; i < 256; i++) {
            int i2 = SBOX[i];
            int mulX = mulX(i2);
            int mulX2 = mulX(mulX);
            int i3 = mulX2 ^ i2;
            int mulX3 = mulX(mulX2);
            int i4 = mulX3 ^ i2;
            C0[i] = packIntoLong(i2, i2, mulX2, i2, mulX3, i3, mulX, i4);
            C1[i] = packIntoLong(i4, i2, i2, mulX2, i2, mulX3, i3, mulX);
            C2[i] = packIntoLong(mulX, i4, i2, i2, mulX2, i2, mulX3, i3);
            C3[i] = packIntoLong(i3, mulX, i4, i2, i2, mulX2, i2, mulX3);
            C4[i] = packIntoLong(mulX3, i3, mulX, i4, i2, i2, mulX2, i2);
            C5[i] = packIntoLong(i2, mulX3, i3, mulX, i4, i2, i2, mulX2);
            C6[i] = packIntoLong(mulX2, i2, mulX3, i3, mulX, i4, i2, i2);
            C7[i] = packIntoLong(i2, mulX2, i2, mulX3, i3, mulX, i4, i2);
        }
    }

    public WhirlpoolDigest() {
        this(CryptoServicePurpose.ANY);
    }

    public WhirlpoolDigest(CryptoServicePurpose cryptoServicePurpose) {
        long[] jArr = new long[11];
        this._rc = jArr;
        this._buffer = new byte[64];
        this._bufferPos = 0;
        this._bitCount = new short[32];
        this._hash = new long[8];
        this._K = new long[8];
        this._L = new long[8];
        this._block = new long[8];
        this._state = new long[8];
        jArr[0] = 0;
        for (int i = 1; i <= 10; i++) {
            int i2 = (i - 1) * 8;
            this._rc[i] = (((((((C0[i2] & (-72057594037927936L)) ^ (C1[i2 + 1] & 71776119061217280L)) ^ (C2[i2 + 2] & 280375465082880L)) ^ (C3[i2 + 3] & 1095216660480L)) ^ (C4[i2 + 4] & 4278190080L)) ^ (C5[i2 + 5] & 16711680)) ^ (C6[i2 + 6] & 65280)) ^ (C7[i2 + 7] & 255);
        }
        this.purpose = cryptoServicePurpose;
        CryptoServicesRegistrar.checkConstraints(Utils.getDefaultProperties(this, getDigestSize(), cryptoServicePurpose));
    }

    public WhirlpoolDigest(WhirlpoolDigest whirlpoolDigest) {
        this._rc = new long[11];
        this._buffer = new byte[64];
        this._bufferPos = 0;
        this._bitCount = new short[32];
        this._hash = new long[8];
        this._K = new long[8];
        this._L = new long[8];
        this._block = new long[8];
        this._state = new long[8];
        CryptoServicePurpose cryptoServicePurpose = whirlpoolDigest.purpose;
        this.purpose = cryptoServicePurpose;
        reset(whirlpoolDigest);
        CryptoServicesRegistrar.checkConstraints(Utils.getDefaultProperties(this, getDigestSize(), cryptoServicePurpose));
    }

    private byte[] copyBitLength() {
        byte[] bArr = new byte[32];
        for (int i = 0; i < 32; i++) {
            bArr[i] = (byte) (this._bitCount[i] & 255);
        }
        return bArr;
    }

    private void finish() {
        byte[] copyBitLength = copyBitLength();
        byte[] bArr = this._buffer;
        int i = this._bufferPos;
        bArr[i] = (byte) (bArr[i] | ByteCompanionObject.MIN_VALUE);
        int i2 = i + 1;
        this._bufferPos = i2;
        if (i2 == bArr.length) {
            processFilledBuffer(bArr, 0);
        }
        if (this._bufferPos > 32) {
            while (this._bufferPos != 0) {
                update((byte) 0);
            }
        }
        while (this._bufferPos <= 32) {
            update((byte) 0);
        }
        System.arraycopy(copyBitLength, 0, this._buffer, 32, copyBitLength.length);
        processFilledBuffer(this._buffer, 0);
    }

    private void increment() {
        int i = 0;
        for (int length = this._bitCount.length - 1; length >= 0; length--) {
            short[] sArr = this._bitCount;
            int i2 = (sArr[length] & 255) + EIGHT[length] + i;
            i = i2 >>> 8;
            sArr[length] = (short) (i2 & 255);
        }
    }

    private static int mulX(int i) {
        return ((-(i >>> 7)) & REDUCTION_POLYNOMIAL) ^ (i << 1);
    }

    private static long packIntoLong(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        return (((((((i2 << 48) ^ (i << 56)) ^ (i3 << 40)) ^ (i4 << 32)) ^ (i5 << 24)) ^ (i6 << 16)) ^ (i7 << 8)) ^ i8;
    }

    private void processFilledBuffer(byte[] bArr, int i) {
        Pack.bigEndianToLong(this._buffer, 0, this._block);
        processBlock();
        this._bufferPos = 0;
        Arrays.fill(this._buffer, (byte) 0);
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        return new WhirlpoolDigest(this);
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        finish();
        Pack.longToBigEndian(this._hash, bArr, i);
        reset();
        return getDigestSize();
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "Whirlpool";
    }

    @Override // org.bouncycastle.crypto.ExtendedDigest
    public int getByteLength() {
        return 64;
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return 64;
    }

    protected void processBlock() {
        char c;
        char c2;
        for (int i = 0; i < 8; i++) {
            long[] jArr = this._state;
            long j = this._block[i];
            long[] jArr2 = this._K;
            long j2 = this._hash[i];
            jArr2[i] = j2;
            jArr[i] = j ^ j2;
        }
        for (int i2 = 1; i2 <= 10; i2++) {
            int i3 = 0;
            while (true) {
                c = ' ';
                c2 = '(';
                if (i3 >= 8) {
                    break;
                }
                long[] jArr3 = this._L;
                jArr3[i3] = 0;
                long[] jArr4 = C0;
                long[] jArr5 = this._K;
                long j3 = jArr4[((int) (jArr5[i3 & 7] >>> 56)) & 255];
                jArr3[i3] = j3;
                long j4 = C1[((int) (jArr5[(i3 - 1) & 7] >>> 48)) & 255] ^ j3;
                jArr3[i3] = j4;
                long j5 = j4 ^ C2[((int) (jArr5[(i3 - 2) & 7] >>> 40)) & 255];
                jArr3[i3] = j5;
                long j6 = j5 ^ C3[((int) (jArr5[(i3 - 3) & 7] >>> 32)) & 255];
                jArr3[i3] = j6;
                long j7 = j6 ^ C4[((int) (jArr5[(i3 - 4) & 7] >>> 24)) & 255];
                jArr3[i3] = j7;
                long j8 = j7 ^ C5[((int) (jArr5[(i3 - 5) & 7] >>> 16)) & 255];
                jArr3[i3] = j8;
                long j9 = j8 ^ C6[((int) (jArr5[(i3 - 6) & 7] >>> 8)) & 255];
                jArr3[i3] = j9;
                jArr3[i3] = j9 ^ C7[((int) jArr5[(i3 - 7) & 7]) & 255];
                i3++;
            }
            long[] jArr6 = this._L;
            long[] jArr7 = this._K;
            System.arraycopy(jArr6, 0, jArr7, 0, jArr7.length);
            long[] jArr8 = this._K;
            jArr8[0] = jArr8[0] ^ this._rc[i2];
            int i4 = 0;
            while (i4 < 8) {
                long[] jArr9 = this._L;
                long j10 = this._K[i4];
                jArr9[i4] = j10;
                long[] jArr10 = C0;
                long[] jArr11 = this._state;
                char c3 = c;
                char c4 = c2;
                long j11 = j10 ^ jArr10[((int) (jArr11[i4 & 7] >>> 56)) & 255];
                jArr9[i4] = j11;
                long j12 = j11 ^ C1[((int) (jArr11[(i4 - 1) & 7] >>> 48)) & 255];
                jArr9[i4] = j12;
                long j13 = j12 ^ C2[((int) (jArr11[(i4 - 2) & 7] >>> c4)) & 255];
                jArr9[i4] = j13;
                long j14 = j13 ^ C3[((int) (jArr11[(i4 - 3) & 7] >>> c3)) & 255];
                jArr9[i4] = j14;
                long j15 = j14 ^ C4[((int) (jArr11[(i4 - 4) & 7] >>> 24)) & 255];
                jArr9[i4] = j15;
                long j16 = j15 ^ C5[((int) (jArr11[(i4 - 5) & 7] >>> 16)) & 255];
                jArr9[i4] = j16;
                long j17 = j16 ^ C6[((int) (jArr11[(i4 - 6) & 7] >>> 8)) & 255];
                jArr9[i4] = j17;
                jArr9[i4] = j17 ^ C7[((int) jArr11[(i4 - 7) & 7]) & 255];
                i4++;
                c2 = c4;
                c = c3;
            }
            long[] jArr12 = this._L;
            long[] jArr13 = this._state;
            System.arraycopy(jArr12, 0, jArr13, 0, jArr13.length);
        }
        for (int i5 = 0; i5 < 8; i5++) {
            long[] jArr14 = this._hash;
            jArr14[i5] = jArr14[i5] ^ (this._state[i5] ^ this._block[i5]);
        }
    }

    @Override // org.bouncycastle.crypto.Digest
    public void reset() {
        this._bufferPos = 0;
        Arrays.fill(this._bitCount, (short) 0);
        Arrays.fill(this._buffer, (byte) 0);
        Arrays.fill(this._hash, 0L);
        Arrays.fill(this._K, 0L);
        Arrays.fill(this._L, 0L);
        Arrays.fill(this._block, 0L);
        Arrays.fill(this._state, 0L);
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        WhirlpoolDigest whirlpoolDigest = (WhirlpoolDigest) memoable;
        long[] jArr = whirlpoolDigest._rc;
        long[] jArr2 = this._rc;
        System.arraycopy(jArr, 0, jArr2, 0, jArr2.length);
        byte[] bArr = whirlpoolDigest._buffer;
        byte[] bArr2 = this._buffer;
        System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
        this._bufferPos = whirlpoolDigest._bufferPos;
        short[] sArr = whirlpoolDigest._bitCount;
        short[] sArr2 = this._bitCount;
        System.arraycopy(sArr, 0, sArr2, 0, sArr2.length);
        long[] jArr3 = whirlpoolDigest._hash;
        long[] jArr4 = this._hash;
        System.arraycopy(jArr3, 0, jArr4, 0, jArr4.length);
        long[] jArr5 = whirlpoolDigest._K;
        long[] jArr6 = this._K;
        System.arraycopy(jArr5, 0, jArr6, 0, jArr6.length);
        long[] jArr7 = whirlpoolDigest._L;
        long[] jArr8 = this._L;
        System.arraycopy(jArr7, 0, jArr8, 0, jArr8.length);
        long[] jArr9 = whirlpoolDigest._block;
        long[] jArr10 = this._block;
        System.arraycopy(jArr9, 0, jArr10, 0, jArr10.length);
        long[] jArr11 = whirlpoolDigest._state;
        long[] jArr12 = this._state;
        System.arraycopy(jArr11, 0, jArr12, 0, jArr12.length);
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte b) {
        byte[] bArr = this._buffer;
        int i = this._bufferPos;
        bArr[i] = b;
        int i2 = i + 1;
        this._bufferPos = i2;
        if (i2 == bArr.length) {
            processFilledBuffer(bArr, 0);
        }
        increment();
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte[] bArr, int i, int i2) {
        while (i2 > 0) {
            update(bArr[i]);
            i++;
            i2--;
        }
    }
}
