package com.squareup.gifencoder;

import java.util.Set;

/* loaded from: classes2.dex */
public interface ColorQuantizer {
    Set<Color> quantize(Multiset<Color> multiset, int i);
}
