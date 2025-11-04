package androidx.compose.ui.platform;

import android.graphics.Matrix;
import android.view.View;
import androidx.compose.ui.graphics.AndroidMatrixConversions_androidKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ViewLayer.android.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u000fø\u0001\u0000ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\b\u0010\u0010\u0011J\u001e\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u000fø\u0001\u0000ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\b\u0013\u0010\u0011J\u0006\u0010\u0014\u001a\u00020\u0015R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000eø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\f\u001a\u0004\u0018\u00010\u0007X\u0082\u000eø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\b\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0016"}, d2 = {"Landroidx/compose/ui/platform/ViewLayerMatrixCache;", "", "()V", "androidMatrixCache", "Landroid/graphics/Matrix;", "inverseAndroidMatrixCache", "inverseMatrixCache", "Landroidx/compose/ui/graphics/Matrix;", "[F", "isDirty", "", "isInverseDirty", "matrixCache", "getInverseMatrix", "view", "Landroid/view/View;", "getInverseMatrix-GrdbGEg", "(Landroid/view/View;)[F", "getMatrix", "getMatrix-GrdbGEg", "invalidate", "", "ui_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
final class ViewLayerMatrixCache {
    private Matrix androidMatrixCache;
    private Matrix inverseAndroidMatrixCache;
    private float[] inverseMatrixCache;
    private boolean isDirty = true;
    private boolean isInverseDirty = true;
    private float[] matrixCache;

    public final void invalidate() {
        this.isDirty = true;
        this.isInverseDirty = true;
    }

    /* renamed from: getMatrix-GrdbGEg, reason: not valid java name */
    public final float[] m2078getMatrixGrdbGEg(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        float[] fArr = this.matrixCache;
        if (fArr == null) {
            fArr = androidx.compose.ui.graphics.Matrix.m835constructorimpl$default(null, 1, null);
            this.matrixCache = fArr;
        }
        if (!this.isDirty) {
            return fArr;
        }
        Matrix matrix = view.getMatrix();
        if (!Intrinsics.areEqual(this.androidMatrixCache, matrix)) {
            Intrinsics.checkNotNullExpressionValue(matrix, "new");
            AndroidMatrixConversions_androidKt.m555setFromtUYjHk(fArr, matrix);
            Matrix matrix2 = this.androidMatrixCache;
            if (matrix2 == null) {
                this.androidMatrixCache = new Matrix(matrix);
            } else {
                Intrinsics.checkNotNull(matrix2);
                matrix2.set(matrix);
            }
        }
        this.isDirty = false;
        return fArr;
    }

    /* renamed from: getInverseMatrix-GrdbGEg, reason: not valid java name */
    public final float[] m2077getInverseMatrixGrdbGEg(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        float[] fArr = this.inverseMatrixCache;
        if (fArr == null) {
            fArr = androidx.compose.ui.graphics.Matrix.m835constructorimpl$default(null, 1, null);
            this.inverseMatrixCache = fArr;
        }
        if (!this.isInverseDirty) {
            return fArr;
        }
        Matrix matrix = view.getMatrix();
        if (!Intrinsics.areEqual(this.inverseAndroidMatrixCache, matrix)) {
            Intrinsics.checkNotNullExpressionValue(matrix, "new");
            AndroidMatrixConversions_androidKt.m555setFromtUYjHk(fArr, matrix);
            androidx.compose.ui.graphics.Matrix.m840invertimpl(fArr);
            Matrix matrix2 = this.inverseAndroidMatrixCache;
            if (matrix2 == null) {
                this.inverseAndroidMatrixCache = new Matrix(matrix);
            } else {
                Intrinsics.checkNotNull(matrix2);
                matrix2.set(matrix);
            }
        }
        this.isInverseDirty = false;
        return fArr;
    }
}
