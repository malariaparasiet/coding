package com.wifiled.ipixels.ui.imgtxt;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.Typeface;
import android.util.Log;
import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.load.Key;
import com.wifiled.ipixels.AppConfig;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.i18n.TextBundle;

/* compiled from: TextBitmapUtil.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\n\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J.\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u001aJ.\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u001aJ6\u0010\u001c\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u0012J6\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010 \u001a\u00020!2\u0006\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u001aJ8\u0010\"\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010 \u001a\u00020!2\u0006\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u001aJ8\u0010#\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010 \u001a\u00020!2\u0006\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u001aJ6\u0010$\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010 \u001a\u00020!2\u0006\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u001aJ8\u0010%\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010 \u001a\u00020!2\u0006\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u001aJ6\u0010&\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010 \u001a\u00020!2\u0006\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u001aJ>\u0010'\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010(\u001a\u00020!2\u0006\u0010 \u001a\u00020!2\u0006\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u001aJ@\u0010)\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010(\u001a\u00020!2\u0006\u0010 \u001a\u00020!2\u0006\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u001aJ6\u0010*\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010 \u001a\u00020!2\u0006\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u001aJ8\u0010+\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010 \u001a\u00020!2\u0006\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u001aJ0\u0010,\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u001aJ0\u0010-\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u001aJ\u000e\u0010.\u001a\u00020\u00162\u0006\u0010/\u001a\u00020\u0014J&\u00100\u001a\b\u0012\u0004\u0012\u00020\u00120\u00102\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u00101\u001a\u00020!2\u0006\u00102\u001a\u000203H\u0002J\u0018\u00104\u001a\u0002052\u0006\u0010\u0018\u001a\u00020\u00162\u0006\u00102\u001a\u000203H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00050\u0010X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00066"}, d2 = {"Lcom/wifiled/ipixels/ui/imgtxt/TextBitmapUtil;", "", "<init>", "()V", "color1", "", "color2", "color3", "color4", "color5", "color6", "color7", "color8", "baseColor", "baseColor2", "colorArray", "", "createScaledTextBitmapVertical", "Landroid/graphics/Bitmap;", TextBundle.TEXT_ENTRY, "", "size", "", "textColor", "gradient", "fontface", "Landroid/graphics/Typeface;", "createScaledTextBitmap", "createScaledTextBitmapBg", "bitmapBg", "createMarqueeTextBitmapVertical", "textSizes", "durations", "", "createMarqueeTextBitmap", "createRotateTextBitmapVertical", "createRotateTextBitmap", "createMarqueeTopTextBitmapVertical", "createMarqueeTopTextBitmap", "createFlippedTextBitmapVertical", "yScale", "createFlippedTextBitmap", "createTextTopPrintBitmapVertical", "createTextTopPrintBitmap", "createTextcenterPrintBitmapVertical", "createTextcenterPrintBitmap", "getByteLength", "str", "getVerticalBitmapList", "distance", "paint", "Landroid/graphics/Paint;", "getLinearGradient", "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TextBitmapUtil {
    public static final TextBitmapUtil INSTANCE = new TextBitmapUtil();
    private static final int[] baseColor;
    private static final int[] baseColor2;
    private static final int[] color1;
    private static final int[] color2;
    private static final int[] color3;
    private static final int[] color4;
    private static final int[] color5;
    private static final int[] color6;
    private static final int[] color7;
    private static final int[] color8;
    private static List<int[]> colorArray;

    private TextBitmapUtil() {
    }

    static {
        int[] iArr = {Color.parseColor("#FFFF00"), Color.parseColor("#0CFF00"), Color.parseColor("#16FEFF"), Color.parseColor("#0B08FF"), Color.parseColor("#FF05FA"), Color.parseColor("#FF0022"), Color.parseColor("#FFBD00")};
        color1 = iArr;
        int[] iArr2 = {Color.parseColor("#FFFF00"), Color.parseColor("#0CFF00"), Color.parseColor("#16FEFF"), Color.parseColor("#0B08FF"), Color.parseColor("#FF05FA"), Color.parseColor("#FF0022"), Color.parseColor("#FFBD00")};
        color2 = iArr2;
        int[] iArr3 = {Color.parseColor("#07EAFF"), Color.parseColor("#7446FF"), Color.parseColor("#F736D3"), Color.parseColor("#0B08FF"), Color.parseColor("#FF0017"), Color.parseColor("#FFDB00")};
        color3 = iArr3;
        int[] iArr4 = {Color.parseColor("#FF0000"), Color.parseColor("#FF00FF"), Color.parseColor("#0000FF"), Color.parseColor("#00FFFF"), Color.parseColor("#00FF00"), Color.parseColor("#FFFF00")};
        color4 = iArr4;
        int[] iArr5 = {Color.parseColor("#12ADFF"), Color.parseColor("#5928FF"), Color.parseColor("#EB00E8"), Color.parseColor("#FF0093"), Color.parseColor("#FF0830"), Color.parseColor("#FFD200"), Color.parseColor("#FF0830"), Color.parseColor("#FF0093"), Color.parseColor("#EB00E8"), Color.parseColor("#5928FF"), Color.parseColor("#12ADFF")};
        color5 = iArr5;
        int[] iArr6 = {Color.parseColor("#D934D9"), Color.parseColor("#6852FF"), Color.parseColor("#EB00E8"), Color.parseColor("#00F7FE"), Color.parseColor("#01FF0B"), Color.parseColor("#E5FE00"), Color.parseColor("#FF1100"), Color.parseColor("#E5FE00"), Color.parseColor("#01FF0B"), Color.parseColor("#00F7FE"), Color.parseColor("#EB00E8"), Color.parseColor("#6852FF"), Color.parseColor("#D934D9")};
        color6 = iArr6;
        int[] iArr7 = {Color.parseColor("#FFE600"), Color.parseColor("#FF002E"), Color.parseColor("#DF00A4"), Color.parseColor("#3B00FF"), Color.parseColor("#00CCFF"), Color.parseColor("#3B00FF"), Color.parseColor("#DF00A4"), Color.parseColor("#FF002E"), Color.parseColor("#01FF0B"), Color.parseColor("#FFE600")};
        color7 = iArr7;
        int[] iArr8 = {Color.parseColor("#FFE600"), Color.parseColor("#FF002E"), Color.parseColor("#DF00A4"), Color.parseColor("#3B00FF"), Color.parseColor("#00CCFF"), Color.parseColor("#3B00FF"), Color.parseColor("#DF00A4"), Color.parseColor("#FF002E"), Color.parseColor("#01FF0B"), Color.parseColor("#FFE600")};
        color8 = iArr8;
        baseColor = new int[]{Color.parseColor("#FF0000"), Color.parseColor("#00FF00"), Color.parseColor("#0000FF"), Color.parseColor("#00FFFF"), Color.parseColor("#FFFF00"), Color.parseColor("#FF00FF"), Color.parseColor("#FFFFFF")};
        baseColor2 = new int[]{Color.parseColor("#FFFFFF"), Color.parseColor("#FF00FF"), Color.parseColor("#FFFF00"), Color.parseColor("#00FFFF"), Color.parseColor("#0000FF"), Color.parseColor("#00FF00"), Color.parseColor("#FF0000")};
        colorArray = CollectionsKt.mutableListOf(iArr, iArr2, iArr3, iArr4, iArr5, iArr6, iArr7, iArr8);
    }

    public final Bitmap createScaledTextBitmapVertical(String text, int size, int textColor, int gradient, Typeface fontface) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(fontface, "fontface");
        LogUtils.vTag("ruis", "gradient------" + gradient);
        Paint paint = new Paint();
        paint.setColor(textColor);
        paint.setTextSize(size);
        paint.setAntiAlias(true);
        paint.setTypeface(fontface);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextAlign(Paint.Align.CENTER);
        getLinearGradient(gradient, paint);
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        List<Bitmap> verticalBitmapList = getVerticalBitmapList(text, ((fontMetrics.bottom - fontMetrics.top) / 2) - fontMetrics.bottom, paint);
        paint.getTextBounds(text, 0, text.length(), new Rect());
        Bitmap createBitmap = Bitmap.createBitmap(AppConfig.INSTANCE.getLedSize().get(0).intValue(), AppConfig.INSTANCE.getLedSize().get(1).intValue(), Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
        Canvas canvas = new Canvas(createBitmap);
        Iterator<Bitmap> it = verticalBitmapList.iterator();
        float f = 0.0f;
        while (it.hasNext()) {
            canvas.drawBitmap(it.next(), f, 0.0f, paint);
            f += 16.0f;
        }
        return createBitmap;
    }

    public final Bitmap createScaledTextBitmap(String text, int size, int textColor, int gradient, Typeface fontface) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(fontface, "fontface");
        LogUtils.vTag("ruis", "gradient ----" + gradient);
        Paint paint = new Paint();
        paint.setColor(textColor);
        paint.setTextSize(size);
        paint.setTypeface(fontface);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.getTextBounds(text, 0, text.length(), new Rect());
        float floatValue = ((AppConfig.INSTANCE.getLedSize().get(1).floatValue() - r7.height()) / 2) - r7.top;
        Bitmap createBitmap = Bitmap.createBitmap(AppConfig.INSTANCE.getLedSize().get(0).intValue(), AppConfig.INSTANCE.getLedSize().get(1).intValue(), Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
        Canvas canvas = new Canvas(createBitmap);
        getLinearGradient(gradient, paint);
        canvas.drawText(text, (AppConfig.INSTANCE.getLedSize().get(0).intValue() - r7.width()) / 2.0f, floatValue, paint);
        return createBitmap;
    }

    public final Bitmap createScaledTextBitmapBg(String text, int size, int textColor, int gradient, Typeface fontface, Bitmap bitmapBg) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(fontface, "fontface");
        Intrinsics.checkNotNullParameter(bitmapBg, "bitmapBg");
        Paint paint = new Paint();
        paint.setColor(textColor);
        paint.setTextSize(size);
        paint.setTypeface(fontface);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.getTextBounds(text, 0, text.length(), new Rect());
        float floatValue = ((AppConfig.INSTANCE.getLedSize().get(1).floatValue() - r7.height()) / 2) - r7.top;
        Bitmap createBitmap = Bitmap.createBitmap(AppConfig.INSTANCE.getLedSize().get(0).intValue(), AppConfig.INSTANCE.getLedSize().get(1).intValue(), Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
        Canvas canvas = new Canvas(createBitmap);
        getLinearGradient(gradient, paint);
        canvas.drawBitmap(bitmapBg, 0.0f, 0.0f, paint);
        canvas.drawText(text, (AppConfig.INSTANCE.getLedSize().get(0).intValue() - r7.width()) / 2.0f, floatValue, paint);
        return createBitmap;
    }

    public final Bitmap createMarqueeTextBitmapVertical(String text, int textSizes, int textColor, float durations, int gradient, Typeface fontface) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(fontface, "fontface");
        Paint paint = new Paint();
        paint.setColor(textColor);
        paint.setTextSize(textSizes);
        paint.setAntiAlias(true);
        paint.setTypeface(fontface);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(1.0f);
        paint.setTextAlign(Paint.Align.CENTER);
        getLinearGradient(gradient, paint);
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        List<Bitmap> verticalBitmapList = getVerticalBitmapList(text, ((fontMetrics.bottom - fontMetrics.top) / 2) - fontMetrics.bottom, paint);
        paint.getTextBounds(text, 0, text.length(), new Rect());
        Bitmap createBitmap = Bitmap.createBitmap(AppConfig.INSTANCE.getLedSize().get(0).intValue(), AppConfig.INSTANCE.getLedSize().get(1).intValue(), Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
        Canvas canvas = new Canvas(createBitmap);
        Iterator<Bitmap> it = verticalBitmapList.iterator();
        float f = 0.0f;
        while (it.hasNext()) {
            canvas.drawBitmap(it.next(), f, 0.0f, paint);
            f += 16.0f;
        }
        Paint paint2 = new Paint();
        paint2.setColor(Color.parseColor("#000000"));
        paint2.setStyle(Paint.Style.FILL);
        paint2.setAntiAlias(true);
        canvas.drawRect(new RectF(durations, 0.0f, AppConfig.INSTANCE.getLedSize().get(0).intValue(), AppConfig.INSTANCE.getLedSize().get(1).intValue()), paint2);
        return createBitmap;
    }

    public final Bitmap createMarqueeTextBitmap(String text, int textSizes, int textColor, float durations, int gradient, Typeface fontface) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(fontface, "fontface");
        Paint paint = new Paint();
        paint.setColor(textColor);
        paint.setTextSize(textSizes);
        paint.setAntiAlias(true);
        paint.setTypeface(fontface);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(1.0f);
        Rect rect = new Rect();
        paint.getTextBounds(text, 0, text.length(), rect);
        int intValue = ((AppConfig.INSTANCE.getLedSize().get(1).intValue() - rect.height()) / 2) - rect.top;
        Bitmap createBitmap = Bitmap.createBitmap(AppConfig.INSTANCE.getLedSize().get(0).intValue(), AppConfig.INSTANCE.getLedSize().get(1).intValue(), Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
        Canvas canvas = new Canvas(createBitmap);
        getLinearGradient(gradient, paint);
        canvas.drawText(text, (AppConfig.INSTANCE.getLedSize().get(0).intValue() - rect.width()) / 2.0f, intValue, paint);
        Paint paint2 = new Paint();
        paint2.setColor(Color.parseColor("#000000"));
        paint2.setStyle(Paint.Style.FILL);
        paint2.setAntiAlias(true);
        canvas.drawRect(new RectF(durations, 0.0f, AppConfig.INSTANCE.getLedSize().get(0).intValue(), AppConfig.INSTANCE.getLedSize().get(1).intValue()), paint2);
        return createBitmap;
    }

    public final Bitmap createRotateTextBitmapVertical(String text, int textSizes, int textColor, float durations, int gradient, Typeface fontface) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(fontface, "fontface");
        Paint paint = new Paint();
        paint.setColor(textColor);
        paint.setTextSize(textSizes);
        paint.setAntiAlias(true);
        paint.setTypeface(fontface);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextAlign(Paint.Align.CENTER);
        getLinearGradient(gradient, paint);
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float f = 2;
        List<Bitmap> verticalBitmapList = getVerticalBitmapList(text, ((fontMetrics.bottom - fontMetrics.top) / f) - fontMetrics.bottom, paint);
        paint.getTextBounds(text, 0, text.length(), new Rect());
        Bitmap createBitmap = Bitmap.createBitmap(AppConfig.INSTANCE.getLedSize().get(0).intValue(), AppConfig.INSTANCE.getLedSize().get(1).intValue(), Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
        Canvas canvas = new Canvas(createBitmap);
        Paint paint2 = new Paint();
        paint2.setStyle(Paint.Style.FILL);
        paint2.setColor(0);
        canvas.drawRect(new RectF(0.0f, 0.0f, text.length() * AppConfig.INSTANCE.getLedSize().get(1).intValue(), AppConfig.INSTANCE.getLedSize().get(1).intValue()), paint2);
        canvas.rotate(durations, AppConfig.INSTANCE.getLedSize().get(0).intValue() / f, AppConfig.INSTANCE.getLedSize().get(1).intValue() / f);
        Iterator<Bitmap> it = verticalBitmapList.iterator();
        float f2 = 0.0f;
        while (it.hasNext()) {
            canvas.drawBitmap(it.next(), f2, 0.0f, paint);
            f2 += 16.0f;
        }
        return createBitmap;
    }

    public final Bitmap createRotateTextBitmap(String text, int textSizes, int textColor, float durations, int gradient, Typeface fontface) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(fontface, "fontface");
        Paint paint = new Paint();
        paint.setColor(textColor);
        paint.setTextSize(textSizes);
        paint.setAntiAlias(true);
        paint.setTypeface(fontface);
        paint.setStyle(Paint.Style.FILL);
        paint.getTextBounds(text, 0, text.length(), new Rect());
        float f = 2;
        Bitmap createBitmap = Bitmap.createBitmap(AppConfig.INSTANCE.getLedSize().get(0).intValue(), AppConfig.INSTANCE.getLedSize().get(1).intValue(), Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
        Canvas canvas = new Canvas(createBitmap);
        Paint paint2 = new Paint();
        paint2.setStyle(Paint.Style.FILL);
        paint2.setColor(0);
        RectF rectF = new RectF(0.0f, 0.0f, text.length() * 16.0f, AppConfig.INSTANCE.getLedSize().get(1).intValue());
        getLinearGradient(gradient, paint);
        canvas.drawRect(rectF, paint2);
        canvas.rotate(durations, AppConfig.INSTANCE.getLedSize().get(0).intValue() / f, AppConfig.INSTANCE.getLedSize().get(1).intValue() / f);
        canvas.drawText(text, (AppConfig.INSTANCE.getLedSize().get(0).intValue() - r0.width()) / 2.0f, ((AppConfig.INSTANCE.getLedSize().get(1).intValue() - r0.height()) / f) - r0.top, paint);
        return createBitmap;
    }

    public final Bitmap createMarqueeTopTextBitmapVertical(String text, int textSizes, int textColor, float durations, int gradient, Typeface fontface) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(fontface, "fontface");
        Paint paint = new Paint();
        paint.setColor(textColor);
        paint.setTextSize(textSizes);
        paint.setAntiAlias(true);
        paint.setTypeface(fontface);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(1.0f);
        paint.setTextAlign(Paint.Align.CENTER);
        getLinearGradient(gradient, paint);
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        List<Bitmap> verticalBitmapList = getVerticalBitmapList(text, ((fontMetrics.bottom - fontMetrics.top) / 2) - fontMetrics.bottom, paint);
        paint.getTextBounds(text, 0, text.length(), new Rect());
        Bitmap createBitmap = Bitmap.createBitmap(AppConfig.INSTANCE.getLedSize().get(0).intValue(), AppConfig.INSTANCE.getLedSize().get(1).intValue(), Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
        Canvas canvas = new Canvas(createBitmap);
        Iterator<Bitmap> it = verticalBitmapList.iterator();
        float f = 0.0f;
        while (it.hasNext()) {
            canvas.drawBitmap(it.next(), f, durations - 14, paint);
            f += 16.0f;
        }
        return createBitmap;
    }

    public final Bitmap createMarqueeTopTextBitmap(String text, int textSizes, int textColor, float durations, int gradient, Typeface fontface) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(fontface, "fontface");
        Paint paint = new Paint();
        paint.setColor(textColor);
        paint.setTextSize(textSizes);
        paint.setAntiAlias(true);
        paint.setTypeface(fontface);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(2.0f);
        paint.getTextBounds(text, 0, text.length(), new Rect());
        Bitmap createBitmap = Bitmap.createBitmap(AppConfig.INSTANCE.getLedSize().get(0).intValue(), AppConfig.INSTANCE.getLedSize().get(1).intValue(), Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
        Canvas canvas = new Canvas(createBitmap);
        getLinearGradient(gradient, paint);
        canvas.drawText(text, (AppConfig.INSTANCE.getLedSize().get(0).intValue() - r10.width()) / 2.0f, durations, paint);
        return createBitmap;
    }

    public final Bitmap createFlippedTextBitmapVertical(String text, int textSizes, int textColor, float yScale, float durations, int gradient, Typeface fontface) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(fontface, "fontface");
        Paint paint = new Paint();
        paint.setColor(textColor);
        paint.setTextSize(textSizes);
        paint.setAntiAlias(true);
        paint.setTypeface(fontface);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(1.0f);
        paint.setTextAlign(Paint.Align.CENTER);
        getLinearGradient(gradient, paint);
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        List<Bitmap> verticalBitmapList = getVerticalBitmapList(text, ((fontMetrics.bottom - fontMetrics.top) / 2) - fontMetrics.bottom, paint);
        paint.getTextBounds(text, 0, text.length(), new Rect());
        Bitmap createBitmap = Bitmap.createBitmap(AppConfig.INSTANCE.getLedSize().get(0).intValue(), AppConfig.INSTANCE.getLedSize().get(1).intValue(), Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
        Canvas canvas = new Canvas(createBitmap);
        Matrix matrix = new Matrix();
        matrix.setScale(1.0f, yScale);
        canvas.concat(matrix);
        Iterator<Bitmap> it = verticalBitmapList.iterator();
        float f = 0.0f;
        while (it.hasNext()) {
            canvas.drawBitmap(it.next(), f, 0.0f, paint);
            f += 16.0f;
        }
        return createBitmap;
    }

    public final Bitmap createFlippedTextBitmap(String text, int textSizes, int textColor, float yScale, float durations, int gradient, Typeface fontface) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(fontface, "fontface");
        Paint paint = new Paint();
        paint.setColor(textColor);
        paint.setTextSize(textSizes);
        paint.setAntiAlias(true);
        paint.setTypeface(fontface);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(1.0f);
        paint.getTextBounds(text, 0, text.length(), new Rect());
        Bitmap createBitmap = Bitmap.createBitmap(AppConfig.INSTANCE.getLedSize().get(0).intValue(), AppConfig.INSTANCE.getLedSize().get(1).intValue(), Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
        Canvas canvas = new Canvas(createBitmap);
        Matrix matrix = new Matrix();
        matrix.setScale(1.0f, yScale);
        canvas.concat(matrix);
        getLinearGradient(gradient, paint);
        canvas.drawText(text, (AppConfig.INSTANCE.getLedSize().get(0).intValue() - r11.width()) / 2.0f, durations, paint);
        return createBitmap;
    }

    public final Bitmap createTextTopPrintBitmapVertical(String text, int textSizes, int textColor, float durations, int gradient, Typeface fontface) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(fontface, "fontface");
        Paint paint = new Paint();
        paint.setColor(textColor);
        paint.setTextSize(textSizes);
        paint.setAntiAlias(true);
        paint.setTypeface(fontface);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(1.0f);
        paint.setTextAlign(Paint.Align.CENTER);
        getLinearGradient(gradient, paint);
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        List<Bitmap> verticalBitmapList = getVerticalBitmapList(text, ((fontMetrics.bottom - fontMetrics.top) / 2) - fontMetrics.bottom, paint);
        paint.getTextBounds(text, 0, text.length(), new Rect());
        Bitmap createBitmap = Bitmap.createBitmap(AppConfig.INSTANCE.getLedSize().get(0).intValue(), AppConfig.INSTANCE.getLedSize().get(1).intValue(), Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
        Canvas canvas = new Canvas(createBitmap);
        Iterator<Bitmap> it = verticalBitmapList.iterator();
        float f = 0.0f;
        while (it.hasNext()) {
            canvas.drawBitmap(it.next(), f, 0.0f, paint);
            f += 16.0f;
        }
        Paint paint2 = new Paint();
        paint2.setColor(Color.parseColor("#000000"));
        paint2.setStyle(Paint.Style.FILL);
        paint2.setAntiAlias(true);
        canvas.drawRect(new RectF(0.0f, durations, AppConfig.INSTANCE.getLedSize().get(0).intValue(), AppConfig.INSTANCE.getLedSize().get(1).intValue()), paint2);
        return createBitmap;
    }

    public final Bitmap createTextTopPrintBitmap(String text, int textSizes, int textColor, float durations, int gradient, Typeface fontface) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(fontface, "fontface");
        Paint paint = new Paint();
        paint.setColor(textColor);
        paint.setTextSize(textSizes);
        paint.setAntiAlias(true);
        paint.setTypeface(fontface);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(1.0f);
        Log.v("ruis", "durations----" + durations);
        paint.getTextBounds(text, 0, text.length(), new Rect());
        Bitmap createBitmap = Bitmap.createBitmap(AppConfig.INSTANCE.getLedSize().get(0).intValue(), AppConfig.INSTANCE.getLedSize().get(1).intValue(), Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
        Canvas canvas = new Canvas(createBitmap);
        getLinearGradient(gradient, paint);
        canvas.drawText(text, (AppConfig.INSTANCE.getLedSize().get(0).intValue() - r8.width()) / 2.0f, ((AppConfig.INSTANCE.getLedSize().get(1).intValue() - r8.height()) / 2) - r8.top, paint);
        Paint paint2 = new Paint();
        paint2.setColor(Color.parseColor("#000000"));
        paint2.setStyle(Paint.Style.FILL);
        paint2.setAntiAlias(true);
        canvas.drawRect(new RectF(0.0f, durations, AppConfig.INSTANCE.getLedSize().get(0).intValue(), AppConfig.INSTANCE.getLedSize().get(1).intValue()), paint2);
        return createBitmap;
    }

    public final Bitmap createTextcenterPrintBitmapVertical(String text, int textSizes, int textColor, int gradient, Typeface fontface) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(fontface, "fontface");
        Paint paint = new Paint();
        paint.setColor(textColor);
        paint.setTextSize(textSizes);
        paint.setAntiAlias(true);
        paint.setTypeface(fontface);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(1.0f);
        paint.setTextAlign(Paint.Align.CENTER);
        getLinearGradient(gradient, paint);
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        List<Bitmap> verticalBitmapList = getVerticalBitmapList(text, ((fontMetrics.bottom - fontMetrics.top) / 2) - fontMetrics.bottom, paint);
        paint.getTextBounds(text, 0, text.length(), new Rect());
        Bitmap createBitmap = Bitmap.createBitmap(AppConfig.INSTANCE.getLedSize().get(0).intValue(), AppConfig.INSTANCE.getLedSize().get(1).intValue(), Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
        Canvas canvas = new Canvas(createBitmap);
        Iterator<Bitmap> it = verticalBitmapList.iterator();
        float f = 0.0f;
        while (it.hasNext()) {
            canvas.drawBitmap(it.next(), f, 0.0f, paint);
            f += 16.0f;
        }
        return createBitmap;
    }

    public final Bitmap createTextcenterPrintBitmap(String text, int textSizes, int textColor, int gradient, Typeface fontface) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(fontface, "fontface");
        Paint paint = new Paint();
        paint.setColor(textColor);
        paint.setTextSize(textSizes);
        paint.setAntiAlias(true);
        paint.setTypeface(fontface);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(1.0f);
        Rect rect = new Rect();
        paint.getTextBounds(text, 0, text.length(), rect);
        int intValue = ((AppConfig.INSTANCE.getLedSize().get(1).intValue() - rect.height()) / 2) - rect.top;
        Bitmap createBitmap = Bitmap.createBitmap(AppConfig.INSTANCE.getLedSize().get(0).intValue(), AppConfig.INSTANCE.getLedSize().get(1).intValue(), Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
        Canvas canvas = new Canvas(createBitmap);
        getLinearGradient(gradient, paint);
        canvas.drawText(text, (AppConfig.INSTANCE.getLedSize().get(0).intValue() - rect.width()) / 2.0f, intValue, paint);
        return createBitmap;
    }

    public final int getByteLength(String str) {
        Intrinsics.checkNotNullParameter(str, "str");
        Charset forName = Charset.forName(Key.STRING_CHARSET_NAME);
        Intrinsics.checkNotNullExpressionValue(forName, "forName(...)");
        byte[] bytes = str.getBytes(forName);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        return bytes.length;
    }

    private final List<Bitmap> getVerticalBitmapList(String text, float distance, Paint paint) {
        ArrayList arrayList = new ArrayList();
        int length = text.length();
        for (int i = 0; i < length; i++) {
            char charAt = text.charAt(i);
            Bitmap createBitmap = Bitmap.createBitmap(AppConfig.INSTANCE.getLedSize().get(1).intValue(), AppConfig.INSTANCE.getLedSize().get(1).intValue(), Bitmap.Config.ARGB_8888);
            Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
            Canvas canvas = new Canvas(createBitmap);
            RectF rectF = new RectF(0.0f, 0.0f, AppConfig.INSTANCE.getLedSize().get(1).intValue(), AppConfig.INSTANCE.getLedSize().get(1).intValue());
            float centerY = rectF.centerY() + distance + 1;
            canvas.rotate(-90.0f, AppConfig.INSTANCE.getLedSize().get(1).intValue() / 2, AppConfig.INSTANCE.getLedSize().get(1).intValue() / 2);
            canvas.drawText(String.valueOf(charAt), rectF.centerX(), centerY, paint);
            arrayList.add(createBitmap);
        }
        return arrayList;
    }

    private final void getLinearGradient(int gradient, Paint paint) {
        float intValue;
        float f;
        LogUtils.vTag("ruis", "gradient--------" + gradient);
        if (gradient != -1 && gradient < 10) {
            float intValue2 = AppConfig.INSTANCE.getLedSize().get(0).intValue();
            if (gradient == 0 || gradient == 2 || gradient == 5 || gradient == 6) {
                intValue = AppConfig.INSTANCE.getLedSize().get(1).intValue();
                f = 0.0f;
            } else {
                f = intValue2;
                intValue = 0.0f;
            }
            paint.setShader(new LinearGradient(0.0f, 0.0f, f, intValue, colorArray.get(gradient), (float[]) null, Shader.TileMode.REPEAT));
        }
        if (gradient >= 10) {
            AppConfig.INSTANCE.getLedSize().get(0).intValue();
            switch (gradient) {
                case 10:
                    paint.setShader(new LinearGradient(0.0f, 0.0f, 0.0f, AppConfig.INSTANCE.getLedSize().get(1).intValue(), baseColor, (float[]) null, Shader.TileMode.MIRROR));
                    break;
                case 12:
                    paint.setShader(new LinearGradient(0.0f, 0.0f, AppConfig.INSTANCE.getLedSize().get(0).intValue(), AppConfig.INSTANCE.getLedSize().get(1).intValue(), baseColor, (float[]) null, Shader.TileMode.MIRROR));
                    break;
                case 13:
                    AppConfig.INSTANCE.getLedSize().get(0).intValue();
                    AppConfig.INSTANCE.getLedSize().get(1).intValue();
                    paint.setShader(new RadialGradient(0.0f, 0.0f, 200.0f, baseColor, (float[]) null, Shader.TileMode.MIRROR));
                    break;
                case 14:
                    AppConfig.INSTANCE.getLedSize().get(0).intValue();
                    AppConfig.INSTANCE.getLedSize().get(1).intValue();
                    paint.setShader(new RadialGradient(0.0f, 0.0f, 200.0f, baseColor2, (float[]) null, Shader.TileMode.MIRROR));
                    break;
                case 15:
                    AppConfig.INSTANCE.getLedSize().get(0).intValue();
                    AppConfig.INSTANCE.getLedSize().get(1).intValue();
                    paint.setShader(new SweepGradient(0.0f, 0.0f, baseColor2, (float[]) null));
                    break;
            }
        }
    }
}
