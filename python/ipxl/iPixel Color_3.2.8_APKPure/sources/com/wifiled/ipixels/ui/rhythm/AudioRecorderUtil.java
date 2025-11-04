package com.wifiled.ipixels.ui.rhythm;

import android.media.AudioRecord;
import android.media.MediaPlayer;
import android.media.audiofx.Visualizer;
import android.util.Log;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.jieli.jl_bt_ota.constant.BluetoothConstant;
import com.wifiled.ipixels.ui.rhythm.AudioRecorderUtil;
import com.wifiled.ipixels.utils.ThreadPoolUtil;
import com.wifiled.ipixels.utils.TimeHelper;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt;

/* compiled from: AudioRecorderUtil.kt */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0017\n\u0002\b\u0003\u0018\u0000 12\u00020\u0001:\u000212B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010&\u001a\u00020'J\b\u0010(\u001a\u00020'H\u0007J\u000e\u0010)\u001a\u00020'2\u0006\u0010*\u001a\u00020\u0005J\u0006\u0010+\u001a\u00020'J\u0006\u0010,\u001a\u00020'J\u000e\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u000200R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u0007X\u0082D¢\u0006\u0004\n\u0002\b\bR\u0014\u0010\t\u001a\u00020\nX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u001bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001c\u0010 \u001a\u0004\u0018\u00010!X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%¨\u00063"}, d2 = {"Lcom/wifiled/ipixels/ui/rhythm/AudioRecorderUtil;", "", "<init>", "()V", "mLister", "Lcom/wifiled/ipixels/ui/rhythm/AudioRecorderUtil$onMicDbListener;", "TAG", "", "TAG$1", "SAMPLE_RATE_IN_HZ", "", "getSAMPLE_RATE_IN_HZ", "()I", "BUFFER_SIZE", "getBUFFER_SIZE", "mAudioRecord", "Landroid/media/AudioRecord;", "getMAudioRecord", "()Landroid/media/AudioRecord;", "setMAudioRecord", "(Landroid/media/AudioRecord;)V", "isGetVoiceRun", "", "()Z", "setGetVoiceRun", "(Z)V", "mLock", "Ljava/lang/Object;", "getMLock", "()Ljava/lang/Object;", "setMLock", "(Ljava/lang/Object;)V", "visualizer", "Landroid/media/audiofx/Visualizer;", "getVisualizer", "()Landroid/media/audiofx/Visualizer;", "setVisualizer", "(Landroid/media/audiofx/Visualizer;)V", "runRhythmSpectrum", "", "runOnNoiseLevel", "registMicDbListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "unregistMicDbListener", "release", "toByteArray", "", "src", "", "Companion", "onMicDbListener", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AudioRecorderUtil {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String TAG = "AudioRecorderUtil";
    private static AudioRecorderUtil instance;
    private boolean isGetVoiceRun;
    private AudioRecord mAudioRecord;
    private onMicDbListener mLister;
    private Visualizer visualizer;

    /* renamed from: TAG$1, reason: from kotlin metadata */
    private final String TAG = "AudioRecord";
    private final int SAMPLE_RATE_IN_HZ = BluetoothConstant.DEFAULT_SCAN_TIMEOUT;
    private final int BUFFER_SIZE = AudioRecord.getMinBufferSize(BluetoothConstant.DEFAULT_SCAN_TIMEOUT, 1, 2);
    private Object mLock = new Object();

    /* compiled from: AudioRecorderUtil.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&¨\u0006\tÀ\u0006\u0003"}, d2 = {"Lcom/wifiled/ipixels/ui/rhythm/AudioRecorderUtil$onMicDbListener;", "", "onCallBackMicDb", "", "volume", "", "onDataCapture", "data", "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface onMicDbListener {
        void onCallBackMicDb(double volume);

        void onDataCapture(byte[] data);
    }

    /* compiled from: AudioRecorderUtil.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\n\u001a\u00020\u0007H\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00078BX\u0082\u000e¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u000b"}, d2 = {"Lcom/wifiled/ipixels/ui/rhythm/AudioRecorderUtil$Companion;", "", "<init>", "()V", "TAG", "", "instance", "Lcom/wifiled/ipixels/ui/rhythm/AudioRecorderUtil;", "getInstance", "()Lcom/wifiled/ipixels/ui/rhythm/AudioRecorderUtil;", "get", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        private final AudioRecorderUtil getInstance() {
            if (AudioRecorderUtil.instance == null) {
                AudioRecorderUtil.instance = new AudioRecorderUtil();
            }
            return AudioRecorderUtil.instance;
        }

        @JvmStatic
        public final synchronized AudioRecorderUtil get() {
            AudioRecorderUtil companion;
            companion = getInstance();
            Intrinsics.checkNotNull(companion);
            return companion;
        }
    }

    @JvmStatic
    public static final synchronized AudioRecorderUtil get() {
        AudioRecorderUtil audioRecorderUtil;
        synchronized (AudioRecorderUtil.class) {
            audioRecorderUtil = INSTANCE.get();
        }
        return audioRecorderUtil;
    }

    public final int getSAMPLE_RATE_IN_HZ() {
        return this.SAMPLE_RATE_IN_HZ;
    }

    public final int getBUFFER_SIZE() {
        return this.BUFFER_SIZE;
    }

    public final AudioRecord getMAudioRecord() {
        return this.mAudioRecord;
    }

    public final void setMAudioRecord(AudioRecord audioRecord) {
        this.mAudioRecord = audioRecord;
    }

    /* renamed from: isGetVoiceRun, reason: from getter */
    public final boolean getIsGetVoiceRun() {
        return this.isGetVoiceRun;
    }

    public final void setGetVoiceRun(boolean z) {
        this.isGetVoiceRun = z;
    }

    public final Object getMLock() {
        return this.mLock;
    }

    public final void setMLock(Object obj) {
        Intrinsics.checkNotNullParameter(obj, "<set-?>");
        this.mLock = obj;
    }

    public final Visualizer getVisualizer() {
        return this.visualizer;
    }

    public final void setVisualizer(Visualizer visualizer) {
        this.visualizer = visualizer;
    }

    public final void runRhythmSpectrum() {
        Visualizer visualizer = new Visualizer(new MediaPlayer().getAudioSessionId());
        this.visualizer = visualizer;
        visualizer.setCaptureSize(Visualizer.getCaptureSizeRange()[0]);
        Visualizer visualizer2 = this.visualizer;
        if (visualizer2 != null) {
            visualizer2.setDataCaptureListener(new Visualizer.OnDataCaptureListener() { // from class: com.wifiled.ipixels.ui.rhythm.AudioRecorderUtil$runRhythmSpectrum$1
                @Override // android.media.audiofx.Visualizer.OnDataCaptureListener
                public void onWaveFormDataCapture(Visualizer visualizer3, byte[] waveform, int samplingRate) {
                }

                @Override // android.media.audiofx.Visualizer.OnDataCaptureListener
                public void onFftDataCapture(Visualizer visualizer3, byte[] fft, int samplingRate) {
                    AudioRecorderUtil.onMicDbListener onmicdblistener;
                    byte[] bArr = new byte[1];
                    Integer valueOf = fft != null ? Integer.valueOf(fft.length) : null;
                    Intrinsics.checkNotNull(valueOf);
                    bArr[0] = (byte) ((valueOf.intValue() / 2) + 1);
                    bArr[0] = fft[1];
                    IntProgression step = RangesKt.step(RangesKt.until(2, 18), 2);
                    int first = step.getFirst();
                    int last = step.getLast();
                    int step2 = step.getStep();
                    if ((step2 > 0 && first <= last) || (step2 < 0 && last <= first)) {
                        int i = 1;
                        while (true) {
                            bArr[i] = (byte) Math.hypot(fft[first], fft[first + 1]);
                            i++;
                            if (first == last) {
                                break;
                            } else {
                                first += step2;
                            }
                        }
                    }
                    onmicdblistener = AudioRecorderUtil.this.mLister;
                    if (onmicdblistener != null) {
                        onmicdblistener.onDataCapture(fft);
                    }
                }
            }, Visualizer.getMaxCaptureRate() / 2, false, true);
        }
    }

    public final void runOnNoiseLevel() {
        if (this.isGetVoiceRun) {
            Log.e(this.TAG, "还在录着呢");
            return;
        }
        this.mAudioRecord = new AudioRecord(1, this.SAMPLE_RATE_IN_HZ, 1, 2, this.BUFFER_SIZE);
        this.isGetVoiceRun = true;
        ThreadPoolUtil.execute(new Runnable() { // from class: com.wifiled.ipixels.ui.rhythm.AudioRecorderUtil$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                AudioRecorderUtil.runOnNoiseLevel$lambda$0(AudioRecorderUtil.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void runOnNoiseLevel$lambda$0(AudioRecorderUtil audioRecorderUtil) {
        onMicDbListener onmicdblistener;
        try {
            AudioRecord audioRecord = audioRecorderUtil.mAudioRecord;
            Intrinsics.checkNotNull(audioRecord);
            audioRecord.startRecording();
            int i = audioRecorderUtil.BUFFER_SIZE;
            short[] sArr = new short[i];
            while (audioRecorderUtil.isGetVoiceRun) {
                AudioRecord audioRecord2 = audioRecorderUtil.mAudioRecord;
                Intrinsics.checkNotNull(audioRecord2);
                int read = audioRecord2.read(sArr, 0, audioRecorderUtil.BUFFER_SIZE);
                long j = 0;
                for (int i2 = 0; i2 < i; i2++) {
                    short s = sArr[i2];
                    j += s * s;
                }
                int i3 = 1;
                int i4 = (i / 2) + 1;
                byte[] bArr = new byte[i4];
                bArr[0] = (byte) (sArr[1] & 255);
                IntProgression step = RangesKt.step(RangesKt.until(2, i4), 8);
                int first = step.getFirst();
                int last = step.getLast();
                int step2 = step.getStep();
                if ((step2 > 0 && first <= last) || (step2 < 0 && last <= first)) {
                    while (true) {
                        bArr[i3] = (byte) Math.hypot(sArr[first], sArr[first + 1]);
                        i3++;
                        if (first == last) {
                            break;
                        } else {
                            first += step2;
                        }
                    }
                }
                if (TimeHelper.INSTANCE.allowSend(100) && (onmicdblistener = audioRecorderUtil.mLister) != null) {
                    onmicdblistener.onDataCapture(bArr);
                }
                double log10 = 10 * Math.log10(j / read);
                onMicDbListener onmicdblistener2 = audioRecorderUtil.mLister;
                if (onmicdblistener2 != null) {
                    onmicdblistener2.onCallBackMicDb(log10);
                }
            }
            AudioRecord audioRecord3 = audioRecorderUtil.mAudioRecord;
            Intrinsics.checkNotNull(audioRecord3);
            audioRecord3.stop();
            AudioRecord audioRecord4 = audioRecorderUtil.mAudioRecord;
            Intrinsics.checkNotNull(audioRecord4);
            audioRecord4.release();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        audioRecorderUtil.mAudioRecord = null;
    }

    public final void registMicDbListener(onMicDbListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.mLister = listener;
    }

    public final void unregistMicDbListener() {
        this.mLister = null;
        release();
    }

    public final void release() {
        this.isGetVoiceRun = false;
    }

    public final byte[] toByteArray(short[] src) {
        Intrinsics.checkNotNullParameter(src, "src");
        int length = src.length;
        byte[] bArr = new byte[length << 1];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            short s = src[i];
            bArr[i2] = (byte) (s >> 8);
            bArr[i2 + 1] = (byte) s;
        }
        return bArr;
    }
}
