package com.wifiled.baselib.utils;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.CsvFormatStrategy;
import com.orhanobut.logger.DiskLogAdapter;
import com.orhanobut.logger.LogcatLogStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import io.reactivex.annotations.SchedulerSupport;

/* loaded from: classes2.dex */
public class LogUtils {
    public static void logInit(final boolean z, String str) {
        Logger.addLogAdapter(new AndroidLogAdapter(PrettyFormatStrategy.newBuilder().showThreadInfo(false).methodCount(2).methodOffset(5).logStrategy(new LogcatLogStrategy()).tag(str).build()) { // from class: com.wifiled.baselib.utils.LogUtils.1
            @Override // com.orhanobut.logger.AndroidLogAdapter, com.orhanobut.logger.LogAdapter
            public boolean isLoggable(int i, String str2) {
                return z;
            }
        });
        Logger.addLogAdapter(new DiskLogAdapter(CsvFormatStrategy.newBuilder().tag(SchedulerSupport.CUSTOM).build()));
    }

    public static void logd(String str, Object... objArr) {
        Logger.d(str, objArr);
    }

    public static void loge(Throwable th, String str, Object... objArr) {
        Logger.e(th, str, objArr);
    }

    public static void loge(String str, Object... objArr) {
        Logger.e(str, objArr);
    }

    public static void logi(String str, Object... objArr) {
        Logger.i(str, objArr);
    }

    public static void logv(String str, Object... objArr) {
        Logger.v(str, objArr);
    }

    public static void logw(String str, Object... objArr) {
        Logger.v(str, objArr);
    }

    public static void logwtf(String str, Object... objArr) {
        Logger.wtf(str, objArr);
    }

    public static void logjson(String str) {
        Logger.json(str);
    }

    public static void logxml(String str) {
        Logger.xml(str);
    }
}
