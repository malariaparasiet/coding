package com.squareup.gifencoder;

import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes2.dex */
final class NetscapeLoopingExtensionBlock {
    private static final String APPLICATION = "NETSCAPE2.0";
    private static final int APPLICATION_EXTENSION = 255;
    private static final int BLOCK_TERMINATOR = 0;
    private static final int EXTENSION_INTRODUCER = 33;
    private static final int SUB_BLOCK_ID = 1;
    private static final int SUB_BLOCK_SIZE = 3;

    private NetscapeLoopingExtensionBlock() {
    }

    static void write(OutputStream outputStream, int i) throws IOException {
        outputStream.write(33);
        outputStream.write(255);
        outputStream.write(APPLICATION.length());
        Streams.writeAsciiString(outputStream, APPLICATION);
        outputStream.write(3);
        outputStream.write(1);
        Streams.writeShort(outputStream, i);
        outputStream.write(0);
    }
}
