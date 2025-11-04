package com.squareup.gifencoder;

import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes2.dex */
final class LogicalScreenDescriptorBlock {
    private static final int GLOBAL_COLOR_TABLE_FLAG = 128;
    private static final int SORT_FLAG = 8;

    private LogicalScreenDescriptorBlock() {
    }

    static void write(OutputStream outputStream, int i, int i2, boolean z, int i3, boolean z2, int i4, int i5, int i6) throws IOException {
        Streams.writeShort(outputStream, i);
        Streams.writeShort(outputStream, i2);
        outputStream.write((z2 ? 8 : 0) | (z ? 128 : 0) | (i3 << 4) | i4);
        outputStream.write(i5);
        outputStream.write(i6);
    }
}
