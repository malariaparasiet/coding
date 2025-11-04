package org.bouncycastle.crypto.engines;

import com.alibaba.fastjson2.JSONB;
import com.jieli.jl_bt_ota.constant.AttrAndFunCode;
import kotlin.UByte;
import kotlin.io.encoding.Base64;
import kotlin.jvm.internal.ByteCompanionObject;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.engines.AEADBaseEngine;
import org.bouncycastle.util.Bytes;

/* loaded from: classes3.dex */
public class GiftCofbEngine extends AEADBaseEngine {
    private static final byte[] GIFT_RC = {1, 3, 7, 15, 31, 62, Base64.padSymbol, 59, 55, JSONB.Constants.BC_INT32_NUM_MAX, 30, 60, 57, 51, 39, 14, 29, 58, 53, 43, AttrAndFunCode.SYS_INFO_FUNCTION_LOW_POWER, 44, 24, JSONB.Constants.BC_INT32_BYTE_MIN, 33, 2, 5, 11, 23, 46, 28, JSONB.Constants.BC_INT32_BYTE_ZERO, 49, 35, 6, 13, 27, 54, 45, 26};
    private byte[] Y;
    private byte[] input;
    private byte[] k;
    private byte[] npub;
    private byte[] offset;

    public GiftCofbEngine() {
        this.KEY_SIZE = 16;
        this.IV_SIZE = 16;
        this.MAC_SIZE = 16;
        this.BlockSize = 16;
        this.AADBufferSize = 16;
        this.algorithmName = "GIFT-COFB AEAD";
        setInnerMembers(AEADBaseEngine.ProcessingBufferType.Buffered, AEADBaseEngine.AADOperatorType.Default, AEADBaseEngine.DataOperatorType.Counter);
    }

    private void double_half_block(byte[] bArr) {
        int i = 0;
        int i2 = ((bArr[0] & UByte.MAX_VALUE) >>> 7) * 27;
        while (i < 7) {
            int i3 = i + 1;
            bArr[i] = (byte) (((bArr[i] & UByte.MAX_VALUE) << 1) | ((bArr[i3] & UByte.MAX_VALUE) >>> 7));
            i = i3;
        }
        bArr[7] = (byte) (((bArr[7] & UByte.MAX_VALUE) << 1) ^ i2);
    }

    private void giftb128(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        char c = 0;
        int[] iArr = {((bArr[0] & UByte.MAX_VALUE) << 24) | ((bArr[1] & UByte.MAX_VALUE) << 16) | ((bArr[2] & UByte.MAX_VALUE) << 8) | (bArr[3] & UByte.MAX_VALUE), ((bArr[4] & UByte.MAX_VALUE) << 24) | ((bArr[5] & UByte.MAX_VALUE) << 16) | ((bArr[6] & UByte.MAX_VALUE) << 8) | (bArr[7] & UByte.MAX_VALUE), ((bArr[8] & UByte.MAX_VALUE) << 24) | ((bArr[9] & UByte.MAX_VALUE) << 16) | ((bArr[10] & UByte.MAX_VALUE) << 8) | (bArr[11] & UByte.MAX_VALUE), ((bArr[13] & UByte.MAX_VALUE) << 16) | ((bArr[12] & UByte.MAX_VALUE) << 24) | ((bArr[14] & UByte.MAX_VALUE) << 8) | (bArr[15] & UByte.MAX_VALUE)};
        short[] sArr = {(short) (((bArr2[0] & UByte.MAX_VALUE) << 8) | (bArr2[1] & UByte.MAX_VALUE)), (short) (((bArr2[2] & UByte.MAX_VALUE) << 8) | (bArr2[3] & UByte.MAX_VALUE)), (short) (((bArr2[4] & UByte.MAX_VALUE) << 8) | (bArr2[5] & UByte.MAX_VALUE)), (short) (((bArr2[6] & UByte.MAX_VALUE) << 8) | (bArr2[7] & UByte.MAX_VALUE)), (short) (((bArr2[8] & UByte.MAX_VALUE) << 8) | (bArr2[9] & UByte.MAX_VALUE)), (short) (((bArr2[10] & UByte.MAX_VALUE) << 8) | (bArr2[11] & UByte.MAX_VALUE)), (short) (((bArr2[12] & UByte.MAX_VALUE) << 8) | (bArr2[13] & UByte.MAX_VALUE)), (short) (((bArr2[14] & UByte.MAX_VALUE) << 8) | (bArr2[15] & UByte.MAX_VALUE))};
        int i = 0;
        while (i < 40) {
            int i2 = iArr[1];
            int i3 = iArr[c];
            int i4 = iArr[2];
            int i5 = i2 ^ (i3 & i4);
            iArr[1] = i5;
            int i6 = iArr[3];
            int i7 = i3 ^ (i5 & i6);
            iArr[c] = i7;
            int i8 = i4 ^ (i7 | i5);
            iArr[2] = i8;
            char c2 = c;
            int i9 = i6 ^ i8;
            iArr[3] = i9;
            int i10 = i5 ^ i9;
            iArr[1] = i10;
            int i11 = ~i9;
            iArr[3] = i11;
            iArr[2] = i8 ^ (i7 & i10);
            iArr[c2] = i11;
            iArr[3] = i7;
            iArr[c2] = rowperm(i11, 0, 3, 2, 1);
            iArr[1] = rowperm(iArr[1], 1, 0, 3, 2);
            iArr[2] = rowperm(iArr[2], 2, 1, 0, 3);
            int rowperm = rowperm(iArr[3], 3, 2, 1, 0);
            iArr[3] = rowperm;
            int i12 = iArr[2];
            short s = sArr[2];
            short s2 = sArr[3];
            iArr[2] = i12 ^ (((s & 65535) << 16) | (s2 & 65535));
            int i13 = iArr[1];
            short s3 = sArr[6];
            short s4 = sArr[7];
            iArr[1] = i13 ^ (((s3 & 65535) << 16) | (s4 & 65535));
            iArr[3] = rowperm ^ ((GIFT_RC[i] & UByte.MAX_VALUE) ^ Integer.MIN_VALUE);
            sArr[7] = sArr[5];
            sArr[6] = sArr[4];
            sArr[5] = s2;
            sArr[4] = s;
            sArr[3] = sArr[1];
            sArr[2] = sArr[c2];
            sArr[1] = (short) (((s4 & 65535) >>> 12) | ((s4 & 65535) << 4));
            sArr[c2] = (short) (((s3 & 65535) >>> 2) | ((s3 & 65535) << 14));
            i++;
            c = c2;
        }
        char c3 = c;
        int i14 = iArr[c3];
        bArr3[c3] = (byte) (i14 >>> 24);
        bArr3[1] = (byte) (i14 >>> 16);
        bArr3[2] = (byte) (i14 >>> 8);
        bArr3[3] = (byte) i14;
        int i15 = iArr[1];
        bArr3[4] = (byte) (i15 >>> 24);
        bArr3[5] = (byte) (i15 >>> 16);
        bArr3[6] = (byte) (i15 >>> 8);
        bArr3[7] = (byte) i15;
        int i16 = iArr[2];
        bArr3[8] = (byte) (i16 >>> 24);
        bArr3[9] = (byte) (i16 >>> 16);
        bArr3[10] = (byte) (i16 >>> 8);
        bArr3[11] = (byte) i16;
        int i17 = iArr[3];
        bArr3[12] = (byte) (i17 >>> 24);
        bArr3[13] = (byte) (i17 >>> 16);
        bArr3[14] = (byte) (i17 >>> 8);
        bArr3[15] = (byte) i17;
    }

    private void pho1(byte[] bArr, byte[] bArr2, byte[] bArr3, int i, int i2) {
        byte[] bArr4 = new byte[16];
        byte[] bArr5 = new byte[16];
        if (i2 == 0) {
            bArr4[0] = ByteCompanionObject.MIN_VALUE;
        } else {
            System.arraycopy(bArr3, i, bArr4, 0, i2);
            if (i2 < 16) {
                bArr4[i2] = ByteCompanionObject.MIN_VALUE;
            }
        }
        System.arraycopy(bArr2, 8, bArr5, 0, 8);
        int i3 = 0;
        while (i3 < 7) {
            int i4 = i3 + 8;
            int i5 = (bArr2[i3] & UByte.MAX_VALUE) << 1;
            i3++;
            bArr5[i4] = (byte) (((bArr2[i3] & UByte.MAX_VALUE) >>> 7) | i5);
        }
        bArr5[15] = (byte) (((bArr2[7] & UByte.MAX_VALUE) << 1) | ((bArr2[0] & UByte.MAX_VALUE) >>> 7));
        System.arraycopy(bArr5, 0, bArr2, 0, 16);
        Bytes.xor(16, bArr2, bArr4, bArr);
    }

    private int rowperm(int i, int i2, int i3, int i4, int i5) {
        int i6 = 0;
        for (int i7 = 0; i7 < 8; i7++) {
            int i8 = i7 * 4;
            i6 = i6 | (((i >>> i8) & 1) << ((i2 * 8) + i7)) | (((i >>> (i8 + 1)) & 1) << ((i3 * 8) + i7)) | (((i >>> (i8 + 2)) & 1) << ((i4 * 8) + i7)) | (((i >>> (i8 + 3)) & 1) << ((8 * i5) + i7));
        }
        return i6;
    }

    private void triple_half_block(byte[] bArr) {
        byte[] bArr2 = new byte[8];
        int i = 0;
        while (i < 7) {
            int i2 = i + 1;
            bArr2[i] = (byte) (((bArr[i2] & UByte.MAX_VALUE) >>> 7) | ((bArr[i] & UByte.MAX_VALUE) << 1));
            i = i2;
        }
        bArr2[7] = (byte) ((((bArr[0] & UByte.MAX_VALUE) >>> 7) * 27) ^ ((bArr[7] & UByte.MAX_VALUE) << 1));
        Bytes.xorTo(8, bArr2, bArr);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ int doFinal(byte[] bArr, int i) throws IllegalStateException, InvalidCipherTextException {
        return super.doFinal(bArr, i);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine
    protected void finishAAD(AEADBaseEngine.State state, boolean z) {
        finishAAD3(state, z);
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
    public /* bridge */ /* synthetic */ int getOutputSize(int i) {
        return super.getOutputSize(i);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ int getUpdateOutputSize(int i) {
        return super.getUpdateOutputSize(i);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ void init(boolean z, CipherParameters cipherParameters) {
        super.init(z, cipherParameters);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine
    protected void init(byte[] bArr, byte[] bArr2) {
        this.npub = bArr2;
        this.k = bArr;
        this.Y = new byte[this.BlockSize];
        this.input = new byte[16];
        this.offset = new byte[8];
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
        pho1(this.input, this.Y, bArr, i, 16);
        double_half_block(this.offset);
        Bytes.xorTo(8, this.offset, this.input);
        giftb128(this.input, this.k, this.Y);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine
    protected void processBufferDecrypt(byte[] bArr, int i, byte[] bArr2, int i2) {
        double_half_block(this.offset);
        Bytes.xor(this.BlockSize, this.Y, bArr, i, bArr2, i2);
        pho1(this.input, this.Y, bArr2, i2, this.BlockSize);
        Bytes.xorTo(8, this.offset, this.input);
        giftb128(this.input, this.k, this.Y);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine
    protected void processBufferEncrypt(byte[] bArr, int i, byte[] bArr2, int i2) {
        double_half_block(this.offset);
        Bytes.xor(this.BlockSize, this.Y, bArr, i, bArr2, i2);
        pho1(this.input, this.Y, bArr, i, this.BlockSize);
        Bytes.xorTo(8, this.offset, this.input);
        giftb128(this.input, this.k, this.Y);
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
        int len = this.dataOperator.getLen() - (this.forEncryption ? 0 : this.MAC_SIZE);
        triple_half_block(this.offset);
        if ((this.m_aadPos & 15) != 0 || this.m_state == AEADBaseEngine.State.DecInit || this.m_state == AEADBaseEngine.State.EncInit) {
            triple_half_block(this.offset);
        }
        if (len == 0) {
            triple_half_block(this.offset);
            triple_half_block(this.offset);
        }
        pho1(this.input, this.Y, this.m_aad, 0, this.m_aadPos);
        Bytes.xorTo(8, this.offset, this.input);
        giftb128(this.input, this.k, this.Y);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine
    protected void processFinalBlock(byte[] bArr, int i) {
        GiftCofbEngine giftCofbEngine;
        int len = this.dataOperator.getLen() - (this.forEncryption ? 0 : this.MAC_SIZE);
        if (len != 0) {
            triple_half_block(this.offset);
            if ((len & 15) != 0) {
                triple_half_block(this.offset);
            }
            Bytes.xor(this.m_bufPos, this.Y, this.m_buf, 0, bArr, i);
            if (this.forEncryption) {
                giftCofbEngine = this;
                giftCofbEngine.pho1(this.input, this.Y, this.m_buf, 0, this.m_bufPos);
            } else {
                giftCofbEngine = this;
                giftCofbEngine.pho1(giftCofbEngine.input, giftCofbEngine.Y, bArr, i, giftCofbEngine.m_bufPos);
            }
            Bytes.xorTo(8, giftCofbEngine.offset, giftCofbEngine.input);
            giftb128(giftCofbEngine.input, giftCofbEngine.k, giftCofbEngine.Y);
        } else {
            giftCofbEngine = this;
        }
        System.arraycopy(giftCofbEngine.Y, 0, giftCofbEngine.mac, 0, giftCofbEngine.BlockSize);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ void reset() {
        super.reset();
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine
    protected void reset(boolean z) {
        super.reset(z);
        System.arraycopy(this.npub, 0, this.input, 0, this.IV_SIZE);
        giftb128(this.input, this.k, this.Y);
        System.arraycopy(this.Y, 0, this.offset, 0, 8);
    }
}
