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
import com.wifiled.ipixels.view.RhythmLedView2;
import com.wifiled.ipixels.view.ViewPagerAllResponse;
import com.wifiled.ipixels.view.ZoomOutPageTransformer;
import com.wifiled.ipixels.view.customview.CustomImageView;
import com.wifiled.musiclib.MusicManager;
import com.wifiled.musiclib.player.constant.PlayerFinal;
import com.wifiled.musiclib.vo.MusicVO;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import pub.devrel.easypermissions.EasyPermissions;

/* compiled from: MusicWidescreenActivity.kt */
@Metadata(d1 = {"\u0000ã\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0010\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0005*\u0001W\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H\u0014J\b\u0010E\u001a\u00020FH\u0014J\b\u0010G\u001a\u00020FH\u0014J\b\u0010H\u001a\u00020FH\u0014J\b\u0010I\u001a\u00020FH\u0014J\b\u0010J\u001a\u00020FH\u0002J\b\u0010K\u001a\u00020FH\u0002J\b\u0010L\u001a\u00020FH\u0002J\b\u0010M\u001a\u00020FH\u0016J\b\u0010N\u001a\u00020FH\u0002J\b\u0010O\u001a\u00020FH\u0014J\b\u0010P\u001a\u00020FH\u0002J\b\u0010Q\u001a\u00020FH\u0002J\b\u0010R\u001a\u00020FH\u0003J\b\u0010S\u001a\u00020FH\u0015J\b\u0010T\u001a\u00020FH\u0002J\b\u0010U\u001a\u00020FH\u0002J\u0014\u0010Y\u001a\u00020F2\f\u0010Z\u001a\b\u0012\u0004\u0012\u00020\u000f0[J \u0010\\\u001a\u00020F2\u0006\u0010]\u001a\u00020\u00072\u0006\u0010^\u001a\u00020_2\u0006\u0010`\u001a\u00020\u0007H\u0016J\u0010\u0010a\u001a\u00020F2\u0006\u0010]\u001a\u00020\u0007H\u0016J\u0010\u0010b\u001a\u00020F2\u0006\u0010c\u001a\u00020\u0007H\u0016J\u0010\u0010d\u001a\u00020F2\u0006\u0010e\u001a\u00020#H\u0002J\u0010\u0010h\u001a\u00020F2\u0006\u0010i\u001a\u00020\u0007H\u0002J\u0010\u0010j\u001a\u00020F2\u0006\u0010k\u001a\u00020\u0007H\u0002J\u0010\u0010l\u001a\u00020F2\u0006\u0010m\u001a\u00020nH\u0002J-\u0010o\u001a\u00020F2\u0006\u0010p\u001a\u00020\u00072\u000e\u0010q\u001a\n\u0012\u0006\b\u0001\u0012\u00020s0r2\u0006\u0010t\u001a\u00020uH\u0016¢\u0006\u0002\u0010vJ\u001e\u0010w\u001a\u00020F2\u0006\u0010p\u001a\u00020\u00072\f\u0010x\u001a\b\u0012\u0004\u0012\u00020s0\u000eH\u0016J\u001e\u0010y\u001a\u00020F2\u0006\u0010p\u001a\u00020\u00072\f\u0010x\u001a\b\u0012\u0004\u0012\u00020s0\u000eH\u0016R\u0016\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001d\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u000e\u0010\"\u001a\u00020#X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010%\u001a\u0004\u0018\u00010&X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020(X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020(X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020+X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020-X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020+X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020+X\u0082.¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020+X\u0082.¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020+X\u0082.¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u000203X\u0082.¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u000205X\u0082.¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u000207X\u0082.¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u000209X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u000209X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010;\u001a\u000209X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010<\u001a\u00020=X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010>\u001a\u00020?X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010@\u001a\u00020?X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010A\u001a\u00020?X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010B\u001a\u00020?X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010C\u001a\u00020?X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010D\u001a\u00020?X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010V\u001a\u00020WX\u0082\u0004¢\u0006\u0004\n\u0002\u0010XR\u000e\u0010f\u001a\u00020gX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006z"}, d2 = {"Lcom/wifiled/ipixels/ui/rhythm/MusicWidescreenActivity;", "Lcom/wifiled/baselib/base/BaseActivity;", "Landroidx/viewpager/widget/ViewPager$OnPageChangeListener;", "Lpub/devrel/easypermissions/EasyPermissions$PermissionCallbacks;", "<init>", "()V", "layoutId", "", "musicManager", "Lcom/wifiled/musiclib/MusicManager;", "kotlin.jvm.PlatformType", "musicAdapter", "Lcom/wifiled/ipixels/ui/adapter/MusicAdapter;", "musicData", "", "Lcom/wifiled/musiclib/vo/MusicVO;", "currentPlayMode", "musicItemDialog", "Landroid/app/Dialog;", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "imageList", "Lcom/wifiled/ipixels/bean/RhythmImage;", "textImageIconAdapter", "Lcom/wifiled/ipixels/ui/adapter/ViewpagerAdapter;", "curSelectPosition", "curSelectMode", "handler", "Landroid/os/Handler;", "iLevel", "getILevel", "()I", "setILevel", "(I)V", "isPlay", "", "SEEK_SCOPE", "builder", "Lcom/wifiled/ipixels/ui/dialog/CustomDialog$Builder;", "iv_right", "Lcom/wifiled/ipixels/view/customview/CustomImageView;", "iv_back", "tv_title", "Landroid/widget/TextView;", "vp_rhyhm", "Lcom/wifiled/ipixels/view/ViewPagerAllResponse;", "tv_song_name", "tv_song_artist", "tv_sb_time", "tv_sb_duration", "sb", "Landroid/widget/SeekBar;", "rl_root", "Landroid/widget/RelativeLayout;", "rl_rhy_bg", "Landroid/widget/LinearLayout;", "rhyledview_2", "Lcom/wifiled/ipixels/view/RhythmLedView2;", "rhyledview_1", "rhyledview_3", "ll_seekbar", "Landroidx/constraintlayout/widget/ConstraintLayout;", "iv_rhythm", "Landroid/widget/ImageView;", "iv_rhy_outside_image_bg", "iv_pre", "iv_play", "iv_next", "iv_mode", "initView", "", "bindData", "onResume", "onDestroy", "showPermissionTipDialog", "requestPermission", "requestMicPermission", "onBackPressed", "initToolbar", "onPause", "initData", "initRhyUI", "initseekbarView", "bindListener", "showMusicItem", "changeMode", "musicCallback", "com/wifiled/ipixels/ui/rhythm/MusicWidescreenActivity$musicCallback$1", "Lcom/wifiled/ipixels/ui/rhythm/MusicWidescreenActivity$musicCallback$1;", "startScanMusic", PlayerFinal.PLAYER_LIST, "", "onPageScrolled", PlayerFinal.PLAYER_POSITION, "positionOffset", "", "positionOffsetPixels", "onPageSelected", "onPageScrollStateChanged", PlayerFinal.STATE, "rhyUIShow", "isVisible", "runnable", "Ljava/lang/Runnable;", "showCurRhyMode", "currentImage", "selectRhythmMode", "selectMode", "setRhyData", "data", "", "onRequestPermissionsResult", "requestCode", "permissions", "", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "onPermissionsGranted", "perms", "onPermissionsDenied", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MusicWidescreenActivity extends BaseActivity implements ViewPager.OnPageChangeListener, EasyPermissions.PermissionCallbacks {
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
    private RhythmLedView2 rhyledview_1;
    private RhythmLedView2 rhyledview_2;
    private RhythmLedView2 rhyledview_3;
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
    private final MusicManager musicManager = MusicManager.getInstance();
    private List<MusicVO> musicData = new ArrayList();
    private int currentPlayMode = 3;
    private final List<RhythmImage> imageList = new ArrayList();
    private final Handler handler = new Handler();
    private final int SEEK_SCOPE = 200;
    private final MusicWidescreenActivity$musicCallback$1 musicCallback = new MusicWidescreenActivity$musicCallback$1(this);
    private Runnable runnable = new Runnable() { // from class: com.wifiled.ipixels.ui.rhythm.MusicWidescreenActivity$$ExternalSyntheticLambda4
        @Override // java.lang.Runnable
        public final void run() {
            MusicWidescreenActivity.runnable$lambda$14(MusicWidescreenActivity.this);
        }
    };

    @Override // com.wifiled.baselib.base.BaseActivity
    protected int layoutId() {
        return R.layout.activity_music_wide;
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
        this.rhyledview_2 = (RhythmLedView2) findViewById12;
        View findViewById13 = findViewById(R.id.rhyledview_3);
        Intrinsics.checkNotNullExpressionValue(findViewById13, "findViewById(...)");
        this.rhyledview_3 = (RhythmLedView2) findViewById13;
        View findViewById14 = findViewById(R.id.rhyledview_1);
        Intrinsics.checkNotNullExpressionValue(findViewById14, "findViewById(...)");
        this.rhyledview_1 = (RhythmLedView2) findViewById14;
        View findViewById15 = findViewById(R.id.ll_seekbar);
        Intrinsics.checkNotNullExpressionValue(findViewById15, "findViewById(...)");
        this.ll_seekbar = (ConstraintLayout) findViewById15;
        View findViewById16 = findViewById(R.id.iv_rhythm);
        Intrinsics.checkNotNullExpressionValue(findViewById16, "findViewById(...)");
        this.iv_rhythm = (ImageView) findViewById16;
        View findViewById17 = findViewById(R.id.iv_rhy_outside_image_bg);
        Intrinsics.checkNotNullExpressionValue(findViewById17, "findViewById(...)");
        this.iv_rhy_outside_image_bg = (ImageView) findViewById17;
        View findViewById18 = findViewById(R.id.iv_pre);
        Intrinsics.checkNotNullExpressionValue(findViewById18, "findViewById(...)");
        this.iv_pre = (ImageView) findViewById18;
        View findViewById19 = findViewById(R.id.iv_play);
        Intrinsics.checkNotNullExpressionValue(findViewById19, "findViewById(...)");
        this.iv_play = (ImageView) findViewById19;
        View findViewById20 = findViewById(R.id.iv_next);
        Intrinsics.checkNotNullExpressionValue(findViewById20, "findViewById(...)");
        this.iv_next = (ImageView) findViewById20;
        View findViewById21 = findViewById(R.id.iv_mode);
        Intrinsics.checkNotNullExpressionValue(findViewById21, "findViewById(...)");
        this.iv_mode = (ImageView) findViewById21;
    }

    @Override // com.wifiled.baselib.base.BaseActivity
    protected void bindData() {
        LogUtils.file("MusicWidescreenActivity  bindData");
        requestPermission();
        initData();
        initToolbar();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        AppConfig appConfig = AppConfig.INSTANCE;
        AppConfig.isOnly = true;
        super.onResume();
    }

    @Override // com.wifiled.baselib.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        MusicCore.INSTANCE.unRegisterMusicCallback(this, this.musicCallback);
        AppConfig appConfig = AppConfig.INSTANCE;
        AppConfig.isOnly = false;
        super.onDestroy();
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
            builder3.setPositiveButton(getString(R.string.sure), new DialogInterface.OnClickListener() { // from class: com.wifiled.ipixels.ui.rhythm.MusicWidescreenActivity$$ExternalSyntheticLambda5
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    MusicWidescreenActivity.showPermissionTipDialog$lambda$0(MusicWidescreenActivity.this, dialogInterface, i);
                }
            });
        }
        CustomDialog.Builder builder4 = this.builder;
        if (builder4 != null) {
            builder4.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() { // from class: com.wifiled.ipixels.ui.rhythm.MusicWidescreenActivity$$ExternalSyntheticLambda6
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
    public static final void showPermissionTipDialog$lambda$0(MusicWidescreenActivity musicWidescreenActivity, DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        if (Build.VERSION.SDK_INT >= 33) {
            EasyPermissions.requestPermissions(musicWidescreenActivity, musicWidescreenActivity.getString(R.string.open_storage), CameraAccessExceptionCompat.CAMERA_UNAVAILABLE_DO_NOT_DISTURB, "android.permission.READ_MEDIA_AUDIO", "android.permission.RECORD_AUDIO");
        } else {
            EasyPermissions.requestPermissions(musicWidescreenActivity, musicWidescreenActivity.getString(R.string.open_storage), CameraAccessExceptionCompat.CAMERA_UNAVAILABLE_DO_NOT_DISTURB, "android.permission.READ_EXTERNAL_STORAGE", "android.permission.RECORD_AUDIO");
        }
    }

    private final void requestPermission() {
        if (Build.VERSION.SDK_INT >= 33) {
            requestPermission(new String[]{"android.permission.READ_MEDIA_AUDIO", "android.permission.RECORD_AUDIO"}, getString(R.string.open_storage), new BaseActivity.GrantedResult() { // from class: com.wifiled.ipixels.ui.rhythm.MusicWidescreenActivity$requestPermission$1
                @Override // com.wifiled.baselib.base.BaseActivity.GrantedResult
                public void onResult(boolean granted) {
                    MusicWidescreenActivity$musicCallback$1 musicWidescreenActivity$musicCallback$1;
                    MusicManager musicManager;
                    if (!granted) {
                        MusicWidescreenActivity.this.showPermissionTipDialog();
                        return;
                    }
                    MusicCore.INSTANCE.init(App.INSTANCE.getContext());
                    MusicCore musicCore = MusicCore.INSTANCE;
                    MusicWidescreenActivity musicWidescreenActivity = MusicWidescreenActivity.this;
                    MusicWidescreenActivity musicWidescreenActivity2 = musicWidescreenActivity;
                    musicWidescreenActivity$musicCallback$1 = musicWidescreenActivity.musicCallback;
                    musicCore.registerMusicCallback(musicWidescreenActivity2, musicWidescreenActivity$musicCallback$1);
                    musicManager = MusicWidescreenActivity.this.musicManager;
                    musicManager.startRhythm();
                    MusicCore.INSTANCE.startScanMusic();
                }
            });
        } else {
            requestPermission(new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.RECORD_AUDIO"}, getString(R.string.open_storage), new BaseActivity.GrantedResult() { // from class: com.wifiled.ipixels.ui.rhythm.MusicWidescreenActivity$requestPermission$2
                @Override // com.wifiled.baselib.base.BaseActivity.GrantedResult
                public void onResult(boolean granted) {
                    MusicWidescreenActivity$musicCallback$1 musicWidescreenActivity$musicCallback$1;
                    MusicManager musicManager;
                    if (!granted) {
                        MusicWidescreenActivity.this.showPermissionTipDialog();
                        return;
                    }
                    LogUtils.vTag("ruis", "startRhythm granted--" + granted);
                    MusicCore.INSTANCE.init(App.INSTANCE.getContext());
                    MusicCore musicCore = MusicCore.INSTANCE;
                    MusicWidescreenActivity musicWidescreenActivity = MusicWidescreenActivity.this;
                    MusicWidescreenActivity musicWidescreenActivity2 = musicWidescreenActivity;
                    musicWidescreenActivity$musicCallback$1 = musicWidescreenActivity.musicCallback;
                    musicCore.registerMusicCallback(musicWidescreenActivity2, musicWidescreenActivity$musicCallback$1);
                    musicManager = MusicWidescreenActivity.this.musicManager;
                    musicManager.startRhythm();
                    MusicCore.INSTANCE.startScanMusic();
                }
            });
        }
    }

    private final void requestMicPermission() {
        LogUtils.file("MusicWidescreenActivity  requestMicPermission");
        requestPermission(new String[]{"android.permission.RECORD_AUDIO"}, getString(R.string.open_bt_relative), new MusicWidescreenActivity$requestMicPermission$1(this));
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        MusicManager musicManager;
        if (this.musicManager.isPlaying() && (musicManager = this.musicManager) != null) {
            musicManager.playOrPause();
        }
        SendCore.INSTANCE.sendExitCmd(null);
        super.onBackPressed();
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
        customImageView4.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.rhythm.MusicWidescreenActivity$$ExternalSyntheticLambda15
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MusicWidescreenActivity.initToolbar$lambda$2(MusicWidescreenActivity.this, view);
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
        customImageView7.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.rhythm.MusicWidescreenActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MusicWidescreenActivity.initToolbar$lambda$3(MusicWidescreenActivity.this, view);
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
    public static final void initToolbar$lambda$2(MusicWidescreenActivity musicWidescreenActivity, View view) {
        MusicManager musicManager;
        LogUtils.file("MusicWidescreenActivity   iv_back.setOnClickListener");
        if (musicWidescreenActivity.musicManager.isPlaying() && (musicManager = musicWidescreenActivity.musicManager) != null) {
            musicManager.playOrPause();
        }
        SendCore.INSTANCE.sendExitCmd(null);
        musicWidescreenActivity.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initToolbar$lambda$3(MusicWidescreenActivity musicWidescreenActivity, View view) {
        LogUtils.file("MusicWidescreenActivity   iv_right.setOnClickListener");
        musicWidescreenActivity.requestMicPermission();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        this.isPlay = this.musicManager.isPlaying();
    }

    private final void initData() {
        initseekbarView();
        this.musicAdapter = new MusicAdapter(this, this.musicData);
        initRhyUI();
        RhythmLedView2 rhythmLedView2 = this.rhyledview_1;
        RhythmLedView2 rhythmLedView22 = null;
        if (rhythmLedView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_1");
            rhythmLedView2 = null;
        }
        rhythmLedView2.setLayerType(1, null);
        RhythmLedView2 rhythmLedView23 = this.rhyledview_1;
        if (rhythmLedView23 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_1");
            rhythmLedView23 = null;
        }
        rhythmLedView23.setMode(0);
        RhythmLedView2 rhythmLedView24 = this.rhyledview_1;
        if (rhythmLedView24 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_1");
            rhythmLedView24 = null;
        }
        rhythmLedView24.removeAllViews();
        RhythmLedView2 rhythmLedView25 = this.rhyledview_1;
        if (rhythmLedView25 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_1");
            rhythmLedView25 = null;
        }
        rhythmLedView25.init(64, 8);
        RhythmLedView2 rhythmLedView26 = this.rhyledview_2;
        if (rhythmLedView26 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_2");
            rhythmLedView26 = null;
        }
        rhythmLedView26.setLayerType(1, null);
        RhythmLedView2 rhythmLedView27 = this.rhyledview_2;
        if (rhythmLedView27 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_2");
            rhythmLedView27 = null;
        }
        rhythmLedView27.setMode(0);
        RhythmLedView2 rhythmLedView28 = this.rhyledview_2;
        if (rhythmLedView28 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_2");
            rhythmLedView28 = null;
        }
        rhythmLedView28.removeAllViews();
        RhythmLedView2 rhythmLedView29 = this.rhyledview_2;
        if (rhythmLedView29 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_2");
            rhythmLedView29 = null;
        }
        rhythmLedView29.init(64, 8);
        RhythmLedView2 rhythmLedView210 = this.rhyledview_3;
        if (rhythmLedView210 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_3");
            rhythmLedView210 = null;
        }
        rhythmLedView210.setLayerType(1, null);
        RhythmLedView2 rhythmLedView211 = this.rhyledview_3;
        if (rhythmLedView211 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_3");
            rhythmLedView211 = null;
        }
        rhythmLedView211.setMode(0);
        RhythmLedView2 rhythmLedView212 = this.rhyledview_3;
        if (rhythmLedView212 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_3");
            rhythmLedView212 = null;
        }
        rhythmLedView212.removeAllViews();
        RhythmLedView2 rhythmLedView213 = this.rhyledview_3;
        if (rhythmLedView213 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_3");
        } else {
            rhythmLedView22 = rhythmLedView213;
        }
        rhythmLedView22.init(64, 16);
    }

    private final void initRhyUI() {
        this.imageList.add(new RhythmImage(R.mipmap.rhythm_picture_16x64_1, false, null, 6, null));
        this.imageList.add(new RhythmImage(R.mipmap.rhythm_picture_16x64_2, false, null, 6, null));
        this.imageList.add(new RhythmImage(R.mipmap.rhythm_picture_16x64_3, false, null, 6, null));
        this.imageList.add(new RhythmImage(R.mipmap.rhythm_picture_16x64_4, false, null, 6, null));
        this.imageList.add(new RhythmImage(R.mipmap.rhythm_picture_16x64_5, false, null, 6, null));
        this.imageList.add(new RhythmImage(R.mipmap.rhythm_picture_16x64_6, true, null, 4, null));
        this.imageList.add(new RhythmImage(R.mipmap.rhythm_picture_16x64_7, true, null, 4, null));
        ViewPagerAllResponse viewPagerAllResponse = this.vp_rhyhm;
        ViewPagerAllResponse viewPagerAllResponse2 = null;
        if (viewPagerAllResponse == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vp_rhyhm");
            viewPagerAllResponse = null;
        }
        ViewGroup.LayoutParams layoutParams = viewPagerAllResponse.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams");
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
        MusicWidescreenActivity musicWidescreenActivity = this;
        layoutParams2.width = (int) (ScreenUtil.getScreenWidth(musicWidescreenActivity) / 1.4d);
        layoutParams2.height = (int) (ScreenUtil.getScreenWidth(musicWidescreenActivity) / 1.8d);
        ViewPagerAllResponse viewPagerAllResponse3 = this.vp_rhyhm;
        if (viewPagerAllResponse3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vp_rhyhm");
            viewPagerAllResponse3 = null;
        }
        viewPagerAllResponse3.setLayoutParams(layoutParams2);
        ViewPagerAllResponse viewPagerAllResponse4 = this.vp_rhyhm;
        if (viewPagerAllResponse4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vp_rhyhm");
            viewPagerAllResponse4 = null;
        }
        this.textImageIconAdapter = new ViewpagerAdapter(musicWidescreenActivity, viewPagerAllResponse4, this.imageList);
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
        viewPagerAllResponse7.setPageMargin(ScreenUtil.dp2px(musicWidescreenActivity, -10.0f));
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
            viewPagerAllResponse2 = viewPagerAllResponse11;
        }
        int currentItem = viewPagerAllResponse2.getCurrentItem() % this.imageList.size();
        this.curSelectMode = currentItem;
        this.curSelectPosition = currentItem;
    }

    private final void initseekbarView() {
        ConstraintLayout constraintLayout = this.ll_seekbar;
        if (constraintLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ll_seekbar");
            constraintLayout = null;
        }
        constraintLayout.setOnTouchListener(new View.OnTouchListener() { // from class: com.wifiled.ipixels.ui.rhythm.MusicWidescreenActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                boolean initseekbarView$lambda$4;
                initseekbarView$lambda$4 = MusicWidescreenActivity.initseekbarView$lambda$4(MusicWidescreenActivity.this, view, motionEvent);
                return initseekbarView$lambda$4;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean initseekbarView$lambda$4(MusicWidescreenActivity musicWidescreenActivity, View view, MotionEvent motionEvent) {
        LogUtils.file("MusicWidescreenActivity   ll_seekbar.setOnTouchListener");
        Rect rect = new Rect();
        SeekBar seekBar = musicWidescreenActivity.sb;
        SeekBar seekBar2 = null;
        if (seekBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sb");
            seekBar = null;
        }
        seekBar.getHitRect(rect);
        if (motionEvent.getY() < rect.top - musicWidescreenActivity.SEEK_SCOPE || motionEvent.getY() > rect.bottom + musicWidescreenActivity.SEEK_SCOPE || motionEvent.getX() < rect.left || motionEvent.getX() > rect.right) {
            return false;
        }
        MotionEvent obtain = MotionEvent.obtain(motionEvent.getDownTime(), motionEvent.getEventTime(), motionEvent.getAction(), motionEvent.getX() - rect.left, rect.top + (rect.height() / 2.0f), motionEvent.getMetaState());
        SeekBar seekBar3 = musicWidescreenActivity.sb;
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
        musicAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() { // from class: com.wifiled.ipixels.ui.rhythm.MusicWidescreenActivity$$ExternalSyntheticLambda7
            @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter.OnItemClickListener
            public final void onItemClick(ViewGroup viewGroup, View view, Object obj, int i) {
                MusicWidescreenActivity.bindListener$lambda$5(MusicWidescreenActivity.this, viewGroup, view, (MusicVO) obj, i);
            }
        });
        ImageView imageView = this.iv_mode;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_mode");
            imageView = null;
        }
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.rhythm.MusicWidescreenActivity$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MusicWidescreenActivity.bindListener$lambda$6(MusicWidescreenActivity.this, view);
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
        imageView3.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.rhythm.MusicWidescreenActivity$$ExternalSyntheticLambda9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MusicWidescreenActivity.bindListener$lambda$7(MusicWidescreenActivity.this, view);
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
        imageView5.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.rhythm.MusicWidescreenActivity$$ExternalSyntheticLambda10
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MusicWidescreenActivity.bindListener$lambda$8(MusicWidescreenActivity.this, view);
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
        imageView7.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.rhythm.MusicWidescreenActivity$$ExternalSyntheticLambda11
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MusicWidescreenActivity.bindListener$lambda$9(MusicWidescreenActivity.this, view);
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
        imageView9.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.rhythm.MusicWidescreenActivity$$ExternalSyntheticLambda12
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MusicWidescreenActivity.bindListener$lambda$10(MusicWidescreenActivity.this, view);
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
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.wifiled.ipixels.ui.rhythm.MusicWidescreenActivity$bindListener$7
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar2, int progress, boolean fromUser) {
                MusicManager musicManager;
                MusicManager musicManager2;
                MusicManager musicManager3;
                if (Ref.IntRef.this.element != progress) {
                    Ref.IntRef.this.element = progress;
                    Integer valueOf = seekBar2 != null ? Integer.valueOf(seekBar2.getProgress()) : null;
                    musicManager = this.musicManager;
                    com.wifiled.baselib.utils.LogUtils.loge("onProgressChanged：" + valueOf + " isPlay:" + musicManager.isPlaying(), new Object[0]);
                    musicManager2 = this.musicManager;
                    if (musicManager2.isPlaying()) {
                        return;
                    }
                    Integer valueOf2 = seekBar2 != null ? Integer.valueOf(seekBar2.getProgress()) : null;
                    Intrinsics.checkNotNull(valueOf2);
                    if (valueOf2.intValue() >= 100) {
                        musicManager3 = this.musicManager;
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
                    MusicWidescreenActivity musicWidescreenActivity = this;
                    musicWidescreenActivity.toast(musicWidescreenActivity.getString(R.string.msg_music_list_empty));
                }
                musicManager = this.musicManager;
                if (musicManager.isPlaying()) {
                    musicManager2 = this.musicManager;
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
                LogUtils.file("MusicWidescreenActivity   sb.setOnSeekBarChangeListener onStopTrackingTouch");
                musicAdapter2 = this.musicAdapter;
                if (musicAdapter2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("musicAdapter");
                    musicAdapter2 = null;
                }
                if (musicAdapter2.getItemCount() <= 0) {
                    MusicWidescreenActivity musicWidescreenActivity = this;
                    musicWidescreenActivity.toast(musicWidescreenActivity.getString(R.string.msg_music_list_empty));
                }
                if (seekBar2 != null) {
                    int progress = seekBar2.getProgress();
                    musicManager4 = this.musicManager;
                    musicManager4.changeSeek(progress);
                }
                Integer valueOf = seekBar2 != null ? Integer.valueOf(seekBar2.getProgress()) : null;
                musicManager = this.musicManager;
                com.wifiled.baselib.utils.LogUtils.loge("process：" + valueOf + " isPlay:" + musicManager.isPlaying(), new Object[0]);
                musicManager2 = this.musicManager;
                if (musicManager2.isPlaying()) {
                    return;
                }
                musicManager3 = this.musicManager;
                musicManager3.getMusicPlayer().resume();
            }
        });
        RelativeLayout relativeLayout2 = this.rl_root;
        if (relativeLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rl_root");
        } else {
            relativeLayout = relativeLayout2;
        }
        relativeLayout.setOnTouchListener(new View.OnTouchListener() { // from class: com.wifiled.ipixels.ui.rhythm.MusicWidescreenActivity$$ExternalSyntheticLambda13
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                boolean bindListener$lambda$11;
                bindListener$lambda$11 = MusicWidescreenActivity.bindListener$lambda$11(MusicWidescreenActivity.this, view, motionEvent);
                return bindListener$lambda$11;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$5(MusicWidescreenActivity musicWidescreenActivity, ViewGroup viewGroup, View view, MusicVO musicVO, int i) {
        LogUtils.file("MusicWidescreenActivity    musicAdapter.setOnItemClickListener");
        musicWidescreenActivity.musicManager.playItem(musicWidescreenActivity.musicData.get(i));
        MusicAdapter musicAdapter = musicWidescreenActivity.musicAdapter;
        if (musicAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("musicAdapter");
            musicAdapter = null;
        }
        musicAdapter.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$6(MusicWidescreenActivity musicWidescreenActivity, View view) {
        LogUtils.file("MusicWidescreenActivity    iv_mode.setOnClickListener");
        musicWidescreenActivity.changeMode();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$7(MusicWidescreenActivity musicWidescreenActivity, View view) {
        LogUtils.file("MusicWidescreenActivity    iv_rhythm.setOnClickListener");
        musicWidescreenActivity.showMusicItem();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$8(MusicWidescreenActivity musicWidescreenActivity, View view) {
        LogUtils.file("MusicWidescreenActivity    iv_pre.setOnClickListener");
        MusicAdapter musicAdapter = musicWidescreenActivity.musicAdapter;
        if (musicAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("musicAdapter");
            musicAdapter = null;
        }
        if (musicAdapter.getItemCount() <= 0) {
            musicWidescreenActivity.toast(musicWidescreenActivity.getString(R.string.msg_music_list_empty));
        }
        if (UtilsExtensionKt.isRepeatClick(500L)) {
            return;
        }
        musicWidescreenActivity.musicManager.pre();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$9(MusicWidescreenActivity musicWidescreenActivity, View view) {
        LogUtils.file("MusicWidescreenActivity    iv_next.setOnClickListener");
        MusicAdapter musicAdapter = musicWidescreenActivity.musicAdapter;
        if (musicAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("musicAdapter");
            musicAdapter = null;
        }
        if (musicAdapter.getItemCount() <= 0) {
            musicWidescreenActivity.toast(musicWidescreenActivity.getString(R.string.msg_music_list_empty));
        }
        if (UtilsExtensionKt.isRepeatClick(500L)) {
            return;
        }
        musicWidescreenActivity.musicManager.next();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$10(MusicWidescreenActivity musicWidescreenActivity, View view) {
        MusicAdapter musicAdapter = musicWidescreenActivity.musicAdapter;
        if (musicAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("musicAdapter");
            musicAdapter = null;
        }
        if (musicAdapter.getItemCount() <= 0) {
            musicWidescreenActivity.toast(musicWidescreenActivity.getString(R.string.msg_music_list_empty));
        }
        musicWidescreenActivity.musicManager.playOrPause();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean bindListener$lambda$11(MusicWidescreenActivity musicWidescreenActivity, View view, MotionEvent motionEvent) {
        ViewPagerAllResponse viewPagerAllResponse = musicWidescreenActivity.vp_rhyhm;
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
            MusicWidescreenActivity musicWidescreenActivity = this;
            double screenHeight = ScreenUtil.getScreenHeight(musicWidescreenActivity) * 0.75d;
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
                recyclerView2.setLayoutManager(new LinearLayoutManager(musicWidescreenActivity));
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
        this.musicManager.changeMode(this.currentPlayMode);
    }

    public final void startScanMusic(List<? extends MusicVO> musicList) {
        Intrinsics.checkNotNullParameter(musicList, "musicList");
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
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.rhythm.MusicWidescreenActivity$$ExternalSyntheticLambda14
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit onPageScrolled$lambda$13;
                onPageScrolled$lambda$13 = MusicWidescreenActivity.onPageScrolled$lambda$13(MusicWidescreenActivity.this);
                return onPageScrolled$lambda$13;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onPageScrolled$lambda$13(MusicWidescreenActivity musicWidescreenActivity) {
        ImageView imageView = musicWidescreenActivity.iv_rhy_outside_image_bg;
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
            rhyUIShow(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void rhyUIShow(boolean isVisible) {
        this.handler.removeCallbacks(this.runnable);
        ViewpagerAdapter viewpagerAdapter = null;
        ImageView imageView = null;
        if (isVisible) {
            LinearLayout linearLayout = this.rl_rhy_bg;
            if (linearLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rl_rhy_bg");
                linearLayout = null;
            }
            UtilsExtensionKt.show(linearLayout);
            ImageView imageView2 = this.iv_rhy_outside_image_bg;
            if (imageView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("iv_rhy_outside_image_bg");
            } else {
                imageView = imageView2;
            }
            UtilsExtensionKt.show(imageView);
            this.handler.postDelayed(this.runnable, 100L);
            return;
        }
        RhythmLedView2 rhythmLedView2 = this.rhyledview_1;
        if (rhythmLedView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_1");
            rhythmLedView2 = null;
        }
        UtilsExtensionKt.hide(rhythmLedView2);
        RhythmLedView2 rhythmLedView22 = this.rhyledview_2;
        if (rhythmLedView22 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_2");
            rhythmLedView22 = null;
        }
        UtilsExtensionKt.hide(rhythmLedView22);
        RhythmLedView2 rhythmLedView23 = this.rhyledview_3;
        if (rhythmLedView23 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_3");
            rhythmLedView23 = null;
        }
        UtilsExtensionKt.hide(rhythmLedView23);
        LinearLayout linearLayout2 = this.rl_rhy_bg;
        if (linearLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rl_rhy_bg");
            linearLayout2 = null;
        }
        UtilsExtensionKt.hide(linearLayout2);
        ImageView imageView3 = this.iv_rhy_outside_image_bg;
        if (imageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_rhy_outside_image_bg");
            imageView3 = null;
        }
        UtilsExtensionKt.invisible(imageView3);
        ViewpagerAdapter viewpagerAdapter2 = this.textImageIconAdapter;
        if (viewpagerAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("textImageIconAdapter");
        } else {
            viewpagerAdapter = viewpagerAdapter2;
        }
        viewpagerAdapter.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void runnable$lambda$14(MusicWidescreenActivity musicWidescreenActivity) {
        ViewpagerAdapter viewpagerAdapter = null;
        if (musicWidescreenActivity.curSelectPosition == 3) {
            RhythmLedView2 rhythmLedView2 = musicWidescreenActivity.rhyledview_3;
            if (rhythmLedView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rhyledview_3");
                rhythmLedView2 = null;
            }
            UtilsExtensionKt.show(rhythmLedView2);
            RhythmLedView2 rhythmLedView22 = musicWidescreenActivity.rhyledview_1;
            if (rhythmLedView22 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rhyledview_1");
                rhythmLedView22 = null;
            }
            UtilsExtensionKt.hide(rhythmLedView22);
            RhythmLedView2 rhythmLedView23 = musicWidescreenActivity.rhyledview_2;
            if (rhythmLedView23 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rhyledview_2");
                rhythmLedView23 = null;
            }
            UtilsExtensionKt.hide(rhythmLedView23);
        } else {
            RhythmLedView2 rhythmLedView24 = musicWidescreenActivity.rhyledview_3;
            if (rhythmLedView24 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rhyledview_3");
                rhythmLedView24 = null;
            }
            UtilsExtensionKt.hide(rhythmLedView24);
            RhythmLedView2 rhythmLedView25 = musicWidescreenActivity.rhyledview_1;
            if (rhythmLedView25 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rhyledview_1");
                rhythmLedView25 = null;
            }
            UtilsExtensionKt.show(rhythmLedView25);
            RhythmLedView2 rhythmLedView26 = musicWidescreenActivity.rhyledview_2;
            if (rhythmLedView26 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rhyledview_2");
                rhythmLedView26 = null;
            }
            UtilsExtensionKt.show(rhythmLedView26);
        }
        LogUtils.i(musicWidescreenActivity.TAG + ">>>[curSelectPosition]:  " + musicWidescreenActivity.curSelectPosition);
        ViewpagerAdapter viewpagerAdapter2 = musicWidescreenActivity.textImageIconAdapter;
        if (viewpagerAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("textImageIconAdapter");
        } else {
            viewpagerAdapter = viewpagerAdapter2;
        }
        viewpagerAdapter.notifyDataSetChanged();
    }

    private final void showCurRhyMode(int currentImage) {
        if (this.musicManager.isPlaying()) {
            rhyUIShow(true);
        } else {
            rhyUIShow(false);
        }
        RhythmLedView2 rhythmLedView2 = null;
        RhythmLedView2 rhythmLedView22 = null;
        LinearLayout linearLayout = null;
        RhythmLedView2 rhythmLedView23 = null;
        RhythmLedView2 rhythmLedView24 = null;
        switch (currentImage) {
            case 0:
                LinearLayout linearLayout2 = this.rl_rhy_bg;
                if (linearLayout2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rl_rhy_bg");
                    linearLayout2 = null;
                }
                linearLayout2.setRotation(0.0f);
                RhythmLedView2 rhythmLedView25 = this.rhyledview_1;
                if (rhythmLedView25 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rhyledview_1");
                    rhythmLedView25 = null;
                }
                rhythmLedView25.setRotationX(0.0f);
                RhythmLedView2 rhythmLedView26 = this.rhyledview_2;
                if (rhythmLedView26 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rhyledview_2");
                } else {
                    rhythmLedView2 = rhythmLedView26;
                }
                rhythmLedView2.setRotationX(180.0f);
                selectRhythmMode(1);
                break;
            case 1:
                LinearLayout linearLayout3 = this.rl_rhy_bg;
                if (linearLayout3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rl_rhy_bg");
                    linearLayout3 = null;
                }
                linearLayout3.setRotation(0.0f);
                RhythmLedView2 rhythmLedView27 = this.rhyledview_1;
                if (rhythmLedView27 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rhyledview_1");
                    rhythmLedView27 = null;
                }
                rhythmLedView27.setRotationX(0.0f);
                RhythmLedView2 rhythmLedView28 = this.rhyledview_2;
                if (rhythmLedView28 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rhyledview_2");
                } else {
                    rhythmLedView24 = rhythmLedView28;
                }
                rhythmLedView24.setRotationX(180.0f);
                selectRhythmMode(2);
                break;
            case 2:
                LinearLayout linearLayout4 = this.rl_rhy_bg;
                if (linearLayout4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rl_rhy_bg");
                    linearLayout4 = null;
                }
                linearLayout4.setRotation(0.0f);
                RhythmLedView2 rhythmLedView29 = this.rhyledview_1;
                if (rhythmLedView29 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rhyledview_1");
                    rhythmLedView29 = null;
                }
                rhythmLedView29.setRotationX(0.0f);
                RhythmLedView2 rhythmLedView210 = this.rhyledview_2;
                if (rhythmLedView210 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rhyledview_2");
                } else {
                    rhythmLedView23 = rhythmLedView210;
                }
                rhythmLedView23.setRotationX(180.0f);
                selectRhythmMode(3);
                break;
            case 3:
                RhythmLedView2 rhythmLedView211 = this.rhyledview_3;
                if (rhythmLedView211 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rhyledview_3");
                    rhythmLedView211 = null;
                }
                rhythmLedView211.setRotationX(0.0f);
                LinearLayout linearLayout5 = this.rl_rhy_bg;
                if (linearLayout5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rl_rhy_bg");
                } else {
                    linearLayout = linearLayout5;
                }
                linearLayout.setRotation(0.0f);
                selectRhythmMode(4);
                break;
            case 4:
                LinearLayout linearLayout6 = this.rl_rhy_bg;
                if (linearLayout6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rl_rhy_bg");
                    linearLayout6 = null;
                }
                linearLayout6.setRotation(0.0f);
                RhythmLedView2 rhythmLedView212 = this.rhyledview_1;
                if (rhythmLedView212 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rhyledview_1");
                    rhythmLedView212 = null;
                }
                rhythmLedView212.setRotationX(0.0f);
                RhythmLedView2 rhythmLedView213 = this.rhyledview_2;
                if (rhythmLedView213 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rhyledview_2");
                } else {
                    rhythmLedView22 = rhythmLedView213;
                }
                rhythmLedView22.setRotationX(180.0f);
                selectRhythmMode(5);
                break;
            case 5:
                selectRhythmMode(7);
                break;
            case 6:
                selectRhythmMode(6);
                break;
            default:
                selectRhythmMode(currentImage + 1);
                break;
        }
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
                this.curSelectMode = 6;
                break;
            case 7:
                this.curSelectMode = 5;
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setRhyData(byte[] data) {
        Log.v("ruis", "setRhyData====" + this.curSelectMode);
        int i = this.curSelectMode;
        RhythmLedView2 rhythmLedView2 = null;
        if (i == 0) {
            List<Integer> rhyData1_wide = RhythmDataUitls.getRhyData1_wide(data);
            Intrinsics.checkNotNullExpressionValue(rhyData1_wide, "getRhyData1_wide(...)");
            RhythmLedView2 rhythmLedView22 = this.rhyledview_1;
            if (rhythmLedView22 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rhyledview_1");
                rhythmLedView22 = null;
            }
            rhythmLedView22.updateRhythmUI(rhyData1_wide);
            RhythmLedView2 rhythmLedView23 = this.rhyledview_2;
            if (rhythmLedView23 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rhyledview_2");
            } else {
                rhythmLedView2 = rhythmLedView23;
            }
            rhythmLedView2.updateRhythmUI(rhyData1_wide);
            return;
        }
        if (i == 1) {
            List<Integer> rhyData2_wide = RhythmDataUitls.getRhyData2_wide(data);
            Intrinsics.checkNotNullExpressionValue(rhyData2_wide, "getRhyData2_wide(...)");
            RhythmLedView2 rhythmLedView24 = this.rhyledview_1;
            if (rhythmLedView24 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rhyledview_1");
                rhythmLedView24 = null;
            }
            rhythmLedView24.setRhyData2(rhyData2_wide);
            RhythmLedView2 rhythmLedView25 = this.rhyledview_2;
            if (rhythmLedView25 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rhyledview_2");
            } else {
                rhythmLedView2 = rhythmLedView25;
            }
            rhythmLedView2.setRhyData2(rhyData2_wide);
            return;
        }
        if (i == 2) {
            List<Integer> rhyData3_wide = RhythmDataUitls.getRhyData3_wide(data);
            Intrinsics.checkNotNullExpressionValue(rhyData3_wide, "getRhyData3_wide(...)");
            RhythmLedView2 rhythmLedView26 = this.rhyledview_1;
            if (rhythmLedView26 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rhyledview_1");
                rhythmLedView26 = null;
            }
            rhythmLedView26.setRhyData3(rhyData3_wide);
            RhythmLedView2 rhythmLedView27 = this.rhyledview_2;
            if (rhythmLedView27 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rhyledview_2");
            } else {
                rhythmLedView2 = rhythmLedView27;
            }
            rhythmLedView2.setRhyData3(rhyData3_wide);
            return;
        }
        if (i == 3) {
            List<Integer> rhyData4_wide = RhythmDataUitls.getRhyData4_wide(data);
            Intrinsics.checkNotNullExpressionValue(rhyData4_wide, "getRhyData4_wide(...)");
            RhythmLedView2 rhythmLedView28 = this.rhyledview_3;
            if (rhythmLedView28 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rhyledview_3");
            } else {
                rhythmLedView2 = rhythmLedView28;
            }
            rhythmLedView2.setRhyData4(rhyData4_wide);
            return;
        }
        if (i != 4) {
            return;
        }
        List<Integer> rhyData5_wide = RhythmDataUitls.getRhyData5_wide(data);
        Intrinsics.checkNotNullExpressionValue(rhyData5_wide, "getRhyData5_wide(...)");
        RhythmLedView2 rhythmLedView29 = this.rhyledview_1;
        if (rhythmLedView29 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_1");
            rhythmLedView29 = null;
        }
        rhythmLedView29.setRhyData5(rhyData5_wide);
        RhythmLedView2 rhythmLedView210 = this.rhyledview_2;
        if (rhythmLedView210 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rhyledview_2");
        } else {
            rhythmLedView2 = rhythmLedView210;
        }
        rhythmLedView2.setRhyData5(rhyData5_wide);
    }

    @Override // com.wifiled.baselib.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        LogUtils.vTag("ruis", "onRequestPermissionsResult");
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override // pub.devrel.easypermissions.EasyPermissions.PermissionCallbacks
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        Intrinsics.checkNotNullParameter(perms, "perms");
        if (requestCode == 10001 && perms.contains("android.permission.RECORD_AUDIO")) {
            LogUtils.vTag("ruis", "onPermissionsGranted perms -" + perms);
            MusicCore.INSTANCE.init(App.INSTANCE.getContext());
            MusicCore.INSTANCE.registerMusicCallback(this, this.musicCallback);
            this.musicManager.startRhythm();
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
            builder.setPositiveButton(getString(R.string.sure), new DialogInterface.OnClickListener() { // from class: com.wifiled.ipixels.ui.rhythm.MusicWidescreenActivity$$ExternalSyntheticLambda2
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    MusicWidescreenActivity.onPermissionsDenied$lambda$15(MusicWidescreenActivity.this, dialogInterface, i);
                }
            });
            builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() { // from class: com.wifiled.ipixels.ui.rhythm.MusicWidescreenActivity$$ExternalSyntheticLambda3
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            builder.create().show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onPermissionsDenied$lambda$15(MusicWidescreenActivity musicWidescreenActivity, DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        App.INSTANCE.gotoAppDetailIntent(musicWidescreenActivity);
    }
}
