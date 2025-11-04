package com.wifiled.ipixels.ui.text;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.FileIOUtils;
import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.wifiled.baselib.CoreBase;
import com.wifiled.baselib.app.language.LanguageUtil;
import com.wifiled.baselib.base.recycleview.RecyclerAdapter;
import com.wifiled.baselib.base.recycleview.RecyclerViewHolder;
import com.wifiled.baselib.uicode.base.UiBaseActivity;
import com.wifiled.baselib.utils.SPUtils;
import com.wifiled.baselib.utils.ScreenUtil;
import com.wifiled.baselib.utils.ToastUtil;
import com.wifiled.ipixels.App;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.UtilsExtensionKt;
import com.wifiled.ipixels.core.SendCore;
import com.wifiled.ipixels.core.text.CharacterUtilsKt;
import com.wifiled.ipixels.databinding.ActivityCreativeTextBinding;
import com.wifiled.ipixels.ui.adapter.IosDialogStyleAdapter;
import com.wifiled.ipixels.ui.channel.ChannelIndex;
import com.wifiled.ipixels.ui.channel.ChannelListItem;
import com.wifiled.ipixels.ui.imgtxt.TextAnimationEffectAdapter;
import com.wifiled.ipixels.ui.imgtxt.TextAnimationEffectBean;
import com.wifiled.ipixels.ui.imgtxt.TextAnimationModel;
import com.wifiled.ipixels.ui.subzone.TextBorderData;
import com.wifiled.ipixels.ui.text.CreativeTextBgBorderDialog;
import com.wifiled.ipixels.view.ColorBarView;
import com.wifiled.ipixels.view.customview.CustomImageView;
import com.wifiled.musiclib.player.constant.PlayerFinal;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: CreativeTextActivity.kt */
@Metadata(d1 = {"\u0000¦\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0004B\u0007¢\u0006\u0004\b\u0005\u0010\u0006J\u0012\u0010-\u001a\u00020.2\b\u0010/\u001a\u0004\u0018\u000100H\u0016J\b\u00101\u001a\u00020.H\u0002J\b\u00102\u001a\u00020\u000bH\u0002J\b\u00103\u001a\u00020.H\u0002J\u0010\u00104\u001a\u00020.2\u0006\u00105\u001a\u00020\u0014H\u0002J\b\u00106\u001a\u00020.H\u0016J\b\u00107\u001a\u00020.H\u0002J\b\u00108\u001a\u00020\u0003H\u0016J\b\u00109\u001a\u00020\u0002H\u0016J\u000e\u0010:\u001a\b\u0012\u0004\u0012\u00020<0;H\u0002J\b\u0010=\u001a\u00020.H\u0002J\u000e\u0010>\u001a\b\u0012\u0004\u0012\u00020<0;H\u0002J\b\u0010?\u001a\u00020.H\u0002J@\u0010@\u001a\u00020.2\u0006\u0010A\u001a\u00020\u00172\u0006\u0010B\u001a\u00020\u000b2\f\u0010C\u001a\b\u0012\u0004\u0012\u00020<0\b2\u0018\u0010D\u001a\u0014\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020.0EH\u0002J\u0010\u0010F\u001a\u00020.2\u0006\u0010G\u001a\u00020HH\u0016J\b\u0010I\u001a\u00020.H\u0002J\b\u0010J\u001a\u00020.H\u0002J\u001a\u0010K\u001a\u00020.2\u0006\u0010C\u001a\u00020*2\b\b\u0002\u0010L\u001a\u00020\u0019H\u0002J\u001a\u0010M\u001a\u00020.2\u0006\u0010C\u001a\u00020*2\b\b\u0002\u0010L\u001a\u00020\u0019H\u0002J\u0018\u0010R\u001a\u00020.2\u0006\u0010S\u001a\u00020\u000b2\u0006\u0010T\u001a\u00020\u0019H\u0002J\b\u0010U\u001a\u00020.H\u0002R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\bX\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\rX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00170\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010!\u001a\b\u0012\u0004\u0012\u00020\u000b0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010$\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010&0%X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010'\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010(\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010&0%X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010)\u001a\u0004\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020,X\u0082.¢\u0006\u0002\n\u0000R\u001f\u0010N\u001a\u0013\u0012\u0004\u0012\u00020P\u0012\u0004\u0012\u00020.0O¢\u0006\u0002\bQX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006V"}, d2 = {"Lcom/wifiled/ipixels/ui/text/CreativeTextActivity;", "Lcom/wifiled/baselib/uicode/base/UiBaseActivity;", "Lcom/wifiled/ipixels/ui/imgtxt/TextAnimationModel;", "Lcom/wifiled/ipixels/databinding/ActivityCreativeTextBinding;", "Landroid/view/View$OnClickListener;", "<init>", "()V", "mTAEffectData", "", "Lcom/wifiled/ipixels/ui/imgtxt/TextAnimationEffectBean;", "mColorsData", "", "mColorAdapter", "Lcom/wifiled/baselib/base/recycleview/RecyclerAdapter;", "mGradientColor", "mTextColor", "mMaxLenth", "mSelectBg", "mSelectBorder", "mAnimationSpeed", "", "mEffectPosition", "mGifPath", "", "mSendGifIsDown", "", "mPauseTimeDialog", "Landroid/app/Dialog;", "mTextDuration", "mPauseTimeRecyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "mFontSizeRecyclerView", "mPauseTime", "mFontSizeList", "mSelectFontSize", "mSendText", "mTextSizeAdapter", "Lcom/wifiled/ipixels/ui/adapter/IosDialogStyleAdapter;", "", "mTextSizeDialog", "mPauseTimeAdapter", "mSendGifData", "", "mTextAnimationEffectAdapter", "Lcom/wifiled/ipixels/ui/imgtxt/TextAnimationEffectAdapter;", "initView", "", "savedInstanceState", "Landroid/os/Bundle;", "initPauseTimeDialog", "getTextTotalSize", "initToolbar", "setRatio", "ratio", "initData", "initEffectAdapter", "getViewBinding", "initViewModel", "buildBorderData", "", "Lcom/wifiled/ipixels/ui/subzone/TextBorderData;", "showBorderDialog", "buildBgData", "showBgDialog", "showDialog", "tag", "titleResId", "data", "onSubmit", "Lkotlin/Function2;", "onClick", "v", "Landroid/view/View;", "initFontSizeDialog", "sendData", "sendGif", "isDown", "sendGif2", "sendGifCallBack", "Lkotlin/Function1;", "Lcom/wifiled/ipixels/core/SendCore$CallbackBuilder;", "Lkotlin/ExtensionFunctionType;", "createGif", PlayerFinal.PLAYER_POSITION, "isSave", "initColorAdapter", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CreativeTextActivity extends UiBaseActivity<TextAnimationModel, ActivityCreativeTextBinding> implements View.OnClickListener {
    private RecyclerAdapter<Integer> mColorAdapter;
    private List<Integer> mColorsData;
    private int mEffectPosition;
    private RecyclerView mFontSizeRecyclerView;
    private IosDialogStyleAdapter<Object> mPauseTimeAdapter;
    private Dialog mPauseTimeDialog;
    private RecyclerView mPauseTimeRecyclerView;
    private int mSelectBg;
    private int mSelectBorder;
    private int mSelectFontSize;
    private byte[] mSendGifData;
    private List<TextAnimationEffectBean> mTAEffectData;
    private TextAnimationEffectAdapter mTextAnimationEffectAdapter;
    private IosDialogStyleAdapter<Object> mTextSizeAdapter;
    private Dialog mTextSizeDialog;
    private int mGradientColor = -1;
    private int mTextColor = -1;
    private int mMaxLenth = 32;
    private float mAnimationSpeed = 150.0f;
    private String mGifPath = "";
    private boolean mSendGifIsDown = true;
    private int mTextDuration = 1;
    private final List<String> mPauseTime = CollectionsKt.listOf((Object[]) new String[]{"1S", "2S", "3S", "4S", "5S"});
    private final List<Integer> mFontSizeList = CollectionsKt.listOf((Object[]) new Integer[]{16, 32});
    private String mSendText = "";
    private Function1<? super SendCore.CallbackBuilder, Unit> sendGifCallBack = new Function1() { // from class: com.wifiled.ipixels.ui.text.CreativeTextActivity$$ExternalSyntheticLambda20
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            Unit sendGifCallBack$lambda$28;
            sendGifCallBack$lambda$28 = CreativeTextActivity.sendGifCallBack$lambda$28(CreativeTextActivity.this, (SendCore.CallbackBuilder) obj);
            return sendGifCallBack$lambda$28;
        }
    };

    @Override // com.wifiled.baselib.uicode.base.UiBaseActivity, com.wifiled.baselib.uicode.inner.IBaseView
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        switch (AppConfig.INSTANCE.getLedType()) {
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
                setRatio(10.0f);
                break;
            case 18:
                setRatio(12.0f);
                break;
            case 19:
                setRatio(14.0f);
                break;
        }
        initToolbar();
        this.mMaxLenth = (AppConfig.INSTANCE.getLedSize().get(0).intValue() / AppConfig.INSTANCE.getLedSize().get(1).intValue()) * 4;
        if (AppConfig.INSTANCE.getLedSize().get(1).intValue() < 32) {
            getBinding().clTextSizeSetBc.setVisibility(8);
        }
        CreativeTextActivity creativeTextActivity = this;
        getBinding().borderBg.setOnClickListener(creativeTextActivity);
        getBinding().ivTextMode.setOnClickListener(creativeTextActivity);
        getBinding().animationBg.setOnClickListener(creativeTextActivity);
        getBinding().ivTextSend.setOnClickListener(creativeTextActivity);
        getBinding().tvTextPaauseTime.setOnClickListener(creativeTextActivity);
        getBinding().ivTextPause.setOnClickListener(creativeTextActivity);
        getBinding().clTextSizeSetBc.setOnClickListener(creativeTextActivity);
        getBinding().clTextBgSet.setOnClickListener(creativeTextActivity);
        getBinding().clTextBorderSet.setOnClickListener(creativeTextActivity);
        getBinding().clTextPauseSet.setOnClickListener(creativeTextActivity);
        getBinding().colorBarViewText.setOnColorChangerListener(new ColorBarView.OnColorChangeListener() { // from class: com.wifiled.ipixels.ui.text.CreativeTextActivity$$ExternalSyntheticLambda28
            @Override // com.wifiled.ipixels.view.ColorBarView.OnColorChangeListener
            public final void onColorChange(int i) {
                CreativeTextActivity.initView$lambda$0(CreativeTextActivity.this, i);
            }
        });
        initColorAdapter();
        initEffectAdapter();
        initPauseTimeDialog();
        initFontSizeDialog();
        getBinding().etInput.addTextChangedListener(new TextWatcher() { // from class: com.wifiled.ipixels.ui.text.CreativeTextActivity$initView$2
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
                ActivityCreativeTextBinding binding;
                ActivityCreativeTextBinding binding2;
                int textTotalSize;
                int i;
                ActivityCreativeTextBinding binding3;
                int textTotalSize2;
                int i2;
                ActivityCreativeTextBinding binding4;
                Intrinsics.checkNotNullParameter(s, "s");
                binding = CreativeTextActivity.this.getBinding();
                this.selectionStart = binding.etInput.getSelectionStart();
                binding2 = CreativeTextActivity.this.getBinding();
                this.selectionEnd = binding2.etInput.getSelectionEnd();
                textTotalSize = CreativeTextActivity.this.getTextTotalSize();
                i = CreativeTextActivity.this.mMaxLenth;
                if (textTotalSize > i) {
                    s.delete(this.selectionStart - 1, this.selectionEnd);
                    binding4 = CreativeTextActivity.this.getBinding();
                    binding4.etInput.setText(s);
                }
                binding3 = CreativeTextActivity.this.getBinding();
                TextView textView = binding3.tvFontlimitInAttr;
                textTotalSize2 = CreativeTextActivity.this.getTextTotalSize();
                i2 = CreativeTextActivity.this.mMaxLenth;
                textView.setText(textTotalSize2 + "/" + i2);
            }
        });
        SeekBar sbSpeed = getBinding().sbSpeed;
        Intrinsics.checkNotNullExpressionValue(sbSpeed, "sbSpeed");
        UtilsExtensionKt.setOnSeekBarStopChangeListener(sbSpeed, new Function1() { // from class: com.wifiled.ipixels.ui.text.CreativeTextActivity$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit initView$lambda$1;
                initView$lambda$1 = CreativeTextActivity.initView$lambda$1(CreativeTextActivity.this, (SeekBar) obj);
                return initView$lambda$1;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$0(CreativeTextActivity creativeTextActivity, int i) {
        creativeTextActivity.mTextColor = i;
        creativeTextActivity.mGradientColor = -1;
        RecyclerAdapter<Integer> recyclerAdapter = creativeTextActivity.mColorAdapter;
        if (recyclerAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mColorAdapter");
            recyclerAdapter = null;
        }
        recyclerAdapter.notifyDataSetChanged();
        creativeTextActivity.createGif(creativeTextActivity.mEffectPosition, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initView$lambda$1(final CreativeTextActivity creativeTextActivity, SeekBar it) {
        Intrinsics.checkNotNullParameter(it, "it");
        it.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.wifiled.ipixels.ui.text.CreativeTextActivity$initView$3$1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                float f;
                int i;
                Intrinsics.checkNotNullParameter(seekBar, "seekBar");
                CreativeTextActivity.this.mAnimationSpeed = ((100 - seekBar.getProgress()) * 3.8f) + 20;
                f = CreativeTextActivity.this.mAnimationSpeed;
                LogUtils.vTag("ruis", "mAnimationSpeed ---" + f);
                CreativeTextActivity creativeTextActivity2 = CreativeTextActivity.this;
                i = creativeTextActivity2.mEffectPosition;
                creativeTextActivity2.createGif(i, false);
            }
        });
        return Unit.INSTANCE;
    }

    private final void initPauseTimeDialog() {
        CreativeTextActivity creativeTextActivity = this;
        this.mPauseTimeAdapter = new IosDialogStyleAdapter<>(creativeTextActivity, this.mPauseTime);
        Dialog dialog = new Dialog(creativeTextActivity, R.style.BottomDialogStyle);
        this.mPauseTimeDialog = dialog;
        Intrinsics.checkNotNull(dialog);
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter = null;
        dialog.setContentView(LayoutInflater.from(creativeTextActivity).inflate(R.layout.dialog_select_media_small, (ViewGroup) null));
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
        window.getAttributes().width = ScreenUtil.getScreenWidth(creativeTextActivity);
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
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.text.CreativeTextActivity$$ExternalSyntheticLambda25
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CreativeTextActivity.initPauseTimeDialog$lambda$2(CreativeTextActivity.this, view);
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
            recyclerView4.post(new Runnable() { // from class: com.wifiled.ipixels.ui.text.CreativeTextActivity$$ExternalSyntheticLambda26
                @Override // java.lang.Runnable
                public final void run() {
                    CreativeTextActivity.initPauseTimeDialog$lambda$3(CreativeTextActivity.this);
                }
            });
        }
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter3 = this.mPauseTimeAdapter;
        if (iosDialogStyleAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPauseTimeAdapter");
        } else {
            iosDialogStyleAdapter = iosDialogStyleAdapter3;
        }
        iosDialogStyleAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() { // from class: com.wifiled.ipixels.ui.text.CreativeTextActivity$$ExternalSyntheticLambda27
            @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter.OnItemClickListener
            public final void onItemClick(ViewGroup viewGroup, View view, Object obj, int i) {
                CreativeTextActivity.initPauseTimeDialog$lambda$4(CreativeTextActivity.this, viewGroup, view, obj, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initPauseTimeDialog$lambda$2(CreativeTextActivity creativeTextActivity, View view) {
        Dialog dialog = creativeTextActivity.mPauseTimeDialog;
        Intrinsics.checkNotNull(dialog);
        dialog.cancel();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initPauseTimeDialog$lambda$3(CreativeTextActivity creativeTextActivity) {
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter = creativeTextActivity.mPauseTimeAdapter;
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter2 = null;
        if (iosDialogStyleAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPauseTimeAdapter");
            iosDialogStyleAdapter = null;
        }
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter3 = creativeTextActivity.mPauseTimeAdapter;
        if (iosDialogStyleAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPauseTimeAdapter");
        } else {
            iosDialogStyleAdapter2 = iosDialogStyleAdapter3;
        }
        int itemCount = iosDialogStyleAdapter2.getItemCount();
        RecyclerView recyclerView = creativeTextActivity.mPauseTimeRecyclerView;
        Intrinsics.checkNotNull(recyclerView);
        iosDialogStyleAdapter.adaptiveRecyclerViewHeight(itemCount, recyclerView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initPauseTimeDialog$lambda$4(CreativeTextActivity creativeTextActivity, ViewGroup viewGroup, View view, Object obj, int i) {
        Dialog dialog = creativeTextActivity.mPauseTimeDialog;
        Intrinsics.checkNotNull(dialog);
        dialog.cancel();
        creativeTextActivity.getBinding().tvTextPaauseTime.setText(creativeTextActivity.mPauseTime.get(i));
        String str = creativeTextActivity.mPauseTime.get(i);
        Intrinsics.checkNotNullExpressionValue(str, "get(...)");
        creativeTextActivity.mTextDuration = Integer.parseInt(StringsKt.replace$default(str, ExifInterface.LATITUDE_SOUTH, "", false, 4, (Object) null));
        creativeTextActivity.createGif(creativeTextActivity.mEffectPosition, false);
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

    private final void initToolbar() {
        String saveLanguage = LanguageUtil.getSaveLanguage(App.INSTANCE.getContext());
        Intrinsics.checkNotNullExpressionValue(saveLanguage, "getSaveLanguage(...)");
        if (StringsKt.contains$default((CharSequence) saveLanguage, (CharSequence) "ar", false, 2, (Object) null)) {
            getBinding().ivBack.setBackgroundResource(R.mipmap.icon_back_ar);
        } else {
            getBinding().ivBack.setBackgroundResource(R.mipmap.icon_back);
        }
        CustomImageView.Companion companion = CustomImageView.INSTANCE;
        CustomImageView ivBack = getBinding().ivBack;
        Intrinsics.checkNotNullExpressionValue(ivBack, "ivBack");
        companion.attachViewOnTouchListener(ivBack);
        getBinding().tvTitle.setText(getString(R.string.creative_text));
        getBinding().ivBack.setOnClickListener(this);
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

    @Override // com.wifiled.baselib.uicode.base.UiBaseActivity, com.wifiled.baselib.uicode.inner.IBaseView
    public void initData() {
        super.initData();
        if (AppConfig.INSTANCE.getLedSize().get(1).intValue() == 20) {
            this.mSelectFontSize = 16;
            getBinding().tvTextSize32Bc.setText("16");
        } else {
            this.mSelectFontSize = AppConfig.INSTANCE.getLedSize().get(1).intValue();
            getBinding().tvTextSize32Bc.setText(String.valueOf(AppConfig.INSTANCE.getLedSize().get(1).intValue()));
        }
        getBinding().tvFontlimitInAttr.setText(getTextTotalSize() + "/" + this.mMaxLenth);
    }

    private final void initEffectAdapter() {
        String saveLanguage = LanguageUtil.getSaveLanguage(App.INSTANCE.getContext());
        Intrinsics.checkNotNullExpressionValue(saveLanguage, "getSaveLanguage(...)");
        TextAnimationEffectAdapter textAnimationEffectAdapter = null;
        if (StringsKt.contains$default((CharSequence) saveLanguage, (CharSequence) "zh", false, 2, (Object) null)) {
            this.mTAEffectData = CollectionsKt.listOf((Object[]) new TextAnimationEffectBean[]{new TextAnimationEffectBean(R.drawable.gif0, true), new TextAnimationEffectBean(R.drawable.gif1, false), new TextAnimationEffectBean(R.drawable.gif2, false), new TextAnimationEffectBean(R.drawable.gif3, false), new TextAnimationEffectBean(R.drawable.gif4, false), new TextAnimationEffectBean(R.drawable.gif5, false), new TextAnimationEffectBean(R.drawable.gif6, false), new TextAnimationEffectBean(R.drawable.gif7, false), new TextAnimationEffectBean(R.drawable.gif8, false), new TextAnimationEffectBean(R.drawable.gif9, false), new TextAnimationEffectBean(R.drawable.gif10, false), new TextAnimationEffectBean(R.drawable.gif11, false)});
        } else {
            this.mTAEffectData = CollectionsKt.listOf((Object[]) new TextAnimationEffectBean[]{new TextAnimationEffectBean(R.drawable.gif_en_0, true), new TextAnimationEffectBean(R.drawable.gif_en_1, false), new TextAnimationEffectBean(R.drawable.gif_en_2, false), new TextAnimationEffectBean(R.drawable.gif_en_3, false), new TextAnimationEffectBean(R.drawable.gif_en_4, false), new TextAnimationEffectBean(R.drawable.gif_en_5, false), new TextAnimationEffectBean(R.drawable.gif_en_6, false), new TextAnimationEffectBean(R.drawable.gif_en_7, false), new TextAnimationEffectBean(R.drawable.gif_en_8, false), new TextAnimationEffectBean(R.drawable.gif_en_9, false), new TextAnimationEffectBean(R.drawable.gif_en_10, false), new TextAnimationEffectBean(R.drawable.gif_en_11, false)});
        }
        TextAnimationEffectAdapter textAnimationEffectAdapter2 = new TextAnimationEffectAdapter(R.layout.text_animation_effect_item);
        this.mTextAnimationEffectAdapter = textAnimationEffectAdapter2;
        List<TextAnimationEffectBean> list = this.mTAEffectData;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTAEffectData");
            list = null;
        }
        textAnimationEffectAdapter2.setList(list);
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
            textAnimationEffectAdapter = textAnimationEffectAdapter4;
        }
        textAnimationEffectAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.wifiled.ipixels.ui.text.CreativeTextActivity$initEffectAdapter$1
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public void onItemClick(BaseQuickAdapter<?, ?> adapter, View view, int position) {
                TextAnimationEffectAdapter textAnimationEffectAdapter5;
                int i;
                TextAnimationEffectAdapter textAnimationEffectAdapter6;
                int i2;
                TextAnimationEffectAdapter textAnimationEffectAdapter7;
                TextAnimationEffectAdapter textAnimationEffectAdapter8;
                int i3;
                int i4;
                Intrinsics.checkNotNullParameter(adapter, "adapter");
                Intrinsics.checkNotNullParameter(view, "view");
                textAnimationEffectAdapter5 = CreativeTextActivity.this.mTextAnimationEffectAdapter;
                TextAnimationEffectAdapter textAnimationEffectAdapter9 = null;
                if (textAnimationEffectAdapter5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mTextAnimationEffectAdapter");
                    textAnimationEffectAdapter5 = null;
                }
                List<TextAnimationEffectBean> data = textAnimationEffectAdapter5.getData();
                i = CreativeTextActivity.this.mEffectPosition;
                data.get(i).setSelect(false);
                textAnimationEffectAdapter6 = CreativeTextActivity.this.mTextAnimationEffectAdapter;
                if (textAnimationEffectAdapter6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mTextAnimationEffectAdapter");
                    textAnimationEffectAdapter6 = null;
                }
                i2 = CreativeTextActivity.this.mEffectPosition;
                textAnimationEffectAdapter6.notifyItemChanged(i2);
                textAnimationEffectAdapter7 = CreativeTextActivity.this.mTextAnimationEffectAdapter;
                if (textAnimationEffectAdapter7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mTextAnimationEffectAdapter");
                    textAnimationEffectAdapter7 = null;
                }
                textAnimationEffectAdapter7.getData().get(position).setSelect(true);
                CreativeTextActivity.this.mEffectPosition = position;
                textAnimationEffectAdapter8 = CreativeTextActivity.this.mTextAnimationEffectAdapter;
                if (textAnimationEffectAdapter8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mTextAnimationEffectAdapter");
                } else {
                    textAnimationEffectAdapter9 = textAnimationEffectAdapter8;
                }
                i3 = CreativeTextActivity.this.mEffectPosition;
                textAnimationEffectAdapter9.notifyItemChanged(i3);
                CreativeTextActivity creativeTextActivity = CreativeTextActivity.this;
                i4 = creativeTextActivity.mEffectPosition;
                creativeTextActivity.createGif(i4, false);
            }
        });
    }

    @Override // com.wifiled.baselib.uicode.base.UiBaseActivity
    public ActivityCreativeTextBinding getViewBinding() {
        ActivityCreativeTextBinding inflate = ActivityCreativeTextBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        return inflate;
    }

    @Override // com.wifiled.baselib.uicode.base.UiBaseActivity
    public TextAnimationModel initViewModel() {
        return (TextAnimationModel) new ViewModelProvider(this).get(TextAnimationModel.class);
    }

    private final List<TextBorderData> buildBorderData() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new TextBorderData(R.drawable.icon_text_border_0, false));
        arrayList.add(new TextBorderData(R.drawable.icon_text_border_6_1, false));
        arrayList.add(new TextBorderData(R.drawable.icon_text_border_6_2, false));
        arrayList.add(new TextBorderData(R.drawable.icon_text_border_6_3, false));
        arrayList.add(new TextBorderData(R.drawable.icon_text_border_6_4, false));
        arrayList.add(new TextBorderData(R.drawable.icon_text_border_6_5, false));
        arrayList.add(new TextBorderData(R.drawable.icon_text_border_6_6, false));
        arrayList.add(new TextBorderData(R.drawable.icon_text_border_6_7, false));
        arrayList.add(new TextBorderData(R.drawable.icon_text_border_6_8, false));
        arrayList.add(new TextBorderData(R.drawable.icon_text_border_6_9, false));
        arrayList.add(new TextBorderData(R.drawable.icon_text_border_6_10, false));
        arrayList.add(new TextBorderData(R.drawable.icon_text_border_6_11, false));
        arrayList.add(new TextBorderData(R.drawable.icon_text_border_6_12, false));
        arrayList.add(new TextBorderData(R.drawable.icon_text_border_6_13, false));
        arrayList.add(new TextBorderData(R.drawable.icon_text_border_6_14, false));
        arrayList.add(new TextBorderData(R.drawable.icon_text_border_6_15, false));
        arrayList.add(new TextBorderData(R.drawable.icon_text_border_6_16, false));
        arrayList.add(new TextBorderData(R.drawable.icon_text_border_6_17, false));
        arrayList.add(new TextBorderData(R.drawable.icon_text_border_6_18, false));
        arrayList.add(new TextBorderData(R.drawable.icon_text_border_6_19, false));
        arrayList.add(new TextBorderData(R.drawable.icon_text_border_6_20, false));
        arrayList.add(new TextBorderData(R.drawable.icon_text_border_6_21, false));
        arrayList.add(new TextBorderData(R.drawable.icon_text_border_6_22, false));
        arrayList.add(new TextBorderData(R.drawable.icon_text_border_6_23, false));
        arrayList.add(new TextBorderData(R.drawable.icon_text_border_6_24, false));
        if (this.mSelectBorder < arrayList.size()) {
            ((TextBorderData) arrayList.get(this.mSelectBorder)).setSelect(true);
            return arrayList;
        }
        ((TextBorderData) arrayList.get(0)).setSelect(true);
        return arrayList;
    }

    private final void showBorderDialog() {
        showDialog("border", R.string.animation_background, buildBorderData(), new Function2() { // from class: com.wifiled.ipixels.ui.text.CreativeTextActivity$$ExternalSyntheticLambda12
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                Unit showBorderDialog$lambda$6;
                showBorderDialog$lambda$6 = CreativeTextActivity.showBorderDialog$lambda$6(CreativeTextActivity.this, ((Integer) obj).intValue(), ((Integer) obj2).intValue());
                return showBorderDialog$lambda$6;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showBorderDialog$lambda$6(CreativeTextActivity creativeTextActivity, int i, int i2) {
        LogUtils.vTag("ruis", "showBorderDialog position-" + i);
        creativeTextActivity.mSelectBorder = i;
        Glide.with((FragmentActivity) creativeTextActivity).load(Integer.valueOf(i2)).into(creativeTextActivity.getBinding().borderBg);
        creativeTextActivity.createGif(creativeTextActivity.mEffectPosition, false);
        return Unit.INSTANCE;
    }

    private final List<TextBorderData> buildBgData() {
        ArrayList arrayList = new ArrayList();
        if (AppConfig.INSTANCE.getLedType() == 19 || AppConfig.INSTANCE.getLedType() == 17 || AppConfig.INSTANCE.getLedType() == 18) {
            arrayList.add(new TextBorderData(R.drawable.icon_text_border_0, false));
            arrayList.add(new TextBorderData(R.drawable.text_ani_bg2_16_96_8, false));
            arrayList.add(new TextBorderData(R.drawable.text_ani_bg2_16_96_7, false));
            arrayList.add(new TextBorderData(R.drawable.text_ani_bg2_16_96_5, false));
            arrayList.add(new TextBorderData(R.drawable.text_ani_bg2_16_96_2, false));
            arrayList.add(new TextBorderData(R.drawable.text_ani_bg2_16_96_6, false));
            arrayList.add(new TextBorderData(R.drawable.text_ani_bg2_16_96_1, false));
            arrayList.add(new TextBorderData(R.drawable.text_ani_bg2_16_96_4, false));
            arrayList.add(new TextBorderData(R.drawable.text_ani_bg2_16_96_3, false));
        } else {
            arrayList.add(new TextBorderData(R.drawable.icon_text_border_0, false));
            arrayList.add(new TextBorderData(R.drawable.text_ani_bg_16_96_1, false));
            arrayList.add(new TextBorderData(R.drawable.text_ani_bg_16_96_2, false));
            arrayList.add(new TextBorderData(R.drawable.text_ani_bg_16_96_3, false));
            arrayList.add(new TextBorderData(R.drawable.text_ani_bg_16_96_4, false));
            arrayList.add(new TextBorderData(R.drawable.text_ani_bg_16_96_5, false));
            arrayList.add(new TextBorderData(R.drawable.text_ani_bg_16_96_6, false));
            arrayList.add(new TextBorderData(R.drawable.text_ani_bg_16_96_7, false));
            arrayList.add(new TextBorderData(R.drawable.text_ani_bg_16_96_8, false));
            arrayList.add(new TextBorderData(R.drawable.text_ani_bg_16_96_9, false));
            arrayList.add(new TextBorderData(R.drawable.text_ani_bg_16_96_10, false));
        }
        if (this.mSelectBg < arrayList.size()) {
            ((TextBorderData) arrayList.get(this.mSelectBg)).setSelect(true);
            return arrayList;
        }
        ((TextBorderData) arrayList.get(0)).setSelect(true);
        return arrayList;
    }

    private final void showBgDialog() {
        showDialog("bg", R.string.animation_background, buildBgData(), new Function2() { // from class: com.wifiled.ipixels.ui.text.CreativeTextActivity$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                Unit showBgDialog$lambda$7;
                showBgDialog$lambda$7 = CreativeTextActivity.showBgDialog$lambda$7(CreativeTextActivity.this, ((Integer) obj).intValue(), ((Integer) obj2).intValue());
                return showBgDialog$lambda$7;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showBgDialog$lambda$7(CreativeTextActivity creativeTextActivity, int i, int i2) {
        LogUtils.vTag("ruis", "showBgDialog position-" + i);
        creativeTextActivity.mSelectBg = i;
        Glide.with((FragmentActivity) creativeTextActivity).load(Integer.valueOf(i2)).into(creativeTextActivity.getBinding().animationBg);
        creativeTextActivity.createGif(creativeTextActivity.mEffectPosition, false);
        return Unit.INSTANCE;
    }

    private final void showDialog(String tag, int titleResId, List<TextBorderData> data, final Function2<? super Integer, ? super Integer, Unit> onSubmit) {
        Fragment findFragmentByTag = getSupportFragmentManager().findFragmentByTag(tag);
        if (findFragmentByTag != null) {
            getSupportFragmentManager().beginTransaction().remove(findFragmentByTag).commitNowAllowingStateLoss();
        }
        CreativeTextBgBorderDialog creativeTextBgBorderDialog = new CreativeTextBgBorderDialog(titleResId, data);
        creativeTextBgBorderDialog.setOnClickListener(new CreativeTextBgBorderDialog.CreativeTextLinstener() { // from class: com.wifiled.ipixels.ui.text.CreativeTextActivity$showDialog$1$1
            @Override // com.wifiled.ipixels.ui.text.CreativeTextBgBorderDialog.CreativeTextLinstener
            public void onCancelClick() {
            }

            @Override // com.wifiled.ipixels.ui.text.CreativeTextBgBorderDialog.CreativeTextLinstener
            public void onSubmitClick(int position, int res) {
                onSubmit.invoke(Integer.valueOf(position), Integer.valueOf(res));
            }
        });
        if (Intrinsics.areEqual(tag, "bg")) {
            creativeTextBgBorderDialog.setPosition(this.mSelectBg);
        } else {
            creativeTextBgBorderDialog.setPosition(this.mSelectBorder);
        }
        creativeTextBgBorderDialog.show(getSupportFragmentManager(), tag);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Dialog dialog;
        Intrinsics.checkNotNullParameter(v, "v");
        if (Intrinsics.areEqual(v, getBinding().ivBack)) {
            finish();
            return;
        }
        if (Intrinsics.areEqual(v, getBinding().borderBg) || Intrinsics.areEqual(v, getBinding().clTextBorderSet)) {
            showBorderDialog();
            return;
        }
        if (Intrinsics.areEqual(v, getBinding().ivTextMode)) {
            startActivity(new Intent(this, (Class<?>) TextActivity.class), ActivityOptions.makeScaleUpAnimation(getBinding().ivTextMode, 0, 0, getBinding().main.getWidth(), getBinding().main.getHeight()).toBundle());
            finish();
            return;
        }
        if (Intrinsics.areEqual(v, getBinding().animationBg) || Intrinsics.areEqual(v, getBinding().clTextBgSet)) {
            showBgDialog();
            return;
        }
        if (Intrinsics.areEqual(v, getBinding().ivTextSend)) {
            if (Intrinsics.areEqual(StringsKt.replace$default(String.valueOf(getBinding().etInput.getText()), "\n", "", false, 4, (Object) null), this.mSendText)) {
                sendData();
                return;
            } else {
                createGif(this.mEffectPosition, true);
                return;
            }
        }
        if (Intrinsics.areEqual(v, getBinding().tvTextPaauseTime) || Intrinsics.areEqual(v, getBinding().ivTextPause) || Intrinsics.areEqual(v, getBinding().clTextPauseSet)) {
            Dialog dialog2 = this.mPauseTimeDialog;
            if (dialog2 != null) {
                dialog2.show();
                return;
            }
            return;
        }
        if (!Intrinsics.areEqual(v, getBinding().clTextSizeSetBc) || (dialog = this.mTextSizeDialog) == null) {
            return;
        }
        dialog.show();
    }

    private final void initFontSizeDialog() {
        CreativeTextActivity creativeTextActivity = this;
        this.mTextSizeAdapter = new IosDialogStyleAdapter<>(creativeTextActivity, this.mFontSizeList);
        Dialog dialog = new Dialog(creativeTextActivity, R.style.BottomDialogStyle);
        this.mTextSizeDialog = dialog;
        Intrinsics.checkNotNull(dialog);
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter = null;
        dialog.setContentView(LayoutInflater.from(creativeTextActivity).inflate(R.layout.dialog_select_media_small, (ViewGroup) null));
        Dialog dialog2 = this.mTextSizeDialog;
        Intrinsics.checkNotNull(dialog2);
        dialog2.setCanceledOnTouchOutside(true);
        Dialog dialog3 = this.mTextSizeDialog;
        Intrinsics.checkNotNull(dialog3);
        dialog3.setCancelable(true);
        Dialog dialog4 = this.mTextSizeDialog;
        Intrinsics.checkNotNull(dialog4);
        Window window = dialog4.getWindow();
        Intrinsics.checkNotNull(window);
        window.getAttributes().width = ScreenUtil.getScreenWidth(creativeTextActivity);
        Dialog dialog5 = this.mTextSizeDialog;
        Intrinsics.checkNotNull(dialog5);
        Window window2 = dialog5.getWindow();
        Intrinsics.checkNotNull(window2);
        window2.setGravity(80);
        Dialog dialog6 = this.mTextSizeDialog;
        Intrinsics.checkNotNull(dialog6);
        Window window3 = dialog6.getWindow();
        Intrinsics.checkNotNull(window3);
        window3.setWindowAnimations(R.style.BottomDialogAnimation);
        Dialog dialog7 = this.mTextSizeDialog;
        Intrinsics.checkNotNull(dialog7);
        TextView textView = (TextView) dialog7.findViewById(R.id.tv_cancel);
        Dialog dialog8 = this.mTextSizeDialog;
        Intrinsics.checkNotNull(dialog8);
        this.mFontSizeRecyclerView = (RecyclerView) dialog8.findViewById(R.id.rl_actions);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.text.CreativeTextActivity$$ExternalSyntheticLambda22
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CreativeTextActivity.initFontSizeDialog$lambda$9(CreativeTextActivity.this, view);
            }
        });
        RecyclerView recyclerView = this.mFontSizeRecyclerView;
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(CoreBase.getContext(), 1, false));
        }
        RecyclerView recyclerView2 = this.mFontSizeRecyclerView;
        if (recyclerView2 != null) {
            recyclerView2.addItemDecoration(new DividerItemDecoration(CoreBase.getContext(), 1));
        }
        RecyclerView recyclerView3 = this.mFontSizeRecyclerView;
        if (recyclerView3 != null) {
            IosDialogStyleAdapter<Object> iosDialogStyleAdapter2 = this.mTextSizeAdapter;
            if (iosDialogStyleAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mTextSizeAdapter");
                iosDialogStyleAdapter2 = null;
            }
            recyclerView3.setAdapter(iosDialogStyleAdapter2);
        }
        RecyclerView recyclerView4 = this.mFontSizeRecyclerView;
        if (recyclerView4 != null) {
            recyclerView4.post(new Runnable() { // from class: com.wifiled.ipixels.ui.text.CreativeTextActivity$$ExternalSyntheticLambda23
                @Override // java.lang.Runnable
                public final void run() {
                    CreativeTextActivity.initFontSizeDialog$lambda$10(CreativeTextActivity.this);
                }
            });
        }
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter3 = this.mTextSizeAdapter;
        if (iosDialogStyleAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTextSizeAdapter");
        } else {
            iosDialogStyleAdapter = iosDialogStyleAdapter3;
        }
        iosDialogStyleAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() { // from class: com.wifiled.ipixels.ui.text.CreativeTextActivity$$ExternalSyntheticLambda24
            @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter.OnItemClickListener
            public final void onItemClick(ViewGroup viewGroup, View view, Object obj, int i) {
                CreativeTextActivity.initFontSizeDialog$lambda$11(CreativeTextActivity.this, viewGroup, view, obj, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initFontSizeDialog$lambda$9(CreativeTextActivity creativeTextActivity, View view) {
        Dialog dialog = creativeTextActivity.mTextSizeDialog;
        Intrinsics.checkNotNull(dialog);
        dialog.cancel();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initFontSizeDialog$lambda$10(CreativeTextActivity creativeTextActivity) {
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter = creativeTextActivity.mTextSizeAdapter;
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter2 = null;
        if (iosDialogStyleAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTextSizeAdapter");
            iosDialogStyleAdapter = null;
        }
        IosDialogStyleAdapter<Object> iosDialogStyleAdapter3 = creativeTextActivity.mTextSizeAdapter;
        if (iosDialogStyleAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTextSizeAdapter");
        } else {
            iosDialogStyleAdapter2 = iosDialogStyleAdapter3;
        }
        int itemCount = iosDialogStyleAdapter2.getItemCount();
        RecyclerView recyclerView = creativeTextActivity.mFontSizeRecyclerView;
        Intrinsics.checkNotNull(recyclerView);
        iosDialogStyleAdapter.adaptiveRecyclerViewHeight(itemCount, recyclerView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initFontSizeDialog$lambda$11(CreativeTextActivity creativeTextActivity, ViewGroup viewGroup, View view, Object obj, int i) {
        LogUtils.file("TextActivity TextAttrFragment   mTextSizeAdapter.setOnItemClickListener position=" + i);
        Dialog dialog = creativeTextActivity.mTextSizeDialog;
        Intrinsics.checkNotNull(dialog);
        dialog.cancel();
        creativeTextActivity.mSelectFontSize = creativeTextActivity.mFontSizeList.get(i).intValue();
        creativeTextActivity.getBinding().tvTextSize32Bc.setText(String.valueOf(creativeTextActivity.mFontSizeList.get(i).intValue()));
        creativeTextActivity.mMaxLenth = (AppConfig.INSTANCE.getLedSize().get(0).intValue() / creativeTextActivity.mSelectFontSize) * 4;
        creativeTextActivity.getBinding().tvFontlimitInAttr.setText(creativeTextActivity.getTextTotalSize() + "/" + creativeTextActivity.mMaxLenth);
        creativeTextActivity.createGif(creativeTextActivity.mEffectPosition, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendData() {
        byte[] readFile2BytesByChannel;
        if (this.mGifPath.length() == 0 || (readFile2BytesByChannel = FileIOUtils.readFile2BytesByChannel(this.mGifPath)) == null || AppConfig.INSTANCE.getMcu() <= 4) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add((byte) 7);
        arrayList.add((byte) 0);
        arrayList.add((byte) 8);
        arrayList.add(Byte.valueOf(ByteCompanionObject.MIN_VALUE));
        arrayList.add((byte) 1);
        arrayList.add((byte) 0);
        ChannelIndex.INSTANCE.inc();
        arrayList.add(Byte.valueOf((byte) ChannelIndex.INSTANCE.index()));
        if (AppConfig.INSTANCE.getConnectType() != -1) {
            SendCore.INSTANCE.sendCompat(CollectionsKt.toByteArray(arrayList), new CreativeTextActivity$sendData$1$1(this, readFile2BytesByChannel));
        } else {
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.text.CreativeTextActivity$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit sendData$lambda$13$lambda$12;
                    sendData$lambda$13$lambda$12 = CreativeTextActivity.sendData$lambda$13$lambda$12(CreativeTextActivity.this);
                    return sendData$lambda$13$lambda$12;
                }
            });
        }
        if (AppConfig.INSTANCE.getConnectType2() != -1) {
            SendCore.INSTANCE.sendCompat2(CollectionsKt.toByteArray(arrayList), new CreativeTextActivity$sendData$1$3(this, readFile2BytesByChannel));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendData$lambda$13$lambda$12(CreativeTextActivity creativeTextActivity) {
        ToastUtil.show(creativeTextActivity.getString(R.string.msg_dev_connect_null));
        return Unit.INSTANCE;
    }

    static /* synthetic */ void sendGif$default(CreativeTextActivity creativeTextActivity, byte[] bArr, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        creativeTextActivity.sendGif(bArr, z);
    }

    private final void sendGif(byte[] data, boolean isDown) {
        this.mSendGifData = data;
        this.mSendGifIsDown = isDown;
        SendCore.INSTANCE.sendGifData(isDown, data, this.sendGifCallBack);
    }

    static /* synthetic */ void sendGif2$default(CreativeTextActivity creativeTextActivity, byte[] bArr, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        creativeTextActivity.sendGif2(bArr, z);
    }

    private final void sendGif2(byte[] data, boolean isDown) {
        this.mSendGifData = data;
        this.mSendGifIsDown = isDown;
        SendCore.INSTANCE.sendGifData2(isDown, data, this.sendGifCallBack);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendGifCallBack$lambda$28(final CreativeTextActivity creativeTextActivity, SendCore.CallbackBuilder callbackBuilder) {
        Intrinsics.checkNotNullParameter(callbackBuilder, "<this>");
        callbackBuilder.onStart(new Function0() { // from class: com.wifiled.ipixels.ui.text.CreativeTextActivity$$ExternalSyntheticLambda14
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendGifCallBack$lambda$28$lambda$15;
                sendGifCallBack$lambda$28$lambda$15 = CreativeTextActivity.sendGifCallBack$lambda$28$lambda$15(CreativeTextActivity.this);
                return sendGifCallBack$lambda$28$lambda$15;
            }
        });
        callbackBuilder.onProgress(new Function1() { // from class: com.wifiled.ipixels.ui.text.CreativeTextActivity$$ExternalSyntheticLambda15
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendGifCallBack$lambda$28$lambda$17;
                sendGifCallBack$lambda$28$lambda$17 = CreativeTextActivity.sendGifCallBack$lambda$28$lambda$17(CreativeTextActivity.this, ((Integer) obj).intValue());
                return sendGifCallBack$lambda$28$lambda$17;
            }
        });
        callbackBuilder.onCompleted(new Function0() { // from class: com.wifiled.ipixels.ui.text.CreativeTextActivity$$ExternalSyntheticLambda16
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendGifCallBack$lambda$28$lambda$19;
                sendGifCallBack$lambda$28$lambda$19 = CreativeTextActivity.sendGifCallBack$lambda$28$lambda$19(CreativeTextActivity.this);
                return sendGifCallBack$lambda$28$lambda$19;
            }
        });
        callbackBuilder.onError(new Function1() { // from class: com.wifiled.ipixels.ui.text.CreativeTextActivity$$ExternalSyntheticLambda17
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendGifCallBack$lambda$28$lambda$22;
                sendGifCallBack$lambda$28$lambda$22 = CreativeTextActivity.sendGifCallBack$lambda$28$lambda$22(CreativeTextActivity.this, ((Integer) obj).intValue());
                return sendGifCallBack$lambda$28$lambda$22;
            }
        });
        callbackBuilder.onResult(new Function1() { // from class: com.wifiled.ipixels.ui.text.CreativeTextActivity$$ExternalSyntheticLambda18
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendGifCallBack$lambda$28$lambda$27;
                sendGifCallBack$lambda$28$lambda$27 = CreativeTextActivity.sendGifCallBack$lambda$28$lambda$27(CreativeTextActivity.this, (byte[]) obj);
                return sendGifCallBack$lambda$28$lambda$27;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendGifCallBack$lambda$28$lambda$15(final CreativeTextActivity creativeTextActivity) {
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.text.CreativeTextActivity$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendGifCallBack$lambda$28$lambda$15$lambda$14;
                sendGifCallBack$lambda$28$lambda$15$lambda$14 = CreativeTextActivity.sendGifCallBack$lambda$28$lambda$15$lambda$14(CreativeTextActivity.this);
                return sendGifCallBack$lambda$28$lambda$15$lambda$14;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendGifCallBack$lambda$28$lambda$15$lambda$14(CreativeTextActivity creativeTextActivity) {
        String string = creativeTextActivity.getString(R.string.msg_sending);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        UtilsExtensionKt.showLoadingDialog$default((Activity) creativeTextActivity, false, string, false, 5, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendGifCallBack$lambda$28$lambda$17(final CreativeTextActivity creativeTextActivity, final int i) {
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.text.CreativeTextActivity$$ExternalSyntheticLambda19
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendGifCallBack$lambda$28$lambda$17$lambda$16;
                sendGifCallBack$lambda$28$lambda$17$lambda$16 = CreativeTextActivity.sendGifCallBack$lambda$28$lambda$17$lambda$16(CreativeTextActivity.this, i);
                return sendGifCallBack$lambda$28$lambda$17$lambda$16;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendGifCallBack$lambda$28$lambda$17$lambda$16(CreativeTextActivity creativeTextActivity, int i) {
        UtilsExtensionKt.showLoadingDialog$default((Activity) creativeTextActivity, false, creativeTextActivity.getString(R.string.msg_sending_progress) + " " + i + " %", false, 5, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendGifCallBack$lambda$28$lambda$19(final CreativeTextActivity creativeTextActivity) {
        if (creativeTextActivity.mSendGifIsDown) {
            try {
                ChannelIndex.INSTANCE.mapSaveChannel().put(Integer.valueOf(ChannelIndex.INSTANCE.index()), new ChannelListItem.GiftView(false, creativeTextActivity.mGifPath, String.valueOf(ChannelIndex.INSTANCE.index()), false, creativeTextActivity.mSendGifData));
                CreativeTextActivity creativeTextActivity2 = creativeTextActivity;
                String str = "channel_data";
                switch (AppConfig.INSTANCE.getLedType()) {
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
                    case 16:
                        str = "channel_data_32_256";
                        break;
                    case 17:
                        str = "channel_data_32_320";
                        break;
                    case 18:
                        str = "channel_data_32_384";
                        break;
                    case 19:
                        str = "channel_data_32_448";
                        break;
                }
                SPUtils.put(creativeTextActivity2, str, ChannelIndex.INSTANCE.mapSaveChannel());
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.text.CreativeTextActivity$$ExternalSyntheticLambda21
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendGifCallBack$lambda$28$lambda$19$lambda$18;
                sendGifCallBack$lambda$28$lambda$19$lambda$18 = CreativeTextActivity.sendGifCallBack$lambda$28$lambda$19$lambda$18(CreativeTextActivity.this);
                return sendGifCallBack$lambda$28$lambda$19$lambda$18;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendGifCallBack$lambda$28$lambda$19$lambda$18(CreativeTextActivity creativeTextActivity) {
        UtilsExtensionKt.showLoadingDialog$default((Activity) creativeTextActivity, false, (String) null, false, 6, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendGifCallBack$lambda$28$lambda$22(final CreativeTextActivity creativeTextActivity, final int i) {
        UtilsExtensionKt.showLoadingDialog$default((Activity) creativeTextActivity, false, (String) null, false, 6, (Object) null);
        if (i == 10016) {
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.text.CreativeTextActivity$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit sendGifCallBack$lambda$28$lambda$22$lambda$20;
                    sendGifCallBack$lambda$28$lambda$22$lambda$20 = CreativeTextActivity.sendGifCallBack$lambda$28$lambda$22$lambda$20(CreativeTextActivity.this, i);
                    return sendGifCallBack$lambda$28$lambda$22$lambda$20;
                }
            });
        } else {
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.text.CreativeTextActivity$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit sendGifCallBack$lambda$28$lambda$22$lambda$21;
                    sendGifCallBack$lambda$28$lambda$22$lambda$21 = CreativeTextActivity.sendGifCallBack$lambda$28$lambda$22$lambda$21(CreativeTextActivity.this, i);
                    return sendGifCallBack$lambda$28$lambda$22$lambda$21;
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendGifCallBack$lambda$28$lambda$22$lambda$20(CreativeTextActivity creativeTextActivity, int i) {
        ToastUtil.show(creativeTextActivity.getString(R.string.send_failed_deivce_space) + "(" + i + ")");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendGifCallBack$lambda$28$lambda$22$lambda$21(CreativeTextActivity creativeTextActivity, int i) {
        ToastUtil.show(creativeTextActivity.getString(R.string.msg_send_fail) + "(" + i + ")");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendGifCallBack$lambda$28$lambda$27(final CreativeTextActivity creativeTextActivity, byte[] it) {
        Intrinsics.checkNotNullParameter(it, "it");
        String str = new String(it, Charsets.UTF_8);
        com.wifiled.baselib.utils.LogUtils.logd("strResponse: ".concat(str), new Object[0]);
        String str2 = str;
        if (StringsKt.contains$default((CharSequence) str2, (CharSequence) "dev disconnect", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str2, (CharSequence) "retry_over_limit_no_answer", false, 2, (Object) null)) {
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.text.CreativeTextActivity$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit sendGifCallBack$lambda$28$lambda$27$lambda$23;
                    sendGifCallBack$lambda$28$lambda$27$lambda$23 = CreativeTextActivity.sendGifCallBack$lambda$28$lambda$27$lambda$23(CreativeTextActivity.this);
                    return sendGifCallBack$lambda$28$lambda$27$lambda$23;
                }
            });
            UtilsExtensionKt.uiDelay$default(new Function0() { // from class: com.wifiled.ipixels.ui.text.CreativeTextActivity$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit sendGifCallBack$lambda$28$lambda$27$lambda$24;
                    sendGifCallBack$lambda$28$lambda$27$lambda$24 = CreativeTextActivity.sendGifCallBack$lambda$28$lambda$27$lambda$24(CreativeTextActivity.this);
                    return sendGifCallBack$lambda$28$lambda$27$lambda$24;
                }
            }, 0L, 2, null);
        }
        if (it.length <= 4 || it[0] != 5) {
            return Unit.INSTANCE;
        }
        byte b = it[4];
        if (b == 0) {
            LogUtils.i("$TAG>>>[onResult]:命令无效 ");
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.text.CreativeTextActivity$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit sendGifCallBack$lambda$28$lambda$27$lambda$25;
                    sendGifCallBack$lambda$28$lambda$27$lambda$25 = CreativeTextActivity.sendGifCallBack$lambda$28$lambda$27$lambda$25(CreativeTextActivity.this);
                    return sendGifCallBack$lambda$28$lambda$27$lambda$25;
                }
            });
        } else if (b == 2) {
            UtilsExtensionKt.showLoadingDialog$default((Activity) creativeTextActivity, false, (String) null, false, 6, (Object) null);
        } else if (b == 3) {
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.text.CreativeTextActivity$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit sendGifCallBack$lambda$28$lambda$27$lambda$26;
                    sendGifCallBack$lambda$28$lambda$27$lambda$26 = CreativeTextActivity.sendGifCallBack$lambda$28$lambda$27$lambda$26(CreativeTextActivity.this);
                    return sendGifCallBack$lambda$28$lambda$27$lambda$26;
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendGifCallBack$lambda$28$lambda$27$lambda$23(CreativeTextActivity creativeTextActivity) {
        String string = creativeTextActivity.getString(R.string.msg_send_fail);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        UtilsExtensionKt.showLoadingDialog$default((Activity) creativeTextActivity, false, string, false, 1, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendGifCallBack$lambda$28$lambda$27$lambda$24(CreativeTextActivity creativeTextActivity) {
        UtilsExtensionKt.showLoadingDialog$default((Activity) creativeTextActivity, false, (String) null, false, 6, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendGifCallBack$lambda$28$lambda$27$lambda$25(CreativeTextActivity creativeTextActivity) {
        UtilsExtensionKt.showLoadingDialog$default((Activity) creativeTextActivity, false, (String) null, false, 6, (Object) null);
        Toast.makeText(creativeTextActivity, creativeTextActivity.getString(R.string.msg_send_fail), 0).show();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendGifCallBack$lambda$28$lambda$27$lambda$26(CreativeTextActivity creativeTextActivity) {
        UtilsExtensionKt.showLoadingDialog$default((Activity) creativeTextActivity, false, (String) null, false, 6, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void createGif(int position, boolean isSave) {
        String replace$default = StringsKt.replace$default(String.valueOf(getBinding().etInput.getText()), "\n", "", false, 4, (Object) null);
        if (replace$default.length() == 0) {
            runOnUiThread(new Runnable() { // from class: com.wifiled.ipixels.ui.text.CreativeTextActivity$$ExternalSyntheticLambda8
                @Override // java.lang.Runnable
                public final void run() {
                    CreativeTextActivity.createGif$lambda$29(CreativeTextActivity.this);
                }
            });
            return;
        }
        this.mSendText = replace$default;
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.text.CreativeTextActivity$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit createGif$lambda$30;
                createGif$lambda$30 = CreativeTextActivity.createGif$lambda$30(CreativeTextActivity.this);
                return createGif$lambda$30;
            }
        });
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), null, new CreativeTextActivity$createGif$3(position, this, replace$default, isSave, null), 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void createGif$lambda$29(CreativeTextActivity creativeTextActivity) {
        ToastUtil.show(creativeTextActivity.getString(R.string.enter_text));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit createGif$lambda$30(CreativeTextActivity creativeTextActivity) {
        String string = creativeTextActivity.getString(R.string.msg_loading);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        UtilsExtensionKt.showLoadingDialog$default((Activity) creativeTextActivity, false, string, false, 5, (Object) null);
        return Unit.INSTANCE;
    }

    private final void initColorAdapter() {
        final List<Integer> listOf = CollectionsKt.listOf((Object[]) new Integer[]{Integer.valueOf(R.drawable.creative_color_1), Integer.valueOf(R.drawable.creative_color_2), Integer.valueOf(R.drawable.creative_color_3), Integer.valueOf(R.drawable.creative_color_4), Integer.valueOf(R.drawable.creative_color_5), Integer.valueOf(R.drawable.creative_color_6)});
        this.mColorsData = listOf;
        RecyclerAdapter<Integer> recyclerAdapter = null;
        if (listOf == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mColorsData");
            listOf = null;
        }
        RecyclerAdapter<Integer> recyclerAdapter2 = new RecyclerAdapter<Integer>(listOf) { // from class: com.wifiled.ipixels.ui.text.CreativeTextActivity$initColorAdapter$1
            @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter
            public /* bridge */ /* synthetic */ void convert(RecyclerViewHolder recyclerViewHolder, Integer num) {
                convert(recyclerViewHolder, num.intValue());
            }

            {
                CreativeTextActivity creativeTextActivity = CreativeTextActivity.this;
            }

            public void convert(RecyclerViewHolder holder, int integer) {
                int i;
                Intrinsics.checkNotNullParameter(holder, "holder");
                View view = holder.getView(R.id.rl_paint_outside_frame);
                View view2 = holder.getView(R.id.colorView);
                i = CreativeTextActivity.this.mGradientColor;
                if (i == getPosition(holder)) {
                    view.setBackgroundResource(R.drawable.icon_paint_color_sel);
                } else {
                    view.setBackgroundResource(R.color.transparent);
                }
                view2.setBackgroundResource(integer);
            }
        };
        this.mColorAdapter = recyclerAdapter2;
        recyclerAdapter2.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() { // from class: com.wifiled.ipixels.ui.text.CreativeTextActivity$$ExternalSyntheticLambda13
            @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter.OnItemClickListener
            public final void onItemClick(ViewGroup viewGroup, View view, Object obj, int i) {
                CreativeTextActivity.initColorAdapter$lambda$31(CreativeTextActivity.this, viewGroup, view, (Integer) obj, i);
            }
        });
        getBinding().recyclerviewColor.setLayoutManager(new GridLayoutManager(this, 6));
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
    public static final void initColorAdapter$lambda$31(CreativeTextActivity creativeTextActivity, ViewGroup viewGroup, View view, Integer num, int i) {
        creativeTextActivity.mGradientColor = i;
        RecyclerAdapter<Integer> recyclerAdapter = creativeTextActivity.mColorAdapter;
        if (recyclerAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mColorAdapter");
            recyclerAdapter = null;
        }
        recyclerAdapter.notifyDataSetChanged();
        creativeTextActivity.createGif(creativeTextActivity.mEffectPosition, false);
    }
}
