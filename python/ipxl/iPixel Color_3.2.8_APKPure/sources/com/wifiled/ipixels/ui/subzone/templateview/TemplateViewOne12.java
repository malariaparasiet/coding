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
import com.blankj.utilcode.util.LogUtils;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.ui.channel.text.ObjectarxItem;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TemplateViewOne12.kt */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001d\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0018J\u001e\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020 J\u001e\u0010\"\u001a\u00020\u001a2\u0006\u0010#\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020 J\u000e\u0010$\u001a\u00020\u001a2\u0006\u0010%\u001a\u00020 J\u0006\u0010&\u001a\u00020\u001aR\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/wifiled/ipixels/ui/subzone/templateview/TemplateViewOne12;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "template1", "Landroid/widget/ImageView;", "tem1_tv1", "Landroid/widget/TextView;", "tem1_tv2", "tem1_tv1_text", "Landroid/widget/RelativeLayout;", "tem1_tv2_text", "tem1_tv1_text_rl", "Lcom/wifiled/ipixels/ui/channel/text/ObjectarxItem;", "tem1_border_view1", "Landroid/view/View;", "tem1_tv2_text_rl", "tem1_border_view2", "mView", "mOnClickListener", "Lcom/wifiled/ipixels/ui/subzone/templateview/OnTvClickListener;", "setOnClickTvClickListener", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "setData1", "textDataEven", "Lcom/wifiled/ipixels/ui/channel/ChannelListItem$TextEmojView;", "borderResource", "", "type", "setData2", "textData", "setBorder", "resource", "clean", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TemplateViewOne12 extends ConstraintLayout {
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
    private ImageView template1;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public TemplateViewOne12(Context context) {
        this(context, null, 2, 0 == true ? 1 : 0);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ TemplateViewOne12(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : attributeSet);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TemplateViewOne12(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        switch (AppConfig.INSTANCE.getLedType()) {
            case 9:
            case 10:
                this.mView = LayoutInflater.from(context).inflate(R.layout.view_template_one_1_2, (ViewGroup) this, true);
                break;
            case 11:
                this.mView = LayoutInflater.from(context).inflate(R.layout.view_template_one_1_3, (ViewGroup) this, true);
                break;
            case 12:
                this.mView = LayoutInflater.from(context).inflate(R.layout.view_template_one_1_4, (ViewGroup) this, true);
                break;
            case 14:
                this.mView = LayoutInflater.from(context).inflate(R.layout.view_template_one_1_5, (ViewGroup) this, true);
                break;
            case 15:
                this.mView = LayoutInflater.from(context).inflate(R.layout.view_template_one_1_6, (ViewGroup) this, true);
                break;
            case 16:
                this.mView = LayoutInflater.from(context).inflate(R.layout.view_template_one_1_8, (ViewGroup) this, true);
                break;
            case 17:
                this.mView = LayoutInflater.from(context).inflate(R.layout.view_template_one_1_10, (ViewGroup) this, true);
                break;
            case 18:
                this.mView = LayoutInflater.from(context).inflate(R.layout.view_template_one_1_12, (ViewGroup) this, true);
                break;
            case 19:
                this.mView = LayoutInflater.from(context).inflate(R.layout.view_template_one_1_14, (ViewGroup) this, true);
                break;
        }
        View view = this.mView;
        Intrinsics.checkNotNull(view);
        View findViewById = view.findViewById(R.id.border_img);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.template1 = (ImageView) findViewById;
        View view2 = this.mView;
        Intrinsics.checkNotNull(view2);
        View findViewById2 = view2.findViewById(R.id.tem1_tv1);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.tem1_tv1 = (TextView) findViewById2;
        View view3 = this.mView;
        Intrinsics.checkNotNull(view3);
        View findViewById3 = view3.findViewById(R.id.tem1_tv2);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.tem1_tv2 = (TextView) findViewById3;
        View view4 = this.mView;
        Intrinsics.checkNotNull(view4);
        View findViewById4 = view4.findViewById(R.id.tem1_tv1_text);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
        this.tem1_tv1_text = (RelativeLayout) findViewById4;
        View view5 = this.mView;
        Intrinsics.checkNotNull(view5);
        View findViewById5 = view5.findViewById(R.id.tem1_tv2_text);
        Intrinsics.checkNotNullExpressionValue(findViewById5, "findViewById(...)");
        this.tem1_tv2_text = (RelativeLayout) findViewById5;
        View view6 = this.mView;
        Intrinsics.checkNotNull(view6);
        View findViewById6 = view6.findViewById(R.id.tem1_border_view2);
        Intrinsics.checkNotNullExpressionValue(findViewById6, "findViewById(...)");
        this.tem1_border_view2 = findViewById6;
        View view7 = this.mView;
        Intrinsics.checkNotNull(view7);
        View findViewById7 = view7.findViewById(R.id.tem1_border_view1);
        Intrinsics.checkNotNullExpressionValue(findViewById7, "findViewById(...)");
        this.tem1_border_view1 = findViewById7;
    }

    public final void setOnClickTvClickListener(OnTvClickListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.mOnClickListener = listener;
        this.tem1_tv1.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.subzone.templateview.TemplateViewOne12$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TemplateViewOne12.setOnClickTvClickListener$lambda$0(TemplateViewOne12.this, view);
            }
        });
        this.tem1_tv2.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.subzone.templateview.TemplateViewOne12$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TemplateViewOne12.setOnClickTvClickListener$lambda$1(TemplateViewOne12.this, view);
            }
        });
        this.tem1_tv1_text.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.subzone.templateview.TemplateViewOne12$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TemplateViewOne12.setOnClickTvClickListener$lambda$2(TemplateViewOne12.this, view);
            }
        });
        this.tem1_tv2_text.setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.ipixels.ui.subzone.templateview.TemplateViewOne12$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TemplateViewOne12.setOnClickTvClickListener$lambda$3(TemplateViewOne12.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setOnClickTvClickListener$lambda$0(TemplateViewOne12 templateViewOne12, View view) {
        OnTvClickListener onTvClickListener = templateViewOne12.mOnClickListener;
        if (onTvClickListener != null) {
            Intrinsics.checkNotNull(view);
            onTvClickListener.onTvClick1(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setOnClickTvClickListener$lambda$1(TemplateViewOne12 templateViewOne12, View view) {
        OnTvClickListener onTvClickListener = templateViewOne12.mOnClickListener;
        if (onTvClickListener != null) {
            Intrinsics.checkNotNull(view);
            onTvClickListener.onTvClick2(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setOnClickTvClickListener$lambda$2(TemplateViewOne12 templateViewOne12, View view) {
        OnTvClickListener onTvClickListener = templateViewOne12.mOnClickListener;
        if (onTvClickListener != null) {
            Intrinsics.checkNotNull(view);
            onTvClickListener.onTvClick1(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setOnClickTvClickListener$lambda$3(TemplateViewOne12 templateViewOne12, View view) {
        OnTvClickListener onTvClickListener = templateViewOne12.mOnClickListener;
        if (onTvClickListener != null) {
            Intrinsics.checkNotNull(view);
            onTvClickListener.onTvClick2(view);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00e3, code lost:
    
        if (com.blankj.utilcode.util.ScreenUtils.getScreenWidth() > 1080) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0109, code lost:
    
        r2 = 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00ed, code lost:
    
        if (com.blankj.utilcode.util.ScreenUtils.getScreenWidth() > 1080) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00f6, code lost:
    
        if (com.blankj.utilcode.util.ScreenUtils.getScreenWidth() > 1080) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00fd, code lost:
    
        if (com.blankj.utilcode.util.ScreenUtils.getScreenWidth() > 1080) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0106, code lost:
    
        if (com.blankj.utilcode.util.ScreenUtils.getScreenWidth() > 1080) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x010f, code lost:
    
        if (com.blankj.utilcode.util.ScreenUtils.getScreenWidth() > 1080) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0118, code lost:
    
        if (com.blankj.utilcode.util.ScreenUtils.getScreenWidth() > 1080) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x014d, code lost:
    
        if (com.blankj.utilcode.util.ScreenUtils.getScreenWidth() > 1080) goto L79;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void setData1(com.wifiled.ipixels.ui.channel.ChannelListItem.TextEmojView r11, int r12, int r13) {
        /*
            Method dump skipped, instructions count: 372
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wifiled.ipixels.ui.subzone.templateview.TemplateViewOne12.setData1(com.wifiled.ipixels.ui.channel.ChannelListItem$TextEmojView, int, int):void");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0107, code lost:
    
        if (com.blankj.utilcode.util.ScreenUtils.getScreenWidth() > 1080) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x012d, code lost:
    
        r2 = 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0111, code lost:
    
        if (com.blankj.utilcode.util.ScreenUtils.getScreenWidth() > 1080) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x011a, code lost:
    
        if (com.blankj.utilcode.util.ScreenUtils.getScreenWidth() > 1080) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0121, code lost:
    
        if (com.blankj.utilcode.util.ScreenUtils.getScreenWidth() > 1080) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x012a, code lost:
    
        if (com.blankj.utilcode.util.ScreenUtils.getScreenWidth() > 1080) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0133, code lost:
    
        if (com.blankj.utilcode.util.ScreenUtils.getScreenWidth() > 1080) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x013c, code lost:
    
        if (com.blankj.utilcode.util.ScreenUtils.getScreenWidth() > 1080) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x0171, code lost:
    
        if (com.blankj.utilcode.util.ScreenUtils.getScreenWidth() > 1080) goto L83;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void setData2(com.wifiled.ipixels.ui.channel.ChannelListItem.TextEmojView r11, int r12, int r13) {
        /*
            Method dump skipped, instructions count: 408
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wifiled.ipixels.ui.subzone.templateview.TemplateViewOne12.setData2(com.wifiled.ipixels.ui.channel.ChannelListItem$TextEmojView, int, int):void");
    }

    public final void setBorder(int resource) {
        LogUtils.vTag("ruis", "resource ---" + resource);
        this.template1.setBackgroundResource(resource);
    }

    public final void clean() {
        this.mOnClickListener = null;
    }
}
