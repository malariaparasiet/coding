package com.wifiled.baselib.utils;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import com.wifiled.baselib.R;
import com.wifiled.baselib.manager.SystemBarTintManager;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* loaded from: classes2.dex */
public class GlobalStatusBarUtil {
    private static final int STATUS_VIEW_ID = R.id.status_view;

    private static int statusColorIntensity(int i, int i2) {
        if (i2 == 0) {
            return i;
        }
        float f = 1.0f - (i2 / 255.0f);
        return ((int) (((i & 255) * f) + 0.5d)) | (((int) ((((i >> 16) & 255) * f) + 0.5d)) << 16) | ViewCompat.MEASURED_STATE_MASK | (((int) ((((i >> 8) & 255) * f) + 0.5d)) << 8);
    }

    public static void setUpStatusBar(Activity activity, int i, boolean z) {
        setTranslucentStatus(activity, true);
        setXiaomiStatusBarDarkMode(activity.getWindow(), z);
        SystemBarTintManager systemBarTintManager = new SystemBarTintManager(activity);
        systemBarTintManager.setStatusBarTintEnabled(true);
        systemBarTintManager.setStatusBarTintResource(i);
        setStatusColor(activity, i);
        setStatusBarDarkFont(activity, z);
    }

    public static boolean setStatusBarDarkIcon(Window window, boolean z) {
        if (window != null) {
            try {
                WindowManager.LayoutParams attributes = window.getAttributes();
                Field declaredField = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field declaredField2 = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
                declaredField.setAccessible(true);
                declaredField2.setAccessible(true);
                int i = declaredField.getInt(null);
                int i2 = declaredField2.getInt(attributes);
                declaredField2.setInt(attributes, z ? i2 | i : (~i) & i2);
                window.setAttributes(attributes);
                return true;
            } catch (Exception unused) {
                LogUtils.loge("MeiZu>>>>+setStatusBarDarkIcon: failed", new Object[0]);
            }
        }
        return false;
    }

    public static void setXiaomiStatusBarDarkMode(Window window, boolean z) {
        if ("Xiaomi".equals(Build.MANUFACTURER)) {
            Class<?> cls = window.getClass();
            try {
                Class<?> cls2 = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                int i = cls2.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE").getInt(cls2);
                cls.getMethod("setExtraFlags", Integer.TYPE, Integer.TYPE).invoke(window, Integer.valueOf(z ? i : 0), Integer.valueOf(i));
            } catch (Exception unused) {
            }
        }
    }

    public static void setTranslucentStatus(Activity activity, boolean z) {
        Window window = activity.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        if (z) {
            attributes.flags |= AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
        } else {
            attributes.flags &= -67108865;
        }
        window.setAttributes(attributes);
    }

    public static void setStatusColor(Activity activity, int i) {
        setStatusColor(activity, i, 0);
    }

    public static void setStatusColor(Activity activity, int i, int i2) {
        activity.getWindow().addFlags(Integer.MIN_VALUE);
        activity.getWindow().clearFlags(AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL);
    }

    private static void setStatusViewToAct(Activity activity, int i, int i2) {
        ViewGroup viewGroup = (ViewGroup) activity.getWindow().getDecorView();
        View findViewById = viewGroup.findViewById(STATUS_VIEW_ID);
        if (findViewById != null) {
            if (findViewById.getVisibility() == 8) {
                findViewById.setVisibility(0);
            }
            findViewById.setBackgroundColor(statusColorIntensity(i, i2));
            return;
        }
        viewGroup.addView(createStatusBarView(activity, i, i2));
    }

    public static void setRootView(Activity activity) {
        ViewGroup viewGroup = (ViewGroup) activity.findViewById(android.R.id.content);
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(0);
            if (childAt instanceof ViewGroup) {
                childAt.setFitsSystemWindows(true);
                ((ViewGroup) childAt).setClipToPadding(true);
            }
        }
    }

    private static View createStatusBarView(Activity activity, int i, int i2) {
        View view = new View(activity);
        view.setLayoutParams(new LinearLayout.LayoutParams(-1, getStatusBarHeight(activity)));
        view.setBackgroundColor(statusColorIntensity(i, i2));
        view.setId(STATUS_VIEW_ID);
        return view;
    }

    public static int getStatusBarHeight(Activity activity) {
        return activity.getResources().getDimensionPixelSize(activity.getResources().getIdentifier("status_bar_height", "dimen", "android"));
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0047  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean setStatusBarDarkFont(android.app.Activity r3, boolean r4) {
        /*
            boolean r0 = setMIUIStatusBarDarkFont(r3, r4)
            if (r0 != 0) goto La
            boolean r0 = setMeizuStatusBarDarkFont(r3, r4)
        La:
            r1 = 1
            if (r0 != 0) goto L22
            if (r4 == 0) goto L22
            android.view.Window r0 = r3.getWindow()
            android.view.View r0 = r0.getDecorView()
            int r2 = r0.getSystemUiVisibility()
            r2 = r2 | 8192(0x2000, float:1.148E-41)
            r0.setSystemUiVisibility(r2)
        L20:
            r0 = r1
            goto L38
        L22:
            if (r0 != 0) goto L38
            if (r4 != 0) goto L38
            android.view.Window r0 = r3.getWindow()
            android.view.View r0 = r0.getDecorView()
            int r2 = r0.getSystemUiVisibility()
            r2 = r2 & (-8193(0xffffffffffffdfff, float:NaN))
            r0.setSystemUiVisibility(r2)
            goto L20
        L38:
            android.view.Window r1 = r3.getWindow()
            android.view.View r1 = r1.getDecorView()
            r2 = 9216(0x2400, float:1.2914E-41)
            r1.setSystemUiVisibility(r2)
            if (r4 != 0) goto L54
            android.view.Window r3 = r3.getWindow()
            android.view.View r3 = r3.getDecorView()
            r4 = 3072(0xc00, float:4.305E-42)
            r3.setSystemUiVisibility(r4)
        L54:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wifiled.baselib.utils.GlobalStatusBarUtil.setStatusBarDarkFont(android.app.Activity, boolean):boolean");
    }

    private static boolean setMeizuStatusBarDarkFont(Activity activity, boolean z) {
        try {
            WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
            Field declaredField = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
            Field declaredField2 = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
            declaredField.setAccessible(true);
            declaredField2.setAccessible(true);
            int i = declaredField.getInt(null);
            int i2 = declaredField2.getInt(attributes);
            declaredField2.setInt(attributes, z ? i2 | i : (~i) & i2);
            activity.getWindow().setAttributes(attributes);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    private static boolean setMIUIStatusBarDarkFont(Activity activity, boolean z) {
        Window window = activity.getWindow();
        Class<?> cls = window.getClass();
        try {
            Class<?> cls2 = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            int i = cls2.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE").getInt(cls2);
            Method method = cls.getMethod("setExtraFlags", Integer.TYPE, Integer.TYPE);
            if (z) {
                method.invoke(window, Integer.valueOf(i), Integer.valueOf(i));
            } else {
                method.invoke(window, 0, Integer.valueOf(i));
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static void setFitsSystem(Activity activity, View view) {
        view.setFitsSystemWindows(true);
        if (view != null) {
            view.setPadding(0, getStatusBarHeight(activity), 0, 0);
        }
    }

    public static void setFitsSystemWindows(Activity activity, boolean z) {
        View childAt = ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
        if (childAt != null) {
            childAt.setFitsSystemWindows(z);
        }
    }
}
