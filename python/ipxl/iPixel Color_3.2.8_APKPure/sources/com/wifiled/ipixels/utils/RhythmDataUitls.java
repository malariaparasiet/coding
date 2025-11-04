package com.wifiled.ipixels.utils;

import android.util.Log;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class RhythmDataUitls {
    private static List<Integer> list0 = new ArrayList();
    private static List<Integer> list = new ArrayList();

    public static List<Integer> getRhyData1(byte[] data) {
        if (data == null) {
            return null;
        }
        list0.clear();
        list.clear();
        for (int i = 0; i < data.length; i++) {
            int height4 = ByteUtils.getHeight4(data[i]);
            int low4 = ByteUtils.getLow4(data[i]);
            if (i < 5) {
                list0.add(Integer.valueOf(height4 * 2));
                list0.add(Integer.valueOf(low4 * 2));
            }
        }
        for (int size = list0.size() - 1; size >= 0; size--) {
            list.add(list0.get(size));
        }
        for (int i2 = 1; i2 <= 9; i2++) {
            list.add(list0.get(i2));
        }
        return list;
    }

    public static List<Integer> getRhyData2(byte[] data) {
        if (data == null) {
            return null;
        }
        list0.clear();
        list.clear();
        for (int i = 0; i < data.length; i++) {
            int height4 = ByteUtils.getHeight4(data[i]);
            int low4 = ByteUtils.getLow4(data[i]);
            if (i < 7) {
                list0.add(Integer.valueOf(height4 * 2));
                list0.add(Integer.valueOf(low4 * 2));
            }
        }
        for (int size = list0.size() - 2; size >= 0; size--) {
            list.add(list0.get(size));
        }
        for (int i2 = 1; i2 <= 12; i2++) {
            list.add(list0.get(i2));
        }
        return list;
    }

    public static List<Integer> getRhyData3(byte[] data) {
        if (data == null) {
            return null;
        }
        list0.clear();
        list.clear();
        for (int i = 0; i < data.length; i++) {
            int height4 = ByteUtils.getHeight4(data[i]);
            int low4 = ByteUtils.getLow4(data[i]);
            if (i < 7) {
                list0.add(Integer.valueOf(height4 * 2));
                list0.add(Integer.valueOf(low4 * 2));
            }
        }
        for (int size = list0.size() - 2; size >= 0; size--) {
            list.add(list0.get(size));
        }
        for (int i2 = 0; i2 <= 12; i2++) {
            list.add(list0.get(i2));
        }
        return list;
    }

    public static List<Integer> getRhyData4(byte[] data) {
        if (data == null) {
            return null;
        }
        list0.clear();
        list.clear();
        for (int i = 0; i < data.length; i++) {
            int height4 = ByteUtils.getHeight4(data[i]);
            int low4 = ByteUtils.getLow4(data[i]);
            if (i < 7) {
                list0.add(Integer.valueOf(height4 * 2));
                list0.add(Integer.valueOf(low4 * 2));
            }
        }
        for (int size = list0.size() - 2; size >= 0; size--) {
            list.add(list0.get(size));
        }
        for (int i2 = 0; i2 <= 12; i2++) {
            list.add(list0.get(i2));
        }
        return list;
    }

    public static List<Integer> getRhyData5(byte[] data) {
        if (data == null) {
            return null;
        }
        list0.clear();
        list.clear();
        for (int i = 0; i < data.length; i++) {
            int height4 = ByteUtils.getHeight4(data[i]);
            int low4 = ByteUtils.getLow4(data[i]);
            if (i < 5) {
                list0.add(Integer.valueOf(height4 * 2));
                list0.add(Integer.valueOf(low4 * 2));
            }
        }
        for (int size = list0.size() - 1; size >= 0; size--) {
            list.add(list0.get(size));
        }
        for (int i2 = 1; i2 <= 9; i2++) {
            list.add(list0.get(i2));
        }
        return list;
    }

    public static List<Integer> getRhyData1_wide(byte[] data) {
        if (data == null) {
            return null;
        }
        list0.clear();
        list.clear();
        for (int i = 0; i < data.length; i++) {
            int height4 = ByteUtils.getHeight4(data[i]);
            int low4 = ByteUtils.getLow4(data[i]);
            if (i < 7) {
                list0.add(Integer.valueOf(height4 * 2));
                list0.add(Integer.valueOf(low4 * 2));
            }
        }
        for (int size = list0.size() - 1; size >= 0; size--) {
            list.add(list0.get(size));
        }
        for (int i2 = 1; i2 <= 12; i2++) {
            list.add(list0.get(i2));
        }
        return list;
    }

    public static List<Integer> getRhyData2_wide(byte[] data) {
        Log.v("ruis", "getRhyData2_wide data.length" + data.length);
        if (data == null) {
            return null;
        }
        list0.clear();
        list.clear();
        for (int i = 0; i < data.length; i++) {
            int height4 = ByteUtils.getHeight4(data[i]);
            int low4 = ByteUtils.getLow4(data[i]);
            if (i < 9) {
                list0.add(Integer.valueOf(height4 * 2));
                list0.add(Integer.valueOf(low4 * 2));
            }
        }
        Log.v("ruis", "getRhyData2_wide list0.size" + list0.size());
        for (int size = list0.size() - 2; size >= 0; size--) {
            list.add(list0.get(size));
        }
        for (int i2 = 1; i2 <= 16; i2++) {
            list.add(list0.get(i2));
        }
        return list;
    }

    public static List<Integer> getRhyData3_wide(byte[] data) {
        if (data == null) {
            return null;
        }
        list0.clear();
        list.clear();
        for (int i = 0; i < data.length; i++) {
            int height4 = ByteUtils.getHeight4(data[i]);
            int low4 = ByteUtils.getLow4(data[i]);
            if (i < 7) {
                list0.add(Integer.valueOf(height4 * 2));
                list0.add(Integer.valueOf(low4 * 2));
            }
        }
        for (int size = list0.size() - 2; size >= 0; size--) {
            list.add(list0.get(size));
        }
        for (int i2 = 0; i2 <= 12; i2++) {
            list.add(list0.get(i2));
        }
        return list;
    }

    public static List<Integer> getRhyData4_wide(byte[] data) {
        if (data == null) {
            return null;
        }
        list0.clear();
        list.clear();
        for (int i = 0; i < data.length; i++) {
            int height4 = ByteUtils.getHeight4(data[i]);
            int low4 = ByteUtils.getLow4(data[i]);
            if (i < 7) {
                list0.add(Integer.valueOf(height4 * 2));
                list0.add(Integer.valueOf(low4 * 2));
            }
        }
        for (int size = list0.size() - 2; size >= 0; size--) {
            list.add(list0.get(size));
        }
        list.addAll(list0);
        return list;
    }

    public static List<Integer> getRhyData5_wide(byte[] data) {
        if (data == null) {
            return null;
        }
        list0.clear();
        list.clear();
        for (int i = 0; i < data.length; i++) {
            int height4 = ByteUtils.getHeight4(data[i]);
            int low4 = ByteUtils.getLow4(data[i]);
            list0.add(Integer.valueOf(height4 * 2));
            list0.add(Integer.valueOf(low4 * 2));
        }
        for (int size = list0.size() - 1; size >= 0; size--) {
            list.add(list0.get(size));
        }
        list.addAll(list0);
        return list;
    }
}
