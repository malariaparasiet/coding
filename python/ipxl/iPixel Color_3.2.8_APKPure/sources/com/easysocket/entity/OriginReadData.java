package com.easysocket.entity;

import com.easysocket.EasySocket;
import java.io.Serializable;
import java.nio.charset.Charset;

/* loaded from: classes2.dex */
public class OriginReadData implements Serializable {
    private byte[] bodyData;
    private byte[] headerData;

    public byte[] getHeaderData() {
        return this.headerData;
    }

    public void setHeaderData(byte[] bArr) {
        this.headerData = bArr;
    }

    public byte[] getBodyData() {
        return this.bodyData;
    }

    public void setBodyData(byte[] bArr) {
        this.bodyData = bArr;
    }

    public void release() {
        this.headerData = null;
        this.bodyData = null;
    }

    public String getBodyString() {
        return new String(getBodyData(), Charset.forName(EasySocket.getInstance().getOptions().getCharsetName()));
    }
}
