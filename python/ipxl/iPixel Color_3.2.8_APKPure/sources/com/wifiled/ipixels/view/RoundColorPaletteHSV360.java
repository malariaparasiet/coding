package com.wifiled.ipixels.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.camera.video.AudioStats;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.ViewCompat;
import com.alibaba.fastjson2.internal.asm.Opcodes;
import com.wifiled.ipixels.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RoundColorPaletteHSV360.kt */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u001d\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001:\u0001QB!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tB\u0011\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\b\u0010\nB\u001b\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\b\u0010\u000bJ\u0018\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020\u00072\u0006\u0010>\u001a\u00020\u0007H\u0014J\u0010\u0010?\u001a\u00020<2\u0006\u0010@\u001a\u00020AH\u0015J\u0010\u0010B\u001a\u00020<2\u0006\u0010@\u001a\u00020AH\u0002J\"\u0010C\u001a\u00020<2\b\u0010@\u001a\u0004\u0018\u00010A2\u0006\u0010D\u001a\u00020\r2\u0006\u0010E\u001a\u00020\rH\u0002J\u000e\u0010F\u001a\u00020<2\u0006\u00109\u001a\u00020:J\u0012\u0010G\u001a\u00020+2\b\u0010H\u001a\u0004\u0018\u00010IH\u0016J\u001c\u0010J\u001a\u00020\r2\b\b\u0002\u0010K\u001a\u00020\r2\b\b\u0002\u0010L\u001a\u00020\rH\u0002J\u001c\u0010M\u001a\u00020<2\b\b\u0002\u0010N\u001a\u00020\r2\b\b\u0002\u0010O\u001a\u00020\rH\u0002J\u001c\u0010P\u001a\u00020\r2\b\b\u0002\u0010K\u001a\u00020\r2\b\b\u0002\u0010L\u001a\u00020\rH\u0002R$\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\r@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R$\u0010\u0013\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\r@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0010\"\u0004\b\u0015\u0010\u0012R$\u0010\u0016\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\r@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0010\"\u0004\b\u0018\u0010\u0012R\u001a\u0010\u0019\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u001b\"\u0004\b \u0010\u001dR$\u0010!\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\r@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0010\"\u0004\b#\u0010\u0012R\u001a\u0010$\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u001b\"\u0004\b&\u0010\u001dR$\u0010'\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\r@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0010\"\u0004\b)\u0010\u0012R\u001a\u0010*\u001a\u00020+X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010,\"\u0004\b-\u0010.R\u000e\u0010/\u001a\u000200X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u000202X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u000204X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u000202X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u000204X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00109\u001a\u0004\u0018\u00010:X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006R"}, d2 = {"Lcom/wifiled/ipixels/view/RoundColorPaletteHSV360;", "Landroid/view/View;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "(Landroid/content/Context;)V", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "value", "", "radius", "getRadius", "()F", "setRadius", "(F)V", "colorRadius", "getColorRadius", "setColorRadius", "colorStroke", "getColorStroke", "setColorStroke", "colorStrokeColor", "getColorStrokeColor", "()I", "setColorStrokeColor", "(I)V", TypedValues.Custom.S_COLOR, "getColor", "setColor", "stroke", "getStroke", "setStroke", "strokeColor", "getStrokeColor", "setStrokeColor", "gap", "getGap", "setGap", "isOutOfBounds", "", "()Z", "setOutOfBounds", "(Z)V", "paint", "Landroid/graphics/Paint;", "colors1", "", "positions1", "", "colors2", "positions2", "xColor", "yColor", "colorChangeCallBack", "Lcom/wifiled/ipixels/view/RoundColorPaletteHSV360$ColorChangeCallBack;", "onMeasure", "", "widthMeasureSpec", "heightMeasureSpec", "onDraw", "canvas", "Landroid/graphics/Canvas;", "createColorWheel", "createColorRadius", "rx", "ry", "setColorChangeCallBack", "onTouchEvent", NotificationCompat.CATEGORY_EVENT, "Landroid/view/MotionEvent;", "pointToCircle", "x", "y", "findPoint", "x1", "y1", "angdeg", "ColorChangeCallBack", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class RoundColorPaletteHSV360 extends View {
    private int color;
    private ColorChangeCallBack colorChangeCallBack;
    private float colorRadius;
    private float colorStroke;
    private int colorStrokeColor;
    private final int[] colors1;
    private final int[] colors2;
    private float gap;
    private boolean isOutOfBounds;
    private final Paint paint;
    private final float[] positions1;
    private final float[] positions2;
    private float radius;
    private float stroke;
    private int strokeColor;
    private float xColor;
    private float yColor;

    /* compiled from: RoundColorPaletteHSV360.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006À\u0006\u0003"}, d2 = {"Lcom/wifiled/ipixels/view/RoundColorPaletteHSV360$ColorChangeCallBack;", "", "onChange", "", TypedValues.Custom.S_COLOR, "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface ColorChangeCallBack {
        void onChange(int color);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RoundColorPaletteHSV360(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.colorRadius = 50.0f;
        this.colorStroke = 8.0f;
        this.colorStrokeColor = ViewCompat.MEASURED_STATE_MASK;
        this.color = -1;
        this.stroke = 24.0f;
        this.strokeColor = InputDeviceCompat.SOURCE_ANY;
        this.gap = 4.0f;
        this.paint = new Paint();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RoundColorPaletteHSV360);
        Intrinsics.checkNotNullExpressionValue(obtainStyledAttributes, "obtainStyledAttributes(...)");
        setColorRadius(obtainStyledAttributes.getDimension(0, 50.0f));
        setColorStroke(obtainStyledAttributes.getDimension(1, 8.0f));
        setGap(obtainStyledAttributes.getDimension(3, 4.0f));
        setStroke(obtainStyledAttributes.getDimension(5, 24.0f));
        this.colorStrokeColor = obtainStyledAttributes.getColor(2, ViewCompat.MEASURED_STATE_MASK);
        this.strokeColor = obtainStyledAttributes.getColor(6, InputDeviceCompat.SOURCE_ANY);
        this.isOutOfBounds = obtainStyledAttributes.getBoolean(4, false);
        obtainStyledAttributes.recycle();
        float[] fArr = new float[361];
        for (int i2 = 0; i2 < 361; i2++) {
            fArr[i2] = i2 / (360 * 1.0f);
        }
        this.positions1 = fArr;
        float[] fArr2 = {0.0f, 1.0f, 1.0f};
        int[] iArr = new int[361];
        for (int i3 = 0; i3 < 361; i3++) {
            fArr2[0] = 360 - (i3 % 360.0f);
            iArr[i3] = Color.HSVToColor(fArr2);
        }
        this.colors1 = iArr;
        float[] fArr3 = {0.0f, 0.0f, 1.0f};
        float[] fArr4 = new float[256];
        for (int i4 = 0; i4 < 256; i4++) {
            fArr4[i4] = i4 / (255 * 1.0f);
        }
        this.positions2 = fArr4;
        int[] iArr2 = new int[256];
        for (int i5 = 0; i5 < 256; i5++) {
            iArr2[i5] = Color.HSVToColor(((255 - i5) * 255) / 255, fArr3);
        }
        this.colors2 = iArr2;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public RoundColorPaletteHSV360(Context context) {
        this(context, null, 0);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public RoundColorPaletteHSV360(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public final float getRadius() {
        return this.radius;
    }

    private final void setRadius(float f) {
        this.radius = ((f - this.stroke) - this.gap) - this.colorRadius;
    }

    public final float getColorRadius() {
        return this.colorRadius;
    }

    public final void setColorRadius(float f) {
        if (f >= 0.0f) {
            this.colorRadius = f;
        }
    }

    public final float getColorStroke() {
        return this.colorStroke;
    }

    public final void setColorStroke(float f) {
        if (f >= 0.0f) {
            this.colorStroke = f;
        }
    }

    public final int getColorStrokeColor() {
        return this.colorStrokeColor;
    }

    public final void setColorStrokeColor(int i) {
        this.colorStrokeColor = i;
    }

    public final int getColor() {
        return this.color;
    }

    public final void setColor(int i) {
        this.color = i;
    }

    public final float getStroke() {
        return this.stroke;
    }

    public final void setStroke(float f) {
        if (f >= 0.0f) {
            this.stroke = f;
        }
    }

    public final int getStrokeColor() {
        return this.strokeColor;
    }

    public final void setStrokeColor(int i) {
        this.strokeColor = i;
    }

    public final float getGap() {
        return this.gap;
    }

    public final void setGap(float f) {
        if (f >= 0.0f) {
            this.gap = f;
        }
    }

    /* renamed from: isOutOfBounds, reason: from getter */
    public final boolean getIsOutOfBounds() {
        return this.isOutOfBounds;
    }

    public final void setOutOfBounds(boolean z) {
        this.isOutOfBounds = z;
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        float size = View.MeasureSpec.getSize(widthMeasureSpec) / 2.0f;
        float size2 = View.MeasureSpec.getSize(heightMeasureSpec) / 2.0f;
        if (size - size2 >= 0.0f) {
            size = size2;
        }
        setRadius(size);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        createColorWheel(canvas);
        createColorRadius(canvas, this.xColor, this.yColor);
        super.onDraw(canvas);
    }

    private final void createColorWheel(Canvas canvas) {
        this.paint.reset();
        this.paint.setAntiAlias(true);
        this.paint.setShader(new ComposeShader(new SweepGradient(getWidth() / 2.0f, getHeight() / 2.0f, this.colors1, this.positions1), new RadialGradient(getWidth() / 2.0f, getHeight() / 2.0f, this.radius - this.colorRadius, this.colors2, this.positions2, Shader.TileMode.CLAMP), PorterDuff.Mode.SRC_OVER));
        if (canvas != null) {
            canvas.drawCircle(getWidth() / 2.0f, getHeight() / 2.0f, this.radius, this.paint);
        }
        this.paint.setShader(null);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeWidth(this.stroke);
        this.paint.setColor(this.strokeColor);
        if (canvas != null) {
            canvas.drawCircle(getWidth() / 2.0f, getHeight() / 2.0f, this.radius + this.gap + (this.stroke / 2), this.paint);
        }
    }

    private final void createColorRadius(Canvas canvas, float rx, float ry) {
        if (rx == 0.0f || ry == 0.0f) {
            rx = getWidth() / 2.0f;
            ry = getHeight() / 2.0f;
        }
        this.paint.reset();
        this.paint.setAntiAlias(true);
        this.paint.setColor(this.color);
        if (canvas != null) {
            canvas.drawCircle(rx, ry, this.colorRadius, this.paint);
        }
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setColor(this.colorStrokeColor);
        this.paint.setStrokeWidth(this.colorStroke);
        if (canvas != null) {
            canvas.drawCircle(rx, ry, this.colorRadius + (this.colorStroke / 2), this.paint);
        }
    }

    public final void setColorChangeCallBack(ColorChangeCallBack colorChangeCallBack) {
        Intrinsics.checkNotNullParameter(colorChangeCallBack, "colorChangeCallBack");
        this.colorChangeCallBack = colorChangeCallBack;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        if (event != null && event.getAction() == 1) {
            float pointToCircle = pointToCircle(event.getX(), event.getY());
            if (pointToCircle <= this.radius - (this.isOutOfBounds ? 0.0f : this.colorRadius - this.colorStroke)) {
                this.xColor = event.getX();
                this.yColor = event.getY();
                int HSVToColor = Color.HSVToColor(new float[]{angdeg(event.getX(), event.getY()), pointToCircle / this.radius, 1.0f});
                this.color = HSVToColor;
                ColorChangeCallBack colorChangeCallBack = this.colorChangeCallBack;
                if (colorChangeCallBack != null) {
                    colorChangeCallBack.onChange(HSVToColor);
                }
            } else {
                findPoint(event.getX(), event.getY());
            }
            invalidate();
        }
        return true;
    }

    static /* synthetic */ float pointToCircle$default(RoundColorPaletteHSV360 roundColorPaletteHSV360, float f, float f2, int i, Object obj) {
        if ((i & 1) != 0) {
            f = roundColorPaletteHSV360.getWidth() / 2.0f;
        }
        if ((i & 2) != 0) {
            f2 = roundColorPaletteHSV360.getHeight() / 2.0f;
        }
        return roundColorPaletteHSV360.pointToCircle(f, f2);
    }

    private final float pointToCircle(float x, float y) {
        return (float) Math.sqrt(((x - (getWidth() / 2.0f)) * (x - (getWidth() / 2.0f))) + ((y - (getHeight() / 2.0f)) * (y - (getHeight() / 2.0f))));
    }

    static /* synthetic */ void findPoint$default(RoundColorPaletteHSV360 roundColorPaletteHSV360, float f, float f2, int i, Object obj) {
        if ((i & 1) != 0) {
            f = roundColorPaletteHSV360.getWidth() / 2.0f;
        }
        if ((i & 2) != 0) {
            f2 = roundColorPaletteHSV360.getHeight() / 2.0f;
        }
        roundColorPaletteHSV360.findPoint(f, f2);
    }

    private final void findPoint(float x1, float y1) {
        float height = y1 - (getHeight() / 2.0f);
        float width = x1 - (getWidth() / 2.0f);
        float f = this.radius - (this.isOutOfBounds ? 0.0f : this.colorRadius - this.colorStroke);
        float f2 = width / height;
        float sqrt = (float) Math.sqrt((f * f) / ((f2 * f2) + 1));
        this.yColor = sqrt;
        if (height < 0.0f) {
            this.yColor = -sqrt;
        }
        this.xColor = ((width * this.yColor) / height) + (getWidth() / 2.0f);
        float height2 = this.yColor + (getHeight() / 2.0f);
        this.yColor = height2;
        int HSVToColor = Color.HSVToColor(new float[]{angdeg(this.xColor, height2), 1.0f, 1.0f});
        this.color = HSVToColor;
        ColorChangeCallBack colorChangeCallBack = this.colorChangeCallBack;
        if (colorChangeCallBack != null) {
            colorChangeCallBack.onChange(HSVToColor);
        }
    }

    static /* synthetic */ float angdeg$default(RoundColorPaletteHSV360 roundColorPaletteHSV360, float f, float f2, int i, Object obj) {
        if ((i & 1) != 0) {
            f = roundColorPaletteHSV360.getWidth() / 2.0f;
        }
        if ((i & 2) != 0) {
            f2 = roundColorPaletteHSV360.getHeight() / 2.0f;
        }
        return roundColorPaletteHSV360.angdeg(f, f2);
    }

    private final float angdeg(float x, float y) {
        int i;
        if (x - (getWidth() / 2.0f) != 0.0f || y - (getHeight() / 2.0f) >= 0.0f) {
            float height = ((y - (getHeight() / 2.0f)) * (y - (getHeight() / 2.0f))) / ((x - (getWidth() / 2.0f)) * (x - (getWidth() / 2.0f)));
            double d = AudioStats.AUDIO_AMPLITUDE_NONE;
            double d2 = 90.0d;
            while (true) {
                double d3 = d2 - d;
                if (d3 <= 1.0d) {
                    break;
                }
                double d4 = (d3 / 2) + d;
                if (height > Math.tan(Math.toRadians(d4))) {
                    d = d4;
                } else {
                    d2 = d4;
                }
            }
            i = (int) (d2 - 1);
        } else {
            i = 90;
        }
        if (x - (getWidth() / 2.0f) <= 0.0f && y - (getHeight() / 2.0f) <= 0.0f) {
            i = 180 - i;
        } else if (x - (getWidth() / 2.0f) <= 0.0f && y - (getHeight() / 2.0f) >= 0.0f) {
            i += Opcodes.GETFIELD;
        } else if (x - (getWidth() / 2.0f) >= 0.0f && y - (getHeight() / 2.0f) >= 0.0f) {
            i = 360 - i;
        }
        return i;
    }
}
