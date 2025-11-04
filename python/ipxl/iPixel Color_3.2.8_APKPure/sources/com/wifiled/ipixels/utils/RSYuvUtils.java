package com.wifiled.ipixels.utils;

import android.graphics.Bitmap;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlend;
import android.renderscript.ScriptIntrinsicYuvToRGB;
import android.renderscript.Type;
import com.wifiled.baselib.utils.LogUtils;
import com.wifiled.ipixels.App;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RSYuvUtils.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0016J \u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0016H\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/wifiled/ipixels/utils/RSYuvUtils;", "", "<init>", "()V", "rs", "Landroid/renderscript/RenderScript;", "mYuvToRgbIntrinsic", "Landroid/renderscript/ScriptIntrinsicYuvToRGB;", "mScriptIntrinsicBlend", "Landroid/renderscript/ScriptIntrinsicBlend;", "mYuvType", "Landroid/renderscript/Type$Builder;", "mRgbaType", "mYuvin", "Landroid/renderscript/Allocation;", "mYuvOut", "mBmpout", "Landroid/graphics/Bitmap;", "nv21ToBitmap", "nv21", "", "width", "", "height", "resetSize", "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class RSYuvUtils {
    public static final RSYuvUtils INSTANCE = new RSYuvUtils();
    private static Bitmap mBmpout;
    private static Type.Builder mRgbaType;
    private static ScriptIntrinsicBlend mScriptIntrinsicBlend;
    private static Allocation mYuvOut;
    private static ScriptIntrinsicYuvToRGB mYuvToRgbIntrinsic;
    private static Type.Builder mYuvType;
    private static Allocation mYuvin;
    private static RenderScript rs;

    private RSYuvUtils() {
    }

    static {
        RenderScript create = RenderScript.create(App.INSTANCE.getContext());
        rs = create;
        ScriptIntrinsicYuvToRGB create2 = ScriptIntrinsicYuvToRGB.create(create, Element.U8_4(create));
        Intrinsics.checkNotNullExpressionValue(create2, "create(...)");
        mYuvToRgbIntrinsic = create2;
        RenderScript renderScript = rs;
        ScriptIntrinsicBlend create3 = ScriptIntrinsicBlend.create(renderScript, Element.RGBA_8888(renderScript));
        Intrinsics.checkNotNullExpressionValue(create3, "create(...)");
        mScriptIntrinsicBlend = create3;
    }

    public final Bitmap nv21ToBitmap(byte[] nv21, int width, int height) {
        Bitmap bitmap;
        Intrinsics.checkNotNullParameter(nv21, "nv21");
        long currentTimeMillis = System.currentTimeMillis();
        Bitmap bitmap2 = mBmpout;
        if (bitmap2 == null) {
            mBmpout = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        } else if (bitmap2 == null || bitmap2.getWidth() != width || (bitmap = mBmpout) == null || bitmap.getHeight() != height) {
            mBmpout = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            resetSize(nv21, width, height);
        }
        if (mYuvType == null) {
            resetSize(nv21, width, height);
        }
        Allocation allocation = null;
        try {
            Allocation allocation2 = mYuvin;
            if (allocation2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mYuvin");
                allocation2 = null;
            }
            allocation2.copyFrom(nv21);
        } catch (Exception e) {
            e.printStackTrace();
            resetSize(nv21, width, height);
            LogUtils.logi("$>>>[nv21ToBitmap]: " + e.getMessage(), new Object[0]);
        }
        ScriptIntrinsicYuvToRGB scriptIntrinsicYuvToRGB = mYuvToRgbIntrinsic;
        Allocation allocation3 = mYuvin;
        if (allocation3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mYuvin");
            allocation3 = null;
        }
        scriptIntrinsicYuvToRGB.setInput(allocation3);
        ScriptIntrinsicYuvToRGB scriptIntrinsicYuvToRGB2 = mYuvToRgbIntrinsic;
        Allocation allocation4 = mYuvOut;
        if (allocation4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mYuvOut");
            allocation4 = null;
        }
        scriptIntrinsicYuvToRGB2.forEach(allocation4);
        Allocation allocation5 = mYuvOut;
        if (allocation5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mYuvOut");
        } else {
            allocation = allocation5;
        }
        allocation.copyTo(mBmpout);
        LogUtils.logi("RSYuvUtils>>>>nv21转bmp" + (System.currentTimeMillis() - currentTimeMillis) + "ms", new Object[0]);
        Bitmap bitmap3 = mBmpout;
        Intrinsics.checkNotNull(bitmap3);
        return bitmap3;
    }

    private final void resetSize(byte[] nv21, int width, int height) {
        RenderScript renderScript = rs;
        Type.Builder x = new Type.Builder(renderScript, Element.U8(renderScript)).setX(nv21.length);
        mYuvType = x;
        Type.Builder builder = null;
        Allocation createTyped = Allocation.createTyped(rs, x != null ? x.create() : null, 1);
        Intrinsics.checkNotNullExpressionValue(createTyped, "createTyped(...)");
        mYuvin = createTyped;
        RenderScript renderScript2 = rs;
        Type.Builder y = new Type.Builder(renderScript2, Element.RGBA_8888(renderScript2)).setX(width).setY(height);
        Intrinsics.checkNotNullExpressionValue(y, "setY(...)");
        mRgbaType = y;
        RenderScript renderScript3 = rs;
        if (y == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mRgbaType");
        } else {
            builder = y;
        }
        Allocation createTyped2 = Allocation.createTyped(renderScript3, builder.create(), 1);
        Intrinsics.checkNotNullExpressionValue(createTyped2, "createTyped(...)");
        mYuvOut = createTyped2;
    }
}
