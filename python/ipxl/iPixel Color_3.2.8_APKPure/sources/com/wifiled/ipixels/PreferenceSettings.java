package com.wifiled.ipixels;

import android.content.Context;
import com.wifiled.baselib.utils.SPUtils;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PreferenceSettings.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u0005J\u000e\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\nJ\u000e\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\nJ\u0006\u0010\u000e\u001a\u00020\nJ\u000e\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0012\u001a\n \u0013*\u0004\u0018\u00010\u00110\u0011J\u000e\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0015\u001a\n \u0013*\u0004\u0018\u00010\u00110\u0011J\u000e\u0010\u0016\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0017J\u0006\u0010\u0018\u001a\u00020\u0017J\u000e\u0010\u0019\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u001a\u001a\u00020\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/wifiled/ipixels/PreferenceSettings;", "", "<init>", "()V", "context", "Landroid/content/Context;", "init", "", "setLedType", "type", "", "getLedType", "setMcu", "mcu", "getMcu", "setCid", "cid", "", "getCid", "kotlin.jvm.PlatformType", "setPid", "getPid", "setLedHasWifi", "", "getLedHasWifi", "setConnectType", "getConnectType", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class PreferenceSettings {
    public static final PreferenceSettings INSTANCE = new PreferenceSettings();
    private static Context context;

    private PreferenceSettings() {
    }

    public final void init(Context context2) {
        Intrinsics.checkNotNullParameter(context2, "context");
        context = context2;
        UtilsExtensionKt.async(new Function0() { // from class: com.wifiled.ipixels.PreferenceSettings$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit init$lambda$0;
                init$lambda$0 = PreferenceSettings.init$lambda$0();
                return init$lambda$0;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit init$lambda$0() {
        PreferenceSettings preferenceSettings = INSTANCE;
        int ledType = preferenceSettings.getLedType();
        boolean ledHasWifi = preferenceSettings.getLedHasWifi();
        AppConfig.INSTANCE.setLedType(ledType);
        AppConfig.INSTANCE.setLedHasWifi(ledHasWifi);
        AppConfig.INSTANCE.setMcu(preferenceSettings.getMcu());
        AppConfig appConfig = AppConfig.INSTANCE;
        String cid = preferenceSettings.getCid();
        Intrinsics.checkNotNullExpressionValue(cid, "getCid(...)");
        appConfig.setCid(cid);
        AppConfig appConfig2 = AppConfig.INSTANCE;
        String pid = preferenceSettings.getPid();
        Intrinsics.checkNotNullExpressionValue(pid, "getPid(...)");
        appConfig2.setPid(pid);
        return Unit.INSTANCE;
    }

    public final void setLedType(int type) {
        Context context2 = context;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context2 = null;
        }
        SPUtils.put(context2, "led_type", Integer.valueOf(type));
    }

    public final int getLedType() {
        Context context2 = context;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context2 = null;
        }
        Object obj = SPUtils.get(context2, "led_type", Integer.valueOf(AppConfig.INSTANCE.getLedType()));
        Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
        return ((Number) obj).intValue();
    }

    public final void setMcu(int mcu) {
        Context context2 = context;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context2 = null;
        }
        SPUtils.put(context2, "mcu", Integer.valueOf(mcu));
    }

    public final int getMcu() {
        Context context2 = context;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context2 = null;
        }
        Object obj = SPUtils.get(context2, "mcu", 3);
        Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
        return ((Number) obj).intValue();
    }

    public final void setCid(String cid) {
        Intrinsics.checkNotNullParameter(cid, "cid");
        Context context2 = context;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context2 = null;
        }
        SPUtils.put(context2, "cid", cid);
    }

    public final String getCid() {
        Context context2 = context;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context2 = null;
        }
        return (String) SPUtils.get(context2, "pid", "");
    }

    public final void setPid(String cid) {
        Intrinsics.checkNotNullParameter(cid, "cid");
        Context context2 = context;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context2 = null;
        }
        SPUtils.put(context2, "cid", cid);
    }

    public final String getPid() {
        Context context2 = context;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context2 = null;
        }
        return (String) SPUtils.get(context2, "pid", "");
    }

    public final void setLedHasWifi(boolean type) {
        Context context2 = context;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context2 = null;
        }
        SPUtils.put(context2, "led_has_wifi", Boolean.valueOf(type));
    }

    public final boolean getLedHasWifi() {
        Context context2 = context;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context2 = null;
        }
        Object obj = SPUtils.get(context2, "led_has_wifi", false);
        Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
        return ((Boolean) obj).booleanValue();
    }

    public final void setConnectType(int type) {
        Context context2 = context;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context2 = null;
        }
        SPUtils.put(context2, "connect_type", Integer.valueOf(type));
    }

    public final int getConnectType() {
        Context context2 = context;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            context2 = null;
        }
        Object obj = SPUtils.get(context2, "connect_type", 0);
        Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
        return ((Number) obj).intValue();
    }
}
