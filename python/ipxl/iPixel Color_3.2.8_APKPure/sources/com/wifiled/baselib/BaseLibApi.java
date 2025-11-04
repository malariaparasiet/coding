package com.wifiled.baselib;

import android.content.Context;
import com.wifiled.baselib.utils.LogUtils;

/* loaded from: classes2.dex */
public class BaseLibApi {
    private static Context mContext;

    public static void init(Context context, Options options) {
        mContext = context;
        setLoggable(options.isLoggable());
    }

    private static void setLoggable(boolean z) {
        LogUtils.logInit(z, "akon");
    }

    public static Context getContext() {
        Context context = mContext;
        if (context != null) {
            return context;
        }
        throw new IllegalStateException("please init BaseLibApi");
    }
}
