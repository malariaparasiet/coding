package com.wifiled.ipixels.ui.subzone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.lifecycle.ViewModelProvider;
import com.wifiled.baselib.app.language.LanguageUtil;
import com.wifiled.baselib.uicode.base.UiBaseActivity;
import com.wifiled.ipixels.App;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.databinding.ActivitySelectTemplateBinding;
import com.wifiled.ipixels.ui.imgtxt.TextAnimationModel;
import com.wifiled.ipixels.view.customview.CustomImageView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: SelectTemplateActivity.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0004B\u0007¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0007\u001a\u00020\u0003H\u0016J\b\u0010\b\u001a\u00020\u0002H\u0016J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\b\u0010\r\u001a\u00020\nH\u0002J\u0010\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u0013H\u0002¨\u0006\u0014"}, d2 = {"Lcom/wifiled/ipixels/ui/subzone/SelectTemplateActivity;", "Lcom/wifiled/baselib/uicode/base/UiBaseActivity;", "Lcom/wifiled/ipixels/ui/imgtxt/TextAnimationModel;", "Lcom/wifiled/ipixels/databinding/ActivitySelectTemplateBinding;", "Landroid/view/View$OnClickListener;", "<init>", "()V", "getViewBinding", "initViewModel", "initView", "", "savedInstanceState", "Landroid/os/Bundle;", "initToolBar", "onClick", "v", "Landroid/view/View;", "toEditTemplate", "index", "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SelectTemplateActivity extends UiBaseActivity<TextAnimationModel, ActivitySelectTemplateBinding> implements View.OnClickListener {
    @Override // com.wifiled.baselib.uicode.base.UiBaseActivity
    public ActivitySelectTemplateBinding getViewBinding() {
        ActivitySelectTemplateBinding inflate = ActivitySelectTemplateBinding.inflate(getLayoutInflater());
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
        initToolBar();
        SelectTemplateActivity selectTemplateActivity = this;
        getBinding().tem1.setOnClickListener(selectTemplateActivity);
        getBinding().tem2.setOnClickListener(selectTemplateActivity);
        getBinding().tem3.setOnClickListener(selectTemplateActivity);
        getBinding().tem4.setOnClickListener(selectTemplateActivity);
        getBinding().tem5.setOnClickListener(selectTemplateActivity);
        getBinding().tem6.setOnClickListener(selectTemplateActivity);
        if (AppConfig.INSTANCE.getLedType() == 9) {
            String saveLanguage = LanguageUtil.getSaveLanguage(App.INSTANCE.getContext());
            Intrinsics.checkNotNullExpressionValue(saveLanguage, "getSaveLanguage(...)");
            if (StringsKt.contains$default((CharSequence) saveLanguage, (CharSequence) "ta", false, 2, (Object) null)) {
                getBinding().tem1.setVisibility(8);
                getBinding().tem4.setVisibility(8);
                getBinding().tem5.setVisibility(8);
            }
        }
    }

    private final void initToolBar() {
        getBinding().tvTitle.setText(getBaseContext().getString(R.string.select_template));
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
        if (Intrinsics.areEqual(v, getBinding().tem1)) {
            toEditTemplate(1);
            return;
        }
        if (Intrinsics.areEqual(v, getBinding().tem2)) {
            toEditTemplate(2);
            return;
        }
        if (Intrinsics.areEqual(v, getBinding().tem3)) {
            toEditTemplate(3);
            return;
        }
        if (Intrinsics.areEqual(v, getBinding().tem4)) {
            toEditTemplate(4);
        } else if (Intrinsics.areEqual(v, getBinding().tem5)) {
            toEditTemplate(5);
        } else if (Intrinsics.areEqual(v, getBinding().tem6)) {
            toEditTemplate(6);
        }
    }

    private final void toEditTemplate(int index) {
        Intent intent = new Intent(this, (Class<?>) EditTemplateActivity.class);
        intent.putExtra("template_type", index);
        startActivity(intent);
        finish();
    }
}
