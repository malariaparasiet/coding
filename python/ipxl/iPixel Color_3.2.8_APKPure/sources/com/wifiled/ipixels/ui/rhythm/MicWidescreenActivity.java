package com.wifiled.ipixels.ui.rhythm;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.camera.video.AudioStats;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;
import com.wifiled.baselib.app.language.LanguageUtil;
import com.wifiled.baselib.base.BaseActivity;
import com.wifiled.baselib.utils.LogUtils;
import com.wifiled.baselib.utils.ScreenUtil;
import com.wifiled.ipixels.App;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.UtilsExtensionKt;
import com.wifiled.ipixels.bean.RhythmImage;
import com.wifiled.ipixels.core.SendCore;
import com.wifiled.ipixels.ui.adapter.ViewpagerAdapter;
import com.wifiled.ipixels.ui.rhythm.AudioRecorderUtil;
import com.wifiled.ipixels.utils.FftUtils;
import com.wifiled.ipixels.utils.RhythmDataUitls;
import com.wifiled.ipixels.utils.TimeHelper;
import com.wifiled.ipixels.view.RhythmLedView2;
import com.wifiled.ipixels.view.ViewPagerAllResponse;
import com.wifiled.ipixels.view.ZoomOutPageTransformer;
import com.wifiled.ipixels.view.customview.CustomImageView;
import com.wifiled.musiclib.MusicManager;
import com.wifiled.musiclib.player.callback.RecordDataCallBack;
import com.wifiled.musiclib.player.constant.PlayerFinal;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* compiled from: MicWidescreenActivity.kt */
@Metadata(d1 = {"\u0000\u009c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\u0017\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004B\u0007¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u001a\u001a\u00020\rH\u0014J\b\u0010(\u001a\u00020)H\u0014J\b\u0010*\u001a\u00020)H\u0014J\b\u0010+\u001a\u00020)H\u0014J\b\u0010,\u001a\u00020)H\u0014J\b\u0010-\u001a\u00020)H\u0014J \u0010.\u001a\u00020)2\u0006\u0010/\u001a\u00020\r2\u0006\u00100\u001a\u0002012\u0006\u00102\u001a\u00020\rH\u0016J\u0010\u00103\u001a\u00020)2\u0006\u0010/\u001a\u00020\rH\u0016J\u0010\u00104\u001a\u00020)2\u0006\u00105\u001a\u00020\rH\u0016J\u0010\u00106\u001a\u00020)2\u0006\u00107\u001a\u00020\rH\u0002J\u0010\u00108\u001a\u00020)2\u0006\u00109\u001a\u00020\rH\u0002J\u0010\u0010:\u001a\u00020)2\u0006\u0010;\u001a\u00020\u0014H\u0002J\b\u0010>\u001a\u00020)H\u0002J\b\u0010?\u001a\u00020)H\u0002J\b\u0010@\u001a\u00020)H\u0002J\b\u0010A\u001a\u00020)H\u0002J\u0010\u0010B\u001a\u00020)2\u0006\u0010C\u001a\u00020\u0012H\u0016J\u0010\u0010D\u001a\u00020)2\u0006\u0010E\u001a\u00020FH\u0016J\u0010\u0010G\u001a\u00020)2\u0006\u0010E\u001a\u00020FH\u0002J\u0012\u0010H\u001a\u00020)2\b\u0010I\u001a\u0004\u0018\u00010JH\u0016J\u000e\u0010K\u001a\u00020F2\u0006\u0010L\u001a\u00020JR\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0015\u001a\u0012\u0012\u0004\u0012\u00020\u00170\u0016j\b\u0012\u0004\u0012\u00020\u0017`\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u001eX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u001eX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\"X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020%X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020'X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010<\u001a\u00020=X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006M"}, d2 = {"Lcom/wifiled/ipixels/ui/rhythm/MicWidescreenActivity;", "Lcom/wifiled/baselib/base/BaseActivity;", "Landroidx/viewpager/widget/ViewPager$OnPageChangeListener;", "Lcom/wifiled/ipixels/ui/rhythm/AudioRecorderUtil$onMicDbListener;", "Lcom/wifiled/musiclib/player/callback/RecordDataCallBack;", "<init>", "()V", "imageList", "", "Lcom/wifiled/ipixels/bean/RhythmImage;", "textImageIconAdapter", "Lcom/wifiled/ipixels/ui/adapter/ViewpagerAdapter;", "curSelectPosition", "", "curSelectMode", "handler", "Landroid/os/Handler;", "m_dVolume", "", "m_bIsMicRecording", "", "mVolumeList", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "m_iTotalItems", "layoutId", "iv_mic_rhy_image_bg", "Landroid/widget/ImageView;", "rhyledview_mic_1", "Lcom/wifiled/ipixels/view/RhythmLedView2;", "rhyledview_mic_2", "rhyledview_mic_3", "rl_mic_rhy_bg", "Landroid/widget/RelativeLayout;", "rl_mic_root", "vp_mic_rhyhm", "Lcom/wifiled/ipixels/view/ViewPagerAllResponse;", "iv_back", "Lcom/wifiled/ipixels/view/customview/CustomImageView;", "onResume", "", "initView", "onDestroy", "bindData", "onRestart", "onPageScrolled", PlayerFinal.PLAYER_POSITION, "positionOffset", "", "positionOffsetPixels", "onPageSelected", "onPageScrollStateChanged", PlayerFinal.STATE, "showCurRhyMode", "currentImage", "selectRhythmMode", "selectMode", "rhyUIShow", "isVisible", "runnable", "Ljava/lang/Runnable;", "setRecordDataCallBack", "initToolBar", "initData", "initRhyUI", "onCallBackMicDb", "volume", "onDataCapture", "data", "", "setRhyData", "onRecordData", "buffer", "", "toByteArray", "src", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MicWidescreenActivity extends BaseActivity implements ViewPager.OnPageChangeListener, AudioRecorderUtil.onMicDbListener, RecordDataCallBack {
    private int curSelectMode;
    private int curSelectPosition;
    private CustomImageView iv_back;
    private ImageView iv_mic_rhy_image_bg;
    private boolean m_bIsMicRecording;
    private double m_dVolume;
    private RhythmLedView2 rhyledview_mic_1;
    private RhythmLedView2 rhyledview_mic_2;
    private RhythmLedView2 rhyledview_mic_3;
    private RelativeLayout rl_mic_rhy_bg;
    private RelativeLayout rl_mic_root;
    private ViewpagerAdapter textImageIconAdapter;
    private ViewPagerAllResponse vp_mic_rhyhm;
    private final List<RhythmImage> imageList = new ArrayList();
    private final Handler handler = new Handler();
    private ArrayList<Byte> mVolumeList = new ArrayList<>();
    private int m_iTotalItems = 17;
    private Runnable runnable = new Runnable() { // from class: com.wifiled.ipixels.ui.rhythm.MicWidescreenActivity$$ExternalSyntheticLambda5
        @Override // java.lang.Runnable
        public final void run() {
            MicWidescreenActivity.runnable$lambda$1(MicWidescreenActivity.this);
        }
    };

    @Override // com.wifiled.baselib.base.BaseActivity
    protected int layoutId() {
        return R.layout.activity_mic_wide;
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        AppConfig appConfig = AppConfig.INSTANCE;
        AppConfig.isOnly = true;
        super.onResume();
    }

    @Override // com.wifiled.baselib.base.BaseActivity
    protected void initView() {
        super.initView();
        View findViewById = findViewById(R.id.iv_mic_rhy_image_bg);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.iv_mic_rhy_image_bg = (ImageView) findViewById;
        View findViewById2 = findViewById(R.id.rhyledview_mic_1);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.rhyledview_mic_1 = (RhythmLedView2) findViewById2;
        View findViewById3 = findViewById(R.id.rhyledview_mic_2);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.rhyledview_mic_2 = (RhythmLedView2) findViewById3;
        View findViewById4 = findViewById(R.id.rhyledview_mic_3);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
        this.rhyledview_mic_3 = (RhythmLedView2) findViewById4;
        View findViewById5 = findViewById(R.id.rl_mic_rhy_bg);
        Intrinsics.checkNotNullExpressionValue(findViewById5, "findViewById(...)");
        this.rl_mic_rhy_bg = (RelativeLayout) findViewById5;
        View findViewById6 = findViewById(R.id.rl_mic_root);
        Intrinsics.checkNotNullExpressionValue(findViewById6, "findViewById(...)");
        this.rl_mic_root = (RelativeLayout) findViewById6;
        View findViewById7 = findViewById(R.id.vp_mic_rhyhm);
        Intrinsics.checkNotNullExpressionValue(findViewById7, "findViewById(...)");
        this.vp_mic_rhyhm = (ViewPagerAllResponse) findViewById7;
        View findViewById8 = findViewById(R.id.iv_back);
        Intrinsics.checkNotNullExpressionValue(findViewById8, "findViewById(...)");
        this.iv_back = (CustomImageView) findViewById8;
    }

    @Override // com.wifiled.baselib.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        AppConfig appConfig = AppConfig.INSTANCE;
        AppConfig.isOnly = false;
        MusicManager.getInstance().stopRecord();
        super.onDestroy();
    }

    @Override // com.wifiled.baselib.base.BaseActivity
    protected void bindData() {
        initData();
        initToolBar();
        setRecordDataCallBack();
    }

    @Override // android.app.Activity
    protected void onRestart() {
        super.onRestart();
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int position) {
        int size = position % this.imageList.size();
        this.curSelectPosition = size;
        LogUtils.logi("ViewpagerAdapter>>>[onPageSelected]: position: " + size, new Object[0]);
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int state) {
        if (state == 0) {
            Log.d(this.TAG, "停止：" + this.curSelectPosition);
            showCurRhyMode(this.curSelectPosition);
            return;
        }
        if (state == 1 || state == 2) {
            Log.d(this.TAG, "开始滑动");
            rhyUIShow(false);
        }
    }

    private final void showCurRhyMode(int currentImage) {
        if (this.m_dVolume > AudioStats.AUDIO_AMPLITUDE_NONE) {
            rhyUIShow(true);
        } else {
            rhyUIShow(false);
        }
        RhythmLedView2 rhythmLedView2 = null;
        if (currentImage == 0) {
            RelativeLayout relativeLayout = this.rl_mic_rhy_bg;
            if (relativeLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rl_mic_rhy_bg");
                relativeLayout = null;
            }
            relativeLayout.setRotation(0.0f);
            RhythmLedView2 rhythmLedView22 = this.rhyledview_mic_1;
            if (rhythmLedView22 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_1");
                rhythmLedView22 = null;
            }
            rhythmLedView22.setRotationX(0.0f);
            RhythmLedView2 rhythmLedView23 = this.rhyledview_mic_2;
            if (rhythmLedView23 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_2");
            } else {
                rhythmLedView2 = rhythmLedView23;
            }
            rhythmLedView2.setRotationX(180.0f);
            selectRhythmMode(1);
            this.m_iTotalItems = 17;
            return;
        }
        if (currentImage == 1) {
            RelativeLayout relativeLayout2 = this.rl_mic_rhy_bg;
            if (relativeLayout2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rl_mic_rhy_bg");
                relativeLayout2 = null;
            }
            relativeLayout2.setRotation(0.0f);
            RhythmLedView2 rhythmLedView24 = this.rhyledview_mic_1;
            if (rhythmLedView24 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_1");
                rhythmLedView24 = null;
            }
            rhythmLedView24.setRotationX(0.0f);
            RhythmLedView2 rhythmLedView25 = this.rhyledview_mic_2;
            if (rhythmLedView25 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_2");
            } else {
                rhythmLedView2 = rhythmLedView25;
            }
            rhythmLedView2.setRotationX(180.0f);
            selectRhythmMode(2);
            this.m_iTotalItems = 25;
            return;
        }
        if (currentImage == 2) {
            RelativeLayout relativeLayout3 = this.rl_mic_rhy_bg;
            if (relativeLayout3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rl_mic_rhy_bg");
                relativeLayout3 = null;
            }
            relativeLayout3.setRotation(0.0f);
            RhythmLedView2 rhythmLedView26 = this.rhyledview_mic_1;
            if (rhythmLedView26 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_1");
                rhythmLedView26 = null;
            }
            rhythmLedView26.setRotationX(0.0f);
            RhythmLedView2 rhythmLedView27 = this.rhyledview_mic_2;
            if (rhythmLedView27 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_2");
            } else {
                rhythmLedView2 = rhythmLedView27;
            }
            rhythmLedView2.setRotationX(180.0f);
            selectRhythmMode(3);
            this.m_iTotalItems = 26;
            return;
        }
        if (currentImage == 3) {
            RelativeLayout relativeLayout4 = this.rl_mic_rhy_bg;
            if (relativeLayout4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rl_mic_rhy_bg");
                relativeLayout4 = null;
            }
            relativeLayout4.setRotation(0.0f);
            RhythmLedView2 rhythmLedView28 = this.rhyledview_mic_3;
            if (rhythmLedView28 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_3");
            } else {
                rhythmLedView2 = rhythmLedView28;
            }
            rhythmLedView2.setRotationX(0.0f);
            selectRhythmMode(4);
            this.m_iTotalItems = 26;
            return;
        }
        if (currentImage == 4) {
            RelativeLayout relativeLayout5 = this.rl_mic_rhy_bg;
            if (relativeLayout5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rl_mic_rhy_bg");
                relativeLayout5 = null;
            }
            relativeLayout5.setRotation(0.0f);
            RhythmLedView2 rhythmLedView29 = this.rhyledview_mic_1;
            if (rhythmLedView29 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_1");
                rhythmLedView29 = null;
            }
            rhythmLedView29.setRotationX(0.0f);
            RhythmLedView2 rhythmLedView210 = this.rhyledview_mic_2;
            if (rhythmLedView210 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_2");
            } else {
                rhythmLedView2 = rhythmLedView210;
            }
            rhythmLedView2.setRotationX(180.0f);
            selectRhythmMode(5);
            this.m_iTotalItems = 17;
            return;
        }
        this.m_iTotalItems = 7;
        selectRhythmMode(currentImage + 1);
    }

    private final void selectRhythmMode(int selectMode) {
        switch (selectMode) {
            case 1:
                this.curSelectMode = 0;
                break;
            case 2:
                this.curSelectMode = 1;
                break;
            case 3:
                this.curSelectMode = 2;
                break;
            case 4:
                this.curSelectMode = 3;
                break;
            case 5:
                this.curSelectMode = 4;
                break;
            case 6:
                this.curSelectMode = 5;
                break;
            case 7:
                this.curSelectMode = 6;
                break;
        }
    }

    private final void rhyUIShow(boolean isVisible) {
        this.handler.removeCallbacks(this.runnable);
        if (isVisible) {
            this.handler.postDelayed(this.runnable, 150L);
        } else {
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.rhythm.MicWidescreenActivity$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit rhyUIShow$lambda$0;
                    rhyUIShow$lambda$0 = MicWidescreenActivity.rhyUIShow$lambda$0(MicWidescreenActivity.this);
                    return rhyUIShow$lambda$0;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit rhyUIShow$lambda$0(MicWidescreenActivity micWidescreenActivity) {
        RhythmLedView2 rhythmLedView2 = micWidescreenActivity.rhyledview_mic_1;
        ViewpagerAdapter viewpagerAdapter = null;
        if (rhythmLedView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_1");
            rhythmLedView2 = null;
        }
        UtilsExtensionKt.hide(rhythmLedView2);
        RhythmLedView2 rhythmLedView22 = micWidescreenActivity.rhyledview_mic_2;
        if (rhythmLedView22 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_2");
            rhythmLedView22 = null;
        }
        UtilsExtensionKt.hide(rhythmLedView22);
        RhythmLedView2 rhythmLedView23 = micWidescreenActivity.rhyledview_mic_3;
        if (rhythmLedView23 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_3");
            rhythmLedView23 = null;
        }
        UtilsExtensionKt.hide(rhythmLedView23);
        ImageView imageView = micWidescreenActivity.iv_mic_rhy_image_bg;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_mic_rhy_image_bg");
            imageView = null;
        }
        UtilsExtensionKt.hide(imageView);
        ViewpagerAdapter viewpagerAdapter2 = micWidescreenActivity.textImageIconAdapter;
        if (viewpagerAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("textImageIconAdapter");
        } else {
            viewpagerAdapter = viewpagerAdapter2;
        }
        viewpagerAdapter.notifyDataSetChanged();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void runnable$lambda$1(MicWidescreenActivity micWidescreenActivity) {
        ViewpagerAdapter viewpagerAdapter = null;
        if (micWidescreenActivity.curSelectPosition == 3) {
            RhythmLedView2 rhythmLedView2 = micWidescreenActivity.rhyledview_mic_1;
            if (rhythmLedView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_1");
                rhythmLedView2 = null;
            }
            UtilsExtensionKt.hide(rhythmLedView2);
            RhythmLedView2 rhythmLedView22 = micWidescreenActivity.rhyledview_mic_2;
            if (rhythmLedView22 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_2");
                rhythmLedView22 = null;
            }
            UtilsExtensionKt.hide(rhythmLedView22);
            RhythmLedView2 rhythmLedView23 = micWidescreenActivity.rhyledview_mic_3;
            if (rhythmLedView23 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_3");
                rhythmLedView23 = null;
            }
            UtilsExtensionKt.show(rhythmLedView23);
            ImageView imageView = micWidescreenActivity.iv_mic_rhy_image_bg;
            if (imageView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("iv_mic_rhy_image_bg");
                imageView = null;
            }
            UtilsExtensionKt.show(imageView);
        } else {
            RhythmLedView2 rhythmLedView24 = micWidescreenActivity.rhyledview_mic_1;
            if (rhythmLedView24 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_1");
                rhythmLedView24 = null;
            }
            UtilsExtensionKt.show(rhythmLedView24);
            RhythmLedView2 rhythmLedView25 = micWidescreenActivity.rhyledview_mic_2;
            if (rhythmLedView25 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_2");
                rhythmLedView25 = null;
            }
            UtilsExtensionKt.show(rhythmLedView25);
            RhythmLedView2 rhythmLedView26 = micWidescreenActivity.rhyledview_mic_3;
            if (rhythmLedView26 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_3");
                rhythmLedView26 = null;
            }
            UtilsExtensionKt.hide(rhythmLedView26);
            ImageView imageView2 = micWidescreenActivity.iv_mic_rhy_image_bg;
            if (imageView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("iv_mic_rhy_image_bg");
                imageView2 = null;
            }
            UtilsExtensionKt.show(imageView2);
        }
        LogUtils.logi(micWidescreenActivity.TAG + ">>>[curSelectPosition]:  " + micWidescreenActivity.curSelectPosition, new Object[0]);
        ViewpagerAdapter viewpagerAdapter2 = micWidescreenActivity.textImageIconAdapter;
        if (viewpagerAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("textImageIconAdapter");
        } else {
            viewpagerAdapter = viewpagerAdapter2;
        }
        viewpagerAdapter.notifyDataSetChanged();
    }

    private final void setRecordDataCallBack() {
        MusicManager.getInstance().setRecordDataCallBack(this);
        MusicManager.getInstance().startRecord();
    }

    private final void initToolBar() {
        String saveLanguage = LanguageUtil.getSaveLanguage(App.INSTANCE.getContext());
        Intrinsics.checkNotNullExpressionValue(saveLanguage, "getSaveLanguage(...)");
        CustomImageView customImageView = null;
        if (StringsKt.contains$default((CharSequence) saveLanguage, (CharSequence) "ar", false, 2, (Object) null)) {
            CustomImageView customImageView2 = this.iv_back;
            if (customImageView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("iv_back");
                customImageView2 = null;
            }
            customImageView2.setBackgroundResource(R.mipmap.icon_back_ar);
        } else {
            CustomImageView customImageView3 = this.iv_back;
            if (customImageView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("iv_back");
                customImageView3 = null;
            }
            customImageView3.setBackgroundResource(R.mipmap.icon_back);
        }
        CustomImageView customImageView4 = this.iv_back;
        if (customImageView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_back");
            customImageView4 = null;
        }
        customImageView4.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.rhythm.MicWidescreenActivity$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MicWidescreenActivity.this.finish();
            }
        });
        CustomImageView.Companion companion = CustomImageView.INSTANCE;
        CustomImageView customImageView5 = this.iv_back;
        if (customImageView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_back");
        } else {
            customImageView = customImageView5;
        }
        companion.attachViewOnTouchListener(customImageView);
    }

    private final void initData() {
        initRhyUI();
        RhythmLedView2 rhythmLedView2 = this.rhyledview_mic_1;
        RhythmLedView2 rhythmLedView22 = null;
        if (rhythmLedView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_1");
            rhythmLedView2 = null;
        }
        rhythmLedView2.setLayerType(1, null);
        RhythmLedView2 rhythmLedView23 = this.rhyledview_mic_1;
        if (rhythmLedView23 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_1");
            rhythmLedView23 = null;
        }
        rhythmLedView23.setMode(0);
        RhythmLedView2 rhythmLedView24 = this.rhyledview_mic_1;
        if (rhythmLedView24 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_1");
            rhythmLedView24 = null;
        }
        rhythmLedView24.removeAllViews();
        RhythmLedView2 rhythmLedView25 = this.rhyledview_mic_1;
        if (rhythmLedView25 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_1");
            rhythmLedView25 = null;
        }
        rhythmLedView25.init(64, 8);
        RhythmLedView2 rhythmLedView26 = this.rhyledview_mic_2;
        if (rhythmLedView26 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_2");
            rhythmLedView26 = null;
        }
        rhythmLedView26.setLayerType(1, null);
        RhythmLedView2 rhythmLedView27 = this.rhyledview_mic_2;
        if (rhythmLedView27 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_2");
            rhythmLedView27 = null;
        }
        rhythmLedView27.setMode(0);
        RhythmLedView2 rhythmLedView28 = this.rhyledview_mic_2;
        if (rhythmLedView28 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_2");
            rhythmLedView28 = null;
        }
        rhythmLedView28.removeAllViews();
        RhythmLedView2 rhythmLedView29 = this.rhyledview_mic_2;
        if (rhythmLedView29 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_2");
            rhythmLedView29 = null;
        }
        rhythmLedView29.init(64, 8);
        RhythmLedView2 rhythmLedView210 = this.rhyledview_mic_3;
        if (rhythmLedView210 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_3");
            rhythmLedView210 = null;
        }
        rhythmLedView210.setLayerType(1, null);
        RhythmLedView2 rhythmLedView211 = this.rhyledview_mic_3;
        if (rhythmLedView211 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_3");
            rhythmLedView211 = null;
        }
        rhythmLedView211.setMode(0);
        RhythmLedView2 rhythmLedView212 = this.rhyledview_mic_3;
        if (rhythmLedView212 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_3");
            rhythmLedView212 = null;
        }
        rhythmLedView212.removeAllViews();
        RhythmLedView2 rhythmLedView213 = this.rhyledview_mic_3;
        if (rhythmLedView213 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_3");
        } else {
            rhythmLedView22 = rhythmLedView213;
        }
        rhythmLedView22.init(64, 16);
    }

    private final void initRhyUI() {
        RhythmImage rhythmImage = new RhythmImage(R.mipmap.rhythm_picture_16x64_1, false, null, 6, null);
        RhythmImage rhythmImage2 = new RhythmImage(R.mipmap.rhythm_picture_16x64_2, false, null, 6, null);
        RhythmImage rhythmImage3 = new RhythmImage(R.mipmap.rhythm_picture_16x64_3, false, null, 6, null);
        RhythmImage rhythmImage4 = new RhythmImage(R.mipmap.rhythm_picture_16x64_4, false, null, 6, null);
        RhythmImage rhythmImage5 = new RhythmImage(R.mipmap.rhythm_picture_16x64_5, false, null, 6, null);
        this.imageList.add(rhythmImage);
        this.imageList.add(rhythmImage2);
        this.imageList.add(rhythmImage3);
        this.imageList.add(rhythmImage4);
        this.imageList.add(rhythmImage5);
        RelativeLayout relativeLayout = this.rl_mic_root;
        ViewPagerAllResponse viewPagerAllResponse = null;
        if (relativeLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rl_mic_root");
            relativeLayout = null;
        }
        ViewGroup.LayoutParams layoutParams = relativeLayout.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
        ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) layoutParams;
        MicWidescreenActivity micWidescreenActivity = this;
        layoutParams2.width = ScreenUtil.getScreenWidth(micWidescreenActivity);
        RelativeLayout relativeLayout2 = this.rl_mic_root;
        if (relativeLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rl_mic_root");
            relativeLayout2 = null;
        }
        relativeLayout2.setLayoutParams(layoutParams2);
        ViewPagerAllResponse viewPagerAllResponse2 = this.vp_mic_rhyhm;
        if (viewPagerAllResponse2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vp_mic_rhyhm");
            viewPagerAllResponse2 = null;
        }
        ViewGroup.LayoutParams layoutParams3 = viewPagerAllResponse2.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams3, "null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams");
        RelativeLayout.LayoutParams layoutParams4 = (RelativeLayout.LayoutParams) layoutParams3;
        layoutParams4.width = (int) (ScreenUtil.getScreenWidth(micWidescreenActivity) / 1.6d);
        layoutParams4.height = ScreenUtil.getScreenWidth(micWidescreenActivity) / 2;
        ViewPagerAllResponse viewPagerAllResponse3 = this.vp_mic_rhyhm;
        if (viewPagerAllResponse3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vp_mic_rhyhm");
            viewPagerAllResponse3 = null;
        }
        viewPagerAllResponse3.setLayoutParams(layoutParams4);
        ViewPagerAllResponse viewPagerAllResponse4 = this.vp_mic_rhyhm;
        if (viewPagerAllResponse4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vp_mic_rhyhm");
            viewPagerAllResponse4 = null;
        }
        this.textImageIconAdapter = new ViewpagerAdapter(micWidescreenActivity, viewPagerAllResponse4, this.imageList);
        ViewPagerAllResponse viewPagerAllResponse5 = this.vp_mic_rhyhm;
        if (viewPagerAllResponse5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vp_mic_rhyhm");
            viewPagerAllResponse5 = null;
        }
        ViewpagerAdapter viewpagerAdapter = this.textImageIconAdapter;
        if (viewpagerAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("textImageIconAdapter");
            viewpagerAdapter = null;
        }
        viewPagerAllResponse5.setAdapter(viewpagerAdapter);
        ViewPagerAllResponse viewPagerAllResponse6 = this.vp_mic_rhyhm;
        if (viewPagerAllResponse6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vp_mic_rhyhm");
            viewPagerAllResponse6 = null;
        }
        viewPagerAllResponse6.setPageTransformer(true, new ZoomOutPageTransformer());
        ViewPagerAllResponse viewPagerAllResponse7 = this.vp_mic_rhyhm;
        if (viewPagerAllResponse7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vp_mic_rhyhm");
            viewPagerAllResponse7 = null;
        }
        viewPagerAllResponse7.setPageMargin(ScreenUtil.dp2px(micWidescreenActivity, -10.0f));
        ViewPagerAllResponse viewPagerAllResponse8 = this.vp_mic_rhyhm;
        if (viewPagerAllResponse8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vp_mic_rhyhm");
            viewPagerAllResponse8 = null;
        }
        viewPagerAllResponse8.setCurrentItem(4998);
        ViewPagerAllResponse viewPagerAllResponse9 = this.vp_mic_rhyhm;
        if (viewPagerAllResponse9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vp_mic_rhyhm");
            viewPagerAllResponse9 = null;
        }
        viewPagerAllResponse9.setOffscreenPageLimit(2);
        ViewPagerAllResponse viewPagerAllResponse10 = this.vp_mic_rhyhm;
        if (viewPagerAllResponse10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vp_mic_rhyhm");
            viewPagerAllResponse10 = null;
        }
        viewPagerAllResponse10.addOnPageChangeListener(this);
        ViewPagerAllResponse viewPagerAllResponse11 = this.vp_mic_rhyhm;
        if (viewPagerAllResponse11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vp_mic_rhyhm");
        } else {
            viewPagerAllResponse = viewPagerAllResponse11;
        }
        int currentItem = viewPagerAllResponse.getCurrentItem() % this.imageList.size();
        this.curSelectMode = currentItem;
        this.curSelectPosition = currentItem;
    }

    @Override // com.wifiled.ipixels.ui.rhythm.AudioRecorderUtil.onMicDbListener
    public void onCallBackMicDb(double volume) {
        Log.v("ruis", "onCallBackMicDb--" + volume);
        this.m_dVolume = volume;
        this.mVolumeList.add(Byte.valueOf((byte) MathKt.roundToInt((volume * (this.curSelectPosition >= 5 ? 7 : 15)) / 100.0f)));
        boolean z = this.m_bIsMicRecording;
        double d = this.m_dVolume;
        if (z != (d > AudioStats.AUDIO_AMPLITUDE_NONE)) {
            this.m_bIsMicRecording = d > AudioStats.AUDIO_AMPLITUDE_NONE;
            showCurRhyMode(this.curSelectPosition);
            ViewpagerAdapter viewpagerAdapter = this.textImageIconAdapter;
            if (viewpagerAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("textImageIconAdapter");
                viewpagerAdapter = null;
            }
            viewpagerAdapter.setMediaState(this.m_bIsMicRecording);
        }
        if (this.mVolumeList.size() > this.m_iTotalItems) {
            this.mVolumeList.clear();
        }
    }

    @Override // com.wifiled.ipixels.ui.rhythm.AudioRecorderUtil.onMicDbListener
    public void onDataCapture(final byte[] data) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (TimeHelper.INSTANCE.allowSend(100)) {
            switch (this.curSelectPosition) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                    UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.rhythm.MicWidescreenActivity$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            Unit onDataCapture$lambda$3;
                            onDataCapture$lambda$3 = MicWidescreenActivity.onDataCapture$lambda$3(MicWidescreenActivity.this, data);
                            return onDataCapture$lambda$3;
                        }
                    });
                    SendCore.INSTANCE.sendRhythmChart(this.curSelectPosition, ArraysKt.copyOfRange(data, 0, 11));
                    break;
                case 5:
                    runOnUiThread(new Runnable() { // from class: com.wifiled.ipixels.ui.rhythm.MicWidescreenActivity$$ExternalSyntheticLambda1
                        @Override // java.lang.Runnable
                        public final void run() {
                            MicWidescreenActivity.onDataCapture$lambda$4(MicWidescreenActivity.this);
                        }
                    });
                    SendCore.INSTANCE.sendRhythm(MathKt.roundToInt((this.m_dVolume * 7) / 100.0f), 0);
                    break;
                case 6:
                    runOnUiThread(new Runnable() { // from class: com.wifiled.ipixels.ui.rhythm.MicWidescreenActivity$$ExternalSyntheticLambda2
                        @Override // java.lang.Runnable
                        public final void run() {
                            MicWidescreenActivity.onDataCapture$lambda$5(MicWidescreenActivity.this);
                        }
                    });
                    SendCore.INSTANCE.sendRhythm(MathKt.roundToInt((this.m_dVolume * 7) / 100.0f), 1);
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onDataCapture$lambda$3(MicWidescreenActivity micWidescreenActivity, byte[] bArr) {
        micWidescreenActivity.setRhyData(bArr);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onDataCapture$lambda$4(MicWidescreenActivity micWidescreenActivity) {
        RhythmLedView2 rhythmLedView2 = micWidescreenActivity.rhyledview_mic_1;
        ImageView imageView = null;
        if (rhythmLedView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_1");
            rhythmLedView2 = null;
        }
        rhythmLedView2.clearSelected();
        RhythmLedView2 rhythmLedView22 = micWidescreenActivity.rhyledview_mic_2;
        if (rhythmLedView22 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_2");
            rhythmLedView22 = null;
        }
        rhythmLedView22.clearSelected();
        ImageView imageView2 = micWidescreenActivity.iv_mic_rhy_image_bg;
        if (imageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_mic_rhy_image_bg");
        } else {
            imageView = imageView2;
        }
        UtilsExtensionKt.hide(imageView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onDataCapture$lambda$5(MicWidescreenActivity micWidescreenActivity) {
        RhythmLedView2 rhythmLedView2 = micWidescreenActivity.rhyledview_mic_1;
        ImageView imageView = null;
        if (rhythmLedView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_1");
            rhythmLedView2 = null;
        }
        rhythmLedView2.clearSelected();
        RhythmLedView2 rhythmLedView22 = micWidescreenActivity.rhyledview_mic_2;
        if (rhythmLedView22 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_2");
            rhythmLedView22 = null;
        }
        rhythmLedView22.clearSelected();
        ImageView imageView2 = micWidescreenActivity.iv_mic_rhy_image_bg;
        if (imageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_mic_rhy_image_bg");
        } else {
            imageView = imageView2;
        }
        UtilsExtensionKt.hide(imageView);
    }

    private final void setRhyData(byte[] data) {
        RhythmLedView2 rhythmLedView2 = null;
        switch (this.curSelectMode) {
            case 0:
                List<Integer> rhyData1_wide = RhythmDataUitls.getRhyData1_wide(data);
                Intrinsics.checkNotNullExpressionValue(rhyData1_wide, "getRhyData1_wide(...)");
                RhythmLedView2 rhythmLedView22 = this.rhyledview_mic_1;
                if (rhythmLedView22 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_1");
                    rhythmLedView22 = null;
                }
                rhythmLedView22.updateRhythmUI(rhyData1_wide);
                RhythmLedView2 rhythmLedView23 = this.rhyledview_mic_2;
                if (rhythmLedView23 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_2");
                } else {
                    rhythmLedView2 = rhythmLedView23;
                }
                rhythmLedView2.updateRhythmUI(rhyData1_wide);
                break;
            case 1:
                List<Integer> rhyData2_wide = RhythmDataUitls.getRhyData2_wide(data);
                Intrinsics.checkNotNullExpressionValue(rhyData2_wide, "getRhyData2_wide(...)");
                RhythmLedView2 rhythmLedView24 = this.rhyledview_mic_1;
                if (rhythmLedView24 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_1");
                    rhythmLedView24 = null;
                }
                rhythmLedView24.setRhyData2(rhyData2_wide);
                RhythmLedView2 rhythmLedView25 = this.rhyledview_mic_2;
                if (rhythmLedView25 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_2");
                } else {
                    rhythmLedView2 = rhythmLedView25;
                }
                rhythmLedView2.setRhyData2(rhyData2_wide);
                break;
            case 2:
                List<Integer> rhyData3_wide = RhythmDataUitls.getRhyData3_wide(data);
                Intrinsics.checkNotNullExpressionValue(rhyData3_wide, "getRhyData3_wide(...)");
                RhythmLedView2 rhythmLedView26 = this.rhyledview_mic_1;
                if (rhythmLedView26 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_1");
                    rhythmLedView26 = null;
                }
                rhythmLedView26.setRhyData3(rhyData3_wide);
                RhythmLedView2 rhythmLedView27 = this.rhyledview_mic_2;
                if (rhythmLedView27 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_2");
                } else {
                    rhythmLedView2 = rhythmLedView27;
                }
                rhythmLedView2.setRhyData3(rhyData3_wide);
                break;
            case 3:
                List<Integer> rhyData4_wide = RhythmDataUitls.getRhyData4_wide(data);
                Intrinsics.checkNotNullExpressionValue(rhyData4_wide, "getRhyData4_wide(...)");
                RhythmLedView2 rhythmLedView28 = this.rhyledview_mic_3;
                if (rhythmLedView28 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_3");
                } else {
                    rhythmLedView2 = rhythmLedView28;
                }
                rhythmLedView2.setRhyData4(rhyData4_wide);
                break;
            case 4:
                List<Integer> rhyData5_wide = RhythmDataUitls.getRhyData5_wide(data);
                Intrinsics.checkNotNullExpressionValue(rhyData5_wide, "getRhyData5_wide(...)");
                RhythmLedView2 rhythmLedView29 = this.rhyledview_mic_1;
                if (rhythmLedView29 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_1");
                    rhythmLedView29 = null;
                }
                rhythmLedView29.setRhyData5(rhyData5_wide);
                RhythmLedView2 rhythmLedView210 = this.rhyledview_mic_2;
                if (rhythmLedView210 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_2");
                } else {
                    rhythmLedView2 = rhythmLedView210;
                }
                rhythmLedView2.setRhyData5(rhyData5_wide);
                break;
            case 5:
            case 6:
                runOnUiThread(new Runnable() { // from class: com.wifiled.ipixels.ui.rhythm.MicWidescreenActivity$$ExternalSyntheticLambda3
                    @Override // java.lang.Runnable
                    public final void run() {
                        MicWidescreenActivity.setRhyData$lambda$6(MicWidescreenActivity.this);
                    }
                });
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setRhyData$lambda$6(MicWidescreenActivity micWidescreenActivity) {
        RhythmLedView2 rhythmLedView2 = micWidescreenActivity.rhyledview_mic_1;
        ImageView imageView = null;
        if (rhythmLedView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_1");
            rhythmLedView2 = null;
        }
        rhythmLedView2.clearSelected();
        RhythmLedView2 rhythmLedView22 = micWidescreenActivity.rhyledview_mic_2;
        if (rhythmLedView22 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_2");
            rhythmLedView22 = null;
        }
        rhythmLedView22.clearSelected();
        ImageView imageView2 = micWidescreenActivity.iv_mic_rhy_image_bg;
        if (imageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_mic_rhy_image_bg");
        } else {
            imageView = imageView2;
        }
        UtilsExtensionKt.hide(imageView);
    }

    @Override // com.wifiled.musiclib.player.callback.RecordDataCallBack
    public void onRecordData(short[] buffer) {
        Log.v("ruis", "onRecordData--" + buffer);
        Intrinsics.checkNotNull(buffer);
        long j = 0;
        for (short s : buffer) {
            j += s * s;
        }
        int length = (buffer.length / 2) + 1;
        byte[] bArr = new byte[length];
        bArr[0] = (byte) (buffer[1] & 255);
        IntProgression step = RangesKt.step(RangesKt.until(2, length), 8);
        int first = step.getFirst();
        int last = step.getLast();
        int step2 = step.getStep();
        if ((step2 > 0 && first <= last) || (step2 < 0 && last <= first)) {
            int i = 1;
            while (true) {
                bArr[i] = (byte) Math.hypot(buffer[first], buffer[first + 1]);
                i++;
                if (first == last) {
                    break;
                } else {
                    first += step2;
                }
            }
        }
        onCallBackMicDb(10 * Math.log10(j / buffer.length));
        byte[] levelByWaveData = FftUtils.getLevelByWaveData(buffer);
        Intrinsics.checkNotNull(levelByWaveData);
        onDataCapture(levelByWaveData);
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
