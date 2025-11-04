package com.wifiled.ipixels.core.text;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.Log;
import com.blankj.utilcode.util.LogUtils;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.utils.FontUtils;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Locale;

/* loaded from: classes3.dex */
public class TextAgreement {
    private static ArrayList<String> arrListGradientColor = new ArrayList<>();

    public static byte[] bitmap2GradientBGR(Bitmap bitmap, int gradientColor) {
        switch (gradientColor) {
            case 2:
                arrListGradientColor = GradientColor.INSTANCE.getGradientColor1();
                break;
            case 3:
                arrListGradientColor = GradientColor.INSTANCE.getGradientColor2();
                break;
            case 4:
                arrListGradientColor = GradientColor.INSTANCE.getGradientColor3();
                break;
            case 5:
                arrListGradientColor = GradientColor.INSTANCE.getGradientColor4();
                break;
            case 6:
                arrListGradientColor = GradientColor.INSTANCE.getGradientColor5();
                break;
            case 7:
                arrListGradientColor = GradientColor.INSTANCE.getGradientColor6();
                break;
            case 8:
                arrListGradientColor = GradientColor.INSTANCE.getGradientColor7();
                break;
            case 9:
                arrListGradientColor = GradientColor.INSTANCE.getGradientColor8();
                break;
        }
        int width = bitmap.getWidth();
        bitmap.getHeight();
        ByteBuffer allocate = ByteBuffer.allocate(bitmap.getByteCount());
        bitmap.copyPixelsToBuffer(allocate);
        byte[] array = allocate.array();
        byte[] bArr = new byte[(array.length / 4) * 3];
        int i = -1;
        for (int i2 = 0; i2 < array.length / 4; i2++) {
            int i3 = i2 % width;
            int i4 = i2 / width;
            if (i3 == 0) {
                i = Color.parseColor(arrListGradientColor.get(i4));
            }
            if (bitmap.getPixel(i3, i4) == -1) {
                int i5 = i2 * 3;
                bArr[i5] = (byte) Color.blue(i);
                bArr[i5 + 1] = (byte) Color.green(i);
                bArr[i5 + 2] = (byte) Color.red(i);
            }
        }
        return bArr;
    }

    public static Bitmap getCharBitmapKorean(char c, int width, int height, int textColor) {
        int i = width * 4;
        int i2 = height * 4;
        String valueOf = String.valueOf(c);
        TextPaint textPaint = new TextPaint();
        textPaint.setAntiAlias(false);
        textPaint.setFilterBitmap(false);
        textPaint.setColor(-1);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setTypeface(FontUtils.getTypeface(28));
        float f = i2;
        textPaint.setTextSize(f);
        textPaint.setTextAlign(Paint.Align.CENTER);
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float f2 = ((f - (fontMetrics.descent - fontMetrics.ascent)) / 2.0f) - fontMetrics.ascent;
        float f3 = i / 2.0f;
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawColor(0);
        textPaint.setStyle(Paint.Style.STROKE);
        textPaint.setStrokeWidth(4.0f);
        canvas.drawText(valueOf, f3, f2, textPaint);
        textPaint.setStyle(Paint.Style.FILL);
        canvas.drawText(valueOf, f3, f2, textPaint);
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(createBitmap, width, height, false);
        createBitmap.recycle();
        return createScaledBitmap;
    }

    public static Bitmap getCharBitmap(char c, int width, int height, int typeFace, int textColor, boolean isBold) {
        int i;
        int i2;
        boolean z;
        float centerY;
        float centerY2;
        boolean isChinese = CharacterUtilsKt.isChinese(c);
        boolean isKoreanCharacter = CharacterUtilsKt.isKoreanCharacter(c);
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawColor(0);
        TextPaint textPaint = new TextPaint();
        float f = height;
        textPaint.setTextSize(f);
        switch (textColor) {
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                i = -1;
                break;
            default:
                i = textColor;
                break;
        }
        textPaint.setColor(i);
        Typeface typeface = FontUtils.getTypeface(typeFace);
        if (isKoreanCharacter) {
            typeface = FontUtils.getTypeface(22);
            z = isBold;
            i2 = 19;
        } else {
            if (AppConfig.INSTANCE.getLedType() != 0) {
                i2 = 19;
                if (AppConfig.INSTANCE.getLedType() != 2) {
                    if (height == 12) {
                        typeface = FontUtils.getTypeface(4);
                    } else if (AppConfig.INSTANCE.getLedType() == 5 || AppConfig.INSTANCE.getLedType() == 4 || AppConfig.INSTANCE.getLedType() == 6 || AppConfig.INSTANCE.getLedType() == 13) {
                        typeface = FontUtils.getTypeface(19);
                    } else if (height == 16 && typeFace == 27) {
                        typeface = FontUtils.getTypeface(1);
                    } else {
                        typeface = FontUtils.getTypeface(typeFace);
                    }
                    z = isBold;
                }
            } else {
                i2 = 19;
            }
            if (!isChinese) {
                typeface = FontUtils.getTypeface(0);
            }
            z = isBold;
        }
        textPaint.setAntiAlias(z);
        textPaint.setTypeface(typeface);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setTextAlign(Paint.Align.CENTER);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(0);
        RectF rectF = new RectF(0.0f, 0.0f, width, f);
        canvas.drawRect(rectF, paint);
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float f2 = ((fontMetrics.bottom - fontMetrics.top) / 2.0f) - fontMetrics.bottom;
        if (isKoreanCharacter) {
            centerY = (rectF.centerY() + f2) - 1.0f;
            textPaint.setTextSize(height + 1);
            if (height == 16) {
                textPaint.setTypeface(FontUtils.getTypeface(28));
            }
        } else {
            if (!isChinese) {
                centerY2 = rectF.centerY();
            } else if (height == 12) {
                centerY = (rectF.centerY() + f2) - 1.0f;
            } else if (AppConfig.INSTANCE.getLedType() == 5 || typeFace == i2) {
                centerY = rectF.centerY() + f2 + 1.0f;
            } else {
                centerY2 = rectF.centerY();
            }
            centerY = centerY2 + f2;
        }
        canvas.drawText(String.valueOf(c), rectF.centerX(), centerY, textPaint);
        return createBitmap;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0145  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.graphics.Bitmap getCharBitmapPreview(java.lang.String r16, int r17, int r18, int r19, int r20, boolean r21) {
        /*
            Method dump skipped, instructions count: 414
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wifiled.ipixels.core.text.TextAgreement.getCharBitmapPreview(java.lang.String, int, int, int, int, boolean):android.graphics.Bitmap");
    }

    public static Bitmap getCharBitmapTHAI(String c, int width, int height, int typeFace, int textColor) {
        float centerY;
        TextPaint textPaint = new TextPaint();
        float measureText = textPaint.measureText(c);
        LogUtils.vTag("ruis", "textWidth---" + measureText);
        int ceil = (int) Math.ceil(measureText);
        LogUtils.vTag("ruis", "textWidth---" + measureText);
        LogUtils.vTag("ruis", "textWidthInt---" + ceil);
        Bitmap createBitmap = Bitmap.createBitmap(ceil, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawColor(0);
        switch (textColor) {
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                textColor = -1;
                break;
        }
        textPaint.setColor(textColor);
        Typeface typeface = FontUtils.getTypeface(23);
        textPaint.setAntiAlias(false);
        textPaint.setTypeface(typeface);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextLocale(new Locale("th"));
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        if (height == 16) {
            textPaint.setTextSize(12.0f);
        } else if (height == 24) {
            textPaint.setTextSize(18.0f);
        } else if (height == 32) {
            textPaint.setTextSize(27.0f);
        } else if (height == 64) {
            textPaint.setTextSize(52.0f);
        } else {
            textPaint.setTextSize(height);
        }
        paint.setColor(0);
        RectF rectF = new RectF(0.0f, 0.0f, ceil, height);
        canvas.drawRect(rectF, paint);
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float f = ((fontMetrics.bottom - fontMetrics.top) / 2.0f) - fontMetrics.bottom;
        if (AppConfig.INSTANCE.getLedType() == 0 || AppConfig.INSTANCE.getLedType() == 2) {
            centerY = rectF.centerY();
        } else {
            centerY = rectF.centerY();
        }
        canvas.drawText(c, rectF.centerX(), centerY + f, textPaint);
        return createBitmap;
    }

    public static Bitmap getCharBitmapTHAI(String text, int height, int textColor) {
        Log.v("ruis", "getCharBitmapTHAI text-----" + text);
        TextPaint textPaint = new TextPaint();
        switch (textColor) {
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                textColor = -1;
                break;
        }
        textPaint.setColor(textColor);
        Typeface typeface = FontUtils.getTypeface(23);
        textPaint.setAntiAlias(false);
        textPaint.setTypeface(typeface);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextLocale(new Locale("th"));
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        if (height == 16) {
            textPaint.setTextSize(13.0f);
        } else if (height == 24) {
            textPaint.setTextSize(18.0f);
        } else if (height == 32) {
            textPaint.setTextSize(27.0f);
        } else if (height == 64) {
            textPaint.setTextSize(52.0f);
        } else {
            textPaint.setTextSize(height);
        }
        paint.setColor(0);
        int ceil = (int) Math.ceil(textPaint.measureText(text));
        if (ceil == 0) {
            ceil = 8;
        }
        RectF rectF = new RectF(0.0f, 0.0f, ceil, height);
        Bitmap createBitmap = Bitmap.createBitmap(ceil, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawColor(0);
        canvas.drawRect(rectF, paint);
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        canvas.drawText(text, rectF.centerX(), rectF.centerY() + (((fontMetrics.bottom - fontMetrics.top) / 2.0f) - fontMetrics.bottom), textPaint);
        return createBitmap;
    }

    public static byte[] getTextData(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int ceil = ((int) Math.ceil(width / 8.0d)) * 8;
        int i = ceil * height;
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = i2 % ceil;
            int i4 = i2 / ceil;
            if (i3 < width && i4 < height) {
                if (bitmap.getPixel(i3, i4) != 0) {
                    bArr[i2] = 1;
                } else {
                    bArr[i2] = 0;
                }
            }
        }
        int i5 = i / 8;
        byte[] bArr2 = new byte[i5];
        int i6 = 0;
        for (int i7 = 0; i7 < i5; i7++) {
            byte b = 0;
            for (int i8 = 0; i8 < 8; i8++) {
                if (i6 < i) {
                    b = (byte) (((byte) (bArr[i6] << i8)) | b);
                    i6++;
                }
            }
            bArr2[i7] = b;
        }
        return bArr2;
    }

    public static byte[] getWhiteTextData(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int ceil = ((int) Math.ceil(width / 8.0d)) * 8;
        int i = ceil * height;
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = i2 % ceil;
            int i4 = i2 / ceil;
            if (i3 < width && i4 < height) {
                if (bitmap.getPixel(i3, i4) != -1) {
                    bArr[i2] = 1;
                } else {
                    bArr[i2] = 0;
                }
            }
        }
        int i5 = i / 8;
        byte[] bArr2 = new byte[i5];
        int i6 = 0;
        for (int i7 = 0; i7 < i5; i7++) {
            byte b = 0;
            for (int i8 = 0; i8 < 8; i8++) {
                if (i6 < i) {
                    b = (byte) (((byte) (bArr[i6] << i8)) | b);
                    i6++;
                }
            }
            bArr2[i7] = b;
        }
        return bArr2;
    }

    public static Bitmap getCharBitmapARC(String text, int height, int textColor) {
        TextPaint paint = getPaint(textColor, height);
        paint.setTypeface(FontUtils.getTypeface(24));
        paint.setTextLocale(new Locale("ar"));
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float f = height - (fontMetrics.descent - fontMetrics.ascent);
        int length = text.length();
        int length2 = text.length();
        Bitmap createBitmap = Bitmap.createBitmap((int) Math.ceil(paint.measureText(text)), height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawColor(0);
        switch (AppConfig.INSTANCE.getLedType()) {
            case 9:
                canvas.drawTextRun((CharSequence) text, 0, length, 0, length2, 0.0f, f + 26.0f, true, (Paint) paint);
                break;
            case 10:
            case 11:
            case 12:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
                if (height == 32) {
                    canvas.drawTextRun((CharSequence) text, 0, length, 0, length2, 0.0f, f + 34.0f, true, (Paint) paint);
                    break;
                } else {
                    canvas.drawTextRun((CharSequence) text, 0, length, 0, length2, 0.0f, f + 16.0f, true, (Paint) paint);
                    break;
                }
            case 13:
            default:
                canvas.drawTextRun((CharSequence) text, 0, length, 0, length2, 0.0f, f + 17.0f, true, (Paint) paint);
                break;
        }
        return createBitmap;
    }

    public static Bitmap getCharBitmapINDIAN(String text, int height, int textColor) {
        TextPaint paint;
        if (height == 16) {
            paint = getPaint(textColor, height - 4);
        } else if (height == 24) {
            paint = getPaint(textColor, height - 5);
        } else {
            paint = getPaint(textColor, height - 5);
        }
        TextPaint textPaint = paint;
        textPaint.setTypeface(FontUtils.getTypeface(20));
        textPaint.setTextLocale(new Locale("hi"));
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float f = height - (fontMetrics.descent - fontMetrics.ascent);
        int length = text.length();
        int length2 = text.length();
        Bitmap createBitmap = Bitmap.createBitmap((int) Math.ceil(textPaint.measureText(text)), height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawColor(0);
        switch (AppConfig.INSTANCE.getLedType()) {
            case 9:
                canvas.drawTextRun((CharSequence) text, 0, length, 0, length2, 0.0f, f + 20.0f, false, (Paint) textPaint);
                break;
            case 10:
            case 11:
            case 12:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
                if (height == 32) {
                    canvas.drawTextRun((CharSequence) text, 0, length, 0, length2, 0.0f, f + 30.0f, false, (Paint) textPaint);
                    break;
                } else {
                    canvas.drawTextRun((CharSequence) text, 0, length, 0, length2, 0.0f, 12.0f + f, false, (Paint) textPaint);
                    break;
                }
            case 13:
            default:
                canvas.drawTextRun((CharSequence) text, 0, length, 0, length2, 0.0f, 12.0f + f, false, (Paint) textPaint);
                break;
        }
        return createBitmap;
    }

    public static Bitmap getCharBitmapTami(String text, int height, int textColor) {
        TextPaint paint;
        if (height == 16) {
            paint = getPaint(textColor, height - 2);
        } else {
            paint = getPaint(textColor, height - 7);
        }
        TextPaint textPaint = paint;
        textPaint.setTypeface(FontUtils.getTypeface(29));
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float f = height - (fontMetrics.descent - fontMetrics.ascent);
        int length = text.length();
        int ceil = (int) Math.ceil(textPaint.measureText(text));
        int length2 = text.length();
        Bitmap createBitmap = Bitmap.createBitmap(ceil, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawColor(0);
        switch (AppConfig.INSTANCE.getLedType()) {
            case 9:
                canvas.drawTextRun((CharSequence) text, 0, length2, 0, length, 0.0f, f + 16.0f, false, (Paint) textPaint);
                break;
            case 10:
            case 11:
            case 12:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
                if (height == 32) {
                    canvas.drawTextRun((CharSequence) text, 0, length2, 0, length, 0.0f, f + 26.0f, false, (Paint) textPaint);
                    break;
                } else {
                    canvas.drawTextRun((CharSequence) text, 0, length2, 0, length, 0.0f, f + 18.0f, false, (Paint) textPaint);
                    break;
                }
            case 13:
            default:
                canvas.drawTextRun((CharSequence) text, 0, length2, 0, length, 0.0f, f + 14.0f, false, (Paint) textPaint);
                break;
        }
        return createBitmap;
    }

    private static TextPaint getPaint(int textColor, int height) {
        TextPaint textPaint = new TextPaint();
        switch (textColor) {
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                textColor = -1;
                break;
        }
        textPaint.setColor(textColor);
        textPaint.setTextSize(height);
        textPaint.setAntiAlias(false);
        return textPaint;
    }

    public static Bitmap getCharBitmap(String text, int width, int height, int typeFace, int textColor) {
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawColor(0);
        TextPaint textPaint = new TextPaint();
        switch (textColor) {
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                textColor = -1;
                break;
        }
        textPaint.setColor(textColor);
        textPaint.setStyle(Paint.Style.FILL);
        float f = height;
        textPaint.setTextSize(f);
        textPaint.setAntiAlias(true);
        textPaint.setSubpixelText(true);
        textPaint.setTextLocale(new Locale("es"));
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        LogUtils.vTag("ruis", fontMetrics.ascent + "fontMetrics.descent - fontMetrics.ascent$=" + fontMetrics.descent);
        canvas.drawText(text, 0.0f, (f - (fontMetrics.descent - fontMetrics.ascent)) + 18.0f, textPaint);
        return createBitmap;
    }

    public static Bitmap getCharBitmapRussian(String text, int height, int textColor) {
        TextPaint textPaint = new TextPaint();
        switch (textColor) {
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                textColor = -1;
                break;
        }
        textPaint.setColor(textColor);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setTextSize(height - 1);
        textPaint.setAntiAlias(false);
        textPaint.setTypeface(FontUtils.getTypeface(26));
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float f = height - (fontMetrics.descent - fontMetrics.ascent);
        Bitmap createBitmap = Bitmap.createBitmap((int) Math.ceil(textPaint.measureText(text)), height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawColor(0);
        if (height == 16) {
            canvas.drawText(text, 0.0f, f + 15.0f, textPaint);
            return createBitmap;
        }
        if (height == 24) {
            canvas.drawText(text, 0.0f, f + 23.0f, textPaint);
            return createBitmap;
        }
        canvas.drawText(text, 0.0f, f + 31.0f, textPaint);
        return createBitmap;
    }

    public static Bitmap getCharBitmapVG(String text, int height, int textColor, boolean containsVietnamese, boolean containsGreekChars) {
        int i;
        TextPaint textPaint = new TextPaint();
        switch (textColor) {
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                i = -1;
                break;
            default:
                i = textColor;
                break;
        }
        textPaint.setColor(i);
        textPaint.setStyle(Paint.Style.FILL);
        if (containsGreekChars) {
            textPaint.setTextSize(height);
        } else if (height == 16) {
            textPaint.setTextSize(14.5f);
        } else if (height == 24) {
            textPaint.setTextSize(21.0f);
        } else if (height == 32) {
            LogUtils.vTag("ruis", " paint.setTextSize(16f/*textSize*/);");
            textPaint.setTextSize(29.0f);
        } else if (height == 64) {
            textPaint.setTextSize(61.0f);
        }
        textPaint.setAntiAlias(false);
        textPaint.setTypeface(FontUtils.getTypeface(26));
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        int length = text.length();
        int length2 = text.length();
        float f = height - (fontMetrics.descent - fontMetrics.ascent);
        int ceil = (int) Math.ceil(textPaint.measureText(text));
        LogUtils.vTag("ruis", "textWidthInt=" + ceil);
        Bitmap createBitmap = Bitmap.createBitmap(ceil, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawColor(0);
        LogUtils.vTag("ruis", "containsVietnamese=" + containsVietnamese);
        LogUtils.vTag("ruis", "containsGreekChars=" + containsGreekChars);
        if (containsGreekChars) {
            if (height == 16) {
                canvas.drawTextRun((CharSequence) text, 0, length, 0, length2, 0.0f, f + 15.0f, false, (Paint) textPaint);
                return createBitmap;
            }
            if (height == 24) {
                canvas.drawTextRun((CharSequence) text, 0, length, 0, length2, 0.0f, f + 23.0f, false, (Paint) textPaint);
                return createBitmap;
            }
            canvas.drawTextRun((CharSequence) text, 0, length, 0, length2, 0.0f, f + 31.0f, false, (Paint) textPaint);
            return createBitmap;
        }
        if (containsVietnamese) {
            if (height == 16) {
                canvas.drawTextRun((CharSequence) text, 0, length, 0, length2, 0.0f, f + 15.0f, false, (Paint) textPaint);
                return createBitmap;
            }
            if (height == 24) {
                canvas.drawTextRun((CharSequence) text, 0, length, 0, length2, 0.0f, f + 21.0f, false, (Paint) textPaint);
                return createBitmap;
            }
            canvas.drawTextRun((CharSequence) text, 0, length, 0, length2, 0.0f, f + 29.0f, false, (Paint) textPaint);
            return createBitmap;
        }
        if (height == 16) {
            canvas.drawTextRun((CharSequence) text, 0, length, 0, length2, 0.0f, f + 15.0f, false, (Paint) textPaint);
            return createBitmap;
        }
        if (height == 24) {
            canvas.drawTextRun((CharSequence) text, 0, length, 0, length2, 0.0f, f + 21.0f, false, (Paint) textPaint);
            return createBitmap;
        }
        canvas.drawTextRun((CharSequence) text, 0, length, 0, length2, 0.0f, f + 29.0f, false, (Paint) textPaint);
        return createBitmap;
    }
}
