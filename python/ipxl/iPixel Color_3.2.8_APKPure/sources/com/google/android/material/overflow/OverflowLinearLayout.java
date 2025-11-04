package com.google.android.material.overflow;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.appcompat.widget.PopupMenu;
import com.google.android.material.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonGroup;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes2.dex */
public class OverflowLinearLayout extends LinearLayout {
    private static final int DEF_STYLE_RES = R.style.Widget_Material3_OverflowLinearLayout;
    private final MaterialButton overflowButton;
    private boolean overflowButtonAdded;
    private final Set<View> overflowViews;

    public OverflowLinearLayout(Context context) {
        this(context, null);
    }

    public OverflowLinearLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.overflowLinearLayoutStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public OverflowLinearLayout(android.content.Context r8, android.util.AttributeSet r9, int r10) {
        /*
            r7 = this;
            int r4 = com.google.android.material.overflow.OverflowLinearLayout.DEF_STYLE_RES
            android.content.Context r8 = com.google.android.material.theme.overlay.MaterialThemeOverlay.wrap(r8, r9, r10, r4)
            r7.<init>(r8, r9, r10)
            r8 = 0
            r7.overflowButtonAdded = r8
            java.util.LinkedHashSet r0 = new java.util.LinkedHashSet
            r0.<init>()
            r7.overflowViews = r0
            android.content.Context r0 = r7.getContext()
            int[] r2 = com.google.android.material.R.styleable.OverflowLinearLayout
            int[] r5 = new int[r8]
            r1 = r9
            r3 = r10
            androidx.appcompat.widget.TintTypedArray r9 = com.google.android.material.internal.ThemeEnforcement.obtainTintedStyledAttributes(r0, r1, r2, r3, r4, r5)
            int r10 = com.google.android.material.R.styleable.OverflowLinearLayout_overflowButtonIcon
            android.graphics.drawable.Drawable r10 = r9.getDrawable(r10)
            r9.recycle()
            android.view.LayoutInflater r9 = android.view.LayoutInflater.from(r0)
            int r1 = com.google.android.material.R.layout.m3_overflow_linear_layout_overflow_button
            android.view.View r8 = r9.inflate(r1, r7, r8)
            r3 = r8
            com.google.android.material.button.MaterialButton r3 = (com.google.android.material.button.MaterialButton) r3
            r7.overflowButton = r3
            android.content.res.Resources r8 = r7.getResources()
            int r9 = com.google.android.material.R.string.m3_overflow_linear_layout_button_tooltip_text
            java.lang.String r8 = r8.getString(r9)
            androidx.appcompat.widget.TooltipCompat.setTooltipText(r3, r8)
            r7.setOverflowButtonIcon(r10)
            java.lang.CharSequence r8 = r3.getContentDescription()
            if (r8 != 0) goto L58
            int r8 = com.google.android.material.R.string.m3_overflow_linear_layout_button_content_description
            java.lang.String r8 = r0.getString(r8)
            r3.setContentDescription(r8)
        L58:
            int r8 = com.google.android.material.R.attr.overflowLinearLayoutPopupMenuStyle
            int r6 = com.google.android.material.resources.MaterialAttributes.resolveOrThrow(r7, r8)
            androidx.appcompat.widget.PopupMenu r1 = new androidx.appcompat.widget.PopupMenu
            android.content.Context r2 = r7.getContext()
            r4 = 17
            r5 = 0
            r1.<init>(r2, r3, r4, r5, r6)
            android.content.res.Resources r8 = r0.getResources()
            int r9 = com.google.android.material.R.dimen.m3_overflow_item_icon_horizontal_padding
            int r8 = r8.getDimensionPixelOffset(r9)
            com.google.android.material.overflow.OverflowLinearLayout$$ExternalSyntheticLambda1 r9 = new com.google.android.material.overflow.OverflowLinearLayout$$ExternalSyntheticLambda1
            r9.<init>()
            r3.setOnClickListener(r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.overflow.OverflowLinearLayout.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    /* renamed from: lambda$new$0$com-google-android-material-overflow-OverflowLinearLayout, reason: not valid java name */
    /* synthetic */ void m2904xc4d03947(PopupMenu popupMenu, int i, View view) {
        handleOverflowButtonClick(popupMenu, i);
    }

    public boolean isOverflowed() {
        return !this.overflowViews.isEmpty();
    }

    public Set<View> getOverflowedViews() {
        return this.overflowViews;
    }

    public void setOverflowButtonIcon(Drawable drawable) {
        this.overflowButton.setIcon(drawable);
    }

    public void setOverflowButtonIconResource(int i) {
        this.overflowButton.setIconResource(i);
    }

    public Drawable getOverflowButtonIcon() {
        return this.overflowButton.getIcon();
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        int size;
        boolean z = getOrientation() == 0;
        int childCount = this.overflowButtonAdded ? getChildCount() - 1 : getChildCount();
        if (z) {
            size = View.MeasureSpec.getSize(i);
        } else {
            size = View.MeasureSpec.getSize(i2);
        }
        int overflowButtonSize = getOverflowButtonSize(z, this.overflowButton, i, i2);
        this.overflowButton.setVisibility(8);
        this.overflowViews.clear();
        int i3 = 0;
        int i4 = 0;
        while (true) {
            if (i3 < childCount) {
                View childAt = getChildAt(i3);
                childAt.setVisibility(0);
                i4 += getChildSize(z, childAt, i, i2);
                if (i4 + overflowButtonSize > size) {
                    this.overflowViews.add(childAt);
                }
                if (i4 > size) {
                    for (int i5 = i3 + 1; i5 < childCount; i5++) {
                        this.overflowViews.add(getChildAt(i5));
                    }
                    Iterator<View> it = this.overflowViews.iterator();
                    while (it.hasNext()) {
                        it.next().setVisibility(8);
                    }
                    if (!this.overflowButtonAdded) {
                        addView(this.overflowButton);
                        this.overflowButtonAdded = true;
                    }
                    this.overflowButton.setVisibility(0);
                } else {
                    i3++;
                }
            } else {
                this.overflowButton.setVisibility(8);
                this.overflowViews.clear();
                break;
            }
        }
        super.onMeasure(i, i2);
    }

    private int getChildSize(boolean z, View view, int i, int i2) {
        int measuredHeight;
        int i3;
        int minimumHeight;
        int i4;
        measureChild(view, i, i2);
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (z) {
            measuredHeight = view.getMeasuredWidth() + layoutParams.leftMargin;
            i3 = layoutParams.rightMargin;
        } else {
            measuredHeight = view.getMeasuredHeight() + layoutParams.topMargin;
            i3 = layoutParams.bottomMargin;
        }
        int i5 = measuredHeight + i3;
        if (i5 != 0) {
            return i5;
        }
        if (z) {
            minimumHeight = view.getMinimumWidth() + layoutParams.leftMargin;
            i4 = layoutParams.rightMargin;
        } else {
            minimumHeight = view.getMinimumHeight() + layoutParams.topMargin;
            i4 = layoutParams.bottomMargin;
        }
        return minimumHeight + i4;
    }

    private int getOverflowButtonSize(boolean z, View view, int i, int i2) {
        int measuredHeight;
        int i3;
        measureChild(view, i, i2);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
        if (z) {
            measuredHeight = view.getMeasuredWidth() + layoutParams.leftMargin;
            i3 = layoutParams.rightMargin;
        } else {
            measuredHeight = view.getMeasuredHeight() + layoutParams.topMargin;
            i3 = layoutParams.bottomMargin;
        }
        return measuredHeight + i3;
    }

    private void handleOverflowButtonClick(PopupMenu popupMenu, int i) {
        int i2;
        popupMenu.getMenu().clear();
        popupMenu.setForceShowIcon(true);
        for (final View view : this.overflowViews) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            final MenuItem add = popupMenu.getMenu().add(MaterialButtonGroup.OverflowUtils.getMenuItemText(view, layoutParams.overflowText));
            Drawable drawable = layoutParams.overflowIcon;
            if (drawable != null) {
                i2 = i;
                add.setIcon(new InsetDrawable(drawable, i2, 0, i, 0));
            } else {
                i2 = i;
            }
            if (view instanceof MaterialButton) {
                MaterialButton materialButton = (MaterialButton) view;
                add.setCheckable(materialButton.isCheckable());
                add.setChecked(materialButton.isChecked());
            }
            add.setEnabled(view.isEnabled());
            add.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() { // from class: com.google.android.material.overflow.OverflowLinearLayout$$ExternalSyntheticLambda0
                @Override // android.view.MenuItem.OnMenuItemClickListener
                public final boolean onMenuItemClick(MenuItem menuItem) {
                    return OverflowLinearLayout.lambda$handleOverflowButtonClick$1(view, add, menuItem);
                }
            });
            i = i2;
        }
        popupMenu.show();
    }

    static /* synthetic */ boolean lambda$handleOverflowButtonClick$1(View view, MenuItem menuItem, MenuItem menuItem2) {
        view.performClick();
        if (menuItem.isCheckable()) {
            menuItem.setChecked(!menuItem.isChecked());
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public LayoutParams generateDefaultLayoutParams() {
        if (getOrientation() == 0) {
            return new LayoutParams(-2, -2);
        }
        return new LayoutParams(-1, -2);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof LayoutParams) {
            return new LayoutParams(layoutParams);
        }
        if (layoutParams instanceof LinearLayout.LayoutParams) {
            return new LayoutParams((LinearLayout.LayoutParams) layoutParams);
        }
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new LayoutParams(layoutParams);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public static class LayoutParams extends LinearLayout.LayoutParams {
        public Drawable overflowIcon;
        public CharSequence overflowText;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.overflowIcon = null;
            this.overflowText = null;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.OverflowLinearLayout_Layout);
            this.overflowIcon = obtainStyledAttributes.getDrawable(R.styleable.OverflowLinearLayout_Layout_layout_overflowIcon);
            this.overflowText = obtainStyledAttributes.getText(R.styleable.OverflowLinearLayout_Layout_layout_overflowText);
            obtainStyledAttributes.recycle();
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.overflowIcon = null;
            this.overflowText = null;
        }

        public LayoutParams(int i, int i2, float f) {
            super(i, i2, f);
            this.overflowIcon = null;
            this.overflowText = null;
        }

        public LayoutParams(int i, int i2, float f, Drawable drawable, CharSequence charSequence) {
            super(i, i2, f);
            this.overflowIcon = drawable;
            this.overflowText = charSequence;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.overflowIcon = null;
            this.overflowText = null;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.overflowIcon = null;
            this.overflowText = null;
        }

        public LayoutParams(LinearLayout.LayoutParams layoutParams) {
            super(layoutParams);
            this.overflowIcon = null;
            this.overflowText = null;
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((LinearLayout.LayoutParams) layoutParams);
            this.overflowIcon = null;
            this.overflowText = null;
            this.overflowText = layoutParams.overflowText;
            this.overflowIcon = layoutParams.overflowIcon;
        }
    }
}
