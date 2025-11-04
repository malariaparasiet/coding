package com.wifiled.musiclib.player;

import android.content.Context;
import android.media.AudioTrack;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaExtractor;
import android.net.Uri;
import android.os.Process;
import android.util.Log;
import com.wifiled.musiclib.player.callback.OnCompletionListener;
import com.wifiled.musiclib.player.callback.OnDataCaptureListener;
import java.nio.ByteBuffer;
import kotlin.UByte;

/* loaded from: classes3.dex */
public class MediaPlayer implements Runnable {
    public static final long DEFALUT_DELAY = 50;
    public static final String LOG_TAG = "MediaPlayer";
    private AudioTrack audioTrack;
    private MediaCodec codec;
    private MediaCodec.BufferInfo decodeBufferInfo;
    private ByteBuffer[] decodeInputBuffers;
    private ByteBuffer[] decodeOutputBuffers;
    private MediaExtractor extractor;
    private Context mContext;
    private OnCompletionListener onCompletionListener;
    private OnDataCaptureListener onDataCaptureListener;
    private OnErrorListener onErrorListener;
    private OnPreparedListener onPreparedListener;
    private OnStartListener onStartListener;
    private int mStreamType = 3;
    private PlayerStates state = new PlayerStates();
    private String sourcePath = null;
    private Uri sourceUri = null;
    private int sourceRawResId = -1;
    private boolean stop = false;
    private boolean isRhythming = false;
    private String mime = null;
    private int sampleRate = 0;
    private int channels = 0;
    private int bitrate = 0;
    private long presentationTimeUs = 0;
    private long duration = 0;
    private long lastTime = System.currentTimeMillis();

    public interface OnErrorListener {
        void onError(MediaPlayer mediaPlayer, String str);
    }

    public interface OnPreparedListener {
        void onPrepared(MediaPlayer mediaPlayer);
    }

    public interface OnStartListener {
        void onStart(String str, int i, int i2, long j);
    }

    public void setLooping(boolean z) {
    }

    public void setOnCompletionListener(OnCompletionListener onCompletionListener) {
        this.onCompletionListener = onCompletionListener;
    }

    public void setDataCaptureListener(OnDataCaptureListener onDataCaptureListener) {
        this.onDataCaptureListener = onDataCaptureListener;
    }

    public void setOnPreparedListener(OnPreparedListener onPreparedListener) {
        this.onPreparedListener = onPreparedListener;
    }

    public void setOnStartListener(OnStartListener onStartListener) {
        this.onStartListener = onStartListener;
    }

    public void setOnErrorListener(OnErrorListener onErrorListener) {
        this.onErrorListener = onErrorListener;
    }

    public boolean isLive() {
        return this.duration == 0;
    }

    public void setVolume(float f, float f2) {
        AudioTrack audioTrack = this.audioTrack;
        if (audioTrack != null) {
            audioTrack.setStereoVolume(f, f2);
        }
    }

    public void setDataSource(String str) {
        this.sourcePath = str;
    }

    public void setDataSource(Context context, Uri uri) {
        this.mContext = context;
        this.sourceUri = uri;
    }

    public void setDataSource(Context context, int i) {
        this.mContext = context;
        this.sourceRawResId = i;
    }

    public void reset() {
        this.state.set(4);
        this.stop = true;
        try {
            Thread.sleep(500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void prepare() {
        Log.v("ruis", "sourcePath=" + this.sourcePath);
        Log.v("ruis", "sourceUri=" + this.sourceUri);
        if (this.sourcePath == null && this.sourceUri == null) {
            throw new IllegalArgumentException("sourcePath can't be null");
        }
        initMediaDecode();
        initAudioTrack();
    }

    public void setAudioStreamType(int i) {
        this.mStreamType = i;
    }

    public int getAudioStreamType() {
        return this.mStreamType;
    }

    public void start() {
        if (this.state.get() == 4) {
            this.stop = false;
            new Thread(this).start();
        }
        if (this.state.get() == 2) {
            this.state.set(3);
            syncNotify();
        }
    }

    public synchronized void syncNotify() {
        notify();
    }

    public void stop() {
        this.stop = true;
    }

    public void pause() {
        this.state.set(2);
    }

    public void seekTo(long j) {
        MediaExtractor mediaExtractor = this.extractor;
        if (mediaExtractor != null) {
            mediaExtractor.seekTo(j, 2);
        }
    }

    public void seekTo(int i) {
        seekTo((i * this.duration) / 100);
    }

    public int getCurrentPosition() {
        return (int) (this.presentationTimeUs / 1000);
    }

    public int getDuration() {
        return (int) (this.duration / 1000);
    }

    public boolean isPlaying() {
        return this.state.isPlaying();
    }

    public int getPlayState() {
        return this.state.get();
    }

    private synchronized void waitPlay() {
        while (this.state.get() == 2) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:45:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void initMediaDecode() {
        /*
            Method dump skipped, instructions count: 341
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wifiled.musiclib.player.MediaPlayer.initMediaDecode():void");
    }

    public void initAudioTrack() {
        int i = this.channels == 1 ? 4 : 12;
        AudioTrack audioTrack = new AudioTrack(this.mStreamType, this.sampleRate, i, 2, AudioTrack.getMinBufferSize(this.sampleRate, i, 2), 1);
        this.audioTrack = audioTrack;
        audioTrack.play();
        this.extractor.selectTrack(0);
        OnPreparedListener onPreparedListener = this.onPreparedListener;
        if (onPreparedListener != null) {
            onPreparedListener.onPrepared(this);
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        OnErrorListener onErrorListener;
        int i;
        Process.setThreadPriority(-19);
        this.state.set(3);
        boolean z = false;
        int i2 = 0;
        boolean z2 = false;
        while (!z && i2 < 10 && !this.stop) {
            waitPlay();
            i2++;
            if (!z2) {
                int dequeueInputBuffer = this.codec.dequeueInputBuffer(1000L);
                if (dequeueInputBuffer < 0) {
                    Log.e(LOG_TAG, "inputBufIndex " + dequeueInputBuffer);
                } else {
                    int readSampleData = this.extractor.readSampleData(this.decodeInputBuffers[dequeueInputBuffer], 0);
                    if (readSampleData <= 0) {
                        Log.d(LOG_TAG, "saw input EOS. Stopping playback");
                        this.presentationTimeUs = this.duration;
                        i = 0;
                        z2 = true;
                    } else {
                        this.presentationTimeUs = this.extractor.getSampleTime();
                        i = readSampleData;
                    }
                    this.codec.queueInputBuffer(dequeueInputBuffer, 0, i, this.presentationTimeUs, z2 ? 4 : 0);
                    if (!z2) {
                        this.extractor.advance();
                    }
                }
            }
            int dequeueOutputBuffer = this.codec.dequeueOutputBuffer(this.decodeBufferInfo, 1000L);
            if (dequeueOutputBuffer >= 0) {
                if (this.decodeBufferInfo.size > 0) {
                    i2 = 0;
                }
                ByteBuffer byteBuffer = this.decodeOutputBuffers[dequeueOutputBuffer];
                int i3 = this.decodeBufferInfo.size;
                byte[] bArr = new byte[i3];
                byteBuffer.get(bArr);
                byteBuffer.clear();
                if (i3 > 0) {
                    this.audioTrack.write(bArr, 0, i3);
                    if (this.onDataCaptureListener != null && this.isRhythming) {
                        int i4 = i3 / 4;
                        short[] sArr = new short[i4];
                        for (int i5 = 0; i5 < i3; i5 += 4) {
                            int i6 = i5 / 4;
                            if (i6 < i4) {
                                short s = (short) (bArr[i5] & UByte.MAX_VALUE);
                                sArr[i6] = s;
                                sArr[i6] = (short) (s | ((short) ((bArr[i5 + 1] & UByte.MAX_VALUE) << 8)));
                            }
                        }
                        if (System.currentTimeMillis() - this.lastTime > 50) {
                            this.lastTime = System.currentTimeMillis();
                            this.onDataCaptureListener.onWaveDataCapture(sArr, bArr, this.sampleRate);
                        }
                    }
                }
                this.codec.releaseOutputBuffer(dequeueOutputBuffer, false);
                if ((this.decodeBufferInfo.flags & 4) != 0) {
                    Log.d(LOG_TAG, "saw output EOS.");
                    z = true;
                }
            } else if (dequeueOutputBuffer == -3) {
                this.decodeOutputBuffers = this.codec.getOutputBuffers();
                Log.d(LOG_TAG, "output buffers have changed.");
            } else if (dequeueOutputBuffer == -2) {
                Log.d(LOG_TAG, "output format has changed to " + this.codec.getOutputFormat());
            } else {
                Log.d(LOG_TAG, "dequeueOutputBuffer returned " + dequeueOutputBuffer);
            }
        }
        OnCompletionListener onCompletionListener = this.onCompletionListener;
        if (onCompletionListener != null && this.presentationTimeUs == this.duration) {
            onCompletionListener.onCompletion(this);
        }
        Log.d(LOG_TAG, "stopping...");
        this.state.set(4);
        this.stop = true;
        if (i2 >= 10 && (onErrorListener = this.onErrorListener) != null) {
            onErrorListener.onError(this, "noOutputCounter more than the noOutputCounterLimit");
        }
        release();
    }

    public void setRhythming(boolean z) {
        this.isRhythming = z;
    }

    public void release() {
        MediaCodec mediaCodec = this.codec;
        if (mediaCodec != null) {
            mediaCodec.stop();
            this.codec.release();
            this.codec = null;
        }
        AudioTrack audioTrack = this.audioTrack;
        if (audioTrack != null) {
            audioTrack.flush();
            this.audioTrack.release();
            this.audioTrack = null;
        }
        this.sourcePath = null;
        this.sourceUri = null;
        this.sourceRawResId = -1;
        this.mime = null;
        this.sampleRate = 0;
        this.channels = 0;
        this.bitrate = 0;
        this.presentationTimeUs = 0L;
        this.duration = 0L;
    }

    public static String listCodecs() {
        int codecCount = MediaCodecList.getCodecCount();
        String str = "";
        int i = 0;
        while (i < codecCount) {
            MediaCodecInfo codecInfoAt = MediaCodecList.getCodecInfoAt(i);
            String name = codecInfoAt.getName();
            codecInfoAt.isEncoder();
            String str2 = "";
            for (String str3 : codecInfoAt.getSupportedTypes()) {
                str2 = str2 + str3 + " ";
            }
            i++;
            str = str + i + ". " + name + " " + str2 + "\n\n";
        }
        return str;
    }
}
