package androidx.camera.core;

import android.view.Display;

/* loaded from: classes.dex */
public final class DisplayOrientedMeteringPointFactory extends MeteringPointFactory {
    private final CameraInfo mCameraInfo;
    private final Display mDisplay;
    private final float mHeight;
    private final float mWidth;

    public DisplayOrientedMeteringPointFactory(Display display, CameraInfo cameraInfo, float f, float f2) {
        this.mWidth = f;
        this.mHeight = f2;
        this.mDisplay = display;
        this.mCameraInfo = cameraInfo;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0034  */
    @Override // androidx.camera.core.MeteringPointFactory
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected android.graphics.PointF convertPoint(float r8, float r9) {
        /*
            r7 = this;
            float r0 = r7.mWidth
            float r1 = r7.mHeight
            androidx.camera.core.CameraInfo r2 = r7.mCameraInfo
            int r2 = r2.getLensFacing()
            if (r2 != 0) goto Le
            r2 = 1
            goto Lf
        Le:
            r2 = 0
        Lf:
            int r3 = r7.getRelativeCameraOrientation(r2)
            r4 = 270(0x10e, float:3.78E-43)
            r5 = 90
            if (r3 == r5) goto L22
            if (r3 != r4) goto L1c
            goto L22
        L1c:
            r6 = r9
            r9 = r8
            r8 = r6
            r6 = r1
            r1 = r0
            r0 = r6
        L22:
            if (r3 == r5) goto L30
            r5 = 180(0xb4, float:2.52E-43)
            if (r3 == r5) goto L2e
            if (r3 == r4) goto L2b
            goto L32
        L2b:
            float r9 = r1 - r9
            goto L32
        L2e:
            float r9 = r1 - r9
        L30:
            float r8 = r0 - r8
        L32:
            if (r2 == 0) goto L36
            float r9 = r1 - r9
        L36:
            float r9 = r9 / r1
            float r8 = r8 / r0
            android.graphics.PointF r0 = new android.graphics.PointF
            r0.<init>(r9, r8)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.core.DisplayOrientedMeteringPointFactory.convertPoint(float, float):android.graphics.PointF");
    }

    private int getRelativeCameraOrientation(boolean z) {
        try {
            int sensorRotationDegrees = this.mCameraInfo.getSensorRotationDegrees(this.mDisplay.getRotation());
            return z ? (360 - sensorRotationDegrees) % 360 : sensorRotationDegrees;
        } catch (Exception unused) {
            return 0;
        }
    }
}
