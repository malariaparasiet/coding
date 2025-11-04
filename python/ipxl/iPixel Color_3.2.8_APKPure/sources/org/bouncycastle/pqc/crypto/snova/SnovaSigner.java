package org.bouncycastle.pqc.crypto.snova;

import java.lang.reflect.Array;
import java.security.SecureRandom;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.pqc.crypto.MessageSigner;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.GF16;

/* loaded from: classes4.dex */
public class SnovaSigner implements MessageSigner {
    private SnovaEngine engine;
    private SnovaParameters params;
    private SnovaPrivateKeyParameters privKey;
    private SnovaPublicKeyParameters pubKey;
    private SecureRandom random;
    private final SHAKEDigest shake = new SHAKEDigest(256);

    private void evaluation(byte[] bArr, MapGroup1 mapGroup1, byte[][][][] bArr2, byte[] bArr3) {
        MapGroup1 mapGroup12 = mapGroup1;
        int m = this.params.getM();
        int alpha = this.params.getAlpha();
        int n = this.params.getN();
        int l = this.params.getL();
        int lsq = this.params.getLsq();
        int o = this.params.getO();
        byte[][][] bArr4 = (byte[][][]) Array.newInstance((Class<?>) Byte.TYPE, alpha, n, lsq);
        byte[][][] bArr5 = (byte[][][]) Array.newInstance((Class<?>) Byte.TYPE, alpha, n, lsq);
        byte[] bArr6 = new byte[lsq];
        int i = 0;
        int i2 = 0;
        while (i < m) {
            int i3 = 0;
            int i4 = 0;
            while (i3 < n) {
                int i5 = 0;
                while (i5 < alpha) {
                    GF16Utils.gf16mTranMulMul(bArr3, i4, mapGroup12.aAlpha[i][i5], mapGroup12.bAlpha[i][i5], mapGroup12.qAlpha1[i][i5], mapGroup12.qAlpha2[i][i5], bArr6, bArr4[i5][i3], bArr5[i5][i3], l);
                    i5++;
                    i3 = i3;
                }
                i4 += lsq;
                i3++;
            }
            int i6 = l;
            int i7 = i;
            int i8 = 0;
            while (i8 < alpha) {
                if (i7 >= o) {
                    i7 -= o;
                }
                int i9 = i;
                int i10 = 0;
                while (i10 < n) {
                    int i11 = i2;
                    int i12 = i9;
                    int i13 = lsq;
                    int i14 = o;
                    int i15 = i7;
                    GF16Utils.gf16mMul(getPMatrix(mapGroup12, bArr2, i15, i10, 0), bArr5[i8][0], bArr6, i6);
                    int i16 = 1;
                    while (i16 < n) {
                        GF16Utils.gf16mMulTo(getPMatrix(mapGroup1, bArr2, i15, i10, i16), bArr5[i8][i16], bArr6, i6);
                        i16++;
                        m = m;
                    }
                    GF16Utils.gf16mMulTo(bArr4[i8][i10], bArr6, bArr, i11, i6);
                    i10++;
                    mapGroup12 = mapGroup1;
                    i7 = i15;
                    lsq = i13;
                    o = i14;
                    i9 = i12;
                    i2 = i11;
                }
                i8++;
                i7++;
                mapGroup12 = mapGroup1;
                o = o;
                i = i9;
            }
            i++;
            i2 += lsq;
            mapGroup12 = mapGroup1;
            l = i6;
        }
    }

    private byte[] getMessageHash(byte[] bArr) {
        byte[] bArr2 = new byte[this.shake.getDigestSize()];
        this.shake.update(bArr, 0, bArr.length);
        this.shake.doFinal(bArr2, 0);
        return bArr2;
    }

    private byte[] getPMatrix(MapGroup1 mapGroup1, byte[][][][] bArr, int i, int i2, int i3) {
        int v = this.params.getV();
        return i2 < v ? i3 < v ? mapGroup1.p11[i][i2][i3] : mapGroup1.p12[i][i2][i3 - v] : i3 < v ? mapGroup1.p21[i][i2 - v][i3] : bArr[i][i2 - v][i3 - v];
    }

    private int performGaussianElimination(byte[][] bArr, byte[] bArr2, int i) {
        int i2 = i + 1;
        int i3 = 0;
        while (i3 < i) {
            int i4 = i3;
            while (i4 < i && bArr[i4][i3] == 0) {
                i4++;
            }
            if (i4 >= i) {
                return 1;
            }
            if (i4 != i3) {
                byte[] bArr3 = bArr[i3];
                bArr[i3] = bArr[i4];
                bArr[i4] = bArr3;
            }
            byte inv = GF16.inv(bArr[i3][i3]);
            for (int i5 = i3; i5 < i2; i5++) {
                byte[] bArr4 = bArr[i3];
                bArr4[i5] = GF16.mul(bArr4[i5], inv);
            }
            int i6 = i3 + 1;
            for (int i7 = i6; i7 < i; i7++) {
                byte b = bArr[i7][i3];
                if (b != 0) {
                    for (int i8 = i3; i8 < i2; i8++) {
                        byte[] bArr5 = bArr[i7];
                        bArr5[i8] = (byte) (bArr5[i8] ^ GF16.mul(bArr[i3][i8], b));
                    }
                }
            }
            i3 = i6;
        }
        for (int i9 = i - 1; i9 >= 0; i9--) {
            byte b2 = bArr[i9][i];
            for (int i10 = i9 + 1; i10 < i; i10++) {
                b2 = (byte) (b2 ^ GF16.mul(bArr[i9][i10], bArr2[i10]));
            }
            bArr2[i9] = b2;
        }
        return 0;
    }

    void createSignedHash(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, int i3, int i4, byte[] bArr4, int i5) {
        this.shake.update(bArr, 0, i);
        this.shake.update(bArr2, 0, i2);
        this.shake.update(bArr3, i3, i4);
        this.shake.doFinal(bArr4, 0, i5);
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public byte[] generateSignature(byte[] bArr) {
        byte[] copyOfRange;
        byte[] copyOfRange2;
        byte[] messageHash = getMessageHash(bArr);
        byte[] bArr2 = new byte[this.params.getSaltLength()];
        this.random.nextBytes(bArr2);
        byte[] bArr3 = new byte[(((this.params.getN() * this.params.getLsq()) + 1) >>> 1) + this.params.getSaltLength()];
        SnovaKeyElements snovaKeyElements = new SnovaKeyElements(this.params);
        if (this.params.isSkIsSeed()) {
            byte[] privateKey = this.privKey.getPrivateKey();
            copyOfRange = Arrays.copyOfRange(privateKey, 0, 16);
            copyOfRange2 = Arrays.copyOfRange(privateKey, 16, privateKey.length);
            this.engine.genMap1T12Map2(snovaKeyElements, copyOfRange, copyOfRange2);
        } else {
            byte[] privateKey2 = this.privKey.getPrivateKey();
            int length = (privateKey2.length - 48) << 1;
            byte[] bArr4 = new byte[length];
            GF16Utils.decodeMergeInHalf(privateKey2, bArr4, length);
            SnovaKeyElements.copy4d(bArr4, SnovaKeyElements.copy4d(bArr4, SnovaKeyElements.copy4d(bArr4, SnovaKeyElements.copy3d(bArr4, SnovaKeyElements.copy3d(bArr4, SnovaKeyElements.copy3d(bArr4, SnovaKeyElements.copy3d(bArr4, SnovaKeyElements.copy3d(bArr4, 0, snovaKeyElements.map1.aAlpha), snovaKeyElements.map1.bAlpha), snovaKeyElements.map1.qAlpha1), snovaKeyElements.map1.qAlpha2), snovaKeyElements.T12), snovaKeyElements.map2.f11), snovaKeyElements.map2.f12), snovaKeyElements.map2.f21);
            copyOfRange = Arrays.copyOfRange(privateKey2, privateKey2.length - 48, privateKey2.length - 32);
            copyOfRange2 = Arrays.copyOfRange(privateKey2, privateKey2.length - 32, privateKey2.length);
        }
        signDigestCore(bArr3, messageHash, bArr2, snovaKeyElements.map1.aAlpha, snovaKeyElements.map1.bAlpha, snovaKeyElements.map1.qAlpha1, snovaKeyElements.map1.qAlpha2, snovaKeyElements.T12, snovaKeyElements.map2.f11, snovaKeyElements.map2.f12, snovaKeyElements.map2.f21, copyOfRange, copyOfRange2);
        return Arrays.concatenate(bArr3, bArr);
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public void init(boolean z, CipherParameters cipherParameters) {
        SecureRandom secureRandom;
        if (z) {
            this.pubKey = null;
            if (cipherParameters instanceof ParametersWithRandom) {
                ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
                this.privKey = (SnovaPrivateKeyParameters) parametersWithRandom.getParameters();
                secureRandom = parametersWithRandom.getRandom();
            } else {
                this.privKey = (SnovaPrivateKeyParameters) cipherParameters;
                secureRandom = CryptoServicesRegistrar.getSecureRandom();
            }
            this.random = secureRandom;
            this.params = this.privKey.getParameters();
        } else {
            SnovaPublicKeyParameters snovaPublicKeyParameters = (SnovaPublicKeyParameters) cipherParameters;
            this.pubKey = snovaPublicKeyParameters;
            this.params = snovaPublicKeyParameters.getParameters();
            this.privKey = null;
            this.random = null;
        }
        this.engine = new SnovaEngine(this.params);
    }

    void signDigestCore(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[][][] bArr4, byte[][][] bArr5, byte[][][] bArr6, byte[][][] bArr7, byte[][][] bArr8, byte[][][][] bArr9, byte[][][][] bArr10, byte[][][][] bArr11, byte[] bArr12, byte[] bArr13) {
        int i;
        int i2;
        int i3;
        byte[] bArr14;
        int i4;
        int i5;
        byte b;
        int i6;
        byte b2;
        byte[] bArr15 = bArr13;
        int m = this.params.getM();
        int l = this.params.getL();
        int lsq = this.params.getLsq();
        int alpha = this.params.getAlpha();
        int v = this.params.getV();
        int o = this.params.getO();
        int n = this.params.getN();
        int i7 = m * lsq;
        int i8 = o * lsq;
        int i9 = v * lsq;
        int i10 = (i8 + 1) >>> 1;
        byte[][] bArr16 = (byte[][]) Array.newInstance((Class<?>) Byte.TYPE, i7, i7 + 1);
        byte[][] bArr17 = (byte[][]) Array.newInstance((Class<?>) Byte.TYPE, lsq, lsq);
        byte[] bArr18 = new byte[i7];
        byte[][][] bArr19 = (byte[][][]) Array.newInstance((Class<?>) Byte.TYPE, alpha, v, lsq);
        byte[][][] bArr20 = (byte[][][]) Array.newInstance((Class<?>) Byte.TYPE, alpha, v, lsq);
        byte[] bArr21 = new byte[lsq];
        byte[] bArr22 = new byte[lsq];
        byte[] bArr23 = new byte[lsq];
        int i11 = lsq;
        byte[] bArr24 = new byte[i7];
        int i12 = n * i11;
        byte[] bArr25 = new byte[i10];
        int i13 = (i9 + 1) >>> 1;
        int i14 = alpha;
        byte[] bArr26 = new byte[i13];
        byte[] bArr27 = new byte[l];
        int i15 = l;
        int i16 = v;
        byte b3 = 0;
        byte[] bArr28 = bArr18;
        byte[] bArr29 = new byte[i12];
        int i17 = i13;
        int i18 = o;
        byte[] bArr30 = bArr22;
        byte[] bArr31 = bArr23;
        int i19 = m;
        createSignedHash(bArr12, bArr12.length, bArr2, bArr2.length, bArr3, 0, bArr3.length, bArr25, i10);
        byte[] bArr32 = bArr2;
        byte[] bArr33 = bArr3;
        GF16.decode(bArr25, 0, bArr24, 0, i7);
        byte b4 = 0;
        while (true) {
            for (int i20 = b3; i20 < bArr16.length; i20++) {
                Arrays.fill(bArr16[i20], b3);
            }
            byte b5 = (byte) (b4 + 1);
            for (int i21 = b3; i21 < i7; i21++) {
                bArr16[i21][i7] = bArr24[i21];
            }
            this.shake.update(bArr15, b3, bArr15.length);
            this.shake.update(bArr32, b3, bArr32.length);
            this.shake.update(bArr33, b3, bArr33.length);
            this.shake.update(b5);
            int i22 = i17;
            this.shake.doFinal(bArr26, b3, i22);
            byte[] bArr34 = bArr29;
            GF16.decode(bArr26, bArr34, i22 << 1);
            int i23 = b3;
            int i24 = i19;
            int i25 = i23;
            while (i23 < i24) {
                byte[] bArr35 = bArr31;
                Arrays.fill(bArr35, b3);
                int i26 = i23;
                int i27 = b3;
                int i28 = i14;
                while (i27 < i28) {
                    byte b6 = b5;
                    int i29 = i18;
                    if (i26 >= i29) {
                        i26 -= i29;
                    }
                    int i30 = i26;
                    byte[] bArr36 = bArr24;
                    int i31 = i16;
                    int i32 = 0;
                    int i33 = 0;
                    while (i32 < i31) {
                        byte[] bArr37 = bArr4[i23][i27];
                        byte[] bArr38 = bArr5[i23][i27];
                        byte[] bArr39 = bArr6[i23][i27];
                        byte[] bArr40 = bArr7[i23][i27];
                        byte[] bArr41 = bArr19[i27][i32];
                        byte[] bArr42 = bArr20[i27][i32];
                        int i34 = i32;
                        int i35 = i28;
                        byte[] bArr43 = bArr34;
                        int i36 = i11;
                        byte[] bArr44 = bArr27;
                        GF16Utils.gf16mTranMulMul(bArr43, i33, bArr37, bArr38, bArr39, bArr40, bArr44, bArr41, bArr42, i15);
                        i33 += i36;
                        i28 = i35;
                        i32 = i34 + 1;
                        bArr36 = bArr36;
                        bArr26 = bArr26;
                        bArr27 = bArr44;
                        i11 = i36;
                        bArr34 = bArr43;
                    }
                    byte[] bArr45 = bArr34;
                    int i37 = i28;
                    byte[] bArr46 = bArr36;
                    int i38 = i11;
                    byte[] bArr47 = bArr27;
                    int i39 = 0;
                    byte[] bArr48 = bArr26;
                    int i40 = 0;
                    while (i40 < i31) {
                        int i41 = i39;
                        while (i41 < i31) {
                            GF16Utils.gf16mMulMulTo(bArr19[i27][i40], bArr9[i30][i40][i41], bArr20[i27][i41], bArr47, bArr35, i15);
                            i41++;
                            i38 = i38;
                            i23 = i23;
                            i24 = i24;
                            i27 = i27;
                            i22 = i22;
                        }
                        i40++;
                        i38 = i38;
                        i27 = i27;
                        i39 = 0;
                    }
                    i28 = i37;
                    i26 = i30 + 1;
                    i11 = i38;
                    i18 = i29;
                    i16 = i31;
                    b5 = b6;
                    bArr24 = bArr46;
                    i22 = i22;
                    i27++;
                    i23 = i23;
                    bArr26 = bArr48;
                    bArr27 = bArr47;
                    bArr34 = bArr45;
                }
                int i42 = i22;
                int i43 = i24;
                byte[] bArr49 = bArr34;
                bArr31 = bArr35;
                byte b7 = b5;
                int i44 = i28;
                byte[] bArr50 = bArr24;
                int i45 = i11;
                byte[] bArr51 = bArr27;
                int i46 = i15;
                int i47 = i16;
                int i48 = i18;
                byte[] bArr52 = bArr26;
                int i49 = i23;
                int i50 = 0;
                int i51 = 0;
                while (i50 < i46) {
                    int i52 = i51;
                    int i53 = 0;
                    while (i53 < i46) {
                        byte[] bArr53 = bArr16[i25 + i52];
                        bArr53[i7] = (byte) (bArr31[i52] ^ bArr53[i7]);
                        i53++;
                        i52++;
                    }
                    i50++;
                    i51 = i52;
                }
                int i54 = 0;
                int i55 = 0;
                while (i54 < i48) {
                    int i56 = i49;
                    int i57 = 0;
                    while (i57 < i44) {
                        if (i56 >= i48) {
                            i56 -= i48;
                        }
                        int i58 = i56;
                        for (int i59 = 0; i59 < i45; i59++) {
                            Arrays.fill(bArr17[i59], (byte) 0);
                        }
                        int i60 = 0;
                        while (i60 < i47) {
                            int i61 = i57;
                            int i62 = i60;
                            GF16Utils.gf16mMulMul(bArr19[i57][i60], bArr10[i58][i60][i54], bArr7[i49][i57], bArr51, bArr21, i46);
                            byte[] bArr54 = bArr30;
                            GF16Utils.gf16mMulMul(bArr6[i49][i61], bArr11[i58][i54][i62], bArr20[i61][i62], bArr51, bArr54, i46);
                            int i63 = 0;
                            int i64 = 0;
                            int i65 = 0;
                            while (i63 < i45) {
                                if (i64 == i46) {
                                    i65 += i46;
                                    i64 = 0;
                                }
                                int i66 = i44;
                                byte b8 = bArr21[i65];
                                byte b9 = bArr54[i64];
                                int i67 = 0;
                                int i68 = 0;
                                int i69 = 0;
                                int i70 = 0;
                                int i71 = i63;
                                int i72 = 0;
                                while (i67 < i45) {
                                    if (i72 == i46) {
                                        i68++;
                                        i69 += i46;
                                        i5 = i67;
                                        b = bArr21[i65 + i68];
                                        b2 = bArr54[i69 + i64];
                                        i6 = 0;
                                        i70 = 0;
                                    } else {
                                        byte b10 = b8;
                                        i5 = i67;
                                        b = b10;
                                        i6 = i72;
                                        b2 = b9;
                                    }
                                    int i73 = i64;
                                    byte b11 = bArr5[i49][i61][i70 + i64];
                                    int i74 = i65;
                                    byte b12 = bArr4[i49][i61][i65 + i6];
                                    byte[] bArr55 = bArr17[i71];
                                    bArr55[i5] = (byte) (bArr55[i5] ^ (GF16.mul(b, b11) ^ GF16.mul(b12, b2)));
                                    int i75 = i5 + 1;
                                    i70 += i46;
                                    b8 = b;
                                    b9 = b2;
                                    i67 = i75;
                                    i72 = i6 + 1;
                                    i64 = i73;
                                    i65 = i74;
                                }
                                i63 = i71 + 1;
                                i64++;
                                i44 = i66;
                            }
                            i60 = i62 + 1;
                            i57 = i61;
                            bArr30 = bArr54;
                        }
                        int i76 = i57;
                        byte[] bArr56 = bArr30;
                        int i77 = i44;
                        int i78 = 0;
                        while (i78 < i45) {
                            int i79 = 0;
                            while (i79 < i45) {
                                byte[] bArr57 = bArr16[i25 + i78];
                                int i80 = i55 + i79;
                                bArr57[i80] = (byte) (bArr57[i80] ^ bArr17[i78][i79]);
                                i79++;
                                i78 = i78;
                            }
                            i78++;
                        }
                        i57 = i76 + 1;
                        i56 = i58 + 1;
                        i44 = i77;
                        bArr30 = bArr56;
                    }
                    i54++;
                    i55 += i45;
                    bArr30 = bArr30;
                }
                i23 = i49 + 1;
                i25 += i45;
                i15 = i46;
                i11 = i45;
                i18 = i48;
                i16 = i47;
                i24 = i43;
                b5 = b7;
                i14 = i44;
                bArr24 = bArr50;
                bArr26 = bArr52;
                i22 = i42;
                b3 = 0;
                bArr27 = bArr51;
                bArr30 = bArr30;
                bArr34 = bArr49;
            }
            i17 = i22;
            int i81 = i24;
            bArr29 = bArr34;
            byte[] bArr58 = bArr24;
            i = i11;
            byte[] bArr59 = bArr30;
            byte[] bArr60 = bArr27;
            i2 = i15;
            i3 = i16;
            bArr14 = bArr28;
            byte[] bArr61 = bArr26;
            int i82 = i14;
            byte b13 = b5;
            i4 = i18;
            if (performGaussianElimination(bArr16, bArr14, i7) == 0) {
                break;
            }
            bArr32 = bArr2;
            bArr28 = bArr14;
            i15 = i2;
            i11 = i;
            i18 = i4;
            i16 = i3;
            i19 = i81;
            b4 = b13;
            i14 = i82;
            bArr24 = bArr58;
            bArr26 = bArr61;
            b3 = 0;
            bArr33 = bArr3;
            bArr15 = bArr13;
            bArr27 = bArr60;
            bArr30 = bArr59;
        }
        int i83 = 0;
        int i84 = 0;
        while (i83 < i3) {
            int i85 = 0;
            int i86 = 0;
            while (i86 < i4) {
                byte[] bArr62 = bArr29;
                GF16Utils.gf16mMulTo(bArr8[i83][i86], bArr14, i85, bArr62, i84, i2);
                i86++;
                i85 += i;
                bArr29 = bArr62;
            }
            i83++;
            i84 += i;
        }
        byte[] bArr63 = bArr29;
        System.arraycopy(bArr14, 0, bArr63, i9, i8);
        GF16.encode(bArr63, bArr, i12);
        System.arraycopy(bArr3, 0, bArr, bArr.length - 16, 16);
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public boolean verifySignature(byte[] bArr, byte[] bArr2) {
        byte[] messageHash = getMessageHash(bArr);
        MapGroup1 mapGroup1 = new MapGroup1(this.params);
        byte[] encoded = this.pubKey.getEncoded();
        byte[] copyOf = Arrays.copyOf(encoded, 16);
        byte[] copyOfRange = Arrays.copyOfRange(encoded, 16, encoded.length);
        this.engine.genABQP(mapGroup1, copyOf);
        byte[][][][] bArr3 = (byte[][][][]) Array.newInstance((Class<?>) Byte.TYPE, this.params.getM(), this.params.getO(), this.params.getO(), this.params.getLsq());
        if ((this.params.getLsq() & 1) == 0) {
            MapGroup1.decodeP(copyOfRange, 0, bArr3, copyOfRange.length << 1);
        } else {
            int length = copyOfRange.length << 1;
            byte[] bArr4 = new byte[length];
            GF16.decode(copyOfRange, bArr4, length);
            MapGroup1.fillP(bArr4, 0, bArr3, length);
        }
        return verifySignatureCore(messageHash, bArr2, copyOf, mapGroup1, bArr3);
    }

    boolean verifySignatureCore(byte[] bArr, byte[] bArr2, byte[] bArr3, MapGroup1 mapGroup1, byte[][][][] bArr4) {
        int lsq = this.params.getLsq();
        int o = this.params.getO() * lsq;
        int i = (o + 1) >>> 1;
        int saltLength = this.params.getSaltLength();
        int m = this.params.getM();
        int n = this.params.getN() * lsq;
        byte[] bArr5 = new byte[i];
        createSignedHash(bArr3, bArr3.length, bArr, bArr.length, bArr2, (n + 1) >>> 1, saltLength, bArr5, i);
        if ((o & 1) != 0) {
            int i2 = i - 1;
            bArr5[i2] = (byte) (bArr5[i2] & 15);
        }
        byte[] bArr6 = new byte[n];
        GF16.decode(bArr2, 0, bArr6, 0, n);
        int i3 = m * lsq;
        byte[] bArr7 = new byte[i3];
        evaluation(bArr7, mapGroup1, bArr4, bArr6);
        byte[] bArr8 = new byte[i];
        GF16.encode(bArr7, bArr8, i3);
        return Arrays.areEqual(bArr5, bArr8);
    }
}
