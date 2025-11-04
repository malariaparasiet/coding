package com.google.android.material.button;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.core.view.ViewCompat;
import com.google.android.material.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;

/* loaded from: classes2.dex */
public class MaterialSplitButton extends MaterialButtonGroup {
    private static final int DEF_STYLE_RES = R.style.Widget_Material3_MaterialSplitButton;
    private static final int REQUIRED_BUTTON_COUNT = 2;

    public MaterialSplitButton(Context context) {
        this(context, null);
    }

    public MaterialSplitButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.materialSplitButtonStyle);
    }

    public MaterialSplitButton(Context context, AttributeSet attributeSet, int i) {
        super(MaterialThemeOverlay.wrap(context, attributeSet, i, DEF_STYLE_RES), attributeSet, i);
    }

    @Override // com.google.android.material.button.MaterialButtonGroup, android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        int i2;
        if (!(view instanceof MaterialButton)) {
            throw new IllegalArgumentException("MaterialSplitButton can only hold MaterialButtons.");
        }
        if (getChildCount() > 2) {
            throw new IllegalArgumentException("MaterialSplitButton can only hold two MaterialButtons.");
        }
        final MaterialButton materialButton = (MaterialButton) view;
        super.addView(view, i, layoutParams);
        if (indexOfChild(view) == 1) {
            materialButton.setCheckable(true);
            materialButton.setA11yClassName(Button.class.getName());
            Resources resources = getResources();
            if (materialButton.isChecked()) {
                i2 = R.string.mtrl_button_expanded_content_description;
            } else {
                i2 = R.string.mtrl_button_collapsed_content_description;
            }
            ViewCompat.setStateDescription(materialButton, resources.getString(i2));
            materialButton.addOnCheckedChangeListener(new MaterialButton.OnCheckedChangeListener() { // from class: com.google.android.material.button.MaterialSplitButton$$ExternalSyntheticLambda0
                @Override // com.google.android.material.button.MaterialButton.OnCheckedChangeListener
                public final void onCheckedChanged(MaterialButton materialButton2, boolean z) {
                    MaterialSplitButton.this.m2847xf376ee79(materialButton, materialButton2, z);
                }
            });
        }
    }

    /* renamed from: lambda$addView$0$com-google-android-material-button-MaterialSplitButton, reason: not valid java name */
    /* synthetic */ void m2847xf376ee79(MaterialButton materialButton, MaterialButton materialButton2, boolean z) {
        materialButton2.playSoundEffect(0);
        ViewCompat.setStateDescription(materialButton, getResources().getString(z ? R.string.mtrl_button_expanded_content_description : R.string.mtrl_button_collapsed_content_description));
    }
}
