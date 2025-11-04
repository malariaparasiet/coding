package org.bouncycastle.pqc.legacy.crypto.ntru;

import com.j256.ormlite.stmt.query.SimpleComparison;
import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.pqc.legacy.math.ntru.polynomial.DenseTernaryPolynomial;
import org.bouncycastle.pqc.legacy.math.ntru.polynomial.IntegerPolynomial;
import org.bouncycastle.pqc.legacy.math.ntru.polynomial.Polynomial;
import org.bouncycastle.pqc.legacy.math.ntru.polynomial.ProductFormPolynomial;
import org.bouncycastle.pqc.legacy.math.ntru.polynomial.SparseTernaryPolynomial;
import org.bouncycastle.pqc.legacy.math.ntru.polynomial.TernaryPolynomial;
import org.bouncycastle.util.Arrays;

/* loaded from: classes4.dex */
public class NTRUEngine implements AsymmetricBlockCipher {
    private boolean forEncryption;
    private NTRUEncryptionParameters params;
    private NTRUEncryptionPrivateKeyParameters privKey;
    private NTRUEncryptionPublicKeyParameters pubKey;
    private SecureRandom random;

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0061, code lost:
    
        if (r4 < r12) goto L28;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private org.bouncycastle.pqc.legacy.math.ntru.polynomial.IntegerPolynomial MGF(byte[] r11, int r12, int r13, boolean r14) {
        /*
            r10 = this;
            org.bouncycastle.pqc.legacy.crypto.ntru.NTRUEncryptionParameters r0 = r10.params
            org.bouncycastle.crypto.Digest r0 = r0.hashAlg
            int r1 = r0.getDigestSize()
            int r2 = r13 * r1
            byte[] r2 = new byte[r2]
            if (r14 == 0) goto L12
            byte[] r11 = r10.calcHash(r0, r11)
        L12:
            r14 = 0
            r3 = r14
        L14:
            if (r3 >= r13) goto L29
            int r4 = r11.length
            r0.update(r11, r14, r4)
            r10.putInt(r0, r3)
            byte[] r4 = r10.calcHash(r0)
            int r5 = r3 * r1
            java.lang.System.arraycopy(r4, r14, r2, r5, r1)
            int r3 = r3 + 1
            goto L14
        L29:
            org.bouncycastle.pqc.legacy.math.ntru.polynomial.IntegerPolynomial r13 = new org.bouncycastle.pqc.legacy.math.ntru.polynomial.IntegerPolynomial
            r13.<init>(r12)
        L2e:
            r1 = r14
            r4 = r1
        L30:
            int r5 = r2.length
            if (r1 == r5) goto L61
            r5 = r2[r1]
            r5 = r5 & 255(0xff, float:3.57E-43)
            r6 = 243(0xf3, float:3.4E-43)
            if (r5 < r6) goto L3c
            goto L5e
        L3c:
            r6 = r14
        L3d:
            r7 = 4
            if (r6 >= r7) goto L53
            int r7 = r5 % 3
            int[] r8 = r13.coeffs
            int r9 = r7 + (-1)
            r8[r4] = r9
            int r4 = r4 + 1
            if (r4 != r12) goto L4d
            goto L63
        L4d:
            int r5 = r5 - r7
            int r5 = r5 / 3
            int r6 = r6 + 1
            goto L3d
        L53:
            int[] r6 = r13.coeffs
            int r5 = r5 + (-1)
            r6[r4] = r5
            int r4 = r4 + 1
            if (r4 != r12) goto L5e
            goto L63
        L5e:
            int r1 = r1 + 1
            goto L30
        L61:
            if (r4 < r12) goto L64
        L63:
            return r13
        L64:
            int r1 = r11.length
            r0.update(r11, r14, r1)
            r10.putInt(r0, r3)
            byte[] r2 = r10.calcHash(r0)
            int r3 = r3 + 1
            goto L2e
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.pqc.legacy.crypto.ntru.NTRUEngine.MGF(byte[], int, int, boolean):org.bouncycastle.pqc.legacy.math.ntru.polynomial.IntegerPolynomial");
    }

    private byte[] buildSData(byte[] bArr, byte[] bArr2, int i, byte[] bArr3, byte[] bArr4) {
        byte[] bArr5 = new byte[bArr.length + i + bArr3.length + bArr4.length];
        System.arraycopy(bArr, 0, bArr5, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr5, bArr.length, bArr2.length);
        System.arraycopy(bArr3, 0, bArr5, bArr.length + bArr2.length, bArr3.length);
        System.arraycopy(bArr4, 0, bArr5, bArr.length + bArr2.length + bArr3.length, bArr4.length);
        return bArr5;
    }

    private byte[] calcHash(Digest digest) {
        byte[] bArr = new byte[digest.getDigestSize()];
        digest.doFinal(bArr, 0);
        return bArr;
    }

    private byte[] calcHash(Digest digest, byte[] bArr) {
        byte[] bArr2 = new byte[digest.getDigestSize()];
        digest.update(bArr, 0, bArr.length);
        digest.doFinal(bArr2, 0);
        return bArr2;
    }

    private byte[] copyOf(byte[] bArr, int i) {
        byte[] bArr2 = new byte[i];
        if (i >= bArr.length) {
            i = bArr.length;
        }
        System.arraycopy(bArr, 0, bArr2, 0, i);
        return bArr2;
    }

    private byte[] decrypt(byte[] bArr, NTRUEncryptionPrivateKeyParameters nTRUEncryptionPrivateKeyParameters) throws InvalidCipherTextException {
        Polynomial polynomial = nTRUEncryptionPrivateKeyParameters.t;
        IntegerPolynomial integerPolynomial = nTRUEncryptionPrivateKeyParameters.fp;
        IntegerPolynomial integerPolynomial2 = nTRUEncryptionPrivateKeyParameters.h;
        int i = this.params.N;
        int i2 = this.params.q;
        int i3 = this.params.db;
        int i4 = this.params.maxMsgLenBytes;
        int i5 = this.params.dm0;
        int i6 = this.params.pkLen;
        int i7 = this.params.minCallsMask;
        boolean z = this.params.hashSeed;
        byte[] bArr2 = this.params.oid;
        if (i4 > 255) {
            throw new DataLengthException("maxMsgLenBytes values bigger than 255 are not supported");
        }
        int i8 = i3 / 8;
        IntegerPolynomial fromBinary = IntegerPolynomial.fromBinary(bArr, i, i2);
        IntegerPolynomial decrypt = decrypt(fromBinary, polynomial, integerPolynomial);
        if (decrypt.count(-1) < i5) {
            throw new InvalidCipherTextException("Less than dm0 coefficients equal -1");
        }
        if (decrypt.count(0) < i5) {
            throw new InvalidCipherTextException("Less than dm0 coefficients equal 0");
        }
        if (decrypt.count(1) < i5) {
            throw new InvalidCipherTextException("Less than dm0 coefficients equal 1");
        }
        IntegerPolynomial integerPolynomial3 = (IntegerPolynomial) fromBinary.clone();
        integerPolynomial3.sub(decrypt);
        integerPolynomial3.modPositive(i2);
        IntegerPolynomial integerPolynomial4 = (IntegerPolynomial) integerPolynomial3.clone();
        integerPolynomial4.modPositive(4);
        decrypt.sub(MGF(integerPolynomial4.toBinary(4), i, i7, z));
        decrypt.mod3();
        byte[] binary3Sves = decrypt.toBinary3Sves();
        byte[] bArr3 = new byte[i8];
        System.arraycopy(binary3Sves, 0, bArr3, 0, i8);
        int i9 = binary3Sves[i8] & 255;
        if (i9 > i4) {
            throw new InvalidCipherTextException("Message too long: " + i9 + SimpleComparison.GREATER_THAN_OPERATION + i4);
        }
        byte[] bArr4 = new byte[i9];
        int i10 = i8 + 1;
        System.arraycopy(binary3Sves, i10, bArr4, 0, i9);
        int i11 = i10 + i9;
        int length = binary3Sves.length - i11;
        byte[] bArr5 = new byte[length];
        System.arraycopy(binary3Sves, i11, bArr5, 0, length);
        if (!Arrays.constantTimeAreEqual(bArr5, new byte[length])) {
            throw new InvalidCipherTextException("The message is not followed by zeroes");
        }
        IntegerPolynomial mult = generateBlindingPoly(buildSData(bArr2, bArr4, i9, bArr3, copyOf(integerPolynomial2.toBinary(i2), i6 / 8)), bArr4).mult(integerPolynomial2);
        mult.modPositive(i2);
        if (mult.equals(integerPolynomial3)) {
            return bArr4;
        }
        throw new InvalidCipherTextException("Invalid message encoding");
    }

    private byte[] encrypt(byte[] bArr, NTRUEncryptionPublicKeyParameters nTRUEncryptionPublicKeyParameters) {
        byte[] bArr2 = bArr;
        IntegerPolynomial integerPolynomial = nTRUEncryptionPublicKeyParameters.h;
        int i = this.params.N;
        int i2 = this.params.q;
        int i3 = this.params.maxMsgLenBytes;
        int i4 = this.params.db;
        int i5 = this.params.bufferLenBits;
        int i6 = this.params.dm0;
        int i7 = this.params.pkLen;
        int i8 = this.params.minCallsMask;
        boolean z = this.params.hashSeed;
        byte[] bArr3 = this.params.oid;
        int length = bArr2.length;
        if (i3 > 255) {
            throw new IllegalArgumentException("llen values bigger than 1 are not supported");
        }
        if (length > i3) {
            throw new DataLengthException("Message too long: " + length + SimpleComparison.GREATER_THAN_OPERATION + i3);
        }
        while (true) {
            int i9 = i4 / 8;
            byte[] bArr4 = new byte[i9];
            byte[] bArr5 = bArr3;
            this.random.nextBytes(bArr4);
            int i10 = (i3 + 1) - length;
            int i11 = i4;
            int i12 = i5;
            byte[] bArr6 = new byte[i12 / 8];
            int i13 = i7;
            System.arraycopy(bArr4, 0, bArr6, 0, i9);
            bArr6[i9] = (byte) length;
            int i14 = i9 + 1;
            System.arraycopy(bArr2, 0, bArr6, i14, bArr2.length);
            System.arraycopy(new byte[i10], 0, bArr6, i14 + bArr2.length, i10);
            IntegerPolynomial fromBinary3Sves = IntegerPolynomial.fromBinary3Sves(bArr6, i);
            length = length;
            bArr3 = bArr5;
            IntegerPolynomial mult = generateBlindingPoly(buildSData(bArr3, bArr2, length, bArr4, copyOf(integerPolynomial.toBinary(i2), i13 / 8)), bArr6).mult(integerPolynomial, i2);
            IntegerPolynomial integerPolynomial2 = (IntegerPolynomial) mult.clone();
            integerPolynomial2.modPositive(4);
            fromBinary3Sves.add(MGF(integerPolynomial2.toBinary(4), i, i8, z));
            fromBinary3Sves.mod3();
            if (fromBinary3Sves.count(-1) >= i6 && fromBinary3Sves.count(0) >= i6 && fromBinary3Sves.count(1) >= i6) {
                mult.add(fromBinary3Sves, i2);
                mult.ensurePositive(i2);
                return mult.toBinary(i2);
            }
            bArr2 = bArr;
            i4 = i11;
            i5 = i12;
            i7 = i13;
        }
    }

    private int[] generateBlindingCoeffs(IndexGenerator indexGenerator, int i) {
        int[] iArr = new int[this.params.N];
        for (int i2 = -1; i2 <= 1; i2 += 2) {
            int i3 = 0;
            while (i3 < i) {
                int nextIndex = indexGenerator.nextIndex();
                if (iArr[nextIndex] == 0) {
                    iArr[nextIndex] = i2;
                    i3++;
                }
            }
        }
        return iArr;
    }

    private Polynomial generateBlindingPoly(byte[] bArr, byte[] bArr2) {
        IndexGenerator indexGenerator = new IndexGenerator(bArr, this.params);
        if (this.params.polyType == 1) {
            return new ProductFormPolynomial(new SparseTernaryPolynomial(generateBlindingCoeffs(indexGenerator, this.params.dr1)), new SparseTernaryPolynomial(generateBlindingCoeffs(indexGenerator, this.params.dr2)), new SparseTernaryPolynomial(generateBlindingCoeffs(indexGenerator, this.params.dr3)));
        }
        int i = this.params.dr;
        boolean z = this.params.sparse;
        int[] generateBlindingCoeffs = generateBlindingCoeffs(indexGenerator, i);
        return z ? new SparseTernaryPolynomial(generateBlindingCoeffs) : new DenseTernaryPolynomial(generateBlindingCoeffs);
    }

    private int log2(int i) {
        if (i == 2048) {
            return 11;
        }
        throw new IllegalStateException("log2 not fully implemented");
    }

    private void putInt(Digest digest, int i) {
        digest.update((byte) (i >> 24));
        digest.update((byte) (i >> 16));
        digest.update((byte) (i >> 8));
        digest.update((byte) i);
    }

    protected IntegerPolynomial decrypt(IntegerPolynomial integerPolynomial, Polynomial polynomial, IntegerPolynomial integerPolynomial2) {
        IntegerPolynomial mult;
        if (this.params.fastFp) {
            mult = polynomial.mult(integerPolynomial, this.params.q);
            mult.mult(3);
            mult.add(integerPolynomial);
        } else {
            mult = polynomial.mult(integerPolynomial, this.params.q);
        }
        mult.center0(this.params.q);
        mult.mod3();
        if (!this.params.fastFp) {
            mult = new DenseTernaryPolynomial(mult).mult(integerPolynomial2, 3);
        }
        mult.center0(3);
        return mult;
    }

    protected IntegerPolynomial encrypt(IntegerPolynomial integerPolynomial, TernaryPolynomial ternaryPolynomial, IntegerPolynomial integerPolynomial2) {
        IntegerPolynomial mult = ternaryPolynomial.mult(integerPolynomial2, this.params.q);
        mult.add(integerPolynomial, this.params.q);
        mult.ensurePositive(this.params.q);
        return mult;
    }

    @Override // org.bouncycastle.crypto.AsymmetricBlockCipher
    public int getInputBlockSize() {
        return this.params.maxMsgLenBytes;
    }

    @Override // org.bouncycastle.crypto.AsymmetricBlockCipher
    public int getOutputBlockSize() {
        return ((this.params.N * log2(this.params.q)) + 7) / 8;
    }

    @Override // org.bouncycastle.crypto.AsymmetricBlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        SecureRandom secureRandom;
        this.forEncryption = z;
        if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            secureRandom = parametersWithRandom.getRandom();
            cipherParameters = parametersWithRandom.getParameters();
        } else {
            secureRandom = null;
        }
        if (z) {
            NTRUEncryptionPublicKeyParameters nTRUEncryptionPublicKeyParameters = (NTRUEncryptionPublicKeyParameters) cipherParameters;
            this.pubKey = nTRUEncryptionPublicKeyParameters;
            this.privKey = null;
            this.params = nTRUEncryptionPublicKeyParameters.getParameters();
            this.random = CryptoServicesRegistrar.getSecureRandom(secureRandom);
            return;
        }
        this.pubKey = null;
        NTRUEncryptionPrivateKeyParameters nTRUEncryptionPrivateKeyParameters = (NTRUEncryptionPrivateKeyParameters) cipherParameters;
        this.privKey = nTRUEncryptionPrivateKeyParameters;
        this.params = nTRUEncryptionPrivateKeyParameters.getParameters();
        this.random = null;
    }

    @Override // org.bouncycastle.crypto.AsymmetricBlockCipher
    public byte[] processBlock(byte[] bArr, int i, int i2) throws InvalidCipherTextException {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        return this.forEncryption ? encrypt(bArr2, this.pubKey) : decrypt(bArr2, this.privKey);
    }
}
