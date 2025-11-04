package org.bouncycastle.crypto.modes;

import com.alibaba.fastjson2.JSONB;
import com.jieli.jl_bt_ota.constant.JL_Constant;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.StreamBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.params.ParametersWithSBox;

/* loaded from: classes3.dex */
public class GCFBBlockCipher extends StreamBlockCipher {
    private static final byte[] C = {JSONB.Constants.BC_STR_ASCII_FIX_32, 0, 114, 34, 100, -55, 4, 35, -115, 58, -37, -106, 70, -23, 42, JSONB.Constants.BC_INT64_SHORT_ZERO, 24, -2, JSONB.Constants.BC_TIMESTAMP_SECONDS, -108, 0, -19, 7, 18, JSONB.Constants.BC_INT64_SHORT_MIN, -122, JL_Constant.PREFIX_FLAG_SECOND, -62, -17, 76, JSONB.Constants.BC_LOCAL_DATE, 43};
    private final CFBBlockCipher cfbEngine;
    private long counter;
    private boolean forEncryption;
    private ParametersWithIV initParams;
    private KeyParameter key;

    public GCFBBlockCipher(BlockCipher blockCipher) {
        super(blockCipher);
        this.counter = 0L;
        this.cfbEngine = new CFBBlockCipher(blockCipher, blockCipher.getBlockSize() * 8);
    }

    @Override // org.bouncycastle.crypto.StreamBlockCipher
    protected byte calculateByte(byte b) {
        long j = this.counter;
        if (j > 0 && (j & 1023) == 0) {
            BlockCipher underlyingCipher = this.cfbEngine.getUnderlyingCipher();
            underlyingCipher.init(false, this.key);
            byte[] bArr = new byte[32];
            int blockSize = underlyingCipher.getBlockSize();
            for (int i = 0; i < 32; i += blockSize) {
                underlyingCipher.processBlock(C, i, bArr, i);
            }
            KeyParameter keyParameter = new KeyParameter(bArr);
            this.key = keyParameter;
            underlyingCipher.init(true, keyParameter);
            byte[] currentIV = this.cfbEngine.getCurrentIV();
            underlyingCipher.processBlock(currentIV, 0, currentIV, 0);
            this.cfbEngine.init(this.forEncryption, new ParametersWithIV(this.key, currentIV));
        }
        this.counter++;
        return this.cfbEngine.calculateByte(b);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        String algorithmName = this.cfbEngine.getAlgorithmName();
        return algorithmName.substring(0, algorithmName.indexOf(47)) + "/G" + algorithmName.substring(algorithmName.indexOf(47) + 1);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return this.cfbEngine.getBlockSize();
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        byte[] bArr;
        ParametersWithIV parametersWithIV;
        ParametersWithIV parametersWithIV2;
        this.counter = 0L;
        this.cfbEngine.init(z, cipherParameters);
        this.forEncryption = z;
        if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV3 = (ParametersWithIV) cipherParameters;
            CipherParameters parameters = parametersWithIV3.getParameters();
            byte[] iv = parametersWithIV3.getIV();
            cipherParameters = parameters;
            bArr = iv;
        } else {
            bArr = null;
        }
        if (cipherParameters instanceof ParametersWithRandom) {
            cipherParameters = ((ParametersWithRandom) cipherParameters).getParameters();
        }
        if (cipherParameters instanceof ParametersWithSBox) {
            cipherParameters = ((ParametersWithSBox) cipherParameters).getParameters();
        }
        KeyParameter keyParameter = (KeyParameter) cipherParameters;
        this.key = keyParameter;
        if (keyParameter == null && (parametersWithIV2 = this.initParams) != null) {
            this.key = (KeyParameter) parametersWithIV2.getParameters();
        }
        this.initParams = new ParametersWithIV(this.key, (bArr != null || (parametersWithIV = this.initParams) == null) ? this.cfbEngine.getCurrentIV() : parametersWithIV.getIV());
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        processBytes(bArr, i, this.cfbEngine.getBlockSize(), bArr2, i2);
        return this.cfbEngine.getBlockSize();
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
        this.counter = 0L;
        ParametersWithIV parametersWithIV = this.initParams;
        if (parametersWithIV == null) {
            this.cfbEngine.reset();
        } else {
            this.key = (KeyParameter) parametersWithIV.getParameters();
            this.cfbEngine.init(this.forEncryption, this.initParams);
        }
    }
}
