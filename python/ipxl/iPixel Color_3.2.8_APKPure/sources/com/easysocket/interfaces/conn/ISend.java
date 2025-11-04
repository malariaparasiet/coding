package com.easysocket.interfaces.conn;

import com.easysocket.entity.basemsg.ISender;
import com.easysocket.entity.basemsg.SuperCallbackSender;

/* loaded from: classes2.dex */
public interface ISend {
    IConnectionManager upBytes(byte[] bArr);

    IConnectionManager upCallbackMessage(SuperCallbackSender superCallbackSender);

    IConnectionManager upObject(ISender iSender);

    IConnectionManager upString(String str);
}
