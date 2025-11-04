package com.wifiled.baselib.uicode.roundview;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageView;
import androidx.appcompat.widget.AppCompatImageView;
import com.wifiled.baselib.R;

@Deprecated
/* loaded from: classes2.dex */
public class RoundImageView extends AppCompatImageView {
    private static final ImageView.ScaleType[] sScaleTypeArray = {ImageView.ScaleType.MATRIX, ImageView.ScaleType.FIT_XY, ImageView.ScaleType.FIT_START, ImageView.ScaleType.FIT_CENTER, ImageView.ScaleType.FIT_END, ImageView.ScaleType.CENTER, ImageView.ScaleType.CENTER_CROP, ImageView.ScaleType.CENTER_INSIDE};
    private final int DEFAULT_BORDER_COLOR;
    private boolean isOval;
    private ColorStateList mBorderColor;
    private float mBorderWidth;
    private Drawable mDrawable;
    private float mLeftBottomCornerRadius;
    private float mLeftTopCornerRadius;
    private float[] mRadii;
    private int mResource;
    private float mRightBottomCornerRadius;
    private float mRightTopCornerRadius;
    private ImageView.ScaleType mScaleType;

    public RoundImageView(Context context) {
        super(context);
        this.mResource = 0;
        this.mScaleType = ImageView.ScaleType.CENTER_CROP;
        this.mLeftTopCornerRadius = 0.0f;
        this.mRightTopCornerRadius = 0.0f;
        this.mLeftBottomCornerRadius = 0.0f;
        this.mRightBottomCornerRadius = 0.0f;
        this.mBorderWidth = 0.0f;
        this.DEFAULT_BORDER_COLOR = 0;
        this.mBorderColor = ColorStateList.valueOf(0);
        this.isOval = false;
        this.mRadii = new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
    }

    public RoundImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RoundImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mResource = 0;
        this.mScaleType = ImageView.ScaleType.CENTER_CROP;
        this.mLeftTopCornerRadius = 0.0f;
        this.mRightTopCornerRadius = 0.0f;
        this.mLeftBottomCornerRadius = 0.0f;
        this.mRightBottomCornerRadius = 0.0f;
        this.mBorderWidth = 0.0f;
        this.DEFAULT_BORDER_COLOR = 0;
        this.mBorderColor = ColorStateList.valueOf(0);
        this.isOval = false;
        this.mRadii = new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
        try {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RoundedImageView, i, 0);
            int i2 = obtainStyledAttributes.getInt(R.styleable.RoundedImageView_android_scaleType, -1);
            if (i2 >= 0) {
                setScaleType(sScaleTypeArray[i2]);
            }
            this.mLeftTopCornerRadius = obtainStyledAttributes.getDimensionPixelSize(R.styleable.RoundedImageView_rv_left_top_corner_radius, 0);
            this.mRightTopCornerRadius = obtainStyledAttributes.getDimensionPixelSize(R.styleable.RoundedImageView_rv_right_top_corner_radius, 0);
            this.mLeftBottomCornerRadius = obtainStyledAttributes.getDimensionPixelSize(R.styleable.RoundedImageView_rv_left_bottom_corner_radius, 0);
            float dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.RoundedImageView_rv_right_bottom_corner_radius, 0);
            this.mRightBottomCornerRadius = dimensionPixelSize;
            if (this.mLeftTopCornerRadius < 0.0f || this.mRightTopCornerRadius < 0.0f || this.mLeftBottomCornerRadius < 0.0f || dimensionPixelSize < 0.0f) {
                throw new IllegalArgumentException("radius values cannot be negative.");
            }
            configRadii();
            float dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.RoundedImageView_rv_border_width, 0);
            this.mBorderWidth = dimensionPixelSize2;
            if (dimensionPixelSize2 < 0.0f) {
                throw new IllegalArgumentException("border width cannot be negative.");
            }
            ColorStateList colorStateList = obtainStyledAttributes.getColorStateList(R.styleable.RoundedImageView_rv_border_color);
            this.mBorderColor = colorStateList;
            if (colorStateList == null) {
                this.mBorderColor = ColorStateList.valueOf(0);
            }
            this.isOval = obtainStyledAttributes.getBoolean(R.styleable.RoundedImageView_rv_oval, false);
            obtainStyledAttributes.recycle();
            updateDrawable();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    private void configRadii() {
        float f = this.mLeftTopCornerRadius;
        float f2 = this.mRightTopCornerRadius;
        float f3 = this.mRightBottomCornerRadius;
        float f4 = this.mLeftBottomCornerRadius;
        this.mRadii = new float[]{f, f, f2, f2, f3, f3, f4, f4};
    }

    public void updateLeftTopCornerRadius(float f) {
        this.mLeftTopCornerRadius = f;
        configRadii();
        invalidate();
    }

    public void updateRightTopCornerRadius(float f) {
        this.mRightTopCornerRadius = f;
        configRadii();
        invalidate();
    }

    public void updateLeftBottomCornerRadius(float f) {
        this.mLeftBottomCornerRadius = f;
        configRadii();
        invalidate();
    }

    public void updateRightBottomCornerRadius(float f) {
        this.mRightBottomCornerRadius = f;
        configRadii();
        invalidate();
    }

    public void updateTopCornerRadius(float f) {
        this.mLeftTopCornerRadius = f;
        this.mRightTopCornerRadius = f;
        configRadii();
        invalidate();
    }

    public void updateBottomCornerRadius(float f) {
        this.mLeftBottomCornerRadius = f;
        this.mRightBottomCornerRadius = f;
        configRadii();
        invalidate();
    }

    public void updateCornerRadius(float f) {
        this.mLeftTopCornerRadius = f;
        this.mRightTopCornerRadius = f;
        this.mLeftBottomCornerRadius = f;
        this.mRightBottomCornerRadius = f;
        configRadii();
        invalidate();
    }

    public void updateBorderColor(int i) {
        this.mBorderColor = ColorStateList.valueOf(i);
        updateDrawable();
        invalidate();
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        invalidate();
    }

    @Override // android.widget.ImageView
    public ImageView.ScaleType getScaleType() {
        return this.mScaleType;
    }

    @Override // android.widget.ImageView
    public void setScaleType(ImageView.ScaleType scaleType) {
        super.setScaleType(scaleType);
        this.mScaleType = scaleType;
        updateDrawable();
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        this.mResource = 0;
        Drawable fromDrawable = SelectableRoundedCornerDrawable.fromDrawable(drawable, getResources());
        this.mDrawable = fromDrawable;
        super.setImageDrawable(fromDrawable);
        updateDrawable();
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        this.mResource = 0;
        SelectableRoundedCornerDrawable fromBitmap = SelectableRoundedCornerDrawable.fromBitmap(bitmap, getResources());
        this.mDrawable = fromBitmap;
        super.setImageDrawable(fromBitmap);
        updateDrawable();
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageResource(int i) {
        if (this.mResource != i) {
            this.mResource = i;
            Drawable resolveResource = resolveResource();
            this.mDrawable = resolveResource;
            super.setImageDrawable(resolveResource);
            updateDrawable();
        }
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        setImageDrawable(getDrawable());
    }

    private Drawable resolveResource() {
        Resources resources = getResources();
        Drawable drawable = null;
        if (resources == null) {
            return null;
        }
        int i = this.mResource;
        if (i != 0) {
            try {
                drawable = resources.getDrawable(i);
            } catch (Resources.NotFoundException unused) {
                this.mResource = 0;
            }
        }
        return SelectableRoundedCornerDrawable.fromDrawable(drawable, getResources());
    }

    private void updateDrawable() {
        Drawable drawable = this.mDrawable;
        if (drawable == null) {
            return;
        }
        try {
            ((SelectableRoundedCornerDrawable) drawable).setScaleType(this.mScaleType);
            ((SelectableRoundedCornerDrawable) this.mDrawable).setCornerRadii(this.mRadii);
            ((SelectableRoundedCornerDrawable) this.mDrawable).setBorderWidth(this.mBorderWidth);
            ((SelectableRoundedCornerDrawable) this.mDrawable).setBorderColor(this.mBorderColor);
            ((SelectableRoundedCornerDrawable) this.mDrawable).setOval(this.isOval);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public float getBorderWidth() {
        return this.mBorderWidth;
    }

    public boolean isOval() {
        return this.isOval;
    }

    public void setOval(boolean z) {
        this.isOval = z;
        updateDrawable();
        invalidate();
    }

    static class SelectableRoundedCornerDrawable extends Drawable {
        private static final int DEFAULT_BORDER_COLOR = -16777216;
        private Bitmap mBitmap;
        private final int mBitmapHeight;
        private final Paint mBitmapPaint;
        private final RectF mBitmapRect;
        private BitmapShader mBitmapShader;
        private final int mBitmapWidth;
        private ColorStateList mBorderColor;
        private final Paint mBorderPaint;
        private float[] mBorderRadii;
        private float mBorderWidth;
        private boolean mBoundsConfigured;
        private boolean mOval;
        private Path mPath;
        private float[] mRadii;
        private ImageView.ScaleType mScaleType;
        private RectF mBounds = new RectF();
        private RectF mBorderBounds = new RectF();

        public SelectableRoundedCornerDrawable(Bitmap bitmap, Resources resources) {
            RectF rectF = new RectF();
            this.mBitmapRect = rectF;
            this.mRadii = new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
            this.mBorderRadii = new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
            this.mOval = false;
            this.mBorderWidth = 0.0f;
            this.mBorderColor = ColorStateList.valueOf(-16777216);
            this.mScaleType = ImageView.ScaleType.FIT_CENTER;
            this.mPath = new Path();
            this.mBoundsConfigured = false;
            this.mBitmap = bitmap;
            this.mBitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            if (bitmap != null) {
                this.mBitmapWidth = bitmap.getScaledWidth(resources.getDisplayMetrics());
                this.mBitmapHeight = bitmap.getScaledHeight(resources.getDisplayMetrics());
            } else {
                this.mBitmapHeight = -1;
                this.mBitmapWidth = -1;
            }
            rectF.set(0.0f, 0.0f, this.mBitmapWidth, this.mBitmapHeight);
            Paint paint = new Paint(1);
            this.mBitmapPaint = paint;
            paint.setStyle(Paint.Style.FILL);
            paint.setShader(this.mBitmapShader);
            Paint paint2 = new Paint(1);
            this.mBorderPaint = paint2;
            paint2.setStyle(Paint.Style.STROKE);
            paint2.setColor(this.mBorderColor.getColorForState(getState(), -16777216));
            paint2.setStrokeWidth(this.mBorderWidth);
        }

        public static SelectableRoundedCornerDrawable fromBitmap(Bitmap bitmap, Resources resources) {
            if (bitmap != null) {
                return new SelectableRoundedCornerDrawable(bitmap, resources);
            }
            return null;
        }

        public static Drawable fromDrawable(Drawable drawable, Resources resources) {
            if (drawable != null) {
                try {
                    if (!(drawable instanceof SelectableRoundedCornerDrawable)) {
                        if (drawable instanceof LayerDrawable) {
                            LayerDrawable layerDrawable = (LayerDrawable) drawable;
                            int numberOfLayers = layerDrawable.getNumberOfLayers();
                            for (int i = 0; i < numberOfLayers; i++) {
                                layerDrawable.setDrawableByLayerId(layerDrawable.getId(i), fromDrawable(layerDrawable.getDrawable(i), resources));
                            }
                            return layerDrawable;
                        }
                        Bitmap drawableToBitmap = drawableToBitmap(drawable);
                        if (drawableToBitmap != null) {
                            return new SelectableRoundedCornerDrawable(drawableToBitmap, resources);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return drawable;
        }

        public static Bitmap drawableToBitmap(Drawable drawable) {
            if (drawable == null) {
                return null;
            }
            if (drawable instanceof BitmapDrawable) {
                return ((BitmapDrawable) drawable).getBitmap();
            }
            try {
                Bitmap createBitmap = Bitmap.createBitmap(Math.max(drawable.getIntrinsicWidth(), 2), Math.max(drawable.getIntrinsicHeight(), 2), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(createBitmap);
                drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
                drawable.draw(canvas);
                return createBitmap;
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override // android.graphics.drawable.Drawable
        public boolean isStateful() {
            return this.mBorderColor.isStateful();
        }

        @Override // android.graphics.drawable.Drawable
        protected boolean onStateChange(int[] iArr) {
            int colorForState = this.mBorderColor.getColorForState(iArr, 0);
            if (this.mBorderPaint.getColor() != colorForState) {
                this.mBorderPaint.setColor(colorForState);
                return true;
            }
            return super.onStateChange(iArr);
        }

        private void configureBounds(Canvas canvas) {
            Rect clipBounds = canvas.getClipBounds();
            Matrix matrix = canvas.getMatrix();
            if (ImageView.ScaleType.CENTER == this.mScaleType) {
                this.mBounds.set(clipBounds);
                return;
            }
            if (ImageView.ScaleType.CENTER_CROP == this.mScaleType) {
                applyScaleToRadii(matrix);
                this.mBounds.set(clipBounds);
                return;
            }
            if (ImageView.ScaleType.FIT_XY == this.mScaleType) {
                Matrix matrix2 = new Matrix();
                matrix2.setRectToRect(this.mBitmapRect, new RectF(clipBounds), Matrix.ScaleToFit.FILL);
                this.mBitmapShader.setLocalMatrix(matrix2);
                this.mBounds.set(clipBounds);
                return;
            }
            if (ImageView.ScaleType.FIT_START == this.mScaleType || ImageView.ScaleType.FIT_END == this.mScaleType || ImageView.ScaleType.FIT_CENTER == this.mScaleType || ImageView.ScaleType.CENTER_INSIDE == this.mScaleType) {
                applyScaleToRadii(matrix);
                this.mBounds.set(this.mBitmapRect);
            } else if (ImageView.ScaleType.MATRIX == this.mScaleType) {
                applyScaleToRadii(matrix);
                this.mBounds.set(this.mBitmapRect);
            }
        }

        private void applyScaleToRadii(Matrix matrix) {
            float[] fArr = new float[9];
            matrix.getValues(fArr);
            int i = 0;
            while (true) {
                float[] fArr2 = this.mRadii;
                if (i >= fArr2.length) {
                    return;
                }
                fArr2[i] = fArr2[i] / fArr[0];
                i++;
            }
        }

        private void adjustCanvasForBorder(Canvas canvas) {
            float[] fArr = new float[9];
            canvas.getMatrix().getValues(fArr);
            float f = fArr[0];
            float f2 = fArr[4];
            float f3 = fArr[2];
            float f4 = fArr[5];
            float width = this.mBounds.width();
            float width2 = this.mBounds.width();
            float f5 = this.mBorderWidth;
            float f6 = width / ((width2 + f5) + f5);
            float height = this.mBounds.height();
            float height2 = this.mBounds.height();
            float f7 = this.mBorderWidth;
            float f8 = height / ((height2 + f7) + f7);
            canvas.scale(f6, f8);
            if (ImageView.ScaleType.FIT_START == this.mScaleType || ImageView.ScaleType.FIT_END == this.mScaleType || ImageView.ScaleType.FIT_XY == this.mScaleType || ImageView.ScaleType.FIT_CENTER == this.mScaleType || ImageView.ScaleType.CENTER_INSIDE == this.mScaleType || ImageView.ScaleType.MATRIX == this.mScaleType) {
                float f9 = this.mBorderWidth;
                canvas.translate(f9, f9);
            } else if (ImageView.ScaleType.CENTER == this.mScaleType || ImageView.ScaleType.CENTER_CROP == this.mScaleType) {
                canvas.translate((-f3) / (f6 * f), (-f4) / (f8 * f2));
                canvas.translate(-(this.mBounds.left - this.mBorderWidth), -(this.mBounds.top - this.mBorderWidth));
            }
        }

        private void adjustBorderWidthAndBorderBounds(Canvas canvas) {
            float[] fArr = new float[9];
            canvas.getMatrix().getValues(fArr);
            float width = (this.mBorderWidth * this.mBounds.width()) / ((this.mBounds.width() * fArr[0]) - (this.mBorderWidth * 2.0f));
            this.mBorderWidth = width;
            this.mBorderPaint.setStrokeWidth(width);
            this.mBorderBounds.set(this.mBounds);
            RectF rectF = this.mBorderBounds;
            float f = this.mBorderWidth;
            rectF.inset((-f) / 2.0f, (-f) / 2.0f);
        }

        private void setBorderRadii() {
            int i = 0;
            while (true) {
                float[] fArr = this.mRadii;
                if (i >= fArr.length) {
                    return;
                }
                float f = fArr[i];
                if (f > 0.0f) {
                    this.mBorderRadii[i] = f;
                    fArr[i] = fArr[i] - this.mBorderWidth;
                }
                i++;
            }
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            canvas.save();
            if (!this.mBoundsConfigured) {
                configureBounds(canvas);
                if (this.mBorderWidth > 0.0f) {
                    adjustBorderWidthAndBorderBounds(canvas);
                    setBorderRadii();
                }
                this.mBoundsConfigured = true;
            }
            if (this.mOval) {
                if (this.mBorderWidth > 0.0f) {
                    adjustCanvasForBorder(canvas);
                    this.mPath.addOval(this.mBounds, Path.Direction.CW);
                    canvas.drawPath(this.mPath, this.mBitmapPaint);
                    this.mPath.reset();
                    this.mPath.addOval(this.mBorderBounds, Path.Direction.CW);
                    canvas.drawPath(this.mPath, this.mBorderPaint);
                } else {
                    this.mPath.addOval(this.mBounds, Path.Direction.CW);
                    canvas.drawPath(this.mPath, this.mBitmapPaint);
                }
            } else if (this.mBorderWidth > 0.0f) {
                adjustCanvasForBorder(canvas);
                this.mPath.addRoundRect(this.mBounds, this.mRadii, Path.Direction.CW);
                canvas.drawPath(this.mPath, this.mBitmapPaint);
                this.mPath.reset();
                this.mPath.addRoundRect(this.mBorderBounds, this.mBorderRadii, Path.Direction.CW);
                canvas.drawPath(this.mPath, this.mBorderPaint);
            } else {
                this.mPath.addRoundRect(this.mBounds, this.mRadii, Path.Direction.CW);
                canvas.drawPath(this.mPath, this.mBitmapPaint);
            }
            canvas.restore();
        }

        public void setCornerRadii(float[] fArr) {
            if (fArr == null) {
                return;
            }
            if (fArr.length != 8) {
                throw new ArrayIndexOutOfBoundsException("radii[] needs 8 values");
            }
            for (int i = 0; i < fArr.length; i++) {
                this.mRadii[i] = fArr[i];
            }
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            Bitmap bitmap = this.mBitmap;
            return (bitmap == null || bitmap.hasAlpha() || this.mBitmapPaint.getAlpha() < 255) ? -3 : -1;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int i) {
            this.mBitmapPaint.setAlpha(i);
            invalidateSelf();
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter colorFilter) {
            this.mBitmapPaint.setColorFilter(colorFilter);
            invalidateSelf();
        }

        @Override // android.graphics.drawable.Drawable
        public void setDither(boolean z) {
            this.mBitmapPaint.setDither(z);
            invalidateSelf();
        }

        @Override // android.graphics.drawable.Drawable
        public void setFilterBitmap(boolean z) {
            this.mBitmapPaint.setFilterBitmap(z);
            invalidateSelf();
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicWidth() {
            return this.mBitmapWidth;
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicHeight() {
            return this.mBitmapHeight;
        }

        public float getBorderWidth() {
            return this.mBorderWidth;
        }

        public void setBorderWidth(float f) {
            this.mBorderWidth = f;
            this.mBorderPaint.setStrokeWidth(f);
        }

        public void setBorderColor(ColorStateList colorStateList) {
            if (colorStateList == null) {
                this.mBorderWidth = 0.0f;
                this.mBorderColor = ColorStateList.valueOf(0);
                this.mBorderPaint.setColor(0);
            } else {
                this.mBorderColor = colorStateList;
                this.mBorderPaint.setColor(colorStateList.getColorForState(getState(), -16777216));
            }
        }

        public boolean isOval() {
            return this.mOval;
        }

        public void setOval(boolean z) {
            this.mOval = z;
        }

        public ImageView.ScaleType getScaleType() {
            return this.mScaleType;
        }

        public void setScaleType(ImageView.ScaleType scaleType) {
            if (scaleType == null) {
                return;
            }
            this.mScaleType = scaleType;
        }
    }
}
