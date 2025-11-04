package com.wifiled.ipixels.ui.text;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import androidx.core.view.ViewGroupKt;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.LogUtils;
import com.wifiled.baselib.app.cache.ACache;
import com.wifiled.baselib.base.BaseFragment;
import com.wifiled.baselib.base.recycleview.RecyclerAdapter;
import com.wifiled.baselib.utils.ContextHolder;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.Constants;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.UtilsExtensionKt;
import com.wifiled.ipixels.UtilsExtensionKt$appViewModels$1;
import com.wifiled.ipixels.UtilsExtensionKt$appViewModels$2;
import com.wifiled.ipixels.core.SendCore;
import com.wifiled.ipixels.core.send.BaseSend;
import com.wifiled.ipixels.core.send.SimpleSend;
import com.wifiled.ipixels.event.EventText;
import com.wifiled.ipixels.event.TextEmojiBuilder;
import com.wifiled.ipixels.ui.text.adapter.ModeAdapter;
import com.wifiled.ipixels.ui.text.vo.EventLanguage;
import com.wifiled.ipixels.ui.text.vo.EventNotifity;
import com.wifiled.ipixels.utils.TimeHelper;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* compiled from: TextEffectFragment.kt */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 (2\u00020\u0001:\u0001(B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0014J\b\u0010\u0018\u001a\u00020\u0019H\u0014J\u0012\u0010\u001a\u001a\u00020\u00192\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u0019H\u0014J\b\u0010\u001e\u001a\u00020\u0019H\u0014J\b\u0010\u001f\u001a\u00020\u0019H\u0016J\b\u0010 \u001a\u00020\u0019H\u0016J\b\u0010!\u001a\u00020\u0019H\u0016J\u0010\u0010\"\u001a\u00020\u00192\u0006\u0010#\u001a\u00020$H\u0007J\u0010\u0010%\u001a\u00020\u00192\u0006\u0010&\u001a\u00020'H\u0007R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0016X\u0082.¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lcom/wifiled/ipixels/ui/text/TextEffectFragment;", "Lcom/wifiled/baselib/base/BaseFragment;", "<init>", "()V", "layoutId", "", "modes", "", "", "textViewModel", "Lcom/wifiled/ipixels/ui/text/TextViewModel;", "getTextViewModel", "()Lcom/wifiled/ipixels/ui/text/TextViewModel;", "textViewModel$delegate", "Lkotlin/Lazy;", "adapter", "Lcom/wifiled/ipixels/ui/text/adapter/ModeAdapter;", "mLanguage", "mFromType", "rl_effect", "Landroidx/recyclerview/widget/RecyclerView;", "sb_speed", "Landroid/widget/SeekBar;", "sb_text_alpha", "initView", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "bindData", "bindListener", "onDetach", "onStart", "onStop", "onCallBackResult", "eventNotifity", "Lcom/wifiled/ipixels/ui/text/vo/EventNotifity;", "onLanguage", "eventLanguage", "Lcom/wifiled/ipixels/ui/text/vo/EventLanguage;", "Companion", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TextEffectFragment extends BaseFragment {
    private static final String ARG_TEMP_TYPE = "from_type";

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private ModeAdapter adapter;
    private int mFromType;
    private int mLanguage;
    private List<String> modes;
    private RecyclerView rl_effect;
    private SeekBar sb_speed;
    private SeekBar sb_text_alpha;

    /* renamed from: textViewModel$delegate, reason: from kotlin metadata */
    private final Lazy textViewModel;

    @Override // com.wifiled.baselib.base.BaseFragment
    protected int layoutId() {
        return R.layout.fragment_text_effect;
    }

    public TextEffectFragment() {
        TextEffectFragment textEffectFragment = this;
        this.textViewModel = FragmentViewModelLazyKt.createViewModelLazy(textEffectFragment, Reflection.getOrCreateKotlinClass(TextViewModel.class), UtilsExtensionKt$appViewModels$1.INSTANCE, new UtilsExtensionKt$appViewModels$2(textEffectFragment), null);
    }

    private final TextViewModel getTextViewModel() {
        return (TextViewModel) this.textViewModel.getValue();
    }

    /* compiled from: TextEffectFragment.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/wifiled/ipixels/ui/text/TextEffectFragment$Companion;", "", "<init>", "()V", "ARG_TEMP_TYPE", "", "newInstance", "Lcom/wifiled/ipixels/ui/text/TextEffectFragment;", "type", "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final TextEffectFragment newInstance(int type) {
            TextEffectFragment textEffectFragment = new TextEffectFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(TextEffectFragment.ARG_TEMP_TYPE, type);
            textEffectFragment.setArguments(bundle);
            return textEffectFragment;
        }
    }

    @Override // com.wifiled.baselib.base.BaseFragment
    protected void initView() {
        super.initView();
        View findViewById = this.mRootView.findViewById(R.id.rl_effect);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.rl_effect = (RecyclerView) findViewById;
        View findViewById2 = this.mRootView.findViewById(R.id.sb_speed);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.sb_speed = (SeekBar) findViewById2;
        View findViewById3 = this.mRootView.findViewById(R.id.sb_text_alpha);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.sb_text_alpha = (SeekBar) findViewById3;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mFromType = arguments.getInt(ARG_TEMP_TYPE);
        }
    }

    @Override // com.wifiled.baselib.base.BaseFragment
    protected void bindData() {
        FragmentActivity fragmentActivity = this.mWeakActivity.get();
        Intrinsics.checkNotNull(fragmentActivity);
        String string = fragmentActivity.getString(R.string.effect_fixed);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        FragmentActivity fragmentActivity2 = this.mWeakActivity.get();
        Intrinsics.checkNotNull(fragmentActivity2);
        String string2 = fragmentActivity2.getString(R.string.effect_left);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        FragmentActivity fragmentActivity3 = this.mWeakActivity.get();
        Intrinsics.checkNotNull(fragmentActivity3);
        String string3 = fragmentActivity3.getString(R.string.effect_right);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
        FragmentActivity fragmentActivity4 = this.mWeakActivity.get();
        Intrinsics.checkNotNull(fragmentActivity4);
        String string4 = fragmentActivity4.getString(R.string.effect_up);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
        FragmentActivity fragmentActivity5 = this.mWeakActivity.get();
        Intrinsics.checkNotNull(fragmentActivity5);
        String string5 = fragmentActivity5.getString(R.string.effect_down);
        Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
        FragmentActivity fragmentActivity6 = this.mWeakActivity.get();
        Intrinsics.checkNotNull(fragmentActivity6);
        String string6 = fragmentActivity6.getString(R.string.effect_blink);
        Intrinsics.checkNotNullExpressionValue(string6, "getString(...)");
        FragmentActivity fragmentActivity7 = this.mWeakActivity.get();
        Intrinsics.checkNotNull(fragmentActivity7);
        String string7 = fragmentActivity7.getString(R.string.effect_breath);
        Intrinsics.checkNotNullExpressionValue(string7, "getString(...)");
        FragmentActivity fragmentActivity8 = this.mWeakActivity.get();
        Intrinsics.checkNotNull(fragmentActivity8);
        String string8 = fragmentActivity8.getString(R.string.effect_snowflakes);
        Intrinsics.checkNotNullExpressionValue(string8, "getString(...)");
        FragmentActivity fragmentActivity9 = this.mWeakActivity.get();
        Intrinsics.checkNotNull(fragmentActivity9);
        String string9 = fragmentActivity9.getString(R.string.effect_laser);
        Intrinsics.checkNotNullExpressionValue(string9, "getString(...)");
        this.modes = CollectionsKt.listOf((Object[]) new String[]{string, string2, string3, string4, string5, string6, string7, string8, string9});
        RecyclerView recyclerView = this.rl_effect;
        ModeAdapter modeAdapter = null;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rl_effect");
            recyclerView = null;
        }
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        FragmentActivity fragmentActivity10 = this.mWeakActivity.get();
        Intrinsics.checkNotNull(fragmentActivity10);
        FragmentActivity fragmentActivity11 = fragmentActivity10;
        List<String> list = this.modes;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("modes");
            list = null;
        }
        this.adapter = new ModeAdapter(fragmentActivity11, list, this.mFromType);
        RecyclerView recyclerView2 = this.rl_effect;
        if (recyclerView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rl_effect");
            recyclerView2 = null;
        }
        ModeAdapter modeAdapter2 = this.adapter;
        if (modeAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            modeAdapter = modeAdapter2;
        }
        recyclerView2.setAdapter(modeAdapter);
    }

    @Override // com.wifiled.baselib.base.BaseFragment
    protected void bindListener() {
        super.bindListener();
        ModeAdapter modeAdapter = this.adapter;
        SeekBar seekBar = null;
        if (modeAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            modeAdapter = null;
        }
        modeAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() { // from class: com.wifiled.ipixels.ui.text.TextEffectFragment$$ExternalSyntheticLambda0
            @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter.OnItemClickListener
            public final void onItemClick(ViewGroup viewGroup, View view, Object obj, int i) {
                TextEffectFragment.bindListener$lambda$2(TextEffectFragment.this, viewGroup, view, (String) obj, i);
            }
        });
        SeekBar seekBar2 = this.sb_text_alpha;
        if (seekBar2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sb_text_alpha");
            seekBar2 = null;
        }
        UtilsExtensionKt.setOnSeekBarStopChangeListener(seekBar2, new Function1() { // from class: com.wifiled.ipixels.ui.text.TextEffectFragment$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit bindListener$lambda$3;
                bindListener$lambda$3 = TextEffectFragment.bindListener$lambda$3((SeekBar) obj);
                return bindListener$lambda$3;
            }
        });
        SeekBar seekBar3 = this.sb_speed;
        if (seekBar3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sb_speed");
        } else {
            seekBar = seekBar3;
        }
        UtilsExtensionKt.setOnSeekBarStopChangeListener(seekBar, new Function1() { // from class: com.wifiled.ipixels.ui.text.TextEffectFragment$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit bindListener$lambda$4;
                bindListener$lambda$4 = TextEffectFragment.bindListener$lambda$4((SeekBar) obj);
                return bindListener$lambda$4;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$2(final TextEffectFragment textEffectFragment, ViewGroup viewGroup, View view, String str, int i) {
        LogUtils.file("TextActivity TextEffectFragment    adapter.setOnItemClickListener position=" + i);
        final EventText eventText = TextEmojiBuilder.INSTANCE.getEventText();
        int i2 = textEffectFragment.mLanguage;
        if (i2 != 1 || i == 2) {
            if (i2 != 2 || i == 1) {
                if (AppConfig.INSTANCE.getLedType() == 6 || AppConfig.INSTANCE.getLedType() == 7 || AppConfig.INSTANCE.getLedType() == 8 || AppConfig.INSTANCE.getLedType() == 12 || AppConfig.INSTANCE.getLedType() == 13 || AppConfig.INSTANCE.getLedType() == 14 || AppConfig.INSTANCE.getLedType() == 15) {
                    if (eventText.getTextdirction() == 1 && (i == 1 || i == 2 || i == 7)) {
                        return;
                    }
                    if (eventText.getTextdirction() == 0 && (i == 3 || i == 4)) {
                        return;
                    }
                } else if (AppConfig.INSTANCE.getLedType() != 0 && AppConfig.INSTANCE.getLedType() != 2) {
                    if (eventText.getTextdirction() == 1) {
                        if (textEffectFragment.mFromType == 1002) {
                            if (i == 1 || i == 2 || i == 6 || i == 7) {
                                return;
                            }
                        } else if (i == 1 || i == 2 || i == 7) {
                            return;
                        }
                    }
                    if (eventText.getTextdirction() == 0) {
                        if (textEffectFragment.mFromType == 1002) {
                            if (i == 3 || i == 4 || i == 6) {
                                return;
                            }
                        } else if (i == 3 || i == 4) {
                            return;
                        }
                    }
                }
                eventText.setTextEffect(i);
                UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.text.TextEffectFragment$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Unit bindListener$lambda$2$lambda$1;
                        bindListener$lambda$2$lambda$1 = TextEffectFragment.bindListener$lambda$2$lambda$1(EventText.this, textEffectFragment);
                        return bindListener$lambda$2$lambda$1;
                    }
                });
                ModeAdapter modeAdapter = null;
                try {
                    Intrinsics.checkNotNull(viewGroup);
                    ModeAdapter modeAdapter2 = textEffectFragment.adapter;
                    if (modeAdapter2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        modeAdapter2 = null;
                    }
                    ViewGroupKt.get(viewGroup, modeAdapter2.getIsSelect()).setSelected(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Intrinsics.checkNotNull(viewGroup);
                Iterator<View> it = ViewGroupKt.iterator(viewGroup);
                while (it.hasNext()) {
                    it.next().setSelected(false);
                }
                view.setSelected(true);
                ModeAdapter modeAdapter3 = textEffectFragment.adapter;
                if (modeAdapter3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                } else {
                    modeAdapter = modeAdapter3;
                }
                modeAdapter.setSelect(i);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit bindListener$lambda$2$lambda$1(EventText eventText, TextEffectFragment textEffectFragment) {
        eventText.setChangeMode(TextAttrEnum.TextEffect);
        textEffectFragment.getTextViewModel().getTextChangedLiveData().setValue(eventText);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit bindListener$lambda$3(final SeekBar it) {
        Intrinsics.checkNotNullParameter(it, "it");
        it.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.wifiled.ipixels.ui.text.TextEffectFragment$bindListener$2$1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                LogUtils.file("TextActivity TextEffectFragment    sb_text_alpha.setOnSeekBarStopChangeListene it.progressr=" + it.getProgress());
                if (TimeHelper.INSTANCE.allowSend(100)) {
                    if (progress <= 10) {
                        progress = 10;
                    }
                    BaseSend.setLedLight$default(SendCore.INSTANCE, progress, null, 2, null);
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                ACache.get(ContextHolder.getContext()).put(Constants.VAL_GLOBAL_LIGHT, String.valueOf(seekBar != null ? Integer.valueOf(seekBar.getProgress()) : null));
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit bindListener$lambda$4(final SeekBar it) {
        Intrinsics.checkNotNullParameter(it, "it");
        it.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.wifiled.ipixels.ui.text.TextEffectFragment$bindListener$3$1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                Intrinsics.checkNotNullParameter(seekBar, "seekBar");
                LogUtils.file("TextActivity TextEffectFragment    sb_speed.setOnSeekBarStopChangeListene onStopTrackingTouch=" + it.getProgress());
                EventText eventText = TextEmojiBuilder.INSTANCE.getEventText();
                SimpleSend.setTextSpeed$default(SendCore.INSTANCE, it.getProgress(), null, 2, null);
                eventText.setTextSpeed(it.getProgress());
                eventText.setChangeMode(TextAttrEnum.TextSpeed);
            }
        });
        return Unit.INSTANCE;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        this.mActivity = null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        Log.d(getClass().getSimpleName(), "~~~~~run onStart~~~~~~~~");
        EventBus.getDefault().register(this);
        try {
            if (ACache.get(ContextHolder.getContext()).getAsString(Constants.VAL_GLOBAL_LIGHT) != null) {
                String asString = ACache.get(ContextHolder.getContext()).getAsString(Constants.VAL_GLOBAL_LIGHT);
                Intrinsics.checkNotNullExpressionValue(asString, "getAsString(...)");
                SeekBar seekBar = this.sb_text_alpha;
                if (seekBar == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("sb_text_alpha");
                    seekBar = null;
                }
                seekBar.setProgress(Integer.parseInt(asString));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onCallBackResult(EventNotifity eventNotifity) {
        Intrinsics.checkNotNullParameter(eventNotifity, "eventNotifity");
        EventText eventText = TextEmojiBuilder.INSTANCE.getEventText();
        eventText.setTextEffect(0);
        eventText.setChangeMode(TextAttrEnum.TextEffect);
        getTextViewModel().getTextChangedLiveData().setValue(eventText);
        ModeAdapter modeAdapter = this.adapter;
        ModeAdapter modeAdapter2 = null;
        if (modeAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            modeAdapter = null;
        }
        modeAdapter.setSelect(0);
        ModeAdapter modeAdapter3 = this.adapter;
        if (modeAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            modeAdapter2 = modeAdapter3;
        }
        modeAdapter2.notifyDataSetChanged();
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public final void onLanguage(EventLanguage eventLanguage) {
        Intrinsics.checkNotNullParameter(eventLanguage, "eventLanguage");
        this.mLanguage = eventLanguage.getType();
        int type = eventLanguage.getType();
        ModeAdapter modeAdapter = null;
        if (type == 1) {
            ModeAdapter modeAdapter2 = this.adapter;
            if (modeAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                modeAdapter2 = null;
            }
            modeAdapter2.setSelect(2);
            ModeAdapter modeAdapter3 = this.adapter;
            if (modeAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                modeAdapter3 = null;
            }
            modeAdapter3.setMLanguageType(1);
            ModeAdapter modeAdapter4 = this.adapter;
            if (modeAdapter4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
            } else {
                modeAdapter = modeAdapter4;
            }
            modeAdapter.notifyDataSetChanged();
        } else if (type == 2) {
            ModeAdapter modeAdapter5 = this.adapter;
            if (modeAdapter5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                modeAdapter5 = null;
            }
            modeAdapter5.setSelect(1);
            ModeAdapter modeAdapter6 = this.adapter;
            if (modeAdapter6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                modeAdapter6 = null;
            }
            modeAdapter6.setMLanguageType(2);
            ModeAdapter modeAdapter7 = this.adapter;
            if (modeAdapter7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
            } else {
                modeAdapter = modeAdapter7;
            }
            modeAdapter.notifyDataSetChanged();
        } else {
            ModeAdapter modeAdapter8 = this.adapter;
            if (modeAdapter8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                modeAdapter8 = null;
            }
            modeAdapter8.setSelect(0);
            ModeAdapter modeAdapter9 = this.adapter;
            if (modeAdapter9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                modeAdapter9 = null;
            }
            modeAdapter9.setMLanguageType(0);
            ModeAdapter modeAdapter10 = this.adapter;
            if (modeAdapter10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
            } else {
                modeAdapter = modeAdapter10;
            }
            modeAdapter.notifyDataSetChanged();
        }
        EventBus.getDefault().removeStickyEvent(eventLanguage);
    }
}
