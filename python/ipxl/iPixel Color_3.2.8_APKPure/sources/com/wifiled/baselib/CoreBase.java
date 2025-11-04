package com.wifiled.baselib;

import android.content.Context;
import com.wifiled.baselib.app.language.LanguageManager;
import com.wifiled.baselib.utils.LogUtils;

/* loaded from: classes2.dex */
public class CoreBase {
    private static Context mContext;

    public static void init(Context context) {
        init(context, null);
    }

    public static void init(Context context, Configuration configuration) {
        mContext = context;
        if (configuration == null) {
            configuration = Configuration.defalut();
        }
        LogUtils.logInit(configuration.loggable, configuration.logTag);
        LanguageManager.init(context, configuration.langable, configuration.language);
    }

    public static Context getContext() {
        Context context = mContext;
        if (context != null) {
            return context;
        }
        throw new IllegalStateException("please init CoreBase");
    }
}
