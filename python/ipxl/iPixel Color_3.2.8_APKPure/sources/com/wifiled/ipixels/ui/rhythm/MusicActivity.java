package com.wifiled.ipixels.ui.rhythm;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.camera.camera2.internal.compat.CameraAccessExceptionCompat;
import androidx.camera.core.CameraInfo;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.blankj.utilcode.util.LogUtils;
import com.wifiled.baselib.app.language.LanguageUtil;
import com.wifiled.baselib.base.BaseActivity;
import com.wifiled.baselib.base.recycleview.RecyclerAdapter;
import com.wifiled.baselib.utils.DialogUtils;
import com.wifiled.baselib.utils.ScreenUtil;
import com.wifiled.ipixels.App;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.UtilsExtensionKt;
import com.wifiled.ipixels.bean.RhythmImage;
import com.wifiled.ipixels.core.MusicCore;
import com.wifiled.ipixels.core.SendCore;
import com.wifiled.ipixels.ui.adapter.MusicAdapter;
import com.wifiled.ipixels.ui.adapter.ViewpagerAdapter;
import com.wifiled.ipixels.ui.dialog.CustomDialog;
import com.wifiled.ipixels.utils.RhythmDataUitls;
import com.wifiled.ipixels.view.RhythmLedView;
import com.wifiled.ipixels.view.ViewPagerAllResponse;
import com.wifiled.ipixels.view.ZoomOutPageTransformer;
import com.wifiled.ipixels.view.customview.CustomImageView;
import com.wifiled.musiclib.MusicManager;
import com.wifiled.musiclib.player.constant.PlayerFinal;
import com.wifiled.musiclib.vo.MusicVO;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import pub.devrel.easypermissions.EasyPermissions;

/* compiled from: MusicActivity.kt */
@Metadata(d1 = {"\u0000Ù\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0010\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002*\u0001Z\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H\u0014J\b\u0010H\u001a\u00020IH\u0014J\b\u0010J\u001a\u00020IH\u0014J\b\u0010K\u001a\u00020IH\u0014J\b\u0010L\u001a\u00020IH\u0002J\b\u0010M\u001a\u00020IH\u0002J\b\u0010N\u001a\u00020IH\u0002J\b\u0010O\u001a\u00020IH\u0002J\b\u0010P\u001a\u00020IH\u0016J\b\u0010Q\u001a\u00020IH\u0014J\b\u0010R\u001a\u00020IH\u0002J\b\u0010S\u001a\u00020IH\u0014J\b\u0010T\u001a\u00020IH\u0002J\b\u0010U\u001a\u00020IH\u0003J\b\u0010V\u001a\u00020IH\u0015J\b\u0010W\u001a\u00020IH\u0002J\b\u0010X\u001a\u00020IH\u0002J\u0014\u0010\\\u001a\u00020I2\f\u0010]\u001a\b\u0012\u0004\u0012\u00020\u00130^J \u0010_\u001a\u00020I2\u0006\u0010`\u001a\u00020\u00072\u0006\u0010a\u001a\u00020b2\u0006\u0010c\u001a\u00020\u0007H\u0016J\u0010\u0010d\u001a\u00020I2\u0006\u0010`\u001a\u00020\u0007H\u0016J\u0010\u0010e\u001a\u00020I2\u0006\u0010f\u001a\u00020\u0007H\u0016J\u0010\u0010g\u001a\u00020I2\u0006\u0010h\u001a\u00020'H\u0002J\u0010\u0010k\u001a\u00020I2\u0006\u0010l\u001a\u00020\u0007H\u0002J\u0010\u0010m\u001a\u00020I2\u0006\u0010n\u001a\u00020\u0007H\u0002J\u0010\u0010o\u001a\u00020I2\u0006\u0010p\u001a\u00020qH\u0002J\u001e\u0010r\u001a\u00020I2\u0006\u0010s\u001a\u00020\u00072\f\u0010t\u001a\b\u0012\u0004\u0012\u00020u0\u0012H\u0016J\u001e\u0010v\u001a\u00020I2\u0006\u0010s\u001a\u00020\u00072\f\u0010t\u001a\b\u0012\u0004\u0012\u00020u0\u0012H\u0016R#\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010!\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u000e\u0010&\u001a\u00020'X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010)\u001a\u0004\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020,X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020,X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020/X\u0082.¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u000201X\u0082.¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020/X\u0082.¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020/X\u0082.¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020/X\u0082.¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u00020/X\u0082.¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u000207X\u0082.¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u000209X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u00020;X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010<\u001a\u00020=X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010>\u001a\u00020=X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010?\u001a\u00020@X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010A\u001a\u00020BX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010C\u001a\u00020BX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010D\u001a\u00020BX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010E\u001a\u00020BX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010F\u001a\u00020BX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010G\u001a\u00020BX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010Y\u001a\u00020ZX\u0082\u0004¢\u0006\u0004\n\u0002\u0010[R\u000e\u0010i\u001a\u00020jX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006w"}, d2 = {"Lcom/wifiled/ipixels/ui/rhythm/MusicActivity;", "Lcom/wifiled/baselib/base/BaseActivity;", "Landroidx/viewpager/widget/ViewPager$OnPageChangeListener;", "Lpub/devrel/easypermissions/EasyPermissions$PermissionCallbacks;", "<init>", "()V", "layoutId", "", "musicManager", "Lcom/wifiled/musiclib/MusicManager;", "kotlin.jvm.PlatformType", "getMusicManager", "()Lcom/wifiled/musiclib/MusicManager;", "musicManager$delegate", "Lkotlin/Lazy;", "musicAdapter", "Lcom/wifiled/ipixels/ui/adapter/MusicAdapter;", "musicData", "", "Lcom/wifiled/musiclib/vo/MusicVO;", "currentPlayMode", "musicItemDialog", "Landroid/app/Dialog;", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "imageList", "Lcom/wifiled/ipixels/bean/RhythmImage;", "textImageIconAdapter", "Lcom/wifiled/ipixels/ui/adapter/ViewpagerAdapter;", "curSelectPosition", "curSelectMode", "handler", "Landroid/os/Handler;", "iLevel", "getILevel", "()I", "setILevel", "(I)V", "isPlay", "", "SEEK_SCOPE", "builder", "Lcom/wifiled/ipixels/ui/dialog/CustomDialog$Builder;", "iv_right", "Lcom/wifiled/ipixels/view/customview/CustomImageView;", "iv_back", "tv_title", "Landroid/widget/TextView;", "vp_rhyhm", "Lcom/wifiled/ipixels/view/ViewPagerAllResponse;", "tv_song_name", "tv_song_artist", "tv_sb_time", "tv_sb_duration", "sb", "Landroid/widget/SeekBar;", "rl_root", "Landroid/widget/RelativeLayout;", "rl_rhy_bg", "Landroid/widget/LinearLayout;", "rhyledview_2", "Lcom/wifiled/ipixels/view/RhythmLedView;", "rhyledview_1", "ll_seekbar", "Landroidx/constraintlayout/widget/ConstraintLayout;", "iv_rhythm", "Landroid/widget/ImageView;", "iv_rhy_outside_image_bg", "iv_pre", "iv_play", "iv_next", "iv_mode", "initView", "", "bindData", "onDestroy", "requestPermission", "showPermissionTipDialog", "initToolbar", "requestMicPermission", "onBackPressed", "onPause", "initData", "onResume", "initRhyUI", "initseekbarView", "bindListener", "showMusicItem", "changeMode", "musicCallback", "com/wifiled/ipixels/ui/rhythm/MusicActivity$musicCallback$1", "Lcom/wifiled/ipixels/ui/rhythm/MusicActivity$musicCallback$1;", "startScanMusic", PlayerFinal.PLAYER_LIST, "", "onPageScrolled", PlayerFinal.PLAYER_POSITION, "positionOffset", "", "positionOffsetPixels", "onPageSelected", "onPageScrollStateChanged", PlayerFinal.STATE, "rhyUIShow", "isVisible", "runnable", "Ljava/lang/Runnable;", "showCurRhyMode", "currentImage", "selectRhythmMode", "selectMode", "setRhyData", "data", "", "onPermissionsGranted", "requestCode", "perms", "", "onPermissionsDenied", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MusicActivity extends BaseActivity implements ViewPager.OnPageChangeListener, EasyPermissions.PermissionCallbacks {
    private CustomDialog.Builder builder;
    private int curSelectMode;
    private int curSelectPosition;
    private int iLevel;
    private boolean isPlay;
    private CustomImageView iv_back;
    private ImageView iv_mode;
    private ImageView iv_next;
    private ImageView iv_play;
    private ImageView iv_pre;
    private ImageView iv_rhy_outside_image_bg;
    private ImageView iv_rhythm;
    private CustomImageView iv_right;
    private ConstraintLayout ll_seekbar;
    private MusicAdapter musicAdapter;
    private Dialog musicItemDialog;
    private RecyclerView recyclerView;
    private RhythmLedView rhyledview_1;
    private RhythmLedView rhyledview_2;
    private LinearLayout rl_rhy_bg;
    private RelativeLayout rl_root;
    private SeekBar sb;
    private ViewpagerAdapter textImageIconAdapter;
    private TextView tv_sb_duration;
    private TextView tv_sb_time;
    private TextView tv_song_artist;
    private TextView tv_song_name;
    private TextView tv_title;
    private ViewPagerAllResponse vp_rhyhm;

    /* renamed from: musicManager$delegate, reason: from kotlin metadata */
    private final Lazy musicManager = LazyKt.lazy(new Function0() { // from class: com.wifiled.ipixels.ui.rhythm.MusicActivity$$ExternalSyntheticLambda6
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            MusicManager musicManager;
            musicManager = MusicManager.getInstance();
            return musicManager;
        }
    });
    private List<MusicVO> musicData = new ArrayList();
    private int currentPlayMode = 3;
    private final List<RhythmImage> imageList = new ArrayList();
    private final Handler handler = new Handler();
    private final int SEEK_SCOPE = 200;
    private final MusicActivity$musicCallback$1 musicCallback = new MusicActivity$musicCallback$1(this);
    private Runnable runnable = new Runnable() { // from class: com.wifiled.ipixels.ui.rhythm.MusicActivity$$ExternalSyntheticLambda7
        @Override // java.lang.Runnable
        public final void run() {
            MusicActivity.runnable$lambda$15(MusicActivity.this);
        }
    };

    @Override // com.wifiled.baselib.base.BaseActivity
    protected int layoutId() {
        return R.layout.activity_music;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MusicManager getMusicManager() {
        return (MusicManager) this.musicManager.getValue();
    }

    public final int getILevel() {
        return this.iLevel;
    }

    public final void setILevel(int i) {
        this.iLevel = i;
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
        View findViewById4 = findViewById(R.id.vp_rhyhm);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
        this.vp_rhyhm = (ViewPagerAllResponse) findViewById4;
        View findViewById5 = findViewById(R.id.tv_song_name);
        Intrinsics.checkNotNullExpressionValue(findViewById5, "findViewById(...)");
        this.tv_song_name = (TextView) findViewById5;
        View findViewById6 = findViewById(R.id.tv_song_artist);
        Intrinsics.checkNotNullExpressionValue(findViewById6, "findViewById(...)");
        this.tv_song_artist = (TextView) findViewById6;
        View findViewById7 = findViewById(R.id.tv_sb_time);
        Intrinsics.checkNotNullExpressionValue(findViewById7, "findViewById(...)");
        this.tv_sb_time = (TextView) findViewById7;
        View findViewById8 = findViewById(R.id.tv_sb_duration);
        Intrinsics.checkNotNullExpressionValue(findViewById8, "findViewById(...)");
        this.tv_sb_duration = (TextView) findViewById8;
        View findViewById9 = findViewById(R.id.sb);
        Intrinsics.checkNotNullExpressionValue(findViewById9, "findViewById(...)");
        this.sb = (SeekBar) findViewById9;
        View findViewById10 = findViewById(R.id.rl_root);
        Intrinsics.checkNotNullExpressionValue(findViewById10, "findViewById(...)");
        this.rl_root = (RelativeLayout) findViewById10;
        View findViewById11 = findViewById(R.id.rl_rhy_bg);
        Intrinsics.checkNotNullExpressionValue(findViewById11, "findViewById(...)");
        this.rl_rhy_bg = (LinearLayout) findViewById11;
        View findViewById12 = findViewById(R.id.rhyledview_2);
        Intrinsics.checkNotNullExpressionValue(findViewById12, "findViewById(...)");
        this.rhyledview_2 = (RhythmLedView) findViewById12;
        View findViewById13 = findViewById(R.id.rhyledview_1);
        Intrinsics.checkNotNullExpressionValue(findViewById13, "findViewById(...)");
        this.rhyledview_1 = (RhythmLedView) findViewById13;
        View findViewById14 = findViewById(R.id.ll_seekbar);
        Intrinsics.checkNotNullExpressionValue(findViewById14, "findViewById(...)");
        this.ll_seekbar = (ConstraintLayout) findViewById14;
        View findViewById15 = findViewById(R.id.iv_rhythm);
        Intrinsics.checkNotNullExpressionValue(findViewById15, "findViewById(...)");
        this.iv_rhythm = (ImageView) findViewById15;
        View findViewById16 = findViewById(R.id.iv_rhy_outside_image_bg);
        Intrinsics.checkNotNullExpressionValue(findViewById16, "findViewById(...)");
        this.iv_rhy_outside_image_bg = (ImageView) findViewById16;
        View findViewById17 = findViewById(R.id.iv_pre);
        Intrinsics.checkNotNullExpressionValue(findViewById17, "findViewById(...)");
        this.iv_pre = (ImageView) findViewById17;
        View findViewById18 = findViewById(R.id.iv_play);
        Intrinsics.checkNotNullExpressionValue(findViewById18, "findViewById(...)");
        this.iv_play = (ImageView) findViewById18;
        View findViewById19 = findViewById(R.id.iv_next);
        Intrinsics.checkNotNullExpressionValue(findViewById19, "findViewById(...)");
        this.iv_next = (ImageView) findViewById19;
        View findViewById20 = findViewById(R.id.iv_mode);
        Intrinsics.checkNotNullExpressionValue(findViewById20, "findViewById(...)");
        this.iv_mode = (ImageView) findViewById20;
    }

    @Override // com.wifiled.baselib.base.BaseActivity
    protected void bindData() {
        LogUtils.file("MusicActivity bindData");
        requestPermission();
        initData();
        initToolbar();
    }

    @Override // com.wifiled.baselib.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        AppConfig appConfig = AppConfig.INSTANCE;
        AppConfig.isOnly = false;
        MusicCore.INSTANCE.unRegisterMusicCallback(this, this.musicCallback);
        super.onDestroy();
    }

    private final void requestPermission() {
        if (Build.VERSION.SDK_INT >= 33) {
            requestPermission(new String[]{"android.permission.READ_MEDIA_AUDIO", "android.permission.RECORD_AUDIO"}, getString(R.string.open_storage), new BaseActivity.GrantedResult() { // from class: com.wifiled.ipixels.ui.rhythm.MusicActivity$requestPermission$1
                @Override // com.wifiled.baselib.base.BaseActivity.GrantedResult
                public void onResult(boolean granted) {
                    MusicActivity$musicCallback$1 musicActivity$musicCallback$1;
                    MusicManager musicManager;
                    if (!granted) {
                        MusicActivity.this.showPermissionTipDialog();
                        return;
                    }
                    MusicCore.INSTANCE.init(App.INSTANCE.getContext());
                    MusicCore musicCore = MusicCore.INSTANCE;
                    MusicActivity musicActivity = MusicActivity.this;
                    MusicActivity musicActivity2 = musicActivity;
                    musicActivity$musicCallback$1 = musicActivity.musicCallback;
                    musicCore.registerMusicCallback(musicActivity2, musicActivity$musicCallback$1);
                    musicManager = MusicActivity.this.getMusicManager();
                    musicManager.startRhythm();
                    MusicCore.INSTANCE.startScanMusic();
                }
            });
        } else {
            requestPermission(new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.RECORD_AUDIO"}, getString(R.string.open_storage), new BaseActivity.GrantedResult() { // from class: com.wifiled.ipixels.ui.rhythm.MusicActivity$requestPermission$2
                @Override // com.wifiled.baselib.base.BaseActivity.GrantedResult
                public void onResult(boolean granted) {
                    MusicActivity$musicCallback$1 musicActivity$musicCallback$1;
                    MusicManager musicManager;
                    if (!granted) {
                        MusicActivity.this.showPermissionTipDialog();
                        return;
                    }
                    MusicCore.INSTANCE.init(App.INSTANCE.getContext());
                    MusicCore musicCore = MusicCore.INSTANCE;
                    MusicActivity musicActivity = MusicActivity.this;
                    MusicActivity musicActivity2 = musicActivity;
                    musicActivity$musicCallback$1 = musicActivity.musicCallback;
                    musicCore.registerMusicCallback(musicActivity2, musicActivity$musicCallback$1);
                    musicManager = MusicActivity.this.getMusicManager();
                    musicManager.startRhythm();
                    MusicCore.INSTANCE.startScanMusic();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showPermissionTipDialog() {
        CustomDialog create;
        if (this.builder == null) {
            this.builder = new CustomDialog.Builder(this);
        }
        CustomDialog.Builder builder = this.builder;
        if (builder != null) {
            builder.setTitle(getString(R.string.gps_tip));
        }
        CustomDialog.Builder builder2 = this.builder;
        if (builder2 != null) {
            builder2.setMessage(getString(R.string.open_audio));
        }
        CustomDialog.Builder builder3 = this.builder;
        if (builder3 != null) {
            builder3.setPositiveButton(getString(R.string.sure), new DialogInterface.OnClickListener() { // from class: com.wifiled.ipixels.ui.rhythm.MusicActivity$$ExternalSyntheticLambda10
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    MusicActivity.showPermissionTipDialog$lambda$1(MusicActivity.this, dialogInterface, i);
                }
            });
        }
        CustomDialog.Builder builder4 = this.builder;
        if (builder4 != null) {
            builder4.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() { // from class: com.wifiled.ipixels.ui.rhythm.MusicActivity$$ExternalSyntheticLambda11
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
        }
        CustomDialog.Builder builder5 = this.builder;
        if (builder5 == null || (create = builder5.create()) == null) {
            return;
        }
        create.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showPermissionTipDialog$lambda$1(MusicActivity musicActivity, DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        if (Build.VERSION.SDK_INT >= 33) {
            EasyPermissions.requestPermissions(musicActivity, musicActivity.getString(R.string.open_storage), CameraAccessExceptionCompat.CAMERA_UNAVAILABLE_DO_NOT_DISTURB, "android.permission.READ_MEDIA_AUDIO", "android.permission.RECORD_AUDIO");
        } else {
            EasyPermissions.requestPermissions(musicActivity, musicActivity.getString(R.string.open_storage), CameraAccessExceptionCompat.CAMERA_UNAVAILABLE_DO_NOT_DISTURB, "android.permission.READ_EXTERNAL_STORAGE", "android.permission.RECORD_AUDIO");
        }
    }

    private final void initToolbar() {
        TextView textView = this.tv_title;
        CustomImageView customImageView = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_title");
            textView = null;
        }
        textView.setText(getString(R.string.title_rhythm));
        String saveLanguage = LanguageUtil.getSaveLanguage(App.INSTANCE.getContext());
        Intrinsics.checkNotNullExpressionValue(saveLanguage, "getSaveLanguage(...)");
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
        customImageView4.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.rhythm.MusicActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MusicActivity.initToolbar$lambda$3(MusicActivity.this, view);
            }
        });
        CustomImageView.Companion companion = CustomImageView.INSTANCE;
        CustomImageView customImageView5 = this.iv_back;
        if (customImageView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_back");
            customImageView5 = null;
        }
        companion.attachViewOnTouchListener(customImageView5);
        CustomImageView customImageView6 = this.iv_right;
        if (customImageView6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_right");
            customImageView6 = null;
        }
        customImageView6.setBackgroundResource(R.drawable.icon_mic);
        CustomImageView customImageView7 = this.iv_right;
        if (customImageView7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_right");
            customImageView7 = null;
        }
        customImageView7.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.rhythm.MusicActivity$$ExternalSyntheticLambda9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MusicActivity.initToolbar$lambda$4(MusicActivity.this, view);
            }
        });
        CustomImageView.Companion companion2 = CustomImageView.INSTANCE;
        CustomImageView customImageView8 = this.iv_right;
        if (customImageView8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_right");
        } else {
            customImageView = customImageView8;
        }
        companion2.attachViewOnTouchListener(customImageView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initToolbar$lambda$3(MusicActivity musicActivity, View view) {
        MusicManager musicManager;
        LogUtils.file("MusicActivity iv_back.setOnClickListener ");
        if (musicActivity.getMusicManager().isPlaying() && (musicManager = musicActivity.getMusicManager()) != null) {
            musicManager.playOrPause();
        }
        SendCore.INSTANCE.sendExitCmd(null);
        musicActivity.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initToolbar$lambda$4(MusicActivity musicActivity, View view) {
        LogUtils.file("MusicActivity requestMicPermission");
        musicActivity.requestMicPermission();
    }

    private final void requestMicPermission() {
        LogUtils.file("MusicWidescreenActivity  requestMicPermission");
        requestPermission(new String[]{"android.permission.RECORD_AUDIO"}, getString(R.string.open_bt_relative), new MusicActivity$requestMicPermission$1(this));
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        MusicManager musicManager;
        if (getMusicManager().isPlaying() && (musicManager = getMusicManager()) != null) {
            musicManager.playOrPause();
        }
        SendCore.INSTANCE.sendExitCmd(null);
        super.onBackPressed();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        this.isPlay = getMusicManager().isPlaying();
    }

    private final void initData() {
        initseekbarView();
        this.musicAdapter = new MusicAdapter(this, this.musicData);
        initRhyUI();
        RhythmLedView rhythmLedView = this.rhyledview_1;
        RhythmLedView rhythmLedView2 = null;
        if (rhythmLedView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_1");
            rhythmLedView = null;
        }
        rhythmLedView.setLayerType(1, null);
        RhythmLedView rhythmLedView3 = this.rhyledview_1;
        if (rhythmLedView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_1");
            rhythmLedView3 = null;
        }
        rhythmLedView3.setMode(0);
        RhythmLedView rhythmLedView4 = this.rhyledview_1;
        if (rhythmLedView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_1");
            rhythmLedView4 = null;
        }
        rhythmLedView4.setPointMargin(0);
        RhythmLedView rhythmLedView5 = this.rhyledview_1;
        if (rhythmLedView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_1");
            rhythmLedView5 = null;
        }
        rhythmLedView5.removeAllViews();
        RhythmLedView rhythmLedView6 = this.rhyledview_1;
        if (rhythmLedView6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_1");
            rhythmLedView6 = null;
        }
        rhythmLedView6.init(52, 32);
        RhythmLedView rhythmLedView7 = this.rhyledview_2;
        if (rhythmLedView7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_2");
            rhythmLedView7 = null;
        }
        rhythmLedView7.setLayerType(1, null);
        RhythmLedView rhythmLedView8 = this.rhyledview_2;
        if (rhythmLedView8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_2");
            rhythmLedView8 = null;
        }
        rhythmLedView8.setMode(0);
        RhythmLedView rhythmLedView9 = this.rhyledview_2;
        if (rhythmLedView9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_2");
            rhythmLedView9 = null;
        }
        rhythmLedView9.setPointMargin(0);
        RhythmLedView rhythmLedView10 = this.rhyledview_2;
        if (rhythmLedView10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_2");
            rhythmLedView10 = null;
        }
        rhythmLedView10.removeAllViews();
        RhythmLedView rhythmLedView11 = this.rhyledview_2;
        if (rhythmLedView11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_2");
        } else {
            rhythmLedView2 = rhythmLedView11;
        }
        rhythmLedView2.init(52, 32);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        AppConfig appConfig = AppConfig.INSTANCE;
        AppConfig.isOnly = true;
        super.onResume();
    }

    private final void initRhyUI() {
        RhythmImage rhythmImage = new RhythmImage(R.mipmap.rhyhm_mode_bg1, false, null, 6, null);
        RhythmImage rhythmImage2 = new RhythmImage(R.mipmap.rhyhm_mode_bg2, false, null, 6, null);
        RhythmImage rhythmImage3 = new RhythmImage(R.mipmap.rhyhm_mode_bg3, false, null, 6, null);
        RhythmImage rhythmImage4 = new RhythmImage(R.mipmap.rhyhm_mode_bg4, false, null, 6, null);
        RhythmImage rhythmImage5 = new RhythmImage(R.mipmap.rhyhm_mode_bg5, false, null, 6, null);
        RhythmImage rhythmImage6 = new RhythmImage(R.mipmap.rhyhm_mode_bg7, true, null, 4, null);
        RhythmImage rhythmImage7 = new RhythmImage(R.mipmap.rhyhm_mode_bg6, true, null, 4, null);
        this.imageList.add(rhythmImage);
        this.imageList.add(rhythmImage2);
        this.imageList.add(rhythmImage3);
        this.imageList.add(rhythmImage4);
        this.imageList.add(rhythmImage5);
        this.imageList.add(rhythmImage6);
        this.imageList.add(rhythmImage7);
        RelativeLayout relativeLayout = this.rl_root;
        ViewPagerAllResponse viewPagerAllResponse = null;
        if (relativeLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rl_root");
            relativeLayout = null;
        }
        ViewGroup.LayoutParams layoutParams = relativeLayout.getLayoutParams();
        MusicActivity musicActivity = this;
        layoutParams.width = ScreenUtil.getScreenWidth(musicActivity);
        RelativeLayout relativeLayout2 = this.rl_root;
        if (relativeLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rl_root");
            relativeLayout2 = null;
        }
        relativeLayout2.setLayoutParams(layoutParams);
        ViewPagerAllResponse viewPagerAllResponse2 = this.vp_rhyhm;
        if (viewPagerAllResponse2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vp_rhyhm");
            viewPagerAllResponse2 = null;
        }
        ViewGroup.LayoutParams layoutParams2 = viewPagerAllResponse2.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams2, "null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams");
        RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) layoutParams2;
        layoutParams3.width = (int) (ScreenUtil.getScreenWidth(musicActivity) / 1.6d);
        layoutParams3.height = (int) (ScreenUtil.getScreenWidth(musicActivity) / 1.6d);
        ViewPagerAllResponse viewPagerAllResponse3 = this.vp_rhyhm;
        if (viewPagerAllResponse3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vp_rhyhm");
            viewPagerAllResponse3 = null;
        }
        viewPagerAllResponse3.setLayoutParams(layoutParams3);
        ViewPagerAllResponse viewPagerAllResponse4 = this.vp_rhyhm;
        if (viewPagerAllResponse4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vp_rhyhm");
            viewPagerAllResponse4 = null;
        }
        this.textImageIconAdapter = new ViewpagerAdapter(musicActivity, viewPagerAllResponse4, this.imageList);
        ViewPagerAllResponse viewPagerAllResponse5 = this.vp_rhyhm;
        if (viewPagerAllResponse5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vp_rhyhm");
            viewPagerAllResponse5 = null;
        }
        ViewpagerAdapter viewpagerAdapter = this.textImageIconAdapter;
        if (viewpagerAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("textImageIconAdapter");
            viewpagerAdapter = null;
        }
        viewPagerAllResponse5.setAdapter(viewpagerAdapter);
        ViewPagerAllResponse viewPagerAllResponse6 = this.vp_rhyhm;
        if (viewPagerAllResponse6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vp_rhyhm");
            viewPagerAllResponse6 = null;
        }
        viewPagerAllResponse6.setPageTransformer(true, new ZoomOutPageTransformer());
        ViewPagerAllResponse viewPagerAllResponse7 = this.vp_rhyhm;
        if (viewPagerAllResponse7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vp_rhyhm");
            viewPagerAllResponse7 = null;
        }
        viewPagerAllResponse7.setPageMargin(ScreenUtil.dp2px(musicActivity, -30.0f));
        ViewPagerAllResponse viewPagerAllResponse8 = this.vp_rhyhm;
        if (viewPagerAllResponse8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vp_rhyhm");
            viewPagerAllResponse8 = null;
        }
        viewPagerAllResponse8.setCurrentItem(4998);
        ViewPagerAllResponse viewPagerAllResponse9 = this.vp_rhyhm;
        if (viewPagerAllResponse9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vp_rhyhm");
            viewPagerAllResponse9 = null;
        }
        viewPagerAllResponse9.setOffscreenPageLimit(2);
        ViewPagerAllResponse viewPagerAllResponse10 = this.vp_rhyhm;
        if (viewPagerAllResponse10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vp_rhyhm");
            viewPagerAllResponse10 = null;
        }
        viewPagerAllResponse10.addOnPageChangeListener(this);
        ViewPagerAllResponse viewPagerAllResponse11 = this.vp_rhyhm;
        if (viewPagerAllResponse11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vp_rhyhm");
        } else {
            viewPagerAllResponse = viewPagerAllResponse11;
        }
        int currentItem = viewPagerAllResponse.getCurrentItem() % this.imageList.size();
        this.curSelectMode = currentItem;
        this.curSelectPosition = currentItem;
    }

    private final void initseekbarView() {
        ConstraintLayout constraintLayout = this.ll_seekbar;
        if (constraintLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ll_seekbar");
            constraintLayout = null;
        }
        constraintLayout.setOnTouchListener(new View.OnTouchListener() { // from class: com.wifiled.ipixels.ui.rhythm.MusicActivity$$ExternalSyntheticLambda8
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                boolean initseekbarView$lambda$5;
                initseekbarView$lambda$5 = MusicActivity.initseekbarView$lambda$5(MusicActivity.this, view, motionEvent);
                return initseekbarView$lambda$5;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean initseekbarView$lambda$5(MusicActivity musicActivity, View view, MotionEvent motionEvent) {
        LogUtils.file("MusicActivity ll_seekbar.setOnTouchListener");
        Rect rect = new Rect();
        SeekBar seekBar = musicActivity.sb;
        SeekBar seekBar2 = null;
        if (seekBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sb");
            seekBar = null;
        }
        seekBar.getHitRect(rect);
        if (motionEvent.getY() < rect.top - musicActivity.SEEK_SCOPE || motionEvent.getY() > rect.bottom + musicActivity.SEEK_SCOPE || motionEvent.getX() < rect.left || motionEvent.getX() > rect.right) {
            return false;
        }
        MotionEvent obtain = MotionEvent.obtain(motionEvent.getDownTime(), motionEvent.getEventTime(), motionEvent.getAction(), motionEvent.getX() - rect.left, rect.top + (rect.height() / 2.0f), motionEvent.getMetaState());
        SeekBar seekBar3 = musicActivity.sb;
        if (seekBar3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sb");
        } else {
            seekBar2 = seekBar3;
        }
        return seekBar2.onTouchEvent(obtain);
    }

    @Override // com.wifiled.baselib.base.BaseActivity
    protected void bindListener() {
        super.bindListener();
        MusicAdapter musicAdapter = this.musicAdapter;
        RelativeLayout relativeLayout = null;
        if (musicAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("musicAdapter");
            musicAdapter = null;
        }
        musicAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() { // from class: com.wifiled.ipixels.ui.rhythm.MusicActivity$$ExternalSyntheticLambda16
            @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter.OnItemClickListener
            public final void onItemClick(ViewGroup viewGroup, View view, Object obj, int i) {
                MusicActivity.bindListener$lambda$6(MusicActivity.this, viewGroup, view, (MusicVO) obj, i);
            }
        });
        ImageView imageView = this.iv_mode;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_mode");
            imageView = null;
        }
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.rhythm.MusicActivity$$ExternalSyntheticLambda17
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MusicActivity.bindListener$lambda$7(MusicActivity.this, view);
            }
        });
        CustomImageView.Companion companion = CustomImageView.INSTANCE;
        ImageView imageView2 = this.iv_mode;
        if (imageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_mode");
            imageView2 = null;
        }
        companion.attachViewOnTouchListener(imageView2);
        ImageView imageView3 = this.iv_rhythm;
        if (imageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_rhythm");
            imageView3 = null;
        }
        imageView3.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.rhythm.MusicActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MusicActivity.bindListener$lambda$8(MusicActivity.this, view);
            }
        });
        CustomImageView.Companion companion2 = CustomImageView.INSTANCE;
        ImageView imageView4 = this.iv_rhythm;
        if (imageView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_rhythm");
            imageView4 = null;
        }
        companion2.attachViewOnTouchListener(imageView4);
        ImageView imageView5 = this.iv_pre;
        if (imageView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_pre");
            imageView5 = null;
        }
        imageView5.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.rhythm.MusicActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MusicActivity.bindListener$lambda$9(MusicActivity.this, view);
            }
        });
        CustomImageView.Companion companion3 = CustomImageView.INSTANCE;
        ImageView imageView6 = this.iv_pre;
        if (imageView6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_pre");
            imageView6 = null;
        }
        companion3.attachViewOnTouchListener(imageView6);
        ImageView imageView7 = this.iv_next;
        if (imageView7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_next");
            imageView7 = null;
        }
        imageView7.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.rhythm.MusicActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MusicActivity.bindListener$lambda$10(MusicActivity.this, view);
            }
        });
        CustomImageView.Companion companion4 = CustomImageView.INSTANCE;
        ImageView imageView8 = this.iv_next;
        if (imageView8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_next");
            imageView8 = null;
        }
        companion4.attachViewOnTouchListener(imageView8);
        ImageView imageView9 = this.iv_play;
        if (imageView9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_play");
            imageView9 = null;
        }
        imageView9.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.rhythm.MusicActivity$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MusicActivity.bindListener$lambda$11(MusicActivity.this, view);
            }
        });
        CustomImageView.Companion companion5 = CustomImageView.INSTANCE;
        ImageView imageView10 = this.iv_play;
        if (imageView10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_play");
            imageView10 = null;
        }
        companion5.attachViewOnTouchListener(imageView10);
        final Ref.IntRef intRef = new Ref.IntRef();
        SeekBar seekBar = this.sb;
        if (seekBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sb");
            seekBar = null;
        }
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.wifiled.ipixels.ui.rhythm.MusicActivity$bindListener$7
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar2, int progress, boolean fromUser) {
                MusicManager musicManager;
                MusicManager musicManager2;
                MusicManager musicManager3;
                if (Ref.IntRef.this.element != progress) {
                    Ref.IntRef.this.element = progress;
                    Integer valueOf = seekBar2 != null ? Integer.valueOf(seekBar2.getProgress()) : null;
                    musicManager = this.getMusicManager();
                    com.wifiled.baselib.utils.LogUtils.loge("onProgressChanged：" + valueOf + " isPlay:" + musicManager.isPlaying(), new Object[0]);
                    musicManager2 = this.getMusicManager();
                    if (musicManager2.isPlaying()) {
                        return;
                    }
                    Integer valueOf2 = seekBar2 != null ? Integer.valueOf(seekBar2.getProgress()) : null;
                    Intrinsics.checkNotNull(valueOf2);
                    if (valueOf2.intValue() >= 100) {
                        musicManager3 = this.getMusicManager();
                        musicManager3.getMusicPlayer().resume();
                    }
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar2) {
                MusicAdapter musicAdapter2;
                MusicManager musicManager;
                MusicManager musicManager2;
                musicAdapter2 = this.musicAdapter;
                if (musicAdapter2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("musicAdapter");
                    musicAdapter2 = null;
                }
                if (musicAdapter2.getItemCount() <= 0) {
                    MusicActivity musicActivity = this;
                    musicActivity.toast(musicActivity.getString(R.string.msg_music_list_empty));
                }
                musicManager = this.getMusicManager();
                if (musicManager.isPlaying()) {
                    musicManager2 = this.getMusicManager();
                    musicManager2.getMusicPlayer().pause();
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar2) {
                MusicAdapter musicAdapter2;
                MusicManager musicManager;
                MusicManager musicManager2;
                MusicManager musicManager3;
                MusicManager musicManager4;
                LogUtils.file("MusicActivity sb.setOnSeekBarChangeListener onStopTrackingTouch");
                musicAdapter2 = this.musicAdapter;
                if (musicAdapter2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("musicAdapter");
                    musicAdapter2 = null;
                }
                if (musicAdapter2.getItemCount() <= 0) {
                    MusicActivity musicActivity = this;
                    musicActivity.toast(musicActivity.getString(R.string.msg_music_list_empty));
                }
                if (seekBar2 != null) {
                    int progress = seekBar2.getProgress();
                    musicManager4 = this.getMusicManager();
                    musicManager4.changeSeek(progress);
                }
                Integer valueOf = seekBar2 != null ? Integer.valueOf(seekBar2.getProgress()) : null;
                musicManager = this.getMusicManager();
                com.wifiled.baselib.utils.LogUtils.loge("process：" + valueOf + " isPlay:" + musicManager.isPlaying(), new Object[0]);
                musicManager2 = this.getMusicManager();
                if (musicManager2.isPlaying()) {
                    return;
                }
                musicManager3 = this.getMusicManager();
                musicManager3.getMusicPlayer().resume();
            }
        });
        RelativeLayout relativeLayout2 = this.rl_root;
        if (relativeLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rl_root");
        } else {
            relativeLayout = relativeLayout2;
        }
        relativeLayout.setOnTouchListener(new View.OnTouchListener() { // from class: com.wifiled.ipixels.ui.rhythm.MusicActivity$$ExternalSyntheticLambda5
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                boolean bindListener$lambda$12;
                bindListener$lambda$12 = MusicActivity.bindListener$lambda$12(MusicActivity.this, view, motionEvent);
                return bindListener$lambda$12;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$6(MusicActivity musicActivity, ViewGroup viewGroup, View view, MusicVO musicVO, int i) {
        LogUtils.file("MusicActivity musicAdapter.setOnItemClickListener");
        musicActivity.getMusicManager().playItem(musicActivity.musicData.get(i));
        MusicAdapter musicAdapter = musicActivity.musicAdapter;
        if (musicAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("musicAdapter");
            musicAdapter = null;
        }
        musicAdapter.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$7(MusicActivity musicActivity, View view) {
        LogUtils.file("MusicActivity iv_mode.setOnClickListener");
        musicActivity.changeMode();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$8(MusicActivity musicActivity, View view) {
        LogUtils.file("MusicActivity iv_rhythm.setOnClickListener");
        musicActivity.showMusicItem();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$9(MusicActivity musicActivity, View view) {
        LogUtils.file("MusicActivity iv_pre.setOnClickListener");
        MusicAdapter musicAdapter = musicActivity.musicAdapter;
        if (musicAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("musicAdapter");
            musicAdapter = null;
        }
        if (musicAdapter.getItemCount() <= 0) {
            musicActivity.toast(musicActivity.getString(R.string.msg_music_list_empty));
        }
        if (UtilsExtensionKt.isRepeatClick(500L)) {
            return;
        }
        musicActivity.getMusicManager().pre();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$10(MusicActivity musicActivity, View view) {
        LogUtils.file("MusicActivity iv_next.setOnClickListener");
        MusicAdapter musicAdapter = musicActivity.musicAdapter;
        if (musicAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("musicAdapter");
            musicAdapter = null;
        }
        if (musicAdapter.getItemCount() <= 0) {
            musicActivity.toast(musicActivity.getString(R.string.msg_music_list_empty));
        }
        if (UtilsExtensionKt.isRepeatClick(500L)) {
            return;
        }
        musicActivity.getMusicManager().next();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$11(MusicActivity musicActivity, View view) {
        LogUtils.file("MusicActivity iv_play.setOnClickListener");
        MusicAdapter musicAdapter = musicActivity.musicAdapter;
        if (musicAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("musicAdapter");
            musicAdapter = null;
        }
        if (musicAdapter.getItemCount() <= 0) {
            musicActivity.toast(musicActivity.getString(R.string.msg_music_list_empty));
        }
        musicActivity.getMusicManager().playOrPause();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean bindListener$lambda$12(MusicActivity musicActivity, View view, MotionEvent motionEvent) {
        LogUtils.file("MusicActivity rl_root.setOnTouchListener");
        ViewPagerAllResponse viewPagerAllResponse = musicActivity.vp_rhyhm;
        if (viewPagerAllResponse == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vp_rhyhm");
            viewPagerAllResponse = null;
        }
        return viewPagerAllResponse.dispatchTouchEvent(motionEvent);
    }

    private final void showMusicItem() {
        MusicAdapter musicAdapter = null;
        if (this.musicItemDialog == null) {
            Dialog showBottomDialog = DialogUtils.showBottomDialog(this, R.layout.music_item_layout);
            this.musicItemDialog = showBottomDialog;
            Window window = showBottomDialog != null ? showBottomDialog.getWindow() : null;
            if (window != null) {
                window.setDimAmount(0.0f);
            }
            WindowManager.LayoutParams attributes = window != null ? window.getAttributes() : null;
            MusicActivity musicActivity = this;
            double screenHeight = ScreenUtil.getScreenHeight(musicActivity) * 0.75d;
            if (attributes != null) {
                attributes.height = (int) screenHeight;
            }
            if (window != null) {
                window.setAttributes(attributes);
            }
            Dialog dialog = this.musicItemDialog;
            RecyclerView recyclerView = dialog != null ? (RecyclerView) dialog.findViewById(R.id.rl_music_list) : null;
            this.recyclerView = recyclerView;
            if (recyclerView != null) {
                MusicAdapter musicAdapter2 = this.musicAdapter;
                if (musicAdapter2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("musicAdapter");
                    musicAdapter2 = null;
                }
                recyclerView.setAdapter(musicAdapter2);
            }
            RecyclerView recyclerView2 = this.recyclerView;
            if (recyclerView2 != null) {
                recyclerView2.setLayoutManager(new LinearLayoutManager(musicActivity));
            }
        }
        Dialog dialog2 = this.musicItemDialog;
        if (dialog2 != null) {
            dialog2.show();
        }
        SeekBar seekBar = this.sb;
        if (seekBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sb");
            seekBar = null;
        }
        MusicAdapter musicAdapter3 = this.musicAdapter;
        if (musicAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("musicAdapter");
        } else {
            musicAdapter = musicAdapter3;
        }
        seekBar.setEnabled(musicAdapter.getItemCount() > 0);
    }

    private final void changeMode() {
        int i = this.currentPlayMode;
        if (i == 0) {
            this.currentPlayMode = 3;
        } else if (i == 1) {
            this.currentPlayMode = 0;
        } else if (i == 3) {
            this.currentPlayMode = 1;
        }
        getMusicManager().changeMode(this.currentPlayMode);
    }

    public final void startScanMusic(List<? extends MusicVO> musicList) {
        Intrinsics.checkNotNullParameter(musicList, "musicList");
        LogUtils.file("MusicActivity startScanMusic");
        TextView textView = null;
        MusicAdapter musicAdapter = null;
        TextView textView2 = null;
        if (musicList.isEmpty()) {
            SeekBar seekBar = this.sb;
            if (seekBar == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sb");
                seekBar = null;
            }
            MusicAdapter musicAdapter2 = this.musicAdapter;
            if (musicAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("musicAdapter");
            } else {
                musicAdapter = musicAdapter2;
            }
            seekBar.setEnabled(musicAdapter.getItemCount() > 0);
            return;
        }
        this.musicData.addAll(musicList);
        int currentMusicPosition = MusicCore.INSTANCE.getCurrentMusicPosition();
        MusicVO musicVO = this.musicData.get(currentMusicPosition);
        MusicAdapter musicAdapter3 = this.musicAdapter;
        if (musicAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("musicAdapter");
            musicAdapter3 = null;
        }
        musicAdapter3.setPlayPosition(currentMusicPosition);
        MusicAdapter musicAdapter4 = this.musicAdapter;
        if (musicAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("musicAdapter");
            musicAdapter4 = null;
        }
        musicAdapter4.notifyDataSetChanged();
        TextView textView3 = this.tv_song_name;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_song_name");
            textView3 = null;
        }
        textView3.setText(musicVO.title);
        int i = (int) (musicVO.duration / 1000);
        String valueOf = String.valueOf(i / 60);
        int i2 = i % 60;
        String valueOf2 = String.valueOf(i2);
        if (valueOf2.length() == 1) {
            valueOf2 = "0" + i2;
        }
        TextView textView4 = this.tv_sb_duration;
        if (textView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_sb_duration");
            textView4 = null;
        }
        textView4.setText(valueOf + ":" + valueOf2);
        if (Intrinsics.areEqual(musicVO.artist, CameraInfo.IMPLEMENTATION_TYPE_UNKNOWN)) {
            TextView textView5 = this.tv_song_artist;
            if (textView5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tv_song_artist");
            } else {
                textView2 = textView5;
            }
            textView2.setText(R.string.unknown);
            return;
        }
        TextView textView6 = this.tv_song_artist;
        if (textView6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_song_artist");
        } else {
            textView = textView6;
        }
        textView.setText(musicVO.artist);
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        LogUtils.i("ViewpagerAdapter>>>[onPageScrolled]: positionOffset: " + positionOffset + " pos:" + position);
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.rhythm.MusicActivity$$ExternalSyntheticLambda12
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit onPageScrolled$lambda$14;
                onPageScrolled$lambda$14 = MusicActivity.onPageScrolled$lambda$14(MusicActivity.this);
                return onPageScrolled$lambda$14;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onPageScrolled$lambda$14(MusicActivity musicActivity) {
        ImageView imageView = musicActivity.iv_rhy_outside_image_bg;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_rhy_outside_image_bg");
            imageView = null;
        }
        UtilsExtensionKt.hide(imageView);
        return Unit.INSTANCE;
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int position) {
        int size = position % this.imageList.size();
        this.curSelectPosition = size;
        LogUtils.i("ViewpagerAdapter>>>[onPageSelected]: curSelectPosition: " + size + " pos:" + position);
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int state) {
        if (state == 0) {
            showCurRhyMode(this.curSelectPosition);
        } else if (state == 1 || state == 2) {
            Log.d("TAG", "开始滑动");
            rhyUIShow(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void rhyUIShow(boolean isVisible) {
        this.handler.removeCallbacks(this.runnable);
        if (isVisible) {
            this.handler.postDelayed(this.runnable, 100L);
            return;
        }
        RhythmLedView rhythmLedView = this.rhyledview_1;
        ViewpagerAdapter viewpagerAdapter = null;
        if (rhythmLedView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_1");
            rhythmLedView = null;
        }
        UtilsExtensionKt.hide(rhythmLedView);
        RhythmLedView rhythmLedView2 = this.rhyledview_2;
        if (rhythmLedView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_2");
            rhythmLedView2 = null;
        }
        UtilsExtensionKt.hide(rhythmLedView2);
        ViewpagerAdapter viewpagerAdapter2 = this.textImageIconAdapter;
        if (viewpagerAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("textImageIconAdapter");
        } else {
            viewpagerAdapter = viewpagerAdapter2;
        }
        viewpagerAdapter.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void runnable$lambda$15(MusicActivity musicActivity) {
        RhythmLedView rhythmLedView = musicActivity.rhyledview_1;
        ViewpagerAdapter viewpagerAdapter = null;
        if (rhythmLedView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_1");
            rhythmLedView = null;
        }
        UtilsExtensionKt.show(rhythmLedView);
        RhythmLedView rhythmLedView2 = musicActivity.rhyledview_2;
        if (rhythmLedView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_2");
            rhythmLedView2 = null;
        }
        UtilsExtensionKt.show(rhythmLedView2);
        ImageView imageView = musicActivity.iv_rhy_outside_image_bg;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_rhy_outside_image_bg");
            imageView = null;
        }
        UtilsExtensionKt.show(imageView);
        LogUtils.i(musicActivity.TAG + ">>>[curSelectPosition]:  " + musicActivity.curSelectPosition);
        ViewpagerAdapter viewpagerAdapter2 = musicActivity.textImageIconAdapter;
        if (viewpagerAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("textImageIconAdapter");
        } else {
            viewpagerAdapter = viewpagerAdapter2;
        }
        viewpagerAdapter.notifyDataSetChanged();
    }

    private final void showCurRhyMode(int currentImage) {
        LogUtils.file("MusicActivity showCurRhyMode");
        if (getMusicManager().isPlaying()) {
            rhyUIShow(true);
        } else {
            rhyUIShow(false);
        }
        RhythmLedView rhythmLedView = null;
        LinearLayout linearLayout = null;
        RhythmLedView rhythmLedView2 = null;
        RhythmLedView rhythmLedView3 = null;
        RhythmLedView rhythmLedView4 = null;
        if (currentImage == 0) {
            LinearLayout linearLayout2 = this.rl_rhy_bg;
            if (linearLayout2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rl_rhy_bg");
                linearLayout2 = null;
            }
            linearLayout2.setRotation(0.0f);
            RhythmLedView rhythmLedView5 = this.rhyledview_1;
            if (rhythmLedView5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rhyledview_1");
                rhythmLedView5 = null;
            }
            rhythmLedView5.setRotationX(0.0f);
            RhythmLedView rhythmLedView6 = this.rhyledview_2;
            if (rhythmLedView6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rhyledview_2");
            } else {
                rhythmLedView = rhythmLedView6;
            }
            rhythmLedView.setRotationX(180.0f);
            selectRhythmMode(1);
            return;
        }
        if (currentImage == 1) {
            LinearLayout linearLayout3 = this.rl_rhy_bg;
            if (linearLayout3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rl_rhy_bg");
                linearLayout3 = null;
            }
            linearLayout3.setRotation(0.0f);
            RhythmLedView rhythmLedView7 = this.rhyledview_1;
            if (rhythmLedView7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rhyledview_1");
                rhythmLedView7 = null;
            }
            rhythmLedView7.setRotationX(0.0f);
            RhythmLedView rhythmLedView8 = this.rhyledview_2;
            if (rhythmLedView8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rhyledview_2");
            } else {
                rhythmLedView4 = rhythmLedView8;
            }
            rhythmLedView4.setRotationX(180.0f);
            selectRhythmMode(2);
            return;
        }
        if (currentImage == 2) {
            LinearLayout linearLayout4 = this.rl_rhy_bg;
            if (linearLayout4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rl_rhy_bg");
                linearLayout4 = null;
            }
            linearLayout4.setRotation(0.0f);
            RhythmLedView rhythmLedView9 = this.rhyledview_1;
            if (rhythmLedView9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rhyledview_1");
                rhythmLedView9 = null;
            }
            rhythmLedView9.setRotationX(0.0f);
            RhythmLedView rhythmLedView10 = this.rhyledview_2;
            if (rhythmLedView10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rhyledview_2");
            } else {
                rhythmLedView3 = rhythmLedView10;
            }
            rhythmLedView3.setRotationX(180.0f);
            selectRhythmMode(3);
            return;
        }
        if (currentImage == 3) {
            LinearLayout linearLayout5 = this.rl_rhy_bg;
            if (linearLayout5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rl_rhy_bg");
                linearLayout5 = null;
            }
            linearLayout5.setRotation(0.0f);
            RhythmLedView rhythmLedView11 = this.rhyledview_1;
            if (rhythmLedView11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rhyledview_1");
                rhythmLedView11 = null;
            }
            rhythmLedView11.setRotationX(180.0f);
            RhythmLedView rhythmLedView12 = this.rhyledview_2;
            if (rhythmLedView12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rhyledview_2");
            } else {
                rhythmLedView2 = rhythmLedView12;
            }
            rhythmLedView2.setRotationX(0.0f);
            selectRhythmMode(4);
            return;
        }
        if (currentImage == 4) {
            RhythmLedView rhythmLedView13 = this.rhyledview_1;
            if (rhythmLedView13 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rhyledview_1");
                rhythmLedView13 = null;
            }
            rhythmLedView13.setRotationX(0.0f);
            RhythmLedView rhythmLedView14 = this.rhyledview_2;
            if (rhythmLedView14 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rhyledview_2");
                rhythmLedView14 = null;
            }
            rhythmLedView14.setRotationX(180.0f);
            LinearLayout linearLayout6 = this.rl_rhy_bg;
            if (linearLayout6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rl_rhy_bg");
            } else {
                linearLayout = linearLayout6;
            }
            linearLayout.setRotation(90.0f);
            selectRhythmMode(5);
            return;
        }
        selectRhythmMode(currentImage + 1);
    }

    private final void selectRhythmMode(int selectMode) {
        LogUtils.file("MusicActivity selectRhythmMode");
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

    /* JADX INFO: Access modifiers changed from: private */
    public final void setRhyData(byte[] data) {
        RhythmLedView rhythmLedView = null;
        switch (this.curSelectMode) {
            case 0:
                List<Integer> rhyData1 = RhythmDataUitls.getRhyData1(data);
                Intrinsics.checkNotNullExpressionValue(rhyData1, "getRhyData1(...)");
                RhythmLedView rhythmLedView2 = this.rhyledview_1;
                if (rhythmLedView2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rhyledview_1");
                    rhythmLedView2 = null;
                }
                rhythmLedView2.updateRhythmUI(rhyData1);
                RhythmLedView rhythmLedView3 = this.rhyledview_2;
                if (rhythmLedView3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rhyledview_2");
                } else {
                    rhythmLedView = rhythmLedView3;
                }
                rhythmLedView.updateRhythmUI(rhyData1);
                break;
            case 1:
                List<Integer> rhyData2 = RhythmDataUitls.getRhyData2(data);
                Intrinsics.checkNotNullExpressionValue(rhyData2, "getRhyData2(...)");
                RhythmLedView rhythmLedView4 = this.rhyledview_1;
                if (rhythmLedView4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rhyledview_1");
                    rhythmLedView4 = null;
                }
                rhythmLedView4.setRhyData2(rhyData2);
                RhythmLedView rhythmLedView5 = this.rhyledview_2;
                if (rhythmLedView5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rhyledview_2");
                } else {
                    rhythmLedView = rhythmLedView5;
                }
                rhythmLedView.setRhyData2(rhyData2);
                break;
            case 2:
                List<Integer> rhyData3 = RhythmDataUitls.getRhyData3(data);
                Intrinsics.checkNotNullExpressionValue(rhyData3, "getRhyData3(...)");
                RhythmLedView rhythmLedView6 = this.rhyledview_1;
                if (rhythmLedView6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rhyledview_1");
                    rhythmLedView6 = null;
                }
                rhythmLedView6.setRhyData3(rhyData3);
                RhythmLedView rhythmLedView7 = this.rhyledview_2;
                if (rhythmLedView7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rhyledview_2");
                } else {
                    rhythmLedView = rhythmLedView7;
                }
                rhythmLedView.setRhyData3(rhyData3);
                break;
            case 3:
                List<Integer> rhyData4 = RhythmDataUitls.getRhyData4(data);
                Intrinsics.checkNotNullExpressionValue(rhyData4, "getRhyData4(...)");
                RhythmLedView rhythmLedView8 = this.rhyledview_1;
                if (rhythmLedView8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rhyledview_1");
                    rhythmLedView8 = null;
                }
                rhythmLedView8.setRhyData4(rhyData4);
                RhythmLedView rhythmLedView9 = this.rhyledview_2;
                if (rhythmLedView9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rhyledview_2");
                } else {
                    rhythmLedView = rhythmLedView9;
                }
                rhythmLedView.setRhyData4(rhyData4);
                break;
            case 4:
                List<Integer> rhyData5 = RhythmDataUitls.getRhyData5(data);
                Intrinsics.checkNotNullExpressionValue(rhyData5, "getRhyData5(...)");
                RhythmLedView rhythmLedView10 = this.rhyledview_1;
                if (rhythmLedView10 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rhyledview_1");
                    rhythmLedView10 = null;
                }
                rhythmLedView10.setRhyData5(rhyData5);
                RhythmLedView rhythmLedView11 = this.rhyledview_2;
                if (rhythmLedView11 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rhyledview_2");
                } else {
                    rhythmLedView = rhythmLedView11;
                }
                rhythmLedView.setRhyData5(rhyData5);
                break;
            case 5:
            case 6:
                runOnUiThread(new Runnable() { // from class: com.wifiled.ipixels.ui.rhythm.MusicActivity$$ExternalSyntheticLambda15
                    @Override // java.lang.Runnable
                    public final void run() {
                        MusicActivity.setRhyData$lambda$16(MusicActivity.this);
                    }
                });
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setRhyData$lambda$16(MusicActivity musicActivity) {
        RhythmLedView rhythmLedView = musicActivity.rhyledview_1;
        RhythmLedView rhythmLedView2 = null;
        if (rhythmLedView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_1");
            rhythmLedView = null;
        }
        rhythmLedView.clearSelected();
        RhythmLedView rhythmLedView3 = musicActivity.rhyledview_2;
        if (rhythmLedView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_2");
        } else {
            rhythmLedView2 = rhythmLedView3;
        }
        rhythmLedView2.clearSelected();
    }

    @Override // pub.devrel.easypermissions.EasyPermissions.PermissionCallbacks
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        Intrinsics.checkNotNullParameter(perms, "perms");
        if (requestCode == 10001) {
            MusicCore.INSTANCE.init(App.INSTANCE.getContext());
            MusicCore.INSTANCE.registerMusicCallback(this, this.musicCallback);
            getMusicManager().startRhythm();
            MusicCore.INSTANCE.startScanMusic();
        }
    }

    @Override // pub.devrel.easypermissions.EasyPermissions.PermissionCallbacks
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Intrinsics.checkNotNullParameter(perms, "perms");
        if (requestCode == 10001 && EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            CustomDialog.Builder builder = new CustomDialog.Builder(this);
            builder.setTitle(getString(R.string.gps_tip));
            builder.setMessage(getString(R.string.open_audio));
            builder.setPositiveButton(getString(R.string.sure), new DialogInterface.OnClickListener() { // from class: com.wifiled.ipixels.ui.rhythm.MusicActivity$$ExternalSyntheticLambda13
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    MusicActivity.onPermissionsDenied$lambda$17(MusicActivity.this, dialogInterface, i);
                }
            });
            builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() { // from class: com.wifiled.ipixels.ui.rhythm.MusicActivity$$ExternalSyntheticLambda14
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            builder.create().show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onPermissionsDenied$lambda$17(MusicActivity musicActivity, DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        App.INSTANCE.gotoAppDetailIntent(musicActivity);
    }
}
