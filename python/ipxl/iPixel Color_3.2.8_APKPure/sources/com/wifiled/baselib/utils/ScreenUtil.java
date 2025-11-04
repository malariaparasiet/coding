package com.wifiled.baselib.utils;

import android.content.Context;
import com.wifiled.baselib.BaseLibApi;

/* loaded from: classes2.dex */
public class ScreenUtil {
    private static float findScale(float f) {
        if (f <= 1.0f) {
            return 1.0f;
        }
        if (f <= 1.5d) {
            return 1.5f;
        }
        if (f <= 2.0f) {
            return 2.0f;
        }
        if (f <= 3.0f) {
            return 3.0f;
        }
        return f;
    }

    public static int dp2px(Context context, float f) {
        return (int) ((f * getScale(context)) + 0.5f);
    }

    public static int px2dp(Context context, float f) {
        return (int) ((f / getScale(context)) + 0.5f);
    }

    public static int px2sp(Context context, float f) {
        return (int) ((f / getScale(context)) + 0.5f);
    }

    public static int sp2px(Context context, float f) {
        return (int) ((f * getScale(context)) + 0.5f);
    }

    public static float getScale(Context context) {
        return findScale(context.getResources().getDisplayMetrics().scaledDensity);
    }

    public static int dip2px(float f) {
        return (int) ((f * BaseLibApi.getContext().getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int sp2px(float f) {
        return (int) ((f * getScale(BaseLibApi.getContext())) + 0.5f);
    }

    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }
}
