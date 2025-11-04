package com.easysocket.interfaces.config;

import java.nio.ByteOrder;

/* loaded from: classes2.dex */
public interface IMessageProtocol {
    int getBodyLength(byte[] bArr, ByteOrder byteOrder);

    int getHeaderLength();
}
