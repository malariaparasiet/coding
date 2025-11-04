package com.wifiled.musiclib.player.callback;

/* loaded from: classes3.dex */
public interface PlayStateCallback {
    void onModeChange(int i);

    void onSeekChange(int i, int i2, String str, String str2);

    void onStateChange(int i, int i2, int i3);
}
