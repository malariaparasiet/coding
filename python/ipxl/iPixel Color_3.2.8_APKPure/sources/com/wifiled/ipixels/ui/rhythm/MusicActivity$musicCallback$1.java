package com.wifiled.ipixels.ui.rhythm;

import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.UtilsExtensionKt;
import com.wifiled.ipixels.core.MusicCore;
import com.wifiled.ipixels.core.SendCore;
import com.wifiled.ipixels.utils.TimeHelper;
import com.wifiled.ipixels.view.RhythmLedView;
import com.wifiled.musiclib.player.constant.PlayerFinal;
import com.wifiled.musiclib.vo.MusicVO;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MusicActivity.kt */
@Metadata(d1 = {"\u00003\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005H\u0016J(\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0016J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J\u0016\u0010\u000f\u001a\u00020\u00032\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0016J\u0010\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0015H\u0016¨\u0006\u0016"}, d2 = {"com/wifiled/ipixels/ui/rhythm/MusicActivity$musicCallback$1", "Lcom/wifiled/ipixels/core/MusicCore$MusicCallback;", "onStateChange", "", PlayerFinal.STATE, "", PlayerFinal.PLAYER_MODE, PlayerFinal.PLAYER_POSITION, "onSeekChange", "progress", "max", "time", "", "duration", "onModeChange", "onMusicScanResult", PlayerFinal.PLAYER_LIST, "", "Lcom/wifiled/musiclib/vo/MusicVO;", "onDataCapture", "data", "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MusicActivity$musicCallback$1 implements MusicCore.MusicCallback {
    final /* synthetic */ MusicActivity this$0;

    MusicActivity$musicCallback$1(MusicActivity musicActivity) {
        this.this$0 = musicActivity;
    }

    /* JADX WARN: Code restructure failed: missing block: B:36:0x00e1, code lost:
    
        if (r8 != 4) goto L72;
     */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0196  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x019a  */
    @Override // com.wifiled.ipixels.core.MusicCore.MusicCallback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onStateChange(int r8, int r9, int r10) {
        /*
            Method dump skipped, instructions count: 415
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wifiled.ipixels.ui.rhythm.MusicActivity$musicCallback$1.onStateChange(int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onStateChange$lambda$0(MusicActivity musicActivity) {
        ImageView imageView;
        imageView = musicActivity.iv_rhy_outside_image_bg;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_rhy_outside_image_bg");
            imageView = null;
        }
        UtilsExtensionKt.show(imageView);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onStateChange$lambda$1(MusicActivity musicActivity) {
        ImageView imageView;
        imageView = musicActivity.iv_rhy_outside_image_bg;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_rhy_outside_image_bg");
            imageView = null;
        }
        UtilsExtensionKt.invisible(imageView);
        return Unit.INSTANCE;
    }

    @Override // com.wifiled.ipixels.core.MusicCore.MusicCallback
    public void onSeekChange(int progress, int max, String time, String duration) {
        SeekBar seekBar;
        TextView textView;
        TextView textView2;
        Intrinsics.checkNotNullParameter(time, "time");
        Intrinsics.checkNotNullParameter(duration, "duration");
        if (max <= 0) {
            return;
        }
        seekBar = this.this$0.sb;
        TextView textView3 = null;
        if (seekBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sb");
            seekBar = null;
        }
        seekBar.setProgress((progress * 100) / max);
        textView = this.this$0.tv_sb_time;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_sb_time");
            textView = null;
        }
        textView.setText(time);
        int i = max / 1000;
        String valueOf = String.valueOf(i / 60);
        int i2 = i % 60;
        String valueOf2 = String.valueOf(i2);
        if (valueOf2.length() == 1) {
            valueOf2 = "0" + i2;
        }
        textView2 = this.this$0.tv_sb_duration;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_sb_duration");
        } else {
            textView3 = textView2;
        }
        textView3.setText(valueOf + ":" + valueOf2);
    }

    @Override // com.wifiled.ipixels.core.MusicCore.MusicCallback
    public void onModeChange(int mode) {
        ImageView imageView;
        ImageView imageView2;
        ImageView imageView3;
        ImageView imageView4 = null;
        if (mode == 0) {
            imageView = this.this$0.iv_mode;
            if (imageView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("iv_mode");
            } else {
                imageView4 = imageView;
            }
            imageView4.setImageResource(R.drawable.btn_random);
            return;
        }
        if (mode == 1) {
            imageView2 = this.this$0.iv_mode;
            if (imageView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("iv_mode");
            } else {
                imageView4 = imageView2;
            }
            imageView4.setImageResource(R.drawable.btn_single_cycle);
            return;
        }
        if (mode != 3) {
            return;
        }
        imageView3 = this.this$0.iv_mode;
        if (imageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_mode");
        } else {
            imageView4 = imageView3;
        }
        imageView4.setImageResource(R.drawable.btn_loop);
    }

    @Override // com.wifiled.ipixels.core.MusicCore.MusicCallback
    public void onMusicScanResult(List<? extends MusicVO> musicList) {
        Intrinsics.checkNotNullParameter(musicList, "musicList");
        this.this$0.startScanMusic(musicList);
    }

    @Override // com.wifiled.ipixels.core.MusicCore.MusicCallback
    public void onDataCapture(final byte[] data) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        Intrinsics.checkNotNullParameter(data, "data");
        if (TimeHelper.INSTANCE.allowSend(100)) {
            i = this.this$0.curSelectPosition;
            switch (i) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                    final MusicActivity musicActivity = this.this$0;
                    UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.rhythm.MusicActivity$musicCallback$1$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            Unit onDataCapture$lambda$2;
                            onDataCapture$lambda$2 = MusicActivity$musicCallback$1.onDataCapture$lambda$2(MusicActivity.this, data);
                            return onDataCapture$lambda$2;
                        }
                    });
                    byte[] copyOfRange = ArraysKt.copyOfRange(data, 0, 11);
                    SendCore sendCore = SendCore.INSTANCE;
                    i2 = this.this$0.curSelectPosition;
                    sendCore.sendRhythmChart(i2, copyOfRange);
                    break;
                case 5:
                case 6:
                    final MusicActivity musicActivity2 = this.this$0;
                    musicActivity2.runOnUiThread(new Runnable() { // from class: com.wifiled.ipixels.ui.rhythm.MusicActivity$musicCallback$1$$ExternalSyntheticLambda3
                        @Override // java.lang.Runnable
                        public final void run() {
                            MusicActivity$musicCallback$1.onDataCapture$lambda$3(MusicActivity.this);
                        }
                    });
                    this.this$0.setILevel(this.this$0.getILevel() + 1);
                    if (this.this$0.getILevel() > 7) {
                        this.this$0.setILevel(0);
                    }
                    i3 = this.this$0.curSelectPosition;
                    if (i3 == 5) {
                        i4 = this.this$0.curSelectPosition;
                        i5 = i4 - 4;
                    } else if (i3 != 6) {
                        i5 = -1;
                    } else {
                        i6 = this.this$0.curSelectPosition;
                        i5 = i6 - 6;
                    }
                    SendCore.INSTANCE.sendRhythm(this.this$0.getILevel(), i5);
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onDataCapture$lambda$2(MusicActivity musicActivity, byte[] bArr) {
        musicActivity.setRhyData(bArr);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onDataCapture$lambda$3(MusicActivity musicActivity) {
        RhythmLedView rhythmLedView;
        RhythmLedView rhythmLedView2;
        ImageView imageView;
        rhythmLedView = musicActivity.rhyledview_1;
        ImageView imageView2 = null;
        if (rhythmLedView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_1");
            rhythmLedView = null;
        }
        rhythmLedView.clearSelected();
        rhythmLedView2 = musicActivity.rhyledview_2;
        if (rhythmLedView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_2");
            rhythmLedView2 = null;
        }
        rhythmLedView2.clearSelected();
        imageView = musicActivity.iv_rhy_outside_image_bg;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_rhy_outside_image_bg");
        } else {
            imageView2 = imageView;
        }
        UtilsExtensionKt.invisible(imageView2);
    }
}
