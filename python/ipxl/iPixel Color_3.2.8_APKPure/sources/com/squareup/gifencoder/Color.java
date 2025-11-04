package com.squareup.gifencoder;

import androidx.camera.video.AudioStats;
import java.util.Collection;
import java.util.Iterator;

/* loaded from: classes2.dex */
public final class Color {
    private final double blue;
    private final double green;
    private final double red;
    public static final Color BLACK = new Color(AudioStats.AUDIO_AMPLITUDE_NONE, AudioStats.AUDIO_AMPLITUDE_NONE, AudioStats.AUDIO_AMPLITUDE_NONE);
    public static final Color WHITE = new Color(1.0d, 1.0d, 1.0d);
    public static final Color RED = new Color(1.0d, AudioStats.AUDIO_AMPLITUDE_NONE, AudioStats.AUDIO_AMPLITUDE_NONE);
    public static final Color GREEN = new Color(AudioStats.AUDIO_AMPLITUDE_NONE, 1.0d, AudioStats.AUDIO_AMPLITUDE_NONE);
    public static final Color BLUE = new Color(AudioStats.AUDIO_AMPLITUDE_NONE, AudioStats.AUDIO_AMPLITUDE_NONE, 1.0d);

    public Color(double d, double d2, double d3) {
        this.red = d;
        this.green = d2;
        this.blue = d3;
    }

    public static Color fromRgbInt(int i) {
        return new Color(((i >>> 16) & 255) / 255.0d, ((i >>> 8) & 255) / 255.0d, (i & 255) / 255.0d);
    }

    public static Color getCentroid(Multiset<Color> multiset) {
        Color color = BLACK;
        Iterator<Color> it = multiset.getDistinctElements().iterator();
        while (it.hasNext()) {
            color = color.plus(it.next().scaled(multiset.count(r2)));
        }
        return color.scaled(1.0d / multiset.size());
    }

    public double getComponent(int i) {
        if (i == 0) {
            return this.red;
        }
        if (i == 1) {
            return this.green;
        }
        if (i == 2) {
            return this.blue;
        }
        throw new IllegalArgumentException("Unexpected component index: " + i);
    }

    public Color scaled(double d) {
        return new Color(this.red * d, this.green * d, this.blue * d);
    }

    public Color plus(Color color) {
        return new Color(this.red + color.red, this.green + color.green, this.blue + color.blue);
    }

    public Color minus(Color color) {
        return new Color(this.red - color.red, this.green - color.green, this.blue - color.blue);
    }

    public double getEuclideanDistanceTo(Color color) {
        Color minus = minus(color);
        double d = minus.red;
        double d2 = minus.green;
        double d3 = (d * d) + (d2 * d2);
        double d4 = minus.blue;
        return Math.sqrt(d3 + (d4 * d4));
    }

    public Color getNearestColor(Collection<Color> collection) {
        Color color = null;
        double d = Double.POSITIVE_INFINITY;
        for (Color color2 : collection) {
            double euclideanDistanceTo = getEuclideanDistanceTo(color2);
            if (euclideanDistanceTo < d) {
                color = color2;
                d = euclideanDistanceTo;
            }
        }
        return color;
    }

    public int getRgbInt() {
        return (((int) (this.red * 255.0d)) << 16) | (((int) (this.green * 255.0d)) << 8) | ((int) (this.blue * 255.0d));
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Color)) {
            return false;
        }
        Color color = (Color) obj;
        return this.red == color.red && this.green == color.green && this.blue == color.blue;
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.red);
        long doubleToLongBits2 = Double.doubleToLongBits(this.green);
        int i = (((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)));
        long doubleToLongBits3 = Double.doubleToLongBits(this.blue);
        return (i * 31) + ((int) ((doubleToLongBits3 >>> 32) ^ doubleToLongBits3));
    }

    public String toString() {
        return String.format("Color[%f, %f, %f]", Double.valueOf(this.red), Double.valueOf(this.green), Double.valueOf(this.blue));
    }
}
