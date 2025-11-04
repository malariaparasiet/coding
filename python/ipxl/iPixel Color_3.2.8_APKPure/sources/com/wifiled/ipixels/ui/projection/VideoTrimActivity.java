package com.wifiled.ipixels.ui.projection;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;
import androidx.lifecycle.LifecycleOwnerKt;
import com.google.android.gms.common.internal.ImagesContract;
import com.wifiled.baselib.base.BaseActivity;
import com.wifiled.baselib.utils.ToastUtil;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.UtilsExtensionKt;
import com.wifiled.ipixels.db.AppDatabase;
import com.wifiled.ipixels.db.GifDao;
import com.wifiled.ipixels.view.customview.CustomImageView;
import com.wifiled.ipixels.view.video_clip.VideoTrimCallback;
import com.wifiled.ipixels.view.video_clip.VideoTrimmerView;
import com.wifiled.musiclib.player.constant.DbFinal;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: VideoTrimActivity.kt */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u0000 92\u00020\u00012\u00020\u0002:\u00019B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010\u001b\u001a\u00020\u001cH\u0014J\b\u0010\u001d\u001a\u00020\u0010H\u0014J\b\u0010\u001e\u001a\u00020\u001cH\u0014J\b\u0010\u001f\u001a\u00020\u001cH\u0002J\b\u0010 \u001a\u00020\u001cH\u0014J\u0010\u0010#\u001a\u00020\u001c2\u0006\u0010$\u001a\u00020%H\u0002J\u0010\u00101\u001a\u00020\u001c2\u0006\u00102\u001a\u00020.H\u0002J\u0012\u00103\u001a\u00020\u001c2\b\u00104\u001a\u0004\u0018\u00010%H\u0016J\b\u00105\u001a\u00020\u001cH\u0016J\b\u00106\u001a\u00020\u001cH\u0016J\b\u00107\u001a\u00020\u001cH\u0014J\b\u00108\u001a\u00020\u001cH\u0014R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u000e\u0010\r\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u000f\u001a\u00020\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\f\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0015X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010&\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u0012\"\u0004\b(\u0010)R\u0014\u0010*\u001a\u00020\u0010X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u0012R\u0017\u0010,\u001a\b\u0012\u0004\u0012\u00020.0-¢\u0006\b\n\u0000\u001a\u0004\b/\u00100¨\u0006:"}, d2 = {"Lcom/wifiled/ipixels/ui/projection/VideoTrimActivity;", "Lcom/wifiled/baselib/base/BaseActivity;", "Lcom/wifiled/ipixels/view/video_clip/VideoTrimCallback;", "<init>", "()V", "videoDecoder", "Lcom/wifiled/ipixels/ui/projection/VideoDecoder;", "gifDao", "Lcom/wifiled/ipixels/db/GifDao;", "getGifDao", "()Lcom/wifiled/ipixels/db/GifDao;", "gifDao$delegate", "Lkotlin/Lazy;", "bgrData", "", "LENGTH_BGR_FRAME", "", "getLENGTH_BGR_FRAME", "()I", "LENGTH_BGR_FRAME$delegate", "iv_right", "Lcom/wifiled/ipixels/view/customview/CustomImageView;", "iv_back", "tv_title", "Landroid/widget/TextView;", "trimmerView", "Lcom/wifiled/ipixels/view/video_clip/VideoTrimmerView;", "initView", "", "layoutId", "bindData", "initToolbar", "bindListener", "startTime", "", "start", DbFinal.LOCAL_PATH, "", "index", "getIndex", "setIndex", "(I)V", "iStep", "getIStep", "bitmaps", "", "Landroid/graphics/Bitmap;", "getBitmaps", "()Ljava/util/List;", "mergeBGRData", "bitmap", "onFinishTrim", ImagesContract.URL, "onStartTrim", "onCancel", "onPause", "onDestroy", "Companion", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class VideoTrimActivity extends BaseActivity implements VideoTrimCallback {
    public static final int REQUEST_CAMERA_VIDEO = 100;
    private byte[] bgrData;
    private int index;
    private CustomImageView iv_back;
    private CustomImageView iv_right;
    private long startTime;
    private VideoTrimmerView trimmerView;
    private TextView tv_title;
    private VideoDecoder videoDecoder;

    /* renamed from: gifDao$delegate, reason: from kotlin metadata */
    private final Lazy gifDao = LazyKt.lazy(new Function0() { // from class: com.wifiled.ipixels.ui.projection.VideoTrimActivity$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            GifDao gifDao_delegate$lambda$0;
            gifDao_delegate$lambda$0 = VideoTrimActivity.gifDao_delegate$lambda$0();
            return gifDao_delegate$lambda$0;
        }
    });

    /* renamed from: LENGTH_BGR_FRAME$delegate, reason: from kotlin metadata */
    private final Lazy LENGTH_BGR_FRAME = LazyKt.lazy(new Function0() { // from class: com.wifiled.ipixels.ui.projection.VideoTrimActivity$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            int LENGTH_BGR_FRAME_delegate$lambda$1;
            LENGTH_BGR_FRAME_delegate$lambda$1 = VideoTrimActivity.LENGTH_BGR_FRAME_delegate$lambda$1();
            return Integer.valueOf(LENGTH_BGR_FRAME_delegate$lambda$1);
        }
    });
    private final int iStep = 5;
    private final List<Bitmap> bitmaps = new ArrayList();

    /* JADX INFO: Access modifiers changed from: private */
    public final GifDao getGifDao() {
        return (GifDao) this.gifDao.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final GifDao gifDao_delegate$lambda$0() {
        return AppDatabase.INSTANCE.getDatabase().gifDao();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int LENGTH_BGR_FRAME_delegate$lambda$1() {
        return AppConfig.INSTANCE.getLedFrameSize();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getLENGTH_BGR_FRAME() {
        return ((Number) this.LENGTH_BGR_FRAME.getValue()).intValue();
    }

    @Override // com.wifiled.baselib.base.BaseActivity
    protected void initView() {
        super.initView();
        View findViewById = findViewById(R.id.iv_right);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.iv_right = (CustomImageView) findViewById;
        View findViewById2 = findViewById(R.id.iv_back);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.iv_back = (CustomImageView) findViewById2;
        View findViewById3 = findViewById(R.id.tv_title);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.tv_title = (TextView) findViewById3;
        View findViewById4 = findViewById(R.id.trimmerView);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
        this.trimmerView = (VideoTrimmerView) findViewById4;
    }

    @Override // com.wifiled.baselib.base.BaseActivity
    protected int layoutId() {
        getWindow().setFlags(128, 128);
        return R.layout.activity_video_trim;
    }

    @Override // com.wifiled.baselib.base.BaseActivity
    protected void bindData() {
        initToolbar();
        this.videoDecoder = new VideoDecoder();
        VideoTrimmerView videoTrimmerView = this.trimmerView;
        VideoTrimmerView videoTrimmerView2 = null;
        if (videoTrimmerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("trimmerView");
            videoTrimmerView = null;
        }
        videoTrimmerView.setOnTrimVideoListener(this);
        String stringExtra = getIntent().getStringExtra(DbFinal.LOCAL_PATH);
        if (stringExtra != null) {
            VideoTrimmerView videoTrimmerView3 = this.trimmerView;
            if (videoTrimmerView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("trimmerView");
            } else {
                videoTrimmerView2 = videoTrimmerView3;
            }
            videoTrimmerView2.initVideoByURI(Uri.parse(stringExtra));
        }
    }

    private final void initToolbar() {
        TextView textView = this.tv_title;
        CustomImageView customImageView = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_title");
            textView = null;
        }
        textView.setText(getString(R.string.tailore));
        CustomImageView customImageView2 = this.iv_back;
        if (customImageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_back");
            customImageView2 = null;
        }
        customImageView2.setBackgroundResource(R.drawable.projection_back);
        CustomImageView customImageView3 = this.iv_right;
        if (customImageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_right");
        } else {
            customImageView = customImageView3;
        }
        customImageView.setBackgroundResource(R.mipmap.icon_ok);
    }

    @Override // com.wifiled.baselib.base.BaseActivity
    protected void bindListener() {
        super.bindListener();
        CustomImageView customImageView = this.iv_right;
        CustomImageView customImageView2 = null;
        if (customImageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_right");
            customImageView = null;
        }
        customImageView.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.projection.VideoTrimActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VideoTrimActivity.bindListener$lambda$3(VideoTrimActivity.this, view);
            }
        });
        CustomImageView customImageView3 = this.iv_back;
        if (customImageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_back");
        } else {
            customImageView2 = customImageView3;
        }
        customImageView2.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.projection.VideoTrimActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VideoTrimActivity.bindListener$lambda$4(VideoTrimActivity.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$3(VideoTrimActivity videoTrimActivity, View view) {
        VideoTrimmerView videoTrimmerView = videoTrimActivity.trimmerView;
        if (videoTrimmerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("trimmerView");
            videoTrimmerView = null;
        }
        videoTrimmerView.startCrop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$4(VideoTrimActivity videoTrimActivity, View view) {
        videoTrimActivity.toActivity(VideoActivity.class);
        videoTrimActivity.finish();
    }

    private final void start(String path) {
        try {
            VideoDecoder videoDecoder = this.videoDecoder;
            if (videoDecoder == null) {
                Intrinsics.throwUninitializedPropertyAccessException("videoDecoder");
                videoDecoder = null;
            }
            videoDecoder.start(path, new VideoTrimActivity$start$1(this, path));
        } catch (IllegalStateException e) {
            e.printStackTrace();
            UtilsExtensionKt.showLoadingDialog$default((Activity) this, false, (String) null, false, 6, (Object) null);
            ToastUtil.show(getString(R.string.fail));
        }
    }

    public final int getIndex() {
        return this.index;
    }

    public final void setIndex(int i) {
        this.index = i;
    }

    public final int getIStep() {
        return this.iStep;
    }

    public final List<Bitmap> getBitmaps() {
        return this.bitmaps;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void mergeBGRData(Bitmap bitmap) {
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), null, new VideoTrimActivity$mergeBGRData$1(this, bitmap, null), 2, null);
    }

    @Override // com.wifiled.ipixels.view.video_clip.VideoTrimCallback
    public void onFinishTrim(final String url) {
        UtilsExtensionKt.showLoadingDialog$default((Activity) this, false, (String) null, false, 6, (Object) null);
        toast(getString(R.string.success));
        UtilsExtensionKt.setTimeout$default(0, 1000L, new Function0() { // from class: com.wifiled.ipixels.ui.projection.VideoTrimActivity$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit onFinishTrim$lambda$6;
                onFinishTrim$lambda$6 = VideoTrimActivity.onFinishTrim$lambda$6(url, this);
                return onFinishTrim$lambda$6;
            }
        }, 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onFinishTrim$lambda$6(String str, VideoTrimActivity videoTrimActivity) {
        if (str != null) {
            videoTrimActivity.start(str);
        }
        return Unit.INSTANCE;
    }

    @Override // com.wifiled.ipixels.view.video_clip.VideoTrimCallback
    public void onStartTrim() {
        String string = getString(R.string.tailoring);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        UtilsExtensionKt.showLoadingDialog$default((Activity) this, false, string, false, 5, (Object) null);
    }

    @Override // com.wifiled.ipixels.view.video_clip.VideoTrimCallback
    public void onCancel() {
        VideoTrimmerView videoTrimmerView = this.trimmerView;
        if (videoTrimmerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("trimmerView");
            videoTrimmerView = null;
        }
        videoTrimmerView.onDestroy();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        VideoTrimmerView videoTrimmerView = this.trimmerView;
        VideoTrimmerView videoTrimmerView2 = null;
        if (videoTrimmerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("trimmerView");
            videoTrimmerView = null;
        }
        videoTrimmerView.onVideoPause();
        VideoTrimmerView videoTrimmerView3 = this.trimmerView;
        if (videoTrimmerView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("trimmerView");
        } else {
            videoTrimmerView2 = videoTrimmerView3;
        }
        videoTrimmerView2.setRestoreState(true);
    }

    @Override // com.wifiled.baselib.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        VideoTrimmerView videoTrimmerView = this.trimmerView;
        if (videoTrimmerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("trimmerView");
            videoTrimmerView = null;
        }
        videoTrimmerView.onDestroy();
    }
}
