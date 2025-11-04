package com.easysocket.interfaces.conn;

import java.io.Serializable;

/* loaded from: classes2.dex */
public interface ISocketActionDispatch {
    void dispatchAction(String str);

    void dispatchAction(String str, Serializable serializable);

    void stopDispatchThread();

    void subscribe(ISocketActionListener iSocketActionListener);

    void unsubscribe(ISocketActionListener iSocketActionListener);
}
