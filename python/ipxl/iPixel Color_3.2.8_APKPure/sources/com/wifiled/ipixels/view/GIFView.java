package com.wifiled.ipixels.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Choreographer;
import android.view.View;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.android.gms.common.internal.ImagesContract;
import com.wifiled.ipixels.R;
import com.wifiled.musiclib.player.constant.DbFinal;
import java.io.InputStream;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import pl.droidsonroids.gif.GifDrawable;

/* compiled from: GIFView.kt */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0013\b\u0016\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0005\u0010\u0006B\u001d\b\u0016\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\u0005\u0010\tB%\b\u0016\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\u0005\u0010\fJ\u0010\u00100\u001a\u0002012\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0018\u00102\u001a\u0002012\u0006\u00103\u001a\u00020\u000b2\u0006\u00104\u001a\u00020\u000bH\u0014J\b\u00105\u001a\u000201H\u0014J\b\u00106\u001a\u000201H\u0014J\u0010\u00107\u001a\u0002012\u0006\u00108\u001a\u000209H\u0015J\u0010\u0010:\u001a\u0002012\u0006\u0010;\u001a\u00020<H\u0016J\b\u0010=\u001a\u000201H\u0002J\b\u0010>\u001a\u000201H\u0002J\u0018\u0010?\u001a\u0004\u0018\u00010@2\u0006\u0010A\u001a\u00020BH\u0082@¢\u0006\u0002\u0010CJ\u0018\u0010D\u001a\u0004\u0018\u00010@2\u0006\u0010E\u001a\u00020BH\u0082@¢\u0006\u0002\u0010CJ\u0010\u0010F\u001a\u0002012\b\b\u0001\u0010G\u001a\u00020\u000bJ\u0010\u0010H\u001a\u0002012\b\u0010I\u001a\u0004\u0018\u00010BJ\u000e\u0010J\u001a\u0002012\u0006\u0010K\u001a\u00020LJ\u000e\u0010M\u001a\u0002012\u0006\u0010N\u001a\u00020OJ\u000e\u0010P\u001a\u0002012\u0006\u0010N\u001a\u00020OR\"\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\u000e@BX\u0082\u000e¢\u0006\b\n\u0000\"\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR$\u0010 \u001a\u00020\u001a2\u0006\u0010\r\u001a\u00020\u001a@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R$\u0010$\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u001d\"\u0004\b&\u0010\u001fR$\u0010'\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u001d\"\u0004\b)\u0010\u001fR\u001a\u0010*\u001a\u00020+X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/¨\u0006Q"}, d2 = {"Lcom/wifiled/ipixels/view/GIFView;", "Landroid/view/View;", "Landroid/view/Choreographer$FrameCallback;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "value", "Lcom/wifiled/ipixels/view/GifSupport;", "gifSupport", "setGifSupport", "(Lcom/wifiled/ipixels/view/GifSupport;)V", "size", "margin", "", "paint", "Landroid/graphics/Paint;", "choreographer", "Landroid/view/Choreographer;", "isRendering", "", "borderColor", "getBorderColor", "()I", "setBorderColor", "(I)V", "isGrid", "()Z", "setGrid", "(Z)V", "widthDigit", "getWidthDigit", "setWidthDigit", "heightDigit", "getHeightDigit", "setHeightDigit", DbFinal.LOCAL_PATH, "Landroid/graphics/Path;", "getPath", "()Landroid/graphics/Path;", "setPath", "(Landroid/graphics/Path;)V", "getAttrs", "", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "onAttachedToWindow", "onDetachedFromWindow", "onDraw", "canvas", "Landroid/graphics/Canvas;", "doFrame", "frameTimeNanos", "", "startRendering", "stopRendering", "getHttpToStream", "Ljava/io/InputStream;", ImagesContract.URL, "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getFileToStream", "filePath", "setColorData", "colorInt", "setUrlOrFile", "link", "setBitmap", TypedValues.AttributesType.S_FRAME, "Landroid/graphics/Bitmap;", "setRawGifBytes", "byte", "", "setRawPictureBytes", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class GIFView extends View implements Choreographer.FrameCallback {
    private int borderColor;
    private final Choreographer choreographer;
    private GifSupport gifSupport;
    private int heightDigit;
    private boolean isGrid;
    private boolean isRendering;
    private float margin;
    private final Paint paint;
    private Path path;
    private int size;
    private int widthDigit;

    public GIFView(Context context) {
        super(context);
        this.paint = new Paint();
        Choreographer choreographer = Choreographer.getInstance();
        Intrinsics.checkNotNullExpressionValue(choreographer, "getInstance(...)");
        this.choreographer = choreographer;
        this.borderColor = Color.parseColor("#66000000");
        this.isGrid = true;
        this.widthDigit = 16;
        this.heightDigit = 16;
        this.path = new Path();
    }

    public GIFView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.paint = new Paint();
        Choreographer choreographer = Choreographer.getInstance();
        Intrinsics.checkNotNullExpressionValue(choreographer, "getInstance(...)");
        this.choreographer = choreographer;
        this.borderColor = Color.parseColor("#66000000");
        this.isGrid = true;
        this.widthDigit = 16;
        this.heightDigit = 16;
        this.path = new Path();
        if (attributeSet != null) {
            getAttrs(attributeSet);
        }
    }

    public GIFView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.paint = new Paint();
        Choreographer choreographer = Choreographer.getInstance();
        Intrinsics.checkNotNullExpressionValue(choreographer, "getInstance(...)");
        this.choreographer = choreographer;
        this.borderColor = Color.parseColor("#66000000");
        this.isGrid = true;
        this.widthDigit = 16;
        this.heightDigit = 16;
        this.path = new Path();
        if (attributeSet != null) {
            getAttrs(attributeSet);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setGifSupport(GifSupport gifSupport) {
        this.gifSupport = gifSupport;
        if (gifSupport != null) {
            post(new Runnable() { // from class: com.wifiled.ipixels.view.GIFView$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    GIFView.this.setBackgroundColor(0);
                }
            });
        }
    }

    public final int getBorderColor() {
        return this.borderColor;
    }

    public final void setBorderColor(int i) {
        this.borderColor = i;
        invalidate();
    }

    /* renamed from: isGrid, reason: from getter */
    public final boolean getIsGrid() {
        return this.isGrid;
    }

    public final void setGrid(boolean z) {
        this.isGrid = z;
        invalidate();
    }

    public final int getWidthDigit() {
        return this.widthDigit;
    }

    public final void setWidthDigit(int i) {
        this.widthDigit = i;
        requestLayout();
    }

    public final int getHeightDigit() {
        return this.heightDigit;
    }

    public final void setHeightDigit(int i) {
        this.heightDigit = i;
        requestLayout();
    }

    public final Path getPath() {
        return this.path;
    }

    public final void setPath(Path path) {
        Intrinsics.checkNotNullParameter(path, "<set-?>");
        this.path = path;
    }

    private final void getAttrs(AttributeSet attrs) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attrs, R.styleable.GIFView);
        Intrinsics.checkNotNullExpressionValue(obtainStyledAttributes, "obtainStyledAttributes(...)");
        try {
            if (obtainStyledAttributes.hasValue(0)) {
                setBorderColor(obtainStyledAttributes.getColor(0, Color.parseColor("#66000000")));
            }
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int size = View.MeasureSpec.getSize(widthMeasureSpec);
        GifSupport gifSupport = this.gifSupport;
        this.size = size / (gifSupport != null ? gifSupport.getWidthNum() : this.widthDigit);
        float size2 = View.MeasureSpec.getSize(widthMeasureSpec);
        int i = this.size;
        float widthNum = size2 - (i * (this.gifSupport != null ? r1.getWidthNum() : this.widthDigit));
        float f = 2;
        this.margin = widthNum / f;
        int i2 = this.size;
        setMeasuredDimension(size, (int) ((i2 * (this.gifSupport != null ? r1.getHeightNum() : this.heightDigit)) + (this.margin * f)));
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startRendering();
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopRendering();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        super.onDraw(canvas);
        GifSupport gifSupport = this.gifSupport;
        if (gifSupport != null) {
            this.paint.reset();
            int heightNum = gifSupport.getHeightNum();
            int i = 0;
            for (int i2 = 0; i2 < heightNum; i2++) {
                int widthNum = gifSupport.getWidthNum();
                for (int i3 = 0; i3 < widthNum; i3++) {
                    this.paint.setColor(gifSupport.getData().get(gifSupport.getPos())[(gifSupport.getWidthNum() * i2) + i3]);
                    float f = this.margin;
                    int i4 = this.size;
                    float f2 = (i3 * i4) + f;
                    float f3 = f + (i4 * i2);
                    if (canvas != null) {
                        int i5 = this.size;
                        canvas.drawRect(new Rect((int) f2, (int) f3, (int) (f2 + i5), (int) (f3 + i5)), this.paint);
                    }
                }
            }
            if (this.isGrid) {
                this.path.reset();
                int heightNum2 = gifSupport.getHeightNum();
                if (heightNum2 >= 0) {
                    int i6 = 0;
                    while (true) {
                        Path path = this.path;
                        float f4 = this.margin;
                        path.moveTo(f4, (this.size * i6) + f4);
                        Path path2 = this.path;
                        float width = getWidth();
                        float f5 = this.margin;
                        path2.lineTo(width - f5, f5 + (this.size * i6));
                        if (i6 == heightNum2) {
                            break;
                        } else {
                            i6++;
                        }
                    }
                }
                int widthNum2 = gifSupport.getWidthNum();
                if (widthNum2 >= 0) {
                    while (true) {
                        Path path3 = this.path;
                        float f6 = this.margin;
                        path3.moveTo((this.size * i) + f6, f6);
                        this.path.lineTo(this.margin + (this.size * i), getHeight() - this.margin);
                        if (i == widthNum2) {
                            break;
                        } else {
                            i++;
                        }
                    }
                }
                this.paint.setColor(this.borderColor);
                this.paint.setStrokeWidth(1.0f);
                this.paint.setStyle(Paint.Style.STROKE);
                if (canvas != null) {
                    canvas.drawPath(this.path, this.paint);
                }
            }
        }
    }

    @Override // android.view.Choreographer.FrameCallback
    public void doFrame(long frameTimeNanos) {
        GifSupport gifSupport = this.gifSupport;
        if (gifSupport != null) {
            gifSupport.next();
        }
        if (this.gifSupport != null) {
            invalidate();
        } else {
            stopRendering();
        }
        if (this.isRendering) {
            Choreographer choreographer = this.choreographer;
            GIFView gIFView = this;
            GifSupport gifSupport2 = this.gifSupport;
            choreographer.postFrameCallbackDelayed(gIFView, gifSupport2 != null ? gifSupport2.getSeekGif() : 100L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startRendering() {
        if (this.isRendering) {
            return;
        }
        this.isRendering = true;
        this.choreographer.postFrameCallback(this);
    }

    private final void stopRendering() {
        invalidate();
        if (this.isRendering) {
            this.isRendering = false;
            this.choreographer.removeFrameCallback(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object getHttpToStream(String str, Continuation<? super InputStream> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new GIFView$getHttpToStream$2(str, null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object getFileToStream(String str, Continuation<? super InputStream> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new GIFView$getFileToStream$2(str, null), continuation);
    }

    public final void setColorData(int colorInt) {
        int i = this.widthDigit * this.heightDigit;
        Integer[] numArr = new Integer[i];
        for (int i2 = 0; i2 < i; i2++) {
            numArr[i2] = Integer.valueOf(colorInt);
        }
        setGifSupport(new GifSupport(this.widthDigit, this.heightDigit, 10L, 0, CollectionsKt.mutableListOf(ArraysKt.toIntArray(numArr))));
        invalidate();
    }

    public final void setUrlOrFile(String link) {
        if (link == null) {
            return;
        }
        setGifSupport(null);
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new GIFView$setUrlOrFile$1(link, this, null), 3, null);
    }

    public final void setBitmap(Bitmap frame) {
        Intrinsics.checkNotNullParameter(frame, "frame");
        final int width = frame.getWidth();
        final int height = frame.getHeight();
        int[] iArr = new int[width * height];
        frame.getPixels(iArr, 0, width, 0, 0, width, height);
        setGifSupport(new GifSupport(width, height, 10L, 0, CollectionsKt.mutableListOf(iArr)));
        post(new Runnable() { // from class: com.wifiled.ipixels.view.GIFView$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                GIFView.setBitmap$lambda$7(GIFView.this, width, height);
            }
        });
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setBitmap$lambda$7(GIFView gIFView, int i, int i2) {
        gIFView.setWidthDigit(i);
        gIFView.setHeightDigit(i2);
    }

    public final void setRawGifBytes(byte[] r13) {
        Intrinsics.checkNotNullParameter(r13, "byte");
        GifDrawable gifDrawable = new GifDrawable(r13);
        int intrinsicWidth = gifDrawable.getIntrinsicWidth();
        int intrinsicHeight = gifDrawable.getIntrinsicHeight();
        ArrayList arrayList = new ArrayList();
        int numberOfFrames = gifDrawable.getNumberOfFrames();
        int i = 0;
        while (i < numberOfFrames) {
            int[] iArr = new int[intrinsicWidth * intrinsicHeight];
            int i2 = intrinsicHeight;
            int i3 = intrinsicWidth;
            gifDrawable.seekToFrameAndGet(i).getPixels(iArr, 0, i3, 0, 0, intrinsicWidth, i2);
            intrinsicWidth = i3;
            arrayList.add(iArr);
            i++;
            intrinsicHeight = i2;
        }
        int i4 = intrinsicHeight;
        setGifSupport(new GifSupport(intrinsicWidth, i4, gifDrawable.getDuration() / gifDrawable.getNumberOfFrames(), 0, arrayList));
        setWidthDigit(intrinsicWidth);
        setHeightDigit(i4);
        startRendering();
        gifDrawable.recycle();
    }

    public final void setRawPictureBytes(byte[] r3) {
        Intrinsics.checkNotNullParameter(r3, "byte");
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(r3, 0, r3.length);
        Intrinsics.checkNotNull(decodeByteArray);
        setBitmap(decodeByteArray);
    }
}
