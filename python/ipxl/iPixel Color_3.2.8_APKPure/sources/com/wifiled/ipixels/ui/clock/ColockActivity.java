package com.wifiled.ipixels.ui.clock;

import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.LogUtils;
import com.wifiled.baselib.app.language.LanguageUtil;
import com.wifiled.baselib.base.BaseActivity;
import com.wifiled.baselib.base.recycleview.RecyclerAdapter;
import com.wifiled.baselib.base.recycleview.RecyclerViewHolder;
import com.wifiled.baselib.utils.SPUtils;
import com.wifiled.ipixels.App;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.UtilsExtensionKt;
import com.wifiled.ipixels.core.SendCore;
import com.wifiled.ipixels.utils.TimeHelper;
import com.wifiled.ipixels.view.customview.CustomImageView;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: ColockActivity.kt */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0015\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010/\u001a\u00020\u000bH\u0014J\b\u00100\u001a\u000201H\u0014J\b\u00102\u001a\u000201H\u0014J\b\u00103\u001a\u000201H\u0014J\b\u00104\u001a\u000201H\u0002J\b\u00105\u001a\u000201H\u0002R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082.¢\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\u00060\bj\b\u0012\u0004\u0012\u00020\u0006`\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0015X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u00066"}, d2 = {"Lcom/wifiled/ipixels/ui/clock/ColockActivity;", "Lcom/wifiled/baselib/base/BaseActivity;", "<init>", "()V", "localAdapter", "Lcom/wifiled/baselib/base/recycleview/RecyclerAdapter;", "Landroid/graphics/Bitmap;", "localImageBitmaps", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "m_iSelect", "", "mIsTime12", "", "mIsShowDate", "time_scale_iv", "Landroid/widget/ImageView;", "date_iv", "rl_time_image", "Landroidx/recyclerview/widget/RecyclerView;", "date_tv", "Landroid/widget/TextView;", "iv_colock_send", "iv_back", "Lcom/wifiled/ipixels/view/customview/CustomImageView;", "tv_title", "arrResidTime_32", "", "arrResidTime_64", "arrResidTime_16", "arrResidTime_20", "arrResidTime_12", "arrResidTime_96", "arrResidTime_128", "arrResidTime_144", "arrResidTime_192", "arrResidTime_24", "arrResidTime_32_64", "arrResidTime_32_96", "arrResidTime_32_96_2", "arrResidTime_128_2", "arrResidTime_32_192", "arrResidTime_32_160", "arrResidTime_32_384", "arrResidTime_32_320", "arrResidTime_32_256", "arrResidTime_32_448", "layoutId", "initView", "", "bindData", "bindListener", "initToolbar", "initLocalImage", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ColockActivity extends BaseActivity {
    private ImageView date_iv;
    private TextView date_tv;
    private CustomImageView iv_back;
    private ImageView iv_colock_send;
    private RecyclerAdapter<Bitmap> localAdapter;
    private RecyclerView rl_time_image;
    private ImageView time_scale_iv;
    private TextView tv_title;
    private ArrayList<Bitmap> localImageBitmaps = new ArrayList<>();
    private int m_iSelect = -1;
    private boolean mIsTime12 = true;
    private boolean mIsShowDate = true;
    private int[] arrResidTime_32 = {R.drawable.time_image_1_1, R.drawable.time_image_1_2, R.drawable.time_image_1_3, R.drawable.time_image_2_1, R.drawable.time_image_2_2, R.drawable.time_image_2_3, R.drawable.time_image_3_1, R.drawable.time_image_3_2, R.drawable.time_image_3_3};
    private int[] arrResidTime_64 = {R.drawable.time_pictures_64_1, R.drawable.time_pictures_64_2, R.drawable.time_pictures_64_3, R.drawable.time_pictures_64_4, R.drawable.time_pictures_64_5, R.drawable.time_pictures_64_6, R.drawable.time_pictures_64_7, R.drawable.time_pictures_64_8, R.drawable.time_pictures_64_9};
    private int[] arrResidTime_16 = {R.drawable.time_pictures_16x64_1_1, R.drawable.time_pictures_16x64_1_2, R.drawable.time_pictures_16x64_1_3, R.drawable.time_pictures_16x64_2_1, R.drawable.time_pictures_16x64_2_2, R.drawable.time_pictures_16x64_2_3, R.drawable.time_pictures_16x64_3_1, R.drawable.time_pictures_16x64_3_2};
    private int[] arrResidTime_20 = {R.drawable.time_pictures_20x64_1_1, R.drawable.time_pictures_20x64_1_2, R.drawable.time_pictures_20x64_1_3, R.drawable.time_pictures_20x64_2_1, R.drawable.time_pictures_20x64_2_2, R.drawable.time_pictures_20x64_2_3, R.drawable.time_pictures_20x64_3_1, R.drawable.time_pictures_20x64_3_2};
    private int[] arrResidTime_12 = {R.drawable.time_pictures_16x32_1_1, R.drawable.time_pictures_16x32_1_2, R.drawable.time_pictures_16x32_2_1, R.drawable.time_pictures_16x32_2_2, R.drawable.time_pictures_16x32_3_1, R.drawable.time_pictures_16x32_3_2, R.drawable.time_pictures_16x32_4_1, R.drawable.time_pictures_16x32_4_2, R.drawable.time_pictures_16x32_5_1, R.drawable.time_pictures_16x32_5_2};
    private int[] arrResidTime_96 = {R.drawable.time_pictures_16x96_1, R.drawable.time_pictures_16x96_2, R.drawable.time_pictures_16x96_3, R.drawable.time_pictures_16x96_4, R.drawable.time_pictures_16x96_5, R.drawable.time_pictures_16x96_6, R.drawable.time_pictures_16x96_7, R.drawable.time_pictures_16x96_8};
    private int[] arrResidTime_128 = {R.drawable.time_image_32x128_01, R.drawable.time_image_32x128_02, R.drawable.time_image_32x128_04, R.drawable.time_image_32x128_03, R.drawable.time_image_32x128_05, R.drawable.time_image_32x128_06, R.drawable.time_image_32x128_07, R.drawable.time_image_32x128_08};
    private int[] arrResidTime_144 = {R.drawable.time_image_16x144_01, R.drawable.time_image_16x144_2, R.drawable.time_image_16x144_4, R.drawable.time_image_16x144_3, R.drawable.time_image_16x144_5, R.drawable.time_image_16x144_6};
    private int[] arrResidTime_192 = {R.drawable.time_image_16x192_01, R.drawable.time_image_16x192_02, R.drawable.time_image_16x192_03, R.drawable.time_image_16x192_04, R.drawable.time_image_16x192_05, R.drawable.time_image_16x192_06, R.drawable.time_image_16x192_07, R.drawable.time_image_16x192_08};
    private int[] arrResidTime_24 = {R.drawable.time_image_24x48_02, R.drawable.time_image_24x48_03, R.drawable.time_image_24x48_04, R.drawable.time_image_24x48_05, R.drawable.time_image_24x48_06, R.drawable.time_image_24x48_07, R.drawable.time_image_24x48_08, R.drawable.time_image_24x48_09};
    private int[] arrResidTime_32_64 = {R.drawable.time_image_32x64_1, R.drawable.time_image_32x64_2, R.drawable.time_image_32x64_3, R.drawable.time_image_32x64_4, R.drawable.time_image_32x64_5, R.drawable.time_image_32x64_6, R.drawable.time_image_32x64_7, R.drawable.time_image_32x64_8};
    private int[] arrResidTime_32_96 = {R.drawable.time_image_32x96_1, R.drawable.time_image_32x96_2, R.drawable.time_image_32x96_3, R.drawable.time_image_32x96_4, R.drawable.time_image_32x96_5, R.drawable.time_image_32x96_6, R.drawable.time_image_32x96_7, R.drawable.time_image_32x96_8};
    private int[] arrResidTime_32_96_2 = {R.drawable.time_image_32x96_2_1, R.drawable.time_image_32x96_2_2, R.drawable.time_image_32x96_2_3, R.drawable.time_image_32x96_2_4, R.drawable.time_image_32x96_2_5, R.drawable.time_image_32x96_2_6, R.drawable.time_image_32x96_2_7, R.drawable.time_image_32x96_2_8};
    private int[] arrResidTime_128_2 = {R.drawable.time_image_32x128_2_1, R.drawable.time_image_32x128_2_2, R.drawable.time_image_32x128_2_3, R.drawable.time_image_32x128_2_4, R.drawable.time_image_32x128_2_5, R.drawable.time_image_32x128_2_6, R.drawable.time_image_32x128_2_7, R.drawable.time_image_32x128_2_8};
    private int[] arrResidTime_32_192 = {R.drawable.time_image_32x192_1, R.drawable.time_image_32x192_2, R.drawable.time_image_32x192_3, R.drawable.time_image_32x192_4, R.drawable.time_image_32x192_5, R.drawable.time_image_32x192_6, R.drawable.time_image_32x192_7, R.drawable.time_image_32x192_8};
    private int[] arrResidTime_32_160 = {R.drawable.time_image_32x160_1, R.drawable.time_image_32x160_2, R.drawable.time_image_32x160_3, R.drawable.time_image_32x160_4, R.drawable.time_image_32x160_5, R.drawable.time_image_32x160_6, R.drawable.time_image_32x160_7, R.drawable.time_image_32x160_8};
    private int[] arrResidTime_32_384 = {R.drawable.time_image_32x384_1, R.drawable.time_image_32x384_2, R.drawable.time_image_32x384_3, R.drawable.time_image_32x384_4, R.drawable.time_image_32x384_5, R.drawable.time_image_32x384_6, R.drawable.time_image_32x384_7, R.drawable.time_image_32x384_8};
    private int[] arrResidTime_32_320 = {R.drawable.time_image_32x320_1, R.drawable.time_image_32x320_2, R.drawable.time_image_32x320_3, R.drawable.time_image_32x320_4, R.drawable.time_image_32x320_5, R.drawable.time_image_32x320_6, R.drawable.time_image_32x320_7, R.drawable.time_image_32x320_8};
    private int[] arrResidTime_32_256 = {R.drawable.time_image_32x256_1, R.drawable.time_image_32x256_2, R.drawable.time_image_32x256_3, R.drawable.time_image_32x256_4, R.drawable.time_image_32x256_5, R.drawable.time_image_32x256_6, R.drawable.time_image_32x256_7, R.drawable.time_image_32x256_8};
    private int[] arrResidTime_32_448 = {R.drawable.time_image_32x448_1, R.drawable.time_image_32x448_2, R.drawable.time_image_32x448_3, R.drawable.time_image_32x448_4, R.drawable.time_image_32x448_5, R.drawable.time_image_32x448_6, R.drawable.time_image_32x448_7, R.drawable.time_image_32x448_8};

    @Override // com.wifiled.baselib.base.BaseActivity
    protected int layoutId() {
        return R.layout.activity_colock;
    }

    @Override // com.wifiled.baselib.base.BaseActivity
    protected void initView() {
        super.initView();
        View findViewById = findViewById(R.id.iv_back);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.iv_back = (CustomImageView) findViewById;
        View findViewById2 = findViewById(R.id.tv_title);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.tv_title = (TextView) findViewById2;
        View findViewById3 = findViewById(R.id.time_scale_iv);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.time_scale_iv = (ImageView) findViewById3;
        View findViewById4 = findViewById(R.id.date_iv);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
        this.date_iv = (ImageView) findViewById4;
        View findViewById5 = findViewById(R.id.rl_time_image);
        Intrinsics.checkNotNullExpressionValue(findViewById5, "findViewById(...)");
        this.rl_time_image = (RecyclerView) findViewById5;
        View findViewById6 = findViewById(R.id.date_tv);
        Intrinsics.checkNotNullExpressionValue(findViewById6, "findViewById(...)");
        this.date_tv = (TextView) findViewById6;
        View findViewById7 = findViewById(R.id.iv_colock_send);
        Intrinsics.checkNotNullExpressionValue(findViewById7, "findViewById(...)");
        this.iv_colock_send = (ImageView) findViewById7;
    }

    @Override // com.wifiled.baselib.base.BaseActivity
    protected void bindData() {
        LogUtils.file("ColockActivity bindData");
        initToolbar();
        initLocalImage();
        ColockActivity colockActivity = this;
        if (SPUtils.contains(colockActivity, "colock_time")) {
            Object obj = SPUtils.get(colockActivity, "colock_time", true);
            Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
            this.mIsTime12 = ((Boolean) obj).booleanValue();
        }
        if (SPUtils.contains(colockActivity, "colock_date")) {
            Object obj2 = SPUtils.get(colockActivity, "colock_date", true);
            Intrinsics.checkNotNullExpressionValue(obj2, "get(...)");
            this.mIsShowDate = ((Boolean) obj2).booleanValue();
        }
        if (this.mIsTime12) {
            ImageView imageView = this.time_scale_iv;
            if (imageView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("time_scale_iv");
                imageView = null;
            }
            imageView.setImageResource(R.drawable.time_12);
        } else {
            ImageView imageView2 = this.time_scale_iv;
            if (imageView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("time_scale_iv");
                imageView2 = null;
            }
            imageView2.setImageResource(R.drawable.time_24);
        }
        ImageView imageView3 = this.date_iv;
        if (imageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("date_iv");
            imageView3 = null;
        }
        imageView3.setSelected(this.mIsShowDate);
        RecyclerView recyclerView = this.rl_time_image;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rl_time_image");
            recyclerView = null;
        }
        recyclerView.setLayoutManager(new GridLayoutManager(colockActivity, 3));
        switch (AppConfig.INSTANCE.getLedType()) {
            case 0:
                this.arrResidTime_32 = this.arrResidTime_64;
                RecyclerView recyclerView2 = this.rl_time_image;
                if (recyclerView2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rl_time_image");
                    recyclerView2 = null;
                }
                recyclerView2.setLayoutManager(new GridLayoutManager(colockActivity, 3));
                break;
            case 1:
                this.arrResidTime_32 = this.arrResidTime_96;
                RecyclerView recyclerView3 = this.rl_time_image;
                if (recyclerView3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rl_time_image");
                    recyclerView3 = null;
                }
                recyclerView3.setLayoutManager(new LinearLayoutManager(colockActivity));
                break;
            case 3:
                this.arrResidTime_32 = this.arrResidTime_16;
                RecyclerView recyclerView4 = this.rl_time_image;
                if (recyclerView4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rl_time_image");
                    recyclerView4 = null;
                }
                recyclerView4.setLayoutManager(new LinearLayoutManager(colockActivity));
                break;
            case 4:
                this.arrResidTime_32 = this.arrResidTime_12;
                RecyclerView recyclerView5 = this.rl_time_image;
                if (recyclerView5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rl_time_image");
                    recyclerView5 = null;
                }
                recyclerView5.setLayoutManager(new GridLayoutManager(colockActivity, 2));
                break;
            case 5:
                this.arrResidTime_32 = this.arrResidTime_20;
                RecyclerView recyclerView6 = this.rl_time_image;
                if (recyclerView6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rl_time_image");
                    recyclerView6 = null;
                }
                recyclerView6.setLayoutManager(new LinearLayoutManager(colockActivity));
                break;
            case 6:
                this.arrResidTime_32 = this.arrResidTime_128;
                RecyclerView recyclerView7 = this.rl_time_image;
                if (recyclerView7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rl_time_image");
                    recyclerView7 = null;
                }
                recyclerView7.setLayoutManager(new LinearLayoutManager(colockActivity));
                break;
            case 7:
                this.arrResidTime_32 = this.arrResidTime_144;
                RecyclerView recyclerView8 = this.rl_time_image;
                if (recyclerView8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rl_time_image");
                    recyclerView8 = null;
                }
                recyclerView8.setLayoutManager(new LinearLayoutManager(colockActivity));
                ImageView imageView4 = this.date_iv;
                if (imageView4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("date_iv");
                    imageView4 = null;
                }
                imageView4.setSelected(true);
                ImageView imageView5 = this.date_iv;
                if (imageView5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("date_iv");
                    imageView5 = null;
                }
                imageView5.setEnabled(false);
                ImageView imageView6 = this.date_iv;
                if (imageView6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("date_iv");
                    imageView6 = null;
                }
                imageView6.setAlpha(0.6f);
                TextView textView = this.date_tv;
                if (textView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("date_tv");
                    textView = null;
                }
                textView.setAlpha(0.6f);
                break;
            case 8:
                this.arrResidTime_32 = this.arrResidTime_192;
                RecyclerView recyclerView9 = this.rl_time_image;
                if (recyclerView9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rl_time_image");
                    recyclerView9 = null;
                }
                recyclerView9.setLayoutManager(new LinearLayoutManager(colockActivity));
                ImageView imageView7 = this.date_iv;
                if (imageView7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("date_iv");
                    imageView7 = null;
                }
                imageView7.setSelected(true);
                ImageView imageView8 = this.date_iv;
                if (imageView8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("date_iv");
                    imageView8 = null;
                }
                imageView8.setEnabled(false);
                ImageView imageView9 = this.date_iv;
                if (imageView9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("date_iv");
                    imageView9 = null;
                }
                imageView9.setAlpha(0.6f);
                TextView textView2 = this.date_tv;
                if (textView2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("date_tv");
                    textView2 = null;
                }
                textView2.setAlpha(0.6f);
                break;
            case 9:
                this.arrResidTime_32 = this.arrResidTime_24;
                RecyclerView recyclerView10 = this.rl_time_image;
                if (recyclerView10 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rl_time_image");
                    recyclerView10 = null;
                }
                recyclerView10.setLayoutManager(new GridLayoutManager(colockActivity, 2));
                break;
            case 10:
                this.arrResidTime_32 = this.arrResidTime_32_64;
                RecyclerView recyclerView11 = this.rl_time_image;
                if (recyclerView11 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rl_time_image");
                    recyclerView11 = null;
                }
                recyclerView11.setLayoutManager(new GridLayoutManager(colockActivity, 2));
                break;
            case 11:
                this.arrResidTime_32 = this.arrResidTime_32_96;
                RecyclerView recyclerView12 = this.rl_time_image;
                if (recyclerView12 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rl_time_image");
                    recyclerView12 = null;
                }
                recyclerView12.setLayoutManager(new GridLayoutManager(colockActivity, 2));
                break;
            case 12:
                this.arrResidTime_32 = this.arrResidTime_128_2;
                RecyclerView recyclerView13 = this.rl_time_image;
                if (recyclerView13 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rl_time_image");
                    recyclerView13 = null;
                }
                recyclerView13.setLayoutManager(new LinearLayoutManager(colockActivity));
                break;
            case 13:
                this.arrResidTime_32 = this.arrResidTime_32_96_2;
                RecyclerView recyclerView14 = this.rl_time_image;
                if (recyclerView14 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rl_time_image");
                    recyclerView14 = null;
                }
                recyclerView14.setLayoutManager(new GridLayoutManager(colockActivity, 2));
                break;
            case 14:
                this.arrResidTime_32 = this.arrResidTime_32_160;
                RecyclerView recyclerView15 = this.rl_time_image;
                if (recyclerView15 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rl_time_image");
                    recyclerView15 = null;
                }
                recyclerView15.setLayoutManager(new LinearLayoutManager(colockActivity));
                break;
            case 15:
                this.arrResidTime_32 = this.arrResidTime_32_192;
                RecyclerView recyclerView16 = this.rl_time_image;
                if (recyclerView16 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rl_time_image");
                    recyclerView16 = null;
                }
                recyclerView16.setLayoutManager(new LinearLayoutManager(colockActivity));
                break;
            case 16:
                this.arrResidTime_32 = this.arrResidTime_32_256;
                RecyclerView recyclerView17 = this.rl_time_image;
                if (recyclerView17 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rl_time_image");
                    recyclerView17 = null;
                }
                recyclerView17.setLayoutManager(new LinearLayoutManager(colockActivity));
                ImageView imageView10 = this.date_iv;
                if (imageView10 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("date_iv");
                    imageView10 = null;
                }
                imageView10.setSelected(true);
                ImageView imageView11 = this.date_iv;
                if (imageView11 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("date_iv");
                    imageView11 = null;
                }
                imageView11.setEnabled(false);
                ImageView imageView12 = this.date_iv;
                if (imageView12 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("date_iv");
                    imageView12 = null;
                }
                imageView12.setAlpha(0.6f);
                TextView textView3 = this.date_tv;
                if (textView3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("date_tv");
                    textView3 = null;
                }
                textView3.setAlpha(0.6f);
                break;
            case 17:
                this.arrResidTime_32 = this.arrResidTime_32_320;
                RecyclerView recyclerView18 = this.rl_time_image;
                if (recyclerView18 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rl_time_image");
                    recyclerView18 = null;
                }
                recyclerView18.setLayoutManager(new LinearLayoutManager(colockActivity));
                ImageView imageView13 = this.date_iv;
                if (imageView13 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("date_iv");
                    imageView13 = null;
                }
                imageView13.setSelected(true);
                ImageView imageView14 = this.date_iv;
                if (imageView14 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("date_iv");
                    imageView14 = null;
                }
                imageView14.setEnabled(false);
                ImageView imageView15 = this.date_iv;
                if (imageView15 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("date_iv");
                    imageView15 = null;
                }
                imageView15.setAlpha(0.6f);
                TextView textView4 = this.date_tv;
                if (textView4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("date_tv");
                    textView4 = null;
                }
                textView4.setAlpha(0.6f);
                break;
            case 18:
                this.arrResidTime_32 = this.arrResidTime_32_384;
                RecyclerView recyclerView19 = this.rl_time_image;
                if (recyclerView19 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rl_time_image");
                    recyclerView19 = null;
                }
                recyclerView19.setLayoutManager(new LinearLayoutManager(colockActivity));
                ImageView imageView16 = this.date_iv;
                if (imageView16 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("date_iv");
                    imageView16 = null;
                }
                imageView16.setSelected(true);
                ImageView imageView17 = this.date_iv;
                if (imageView17 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("date_iv");
                    imageView17 = null;
                }
                imageView17.setEnabled(false);
                ImageView imageView18 = this.date_iv;
                if (imageView18 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("date_iv");
                    imageView18 = null;
                }
                imageView18.setAlpha(0.6f);
                TextView textView5 = this.date_tv;
                if (textView5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("date_tv");
                    textView5 = null;
                }
                textView5.setAlpha(0.6f);
                break;
            case 19:
                this.arrResidTime_32 = this.arrResidTime_32_448;
                RecyclerView recyclerView20 = this.rl_time_image;
                if (recyclerView20 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rl_time_image");
                    recyclerView20 = null;
                }
                recyclerView20.setLayoutManager(new LinearLayoutManager(colockActivity));
                ImageView imageView19 = this.date_iv;
                if (imageView19 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("date_iv");
                    imageView19 = null;
                }
                imageView19.setSelected(true);
                ImageView imageView20 = this.date_iv;
                if (imageView20 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("date_iv");
                    imageView20 = null;
                }
                imageView20.setEnabled(false);
                ImageView imageView21 = this.date_iv;
                if (imageView21 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("date_iv");
                    imageView21 = null;
                }
                imageView21.setAlpha(0.6f);
                TextView textView6 = this.date_tv;
                if (textView6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("date_tv");
                    textView6 = null;
                }
                textView6.setAlpha(0.6f);
                break;
        }
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), null, new ColockActivity$bindData$1(this, null), 2, null);
    }

    @Override // com.wifiled.baselib.base.BaseActivity
    protected void bindListener() {
        super.bindListener();
        this.m_iSelect = 0;
        RecyclerAdapter<Bitmap> recyclerAdapter = this.localAdapter;
        ImageView imageView = null;
        if (recyclerAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("localAdapter");
            recyclerAdapter = null;
        }
        recyclerAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() { // from class: com.wifiled.ipixels.ui.clock.ColockActivity$$ExternalSyntheticLambda0
            @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter.OnItemClickListener
            public final void onItemClick(ViewGroup viewGroup, View view, Object obj, int i) {
                ColockActivity.bindListener$lambda$0(ColockActivity.this, viewGroup, view, (Bitmap) obj, i);
            }
        });
        ImageView imageView2 = this.iv_colock_send;
        if (imageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_colock_send");
            imageView2 = null;
        }
        imageView2.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.clock.ColockActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ColockActivity.bindListener$lambda$1(ColockActivity.this, view);
            }
        });
        ImageView imageView3 = this.date_iv;
        if (imageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("date_iv");
            imageView3 = null;
        }
        imageView3.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.clock.ColockActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ColockActivity.bindListener$lambda$2(ColockActivity.this, view);
            }
        });
        ImageView imageView4 = this.time_scale_iv;
        if (imageView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("time_scale_iv");
            imageView4 = null;
        }
        imageView4.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.clock.ColockActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ColockActivity.bindListener$lambda$3(ColockActivity.this, view);
            }
        });
        CustomImageView.Companion companion = CustomImageView.INSTANCE;
        ImageView imageView5 = this.iv_colock_send;
        if (imageView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_colock_send");
        } else {
            imageView = imageView5;
        }
        companion.attachViewOnTouchListener(imageView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$0(ColockActivity colockActivity, ViewGroup viewGroup, View view, Bitmap bitmap, int i) {
        LogUtils.file("ColockActivity localAdapter.setOnItemClickListener");
        colockActivity.m_iSelect = i;
        RecyclerAdapter<Bitmap> recyclerAdapter = null;
        SendCore.INSTANCE.sendColockMode(colockActivity.m_iSelect + 1, colockActivity.mIsTime12, colockActivity.mIsShowDate, null);
        RecyclerAdapter<Bitmap> recyclerAdapter2 = colockActivity.localAdapter;
        if (recyclerAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("localAdapter");
        } else {
            recyclerAdapter = recyclerAdapter2;
        }
        recyclerAdapter.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$1(ColockActivity colockActivity, View view) {
        LogUtils.file("ColockActivity iv_colock_send.setOnClickListener");
        if (TimeHelper.INSTANCE.allowSend(500)) {
            SendCore.INSTANCE.sendColockMode(colockActivity.m_iSelect + 1, colockActivity.mIsTime12, colockActivity.mIsShowDate, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$2(ColockActivity colockActivity, View view) {
        LogUtils.file("ColockActivity date_iv.setOnClickListener");
        if (colockActivity.mIsShowDate) {
            colockActivity.mIsShowDate = false;
            ImageView imageView = colockActivity.date_iv;
            if (imageView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("date_iv");
                imageView = null;
            }
            imageView.setSelected(false);
        } else {
            colockActivity.mIsShowDate = true;
            ImageView imageView2 = colockActivity.date_iv;
            if (imageView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("date_iv");
                imageView2 = null;
            }
            imageView2.setSelected(true);
        }
        ImageView imageView3 = colockActivity.date_iv;
        if (imageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("date_iv");
            imageView3 = null;
        }
        imageView3.setSelected(colockActivity.mIsShowDate);
        SPUtils.put(colockActivity, "colock_date", Boolean.valueOf(colockActivity.mIsShowDate));
        SendCore.INSTANCE.sendColockMode(colockActivity.m_iSelect + 1, colockActivity.mIsTime12, colockActivity.mIsShowDate, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$3(ColockActivity colockActivity, View view) {
        LogUtils.file("ColockActivity time_scale_iv.setOnClickListener");
        if (colockActivity.mIsTime12) {
            colockActivity.mIsTime12 = false;
            ImageView imageView = colockActivity.time_scale_iv;
            if (imageView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("time_scale_iv");
                imageView = null;
            }
            imageView.setImageResource(R.drawable.time_24);
        } else {
            colockActivity.mIsTime12 = true;
            ImageView imageView2 = colockActivity.time_scale_iv;
            if (imageView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("time_scale_iv");
                imageView2 = null;
            }
            imageView2.setImageResource(R.drawable.time_12);
        }
        SPUtils.put(colockActivity, "colock_time", Boolean.valueOf(colockActivity.mIsTime12));
        SendCore.INSTANCE.sendColockMode(colockActivity.m_iSelect + 1, colockActivity.mIsTime12, colockActivity.mIsShowDate, null);
    }

    private final void initToolbar() {
        String saveLanguage = LanguageUtil.getSaveLanguage(App.INSTANCE.getContext());
        Intrinsics.checkNotNullExpressionValue(saveLanguage, "getSaveLanguage(...)");
        TextView textView = null;
        if (StringsKt.contains$default((CharSequence) saveLanguage, (CharSequence) "ar", false, 2, (Object) null)) {
            CustomImageView customImageView = this.iv_back;
            if (customImageView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("iv_back");
                customImageView = null;
            }
            customImageView.setBackgroundResource(R.mipmap.icon_back_ar);
        } else {
            CustomImageView customImageView2 = this.iv_back;
            if (customImageView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("iv_back");
                customImageView2 = null;
            }
            customImageView2.setBackgroundResource(R.mipmap.icon_back);
        }
        CustomImageView customImageView3 = this.iv_back;
        if (customImageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_back");
            customImageView3 = null;
        }
        customImageView3.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.clock.ColockActivity$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ColockActivity.initToolbar$lambda$4(ColockActivity.this, view);
            }
        });
        CustomImageView.Companion companion = CustomImageView.INSTANCE;
        CustomImageView customImageView4 = this.iv_back;
        if (customImageView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iv_back");
            customImageView4 = null;
        }
        companion.attachViewOnTouchListener(customImageView4);
        TextView textView2 = this.tv_title;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tv_title");
        } else {
            textView = textView2;
        }
        textView.setText(getString(R.string.title_colock));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initToolbar$lambda$4(ColockActivity colockActivity, View view) {
        LogUtils.file("ColockActivity iv_back.setOnClickListener");
        colockActivity.finish();
    }

    private final void initLocalImage() {
        final Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = R.layout.item_image_imageview;
        switch (AppConfig.INSTANCE.getLedType()) {
            case 1:
            case 15:
                intRef.element = R.layout.item_image_imageview_1696;
                break;
            case 3:
            case 12:
                intRef.element = R.layout.item_image_imageview_1664;
                break;
            case 4:
            case 9:
            case 10:
                intRef.element = R.layout.item_image_imageview_1632;
                break;
            case 5:
                intRef.element = R.layout.item_image_imageview_2064;
                break;
            case 6:
                intRef.element = R.layout.item_image_imageview_32128;
                break;
            case 7:
                intRef.element = R.layout.item_image_imageview_16144;
                break;
            case 8:
                intRef.element = R.layout.item_image_imageview_16192;
                break;
            case 11:
            case 13:
                intRef.element = R.layout.item_image_imageview_3296;
                break;
            case 14:
                intRef.element = R.layout.item_image_imageview_32160;
                break;
            case 16:
                intRef.element = R.layout.item_image_imageview_32256;
                break;
            case 17:
                intRef.element = R.layout.item_image_imageview_32320;
                break;
            case 18:
                intRef.element = R.layout.item_image_imageview_32384;
                break;
            case 19:
                intRef.element = R.layout.item_image_imageview_32448;
                break;
        }
        final ArrayList<Bitmap> arrayList = this.localImageBitmaps;
        this.localAdapter = new RecyclerAdapter<Bitmap>(intRef, arrayList) { // from class: com.wifiled.ipixels.ui.clock.ColockActivity$initLocalImage$1
            {
                super(ColockActivity.this, arrayList, intRef.element);
            }

            @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter
            public void convert(RecyclerViewHolder holder, Bitmap bitmap) {
                int i;
                boolean z;
                int i2;
                int i3;
                int i4;
                int i5;
                int i6;
                Intrinsics.checkNotNullParameter(holder, "holder");
                Intrinsics.checkNotNullParameter(bitmap, "bitmap");
                holder.setImageBitmap(R.id.iv_preview, bitmap);
                ImageView imageView = (ImageView) holder.getView(R.id.iv_preview);
                switch (AppConfig.INSTANCE.getLedType()) {
                    case 1:
                    case 3:
                    case 7:
                    case 8:
                    case 12:
                    case 14:
                    case 15:
                    case 16:
                    case 17:
                    case 18:
                    case 19:
                        i = ColockActivity.this.m_iSelect;
                        z = i == getPosition(holder);
                        if (z) {
                            holder.setBackgroundRes(R.id.rl_image_outside_frame, R.drawable.colock_item_select_while);
                            return;
                        } else {
                            if (z) {
                                throw new NoWhenBranchMatchedException();
                            }
                            holder.setBackgroundRes(R.id.rl_image_outside_frame, R.color.transparent);
                            return;
                        }
                    case 2:
                    case 11:
                    default:
                        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
                        layoutParams.height = (this.mContext.getResources().getDisplayMetrics().widthPixels / 3) - UtilsExtensionKt.toDp(24);
                        imageView.setLayoutParams(layoutParams);
                        i6 = ColockActivity.this.m_iSelect;
                        z = i6 == getPosition(holder);
                        if (z) {
                            holder.setBackgroundRes(R.id.rl_image_outside_frame, R.drawable.item_sel);
                            return;
                        } else {
                            if (z) {
                                throw new NoWhenBranchMatchedException();
                            }
                            holder.setBackgroundRes(R.id.rl_image_outside_frame, R.color.transparent);
                            return;
                        }
                    case 4:
                    case 9:
                    case 10:
                        ViewGroup.LayoutParams layoutParams2 = imageView.getLayoutParams();
                        layoutParams2.height = (this.mContext.getResources().getDisplayMetrics().widthPixels / 2) - UtilsExtensionKt.toDp(24);
                        imageView.setLayoutParams(layoutParams2);
                        i2 = ColockActivity.this.m_iSelect;
                        z = i2 == getPosition(holder);
                        if (z) {
                            holder.setBackgroundRes(R.id.rl_image_outside_frame, R.drawable.item_sel);
                            return;
                        } else {
                            if (z) {
                                throw new NoWhenBranchMatchedException();
                            }
                            holder.setBackgroundRes(R.id.rl_image_outside_frame, R.color.transparent);
                            return;
                        }
                    case 5:
                        i3 = ColockActivity.this.m_iSelect;
                        z = i3 == getPosition(holder);
                        if (z) {
                            holder.setBackgroundRes(R.id.rl_image_outside_frame, R.drawable.gallery_frame_select_2064);
                            return;
                        } else {
                            if (z) {
                                throw new NoWhenBranchMatchedException();
                            }
                            holder.setBackgroundRes(R.id.rl_image_outside_frame, R.color.transparent);
                            return;
                        }
                    case 6:
                        i4 = ColockActivity.this.m_iSelect;
                        z = i4 == getPosition(holder);
                        if (z) {
                            holder.setBackgroundRes(R.id.rl_select_bg, R.drawable.time_image_32x128_select);
                            return;
                        } else {
                            if (z) {
                                throw new NoWhenBranchMatchedException();
                            }
                            holder.setBackgroundRes(R.id.rl_select_bg, R.color.transparent);
                            return;
                        }
                    case 13:
                        i5 = ColockActivity.this.m_iSelect;
                        z = i5 == getPosition(holder);
                        if (z) {
                            holder.setBackgroundRes(R.id.rl_select_bg, R.drawable.image_96_item_sel);
                            return;
                        } else {
                            if (z) {
                                throw new NoWhenBranchMatchedException();
                            }
                            holder.setBackgroundRes(R.id.rl_select_bg, R.color.transparent);
                            return;
                        }
                }
            }
        };
        RecyclerView recyclerView = this.rl_time_image;
        RecyclerAdapter<Bitmap> recyclerAdapter = null;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rl_time_image");
            recyclerView = null;
        }
        RecyclerAdapter<Bitmap> recyclerAdapter2 = this.localAdapter;
        if (recyclerAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("localAdapter");
        } else {
            recyclerAdapter = recyclerAdapter2;
        }
        recyclerView.setAdapter(recyclerAdapter);
    }
}
