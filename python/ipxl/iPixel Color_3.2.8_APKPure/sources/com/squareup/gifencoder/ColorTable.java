package com.squareup.gifencoder;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* loaded from: classes2.dex */
final class ColorTable {
    private final Map<Color, Integer> colorToIndex;
    private final Map<Integer, Color> indexToColor;

    private ColorTable(Map<Integer, Color> map, Map<Color, Integer> map2) {
        this.indexToColor = map;
        this.colorToIndex = map2;
    }

    static ColorTable fromColors(Set<Color> set) {
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        int i = 0;
        for (Color color : set) {
            if (!hashMap2.containsKey(color)) {
                hashMap.put(Integer.valueOf(i), color);
                hashMap2.put(color, Integer.valueOf(i));
                i++;
            }
        }
        return new ColorTable(hashMap, hashMap2);
    }

    int paddedSize() {
        return Math.max(GifMath.roundUpToPowerOfTwo(unpaddedSize()), 2);
    }

    private int unpaddedSize() {
        return this.colorToIndex.size();
    }

    void write(OutputStream outputStream) throws IOException {
        for (int i = 0; i < unpaddedSize(); i++) {
            Streams.writeRgb(outputStream, this.indexToColor.get(Integer.valueOf(i)).getRgbInt());
        }
        for (int unpaddedSize = unpaddedSize(); unpaddedSize < paddedSize(); unpaddedSize++) {
            Streams.writeRgb(outputStream, 0);
        }
    }

    int[] getIndices(Image image) {
        int numPixels = image.getNumPixels();
        int[] iArr = new int[numPixels];
        for (int i = 0; i < numPixels; i++) {
            iArr[i] = this.colorToIndex.get(image.getColor(i)).intValue();
        }
        return iArr;
    }
}
