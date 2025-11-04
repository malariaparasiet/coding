package com.wifiled.baselib.uicode.net;

import kotlin.Metadata;

/* compiled from: HttpNetCode.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/wifiled/baselib/uicode/net/HttpNetCode;", "", "<init>", "()V", "SUCCESS", "", "LOGIN_EXPIRED", "DATA_ERROR", "NET_CONNECT_ERROR", "NET_TIMEOUT", "PARSE_ERROR", "JSON_ERROR", "baselib_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class HttpNetCode {
    public static final int DATA_ERROR = 15001;
    public static final HttpNetCode INSTANCE = new HttpNetCode();
    public static final int JSON_ERROR = 15005;
    public static final int LOGIN_EXPIRED = 401;
    public static final int NET_CONNECT_ERROR = 15002;
    public static final int NET_TIMEOUT = 15003;
    public static final int PARSE_ERROR = 15004;
    public static final int SUCCESS = 0;

    private HttpNetCode() {
    }
}
