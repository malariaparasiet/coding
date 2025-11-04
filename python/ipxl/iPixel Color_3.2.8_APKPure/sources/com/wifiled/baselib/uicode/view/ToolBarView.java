package com.wifiled.baselib.uicode.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.content.ContextCompat;
import com.blankj.utilcode.util.SizeUtils;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.wifiled.baselib.R;
import com.wifiled.baselib.uicode.inner.OnToolBarClickListener;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.i18n.TextBundle;

/* compiled from: ToolBarView.kt */
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u0011\n\u0002\b\u0016\u0018\u00002\u00020\u00012\u00020\u0002:\u0002DEB'\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u000e\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u0014J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0018\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u0018\u0010\u001f\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010 \u001a\u00020\bH\u0002J\u0018\u0010\u001f\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\"H\u0002J\u001a\u0010#\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010$\u001a\u0004\u0018\u00010%H\u0002J\u0018\u0010&\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010'\u001a\u00020\bH\u0002J\u001a\u0010(\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u001c2\b\b\u0001\u0010)\u001a\u00020\bH\u0002J\u001a\u0010*\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u001c2\b\b\u0001\u0010)\u001a\u00020\bH\u0002J\u0012\u0010+\u001a\u00020\b2\b\b\u0001\u0010)\u001a\u00020\bH\u0003J\u0012\u0010,\u001a\u0004\u0018\u00010\u00122\u0006\u0010-\u001a\u00020\u001cH\u0002J'\u0010.\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u00192\u0012\u0010/\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001c00\"\u00020\u001c¢\u0006\u0002\u00101J\u0010\u00102\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0010\u00103\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0010\u00104\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0010\u00105\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0018\u00106\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0010\u00107\u001a\u00020\u00002\b\b\u0001\u00108\u001a\u00020\bJ\u0010\u00107\u001a\u00020\u00002\b\u0010$\u001a\u0004\u0018\u00010%J\u0010\u00109\u001a\u00020\u00002\b\b\u0001\u0010)\u001a\u00020\bJ\u0010\u0010:\u001a\u00020\u00002\b\b\u0001\u0010)\u001a\u00020\bJ\u0010\u0010;\u001a\u00020\u00002\b\b\u0001\u00108\u001a\u00020\bJ\u0010\u0010;\u001a\u00020\u00002\b\u0010$\u001a\u0004\u0018\u00010%J\u0010\u0010<\u001a\u00020\u00002\b\b\u0001\u0010)\u001a\u00020\bJ\u0010\u0010=\u001a\u00020\u00002\b\b\u0001\u0010)\u001a\u00020\bJ\u000e\u0010>\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u001eJ\u000e\u0010?\u001a\u00020\u00002\u0006\u0010!\u001a\u00020\"J\u0010\u0010?\u001a\u00020\u00002\b\b\u0001\u0010@\u001a\u00020\bJ\u000e\u0010A\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u0019J\u0010\u0010B\u001a\u00020\u00172\u0006\u0010C\u001a\u00020\u0012H\u0016R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006F"}, d2 = {"Lcom/wifiled/baselib/uicode/view/ToolBarView;", "Landroidx/appcompat/widget/Toolbar;", "Landroid/view/View$OnClickListener;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "imgLeft", "Landroidx/appcompat/widget/AppCompatImageView;", "tvCenter", "Landroidx/appcompat/widget/AppCompatTextView;", "tvRight", "imgRight", "bottomLine", "Landroid/view/View;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/wifiled/baselib/uicode/inner/OnToolBarClickListener;", "setToolBarClickListener", "showLine", "", "isVisible", "", "setImageScaleType", "type", "Lcom/wifiled/baselib/uicode/view/ToolBarView$ViewType;", "scaleType", "Landroid/widget/ImageView$ScaleType;", "setImageView", "src", "bm", "Landroid/graphics/Bitmap;", "setText", TextBundle.TEXT_ENTRY, "", "setTextPxSize", "size", "setTextColor", TypedValues.Custom.S_COLOR, "setTextColorInt", "getResourcesColor", "getView", "viewType", "setToolBarViewVisible", "events", "", "(Z[Lcom/wifiled/baselib/uicode/view/ToolBarView$ViewType;)Lcom/wifiled/baselib/uicode/view/ToolBarView;", "setLeftImageVisible", "setCenterTextVisible", "setRightTextVisible", "setRightImageVisible", "setVisible", "setCenterText", "resId", "setCenterTextColor", "setCenterTextColorInt", "setRightText", "setRightTextColor", "setRightTextColorInt", "setRightImageScaleType", "setRightImage", "drawable", "showBottomLine", "onClick", "v", "ToolBarBg", "ViewType", "baselib_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ToolBarView extends Toolbar implements View.OnClickListener {
    private View bottomLine;
    private AppCompatImageView imgLeft;
    private AppCompatImageView imgRight;
    private OnToolBarClickListener listener;
    private AppCompatTextView tvCenter;
    private AppCompatTextView tvRight;

    /* compiled from: ToolBarView.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ViewType.values().length];
            try {
                iArr[ViewType.LEFT_IMAGE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ViewType.CENTER_TEXT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ViewType.RIGHT_IMAGE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[ViewType.RIGHT_TEXT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ToolBarView(Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ToolBarView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ ToolBarView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ToolBarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        View inflate = LayoutInflater.from(context).inflate(R.layout.view_toolbar, (ViewGroup) this, true);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.toolbarView);
        Intrinsics.checkNotNullExpressionValue(obtainStyledAttributes, "obtainStyledAttributes(...)");
        String string = obtainStyledAttributes.getString(R.styleable.toolbarView_centerText);
        int resourceId = obtainStyledAttributes.getResourceId(R.styleable.toolbarView_centerText, 0);
        int color = obtainStyledAttributes.getColor(R.styleable.toolbarView_centerTextColor, 0);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.toolbarView_centerTextSize, SizeUtils.sp2px(18.0f));
        String string2 = obtainStyledAttributes.getString(R.styleable.toolbarView_rightText);
        int resourceId2 = obtainStyledAttributes.getResourceId(R.styleable.toolbarView_rightText, 0);
        int color2 = obtainStyledAttributes.getColor(R.styleable.toolbarView_rightTextColor, 0);
        int dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.toolbarView_rightTextSize, SizeUtils.sp2px(16.0f));
        int resourceId3 = obtainStyledAttributes.getResourceId(R.styleable.toolbarView_rightImageView, 0);
        int resourceId4 = obtainStyledAttributes.getResourceId(R.styleable.toolbarView_leftImageView, 0);
        boolean z = obtainStyledAttributes.getBoolean(R.styleable.toolbarView_showBottomLine, false);
        obtainStyledAttributes.recycle();
        View findViewById = inflate.findViewById(R.id.img_left);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.imgLeft = (AppCompatImageView) findViewById;
        View findViewById2 = inflate.findViewById(R.id.tv_center);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.tvCenter = (AppCompatTextView) findViewById2;
        View findViewById3 = inflate.findViewById(R.id.tv_right);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.tvRight = (AppCompatTextView) findViewById3;
        View findViewById4 = inflate.findViewById(R.id.img_right);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
        this.imgRight = (AppCompatImageView) findViewById4;
        View findViewById5 = inflate.findViewById(R.id.bottom_line);
        Intrinsics.checkNotNullExpressionValue(findViewById5, "findViewById(...)");
        this.bottomLine = findViewById5;
        ToolBarView toolBarView = this;
        this.imgLeft.setOnClickListener(toolBarView);
        this.tvRight.setOnClickListener(toolBarView);
        this.imgRight.setOnClickListener(toolBarView);
        showLine(z);
        if (!TextUtils.isEmpty(string)) {
            setText(ViewType.CENTER_TEXT, string);
        } else if (resourceId != 0) {
            setText(ViewType.CENTER_TEXT, context.getResources().getString(resourceId));
        }
        setTextPxSize(ViewType.CENTER_TEXT, dimensionPixelSize);
        if (color != 0) {
            setTextColorInt(ViewType.CENTER_TEXT, color);
        }
        if (!TextUtils.isEmpty(string2)) {
            setText(ViewType.RIGHT_TEXT, string2);
        } else if (resourceId2 != 0) {
            setText(ViewType.RIGHT_TEXT, context.getResources().getString(resourceId2));
        }
        setTextPxSize(ViewType.RIGHT_TEXT, dimensionPixelSize2);
        if (color2 != 0) {
            setTextColorInt(ViewType.RIGHT_TEXT, color2);
        }
        if (resourceId4 != 0) {
            setImageView(ViewType.LEFT_IMAGE, resourceId4);
        }
        if (resourceId3 != 0) {
            setImageView(ViewType.RIGHT_IMAGE, resourceId3);
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: ToolBarView.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\f\b\u0086\u0081\u0002\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\r¨\u0006\u000e"}, d2 = {"Lcom/wifiled/baselib/uicode/view/ToolBarView$ToolBarBg;", "Ljava/io/Serializable;", "", "<init>", "(Ljava/lang/String;I)V", "GRA_YELLOW", "GRA_RED", "YELLOW", "RED", "WHITE", "GRAY", "PURPLE", "BLACK", "ORANGE", "baselib_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class ToolBarBg implements Serializable {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ ToolBarBg[] $VALUES;
        public static final ToolBarBg GRA_YELLOW = new ToolBarBg("GRA_YELLOW", 0);
        public static final ToolBarBg GRA_RED = new ToolBarBg("GRA_RED", 1);
        public static final ToolBarBg YELLOW = new ToolBarBg("YELLOW", 2);
        public static final ToolBarBg RED = new ToolBarBg("RED", 3);
        public static final ToolBarBg WHITE = new ToolBarBg("WHITE", 4);
        public static final ToolBarBg GRAY = new ToolBarBg("GRAY", 5);
        public static final ToolBarBg PURPLE = new ToolBarBg("PURPLE", 6);
        public static final ToolBarBg BLACK = new ToolBarBg("BLACK", 7);
        public static final ToolBarBg ORANGE = new ToolBarBg("ORANGE", 8);

        private static final /* synthetic */ ToolBarBg[] $values() {
            return new ToolBarBg[]{GRA_YELLOW, GRA_RED, YELLOW, RED, WHITE, GRAY, PURPLE, BLACK, ORANGE};
        }

        public static EnumEntries<ToolBarBg> getEntries() {
            return $ENTRIES;
        }

        public static ToolBarBg valueOf(String str) {
            return (ToolBarBg) Enum.valueOf(ToolBarBg.class, str);
        }

        public static ToolBarBg[] values() {
            return (ToolBarBg[]) $VALUES.clone();
        }

        private ToolBarBg(String str, int i) {
        }

        static {
            ToolBarBg[] $values = $values();
            $VALUES = $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: ToolBarView.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/wifiled/baselib/uicode/view/ToolBarView$ViewType;", "", "<init>", "(Ljava/lang/String;I)V", "LEFT_IMAGE", "CENTER_TEXT", "RIGHT_TEXT", "RIGHT_IMAGE", "baselib_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class ViewType {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ ViewType[] $VALUES;
        public static final ViewType LEFT_IMAGE = new ViewType("LEFT_IMAGE", 0);
        public static final ViewType CENTER_TEXT = new ViewType("CENTER_TEXT", 1);
        public static final ViewType RIGHT_TEXT = new ViewType("RIGHT_TEXT", 2);
        public static final ViewType RIGHT_IMAGE = new ViewType("RIGHT_IMAGE", 3);

        private static final /* synthetic */ ViewType[] $values() {
            return new ViewType[]{LEFT_IMAGE, CENTER_TEXT, RIGHT_TEXT, RIGHT_IMAGE};
        }

        public static EnumEntries<ViewType> getEntries() {
            return $ENTRIES;
        }

        public static ViewType valueOf(String str) {
            return (ViewType) Enum.valueOf(ViewType.class, str);
        }

        public static ViewType[] values() {
            return (ViewType[]) $VALUES.clone();
        }

        private ViewType(String str, int i) {
        }

        static {
            ViewType[] $values = $values();
            $VALUES = $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }
    }

    public final ToolBarView setToolBarClickListener(OnToolBarClickListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
        return this;
    }

    private final void showLine(boolean isVisible) {
        this.bottomLine.setVisibility(isVisible ? 0 : 8);
    }

    private final void setImageScaleType(ViewType type, ImageView.ScaleType scaleType) {
        AppCompatImageView appCompatImageView = (AppCompatImageView) getView(type);
        if (appCompatImageView != null) {
            appCompatImageView.setScaleType(scaleType);
        }
    }

    private final void setImageView(ViewType type, int src) {
        AppCompatImageView appCompatImageView = (AppCompatImageView) getView(type);
        if (appCompatImageView != null) {
            appCompatImageView.setVisibility(0);
        }
        if (appCompatImageView != null) {
            appCompatImageView.setImageResource(src);
        }
    }

    private final void setImageView(ViewType type, Bitmap bm) {
        AppCompatImageView appCompatImageView = (AppCompatImageView) getView(type);
        if (appCompatImageView != null) {
            appCompatImageView.setVisibility(0);
        }
        if (appCompatImageView != null) {
            appCompatImageView.setImageBitmap(bm);
        }
    }

    private final void setText(ViewType type, String text) {
        AppCompatTextView appCompatTextView = (AppCompatTextView) getView(type);
        if (appCompatTextView != null) {
            appCompatTextView.setVisibility(0);
        }
        if (appCompatTextView != null) {
            if (text == null) {
                text = "";
            }
            appCompatTextView.setText(text);
        }
    }

    private final void setTextPxSize(ViewType type, int size) {
        AppCompatTextView appCompatTextView = (AppCompatTextView) getView(type);
        Intrinsics.checkNotNull(appCompatTextView);
        appCompatTextView.setTextSize(0, size);
    }

    private final void setTextColor(ViewType type, int color) {
        setTextColorInt(type, getResourcesColor(color));
    }

    private final void setTextColorInt(ViewType type, int color) {
        AppCompatTextView appCompatTextView = (AppCompatTextView) getView(type);
        if (appCompatTextView != null) {
            appCompatTextView.setTextColor(color);
        }
    }

    private final int getResourcesColor(int color) {
        return ContextCompat.getColor(getContext(), color);
    }

    private final View getView(ViewType viewType) {
        int i = WhenMappings.$EnumSwitchMapping$0[viewType.ordinal()];
        if (i == 1) {
            return this.imgLeft;
        }
        if (i == 2) {
            return this.tvCenter;
        }
        if (i == 3) {
            return this.imgRight;
        }
        if (i == 4) {
            return this.tvRight;
        }
        return new View(getContext());
    }

    public final ToolBarView setToolBarViewVisible(boolean isVisible, ViewType... events) {
        Intrinsics.checkNotNullParameter(events, "events");
        for (ViewType viewType : events) {
            int i = WhenMappings.$EnumSwitchMapping$0[viewType.ordinal()];
            if (i == 1) {
                setLeftImageVisible(isVisible);
            } else if (i == 2) {
                setCenterTextVisible(isVisible);
            } else if (i != 3) {
                if (i != 4) {
                    throw new NoWhenBranchMatchedException();
                }
                setRightTextVisible(isVisible);
            } else {
                setRightImageVisible(isVisible);
            }
        }
        return this;
    }

    private final ToolBarView setLeftImageVisible(boolean isVisible) {
        setVisible(ViewType.LEFT_IMAGE, isVisible);
        return this;
    }

    private final ToolBarView setCenterTextVisible(boolean isVisible) {
        setVisible(ViewType.CENTER_TEXT, isVisible);
        return this;
    }

    private final ToolBarView setRightTextVisible(boolean isVisible) {
        setVisible(ViewType.RIGHT_TEXT, isVisible);
        return this;
    }

    private final ToolBarView setRightImageVisible(boolean isVisible) {
        setVisible(ViewType.RIGHT_IMAGE, isVisible);
        return this;
    }

    private final void setVisible(ViewType type, boolean isVisible) {
        View view = getView(type);
        if (view != null) {
            view.setVisibility(isVisible ? 0 : 8);
        }
    }

    public final ToolBarView setCenterText(int resId) {
        setText(ViewType.CENTER_TEXT, getContext().getResources().getText(resId).toString());
        return this;
    }

    public final ToolBarView setCenterText(String text) {
        setText(ViewType.CENTER_TEXT, text);
        return this;
    }

    public final ToolBarView setCenterTextColor(int color) {
        setTextColor(ViewType.CENTER_TEXT, color);
        return this;
    }

    public final ToolBarView setCenterTextColorInt(int color) {
        setTextColorInt(ViewType.CENTER_TEXT, color);
        return this;
    }

    public final ToolBarView setRightText(int resId) {
        setText(ViewType.RIGHT_TEXT, getContext().getResources().getText(resId).toString());
        return this;
    }

    public final ToolBarView setRightText(String text) {
        setText(ViewType.RIGHT_TEXT, text);
        return this;
    }

    public final ToolBarView setRightTextColor(int color) {
        setTextColor(ViewType.RIGHT_TEXT, color);
        return this;
    }

    public final ToolBarView setRightTextColorInt(int color) {
        setTextColorInt(ViewType.RIGHT_TEXT, color);
        return this;
    }

    public final ToolBarView setRightImageScaleType(ImageView.ScaleType scaleType) {
        Intrinsics.checkNotNullParameter(scaleType, "scaleType");
        setImageScaleType(ViewType.RIGHT_IMAGE, scaleType);
        return this;
    }

    public final ToolBarView setRightImage(Bitmap bm) {
        Intrinsics.checkNotNullParameter(bm, "bm");
        setImageView(ViewType.RIGHT_IMAGE, bm);
        return this;
    }

    public final ToolBarView setRightImage(int drawable) {
        setImageView(ViewType.RIGHT_IMAGE, drawable);
        return this;
    }

    public final ToolBarView showBottomLine(boolean isVisible) {
        showLine(isVisible);
        return this;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        OnToolBarClickListener onToolBarClickListener;
        Intrinsics.checkNotNullParameter(v, "v");
        int id = v.getId();
        if (id == R.id.img_left) {
            OnToolBarClickListener onToolBarClickListener2 = this.listener;
            if (onToolBarClickListener2 != null) {
                onToolBarClickListener2.onClickToolBarView(v, ViewType.LEFT_IMAGE);
                return;
            }
            return;
        }
        if (id == R.id.tv_right) {
            OnToolBarClickListener onToolBarClickListener3 = this.listener;
            if (onToolBarClickListener3 != null) {
                onToolBarClickListener3.onClickToolBarView(v, ViewType.RIGHT_TEXT);
                return;
            }
            return;
        }
        if (id != R.id.img_right || (onToolBarClickListener = this.listener) == null) {
            return;
        }
        onToolBarClickListener.onClickToolBarView(v, ViewType.RIGHT_IMAGE);
    }
}
