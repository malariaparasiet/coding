package com.wifiled.ipixels.ui.diy;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.PickVisualMediaRequestKt;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.UriUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.chad.library.adapter.base.listener.OnItemLongClickListener;
import com.chad.library.adapter.base.module.BaseDraggableModule;
import com.squareup.gifencoder.Image;
import com.wifiled.baselib.app.language.LanguageUtil;
import com.wifiled.baselib.base.BaseActivity;
import com.wifiled.baselib.base.recycleview.RecyclerAdapter;
import com.wifiled.baselib.utils.DialogUtils;
import com.wifiled.baselib.utils.ToastUtil;
import com.wifiled.ipixels.App;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.Constants;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.UtilsExtensionKt;
import com.wifiled.ipixels.core.SendCore;
import com.wifiled.ipixels.db.AppDatabase;
import com.wifiled.ipixels.db.GifDao;
import com.wifiled.ipixels.ui.UpDataState;
import com.wifiled.ipixels.ui.adapter.DiyAnimAdapter;
import com.wifiled.ipixels.ui.adapter.IosDialogStyleAdapter;
import com.wifiled.ipixels.ui.gallery.GalleryActivity;
import com.wifiled.ipixels.utils.BGRUtils;
import com.wifiled.ipixels.utils.BitmapUtils;
import com.wifiled.ipixels.view.FrameAnimation;
import com.wifiled.ipixels.view.VerticalSeekBar;
import com.wifiled.ipixels.view.customview.CustomImageView;
import com.wifiled.ipixels.vo.DiyAnimVO;
import com.wifiled.musiclib.player.constant.PlayerFinal;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import pl.droidsonroids.gif.GifIOException;

/* compiled from: DiyAnimActivity.kt */
@Metadata(d1 = {"\u0000Ð\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 o2\u00020\u00012\u00020\u00022\u00020\u0003:\u0001oB\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\b\u00107\u001a\u00020\u001eH\u0014J\u0010\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020;H\u0007J\b\u0010<\u001a\u000209H\u0014J\b\u0010=\u001a\u000209H\u0014J \u0010>\u001a\u0002092\u0016\u0010?\u001a\u0012\u0012\u0004\u0012\u00020@0\u0010j\b\u0012\u0004\u0012\u00020@`\u0012H\u0007J\u0010\u0010>\u001a\u0002092\u0006\u0010A\u001a\u00020BH\u0007J\u0010\u0010C\u001a\u00020\u001e2\u0006\u0010D\u001a\u00020EH\u0002J\b\u0010F\u001a\u000209H\u0003J\b\u0010G\u001a\u000209H\u0014J\u0010\u0010H\u001a\u0002092\u0006\u0010I\u001a\u00020\u001eH\u0002J\b\u0010J\u001a\u000209H\u0002J\b\u0010K\u001a\u000209H\u0003J\b\u0010L\u001a\u000209H\u0002J\b\u0010M\u001a\u000209H\u0002J\b\u0010T\u001a\u000209H\u0002J\u0010\u0010U\u001a\u0002092\u0006\u0010V\u001a\u00020@H\u0002J\u0018\u0010U\u001a\u0002092\u0006\u0010V\u001a\u00020@2\u0006\u0010W\u001a\u00020\u001eH\u0002J\b\u0010X\u001a\u000209H\u0014J,\u0010Y\u001a\u0002092\b\u0010Z\u001a\u0004\u0018\u00010[2\u0006\u0010\\\u001a\u00020\u001e2\b\u0010]\u001a\u0004\u0018\u00010[2\u0006\u0010^\u001a\u00020\u001eH\u0016J\u001a\u0010_\u001a\u0002092\b\u0010`\u001a\u0004\u0018\u00010[2\u0006\u0010a\u001a\u00020\u001eH\u0016J\u001a\u0010b\u001a\u0002092\b\u0010`\u001a\u0004\u0018\u00010[2\u0006\u0010a\u001a\u00020\u001eH\u0016J/\u0010c\u001a\u0002092\f\u0010?\u001a\b\u0012\u0004\u0012\u00020@0d2\u0017\u0010e\u001a\u0013\u0012\u0004\u0012\u00020g\u0012\u0004\u0012\u0002090f¢\u0006\u0002\bhH\u0002J\b\u0010i\u001a\u000209H\u0003J\u001c\u0010j\u001a\u0016\u0012\u0004\u0012\u00020@\u0018\u00010\u0010j\n\u0012\u0004\u0012\u00020@\u0018\u0001`\u0012H\u0002J\b\u0010k\u001a\u000209H\u0016J\b\u0010l\u001a\u000209H\u0016J\b\u0010m\u001a\u000209H\u0016J\b\u0010n\u001a\u000209H\u0016R\u0016\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000R\u001e\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u00110\u0010j\b\u0012\u0004\u0012\u00020\u0011`\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0013\u001a\u0012\u0012\u0004\u0012\u00020\u00140\u0010j\b\u0012\u0004\u0012\u00020\u0014`\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0015\u001a\u00020\u00168BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u0017\u0010\u0018R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020#X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020&X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020)X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020&X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020&X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020.X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020&X\u0082.¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020&X\u0082.¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020)X\u0082.¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u000203X\u0082.¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u000203X\u0082.¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u000203X\u0082.¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u000203X\u0082.¢\u0006\u0002\n\u0000R$\u0010N\u001a\u0010\u0012\f\u0012\n Q*\u0004\u0018\u00010P0P0O8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bR\u0010S¨\u0006p"}, d2 = {"Lcom/wifiled/ipixels/ui/diy/DiyAnimActivity;", "Lcom/wifiled/baselib/base/BaseActivity;", "Lcom/chad/library/adapter/base/listener/OnItemDragListener;", "Lcom/wifiled/ipixels/view/FrameAnimation$AnimationListener;", "<init>", "()V", "mAdapter", "Lcom/wifiled/ipixels/ui/adapter/IosDialogStyleAdapter;", "", "actionRecyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "frameAnimation", "Lcom/wifiled/ipixels/view/FrameAnimation;", "diyAnimAdapter", "Lcom/wifiled/ipixels/ui/adapter/DiyAnimAdapter;", "diyAnims", "Ljava/util/ArrayList;", "Lcom/wifiled/ipixels/vo/DiyAnimVO;", "Lkotlin/collections/ArrayList;", "diyImage", "Lcom/squareup/gifencoder/Image;", "gifDao", "Lcom/wifiled/ipixels/db/GifDao;", "getGifDao", "()Lcom/wifiled/ipixels/db/GifDao;", "gifDao$delegate", "Lkotlin/Lazy;", "editable", "", "srcPosition", "", "targetPosition", "isEditClick", "duration", "sb_anim_alpha", "Landroidx/appcompat/widget/AppCompatSeekBar;", "sb_anim_speed", "iv_right", "Lcom/wifiled/ipixels/view/customview/CustomImageView;", "iv_back", "tv_title", "Landroid/widget/TextView;", "rl_diy_anim_add_item", "iv_diy_anim_copy", "iv_diy_anim_delete", "cl_edit_bg", "Landroidx/constraintlayout/widget/ConstraintLayout;", "iv_diy_anim_edit_ok", "iv_diy_anim_edit", "tv_edit", "btn_select", "Landroid/widget/ImageView;", "iv_diy_anim_play", "iv_diy_anim_clear", "iv_git_preview", "layoutId", "onUpDataState", "", "instance", "Lcom/wifiled/ipixels/ui/UpDataState;", "initView", "bindData", "onEvent", "bitmaps", "Landroid/graphics/Bitmap;", "it", "Lcom/wifiled/ipixels/ui/diy/BRGPage;", "dip2px", "dpValue", "", "initAdapter", "bindListener", "itemClick", PlayerFinal.PLAYER_POSITION, "doPlayAction", "doClearAction", "initIosDialogAdapter", "doSelectAction", "pickMultipleMedia", "Landroidx/activity/result/ActivityResultLauncher;", "Landroidx/activity/result/PickVisualMediaRequest;", "kotlin.jvm.PlatformType", "getPickMultipleMedia", "()Landroidx/activity/result/ActivityResultLauncher;", "doEditAction", "addFrame", "bitmap", "index", "onDestroy", "onItemDragMoving", "source", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", TypedValues.TransitionType.S_FROM, TypedValues.AttributesType.S_TARGET, TypedValues.TransitionType.S_TO, "onItemDragStart", "viewHolder", "pos", "onItemDragEnd", "saveGif", "", "callbackBuilder", "Lkotlin/Function1;", "Lcom/wifiled/ipixels/core/SendCore$CallbackBuilder;", "Lkotlin/ExtensionFunctionType;", "addPaintItem", "checkEdited", "onAnimationStart", "onAnimationEnd", "onAnimationStop", "onAnimationRepeat", "Companion", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DiyAnimActivity extends BaseActivity implements OnItemDragListener, FrameAnimation.AnimationListener {
    public static final int DIY_ANIM_SIZE = 200;
    private RecyclerView actionRecyclerView;
    private ImageView btn_select;
    private ConstraintLayout cl_edit_bg;
    private DiyAnimAdapter diyAnimAdapter;
    private boolean editable;
    private FrameAnimation frameAnimation;
    private boolean isEditClick;
    private CustomImageView iv_back;
    private ImageView iv_diy_anim_clear;
    private CustomImageView iv_diy_anim_copy;
    private CustomImageView iv_diy_anim_delete;
    private CustomImageView iv_diy_anim_edit;
    private CustomImageView iv_diy_anim_edit_ok;
    private ImageView iv_diy_anim_play;
    private ImageView iv_git_preview;
    private CustomImageView iv_right;
    private IosDialogStyleAdapter<Object> mAdapter;
    private final ActivityResultLauncher<PickVisualMediaRequest> pickMultipleMedia;
    private RecyclerView rl_diy_anim_add_item;
    private AppCompatSeekBar sb_anim_alpha;
    private AppCompatSeekBar sb_anim_speed;
    private TextView tv_edit;
    private TextView tv_title;
    private final ArrayList<DiyAnimVO> diyAnims = new ArrayList<>();
    private final ArrayList<Image> diyImage = new ArrayList<>();

    /* renamed from: gifDao$delegate, reason: from kotlin metadata */
    private final Lazy gifDao = LazyKt.lazy(new Function0() { // from class: com.wifiled.ipixels.ui.diy.DiyAnimActivity$$ExternalSyntheticLambda5
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            GifDao gifDao_delegate$lambda$0;
            gifDao_delegate$lambda$0 = DiyAnimActivity.gifDao_delegate$lambda$0();
            return gifDao_delegate$lambda$0;
        }
    });
    private int srcPosition = -1;
    private int targetPosition = -1;
    private int duration = 210;

    @Override // com.wifiled.ipixels.view.FrameAnimation.AnimationListener
    public void onAnimationEnd() {
    }

    @Override // com.wifiled.ipixels.view.FrameAnimation.AnimationListener
    public void onAnimationRepeat() {
    }

    public DiyAnimActivity() {
        ActivityResultLauncher<PickVisualMediaRequest> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.PickMultipleVisualMedia(0, 1, null), new ActivityResultCallback() { // from class: com.wifiled.ipixels.ui.diy.DiyAnimActivity$$ExternalSyntheticLambda6
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                DiyAnimActivity.pickMultipleMedia$lambda$41(DiyAnimActivity.this, (List) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResult(...)");
        this.pickMultipleMedia = registerForActivityResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final GifDao getGifDao() {
        return (GifDao) this.gifDao.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final GifDao gifDao_delegate$lambda$0() {
        return AppDatabase.INSTANCE.getDatabase().gifDao();
    }

    @Override // com.wifiled.baselib.base.BaseActivity
    protected int layoutId() {
        switch (AppConfig.INSTANCE.getLedType()) {
        }
        return R.layout.fragment_diy_anim_1236;
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
        View findViewById = findViewById(R.id.sb_anim_alpha);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.sb_anim_alpha = (AppCompatSeekBar) findViewById;
        View findViewById2 = findViewById(R.id.sb_anim_speed);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.sb_anim_speed = (AppCompatSeekBar) findViewById2;
        View findViewById3 = findViewById(R.id.iv_right);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.iv_right = (CustomImageView) findViewById3;
        View findViewById4 = findViewById(R.id.iv_back);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
        this.iv_back = (CustomImageView) findViewById4;
        View findViewById5 = findViewById(R.id.tv_title);
        Intrinsics.checkNotNullExpressionValue(findViewById5, "findViewById(...)");
        this.tv_title = (TextView) findViewById5;
        View findViewById6 = findViewById(R.id.rl_diy_anim_add_item);
        Intrinsics.checkNotNullExpressionValue(findViewById6, "findViewById(...)");
        this.rl_diy_anim_add_item = (RecyclerView) findViewById6;
        View findViewById7 = findViewById(R.id.iv_diy_anim_copy);
        Intrinsics.checkNotNullExpressionValue(findViewById7, "findViewById(...)");
        this.iv_diy_anim_copy = (CustomImageView) findViewById7;
        View findViewById8 = findViewById(R.id.iv_diy_anim_delete);
        Intrinsics.checkNotNullExpressionValue(findViewById8, "findViewById(...)");
        this.iv_diy_anim_delete = (CustomImageView) findViewById8;
        View findViewById9 = findViewById(R.id.cl_edit_bg);
        Intrinsics.checkNotNullExpressionValue(findViewById9, "findViewById(...)");
        this.cl_edit_bg = (ConstraintLayout) findViewById9;
        View findViewById10 = findViewById(R.id.iv_diy_anim_edit_ok);
        Intrinsics.checkNotNullExpressionValue(findViewById10, "findViewById(...)");
        this.iv_diy_anim_edit_ok = (CustomImageView) findViewById10;
        View findViewById11 = findViewById(R.id.iv_diy_anim_edit);
        Intrinsics.checkNotNullExpressionValue(findViewById11, "findViewById(...)");
        this.iv_diy_anim_edit = (CustomImageView) findViewById11;
        View findViewById12 = findViewById(R.id.tv_edit);
        Intrinsics.checkNotNullExpressionValue(findViewById12, "findViewById(...)");
        this.tv_edit = (TextView) findViewById12;
        View findViewById13 = findViewById(R.id.btn_select);
        Intrinsics.checkNotNullExpressionValue(findViewById13, "findViewById(...)");
        this.btn_select = (ImageView) findViewById13;
        View findViewById14 = findViewById(R.id.iv_diy_anim_play);
        Intrinsics.checkNotNullExpressionValue(findViewById14, "findViewById(...)");
        this.iv_diy_anim_play = (ImageView) findViewById14;
        View findViewById15 = findViewById(R.id.iv_diy_anim_clear);
        Intrinsics.checkNotNullExpressionValue(findViewById15, "findViewById(...)");
        this.iv_diy_anim_clear = (ImageView) findViewById15;
        View findViewById16 = findViewById(R.id.iv_git_preview);
        Intrinsics.checkNotNullExpressionValue(findViewById16, "findViewById(...)");
        this.iv_git_preview = (ImageView) findViewById16;
    }

    @Override // com.wifiled.baselib.base.BaseActivity
    protected void bindData() {
        LogUtils.file("DiyAnimActivity bindData");
        EventBus.getDefault().register(this);
        CustomImageView customImageView = this.iv_right;
        AppCompatSeekBar appCompatSeekBar = null;
        if (customImageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_right");
            customImageView = null;
        }
        customImageView.setBackgroundResource(R.mipmap.icon_setting_ok);
        CustomImageView customImageView2 = this.iv_right;
        if (customImageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_right");
            customImageView2 = null;
        }
        customImageView2.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.diy.DiyAnimActivity$$ExternalSyntheticLambda9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DiyAnimActivity.bindData$lambda$7(DiyAnimActivity.this, view);
            }
        });
        CustomImageView.Companion companion = CustomImageView.INSTANCE;
        CustomImageView customImageView3 = this.iv_right;
        if (customImageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_right");
            customImageView3 = null;
        }
        companion.attachViewOnTouchListener(customImageView3);
        String saveLanguage = LanguageUtil.getSaveLanguage(App.INSTANCE.getContext());
        Intrinsics.checkNotNullExpressionValue(saveLanguage, "getSaveLanguage(...)");
        if (StringsKt.contains$default((CharSequence) saveLanguage, (CharSequence) "ar", false, 2, (Object) null)) {
            CustomImageView customImageView4 = this.iv_back;
            if (customImageView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("iv_back");
                customImageView4 = null;
            }
            customImageView4.setBackgroundResource(R.mipmap.icon_back_ar);
        } else {
            CustomImageView customImageView5 = this.iv_back;
            if (customImageView5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("iv_back");
                customImageView5 = null;
            }
            customImageView5.setBackgroundResource(R.mipmap.icon_back);
        }
        CustomImageView customImageView6 = this.iv_back;
        if (customImageView6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_back");
            customImageView6 = null;
        }
        customImageView6.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.diy.DiyAnimActivity$$ExternalSyntheticLambda10
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DiyAnimActivity.bindData$lambda$8(DiyAnimActivity.this, view);
            }
        });
        CustomImageView.Companion companion2 = CustomImageView.INSTANCE;
        CustomImageView customImageView7 = this.iv_back;
        if (customImageView7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_back");
            customImageView7 = null;
        }
        companion2.attachViewOnTouchListener(customImageView7);
        TextView textView = this.tv_title;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_title");
            textView = null;
        }
        textView.setText(getString(R.string.title_diy_video));
        initAdapter();
        initIosDialogAdapter();
        DiyActivity.INSTANCE.checkPermissions(this);
        AppCompatSeekBar appCompatSeekBar2 = this.sb_anim_alpha;
        if (appCompatSeekBar2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sb_anim_alpha");
            appCompatSeekBar2 = null;
        }
        appCompatSeekBar2.setProgress(Constants.INSTANCE.getIAnimAlha());
        AppCompatSeekBar appCompatSeekBar3 = this.sb_anim_alpha;
        if (appCompatSeekBar3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sb_anim_alpha");
        } else {
            appCompatSeekBar = appCompatSeekBar3;
        }
        appCompatSeekBar.setProgress(Constants.INSTANCE.getIAnimSpeed());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindData$lambda$7(final DiyAnimActivity diyAnimActivity, View view) {
        LogUtils.file("DiyAnimActivity iv_right.setOnClickListener");
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.diy.DiyAnimActivity$$ExternalSyntheticLambda30
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit bindData$lambda$7$lambda$1;
                bindData$lambda$7$lambda$1 = DiyAnimActivity.bindData$lambda$7$lambda$1(DiyAnimActivity.this);
                return bindData$lambda$7$lambda$1;
            }
        });
        ArrayList<Bitmap> checkEdited = diyAnimActivity.checkEdited();
        if (checkEdited != null) {
            diyAnimActivity.saveGif(checkEdited, new Function1() { // from class: com.wifiled.ipixels.ui.diy.DiyAnimActivity$$ExternalSyntheticLambda31
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Unit bindData$lambda$7$lambda$5;
                    bindData$lambda$7$lambda$5 = DiyAnimActivity.bindData$lambda$7$lambda$5(DiyAnimActivity.this, (SendCore.CallbackBuilder) obj);
                    return bindData$lambda$7$lambda$5;
                }
            });
        } else {
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.diy.DiyAnimActivity$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit bindData$lambda$7$lambda$6;
                    bindData$lambda$7$lambda$6 = DiyAnimActivity.bindData$lambda$7$lambda$6(DiyAnimActivity.this);
                    return bindData$lambda$7$lambda$6;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit bindData$lambda$7$lambda$1(DiyAnimActivity diyAnimActivity) {
        String string = diyAnimActivity.getString(R.string.diy_anim_saving);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        UtilsExtensionKt.showLoadingDialog$default((Activity) diyAnimActivity, false, string, false, 5, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit bindData$lambda$7$lambda$5(final DiyAnimActivity diyAnimActivity, SendCore.CallbackBuilder saveGif) {
        Intrinsics.checkNotNullParameter(saveGif, "$this$saveGif");
        saveGif.onStart(new Function0() { // from class: com.wifiled.ipixels.ui.diy.DiyAnimActivity$$ExternalSyntheticLambda25
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit unit;
                unit = Unit.INSTANCE;
                return unit;
            }
        });
        saveGif.onCompleted(new Function0() { // from class: com.wifiled.ipixels.ui.diy.DiyAnimActivity$$ExternalSyntheticLambda26
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit bindData$lambda$7$lambda$5$lambda$4;
                bindData$lambda$7$lambda$5$lambda$4 = DiyAnimActivity.bindData$lambda$7$lambda$5$lambda$4(DiyAnimActivity.this);
                return bindData$lambda$7$lambda$5$lambda$4;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit bindData$lambda$7$lambda$5$lambda$4(final DiyAnimActivity diyAnimActivity) {
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.diy.DiyAnimActivity$$ExternalSyntheticLambda29
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit bindData$lambda$7$lambda$5$lambda$4$lambda$3;
                bindData$lambda$7$lambda$5$lambda$4$lambda$3 = DiyAnimActivity.bindData$lambda$7$lambda$5$lambda$4$lambda$3(DiyAnimActivity.this);
                return bindData$lambda$7$lambda$5$lambda$4$lambda$3;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit bindData$lambda$7$lambda$5$lambda$4$lambda$3(DiyAnimActivity diyAnimActivity) {
        UtilsExtensionKt.showLoadingDialog$default((Activity) diyAnimActivity, false, (String) null, false, 6, (Object) null);
        ToastUtil.show(diyAnimActivity.getString(R.string.diy_anim_saved));
        AppConfig.INSTANCE.setPrevActivityName("diy_anim");
        Intent intent = new Intent(diyAnimActivity, (Class<?>) GalleryActivity.class);
        Bundle bundle = new Bundle();
        bundle.putBoolean("select_mode", true);
        bundle.putBoolean("exit_anim_activty", true);
        intent.putExtras(bundle);
        diyAnimActivity.startActivity(intent);
        diyAnimActivity.finish();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit bindData$lambda$7$lambda$6(DiyAnimActivity diyAnimActivity) {
        UtilsExtensionKt.showLoadingDialog$default((Activity) diyAnimActivity, false, (String) null, false, 6, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindData$lambda$8(DiyAnimActivity diyAnimActivity, View view) {
        LogUtils.file("DiyAnimActivity  iv_back.setOnClickListener");
        Constants constants = Constants.INSTANCE;
        AppCompatSeekBar appCompatSeekBar = diyAnimActivity.sb_anim_alpha;
        if (appCompatSeekBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sb_anim_alpha");
            appCompatSeekBar = null;
        }
        constants.setIAnimAlha(appCompatSeekBar.getProgress());
        Constants constants2 = Constants.INSTANCE;
        AppCompatSeekBar appCompatSeekBar2 = diyAnimActivity.sb_anim_alpha;
        if (appCompatSeekBar2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sb_anim_alpha");
            appCompatSeekBar2 = null;
        }
        constants2.setIAnimSpeed(appCompatSeekBar2.getProgress());
        String prevActivityName = AppConfig.INSTANCE.getPrevActivityName();
        Intrinsics.checkNotNullExpressionValue("GalleryActivity", "getSimpleName(...)");
        if (StringsKt.contains$default((CharSequence) prevActivityName, (CharSequence) "GalleryActivity", false, 2, (Object) null)) {
            diyAnimActivity.toActivity(GalleryActivity.class);
        }
        diyAnimActivity.finish();
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public final void onEvent(ArrayList<Bitmap> bitmaps) {
        Intrinsics.checkNotNullParameter(bitmaps, "bitmaps");
        LogUtils.i(this.TAG + ">>>[totalFrames]: " + bitmaps.size());
        FrameAnimation frameAnimation = this.frameAnimation;
        if (frameAnimation != null) {
            frameAnimation.clear();
        }
        this.diyAnims.clear();
        Iterator<T> it = bitmaps.iterator();
        int i = 0;
        while (it.hasNext()) {
            addFrame((Bitmap) it.next(), i);
            i++;
        }
        EventBus.getDefault().removeStickyEvent(bitmaps);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public final void onEvent(BRGPage it) {
        Intrinsics.checkNotNullParameter(it, "it");
        Bitmap RGB2bitmap = BGRUtils.RGB2bitmap(it.getBytes());
        Intrinsics.checkNotNull(RGB2bitmap);
        addFrame(RGB2bitmap);
        EventBus.getDefault().removeStickyEvent(it);
    }

    private final int dip2px(float dpValue) {
        return (int) ((dpValue * getResources().getDisplayMetrics().density) + 0.5f);
    }

    private final void initAdapter() {
        int i;
        View inflate;
        View inflate2;
        int i2;
        int ledType = AppConfig.INSTANCE.getLedType();
        int i3 = R.drawable.diy_add_frame_3_1;
        int i4 = R.drawable.diy_add_frame_1664;
        switch (ledType) {
            case 1:
            case 15:
                i = R.layout.item_anim_1696;
                inflate = inflate(R.layout.item_anim_1696);
                Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
                i4 = R.drawable.diy_add_frame_1696;
                int i5 = i4;
                inflate2 = inflate;
                i3 = i5;
                break;
            case 2:
            default:
                i = R.layout.item_anim;
                inflate = inflate(R.layout.item_anim);
                Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
                i4 = R.drawable.layer_list_diy_anim_add;
                int i52 = i4;
                inflate2 = inflate;
                i3 = i52;
                break;
            case 3:
            case 12:
                i = R.layout.item_anim_1664;
                inflate = inflate(R.layout.item_anim_1664);
                Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
                int i522 = i4;
                inflate2 = inflate;
                i3 = i522;
                break;
            case 4:
            case 9:
            case 10:
                i = R.layout.item_anim_1236;
                inflate = inflate(R.layout.item_anim_1236);
                Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
                i4 = R.drawable.diy_add_frame_1632;
                int i5222 = i4;
                inflate2 = inflate;
                i3 = i5222;
                break;
            case 5:
                i = R.layout.item_anim_2064;
                inflate = inflate(R.layout.item_anim_2064);
                Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
                i4 = R.drawable.diy_add_frame_2064;
                int i52222 = i4;
                inflate2 = inflate;
                i3 = i52222;
                break;
            case 6:
                i = R.layout.item_anim_32128;
                inflate = inflate(R.layout.item_anim_32128);
                Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
                int i522222 = i4;
                inflate2 = inflate;
                i3 = i522222;
                break;
            case 7:
                i = R.layout.item_anim_16144;
                inflate = inflate(R.layout.item_anim_16144);
                Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
                i4 = R.drawable.diy_add_frame_16144;
                int i5222222 = i4;
                inflate2 = inflate;
                i3 = i5222222;
                break;
            case 8:
                i = R.layout.item_anim_16192;
                inflate = inflate(R.layout.item_anim_16192);
                Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
                i4 = R.drawable.diy_add_frame_16192;
                int i52222222 = i4;
                inflate2 = inflate;
                i3 = i52222222;
                break;
            case 11:
                i = R.layout.item_anim_3296;
                inflate2 = inflate(R.layout.item_anim_3296);
                Intrinsics.checkNotNullExpressionValue(inflate2, "inflate(...)");
                break;
            case 13:
                i = R.layout.item_anim_3296_2;
                inflate2 = inflate(R.layout.item_anim_3296_2);
                Intrinsics.checkNotNullExpressionValue(inflate2, "inflate(...)");
                break;
            case 14:
                i = R.layout.item_anim_32160;
                inflate = inflate(R.layout.item_anim_32160);
                Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
                i4 = R.drawable.diy_add_frame_32160;
                int i522222222 = i4;
                inflate2 = inflate;
                i3 = i522222222;
                break;
            case 16:
                i = R.layout.item_anim_32258;
                inflate = inflate(R.layout.item_anim_32258);
                Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
                i4 = R.drawable.diy_add_frame_32256;
                int i5222222222 = i4;
                inflate2 = inflate;
                i3 = i5222222222;
                break;
        }
        ImageView imageView = (ImageView) inflate2.findViewById(R.id.iv_anim_preview);
        BitmapUtils bitmapUtils = BitmapUtils.INSTANCE;
        Drawable drawable = getDrawable(i3);
        Intrinsics.checkNotNull(drawable);
        imageView.setImageBitmap(bitmapUtils.DrawableToBitmap(drawable));
        ((ImageView) inflate2.findViewById(R.id.iv_anim_preview)).setScaleType(ImageView.ScaleType.CENTER_CROP);
        DiyAnimAdapter diyAnimAdapter = new DiyAnimAdapter(i, this.diyAnims);
        this.diyAnimAdapter = diyAnimAdapter;
        BaseQuickAdapter.addFooterView$default(diyAnimAdapter, inflate2, 0, 0, 6, null);
        RecyclerView recyclerView = this.rl_diy_anim_add_item;
        RecyclerView recyclerView2 = null;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rl_diy_anim_add_item");
            recyclerView = null;
        }
        DiyAnimAdapter diyAnimAdapter2 = this.diyAnimAdapter;
        if (diyAnimAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diyAnimAdapter");
            diyAnimAdapter2 = null;
        }
        recyclerView.setAdapter(diyAnimAdapter2);
        RecyclerView recyclerView3 = this.rl_diy_anim_add_item;
        if (recyclerView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rl_diy_anim_add_item");
        } else {
            recyclerView2 = recyclerView3;
        }
        DiyAnimActivity diyAnimActivity = this;
        switch (AppConfig.INSTANCE.getLedType()) {
            case 1:
            case 7:
            case 8:
            case 15:
            case 16:
                i2 = 1;
                break;
            case 2:
            default:
                i2 = 4;
                break;
            case 3:
            case 4:
            case 5:
            case 6:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
                i2 = 2;
                break;
        }
        recyclerView2.setLayoutManager(new GridLayoutManager(diyAnimActivity, i2));
        inflate2.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.diy.DiyAnimActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DiyAnimActivity.initAdapter$lambda$11(DiyAnimActivity.this, view);
            }
        });
        addPaintItem();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initAdapter$lambda$11(DiyAnimActivity diyAnimActivity, View view) {
        LogUtils.file("DiyAnimActivity   footView.setOnClickListener");
        DiyAnimVO diyAnimVO = new DiyAnimVO(BitmapFactory.decodeResource(diyAnimActivity.getResources(), R.drawable.icon_textinput_bg));
        DiyAnimAdapter diyAnimAdapter = diyAnimActivity.diyAnimAdapter;
        DiyAnimAdapter diyAnimAdapter2 = null;
        if (diyAnimAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diyAnimAdapter");
            diyAnimAdapter = null;
        }
        if (diyAnimAdapter.getItemCount() > 200) {
            diyAnimActivity.toast(diyAnimActivity.getString(R.string.diy_anmi_add_notice));
            return;
        }
        DiyAnimAdapter diyAnimAdapter3 = diyAnimActivity.diyAnimAdapter;
        if (diyAnimAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diyAnimAdapter");
            diyAnimAdapter3 = null;
        }
        diyAnimAdapter3.addData((DiyAnimAdapter) diyAnimVO);
        DiyAnimAdapter diyAnimAdapter4 = diyAnimActivity.diyAnimAdapter;
        if (diyAnimAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diyAnimAdapter");
        } else {
            diyAnimAdapter2 = diyAnimAdapter4;
        }
        diyAnimAdapter2.notifyDataSetChanged();
    }

    @Override // com.wifiled.baselib.base.BaseActivity
    protected void bindListener() {
        super.bindListener();
        DiyAnimAdapter diyAnimAdapter = this.diyAnimAdapter;
        CustomImageView customImageView = null;
        if (diyAnimAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diyAnimAdapter");
            diyAnimAdapter = null;
        }
        diyAnimAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.wifiled.ipixels.ui.diy.DiyAnimActivity$$ExternalSyntheticLambda12
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                DiyAnimActivity.bindListener$lambda$13(DiyAnimActivity.this, baseQuickAdapter, view, i);
            }
        });
        DiyAnimAdapter diyAnimAdapter2 = this.diyAnimAdapter;
        if (diyAnimAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diyAnimAdapter");
            diyAnimAdapter2 = null;
        }
        diyAnimAdapter2.setOnItemLongClickListener(new OnItemLongClickListener() { // from class: com.wifiled.ipixels.ui.diy.DiyAnimActivity$$ExternalSyntheticLambda15
            @Override // com.chad.library.adapter.base.listener.OnItemLongClickListener
            public final boolean onItemLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                boolean bindListener$lambda$14;
                bindListener$lambda$14 = DiyAnimActivity.bindListener$lambda$14(DiyAnimActivity.this, baseQuickAdapter, view, i);
                return bindListener$lambda$14;
            }
        });
        TextView textView = this.tv_edit;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_edit");
            textView = null;
        }
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.diy.DiyAnimActivity$$ExternalSyntheticLambda16
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DiyAnimActivity.bindListener$lambda$15(DiyAnimActivity.this, view);
            }
        });
        ImageView imageView = this.btn_select;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btn_select");
            imageView = null;
        }
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.diy.DiyAnimActivity$$ExternalSyntheticLambda17
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DiyAnimActivity.bindListener$lambda$18(DiyAnimActivity.this, view);
            }
        });
        ImageView imageView2 = this.iv_diy_anim_play;
        if (imageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_diy_anim_play");
            imageView2 = null;
        }
        UtilsExtensionKt.setOnClickFilterListener(imageView2, new Function0() { // from class: com.wifiled.ipixels.ui.diy.DiyAnimActivity$$ExternalSyntheticLambda18
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit bindListener$lambda$19;
                bindListener$lambda$19 = DiyAnimActivity.bindListener$lambda$19(DiyAnimActivity.this);
                return bindListener$lambda$19;
            }
        });
        CustomImageView.Companion companion = CustomImageView.INSTANCE;
        ImageView imageView3 = this.iv_diy_anim_play;
        if (imageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_diy_anim_play");
            imageView3 = null;
        }
        companion.attachViewOnTouchListener(imageView3);
        ImageView imageView4 = this.iv_diy_anim_clear;
        if (imageView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_diy_anim_clear");
            imageView4 = null;
        }
        imageView4.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.diy.DiyAnimActivity$$ExternalSyntheticLambda19
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DiyAnimActivity.bindListener$lambda$20(DiyAnimActivity.this, view);
            }
        });
        CustomImageView.Companion companion2 = CustomImageView.INSTANCE;
        ImageView imageView5 = this.iv_diy_anim_clear;
        if (imageView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_diy_anim_clear");
            imageView5 = null;
        }
        companion2.attachViewOnTouchListener(imageView5);
        switch (AppConfig.INSTANCE.getLedType()) {
            case 1:
            case 3:
            case 5:
            case 6:
            case 7:
            case 8:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
                AppCompatSeekBar appCompatSeekBar = this.sb_anim_alpha;
                if (appCompatSeekBar == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("sb_anim_alpha");
                    appCompatSeekBar = null;
                }
                appCompatSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.wifiled.ipixels.ui.diy.DiyAnimActivity$bindListener$7
                    @Override // android.widget.SeekBar.OnSeekBarChangeListener
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    }

                    @Override // android.widget.SeekBar.OnSeekBarChangeListener
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    /* JADX WARN: Code restructure failed: missing block: B:9:0x004a, code lost:
                    
                        r0 = r4.this$0.frameAnimation;
                     */
                    @Override // android.widget.SeekBar.OnSeekBarChangeListener
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct add '--show-bad-code' argument
                    */
                    public void onStopTrackingTouch(android.widget.SeekBar r5) {
                        /*
                            r4 = this;
                            r0 = 0
                            if (r5 == 0) goto Lc
                            int r1 = r5.getProgress()
                            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
                            goto Ld
                        Lc:
                            r1 = r0
                        Ld:
                            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
                            java.lang.StringBuilder r2 = new java.lang.StringBuilder
                            java.lang.String r3 = "DiyAnimActivity  sb_anim_alpha onStopTrackingTouch seekBar?.progress!!"
                            r2.<init>(r3)
                            java.lang.StringBuilder r1 = r2.append(r1)
                            java.lang.String r1 = r1.toString()
                            com.blankj.utilcode.util.LogUtils.file(r1)
                            com.wifiled.ipixels.ui.diy.DiyAnimActivity r1 = com.wifiled.ipixels.ui.diy.DiyAnimActivity.this
                            android.widget.ImageView r1 = com.wifiled.ipixels.ui.diy.DiyAnimActivity.access$getIv_git_preview$p(r1)
                            if (r1 != 0) goto L30
                            java.lang.String r1 = "iv_git_preview"
                            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
                            goto L31
                        L30:
                            r0 = r1
                        L31:
                            int r5 = r5.getProgress()
                            float r5 = (float) r5
                            r1 = 1120403456(0x42c80000, float:100.0)
                            float r5 = r5 / r1
                            r0.setAlpha(r5)
                            com.wifiled.ipixels.ui.diy.DiyAnimActivity r5 = com.wifiled.ipixels.ui.diy.DiyAnimActivity.this
                            java.util.ArrayList r5 = com.wifiled.ipixels.ui.diy.DiyAnimActivity.access$checkEdited(r5)
                            com.wifiled.ipixels.ui.diy.DiyAnimActivity r0 = com.wifiled.ipixels.ui.diy.DiyAnimActivity.this
                            com.wifiled.ipixels.view.FrameAnimation r0 = com.wifiled.ipixels.ui.diy.DiyAnimActivity.access$getFrameAnimation$p(r0)
                            if (r0 == 0) goto L65
                            com.wifiled.ipixels.ui.diy.DiyAnimActivity r0 = com.wifiled.ipixels.ui.diy.DiyAnimActivity.this
                            com.wifiled.ipixels.view.FrameAnimation r0 = com.wifiled.ipixels.ui.diy.DiyAnimActivity.access$getFrameAnimation$p(r0)
                            if (r0 == 0) goto L59
                            boolean r0 = r0.isPlaying()
                            if (r0 != 0) goto L59
                            goto L65
                        L59:
                            java.util.Collection r5 = (java.util.Collection) r5
                            if (r5 == 0) goto L65
                            boolean r5 = r5.isEmpty()
                            if (r5 == 0) goto L64
                            goto L65
                        L64:
                            return
                        L65:
                            com.wifiled.ipixels.ui.diy.DiyAnimActivity r5 = com.wifiled.ipixels.ui.diy.DiyAnimActivity.this
                            com.wifiled.ipixels.ui.diy.DiyAnimActivity.access$doPlayAction(r5)
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.wifiled.ipixels.ui.diy.DiyAnimActivity$bindListener$7.onStopTrackingTouch(android.widget.SeekBar):void");
                    }
                });
                AppCompatSeekBar appCompatSeekBar2 = this.sb_anim_speed;
                if (appCompatSeekBar2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("sb_anim_speed");
                    appCompatSeekBar2 = null;
                }
                appCompatSeekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.wifiled.ipixels.ui.diy.DiyAnimActivity$bindListener$8
                    @Override // android.widget.SeekBar.OnSeekBarChangeListener
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    }

                    @Override // android.widget.SeekBar.OnSeekBarChangeListener
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    /* JADX WARN: Code restructure failed: missing block: B:9:0x0055, code lost:
                    
                        r1 = r4.this$0.frameAnimation;
                     */
                    @Override // android.widget.SeekBar.OnSeekBarChangeListener
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct add '--show-bad-code' argument
                    */
                    public void onStopTrackingTouch(android.widget.SeekBar r5) {
                        /*
                            r4 = this;
                            r0 = 0
                            if (r5 == 0) goto Lc
                            int r1 = r5.getProgress()
                            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
                            goto Ld
                        Lc:
                            r1 = r0
                        Ld:
                            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
                            java.lang.StringBuilder r2 = new java.lang.StringBuilder
                            java.lang.String r3 = "DiyAnimActivity  sb_anim_speed onStopTrackingTouch seekBar?.progress!!"
                            r2.<init>(r3)
                            java.lang.StringBuilder r1 = r2.append(r1)
                            java.lang.String r1 = r1.toString()
                            com.blankj.utilcode.util.LogUtils.file(r1)
                            if (r5 == 0) goto L2c
                            int r0 = r5.getProgress()
                            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
                        L2c:
                            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
                            java.lang.StringBuilder r1 = new java.lang.StringBuilder
                            java.lang.String r2 = "sb_anim_speed onStopTrackingTouch seekBar?.progress!!"
                            r1.<init>(r2)
                            java.lang.StringBuilder r0 = r1.append(r0)
                            java.lang.String r0 = r0.toString()
                            java.lang.Object[] r0 = new java.lang.Object[]{r0}
                            java.lang.String r1 = "ruis"
                            com.blankj.utilcode.util.LogUtils.vTag(r1, r0)
                            com.wifiled.ipixels.ui.diy.DiyAnimActivity r0 = com.wifiled.ipixels.ui.diy.DiyAnimActivity.this
                            java.util.ArrayList r0 = com.wifiled.ipixels.ui.diy.DiyAnimActivity.access$checkEdited(r0)
                            com.wifiled.ipixels.ui.diy.DiyAnimActivity r1 = com.wifiled.ipixels.ui.diy.DiyAnimActivity.this
                            com.wifiled.ipixels.view.FrameAnimation r1 = com.wifiled.ipixels.ui.diy.DiyAnimActivity.access$getFrameAnimation$p(r1)
                            if (r1 == 0) goto L6e
                            com.wifiled.ipixels.ui.diy.DiyAnimActivity r1 = com.wifiled.ipixels.ui.diy.DiyAnimActivity.this
                            com.wifiled.ipixels.view.FrameAnimation r1 = com.wifiled.ipixels.ui.diy.DiyAnimActivity.access$getFrameAnimation$p(r1)
                            if (r1 == 0) goto L64
                            boolean r1 = r1.isPlaying()
                            if (r1 != 0) goto L64
                            goto L6e
                        L64:
                            java.util.Collection r0 = (java.util.Collection) r0
                            if (r0 == 0) goto L6e
                            boolean r0 = r0.isEmpty()
                            if (r0 == 0) goto L73
                        L6e:
                            com.wifiled.ipixels.ui.diy.DiyAnimActivity r0 = com.wifiled.ipixels.ui.diy.DiyAnimActivity.this
                            com.wifiled.ipixels.ui.diy.DiyAnimActivity.access$doPlayAction(r0)
                        L73:
                            com.wifiled.ipixels.ui.diy.DiyAnimActivity r0 = com.wifiled.ipixels.ui.diy.DiyAnimActivity.this
                            int r5 = r5.getProgress()
                            int r5 = 100 - r5
                            float r5 = (float) r5
                            r1 = 1081291571(0x40733333, float:3.8)
                            float r5 = r5 * r1
                            r1 = 20
                            float r1 = (float) r1
                            float r5 = r5 + r1
                            int r5 = (int) r5
                            com.wifiled.ipixels.ui.diy.DiyAnimActivity.access$setDuration$p(r0, r5)
                            com.wifiled.ipixels.ui.diy.DiyAnimActivity r5 = com.wifiled.ipixels.ui.diy.DiyAnimActivity.this
                            com.wifiled.ipixels.view.FrameAnimation r5 = com.wifiled.ipixels.ui.diy.DiyAnimActivity.access$getFrameAnimation$p(r5)
                            if (r5 == 0) goto L99
                            com.wifiled.ipixels.ui.diy.DiyAnimActivity r0 = com.wifiled.ipixels.ui.diy.DiyAnimActivity.this
                            int r0 = com.wifiled.ipixels.ui.diy.DiyAnimActivity.access$getDuration$p(r0)
                            r5.setDuration(r0)
                        L99:
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.wifiled.ipixels.ui.diy.DiyAnimActivity$bindListener$8.onStopTrackingTouch(android.widget.SeekBar):void");
                    }
                });
                break;
            case 2:
            case 4:
            case 9:
            case 10:
            default:
                AppCompatSeekBar appCompatSeekBar3 = this.sb_anim_alpha;
                if (appCompatSeekBar3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("sb_anim_alpha");
                    appCompatSeekBar3 = null;
                }
                ((VerticalSeekBar) appCompatSeekBar3).setOnSeekBarStopListener(new VerticalSeekBar.OnSeekBarListener() { // from class: com.wifiled.ipixels.ui.diy.DiyAnimActivity$$ExternalSyntheticLambda20
                    @Override // com.wifiled.ipixels.view.VerticalSeekBar.OnSeekBarListener
                    public final void onStopTrackingTouch(SeekBar seekBar) {
                        DiyAnimActivity.bindListener$lambda$21(DiyAnimActivity.this, seekBar);
                    }
                });
                AppCompatSeekBar appCompatSeekBar4 = this.sb_anim_speed;
                if (appCompatSeekBar4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("sb_anim_speed");
                    appCompatSeekBar4 = null;
                }
                ((VerticalSeekBar) appCompatSeekBar4).setOnSeekBarStopListener(new VerticalSeekBar.OnSeekBarListener() { // from class: com.wifiled.ipixels.ui.diy.DiyAnimActivity$$ExternalSyntheticLambda21
                    @Override // com.wifiled.ipixels.view.VerticalSeekBar.OnSeekBarListener
                    public final void onStopTrackingTouch(SeekBar seekBar) {
                        DiyAnimActivity.bindListener$lambda$22(DiyAnimActivity.this, seekBar);
                    }
                });
                break;
        }
        CustomImageView customImageView2 = this.iv_diy_anim_edit;
        if (customImageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_diy_anim_edit");
            customImageView2 = null;
        }
        customImageView2.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.diy.DiyAnimActivity$$ExternalSyntheticLambda23
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DiyAnimActivity.bindListener$lambda$23(DiyAnimActivity.this, view);
            }
        });
        CustomImageView.Companion companion3 = CustomImageView.INSTANCE;
        CustomImageView customImageView3 = this.iv_diy_anim_edit;
        if (customImageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_diy_anim_edit");
            customImageView3 = null;
        }
        companion3.attachViewOnTouchListener(customImageView3);
        CustomImageView customImageView4 = this.iv_diy_anim_edit_ok;
        if (customImageView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_diy_anim_edit_ok");
            customImageView4 = null;
        }
        customImageView4.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.diy.DiyAnimActivity$$ExternalSyntheticLambda24
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DiyAnimActivity.bindListener$lambda$24(DiyAnimActivity.this, view);
            }
        });
        CustomImageView.Companion companion4 = CustomImageView.INSTANCE;
        CustomImageView customImageView5 = this.iv_diy_anim_edit_ok;
        if (customImageView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_diy_anim_edit_ok");
            customImageView5 = null;
        }
        companion4.attachViewOnTouchListener(customImageView5);
        CustomImageView customImageView6 = this.iv_diy_anim_copy;
        if (customImageView6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_diy_anim_copy");
            customImageView6 = null;
        }
        customImageView6.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.diy.DiyAnimActivity$$ExternalSyntheticLambda13
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DiyAnimActivity.bindListener$lambda$26(DiyAnimActivity.this, view);
            }
        });
        CustomImageView.Companion companion5 = CustomImageView.INSTANCE;
        CustomImageView customImageView7 = this.iv_diy_anim_copy;
        if (customImageView7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_diy_anim_copy");
            customImageView7 = null;
        }
        companion5.attachViewOnTouchListener(customImageView7);
        CustomImageView customImageView8 = this.iv_diy_anim_delete;
        if (customImageView8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_diy_anim_delete");
            customImageView8 = null;
        }
        customImageView8.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.diy.DiyAnimActivity$$ExternalSyntheticLambda14
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DiyAnimActivity.bindListener$lambda$29(DiyAnimActivity.this, view);
            }
        });
        CustomImageView.Companion companion6 = CustomImageView.INSTANCE;
        CustomImageView customImageView9 = this.iv_diy_anim_delete;
        if (customImageView9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_diy_anim_delete");
        } else {
            customImageView = customImageView9;
        }
        companion6.attachViewOnTouchListener(customImageView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$13(DiyAnimActivity diyAnimActivity, BaseQuickAdapter adapter, View view, int i) {
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        Intrinsics.checkNotNullParameter(view, "view");
        LogUtils.file("DiyAnimActivity   diyAnimAdapter.setOnItemClickListener");
        DiyAnimAdapter diyAnimAdapter = diyAnimActivity.diyAnimAdapter;
        CustomImageView customImageView = null;
        if (diyAnimAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diyAnimAdapter");
            diyAnimAdapter = null;
        }
        boolean z = false;
        if (!diyAnimAdapter.getMMapMarkItemStatus().containsKey(Integer.valueOf(i))) {
            boolean z2 = diyAnimActivity.editable;
            DiyAnimAdapter diyAnimAdapter2 = diyAnimActivity.diyAnimAdapter;
            if (diyAnimAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("diyAnimAdapter");
                diyAnimAdapter2 = null;
            }
            diyAnimAdapter2.getMMapMarkItemStatus().put(Integer.valueOf(i), Byte.valueOf(z2 ? (byte) 1 : (byte) 0));
        } else {
            DiyAnimAdapter diyAnimAdapter3 = diyAnimActivity.diyAnimAdapter;
            if (diyAnimAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("diyAnimAdapter");
                diyAnimAdapter3 = null;
            }
            Byte b = diyAnimAdapter3.getMMapMarkItemStatus().get(Integer.valueOf(i));
            boolean z3 = !(b != null && ((byte) (b.byteValue() & 1)) == 1);
            if (!diyAnimActivity.editable) {
                z3 = false;
            }
            DiyAnimAdapter diyAnimAdapter4 = diyAnimActivity.diyAnimAdapter;
            if (diyAnimAdapter4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("diyAnimAdapter");
                diyAnimAdapter4 = null;
            }
            Byte b2 = diyAnimAdapter4.getMMapMarkItemStatus().get(Integer.valueOf(i));
            Intrinsics.checkNotNull(b2, "null cannot be cast to non-null type kotlin.Byte");
            byte byteValue = b2.byteValue();
            byte b3 = (byte) (!z3 ? (byte) (byteValue & 2) : ((byte) (byteValue & 2)) + 1);
            DiyAnimAdapter diyAnimAdapter5 = diyAnimActivity.diyAnimAdapter;
            if (diyAnimAdapter5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("diyAnimAdapter");
                diyAnimAdapter5 = null;
            }
            diyAnimAdapter5.getMMapMarkItemStatus().put(Integer.valueOf(i), Byte.valueOf(b3));
        }
        DiyAnimAdapter diyAnimAdapter6 = diyAnimActivity.diyAnimAdapter;
        if (diyAnimAdapter6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diyAnimAdapter");
            diyAnimAdapter6 = null;
        }
        Iterator<Map.Entry<Integer, Byte>> it = diyAnimAdapter6.getMMapMarkItemStatus().entrySet().iterator();
        while (it.hasNext()) {
            if (((byte) (it.next().getValue().byteValue() & 1)) == 1) {
                z = true;
            }
        }
        CustomImageView customImageView2 = diyAnimActivity.iv_diy_anim_copy;
        if (customImageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_diy_anim_copy");
            customImageView2 = null;
        }
        customImageView2.setEnabled(z);
        CustomImageView customImageView3 = diyAnimActivity.iv_diy_anim_delete;
        if (customImageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_diy_anim_delete");
        } else {
            customImageView = customImageView3;
        }
        customImageView.setEnabled(z);
        diyAnimActivity.itemClick(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean bindListener$lambda$14(DiyAnimActivity diyAnimActivity, BaseQuickAdapter adapter, View view, int i) {
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        Intrinsics.checkNotNullParameter(view, "view");
        LogUtils.file("DiyAnimActivity diyAnimAdapter.setOnItemLongClickListener");
        boolean z = diyAnimActivity.isEditClick;
        if (!z) {
            diyAnimActivity.isEditClick = !z;
            ConstraintLayout constraintLayout = diyAnimActivity.cl_edit_bg;
            DiyAnimAdapter diyAnimAdapter = null;
            if (constraintLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("cl_edit_bg");
                constraintLayout = null;
            }
            constraintLayout.setSelected(diyAnimActivity.isEditClick);
            CustomImageView customImageView = diyAnimActivity.iv_diy_anim_edit_ok;
            if (customImageView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("iv_diy_anim_edit_ok");
                customImageView = null;
            }
            UtilsExtensionKt.show(customImageView);
            CustomImageView customImageView2 = diyAnimActivity.iv_diy_anim_edit;
            if (customImageView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("iv_diy_anim_edit");
                customImageView2 = null;
            }
            UtilsExtensionKt.hide(customImageView2);
            CustomImageView customImageView3 = diyAnimActivity.iv_diy_anim_copy;
            if (customImageView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("iv_diy_anim_copy");
                customImageView3 = null;
            }
            UtilsExtensionKt.show(customImageView3);
            CustomImageView customImageView4 = diyAnimActivity.iv_diy_anim_delete;
            if (customImageView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("iv_diy_anim_delete");
                customImageView4 = null;
            }
            UtilsExtensionKt.show(customImageView4);
            diyAnimActivity.doEditAction();
            DiyAnimAdapter diyAnimAdapter2 = diyAnimActivity.diyAnimAdapter;
            if (diyAnimAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("diyAnimAdapter");
            } else {
                diyAnimAdapter = diyAnimAdapter2;
            }
            LinearLayout footerLayout = diyAnimAdapter.getFooterLayout();
            if (footerLayout != null) {
                UtilsExtensionKt.hide(footerLayout);
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$15(DiyAnimActivity diyAnimActivity, View view) {
        LogUtils.file("DiyAnimActivity tv_edit.setOnClickListener");
        diyAnimActivity.doEditAction();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$18(final DiyAnimActivity diyAnimActivity, View view) {
        LogUtils.file("DiyAnimActivity btn_select.setOnClickListener");
        DiyAnimAdapter diyAnimAdapter = diyAnimActivity.diyAnimAdapter;
        if (diyAnimAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diyAnimAdapter");
            diyAnimAdapter = null;
        }
        if (diyAnimAdapter.getData().size() > 2) {
            String string = diyAnimActivity.getString(R.string.diy_anmi_clear_notice);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            UtilsExtensionKt.showDialog$default(diyAnimActivity, null, string, new Function0() { // from class: com.wifiled.ipixels.ui.diy.DiyAnimActivity$$ExternalSyntheticLambda27
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit bindListener$lambda$18$lambda$16;
                    bindListener$lambda$18$lambda$16 = DiyAnimActivity.bindListener$lambda$18$lambda$16(DiyAnimActivity.this);
                    return bindListener$lambda$18$lambda$16;
                }
            }, new Function0() { // from class: com.wifiled.ipixels.ui.diy.DiyAnimActivity$$ExternalSyntheticLambda28
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit unit;
                    unit = Unit.INSTANCE;
                    return unit;
                }
            }, 1, null);
            return;
        }
        diyAnimActivity.doSelectAction();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit bindListener$lambda$18$lambda$16(DiyAnimActivity diyAnimActivity) {
        diyAnimActivity.doSelectAction();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit bindListener$lambda$19(DiyAnimActivity diyAnimActivity) {
        LogUtils.file("DiyAnimActivity  iv_diy_anim_play.setOnClickFilterListener ");
        diyAnimActivity.doPlayAction();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$20(DiyAnimActivity diyAnimActivity, View view) {
        LogUtils.file("DiyAnimActivity  iv_diy_anim_clear.setOnClickListener");
        diyAnimActivity.doClearAction();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$21(DiyAnimActivity diyAnimActivity, SeekBar seekBar) {
        ArrayList<Bitmap> arrayList;
        ImageView imageView = diyAnimActivity.iv_git_preview;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_git_preview");
            imageView = null;
        }
        imageView.setAlpha(seekBar.getProgress() / 100.0f);
        ArrayList<Bitmap> checkEdited = diyAnimActivity.checkEdited();
        FrameAnimation frameAnimation = diyAnimActivity.frameAnimation;
        if (frameAnimation == null || (!(frameAnimation == null || frameAnimation.isPlaying()) || (arrayList = checkEdited) == null || arrayList.isEmpty())) {
            diyAnimActivity.doPlayAction();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$22(DiyAnimActivity diyAnimActivity, SeekBar seekBar) {
        ArrayList<Bitmap> arrayList;
        ArrayList<Bitmap> checkEdited = diyAnimActivity.checkEdited();
        FrameAnimation frameAnimation = diyAnimActivity.frameAnimation;
        if (frameAnimation == null || ((frameAnimation != null && !frameAnimation.isPlaying()) || (arrayList = checkEdited) == null || arrayList.isEmpty())) {
            diyAnimActivity.doPlayAction();
        }
        Intrinsics.checkNotNull(seekBar != null ? Integer.valueOf(seekBar.getProgress()) : null);
        int intValue = (int) (((100 - r3.intValue()) * 3.8f) + 20);
        diyAnimActivity.duration = intValue;
        FrameAnimation frameAnimation2 = diyAnimActivity.frameAnimation;
        if (frameAnimation2 != null) {
            frameAnimation2.setDuration(intValue);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$23(DiyAnimActivity diyAnimActivity, View view) {
        LogUtils.file("DiyAnimActivity  iv_diy_anim_edit.setOnClickListener");
        diyAnimActivity.isEditClick = !diyAnimActivity.isEditClick;
        ConstraintLayout constraintLayout = diyAnimActivity.cl_edit_bg;
        DiyAnimAdapter diyAnimAdapter = null;
        if (constraintLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cl_edit_bg");
            constraintLayout = null;
        }
        constraintLayout.setSelected(diyAnimActivity.isEditClick);
        CustomImageView customImageView = diyAnimActivity.iv_diy_anim_edit_ok;
        if (customImageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_diy_anim_edit_ok");
            customImageView = null;
        }
        UtilsExtensionKt.show(customImageView);
        CustomImageView customImageView2 = diyAnimActivity.iv_diy_anim_edit;
        if (customImageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_diy_anim_edit");
            customImageView2 = null;
        }
        UtilsExtensionKt.hide(customImageView2);
        CustomImageView customImageView3 = diyAnimActivity.iv_diy_anim_copy;
        if (customImageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_diy_anim_copy");
            customImageView3 = null;
        }
        UtilsExtensionKt.show(customImageView3);
        CustomImageView customImageView4 = diyAnimActivity.iv_diy_anim_delete;
        if (customImageView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_diy_anim_delete");
            customImageView4 = null;
        }
        UtilsExtensionKt.show(customImageView4);
        if (diyAnimActivity.isEditClick) {
            diyAnimActivity.doEditAction();
            DiyAnimAdapter diyAnimAdapter2 = diyAnimActivity.diyAnimAdapter;
            if (diyAnimAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("diyAnimAdapter");
            } else {
                diyAnimAdapter = diyAnimAdapter2;
            }
            LinearLayout footerLayout = diyAnimAdapter.getFooterLayout();
            if (footerLayout != null) {
                UtilsExtensionKt.hide(footerLayout);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$24(DiyAnimActivity diyAnimActivity, View view) {
        LogUtils.file("DiyAnimActivity  iv_diy_anim_edit_ok.setOnClickListener");
        diyAnimActivity.isEditClick = !diyAnimActivity.isEditClick;
        ConstraintLayout constraintLayout = diyAnimActivity.cl_edit_bg;
        DiyAnimAdapter diyAnimAdapter = null;
        if (constraintLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cl_edit_bg");
            constraintLayout = null;
        }
        constraintLayout.setSelected(diyAnimActivity.isEditClick);
        CustomImageView customImageView = diyAnimActivity.iv_diy_anim_edit;
        if (customImageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_diy_anim_edit");
            customImageView = null;
        }
        UtilsExtensionKt.show(customImageView);
        CustomImageView customImageView2 = diyAnimActivity.iv_diy_anim_edit_ok;
        if (customImageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_diy_anim_edit_ok");
            customImageView2 = null;
        }
        UtilsExtensionKt.hide(customImageView2);
        CustomImageView customImageView3 = diyAnimActivity.iv_diy_anim_copy;
        if (customImageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_diy_anim_copy");
            customImageView3 = null;
        }
        UtilsExtensionKt.hide(customImageView3);
        CustomImageView customImageView4 = diyAnimActivity.iv_diy_anim_delete;
        if (customImageView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_diy_anim_delete");
            customImageView4 = null;
        }
        UtilsExtensionKt.hide(customImageView4);
        DiyAnimAdapter diyAnimAdapter2 = diyAnimActivity.diyAnimAdapter;
        if (diyAnimAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diyAnimAdapter");
            diyAnimAdapter2 = null;
        }
        LinearLayout footerLayout = diyAnimAdapter2.getFooterLayout();
        if (footerLayout != null) {
            UtilsExtensionKt.show(footerLayout);
        }
        diyAnimActivity.doEditAction();
        CustomImageView customImageView5 = diyAnimActivity.iv_diy_anim_copy;
        if (customImageView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_diy_anim_copy");
            customImageView5 = null;
        }
        customImageView5.setEnabled(false);
        CustomImageView customImageView6 = diyAnimActivity.iv_diy_anim_delete;
        if (customImageView6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_diy_anim_delete");
            customImageView6 = null;
        }
        customImageView6.setEnabled(false);
        DiyAnimAdapter diyAnimAdapter3 = diyAnimActivity.diyAnimAdapter;
        if (diyAnimAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diyAnimAdapter");
            diyAnimAdapter3 = null;
        }
        diyAnimAdapter3.setM_iSelect(-1);
        DiyAnimAdapter diyAnimAdapter4 = diyAnimActivity.diyAnimAdapter;
        if (diyAnimAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diyAnimAdapter");
            diyAnimAdapter4 = null;
        }
        for (Map.Entry<Integer, Byte> entry : diyAnimAdapter4.getMMapMarkItemStatus().entrySet()) {
            Intrinsics.checkNotNullExpressionValue(entry, "next(...)");
            Map.Entry<Integer, Byte> entry2 = entry;
            Byte value = entry2.getValue();
            Intrinsics.checkNotNullExpressionValue(value, "<get-value>(...)");
            byte byteValue = value.byteValue();
            if (((byte) (byteValue & 1)) == 1) {
                entry2.setValue(Byte.valueOf((byte) (((byte) (byteValue & 2)) + ((byte) 0))));
            }
        }
        DiyAnimAdapter diyAnimAdapter5 = diyAnimActivity.diyAnimAdapter;
        if (diyAnimAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diyAnimAdapter");
        } else {
            diyAnimAdapter = diyAnimAdapter5;
        }
        diyAnimAdapter.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void bindListener$lambda$26(DiyAnimActivity diyAnimActivity, View view) {
        int i;
        boolean z;
        LogUtils.file("DiyAnimActivity  iv_diy_anim_copy.setOnClickListener");
        DiyAnimAdapter diyAnimAdapter = diyAnimActivity.diyAnimAdapter;
        DiyAnimAdapter diyAnimAdapter2 = null;
        if (diyAnimAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diyAnimAdapter");
            diyAnimAdapter = null;
        }
        Iterator<Map.Entry<Integer, Byte>> it = diyAnimAdapter.getMMapMarkItemStatus().entrySet().iterator();
        int i2 = 0;
        while (it.hasNext()) {
            if (((byte) (it.next().getValue().byteValue() & 1)) == 1) {
                i2++;
            }
        }
        DiyAnimAdapter diyAnimAdapter3 = diyAnimActivity.diyAnimAdapter;
        if (diyAnimAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diyAnimAdapter");
            diyAnimAdapter3 = null;
        }
        if ((i2 + diyAnimAdapter3.getItemCount()) - 1 > 200) {
            diyAnimActivity.toast(diyAnimActivity.getString(R.string.diy_anmi_add_notice));
            return;
        }
        DiyAnimAdapter diyAnimAdapter4 = diyAnimActivity.diyAnimAdapter;
        if (diyAnimAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diyAnimAdapter");
            diyAnimAdapter4 = null;
        }
        WeakHashMap weakHashMap = new WeakHashMap();
        int i3 = 0;
        for (Map.Entry<Integer, Byte> entry : diyAnimAdapter4.getMMapMarkItemStatus().entrySet()) {
            Intrinsics.checkNotNullExpressionValue(entry, "next(...)");
            Map.Entry<Integer, Byte> entry2 = entry;
            Byte value = entry2.getValue();
            Intrinsics.checkNotNullExpressionValue(value, "<get-value>(...)");
            byte byteValue = value.byteValue();
            Resources resources = diyAnimActivity.getResources();
            switch (AppConfig.INSTANCE.getLedType()) {
                case 1:
                case 3:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                case 16:
                    i = R.mipmap.diy_edit_frame_1664;
                    break;
                case 2:
                default:
                    i = R.mipmap.icon_textinput_bg;
                    break;
                case 4:
                    i = R.mipmap.icon_textinput_bg_1632;
                    break;
            }
            DiyAnimVO diyAnimVO = new DiyAnimVO(BitmapFactory.decodeResource(resources, i));
            if (((byte) (byteValue & 1)) == 1) {
                DiyAnimAdapter diyAnimAdapter5 = diyAnimActivity.diyAnimAdapter;
                if (diyAnimAdapter5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("diyAnimAdapter");
                    diyAnimAdapter5 = null;
                }
                Integer key = entry2.getKey();
                Intrinsics.checkNotNullExpressionValue(key, "<get-key>(...)");
                SoftReference softReference = new SoftReference(diyAnimAdapter5.getItem(key.intValue()).getBitmap());
                if (((byte) (byteValue & 2)) == 2) {
                    Bitmap bitmap = (Bitmap) softReference.get();
                    diyAnimVO.setBitmap(bitmap != null ? bitmap.copy(Bitmap.Config.ARGB_8888, true) : null);
                    z = true;
                } else {
                    z = false;
                }
                DiyAnimAdapter diyAnimAdapter6 = diyAnimActivity.diyAnimAdapter;
                if (diyAnimAdapter6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("diyAnimAdapter");
                    diyAnimAdapter6 = null;
                }
                diyAnimAdapter6.addData((DiyAnimAdapter) diyAnimVO);
                WeakHashMap weakHashMap2 = weakHashMap;
                DiyAnimAdapter diyAnimAdapter7 = diyAnimActivity.diyAnimAdapter;
                if (diyAnimAdapter7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("diyAnimAdapter");
                    diyAnimAdapter7 = null;
                }
                weakHashMap2.put(Integer.valueOf((diyAnimAdapter7.getItemCount() - 2) + i3), Byte.valueOf(z ? (byte) 2 : (byte) 0));
                i3++;
            }
        }
        for (Object obj : weakHashMap.entrySet()) {
            Intrinsics.checkNotNullExpressionValue(obj, "next(...)");
            Map.Entry entry3 = (Map.Entry) obj;
            DiyAnimAdapter diyAnimAdapter8 = diyAnimActivity.diyAnimAdapter;
            if (diyAnimAdapter8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("diyAnimAdapter");
                diyAnimAdapter8 = null;
            }
            diyAnimAdapter8.getMMapMarkItemStatus().put(entry3.getKey(), entry3.getValue());
        }
        DiyAnimAdapter diyAnimAdapter9 = diyAnimActivity.diyAnimAdapter;
        if (diyAnimAdapter9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diyAnimAdapter");
            diyAnimAdapter9 = null;
        }
        for (Map.Entry<Integer, Byte> entry4 : diyAnimAdapter9.getMMapMarkItemStatus().entrySet()) {
            Intrinsics.checkNotNullExpressionValue(entry4, "next(...)");
            Map.Entry<Integer, Byte> entry5 = entry4;
            Byte value2 = entry5.getValue();
            Intrinsics.checkNotNullExpressionValue(value2, "<get-value>(...)");
            byte byteValue2 = value2.byteValue();
            if (((byte) (byteValue2 & 1)) == 1) {
                entry5.setValue(Byte.valueOf((byte) (((byte) (byteValue2 & 2)) + ((byte) 0))));
            }
        }
        CustomImageView customImageView = diyAnimActivity.iv_diy_anim_copy;
        if (customImageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_diy_anim_copy");
            customImageView = null;
        }
        customImageView.setEnabled(false);
        CustomImageView customImageView2 = diyAnimActivity.iv_diy_anim_delete;
        if (customImageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_diy_anim_delete");
            customImageView2 = null;
        }
        customImageView2.setEnabled(false);
        DiyAnimAdapter diyAnimAdapter10 = diyAnimActivity.diyAnimAdapter;
        if (diyAnimAdapter10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diyAnimAdapter");
            diyAnimAdapter10 = null;
        }
        diyAnimAdapter10.setM_iSelect(-1);
        DiyAnimAdapter diyAnimAdapter11 = diyAnimActivity.diyAnimAdapter;
        if (diyAnimAdapter11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diyAnimAdapter");
        } else {
            diyAnimAdapter2 = diyAnimAdapter11;
        }
        diyAnimAdapter2.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$29(DiyAnimActivity diyAnimActivity, View view) {
        LogUtils.file("DiyAnimActivity  iv_diy_anim_delete.setOnClickListener");
        FrameAnimation frameAnimation = diyAnimActivity.frameAnimation;
        if (frameAnimation != null) {
            frameAnimation.stop();
        }
        FrameAnimation frameAnimation2 = diyAnimActivity.frameAnimation;
        if (frameAnimation2 != null) {
            frameAnimation2.clear();
        }
        HashMap hashMap = new HashMap();
        WeakReference weakReference = new WeakReference(hashMap);
        DiyAnimAdapter diyAnimAdapter = diyAnimActivity.diyAnimAdapter;
        DiyAnimAdapter diyAnimAdapter2 = null;
        if (diyAnimAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diyAnimAdapter");
            diyAnimAdapter = null;
        }
        Iterator<Map.Entry<Integer, Byte>> it = diyAnimAdapter.getMMapMarkItemStatus().entrySet().iterator();
        boolean z = false;
        int i = 0;
        while (it.hasNext()) {
            Map.Entry<Integer, Byte> next = it.next();
            Intrinsics.checkNotNullExpressionValue(next, "next(...)");
            Map.Entry<Integer, Byte> entry = next;
            Byte value = entry.getValue();
            Intrinsics.checkNotNullExpressionValue(value, "<get-value>(...)");
            byte byteValue = value.byteValue();
            if (((byte) (byteValue & 1)) == 1) {
                int intValue = entry.getKey().intValue() - i;
                DiyAnimAdapter diyAnimAdapter3 = diyAnimActivity.diyAnimAdapter;
                if (diyAnimAdapter3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("diyAnimAdapter");
                    diyAnimAdapter3 = null;
                }
                DiyAnimAdapter diyAnimAdapter4 = diyAnimActivity.diyAnimAdapter;
                if (diyAnimAdapter4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("diyAnimAdapter");
                    diyAnimAdapter4 = null;
                }
                diyAnimAdapter3.remove((DiyAnimAdapter) diyAnimAdapter4.getItem(intValue));
                it.remove();
                i++;
            } else {
                hashMap.put(Integer.valueOf(entry.getKey().intValue() - i), Byte.valueOf(byteValue));
            }
        }
        HashMap hashMap2 = (HashMap) weakReference.get();
        Object clone = hashMap2 != null ? hashMap2.clone() : null;
        Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.HashMap<kotlin.Int, kotlin.Byte>");
        HashMap<Integer, Byte> hashMap3 = (HashMap) clone;
        DiyAnimAdapter diyAnimAdapter5 = diyAnimActivity.diyAnimAdapter;
        if (diyAnimAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diyAnimAdapter");
            diyAnimAdapter5 = null;
        }
        diyAnimAdapter5.setMMapMarkItemStatus(hashMap3);
        DiyAnimAdapter diyAnimAdapter6 = diyAnimActivity.diyAnimAdapter;
        if (diyAnimAdapter6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diyAnimAdapter");
            diyAnimAdapter6 = null;
        }
        Iterator<Map.Entry<Integer, Byte>> it2 = diyAnimAdapter6.getMMapMarkItemStatus().entrySet().iterator();
        while (it2.hasNext()) {
            if (((byte) (it2.next().getValue().byteValue() & 1)) == 1) {
                z = true;
            }
        }
        CustomImageView customImageView = diyAnimActivity.iv_diy_anim_copy;
        if (customImageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_diy_anim_copy");
            customImageView = null;
        }
        customImageView.setEnabled(z);
        CustomImageView customImageView2 = diyAnimActivity.iv_diy_anim_delete;
        if (customImageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_diy_anim_delete");
            customImageView2 = null;
        }
        customImageView2.setEnabled(z);
        DiyAnimAdapter diyAnimAdapter7 = diyAnimActivity.diyAnimAdapter;
        if (diyAnimAdapter7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diyAnimAdapter");
        } else {
            diyAnimAdapter2 = diyAnimAdapter7;
        }
        if (diyAnimAdapter2.getItemCount() == 1) {
            diyAnimActivity.addPaintItem();
        }
    }

    private final void itemClick(int position) {
        LogUtils.file("DiyAnimActivity  itemClick position=" + position);
        DiyAnimAdapter diyAnimAdapter = this.diyAnimAdapter;
        DiyAnimAdapter diyAnimAdapter2 = null;
        if (diyAnimAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diyAnimAdapter");
            diyAnimAdapter = null;
        }
        diyAnimAdapter.setM_iSelect(position);
        DiyAnimVO diyAnimVO = this.diyAnims.get(position);
        Intrinsics.checkNotNullExpressionValue(diyAnimVO, "get(...)");
        DiyAnimVO diyAnimVO2 = diyAnimVO;
        if (this.editable) {
            DiyAnimAdapter diyAnimAdapter3 = this.diyAnimAdapter;
            if (diyAnimAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("diyAnimAdapter");
            } else {
                diyAnimAdapter2 = diyAnimAdapter3;
            }
            diyAnimAdapter2.notifyItemChanged(position);
            return;
        }
        DiyAnimAdapter diyAnimAdapter4 = this.diyAnimAdapter;
        if (diyAnimAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diyAnimAdapter");
            diyAnimAdapter4 = null;
        }
        Byte b = diyAnimAdapter4.getMMapMarkItemStatus().get(Integer.valueOf(position));
        Intrinsics.checkNotNull(b, "null cannot be cast to non-null type kotlin.Byte");
        byte[] bitmap2RGB = ((byte) (b.byteValue() & 2)) == 2 ? BGRUtils.bitmap2RGB(diyAnimVO2.getBitmap()) : null;
        LogUtils.vTag("ruis", "DiyAnimActivity  itemClick position=" + position);
        if (AppConfig.INSTANCE.getLedType() == 16) {
            DiyAnimAdapter diyAnimAdapter5 = this.diyAnimAdapter;
            if (diyAnimAdapter5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("diyAnimAdapter");
            } else {
                diyAnimAdapter2 = diyAnimAdapter5;
            }
            if (diyAnimAdapter2.getData().size() > 2) {
                String string = getString(R.string.diy_anmi_clear_notice);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                UtilsExtensionKt.showDialog$default(this, null, string, new Function0() { // from class: com.wifiled.ipixels.ui.diy.DiyAnimActivity$$ExternalSyntheticLambda7
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Unit itemClick$lambda$30;
                        itemClick$lambda$30 = DiyAnimActivity.itemClick$lambda$30(DiyAnimActivity.this);
                        return itemClick$lambda$30;
                    }
                }, new Function0() { // from class: com.wifiled.ipixels.ui.diy.DiyAnimActivity$$ExternalSyntheticLambda8
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Unit unit;
                        unit = Unit.INSTANCE;
                        return unit;
                    }
                }, 1, null);
                return;
            }
            doSelectAction();
            return;
        }
        DiyImageActivity.INSTANCE.launcher(bitmap2RGB, this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit itemClick$lambda$30(DiyAnimActivity diyAnimActivity) {
        diyAnimActivity.doSelectAction();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void doPlayAction() {
        LogUtils.file("DiyAnimActivity doPlayAction");
        ArrayList<Bitmap> checkEdited = checkEdited();
        ArrayList<Bitmap> arrayList = checkEdited;
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        if (this.frameAnimation == null) {
            FrameAnimation frameAnimation = new FrameAnimation(checkEdited, 100, true, new Handler());
            this.frameAnimation = frameAnimation;
            Intrinsics.checkNotNull(frameAnimation);
            frameAnimation.setAnimationListener(this);
        }
        FrameAnimation frameAnimation2 = this.frameAnimation;
        if (frameAnimation2 != null) {
            frameAnimation2.updata(checkEdited);
        }
        FrameAnimation frameAnimation3 = this.frameAnimation;
        if (frameAnimation3 != null) {
            if (frameAnimation3.isPlaying()) {
                FrameAnimation frameAnimation4 = this.frameAnimation;
                if (frameAnimation4 != null) {
                    frameAnimation4.stop();
                    return;
                }
                return;
            }
            FrameAnimation frameAnimation5 = this.frameAnimation;
            if (frameAnimation5 != null) {
                ImageView imageView = this.iv_git_preview;
                if (imageView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("iv_git_preview");
                    imageView = null;
                }
                frameAnimation5.start(imageView);
            }
        }
    }

    private final void doClearAction() {
        LogUtils.file("DiyAnimActivity doClearAction");
        String string = getString(R.string.diy_anim_delete_notice);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        UtilsExtensionKt.showDialog$default(this, null, string, new Function0() { // from class: com.wifiled.ipixels.ui.diy.DiyAnimActivity$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit doClearAction$lambda$34;
                doClearAction$lambda$34 = DiyAnimActivity.doClearAction$lambda$34(DiyAnimActivity.this);
                return doClearAction$lambda$34;
            }
        }, new Function0() { // from class: com.wifiled.ipixels.ui.diy.DiyAnimActivity$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit unit;
                unit = Unit.INSTANCE;
                return unit;
            }
        }, 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit doClearAction$lambda$34(DiyAnimActivity diyAnimActivity) {
        int i;
        FrameAnimation frameAnimation = diyAnimActivity.frameAnimation;
        if (frameAnimation != null) {
            frameAnimation.stop();
        }
        FrameAnimation frameAnimation2 = diyAnimActivity.frameAnimation;
        if (frameAnimation2 != null) {
            frameAnimation2.clear();
        }
        ImageView imageView = diyAnimActivity.iv_git_preview;
        DiyAnimAdapter diyAnimAdapter = null;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_git_preview");
            imageView = null;
        }
        imageView.setImageResource(0);
        DiyAnimAdapter diyAnimAdapter2 = diyAnimActivity.diyAnimAdapter;
        if (diyAnimAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diyAnimAdapter");
            diyAnimAdapter2 = null;
        }
        diyAnimAdapter2.setList(null);
        BitmapUtils bitmapUtils = BitmapUtils.INSTANCE;
        switch (AppConfig.INSTANCE.getLedType()) {
            case 1:
            case 15:
                i = R.drawable.diy_edit_frame_1696;
                break;
            case 2:
            default:
                i = R.drawable.layer_list_diy_anim_paint;
                break;
            case 3:
            case 12:
                i = R.drawable.diy_edit_frame_1664;
                break;
            case 4:
            case 9:
            case 10:
                i = R.drawable.diy_edit_frame_1632;
                break;
            case 5:
                i = R.drawable.diy_edit_frame_2064;
                break;
            case 6:
                i = R.drawable.diy_edit_frame_32128;
                break;
            case 7:
                i = R.drawable.diy_edit_frame_16144;
                break;
            case 8:
                i = R.drawable.diy_edit_frame_16192;
                break;
            case 11:
                i = R.drawable.diy_edit_frame_3_1;
                break;
            case 13:
                i = R.drawable.diy_edit_frame_3296_2;
                break;
            case 14:
                i = R.drawable.diy_edit_frame_32160;
                break;
            case 16:
                i = R.drawable.diy_edit_frame_32256;
                break;
        }
        Drawable drawable = diyAnimActivity.getDrawable(i);
        Intrinsics.checkNotNull(drawable);
        diyAnimAdapter2.addData((DiyAnimAdapter) new DiyAnimVO(bitmapUtils.DrawableToBitmap(drawable)));
        DiyAnimAdapter diyAnimAdapter3 = diyAnimActivity.diyAnimAdapter;
        if (diyAnimAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diyAnimAdapter");
            diyAnimAdapter3 = null;
        }
        diyAnimAdapter3.getMMapMarkItemStatus().put(0, (byte) 0);
        DiyAnimAdapter diyAnimAdapter4 = diyAnimActivity.diyAnimAdapter;
        if (diyAnimAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diyAnimAdapter");
        } else {
            diyAnimAdapter = diyAnimAdapter4;
        }
        diyAnimAdapter.getMMapMarkItemStatus().put(1, (byte) 0);
        return Unit.INSTANCE;
    }

    private final void initIosDialogAdapter() {
        this.mAdapter = new IosDialogStyleAdapter<>(this, CollectionsKt.listOf((Object[]) new String[]{getString(R.string.title_diy_app_photos), getString(R.string.title_diy_phone_photos)}));
    }

    private final void doSelectAction() {
        LogUtils.file("DiyAnimActivity doSelectAction");
        final Dialog showBottomDialog = DialogUtils.showBottomDialog(this, R.layout.dialog_select_media);
        this.actionRecyclerView = (RecyclerView) showBottomDialog.findViewById(R.id.rl_actions);
        DiyAnimActivity diyAnimActivity = this;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(diyAnimActivity, 1, false);
        RecyclerView recyclerView = this.actionRecyclerView;
        if (recyclerView != null) {
            recyclerView.setLayoutManager(linearLayoutManager);
        }
        RecyclerView recyclerView2 = this.actionRecyclerView;
        if (recyclerView2 != null) {
            recyclerView2.addItemDecoration(new DividerItemDecoration(diyAnimActivity, 1));
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
            recyclerView4.post(new Runnable() { // from class: com.wifiled.ipixels.ui.diy.DiyAnimActivity$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    DiyAnimActivity.doSelectAction$lambda$36(DiyAnimActivity.this);
                }
            });
        }
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter3 = this.mAdapter;
        if (iosDialogStyleAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
        } else {
            iosDialogStyleAdapter = iosDialogStyleAdapter3;
        }
        iosDialogStyleAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() { // from class: com.wifiled.ipixels.ui.diy.DiyAnimActivity$$ExternalSyntheticLambda11
            @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter.OnItemClickListener
            public final void onItemClick(ViewGroup viewGroup, View view, Object obj, int i) {
                DiyAnimActivity.doSelectAction$lambda$37(showBottomDialog, this, viewGroup, view, obj, i);
            }
        });
        TextView textView = (TextView) showBottomDialog.findViewById(R.id.tv_cancel);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.diy.DiyAnimActivity$$ExternalSyntheticLambda22
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
    public static final void doSelectAction$lambda$36(DiyAnimActivity diyAnimActivity) {
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter = diyAnimActivity.mAdapter;
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter2 = null;
        if (iosDialogStyleAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
            iosDialogStyleAdapter = null;
        }
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter3 = diyAnimActivity.mAdapter;
        if (iosDialogStyleAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
        } else {
            iosDialogStyleAdapter2 = iosDialogStyleAdapter3;
        }
        int itemCount = iosDialogStyleAdapter2.getItemCount();
        RecyclerView recyclerView = diyAnimActivity.actionRecyclerView;
        Intrinsics.checkNotNull(recyclerView);
        iosDialogStyleAdapter.adaptiveRecyclerViewHeight(itemCount, recyclerView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void doSelectAction$lambda$37(Dialog dialog, DiyAnimActivity diyAnimActivity, ViewGroup viewGroup, View view, Object obj, int i) {
        LogUtils.file("DiyAnimActivity doSelectAction  mAdapter.setOnItemClickListener");
        dialog.cancel();
        if (i == 0) {
            if (!Intrinsics.areEqual(AppConfig.INSTANCE.getPrevActivityName(), "diy_anim")) {
                AppConfig.INSTANCE.setPrevActivityName(AppConfig.INSTANCE.getTopActivity());
            }
            Intent intent = new Intent(diyAnimActivity, (Class<?>) GalleryActivity.class);
            Bundle bundle = new Bundle();
            bundle.putBoolean("select_mode_by_selector", true);
            intent.putExtras(bundle);
            diyAnimActivity.startActivity(intent);
        } else if (i == 1) {
            diyAnimActivity.pickMultipleMedia.launch(PickVisualMediaRequestKt.PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE));
        }
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter = diyAnimActivity.mAdapter;
        if (iosDialogStyleAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
            iosDialogStyleAdapter = null;
        }
        iosDialogStyleAdapter.notifyDataSetChanged();
    }

    public final ActivityResultLauncher<PickVisualMediaRequest> getPickMultipleMedia() {
        return this.pickMultipleMedia;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void pickMultipleMedia$lambda$41(DiyAnimActivity diyAnimActivity, List list) {
        int i;
        T t;
        Intrinsics.checkNotNull(list);
        if (list.isEmpty()) {
            return;
        }
        DiyAnimAdapter diyAnimAdapter = diyAnimActivity.diyAnimAdapter;
        if (diyAnimAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diyAnimAdapter");
            diyAnimAdapter = null;
        }
        diyAnimAdapter.setList(null);
        FrameAnimation frameAnimation = diyAnimActivity.frameAnimation;
        if (frameAnimation != null) {
            frameAnimation.clear();
        }
        FrameAnimation frameAnimation2 = diyAnimActivity.frameAnimation;
        if (frameAnimation2 != null) {
            frameAnimation2.stop();
        }
        BitmapUtils bitmapUtils = BitmapUtils.INSTANCE;
        DiyAnimActivity diyAnimActivity2 = diyAnimActivity;
        switch (AppConfig.INSTANCE.getLedType()) {
            case 1:
            case 15:
                i = R.drawable.diy_edit_frame_1696;
                break;
            case 2:
            default:
                i = R.drawable.layer_list_diy_anim_paint;
                break;
            case 3:
            case 12:
                i = R.drawable.diy_edit_frame_1664;
                break;
            case 4:
            case 9:
            case 10:
                i = R.drawable.diy_edit_frame_1632;
                break;
            case 5:
                i = R.drawable.diy_edit_frame_2064;
                break;
            case 6:
                i = R.drawable.diy_edit_frame_32128;
                break;
            case 7:
                i = R.drawable.diy_edit_frame_16144;
                break;
            case 8:
                i = R.drawable.diy_edit_frame_16192;
                break;
            case 11:
                i = R.drawable.diy_edit_frame_3_1;
                break;
            case 13:
                i = R.drawable.diy_edit_frame_3296_2;
                break;
            case 14:
                i = R.drawable.diy_edit_frame_32160;
                break;
            case 16:
                i = R.drawable.diy_edit_frame_32256;
                break;
        }
        Drawable drawable = AppCompatResources.getDrawable(diyAnimActivity2, i);
        Intrinsics.checkNotNull(drawable);
        diyAnimAdapter.addData((DiyAnimAdapter) new DiyAnimVO(bitmapUtils.DrawableToBitmap(drawable)));
        DiyAnimAdapter diyAnimAdapter2 = diyAnimActivity.diyAnimAdapter;
        if (diyAnimAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diyAnimAdapter");
            diyAnimAdapter2 = null;
        }
        diyAnimAdapter2.getMMapMarkItemStatus().put(0, (byte) 0);
        DiyAnimAdapter diyAnimAdapter3 = diyAnimActivity.diyAnimAdapter;
        if (diyAnimAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diyAnimAdapter");
            diyAnimAdapter3 = null;
        }
        diyAnimAdapter3.getMMapMarkItemStatus().put(1, (byte) 0);
        Ref.IntRef intRef = new Ref.IntRef();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Uri uri = (Uri) it.next();
            Cursor query = diyAnimActivity.getContentResolver().query(uri, null, null, null, null);
            if (query != null) {
                query.moveToFirst();
                int columnIndex = query.getColumnIndex("_data");
                Ref.ObjectRef objectRef = new Ref.ObjectRef();
                if (columnIndex != -1) {
                    t = query.getString(columnIndex);
                } else {
                    t = UriUtils.uri2File(uri).getPath();
                }
                objectRef.element = t;
                String string = query.getString(query.getColumnIndex("mime_type"));
                query.close();
                if (Intrinsics.areEqual("image/gif", string)) {
                    FrameAnimation frameAnimation3 = diyAnimActivity.frameAnimation;
                    if (frameAnimation3 != null) {
                        frameAnimation3.stop();
                    }
                    try {
                        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(diyAnimActivity), null, null, new DiyAnimActivity$pickMultipleMedia$1$2$1(diyAnimActivity, objectRef, intRef, null), 3, null);
                    } catch (GifIOException unused) {
                        ToastUtil.show("无法解析，请重试！");
                        Unit unit = Unit.INSTANCE;
                    }
                } else {
                    Bitmap mskBitmap = BitmapUtils.INSTANCE.getMskBitmap(diyAnimActivity2, BitmapUtils.INSTANCE.getImageThumbnail((String) objectRef.element, AppConfig.INSTANCE.getLedSize().get(0).intValue(), AppConfig.INSTANCE.getLedSize().get(1).intValue()));
                    int i2 = intRef.element;
                    intRef.element = i2 + 1;
                    diyAnimActivity.addFrame(mskBitmap, i2);
                }
            }
            Log.v("ruis", " URI = " + uri);
        }
    }

    private final void doEditAction() {
        this.editable = !this.editable;
        DiyAnimAdapter diyAnimAdapter = this.diyAnimAdapter;
        DiyAnimAdapter diyAnimAdapter2 = null;
        if (diyAnimAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diyAnimAdapter");
            diyAnimAdapter = null;
        }
        diyAnimAdapter.setM_bEditable(this.editable);
        TextView textView = this.tv_edit;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_edit");
            textView = null;
        }
        textView.setText(this.editable ? "完成" : "编辑");
        DiyAnimAdapter diyAnimAdapter3 = this.diyAnimAdapter;
        if (diyAnimAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diyAnimAdapter");
        } else {
            diyAnimAdapter2 = diyAnimAdapter3;
        }
        BaseDraggableModule draggableModule = diyAnimAdapter2.getDraggableModule();
        draggableModule.setDragEnabled(this.editable);
        draggableModule.setOnItemDragListener(this);
    }

    private final void addFrame(Bitmap bitmap) {
        int m_iSelect;
        DiyAnimAdapter diyAnimAdapter = this.diyAnimAdapter;
        DiyAnimAdapter diyAnimAdapter2 = null;
        if (diyAnimAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diyAnimAdapter");
            diyAnimAdapter = null;
        }
        if (diyAnimAdapter.getM_iSelect() == -1) {
            m_iSelect = 0;
        } else {
            DiyAnimAdapter diyAnimAdapter3 = this.diyAnimAdapter;
            if (diyAnimAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("diyAnimAdapter");
                diyAnimAdapter3 = null;
            }
            m_iSelect = diyAnimAdapter3.getM_iSelect();
        }
        LogUtils.i(this.TAG + ">>>[addFrame]: " + m_iSelect + " ->");
        this.diyAnims.get(m_iSelect).setBitmap(bitmap);
        DiyAnimAdapter diyAnimAdapter4 = this.diyAnimAdapter;
        if (diyAnimAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diyAnimAdapter");
            diyAnimAdapter4 = null;
        }
        Byte b = diyAnimAdapter4.getMMapMarkItemStatus().get(Integer.valueOf(m_iSelect));
        Intrinsics.checkNotNull(b, "null cannot be cast to non-null type kotlin.Byte");
        byte byteValue = b.byteValue();
        DiyAnimAdapter diyAnimAdapter5 = this.diyAnimAdapter;
        if (diyAnimAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diyAnimAdapter");
            diyAnimAdapter5 = null;
        }
        diyAnimAdapter5.getMMapMarkItemStatus().put(Integer.valueOf(m_iSelect), Byte.valueOf((byte) (((byte) (byteValue & 1)) + 2)));
        DiyAnimAdapter diyAnimAdapter6 = this.diyAnimAdapter;
        if (diyAnimAdapter6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diyAnimAdapter");
        } else {
            diyAnimAdapter2 = diyAnimAdapter6;
        }
        diyAnimAdapter2.notifyItemChanged(m_iSelect);
        FrameAnimation frameAnimation = this.frameAnimation;
        if (frameAnimation != null) {
            frameAnimation.addFrame(bitmap);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void addFrame(Bitmap bitmap, int index) {
        DiyAnimAdapter diyAnimAdapter = this.diyAnimAdapter;
        DiyAnimAdapter diyAnimAdapter2 = null;
        if (diyAnimAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diyAnimAdapter");
            diyAnimAdapter = null;
        }
        diyAnimAdapter.getMMapMarkItemStatus().put(Integer.valueOf(index), (byte) 2);
        if (index == 0) {
            if (this.diyAnims.isEmpty()) {
                this.diyAnims.add(new DiyAnimVO(bitmap));
            } else {
                this.diyAnims.get(index).setBitmap(bitmap);
            }
            DiyAnimAdapter diyAnimAdapter3 = this.diyAnimAdapter;
            if (diyAnimAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("diyAnimAdapter");
            } else {
                diyAnimAdapter2 = diyAnimAdapter3;
            }
            diyAnimAdapter2.notifyItemChanged(index);
        } else if (index <= 200) {
            this.diyAnims.add(new DiyAnimVO(bitmap));
            DiyAnimAdapter diyAnimAdapter4 = this.diyAnimAdapter;
            if (diyAnimAdapter4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("diyAnimAdapter");
            } else {
                diyAnimAdapter2 = diyAnimAdapter4;
            }
            diyAnimAdapter2.notifyDataSetChanged();
        }
        FrameAnimation frameAnimation = this.frameAnimation;
        if (frameAnimation != null) {
            frameAnimation.addFrame(bitmap);
        }
    }

    @Override // com.wifiled.baselib.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        FrameAnimation frameAnimation = this.frameAnimation;
        if (frameAnimation != null) {
            frameAnimation.stop();
        }
        this.diyAnims.clear();
        DiyAnimAdapter diyAnimAdapter = this.diyAnimAdapter;
        if (diyAnimAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diyAnimAdapter");
            diyAnimAdapter = null;
        }
        diyAnimAdapter.getMMapMarkItemStatus().clear();
        EventBus.getDefault().unregister(this);
    }

    @Override // com.chad.library.adapter.base.listener.OnItemDragListener
    public void onItemDragMoving(RecyclerView.ViewHolder source, int from, RecyclerView.ViewHolder target, int to) {
        Integer valueOf = source != null ? Integer.valueOf(source.getAdapterPosition()) : null;
        Intrinsics.checkNotNull(valueOf);
        int intValue = valueOf.intValue();
        Integer valueOf2 = target != null ? Integer.valueOf(target.getAdapterPosition()) : null;
        Intrinsics.checkNotNull(valueOf2);
        int intValue2 = valueOf2.intValue();
        Collections.swap(this.diyAnims, from, to);
        Log.d(this.TAG, "onItemDragMoving: from:" + from + " to:" + to + " source:" + intValue + " target:" + intValue2);
    }

    @Override // com.chad.library.adapter.base.listener.OnItemDragListener
    public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int pos) {
        Log.d(this.TAG, "onItemDragStart: " + (viewHolder != null ? Integer.valueOf(viewHolder.getAdapterPosition()) : null));
        Integer valueOf = viewHolder != null ? Integer.valueOf(viewHolder.getAdapterPosition()) : null;
        Intrinsics.checkNotNull(valueOf);
        this.srcPosition = valueOf.intValue();
        viewHolder.itemView.setScaleX(1.2f);
        viewHolder.itemView.setScaleY(1.2f);
    }

    @Override // com.chad.library.adapter.base.listener.OnItemDragListener
    public void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int pos) {
        DiyAnimAdapter diyAnimAdapter = null;
        Log.d(this.TAG, "onItemDragEnd: source:" + this.srcPosition + " target:" + (viewHolder != null ? Integer.valueOf(viewHolder.getAdapterPosition()) : null));
        Integer valueOf = viewHolder != null ? Integer.valueOf(viewHolder.getAdapterPosition()) : null;
        Intrinsics.checkNotNull(valueOf);
        this.targetPosition = valueOf.intValue();
        viewHolder.itemView.setScaleX(1.0f);
        viewHolder.itemView.setScaleY(1.0f);
        Collections.swap(this.diyAnims, this.srcPosition, this.targetPosition);
        DiyAnimVO diyAnimVO = this.diyAnims.get(this.srcPosition);
        Intrinsics.checkNotNullExpressionValue(diyAnimVO, "get(...)");
        DiyAnimVO diyAnimVO2 = diyAnimVO;
        DiyAnimVO diyAnimVO3 = this.diyAnims.get(this.targetPosition);
        Intrinsics.checkNotNullExpressionValue(diyAnimVO3, "get(...)");
        DiyAnimVO diyAnimVO4 = diyAnimVO3;
        DiyAnimAdapter diyAnimAdapter2 = this.diyAnimAdapter;
        if (diyAnimAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diyAnimAdapter");
            diyAnimAdapter2 = null;
        }
        diyAnimAdapter2.notifyItemChanged(this.srcPosition, diyAnimVO4);
        DiyAnimAdapter diyAnimAdapter3 = this.diyAnimAdapter;
        if (diyAnimAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diyAnimAdapter");
        } else {
            diyAnimAdapter = diyAnimAdapter3;
        }
        diyAnimAdapter.notifyItemChanged(this.targetPosition, diyAnimVO2);
    }

    private final void saveGif(List<Bitmap> bitmaps, Function1<? super SendCore.CallbackBuilder, Unit> callbackBuilder) {
        LogUtils.file("DiyAnimActivity saveGif");
        SendCore.CallbackBuilder callbackBuilder2 = new SendCore.CallbackBuilder();
        callbackBuilder.invoke(callbackBuilder2);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new DiyAnimActivity$saveGif$1(callbackBuilder2, this, bitmaps, null), 3, null);
    }

    private final void addPaintItem() {
        int i;
        DiyAnimAdapter diyAnimAdapter = this.diyAnimAdapter;
        CustomImageView customImageView = null;
        if (diyAnimAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diyAnimAdapter");
            diyAnimAdapter = null;
        }
        BitmapUtils bitmapUtils = BitmapUtils.INSTANCE;
        switch (AppConfig.INSTANCE.getLedType()) {
            case 1:
            case 15:
                i = R.drawable.diy_edit_frame_1696;
                break;
            case 2:
            default:
                i = R.drawable.layer_list_diy_anim_paint;
                break;
            case 3:
            case 12:
                i = R.drawable.diy_edit_frame_1664;
                break;
            case 4:
            case 9:
            case 10:
                i = R.drawable.diy_edit_frame_1632;
                break;
            case 5:
                i = R.drawable.diy_edit_frame_2064;
                break;
            case 6:
                i = R.drawable.diy_edit_frame_32128;
                break;
            case 7:
                i = R.drawable.diy_edit_frame_16144;
                break;
            case 8:
                i = R.drawable.diy_edit_frame_16192;
                break;
            case 11:
                i = R.drawable.diy_edit_frame_3_1;
                break;
            case 13:
                i = R.drawable.diy_edit_frame_3296_2;
                break;
            case 14:
                i = R.drawable.diy_edit_frame_32160;
                break;
            case 16:
                i = R.drawable.diy_edit_frame_32256;
                break;
        }
        Drawable drawable = getDrawable(i);
        Intrinsics.checkNotNull(drawable);
        diyAnimAdapter.addData((DiyAnimAdapter) new DiyAnimVO(bitmapUtils.DrawableToBitmap(drawable)));
        DiyAnimAdapter diyAnimAdapter2 = this.diyAnimAdapter;
        if (diyAnimAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diyAnimAdapter");
            diyAnimAdapter2 = null;
        }
        diyAnimAdapter2.getMMapMarkItemStatus().put(0, (byte) 0);
        DiyAnimAdapter diyAnimAdapter3 = this.diyAnimAdapter;
        if (diyAnimAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diyAnimAdapter");
            diyAnimAdapter3 = null;
        }
        diyAnimAdapter3.getMMapMarkItemStatus().put(1, (byte) 0);
        String str = this.TAG;
        DiyAnimAdapter diyAnimAdapter4 = this.diyAnimAdapter;
        if (diyAnimAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diyAnimAdapter");
            diyAnimAdapter4 = null;
        }
        Log.d(str, "count: " + diyAnimAdapter4.getItemCount());
        CustomImageView customImageView2 = this.iv_diy_anim_copy;
        if (customImageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_diy_anim_copy");
            customImageView2 = null;
        }
        customImageView2.setEnabled(false);
        CustomImageView customImageView3 = this.iv_diy_anim_delete;
        if (customImageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_diy_anim_delete");
        } else {
            customImageView = customImageView3;
        }
        customImageView.setEnabled(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ArrayList<Bitmap> checkEdited() {
        ArrayList<Bitmap> arrayList = new ArrayList<>();
        ArrayList<DiyAnimVO> arrayList2 = this.diyAnims;
        ArrayList arrayList3 = new ArrayList();
        for (Object obj : arrayList2) {
            if (((DiyAnimVO) obj).getBitmap() != null) {
                arrayList3.add(obj);
            }
        }
        Iterator it = arrayList3.iterator();
        int i = 0;
        int i2 = 0;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            DiyAnimVO diyAnimVO = (DiyAnimVO) it.next();
            DiyAnimAdapter diyAnimAdapter = this.diyAnimAdapter;
            if (diyAnimAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("diyAnimAdapter");
                diyAnimAdapter = null;
            }
            Byte b = diyAnimAdapter.getMMapMarkItemStatus().get(Integer.valueOf(i2));
            Byte valueOf = b != null ? Byte.valueOf((byte) (b.byteValue() & 2)) : null;
            if (valueOf != null && valueOf.byteValue() == 2) {
                Bitmap bitmap = diyAnimVO.getBitmap();
                Intrinsics.checkNotNull(bitmap);
                arrayList.add(bitmap);
            }
            i2++;
        }
        DiyAnimAdapter diyAnimAdapter2 = this.diyAnimAdapter;
        if (diyAnimAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diyAnimAdapter");
            diyAnimAdapter2 = null;
        }
        Iterator<Map.Entry<Integer, Byte>> it2 = diyAnimAdapter2.getMMapMarkItemStatus().entrySet().iterator();
        while (it2.hasNext()) {
            if (((byte) (it2.next().getValue().byteValue() & 2)) == 2) {
                i++;
            }
        }
        if (!arrayList.isEmpty() && arrayList.size() >= 2 && i >= 2) {
            return arrayList;
        }
        toast(getString(R.string.diy_anmi_play_notice));
        return null;
    }

    @Override // com.wifiled.ipixels.view.FrameAnimation.AnimationListener
    public void onAnimationStart() {
        ImageView imageView = this.iv_diy_anim_play;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_diy_anim_play");
            imageView = null;
        }
        imageView.setSelected(true);
    }

    @Override // com.wifiled.ipixels.view.FrameAnimation.AnimationListener
    public void onAnimationStop() {
        ImageView imageView = this.iv_diy_anim_play;
        ImageView imageView2 = null;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_diy_anim_play");
            imageView = null;
        }
        imageView.setSelected(false);
        ImageView imageView3 = this.iv_git_preview;
        if (imageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_git_preview");
        } else {
            imageView2 = imageView3;
        }
        imageView2.setImageResource(0);
    }
}
