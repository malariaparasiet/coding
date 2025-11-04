package com.wifiled.baselib.uicode.statuslayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.wifiled.baselib.R;
import com.wifiled.baselib.databinding.ViewStatusLayoutBinding;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DefaultStatusLayout.kt */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\u0010\r\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\f\u001a\u00020\r2\b\b\u0001\u0010\u000e\u001a\u00020\u0007J\u0010\u0010\u000f\u001a\u00020\r2\b\b\u0001\u0010\u0010\u001a\u00020\u0007J\u000e\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0013J\u0010\u0010\u0014\u001a\u00020\r2\b\b\u0001\u0010\u0015\u001a\u00020\u0007J\u0010\u0010\u0016\u001a\u00020\r2\b\b\u0001\u0010\u0015\u001a\u00020\u0007J\u0006\u0010\u0017\u001a\u00020\u0018J\u0006\u0010\u0019\u001a\u00020\u001aJ\u0010\u0010\u001b\u001a\u00020\r2\b\b\u0001\u0010\u0015\u001a\u00020\u0007J\u0010\u0010\u001c\u001a\u00020\r2\b\b\u0001\u0010\u001d\u001a\u00020\u0007J\u000e\u0010\u001e\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020 J\u0010\u0010!\u001a\u00020\r2\b\u0010\"\u001a\u0004\u0018\u00010#J\u0010\u0010$\u001a\u00020\r2\b\u0010\"\u001a\u0004\u0018\u00010#R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/wifiled/baselib/uicode/statuslayout/DefaultStatusLayout;", "Landroid/widget/LinearLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "binding", "Lcom/wifiled/baselib/databinding/ViewStatusLayoutBinding;", "setImage", "", "resId", "setTipText", "strId", "sequence", "", "", "setTipTextColor", "id", "setRefreshBackgroundColor", "getTextView", "Landroidx/appcompat/widget/AppCompatTextView;", "getImageView", "Landroidx/appcompat/widget/AppCompatImageView;", "setDefaultBackgroundColor", "setDefaultBackgroundResource", "resource", "setVisibleRefresh", "isShow", "", "setErrorClickListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Landroid/view/View$OnClickListener;", "setEmptyClickListener", "baselib_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DefaultStatusLayout extends LinearLayout {
    private final ViewStatusLayoutBinding binding;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DefaultStatusLayout(Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DefaultStatusLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ DefaultStatusLayout(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DefaultStatusLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        ViewStatusLayoutBinding inflate = ViewStatusLayoutBinding.inflate(LayoutInflater.from(context), this, true);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        this.binding = inflate;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.statusLayout);
        Intrinsics.checkNotNullExpressionValue(obtainStyledAttributes, "obtainStyledAttributes(...)");
        int resourceId = obtainStyledAttributes.getResourceId(R.styleable.statusLayout_imageSrc, -1);
        String string = obtainStyledAttributes.getString(R.styleable.statusLayout_tipText);
        int resourceId2 = obtainStyledAttributes.getResourceId(R.styleable.statusLayout_tipText, -1);
        obtainStyledAttributes.recycle();
        if (resourceId > 0) {
            setImage(resourceId);
        }
        if (!TextUtils.isEmpty(string)) {
            setTipText(string == null ? "" : string);
        } else if (resourceId2 != -1) {
            setTipText(resourceId2);
        }
    }

    public final void setImage(int resId) {
        this.binding.ivStatusImage.setImageResource(resId);
    }

    public final void setTipText(int strId) {
        this.binding.tvStatusText.setText(strId);
    }

    public final void setTipText(String sequence) {
        Intrinsics.checkNotNullParameter(sequence, "sequence");
        this.binding.tvStatusText.setText(sequence);
    }

    public final void setTipText(CharSequence sequence) {
        Intrinsics.checkNotNullParameter(sequence, "sequence");
        this.binding.tvStatusText.setText(sequence);
    }

    public final void setTipTextColor(int id) {
        this.binding.tvStatusText.setTextColor(ContextCompat.getColor(getContext(), id));
    }

    public final void setRefreshBackgroundColor(int id) {
        this.binding.rtvRefresh.getDelegate().setBackgroundColor(ContextCompat.getColor(getContext(), id));
    }

    public final AppCompatTextView getTextView() {
        AppCompatTextView tvStatusText = this.binding.tvStatusText;
        Intrinsics.checkNotNullExpressionValue(tvStatusText, "tvStatusText");
        return tvStatusText;
    }

    public final AppCompatImageView getImageView() {
        AppCompatImageView ivStatusImage = this.binding.ivStatusImage;
        Intrinsics.checkNotNullExpressionValue(ivStatusImage, "ivStatusImage");
        return ivStatusImage;
    }

    public final void setDefaultBackgroundColor(int id) {
        this.binding.llBaseRoot.setBackgroundColor(ContextCompat.getColor(getContext(), id));
    }

    public final void setDefaultBackgroundResource(int resource) {
        this.binding.llBaseRoot.setBackgroundResource(resource);
    }

    public final void setVisibleRefresh(boolean isShow) {
        this.binding.rtvRefresh.setVisibility(isShow ? 0 : 8);
    }

    public final void setErrorClickListener(View.OnClickListener listener) {
        this.binding.rtvRefresh.setOnClickListener(listener);
    }

    public final void setEmptyClickListener(View.OnClickListener listener) {
        setOnClickListener(listener);
    }
}
