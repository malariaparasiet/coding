package com.squareup.gifencoder;

import java.util.Set;

/* loaded from: classes2.dex */
public interface Ditherer {
    Image dither(Image image, Set<Color> set);
}
