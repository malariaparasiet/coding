package com.squareup.gifencoder;

import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes2.dex */
final class ImageDataBlock {
    private ImageDataBlock() {
    }

    static void write(OutputStream outputStream, int i, byte[] bArr) throws IOException {
        outputStream.write(i);
        int i2 = 0;
        while (i2 < bArr.length) {
            int min = Math.min(bArr.length - i2, 255);
            outputStream.write(min);
            outputStream.write(bArr, i2, min);
            i2 += min;
        }
        outputStream.write(0);
    }
}
