package com.squareup.gifencoder;

import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes2.dex */
final class ImageDescriptorBlock {
    private static final int IMAGE_SEPARATOR = 44;
    private static final int INTERLACE_FLAG = 64;
    private static final int LOCAL_COLOR_TABLE_FLAG = 128;
    private static final int SORT_FLAG = 32;

    private ImageDescriptorBlock() {
    }

    static void write(OutputStream outputStream, int i, int i2, int i3, int i4, boolean z, boolean z2, boolean z3, int i5) throws IOException {
        outputStream.write(44);
        Streams.writeShort(outputStream, i);
        Streams.writeShort(outputStream, i2);
        Streams.writeShort(outputStream, i3);
        Streams.writeShort(outputStream, i4);
        outputStream.write((z3 ? 32 : 0) | (z ? 128 : 0) | (z2 ? 64 : 0) | i5);
    }
}
