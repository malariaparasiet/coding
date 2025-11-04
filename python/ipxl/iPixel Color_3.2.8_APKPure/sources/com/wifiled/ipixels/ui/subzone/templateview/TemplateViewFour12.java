package com.wifiled.ipixels.ui.subzone.templateview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.bumptech.glide.Glide;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.ui.channel.text.ObjectarxItem;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TemplateViewFour12.kt */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001d\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0019J\u0006\u0010\u001d\u001a\u00020\u001bJ\u001e\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\"J\u001e\u0010$\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\"J\u000e\u0010%\u001a\u00020\u001b2\u0006\u0010&\u001a\u00020\"J\u000e\u0010'\u001a\u00020\u001b2\u0006\u0010(\u001a\u00020)J\u000e\u0010'\u001a\u00020\u001b2\u0006\u0010(\u001a\u00020*R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lcom/wifiled/ipixels/ui/subzone/templateview/TemplateViewFour12;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "template1", "Landroid/view/View;", "tem1_tv1", "Landroid/widget/TextView;", "tem1_tv2", "tem1_tv1_text", "Landroid/widget/RelativeLayout;", "tem1_tv2_text", "tem1_tv1_text_rl", "Lcom/wifiled/ipixels/ui/channel/text/ObjectarxItem;", "tem1_border_view1", "tem1_tv2_text_rl", "tem1_border_view2", "tem4_iv1", "Landroid/widget/ImageView;", "mView", "mOnClickListener", "Lcom/wifiled/ipixels/ui/subzone/templateview/OnTvClickListener;", "setOnClickTvClickListener", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "clean", "setData1", "textDataEven", "Lcom/wifiled/ipixels/ui/channel/ChannelListItem$TextEmojView;", "borderResource", "", "type", "setData2", "setBorder", "resource", "setImageData", "data", "", "Ljava/io/File;", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TemplateViewFour12 extends ConstraintLayout {
    private OnTvClickListener mOnClickListener;
    private View mView;
    private View tem1_border_view1;
    private View tem1_border_view2;
    private TextView tem1_tv1;
    private RelativeLayout tem1_tv1_text;
    private ObjectarxItem tem1_tv1_text_rl;
    private TextView tem1_tv2;
    private RelativeLayout tem1_tv2_text;
    private ObjectarxItem tem1_tv2_text_rl;
    private ImageView tem4_iv1;
    private View template1;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public TemplateViewFour12(Context context) {
        this(context, null, 2, 0 == true ? 1 : 0);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ TemplateViewFour12(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : attributeSet);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TemplateViewFour12(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        switch (AppConfig.INSTANCE.getLedType()) {
            case 9:
            case 10:
                this.mView = LayoutInflater.from(context).inflate(R.layout.view_template_four_1_2, (ViewGroup) this, true);
                break;
            case 11:
                this.mView = LayoutInflater.from(context).inflate(R.layout.view_template_four_1_3, (ViewGroup) this, true);
                break;
            case 12:
                this.mView = LayoutInflater.from(context).inflate(R.layout.view_template_four_1_4, (ViewGroup) this, true);
                break;
            case 14:
                this.mView = LayoutInflater.from(context).inflate(R.layout.view_template_four_1_5, (ViewGroup) this, true);
                break;
            case 15:
                this.mView = LayoutInflater.from(context).inflate(R.layout.view_template_four_1_6, (ViewGroup) this, true);
                break;
            case 16:
                this.mView = LayoutInflater.from(context).inflate(R.layout.view_template_four_1_8, (ViewGroup) this, true);
                break;
            case 17:
                this.mView = LayoutInflater.from(context).inflate(R.layout.view_template_four_1_10, (ViewGroup) this, true);
                break;
            case 18:
                this.mView = LayoutInflater.from(context).inflate(R.layout.view_template_four_1_12, (ViewGroup) this, true);
                break;
            case 19:
                this.mView = LayoutInflater.from(context).inflate(R.layout.view_template_four_1_14, (ViewGroup) this, true);
                break;
        }
        View view = this.mView;
        Intrinsics.checkNotNull(view);
        View findViewById = view.findViewById(R.id.tem4_border);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.template1 = findViewById;
        View view2 = this.mView;
        Intrinsics.checkNotNull(view2);
        View findViewById2 = view2.findViewById(R.id.tem4_tv1);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.tem1_tv1 = (TextView) findViewById2;
        View view3 = this.mView;
        Intrinsics.checkNotNull(view3);
        View findViewById3 = view3.findViewById(R.id.tem4_tv2);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.tem1_tv2 = (TextView) findViewById3;
        View view4 = this.mView;
        Intrinsics.checkNotNull(view4);
        View findViewById4 = view4.findViewById(R.id.tem4_tv1_text);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
        this.tem1_tv1_text = (RelativeLayout) findViewById4;
        View view5 = this.mView;
        Intrinsics.checkNotNull(view5);
        View findViewById5 = view5.findViewById(R.id.tem4_tv2_text);
        Intrinsics.checkNotNullExpressionValue(findViewById5, "findViewById(...)");
        this.tem1_tv2_text = (RelativeLayout) findViewById5;
        View view6 = this.mView;
        Intrinsics.checkNotNull(view6);
        View findViewById6 = view6.findViewById(R.id.tem4_tv1_text_rl);
        Intrinsics.checkNotNullExpressionValue(findViewById6, "findViewById(...)");
        this.tem1_tv1_text_rl = (ObjectarxItem) findViewById6;
        View view7 = this.mView;
        Intrinsics.checkNotNull(view7);
        View findViewById7 = view7.findViewById(R.id.tem4_tv2_text_rl);
        Intrinsics.checkNotNullExpressionValue(findViewById7, "findViewById(...)");
        this.tem1_tv2_text_rl = (ObjectarxItem) findViewById7;
        View view8 = this.mView;
        Intrinsics.checkNotNull(view8);
        View findViewById8 = view8.findViewById(R.id.tem4_border_view2);
        Intrinsics.checkNotNullExpressionValue(findViewById8, "findViewById(...)");
        this.tem1_border_view2 = findViewById8;
        View view9 = this.mView;
        Intrinsics.checkNotNull(view9);
        View findViewById9 = view9.findViewById(R.id.tem4_border_view1);
        Intrinsics.checkNotNullExpressionValue(findViewById9, "findViewById(...)");
        this.tem1_border_view1 = findViewById9;
        View view10 = this.mView;
        Intrinsics.checkNotNull(view10);
        View findViewById10 = view10.findViewById(R.id.tem4_iv1);
        Intrinsics.checkNotNullExpressionValue(findViewById10, "findViewById(...)");
        this.tem4_iv1 = (ImageView) findViewById10;
    }

    public final void setOnClickTvClickListener(OnTvClickListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.mOnClickListener = listener;
        this.tem1_tv1.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.subzone.templateview.TemplateViewFour12$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TemplateViewFour12.setOnClickTvClickListener$lambda$0(TemplateViewFour12.this, view);
            }
        });
        this.tem1_tv2.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.subzone.templateview.TemplateViewFour12$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TemplateViewFour12.setOnClickTvClickListener$lambda$1(TemplateViewFour12.this, view);
            }
        });
        this.tem1_tv1_text.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.subzone.templateview.TemplateViewFour12$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TemplateViewFour12.setOnClickTvClickListener$lambda$2(TemplateViewFour12.this, view);
            }
        });
        this.tem1_tv2_text.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.subzone.templateview.TemplateViewFour12$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TemplateViewFour12.setOnClickTvClickListener$lambda$3(TemplateViewFour12.this, view);
            }
        });
        this.tem4_iv1.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.subzone.templateview.TemplateViewFour12$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TemplateViewFour12.setOnClickTvClickListener$lambda$4(TemplateViewFour12.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setOnClickTvClickListener$lambda$0(TemplateViewFour12 templateViewFour12, View view) {
        OnTvClickListener onTvClickListener = templateViewFour12.mOnClickListener;
        if (onTvClickListener != null) {
            Intrinsics.checkNotNull(view);
            onTvClickListener.onTvClick1(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setOnClickTvClickListener$lambda$1(TemplateViewFour12 templateViewFour12, View view) {
        OnTvClickListener onTvClickListener = templateViewFour12.mOnClickListener;
        if (onTvClickListener != null) {
            Intrinsics.checkNotNull(view);
            onTvClickListener.onTvClick2(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setOnClickTvClickListener$lambda$2(TemplateViewFour12 templateViewFour12, View view) {
        OnTvClickListener onTvClickListener = templateViewFour12.mOnClickListener;
        if (onTvClickListener != null) {
            Intrinsics.checkNotNull(view);
            onTvClickListener.onTvClick1(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setOnClickTvClickListener$lambda$3(TemplateViewFour12 templateViewFour12, View view) {
        OnTvClickListener onTvClickListener = templateViewFour12.mOnClickListener;
        if (onTvClickListener != null) {
            Intrinsics.checkNotNull(view);
            onTvClickListener.onTvClick2(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setOnClickTvClickListener$lambda$4(TemplateViewFour12 templateViewFour12, View view) {
        OnTvClickListener onTvClickListener = templateViewFour12.mOnClickListener;
        if (onTvClickListener != null) {
            Intrinsics.checkNotNull(view);
            onTvClickListener.onIvClick1(view);
        }
    }

    public final void clean() {
        this.mOnClickListener = null;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00cb, code lost:
    
        if (com.blankj.utilcode.util.ScreenUtils.getScreenWidth() > 1080) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00f1, code lost:
    
        r0 = 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00d5, code lost:
    
        if (com.blankj.utilcode.util.ScreenUtils.getScreenWidth() > 1080) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00de, code lost:
    
        if (com.blankj.utilcode.util.ScreenUtils.getScreenWidth() > 1080) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00e5, code lost:
    
        if (com.blankj.utilcode.util.ScreenUtils.getScreenWidth() > 1080) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00ee, code lost:
    
        if (com.blankj.utilcode.util.ScreenUtils.getScreenWidth() > 1080) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00f7, code lost:
    
        if (com.blankj.utilcode.util.ScreenUtils.getScreenWidth() > 1080) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0100, code lost:
    
        if (com.blankj.utilcode.util.ScreenUtils.getScreenWidth() > 1080) goto L60;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void setData1(com.wifiled.ipixels.ui.channel.ChannelListItem.TextEmojView r10, int r11, int r12) {
        /*
            Method dump skipped, instructions count: 346
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wifiled.ipixels.ui.subzone.templateview.TemplateViewFour12.setData1(com.wifiled.ipixels.ui.channel.ChannelListItem$TextEmojView, int, int):void");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00cb, code lost:
    
        if (com.blankj.utilcode.util.ScreenUtils.getScreenWidth() > 1080) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00f1, code lost:
    
        r0 = 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00d5, code lost:
    
        if (com.blankj.utilcode.util.ScreenUtils.getScreenWidth() > 1080) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00de, code lost:
    
        if (com.blankj.utilcode.util.ScreenUtils.getScreenWidth() > 1080) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00e5, code lost:
    
        if (com.blankj.utilcode.util.ScreenUtils.getScreenWidth() > 1080) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00ee, code lost:
    
        if (com.blankj.utilcode.util.ScreenUtils.getScreenWidth() > 1080) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00f7, code lost:
    
        if (com.blankj.utilcode.util.ScreenUtils.getScreenWidth() > 1080) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0100, code lost:
    
        if (com.blankj.utilcode.util.ScreenUtils.getScreenWidth() > 1080) goto L60;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void setData2(com.wifiled.ipixels.ui.channel.ChannelListItem.TextEmojView r10, int r11, int r12) {
        /*
            Method dump skipped, instructions count: 346
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wifiled.ipixels.ui.subzone.templateview.TemplateViewFour12.setData2(com.wifiled.ipixels.ui.channel.ChannelListItem$TextEmojView, int, int):void");
    }

    public final void setBorder(int resource) {
        this.template1.setBackgroundResource(resource);
    }

    public final void setImageData(byte[] data) {
        Intrinsics.checkNotNullParameter(data, "data");
        Glide.with(this).load(data).into(this.tem4_iv1);
    }

    public final void setImageData(File data) {
        Intrinsics.checkNotNullParameter(data, "data");
        Glide.with(this).load(data).into(this.tem4_iv1);
    }
}
