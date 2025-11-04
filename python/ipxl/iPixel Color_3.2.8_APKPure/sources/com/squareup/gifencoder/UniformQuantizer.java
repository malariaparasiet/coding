package com.squareup.gifencoder;

import java.util.HashSet;
import java.util.Set;

/* loaded from: classes2.dex */
public final class UniformQuantizer implements ColorQuantizer {
    public static final UniformQuantizer INSTANCE = new UniformQuantizer();

    private UniformQuantizer() {
    }

    @Override // com.squareup.gifencoder.ColorQuantizer
    public Set<Color> quantize(Multiset<Color> multiset, int i) {
        int pow = (int) Math.pow(i, 0.3333333333333333d);
        int i2 = pow + 1;
        int i3 = (pow * i2) * pow <= i ? i2 : pow;
        if (i2 * i3 * pow > i) {
            i2 = pow;
        }
        HashSet hashSet = new HashSet();
        for (int i4 = 0; i4 < i2; i4++) {
            for (int i5 = 0; i5 < i3; i5++) {
                for (int i6 = 0; i6 < pow; i6++) {
                    hashSet.add(new Color(i4 / (i2 - 1.0d), i5 / (i3 - 1.0d), i6 / (pow - 1.0d)));
                }
            }
        }
        return hashSet;
    }
}
