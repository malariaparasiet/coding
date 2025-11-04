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
import com.wifiled.ipixels.view.RhythmLedView;
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

/* compiled from: MicTestActivity.kt */
@Metadata(d1 = {"\u0000\u009c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\u0017\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004B\u0007¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010&\u001a\u00020\rH\u0014J\b\u0010'\u001a\u00020(H\u0014J\b\u0010)\u001a\u00020(H\u0014J\b\u0010*\u001a\u00020(H\u0014J\b\u0010+\u001a\u00020(H\u0014J\b\u0010,\u001a\u00020(H\u0014J \u0010-\u001a\u00020(2\u0006\u0010.\u001a\u00020\r2\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u00020\rH\u0016J\u0010\u00102\u001a\u00020(2\u0006\u0010.\u001a\u00020\rH\u0016J\u0010\u00103\u001a\u00020(2\u0006\u00104\u001a\u00020\rH\u0016J\u0010\u00105\u001a\u00020(2\u0006\u00106\u001a\u00020\rH\u0002J\u0010\u00107\u001a\u00020(2\u0006\u00108\u001a\u00020\rH\u0002J\u0010\u00109\u001a\u00020(2\u0006\u0010:\u001a\u00020\u0014H\u0002J\b\u0010=\u001a\u00020(H\u0002J\u0006\u0010>\u001a\u00020(J\b\u0010?\u001a\u00020(H\u0002J\b\u0010@\u001a\u00020(H\u0002J\u0010\u0010A\u001a\u00020(2\u0006\u0010B\u001a\u00020\u0012H\u0016J\u0010\u0010C\u001a\u00020(2\u0006\u0010D\u001a\u00020EH\u0016J\u0010\u0010F\u001a\u00020(2\u0006\u0010D\u001a\u00020EH\u0002J\u0012\u0010G\u001a\u00020(2\b\u0010H\u001a\u0004\u0018\u00010IH\u0016J\u000e\u0010J\u001a\u00020E2\u0006\u0010K\u001a\u00020IR\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0015\u001a\u0012\u0012\u0004\u0012\u00020\u00170\u0016j\b\u0012\u0004\u0012\u00020\u0017`\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001cX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u001fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010;\u001a\u00020<X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006L"}, d2 = {"Lcom/wifiled/ipixels/ui/rhythm/MicTestActivity;", "Lcom/wifiled/baselib/base/BaseActivity;", "Landroidx/viewpager/widget/ViewPager$OnPageChangeListener;", "Lcom/wifiled/ipixels/ui/rhythm/AudioRecorderUtil$onMicDbListener;", "Lcom/wifiled/musiclib/player/callback/RecordDataCallBack;", "<init>", "()V", "imageList", "", "Lcom/wifiled/ipixels/bean/RhythmImage;", "textImageIconAdapter", "Lcom/wifiled/ipixels/ui/adapter/ViewpagerAdapter;", "curSelectPosition", "", "curSelectMode", "handler", "Landroid/os/Handler;", "m_dVolume", "", "m_bIsMicRecording", "", "mVolumeList", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "iv_mic_rhy_image_bg", "Landroid/widget/ImageView;", "rhyledview_mic_1", "Lcom/wifiled/ipixels/view/RhythmLedView;", "rhyledview_mic_2", "rl_mic_rhy_bg", "Landroid/widget/RelativeLayout;", "rl_mic_root", "vp_mic_rhyhm", "Lcom/wifiled/ipixels/view/ViewPagerAllResponse;", "iv_back", "Lcom/wifiled/ipixels/view/customview/CustomImageView;", "m_iTotalItems", "layoutId", "initView", "", "onDestroy", "bindData", "onRestart", "onResume", "onPageScrolled", PlayerFinal.PLAYER_POSITION, "positionOffset", "", "positionOffsetPixels", "onPageSelected", "onPageScrollStateChanged", PlayerFinal.STATE, "showCurRhyMode", "currentImage", "selectRhythmMode", "selectMode", "rhyUIShow", "isVisible", "runnable", "Ljava/lang/Runnable;", "setRecordDataCallBack", "initToolBar", "initData", "initRhyUI", "onCallBackMicDb", "volume", "onDataCapture", "data", "", "setRhyData", "onRecordData", "buffer", "", "toByteArray", "src", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MicTestActivity extends BaseActivity implements ViewPager.OnPageChangeListener, AudioRecorderUtil.onMicDbListener, RecordDataCallBack {
    private int curSelectMode;
    private int curSelectPosition;
    private CustomImageView iv_back;
    private ImageView iv_mic_rhy_image_bg;
    private boolean m_bIsMicRecording;
    private double m_dVolume;
    private RhythmLedView rhyledview_mic_1;
    private RhythmLedView rhyledview_mic_2;
    private RelativeLayout rl_mic_rhy_bg;
    private RelativeLayout rl_mic_root;
    private ViewpagerAdapter textImageIconAdapter;
    private ViewPagerAllResponse vp_mic_rhyhm;
    private final List<RhythmImage> imageList = new ArrayList();
    private final Handler handler = new Handler();
    private ArrayList<Byte> mVolumeList = new ArrayList<>();
    private int m_iTotalItems = 17;
    private Runnable runnable = new Runnable() { // from class: com.wifiled.ipixels.ui.rhythm.MicTestActivity$$ExternalSyntheticLambda0
        @Override // java.lang.Runnable
        public final void run() {
            MicTestActivity.runnable$lambda$1(MicTestActivity.this);
        }
    };

    @Override // com.wifiled.baselib.base.BaseActivity
    protected int layoutId() {
        return R.layout.activity_mic_test;
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override // com.wifiled.baselib.base.BaseActivity
    protected void initView() {
        super.initView();
        View findViewById = findViewById(R.id.iv_mic_rhy_image_bg);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.iv_mic_rhy_image_bg = (ImageView) findViewById;
        View findViewById2 = findViewById(R.id.rhyledview_mic_1);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.rhyledview_mic_1 = (RhythmLedView) findViewById2;
        View findViewById3 = findViewById(R.id.rhyledview_mic_2);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.rhyledview_mic_2 = (RhythmLedView) findViewById3;
        View findViewById4 = findViewById(R.id.rl_mic_rhy_bg);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
        this.rl_mic_rhy_bg = (RelativeLayout) findViewById4;
        View findViewById5 = findViewById(R.id.rl_mic_root);
        Intrinsics.checkNotNullExpressionValue(findViewById5, "findViewById(...)");
        this.rl_mic_root = (RelativeLayout) findViewById5;
        View findViewById6 = findViewById(R.id.vp_mic_rhyhm);
        Intrinsics.checkNotNullExpressionValue(findViewById6, "findViewById(...)");
        this.vp_mic_rhyhm = (ViewPagerAllResponse) findViewById6;
        View findViewById7 = findViewById(R.id.iv_back);
        Intrinsics.checkNotNullExpressionValue(findViewById7, "findViewById(...)");
        this.iv_back = (CustomImageView) findViewById7;
    }

    @Override // com.wifiled.baselib.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        AppConfig appConfig = AppConfig.INSTANCE;
        AppConfig.isOnly = false;
        MusicManager.getInstance().unsetRecordDataCallBack();
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

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        AppConfig appConfig = AppConfig.INSTANCE;
        AppConfig.isOnly = true;
        super.onResume();
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
        RhythmLedView rhythmLedView = null;
        RelativeLayout relativeLayout = null;
        RhythmLedView rhythmLedView2 = null;
        RhythmLedView rhythmLedView3 = null;
        RhythmLedView rhythmLedView4 = null;
        if (currentImage == 0) {
            RelativeLayout relativeLayout2 = this.rl_mic_rhy_bg;
            if (relativeLayout2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rl_mic_rhy_bg");
                relativeLayout2 = null;
            }
            relativeLayout2.setRotation(0.0f);
            RhythmLedView rhythmLedView5 = this.rhyledview_mic_1;
            if (rhythmLedView5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_1");
                rhythmLedView5 = null;
            }
            rhythmLedView5.setRotationX(0.0f);
            RhythmLedView rhythmLedView6 = this.rhyledview_mic_2;
            if (rhythmLedView6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_2");
            } else {
                rhythmLedView = rhythmLedView6;
            }
            rhythmLedView.setRotationX(180.0f);
            selectRhythmMode(1);
            this.m_iTotalItems = 17;
            return;
        }
        if (currentImage == 1) {
            RelativeLayout relativeLayout3 = this.rl_mic_rhy_bg;
            if (relativeLayout3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rl_mic_rhy_bg");
                relativeLayout3 = null;
            }
            relativeLayout3.setRotation(0.0f);
            RhythmLedView rhythmLedView7 = this.rhyledview_mic_1;
            if (rhythmLedView7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_1");
                rhythmLedView7 = null;
            }
            rhythmLedView7.setRotationX(0.0f);
            RhythmLedView rhythmLedView8 = this.rhyledview_mic_2;
            if (rhythmLedView8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_2");
            } else {
                rhythmLedView4 = rhythmLedView8;
            }
            rhythmLedView4.setRotationX(180.0f);
            selectRhythmMode(2);
            this.m_iTotalItems = 25;
            return;
        }
        if (currentImage == 2) {
            RelativeLayout relativeLayout4 = this.rl_mic_rhy_bg;
            if (relativeLayout4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rl_mic_rhy_bg");
                relativeLayout4 = null;
            }
            relativeLayout4.setRotation(0.0f);
            RhythmLedView rhythmLedView9 = this.rhyledview_mic_1;
            if (rhythmLedView9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_1");
                rhythmLedView9 = null;
            }
            rhythmLedView9.setRotationX(0.0f);
            RhythmLedView rhythmLedView10 = this.rhyledview_mic_2;
            if (rhythmLedView10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_2");
            } else {
                rhythmLedView3 = rhythmLedView10;
            }
            rhythmLedView3.setRotationX(180.0f);
            selectRhythmMode(3);
            this.m_iTotalItems = 26;
            return;
        }
        if (currentImage == 3) {
            RelativeLayout relativeLayout5 = this.rl_mic_rhy_bg;
            if (relativeLayout5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rl_mic_rhy_bg");
                relativeLayout5 = null;
            }
            relativeLayout5.setRotation(0.0f);
            RhythmLedView rhythmLedView11 = this.rhyledview_mic_1;
            if (rhythmLedView11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_1");
                rhythmLedView11 = null;
            }
            rhythmLedView11.setRotationX(180.0f);
            RhythmLedView rhythmLedView12 = this.rhyledview_mic_2;
            if (rhythmLedView12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_2");
            } else {
                rhythmLedView2 = rhythmLedView12;
            }
            rhythmLedView2.setRotationX(0.0f);
            selectRhythmMode(4);
            this.m_iTotalItems = 26;
            return;
        }
        if (currentImage == 4) {
            RhythmLedView rhythmLedView13 = this.rhyledview_mic_1;
            if (rhythmLedView13 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_1");
                rhythmLedView13 = null;
            }
            rhythmLedView13.setRotationX(0.0f);
            RhythmLedView rhythmLedView14 = this.rhyledview_mic_2;
            if (rhythmLedView14 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_2");
                rhythmLedView14 = null;
            }
            rhythmLedView14.setRotationX(180.0f);
            RelativeLayout relativeLayout6 = this.rl_mic_rhy_bg;
            if (relativeLayout6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rl_mic_rhy_bg");
            } else {
                relativeLayout = relativeLayout6;
            }
            relativeLayout.setRotation(90.0f);
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
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.rhythm.MicTestActivity$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit rhyUIShow$lambda$0;
                    rhyUIShow$lambda$0 = MicTestActivity.rhyUIShow$lambda$0(MicTestActivity.this);
                    return rhyUIShow$lambda$0;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit rhyUIShow$lambda$0(MicTestActivity micTestActivity) {
        RhythmLedView rhythmLedView = micTestActivity.rhyledview_mic_1;
        ViewpagerAdapter viewpagerAdapter = null;
        if (rhythmLedView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_1");
            rhythmLedView = null;
        }
        UtilsExtensionKt.hide(rhythmLedView);
        RhythmLedView rhythmLedView2 = micTestActivity.rhyledview_mic_2;
        if (rhythmLedView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_2");
            rhythmLedView2 = null;
        }
        UtilsExtensionKt.hide(rhythmLedView2);
        ImageView imageView = micTestActivity.iv_mic_rhy_image_bg;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_mic_rhy_image_bg");
            imageView = null;
        }
        UtilsExtensionKt.hide(imageView);
        ViewpagerAdapter viewpagerAdapter2 = micTestActivity.textImageIconAdapter;
        if (viewpagerAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("textImageIconAdapter");
        } else {
            viewpagerAdapter = viewpagerAdapter2;
        }
        viewpagerAdapter.notifyDataSetChanged();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void runnable$lambda$1(MicTestActivity micTestActivity) {
        RhythmLedView rhythmLedView = micTestActivity.rhyledview_mic_1;
        ViewpagerAdapter viewpagerAdapter = null;
        if (rhythmLedView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_1");
            rhythmLedView = null;
        }
        UtilsExtensionKt.show(rhythmLedView);
        RhythmLedView rhythmLedView2 = micTestActivity.rhyledview_mic_2;
        if (rhythmLedView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_2");
            rhythmLedView2 = null;
        }
        UtilsExtensionKt.show(rhythmLedView2);
        ImageView imageView = micTestActivity.iv_mic_rhy_image_bg;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_mic_rhy_image_bg");
            imageView = null;
        }
        UtilsExtensionKt.show(imageView);
        LogUtils.logi(micTestActivity.TAG + ">>>[curSelectPosition]:  " + micTestActivity.curSelectPosition, new Object[0]);
        ViewpagerAdapter viewpagerAdapter2 = micTestActivity.textImageIconAdapter;
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

    public final void initToolBar() {
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
        customImageView4.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.rhythm.MicTestActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MicTestActivity.this.finish();
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
        RhythmLedView rhythmLedView = this.rhyledview_mic_1;
        RhythmLedView rhythmLedView2 = null;
        if (rhythmLedView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_1");
            rhythmLedView = null;
        }
        rhythmLedView.setLayerType(1, null);
        RhythmLedView rhythmLedView3 = this.rhyledview_mic_1;
        if (rhythmLedView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_1");
            rhythmLedView3 = null;
        }
        rhythmLedView3.setMode(0);
        RhythmLedView rhythmLedView4 = this.rhyledview_mic_1;
        if (rhythmLedView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_1");
            rhythmLedView4 = null;
        }
        rhythmLedView4.setPointMargin(0);
        RhythmLedView rhythmLedView5 = this.rhyledview_mic_1;
        if (rhythmLedView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_1");
            rhythmLedView5 = null;
        }
        rhythmLedView5.removeAllViews();
        RhythmLedView rhythmLedView6 = this.rhyledview_mic_1;
        if (rhythmLedView6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_1");
            rhythmLedView6 = null;
        }
        rhythmLedView6.init(52, 32);
        RhythmLedView rhythmLedView7 = this.rhyledview_mic_2;
        if (rhythmLedView7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_2");
            rhythmLedView7 = null;
        }
        rhythmLedView7.setLayerType(1, null);
        RhythmLedView rhythmLedView8 = this.rhyledview_mic_2;
        if (rhythmLedView8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_2");
            rhythmLedView8 = null;
        }
        rhythmLedView8.setMode(0);
        RhythmLedView rhythmLedView9 = this.rhyledview_mic_2;
        if (rhythmLedView9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_2");
            rhythmLedView9 = null;
        }
        rhythmLedView9.setPointMargin(0);
        RhythmLedView rhythmLedView10 = this.rhyledview_mic_2;
        if (rhythmLedView10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_2");
            rhythmLedView10 = null;
        }
        rhythmLedView10.removeAllViews();
        RhythmLedView rhythmLedView11 = this.rhyledview_mic_2;
        if (rhythmLedView11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_2");
        } else {
            rhythmLedView2 = rhythmLedView11;
        }
        rhythmLedView2.init(52, 32);
    }

    private final void initRhyUI() {
        RhythmImage rhythmImage = new RhythmImage(R.mipmap.rhyhm_mode_bg1, false, null, 6, null);
        RhythmImage rhythmImage2 = new RhythmImage(R.mipmap.rhyhm_mode_bg2, false, null, 6, null);
        RhythmImage rhythmImage3 = new RhythmImage(R.mipmap.rhyhm_mode_bg3, false, null, 6, null);
        RhythmImage rhythmImage4 = new RhythmImage(R.mipmap.rhyhm_mode_bg4, false, null, 6, null);
        RhythmImage rhythmImage5 = new RhythmImage(R.mipmap.rhyhm_mode_bg5, false, null, 6, null);
        RhythmImage rhythmImage6 = new RhythmImage(R.mipmap.rhyhm_mode_bg6, false, null, 6, null);
        RhythmImage rhythmImage7 = new RhythmImage(R.mipmap.rhyhm_mode_bg7, false, null, 6, null);
        this.imageList.add(rhythmImage);
        this.imageList.add(rhythmImage2);
        this.imageList.add(rhythmImage3);
        this.imageList.add(rhythmImage4);
        this.imageList.add(rhythmImage5);
        this.imageList.add(rhythmImage7);
        this.imageList.add(rhythmImage6);
        RelativeLayout relativeLayout = this.rl_mic_root;
        ViewPagerAllResponse viewPagerAllResponse = null;
        if (relativeLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rl_mic_root");
            relativeLayout = null;
        }
        ViewGroup.LayoutParams layoutParams = relativeLayout.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
        ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) layoutParams;
        MicTestActivity micTestActivity = this;
        layoutParams2.width = ScreenUtil.getScreenWidth(micTestActivity);
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
        layoutParams4.width = (int) (ScreenUtil.getScreenWidth(micTestActivity) / 1.6d);
        layoutParams4.height = (int) (ScreenUtil.getScreenWidth(micTestActivity) / 1.6d);
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
        this.textImageIconAdapter = new ViewpagerAdapter(micTestActivity, viewPagerAllResponse4, this.imageList);
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
        viewPagerAllResponse7.setPageMargin(ScreenUtil.dp2px(micTestActivity, -30.0f));
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
        System.currentTimeMillis();
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
                    UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.rhythm.MicTestActivity$$ExternalSyntheticLambda4
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            Unit onDataCapture$lambda$3;
                            onDataCapture$lambda$3 = MicTestActivity.onDataCapture$lambda$3(MicTestActivity.this, data);
                            return onDataCapture$lambda$3;
                        }
                    });
                    byte[] copyOfRange = ArraysKt.copyOfRange(data, 0, 11);
                    System.currentTimeMillis();
                    SendCore.INSTANCE.sendRhythmChart(this.curSelectPosition, copyOfRange);
                    break;
                case 5:
                case 6:
                    runOnUiThread(new Runnable() { // from class: com.wifiled.ipixels.ui.rhythm.MicTestActivity$$ExternalSyntheticLambda5
                        @Override // java.lang.Runnable
                        public final void run() {
                            MicTestActivity.onDataCapture$lambda$4(MicTestActivity.this);
                        }
                    });
                    int i = this.curSelectPosition;
                    SendCore.INSTANCE.sendRhythm(MathKt.roundToInt((this.m_dVolume * 7) / 100.0f), i != 5 ? i != 6 ? -1 : i - 6 : i - 4);
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onDataCapture$lambda$3(MicTestActivity micTestActivity, byte[] bArr) {
        micTestActivity.setRhyData(bArr);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onDataCapture$lambda$4(MicTestActivity micTestActivity) {
        RhythmLedView rhythmLedView = micTestActivity.rhyledview_mic_1;
        ImageView imageView = null;
        if (rhythmLedView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_1");
            rhythmLedView = null;
        }
        rhythmLedView.clearSelected();
        RhythmLedView rhythmLedView2 = micTestActivity.rhyledview_mic_2;
        if (rhythmLedView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_2");
            rhythmLedView2 = null;
        }
        rhythmLedView2.clearSelected();
        ImageView imageView2 = micTestActivity.iv_mic_rhy_image_bg;
        if (imageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_mic_rhy_image_bg");
        } else {
            imageView = imageView2;
        }
        UtilsExtensionKt.hide(imageView);
    }

    private final void setRhyData(byte[] data) {
        RhythmLedView rhythmLedView = null;
        switch (this.curSelectMode) {
            case 0:
                List<Integer> rhyData1 = RhythmDataUitls.getRhyData1(data);
                Intrinsics.checkNotNullExpressionValue(rhyData1, "getRhyData1(...)");
                RhythmLedView rhythmLedView2 = this.rhyledview_mic_1;
                if (rhythmLedView2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_1");
                    rhythmLedView2 = null;
                }
                rhythmLedView2.updateRhythmUI(rhyData1);
                RhythmLedView rhythmLedView3 = this.rhyledview_mic_2;
                if (rhythmLedView3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_2");
                } else {
                    rhythmLedView = rhythmLedView3;
                }
                rhythmLedView.updateRhythmUI(rhyData1);
                break;
            case 1:
                List<Integer> rhyData2 = RhythmDataUitls.getRhyData2(data);
                Intrinsics.checkNotNullExpressionValue(rhyData2, "getRhyData2(...)");
                RhythmLedView rhythmLedView4 = this.rhyledview_mic_1;
                if (rhythmLedView4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_1");
                    rhythmLedView4 = null;
                }
                rhythmLedView4.setRhyData2(rhyData2);
                RhythmLedView rhythmLedView5 = this.rhyledview_mic_2;
                if (rhythmLedView5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_2");
                } else {
                    rhythmLedView = rhythmLedView5;
                }
                rhythmLedView.setRhyData2(rhyData2);
                break;
            case 2:
                List<Integer> rhyData3 = RhythmDataUitls.getRhyData3(data);
                Intrinsics.checkNotNullExpressionValue(rhyData3, "getRhyData3(...)");
                RhythmLedView rhythmLedView6 = this.rhyledview_mic_1;
                if (rhythmLedView6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_1");
                    rhythmLedView6 = null;
                }
                rhythmLedView6.setRhyData3(rhyData3);
                RhythmLedView rhythmLedView7 = this.rhyledview_mic_2;
                if (rhythmLedView7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_2");
                } else {
                    rhythmLedView = rhythmLedView7;
                }
                rhythmLedView.setRhyData3(rhyData3);
                break;
            case 3:
                List<Integer> rhyData4 = RhythmDataUitls.getRhyData4(data);
                Intrinsics.checkNotNullExpressionValue(rhyData4, "getRhyData4(...)");
                RhythmLedView rhythmLedView8 = this.rhyledview_mic_1;
                if (rhythmLedView8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_1");
                    rhythmLedView8 = null;
                }
                rhythmLedView8.setRhyData4(rhyData4);
                RhythmLedView rhythmLedView9 = this.rhyledview_mic_2;
                if (rhythmLedView9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_2");
                } else {
                    rhythmLedView = rhythmLedView9;
                }
                rhythmLedView.setRhyData4(rhyData4);
                break;
            case 4:
                List<Integer> rhyData5 = RhythmDataUitls.getRhyData5(data);
                Intrinsics.checkNotNullExpressionValue(rhyData5, "getRhyData5(...)");
                RhythmLedView rhythmLedView10 = this.rhyledview_mic_1;
                if (rhythmLedView10 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_1");
                    rhythmLedView10 = null;
                }
                rhythmLedView10.setRhyData5(rhyData5);
                RhythmLedView rhythmLedView11 = this.rhyledview_mic_2;
                if (rhythmLedView11 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_2");
                } else {
                    rhythmLedView = rhythmLedView11;
                }
                rhythmLedView.setRhyData5(rhyData5);
                break;
            case 5:
            case 6:
                runOnUiThread(new Runnable() { // from class: com.wifiled.ipixels.ui.rhythm.MicTestActivity$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        MicTestActivity.setRhyData$lambda$5(MicTestActivity.this);
                    }
                });
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setRhyData$lambda$5(MicTestActivity micTestActivity) {
        RhythmLedView rhythmLedView = micTestActivity.rhyledview_mic_1;
        ImageView imageView = null;
        if (rhythmLedView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_1");
            rhythmLedView = null;
        }
        rhythmLedView.clearSelected();
        RhythmLedView rhythmLedView2 = micTestActivity.rhyledview_mic_2;
        if (rhythmLedView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_mic_2");
            rhythmLedView2 = null;
        }
        rhythmLedView2.clearSelected();
        ImageView imageView2 = micTestActivity.iv_mic_rhy_image_bg;
        if (imageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_mic_rhy_image_bg");
        } else {
            imageView = imageView2;
        }
        UtilsExtensionKt.hide(imageView);
    }

    @Override // com.wifiled.musiclib.player.callback.RecordDataCallBack
    public void onRecordData(short[] buffer) {
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
