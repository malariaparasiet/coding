package org.bouncycastle.pqc.crypto.rainbow;

import java.lang.reflect.Array;
import java.security.SecureRandom;
import kotlin.UByte;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.pqc.crypto.MessageSigner;
import org.bouncycastle.util.Arrays;

/* loaded from: classes4.dex */
public class RainbowSigner implements MessageSigner {
    private static final int MAXITS = 65536;
    private ComputeInField cf = new ComputeInField();
    private Digest hashAlgo;
    private RainbowKeyParameters key;
    private SecureRandom random;
    int signableDocumentLength;
    private Version version;

    /* renamed from: org.bouncycastle.pqc.crypto.rainbow.RainbowSigner$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$bouncycastle$pqc$crypto$rainbow$Version;

        static {
            int[] iArr = new int[Version.values().length];
            $SwitchMap$org$bouncycastle$pqc$crypto$rainbow$Version = iArr;
            try {
                iArr[Version.CLASSIC.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$bouncycastle$pqc$crypto$rainbow$Version[Version.CIRCUMZENITHAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$bouncycastle$pqc$crypto$rainbow$Version[Version.COMPRESSED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private byte[] genSignature(byte[] bArr) {
        short[] sArr;
        int i;
        int i2;
        byte[] bArr2 = new byte[this.hashAlgo.getDigestSize()];
        this.hashAlgo.update(bArr, 0, bArr.length);
        this.hashAlgo.doFinal(bArr2, 0);
        int v1 = this.key.getParameters().getV1();
        int o1 = this.key.getParameters().getO1();
        int o2 = this.key.getParameters().getO2();
        int m = this.key.getParameters().getM();
        int n = this.key.getParameters().getN();
        RainbowPrivateKeyParameters rainbowPrivateKeyParameters = (RainbowPrivateKeyParameters) this.key;
        this.random = new RainbowDRBG(RainbowUtil.hash(this.hashAlgo, rainbowPrivateKeyParameters.sk_seed, bArr2, new byte[this.hashAlgo.getDigestSize()]), rainbowPrivateKeyParameters.getParameters().getHash_algo());
        short[] sArr2 = new short[v1];
        short[] sArr3 = new short[o1];
        short[] sArr4 = new short[o2];
        short[][] sArr5 = (short[][]) Array.newInstance((Class<?>) Short.TYPE, o2, o1);
        int i3 = 0;
        short[][] sArr6 = (short[][]) Array.newInstance((Class<?>) Short.TYPE, o2, o2);
        byte[] bArr3 = new byte[rainbowPrivateKeyParameters.getParameters().getLen_salt()];
        short[] sArr7 = new short[m];
        short[] sArr8 = new short[o1];
        short[] sArr9 = new short[o2];
        int i4 = 0;
        short[][] sArr10 = null;
        while (sArr10 == null && i4 < 65536) {
            byte[] bArr4 = new byte[v1];
            this.random.nextBytes(bArr4);
            int i5 = 0;
            while (i5 < v1) {
                byte[] bArr5 = bArr4;
                sArr2[i5] = (short) (bArr5[i5] & UByte.MAX_VALUE);
                i5++;
                bArr4 = bArr5;
            }
            short[][] sArr11 = (short[][]) Array.newInstance((Class<?>) Short.TYPE, o1, o1);
            int i6 = 0;
            while (true) {
                i2 = i4;
                if (i6 < v1) {
                    int i7 = 0;
                    while (i7 < o1) {
                        int i8 = i7;
                        int i9 = 0;
                        while (i9 < o1) {
                            int i10 = i9;
                            int i11 = i6;
                            short multElem = GF2Field.multElem(rainbowPrivateKeyParameters.l1_F2[i8][i6][i10], sArr2[i11]);
                            short[] sArr12 = sArr11[i8];
                            sArr12[i10] = GF2Field.addElem(sArr12[i10], multElem);
                            i9 = i10 + 1;
                            i6 = i11;
                        }
                        i7 = i8 + 1;
                    }
                    i6++;
                    i4 = i2;
                }
            }
            sArr10 = this.cf.inverse(sArr11);
            i4 = i2 + 1;
        }
        int i12 = i4;
        int i13 = 0;
        while (i13 < o1) {
            int i14 = i13;
            sArr3[i14] = this.cf.multiplyMatrix_quad(rainbowPrivateKeyParameters.l1_F1[i14], sArr2);
            i13 = i14 + 1;
        }
        int i15 = 0;
        while (i15 < v1) {
            int i16 = 0;
            while (true) {
                i = i15;
                if (i16 < o2) {
                    int i17 = i16;
                    sArr9[i17] = this.cf.multiplyMatrix_quad(rainbowPrivateKeyParameters.l2_F1[i17], sArr2);
                    int i18 = 0;
                    while (i18 < o1) {
                        int i19 = i18;
                        short multElem2 = GF2Field.multElem(rainbowPrivateKeyParameters.l2_F2[i17][i][i18], sArr2[i]);
                        short[] sArr13 = sArr5[i17];
                        sArr13[i19] = GF2Field.addElem(sArr13[i19], multElem2);
                        i18 = i19 + 1;
                    }
                    int i20 = 0;
                    while (i20 < o2) {
                        int i21 = i20;
                        short multElem3 = GF2Field.multElem(rainbowPrivateKeyParameters.l2_F3[i17][i][i20], sArr2[i]);
                        short[] sArr14 = sArr6[i17];
                        sArr14[i21] = GF2Field.addElem(sArr14[i21], multElem3);
                        i20 = i21 + 1;
                    }
                    i16 = i17 + 1;
                    i15 = i;
                }
            }
            i15 = i + 1;
        }
        byte[] bArr6 = new byte[m];
        short[] sArr15 = sArr8;
        short[] sArr16 = null;
        int i22 = i12;
        while (true) {
            sArr = sArr2;
            if (sArr16 != null || i22 >= 65536) {
                break;
            }
            int[] iArr = new int[2];
            iArr[1] = o2;
            iArr[i3] = o2;
            short[][] sArr17 = (short[][]) Array.newInstance((Class<?>) Short.TYPE, iArr);
            this.random.nextBytes(bArr3);
            short[] makeMessageRepresentative = makeMessageRepresentative(RainbowUtil.hash(this.hashAlgo, bArr2, bArr3, bArr6));
            byte[] bArr7 = bArr2;
            byte[] bArr8 = bArr6;
            byte[] bArr9 = bArr3;
            int i23 = i3;
            System.arraycopy(this.cf.addVect(Arrays.copyOf(makeMessageRepresentative, o1), this.cf.multiplyMatrix(rainbowPrivateKeyParameters.s1, Arrays.copyOfRange(makeMessageRepresentative, o1, m))), i23, sArr7, i23, o1);
            System.arraycopy(makeMessageRepresentative, o1, sArr7, o1, o2);
            short[] multiplyMatrix = this.cf.multiplyMatrix(sArr10, this.cf.addVect(sArr3, Arrays.copyOf(sArr7, o1)));
            short[] multiplyMatrix2 = this.cf.multiplyMatrix(sArr5, multiplyMatrix);
            int i24 = 0;
            while (i24 < o2) {
                sArr4[i24] = this.cf.multiplyMatrix_quad(rainbowPrivateKeyParameters.l2_F5[i24], multiplyMatrix);
                i24++;
                sArr10 = sArr10;
            }
            short[][] sArr18 = sArr10;
            short[] sArr19 = sArr9;
            short[] addVect = this.cf.addVect(this.cf.addVect(this.cf.addVect(multiplyMatrix2, sArr4), sArr19), Arrays.copyOfRange(sArr7, o1, m));
            for (int i25 = 0; i25 < o1; i25++) {
                int i26 = 0;
                while (true) {
                    short[] sArr20 = multiplyMatrix;
                    if (i26 < o2) {
                        int i27 = 0;
                        while (i27 < o2) {
                            int i28 = i27;
                            int i29 = m;
                            short multElem4 = GF2Field.multElem(rainbowPrivateKeyParameters.l2_F6[i26][i25][i28], sArr20[i25]);
                            short[] sArr21 = sArr17[i26];
                            sArr21[i28] = GF2Field.addElem(sArr21[i28], multElem4);
                            i27 = i28 + 1;
                            m = i29;
                        }
                        i26++;
                        multiplyMatrix = sArr20;
                    }
                }
            }
            i22++;
            sArr16 = this.cf.solveEquation(this.cf.addMatrix(sArr17, sArr6), addVect);
            sArr15 = multiplyMatrix;
            sArr9 = sArr19;
            sArr2 = sArr;
            bArr2 = bArr7;
            bArr6 = bArr8;
            bArr3 = bArr9;
            sArr10 = sArr18;
            m = m;
            i3 = 0;
        }
        byte[] bArr10 = bArr3;
        short[] sArr22 = sArr16 == null ? new short[o2] : sArr16;
        short[] addVect2 = this.cf.addVect(this.cf.addVect(sArr, this.cf.multiplyMatrix(rainbowPrivateKeyParameters.t1, sArr15)), this.cf.multiplyMatrix(rainbowPrivateKeyParameters.t4, sArr22));
        short[] addVect3 = this.cf.addVect(sArr15, this.cf.multiplyMatrix(rainbowPrivateKeyParameters.t3, sArr22));
        short[] copyOf = Arrays.copyOf(addVect2, n);
        System.arraycopy(addVect3, 0, copyOf, v1, o1);
        System.arraycopy(sArr22, 0, copyOf, o1 + v1, o2);
        if (i22 != 65536) {
            return Arrays.concatenate(RainbowUtil.convertArray(copyOf), bArr10);
        }
        throw new IllegalStateException("unable to generate signature - LES not solvable");
    }

    private short[] makeMessageRepresentative(byte[] bArr) {
        int i = this.signableDocumentLength;
        short[] sArr = new short[i];
        int i2 = 0;
        int i3 = 0;
        while (i2 < bArr.length) {
            sArr[i2] = (short) (bArr[i3] & UByte.MAX_VALUE);
            i3++;
            i2++;
            if (i2 >= i) {
                break;
            }
        }
        return sArr;
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public byte[] generateSignature(byte[] bArr) {
        return genSignature(bArr);
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public void init(boolean z, CipherParameters cipherParameters) {
        RainbowKeyParameters rainbowKeyParameters;
        if (z) {
            if (cipherParameters instanceof ParametersWithRandom) {
                ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
                this.random = parametersWithRandom.getRandom();
                rainbowKeyParameters = (RainbowKeyParameters) parametersWithRandom.getParameters();
            } else {
                rainbowKeyParameters = (RainbowKeyParameters) cipherParameters;
                SecureRandom secureRandom = CryptoServicesRegistrar.getSecureRandom();
                byte[] bArr = new byte[rainbowKeyParameters.getParameters().getLen_skseed()];
                secureRandom.nextBytes(bArr);
                this.random = new RainbowDRBG(bArr, rainbowKeyParameters.getParameters().getHash_algo());
            }
            this.version = rainbowKeyParameters.getParameters().getVersion();
            this.key = rainbowKeyParameters;
        } else {
            RainbowKeyParameters rainbowKeyParameters2 = (RainbowKeyParameters) cipherParameters;
            this.key = rainbowKeyParameters2;
            this.version = rainbowKeyParameters2.getParameters().getVersion();
        }
        this.signableDocumentLength = this.key.getDocLength();
        this.hashAlgo = this.key.getParameters().getHash_algo();
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public boolean verifySignature(byte[] bArr, byte[] bArr2) {
        short[] publicMap;
        byte[] bArr3 = new byte[this.hashAlgo.getDigestSize()];
        this.hashAlgo.update(bArr, 0, bArr.length);
        this.hashAlgo.doFinal(bArr3, 0);
        int m = this.key.getParameters().getM();
        int n = this.key.getParameters().getN();
        RainbowPublicMap rainbowPublicMap = new RainbowPublicMap(this.key.getParameters());
        short[] makeMessageRepresentative = makeMessageRepresentative(RainbowUtil.hash(this.hashAlgo, bArr3, Arrays.copyOfRange(bArr2, n, bArr2.length), new byte[m]));
        short[] convertArray = RainbowUtil.convertArray(Arrays.copyOfRange(bArr2, 0, n));
        int i = AnonymousClass1.$SwitchMap$org$bouncycastle$pqc$crypto$rainbow$Version[this.version.ordinal()];
        if (i == 1) {
            publicMap = rainbowPublicMap.publicMap((RainbowPublicKeyParameters) this.key, convertArray);
        } else {
            if (i != 2 && i != 3) {
                throw new IllegalArgumentException("No valid version. Please choose one of the following: classic, circumzenithal, compressed");
            }
            publicMap = rainbowPublicMap.publicMap_cyclic((RainbowPublicKeyParameters) this.key, convertArray);
        }
        return RainbowUtil.equals(makeMessageRepresentative, publicMap);
    }
}
