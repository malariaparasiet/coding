package com.google.android.material.button;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.appcompat.widget.PopupMenu;
import com.google.android.material.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialAttributes;
import com.google.android.material.shape.CornerSize;
import com.google.android.material.shape.ShapeAppearance;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.StateListCornerSize;
import com.google.android.material.shape.StateListShapeAppearanceModel;
import com.google.android.material.shape.StateListSizeChange;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: classes2.dex */
public class MaterialButtonGroup extends LinearLayout {
    private static final String LOG_TAG = "MButtonGroup";
    public static final int OVERFLOW_MODE_MENU = 1;
    public static final int OVERFLOW_MODE_NONE = 0;
    private boolean buttonOverflowInitialized;
    private StateListSizeChange buttonSizeChange;
    private final Map<Button, MenuItem> buttonToMenuItemMapping;
    private Integer[] childOrder;
    private final Comparator<MaterialButton> childOrderComparator;
    private boolean childShapesDirty;
    private StateListShapeAppearanceModel groupStateListShapeAppearance;
    StateListCornerSize innerCornerSize;
    private final List<ShapeAppearance> originalChildShapeAppearanceModels;
    private MaterialButton overflowButton;
    private final List<Button> overflowButtonsList;
    private final int overflowMenuItemIconPadding;
    private int overflowMode;
    private PopupMenu popupMenu;
    private final Map<Integer, Button> popupMenuItemToButtonMapping;
    private final PressedStateTracker pressedStateTracker;
    private int spacing;
    private final List<Button> tempOverflowButtonsList;
    private static final int DEF_STYLE_RES = R.style.Widget_Material3_MaterialButtonGroup;
    public static final Object OVERFLOW_BUTTON_TAG = new Object();

    @Retention(RetentionPolicy.SOURCE)
    public @interface OverflowMode {
    }

    boolean isOverflowSupported() {
        return true;
    }

    /* renamed from: lambda$new$0$com-google-android-material-button-MaterialButtonGroup, reason: not valid java name */
    /* synthetic */ int m2846xa7af0300(MaterialButton materialButton, MaterialButton materialButton2) {
        int compareTo = Boolean.valueOf(materialButton.isChecked()).compareTo(Boolean.valueOf(materialButton2.isChecked()));
        if (compareTo != 0) {
            return compareTo;
        }
        int compareTo2 = Boolean.valueOf(materialButton.isPressed()).compareTo(Boolean.valueOf(materialButton2.isPressed()));
        return compareTo2 != 0 ? compareTo2 : Integer.compare(indexOfChild(materialButton), indexOfChild(materialButton2));
    }

    public MaterialButtonGroup(Context context) {
        this(context, null);
    }

    public MaterialButtonGroup(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.materialButtonGroupStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public MaterialButtonGroup(android.content.Context r8, android.util.AttributeSet r9, int r10) {
        /*
            r7 = this;
            int r4 = com.google.android.material.button.MaterialButtonGroup.DEF_STYLE_RES
            android.content.Context r8 = com.google.android.material.theme.overlay.MaterialThemeOverlay.wrap(r8, r9, r10, r4)
            r7.<init>(r8, r9, r10)
            r8 = 0
            r7.overflowMode = r8
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r7.originalChildShapeAppearanceModels = r0
            com.google.android.material.button.MaterialButtonGroup$PressedStateTracker r0 = new com.google.android.material.button.MaterialButtonGroup$PressedStateTracker
            r1 = 0
            r0.<init>()
            r7.pressedStateTracker = r0
            com.google.android.material.button.MaterialButtonGroup$$ExternalSyntheticLambda1 r0 = new com.google.android.material.button.MaterialButtonGroup$$ExternalSyntheticLambda1
            r0.<init>()
            r7.childOrderComparator = r0
            r6 = 1
            r7.childShapesDirty = r6
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            r7.popupMenuItemToButtonMapping = r0
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            r7.buttonToMenuItemMapping = r0
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r7.tempOverflowButtonsList = r0
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r7.overflowButtonsList = r0
            android.content.Context r0 = r7.getContext()
            int[] r2 = com.google.android.material.R.styleable.MaterialButtonGroup
            int[] r5 = new int[r8]
            r1 = r9
            r3 = r10
            android.content.res.TypedArray r9 = com.google.android.material.internal.ThemeEnforcement.obtainStyledAttributes(r0, r1, r2, r3, r4, r5)
            int r10 = com.google.android.material.R.styleable.MaterialButtonGroup_buttonSizeChange
            boolean r10 = r9.hasValue(r10)
            if (r10 == 0) goto L5f
            int r10 = com.google.android.material.R.styleable.MaterialButtonGroup_buttonSizeChange
            com.google.android.material.shape.StateListSizeChange r10 = com.google.android.material.shape.StateListSizeChange.create(r0, r9, r10)
            r7.buttonSizeChange = r10
        L5f:
            int r10 = com.google.android.material.R.styleable.MaterialButtonGroup_shapeAppearance
            boolean r10 = r9.hasValue(r10)
            if (r10 == 0) goto L90
            int r10 = com.google.android.material.R.styleable.MaterialButtonGroup_shapeAppearance
            com.google.android.material.shape.StateListShapeAppearanceModel r10 = com.google.android.material.shape.StateListShapeAppearanceModel.create(r0, r9, r10)
            r7.groupStateListShapeAppearance = r10
            if (r10 != 0) goto L90
            com.google.android.material.shape.StateListShapeAppearanceModel$Builder r10 = new com.google.android.material.shape.StateListShapeAppearanceModel$Builder
            int r1 = com.google.android.material.R.styleable.MaterialButtonGroup_shapeAppearance
            int r1 = r9.getResourceId(r1, r8)
            int r2 = com.google.android.material.R.styleable.MaterialButtonGroup_shapeAppearanceOverlay
            int r2 = r9.getResourceId(r2, r8)
            com.google.android.material.shape.ShapeAppearanceModel$Builder r1 = com.google.android.material.shape.ShapeAppearanceModel.builder(r0, r1, r2)
            com.google.android.material.shape.ShapeAppearanceModel r1 = r1.build()
            r10.<init>(r1)
            com.google.android.material.shape.StateListShapeAppearanceModel r10 = r10.build()
            r7.groupStateListShapeAppearance = r10
        L90:
            int r10 = com.google.android.material.R.styleable.MaterialButtonGroup_innerCornerSize
            boolean r10 = r9.hasValue(r10)
            if (r10 == 0) goto La6
            int r10 = com.google.android.material.R.styleable.MaterialButtonGroup_innerCornerSize
            com.google.android.material.shape.AbsoluteCornerSize r1 = new com.google.android.material.shape.AbsoluteCornerSize
            r2 = 0
            r1.<init>(r2)
            com.google.android.material.shape.StateListCornerSize r10 = com.google.android.material.shape.StateListCornerSize.create(r0, r9, r10, r1)
            r7.innerCornerSize = r10
        La6:
            int r10 = com.google.android.material.R.styleable.MaterialButtonGroup_android_spacing
            int r10 = r9.getDimensionPixelSize(r10, r8)
            r7.spacing = r10
            r7.setChildrenDrawingOrderEnabled(r6)
            int r10 = com.google.android.material.R.styleable.MaterialButtonGroup_android_enabled
            boolean r10 = r9.getBoolean(r10, r6)
            r7.setEnabled(r10)
            int r10 = com.google.android.material.R.styleable.MaterialButtonGroup_overflowMode
            int r8 = r9.getInt(r10, r8)
            r7.setOverflowMode(r8)
            android.content.res.Resources r8 = r7.getResources()
            int r10 = com.google.android.material.R.dimen.m3_btn_group_overflow_item_icon_horizontal_padding
            int r8 = r8.getDimensionPixelOffset(r10)
            r7.overflowMenuItemIconPadding = r8
            boolean r8 = r7.isOverflowSupported()
            if (r8 == 0) goto Ld8
            r7.initializeButtonOverflow(r0, r9)
        Ld8:
            r9.recycle()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.button.MaterialButtonGroup.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    void initializeButtonOverflow(Context context, TypedArray typedArray) {
        Drawable drawable = typedArray.getDrawable(R.styleable.MaterialButtonGroup_overflowButtonIcon);
        MaterialButton materialButton = (MaterialButton) LayoutInflater.from(context).inflate(R.layout.m3_button_group_overflow_button, (ViewGroup) this, false);
        this.overflowButton = materialButton;
        materialButton.setTag(OVERFLOW_BUTTON_TAG);
        setOverflowButtonIcon(drawable);
        if (this.overflowButton.getContentDescription() == null) {
            this.overflowButton.setContentDescription(getResources().getString(R.string.mtrl_button_overflow_icon_content_description));
        }
        this.overflowButton.setVisibility(8);
        PopupMenu popupMenu = new PopupMenu(getContext(), this.overflowButton, 17, 0, MaterialAttributes.resolveOrThrow(this, R.attr.materialButtonGroupPopupMenuStyle));
        this.popupMenu = popupMenu;
        popupMenu.setForceShowIcon(true);
        this.overflowButton.setOnClickListener(new View.OnClickListener() { // from class: com.google.android.material.button.MaterialButtonGroup$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MaterialButtonGroup.this.m2845x38fdbfa1(view);
            }
        });
        addView(this.overflowButton);
        this.buttonOverflowInitialized = true;
    }

    /* renamed from: lambda$initializeButtonOverflow$1$com-google-android-material-button-MaterialButtonGroup, reason: not valid java name */
    /* synthetic */ void m2845x38fdbfa1(View view) {
        updateOverflowMenuItemsState();
        this.popupMenu.show();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        updateChildOrder();
        super.dispatchDraw(canvas);
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (!(view instanceof MaterialButton)) {
            Log.e(LOG_TAG, "Child views must be of type MaterialButton.");
            return;
        }
        recoverAllChildrenLayoutParams();
        this.childShapesDirty = true;
        int indexOfChild = indexOfChild(this.overflowButton);
        if (indexOfChild >= 0 && i == -1) {
            super.addView(view, indexOfChild, layoutParams);
        } else {
            super.addView(view, i, layoutParams);
        }
        MaterialButton materialButton = (MaterialButton) view;
        setGeneratedIdIfNeeded(materialButton);
        materialButton.setOnPressedChangeListenerInternal(this.pressedStateTracker);
        this.originalChildShapeAppearanceModels.add(materialButton.getShapeAppearance());
        materialButton.setEnabled(isEnabled());
    }

    @Override // android.view.ViewGroup
    public void onViewRemoved(View view) {
        super.onViewRemoved(view);
        if (view instanceof MaterialButton) {
            ((MaterialButton) view).setOnPressedChangeListenerInternal(null);
        }
        int indexOfChild = indexOfChild(view);
        if (indexOfChild >= 0) {
            this.originalChildShapeAppearanceModels.remove(indexOfChild);
        }
        this.childShapesDirty = true;
        updateChildShapes();
        recoverAllChildrenLayoutParams();
        adjustChildMarginsAndUpdateLayout();
    }

    public ShapeAppearanceModel getChildOriginalShapeAppearanceModel(int i) {
        return this.originalChildShapeAppearanceModels.get(i).getDefaultShape();
    }

    private void recoverAllChildrenLayoutParams() {
        for (int i = 0; i < getChildCount(); i++) {
            getChildButton(i).recoverOriginalLayoutParams();
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        adjustChildMarginsAndUpdateLayout();
        maybeUpdateOverflowMenu(i, i2);
        updateChildShapes();
        super.onMeasure(i, i2);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (z) {
            recoverAllChildrenLayoutParams();
            adjustChildSizeChange();
        }
    }

    private void maybeUpdateOverflowMenu(int i, int i2) {
        int size;
        if (this.buttonOverflowInitialized) {
            if (this.overflowMode != 1) {
                this.overflowButton.setVisibility(8);
                return;
            }
            boolean z = getOrientation() == 0;
            this.tempOverflowButtonsList.clear();
            if (z) {
                size = View.MeasureSpec.getSize(i);
            } else {
                size = View.MeasureSpec.getSize(i2);
            }
            int measureAndGetChildButtonSize = measureAndGetChildButtonSize(z, this.overflowButton, i, i2);
            int i3 = 0;
            int i4 = 0;
            while (true) {
                if (i3 < getChildCount() - 1) {
                    MaterialButton childButton = getChildButton(i3);
                    i4 += measureAndGetChildButtonSize(z, childButton, i, i2);
                    if (i4 + measureAndGetChildButtonSize > size) {
                        this.tempOverflowButtonsList.add(childButton);
                    }
                    if (i4 > size) {
                        for (int i5 = i3 + 1; i5 < getChildCount() - 1; i5++) {
                            this.tempOverflowButtonsList.add(getChildButton(i5));
                        }
                        this.overflowButton.setVisibility(0);
                    } else {
                        i3++;
                    }
                } else {
                    this.overflowButton.setVisibility(8);
                    this.tempOverflowButtonsList.clear();
                    break;
                }
            }
            maybeUpdateOverflowMenuItemsAndChildVisibility();
        }
    }

    private void maybeUpdateOverflowMenuItemsAndChildVisibility() {
        if (this.tempOverflowButtonsList.equals(this.overflowButtonsList)) {
            return;
        }
        for (int i = 0; i < getChildCount() - 1; i++) {
            MaterialButton childButton = getChildButton(i);
            if (this.buttonToMenuItemMapping.containsKey(childButton)) {
                childButton.setVisibility(0);
            }
        }
        this.overflowButtonsList.clear();
        this.overflowButtonsList.addAll(this.tempOverflowButtonsList);
        Menu menu = this.popupMenu.getMenu();
        this.popupMenuItemToButtonMapping.clear();
        this.buttonToMenuItemMapping.clear();
        menu.clear();
        for (Button button : this.overflowButtonsList) {
            MenuItem addMenuItemForButton = addMenuItemForButton(menu, button);
            if (addMenuItemForButton != null) {
                this.popupMenuItemToButtonMapping.put(Integer.valueOf(addMenuItemForButton.getItemId()), button);
                this.buttonToMenuItemMapping.put(button, addMenuItemForButton);
                button.setVisibility(8);
            }
        }
        updateOverflowMenuItemsState();
    }

    private int measureAndGetChildButtonSize(boolean z, Button button, int i, int i2) {
        int i3;
        int i4;
        measureChild(button, i, i2);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) button.getLayoutParams();
        int measuredWidth = z ? button.getMeasuredWidth() : button.getMeasuredHeight();
        if (z) {
            i3 = layoutParams.leftMargin;
            i4 = layoutParams.rightMargin;
        } else {
            i3 = layoutParams.topMargin;
            i4 = layoutParams.bottomMargin;
        }
        int i5 = i3 + i4;
        if (measuredWidth == 0) {
            measuredWidth = z ? button.getMinimumWidth() : button.getMinimumHeight();
        }
        return measuredWidth + i5;
    }

    private MenuItem addMenuItemForButton(Menu menu, final Button button) {
        if (!(button.getLayoutParams() instanceof LayoutParams)) {
            return null;
        }
        LayoutParams layoutParams = (LayoutParams) button.getLayoutParams();
        CharSequence menuItemText = OverflowUtils.getMenuItemText(button, layoutParams.overflowText);
        Drawable drawable = layoutParams.overflowIcon;
        MenuItem add = menu.add(menuItemText);
        if (drawable != null) {
            int i = this.overflowMenuItemIconPadding;
            add.setIcon(new InsetDrawable(drawable, i, 0, i, 0));
        }
        add.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() { // from class: com.google.android.material.button.MaterialButtonGroup$$ExternalSyntheticLambda2
            @Override // android.view.MenuItem.OnMenuItemClickListener
            public final boolean onMenuItemClick(MenuItem menuItem) {
                return MaterialButtonGroup.lambda$addMenuItemForButton$2(button, menuItem);
            }
        });
        return add;
    }

    static /* synthetic */ boolean lambda$addMenuItemForButton$2(Button button, MenuItem menuItem) {
        button.performClick();
        return true;
    }

    private void updateOverflowMenuItemsState() {
        for (Map.Entry<Button, MenuItem> entry : this.buttonToMenuItemMapping.entrySet()) {
            Button key = entry.getKey();
            MenuItem value = entry.getValue();
            if (entry.getKey() instanceof MaterialButton) {
                MaterialButton materialButton = (MaterialButton) key;
                value.setCheckable(materialButton.isCheckable());
                value.setChecked(materialButton.isChecked());
            }
            value.setEnabled(key.isEnabled());
        }
    }

    void updateChildShapes() {
        int i;
        if (!(this.innerCornerSize == null && this.groupStateListShapeAppearance == null) && this.childShapesDirty) {
            this.childShapesDirty = false;
            int childCount = getChildCount();
            int firstVisibleChildIndex = getFirstVisibleChildIndex();
            int lastVisibleChildIndex = getLastVisibleChildIndex();
            int i2 = 0;
            while (i2 < childCount) {
                MaterialButton childButton = getChildButton(i2);
                if (childButton.getVisibility() != 8) {
                    boolean z = i2 == firstVisibleChildIndex;
                    boolean z2 = i2 == lastVisibleChildIndex;
                    StateListShapeAppearanceModel.Builder originalStateListShapeBuilder = getOriginalStateListShapeBuilder(z, z2, i2);
                    boolean z3 = getOrientation() == 0;
                    boolean isLayoutRtl = ViewUtils.isLayoutRtl(this);
                    if (z3) {
                        i = z ? 5 : 0;
                        if (z2) {
                            i |= 10;
                        }
                        if (isLayoutRtl) {
                            i = StateListShapeAppearanceModel.swapCornerPositionRtl(i);
                        }
                    } else {
                        i = z ? 3 : 0;
                        if (z2) {
                            i |= 12;
                        }
                    }
                    StateListShapeAppearanceModel build = originalStateListShapeBuilder.setCornerSizeOverride(this.innerCornerSize, ~i).build();
                    boolean isStateful = build.isStateful();
                    ShapeAppearance shapeAppearance = build;
                    if (!isStateful) {
                        shapeAppearance = build.getDefaultShape(true);
                    }
                    childButton.setShapeAppearance(shapeAppearance);
                }
                i2++;
            }
        }
    }

    private StateListShapeAppearanceModel.Builder getOriginalStateListShapeBuilder(boolean z, boolean z2, int i) {
        Object obj = this.groupStateListShapeAppearance;
        if (obj == null || (!z && !z2)) {
            obj = (ShapeAppearance) this.originalChildShapeAppearanceModels.get(i);
        }
        if (!(obj instanceof StateListShapeAppearanceModel)) {
            return new StateListShapeAppearanceModel.Builder((ShapeAppearanceModel) this.originalChildShapeAppearanceModels.get(i));
        }
        return ((StateListShapeAppearanceModel) obj).toBuilder();
    }

    @Override // android.view.ViewGroup
    protected int getChildDrawingOrder(int i, int i2) {
        Integer[] numArr = this.childOrder;
        if (numArr == null || i2 >= numArr.length) {
            Log.w(LOG_TAG, "Child order wasn't updated");
            return i2;
        }
        return numArr[i2].intValue();
    }

    private void adjustChildMarginsAndUpdateLayout() {
        int i;
        int firstVisibleChildIndex = getFirstVisibleChildIndex();
        if (firstVisibleChildIndex == -1) {
            return;
        }
        for (int i2 = firstVisibleChildIndex + 1; i2 < getChildCount(); i2++) {
            MaterialButton childButton = getChildButton(i2);
            MaterialButton childButton2 = getChildButton(i2 - 1);
            if (this.spacing <= 0) {
                i = Math.min(childButton.getStrokeWidth(), childButton2.getStrokeWidth());
                childButton.setShouldDrawSurfaceColorStroke(true);
                childButton2.setShouldDrawSurfaceColorStroke(true);
            } else {
                childButton.setShouldDrawSurfaceColorStroke(false);
                childButton2.setShouldDrawSurfaceColorStroke(false);
                i = 0;
            }
            LinearLayout.LayoutParams buildLayoutParams = buildLayoutParams(childButton);
            if (getOrientation() == 0) {
                buildLayoutParams.setMarginEnd(0);
                buildLayoutParams.setMarginStart(this.spacing - i);
                buildLayoutParams.topMargin = 0;
            } else {
                buildLayoutParams.bottomMargin = 0;
                buildLayoutParams.topMargin = this.spacing - i;
                buildLayoutParams.setMarginStart(0);
            }
            childButton.setLayoutParams(buildLayoutParams);
        }
        resetChildMargins(firstVisibleChildIndex);
    }

    private void resetChildMargins(int i) {
        if (getChildCount() == 0 || i == -1) {
            return;
        }
        LinearLayout.LayoutParams buildLayoutParams = buildLayoutParams(getChildButton(i));
        if (getOrientation() == 1) {
            buildLayoutParams.topMargin = 0;
            buildLayoutParams.bottomMargin = 0;
        } else {
            buildLayoutParams.setMarginEnd(0);
            buildLayoutParams.setMarginStart(0);
            buildLayoutParams.leftMargin = 0;
            buildLayoutParams.rightMargin = 0;
        }
    }

    void onButtonWidthChanged(MaterialButton materialButton, int i) {
        int indexOfChild = indexOfChild(materialButton);
        if (indexOfChild < 0) {
            return;
        }
        MaterialButton prevVisibleChildButton = getPrevVisibleChildButton(indexOfChild);
        MaterialButton nextVisibleChildButton = getNextVisibleChildButton(indexOfChild);
        if (prevVisibleChildButton == null && nextVisibleChildButton == null) {
            return;
        }
        if (prevVisibleChildButton == null) {
            nextVisibleChildButton.setDisplayedWidthDecrease(i);
        }
        if (nextVisibleChildButton == null) {
            prevVisibleChildButton.setDisplayedWidthDecrease(i);
        }
        if (prevVisibleChildButton == null || nextVisibleChildButton == null) {
            return;
        }
        prevVisibleChildButton.setDisplayedWidthDecrease(i / 2);
        nextVisibleChildButton.setDisplayedWidthDecrease((i + 1) / 2);
    }

    private void adjustChildSizeChange() {
        int firstVisibleChildIndex = getFirstVisibleChildIndex();
        int lastVisibleChildIndex = getLastVisibleChildIndex();
        if (firstVisibleChildIndex == -1 || this.buttonSizeChange == null || getChildCount() == 0) {
            return;
        }
        int i = Integer.MAX_VALUE;
        for (int i2 = firstVisibleChildIndex; i2 <= lastVisibleChildIndex; i2++) {
            if (isChildVisible(i2)) {
                int buttonAllowedWidthIncrease = getButtonAllowedWidthIncrease(i2);
                if (i2 != firstVisibleChildIndex && i2 != lastVisibleChildIndex) {
                    buttonAllowedWidthIncrease /= 2;
                }
                i = Math.min(i, buttonAllowedWidthIncrease);
            }
        }
        int i3 = firstVisibleChildIndex;
        while (i3 <= lastVisibleChildIndex) {
            if (isChildVisible(i3)) {
                getChildButton(i3).setSizeChange(this.buttonSizeChange);
                getChildButton(i3).setWidthChangeMax((i3 == firstVisibleChildIndex || i3 == lastVisibleChildIndex) ? i : i * 2);
            }
            i3++;
        }
    }

    private int getButtonAllowedWidthIncrease(int i) {
        if (!isChildVisible(i) || this.buttonSizeChange == null) {
            return 0;
        }
        int max = Math.max(0, this.buttonSizeChange.getMaxWidthChange(getChildButton(i).getWidth()));
        MaterialButton prevVisibleChildButton = getPrevVisibleChildButton(i);
        int allowedWidthDecrease = prevVisibleChildButton == null ? 0 : prevVisibleChildButton.getAllowedWidthDecrease();
        MaterialButton nextVisibleChildButton = getNextVisibleChildButton(i);
        return Math.min(max, allowedWidthDecrease + (nextVisibleChildButton != null ? nextVisibleChildButton.getAllowedWidthDecrease() : 0));
    }

    @Override // android.widget.LinearLayout
    public void setOrientation(int i) {
        if (getOrientation() != i) {
            this.childShapesDirty = true;
        }
        super.setOrientation(i);
    }

    public StateListSizeChange getButtonSizeChange() {
        return this.buttonSizeChange;
    }

    public void setButtonSizeChange(StateListSizeChange stateListSizeChange) {
        if (this.buttonSizeChange != stateListSizeChange) {
            this.buttonSizeChange = stateListSizeChange;
            adjustChildSizeChange();
            requestLayout();
            invalidate();
        }
    }

    public int getSpacing() {
        return this.spacing;
    }

    public void setSpacing(int i) {
        this.spacing = i;
        invalidate();
        requestLayout();
    }

    public CornerSize getInnerCornerSize() {
        return this.innerCornerSize.getDefaultCornerSize();
    }

    public void setInnerCornerSize(CornerSize cornerSize) {
        this.innerCornerSize = StateListCornerSize.create(cornerSize);
        this.childShapesDirty = true;
        updateChildShapes();
        invalidate();
    }

    public StateListCornerSize getInnerCornerSizeStateList() {
        return this.innerCornerSize;
    }

    public void setInnerCornerSizeStateList(StateListCornerSize stateListCornerSize) {
        this.innerCornerSize = stateListCornerSize;
        this.childShapesDirty = true;
        updateChildShapes();
        invalidate();
    }

    public ShapeAppearanceModel getShapeAppearance() {
        StateListShapeAppearanceModel stateListShapeAppearanceModel = this.groupStateListShapeAppearance;
        if (stateListShapeAppearanceModel == null) {
            return null;
        }
        return stateListShapeAppearanceModel.getDefaultShape(true);
    }

    public void setShapeAppearance(ShapeAppearanceModel shapeAppearanceModel) {
        this.groupStateListShapeAppearance = new StateListShapeAppearanceModel.Builder(shapeAppearanceModel).build();
        this.childShapesDirty = true;
        updateChildShapes();
        invalidate();
    }

    public StateListShapeAppearanceModel getStateListShapeAppearance() {
        return this.groupStateListShapeAppearance;
    }

    public void setStateListShapeAppearance(StateListShapeAppearanceModel stateListShapeAppearanceModel) {
        this.groupStateListShapeAppearance = stateListShapeAppearanceModel;
        this.childShapesDirty = true;
        updateChildShapes();
        invalidate();
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

    public void setOverflowMode(int i) {
        if (this.overflowMode != i) {
            this.overflowMode = i;
            requestLayout();
            invalidate();
        }
    }

    public int getOverflowMode() {
        return this.overflowMode;
    }

    MaterialButton getChildButton(int i) {
        return (MaterialButton) getChildAt(i);
    }

    LinearLayout.LayoutParams buildLayoutParams(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof LinearLayout.LayoutParams) {
            return (LinearLayout.LayoutParams) layoutParams;
        }
        return new LayoutParams(layoutParams.width, layoutParams.height);
    }

    private int getFirstVisibleChildIndex() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (isChildVisible(i)) {
                return i;
            }
        }
        return -1;
    }

    private int getLastVisibleChildIndex() {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            if (isChildVisible(childCount)) {
                return childCount;
            }
        }
        return -1;
    }

    private boolean isChildVisible(int i) {
        return getChildAt(i).getVisibility() != 8;
    }

    private void setGeneratedIdIfNeeded(MaterialButton materialButton) {
        if (materialButton.getId() == -1) {
            materialButton.setId(View.generateViewId());
        }
    }

    private MaterialButton getNextVisibleChildButton(int i) {
        int childCount = getChildCount();
        do {
            i++;
            if (i >= childCount) {
                return null;
            }
        } while (!isChildVisible(i));
        return getChildButton(i);
    }

    private MaterialButton getPrevVisibleChildButton(int i) {
        for (int i2 = i - 1; i2 >= 0; i2--) {
            if (isChildVisible(i2)) {
                return getChildButton(i2);
            }
        }
        return null;
    }

    private void updateChildOrder() {
        TreeMap treeMap = new TreeMap(this.childOrderComparator);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            treeMap.put(getChildButton(i), Integer.valueOf(i));
        }
        this.childOrder = (Integer[]) treeMap.values().toArray(new Integer[0]);
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        for (int i = 0; i < getChildCount(); i++) {
            getChildButton(i).setEnabled(z);
        }
    }

    private class PressedStateTracker implements MaterialButton.OnPressedChangeListener {
        private PressedStateTracker() {
        }

        @Override // com.google.android.material.button.MaterialButton.OnPressedChangeListener
        public void onPressedChanged(MaterialButton materialButton, boolean z) {
            MaterialButtonGroup.this.invalidate();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
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
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MaterialButtonGroup_Layout);
            this.overflowIcon = obtainStyledAttributes.getDrawable(R.styleable.MaterialButtonGroup_Layout_layout_overflowIcon);
            this.overflowText = obtainStyledAttributes.getText(R.styleable.MaterialButtonGroup_Layout_layout_overflowText);
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

    public static class OverflowUtils {
        private OverflowUtils() {
        }

        public static CharSequence getMenuItemText(View view, CharSequence charSequence) {
            if (!TextUtils.isEmpty(charSequence)) {
                return charSequence;
            }
            if (view instanceof MaterialButton) {
                MaterialButton materialButton = (MaterialButton) view;
                if (!TextUtils.isEmpty(materialButton.getText())) {
                    return materialButton.getText();
                }
            }
            return view.getContentDescription();
        }
    }
}
