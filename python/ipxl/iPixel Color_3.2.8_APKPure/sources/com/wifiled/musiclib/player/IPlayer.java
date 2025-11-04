package com.wifiled.musiclib.player;

import android.content.res.AssetFileDescriptor;
import com.wifiled.musiclib.player.MediaPlayerCompat;
import java.io.IOException;

/* loaded from: classes3.dex */
public interface IPlayer<T> {
    int getPlayCurrentTime();

    int getPlayDuration();

    boolean isPlaying();

    void pause();

    void play(String str, AssetFileDescriptor assetFileDescriptor, int i) throws IOException;

    void seekTo(int i);

    void setLooping(boolean z);

    void setMediaPlayerType(MediaPlayerCompat.PlayerType playerType);

    void setVolume(float f, float f2);

    void start();

    void stop();
}
