package com.wifiled.ipixels.ui.projection;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.PickVisualMediaRequestKt;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.google.android.gms.common.internal.ImagesContract;
import com.wifiled.baselib.base.BaseActivity;
import com.wifiled.baselib.base.recycleview.RecyclerAdapter;
import com.wifiled.baselib.utils.DialogUtils;
import com.wifiled.baselib.utils.LogUtils;
import com.wifiled.baselib.utils.SPUtils;
import com.wifiled.baselib.utils.ToastUtil;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.UtilsExtensionKt;
import com.wifiled.ipixels.core.SendCore;
import com.wifiled.ipixels.ui.adapter.IosDialogStyleAdapter;
import com.wifiled.ipixels.ui.channel.ChannelIndex;
import com.wifiled.ipixels.ui.channel.ChannelListItem;
import com.wifiled.ipixels.ui.text.vo.SendResultMsg;
import com.wifiled.ipixels.utils.FileUtil;
import com.wifiled.ipixels.utils.HandlerUtil;
import com.wifiled.ipixels.view.customview.CustomImageView;
import com.wifiled.ipixels.view.video_clip.ZVideoView;
import com.wifiled.musiclib.player.constant.DbFinal;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.greenrobot.eventbus.EventBus;

/* compiled from: VideoActivity.kt */
@Metadata(d1 = {"\u0000\u0081\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0012\n\u0002\b\u0002*\u0001*\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u001c\u001a\u00020\u001dH\u0014J\b\u0010\u001e\u001a\u00020\u001fH\u0014J\b\u0010 \u001a\u00020\u001fH\u0014J\b\u0010!\u001a\u00020\u001fH\u0014J\b\u0010\"\u001a\u00020\u001fH\u0014J\b\u0010#\u001a\u00020\u001fH\u0002J\u0010\u0010$\u001a\u00020\u001f2\u0006\u0010%\u001a\u00020&H\u0002J\b\u0010'\u001a\u00020\u001fH\u0002J\b\u0010(\u001a\u00020\u001fH\u0002J\b\u00100\u001a\u00020\u001fH\u0002J\u0010\u00103\u001a\u00020\u001f2\u0006\u0010%\u001a\u00020&H\u0002J\u0010\u00104\u001a\u00020\u001f2\u0006\u00105\u001a\u00020&H\u0002J\u0010\u00106\u001a\u00020\u001f2\u0006\u00107\u001a\u000208H\u0002J\b\u00109\u001a\u00020\u001fH\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082.¢\u0006\u0002\n\u0000R\u001c\u0010\u0018\u001a\u0010\u0012\f\u0012\n \u001b*\u0004\u0018\u00010\u001a0\u001a0\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010)\u001a\u00020*X\u0082\u0004¢\u0006\u0004\n\u0002\u0010+R\u001f\u0010,\u001a\u0010\u0012\f\u0012\n \u001b*\u0004\u0018\u00010-0-0\u0019¢\u0006\b\n\u0000\u001a\u0004\b.\u0010/R\u000e\u00101\u001a\u00020&X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020&X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006:"}, d2 = {"Lcom/wifiled/ipixels/ui/projection/VideoActivity;", "Lcom/wifiled/baselib/base/BaseActivity;", "<init>", "()V", "handler", "Landroid/os/Handler;", "mAdapter", "Lcom/wifiled/ipixels/ui/adapter/IosDialogStyleAdapter;", "", "actionRecyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "iv_right", "Lcom/wifiled/ipixels/view/customview/CustomImageView;", "iv_back", "tv_title", "Landroid/widget/TextView;", "title_bar", "Landroidx/constraintlayout/widget/ConstraintLayout;", "videoView", "Lcom/wifiled/ipixels/view/video_clip/ZVideoView;", "seekbar", "Landroid/widget/SeekBar;", "iv_preview", "Landroid/widget/ImageView;", "registForActivityResult", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "kotlin.jvm.PlatformType", "layoutId", "", "initView", "", "bindData", "bindListener", "onResume", "initToolbar", "initLocalView", DbFinal.LOCAL_PATH, "", "initIosDialogAdapter", "chooseImage", "runnable", "com/wifiled/ipixels/ui/projection/VideoActivity$runnable$1", "Lcom/wifiled/ipixels/ui/projection/VideoActivity$runnable$1;", "pickMedia", "Landroidx/activity/result/PickVisualMediaRequest;", "getPickMedia", "()Landroidx/activity/result/ActivityResultLauncher;", "selectVideo", "mTrimPath", "mGifPath", "launcherVideoActivity", "sendLocalVideoData", ImagesContract.URL, "doResultAction", "bytes", "", "onDestroy", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class VideoActivity extends BaseActivity {
    private RecyclerView actionRecyclerView;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private CustomImageView iv_back;
    private ImageView iv_preview;
    private CustomImageView iv_right;
    private IosDialogStyleAdapter<Object> mAdapter;
    private String mGifPath;
    private String mTrimPath;
    private final ActivityResultLauncher<PickVisualMediaRequest> pickMedia;
    private final ActivityResultLauncher<Intent> registForActivityResult;
    private final VideoActivity$runnable$1 runnable;
    private SeekBar seekbar;
    private ConstraintLayout title_bar;
    private TextView tv_title;
    private ZVideoView videoView;

    /* JADX WARN: Type inference failed for: r0v4, types: [com.wifiled.ipixels.ui.projection.VideoActivity$runnable$1] */
    public VideoActivity() {
        ActivityResultLauncher<Intent> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.wifiled.ipixels.ui.projection.VideoActivity$$ExternalSyntheticLambda6
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                VideoActivity.registForActivityResult$lambda$1(VideoActivity.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResult(...)");
        this.registForActivityResult = registerForActivityResult;
        this.runnable = new Runnable() { // from class: com.wifiled.ipixels.ui.projection.VideoActivity$runnable$1
            @Override // java.lang.Runnable
            public void run() {
                ZVideoView zVideoView;
                ZVideoView zVideoView2;
                SeekBar seekBar;
                Handler handler;
                String str;
                zVideoView = VideoActivity.this.videoView;
                SeekBar seekBar2 = null;
                if (zVideoView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("videoView");
                    zVideoView = null;
                }
                int currentPosition = zVideoView.getCurrentPosition();
                zVideoView2 = VideoActivity.this.videoView;
                if (zVideoView2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("videoView");
                    zVideoView2 = null;
                }
                if (currentPosition >= zVideoView2.getDuration()) {
                    str = VideoActivity.this.TAG;
                    LogUtils.logi(str + ">>>[updateProgress]: 播放完成", new Object[0]);
                    return;
                }
                seekBar = VideoActivity.this.seekbar;
                if (seekBar == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("seekbar");
                } else {
                    seekBar2 = seekBar;
                }
                seekBar2.setProgress(currentPosition);
                handler = VideoActivity.this.handler;
                handler.postDelayed(this, 30L);
            }
        };
        ActivityResultLauncher<PickVisualMediaRequest> registerForActivityResult2 = registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), new ActivityResultCallback() { // from class: com.wifiled.ipixels.ui.projection.VideoActivity$$ExternalSyntheticLambda7
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                VideoActivity.pickMedia$lambda$10(VideoActivity.this, (Uri) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult2, "registerForActivityResult(...)");
        this.pickMedia = registerForActivityResult2;
        this.mTrimPath = "";
        this.mGifPath = "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void registForActivityResult$lambda$1(VideoActivity videoActivity, ActivityResult activityResult) {
        if (activityResult.getResultCode() == -1) {
            ChannelIndex.INSTANCE.initChannelData();
            Intent data = activityResult.getData();
            ImageView imageView = null;
            String stringExtra = data != null ? data.getStringExtra("trim_path") : null;
            Intrinsics.checkNotNull(stringExtra);
            videoActivity.mTrimPath = stringExtra;
            Intent data2 = activityResult.getData();
            String stringExtra2 = data2 != null ? data2.getStringExtra("gif_path") : null;
            Intrinsics.checkNotNull(stringExtra2);
            videoActivity.mGifPath = stringExtra2;
            videoActivity.initLocalView(stringExtra);
            RequestBuilder<GifDrawable> load = Glide.with((FragmentActivity) videoActivity).asGif().load(stringExtra2);
            ImageView imageView2 = videoActivity.iv_preview;
            if (imageView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("iv_preview");
            } else {
                imageView = imageView2;
            }
            load.into(imageView);
            if (ChannelIndex.INSTANCE.mapSaveChannel().size() >= 100) {
                Handler handler = HandlerUtil.HandlerMain.INSTANCE.getMainHandler().get();
                if (handler != null) {
                    handler.post(new Runnable() { // from class: com.wifiled.ipixels.ui.projection.VideoActivity$$ExternalSyntheticLambda8
                        @Override // java.lang.Runnable
                        public final void run() {
                            VideoActivity.registForActivityResult$lambda$1$lambda$0();
                        }
                    });
                    return;
                }
                return;
            }
            if (AppConfig.INSTANCE.getMcu() > 4) {
                ArrayList arrayList = new ArrayList();
                arrayList.add((byte) 7);
                arrayList.add((byte) 0);
                arrayList.add((byte) 8);
                arrayList.add(Byte.valueOf(ByteCompanionObject.MIN_VALUE));
                arrayList.add((byte) 1);
                arrayList.add((byte) 0);
                ChannelIndex.INSTANCE.inc();
                arrayList.add(Byte.valueOf((byte) ChannelIndex.INSTANCE.index()));
                Log.v("ruis", "sendVideo.index().toByte()=" + ((int) ((byte) ChannelIndex.INSTANCE.index())));
                SendCore.INSTANCE.sendCompat(CollectionsKt.toByteArray(arrayList), new VideoActivity$registForActivityResult$1$2(videoActivity, stringExtra2));
                return;
            }
            videoActivity.sendLocalVideoData(stringExtra2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void registForActivityResult$lambda$1$lambda$0() {
        EventBus eventBus = EventBus.getDefault();
        byte[] bytes = "channel low space".getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        eventBus.post(new SendResultMsg(bytes));
    }

    @Override // com.wifiled.baselib.base.BaseActivity
    protected int layoutId() {
        getWindow().setFlags(128, 128);
        return R.layout.activity_video;
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
        View findViewById4 = findViewById(R.id.title_bar);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
        this.title_bar = (ConstraintLayout) findViewById4;
        View findViewById5 = findViewById(R.id.videoView);
        Intrinsics.checkNotNullExpressionValue(findViewById5, "findViewById(...)");
        this.videoView = (ZVideoView) findViewById5;
        View findViewById6 = findViewById(R.id.seekbar);
        Intrinsics.checkNotNullExpressionValue(findViewById6, "findViewById(...)");
        this.seekbar = (SeekBar) findViewById6;
        View findViewById7 = findViewById(R.id.iv_preview);
        Intrinsics.checkNotNullExpressionValue(findViewById7, "findViewById(...)");
        this.iv_preview = (ImageView) findViewById7;
    }

    @Override // com.wifiled.baselib.base.BaseActivity
    protected void bindData() {
        initIosDialogAdapter();
        if (getIntent().hasExtra("select_storage_video") && getIntent().getBooleanExtra("select_storage_video", false)) {
            selectVideo();
        }
        if (getIntent().hasExtra("select_cur_record_video")) {
            CharSequence charSequenceExtra = getIntent().getCharSequenceExtra("select_cur_record_video");
            Intrinsics.checkNotNull(charSequenceExtra, "null cannot be cast to non-null type kotlin.String");
            String str = (String) charSequenceExtra;
            if (str.length() > 0) {
                launcherVideoActivity(str);
            }
        }
        initToolbar();
    }

    @Override // com.wifiled.baselib.base.BaseActivity
    protected void bindListener() {
        super.bindListener();
        CustomImageView customImageView = this.iv_back;
        CustomImageView customImageView2 = null;
        if (customImageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_back");
            customImageView = null;
        }
        customImageView.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.projection.VideoActivity$$ExternalSyntheticLambda13
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VideoActivity.this.chooseImage();
            }
        });
        CustomImageView customImageView3 = this.iv_right;
        if (customImageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_right");
        } else {
            customImageView2 = customImageView3;
        }
        customImageView2.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.projection.VideoActivity$$ExternalSyntheticLambda14
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VideoActivity.bindListener$lambda$3(VideoActivity.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$3(VideoActivity videoActivity, View view) {
        AppConfig.INSTANCE.setPrevActivityName(AppConfig.INSTANCE.getTopActivity());
        SendCore.INSTANCE.sendExitCmd(null);
        videoActivity.finish();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        if (this.mTrimPath.length() <= 0 || this.mGifPath.length() <= 0) {
            return;
        }
        initLocalView(this.mTrimPath);
        RequestBuilder<GifDrawable> load = Glide.with((FragmentActivity) this).asGif().load(this.mGifPath);
        ImageView imageView = this.iv_preview;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_preview");
            imageView = null;
        }
        load.into(imageView);
    }

    private final void initToolbar() {
        ConstraintLayout constraintLayout = this.title_bar;
        CustomImageView customImageView = null;
        if (constraintLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("title_bar");
            constraintLayout = null;
        }
        constraintLayout.setBackgroundResource(R.mipmap.projection_nav_bg);
        CustomImageView customImageView2 = this.iv_back;
        if (customImageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_back");
            customImageView2 = null;
        }
        customImageView2.setBackgroundResource(R.mipmap.icon_add_video);
        TextView textView = this.tv_title;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_title");
            textView = null;
        }
        textView.setText(getString(R.string.title_projection));
        CustomImageView customImageView3 = this.iv_right;
        if (customImageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_right");
        } else {
            customImageView = customImageView3;
        }
        customImageView.setBackgroundResource(R.mipmap.icon_cancel);
    }

    private final void initLocalView(String path) {
        ZVideoView zVideoView = this.videoView;
        ZVideoView zVideoView2 = null;
        if (zVideoView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("videoView");
            zVideoView = null;
        }
        zVideoView.setVideoURI(Uri.parse(path));
        ZVideoView zVideoView3 = this.videoView;
        if (zVideoView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("videoView");
            zVideoView3 = null;
        }
        zVideoView3.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.wifiled.ipixels.ui.projection.VideoActivity$$ExternalSyntheticLambda10
            @Override // android.media.MediaPlayer.OnCompletionListener
            public final void onCompletion(MediaPlayer mediaPlayer) {
                VideoActivity.initLocalView$lambda$4(VideoActivity.this, mediaPlayer);
            }
        });
        ZVideoView zVideoView4 = this.videoView;
        if (zVideoView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("videoView");
            zVideoView4 = null;
        }
        zVideoView4.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: com.wifiled.ipixels.ui.projection.VideoActivity$$ExternalSyntheticLambda12
            @Override // android.media.MediaPlayer.OnPreparedListener
            public final void onPrepared(MediaPlayer mediaPlayer) {
                VideoActivity.initLocalView$lambda$5(VideoActivity.this, mediaPlayer);
            }
        });
        ZVideoView zVideoView5 = this.videoView;
        if (zVideoView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("videoView");
        } else {
            zVideoView2 = zVideoView5;
        }
        zVideoView2.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initLocalView$lambda$4(VideoActivity videoActivity, MediaPlayer mediaPlayer) {
        SeekBar seekBar = videoActivity.seekbar;
        SeekBar seekBar2 = null;
        if (seekBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("seekbar");
            seekBar = null;
        }
        SeekBar seekBar3 = videoActivity.seekbar;
        if (seekBar3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("seekbar");
        } else {
            seekBar2 = seekBar3;
        }
        seekBar.setProgress(seekBar2.getMax());
        mediaPlayer.start();
        videoActivity.handler.post(videoActivity.runnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initLocalView$lambda$5(VideoActivity videoActivity, MediaPlayer mediaPlayer) {
        mediaPlayer.setVideoScalingMode(1);
        SeekBar seekBar = videoActivity.seekbar;
        ZVideoView zVideoView = null;
        if (seekBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("seekbar");
            seekBar = null;
        }
        ZVideoView zVideoView2 = videoActivity.videoView;
        if (zVideoView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("videoView");
        } else {
            zVideoView = zVideoView2;
        }
        seekBar.setMax(zVideoView.getDuration());
        videoActivity.handler.post(videoActivity.runnable);
    }

    private final void initIosDialogAdapter() {
        this.mAdapter = new IosDialogStyleAdapter<>(this, CollectionsKt.listOf((Object[]) new String[]{getString(R.string.title_diy_phone_photos), getString(R.string.title_diy_phone_camera)}));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void chooseImage() {
        final Dialog showBottomDialog = DialogUtils.showBottomDialog(this, R.layout.dialog_select_media);
        this.actionRecyclerView = (RecyclerView) showBottomDialog.findViewById(R.id.rl_actions);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext(), 1, false);
        RecyclerView recyclerView = this.actionRecyclerView;
        if (recyclerView != null) {
            recyclerView.setLayoutManager(linearLayoutManager);
        }
        RecyclerView recyclerView2 = this.actionRecyclerView;
        if (recyclerView2 != null) {
            recyclerView2.addItemDecoration(new DividerItemDecoration(getBaseContext(), 1));
        }
        RecyclerView recyclerView3 = this.actionRecyclerView;
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter = null;
        if (recyclerView3 != null) {
            IosDialogStyleAdapter<Object> iosDialogStyleAdapter2 = this.mAdapter;
            if (iosDialogStyleAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
                iosDialogStyleAdapter2 = null;
            }
            recyclerView3.setAdapter(iosDialogStyleAdapter2);
        }
        RecyclerView recyclerView4 = this.actionRecyclerView;
        if (recyclerView4 != null) {
            recyclerView4.post(new Runnable() { // from class: com.wifiled.ipixels.ui.projection.VideoActivity$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    VideoActivity.chooseImage$lambda$6(VideoActivity.this);
                }
            });
        }
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter3 = this.mAdapter;
        if (iosDialogStyleAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
        } else {
            iosDialogStyleAdapter = iosDialogStyleAdapter3;
        }
        iosDialogStyleAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() { // from class: com.wifiled.ipixels.ui.projection.VideoActivity$$ExternalSyntheticLambda3
            @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter.OnItemClickListener
            public final void onItemClick(ViewGroup viewGroup, View view, Object obj, int i) {
                VideoActivity.chooseImage$lambda$8(showBottomDialog, this, viewGroup, view, obj, i);
            }
        });
        TextView textView = (TextView) showBottomDialog.findViewById(R.id.tv_cancel);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.projection.VideoActivity$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                showBottomDialog.cancel();
            }
        });
        CustomImageView.Companion companion = CustomImageView.INSTANCE;
        Intrinsics.checkNotNull(textView);
        companion.attachViewOnTouchListener(textView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void chooseImage$lambda$6(VideoActivity videoActivity) {
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter = videoActivity.mAdapter;
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter2 = null;
        if (iosDialogStyleAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
            iosDialogStyleAdapter = null;
        }
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter3 = videoActivity.mAdapter;
        if (iosDialogStyleAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
        } else {
            iosDialogStyleAdapter2 = iosDialogStyleAdapter3;
        }
        int itemCount = iosDialogStyleAdapter2.getItemCount();
        RecyclerView recyclerView = videoActivity.actionRecyclerView;
        Intrinsics.checkNotNull(recyclerView);
        iosDialogStyleAdapter.adaptiveRecyclerViewHeight(itemCount, recyclerView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void chooseImage$lambda$8(Dialog dialog, final VideoActivity videoActivity, ViewGroup viewGroup, View view, Object obj, int i) {
        dialog.cancel();
        if (i == 0) {
            videoActivity.selectVideo();
        } else if (i == 1) {
            videoActivity.toActivityForResult(new Intent("android.media.action.VIDEO_CAPTURE"), new com.wifiled.baselib.callback.ActivityResultCallback() { // from class: com.wifiled.ipixels.ui.projection.VideoActivity$$ExternalSyntheticLambda9
                @Override // com.wifiled.baselib.callback.ActivityResultCallback
                public final void onActivityResult(int i2, Intent intent) {
                    VideoActivity.chooseImage$lambda$8$lambda$7(VideoActivity.this, i2, intent);
                }
            });
        }
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter = videoActivity.mAdapter;
        if (iosDialogStyleAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
            iosDialogStyleAdapter = null;
        }
        iosDialogStyleAdapter.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void chooseImage$lambda$8$lambda$7(VideoActivity videoActivity, int i, Intent intent) {
        if (i == -1) {
            Uri data = intent.getData();
            Intrinsics.checkNotNull(data);
            String path = UriUtils.INSTANCE.getPath(videoActivity, data);
            Intrinsics.checkNotNull(path);
            videoActivity.launcherVideoActivity(path);
        }
    }

    public final ActivityResultLauncher<PickVisualMediaRequest> getPickMedia() {
        return this.pickMedia;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void pickMedia$lambda$10(VideoActivity videoActivity, Uri uri) {
        if (uri != null) {
            videoActivity.launcherVideoActivity(String.valueOf(UriUtils.INSTANCE.getPath(videoActivity, uri)));
        }
    }

    private final void selectVideo() {
        this.pickMedia.launch(PickVisualMediaRequestKt.PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.VideoOnly.INSTANCE));
    }

    private final void launcherVideoActivity(String path) {
        try {
            Intent intent = new Intent(this, (Class<?>) VideoTrimActivity.class);
            intent.putExtra(DbFinal.LOCAL_PATH, path);
            this.registForActivityResult.launch(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendLocalVideoData(String url) {
        final byte[] readFileBytes = FileUtil.readFileBytes(url);
        SendCore sendCore = SendCore.INSTANCE;
        Intrinsics.checkNotNull(readFileBytes);
        sendCore.sendGifData(true, readFileBytes, new Function1() { // from class: com.wifiled.ipixels.ui.projection.VideoActivity$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendLocalVideoData$lambda$20;
                sendLocalVideoData$lambda$20 = VideoActivity.sendLocalVideoData$lambda$20(VideoActivity.this, readFileBytes, (SendCore.CallbackBuilder) obj);
                return sendLocalVideoData$lambda$20;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendLocalVideoData$lambda$20(final VideoActivity videoActivity, final byte[] bArr, SendCore.CallbackBuilder sendGifData) {
        Intrinsics.checkNotNullParameter(sendGifData, "$this$sendGifData");
        sendGifData.onStart(new Function0() { // from class: com.wifiled.ipixels.ui.projection.VideoActivity$$ExternalSyntheticLambda19
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendLocalVideoData$lambda$20$lambda$12;
                sendLocalVideoData$lambda$20$lambda$12 = VideoActivity.sendLocalVideoData$lambda$20$lambda$12(VideoActivity.this);
                return sendLocalVideoData$lambda$20$lambda$12;
            }
        });
        sendGifData.onProgress(new Function1() { // from class: com.wifiled.ipixels.ui.projection.VideoActivity$$ExternalSyntheticLambda20
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendLocalVideoData$lambda$20$lambda$14;
                sendLocalVideoData$lambda$20$lambda$14 = VideoActivity.sendLocalVideoData$lambda$20$lambda$14(VideoActivity.this, ((Integer) obj).intValue());
                return sendLocalVideoData$lambda$20$lambda$14;
            }
        });
        sendGifData.onCompleted(new Function0() { // from class: com.wifiled.ipixels.ui.projection.VideoActivity$$ExternalSyntheticLambda21
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendLocalVideoData$lambda$20$lambda$17;
                sendLocalVideoData$lambda$20$lambda$17 = VideoActivity.sendLocalVideoData$lambda$20$lambda$17(VideoActivity.this, bArr);
                return sendLocalVideoData$lambda$20$lambda$17;
            }
        });
        sendGifData.onError(new Function1() { // from class: com.wifiled.ipixels.ui.projection.VideoActivity$$ExternalSyntheticLambda22
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendLocalVideoData$lambda$20$lambda$18;
                sendLocalVideoData$lambda$20$lambda$18 = VideoActivity.sendLocalVideoData$lambda$20$lambda$18(VideoActivity.this, ((Integer) obj).intValue());
                return sendLocalVideoData$lambda$20$lambda$18;
            }
        });
        sendGifData.onResult(new Function1() { // from class: com.wifiled.ipixels.ui.projection.VideoActivity$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendLocalVideoData$lambda$20$lambda$19;
                sendLocalVideoData$lambda$20$lambda$19 = VideoActivity.sendLocalVideoData$lambda$20$lambda$19(VideoActivity.this, (byte[]) obj);
                return sendLocalVideoData$lambda$20$lambda$19;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendLocalVideoData$lambda$20$lambda$12(final VideoActivity videoActivity) {
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.projection.VideoActivity$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendLocalVideoData$lambda$20$lambda$12$lambda$11;
                sendLocalVideoData$lambda$20$lambda$12$lambda$11 = VideoActivity.sendLocalVideoData$lambda$20$lambda$12$lambda$11(VideoActivity.this);
                return sendLocalVideoData$lambda$20$lambda$12$lambda$11;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendLocalVideoData$lambda$20$lambda$12$lambda$11(VideoActivity videoActivity) {
        String string = videoActivity.getString(R.string.msg_sending);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        UtilsExtensionKt.showLoadingDialog$default((Activity) videoActivity, false, string, false, 5, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendLocalVideoData$lambda$20$lambda$14(final VideoActivity videoActivity, final int i) {
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.projection.VideoActivity$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendLocalVideoData$lambda$20$lambda$14$lambda$13;
                sendLocalVideoData$lambda$20$lambda$14$lambda$13 = VideoActivity.sendLocalVideoData$lambda$20$lambda$14$lambda$13(VideoActivity.this, i);
                return sendLocalVideoData$lambda$20$lambda$14$lambda$13;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendLocalVideoData$lambda$20$lambda$14$lambda$13(VideoActivity videoActivity, int i) {
        UtilsExtensionKt.showLoadingDialog$default((Activity) videoActivity, false, videoActivity.getString(R.string.msg_sending_progress) + " " + i + " %", false, 5, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendLocalVideoData$lambda$20$lambda$17(final VideoActivity videoActivity, byte[] bArr) {
        ChannelIndex.INSTANCE.mapSaveChannel().put(Integer.valueOf(ChannelIndex.INSTANCE.index()), new ChannelListItem.GiftView(false, videoActivity.mGifPath, String.valueOf(ChannelIndex.INSTANCE.index()), false, bArr));
        Context baseContext = videoActivity.getBaseContext();
        String str = "channel_data";
        switch (AppConfig.INSTANCE.getLedType()) {
            case 0:
                break;
            case 1:
                str = "channel_data_96";
                break;
            case 2:
                str = "channel_data_32";
                break;
            case 3:
                str = "channel_data_16";
                break;
            case 4:
                str = "channel_data_12";
                break;
            case 5:
                str = "channel_data_20";
                break;
            case 6:
                str = "channel_data_128";
                break;
            case 7:
                str = "channel_data_144";
                break;
            case 8:
                str = "channel_data_192";
                break;
            case 9:
                str = "channel_data_24_48";
                break;
            case 10:
                str = "channel_data_32_64";
                break;
            case 11:
                str = "channel_data_32_96";
                break;
            case 12:
                str = "channel_data_128_2";
                break;
            case 13:
                str = "channel_data_32_96_2";
                break;
            case 14:
                str = "channel_data_32_160";
                break;
            case 15:
                str = "channel_data_32_192";
                break;
            default:
                new RuntimeException("尺寸未适配");
                break;
        }
        SPUtils.put(baseContext, str, ChannelIndex.INSTANCE.mapSaveChannel());
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.projection.VideoActivity$$ExternalSyntheticLambda17
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendLocalVideoData$lambda$20$lambda$17$lambda$15;
                sendLocalVideoData$lambda$20$lambda$17$lambda$15 = VideoActivity.sendLocalVideoData$lambda$20$lambda$17$lambda$15(VideoActivity.this);
                return sendLocalVideoData$lambda$20$lambda$17$lambda$15;
            }
        });
        UtilsExtensionKt.uiDelay$default(new Function0() { // from class: com.wifiled.ipixels.ui.projection.VideoActivity$$ExternalSyntheticLambda18
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendLocalVideoData$lambda$20$lambda$17$lambda$16;
                sendLocalVideoData$lambda$20$lambda$17$lambda$16 = VideoActivity.sendLocalVideoData$lambda$20$lambda$17$lambda$16(VideoActivity.this);
                return sendLocalVideoData$lambda$20$lambda$17$lambda$16;
            }
        }, 0L, 2, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendLocalVideoData$lambda$20$lambda$17$lambda$15(VideoActivity videoActivity) {
        VideoActivity videoActivity2 = videoActivity;
        String string = videoActivity.getString(R.string.msg_send_suc);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        UtilsExtensionKt.showLoadingDialog((Activity) videoActivity2, true, string, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendLocalVideoData$lambda$20$lambda$17$lambda$16(VideoActivity videoActivity) {
        UtilsExtensionKt.showLoadingDialog$default((Activity) videoActivity, false, (String) null, false, 6, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendLocalVideoData$lambda$20$lambda$18(VideoActivity videoActivity, int i) {
        ToastUtil.show(videoActivity.getString(R.string.msg_send_fail) + "(" + i + ")");
        UtilsExtensionKt.showLoadingDialog$default((Activity) videoActivity, false, (String) null, false, 6, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendLocalVideoData$lambda$20$lambda$19(VideoActivity videoActivity, byte[] it) {
        Intrinsics.checkNotNullParameter(it, "it");
        videoActivity.doResultAction(it);
        return Unit.INSTANCE;
    }

    private final void doResultAction(byte[] bytes) {
        if (bytes.length > 4) {
            byte b = bytes[4];
            if (b == 0) {
                LogUtils.logi(this.TAG + ">>>[onResult]:命令无效 ", new Object[0]);
                UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.projection.VideoActivity$$ExternalSyntheticLambda15
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Unit doResultAction$lambda$21;
                        doResultAction$lambda$21 = VideoActivity.doResultAction$lambda$21(VideoActivity.this);
                        return doResultAction$lambda$21;
                    }
                });
                return;
            } else if (b == 2) {
                LogUtils.logi(this.TAG + ">>>[onResult]:空间不足 ", new Object[0]);
                UtilsExtensionKt.showLoadingDialog$default((Activity) this, false, (String) null, false, 6, (Object) null);
                return;
            } else {
                if (b != 3) {
                    return;
                }
                LogUtils.logi(this.TAG + ">>>[onResult]:保存文件成功 ", new Object[0]);
                return;
            }
        }
        if (bytes[0] == Byte.MAX_VALUE) {
            LogUtils.logi(this.TAG + ">>>[onResult]:fail ", new Object[0]);
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.projection.VideoActivity$$ExternalSyntheticLambda16
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit doResultAction$lambda$22;
                    doResultAction$lambda$22 = VideoActivity.doResultAction$lambda$22(VideoActivity.this);
                    return doResultAction$lambda$22;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit doResultAction$lambda$21(VideoActivity videoActivity) {
        UtilsExtensionKt.showLoadingDialog$default((Activity) videoActivity, false, (String) null, false, 6, (Object) null);
        videoActivity.toast(videoActivity.getString(R.string.msg_send_fail));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit doResultAction$lambda$22(VideoActivity videoActivity) {
        UtilsExtensionKt.showLoadingDialog$default((Activity) videoActivity, false, (String) null, false, 6, (Object) null);
        return Unit.INSTANCE;
    }

    @Override // com.wifiled.baselib.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.handler.removeCallbacks(this.runnable);
    }
}
