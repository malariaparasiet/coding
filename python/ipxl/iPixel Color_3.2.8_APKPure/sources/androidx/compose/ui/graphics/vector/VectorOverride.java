package androidx.compose.ui.graphics.vector;

import androidx.compose.ui.graphics.Brush;
import androidx.constraintlayout.motion.widget.Key;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VectorPainter.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u001c\b`\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003H\u0016J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J\u001c\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0016J\u0010\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0006H\u0016J\u0010\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0006H\u0016J\u0010\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0006H\u0016J\u0010\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u0006H\u0016J\u0010\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u0006H\u0016J\u0014\u0010\u0016\u001a\u0004\u0018\u00010\u00032\b\u0010\u0017\u001a\u0004\u0018\u00010\u0003H\u0016J\u0010\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u0006H\u0016J\u0010\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u0006H\u0016J\u0010\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u0006H\u0016J\u0010\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\u0006H\u0016J\u0010\u0010 \u001a\u00020\u00062\u0006\u0010!\u001a\u00020\u0006H\u0016J\u0010\u0010\"\u001a\u00020\u00062\u0006\u0010#\u001a\u00020\u0006H\u0016J\u0010\u0010$\u001a\u00020\u00062\u0006\u0010%\u001a\u00020\u0006H\u0016¨\u0006&"}, d2 = {"Landroidx/compose/ui/graphics/vector/VectorOverride;", "", "obtainFill", "Landroidx/compose/ui/graphics/Brush;", "fill", "obtainFillAlpha", "", "fillAlpha", "obtainPathData", "", "Landroidx/compose/ui/graphics/vector/PathNode;", "pathData", "obtainPivotX", "pivotX", "obtainPivotY", "pivotY", "obtainRotation", Key.ROTATION, "obtainScaleX", "scaleX", "obtainScaleY", "scaleY", "obtainStroke", "stroke", "obtainStrokeAlpha", "strokeAlpha", "obtainStrokeWidth", "strokeWidth", "obtainTranslateX", "translateX", "obtainTranslateY", "translateY", "obtainTrimPathEnd", "trimPathEnd", "obtainTrimPathOffset", "trimPathOffset", "obtainTrimPathStart", "trimPathStart", "ui_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public interface VectorOverride {

    /* compiled from: VectorPainter.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    public static final class DefaultImpls {
        public static Brush obtainFill(VectorOverride vectorOverride, Brush brush) {
            Intrinsics.checkNotNullParameter(vectorOverride, "this");
            return brush;
        }

        public static float obtainFillAlpha(VectorOverride vectorOverride, float f) {
            Intrinsics.checkNotNullParameter(vectorOverride, "this");
            return f;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static List<PathNode> obtainPathData(VectorOverride vectorOverride, List<? extends PathNode> pathData) {
            Intrinsics.checkNotNullParameter(vectorOverride, "this");
            Intrinsics.checkNotNullParameter(pathData, "pathData");
            return pathData;
        }

        public static float obtainPivotX(VectorOverride vectorOverride, float f) {
            Intrinsics.checkNotNullParameter(vectorOverride, "this");
            return f;
        }

        public static float obtainPivotY(VectorOverride vectorOverride, float f) {
            Intrinsics.checkNotNullParameter(vectorOverride, "this");
            return f;
        }

        public static float obtainRotation(VectorOverride vectorOverride, float f) {
            Intrinsics.checkNotNullParameter(vectorOverride, "this");
            return f;
        }

        public static float obtainScaleX(VectorOverride vectorOverride, float f) {
            Intrinsics.checkNotNullParameter(vectorOverride, "this");
            return f;
        }

        public static float obtainScaleY(VectorOverride vectorOverride, float f) {
            Intrinsics.checkNotNullParameter(vectorOverride, "this");
            return f;
        }

        public static Brush obtainStroke(VectorOverride vectorOverride, Brush brush) {
            Intrinsics.checkNotNullParameter(vectorOverride, "this");
            return brush;
        }

        public static float obtainStrokeAlpha(VectorOverride vectorOverride, float f) {
            Intrinsics.checkNotNullParameter(vectorOverride, "this");
            return f;
        }

        public static float obtainStrokeWidth(VectorOverride vectorOverride, float f) {
            Intrinsics.checkNotNullParameter(vectorOverride, "this");
            return f;
        }

        public static float obtainTranslateX(VectorOverride vectorOverride, float f) {
            Intrinsics.checkNotNullParameter(vectorOverride, "this");
            return f;
        }

        public static float obtainTranslateY(VectorOverride vectorOverride, float f) {
            Intrinsics.checkNotNullParameter(vectorOverride, "this");
            return f;
        }

        public static float obtainTrimPathEnd(VectorOverride vectorOverride, float f) {
            Intrinsics.checkNotNullParameter(vectorOverride, "this");
            return f;
        }

        public static float obtainTrimPathOffset(VectorOverride vectorOverride, float f) {
            Intrinsics.checkNotNullParameter(vectorOverride, "this");
            return f;
        }

        public static float obtainTrimPathStart(VectorOverride vectorOverride, float f) {
            Intrinsics.checkNotNullParameter(vectorOverride, "this");
            return f;
        }
    }

    Brush obtainFill(Brush fill);

    float obtainFillAlpha(float fillAlpha);

    List<PathNode> obtainPathData(List<? extends PathNode> pathData);

    float obtainPivotX(float pivotX);

    float obtainPivotY(float pivotY);

    float obtainRotation(float rotation);

    float obtainScaleX(float scaleX);

    float obtainScaleY(float scaleY);

    Brush obtainStroke(Brush stroke);

    float obtainStrokeAlpha(float strokeAlpha);

    float obtainStrokeWidth(float strokeWidth);

    float obtainTranslateX(float translateX);

    float obtainTranslateY(float translateY);

    float obtainTrimPathEnd(float trimPathEnd);

    float obtainTrimPathOffset(float trimPathOffset);

    float obtainTrimPathStart(float trimPathStart);
}
