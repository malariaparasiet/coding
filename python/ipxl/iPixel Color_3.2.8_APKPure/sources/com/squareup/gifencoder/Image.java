package com.squareup.gifencoder;

import java.lang.reflect.Array;

/* loaded from: classes2.dex */
public final class Image {
    private final Color[][] colors;

    private Image(Color[][] colorArr) {
        this.colors = colorArr;
    }

    public static Image fromColors(Color[][] colorArr) {
        return new Image(colorArr);
    }

    public static Image fromRgb(int[][] iArr) {
        int length = iArr.length;
        int length2 = iArr[0].length;
        Color[][] colorArr = (Color[][]) Array.newInstance((Class<?>) Color.class, length, length2);
        for (int i = 0; i < length; i++) {
            if (iArr[i].length != length2) {
                throw new IllegalArgumentException("rows lengths do not match in RGB array");
            }
            for (int i2 = 0; i2 < length2; i2++) {
                colorArr[i][i2] = Color.fromRgbInt(iArr[i][i2]);
            }
        }
        return new Image(colorArr);
    }

    public static Image fromRgb(int[] iArr, int i) {
        if (iArr.length % i != 0) {
            throw new IllegalArgumentException("the given width does not divide the number of pixels");
        }
        int length = iArr.length / i;
        Color[][] colorArr = (Color[][]) Array.newInstance((Class<?>) Color.class, length, i);
        for (int i2 = 0; i2 < length; i2++) {
            for (int i3 = 0; i3 < i; i3++) {
                colorArr[i2][i3] = Color.fromRgbInt(iArr[(i2 * i) + i3]);
            }
        }
        return new Image(colorArr);
    }

    public Color getColor(int i, int i2) {
        return this.colors[i2][i];
    }

    public Color getColor(int i) {
        return this.colors[i / getWidth()][i % getWidth()];
    }

    Multiset<Color> getColors() {
        HashMultiset hashMultiset = new HashMultiset();
        for (int i = 0; i < getNumPixels(); i++) {
            hashMultiset.add(getColor(i));
        }
        return hashMultiset;
    }

    public int getWidth() {
        return this.colors[0].length;
    }

    public int getHeight() {
        return this.colors.length;
    }

    public int getNumPixels() {
        return getWidth() * getHeight();
    }
}
