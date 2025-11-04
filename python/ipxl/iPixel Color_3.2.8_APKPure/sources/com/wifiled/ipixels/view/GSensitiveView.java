package com.wifiled.ipixels.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;
import com.wifiled.ipixels.R;

/* loaded from: classes3.dex */
public class GSensitiveView extends AppCompatImageView {
    private Bitmap image;
    private Paint paint;
    private double rotation;

    public GSensitiveView(Context context) {
        this(context, null);
    }

    public GSensitiveView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GSensitiveView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.image = ((BitmapDrawable) context.getResources().getDrawable(R.drawable.ic_launcher)).getBitmap();
        this.paint = new Paint();
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        double width = this.image.getWidth();
        double height = this.image.getHeight();
        getDrawingRect(new Rect());
        canvas.rotate((int) ((this.rotation * 180.0d) / 3.141592653589793d), r4.width() / 2, r4.height() / 2);
        canvas.drawBitmap(this.image, (float) ((r4.width() - width) / 2.0d), (float) ((r4.height() - height) / 2.0d), this.paint);
    }

    public void setRotation(double rad) {
        this.rotation = rad;
        invalidate();
    }
}
