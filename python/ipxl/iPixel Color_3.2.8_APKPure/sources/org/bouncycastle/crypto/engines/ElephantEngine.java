package org.bouncycastle.crypto.engines;

import com.alibaba.fastjson2.JSONB;
import com.alibaba.fastjson2.internal.asm.Opcodes;
import com.jieli.jl_bt_ota.constant.AttrAndFunCode;
import com.jieli.jl_bt_ota.constant.JL_Constant;
import java.util.Arrays;
import kotlin.UByte;
import kotlin.io.encoding.Base64;
import kotlin.jvm.internal.ByteCompanionObject;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.engines.AEADBaseEngine;
import org.bouncycastle.util.Bytes;

/* loaded from: classes3.dex */
public class ElephantEngine extends AEADBaseEngine {
    private byte[] ad;
    private int adOff;
    private int adlen;
    private final byte[] buffer;
    private byte[] current_mask;
    private byte[] expanded_key;
    private final Permutation instance;
    private int nb_its;
    private byte[] next_mask;
    private byte[] npub;
    private byte[] previous_mask;
    private final byte[] previous_outputMessage;
    private final byte[] tag_buffer;

    private class Delirium implements Permutation {
        private static final int nRounds = 18;
        private final int[] KeccakRhoOffsets;
        private final byte[] KeccakRoundConstants;

        private Delirium() {
            this.KeccakRoundConstants = new byte[]{1, -126, -118, 0, -117, 1, -127, 9, -118, -120, 9, 10, -117, -117, -119, 3, 2, ByteCompanionObject.MIN_VALUE};
            this.KeccakRhoOffsets = new int[]{0, 1, 6, 4, 3, 4, 4, 6, 7, 4, 3, 2, 3, 1, 7, 1, 5, 7, 5, 0, 2, 2, 5, 0, 6};
        }

        private void KeccakP200Round(byte[] bArr, int i) {
            byte[] bArr2 = new byte[25];
            for (int i2 = 0; i2 < 5; i2++) {
                for (int i3 = 0; i3 < 5; i3++) {
                    bArr2[i2] = (byte) (bArr2[i2] ^ bArr[index(i2, i3)]);
                }
            }
            int i4 = 0;
            while (i4 < 5) {
                int i5 = i4 + 1;
                bArr2[i4 + 5] = (byte) (bArr2[(i4 + 4) % 5] ^ ROL8(bArr2[i5 % 5], 1));
                i4 = i5;
            }
            for (int i6 = 0; i6 < 5; i6++) {
                for (int i7 = 0; i7 < 5; i7++) {
                    int index = index(i6, i7);
                    bArr[index] = (byte) (bArr[index] ^ bArr2[i6 + 5]);
                }
            }
            for (int i8 = 0; i8 < 5; i8++) {
                for (int i9 = 0; i9 < 5; i9++) {
                    bArr2[index(i8, i9)] = ROL8(bArr[index(i8, i9)], this.KeccakRhoOffsets[index(i8, i9)]);
                }
            }
            for (int i10 = 0; i10 < 5; i10++) {
                for (int i11 = 0; i11 < 5; i11++) {
                    bArr[index(i11, ((i10 * 2) + (i11 * 3)) % 5)] = bArr2[index(i10, i11)];
                }
            }
            for (int i12 = 0; i12 < 5; i12++) {
                int i13 = 0;
                while (i13 < 5) {
                    int i14 = i13 + 1;
                    bArr2[i13] = (byte) (bArr[index(i13, i12)] ^ ((~bArr[index(i14 % 5, i12)]) & bArr[index((i13 + 2) % 5, i12)]));
                    i13 = i14;
                }
                for (int i15 = 0; i15 < 5; i15++) {
                    bArr[index(i15, i12)] = bArr2[i15];
                }
            }
            bArr[0] = (byte) (this.KeccakRoundConstants[i] ^ bArr[0]);
        }

        private byte ROL8(byte b, int i) {
            return (byte) (((b & 255) >>> (8 - i)) | (b << i));
        }

        private int index(int i, int i2) {
            return i + (i2 * 5);
        }

        @Override // org.bouncycastle.crypto.engines.ElephantEngine.Permutation
        public void lfsr_step() {
            byte[] bArr = ElephantEngine.this.next_mask;
            int i = ElephantEngine.this.BlockSize - 1;
            ElephantEngine elephantEngine = ElephantEngine.this;
            byte rotl = elephantEngine.rotl(elephantEngine.current_mask[0]);
            ElephantEngine elephantEngine2 = ElephantEngine.this;
            bArr[i] = (byte) ((rotl ^ elephantEngine2.rotl(elephantEngine2.current_mask[2])) ^ (ElephantEngine.this.current_mask[13] << 1));
        }

        @Override // org.bouncycastle.crypto.engines.ElephantEngine.Permutation
        public void permutation(byte[] bArr) {
            for (int i = 0; i < 18; i++) {
                KeccakP200Round(bArr, i);
            }
        }
    }

    private class Dumbo extends Spongent {
        public Dumbo() {
            super(Opcodes.IF_ICMPNE, 20, 80, (byte) 117);
        }

        @Override // org.bouncycastle.crypto.engines.ElephantEngine.Permutation
        public void lfsr_step() {
            ElephantEngine.this.next_mask[ElephantEngine.this.BlockSize - 1] = (byte) (((((ElephantEngine.this.current_mask[0] & UByte.MAX_VALUE) << 3) | ((ElephantEngine.this.current_mask[0] & UByte.MAX_VALUE) >>> 5)) ^ ((ElephantEngine.this.current_mask[3] & UByte.MAX_VALUE) << 7)) ^ ((ElephantEngine.this.current_mask[13] & UByte.MAX_VALUE) >>> 7));
        }
    }

    public enum ElephantParameters {
        elephant160,
        elephant176,
        elephant200
    }

    private class Jumbo extends Spongent {
        public Jumbo() {
            super(Opcodes.ARETURN, 22, 90, (byte) 69);
        }

        @Override // org.bouncycastle.crypto.engines.ElephantEngine.Permutation
        public void lfsr_step() {
            byte[] bArr = ElephantEngine.this.next_mask;
            int i = ElephantEngine.this.BlockSize - 1;
            ElephantEngine elephantEngine = ElephantEngine.this;
            bArr[i] = (byte) ((elephantEngine.rotl(elephantEngine.current_mask[0]) ^ ((ElephantEngine.this.current_mask[3] & UByte.MAX_VALUE) << 7)) ^ ((ElephantEngine.this.current_mask[19] & UByte.MAX_VALUE) >>> 7));
        }
    }

    private interface Permutation {
        void lfsr_step();

        void permutation(byte[] bArr);
    }

    private static abstract class Spongent implements Permutation {
        private final byte lfsrIV;
        private final int nBits;
        private final int nRounds;
        private final int nSBox;
        private final byte[] sBoxLayer = {-18, -19, -21, -32, -30, -31, -28, -17, -25, -22, -24, -27, -23, -20, -29, -26, -34, -35, -37, JSONB.Constants.BC_INT64_BYTE_ZERO, -46, -47, -44, -33, JSONB.Constants.BC_INT64_BYTE_MAX, -38, JSONB.Constants.BC_INT64_NUM_MIN, -43, -39, JL_Constant.PREFIX_FLAG_SECOND, -45, -42, JSONB.Constants.BC_INT64, JSONB.Constants.BC_INT8, JSONB.Constants.BC_BIGINT, JSONB.Constants.BC_FALSE, JSONB.Constants.BC_DOUBLE_NUM_0, JSONB.Constants.BC_TRUE, JSONB.Constants.BC_DOUBLE_LONG, JSONB.Constants.BC_INT64_INT, JSONB.Constants.BC_FLOAT, -70, JSONB.Constants.BC_DECIMAL_LONG, JSONB.Constants.BC_DOUBLE, JSONB.Constants.BC_DECIMAL, -68, JSONB.Constants.BC_DOUBLE_NUM_1, JSONB.Constants.BC_FLOAT_INT, 14, 13, 11, 0, 2, 1, 4, 15, 7, 10, 8, 5, 9, 12, 3, 6, 46, 45, 43, 32, 34, 33, 36, JSONB.Constants.BC_INT32_NUM_MAX, 39, 42, 40, 37, 41, 44, 35, 38, 30, 29, 27, JSONB.Constants.BC_INT32_NUM_16, 18, 17, 20, 31, 23, 26, 24, 21, 25, 28, 19, AttrAndFunCode.SYS_INFO_FUNCTION_LOW_POWER, JSONB.Constants.BC_STR_ASCII_FIX_5, JSONB.Constants.BC_STR_ASCII_FIX_4, 75, JSONB.Constants.BC_INT32_SHORT_MIN, 66, 65, JSONB.Constants.BC_INT32_SHORT_ZERO, 79, JSONB.Constants.BC_INT32_SHORT_MAX, JSONB.Constants.BC_STR_ASCII_FIX_1, JSONB.Constants.BC_INT32, 69, 73, 76, 67, 70, -2, -3, -5, JSONB.Constants.BC_INT32_NUM_MIN, -14, -15, -12, -1, -9, -6, -8, -11, -7, -4, -13, -10, JSONB.Constants.BC_STR_GB18030, JSONB.Constants.BC_STR_UTF16BE, JSONB.Constants.BC_STR_UTF16, 112, 114, 113, 116, Byte.MAX_VALUE, 119, JSONB.Constants.BC_STR_UTF8, JSONB.Constants.BC_STR_ASCII_FIX_MAX, 117, JSONB.Constants.BC_STR_ASCII, JSONB.Constants.BC_STR_UTF16LE, 115, 118, JSONB.Constants.BC_TIMESTAMP, JSONB.Constants.BC_TIMESTAMP_MINUTES, JSONB.Constants.BC_TIMESTAMP_MILLIS, -96, -94, -95, JSONB.Constants.BC_ARRAY, JSONB.Constants.BC_NULL, JSONB.Constants.BC_LOCAL_TIME, JSONB.Constants.BC_TIMESTAMP_WITH_TIMEZONE, JSONB.Constants.BC_LOCAL_DATETIME, JSONB.Constants.BC_OBJECT_END, JSONB.Constants.BC_LOCAL_DATE, JSONB.Constants.BC_TIMESTAMP_SECONDS, JSONB.Constants.BC_ARRAY_FIX_MAX, JSONB.Constants.BC_OBJECT, -114, -115, -117, ByteCompanionObject.MIN_VALUE, -126, -127, -124, -113, -121, -118, -120, -123, -119, -116, -125, -122, 94, 93, 91, 80, 82, 81, 84, 95, 87, 90, 88, 85, 89, 92, 83, 86, -98, -99, -101, JSONB.Constants.BC_CHAR, JSONB.Constants.BC_TYPED_ANY, JSONB.Constants.BC_BINARY, -108, -97, -105, -102, -104, -107, -103, -100, JSONB.Constants.BC_REFERENCE, -106, -50, -51, -53, JSONB.Constants.BC_INT64_SHORT_MIN, -62, -63, JSONB.Constants.BC_INT64_SHORT_ZERO, -49, JSONB.Constants.BC_INT64_SHORT_MAX, -54, JSONB.Constants.BC_INT64_BYTE_MIN, -59, -55, -52, -61, -58, 62, Base64.padSymbol, 59, JSONB.Constants.BC_INT32_BYTE_MIN, 50, 49, 52, 63, 55, 58, JSONB.Constants.BC_INT32_BYTE_ZERO, 53, 57, 60, 51, 54, 110, JSONB.Constants.BC_STR_ASCII_FIX_36, 107, 96, 98, 97, 100, 111, 103, 106, 104, 101, JSONB.Constants.BC_STR_ASCII_FIX_32, 108, 99, 102};

        public Spongent(int i, int i2, int i3, byte b) {
            this.nRounds = i3;
            this.nSBox = i2;
            this.lfsrIV = b;
            this.nBits = i;
        }

        @Override // org.bouncycastle.crypto.engines.ElephantEngine.Permutation
        public void permutation(byte[] bArr) {
            int i;
            byte b = this.lfsrIV;
            byte[] bArr2 = new byte[this.nSBox];
            for (int i2 = 0; i2 < this.nRounds; i2++) {
                bArr[0] = (byte) (bArr[0] ^ b);
                int i3 = this.nSBox - 1;
                byte b2 = bArr[i3];
                int i4 = b & 32;
                int i5 = ((b & 1) << 7) | ((b & 2) << 5) | ((b & 4) << 3) | ((b & 8) << 1) | ((b & JSONB.Constants.BC_INT32_NUM_16) >>> 1) | (i4 >>> 3);
                int i6 = b & JSONB.Constants.BC_INT32_SHORT_MIN;
                bArr[i3] = (byte) (b2 ^ ((byte) ((i5 | (i6 >>> 5)) | ((b & ByteCompanionObject.MIN_VALUE) >>> 7))));
                b = (byte) (((b << 1) | ((i6 >>> 6) ^ (i4 >>> 5))) & 127);
                for (int i7 = 0; i7 < this.nSBox; i7++) {
                    bArr[i7] = this.sBoxLayer[bArr[i7] & UByte.MAX_VALUE];
                }
                Arrays.fill(bArr2, (byte) 0);
                int i8 = 0;
                while (true) {
                    i = this.nSBox;
                    if (i8 < i) {
                        for (int i9 = 0; i9 < 8; i9++) {
                            int i10 = (i8 << 3) + i9;
                            int i11 = this.nBits;
                            if (i10 != i11 - 1) {
                                i10 = ((i10 * i11) >> 2) % (i11 - 1);
                            }
                            int i12 = i10 >>> 3;
                            bArr2[i12] = (byte) (((((bArr[i8] & UByte.MAX_VALUE) >>> i9) & 1) << (i10 & 7)) ^ bArr2[i12]);
                        }
                        i8++;
                    }
                }
                System.arraycopy(bArr2, 0, bArr, 0, i);
            }
        }
    }

    public ElephantEngine(ElephantParameters elephantParameters) {
        this.KEY_SIZE = 16;
        this.IV_SIZE = 12;
        int ordinal = elephantParameters.ordinal();
        if (ordinal == 0) {
            this.BlockSize = 20;
            this.instance = new Dumbo();
            this.MAC_SIZE = 8;
            this.algorithmName = "Elephant 160 AEAD";
        } else if (ordinal == 1) {
            this.BlockSize = 22;
            this.instance = new Jumbo();
            this.algorithmName = "Elephant 176 AEAD";
            this.MAC_SIZE = 8;
        } else {
            if (ordinal != 2) {
                throw new IllegalArgumentException("Invalid parameter settings for Elephant");
            }
            this.BlockSize = 25;
            this.instance = new Delirium();
            this.algorithmName = "Elephant 200 AEAD";
            this.MAC_SIZE = 16;
        }
        this.tag_buffer = new byte[this.BlockSize];
        this.previous_mask = new byte[this.BlockSize];
        this.current_mask = new byte[this.BlockSize];
        this.next_mask = new byte[this.BlockSize];
        this.buffer = new byte[this.BlockSize];
        this.previous_outputMessage = new byte[this.BlockSize];
        setInnerMembers(AEADBaseEngine.ProcessingBufferType.Immediate, AEADBaseEngine.AADOperatorType.Stream, AEADBaseEngine.DataOperatorType.Counter);
    }

    private void absorbAAD() {
        processAADBytes(this.buffer);
        Bytes.xorTo(this.BlockSize, this.next_mask, this.buffer);
        this.instance.permutation(this.buffer);
        Bytes.xorTo(this.BlockSize, this.next_mask, this.buffer);
        Bytes.xorTo(this.BlockSize, this.buffer, this.tag_buffer);
    }

    private void absorbCiphertext() {
        xorTo(this.BlockSize, this.previous_mask, this.next_mask, this.buffer);
        this.instance.permutation(this.buffer);
        xorTo(this.BlockSize, this.previous_mask, this.next_mask, this.buffer);
        Bytes.xorTo(this.BlockSize, this.buffer, this.tag_buffer);
    }

    private void computeCipherBlock(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        System.arraycopy(this.npub, 0, this.buffer, 0, this.IV_SIZE);
        Arrays.fill(this.buffer, this.IV_SIZE, this.BlockSize, (byte) 0);
        xorTo(this.BlockSize, this.current_mask, this.next_mask, this.buffer);
        this.instance.permutation(this.buffer);
        xorTo(this.BlockSize, this.current_mask, this.next_mask, this.buffer);
        Bytes.xorTo(i2, bArr, i, this.buffer);
        System.arraycopy(this.buffer, 0, bArr2, i3, i2);
    }

    private void lfsr_step() {
        this.instance.lfsr_step();
        System.arraycopy(this.current_mask, 1, this.next_mask, 0, this.BlockSize - 1);
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x000f, code lost:
    
        if (r0 != 6) goto L14;
     */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0062  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void processAADBytes(byte[] r10) {
        /*
            r9 = this;
            org.bouncycastle.crypto.engines.AEADBaseEngine$State r0 = r9.m_state
            int r0 = r0.ord
            r1 = 6
            r2 = 2
            r3 = 0
            r4 = 1
            if (r0 == r4) goto L37
            if (r0 == r2) goto L27
            r5 = 5
            if (r0 == r5) goto L12
            if (r0 == r1) goto L27
            goto L35
        L12:
            byte[] r0 = r9.expanded_key
            byte[] r5 = r9.current_mask
            int r6 = r9.BlockSize
            java.lang.System.arraycopy(r0, r3, r5, r3, r6)
            byte[] r0 = r9.npub
            int r5 = r9.IV_SIZE
            java.lang.System.arraycopy(r0, r3, r10, r3, r5)
            int r0 = r9.IV_SIZE
            org.bouncycastle.crypto.engines.AEADBaseEngine$State r5 = org.bouncycastle.crypto.engines.AEADBaseEngine.State.DecAad
            goto L4b
        L27:
            int r0 = r9.adOff
            int r5 = r9.adlen
            if (r0 != r5) goto L35
            int r0 = r9.BlockSize
            java.util.Arrays.fill(r10, r3, r0, r3)
            r10[r3] = r4
            return
        L35:
            r0 = r3
            goto L4d
        L37:
            byte[] r0 = r9.expanded_key
            byte[] r5 = r9.current_mask
            int r6 = r9.BlockSize
            java.lang.System.arraycopy(r0, r3, r5, r3, r6)
            byte[] r0 = r9.npub
            int r5 = r9.IV_SIZE
            java.lang.System.arraycopy(r0, r3, r10, r3, r5)
            int r0 = r9.IV_SIZE
            org.bouncycastle.crypto.engines.AEADBaseEngine$State r5 = org.bouncycastle.crypto.engines.AEADBaseEngine.State.EncAad
        L4b:
            r9.m_state = r5
        L4d:
            int r5 = r9.BlockSize
            int r5 = r5 - r0
            int r6 = r9.adlen
            int r7 = r9.adOff
            int r6 = r6 - r7
            if (r5 > r6) goto L62
            byte[] r1 = r9.ad
            java.lang.System.arraycopy(r1, r7, r10, r0, r5)
            int r10 = r9.adOff
            int r10 = r10 + r5
            r9.adOff = r10
            return
        L62:
            if (r6 <= 0) goto L6e
            byte[] r8 = r9.ad
            java.lang.System.arraycopy(r8, r7, r10, r0, r6)
            int r7 = r9.adOff
            int r7 = r7 + r6
            r9.adOff = r7
        L6e:
            int r6 = r6 + r0
            int r0 = r0 + r5
            java.util.Arrays.fill(r10, r6, r0, r3)
            r10[r6] = r4
            org.bouncycastle.crypto.engines.AEADBaseEngine$State r10 = r9.m_state
            int r10 = r10.ord
            if (r10 == r2) goto L83
            if (r10 == r1) goto L7e
            return
        L7e:
            org.bouncycastle.crypto.engines.AEADBaseEngine$State r10 = org.bouncycastle.crypto.engines.AEADBaseEngine.State.DecData
            r9.m_state = r10
            return
        L83:
            org.bouncycastle.crypto.engines.AEADBaseEngine$State r10 = org.bouncycastle.crypto.engines.AEADBaseEngine.State.EncData
            r9.m_state = r10
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.crypto.engines.ElephantEngine.processAADBytes(byte[]):void");
    }

    private void processBuffer(byte[] bArr, int i, byte[] bArr2, int i2, AEADBaseEngine.State state) {
        if (this.m_state == AEADBaseEngine.State.DecInit || this.m_state == AEADBaseEngine.State.EncInit) {
            processFinalAAD();
        }
        lfsr_step();
        computeCipherBlock(bArr, i, this.BlockSize, bArr2, i2);
        if (this.nb_its > 0) {
            System.arraycopy(this.previous_outputMessage, 0, this.buffer, 0, this.BlockSize);
            absorbCiphertext();
        }
        if (this.m_state != state) {
            absorbAAD();
        }
        swapMasks();
        this.nb_its++;
    }

    private void processBytes(byte[] bArr, byte[] bArr2, int i, int i2, int i3, int i4, int i5, int i6) {
        byte[] bArr3 = new byte[this.BlockSize];
        int i7 = this.nb_its;
        int i8 = i;
        int i9 = 0;
        while (i7 < i2) {
            int i10 = i7 == i3 + (-1) ? i5 - (this.BlockSize * i7) : this.BlockSize;
            lfsr_step();
            if (i7 < i3) {
                computeCipherBlock(bArr, i9, i10, bArr2, i8);
                if (this.forEncryption) {
                    System.arraycopy(this.buffer, 0, bArr3, 0, i10);
                } else {
                    System.arraycopy(bArr, i9, bArr3, 0, i10);
                }
                i8 += i10;
                i9 += i10;
            }
            if (i7 > 0 && i7 <= i4) {
                int i11 = (i7 - 1) * this.BlockSize;
                if (i11 == i5) {
                    Arrays.fill(this.buffer, 1, this.BlockSize, (byte) 0);
                    this.buffer[0] = 1;
                } else {
                    int i12 = i5 - i11;
                    if (this.BlockSize <= i12) {
                        System.arraycopy(this.previous_outputMessage, 0, this.buffer, 0, this.BlockSize);
                    } else if (i12 > 0) {
                        System.arraycopy(this.previous_outputMessage, 0, this.buffer, 0, i12);
                        Arrays.fill(this.buffer, i12, this.BlockSize, (byte) 0);
                        this.buffer[i12] = 1;
                    }
                }
                absorbCiphertext();
            }
            i7++;
            if (i7 < i6) {
                absorbAAD();
            }
            swapMasks();
            System.arraycopy(bArr3, 0, this.previous_outputMessage, 0, this.BlockSize);
        }
        this.nb_its = i7;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte rotl(byte b) {
        return (byte) (((b & UByte.MAX_VALUE) >>> 7) | (b << 1));
    }

    private void swapMasks() {
        byte[] bArr = this.previous_mask;
        this.previous_mask = this.current_mask;
        this.current_mask = this.next_mask;
        this.next_mask = bArr;
    }

    public static void xorTo(int i, byte[] bArr, byte[] bArr2, byte[] bArr3) {
        for (int i2 = 0; i2 < i; i2++) {
            bArr3[i2] = (byte) (bArr3[i2] ^ (bArr[i2] ^ bArr2[i2]));
        }
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine
    protected void checkAAD() {
        int i = this.m_state.ord;
        if (i == 3) {
            throw new IllegalArgumentException(this.algorithmName + " cannot process AAD when the length of the ciphertext to be processed exceeds the a block size");
        }
        if (i == 4) {
            throw new IllegalArgumentException(this.algorithmName + " cannot be reused for encryption");
        }
        if (i == 7) {
            throw new IllegalArgumentException(this.algorithmName + " cannot process AAD when the length of the plaintext to be processed exceeds the a block size");
        }
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine
    protected boolean checkData(boolean z) {
        switch (this.m_state.ord) {
            case 1:
            case 2:
            case 3:
                return true;
            case 4:
                throw new IllegalStateException(getAlgorithmName() + " cannot be reused for encryption");
            case 5:
            case 6:
            case 7:
                return false;
            default:
                throw new IllegalStateException(getAlgorithmName() + " needs to be initialized");
        }
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ int doFinal(byte[] bArr, int i) throws IllegalStateException, InvalidCipherTextException {
        return super.doFinal(bArr, i);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine
    protected void finishAAD(AEADBaseEngine.State state, boolean z) {
        finishAAD2(state);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ String getAlgorithmName() {
        return super.getAlgorithmName();
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine
    public /* bridge */ /* synthetic */ int getIVBytesSize() {
        return super.getIVBytesSize();
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine
    public /* bridge */ /* synthetic */ int getKeyBytesSize() {
        return super.getKeyBytesSize();
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ byte[] getMac() {
        return super.getMac();
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public int getOutputSize(int i) {
        int i2 = this.m_state.ord;
        if (i2 == 0) {
            throw new IllegalArgumentException(this.algorithmName + " needs call init function before getUpdateOutputSize");
        }
        if (i2 == 1 || i2 == 2 || i2 == 3) {
            return i + this.m_bufPos + this.MAC_SIZE;
        }
        if (i2 == 4 || i2 == 8) {
            return 0;
        }
        return Math.max(0, (i + this.m_bufPos) - this.MAC_SIZE);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public int getUpdateOutputSize(int i) {
        switch (this.m_state.ord) {
            case 0:
                throw new IllegalArgumentException(this.algorithmName + " needs call init function before getUpdateOutputSize");
            case 1:
            case 2:
            case 3:
                int i2 = this.m_bufPos + i;
                return i2 - (i2 % this.BlockSize);
            case 4:
            case 8:
                return 0;
            case 5:
            case 6:
            case 7:
                int max = Math.max(0, (this.m_bufPos + i) - this.MAC_SIZE);
                return max - (max % this.BlockSize);
            default:
                return Math.max(0, (i + this.m_bufPos) - this.MAC_SIZE);
        }
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ void init(boolean z, CipherParameters cipherParameters) {
        super.init(z, cipherParameters);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine
    protected void init(byte[] bArr, byte[] bArr2) throws IllegalArgumentException {
        this.npub = bArr2;
        byte[] bArr3 = new byte[this.BlockSize];
        this.expanded_key = bArr3;
        System.arraycopy(bArr, 0, bArr3, 0, this.KEY_SIZE);
        this.instance.permutation(this.expanded_key);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ void processAADByte(byte b) {
        super.processAADByte(b);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ void processAADBytes(byte[] bArr, int i, int i2) {
        super.processAADBytes(bArr, i, i2);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine
    protected void processBufferAAD(byte[] bArr, int i) {
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine
    protected void processBufferDecrypt(byte[] bArr, int i, byte[] bArr2, int i2) {
        processBuffer(bArr, i, bArr2, i2, AEADBaseEngine.State.DecData);
        System.arraycopy(bArr, i, this.previous_outputMessage, 0, this.BlockSize);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine
    protected void processBufferEncrypt(byte[] bArr, int i, byte[] bArr2, int i2) {
        processBuffer(bArr, i, bArr2, i2, AEADBaseEngine.State.EncData);
        System.arraycopy(bArr2, i2, this.previous_outputMessage, 0, this.BlockSize);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ int processByte(byte b, byte[] bArr, int i) throws DataLengthException {
        return super.processByte(b, bArr, i);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws DataLengthException {
        return super.processBytes(bArr, i, i2, bArr2, i3);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine
    protected void processFinalAAD() {
        if (this.adOff == -1) {
            this.ad = ((AEADBaseEngine.StreamAADOperator) this.aadOperator).getBytes();
            this.adOff = 0;
            this.adlen = this.aadOperator.getLen();
            this.aadOperator.reset();
        }
        int i = this.m_state.ord;
        if (i == 1 || i == 5) {
            processAADBytes(this.tag_buffer);
        }
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine
    protected void processFinalBlock(byte[] bArr, int i) {
        int len = this.dataOperator.getLen() - (this.forEncryption ? 0 : this.MAC_SIZE);
        processFinalAAD();
        int i2 = len / this.BlockSize;
        int i3 = i2 + 1;
        int i4 = len % this.BlockSize != 0 ? i3 : i2;
        int i5 = (this.IV_SIZE + this.adlen) / this.BlockSize;
        processBytes(this.m_buf, bArr, i, Math.max(i2 + 2, i5), i4, i3, len, i5 + 1);
        Bytes.xorTo(this.BlockSize, this.expanded_key, this.tag_buffer);
        this.instance.permutation(this.tag_buffer);
        Bytes.xorTo(this.BlockSize, this.expanded_key, this.tag_buffer);
        System.arraycopy(this.tag_buffer, 0, this.mac, 0, this.MAC_SIZE);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ void reset() {
        super.reset();
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine
    protected void reset(boolean z) {
        super.reset(z);
        Arrays.fill(this.tag_buffer, (byte) 0);
        Arrays.fill(this.previous_outputMessage, (byte) 0);
        this.nb_its = 0;
        this.adOff = -1;
    }
}
