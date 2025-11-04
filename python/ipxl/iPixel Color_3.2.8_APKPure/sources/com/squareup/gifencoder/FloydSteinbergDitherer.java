package com.squareup.gifencoder;

import java.lang.reflect.Array;
import java.util.Set;

/* loaded from: classes2.dex */
public final class FloydSteinbergDitherer implements Ditherer {
    public static final FloydSteinbergDitherer INSTANCE = new FloydSteinbergDitherer();
    private static final ErrorComponent[] ERROR_DISTRIBUTION = {new ErrorComponent(1, 0, 0.4375d), new ErrorComponent(-1, 1, 0.1875d), new ErrorComponent(0, 1, 0.3125d), new ErrorComponent(1, 1, 0.0625d)};

    private FloydSteinbergDitherer() {
    }

    @Override // com.squareup.gifencoder.Ditherer
    public Image dither(Image image, Set<Color> set) {
        int width = image.getWidth();
        int height = image.getHeight();
        Color[][] colorArr = (Color[][]) Array.newInstance((Class<?>) Color.class, height, width);
        for (int i = 0; i < height; i++) {
            for (int i2 = 0; i2 < width; i2++) {
                colorArr[i][i2] = image.getColor(i2, i);
            }
        }
        for (int i3 = 0; i3 < height; i3++) {
            for (int i4 = 0; i4 < width; i4++) {
                Color color = colorArr[i3][i4];
                Color nearestColor = color.getNearestColor(set);
                colorArr[i3][i4] = nearestColor;
                Color minus = color.minus(nearestColor);
                for (ErrorComponent errorComponent : ERROR_DISTRIBUTION) {
                    int i5 = errorComponent.deltaX + i4;
                    int i6 = errorComponent.deltaY + i3;
                    if (i5 >= 0 && i6 >= 0 && i5 < width && i6 < height) {
                        Color scaled = minus.scaled(errorComponent.errorFraction);
                        Color[] colorArr2 = colorArr[i6];
                        colorArr2[i5] = colorArr2[i5].plus(scaled);
                    }
                }
            }
        }
        return Image.fromColors(colorArr);
    }

    private static final class ErrorComponent {
        final int deltaX;
        final int deltaY;
        final double errorFraction;

        ErrorComponent(int i, int i2, double d) {
            this.deltaX = i;
            this.deltaY = i2;
            this.errorFraction = d;
        }
    }
}
