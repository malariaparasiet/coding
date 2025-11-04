package jp.co.cyberagent.android.gpuimage.filter;

import android.opengl.GLES20;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import jp.co.cyberagent.android.gpuimage.GPUImageRenderer;
import jp.co.cyberagent.android.gpuimage.util.Rotation;
import jp.co.cyberagent.android.gpuimage.util.TextureRotationUtil;

/* loaded from: classes3.dex */
public class GPUImageFilterGroup extends GPUImageFilter {
    private List<GPUImageFilter> filters;
    private int[] frameBufferTextures;
    private int[] frameBuffers;
    private final FloatBuffer glCubeBuffer;
    private final FloatBuffer glTextureBuffer;
    private final FloatBuffer glTextureFlipBuffer;
    private List<GPUImageFilter> mergedFilters;

    public GPUImageFilterGroup() {
        this(null);
    }

    public GPUImageFilterGroup(List<GPUImageFilter> list) {
        this.filters = list;
        if (list == null) {
            this.filters = new ArrayList();
        } else {
            updateMergedFilters();
        }
        FloatBuffer asFloatBuffer = ByteBuffer.allocateDirect(GPUImageRenderer.CUBE.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.glCubeBuffer = asFloatBuffer;
        asFloatBuffer.put(GPUImageRenderer.CUBE).position(0);
        FloatBuffer asFloatBuffer2 = ByteBuffer.allocateDirect(TextureRotationUtil.TEXTURE_NO_ROTATION.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.glTextureBuffer = asFloatBuffer2;
        asFloatBuffer2.put(TextureRotationUtil.TEXTURE_NO_ROTATION).position(0);
        float[] rotation = TextureRotationUtil.getRotation(Rotation.NORMAL, false, true);
        FloatBuffer asFloatBuffer3 = ByteBuffer.allocateDirect(rotation.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.glTextureFlipBuffer = asFloatBuffer3;
        asFloatBuffer3.put(rotation).position(0);
    }

    public void addFilter(GPUImageFilter gPUImageFilter) {
        if (gPUImageFilter == null) {
            return;
        }
        this.filters.add(gPUImageFilter);
        updateMergedFilters();
    }

    @Override // jp.co.cyberagent.android.gpuimage.filter.GPUImageFilter
    public void onInit() {
        super.onInit();
        Iterator<GPUImageFilter> it = this.filters.iterator();
        while (it.hasNext()) {
            it.next().ifNeedInit();
        }
    }

    @Override // jp.co.cyberagent.android.gpuimage.filter.GPUImageFilter
    public void onDestroy() {
        destroyFramebuffers();
        Iterator<GPUImageFilter> it = this.filters.iterator();
        while (it.hasNext()) {
            it.next().destroy();
        }
        super.onDestroy();
    }

    private void destroyFramebuffers() {
        int[] iArr = this.frameBufferTextures;
        if (iArr != null) {
            GLES20.glDeleteTextures(iArr.length, iArr, 0);
            this.frameBufferTextures = null;
        }
        int[] iArr2 = this.frameBuffers;
        if (iArr2 != null) {
            GLES20.glDeleteFramebuffers(iArr2.length, iArr2, 0);
            this.frameBuffers = null;
        }
    }

    @Override // jp.co.cyberagent.android.gpuimage.filter.GPUImageFilter
    public void onOutputSizeChanged(int i, int i2) {
        super.onOutputSizeChanged(i, i2);
        if (this.frameBuffers != null) {
            destroyFramebuffers();
        }
        int size = this.filters.size();
        for (int i3 = 0; i3 < size; i3++) {
            this.filters.get(i3).onOutputSizeChanged(i, i2);
        }
        int i4 = i2;
        List<GPUImageFilter> list = this.mergedFilters;
        if (list == null || list.size() <= 0) {
            return;
        }
        int size2 = this.mergedFilters.size() - 1;
        this.frameBuffers = new int[size2];
        this.frameBufferTextures = new int[size2];
        int i5 = 0;
        while (i5 < size2) {
            GLES20.glGenFramebuffers(1, this.frameBuffers, i5);
            GLES20.glGenTextures(1, this.frameBufferTextures, i5);
            GLES20.glBindTexture(3553, this.frameBufferTextures[i5]);
            GLES20.glTexImage2D(3553, 0, 6408, i, i4, 0, 6408, 5121, null);
            GLES20.glTexParameterf(3553, 10240, 9729.0f);
            GLES20.glTexParameterf(3553, 10241, 9729.0f);
            GLES20.glTexParameterf(3553, 10242, 33071.0f);
            GLES20.glTexParameterf(3553, 10243, 33071.0f);
            GLES20.glBindFramebuffer(36160, this.frameBuffers[i5]);
            GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.frameBufferTextures[i5], 0);
            GLES20.glBindTexture(3553, 0);
            GLES20.glBindFramebuffer(36160, 0);
            i5++;
            i4 = i2;
        }
    }

    @Override // jp.co.cyberagent.android.gpuimage.filter.GPUImageFilter
    public void onDraw(int i, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        List<GPUImageFilter> list;
        runPendingOnDrawTasks();
        if (!isInitialized() || this.frameBuffers == null || this.frameBufferTextures == null || (list = this.mergedFilters) == null) {
            return;
        }
        int size = list.size();
        int i2 = 0;
        while (i2 < size) {
            GPUImageFilter gPUImageFilter = this.mergedFilters.get(i2);
            int i3 = size - 1;
            boolean z = i2 < i3;
            if (z) {
                GLES20.glBindFramebuffer(36160, this.frameBuffers[i2]);
                GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
            }
            if (i2 == 0) {
                gPUImageFilter.onDraw(i, floatBuffer, floatBuffer2);
            } else if (i2 == i3) {
                gPUImageFilter.onDraw(i, this.glCubeBuffer, size % 2 == 0 ? this.glTextureFlipBuffer : this.glTextureBuffer);
            } else {
                gPUImageFilter.onDraw(i, this.glCubeBuffer, this.glTextureBuffer);
            }
            if (z) {
                GLES20.glBindFramebuffer(36160, 0);
                i = this.frameBufferTextures[i2];
            }
            i2++;
        }
    }

    public List<GPUImageFilter> getFilters() {
        return this.filters;
    }

    public List<GPUImageFilter> getMergedFilters() {
        return this.mergedFilters;
    }

    public void updateMergedFilters() {
        if (this.filters == null) {
            return;
        }
        List<GPUImageFilter> list = this.mergedFilters;
        if (list == null) {
            this.mergedFilters = new ArrayList();
        } else {
            list.clear();
        }
        for (GPUImageFilter gPUImageFilter : this.filters) {
            if (gPUImageFilter instanceof GPUImageFilterGroup) {
                GPUImageFilterGroup gPUImageFilterGroup = (GPUImageFilterGroup) gPUImageFilter;
                gPUImageFilterGroup.updateMergedFilters();
                List<GPUImageFilter> mergedFilters = gPUImageFilterGroup.getMergedFilters();
                if (mergedFilters != null && !mergedFilters.isEmpty()) {
                    this.mergedFilters.addAll(mergedFilters);
                }
            } else {
                this.mergedFilters.add(gPUImageFilter);
            }
        }
    }
}
