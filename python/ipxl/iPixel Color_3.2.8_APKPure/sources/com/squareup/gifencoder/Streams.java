package com.squareup.gifencoder;

import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes2.dex */
final class Streams {
    private Streams() {
    }

    static void writeShort(OutputStream outputStream, int i) throws IOException {
        outputStream.write(i);
        outputStream.write(i >>> 8);
    }

    static void writeRgb(OutputStream outputStream, int i) throws IOException {
        outputStream.write(i >>> 16);
        outputStream.write(i >>> 8);
        outputStream.write(i);
    }

    static void writeAsciiString(OutputStream outputStream, String str) throws IOException {
        for (char c : str.toCharArray()) {
            outputStream.write(c);
        }
    }
}
