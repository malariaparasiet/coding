package com.wifiled.ipixels.core;

import android.content.Context;
import android.util.Log;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import com.wifiled.baselib.utils.LogUtils;
import com.wifiled.blelibrary.ble.utils.ByteUtils;
import com.wifiled.ipixels.core.MusicCore;
import com.wifiled.musiclib.MusicManager;
import com.wifiled.musiclib.player.callback.MusicScanCallback;
import com.wifiled.musiclib.player.callback.OnDataCaptureListener;
import com.wifiled.musiclib.player.callback.PlayStateCallback;
import com.wifiled.musiclib.player.constant.PlayerFinal;
import com.wifiled.musiclib.utils.FftConvertUtils;
import com.wifiled.musiclib.vo.MusicVO;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MusicCore.kt */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001(B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aJ\u0016\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u00142\u0006\u0010\u001d\u001a\u00020\u0011J\u0016\u0010\u001e\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u00142\u0006\u0010\u001d\u001a\u00020\u0011J\u0006\u0010\u001f\u001a\u00020\u0016J\b\u0010 \u001a\u00020\u0018H\u0002J\b\u0010!\u001a\u00020\u0018H\u0002J\u0006\u0010\"\u001a\u00020\u0018J\b\u0010%\u001a\u00020\u000eH\u0002J\u0012\u0010&\u001a\u00020\u00182\b\b\u0001\u0010\u001c\u001a\u00020\u0014H\u0007J\u0006\u0010'\u001a\u00020\u0018R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R#\u0010\u0006\u001a\n \b*\u0004\u0018\u00010\u00070\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00110\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lcom/wifiled/ipixels/core/MusicCore;", "Landroidx/lifecycle/LifecycleObserver;", "<init>", "()V", "TAG", "", "musicManager", "Lcom/wifiled/musiclib/MusicManager;", "kotlin.jvm.PlatformType", "getMusicManager", "()Lcom/wifiled/musiclib/MusicManager;", "musicManager$delegate", "Lkotlin/Lazy;", "musicServiceInit", "", "musicCallbacks", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Lcom/wifiled/ipixels/core/MusicCore$MusicCallback;", "callbackMap", "", "Landroidx/lifecycle/LifecycleOwner;", "currentMusicPosition", "", "init", "", "context", "Landroid/content/Context;", "registerMusicCallback", "lifecycleOwner", "callback", "unRegisterMusicCallback", "getCurrentMusicPosition", "setPlayStateCallback", "setRhythmCallback", "startScanMusic", "lastSendTime", "", "allowSend", "onDestroy", "destroy", "MusicCallback", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MusicCore implements LifecycleObserver {
    public static final String TAG = "MusicCore";
    private static int currentMusicPosition;
    private static long lastSendTime;
    private static boolean musicServiceInit;
    public static final MusicCore INSTANCE = new MusicCore();

    /* renamed from: musicManager$delegate, reason: from kotlin metadata */
    private static final Lazy musicManager = LazyKt.lazy(new Function0() { // from class: com.wifiled.ipixels.core.MusicCore$$ExternalSyntheticLambda2
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            MusicManager musicManager2;
            musicManager2 = MusicManager.getInstance();
            return musicManager2;
        }
    });
    private static CopyOnWriteArrayList<MusicCallback> musicCallbacks = new CopyOnWriteArrayList<>();
    private static final Map<LifecycleOwner, MusicCallback> callbackMap = new LinkedHashMap();

    /* compiled from: MusicCore.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005H&J(\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH&J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005H&J\u0016\u0010\u000f\u001a\u00020\u00032\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H&J\u0010\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0015H&¨\u0006\u0016À\u0006\u0003"}, d2 = {"Lcom/wifiled/ipixels/core/MusicCore$MusicCallback;", "", "onStateChange", "", PlayerFinal.STATE, "", PlayerFinal.PLAYER_MODE, PlayerFinal.PLAYER_POSITION, "onSeekChange", "progress", "max", "time", "", "duration", "onModeChange", "onMusicScanResult", PlayerFinal.PLAYER_LIST, "", "Lcom/wifiled/musiclib/vo/MusicVO;", "onDataCapture", "data", "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface MusicCallback {
        void onDataCapture(byte[] data);

        void onModeChange(int mode);

        void onMusicScanResult(List<? extends MusicVO> musicList);

        void onSeekChange(int progress, int max, String time, String duration);

        void onStateChange(int state, int mode, int position);
    }

    private MusicCore() {
    }

    private final MusicManager getMusicManager() {
        return (MusicManager) musicManager.getValue();
    }

    public final void init(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Log.v("ruis", "MusicCore init musicServiceInit====" + musicServiceInit);
        if (musicServiceInit) {
            return;
        }
        MusicManager.getInstance().init(context);
        musicServiceInit = true;
        setRhythmCallback();
        setPlayStateCallback();
    }

    public final void registerMusicCallback(LifecycleOwner lifecycleOwner, MusicCallback callback) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        Intrinsics.checkNotNullParameter(callback, "callback");
        if (musicCallbacks.contains(callback)) {
            return;
        }
        musicCallbacks.add(callback);
        callbackMap.put(lifecycleOwner, callback);
        lifecycleOwner.getLifecycle().addObserver(this);
    }

    public final void unRegisterMusicCallback(LifecycleOwner lifecycleOwner, MusicCallback callback) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        Intrinsics.checkNotNullParameter(callback, "callback");
        if (musicCallbacks.contains(callback)) {
            getMusicManager().unsetRecordDataCallBack();
            musicCallbacks.remove(callback);
            callbackMap.remove(lifecycleOwner);
            lifecycleOwner.getLifecycle().removeObserver(this);
        }
    }

    public final int getCurrentMusicPosition() {
        return currentMusicPosition;
    }

    private final void setPlayStateCallback() {
        getMusicManager().setPlayStateCallback(new PlayStateCallback() { // from class: com.wifiled.ipixels.core.MusicCore$setPlayStateCallback$1
            @Override // com.wifiled.musiclib.player.callback.PlayStateCallback
            public void onStateChange(int state, int mode, int position) {
                CopyOnWriteArrayList copyOnWriteArrayList;
                MusicCore musicCore = MusicCore.INSTANCE;
                MusicCore.currentMusicPosition = position;
                copyOnWriteArrayList = MusicCore.musicCallbacks;
                Iterator it = copyOnWriteArrayList.iterator();
                while (it.hasNext()) {
                    ((MusicCore.MusicCallback) it.next()).onStateChange(state, mode, position);
                }
            }

            @Override // com.wifiled.musiclib.player.callback.PlayStateCallback
            public void onSeekChange(int progress, int max, String time, String duration) {
                CopyOnWriteArrayList copyOnWriteArrayList;
                Intrinsics.checkNotNullParameter(time, "time");
                Intrinsics.checkNotNullParameter(duration, "duration");
                copyOnWriteArrayList = MusicCore.musicCallbacks;
                Iterator it = copyOnWriteArrayList.iterator();
                while (it.hasNext()) {
                    ((MusicCore.MusicCallback) it.next()).onSeekChange(progress, max, time, duration);
                }
            }

            @Override // com.wifiled.musiclib.player.callback.PlayStateCallback
            public void onModeChange(int mode) {
                CopyOnWriteArrayList copyOnWriteArrayList;
                copyOnWriteArrayList = MusicCore.musicCallbacks;
                Iterator it = copyOnWriteArrayList.iterator();
                while (it.hasNext()) {
                    ((MusicCore.MusicCallback) it.next()).onModeChange(mode);
                }
            }
        });
    }

    private final void setRhythmCallback() {
        getMusicManager().setDataCaptureCallback(new OnDataCaptureListener() { // from class: com.wifiled.ipixels.core.MusicCore$$ExternalSyntheticLambda1
            @Override // com.wifiled.musiclib.player.callback.OnDataCaptureListener
            public final void onWaveDataCapture(short[] sArr, byte[] bArr, int i) {
                MusicCore.setRhythmCallback$lambda$2(sArr, bArr, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setRhythmCallback$lambda$2(short[] sArr, byte[] bArr, int i) {
        if (!INSTANCE.getMusicManager().isPlaying() || bArr.length < 128) {
            return;
        }
        byte[] fftData = FftConvertUtils.getInstance().getFftData(bArr);
        LogUtils.logi("MusicCore>>>[setRhythmCallback]: " + ByteUtils.toHexString(fftData), new Object[0]);
        for (MusicCallback musicCallback : musicCallbacks) {
            Intrinsics.checkNotNull(fftData);
            musicCallback.onDataCapture(fftData);
        }
    }

    public final void startScanMusic() {
        getMusicManager().startScanMusic(new MusicScanCallback() { // from class: com.wifiled.ipixels.core.MusicCore$$ExternalSyntheticLambda0
            @Override // com.wifiled.musiclib.player.callback.MusicScanCallback
            public final void onMusicScanResult(List list) {
                MusicCore.startScanMusic$lambda$4(list);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startScanMusic$lambda$4(List list) {
        for (MusicCallback musicCallback : musicCallbacks) {
            Intrinsics.checkNotNull(list);
            musicCallback.onMusicScanResult(list);
        }
    }

    private final boolean allowSend() {
        if (System.currentTimeMillis() - lastSendTime < 400) {
            return false;
        }
        lastSendTime = System.currentTimeMillis();
        return true;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public final void onDestroy(LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        Map<LifecycleOwner, MusicCallback> map = callbackMap;
        if (map.containsKey(lifecycleOwner)) {
            musicCallbacks.remove(map.remove(lifecycleOwner));
            lifecycleOwner.getLifecycle().removeObserver(this);
            LogUtils.logi("MusicCore>>>[onDestroy]: ", new Object[0]);
        }
    }

    public final void destroy() {
        musicCallbacks.clear();
        MusicManager.getInstance().destroy();
    }
}
