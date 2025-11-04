package androidx.compose.ui.graphics;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DpRect;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GraphicsLayerScope.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\bf\u0018\u00002\u00020\u0001R\u0018\u0010\u0002\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u0018\u0010\b\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\t\u0010\u0005\"\u0004\b\n\u0010\u0007R\u0018\u0010\u000b\u001a\u00020\fX¦\u000e¢\u0006\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0018\u0010\u0011\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0012\u0010\u0005\"\u0004\b\u0013\u0010\u0007R\u0018\u0010\u0014\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0015\u0010\u0005\"\u0004\b\u0016\u0010\u0007R\u0018\u0010\u0017\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0018\u0010\u0005\"\u0004\b\u0019\u0010\u0007R\u0018\u0010\u001a\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u001b\u0010\u0005\"\u0004\b\u001c\u0010\u0007R\u0018\u0010\u001d\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u001e\u0010\u0005\"\u0004\b\u001f\u0010\u0007R\u0018\u0010 \u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b!\u0010\u0005\"\u0004\b\"\u0010\u0007R\u0018\u0010#\u001a\u00020$X¦\u000e¢\u0006\f\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R!\u0010)\u001a\u00020*X¦\u000eø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\f\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u0018\u0010/\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b0\u0010\u0005\"\u0004\b1\u0010\u0007R\u0018\u00102\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b3\u0010\u0005\"\u0004\b4\u0010\u0007\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u00065"}, d2 = {"Landroidx/compose/ui/graphics/GraphicsLayerScope;", "Landroidx/compose/ui/unit/Density;", "alpha", "", "getAlpha", "()F", "setAlpha", "(F)V", "cameraDistance", "getCameraDistance", "setCameraDistance", "clip", "", "getClip", "()Z", "setClip", "(Z)V", "rotationX", "getRotationX", "setRotationX", "rotationY", "getRotationY", "setRotationY", "rotationZ", "getRotationZ", "setRotationZ", "scaleX", "getScaleX", "setScaleX", "scaleY", "getScaleY", "setScaleY", "shadowElevation", "getShadowElevation", "setShadowElevation", "shape", "Landroidx/compose/ui/graphics/Shape;", "getShape", "()Landroidx/compose/ui/graphics/Shape;", "setShape", "(Landroidx/compose/ui/graphics/Shape;)V", "transformOrigin", "Landroidx/compose/ui/graphics/TransformOrigin;", "getTransformOrigin-SzJe1aQ", "()J", "setTransformOrigin-__ExYCQ", "(J)V", "translationX", "getTranslationX", "setTranslationX", "translationY", "getTranslationY", "setTranslationY", "ui_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public interface GraphicsLayerScope extends Density {
    float getAlpha();

    float getCameraDistance();

    boolean getClip();

    float getRotationX();

    float getRotationY();

    float getRotationZ();

    float getScaleX();

    float getScaleY();

    float getShadowElevation();

    Shape getShape();

    /* renamed from: getTransformOrigin-SzJe1aQ, reason: not valid java name */
    long mo807getTransformOriginSzJe1aQ();

    float getTranslationX();

    float getTranslationY();

    void setAlpha(float f);

    void setCameraDistance(float f);

    void setClip(boolean z);

    void setRotationX(float f);

    void setRotationY(float f);

    void setRotationZ(float f);

    void setScaleX(float f);

    void setScaleY(float f);

    void setShadowElevation(float f);

    void setShape(Shape shape);

    /* renamed from: setTransformOrigin-__ExYCQ, reason: not valid java name */
    void mo808setTransformOrigin__ExYCQ(long j);

    void setTranslationX(float f);

    void setTranslationY(float f);

    /* compiled from: GraphicsLayerScope.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    public static final class DefaultImpls {
        /* renamed from: roundToPx--R2X_6o, reason: not valid java name */
        public static int m809roundToPxR2X_6o(GraphicsLayerScope graphicsLayerScope, long j) {
            Intrinsics.checkNotNullParameter(graphicsLayerScope, "this");
            return Density.DefaultImpls.m2418roundToPxR2X_6o(graphicsLayerScope, j);
        }

        /* renamed from: roundToPx-0680j_4, reason: not valid java name */
        public static int m810roundToPx0680j_4(GraphicsLayerScope graphicsLayerScope, float f) {
            Intrinsics.checkNotNullParameter(graphicsLayerScope, "this");
            return Density.DefaultImpls.m2419roundToPx0680j_4(graphicsLayerScope, f);
        }

        /* renamed from: toDp-GaN1DYA, reason: not valid java name */
        public static float m811toDpGaN1DYA(GraphicsLayerScope graphicsLayerScope, long j) {
            Intrinsics.checkNotNullParameter(graphicsLayerScope, "this");
            return Density.DefaultImpls.m2420toDpGaN1DYA(graphicsLayerScope, j);
        }

        /* renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m812toDpu2uoSUM(GraphicsLayerScope graphicsLayerScope, float f) {
            Intrinsics.checkNotNullParameter(graphicsLayerScope, "this");
            return Density.DefaultImpls.m2421toDpu2uoSUM(graphicsLayerScope, f);
        }

        /* renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m813toDpu2uoSUM(GraphicsLayerScope graphicsLayerScope, int i) {
            Intrinsics.checkNotNullParameter(graphicsLayerScope, "this");
            return Density.DefaultImpls.m2422toDpu2uoSUM((Density) graphicsLayerScope, i);
        }

        /* renamed from: toPx--R2X_6o, reason: not valid java name */
        public static float m814toPxR2X_6o(GraphicsLayerScope graphicsLayerScope, long j) {
            Intrinsics.checkNotNullParameter(graphicsLayerScope, "this");
            return Density.DefaultImpls.m2423toPxR2X_6o(graphicsLayerScope, j);
        }

        /* renamed from: toPx-0680j_4, reason: not valid java name */
        public static float m815toPx0680j_4(GraphicsLayerScope graphicsLayerScope, float f) {
            Intrinsics.checkNotNullParameter(graphicsLayerScope, "this");
            return Density.DefaultImpls.m2424toPx0680j_4(graphicsLayerScope, f);
        }

        public static Rect toRect(GraphicsLayerScope graphicsLayerScope, DpRect receiver) {
            Intrinsics.checkNotNullParameter(graphicsLayerScope, "this");
            Intrinsics.checkNotNullParameter(receiver, "receiver");
            return Density.DefaultImpls.toRect(graphicsLayerScope, receiver);
        }

        /* renamed from: toSp-0xMU5do, reason: not valid java name */
        public static long m816toSp0xMU5do(GraphicsLayerScope graphicsLayerScope, float f) {
            Intrinsics.checkNotNullParameter(graphicsLayerScope, "this");
            return Density.DefaultImpls.m2425toSp0xMU5do(graphicsLayerScope, f);
        }

        /* renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m817toSpkPz2Gy4(GraphicsLayerScope graphicsLayerScope, float f) {
            Intrinsics.checkNotNullParameter(graphicsLayerScope, "this");
            return Density.DefaultImpls.m2426toSpkPz2Gy4(graphicsLayerScope, f);
        }

        /* renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m818toSpkPz2Gy4(GraphicsLayerScope graphicsLayerScope, int i) {
            Intrinsics.checkNotNullParameter(graphicsLayerScope, "this");
            return Density.DefaultImpls.m2427toSpkPz2Gy4((Density) graphicsLayerScope, i);
        }
    }
}
