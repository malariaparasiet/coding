package com.wifiled.ipixels.ui.imgtxt;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.fastjson2.internal.asm.Opcodes;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.wifiled.baselib.CoreBase;
import com.wifiled.baselib.app.language.LanguageUtil;
import com.wifiled.baselib.base.recycleview.RecyclerAdapter;
import com.wifiled.baselib.base.recycleview.RecyclerViewHolder;
import com.wifiled.baselib.uicode.base.UiBaseActivity;
import com.wifiled.baselib.utils.ScreenUtil;
import com.wifiled.baselib.utils.ToastUtil;
import com.wifiled.ipixels.App;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.UtilsExtensionKt;
import com.wifiled.ipixels.core.GifCore;
import com.wifiled.ipixels.core.text.CharacterUtilsKt;
import com.wifiled.ipixels.databinding.ActivityTextAnimationBinding;
import com.wifiled.ipixels.ui.adapter.IosDialogStyleAdapter;
import com.wifiled.ipixels.view.ColorBarView;
import com.wifiled.ipixels.view.customview.CustomImageView;
import com.wifiled.musiclib.player.constant.PlayerFinal;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import org.bouncycastle.pqc.crypto.mlkem.MLKEMEngine;

/* compiled from: TextAnimationActivity.kt */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0004B\u0007¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010%\u001a\u00020\u0003H\u0016J\b\u0010&\u001a\u00020\u0002H\u0016J\u0012\u0010'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010*H\u0016J\b\u0010+\u001a\u00020\tH\u0002J\u0010\u0010,\u001a\u00020(2\u0006\u0010-\u001a\u00020\u0019H\u0002J\b\u0010.\u001a\u00020(H\u0002J\b\u0010/\u001a\u00020(H\u0002J\b\u00100\u001a\u00020(H\u0002J\b\u00101\u001a\u00020(H\u0002J\u0018\u00102\u001a\u00020(2\u0006\u00103\u001a\u00020\t2\u0006\u00104\u001a\u000205H\u0002J\b\u00106\u001a\u00020(H\u0002J\u0010\u00107\u001a\u00020(2\u0006\u00108\u001a\u000209H\u0016J \u0010:\u001a\u00020(2\f\u0010;\u001a\b\u0012\u0004\u0012\u00020<0\b2\b\b\u0002\u00104\u001a\u000205H\u0002R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\bX\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\t0\u000eX\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\t0\u000eX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00130\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u001f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010!0 X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u0004\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006="}, d2 = {"Lcom/wifiled/ipixels/ui/imgtxt/TextAnimationActivity;", "Lcom/wifiled/baselib/uicode/base/UiBaseActivity;", "Lcom/wifiled/ipixels/ui/imgtxt/TextAnimationModel;", "Lcom/wifiled/ipixels/databinding/ActivityTextAnimationBinding;", "Landroid/view/View$OnClickListener;", "<init>", "()V", "mColorsData", "", "", "mSingleColorsData", "mTAEffectData", "Lcom/wifiled/ipixels/ui/imgtxt/TextAnimationEffectBean;", "mColorAdapter", "Lcom/wifiled/baselib/base/recycleview/RecyclerAdapter;", "mSingleColorAdapter", "mTextAnimationEffectAdapter", "Lcom/wifiled/ipixels/ui/imgtxt/TextAnimationEffectAdapter;", "mGifPath", "", "mTextColor", "mGradientColor", "mSingleColor", "mEffectPosition", "mAnimationSpeed", "", "mPauseTime", "mPauseTimeGif", "mPauseTimeRecyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "mMaxLenth", "mPauseTimeAdapter", "Lcom/wifiled/ipixels/ui/adapter/IosDialogStyleAdapter;", "", "mPauseTimeDialog", "Landroid/app/Dialog;", "mTextDirection", "getViewBinding", "initViewModel", "initView", "", "savedInstanceState", "Landroid/os/Bundle;", "getTextTotalSize", "setRatio", "ratio", "initPauseTimeDialog", "initEffectAdapter", "initColorAdapter", "initBaseColorAdapter", "createGif", PlayerFinal.PLAYER_POSITION, "isSave", "", "initToolBar", "onClick", "v", "Landroid/view/View;", "saveFramesAsGif2", "frames", "Lcom/wifiled/ipixels/ui/imgtxt/SaveGifData;", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TextAnimationActivity extends UiBaseActivity<TextAnimationModel, ActivityTextAnimationBinding> implements View.OnClickListener {
    private RecyclerAdapter<Integer> mColorAdapter;
    private List<Integer> mColorsData;
    private int mEffectPosition;
    private IosDialogStyleAdapter<Object> mPauseTimeAdapter;
    private Dialog mPauseTimeDialog;
    private RecyclerView mPauseTimeRecyclerView;
    private RecyclerAdapter<Integer> mSingleColorAdapter;
    private List<Integer> mSingleColorsData;
    private List<TextAnimationEffectBean> mTAEffectData;
    private TextAnimationEffectAdapter mTextAnimationEffectAdapter;
    private String mGifPath = "";
    private int mTextColor = -1;
    private int mGradientColor = -1;
    private int mSingleColor = -1;
    private float mAnimationSpeed = 13.0f;
    private final List<String> mPauseTime = CollectionsKt.listOf((Object[]) new String[]{"1S", "2S", "3S", "5S", "10S"});
    private int mPauseTimeGif = 1000;
    private int mMaxLenth = 32;
    private int mTextDirection = 1;

    @Override // com.wifiled.baselib.uicode.base.UiBaseActivity
    public ActivityTextAnimationBinding getViewBinding() {
        ActivityTextAnimationBinding inflate = ActivityTextAnimationBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        return inflate;
    }

    @Override // com.wifiled.baselib.uicode.base.UiBaseActivity
    public TextAnimationModel initViewModel() {
        return (TextAnimationModel) new ViewModelProvider(this).get(TextAnimationModel.class);
    }

    @Override // com.wifiled.baselib.uicode.base.UiBaseActivity, com.wifiled.baselib.uicode.inner.IBaseView
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        switch (AppConfig.INSTANCE.getLedType()) {
            case 0:
            case 2:
                setRatio(1.0f);
                getBinding().textDirection.setVisibility(8);
                getBinding().textDirectionVertical.setVisibility(8);
                getBinding().textDirectionHorizontal.setVisibility(8);
                break;
            case 1:
            case 15:
                setRatio(6.0f);
                break;
            case 3:
            case 6:
            case 12:
                setRatio(4.0f);
                break;
            case 4:
            case 9:
            case 10:
                setRatio(2.0f);
                break;
            case 5:
                setRatio(3.2f);
                break;
            case 7:
                setRatio(9.0f);
                break;
            case 8:
                setRatio(12.0f);
                break;
            case 11:
            case 13:
                setRatio(3.0f);
                break;
            case 14:
                setRatio(5.0f);
                break;
            case 16:
                setRatio(8.0f);
                break;
            case 17:
                getBinding().recyclerviewColorBcGradation.setVisibility(0);
                getBinding().recyclerviewColorBc.setVisibility(0);
                getBinding().recyclerviewColor.setVisibility(4);
                getBinding().colorBarViewText.setVisibility(4);
                setRatio(10.0f);
                break;
            case 18:
                getBinding().recyclerviewColorBcGradation.setVisibility(0);
                getBinding().recyclerviewColorBc.setVisibility(0);
                getBinding().recyclerviewColor.setVisibility(4);
                getBinding().colorBarViewText.setVisibility(4);
                setRatio(12.0f);
                break;
            case 19:
                getBinding().recyclerviewColorBcGradation.setVisibility(0);
                getBinding().recyclerviewColorBc.setVisibility(0);
                getBinding().recyclerviewColor.setVisibility(4);
                getBinding().colorBarViewText.setVisibility(4);
                setRatio(14.0f);
                break;
        }
        initToolBar();
        if (AppConfig.INSTANCE.getLedType() != 18 && AppConfig.INSTANCE.getLedType() != 17 && AppConfig.INSTANCE.getLedType() != 19) {
            initColorAdapter();
        } else {
            initBaseColorAdapter();
        }
        initEffectAdapter();
        initPauseTimeDialog();
        getBinding().textDirectionHorizontal.setSelected(true);
        getBinding().colorBarViewText.setOnColorChangerListener(new ColorBarView.OnColorChangeListener() { // from class: com.wifiled.ipixels.ui.imgtxt.TextAnimationActivity$$ExternalSyntheticLambda8
            @Override // com.wifiled.ipixels.view.ColorBarView.OnColorChangeListener
            public final void onColorChange(int i) {
                TextAnimationActivity.initView$lambda$0(TextAnimationActivity.this, i);
            }
        });
        TextAnimationActivity textAnimationActivity = this;
        getBinding().textDirectionHorizontal.setOnClickListener(textAnimationActivity);
        getBinding().textDirectionVertical.setOnClickListener(textAnimationActivity);
        getBinding().tvTextPaauseTime.setOnClickListener(textAnimationActivity);
        getBinding().ivTextPause.setOnClickListener(textAnimationActivity);
        getBinding().ivTextSend.setOnClickListener(textAnimationActivity);
        SeekBar sbSpeed = getBinding().sbSpeed;
        Intrinsics.checkNotNullExpressionValue(sbSpeed, "sbSpeed");
        UtilsExtensionKt.setOnSeekBarStopChangeListener(sbSpeed, new Function1() { // from class: com.wifiled.ipixels.ui.imgtxt.TextAnimationActivity$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit initView$lambda$1;
                initView$lambda$1 = TextAnimationActivity.initView$lambda$1(TextAnimationActivity.this, (SeekBar) obj);
                return initView$lambda$1;
            }
        });
        getBinding().etInput.addTextChangedListener(new TextWatcher() { // from class: com.wifiled.ipixels.ui.imgtxt.TextAnimationActivity$initView$3
            private int selectionEnd;
            private int selectionStart;

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                ActivityTextAnimationBinding binding;
                ActivityTextAnimationBinding binding2;
                int textTotalSize;
                ActivityTextAnimationBinding binding3;
                int textTotalSize2;
                int i;
                ActivityTextAnimationBinding binding4;
                Intrinsics.checkNotNullParameter(s, "s");
                binding = TextAnimationActivity.this.getBinding();
                this.selectionStart = binding.etInput.getSelectionStart();
                binding2 = TextAnimationActivity.this.getBinding();
                this.selectionEnd = binding2.etInput.getSelectionEnd();
                textTotalSize = TextAnimationActivity.this.getTextTotalSize();
                if (textTotalSize > 32) {
                    s.delete(this.selectionStart - 1, this.selectionEnd);
                    binding4 = TextAnimationActivity.this.getBinding();
                    binding4.etInput.setText(s);
                }
                binding3 = TextAnimationActivity.this.getBinding();
                TextView textView = binding3.tvFontlimitInAttr;
                textTotalSize2 = TextAnimationActivity.this.getTextTotalSize();
                i = TextAnimationActivity.this.mMaxLenth;
                textView.setText(textTotalSize2 + "/" + i);
            }
        });
        getBinding().tvFontlimitInAttr.setText("0/" + this.mMaxLenth);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$0(TextAnimationActivity textAnimationActivity, int i) {
        textAnimationActivity.mTextColor = i;
        textAnimationActivity.mGradientColor = -1;
        RecyclerAdapter<Integer> recyclerAdapter = textAnimationActivity.mColorAdapter;
        if (recyclerAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mColorAdapter");
            recyclerAdapter = null;
        }
        recyclerAdapter.notifyDataSetChanged();
        textAnimationActivity.createGif(textAnimationActivity.mEffectPosition, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initView$lambda$1(final TextAnimationActivity textAnimationActivity, SeekBar it) {
        Intrinsics.checkNotNullParameter(it, "it");
        it.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.wifiled.ipixels.ui.imgtxt.TextAnimationActivity$initView$2$1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                int i;
                Intrinsics.checkNotNullParameter(seekBar, "seekBar");
                TextAnimationActivity.this.mAnimationSpeed = 50 - seekBar.getProgress();
                TextAnimationActivity textAnimationActivity2 = TextAnimationActivity.this;
                i = textAnimationActivity2.mEffectPosition;
                textAnimationActivity2.createGif(i, false);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getTextTotalSize() {
        String valueOf = String.valueOf(getBinding().etInput.getText());
        int i = 0;
        for (int i2 = 0; i2 < valueOf.length(); i2++) {
            i = CharacterUtilsKt.isChinese(valueOf.charAt(i2)) ? i + 2 : i + 1;
        }
        return i;
    }

    private final void setRatio(float ratio) {
        ViewGroup.LayoutParams layoutParams = getBinding().gifImg.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
        ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) layoutParams;
        if (AppConfig.INSTANCE.getLedType() == 0 || AppConfig.INSTANCE.getLedType() == 2) {
            layoutParams2.width = ScreenUtil.dp2px(this, 150.0f);
        }
        layoutParams2.dimensionRatio = ratio + ":1";
        int ledType = AppConfig.INSTANCE.getLedType();
        if (ledType == 6) {
            ViewGroup.LayoutParams layoutParams3 = getBinding().imgBg.getLayoutParams();
            Intrinsics.checkNotNull(layoutParams3, "null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
            ((ConstraintLayout.LayoutParams) layoutParams3).dimensionRatio = ratio + ":1";
            getBinding().imgBg.setVisibility(0);
            getBinding().imgBg.setBackgroundResource(R.mipmap.image_text_add_128_bg);
            return;
        }
        if (ledType == 13) {
            ViewGroup.LayoutParams layoutParams4 = getBinding().imgBg.getLayoutParams();
            Intrinsics.checkNotNull(layoutParams4, "null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
            ((ConstraintLayout.LayoutParams) layoutParams4).dimensionRatio = ratio + ":1";
            getBinding().imgBg.setVisibility(0);
            getBinding().imgBg.setBackgroundResource(R.mipmap.diy_32_96_bg);
            return;
        }
        getBinding().imgBg.setVisibility(8);
    }

    private final void initPauseTimeDialog() {
        TextAnimationActivity textAnimationActivity = this;
        this.mPauseTimeAdapter = new IosDialogStyleAdapter<>(textAnimationActivity, this.mPauseTime);
        Dialog dialog = new Dialog(textAnimationActivity, R.style.BottomDialogStyle);
        this.mPauseTimeDialog = dialog;
        Intrinsics.checkNotNull(dialog);
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter = null;
        dialog.setContentView(LayoutInflater.from(textAnimationActivity).inflate(R.layout.dialog_select_media_small, (ViewGroup) null));
        Dialog dialog2 = this.mPauseTimeDialog;
        Intrinsics.checkNotNull(dialog2);
        dialog2.setCanceledOnTouchOutside(true);
        Dialog dialog3 = this.mPauseTimeDialog;
        Intrinsics.checkNotNull(dialog3);
        dialog3.setCancelable(true);
        Dialog dialog4 = this.mPauseTimeDialog;
        Intrinsics.checkNotNull(dialog4);
        Window window = dialog4.getWindow();
        Intrinsics.checkNotNull(window);
        window.getAttributes().width = ScreenUtil.getScreenWidth(textAnimationActivity);
        Dialog dialog5 = this.mPauseTimeDialog;
        Intrinsics.checkNotNull(dialog5);
        Window window2 = dialog5.getWindow();
        Intrinsics.checkNotNull(window2);
        window2.setGravity(80);
        Dialog dialog6 = this.mPauseTimeDialog;
        Intrinsics.checkNotNull(dialog6);
        Window window3 = dialog6.getWindow();
        Intrinsics.checkNotNull(window3);
        window3.setWindowAnimations(R.style.BottomDialogAnimation);
        Dialog dialog7 = this.mPauseTimeDialog;
        Intrinsics.checkNotNull(dialog7);
        TextView textView = (TextView) dialog7.findViewById(R.id.tv_cancel);
        Dialog dialog8 = this.mPauseTimeDialog;
        Intrinsics.checkNotNull(dialog8);
        this.mPauseTimeRecyclerView = (RecyclerView) dialog8.findViewById(R.id.rl_actions);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.imgtxt.TextAnimationActivity$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TextAnimationActivity.initPauseTimeDialog$lambda$3(TextAnimationActivity.this, view);
            }
        });
        RecyclerView recyclerView = this.mPauseTimeRecyclerView;
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(CoreBase.getContext(), 1, false));
        }
        RecyclerView recyclerView2 = this.mPauseTimeRecyclerView;
        if (recyclerView2 != null) {
            recyclerView2.addItemDecoration(new DividerItemDecoration(CoreBase.getContext(), 1));
        }
        RecyclerView recyclerView3 = this.mPauseTimeRecyclerView;
        if (recyclerView3 != null) {
            IosDialogStyleAdapter<Object> iosDialogStyleAdapter2 = this.mPauseTimeAdapter;
            if (iosDialogStyleAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mPauseTimeAdapter");
                iosDialogStyleAdapter2 = null;
            }
            recyclerView3.setAdapter(iosDialogStyleAdapter2);
        }
        RecyclerView recyclerView4 = this.mPauseTimeRecyclerView;
        if (recyclerView4 != null) {
            recyclerView4.post(new Runnable() { // from class: com.wifiled.ipixels.ui.imgtxt.TextAnimationActivity$$ExternalSyntheticLambda6
                @Override // java.lang.Runnable
                public final void run() {
                    TextAnimationActivity.initPauseTimeDialog$lambda$4(TextAnimationActivity.this);
                }
            });
        }
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter3 = this.mPauseTimeAdapter;
        if (iosDialogStyleAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPauseTimeAdapter");
        } else {
            iosDialogStyleAdapter = iosDialogStyleAdapter3;
        }
        iosDialogStyleAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() { // from class: com.wifiled.ipixels.ui.imgtxt.TextAnimationActivity$$ExternalSyntheticLambda7
            @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter.OnItemClickListener
            public final void onItemClick(ViewGroup viewGroup, View view, Object obj, int i) {
                TextAnimationActivity.initPauseTimeDialog$lambda$5(TextAnimationActivity.this, viewGroup, view, obj, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initPauseTimeDialog$lambda$3(TextAnimationActivity textAnimationActivity, View view) {
        Dialog dialog = textAnimationActivity.mPauseTimeDialog;
        Intrinsics.checkNotNull(dialog);
        dialog.cancel();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initPauseTimeDialog$lambda$4(TextAnimationActivity textAnimationActivity) {
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter = textAnimationActivity.mPauseTimeAdapter;
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter2 = null;
        if (iosDialogStyleAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPauseTimeAdapter");
            iosDialogStyleAdapter = null;
        }
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter3 = textAnimationActivity.mPauseTimeAdapter;
        if (iosDialogStyleAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPauseTimeAdapter");
        } else {
            iosDialogStyleAdapter2 = iosDialogStyleAdapter3;
        }
        int itemCount = iosDialogStyleAdapter2.getItemCount();
        RecyclerView recyclerView = textAnimationActivity.mPauseTimeRecyclerView;
        Intrinsics.checkNotNull(recyclerView);
        iosDialogStyleAdapter.adaptiveRecyclerViewHeight(itemCount, recyclerView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initPauseTimeDialog$lambda$5(TextAnimationActivity textAnimationActivity, ViewGroup viewGroup, View view, Object obj, int i) {
        Dialog dialog = textAnimationActivity.mPauseTimeDialog;
        Intrinsics.checkNotNull(dialog);
        dialog.cancel();
        textAnimationActivity.getBinding().tvTextPaauseTime.setText(textAnimationActivity.mPauseTime.get(i));
        String str = textAnimationActivity.mPauseTime.get(i);
        Intrinsics.checkNotNullExpressionValue(str, "get(...)");
        textAnimationActivity.mPauseTimeGif = Integer.parseInt(StringsKt.replace$default(str, ExifInterface.LATITUDE_SOUTH, "", false, 4, (Object) null)) * 1000;
        textAnimationActivity.createGif(textAnimationActivity.mEffectPosition, false);
    }

    private final void initEffectAdapter() {
        this.mTAEffectData = CollectionsKt.listOf((Object[]) new TextAnimationEffectBean[]{new TextAnimationEffectBean(R.drawable.ta_1, true), new TextAnimationEffectBean(R.drawable.ta_2, false), new TextAnimationEffectBean(R.drawable.ta_3, false), new TextAnimationEffectBean(R.drawable.ta_4, false), new TextAnimationEffectBean(R.drawable.ta_5, false), new TextAnimationEffectBean(R.drawable.ta_6, false), new TextAnimationEffectBean(R.drawable.ta_7, false), new TextAnimationEffectBean(R.drawable.ta_8, false)});
        TextAnimationEffectAdapter textAnimationEffectAdapter = new TextAnimationEffectAdapter(R.layout.text_animation_effect_item);
        this.mTextAnimationEffectAdapter = textAnimationEffectAdapter;
        List<TextAnimationEffectBean> list = this.mTAEffectData;
        TextAnimationEffectAdapter textAnimationEffectAdapter2 = null;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTAEffectData");
            list = null;
        }
        textAnimationEffectAdapter.setList(list);
        getBinding().rvAnimation.setLayoutManager(new GridLayoutManager(this, 4));
        RecyclerView recyclerView = getBinding().rvAnimation;
        TextAnimationEffectAdapter textAnimationEffectAdapter3 = this.mTextAnimationEffectAdapter;
        if (textAnimationEffectAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTextAnimationEffectAdapter");
            textAnimationEffectAdapter3 = null;
        }
        recyclerView.setAdapter(textAnimationEffectAdapter3);
        TextAnimationEffectAdapter textAnimationEffectAdapter4 = this.mTextAnimationEffectAdapter;
        if (textAnimationEffectAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTextAnimationEffectAdapter");
        } else {
            textAnimationEffectAdapter2 = textAnimationEffectAdapter4;
        }
        textAnimationEffectAdapter2.setOnItemClickListener(new OnItemClickListener() { // from class: com.wifiled.ipixels.ui.imgtxt.TextAnimationActivity$initEffectAdapter$1
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public void onItemClick(BaseQuickAdapter<?, ?> adapter, View view, int position) {
                Intrinsics.checkNotNullParameter(adapter, "adapter");
                Intrinsics.checkNotNullParameter(view, "view");
                TextAnimationActivity.this.createGif(position, false);
            }
        });
    }

    private final void initColorAdapter() {
        final List<Integer> listOf = CollectionsKt.listOf((Object[]) new Integer[]{Integer.valueOf(R.drawable.text_colour_1), Integer.valueOf(R.drawable.text_colour_2), Integer.valueOf(R.drawable.text_colour_3), Integer.valueOf(R.drawable.text_colour_4), Integer.valueOf(R.drawable.text_colour_5), Integer.valueOf(R.drawable.text_colour_6), Integer.valueOf(R.drawable.text_colour_7), Integer.valueOf(R.drawable.text_colour_8)});
        this.mColorsData = listOf;
        RecyclerAdapter<Integer> recyclerAdapter = null;
        if (listOf == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mColorsData");
            listOf = null;
        }
        RecyclerAdapter<Integer> recyclerAdapter2 = new RecyclerAdapter<Integer>(listOf) { // from class: com.wifiled.ipixels.ui.imgtxt.TextAnimationActivity$initColorAdapter$1
            @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter
            public /* bridge */ /* synthetic */ void convert(RecyclerViewHolder recyclerViewHolder, Integer num) {
                convert(recyclerViewHolder, num.intValue());
            }

            {
                TextAnimationActivity textAnimationActivity = TextAnimationActivity.this;
            }

            public void convert(RecyclerViewHolder holder, int integer) {
                int i;
                Intrinsics.checkNotNullParameter(holder, "holder");
                View view = holder.getView(R.id.rl_paint_outside_frame);
                View view2 = holder.getView(R.id.colorView);
                i = TextAnimationActivity.this.mGradientColor;
                if (i == getPosition(holder)) {
                    view.setBackgroundResource(R.drawable.icon_paint_color_sel);
                } else {
                    view.setBackgroundResource(R.color.transparent);
                }
                view2.setBackgroundResource(integer);
            }
        };
        this.mColorAdapter = recyclerAdapter2;
        recyclerAdapter2.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() { // from class: com.wifiled.ipixels.ui.imgtxt.TextAnimationActivity$$ExternalSyntheticLambda0
            @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter.OnItemClickListener
            public final void onItemClick(ViewGroup viewGroup, View view, Object obj, int i) {
                TextAnimationActivity.initColorAdapter$lambda$6(TextAnimationActivity.this, viewGroup, view, (Integer) obj, i);
            }
        });
        getBinding().recyclerviewColor.setLayoutManager(new GridLayoutManager(this, 8));
        RecyclerView recyclerView = getBinding().recyclerviewColor;
        RecyclerAdapter<Integer> recyclerAdapter3 = this.mColorAdapter;
        if (recyclerAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mColorAdapter");
        } else {
            recyclerAdapter = recyclerAdapter3;
        }
        recyclerView.setAdapter(recyclerAdapter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initColorAdapter$lambda$6(TextAnimationActivity textAnimationActivity, ViewGroup viewGroup, View view, Integer num, int i) {
        textAnimationActivity.mGradientColor = i;
        RecyclerAdapter<Integer> recyclerAdapter = textAnimationActivity.mColorAdapter;
        if (recyclerAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mColorAdapter");
            recyclerAdapter = null;
        }
        recyclerAdapter.notifyDataSetChanged();
        textAnimationActivity.createGif(textAnimationActivity.mEffectPosition, false);
    }

    private final void initBaseColorAdapter() {
        this.mColorsData = CollectionsKt.listOf((Object[]) new Integer[]{Integer.valueOf(R.drawable.text_colour_bc_1), Integer.valueOf(R.drawable.text_colour_bc_2), Integer.valueOf(R.drawable.text_colour_bc_3), Integer.valueOf(R.drawable.text_colour_bc_4), Integer.valueOf(R.drawable.text_colour_bc_5), Integer.valueOf(R.drawable.text_colour_bc_6), Integer.valueOf(R.drawable.text_colour_bc_7)});
        this.mSingleColorsData = CollectionsKt.listOf((Object[]) new Integer[]{Integer.valueOf(R.drawable.text_colorblock_1), Integer.valueOf(R.drawable.text_colorblock_2), Integer.valueOf(R.drawable.text_colorblock_3), Integer.valueOf(R.drawable.text_colorblock_4), Integer.valueOf(R.drawable.text_colorblock_5), Integer.valueOf(R.drawable.text_colorblock_6), Integer.valueOf(R.drawable.text_colorblock_7)});
        final List<Integer> list = this.mColorsData;
        RecyclerAdapter<Integer> recyclerAdapter = null;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mColorsData");
            list = null;
        }
        RecyclerAdapter<Integer> recyclerAdapter2 = new RecyclerAdapter<Integer>(list) { // from class: com.wifiled.ipixels.ui.imgtxt.TextAnimationActivity$initBaseColorAdapter$1
            @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter
            public /* bridge */ /* synthetic */ void convert(RecyclerViewHolder recyclerViewHolder, Integer num) {
                convert(recyclerViewHolder, num.intValue());
            }

            {
                TextAnimationActivity textAnimationActivity = TextAnimationActivity.this;
            }

            public void convert(RecyclerViewHolder holder, int integer) {
                int i;
                Intrinsics.checkNotNullParameter(holder, "holder");
                View view = holder.getView(R.id.rl_paint_outside_frame);
                View view2 = holder.getView(R.id.colorView);
                i = TextAnimationActivity.this.mGradientColor;
                if (i == getPosition(holder)) {
                    view.setBackgroundResource(R.drawable.icon_paint_color_sel);
                } else {
                    view.setBackgroundResource(R.color.transparent);
                }
                view2.setBackgroundResource(integer);
            }
        };
        this.mColorAdapter = recyclerAdapter2;
        recyclerAdapter2.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() { // from class: com.wifiled.ipixels.ui.imgtxt.TextAnimationActivity$$ExternalSyntheticLambda2
            @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter.OnItemClickListener
            public final void onItemClick(ViewGroup viewGroup, View view, Object obj, int i) {
                TextAnimationActivity.initBaseColorAdapter$lambda$7(TextAnimationActivity.this, viewGroup, view, (Integer) obj, i);
            }
        });
        TextAnimationActivity textAnimationActivity = this;
        getBinding().recyclerviewColorBcGradation.setLayoutManager(new GridLayoutManager(textAnimationActivity, 7));
        RecyclerView recyclerView = getBinding().recyclerviewColorBcGradation;
        RecyclerAdapter<Integer> recyclerAdapter3 = this.mColorAdapter;
        if (recyclerAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mColorAdapter");
            recyclerAdapter3 = null;
        }
        recyclerView.setAdapter(recyclerAdapter3);
        final List<Integer> list2 = this.mSingleColorsData;
        if (list2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mSingleColorsData");
            list2 = null;
        }
        RecyclerAdapter<Integer> recyclerAdapter4 = new RecyclerAdapter<Integer>(list2) { // from class: com.wifiled.ipixels.ui.imgtxt.TextAnimationActivity$initBaseColorAdapter$3
            @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter
            public /* bridge */ /* synthetic */ void convert(RecyclerViewHolder recyclerViewHolder, Integer num) {
                convert(recyclerViewHolder, num.intValue());
            }

            {
                TextAnimationActivity textAnimationActivity2 = TextAnimationActivity.this;
            }

            public void convert(RecyclerViewHolder holder, int integer) {
                int i;
                Intrinsics.checkNotNullParameter(holder, "holder");
                View view = holder.getView(R.id.rl_paint_outside_frame);
                View view2 = holder.getView(R.id.colorView);
                i = TextAnimationActivity.this.mSingleColor;
                if (i == getPosition(holder)) {
                    view.setBackgroundResource(R.drawable.text_colorblock_select);
                } else {
                    view.setBackgroundResource(R.color.transparent);
                }
                view2.setBackgroundResource(integer);
            }
        };
        this.mSingleColorAdapter = recyclerAdapter4;
        recyclerAdapter4.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() { // from class: com.wifiled.ipixels.ui.imgtxt.TextAnimationActivity$$ExternalSyntheticLambda3
            @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter.OnItemClickListener
            public final void onItemClick(ViewGroup viewGroup, View view, Object obj, int i) {
                TextAnimationActivity.initBaseColorAdapter$lambda$8(TextAnimationActivity.this, viewGroup, view, (Integer) obj, i);
            }
        });
        getBinding().recyclerviewColorBc.setLayoutManager(new GridLayoutManager(textAnimationActivity, 7));
        RecyclerView recyclerView2 = getBinding().recyclerviewColorBc;
        RecyclerAdapter<Integer> recyclerAdapter5 = this.mSingleColorAdapter;
        if (recyclerAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mSingleColorAdapter");
        } else {
            recyclerAdapter = recyclerAdapter5;
        }
        recyclerView2.setAdapter(recyclerAdapter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initBaseColorAdapter$lambda$7(TextAnimationActivity textAnimationActivity, ViewGroup viewGroup, View view, Integer num, int i) {
        textAnimationActivity.mGradientColor = i + 10;
        textAnimationActivity.mSingleColor = -1;
        RecyclerAdapter<Integer> recyclerAdapter = textAnimationActivity.mColorAdapter;
        RecyclerAdapter<Integer> recyclerAdapter2 = null;
        if (recyclerAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mColorAdapter");
            recyclerAdapter = null;
        }
        recyclerAdapter.notifyDataSetChanged();
        RecyclerAdapter<Integer> recyclerAdapter3 = textAnimationActivity.mSingleColorAdapter;
        if (recyclerAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mSingleColorAdapter");
        } else {
            recyclerAdapter2 = recyclerAdapter3;
        }
        recyclerAdapter2.notifyDataSetChanged();
        textAnimationActivity.createGif(textAnimationActivity.mEffectPosition, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initBaseColorAdapter$lambda$8(TextAnimationActivity textAnimationActivity, ViewGroup viewGroup, View view, Integer num, int i) {
        int parseColor;
        textAnimationActivity.mSingleColor = i;
        textAnimationActivity.mGradientColor = -1;
        RecyclerAdapter<Integer> recyclerAdapter = textAnimationActivity.mColorAdapter;
        RecyclerAdapter<Integer> recyclerAdapter2 = null;
        if (recyclerAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mColorAdapter");
            recyclerAdapter = null;
        }
        recyclerAdapter.notifyDataSetChanged();
        RecyclerAdapter<Integer> recyclerAdapter3 = textAnimationActivity.mSingleColorAdapter;
        if (recyclerAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mSingleColorAdapter");
        } else {
            recyclerAdapter2 = recyclerAdapter3;
        }
        recyclerAdapter2.notifyDataSetChanged();
        switch (textAnimationActivity.mSingleColor) {
            case 0:
                parseColor = Color.parseColor("#FF0000");
                break;
            case 1:
                parseColor = Color.parseColor("#00FF00");
                break;
            case 2:
                parseColor = Color.parseColor("#0000FF");
                break;
            case 3:
                parseColor = Color.parseColor("#00FFFF");
                break;
            case 4:
                parseColor = Color.parseColor("#FFFF00");
                break;
            case 5:
                parseColor = Color.parseColor("#FF00FF");
                break;
            case 6:
                parseColor = Color.parseColor("#FFFFFF");
                break;
            default:
                parseColor = Color.parseColor("#FFFFFF");
                break;
        }
        textAnimationActivity.mTextColor = parseColor;
        textAnimationActivity.createGif(textAnimationActivity.mEffectPosition, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void createGif(int position, boolean isSave) {
        TextAnimationEffectAdapter textAnimationEffectAdapter = this.mTextAnimationEffectAdapter;
        TextAnimationEffectAdapter textAnimationEffectAdapter2 = null;
        if (textAnimationEffectAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTextAnimationEffectAdapter");
            textAnimationEffectAdapter = null;
        }
        textAnimationEffectAdapter.getData().get(this.mEffectPosition).setSelect(false);
        TextAnimationEffectAdapter textAnimationEffectAdapter3 = this.mTextAnimationEffectAdapter;
        if (textAnimationEffectAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTextAnimationEffectAdapter");
            textAnimationEffectAdapter3 = null;
        }
        textAnimationEffectAdapter3.notifyItemChanged(this.mEffectPosition);
        TextAnimationEffectAdapter textAnimationEffectAdapter4 = this.mTextAnimationEffectAdapter;
        if (textAnimationEffectAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTextAnimationEffectAdapter");
            textAnimationEffectAdapter4 = null;
        }
        textAnimationEffectAdapter4.getData().get(position).setSelect(true);
        this.mEffectPosition = position;
        TextAnimationEffectAdapter textAnimationEffectAdapter5 = this.mTextAnimationEffectAdapter;
        if (textAnimationEffectAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTextAnimationEffectAdapter");
        } else {
            textAnimationEffectAdapter2 = textAnimationEffectAdapter5;
        }
        textAnimationEffectAdapter2.notifyItemChanged(this.mEffectPosition);
        String replace$default = StringsKt.replace$default(String.valueOf(getBinding().etInput.getText()), "\n", "", false, 4, (Object) null);
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        if (replace$default.length() == 0) {
            runOnUiThread(new Runnable() { // from class: com.wifiled.ipixels.ui.imgtxt.TextAnimationActivity$$ExternalSyntheticLambda4
                @Override // java.lang.Runnable
                public final void run() {
                    TextAnimationActivity.createGif$lambda$9(TextAnimationActivity.this);
                }
            });
            return;
        }
        Ref.IntRef intRef = new Ref.IntRef();
        Ref.IntRef intRef2 = new Ref.IntRef();
        intRef2.element = 12;
        Ref.IntRef intRef3 = new Ref.IntRef();
        intRef3.element = 64;
        switch (AppConfig.INSTANCE.getLedType()) {
            case 1:
                intRef2.element = 18;
                intRef3.element = 96;
                break;
            case 2:
                intRef2.element = 6;
                intRef3.element = 32;
                break;
            case 4:
                intRef2.element = 6;
                intRef3.element = 32;
                break;
            case 6:
            case 12:
                intRef2.element = 12;
                intRef3.element = 128;
                break;
            case 7:
                intRef2.element = 27;
                intRef3.element = 144;
                break;
            case 8:
                intRef2.element = 36;
                intRef3.element = 192;
                break;
            case 9:
                intRef2.element = 4;
                intRef3.element = 48;
                break;
            case 10:
                intRef2.element = 6;
                intRef3.element = 64;
                break;
            case 11:
            case 13:
                intRef2.element = 10;
                intRef3.element = 96;
                break;
            case 14:
                intRef2.element = 16;
                intRef3.element = Opcodes.IF_ICMPNE;
                break;
            case 15:
                intRef2.element = 18;
                intRef3.element = 192;
                break;
            case 16:
                intRef2.element = 26;
                intRef3.element = 256;
                break;
            case 17:
                intRef2.element = 32;
                intRef3.element = 320;
                break;
            case 18:
                intRef2.element = 36;
                intRef3.element = MLKEMEngine.KyberPolyBytes;
                break;
            case 19:
                intRef2.element = 40;
                intRef3.element = 448;
                break;
        }
        UtilsExtensionKt.showLoadingDialog$default((Activity) this, true, (String) null, false, 6, (Object) null);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), null, new TextAnimationActivity$createGif$2(replace$default, intRef, intRef2, sb, arrayList, position, this, intRef3, isSave, null), 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void createGif$lambda$9(TextAnimationActivity textAnimationActivity) {
        ToastUtil.show(textAnimationActivity.getString(R.string.enter_text));
    }

    private final void initToolBar() {
        getBinding().tvTitle.setText(getString(R.string.img_txt_combination));
        String saveLanguage = LanguageUtil.getSaveLanguage(App.INSTANCE.getContext());
        Intrinsics.checkNotNullExpressionValue(saveLanguage, "getSaveLanguage(...)");
        if (StringsKt.contains$default((CharSequence) saveLanguage, (CharSequence) "ar", false, 2, (Object) null)) {
            getBinding().ivBack.setBackgroundResource(R.mipmap.icon_back_ar);
        } else {
            getBinding().ivBack.setBackgroundResource(R.mipmap.icon_back);
        }
        getBinding().ivBack.setOnClickListener(this);
        CustomImageView.Companion companion = CustomImageView.INSTANCE;
        CustomImageView ivBack = getBinding().ivBack;
        Intrinsics.checkNotNullExpressionValue(ivBack, "ivBack");
        companion.attachViewOnTouchListener(ivBack);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        if (Intrinsics.areEqual(v, getBinding().ivBack)) {
            finish();
            return;
        }
        if (Intrinsics.areEqual(v, getBinding().tvTextPaauseTime) || Intrinsics.areEqual(v, getBinding().ivTextPause)) {
            Dialog dialog = this.mPauseTimeDialog;
            if (dialog != null) {
                dialog.show();
                return;
            }
            return;
        }
        if (Intrinsics.areEqual(v, getBinding().textDirectionHorizontal)) {
            this.mTextDirection = 1;
            getBinding().textDirectionHorizontal.setSelected(true);
            getBinding().textDirectionVertical.setSelected(false);
            createGif(this.mEffectPosition, false);
            return;
        }
        if (Intrinsics.areEqual(v, getBinding().textDirectionVertical)) {
            this.mTextDirection = 2;
            getBinding().textDirectionHorizontal.setSelected(false);
            getBinding().textDirectionVertical.setSelected(true);
            createGif(this.mEffectPosition, false);
            return;
        }
        if (Intrinsics.areEqual(v, getBinding().ivTextSend)) {
            if (this.mEffectPosition == -1) {
                ToastUtil.show(getString(R.string.tip_no_select_at));
                return;
            }
            if (this.mGifPath.length() > 0) {
                Intent intent = new Intent();
                intent.putExtra("gif_path", this.mGifPath);
                setResult(-1, intent);
                finish();
                return;
            }
            createGif(this.mEffectPosition, true);
        }
    }

    static /* synthetic */ void saveFramesAsGif2$default(TextAnimationActivity textAnimationActivity, List list, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        textAnimationActivity.saveFramesAsGif2(list, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void saveFramesAsGif2(List<SaveGifData> frames, boolean isSave) {
        this.mGifPath = GifCore.INSTANCE.bitmaps2gifTA2(frames);
        runOnUiThread(new Runnable() { // from class: com.wifiled.ipixels.ui.imgtxt.TextAnimationActivity$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                TextAnimationActivity.saveFramesAsGif2$lambda$10(TextAnimationActivity.this);
            }
        });
        if (isSave) {
            Intent intent = new Intent();
            intent.putExtra("gif_path", this.mGifPath);
            setResult(-1, intent);
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void saveFramesAsGif2$lambda$10(TextAnimationActivity textAnimationActivity) {
        UtilsExtensionKt.showLoadingDialog$default((Activity) textAnimationActivity, false, (String) null, false, 6, (Object) null);
        Glide.with((FragmentActivity) textAnimationActivity).load(textAnimationActivity.mGifPath).into(textAnimationActivity.getBinding().gifImg);
    }
}
