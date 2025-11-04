package androidx.compose.ui.platform;

import android.graphics.Matrix;
import androidx.compose.ui.graphics.AndroidMatrixConversions_androidKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RenderNodeLayer.android.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0011ø\u0001\u0000ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\b\u0012\u0010\u0013J\u001e\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0011ø\u0001\u0000ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\b\u0015\u0010\u0013J\u0006\u0010\u0016\u001a\u00020\u0017R\u001b\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000eø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u0005R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u0004\u0018\u00010\u0004X\u0082\u000eø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u0005R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0018"}, d2 = {"Landroidx/compose/ui/platform/RenderNodeMatrixCache;", "", "()V", "inverseMatrixCache", "Landroidx/compose/ui/graphics/Matrix;", "[F", "isDirty", "", "isInverseDirty", "matrixCache", "newAndroidMatrixCache", "Landroid/graphics/Matrix;", "newInverseAndroidMatrixCache", "oldAndroidMatrixCache", "oldInverseAndroidMatrixCache", "getInverseMatrix", "renderNode", "Landroidx/compose/ui/platform/DeviceRenderNode;", "getInverseMatrix-GrdbGEg", "(Landroidx/compose/ui/platform/DeviceRenderNode;)[F", "getMatrix", "getMatrix-GrdbGEg", "invalidate", "", "ui_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
final class RenderNodeMatrixCache {
    private float[] inverseMatrixCache;
    private boolean isDirty = true;
    private boolean isInverseDirty = true;
    private float[] matrixCache;
    private Matrix newAndroidMatrixCache;
    private Matrix newInverseAndroidMatrixCache;
    private Matrix oldAndroidMatrixCache;
    private Matrix oldInverseAndroidMatrixCache;

    public final void invalidate() {
        this.isDirty = true;
        this.isInverseDirty = true;
    }

    /* renamed from: getMatrix-GrdbGEg, reason: not valid java name */
    public final float[] m2075getMatrixGrdbGEg(DeviceRenderNode renderNode) {
        Intrinsics.checkNotNullParameter(renderNode, "renderNode");
        float[] fArr = this.matrixCache;
        if (fArr == null) {
            fArr = androidx.compose.ui.graphics.Matrix.m835constructorimpl$default(null, 1, null);
            this.matrixCache = fArr;
        }
        if (!this.isDirty) {
            return fArr;
        }
        Matrix matrix = this.newAndroidMatrixCache;
        if (matrix == null) {
            matrix = new Matrix();
            this.newAndroidMatrixCache = matrix;
        }
        renderNode.getMatrix(matrix);
        if (!Intrinsics.areEqual(this.oldAndroidMatrixCache, matrix)) {
            AndroidMatrixConversions_androidKt.m555setFromtUYjHk(fArr, matrix);
            Matrix matrix2 = this.oldAndroidMatrixCache;
            if (matrix2 == null) {
                this.oldAndroidMatrixCache = new Matrix(matrix);
            } else {
                Intrinsics.checkNotNull(matrix2);
                matrix2.set(matrix);
            }
        }
        this.isDirty = false;
        return fArr;
    }

    /* renamed from: getInverseMatrix-GrdbGEg, reason: not valid java name */
    public final float[] m2074getInverseMatrixGrdbGEg(DeviceRenderNode renderNode) {
        Intrinsics.checkNotNullParameter(renderNode, "renderNode");
        float[] fArr = this.inverseMatrixCache;
        if (fArr == null) {
            fArr = androidx.compose.ui.graphics.Matrix.m835constructorimpl$default(null, 1, null);
            this.inverseMatrixCache = fArr;
        }
        if (!this.isInverseDirty) {
            return fArr;
        }
        Matrix matrix = this.newInverseAndroidMatrixCache;
        if (matrix == null) {
            matrix = new Matrix();
            this.newInverseAndroidMatrixCache = matrix;
        }
        renderNode.getInverseMatrix(matrix);
        if (!Intrinsics.areEqual(this.oldInverseAndroidMatrixCache, matrix)) {
            AndroidMatrixConversions_androidKt.m555setFromtUYjHk(fArr, matrix);
            Matrix matrix2 = this.oldInverseAndroidMatrixCache;
            if (matrix2 == null) {
                this.oldInverseAndroidMatrixCache = new Matrix(matrix);
            } else {
                Intrinsics.checkNotNull(matrix2);
                matrix2.set(matrix);
            }
        }
        this.isInverseDirty = false;
        return fArr;
    }
}
