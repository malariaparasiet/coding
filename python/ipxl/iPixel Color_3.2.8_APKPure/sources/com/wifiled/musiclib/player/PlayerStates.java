package com.wifiled.musiclib.player;

/* loaded from: classes3.dex */
public class PlayerStates {
    public static final int PLAYING = 3;
    public static final int READY_TO_PLAY = 2;
    public static final int STATE_WAIT = 0;
    public static final int STOPPED = 4;
    public int playerState = 0;

    public int get() {
        return this.playerState;
    }

    public void set(int i) {
        this.playerState = i;
    }

    public synchronized boolean isReadyToPlay() {
        return this.playerState == 2;
    }

    public synchronized boolean isPlaying() {
        return this.playerState == 3;
    }

    public synchronized boolean isStopped() {
        return this.playerState == 4;
    }
}
