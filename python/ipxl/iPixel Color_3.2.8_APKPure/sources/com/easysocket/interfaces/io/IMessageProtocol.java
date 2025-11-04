package com.easysocket.interfaces.io;

import java.nio.ByteOrder;

/* loaded from: classes2.dex */
public interface IMessageProtocol {
    int getBodyLength(byte[] bArr, ByteOrder byteOrder);

    int getHeaderLength();

    byte[] pack(byte[] bArr);
}
