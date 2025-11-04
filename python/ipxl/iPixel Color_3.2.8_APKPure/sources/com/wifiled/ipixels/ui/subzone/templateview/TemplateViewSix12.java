package com.wifiled.ipixels.ui.subzone.templateview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.bumptech.glide.Glide;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.wifiled.ipixels.R;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TemplateViewSix12.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u001d\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000fJ\u000e\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0015J\u000e\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0015J\u000e\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0017J\u000e\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0017J\u000e\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u001aR\u000e\u0010\b\u001a\u00020\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/wifiled/ipixels/ui/subzone/templateview/TemplateViewSix12;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "template", "tem_iv1", "Landroid/widget/ImageView;", "tem_iv2", "mView", "Landroid/view/View;", "mOnClickListener", "Lcom/wifiled/ipixels/ui/subzone/templateview/OnTvClickListener;", "setOnClickTvClickListener", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "setData1", "data", "", "setData2", "Ljava/io/File;", "setBorder", "resource", "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TemplateViewSix12 extends ConstraintLayout {
    private OnTvClickListener mOnClickListener;
    private View mView;
    private ImageView tem_iv1;
    private ImageView tem_iv2;
    private ConstraintLayout template;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public TemplateViewSix12(Context context) {
        this(context, null, 2, 0 == true ? 1 : 0);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ TemplateViewSix12(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : attributeSet);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TemplateViewSix12(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        View inflate = LayoutInflater.from(context).inflate(R.layout.view_template_six_1_2, (ViewGroup) this, true);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        this.mView = inflate;
        View findViewById = inflate.findViewById(R.id.template);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.template = (ConstraintLayout) findViewById;
        View findViewById2 = this.mView.findViewById(R.id.tem6_iv1);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.tem_iv1 = (ImageView) findViewById2;
        View findViewById3 = this.mView.findViewById(R.id.tem6_iv2);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.tem_iv2 = (ImageView) findViewById3;
        this.tem_iv1.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.subzone.templateview.TemplateViewSix12$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TemplateViewSix12._init_$lambda$0(TemplateViewSix12.this, view);
            }
        });
        this.tem_iv2.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.subzone.templateview.TemplateViewSix12$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TemplateViewSix12._init_$lambda$1(TemplateViewSix12.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$0(TemplateViewSix12 templateViewSix12, View view) {
        OnTvClickListener onTvClickListener = templateViewSix12.mOnClickListener;
        if (onTvClickListener != null) {
            Intrinsics.checkNotNull(view);
            onTvClickListener.onIvClick1(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$1(TemplateViewSix12 templateViewSix12, View view) {
        OnTvClickListener onTvClickListener = templateViewSix12.mOnClickListener;
        if (onTvClickListener != null) {
            Intrinsics.checkNotNull(view);
            onTvClickListener.onIvClick2(view);
        }
    }

    public final void setOnClickTvClickListener(OnTvClickListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.mOnClickListener = listener;
    }

    public final void setData1(byte[] data) {
        Intrinsics.checkNotNullParameter(data, "data");
        Glide.with(this).load(data).into(this.tem_iv1);
    }

    public final void setData2(byte[] data) {
        Intrinsics.checkNotNullParameter(data, "data");
        Glide.with(this).load(data).into(this.tem_iv2);
    }

    public final void setData1(File data) {
        Intrinsics.checkNotNullParameter(data, "data");
        Glide.with(this).load(data).into(this.tem_iv1);
    }

    public final void setData2(File data) {
        Intrinsics.checkNotNullParameter(data, "data");
        Glide.with(this).load(data).into(this.tem_iv2);
    }

    public final void setBorder(int resource) {
        this.template.setBackgroundResource(resource);
    }
}
