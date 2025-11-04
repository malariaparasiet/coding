package com.wifiled.ipixels.ui.projection;

import android.app.Activity;
import android.media.Image;
import android.media.MediaFormat;
import androidx.constraintlayout.motion.widget.Key;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.LifecycleOwnerKt;
import com.wifiled.baselib.utils.LogUtils;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.UtilsExtensionKt;
import com.wifiled.ipixels.core.CoreDecoder;
import com.wifiled.ipixels.ui.projection.VideoDecoder;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: VideoTrimActivity.kt */
@Metadata(d1 = {"\u0000/\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0018\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0012\u0010\t\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000eH\u0016¨\u0006\u000f"}, d2 = {"com/wifiled/ipixels/ui/projection/VideoTrimActivity$start$1", "Lcom/wifiled/ipixels/ui/projection/VideoDecoder$CodecCallback;", "onFinish", "", "onFrame", "image", "Landroid/media/Image;", Key.ROTATION, "", "onError", NotificationCompat.CATEGORY_MESSAGE, "", "onStart", "mediaFormat", "Landroid/media/MediaFormat;", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class VideoTrimActivity$start$1 implements VideoDecoder.CodecCallback {
    final /* synthetic */ String $path;
    final /* synthetic */ VideoTrimActivity this$0;

    VideoTrimActivity$start$1(VideoTrimActivity videoTrimActivity, String str) {
        this.this$0 = videoTrimActivity;
        this.$path = str;
    }

    @Override // com.wifiled.ipixels.ui.projection.VideoDecoder.CodecCallback
    public void onFinish() {
        String str;
        long j;
        str = this.this$0.TAG;
        long currentTimeMillis = System.currentTimeMillis();
        j = this.this$0.startTime;
        LogUtils.logi(str + ">>>[onFinish]:耗时::: " + (currentTimeMillis - j) + "ms", new Object[0]);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this.this$0), Dispatchers.getIO(), null, new VideoTrimActivity$start$1$onFinish$1(this.this$0, this.$path, null), 2, null);
    }

    @Override // com.wifiled.ipixels.ui.projection.VideoDecoder.CodecCallback
    public void onFrame(Image image, int rotation) {
        Intrinsics.checkNotNullParameter(image, "image");
        this.this$0.mergeBGRData(CoreDecoder.INSTANCE.videoDecode(image, rotation));
    }

    @Override // com.wifiled.ipixels.ui.projection.VideoDecoder.CodecCallback
    public void onError(String msg) {
        final VideoTrimActivity videoTrimActivity = this.this$0;
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.projection.VideoTrimActivity$start$1$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit onError$lambda$0;
                onError$lambda$0 = VideoTrimActivity$start$1.onError$lambda$0(VideoTrimActivity.this);
                return onError$lambda$0;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onError$lambda$0(VideoTrimActivity videoTrimActivity) {
        String str;
        str = videoTrimActivity.TAG;
        LogUtils.logi(str + ">>>[onError]:", new Object[0]);
        UtilsExtensionKt.showLoadingDialog$default((Activity) videoTrimActivity, false, (String) null, false, 6, (Object) null);
        return Unit.INSTANCE;
    }

    @Override // com.wifiled.ipixels.ui.projection.VideoDecoder.CodecCallback
    public void onStart(MediaFormat mediaFormat) {
        String str;
        int i;
        int length_bgr_frame;
        Intrinsics.checkNotNullParameter(mediaFormat, "mediaFormat");
        VideoTrimActivity videoTrimActivity = this.this$0;
        String string = videoTrimActivity.getString(R.string.msg_import);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        UtilsExtensionKt.showLoadingDialog$default((Activity) videoTrimActivity, false, string, false, 5, (Object) null);
        this.this$0.startTime = System.currentTimeMillis();
        str = this.this$0.TAG;
        LogUtils.logi(str + ">>>[onStart]: " + mediaFormat, new Object[0]);
        try {
            i = mediaFormat.getInteger("frame-count");
        } catch (Exception e) {
            e.printStackTrace();
            i = 10;
        }
        VideoTrimActivity videoTrimActivity2 = this.this$0;
        length_bgr_frame = videoTrimActivity2.getLENGTH_BGR_FRAME();
        videoTrimActivity2.bgrData = new byte[(i / 2) * length_bgr_frame];
    }
}
