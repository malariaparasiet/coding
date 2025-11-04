package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.digests.ISAPDigest;
import org.bouncycastle.util.Longs;

/* loaded from: classes3.dex */
public class AsconPermutationFriend {

    public static class AsconPermutation {
        public long x0;
        public long x1;
        public long x2;
        public long x3;
        public long x4;

        AsconPermutation() {
        }

        public void p(int i) {
            if (i == 12) {
                round(240L);
                round(225L);
                round(210L);
                round(195L);
            }
            if (i >= 8) {
                round(180L);
                round(165L);
            }
            round(150L);
            round(135L);
            round(120L);
            round(105L);
            round(90L);
            round(75L);
        }

        public void round(long j) {
            long j2 = this.x2 ^ j;
            this.x2 = j2;
            long j3 = this.x0;
            long j4 = this.x4;
            long j5 = j3 ^ j4;
            long j6 = this.x1;
            long j7 = j6 ^ j2;
            long j8 = j6 | j2;
            long j9 = this.x3;
            long j10 = ((j9 ^ j8) ^ j3) ^ (j6 & j5);
            long j11 = (j5 ^ (j8 | j9)) ^ ((j6 & j2) & j9);
            long j12 = ((~j9) & j4) ^ j7;
            long j13 = j7 ^ (j3 | (j9 ^ j4));
            long j14 = (j9 ^ (j4 | j6)) ^ (j3 & j6);
            this.x0 = Longs.rotateRight(j10, 28) ^ (Longs.rotateRight(j10, 19) ^ j10);
            this.x1 = (Longs.rotateRight(j11, 39) ^ j11) ^ Longs.rotateRight(j11, 61);
            this.x2 = ~((Longs.rotateRight(j12, 1) ^ j12) ^ Longs.rotateRight(j12, 6));
            this.x3 = (Longs.rotateRight(j13, 10) ^ j13) ^ Longs.rotateRight(j13, 17);
            this.x4 = Longs.rotateRight(j14, 41) ^ (Longs.rotateRight(j14, 7) ^ j14);
        }

        public void set(long j, long j2, long j3, long j4, long j5) {
            this.x0 = j;
            this.x1 = j2;
            this.x2 = j3;
            this.x3 = j4;
            this.x4 = j5;
        }
    }

    public static AsconPermutation getAsconPermutation(ISAPDigest.Friend friend) {
        if (friend != null) {
            return new AsconPermutation();
        }
        throw new NullPointerException("This method is only for use by ISAPDigest or Ascon Digest");
    }
}
