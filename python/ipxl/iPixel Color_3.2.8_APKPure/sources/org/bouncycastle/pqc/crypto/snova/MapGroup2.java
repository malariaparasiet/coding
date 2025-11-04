package org.bouncycastle.pqc.crypto.snova;

import java.lang.reflect.Array;

/* loaded from: classes4.dex */
class MapGroup2 {
    public final byte[][][][] f11;
    public final byte[][][][] f12;
    public final byte[][][][] f21;

    public MapGroup2(SnovaParameters snovaParameters) {
        int m = snovaParameters.getM();
        int v = snovaParameters.getV();
        int o = snovaParameters.getO();
        int lsq = snovaParameters.getLsq();
        this.f11 = (byte[][][][]) Array.newInstance((Class<?>) Byte.TYPE, m, v, v, lsq);
        this.f12 = (byte[][][][]) Array.newInstance((Class<?>) Byte.TYPE, m, v, o, lsq);
        this.f21 = (byte[][][][]) Array.newInstance((Class<?>) Byte.TYPE, m, o, v, lsq);
    }
}
