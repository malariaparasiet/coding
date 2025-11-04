package com.wifiled.ipixels.ui.settings;

import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.wifiled.baselib.app.language.Constance;
import com.wifiled.baselib.app.language.LanguageManager;
import com.wifiled.baselib.base.BaseFragment;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.bean.LanguageData;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SettingsLanFragment.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u000f\u001a\u00020\tH\u0014J\b\u0010\u0010\u001a\u00020\u0011H\u0014J\b\u0010\u0012\u001a\u00020\u0011H\u0014J\b\u0010\u0013\u001a\u00020\u0011H\u0014J\u0006\u0010\u0014\u001a\u00020\u0011J\b\u0010\u0015\u001a\u00020\u0011H\u0002J\u0006\u0010\u0016\u001a\u00020\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/wifiled/ipixels/ui/settings/SettingsLanFragment;", "Lcom/wifiled/baselib/base/BaseFragment;", "<init>", "()V", "languageAdapter", "Lcom/wifiled/ipixels/ui/settings/LanguageAdapter;", "mLanguage", "", "mSelectPos", "", "data", "", "Lcom/wifiled/ipixels/bean/LanguageData;", "lv_language", "Landroidx/recyclerview/widget/RecyclerView;", "layoutId", "initView", "", "bindData", "bindListener", "initData", "getLanguageListData", "getLan", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SettingsLanFragment extends BaseFragment {
    private LanguageAdapter languageAdapter;
    private RecyclerView lv_language;
    private int mSelectPos;
    private String mLanguage = "en";
    private final List<LanguageData> data = new ArrayList();

    @Override // com.wifiled.baselib.base.BaseFragment
    protected int layoutId() {
        return R.layout.fragment_settings_language;
    }

    @Override // com.wifiled.baselib.base.BaseFragment
    protected void initView() {
        super.initView();
        View findViewById = this.mRootView.findViewById(R.id.lv_language);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.lv_language = (RecyclerView) findViewById;
    }

    @Override // com.wifiled.baselib.base.BaseFragment
    protected void bindData() {
        initData();
    }

    @Override // com.wifiled.baselib.base.BaseFragment
    protected void bindListener() {
        super.bindListener();
    }

    public final void initData() {
        getLanguageListData();
        RecyclerView recyclerView = this.lv_language;
        LanguageAdapter languageAdapter = null;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("lv_language");
            recyclerView = null;
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this.mActivity));
        this.languageAdapter = new LanguageAdapter(R.layout.item_language_single_choice, this.data);
        RecyclerView recyclerView2 = this.lv_language;
        if (recyclerView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("lv_language");
            recyclerView2 = null;
        }
        LanguageAdapter languageAdapter2 = this.languageAdapter;
        if (languageAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("languageAdapter");
            languageAdapter2 = null;
        }
        recyclerView2.setAdapter(languageAdapter2);
        this.mLanguage = LanguageManager.getSaveLanguage(getActivity()).toString();
        Iterator<T> it = this.data.iterator();
        int i = 0;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            int i2 = i + 1;
            if (Intrinsics.areEqual(this.mLanguage, ((LanguageData) it.next()).getLanguageCode())) {
                this.mSelectPos = i;
                this.data.get(i).setSelect(true);
                break;
            }
            i = i2;
        }
        LanguageAdapter languageAdapter3 = this.languageAdapter;
        if (languageAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("languageAdapter");
        } else {
            languageAdapter = languageAdapter3;
        }
        languageAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.wifiled.ipixels.ui.settings.SettingsLanFragment$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i3) {
                SettingsLanFragment.initData$lambda$1(SettingsLanFragment.this, baseQuickAdapter, view, i3);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initData$lambda$1(SettingsLanFragment settingsLanFragment, BaseQuickAdapter adapter, View view, int i) {
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        Intrinsics.checkNotNullParameter(view, "view");
        LanguageAdapter languageAdapter = settingsLanFragment.languageAdapter;
        LanguageAdapter languageAdapter2 = null;
        if (languageAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("languageAdapter");
            languageAdapter = null;
        }
        languageAdapter.getData().get(settingsLanFragment.mSelectPos).setSelect(false);
        LanguageAdapter languageAdapter3 = settingsLanFragment.languageAdapter;
        if (languageAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("languageAdapter");
            languageAdapter3 = null;
        }
        languageAdapter3.notifyItemChanged(settingsLanFragment.mSelectPos);
        settingsLanFragment.mSelectPos = i;
        LanguageAdapter languageAdapter4 = settingsLanFragment.languageAdapter;
        if (languageAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("languageAdapter");
            languageAdapter4 = null;
        }
        languageAdapter4.getData().get(i).setSelect(true);
        LanguageAdapter languageAdapter5 = settingsLanFragment.languageAdapter;
        if (languageAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("languageAdapter");
            languageAdapter5 = null;
        }
        languageAdapter5.notifyItemChanged(i);
        LanguageAdapter languageAdapter6 = settingsLanFragment.languageAdapter;
        if (languageAdapter6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("languageAdapter");
        } else {
            languageAdapter2 = languageAdapter6;
        }
        settingsLanFragment.mLanguage = languageAdapter2.getData().get(i).getLanguageCode();
    }

    private final void getLanguageListData() {
        List<LanguageData> list = this.data;
        String string = getString(R.string.language_english);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        list.add(new LanguageData(string, Constance.SP.INSTANCE.getLANGUAGE_ENGLISH(), false));
        List<LanguageData> list2 = this.data;
        String string2 = getString(R.string.language_china);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        list2.add(new LanguageData(string2, Constance.SP.INSTANCE.getLANGUAGE_CHINESE_SIMPLIFIED(), false));
        List<LanguageData> list3 = this.data;
        String string3 = getString(R.string.language_tw);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
        list3.add(new LanguageData(string3, Constance.SP.INSTANCE.getLANGUAGE_CHINESE_TRADITIONAL(), false));
        List<LanguageData> list4 = this.data;
        String string4 = getString(R.string.language_ja);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
        list4.add(new LanguageData(string4, Constance.SP.INSTANCE.getLANGUAGE_JA(), false));
        List<LanguageData> list5 = this.data;
        String string5 = getString(R.string.language_de);
        Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
        list5.add(new LanguageData(string5, Constance.SP.INSTANCE.getLANGUAGE_DE(), false));
        List<LanguageData> list6 = this.data;
        String string6 = getString(R.string.language_pt);
        Intrinsics.checkNotNullExpressionValue(string6, "getString(...)");
        list6.add(new LanguageData(string6, Constance.SP.INSTANCE.getLANGUAGE_PT(), false));
        List<LanguageData> list7 = this.data;
        String string7 = getString(R.string.language_pt_rbr);
        Intrinsics.checkNotNullExpressionValue(string7, "getString(...)");
        list7.add(new LanguageData(string7, Constance.SP.INSTANCE.getLANGUAGE_PT_rBR(), false));
        List<LanguageData> list8 = this.data;
        String string8 = getString(R.string.language_es);
        Intrinsics.checkNotNullExpressionValue(string8, "getString(...)");
        list8.add(new LanguageData(string8, Constance.SP.INSTANCE.getLANGUAGE_ES(), false));
        List<LanguageData> list9 = this.data;
        String string9 = getString(R.string.language_fr);
        Intrinsics.checkNotNullExpressionValue(string9, "getString(...)");
        list9.add(new LanguageData(string9, Constance.SP.INSTANCE.getLANGUAGE_FR(), false));
        List<LanguageData> list10 = this.data;
        String string10 = getString(R.string.language_ko);
        Intrinsics.checkNotNullExpressionValue(string10, "getString(...)");
        list10.add(new LanguageData(string10, Constance.SP.INSTANCE.getLANGUAGE_KO(), false));
        List<LanguageData> list11 = this.data;
        String string11 = getString(R.string.language_ru);
        Intrinsics.checkNotNullExpressionValue(string11, "getString(...)");
        list11.add(new LanguageData(string11, Constance.SP.INSTANCE.getLANGUAGE_RU(), false));
        List<LanguageData> list12 = this.data;
        String string12 = getString(R.string.language_it);
        Intrinsics.checkNotNullExpressionValue(string12, "getString(...)");
        list12.add(new LanguageData(string12, Constance.SP.INSTANCE.getLANGUAGE_IT(), false));
        List<LanguageData> list13 = this.data;
        String string13 = getString(R.string.language_vi);
        Intrinsics.checkNotNullExpressionValue(string13, "getString(...)");
        list13.add(new LanguageData(string13, Constance.SP.INSTANCE.getLANGUAGE_VI(), false));
        List<LanguageData> list14 = this.data;
        String string14 = getString(R.string.language_thai);
        Intrinsics.checkNotNullExpressionValue(string14, "getString(...)");
        list14.add(new LanguageData(string14, Constance.SP.INSTANCE.getLANGUAGE_THAI(), false));
        List<LanguageData> list15 = this.data;
        String string15 = getString(R.string.language_ar);
        Intrinsics.checkNotNullExpressionValue(string15, "getString(...)");
        list15.add(new LanguageData(string15, Constance.SP.INSTANCE.getLANGUAGE_AR(), false));
        List<LanguageData> list16 = this.data;
        String string16 = getString(R.string.language_bg);
        Intrinsics.checkNotNullExpressionValue(string16, "getString(...)");
        list16.add(new LanguageData(string16, Constance.SP.INSTANCE.getLANGUAGE_BG(), false));
        List<LanguageData> list17 = this.data;
        String string17 = getString(R.string.language_cs);
        Intrinsics.checkNotNullExpressionValue(string17, "getString(...)");
        list17.add(new LanguageData(string17, Constance.SP.INSTANCE.getLANGUAGE_CS(), false));
        List<LanguageData> list18 = this.data;
        String string18 = getString(R.string.language_el);
        Intrinsics.checkNotNullExpressionValue(string18, "getString(...)");
        list18.add(new LanguageData(string18, Constance.SP.INSTANCE.getLANGUAGE_EL(), false));
        List<LanguageData> list19 = this.data;
        String string19 = getString(R.string.language_hr);
        Intrinsics.checkNotNullExpressionValue(string19, "getString(...)");
        list19.add(new LanguageData(string19, Constance.SP.INSTANCE.getLANGUAGE_HR(), false));
        List<LanguageData> list20 = this.data;
        String string20 = getString(R.string.language_nl);
        Intrinsics.checkNotNullExpressionValue(string20, "getString(...)");
        list20.add(new LanguageData(string20, Constance.SP.INSTANCE.getLANGUAGE_NL(), false));
        List<LanguageData> list21 = this.data;
        String string21 = getString(R.string.language_pl);
        Intrinsics.checkNotNullExpressionValue(string21, "getString(...)");
        list21.add(new LanguageData(string21, Constance.SP.INSTANCE.getLANGUAGE_PL(), false));
        List<LanguageData> list22 = this.data;
        String string22 = getString(R.string.language_ro);
        Intrinsics.checkNotNullExpressionValue(string22, "getString(...)");
        list22.add(new LanguageData(string22, Constance.SP.INSTANCE.getLANGUAGE_RO(), false));
        List<LanguageData> list23 = this.data;
        String string23 = getString(R.string.language_sl);
        Intrinsics.checkNotNullExpressionValue(string23, "getString(...)");
        list23.add(new LanguageData(string23, Constance.SP.INSTANCE.getLANGUAGE_SL(), false));
        List<LanguageData> list24 = this.data;
        String string24 = getString(R.string.language_sk);
        Intrinsics.checkNotNullExpressionValue(string24, "getString(...)");
        list24.add(new LanguageData(string24, Constance.SP.INSTANCE.getLANGUAGE_SK(), false));
        List<LanguageData> list25 = this.data;
        String string25 = getString(R.string.language_tr);
        Intrinsics.checkNotNullExpressionValue(string25, "getString(...)");
        list25.add(new LanguageData(string25, Constance.SP.INSTANCE.getLANGUAGE_TR(), false));
        List<LanguageData> list26 = this.data;
        String string26 = getString(R.string.language_hi);
        Intrinsics.checkNotNullExpressionValue(string26, "getString(...)");
        list26.add(new LanguageData(string26, Constance.SP.INSTANCE.getLANGUAGE_HI(), false));
        List<LanguageData> list27 = this.data;
        String string27 = getString(R.string.language_mr);
        Intrinsics.checkNotNullExpressionValue(string27, "getString(...)");
        list27.add(new LanguageData(string27, Constance.SP.INSTANCE.getLANGUAGE_MR(), false));
        List<LanguageData> list28 = this.data;
        String string28 = getString(R.string.language_te);
        Intrinsics.checkNotNullExpressionValue(string28, "getString(...)");
        list28.add(new LanguageData(string28, Constance.SP.INSTANCE.getLANGUAGE_TE(), false));
        List<LanguageData> list29 = this.data;
        String string29 = getString(R.string.language_kn);
        Intrinsics.checkNotNullExpressionValue(string29, "getString(...)");
        list29.add(new LanguageData(string29, Constance.SP.INSTANCE.getLANGUAGE_KN(), false));
        List<LanguageData> list30 = this.data;
        String string30 = getString(R.string.language_ta);
        Intrinsics.checkNotNullExpressionValue(string30, "getString(...)");
        list30.add(new LanguageData(string30, Constance.SP.INSTANCE.getLANGUAGE_TA(), false));
        List<LanguageData> list31 = this.data;
        String string31 = getString(R.string.language_ml);
        Intrinsics.checkNotNullExpressionValue(string31, "getString(...)");
        list31.add(new LanguageData(string31, Constance.SP.INSTANCE.getLANGUAGE_ML(), false));
        List<LanguageData> list32 = this.data;
        String string32 = getString(R.string.language_gu);
        Intrinsics.checkNotNullExpressionValue(string32, "getString(...)");
        list32.add(new LanguageData(string32, Constance.SP.INSTANCE.getLANGUAGE_GU(), false));
        List<LanguageData> list33 = this.data;
        String string33 = getString(R.string.language_bn);
        Intrinsics.checkNotNullExpressionValue(string33, "getString(...)");
        list33.add(new LanguageData(string33, Constance.SP.INSTANCE.getLANGUAGE_BN(), false));
    }

    /* renamed from: getLan, reason: from getter */
    public final String getMLanguage() {
        return this.mLanguage;
    }
}
