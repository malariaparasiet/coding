package com.squareup.gifencoder;

import java.lang.reflect.Array;
import java.util.Set;

/* loaded from: classes2.dex */
public final class NearestColorDitherer implements Ditherer {
    public static final NearestColorDitherer INSTANCE = new NearestColorDitherer();

    private NearestColorDitherer() {
    }

    @Override // com.squareup.gifencoder.Ditherer
    public Image dither(Image image, Set<Color> set) {
        int width = image.getWidth();
        int height = image.getHeight();
        Color[][] colorArr = (Color[][]) Array.newInstance((Class<?>) Color.class, height, width);
        for (int i = 0; i < height; i++) {
            for (int i2 = 0; i2 < width; i2++) {
                colorArr[i][i2] = image.getColor(i2, i).getNearestColor(set);
            }
        }
        return Image.fromColors(colorArr);
    }
}
