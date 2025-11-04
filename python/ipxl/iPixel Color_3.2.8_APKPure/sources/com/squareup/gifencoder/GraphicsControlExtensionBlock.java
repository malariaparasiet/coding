package com.squareup.gifencoder;

import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes2.dex */
final class GraphicsControlExtensionBlock {
    private static final int BLOCK_TERMINATOR = 0;
    private static final int EXTENSION_INTRODUCER = 33;
    private static final int GRAPHICS_CONTROL_EXTENSION_BLOCK_SIZE = 4;
    private static final int GRAPHICS_CONTROL_LABEL = 249;
    private static final int TRANSPARENT_COLOR_FLAG = 1;
    private static final int USER_INPUT_FLAG = 2;

    private GraphicsControlExtensionBlock() {
    }

    static void write(OutputStream outputStream, DisposalMethod disposalMethod, boolean z, boolean z2, int i, int i2) throws IOException {
        outputStream.write(33);
        outputStream.write(GRAPHICS_CONTROL_LABEL);
        outputStream.write(4);
        outputStream.write((disposalMethod.ordinal() << 3) | (z ? 2 : 0) | (z2 ? 1 : 0));
        Streams.writeShort(outputStream, i);
        outputStream.write(i2);
        outputStream.write(0);
    }
}
