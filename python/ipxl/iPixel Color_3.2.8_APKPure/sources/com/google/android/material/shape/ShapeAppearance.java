package com.google.android.material.shape;

/* loaded from: classes2.dex */
public interface ShapeAppearance {
    ShapeAppearanceModel getDefaultShape();

    ShapeAppearanceModel[] getShapeAppearanceModels();

    ShapeAppearanceModel getShapeForState(int[] iArr);

    boolean isStateful();

    ShapeAppearanceModel withCornerSize(float f);

    ShapeAppearanceModel withCornerSize(CornerSize cornerSize);
}
