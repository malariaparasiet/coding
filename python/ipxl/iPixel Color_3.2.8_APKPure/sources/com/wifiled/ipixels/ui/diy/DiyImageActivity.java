package com.wifiled.ipixels.ui.diy;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.PickVisualMediaRequestKt;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.UriUtils;
import com.wifiled.baselib.app.language.LanguageUtil;
import com.wifiled.baselib.base.BaseActivity;
import com.wifiled.baselib.base.recycleview.RecyclerAdapter;
import com.wifiled.baselib.base.recycleview.RecyclerViewHolder;
import com.wifiled.baselib.utils.DialogUtils;
import com.wifiled.baselib.utils.ScreenUtil;
import com.wifiled.ipixels.App;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.UtilsExtensionKt;
import com.wifiled.ipixels.callback.CallBack;
import com.wifiled.ipixels.core.SendCore;
import com.wifiled.ipixels.db.AppDatabase;
import com.wifiled.ipixels.db.ImageDao;
import com.wifiled.ipixels.ui.UpDataState;
import com.wifiled.ipixels.ui.adapter.IosDialogStyleAdapter;
import com.wifiled.ipixels.ui.gallery.GalleryActivity;
import com.wifiled.ipixels.utils.BGRUtils;
import com.wifiled.ipixels.utils.BitmapUtils;
import com.wifiled.ipixels.utils.FileUtil;
import com.wifiled.ipixels.utils.TimeHelper;
import com.wifiled.ipixels.view.ColorBarView;
import com.wifiled.ipixels.view.LedView;
import com.wifiled.ipixels.view.customview.CustomImageView;
import com.yalantis.ucrop.UCrop;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* compiled from: DiyImageActivity.kt */
@Metadata(d1 = {"\u0000\u009c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 X2\u00020\u0001:\u0001XB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0011\u001a\u00020\u0012H\u0014J\u0010\u00107\u001a\u0002082\u0006\u00109\u001a\u00020:H\u0007J\b\u0010;\u001a\u000208H\u0014J\b\u0010<\u001a\u000208H\u0014J\b\u0010=\u001a\u000208H\u0014J\b\u0010>\u001a\u000208H\u0014J\b\u0010?\u001a\u000208H\u0014J\b\u0010@\u001a\u000208H\u0002J\b\u0010A\u001a\u000208H\u0016J)\u0010B\u001a\u0002082\u0006\u0010C\u001a\u00020\u00052\u0017\u0010D\u001a\u0013\u0012\u0004\u0012\u00020F\u0012\u0004\u0012\u0002080E¢\u0006\u0002\bGH\u0002J\b\u0010H\u001a\u000208H\u0002J\b\u0010I\u001a\u000208H\u0014J\b\u0010J\u001a\u000208H\u0002J\b\u0010K\u001a\u000208H\u0002J\"\u0010R\u001a\u0002082\u0006\u0010S\u001a\u00020\u00122\u0006\u0010T\u001a\u00020\u00122\b\u0010U\u001a\u0004\u0018\u00010VH\u0014J\b\u0010W\u001a\u000208H\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u0016\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0012X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00120\u0018X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00120\u001aX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020$X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020$X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\"X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\"X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020*X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020,X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020$X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020$X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020$X\u0082.¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020$X\u0082.¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020$X\u0082.¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020$X\u0082.¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020$X\u0082.¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020*X\u0082.¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u00020$X\u0082.¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u00020$X\u0082.¢\u0006\u0002\n\u0000R\u001f\u0010L\u001a\u0010\u0012\f\u0012\n O*\u0004\u0018\u00010N0N0M¢\u0006\b\n\u0000\u001a\u0004\bP\u0010Q¨\u0006Y"}, d2 = {"Lcom/wifiled/ipixels/ui/diy/DiyImageActivity;", "Lcom/wifiled/baselib/base/BaseActivity;", "<init>", "()V", "bgrData", "", "imageDao", "Lcom/wifiled/ipixels/db/ImageDao;", "getImageDao", "()Lcom/wifiled/ipixels/db/ImageDao;", "imageDao$delegate", "Lkotlin/Lazy;", "mAdapter", "Lcom/wifiled/ipixels/ui/adapter/IosDialogStyleAdapter;", "", "actionRecyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "layoutId", "", "mBundle", "Landroid/os/Bundle;", "REQUEST_TAKE_PHOTO_CODE", "selPos", "colors", "", "adapter", "Lcom/wifiled/baselib/base/recycleview/RecyclerAdapter;", "mIsTemplate", "mTemplateWidth", "mTemplateHeight", "ledView", "Lcom/wifiled/ipixels/view/LedView;", "recyclerview_color", "iv_show_diycolor", "Lcom/wifiled/ipixels/view/customview/CustomImageView;", "btn_undo", "Landroid/widget/ImageView;", "btn_redo", "btn_import", "iv_right", "iv_back", "tv_title", "Landroid/widget/TextView;", "colorBarView", "Lcom/wifiled/ipixels/view/ColorBarView;", "btn_horizontal_mirror", "btn_vertical_mirror", "iv_diy_anim_clear", "btn_move_outside_bg", "btn_move_painted", "btn_paint", "btn_eraser", "tv_scale_value", "btn_increase", "btn_decrease", "onUpDataState", "", "instance", "Lcom/wifiled/ipixels/ui/UpDataState;", "initView", "bindData", "onStart", "onResume", "onDestroy", "initToolBar", "onBackPressed", "doSaveImage", "arrByte", "callbackBuilder", "Lkotlin/Function1;", "Lcom/wifiled/ipixels/callback/CallBack;", "Lkotlin/ExtensionFunctionType;", "showLedType", "bindListener", "initIosDialogAdapter", "chooseImage", "pickMedia", "Landroidx/activity/result/ActivityResultLauncher;", "Landroidx/activity/result/PickVisualMediaRequest;", "kotlin.jvm.PlatformType", "getPickMedia", "()Landroidx/activity/result/ActivityResultLauncher;", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "resetUi", "Companion", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DiyImageActivity extends BaseActivity {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static boolean bRoutFromAnimActivity;
    private RecyclerView actionRecyclerView;
    private RecyclerAdapter<Integer> adapter;
    private byte[] bgrData;
    private ImageView btn_decrease;
    private ImageView btn_eraser;
    private ImageView btn_horizontal_mirror;
    private ImageView btn_import;
    private ImageView btn_increase;
    private ImageView btn_move_outside_bg;
    private ImageView btn_move_painted;
    private ImageView btn_paint;
    private ImageView btn_redo;
    private ImageView btn_undo;
    private ImageView btn_vertical_mirror;
    private ColorBarView colorBarView;
    private List<Integer> colors;
    private CustomImageView iv_back;
    private ImageView iv_diy_anim_clear;
    private CustomImageView iv_right;
    private CustomImageView iv_show_diycolor;
    private LedView ledView;
    private IosDialogStyleAdapter<Object> mAdapter;
    private Bundle mBundle;
    private int mIsTemplate;
    private int mTemplateHeight;
    private int mTemplateWidth;
    private final ActivityResultLauncher<PickVisualMediaRequest> pickMedia;
    private RecyclerView recyclerview_color;
    private TextView tv_scale_value;
    private TextView tv_title;

    /* renamed from: imageDao$delegate, reason: from kotlin metadata */
    private final Lazy imageDao = LazyKt.lazy(new Function0() { // from class: com.wifiled.ipixels.ui.diy.DiyImageActivity$$ExternalSyntheticLambda10
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            ImageDao imageDao_delegate$lambda$0;
            imageDao_delegate$lambda$0 = DiyImageActivity.imageDao_delegate$lambda$0();
            return imageDao_delegate$lambda$0;
        }
    });
    private final int REQUEST_TAKE_PHOTO_CODE = 13;
    private int selPos = -1;

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initToolBar$lambda$8$lambda$7$lambda$6$lambda$5$lambda$4(int i, Intent intent) {
    }

    @Override // com.wifiled.baselib.base.BaseActivity
    protected int layoutId() {
        return R.layout.fragment_diy_image;
    }

    public DiyImageActivity() {
        ActivityResultLauncher<PickVisualMediaRequest> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), new ActivityResultCallback() { // from class: com.wifiled.ipixels.ui.diy.DiyImageActivity$$ExternalSyntheticLambda12
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                DiyImageActivity.pickMedia$lambda$36(DiyImageActivity.this, (Uri) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResult(...)");
        this.pickMedia = registerForActivityResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ImageDao getImageDao() {
        return (ImageDao) this.imageDao.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ImageDao imageDao_delegate$lambda$0() {
        return AppDatabase.INSTANCE.getDatabase().imageDao();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onUpDataState(UpDataState instance) {
        Intrinsics.checkNotNullParameter(instance, "instance");
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    @Override // com.wifiled.baselib.base.BaseActivity
    protected void initView() {
        super.initView();
        View findViewById = findViewById(R.id.ledView);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.ledView = (LedView) findViewById;
        View findViewById2 = findViewById(R.id.recyclerview_color);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.recyclerview_color = (RecyclerView) findViewById2;
        View findViewById3 = findViewById(R.id.iv_show_diycolor);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.iv_show_diycolor = (CustomImageView) findViewById3;
        View findViewById4 = findViewById(R.id.btn_redo);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
        this.btn_redo = (ImageView) findViewById4;
        View findViewById5 = findViewById(R.id.btn_undo);
        Intrinsics.checkNotNullExpressionValue(findViewById5, "findViewById(...)");
        this.btn_undo = (ImageView) findViewById5;
        View findViewById6 = findViewById(R.id.btn_import);
        Intrinsics.checkNotNullExpressionValue(findViewById6, "findViewById(...)");
        this.btn_import = (ImageView) findViewById6;
        View findViewById7 = findViewById(R.id.iv_right);
        Intrinsics.checkNotNullExpressionValue(findViewById7, "findViewById(...)");
        this.iv_right = (CustomImageView) findViewById7;
        View findViewById8 = findViewById(R.id.iv_back);
        Intrinsics.checkNotNullExpressionValue(findViewById8, "findViewById(...)");
        this.iv_back = (CustomImageView) findViewById8;
        View findViewById9 = findViewById(R.id.tv_title);
        Intrinsics.checkNotNullExpressionValue(findViewById9, "findViewById(...)");
        this.tv_title = (TextView) findViewById9;
        View findViewById10 = findViewById(R.id.colorBarView);
        Intrinsics.checkNotNullExpressionValue(findViewById10, "findViewById(...)");
        this.colorBarView = (ColorBarView) findViewById10;
        View findViewById11 = findViewById(R.id.btn_horizontal_mirror);
        Intrinsics.checkNotNullExpressionValue(findViewById11, "findViewById(...)");
        this.btn_horizontal_mirror = (ImageView) findViewById11;
        View findViewById12 = findViewById(R.id.btn_vertical_mirror);
        Intrinsics.checkNotNullExpressionValue(findViewById12, "findViewById(...)");
        this.btn_vertical_mirror = (ImageView) findViewById12;
        View findViewById13 = findViewById(R.id.iv_diy_anim_clear);
        Intrinsics.checkNotNullExpressionValue(findViewById13, "findViewById(...)");
        this.iv_diy_anim_clear = (ImageView) findViewById13;
        View findViewById14 = findViewById(R.id.btn_move_outside_bg);
        Intrinsics.checkNotNullExpressionValue(findViewById14, "findViewById(...)");
        this.btn_move_outside_bg = (ImageView) findViewById14;
        View findViewById15 = findViewById(R.id.btn_move_painted);
        Intrinsics.checkNotNullExpressionValue(findViewById15, "findViewById(...)");
        this.btn_move_painted = (ImageView) findViewById15;
        View findViewById16 = findViewById(R.id.btn_paint);
        Intrinsics.checkNotNullExpressionValue(findViewById16, "findViewById(...)");
        this.btn_paint = (ImageView) findViewById16;
        View findViewById17 = findViewById(R.id.btn_eraser);
        Intrinsics.checkNotNullExpressionValue(findViewById17, "findViewById(...)");
        this.btn_eraser = (ImageView) findViewById17;
        View findViewById18 = findViewById(R.id.tv_scale_value);
        Intrinsics.checkNotNullExpressionValue(findViewById18, "findViewById(...)");
        this.tv_scale_value = (TextView) findViewById18;
        View findViewById19 = findViewById(R.id.btn_increase);
        Intrinsics.checkNotNullExpressionValue(findViewById19, "findViewById(...)");
        this.btn_increase = (ImageView) findViewById19;
        View findViewById20 = findViewById(R.id.btn_decrease);
        Intrinsics.checkNotNullExpressionValue(findViewById20, "findViewById(...)");
        this.btn_decrease = (ImageView) findViewById20;
    }

    @Override // com.wifiled.baselib.base.BaseActivity
    protected void bindData() {
        LogUtils.file("DiyImageActivity  bindData");
        EventBus.getDefault().register(this);
        AppConfig.INSTANCE.setNextActivityName(AppConfig.INSTANCE.getTopActivity());
        initIosDialogAdapter();
        initToolBar();
        this.mIsTemplate = getIntent().getIntExtra("type", 0);
        bRoutFromAnimActivity = getIntent().getBooleanExtra("fromDiy", false);
        this.mBundle = getIntent().getExtras();
        byte[] byteArrayExtra = getIntent().getByteArrayExtra("bgr");
        ImageView imageView = null;
        if (byteArrayExtra != null) {
            this.bgrData = byteArrayExtra;
            LedView ledView = this.ledView;
            if (ledView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ledView");
                ledView = null;
            }
            ledView.clear();
        }
        SendCore.INSTANCE.setDiyFunMode(DiyImageFun.ENTER_CLEAR_CUR_SHOW.getMode(), null);
        LedView ledView2 = this.ledView;
        if (ledView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ledView");
            ledView2 = null;
        }
        ledView2.setMode(1);
        LedView ledView3 = this.ledView;
        if (ledView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ledView");
            ledView3 = null;
        }
        ledView3.setLayerType(1, null);
        showLedType();
        final List<Integer> listOf = CollectionsKt.listOf((Object[]) new Integer[]{Integer.valueOf(Color.parseColor("#FF0000")), Integer.valueOf(Color.parseColor("#FF8A00")), Integer.valueOf(Color.parseColor("#FFFF02")), Integer.valueOf(Color.parseColor("#00FF00")), Integer.valueOf(Color.parseColor("#00FFFF")), Integer.valueOf(Color.parseColor("#0000FF")), Integer.valueOf(Color.parseColor("#FF00FF")), Integer.valueOf(Color.parseColor("#FFFFFF"))});
        this.colors = listOf;
        if (listOf == null) {
            Intrinsics.throwUninitializedPropertyAccessException("colors");
            listOf = null;
        }
        this.adapter = new RecyclerAdapter<Integer>(listOf) { // from class: com.wifiled.ipixels.ui.diy.DiyImageActivity$bindData$1
            @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter
            public /* bridge */ /* synthetic */ void convert(RecyclerViewHolder recyclerViewHolder, Integer num) {
                convert(recyclerViewHolder, num.intValue());
            }

            {
                DiyImageActivity diyImageActivity = DiyImageActivity.this;
            }

            public void convert(RecyclerViewHolder holder, int integer) {
                int i;
                Intrinsics.checkNotNullParameter(holder, "holder");
                GradientDrawable gradientDrawable = new GradientDrawable();
                gradientDrawable.setCornerRadius(10.0f);
                gradientDrawable.setColor(integer);
                View view = holder.getView(R.id.rl_paint_outside_frame);
                i = DiyImageActivity.this.selPos;
                if (i == getPosition(holder)) {
                    view.setBackgroundResource(R.drawable.icon_paint_color_sel);
                } else {
                    view.setBackgroundResource(R.color.transparent);
                }
                view.findViewById(R.id.colorView).setBackground(gradientDrawable);
            }
        };
        RecyclerView recyclerView = this.recyclerview_color;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerview_color");
            recyclerView = null;
        }
        DiyImageActivity diyImageActivity = this;
        List<Integer> list = this.colors;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("colors");
            list = null;
        }
        recyclerView.setLayoutManager(new GridLayoutManager(diyImageActivity, list.size()));
        RecyclerView recyclerView2 = this.recyclerview_color;
        if (recyclerView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerview_color");
            recyclerView2 = null;
        }
        RecyclerAdapter<Integer> recyclerAdapter = this.adapter;
        if (recyclerAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            recyclerAdapter = null;
        }
        recyclerView2.setAdapter(recyclerAdapter);
        RecyclerAdapter<Integer> recyclerAdapter2 = this.adapter;
        if (recyclerAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            recyclerAdapter2 = null;
        }
        recyclerAdapter2.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() { // from class: com.wifiled.ipixels.ui.diy.DiyImageActivity$$ExternalSyntheticLambda19
            @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter.OnItemClickListener
            public final void onItemClick(ViewGroup viewGroup, View view, Object obj, int i) {
                DiyImageActivity.bindData$lambda$2(DiyImageActivity.this, viewGroup, view, (Integer) obj, i);
            }
        });
        DiyActivity.INSTANCE.checkPermissions(this);
        ImageView imageView2 = this.btn_undo;
        if (imageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btn_undo");
            imageView2 = null;
        }
        imageView2.setEnabled(false);
        ImageView imageView3 = this.btn_redo;
        if (imageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btn_redo");
        } else {
            imageView = imageView3;
        }
        imageView.setEnabled(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindData$lambda$2(final DiyImageActivity diyImageActivity, ViewGroup viewGroup, View view, final Integer num, int i) {
        LogUtils.file("DiyImageActivity  adapter.setOnItemClickListener");
        LedView ledView = diyImageActivity.ledView;
        RecyclerAdapter<Integer> recyclerAdapter = null;
        if (ledView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ledView");
            ledView = null;
        }
        Intrinsics.checkNotNull(num);
        ledView.setSelectedColor(num.intValue());
        diyImageActivity.runOnUiThread(new Runnable() { // from class: com.wifiled.ipixels.ui.diy.DiyImageActivity$$ExternalSyntheticLambda28
            @Override // java.lang.Runnable
            public final void run() {
                DiyImageActivity.bindData$lambda$2$lambda$1(DiyImageActivity.this, num);
            }
        });
        diyImageActivity.selPos = i;
        RecyclerAdapter<Integer> recyclerAdapter2 = diyImageActivity.adapter;
        if (recyclerAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            recyclerAdapter = recyclerAdapter2;
        }
        recyclerAdapter.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindData$lambda$2$lambda$1(DiyImageActivity diyImageActivity, Integer num) {
        CustomImageView customImageView = diyImageActivity.iv_show_diycolor;
        if (customImageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_show_diycolor");
            customImageView = null;
        }
        Intrinsics.checkNotNull(num);
        customImageView.setImageDrawable(new ColorDrawable(num.intValue()));
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        LedView ledView = this.ledView;
        if (ledView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ledView");
            ledView = null;
        }
        ledView.invalidate();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        AppConfig appConfig = AppConfig.INSTANCE;
        AppConfig.isOnly = true;
        super.onResume();
    }

    @Override // com.wifiled.baselib.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        AppConfig appConfig = AppConfig.INSTANCE;
        AppConfig.isOnly = false;
        EventBus.getDefault().unregister(this);
        Log.d(this.TAG, " #run onDestroy# ");
    }

    private final void initToolBar() {
        TextView textView = this.tv_title;
        CustomImageView customImageView = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_title");
            textView = null;
        }
        textView.setText(getString(R.string.title_diy_image));
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
        customImageView4.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.diy.DiyImageActivity$$ExternalSyntheticLambda26
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DiyImageActivity.initToolBar$lambda$3(DiyImageActivity.this, view);
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
        customImageView6.setBackgroundResource(R.mipmap.icon_setting_ok);
        CustomImageView customImageView7 = this.iv_right;
        if (customImageView7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_right");
            customImageView7 = null;
        }
        customImageView7.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.diy.DiyImageActivity$$ExternalSyntheticLambda27
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DiyImageActivity.initToolBar$lambda$8(DiyImageActivity.this, view);
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
    public static final void initToolBar$lambda$3(DiyImageActivity diyImageActivity, View view) {
        LogUtils.file("DiyImageActivity        iv_back.setOnClickListener");
        SendCore.INSTANCE.setDiyFunMode(DiyImageFun.QUIT_NOSAVE_KEEP_PREV.getMode(), null);
        LedView ledView = diyImageActivity.ledView;
        if (ledView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ledView");
            ledView = null;
        }
        ledView.setRoutDiyImage(false);
        LedView ledView2 = diyImageActivity.ledView;
        if (ledView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ledView");
            ledView2 = null;
        }
        ledView2.clear();
        diyImageActivity.resetUi();
        if (!bRoutFromAnimActivity) {
            String nextActivityName = AppConfig.INSTANCE.getNextActivityName();
            Intrinsics.checkNotNullExpressionValue("DiyAnimActivity", "getSimpleName(...)");
            if (!StringsKt.contains$default((CharSequence) nextActivityName, (CharSequence) "DiyAnimActivity", false, 2, (Object) null)) {
                String prevActivityName = AppConfig.INSTANCE.getPrevActivityName();
                Intrinsics.checkNotNullExpressionValue("GalleryActivity", "getSimpleName(...)");
                if (StringsKt.contains$default((CharSequence) prevActivityName, (CharSequence) "GalleryActivity", false, 2, (Object) null)) {
                    diyImageActivity.toActivity(GalleryActivity.class);
                }
                diyImageActivity.finish();
                return;
            }
        }
        bRoutFromAnimActivity = false;
        diyImageActivity.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initToolBar$lambda$8(final DiyImageActivity diyImageActivity, View view) {
        LogUtils.file("DiyImageActivity        iv_right.setOnClickListener");
        LedView ledView = diyImageActivity.ledView;
        if (ledView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ledView");
            ledView = null;
        }
        if (!ledView.isPainted()) {
            diyImageActivity.toast(diyImageActivity.getString(R.string.diy_image_add_frame));
            return;
        }
        LedView ledView2 = diyImageActivity.ledView;
        if (ledView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ledView");
            ledView2 = null;
        }
        final byte[] rGBData = ledView2.getRGBData();
        SendCore.INSTANCE.setDiyFunMode(DiyImageFun.QUIT_NOSAVE_KEEP_PREV.getMode(), null);
        Intrinsics.checkNotNull(rGBData);
        diyImageActivity.doSaveImage(rGBData, new Function1() { // from class: com.wifiled.ipixels.ui.diy.DiyImageActivity$$ExternalSyntheticLambda25
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit initToolBar$lambda$8$lambda$7;
                initToolBar$lambda$8$lambda$7 = DiyImageActivity.initToolBar$lambda$8$lambda$7(DiyImageActivity.this, rGBData, (CallBack) obj);
                return initToolBar$lambda$8$lambda$7;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initToolBar$lambda$8$lambda$7(final DiyImageActivity diyImageActivity, final byte[] bArr, CallBack doSaveImage) {
        Intrinsics.checkNotNullParameter(doSaveImage, "$this$doSaveImage");
        doSaveImage.onSaveDiyImage(new Function1() { // from class: com.wifiled.ipixels.ui.diy.DiyImageActivity$$ExternalSyntheticLambda24
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit initToolBar$lambda$8$lambda$7$lambda$6;
                initToolBar$lambda$8$lambda$7$lambda$6 = DiyImageActivity.initToolBar$lambda$8$lambda$7$lambda$6(DiyImageActivity.this, bArr, (String) obj);
                return initToolBar$lambda$8$lambda$7$lambda$6;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initToolBar$lambda$8$lambda$7$lambda$6(final DiyImageActivity diyImageActivity, byte[] bArr, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        LedView ledView = null;
        if (diyImageActivity.mIsTemplate == 0) {
            if (!bRoutFromAnimActivity) {
                String prevActivityName = AppConfig.INSTANCE.getPrevActivityName();
                Intrinsics.checkNotNullExpressionValue("DiyAnimActivity", "getSimpleName(...)");
                if (!StringsKt.contains$default((CharSequence) prevActivityName, (CharSequence) "DiyAnimActivity", false, 2, (Object) null)) {
                    diyImageActivity.runOnUiThread(new Runnable() { // from class: com.wifiled.ipixels.ui.diy.DiyImageActivity$$ExternalSyntheticLambda17
                        @Override // java.lang.Runnable
                        public final void run() {
                            DiyImageActivity.initToolBar$lambda$8$lambda$7$lambda$6$lambda$5(DiyImageActivity.this);
                        }
                    });
                }
            }
            bRoutFromAnimActivity = false;
            EventBus eventBus = EventBus.getDefault();
            Intrinsics.checkNotNull(bArr);
            LedView ledView2 = diyImageActivity.ledView;
            if (ledView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ledView");
            } else {
                ledView = ledView2;
            }
            int[] colorData = ledView.getColorData();
            Intrinsics.checkNotNullExpressionValue(colorData, "getColorData(...)");
            eventBus.post(new BRGPage(bArr, colorData));
        } else if (bRoutFromAnimActivity) {
            bRoutFromAnimActivity = false;
            EventBus eventBus2 = EventBus.getDefault();
            Intrinsics.checkNotNull(bArr);
            LedView ledView3 = diyImageActivity.ledView;
            if (ledView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ledView");
            } else {
                ledView = ledView3;
            }
            int[] colorData2 = ledView.getColorData();
            Intrinsics.checkNotNullExpressionValue(colorData2, "getColorData(...)");
            eventBus2.post(new BRGPage(bArr, colorData2));
        } else {
            byte[] readFileBytes = FileUtil.readFileBytes(it);
            Intent intent = new Intent();
            intent.putExtra("sendData", readFileBytes);
            diyImageActivity.setResult(-1, intent);
        }
        diyImageActivity.finish();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initToolBar$lambda$8$lambda$7$lambda$6$lambda$5(DiyImageActivity diyImageActivity) {
        Intent intent = new Intent(diyImageActivity, (Class<?>) GalleryActivity.class);
        Bundle bundle = new Bundle();
        bundle.putBoolean("select_mode", true);
        bundle.putBoolean("exit_image_activty", true);
        intent.putExtras(bundle);
        diyImageActivity.toActivityForResult(intent, new com.wifiled.baselib.callback.ActivityResultCallback() { // from class: com.wifiled.ipixels.ui.diy.DiyImageActivity$$ExternalSyntheticLambda29
            @Override // com.wifiled.baselib.callback.ActivityResultCallback
            public final void onActivityResult(int i, Intent intent2) {
                DiyImageActivity.initToolBar$lambda$8$lambda$7$lambda$6$lambda$5$lambda$4(i, intent2);
            }
        });
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        SendCore.INSTANCE.setDiyFunMode(DiyImageFun.QUIT_NOSAVE_KEEP_PREV.getMode(), null);
        LedView ledView = this.ledView;
        if (ledView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ledView");
            ledView = null;
        }
        ledView.setRoutDiyImage(false);
        LedView ledView2 = this.ledView;
        if (ledView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ledView");
            ledView2 = null;
        }
        ledView2.clear();
        resetUi();
        if (!bRoutFromAnimActivity) {
            String nextActivityName = AppConfig.INSTANCE.getNextActivityName();
            Intrinsics.checkNotNullExpressionValue("DiyAnimActivity", "getSimpleName(...)");
            if (!StringsKt.contains$default((CharSequence) nextActivityName, (CharSequence) "DiyAnimActivity", false, 2, (Object) null)) {
                String prevActivityName = AppConfig.INSTANCE.getPrevActivityName();
                Intrinsics.checkNotNullExpressionValue("GalleryActivity", "getSimpleName(...)");
                if (StringsKt.contains$default((CharSequence) prevActivityName, (CharSequence) "GalleryActivity", false, 2, (Object) null)) {
                    toActivity(GalleryActivity.class);
                    return;
                }
                return;
            }
        }
        bRoutFromAnimActivity = false;
    }

    private final void doSaveImage(byte[] arrByte, Function1<? super CallBack, Unit> callbackBuilder) {
        CallBack callBack = new CallBack();
        callbackBuilder.invoke(callBack);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new DiyImageActivity$doSaveImage$1(this, arrByte, callBack, null), 3, null);
    }

    private final void showLedType() {
        DiyImageActivity diyImageActivity = this;
        UtilsExtensionKt.showLoadingDialog$default((Activity) diyImageActivity, false, (String) null, false, 7, (Object) null);
        List<Integer> ledSize = AppConfig.INSTANCE.getLedSize();
        LedView ledView = this.ledView;
        LedView ledView2 = null;
        if (ledView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ledView");
            ledView = null;
        }
        ViewGroup.LayoutParams layoutParams = ledView.getLayoutParams();
        float screenWidth = ScreenUtil.getScreenWidth(this) * 0.9f;
        float floatValue = screenWidth / ledSize.get(0).floatValue();
        int i = (int) screenWidth;
        layoutParams.width = i;
        layoutParams.height = i;
        LedView ledView3 = this.ledView;
        if (ledView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ledView");
            ledView3 = null;
        }
        ledView3.setLayoutParams(layoutParams);
        LedView ledView4 = this.ledView;
        if (ledView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ledView");
            ledView4 = null;
        }
        ledView4.setPointMargin(1);
        if (this.mIsTemplate == 1) {
            if (AppConfig.INSTANCE.getLedType() == 9) {
                this.mTemplateWidth = ledSize.get(0).intValue() / 2;
                this.mTemplateHeight = ledSize.get(1).intValue();
                LedView ledView5 = this.ledView;
                if (ledView5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("ledView");
                    ledView5 = null;
                }
                ledView5.init(this.mTemplateWidth, this.mTemplateHeight, 0.2f);
            } else {
                this.mTemplateWidth = ledSize.get(1).intValue();
                this.mTemplateHeight = ledSize.get(1).intValue();
                LedView ledView6 = this.ledView;
                if (ledView6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("ledView");
                    ledView6 = null;
                }
                ledView6.init(this.mTemplateWidth, this.mTemplateHeight, 0.2f);
            }
        } else {
            LedView ledView7 = this.ledView;
            if (ledView7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ledView");
                ledView7 = null;
            }
            ledView7.init(ledSize.get(0).intValue(), ledSize.get(1).intValue(), 0.2f);
        }
        if (this.bgrData == null) {
            LedView ledView8 = this.ledView;
            if (ledView8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ledView");
                ledView8 = null;
            }
            ledView8.clear();
        } else {
            LedView ledView9 = this.ledView;
            if (ledView9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ledView");
                ledView9 = null;
            }
            ledView9.setData(this.bgrData);
            SendCore sendCore = SendCore.INSTANCE;
            byte[] bArr = this.bgrData;
            Intrinsics.checkNotNull(bArr);
            sendCore.sendCameraData(bArr);
        }
        String str = this.TAG;
        LedView ledView10 = this.ledView;
        if (ledView10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ledView");
            ledView10 = null;
        }
        float translationX = ledView10.getTranslationX();
        LedView ledView11 = this.ledView;
        if (ledView11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ledView");
        } else {
            ledView2 = ledView11;
        }
        Log.e(str, "init: " + floatValue + "====" + screenWidth + " --x[" + translationX + "] y[" + ledView2.getTranslationY() + "]");
        UtilsExtensionKt.showLoadingDialog$default((Activity) diyImageActivity, false, (String) null, false, 6, (Object) null);
    }

    @Override // com.wifiled.baselib.base.BaseActivity
    protected void bindListener() {
        super.bindListener();
        LedView ledView = this.ledView;
        ImageView imageView = null;
        if (ledView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ledView");
            ledView = null;
        }
        ledView.setLedListener(new LedView.LedListener() { // from class: com.wifiled.ipixels.ui.diy.DiyImageActivity$$ExternalSyntheticLambda30
            @Override // com.wifiled.ipixels.view.LedView.LedListener
            public final void onItemSelect(byte[] bArr) {
                DiyImageActivity.bindListener$lambda$9(bArr);
            }
        });
        LedView ledView2 = this.ledView;
        if (ledView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ledView");
            ledView2 = null;
        }
        ledView2.setLedDiyListener(new LedView.LedDiyListener() { // from class: com.wifiled.ipixels.ui.diy.DiyImageActivity$$ExternalSyntheticLambda1
            @Override // com.wifiled.ipixels.view.LedView.LedDiyListener
            public final void onRunTimeItemSelect(CPaintRunTimeItem cPaintRunTimeItem) {
                DiyImageActivity.bindListener$lambda$12(DiyImageActivity.this, cPaintRunTimeItem);
            }
        });
        ImageView imageView2 = this.btn_import;
        if (imageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btn_import");
            imageView2 = null;
        }
        imageView2.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.diy.DiyImageActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DiyImageActivity.bindListener$lambda$15(DiyImageActivity.this, view);
            }
        });
        CustomImageView.Companion companion = CustomImageView.INSTANCE;
        ImageView imageView3 = this.btn_import;
        if (imageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btn_import");
            imageView3 = null;
        }
        companion.attachViewOnTouchListener(imageView3);
        ColorBarView colorBarView = this.colorBarView;
        if (colorBarView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("colorBarView");
            colorBarView = null;
        }
        colorBarView.setOnColorChangerListener(new ColorBarView.OnColorChangeListener() { // from class: com.wifiled.ipixels.ui.diy.DiyImageActivity$$ExternalSyntheticLambda3
            @Override // com.wifiled.ipixels.view.ColorBarView.OnColorChangeListener
            public final void onColorChange(int i) {
                DiyImageActivity.bindListener$lambda$17(DiyImageActivity.this, i);
            }
        });
        ColorBarView colorBarView2 = this.colorBarView;
        if (colorBarView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("colorBarView");
            colorBarView2 = null;
        }
        colorBarView2.setCurrentColor(-16711936);
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        ImageView imageView4 = this.btn_horizontal_mirror;
        if (imageView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btn_horizontal_mirror");
            imageView4 = null;
        }
        imageView4.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.diy.DiyImageActivity$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DiyImageActivity.bindListener$lambda$18(DiyImageActivity.this, booleanRef, view);
            }
        });
        CustomImageView.Companion companion2 = CustomImageView.INSTANCE;
        ImageView imageView5 = this.btn_horizontal_mirror;
        if (imageView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btn_horizontal_mirror");
            imageView5 = null;
        }
        companion2.attachViewOnTouchListener(imageView5);
        ImageView imageView6 = this.btn_vertical_mirror;
        if (imageView6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btn_vertical_mirror");
            imageView6 = null;
        }
        imageView6.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.diy.DiyImageActivity$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DiyImageActivity.bindListener$lambda$19(DiyImageActivity.this, booleanRef, view);
            }
        });
        CustomImageView.Companion companion3 = CustomImageView.INSTANCE;
        ImageView imageView7 = this.btn_vertical_mirror;
        if (imageView7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btn_vertical_mirror");
            imageView7 = null;
        }
        companion3.attachViewOnTouchListener(imageView7);
        ImageView imageView8 = this.btn_redo;
        if (imageView8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btn_redo");
            imageView8 = null;
        }
        imageView8.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.diy.DiyImageActivity$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DiyImageActivity.bindListener$lambda$20(DiyImageActivity.this, view);
            }
        });
        CustomImageView.Companion companion4 = CustomImageView.INSTANCE;
        ImageView imageView9 = this.btn_redo;
        if (imageView9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btn_redo");
            imageView9 = null;
        }
        companion4.attachViewOnTouchListener(imageView9);
        ImageView imageView10 = this.btn_undo;
        if (imageView10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btn_undo");
            imageView10 = null;
        }
        imageView10.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.diy.DiyImageActivity$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DiyImageActivity.bindListener$lambda$21(DiyImageActivity.this, view);
            }
        });
        CustomImageView.Companion companion5 = CustomImageView.INSTANCE;
        ImageView imageView11 = this.btn_undo;
        if (imageView11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btn_undo");
            imageView11 = null;
        }
        companion5.attachViewOnTouchListener(imageView11);
        ImageView imageView12 = this.iv_diy_anim_clear;
        if (imageView12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_diy_anim_clear");
            imageView12 = null;
        }
        imageView12.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.diy.DiyImageActivity$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DiyImageActivity.bindListener$lambda$22(DiyImageActivity.this, view);
            }
        });
        CustomImageView.Companion companion6 = CustomImageView.INSTANCE;
        ImageView imageView13 = this.iv_diy_anim_clear;
        if (imageView13 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_diy_anim_clear");
            imageView13 = null;
        }
        companion6.attachViewOnTouchListener(imageView13);
        final Ref.BooleanRef booleanRef2 = new Ref.BooleanRef();
        ImageView imageView14 = this.btn_move_outside_bg;
        if (imageView14 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btn_move_outside_bg");
            imageView14 = null;
        }
        imageView14.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.diy.DiyImageActivity$$ExternalSyntheticLambda9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DiyImageActivity.bindListener$lambda$23(DiyImageActivity.this, booleanRef2, view);
            }
        });
        CustomImageView.Companion companion7 = CustomImageView.INSTANCE;
        ImageView imageView15 = this.btn_move_outside_bg;
        if (imageView15 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btn_move_outside_bg");
            imageView15 = null;
        }
        companion7.attachViewOnTouchListener(imageView15);
        ImageView imageView16 = this.btn_move_painted;
        if (imageView16 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btn_move_painted");
            imageView16 = null;
        }
        imageView16.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.diy.DiyImageActivity$$ExternalSyntheticLambda31
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DiyImageActivity.bindListener$lambda$24(DiyImageActivity.this, booleanRef2, view);
            }
        });
        CustomImageView.Companion companion8 = CustomImageView.INSTANCE;
        ImageView imageView17 = this.btn_move_painted;
        if (imageView17 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btn_move_painted");
            imageView17 = null;
        }
        companion8.attachViewOnTouchListener(imageView17);
        ImageView imageView18 = this.btn_eraser;
        if (imageView18 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btn_eraser");
            imageView18 = null;
        }
        imageView18.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.diy.DiyImageActivity$$ExternalSyntheticLambda32
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DiyImageActivity.bindListener$lambda$25(DiyImageActivity.this, view);
            }
        });
        CustomImageView.Companion companion9 = CustomImageView.INSTANCE;
        ImageView imageView19 = this.btn_eraser;
        if (imageView19 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btn_eraser");
            imageView19 = null;
        }
        companion9.attachViewOnTouchListener(imageView19);
        ImageView imageView20 = this.btn_paint;
        if (imageView20 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btn_paint");
            imageView20 = null;
        }
        imageView20.setSelected(true);
        ImageView imageView21 = this.btn_paint;
        if (imageView21 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btn_paint");
            imageView21 = null;
        }
        imageView21.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.diy.DiyImageActivity$$ExternalSyntheticLambda33
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DiyImageActivity.bindListener$lambda$26(DiyImageActivity.this, view);
            }
        });
        CustomImageView.Companion companion10 = CustomImageView.INSTANCE;
        ImageView imageView22 = this.btn_paint;
        if (imageView22 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btn_paint");
            imageView22 = null;
        }
        companion10.attachViewOnTouchListener(imageView22);
        final StringBuilder sb = new StringBuilder();
        final Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = 100;
        TextView textView = this.tv_scale_value;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_scale_value");
            textView = null;
        }
        textView.setText(sb.append(intRef.element).append("%").toString());
        ImageView imageView23 = this.btn_increase;
        if (imageView23 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btn_increase");
            imageView23 = null;
        }
        imageView23.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.diy.DiyImageActivity$$ExternalSyntheticLambda34
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DiyImageActivity.bindListener$lambda$27(Ref.IntRef.this, sb, this, view);
            }
        });
        CustomImageView.Companion companion11 = CustomImageView.INSTANCE;
        ImageView imageView24 = this.btn_increase;
        if (imageView24 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btn_increase");
            imageView24 = null;
        }
        companion11.attachViewOnTouchListener(imageView24);
        ImageView imageView25 = this.btn_decrease;
        if (imageView25 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btn_decrease");
            imageView25 = null;
        }
        imageView25.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.diy.DiyImageActivity$$ExternalSyntheticLambda35
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DiyImageActivity.bindListener$lambda$28(Ref.IntRef.this, this, sb, view);
            }
        });
        CustomImageView.Companion companion12 = CustomImageView.INSTANCE;
        ImageView imageView26 = this.btn_decrease;
        if (imageView26 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btn_decrease");
        } else {
            imageView = imageView26;
        }
        companion12.attachViewOnTouchListener(imageView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$9(byte[] bArr) {
        SendCore sendCore = SendCore.INSTANCE;
        Intrinsics.checkNotNull(bArr);
        SendCore.sendUnRedoImageData$default(sendCore, bArr, null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$12(final DiyImageActivity diyImageActivity, CPaintRunTimeItem cPaintRunTimeItem) {
        SendCore sendCore = SendCore.INSTANCE;
        Intrinsics.checkNotNull(cPaintRunTimeItem);
        LedView ledView = null;
        sendCore.sendDiyImageData(cPaintRunTimeItem, null);
        if (cPaintRunTimeItem.getColumn() != -1 && cPaintRunTimeItem.getRow() != -1) {
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.diy.DiyImageActivity$$ExternalSyntheticLambda15
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit bindListener$lambda$12$lambda$10;
                    bindListener$lambda$12$lambda$10 = DiyImageActivity.bindListener$lambda$12$lambda$10(DiyImageActivity.this);
                    return bindListener$lambda$12$lambda$10;
                }
            });
            return;
        }
        LedView ledView2 = diyImageActivity.ledView;
        if (ledView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ledView");
        } else {
            ledView = ledView2;
        }
        if (ledView.isPainted()) {
            return;
        }
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.diy.DiyImageActivity$$ExternalSyntheticLambda16
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit bindListener$lambda$12$lambda$11;
                bindListener$lambda$12$lambda$11 = DiyImageActivity.bindListener$lambda$12$lambda$11(DiyImageActivity.this);
                return bindListener$lambda$12$lambda$11;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit bindListener$lambda$12$lambda$10(DiyImageActivity diyImageActivity) {
        ImageView imageView = diyImageActivity.btn_undo;
        ImageView imageView2 = null;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btn_undo");
            imageView = null;
        }
        if (!imageView.isEnabled()) {
            ImageView imageView3 = diyImageActivity.btn_undo;
            if (imageView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("btn_undo");
            } else {
                imageView2 = imageView3;
            }
            imageView2.setEnabled(true);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit bindListener$lambda$12$lambda$11(DiyImageActivity diyImageActivity) {
        ImageView imageView = diyImageActivity.btn_undo;
        ImageView imageView2 = null;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btn_undo");
            imageView = null;
        }
        if (imageView.isEnabled()) {
            ImageView imageView3 = diyImageActivity.btn_undo;
            if (imageView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("btn_undo");
            } else {
                imageView2 = imageView3;
            }
            imageView2.setEnabled(false);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$15(final DiyImageActivity diyImageActivity, View view) {
        LogUtils.file("DiyImageActivity        btn_import.setOnClickListener");
        LedView ledView = diyImageActivity.ledView;
        if (ledView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ledView");
            ledView = null;
        }
        if (!ledView.isPainted()) {
            diyImageActivity.chooseImage();
            return;
        }
        String string = diyImageActivity.getString(R.string.diy_anmi_clear_notice);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        UtilsExtensionKt.showDialog$default(diyImageActivity, null, string, new Function0() { // from class: com.wifiled.ipixels.ui.diy.DiyImageActivity$$ExternalSyntheticLambda21
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit bindListener$lambda$15$lambda$13;
                bindListener$lambda$15$lambda$13 = DiyImageActivity.bindListener$lambda$15$lambda$13(DiyImageActivity.this);
                return bindListener$lambda$15$lambda$13;
            }
        }, new Function0() { // from class: com.wifiled.ipixels.ui.diy.DiyImageActivity$$ExternalSyntheticLambda23
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit unit;
                unit = Unit.INSTANCE;
                return unit;
            }
        }, 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit bindListener$lambda$15$lambda$13(DiyImageActivity diyImageActivity) {
        diyImageActivity.chooseImage();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$17(final DiyImageActivity diyImageActivity, final int i) {
        LogUtils.file("DiyImageActivity         colorBarView.setOnColorChangerListener");
        Log.e(diyImageActivity.TAG, "onColorChange: " + i);
        int i2 = diyImageActivity.selPos;
        diyImageActivity.selPos = -1;
        RecyclerAdapter<Integer> recyclerAdapter = diyImageActivity.adapter;
        LedView ledView = null;
        if (recyclerAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            recyclerAdapter = null;
        }
        recyclerAdapter.notifyItemChanged(i2);
        LedView ledView2 = diyImageActivity.ledView;
        if (ledView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ledView");
        } else {
            ledView = ledView2;
        }
        ledView.setSelectedColor(i);
        diyImageActivity.runOnUiThread(new Runnable() { // from class: com.wifiled.ipixels.ui.diy.DiyImageActivity$$ExternalSyntheticLambda18
            @Override // java.lang.Runnable
            public final void run() {
                DiyImageActivity.bindListener$lambda$17$lambda$16(DiyImageActivity.this, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$17$lambda$16(DiyImageActivity diyImageActivity, int i) {
        CustomImageView customImageView = diyImageActivity.iv_show_diycolor;
        if (customImageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_show_diycolor");
            customImageView = null;
        }
        customImageView.setImageDrawable(new ColorDrawable(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$18(DiyImageActivity diyImageActivity, Ref.BooleanRef booleanRef, View view) {
        LogUtils.file("DiyImageActivity         btn_horizontal_mirror.setOnClickListener");
        LedView ledView = diyImageActivity.ledView;
        LedView ledView2 = null;
        if (ledView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ledView");
            ledView = null;
        }
        ledView.setMirrorMode(0);
        ImageView imageView = diyImageActivity.btn_horizontal_mirror;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btn_horizontal_mirror");
            imageView = null;
        }
        booleanRef.element = imageView.isSelected();
        booleanRef.element = !booleanRef.element;
        ImageView imageView2 = diyImageActivity.btn_vertical_mirror;
        if (imageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btn_vertical_mirror");
            imageView2 = null;
        }
        if (imageView2.isSelected()) {
            ImageView imageView3 = diyImageActivity.btn_vertical_mirror;
            if (imageView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("btn_vertical_mirror");
                imageView3 = null;
            }
            imageView3.setSelected(true ^ booleanRef.element);
        }
        ImageView imageView4 = diyImageActivity.btn_horizontal_mirror;
        if (imageView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btn_horizontal_mirror");
            imageView4 = null;
        }
        imageView4.setSelected(booleanRef.element);
        if (booleanRef.element) {
            return;
        }
        LedView ledView3 = diyImageActivity.ledView;
        if (ledView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ledView");
        } else {
            ledView2 = ledView3;
        }
        ledView2.setMirrorMode(-1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$19(DiyImageActivity diyImageActivity, Ref.BooleanRef booleanRef, View view) {
        LogUtils.file("DiyImageActivity         btn_vertical_mirror.setOnClickListener");
        LedView ledView = diyImageActivity.ledView;
        LedView ledView2 = null;
        if (ledView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ledView");
            ledView = null;
        }
        ledView.setMirrorMode(1);
        ImageView imageView = diyImageActivity.btn_vertical_mirror;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btn_vertical_mirror");
            imageView = null;
        }
        booleanRef.element = imageView.isSelected();
        booleanRef.element = !booleanRef.element;
        ImageView imageView2 = diyImageActivity.btn_horizontal_mirror;
        if (imageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btn_horizontal_mirror");
            imageView2 = null;
        }
        if (imageView2.isSelected()) {
            ImageView imageView3 = diyImageActivity.btn_horizontal_mirror;
            if (imageView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("btn_horizontal_mirror");
                imageView3 = null;
            }
            imageView3.setSelected(true ^ booleanRef.element);
        }
        ImageView imageView4 = diyImageActivity.btn_vertical_mirror;
        if (imageView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btn_vertical_mirror");
            imageView4 = null;
        }
        imageView4.setSelected(booleanRef.element);
        if (booleanRef.element) {
            return;
        }
        LedView ledView3 = diyImageActivity.ledView;
        if (ledView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ledView");
        } else {
            ledView2 = ledView3;
        }
        ledView2.setMirrorMode(-1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$20(DiyImageActivity diyImageActivity, View view) {
        LogUtils.file("DiyImageActivity   btn_redo.setOnClickListener");
        if (TimeHelper.INSTANCE.allowSend(500)) {
            LedView ledView = diyImageActivity.ledView;
            LedView ledView2 = null;
            if (ledView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ledView");
                ledView = null;
            }
            ledView.redo();
            LedView ledView3 = diyImageActivity.ledView;
            if (ledView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ledView");
                ledView3 = null;
            }
            if (ledView3.isRoutDiyImage()) {
                LedView ledView4 = diyImageActivity.ledView;
                if (ledView4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("ledView");
                    ledView4 = null;
                }
                if (ledView4.getUndoRedoList().getUndoList().size() <= 2) {
                    SendCore.INSTANCE.setDiyFunMode(DiyImageFun.ENTER_NO_CLEAR_CUR_SHOW.getMode(), null);
                }
            }
            LedView ledView5 = diyImageActivity.ledView;
            if (ledView5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ledView");
                ledView5 = null;
            }
            view.setEnabled(ledView5.getUndoRedoList().getRedoList().size() > 0);
            ImageView imageView = diyImageActivity.btn_undo;
            if (imageView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("btn_undo");
                imageView = null;
            }
            LedView ledView6 = diyImageActivity.ledView;
            if (ledView6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ledView");
            } else {
                ledView2 = ledView6;
            }
            imageView.setEnabled(ledView2.getUndoRedoList().getUndoList().size() > 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$21(DiyImageActivity diyImageActivity, View view) {
        LogUtils.file("DiyImageActivity  btn_undo.setOnClickListener");
        if (TimeHelper.INSTANCE.allowSend(500)) {
            LedView ledView = diyImageActivity.ledView;
            LedView ledView2 = null;
            if (ledView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ledView");
                ledView = null;
            }
            ledView.undo();
            LedView ledView3 = diyImageActivity.ledView;
            if (ledView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ledView");
                ledView3 = null;
            }
            view.setEnabled(ledView3.getUndoRedoList().getUndoList().size() > 1);
            ImageView imageView = diyImageActivity.btn_redo;
            if (imageView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("btn_redo");
                imageView = null;
            }
            LedView ledView4 = diyImageActivity.ledView;
            if (ledView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ledView");
            } else {
                ledView2 = ledView4;
            }
            imageView.setEnabled(ledView2.getUndoRedoList().getRedoList().size() > 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$22(DiyImageActivity diyImageActivity, View view) {
        LogUtils.file("DiyImageActivity  iv_diy_anim_clear.setOnClickListener");
        LedView ledView = null;
        SendCore.INSTANCE.setDiyFunMode(DiyImageFun.ENTER_CLEAR_CUR_SHOW.getMode(), null);
        LedView ledView2 = diyImageActivity.ledView;
        if (ledView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ledView");
            ledView2 = null;
        }
        ledView2.clear();
        LedView ledView3 = diyImageActivity.ledView;
        if (ledView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ledView");
            ledView3 = null;
        }
        ledView3.setRoutDiyImage(false);
        ImageView imageView = diyImageActivity.btn_undo;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btn_undo");
            imageView = null;
        }
        imageView.setEnabled(false);
        ImageView imageView2 = diyImageActivity.btn_redo;
        if (imageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btn_redo");
            imageView2 = null;
        }
        imageView2.setEnabled(false);
        LedView ledView4 = diyImageActivity.ledView;
        if (ledView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ledView");
            ledView4 = null;
        }
        ledView4.getUndoRedoList().clear();
        LedView ledView5 = diyImageActivity.ledView;
        if (ledView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ledView");
        } else {
            ledView = ledView5;
        }
        ledView.initUndoRedoList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$23(DiyImageActivity diyImageActivity, Ref.BooleanRef booleanRef, View view) {
        LogUtils.file("DiyImageActivity   btn_move_outside_bg.setOnClickListener");
        LedView ledView = diyImageActivity.ledView;
        ImageView imageView = null;
        LedView ledView2 = null;
        if (ledView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ledView");
            ledView = null;
        }
        ledView.setDispatchTouch(true);
        LedView ledView3 = diyImageActivity.ledView;
        if (ledView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ledView");
            ledView3 = null;
        }
        ledView3.setMode(3);
        ImageView imageView2 = diyImageActivity.btn_move_outside_bg;
        if (imageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btn_move_outside_bg");
            imageView2 = null;
        }
        booleanRef.element = imageView2.isSelected();
        booleanRef.element = !booleanRef.element;
        ImageView imageView3 = diyImageActivity.btn_move_outside_bg;
        if (imageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btn_move_outside_bg");
            imageView3 = null;
        }
        imageView3.setSelected(booleanRef.element);
        ImageView imageView4 = diyImageActivity.btn_move_painted;
        if (imageView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btn_move_painted");
            imageView4 = null;
        }
        if (imageView4.isSelected()) {
            ImageView imageView5 = diyImageActivity.btn_move_painted;
            if (imageView5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("btn_move_painted");
                imageView5 = null;
            }
            imageView5.setSelected(!booleanRef.element);
        }
        if (!booleanRef.element) {
            ImageView imageView6 = diyImageActivity.btn_paint;
            if (imageView6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("btn_paint");
                imageView6 = null;
            }
            imageView6.setSelected(true);
            LedView ledView4 = diyImageActivity.ledView;
            if (ledView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ledView");
                ledView4 = null;
            }
            ledView4.setMode(1);
            LedView ledView5 = diyImageActivity.ledView;
            if (ledView5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ledView");
            } else {
                ledView2 = ledView5;
            }
            ledView2.setDispatchTouch(true);
            return;
        }
        ImageView imageView7 = diyImageActivity.btn_paint;
        if (imageView7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btn_paint");
            imageView7 = null;
        }
        imageView7.setSelected(false);
        ImageView imageView8 = diyImageActivity.btn_eraser;
        if (imageView8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btn_eraser");
        } else {
            imageView = imageView8;
        }
        imageView.setSelected(!booleanRef.element);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:38:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00a0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void bindListener$lambda$24(com.wifiled.ipixels.ui.diy.DiyImageActivity r6, kotlin.jvm.internal.Ref.BooleanRef r7, android.view.View r8) {
        /*
            java.lang.String r8 = "DiyImageActivity   btn_move_painted.setOnClickListener"
            com.blankj.utilcode.util.LogUtils.file(r8)
            com.wifiled.ipixels.view.LedView r8 = r6.ledView
            java.lang.String r0 = "ledView"
            r1 = 0
            if (r8 != 0) goto L10
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
            r8 = r1
        L10:
            r2 = 0
            r8.setDispatchTouch(r2)
            android.widget.ImageView r8 = r6.btn_move_painted
            java.lang.String r3 = "btn_move_painted"
            if (r8 != 0) goto L1e
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            r8 = r1
        L1e:
            boolean r8 = r8.isSelected()
            r7.element = r8
            boolean r8 = r7.element
            r4 = 1
            r8 = r8 ^ r4
            r7.element = r8
            android.widget.ImageView r8 = r6.btn_move_painted
            if (r8 != 0) goto L32
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            r8 = r1
        L32:
            boolean r3 = r7.element
            r8.setSelected(r3)
            android.widget.ImageView r8 = r6.btn_move_outside_bg
            java.lang.String r3 = "btn_move_outside_bg"
            if (r8 != 0) goto L41
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            r8 = r1
        L41:
            boolean r8 = r8.isSelected()
            if (r8 != r4) goto L55
            android.widget.ImageView r8 = r6.btn_move_outside_bg
            if (r8 != 0) goto L4f
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            r8 = r1
        L4f:
            boolean r5 = r7.element
            r5 = r5 ^ r4
            r8.setSelected(r5)
        L55:
            boolean r7 = r7.element
            java.lang.String r8 = "btn_paint"
            if (r7 != 0) goto L8b
            android.widget.ImageView r7 = r6.btn_move_outside_bg
            if (r7 != 0) goto L63
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            r7 = r1
        L63:
            boolean r7 = r7.isSelected()
            if (r7 != 0) goto L8b
            android.widget.ImageView r7 = r6.btn_paint
            if (r7 != 0) goto L71
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r8)
            r7 = r1
        L71:
            r7.setSelected(r4)
            com.wifiled.ipixels.view.LedView r7 = r6.ledView
            if (r7 != 0) goto L7c
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
            r7 = r1
        L7c:
            r7.setMode(r4)
            com.wifiled.ipixels.view.LedView r7 = r6.ledView
            if (r7 != 0) goto L87
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
            r7 = r1
        L87:
            r7.setDispatchTouch(r4)
            goto L96
        L8b:
            android.widget.ImageView r7 = r6.btn_paint
            if (r7 != 0) goto L93
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r8)
            r7 = r1
        L93:
            r7.setSelected(r2)
        L96:
            android.widget.ImageView r6 = r6.btn_eraser
            if (r6 != 0) goto La0
            java.lang.String r6 = "btn_eraser"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r6)
            goto La1
        La0:
            r1 = r6
        La1:
            r1.setSelected(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wifiled.ipixels.ui.diy.DiyImageActivity.bindListener$lambda$24(com.wifiled.ipixels.ui.diy.DiyImageActivity, kotlin.jvm.internal.Ref$BooleanRef, android.view.View):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$25(DiyImageActivity diyImageActivity, View view) {
        LogUtils.file("DiyImageActivity   btn_eraser.setOnClickListener");
        LedView ledView = diyImageActivity.ledView;
        ImageView imageView = null;
        if (ledView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ledView");
            ledView = null;
        }
        ledView.setDispatchTouch(true);
        LedView ledView2 = diyImageActivity.ledView;
        if (ledView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ledView");
            ledView2 = null;
        }
        ledView2.setMode(2);
        ImageView imageView2 = diyImageActivity.btn_paint;
        if (imageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btn_paint");
            imageView2 = null;
        }
        imageView2.setSelected(false);
        ImageView imageView3 = diyImageActivity.btn_move_painted;
        if (imageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btn_move_painted");
            imageView3 = null;
        }
        imageView3.setSelected(false);
        ImageView imageView4 = diyImageActivity.btn_move_outside_bg;
        if (imageView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btn_move_outside_bg");
            imageView4 = null;
        }
        imageView4.setSelected(false);
        ImageView imageView5 = diyImageActivity.btn_eraser;
        if (imageView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btn_eraser");
        } else {
            imageView = imageView5;
        }
        imageView.setSelected(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$26(DiyImageActivity diyImageActivity, View view) {
        LogUtils.file("DiyImageActivity   btn_paint.setOnClickListener");
        LedView ledView = diyImageActivity.ledView;
        ImageView imageView = null;
        if (ledView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ledView");
            ledView = null;
        }
        ledView.setMode(1);
        ImageView imageView2 = diyImageActivity.btn_paint;
        if (imageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btn_paint");
            imageView2 = null;
        }
        imageView2.setSelected(true);
        LedView ledView2 = diyImageActivity.ledView;
        if (ledView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ledView");
            ledView2 = null;
        }
        ledView2.setDispatchTouch(true);
        ImageView imageView3 = diyImageActivity.btn_move_outside_bg;
        if (imageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btn_move_outside_bg");
            imageView3 = null;
        }
        imageView3.setSelected(false);
        ImageView imageView4 = diyImageActivity.btn_move_painted;
        if (imageView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btn_move_painted");
            imageView4 = null;
        }
        imageView4.setSelected(false);
        ImageView imageView5 = diyImageActivity.btn_eraser;
        if (imageView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btn_eraser");
        } else {
            imageView = imageView5;
        }
        imageView.setSelected(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$27(Ref.IntRef intRef, StringBuilder sb, DiyImageActivity diyImageActivity, View view) {
        LogUtils.file("DiyImageActivity   btn_increase.setOnClickListener");
        if (intRef.element >= 300) {
            return;
        }
        intRef.element += 50;
        StringsKt.clear(sb);
        LedView ledView = diyImageActivity.ledView;
        TextView textView = null;
        if (ledView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ledView");
            ledView = null;
        }
        ledView.setScale(intRef.element / 100.0f);
        TextView textView2 = diyImageActivity.tv_scale_value;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_scale_value");
        } else {
            textView = textView2;
        }
        textView.setText(sb.append(intRef.element).append("%").toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$28(Ref.IntRef intRef, DiyImageActivity diyImageActivity, StringBuilder sb, View view) {
        LogUtils.file("DiyImageActivity   btn_decrease.setOnClickListener");
        TextView textView = null;
        LedView ledView = null;
        if (intRef.element <= 100) {
            LedView ledView2 = diyImageActivity.ledView;
            if (ledView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ledView");
                ledView2 = null;
            }
            ledView2.setTranslationX(0.0f);
            LedView ledView3 = diyImageActivity.ledView;
            if (ledView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ledView");
            } else {
                ledView = ledView3;
            }
            ledView.setTranslationY(0.0f);
            return;
        }
        intRef.element -= 50;
        StringsKt.clear(sb);
        LedView ledView4 = diyImageActivity.ledView;
        if (ledView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ledView");
            ledView4 = null;
        }
        ledView4.setScale(intRef.element / 100.0f);
        TextView textView2 = diyImageActivity.tv_scale_value;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_scale_value");
        } else {
            textView = textView2;
        }
        textView.setText(sb.append(intRef.element).append("%").toString());
    }

    private final void initIosDialogAdapter() {
        this.mAdapter = new IosDialogStyleAdapter<>(this, CollectionsKt.listOf((Object[]) new String[]{getString(R.string.title_diy_app_photos), getString(R.string.title_diy_phone_camera), getString(R.string.title_diy_phone_photos)}));
    }

    private final void chooseImage() {
        final Dialog showBottomDialog = DialogUtils.showBottomDialog(this, R.layout.dialog_select_media);
        this.actionRecyclerView = (RecyclerView) showBottomDialog.findViewById(R.id.rl_actions);
        DiyImageActivity diyImageActivity = this;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(diyImageActivity, 1, false);
        RecyclerView recyclerView = this.actionRecyclerView;
        if (recyclerView != null) {
            recyclerView.setLayoutManager(linearLayoutManager);
        }
        RecyclerView recyclerView2 = this.actionRecyclerView;
        if (recyclerView2 != null) {
            recyclerView2.addItemDecoration(new DividerItemDecoration(diyImageActivity, 1));
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
            recyclerView4.post(new Runnable() { // from class: com.wifiled.ipixels.ui.diy.DiyImageActivity$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    DiyImageActivity.chooseImage$lambda$29(DiyImageActivity.this);
                }
            });
        }
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter3 = this.mAdapter;
        if (iosDialogStyleAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
        } else {
            iosDialogStyleAdapter = iosDialogStyleAdapter3;
        }
        iosDialogStyleAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() { // from class: com.wifiled.ipixels.ui.diy.DiyImageActivity$$ExternalSyntheticLambda11
            @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter.OnItemClickListener
            public final void onItemClick(ViewGroup viewGroup, View view, Object obj, int i) {
                DiyImageActivity.chooseImage$lambda$34(showBottomDialog, this, viewGroup, view, obj, i);
            }
        });
        TextView textView = (TextView) showBottomDialog.findViewById(R.id.tv_cancel);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.diy.DiyImageActivity$$ExternalSyntheticLambda22
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
    public static final void chooseImage$lambda$29(DiyImageActivity diyImageActivity) {
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter = diyImageActivity.mAdapter;
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter2 = null;
        if (iosDialogStyleAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
            iosDialogStyleAdapter = null;
        }
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter3 = diyImageActivity.mAdapter;
        if (iosDialogStyleAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
        } else {
            iosDialogStyleAdapter2 = iosDialogStyleAdapter3;
        }
        int itemCount = iosDialogStyleAdapter2.getItemCount();
        RecyclerView recyclerView = diyImageActivity.actionRecyclerView;
        Intrinsics.checkNotNull(recyclerView);
        iosDialogStyleAdapter.adaptiveRecyclerViewHeight(itemCount, recyclerView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void chooseImage$lambda$34(Dialog dialog, final DiyImageActivity diyImageActivity, ViewGroup viewGroup, View view, Object obj, int i) {
        LogUtils.file("DiyImageActivity mAdapter.setOnItemClickListener");
        dialog.cancel();
        if (i == 0) {
            if (!Intrinsics.areEqual(AppConfig.INSTANCE.getPrevActivityName(), "diy_image") && !Intrinsics.areEqual(AppConfig.INSTANCE.getPrevActivityName(), "enter_diy_image")) {
                AppConfig.INSTANCE.setPrevActivityName(AppConfig.INSTANCE.getTopActivity());
            }
            Intent intent = new Intent(diyImageActivity, (Class<?>) GalleryActivity.class);
            Bundle bundle = new Bundle();
            bundle.putBoolean("select_mode_by_selector", true);
            if (diyImageActivity.mIsTemplate == 1) {
                bundle.putInt("template_size", AppConfig.INSTANCE.getLedType());
            }
            intent.putExtras(bundle);
            diyImageActivity.toActivityForResult(intent, new com.wifiled.baselib.callback.ActivityResultCallback() { // from class: com.wifiled.ipixels.ui.diy.DiyImageActivity$$ExternalSyntheticLambda14
                @Override // com.wifiled.baselib.callback.ActivityResultCallback
                public final void onActivityResult(int i2, Intent intent2) {
                    DiyImageActivity.chooseImage$lambda$34$lambda$33(DiyImageActivity.this, i2, intent2);
                }
            });
        } else if (i == 1) {
            diyImageActivity.requestPermission(new String[]{"android.permission.CAMERA"}, "Need to enable Camera related permissions", new DiyImageActivity$chooseImage$2$2(diyImageActivity));
        } else if (i == 2) {
            diyImageActivity.pickMedia.launch(PickVisualMediaRequestKt.PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE));
        }
        LedView ledView = diyImageActivity.ledView;
        if (ledView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ledView");
            ledView = null;
        }
        ledView.setRoutDiyImage(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void chooseImage$lambda$34$lambda$33(final DiyImageActivity diyImageActivity, int i, Intent intent) {
        if (i == -1) {
            LedView ledView = diyImageActivity.ledView;
            LedView ledView2 = null;
            if (ledView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ledView");
                ledView = null;
            }
            ledView.clear();
            LedView ledView3 = diyImageActivity.ledView;
            if (ledView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ledView");
                ledView3 = null;
            }
            ledView3.getUndoRedoList().clear();
            LedView ledView4 = diyImageActivity.ledView;
            if (ledView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ledView");
                ledView4 = null;
            }
            ledView4.initUndoRedoList();
            byte[] byteArrayExtra = intent.getByteArrayExtra("bgr");
            LedView ledView5 = diyImageActivity.ledView;
            if (ledView5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ledView");
            } else {
                ledView2 = ledView5;
            }
            ledView2.setData(byteArrayExtra, true);
            if (byteArrayExtra != null) {
                SendCore.INSTANCE.sendCameraData(byteArrayExtra);
            }
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.diy.DiyImageActivity$$ExternalSyntheticLambda13
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit chooseImage$lambda$34$lambda$33$lambda$32;
                    chooseImage$lambda$34$lambda$33$lambda$32 = DiyImageActivity.chooseImage$lambda$34$lambda$33$lambda$32(DiyImageActivity.this);
                    return chooseImage$lambda$34$lambda$33$lambda$32;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit chooseImage$lambda$34$lambda$33$lambda$32(DiyImageActivity diyImageActivity) {
        ImageView imageView = diyImageActivity.btn_undo;
        ImageView imageView2 = null;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btn_undo");
            imageView = null;
        }
        if (!imageView.isEnabled()) {
            ImageView imageView3 = diyImageActivity.btn_undo;
            if (imageView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("btn_undo");
            } else {
                imageView2 = imageView3;
            }
            imageView2.setEnabled(true);
        }
        return Unit.INSTANCE;
    }

    public final ActivityResultLauncher<PickVisualMediaRequest> getPickMedia() {
        return this.pickMedia;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void pickMedia$lambda$36(DiyImageActivity diyImageActivity, Uri uri) {
        float f;
        if (uri != null) {
            Uri fromFile = Uri.fromFile(new File(diyImageActivity.getCacheDir(), "cropped_image.jpg"));
            switch (AppConfig.INSTANCE.getLedType()) {
                case 0:
                case 2:
                default:
                    f = 1.0f;
                    break;
                case 1:
                case 15:
                    f = 6.0f;
                    break;
                case 3:
                case 6:
                case 12:
                    f = 4.0f;
                    break;
                case 4:
                case 9:
                case 10:
                    f = 2.0f;
                    break;
                case 5:
                    f = 3.2f;
                    break;
                case 7:
                    f = 9.0f;
                    break;
                case 8:
                    f = 12.0f;
                    break;
                case 11:
                case 13:
                    f = 3.0f;
                    break;
                case 14:
                    f = 5.0f;
                    break;
                case 16:
                    f = 8.0f;
                    break;
            }
            UCrop.Options options = new UCrop.Options();
            options.setHideBottomControls(true);
            options.setAllowedGestures(1, 0, 1);
            UCrop.of(uri, fromFile).withAspectRatio(f, 1.0f).withMaxResultSize(200, 200).withOptions(options).start(diyImageActivity);
            return;
        }
        Log.d("PhotoPicker", "No media selected");
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        float f;
        super.onActivityResult(requestCode, resultCode, data);
        LedView ledView = null;
        if (requestCode == this.REQUEST_TAKE_PHOTO_CODE) {
            if (data == null) {
                return;
            }
            try {
                if (data.getExtras() == null) {
                    return;
                }
                Bundle extras = data.getExtras();
                Intrinsics.checkNotNull(extras);
                Bitmap bitmap = (Bitmap) extras.get("data");
                Uri imageUri = bitmap != null ? BitmapUtils.INSTANCE.getImageUri(this, bitmap) : null;
                Uri fromFile = Uri.fromFile(new File(getCacheDir(), "cropped_image.jpg"));
                switch (AppConfig.INSTANCE.getLedType()) {
                    case 0:
                    case 2:
                    default:
                        f = 1.0f;
                        break;
                    case 1:
                    case 15:
                        f = 6.0f;
                        break;
                    case 3:
                    case 6:
                    case 12:
                        f = 4.0f;
                        break;
                    case 4:
                    case 9:
                    case 10:
                        f = 2.0f;
                        break;
                    case 5:
                        f = 3.2f;
                        break;
                    case 7:
                        f = 9.0f;
                        break;
                    case 8:
                        f = 12.0f;
                        break;
                    case 11:
                    case 13:
                        f = 3.0f;
                        break;
                    case 14:
                        f = 5.0f;
                        break;
                    case 16:
                        f = 8.0f;
                        break;
                }
                UCrop.Options options = new UCrop.Options();
                options.setHideBottomControls(true);
                options.setAllowedGestures(1, 0, 1);
                if (imageUri != null) {
                    UCrop.of(imageUri, fromFile).withAspectRatio(f, 1.0f).withMaxResultSize(200, 200).withOptions(options).start(this);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (resultCode != -1 || requestCode != 69) {
            if (resultCode == 96) {
                LogUtils.vTag("ruis", "cropError-----" + (data != null ? UCrop.getError(data) : null));
                return;
            }
            return;
        }
        Uri output = data != null ? UCrop.getOutput(data) : null;
        LedView ledView2 = this.ledView;
        if (ledView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ledView");
            ledView2 = null;
        }
        ledView2.clear();
        LedView ledView3 = this.ledView;
        if (ledView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ledView");
            ledView3 = null;
        }
        ledView3.getUndoRedoList().clear();
        LedView ledView4 = this.ledView;
        if (ledView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ledView");
            ledView4 = null;
        }
        ledView4.initUndoRedoList();
        byte[] bitmap2RGB = BGRUtils.bitmap2RGB(BitmapUtils.INSTANCE.getRotateBitmap(UriUtils.uri2File(output).getPath(), AppConfig.INSTANCE.getLedSize().get(0).intValue(), AppConfig.INSTANCE.getLedSize().get(1).intValue()));
        LedView ledView5 = this.ledView;
        if (ledView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ledView");
        } else {
            ledView = ledView5;
        }
        ledView.setData(bitmap2RGB, true);
        SendCore sendCore = SendCore.INSTANCE;
        Intrinsics.checkNotNull(bitmap2RGB);
        sendCore.sendCameraData(bitmap2RGB);
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.diy.DiyImageActivity$$ExternalSyntheticLambda20
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit onActivityResult$lambda$40;
                onActivityResult$lambda$40 = DiyImageActivity.onActivityResult$lambda$40(DiyImageActivity.this);
                return onActivityResult$lambda$40;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onActivityResult$lambda$40(DiyImageActivity diyImageActivity) {
        ImageView imageView = diyImageActivity.btn_undo;
        ImageView imageView2 = null;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btn_undo");
            imageView = null;
        }
        if (!imageView.isEnabled()) {
            ImageView imageView3 = diyImageActivity.btn_undo;
            if (imageView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("btn_undo");
            } else {
                imageView2 = imageView3;
            }
            imageView2.setEnabled(true);
        }
        return Unit.INSTANCE;
    }

    private final void resetUi() {
        ImageView imageView = this.btn_paint;
        LedView ledView = null;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btn_paint");
            imageView = null;
        }
        imageView.setSelected(true);
        ImageView imageView2 = this.btn_move_painted;
        if (imageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btn_move_painted");
            imageView2 = null;
        }
        imageView2.setSelected(false);
        ImageView imageView3 = this.btn_move_outside_bg;
        if (imageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btn_move_outside_bg");
            imageView3 = null;
        }
        imageView3.setSelected(false);
        ImageView imageView4 = this.btn_eraser;
        if (imageView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btn_eraser");
            imageView4 = null;
        }
        imageView4.setSelected(false);
        ImageView imageView5 = this.btn_horizontal_mirror;
        if (imageView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btn_horizontal_mirror");
            imageView5 = null;
        }
        imageView5.setSelected(false);
        ImageView imageView6 = this.btn_vertical_mirror;
        if (imageView6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btn_vertical_mirror");
            imageView6 = null;
        }
        imageView6.setSelected(false);
        LedView ledView2 = this.ledView;
        if (ledView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ledView");
            ledView2 = null;
        }
        ledView2.setMode(1);
        LedView ledView3 = this.ledView;
        if (ledView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ledView");
        } else {
            ledView = ledView3;
        }
        ledView.setDispatchTouch(true);
    }

    /* compiled from: DiyImageActivity.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000fR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\u0010"}, d2 = {"Lcom/wifiled/ipixels/ui/diy/DiyImageActivity$Companion;", "", "<init>", "()V", "bRoutFromAnimActivity", "", "getBRoutFromAnimActivity", "()Z", "setBRoutFromAnimActivity", "(Z)V", "launcher", "", "bgrData2", "", "activity", "Landroid/app/Activity;", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean getBRoutFromAnimActivity() {
            return DiyImageActivity.bRoutFromAnimActivity;
        }

        public final void setBRoutFromAnimActivity(boolean z) {
            DiyImageActivity.bRoutFromAnimActivity = z;
        }

        public final void launcher(byte[] bgrData2, Activity activity) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            setBRoutFromAnimActivity(true);
            Intent intent = new Intent(activity, (Class<?>) DiyImageActivity.class);
            intent.putExtra("bgr", bgrData2);
            intent.putExtra("fromDiy", true);
            activity.startActivity(intent);
        }
    }
}
