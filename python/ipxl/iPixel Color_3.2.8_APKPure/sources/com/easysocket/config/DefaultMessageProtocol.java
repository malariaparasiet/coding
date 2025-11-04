package com.easysocket.config;

import com.easysocket.interfaces.io.IMessageProtocol;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes2.dex */
public class DefaultMessageProtocol implements IMessageProtocol {
    @Override // com.easysocket.interfaces.io.IMessageProtocol
    public int getHeaderLength() {
        return 4;
    }

    @Override // com.easysocket.interfaces.io.IMessageProtocol
    public int getBodyLength(byte[] bArr, ByteOrder byteOrder) {
        if (bArr == null || bArr.length < getHeaderLength()) {
            return 0;
        }
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(byteOrder);
        return wrap.getInt();
    }

    @Override // com.easysocket.interfaces.io.IMessageProtocol
    public byte[] pack(byte[] bArr) {
        ByteBuffer allocate = ByteBuffer.allocate(getHeaderLength() + bArr.length);
        allocate.order(ByteOrder.BIG_ENDIAN);
        allocate.putInt(bArr.length);
        allocate.put(bArr);
        return allocate.array();
    }
}
