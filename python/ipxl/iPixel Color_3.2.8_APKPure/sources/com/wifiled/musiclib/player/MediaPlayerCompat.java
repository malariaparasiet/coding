package com.wifiled.musiclib.player;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.audiofx.Visualizer;
import android.util.Log;
import com.wifiled.musiclib.player.callback.OnCompletionListener;
import com.wifiled.musiclib.player.callback.OnDataCaptureListener;
import java.io.IOException;
import kotlin.UByte;

/* loaded from: classes3.dex */
public class MediaPlayerCompat implements IPlayer {
    private static final String TAG = "MediaPlayerCompat";
    private OnDataCaptureListener dataCaptureListener;
    public Visualizer visualizer;
    private MediaPlayer customMediaPlayer = new MediaPlayer();
    private android.media.MediaPlayer nativeMediaPlayer = new android.media.MediaPlayer();
    private PlayerType playerType = PlayerType.NATIVE_PLAYER;

    public enum PlayerType {
        NATIVE_PLAYER,
        CUSTOM_PLAYER
    }

    public MediaPlayer getCustomMediaPlayer() {
        return this.customMediaPlayer;
    }

    @Override // com.wifiled.musiclib.player.IPlayer
    public void setVolume(float f, float f2) {
        if (isNativeMediaPlayer()) {
            this.nativeMediaPlayer.setVolume(f, f2);
        } else {
            this.customMediaPlayer.setVolume(f, f2);
        }
    }

    @Override // com.wifiled.musiclib.player.IPlayer
    public void start() {
        if (isNativeMediaPlayer()) {
            this.nativeMediaPlayer.start();
        } else {
            this.customMediaPlayer.start();
        }
    }

    @Override // com.wifiled.musiclib.player.IPlayer
    public void stop() {
        if (isNativeMediaPlayer()) {
            this.nativeMediaPlayer.stop();
        } else {
            this.customMediaPlayer.stop();
        }
    }

    @Override // com.wifiled.musiclib.player.IPlayer
    public void pause() {
        if (isNativeMediaPlayer()) {
            this.nativeMediaPlayer.pause();
        } else {
            this.customMediaPlayer.pause();
        }
    }

    @Override // com.wifiled.musiclib.player.IPlayer
    public void seekTo(int i) {
        Log.e(TAG, "seekTo: " + i);
        if (isNativeMediaPlayer()) {
            android.media.MediaPlayer mediaPlayer = this.nativeMediaPlayer;
            mediaPlayer.seekTo((i * mediaPlayer.getDuration()) / 100);
            this.nativeMediaPlayer.start();
            return;
        }
        this.customMediaPlayer.seekTo(i);
    }

    @Override // com.wifiled.musiclib.player.IPlayer
    public int getPlayCurrentTime() {
        if (isNativeMediaPlayer()) {
            return this.nativeMediaPlayer.getCurrentPosition();
        }
        return this.customMediaPlayer.getCurrentPosition();
    }

    @Override // com.wifiled.musiclib.player.IPlayer
    public int getPlayDuration() {
        if (isNativeMediaPlayer()) {
            return this.nativeMediaPlayer.getDuration();
        }
        return this.customMediaPlayer.getDuration();
    }

    @Override // com.wifiled.musiclib.player.IPlayer
    public boolean isPlaying() {
        if (isNativeMediaPlayer()) {
            return this.nativeMediaPlayer.isPlaying();
        }
        return this.customMediaPlayer.isPlaying();
    }

    @Override // com.wifiled.musiclib.player.IPlayer
    public void play(String str, AssetFileDescriptor assetFileDescriptor, int i) throws IOException {
        if (isNativeMediaPlayer()) {
            this.nativeMediaPlayer.reset();
            this.nativeMediaPlayer.setAudioStreamType(3);
            if (i == 0) {
                this.nativeMediaPlayer.setDataSource(str);
            } else {
                this.nativeMediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
            }
            this.nativeMediaPlayer.prepare();
            return;
        }
        this.customMediaPlayer.reset();
        this.customMediaPlayer.setAudioStreamType(3);
        if (i == 0) {
            this.customMediaPlayer.setDataSource(str);
        }
        this.customMediaPlayer.prepare();
    }

    @Override // com.wifiled.musiclib.player.IPlayer
    public void setLooping(boolean z) {
        if (isNativeMediaPlayer()) {
            this.nativeMediaPlayer.setLooping(z);
        } else {
            this.customMediaPlayer.setLooping(z);
        }
    }

    @Override // com.wifiled.musiclib.player.IPlayer
    public void setMediaPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public boolean isNativeMediaPlayer() {
        return this.playerType == PlayerType.NATIVE_PLAYER;
    }

    public Object getMediaPlayer() {
        if (this.playerType == PlayerType.NATIVE_PLAYER) {
            return this.nativeMediaPlayer;
        }
        return this.customMediaPlayer;
    }

    public void setOnCompletionListener(final OnCompletionListener onCompletionListener) {
        if (isNativeMediaPlayer()) {
            this.nativeMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.wifiled.musiclib.player.MediaPlayerCompat.1
                @Override // android.media.MediaPlayer.OnCompletionListener
                public void onCompletion(android.media.MediaPlayer mediaPlayer) {
                    onCompletionListener.onCompletion(mediaPlayer);
                }
            });
        } else {
            this.customMediaPlayer.setOnCompletionListener(onCompletionListener);
        }
    }

    public void setDataCaptureListener(OnDataCaptureListener onDataCaptureListener) {
        if (isNativeMediaPlayer()) {
            this.dataCaptureListener = onDataCaptureListener;
        } else {
            this.customMediaPlayer.setDataCaptureListener(onDataCaptureListener);
        }
    }

    private void startVisualizer() {
        this.visualizer = new Visualizer(this.nativeMediaPlayer.getAudioSessionId());
        Log.e(TAG, "AudioSessionId:" + this.nativeMediaPlayer.getAudioSessionId());
        this.visualizer.setCaptureSize(Visualizer.getCaptureSizeRange()[0]);
        this.visualizer.setDataCaptureListener(new Visualizer.OnDataCaptureListener() { // from class: com.wifiled.musiclib.player.MediaPlayerCompat.2
            @Override // android.media.audiofx.Visualizer.OnDataCaptureListener
            public void onWaveFormDataCapture(Visualizer visualizer, byte[] bArr, int i) {
            }

            @Override // android.media.audiofx.Visualizer.OnDataCaptureListener
            public void onFftDataCapture(Visualizer visualizer, byte[] bArr, int i) {
                try {
                    int length = bArr.length;
                    short[] sArr = new short[length / 4];
                    for (int i2 = 0; i2 < length; i2 += 4) {
                        int i3 = i2 / 4;
                        if (i3 < length / 4) {
                            short s = (short) (bArr[i2] & UByte.MAX_VALUE);
                            sArr[i3] = s;
                            sArr[i3] = (short) (s | ((short) ((bArr[i2 + 1] & UByte.MAX_VALUE) << 8)));
                        }
                    }
                    if (MediaPlayerCompat.this.dataCaptureListener == null || bArr == null) {
                        return;
                    }
                    MediaPlayerCompat.this.dataCaptureListener.onWaveDataCapture(sArr, bArr, i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, Visualizer.getMaxCaptureRate() / 2, true, true);
        this.visualizer.setEnabled(true);
    }

    private void stopVisualizer() {
        Visualizer visualizer = this.visualizer;
        if (visualizer != null) {
            visualizer.setEnabled(false);
            this.visualizer.release();
            this.visualizer = null;
        }
    }

    public void stopRhythm() {
        if (isNativeMediaPlayer()) {
            stopVisualizer();
        } else {
            this.customMediaPlayer.setRhythming(false);
        }
    }

    public void startRhythm() {
        if (isNativeMediaPlayer()) {
            startVisualizer();
        } else {
            this.customMediaPlayer.setRhythming(true);
        }
    }
}
