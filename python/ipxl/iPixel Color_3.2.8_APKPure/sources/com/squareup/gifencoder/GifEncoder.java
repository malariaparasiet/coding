package com.squareup.gifencoder;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Set;

/* loaded from: classes2.dex */
public final class GifEncoder {
    private static final int MAX_COLOR_COUNT = 256;
    private final OutputStream outputStream;
    private final int screenHeight;
    private final int screenWidth;

    private static int getColorTableSizeField(int i) {
        int i2 = 0;
        while (true) {
            int i3 = i2 + 1;
            if ((1 << i3) >= i) {
                return i2;
            }
            i2 = i3;
        }
    }

    public GifEncoder(OutputStream outputStream, int i, int i2, int i3) throws IOException {
        this.outputStream = outputStream;
        this.screenWidth = i;
        this.screenHeight = i2;
        HeaderBlock.write(outputStream);
        LogicalScreenDescriptorBlock.write(outputStream, i, i2, false, 1, false, 0, 0, 0);
        NetscapeLoopingExtensionBlock.write(outputStream, i3);
    }

    public GifEncoder addImage(int[][] iArr, ImageOptions imageOptions) throws IOException {
        addImage(Image.fromRgb(iArr), imageOptions);
        return this;
    }

    public GifEncoder addImage(int[] iArr, int i, ImageOptions imageOptions) throws IOException {
        addImage(Image.fromRgb(iArr, i), imageOptions);
        return this;
    }

    public synchronized void finishEncoding() throws IOException {
        this.outputStream.write(59);
    }

    public synchronized void addImage(Image image, ImageOptions imageOptions) throws IOException {
        Image image2;
        if (imageOptions.left + image.getWidth() > this.screenWidth || imageOptions.top + image.getHeight() > this.screenHeight) {
            throw new IllegalArgumentException("Image does not fit in screen.");
        }
        Multiset<Color> colors = image.getColors();
        Set<Color> distinctElements = colors.getDistinctElements();
        if (distinctElements.size() > 256) {
            distinctElements = imageOptions.quantizer.quantize(colors, 256);
            image2 = imageOptions.ditherer.dither(image, distinctElements);
        } else {
            image2 = image;
        }
        ColorTable fromColors = ColorTable.fromColors(distinctElements);
        int paddedSize = fromColors.paddedSize();
        int[] indices = fromColors.getIndices(image2);
        GraphicsControlExtensionBlock.write(this.outputStream, imageOptions.disposalMethod, false, false, imageOptions.delayCentiseconds, 0);
        ImageDescriptorBlock.write(this.outputStream, imageOptions.left, imageOptions.top, image2.getWidth(), image2.getHeight(), true, false, false, getColorTableSizeField(paddedSize));
        fromColors.write(this.outputStream);
        LzwEncoder lzwEncoder = new LzwEncoder(paddedSize);
        ImageDataBlock.write(this.outputStream, lzwEncoder.getMinimumCodeSize(), lzwEncoder.encode(indices));
    }
}
