package com.easysocket.entity.basemsg;

import com.easysocket.utils.Util;

/* loaded from: classes2.dex */
public abstract class SuperCallbackSender extends SuperSender {
    public abstract String getCallbackId();

    public abstract void setCallbackId(String str);

    public SuperCallbackSender() {
        setCallbackId(generateCallbackId());
    }

    public String generateCallbackId() {
        return Util.getRandomChar(20);
    }
}
