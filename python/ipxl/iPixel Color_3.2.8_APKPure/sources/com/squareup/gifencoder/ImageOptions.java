package com.squareup.gifencoder;

import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public final class ImageOptions {
    int left = 0;
    int top = 0;
    ColorQuantizer quantizer = MedianCutQuantizer.INSTANCE;
    Ditherer ditherer = FloydSteinbergDitherer.INSTANCE;
    DisposalMethod disposalMethod = DisposalMethod.UNSPECIFIED;
    int delayCentiseconds = 0;

    public ImageOptions setLeft(int i) {
        this.left = i;
        return this;
    }

    public ImageOptions setTop(int i) {
        this.top = i;
        return this;
    }

    public ImageOptions setColorQuantizer(ColorQuantizer colorQuantizer) {
        this.quantizer = colorQuantizer;
        return this;
    }

    public ImageOptions setDitherer(Ditherer ditherer) {
        this.ditherer = ditherer;
        return this;
    }

    public ImageOptions setDisposalMethod(DisposalMethod disposalMethod) {
        this.disposalMethod = disposalMethod;
        return this;
    }

    public ImageOptions setDelay(long j, TimeUnit timeUnit) {
        this.delayCentiseconds = (int) (timeUnit.toMillis(j) / 10);
        return this;
    }
}
