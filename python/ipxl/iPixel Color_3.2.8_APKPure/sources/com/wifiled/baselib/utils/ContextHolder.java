package com.wifiled.baselib.utils;

import android.app.Application;
import android.content.Context;

/* loaded from: classes2.dex */
public class ContextHolder {
    static Context ApplicationContext;
    private final ContextHolder self = this;

    public static void init(Context context) {
        ApplicationContext = context;
    }

    public static Context getContext() {
        Context context = ApplicationContext;
        if (context != null) {
            return context;
        }
        try {
            Application application = (Application) Class.forName("android.app.ActivityThread").getMethod("currentApplication", new Class[0]).invoke(null, null);
            if (application != null) {
                ApplicationContext = application;
                return application;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Application application2 = (Application) Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, null);
            if (application2 != null) {
                ApplicationContext = application2;
                return application2;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        throw new IllegalStateException("ContextHolder is not initialed, it is recommend to init with application context.");
    }
}
