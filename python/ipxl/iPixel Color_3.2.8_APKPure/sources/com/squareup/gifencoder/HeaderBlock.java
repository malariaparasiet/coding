package com.squareup.gifencoder;

import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes2.dex */
final class HeaderBlock {
    private HeaderBlock() {
    }

    static void write(OutputStream outputStream) throws IOException {
        Streams.writeAsciiString(outputStream, "GIF");
        Streams.writeAsciiString(outputStream, "89a");
    }
}
