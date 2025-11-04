package org.bouncycastle.crypto.engines;

import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.digests.SparkleDigest;
import org.bouncycastle.crypto.engines.AEADBaseEngine;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Integers;
import org.bouncycastle.util.Pack;

/* loaded from: classes3.dex */
public class SparkleEngine extends AEADBaseEngine {
    private static final int[] RCON = {-1209970334, -1083090816, 951376470, 844003128, -1156479509, 1333558103, -809524792, -1028445891};
    private final int CAP_MASK;
    private final int KEY_WORDS;
    private final int RATE_WORDS;
    private final int SPARKLE_STEPS_BIG;
    private final int SPARKLE_STEPS_SLIM;
    private final int STATE_WORDS;
    private final int TAG_WORDS;
    private final int _A0;
    private final int _A1;
    private final int _M2;
    private final int _M3;
    private boolean encrypted;
    private final int[] k;
    private final int[] npub;
    private final int[] state;

    public enum SparkleParameters {
        SCHWAEMM128_128,
        SCHWAEMM256_128,
        SCHWAEMM192_192,
        SCHWAEMM256_256
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x007c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public SparkleEngine(org.bouncycastle.crypto.engines.SparkleEngine.SparkleParameters r8) {
        /*
            r7 = this;
            r7.<init>()
            int r8 = r8.ordinal()
            r0 = 7
            r1 = 1
            r2 = 3
            r3 = 256(0x100, float:3.59E-43)
            r4 = 128(0x80, float:1.8E-43)
            if (r8 == 0) goto L4c
            r5 = 11
            r6 = 384(0x180, float:5.38E-43)
            if (r8 == r1) goto L41
            r4 = 2
            if (r8 == r4) goto L33
            if (r8 != r2) goto L2b
            r8 = 8
            r7.SPARKLE_STEPS_SLIM = r8
            r8 = 12
            r7.SPARKLE_STEPS_BIG = r8
            java.lang.String r8 = "SCHWAEMM256-256"
            r7.algorithmName = r8
            r8 = 512(0x200, float:7.17E-43)
            r6 = r8
            goto L3d
        L2b:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "Invalid definition of SCHWAEMM instance"
            r8.<init>(r0)
            throw r8
        L33:
            r7.SPARKLE_STEPS_SLIM = r0
            r7.SPARKLE_STEPS_BIG = r5
            java.lang.String r8 = "SCHWAEMM192-192"
            r7.algorithmName = r8
            r3 = 192(0xc0, float:2.69E-43)
        L3d:
            r8 = r3
            r0 = r8
            r4 = r0
            goto L5a
        L41:
            r7.SPARKLE_STEPS_SLIM = r0
            r7.SPARKLE_STEPS_BIG = r5
            java.lang.String r8 = "SCHWAEMM256-128"
            r7.algorithmName = r8
            r8 = r3
            r0 = r4
            goto L59
        L4c:
            r7.SPARKLE_STEPS_SLIM = r0
            r8 = 10
            r7.SPARKLE_STEPS_BIG = r8
            java.lang.String r8 = "SCHWAEMM128-128"
            r7.algorithmName = r8
            r6 = r3
            r8 = r4
            r0 = r8
        L59:
            r3 = r0
        L5a:
            int r5 = r3 >>> 5
            r7.KEY_WORDS = r5
            int r3 = r3 >>> r2
            r7.KEY_SIZE = r3
            int r3 = r4 >>> 5
            r7.TAG_WORDS = r3
            int r3 = r4 >>> 3
            r7.MAC_SIZE = r3
            int r3 = r6 >>> 5
            r7.STATE_WORDS = r3
            int r4 = r8 >>> 5
            r7.RATE_WORDS = r4
            int r8 = r8 >>> r2
            r7.IV_SIZE = r8
            int r8 = r0 >>> 6
            int r0 = r0 >>> 5
            if (r4 <= r0) goto L7c
            int r0 = r0 - r1
            goto L7d
        L7c:
            r0 = -1
        L7d:
            r7.CAP_MASK = r0
            int r8 = r1 << r8
            int r0 = r8 << 24
            r7._A0 = r0
            r0 = r8 ^ 1
            int r0 = r0 << 24
            r7._A1 = r0
            r0 = r8 ^ 2
            int r0 = r0 << 24
            r7._M2 = r0
            r8 = r8 ^ r2
            int r8 = r8 << 24
            r7._M3 = r8
            int[] r8 = new int[r3]
            r7.state = r8
            int[] r8 = new int[r5]
            r7.k = r8
            int[] r8 = new int[r4]
            r7.npub = r8
            int r8 = r7.IV_SIZE
            r7.BlockSize = r8
            r7.AADBufferSize = r8
            org.bouncycastle.crypto.engines.AEADBaseEngine$ProcessingBufferType r8 = org.bouncycastle.crypto.engines.AEADBaseEngine.ProcessingBufferType.Buffered
            org.bouncycastle.crypto.engines.AEADBaseEngine$AADOperatorType r0 = org.bouncycastle.crypto.engines.AEADBaseEngine.AADOperatorType.Default
            org.bouncycastle.crypto.engines.AEADBaseEngine$DataOperatorType r1 = org.bouncycastle.crypto.engines.AEADBaseEngine.DataOperatorType.Default
            r7.setInnerMembers(r8, r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.crypto.engines.SparkleEngine.<init>(org.bouncycastle.crypto.engines.SparkleEngine$SparkleParameters):void");
    }

    private static int ELL(int i) {
        return (i & 65535) ^ Integers.rotateRight(i, 16);
    }

    private static void sparkle_opt(int[] iArr, int i) {
        int length = iArr.length;
        if (length == 8) {
            sparkle_opt8(iArr, i);
        } else if (length == 12) {
            sparkle_opt12(iArr, i);
        } else {
            if (length != 16) {
                throw new IllegalStateException();
            }
            sparkle_opt16(iArr, i);
        }
    }

    public static void sparkle_opt12(SparkleDigest.Friend friend, int[] iArr, int i) {
        if (friend == null) {
            throw new NullPointerException("This method is only for use by SparkleDigest");
        }
        sparkle_opt12(iArr, i);
    }

    static void sparkle_opt12(int[] iArr, int i) {
        int i2 = iArr[0];
        int i3 = iArr[1];
        int i4 = iArr[2];
        int i5 = iArr[3];
        char c = 4;
        int i6 = iArr[4];
        char c2 = 5;
        int i7 = iArr[5];
        char c3 = 6;
        int i8 = iArr[6];
        char c4 = 7;
        int i9 = iArr[7];
        int i10 = iArr[8];
        int i11 = iArr[9];
        int i12 = iArr[10];
        int i13 = iArr[11];
        int i14 = 0;
        int i15 = i11;
        while (i14 < i) {
            int[] iArr2 = RCON;
            int i16 = i3 ^ iArr2[i14 & 7];
            int i17 = i5 ^ i14;
            int i18 = iArr2[0];
            char c5 = c;
            int rotateRight = i2 + Integers.rotateRight(i16, 31);
            char c6 = c2;
            int rotateRight2 = i16 ^ Integers.rotateRight(rotateRight, 24);
            char c7 = c3;
            int rotateRight3 = (rotateRight ^ i18) + Integers.rotateRight(rotateRight2, 17);
            int rotateRight4 = rotateRight2 ^ Integers.rotateRight(rotateRight3, 17);
            int i19 = (rotateRight3 ^ i18) + rotateRight4;
            int rotateRight5 = rotateRight4 ^ Integers.rotateRight(i19, 31);
            int rotateRight6 = (i19 ^ i18) + Integers.rotateRight(rotateRight5, 24);
            char c8 = c4;
            int rotateRight7 = rotateRight5 ^ Integers.rotateRight(rotateRight6, 16);
            int i20 = rotateRight6 ^ i18;
            int i21 = iArr2[1];
            int rotateRight8 = i4 + Integers.rotateRight(i17, 31);
            int rotateRight9 = i17 ^ Integers.rotateRight(rotateRight8, 24);
            int rotateRight10 = (rotateRight8 ^ i21) + Integers.rotateRight(rotateRight9, 17);
            int rotateRight11 = rotateRight9 ^ Integers.rotateRight(rotateRight10, 17);
            int i22 = (rotateRight10 ^ i21) + rotateRight11;
            int rotateRight12 = rotateRight11 ^ Integers.rotateRight(i22, 31);
            int rotateRight13 = (i22 ^ i21) + Integers.rotateRight(rotateRight12, 24);
            int rotateRight14 = rotateRight12 ^ Integers.rotateRight(rotateRight13, 16);
            int i23 = rotateRight13 ^ i21;
            int i24 = iArr2[2];
            int rotateRight15 = i6 + Integers.rotateRight(i7, 31);
            int rotateRight16 = i7 ^ Integers.rotateRight(rotateRight15, 24);
            int rotateRight17 = (rotateRight15 ^ i24) + Integers.rotateRight(rotateRight16, 17);
            int rotateRight18 = rotateRight16 ^ Integers.rotateRight(rotateRight17, 17);
            int i25 = (rotateRight17 ^ i24) + rotateRight18;
            int rotateRight19 = rotateRight18 ^ Integers.rotateRight(i25, 31);
            int rotateRight20 = (i25 ^ i24) + Integers.rotateRight(rotateRight19, 24);
            int rotateRight21 = rotateRight19 ^ Integers.rotateRight(rotateRight20, 16);
            int i26 = rotateRight20 ^ i24;
            int i27 = iArr2[3];
            int rotateRight22 = i8 + Integers.rotateRight(i9, 31);
            int rotateRight23 = i9 ^ Integers.rotateRight(rotateRight22, 24);
            int rotateRight24 = (rotateRight22 ^ i27) + Integers.rotateRight(rotateRight23, 17);
            int rotateRight25 = rotateRight23 ^ Integers.rotateRight(rotateRight24, 17);
            int i28 = (rotateRight24 ^ i27) + rotateRight25;
            int rotateRight26 = rotateRight25 ^ Integers.rotateRight(i28, 31);
            int rotateRight27 = (i28 ^ i27) + Integers.rotateRight(rotateRight26, 24);
            int rotateRight28 = rotateRight26 ^ Integers.rotateRight(rotateRight27, 16);
            int i29 = rotateRight27 ^ i27;
            int i30 = iArr2[c5];
            int rotateRight29 = i10 + Integers.rotateRight(i15, 31);
            int rotateRight30 = i15 ^ Integers.rotateRight(rotateRight29, 24);
            int rotateRight31 = (rotateRight29 ^ i30) + Integers.rotateRight(rotateRight30, 17);
            int rotateRight32 = rotateRight30 ^ Integers.rotateRight(rotateRight31, 17);
            int i31 = (rotateRight31 ^ i30) + rotateRight32;
            int rotateRight33 = rotateRight32 ^ Integers.rotateRight(i31, 31);
            int rotateRight34 = (i31 ^ i30) + Integers.rotateRight(rotateRight33, 24);
            int rotateRight35 = rotateRight33 ^ Integers.rotateRight(rotateRight34, 16);
            int i32 = rotateRight34 ^ i30;
            int i33 = iArr2[c6];
            int rotateRight36 = i12 + Integers.rotateRight(i13, 31);
            int rotateRight37 = i13 ^ Integers.rotateRight(rotateRight36, 24);
            int rotateRight38 = (rotateRight36 ^ i33) + Integers.rotateRight(rotateRight37, 17);
            int rotateRight39 = rotateRight37 ^ Integers.rotateRight(rotateRight38, 17);
            int i34 = (rotateRight38 ^ i33) + rotateRight39;
            int rotateRight40 = rotateRight39 ^ Integers.rotateRight(i34, 31);
            int rotateRight41 = (i34 ^ i33) + Integers.rotateRight(rotateRight40, 24);
            int rotateRight42 = rotateRight40 ^ Integers.rotateRight(rotateRight41, 16);
            int i35 = rotateRight41 ^ i33;
            int ELL = ELL((i20 ^ i23) ^ i26);
            int ELL2 = ELL((rotateRight7 ^ rotateRight14) ^ rotateRight21);
            int i36 = (i32 ^ i23) ^ ELL2;
            int i37 = (rotateRight35 ^ rotateRight14) ^ ELL;
            int i38 = (rotateRight42 ^ rotateRight21) ^ ELL;
            int i39 = ELL ^ (rotateRight28 ^ rotateRight7);
            i14++;
            i9 = rotateRight7;
            i10 = i23;
            i4 = (i35 ^ i26) ^ ELL2;
            i12 = i26;
            i6 = (i29 ^ i20) ^ ELL2;
            c = c5;
            c4 = c8;
            i3 = i37;
            i8 = i20;
            i15 = rotateRight14;
            i2 = i36;
            c3 = c7;
            i5 = i38;
            i13 = rotateRight21;
            i7 = i39;
            c2 = c6;
        }
        iArr[0] = i2;
        iArr[1] = i3;
        iArr[2] = i4;
        iArr[3] = i5;
        iArr[c] = i6;
        iArr[c2] = i7;
        iArr[c3] = i8;
        iArr[c4] = i9;
        iArr[8] = i10;
        iArr[9] = i15;
        iArr[10] = i12;
        iArr[11] = i13;
    }

    public static void sparkle_opt16(SparkleDigest.Friend friend, int[] iArr, int i) {
        if (friend == null) {
            throw new NullPointerException("This method is only for use by SparkleDigest");
        }
        sparkle_opt16(iArr, i);
    }

    static void sparkle_opt16(int[] iArr, int i) {
        int i2 = iArr[0];
        int i3 = iArr[1];
        int i4 = iArr[2];
        int i5 = iArr[3];
        int i6 = iArr[4];
        int i7 = iArr[5];
        char c = 6;
        int i8 = iArr[6];
        char c2 = 7;
        int i9 = iArr[7];
        int i10 = iArr[8];
        int i11 = iArr[9];
        int i12 = iArr[10];
        int i13 = iArr[11];
        int i14 = iArr[12];
        int i15 = iArr[13];
        int i16 = iArr[14];
        int i17 = i13;
        int i18 = i15;
        int i19 = iArr[15];
        int i20 = 0;
        int i21 = i11;
        while (i20 < i) {
            int[] iArr2 = RCON;
            int i22 = i3 ^ iArr2[i20 & 7];
            int i23 = i5 ^ i20;
            int i24 = iArr2[0];
            char c3 = c;
            int rotateRight = i2 + Integers.rotateRight(i22, 31);
            char c4 = c2;
            int rotateRight2 = i22 ^ Integers.rotateRight(rotateRight, 24);
            int rotateRight3 = (rotateRight ^ i24) + Integers.rotateRight(rotateRight2, 17);
            int rotateRight4 = rotateRight2 ^ Integers.rotateRight(rotateRight3, 17);
            int i25 = (rotateRight3 ^ i24) + rotateRight4;
            int rotateRight5 = rotateRight4 ^ Integers.rotateRight(i25, 31);
            int rotateRight6 = (i25 ^ i24) + Integers.rotateRight(rotateRight5, 24);
            int rotateRight7 = rotateRight5 ^ Integers.rotateRight(rotateRight6, 16);
            int i26 = rotateRight6 ^ i24;
            int i27 = iArr2[1];
            int rotateRight8 = i4 + Integers.rotateRight(i23, 31);
            int rotateRight9 = i23 ^ Integers.rotateRight(rotateRight8, 24);
            int rotateRight10 = (rotateRight8 ^ i27) + Integers.rotateRight(rotateRight9, 17);
            int rotateRight11 = rotateRight9 ^ Integers.rotateRight(rotateRight10, 17);
            int i28 = (rotateRight10 ^ i27) + rotateRight11;
            int rotateRight12 = rotateRight11 ^ Integers.rotateRight(i28, 31);
            int rotateRight13 = (i28 ^ i27) + Integers.rotateRight(rotateRight12, 24);
            int rotateRight14 = rotateRight12 ^ Integers.rotateRight(rotateRight13, 16);
            int i29 = rotateRight13 ^ i27;
            int i30 = iArr2[2];
            int rotateRight15 = i6 + Integers.rotateRight(i7, 31);
            int rotateRight16 = i7 ^ Integers.rotateRight(rotateRight15, 24);
            int rotateRight17 = (rotateRight15 ^ i30) + Integers.rotateRight(rotateRight16, 17);
            int rotateRight18 = rotateRight16 ^ Integers.rotateRight(rotateRight17, 17);
            int i31 = (rotateRight17 ^ i30) + rotateRight18;
            int rotateRight19 = rotateRight18 ^ Integers.rotateRight(i31, 31);
            int rotateRight20 = (i31 ^ i30) + Integers.rotateRight(rotateRight19, 24);
            int rotateRight21 = rotateRight19 ^ Integers.rotateRight(rotateRight20, 16);
            int i32 = rotateRight20 ^ i30;
            int i33 = iArr2[3];
            int rotateRight22 = i8 + Integers.rotateRight(i9, 31);
            int rotateRight23 = i9 ^ Integers.rotateRight(rotateRight22, 24);
            int rotateRight24 = (rotateRight22 ^ i33) + Integers.rotateRight(rotateRight23, 17);
            int rotateRight25 = rotateRight23 ^ Integers.rotateRight(rotateRight24, 17);
            int i34 = (rotateRight24 ^ i33) + rotateRight25;
            int rotateRight26 = rotateRight25 ^ Integers.rotateRight(i34, 31);
            int rotateRight27 = (i34 ^ i33) + Integers.rotateRight(rotateRight26, 24);
            int rotateRight28 = rotateRight26 ^ Integers.rotateRight(rotateRight27, 16);
            int i35 = i33 ^ rotateRight27;
            int i36 = iArr2[4];
            int rotateRight29 = i10 + Integers.rotateRight(i21, 31);
            int rotateRight30 = i21 ^ Integers.rotateRight(rotateRight29, 24);
            int rotateRight31 = (rotateRight29 ^ i36) + Integers.rotateRight(rotateRight30, 17);
            int rotateRight32 = rotateRight30 ^ Integers.rotateRight(rotateRight31, 17);
            int i37 = (rotateRight31 ^ i36) + rotateRight32;
            int rotateRight33 = rotateRight32 ^ Integers.rotateRight(i37, 31);
            int rotateRight34 = (i37 ^ i36) + Integers.rotateRight(rotateRight33, 24);
            int rotateRight35 = rotateRight33 ^ Integers.rotateRight(rotateRight34, 16);
            int i38 = rotateRight34 ^ i36;
            int i39 = iArr2[5];
            int rotateRight36 = i12 + Integers.rotateRight(i17, 31);
            int rotateRight37 = i17 ^ Integers.rotateRight(rotateRight36, 24);
            int rotateRight38 = (rotateRight36 ^ i39) + Integers.rotateRight(rotateRight37, 17);
            int rotateRight39 = rotateRight37 ^ Integers.rotateRight(rotateRight38, 17);
            int i40 = (rotateRight38 ^ i39) + rotateRight39;
            int rotateRight40 = rotateRight39 ^ Integers.rotateRight(i40, 31);
            int rotateRight41 = (i40 ^ i39) + Integers.rotateRight(rotateRight40, 24);
            int rotateRight42 = rotateRight40 ^ Integers.rotateRight(rotateRight41, 16);
            int i41 = rotateRight41 ^ i39;
            int i42 = iArr2[c3];
            int rotateRight43 = i14 + Integers.rotateRight(i18, 31);
            int rotateRight44 = i18 ^ Integers.rotateRight(rotateRight43, 24);
            int rotateRight45 = (rotateRight43 ^ i42) + Integers.rotateRight(rotateRight44, 17);
            int rotateRight46 = rotateRight44 ^ Integers.rotateRight(rotateRight45, 17);
            int i43 = (rotateRight45 ^ i42) + rotateRight46;
            int rotateRight47 = rotateRight46 ^ Integers.rotateRight(i43, 31);
            int rotateRight48 = (i43 ^ i42) + Integers.rotateRight(rotateRight47, 24);
            int rotateRight49 = rotateRight47 ^ Integers.rotateRight(rotateRight48, 16);
            int i44 = rotateRight48 ^ i42;
            int i45 = iArr2[c4];
            int rotateRight50 = i16 + Integers.rotateRight(i19, 31);
            int rotateRight51 = i19 ^ Integers.rotateRight(rotateRight50, 24);
            int rotateRight52 = (rotateRight50 ^ i45) + Integers.rotateRight(rotateRight51, 17);
            int rotateRight53 = rotateRight51 ^ Integers.rotateRight(rotateRight52, 17);
            int i46 = (rotateRight52 ^ i45) + rotateRight53;
            int rotateRight54 = rotateRight53 ^ Integers.rotateRight(i46, 31);
            int rotateRight55 = (i46 ^ i45) + Integers.rotateRight(rotateRight54, 24);
            int rotateRight56 = rotateRight54 ^ Integers.rotateRight(rotateRight55, 16);
            int i47 = rotateRight55 ^ i45;
            int ELL = ELL(((i26 ^ i29) ^ i32) ^ i35);
            int ELL2 = ELL(((rotateRight7 ^ rotateRight14) ^ rotateRight21) ^ rotateRight28);
            int i48 = rotateRight14 ^ rotateRight42;
            int i49 = (i41 ^ i29) ^ ELL2;
            int i50 = (i44 ^ i32) ^ ELL2;
            int i51 = (rotateRight21 ^ rotateRight49) ^ ELL;
            int i52 = (i47 ^ i35) ^ ELL2;
            int i53 = (i26 ^ i38) ^ ELL2;
            i9 = (rotateRight7 ^ rotateRight35) ^ ELL;
            i20++;
            i7 = (rotateRight56 ^ rotateRight28) ^ ELL;
            i21 = rotateRight7;
            i3 = i48 ^ ELL;
            i12 = i29;
            i4 = i50;
            i18 = rotateRight21;
            i8 = i53;
            i2 = i49;
            i17 = rotateRight14;
            i16 = i35;
            i5 = i51;
            c = c3;
            i10 = i26;
            i14 = i32;
            i6 = i52;
            i19 = rotateRight28;
            c2 = c4;
        }
        iArr[0] = i2;
        iArr[1] = i3;
        iArr[2] = i4;
        iArr[3] = i5;
        iArr[4] = i6;
        iArr[5] = i7;
        iArr[c] = i8;
        iArr[c2] = i9;
        iArr[8] = i10;
        iArr[9] = i21;
        iArr[10] = i12;
        iArr[11] = i17;
        iArr[12] = i14;
        iArr[13] = i18;
        iArr[14] = i16;
        iArr[15] = i19;
    }

    static void sparkle_opt8(int[] iArr, int i) {
        int i2 = iArr[0];
        int i3 = iArr[1];
        char c = 2;
        int i4 = iArr[2];
        char c2 = 3;
        int i5 = iArr[3];
        char c3 = 4;
        int i6 = iArr[4];
        char c4 = 5;
        int i7 = iArr[5];
        int i8 = iArr[6];
        int i9 = iArr[7];
        int i10 = 0;
        while (i10 < i) {
            int[] iArr2 = RCON;
            int i11 = i3 ^ iArr2[i10 & 7];
            int i12 = i5 ^ i10;
            int i13 = iArr2[0];
            char c5 = c;
            int rotateRight = i2 + Integers.rotateRight(i11, 31);
            char c6 = c2;
            int rotateRight2 = i11 ^ Integers.rotateRight(rotateRight, 24);
            char c7 = c3;
            int rotateRight3 = (rotateRight ^ i13) + Integers.rotateRight(rotateRight2, 17);
            int rotateRight4 = rotateRight2 ^ Integers.rotateRight(rotateRight3, 17);
            int i14 = (rotateRight3 ^ i13) + rotateRight4;
            int rotateRight5 = rotateRight4 ^ Integers.rotateRight(i14, 31);
            int rotateRight6 = (i14 ^ i13) + Integers.rotateRight(rotateRight5, 24);
            char c8 = c4;
            int rotateRight7 = rotateRight5 ^ Integers.rotateRight(rotateRight6, 16);
            int i15 = rotateRight6 ^ i13;
            int i16 = iArr2[1];
            int rotateRight8 = i4 + Integers.rotateRight(i12, 31);
            int rotateRight9 = i12 ^ Integers.rotateRight(rotateRight8, 24);
            int rotateRight10 = (rotateRight8 ^ i16) + Integers.rotateRight(rotateRight9, 17);
            int rotateRight11 = rotateRight9 ^ Integers.rotateRight(rotateRight10, 17);
            int i17 = (rotateRight10 ^ i16) + rotateRight11;
            int rotateRight12 = rotateRight11 ^ Integers.rotateRight(i17, 31);
            int rotateRight13 = (i17 ^ i16) + Integers.rotateRight(rotateRight12, 24);
            int rotateRight14 = rotateRight12 ^ Integers.rotateRight(rotateRight13, 16);
            int i18 = rotateRight13 ^ i16;
            int i19 = iArr2[c5];
            int rotateRight15 = i6 + Integers.rotateRight(i7, 31);
            int rotateRight16 = i7 ^ Integers.rotateRight(rotateRight15, 24);
            int rotateRight17 = (rotateRight15 ^ i19) + Integers.rotateRight(rotateRight16, 17);
            int rotateRight18 = rotateRight16 ^ Integers.rotateRight(rotateRight17, 17);
            int i20 = (rotateRight17 ^ i19) + rotateRight18;
            int rotateRight19 = rotateRight18 ^ Integers.rotateRight(i20, 31);
            int rotateRight20 = (i20 ^ i19) + Integers.rotateRight(rotateRight19, 24);
            int rotateRight21 = rotateRight19 ^ Integers.rotateRight(rotateRight20, 16);
            int i21 = iArr2[c6];
            int rotateRight22 = i8 + Integers.rotateRight(i9, 31);
            int rotateRight23 = i9 ^ Integers.rotateRight(rotateRight22, 24);
            int rotateRight24 = (rotateRight22 ^ i21) + Integers.rotateRight(rotateRight23, 17);
            int rotateRight25 = Integers.rotateRight(rotateRight24, 17) ^ rotateRight23;
            int i22 = (rotateRight24 ^ i21) + rotateRight25;
            int rotateRight26 = Integers.rotateRight(i22, 31) ^ rotateRight25;
            int rotateRight27 = (i22 ^ i21) + Integers.rotateRight(rotateRight26, 24);
            int rotateRight28 = rotateRight26 ^ Integers.rotateRight(rotateRight27, 16);
            int i23 = rotateRight27 ^ i21;
            int ELL = ELL(i15 ^ i18);
            int ELL2 = ELL(rotateRight7 ^ rotateRight14);
            int i24 = (i23 ^ i18) ^ ELL2;
            int i25 = (rotateRight28 ^ rotateRight14) ^ ELL;
            int i26 = ELL ^ (rotateRight21 ^ rotateRight7);
            i10++;
            i7 = rotateRight7;
            i3 = i25;
            i8 = i18;
            i9 = rotateRight14;
            i5 = i26;
            i4 = ((rotateRight20 ^ i19) ^ i15) ^ ELL2;
            c = c5;
            c3 = c7;
            c4 = c8;
            i6 = i15;
            i2 = i24;
            c2 = c6;
        }
        iArr[0] = i2;
        iArr[1] = i3;
        iArr[c] = i4;
        iArr[c2] = i5;
        iArr[c3] = i6;
        iArr[c4] = i7;
        iArr[6] = i8;
        iArr[7] = i9;
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
    protected void init(byte[] bArr, byte[] bArr2) throws IllegalArgumentException {
        Pack.littleEndianToInt(bArr, 0, this.k);
        Pack.littleEndianToInt(bArr2, 0, this.npub);
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
        int i2 = 0;
        while (true) {
            int i3 = this.RATE_WORDS;
            if (i2 >= i3 / 2) {
                sparkle_opt(this.state, this.SPARKLE_STEPS_SLIM);
                return;
            }
            int i4 = (i3 / 2) + i2;
            int[] iArr = this.state;
            int i5 = iArr[i2];
            int i6 = iArr[i4];
            int littleEndianToInt = Pack.littleEndianToInt(bArr, (i2 * 4) + i);
            int littleEndianToInt2 = Pack.littleEndianToInt(bArr, (i4 * 4) + i);
            int[] iArr2 = this.state;
            int i7 = this.RATE_WORDS;
            iArr2[i2] = (littleEndianToInt ^ i6) ^ iArr2[i7 + i2];
            iArr2[i4] = ((i6 ^ i5) ^ littleEndianToInt2) ^ iArr2[i7 + (this.CAP_MASK & i4)];
            i2++;
        }
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine
    protected void processBufferDecrypt(byte[] bArr, int i, byte[] bArr2, int i2) {
        int i3 = 0;
        while (true) {
            int i4 = this.RATE_WORDS;
            if (i3 >= i4 / 2) {
                sparkle_opt(this.state, this.SPARKLE_STEPS_SLIM);
                this.encrypted = true;
                return;
            }
            int i5 = (i4 / 2) + i3;
            int[] iArr = this.state;
            int i6 = iArr[i3];
            int i7 = iArr[i5];
            int i8 = i3 * 4;
            int littleEndianToInt = Pack.littleEndianToInt(bArr, i + i8);
            int i9 = i5 * 4;
            int littleEndianToInt2 = Pack.littleEndianToInt(bArr, i + i9);
            int[] iArr2 = this.state;
            int i10 = this.RATE_WORDS;
            iArr2[i3] = ((i6 ^ i7) ^ littleEndianToInt) ^ iArr2[i10 + i3];
            iArr2[i5] = (i6 ^ littleEndianToInt2) ^ iArr2[i10 + (this.CAP_MASK & i5)];
            Pack.intToLittleEndian(littleEndianToInt ^ i6, bArr2, i2 + i8);
            Pack.intToLittleEndian(littleEndianToInt2 ^ i7, bArr2, i2 + i9);
            i3++;
        }
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine
    protected void processBufferEncrypt(byte[] bArr, int i, byte[] bArr2, int i2) {
        int i3 = 0;
        while (true) {
            int i4 = this.RATE_WORDS;
            if (i3 >= i4 / 2) {
                sparkle_opt(this.state, this.SPARKLE_STEPS_SLIM);
                this.encrypted = true;
                return;
            }
            int i5 = (i4 / 2) + i3;
            int[] iArr = this.state;
            int i6 = iArr[i3];
            int i7 = iArr[i5];
            int i8 = i3 * 4;
            int littleEndianToInt = Pack.littleEndianToInt(bArr, i + i8);
            int i9 = i5 * 4;
            int littleEndianToInt2 = Pack.littleEndianToInt(bArr, i + i9);
            int[] iArr2 = this.state;
            int i10 = this.RATE_WORDS;
            iArr2[i3] = (i7 ^ littleEndianToInt) ^ iArr2[i10 + i3];
            iArr2[i5] = ((i6 ^ i7) ^ littleEndianToInt2) ^ iArr2[i10 + (this.CAP_MASK & i5)];
            Pack.intToLittleEndian(littleEndianToInt ^ i6, bArr2, i2 + i8);
            Pack.intToLittleEndian(littleEndianToInt2 ^ i7, bArr2, i2 + i9);
            i3++;
        }
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
        int i = 0;
        if (this.m_aadPos < this.BlockSize) {
            int[] iArr = this.state;
            int i2 = this.STATE_WORDS - 1;
            iArr[i2] = iArr[i2] ^ this._A0;
            byte[] bArr = this.m_aad;
            int i3 = this.m_aadPos;
            this.m_aadPos = i3 + 1;
            bArr[i3] = ByteCompanionObject.MIN_VALUE;
            Arrays.fill(this.m_aad, this.m_aadPos, this.BlockSize, (byte) 0);
        } else {
            int[] iArr2 = this.state;
            int i4 = this.STATE_WORDS - 1;
            iArr2[i4] = iArr2[i4] ^ this._A1;
        }
        while (true) {
            int i5 = this.RATE_WORDS;
            if (i >= i5 / 2) {
                sparkle_opt(this.state, this.SPARKLE_STEPS_BIG);
                return;
            }
            int i6 = (i5 / 2) + i;
            int[] iArr3 = this.state;
            int i7 = iArr3[i];
            int i8 = iArr3[i6];
            int littleEndianToInt = Pack.littleEndianToInt(this.m_aad, i * 4);
            int littleEndianToInt2 = Pack.littleEndianToInt(this.m_aad, i6 * 4);
            int[] iArr4 = this.state;
            int i9 = this.RATE_WORDS;
            iArr4[i] = (littleEndianToInt ^ i8) ^ iArr4[i9 + i];
            iArr4[i6] = ((i8 ^ i7) ^ littleEndianToInt2) ^ iArr4[i9 + (this.CAP_MASK & i6)];
            i++;
        }
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine
    protected void processFinalBlock(byte[] bArr, int i) {
        if (this.encrypted || this.m_bufPos > 0) {
            int[] iArr = this.state;
            int i2 = this.STATE_WORDS - 1;
            iArr[i2] = iArr[i2] ^ (this.m_bufPos < this.IV_SIZE ? this._M2 : this._M3);
            int[] iArr2 = new int[this.RATE_WORDS];
            for (int i3 = 0; i3 < this.m_bufPos; i3++) {
                int i4 = i3 >>> 2;
                iArr2[i4] = iArr2[i4] | ((this.m_buf[i3] & UByte.MAX_VALUE) << ((i3 & 3) << 3));
            }
            if (this.m_bufPos < this.IV_SIZE) {
                if (!this.forEncryption) {
                    int i5 = (this.m_bufPos & 3) << 3;
                    int i6 = this.m_bufPos >>> 2;
                    iArr2[i6] = ((this.state[this.m_bufPos >>> 2] >>> i5) << i5) | iArr2[i6];
                    int i7 = (this.m_bufPos >>> 2) + 1;
                    System.arraycopy(this.state, i7, iArr2, i7, this.RATE_WORDS - i7);
                }
                int i8 = this.m_bufPos >>> 2;
                iArr2[i8] = iArr2[i8] ^ (128 << ((this.m_bufPos & 3) << 3));
            }
            int i9 = 0;
            while (true) {
                int i10 = this.RATE_WORDS;
                if (i9 >= i10 / 2) {
                    break;
                }
                int i11 = (i10 / 2) + i9;
                int[] iArr3 = this.state;
                int i12 = iArr3[i9];
                int i13 = iArr3[i11];
                if (this.forEncryption) {
                    int[] iArr4 = this.state;
                    int i14 = iArr2[i9] ^ i13;
                    int i15 = this.RATE_WORDS;
                    iArr4[i9] = i14 ^ iArr4[i15 + i9];
                    iArr4[i11] = ((i12 ^ i13) ^ iArr2[i11]) ^ iArr4[i15 + (this.CAP_MASK & i11)];
                } else {
                    int[] iArr5 = this.state;
                    int i16 = (i12 ^ i13) ^ iArr2[i9];
                    int i17 = this.RATE_WORDS;
                    iArr5[i9] = i16 ^ iArr5[i17 + i9];
                    iArr5[i11] = (iArr2[i11] ^ i12) ^ iArr5[i17 + (this.CAP_MASK & i11)];
                }
                iArr2[i9] = i12 ^ iArr2[i9];
                iArr2[i11] = i13 ^ iArr2[i11];
                i9++;
            }
            int i18 = 0;
            while (i18 < this.m_bufPos) {
                bArr[i] = (byte) (iArr2[i18 >>> 2] >>> ((i18 & 3) << 3));
                i18++;
                i++;
            }
            sparkle_opt(this.state, this.SPARKLE_STEPS_BIG);
        }
        for (int i19 = 0; i19 < this.KEY_WORDS; i19++) {
            int[] iArr6 = this.state;
            int i20 = this.RATE_WORDS + i19;
            iArr6[i20] = iArr6[i20] ^ this.k[i19];
        }
        Pack.intToLittleEndian(this.state, this.RATE_WORDS, this.TAG_WORDS, this.mac, 0);
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine, org.bouncycastle.crypto.modes.AEADCipher
    public /* bridge */ /* synthetic */ void reset() {
        super.reset();
    }

    @Override // org.bouncycastle.crypto.engines.AEADBaseEngine
    protected void reset(boolean z) {
        this.encrypted = false;
        System.arraycopy(this.npub, 0, this.state, 0, this.RATE_WORDS);
        System.arraycopy(this.k, 0, this.state, this.RATE_WORDS, this.KEY_WORDS);
        sparkle_opt(this.state, this.SPARKLE_STEPS_BIG);
        super.reset(z);
    }
}
