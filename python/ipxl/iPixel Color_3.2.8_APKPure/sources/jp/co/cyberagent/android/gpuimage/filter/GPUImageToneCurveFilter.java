package jp.co.cyberagent.android.gpuimage.filter;

import android.graphics.Point;
import android.graphics.PointF;
import android.opengl.GLES20;
import androidx.camera.video.AudioStats;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

/* loaded from: classes3.dex */
public class GPUImageToneCurveFilter extends GPUImageFilter {
    public static final String TONE_CURVE_FRAGMENT_SHADER = " varying highp vec2 textureCoordinate;\n uniform sampler2D inputImageTexture;\n uniform sampler2D toneCurveTexture;\n\n void main()\n {\n     lowp vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);\n     lowp float redCurveValue = texture2D(toneCurveTexture, vec2(textureColor.r, 0.0)).r;\n     lowp float greenCurveValue = texture2D(toneCurveTexture, vec2(textureColor.g, 0.0)).g;\n     lowp float blueCurveValue = texture2D(toneCurveTexture, vec2(textureColor.b, 0.0)).b;\n\n     gl_FragColor = vec4(redCurveValue, greenCurveValue, blueCurveValue, textureColor.a);\n }";
    private PointF[] blueControlPoints;
    private ArrayList<Float> blueCurve;
    private PointF[] greenControlPoints;
    private ArrayList<Float> greenCurve;
    private PointF[] redControlPoints;
    private ArrayList<Float> redCurve;
    private PointF[] rgbCompositeControlPoints;
    private ArrayList<Float> rgbCompositeCurve;
    private int[] toneCurveTexture;
    private int toneCurveTextureUniformLocation;

    public GPUImageToneCurveFilter() {
        super(GPUImageFilter.NO_FILTER_VERTEX_SHADER, TONE_CURVE_FRAGMENT_SHADER);
        this.toneCurveTexture = new int[]{-1};
        PointF[] pointFArr = {new PointF(0.0f, 0.0f), new PointF(0.5f, 0.5f), new PointF(1.0f, 1.0f)};
        this.rgbCompositeControlPoints = pointFArr;
        this.redControlPoints = pointFArr;
        this.greenControlPoints = pointFArr;
        this.blueControlPoints = pointFArr;
    }

    @Override // jp.co.cyberagent.android.gpuimage.filter.GPUImageFilter
    public void onInit() {
        super.onInit();
        this.toneCurveTextureUniformLocation = GLES20.glGetUniformLocation(getProgram(), "toneCurveTexture");
        GLES20.glActiveTexture(33987);
        GLES20.glGenTextures(1, this.toneCurveTexture, 0);
        GLES20.glBindTexture(3553, this.toneCurveTexture[0]);
        GLES20.glTexParameteri(3553, 10241, 9729);
        GLES20.glTexParameteri(3553, 10240, 9729);
        GLES20.glTexParameteri(3553, 10242, 33071);
        GLES20.glTexParameteri(3553, 10243, 33071);
    }

    @Override // jp.co.cyberagent.android.gpuimage.filter.GPUImageFilter
    public void onInitialized() {
        super.onInitialized();
        setRgbCompositeControlPoints(this.rgbCompositeControlPoints);
        setRedControlPoints(this.redControlPoints);
        setGreenControlPoints(this.greenControlPoints);
        setBlueControlPoints(this.blueControlPoints);
    }

    @Override // jp.co.cyberagent.android.gpuimage.filter.GPUImageFilter
    protected void onDrawArraysPre() {
        if (this.toneCurveTexture[0] != -1) {
            GLES20.glActiveTexture(33987);
            GLES20.glBindTexture(3553, this.toneCurveTexture[0]);
            GLES20.glUniform1i(this.toneCurveTextureUniformLocation, 3);
        }
    }

    public void setFromCurveFileInputStream(InputStream inputStream) {
        try {
            readShort(inputStream);
            short readShort = readShort(inputStream);
            ArrayList arrayList = new ArrayList(readShort);
            for (int i = 0; i < readShort; i++) {
                int readShort2 = readShort(inputStream);
                PointF[] pointFArr = new PointF[readShort2];
                for (int i2 = 0; i2 < readShort2; i2++) {
                    pointFArr[i2] = new PointF(readShort(inputStream) * 0.003921569f, readShort(inputStream) * 0.003921569f);
                }
                arrayList.add(pointFArr);
            }
            inputStream.close();
            this.rgbCompositeControlPoints = (PointF[]) arrayList.get(0);
            this.redControlPoints = (PointF[]) arrayList.get(1);
            this.greenControlPoints = (PointF[]) arrayList.get(2);
            this.blueControlPoints = (PointF[]) arrayList.get(3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private short readShort(InputStream inputStream) throws IOException {
        return (short) (inputStream.read() | (inputStream.read() << 8));
    }

    public void setRgbCompositeControlPoints(PointF[] pointFArr) {
        this.rgbCompositeControlPoints = pointFArr;
        this.rgbCompositeCurve = createSplineCurve(pointFArr);
        updateToneCurveTexture();
    }

    public void setRedControlPoints(PointF[] pointFArr) {
        this.redControlPoints = pointFArr;
        this.redCurve = createSplineCurve(pointFArr);
        updateToneCurveTexture();
    }

    public void setGreenControlPoints(PointF[] pointFArr) {
        this.greenControlPoints = pointFArr;
        this.greenCurve = createSplineCurve(pointFArr);
        updateToneCurveTexture();
    }

    public void setBlueControlPoints(PointF[] pointFArr) {
        this.blueControlPoints = pointFArr;
        this.blueCurve = createSplineCurve(pointFArr);
        updateToneCurveTexture();
    }

    private void updateToneCurveTexture() {
        runOnDraw(new Runnable() { // from class: jp.co.cyberagent.android.gpuimage.filter.GPUImageToneCurveFilter.1
            @Override // java.lang.Runnable
            public void run() {
                GLES20.glActiveTexture(33987);
                GLES20.glBindTexture(3553, GPUImageToneCurveFilter.this.toneCurveTexture[0]);
                if (GPUImageToneCurveFilter.this.redCurve.size() < 256 || GPUImageToneCurveFilter.this.greenCurve.size() < 256 || GPUImageToneCurveFilter.this.blueCurve.size() < 256 || GPUImageToneCurveFilter.this.rgbCompositeCurve.size() < 256) {
                    return;
                }
                byte[] bArr = new byte[1024];
                for (int i = 0; i < 256; i++) {
                    int i2 = i * 4;
                    float f = i;
                    bArr[i2 + 2] = (byte) (((int) Math.min(Math.max(((Float) GPUImageToneCurveFilter.this.blueCurve.get(i)).floatValue() + f + ((Float) GPUImageToneCurveFilter.this.rgbCompositeCurve.get(i)).floatValue(), 0.0f), 255.0f)) & 255);
                    bArr[i2 + 1] = (byte) (((int) Math.min(Math.max(((Float) GPUImageToneCurveFilter.this.greenCurve.get(i)).floatValue() + f + ((Float) GPUImageToneCurveFilter.this.rgbCompositeCurve.get(i)).floatValue(), 0.0f), 255.0f)) & 255);
                    bArr[i2] = (byte) (((int) Math.min(Math.max(f + ((Float) GPUImageToneCurveFilter.this.redCurve.get(i)).floatValue() + ((Float) GPUImageToneCurveFilter.this.rgbCompositeCurve.get(i)).floatValue(), 0.0f), 255.0f)) & 255);
                    bArr[i2 + 3] = -1;
                }
                GLES20.glTexImage2D(3553, 0, 6408, 256, 1, 0, 6408, 5121, ByteBuffer.wrap(bArr));
            }
        });
    }

    private ArrayList<Float> createSplineCurve(PointF[] pointFArr) {
        if (pointFArr == null || pointFArr.length <= 0) {
            return null;
        }
        PointF[] pointFArr2 = (PointF[]) pointFArr.clone();
        Arrays.sort(pointFArr2, new Comparator<PointF>() { // from class: jp.co.cyberagent.android.gpuimage.filter.GPUImageToneCurveFilter.2
            @Override // java.util.Comparator
            public int compare(PointF pointF, PointF pointF2) {
                if (pointF.x < pointF2.x) {
                    return -1;
                }
                return pointF.x > pointF2.x ? 1 : 0;
            }
        });
        Point[] pointArr = new Point[pointFArr2.length];
        for (int i = 0; i < pointFArr.length; i++) {
            PointF pointF = pointFArr2[i];
            pointArr[i] = new Point((int) (pointF.x * 255.0f), (int) (pointF.y * 255.0f));
        }
        ArrayList<Point> createSplineCurve2 = createSplineCurve2(pointArr);
        Point point = createSplineCurve2.get(0);
        if (point.x > 0) {
            for (int i2 = point.x; i2 >= 0; i2--) {
                createSplineCurve2.add(0, new Point(i2, 0));
            }
        }
        Point point2 = createSplineCurve2.get(createSplineCurve2.size() - 1);
        if (point2.x < 255) {
            int i3 = point2.x;
            while (true) {
                i3++;
                if (i3 > 255) {
                    break;
                }
                createSplineCurve2.add(new Point(i3, 255));
            }
        }
        ArrayList<Float> arrayList = new ArrayList<>(createSplineCurve2.size());
        Iterator<Point> it = createSplineCurve2.iterator();
        while (it.hasNext()) {
            Point next = it.next();
            Point point3 = new Point(next.x, next.x);
            float sqrt = (float) Math.sqrt(Math.pow(point3.x - next.x, 2.0d) + Math.pow(point3.y - next.y, 2.0d));
            if (point3.y > next.y) {
                sqrt = -sqrt;
            }
            arrayList.add(Float.valueOf(sqrt));
        }
        return arrayList;
    }

    private ArrayList<Point> createSplineCurve2(Point[] pointArr) {
        ArrayList<Double> createSecondDerivative = createSecondDerivative(pointArr);
        int size = createSecondDerivative.size();
        boolean z = true;
        if (size < 1) {
            return null;
        }
        double[] dArr = new double[size];
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            dArr[i2] = createSecondDerivative.get(i2).doubleValue();
        }
        ArrayList<Point> arrayList = new ArrayList<>(size + 1);
        while (i < size - 1) {
            Point point = pointArr[i];
            int i3 = i + 1;
            Point point2 = pointArr[i3];
            int i4 = point.x;
            while (i4 < point2.x) {
                double d = (i4 - point.x) / (point2.x - point.x);
                double d2 = 1.0d - d;
                double d3 = point2.x - point.x;
                boolean z2 = z;
                int i5 = size;
                double d4 = (point.y * d2) + (point2.y * d) + (((d3 * d3) / 6.0d) * (((((d2 * d2) * d2) - d2) * dArr[i]) + ((((d * d) * d) - d) * dArr[i3])));
                double d5 = 255.0d;
                if (d4 <= 255.0d) {
                    d5 = AudioStats.AUDIO_AMPLITUDE_NONE;
                    if (d4 >= AudioStats.AUDIO_AMPLITUDE_NONE) {
                        arrayList.add(new Point(i4, (int) Math.round(d4)));
                        i4++;
                        z = z2;
                        size = i5;
                    }
                }
                d4 = d5;
                arrayList.add(new Point(i4, (int) Math.round(d4)));
                i4++;
                z = z2;
                size = i5;
            }
            i = i3;
        }
        if (arrayList.size() == 255) {
            arrayList.add(pointArr[pointArr.length - 1]);
        }
        return arrayList;
    }

    private ArrayList<Double> createSecondDerivative(Point[] pointArr) {
        int i;
        int length = pointArr.length;
        int i2 = 1;
        if (length <= 1) {
            return null;
        }
        char c = 2;
        int i3 = 0;
        double[][] dArr = (double[][]) Array.newInstance((Class<?>) Double.TYPE, length, 3);
        double[] dArr2 = new double[length];
        double[] dArr3 = dArr[0];
        double d = 1.0d;
        dArr3[1] = 1.0d;
        dArr3[0] = 0.0d;
        dArr3[2] = 0.0d;
        int i4 = 1;
        while (true) {
            i = length - 1;
            if (i4 >= i) {
                break;
            }
            Point point = pointArr[i4 - 1];
            Point point2 = pointArr[i4];
            int i5 = i4 + 1;
            Point point3 = pointArr[i5];
            int i6 = i2;
            char c2 = c;
            dArr[i4][i3] = (point2.x - point.x) / 6.0d;
            dArr[i4][i6] = (point3.x - point.x) / 3.0d;
            dArr[i4][c2] = (point3.x - point2.x) / 6.0d;
            dArr2[i4] = ((point3.y - point2.y) / (point3.x - point2.x)) - ((point2.y - point.y) / (point2.x - point.x));
            i4 = i5;
            i3 = i3;
            i2 = i6;
            c = c2;
            d = d;
        }
        int i7 = i2;
        char c3 = c;
        int i8 = i3;
        dArr2[i8] = 0.0d;
        dArr2[i] = 0.0d;
        double[] dArr4 = dArr[i];
        dArr4[i7] = d;
        dArr4[i8] = 0.0d;
        dArr4[c3] = 0.0d;
        for (int i9 = i7; i9 < length; i9++) {
            double[] dArr5 = dArr[i9];
            double d2 = dArr5[i8];
            int i10 = i9 - 1;
            double[] dArr6 = dArr[i10];
            double d3 = d2 / dArr6[i7];
            dArr5[i7] = dArr5[i7] - (dArr6[c3] * d3);
            dArr5[i8] = 0.0d;
            dArr2[i9] = dArr2[i9] - (d3 * dArr2[i10]);
        }
        for (int i11 = length - 2; i11 >= 0; i11--) {
            double[] dArr7 = dArr[i11];
            double d4 = dArr7[c3];
            int i12 = i11 + 1;
            double[] dArr8 = dArr[i12];
            double d5 = d4 / dArr8[i7];
            dArr7[i7] = dArr7[i7] - (dArr8[i8] * d5);
            dArr7[c3] = 0.0d;
            dArr2[i11] = dArr2[i11] - (d5 * dArr2[i12]);
        }
        ArrayList<Double> arrayList = new ArrayList<>(length);
        for (int i13 = i8; i13 < length; i13++) {
            arrayList.add(Double.valueOf(dArr2[i13] / dArr[i13][i7]));
        }
        return arrayList;
    }
}
