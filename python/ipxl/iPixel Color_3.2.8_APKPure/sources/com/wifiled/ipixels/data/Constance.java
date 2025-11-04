package com.wifiled.ipixels.data;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import com.wifiled.baselib.crash.CrashCollect;
import com.wifiled.ipixels.App;
import com.wifiled.ipixels.R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;
import kotlin.annotation.AnnotationRetention;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Constance.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/wifiled/ipixels/data/Constance;", "", "<init>", "()V", "AppInformation", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class Constance {

    /* compiled from: Constance.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0002\b\u0002\b\u0087\u0002\u0018\u0000 \u00022\u00020\u0001:\u0001\u0002B\u0000¨\u0006\u0003"}, d2 = {"Lcom/wifiled/ipixels/data/Constance$AppInformation;", "", "Companion", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @Retention(RetentionPolicy.SOURCE)
    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    public @interface AppInformation {

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = Companion.$$INSTANCE;

        /* compiled from: Constance.kt */
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\n\u001a\u00020\u000bJ\u0006\u0010\f\u001a\u00020\u000bJ\u0006\u0010\r\u001a\u00020\u000bJ\u0006\u0010\u000e\u001a\u00020\u000bJ\u0006\u0010\u000f\u001a\u00020\u000bJ\u0006\u0010\u0010\u001a\u00020\u000bJ\u0006\u0010\u0011\u001a\u00020\u000bJ\u0006\u0010\u0012\u001a\u00020\u000bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/wifiled/ipixels/data/Constance$AppInformation$Companion;", "", "<init>", "()V", "packageManager", "Landroid/content/pm/PackageManager;", "packageInfo", "Landroid/content/pm/PackageInfo;", "applicationInfo", "Landroid/content/pm/ApplicationInfo;", "appName", "", "packageName", "channel", "brand", "model", "release", CrashCollect.VERSION_NAME, CrashCollect.VERSION_CODE, "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Companion {
            static final /* synthetic */ Companion $$INSTANCE = new Companion();
            private static final ApplicationInfo applicationInfo;
            private static final PackageInfo packageInfo;
            private static final PackageManager packageManager;

            private Companion() {
            }

            static {
                PackageInfo packageInfo2;
                ApplicationInfo applicationInfo2;
                Context context = App.INSTANCE.getContext();
                PackageManager packageManager2 = context.getPackageManager();
                Intrinsics.checkNotNullExpressionValue(packageManager2, "getPackageManager(...)");
                packageManager = packageManager2;
                if (Build.VERSION.SDK_INT >= 33) {
                    packageInfo2 = packageManager2.getPackageInfo(context.getPackageName(), 0);
                } else {
                    packageInfo2 = packageManager2.getPackageInfo(context.getPackageName(), 0);
                }
                Intrinsics.checkNotNull(packageInfo2);
                packageInfo = packageInfo2;
                if (Build.VERSION.SDK_INT >= 33) {
                    applicationInfo2 = packageManager2.getApplicationInfo(context.getPackageName(), 128);
                    Intrinsics.checkNotNull(applicationInfo2);
                } else {
                    applicationInfo2 = packageManager2.getApplicationInfo(context.getPackageName(), 128);
                    Intrinsics.checkNotNull(applicationInfo2);
                }
                applicationInfo = applicationInfo2;
            }

            public final String appName() {
                String string = App.INSTANCE.getContext().getResources().getString(R.string.app_name);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                return string;
            }

            public final String packageName() {
                String packageName = packageInfo.packageName;
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                return packageName;
            }

            public final String channel() {
                String string;
                Bundle bundle = applicationInfo.metaData;
                return (bundle == null || (string = bundle.getString("HEATON_CHANNEL")) == null) ? "NULL" : string;
            }

            public final String brand() {
                String BRAND = Build.BRAND;
                Intrinsics.checkNotNullExpressionValue(BRAND, "BRAND");
                return BRAND;
            }

            public final String model() {
                String MODEL = Build.MODEL;
                Intrinsics.checkNotNullExpressionValue(MODEL, "MODEL");
                return MODEL;
            }

            public final String release() {
                if (Build.VERSION.SDK_INT < 31) {
                    String RELEASE = Build.VERSION.RELEASE;
                    Intrinsics.checkNotNullExpressionValue(RELEASE, "RELEASE");
                    return RELEASE;
                }
                String str = applicationInfo.compileSdkVersionCodename;
                if (str == null) {
                    str = Build.VERSION.RELEASE;
                }
                Intrinsics.checkNotNull(str);
                return str;
            }

            public final String versionName() {
                return String.valueOf(packageInfo.versionName);
            }

            public final String versionCode() {
                return Build.VERSION.SDK_INT >= 28 ? String.valueOf(packageInfo.getLongVersionCode()) : String.valueOf(packageInfo.versionCode);
            }
        }
    }
}
