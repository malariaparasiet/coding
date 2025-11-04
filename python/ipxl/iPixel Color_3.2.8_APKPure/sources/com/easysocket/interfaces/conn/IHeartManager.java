package com.easysocket.interfaces.conn;

import com.easysocket.connection.heartbeat.HeartManager;
import com.easysocket.entity.basemsg.ISender;

/* loaded from: classes2.dex */
public interface IHeartManager {
    void onReceiveHeartBeat();

    void startHeartbeat(ISender iSender, HeartManager.HeartbeatListener heartbeatListener);

    void startHeartbeat(String str, HeartManager.HeartbeatListener heartbeatListener);

    void startHeartbeat(byte[] bArr, HeartManager.HeartbeatListener heartbeatListener);

    void stopHeartbeat();
}
