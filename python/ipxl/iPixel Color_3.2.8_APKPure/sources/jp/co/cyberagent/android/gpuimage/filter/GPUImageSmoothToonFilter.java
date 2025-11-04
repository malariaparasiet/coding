package jp.co.cyberagent.android.gpuimage.filter;

/* loaded from: classes3.dex */
public class GPUImageSmoothToonFilter extends GPUImageFilterGroup {
    private GPUImageGaussianBlurFilter blurFilter;
    private GPUImageToonFilter toonFilter;

    public GPUImageSmoothToonFilter() {
        GPUImageGaussianBlurFilter gPUImageGaussianBlurFilter = new GPUImageGaussianBlurFilter();
        this.blurFilter = gPUImageGaussianBlurFilter;
        addFilter(gPUImageGaussianBlurFilter);
        GPUImageToonFilter gPUImageToonFilter = new GPUImageToonFilter();
        this.toonFilter = gPUImageToonFilter;
        addFilter(gPUImageToonFilter);
        getFilters().add(this.blurFilter);
    }

    @Override // jp.co.cyberagent.android.gpuimage.filter.GPUImageFilter
    public void onInitialized() {
        super.onInitialized();
        setBlurSize(0.5f);
        setThreshold(0.2f);
        setQuantizationLevels(10.0f);
    }

    public void setTexelWidth(float f) {
        this.toonFilter.setTexelWidth(f);
    }

    public void setTexelHeight(float f) {
        this.toonFilter.setTexelHeight(f);
    }

    public void setBlurSize(float f) {
        this.blurFilter.setBlurSize(f);
    }

    public void setThreshold(float f) {
        this.toonFilter.setThreshold(f);
    }

    public void setQuantizationLevels(float f) {
        this.toonFilter.setQuantizationLevels(f);
    }
}
