package com.wifiled.baselib.uicode.utils;

import com.wifiled.baselib.uicode.inner.UICoreThrowableListener;
import com.wifiled.musiclib.player.constant.PlayerFinal;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UICoreConfig.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010$\u001a\u00020%2\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005J\u000e\u0010&\u001a\u00020%2\u0006\u0010&\u001a\u00020'R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000f\"\u0004\b\u0014\u0010\u0011R\u001a\u0010\u0015\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u000f\"\u0004\b\u0017\u0010\u0011R\u001a\u0010\u0018\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u000f\"\u0004\b\u001a\u0010\u0011R\u001a\u0010\u001b\u001a\u00020\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001a\u0010!\u001a\u00020\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u001e\"\u0004\b#\u0010 ¨\u0006("}, d2 = {"Lcom/wifiled/baselib/uicode/utils/UICoreConfig;", "", "<init>", "()V", "throwableListener", "Lcom/wifiled/baselib/uicode/inner/UICoreThrowableListener;", PlayerFinal.PLAYER_MODE, "", "getMode", "()Z", "setMode", "(Z)V", "defaultThemeColor", "", "getDefaultThemeColor", "()I", "setDefaultThemeColor", "(I)V", "defaultEmptyIcon", "getDefaultEmptyIcon", "setDefaultEmptyIcon", "loadErrorIcon", "getLoadErrorIcon", "setLoadErrorIcon", "netDisconnectIcon", "getNetDisconnectIcon", "setNetDisconnectIcon", "loadingLottie", "", "getLoadingLottie", "()Ljava/lang/String;", "setLoadingLottie", "(Ljava/lang/String;)V", "progressLottie", "getProgressLottie", "setProgressLottie", "setCatchThrowableListener", "", "throwable", "", "baselib_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class UICoreConfig {
    private static int defaultEmptyIcon;
    private static int defaultThemeColor;
    private static int loadErrorIcon;
    private static boolean mode;
    private static int netDisconnectIcon;
    private static UICoreThrowableListener throwableListener;
    public static final UICoreConfig INSTANCE = new UICoreConfig();
    private static String loadingLottie = "";
    private static String progressLottie = "commonProgressLottie.json";

    private UICoreConfig() {
    }

    public final boolean getMode() {
        return mode;
    }

    public final void setMode(boolean z) {
        mode = z;
    }

    public final int getDefaultThemeColor() {
        return defaultThemeColor;
    }

    public final void setDefaultThemeColor(int i) {
        defaultThemeColor = i;
    }

    public final int getDefaultEmptyIcon() {
        return defaultEmptyIcon;
    }

    public final void setDefaultEmptyIcon(int i) {
        defaultEmptyIcon = i;
    }

    public final int getLoadErrorIcon() {
        return loadErrorIcon;
    }

    public final void setLoadErrorIcon(int i) {
        loadErrorIcon = i;
    }

    public final int getNetDisconnectIcon() {
        return netDisconnectIcon;
    }

    public final void setNetDisconnectIcon(int i) {
        netDisconnectIcon = i;
    }

    public final String getLoadingLottie() {
        return loadingLottie;
    }

    public final void setLoadingLottie(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        loadingLottie = str;
    }

    public final String getProgressLottie() {
        return progressLottie;
    }

    public final void setProgressLottie(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        progressLottie = str;
    }

    public final void setCatchThrowableListener(UICoreThrowableListener throwableListener2) {
        throwableListener = throwableListener2;
    }

    public final void throwable(Throwable throwable) {
        Intrinsics.checkNotNullParameter(throwable, "throwable");
        UICoreThrowableListener uICoreThrowableListener = throwableListener;
        if (uICoreThrowableListener != null) {
            Intrinsics.checkNotNull(uICoreThrowableListener);
            uICoreThrowableListener.catchThrowable(throwable);
        }
    }
}
