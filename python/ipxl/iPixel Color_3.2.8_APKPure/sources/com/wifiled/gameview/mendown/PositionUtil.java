package com.wifiled.gameview.mendown;

/* loaded from: classes3.dex */
public class PositionUtil {
    public static int getRangeX(int i) {
        double random = Math.random();
        while (random >= 0.75d) {
            random = Math.random();
        }
        return (int) (i * random);
    }
}
