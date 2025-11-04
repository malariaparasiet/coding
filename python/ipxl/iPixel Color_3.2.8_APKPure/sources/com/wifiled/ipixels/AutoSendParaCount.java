package com.wifiled.ipixels;

import kotlin.Metadata;

/* compiled from: AppConfig.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u001c\u001a\u00020\u001dJ\u0006\u0010\u001e\u001a\u00020\u001dJ\u0006\u0010\u001f\u001a\u00020\u001dJ\u0006\u0010 \u001a\u00020\u001dJ\u0006\u0010!\u001a\u00020\u001dR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\tR\u001a\u0010\r\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u0007\"\u0004\b\u000f\u0010\tR\u001a\u0010\u0010\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0007\"\u0004\b\u0012\u0010\tR\u001a\u0010\u0013\u001a\u00020\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0016\"\u0004\b\u001b\u0010\u0018¨\u0006\""}, d2 = {"Lcom/wifiled/ipixels/AutoSendParaCount;", "", "<init>", "()V", "iChannelSendSuc", "", "getIChannelSendSuc", "()I", "setIChannelSendSuc", "(I)V", "iCrcCount", "getICrcCount", "setICrcCount", "iDataResend", "getIDataResend", "setIDataResend", "iChannelSendFail", "getIChannelSendFail", "setIChannelSendFail", "iRandomClearScreenSec", "", "getIRandomClearScreenSec", "()J", "setIRandomClearScreenSec", "(J)V", "iRandomResendSec", "getIRandomResendSec", "setIRandomResendSec", "addChannelSendSuc", "", "addCrcCount", "addDataResend", "addChannelSendFail", "reset", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AutoSendParaCount {
    public static final AutoSendParaCount INSTANCE = new AutoSendParaCount();
    private static int iChannelSendFail;
    private static int iChannelSendSuc;
    private static int iCrcCount;
    private static int iDataResend;
    private static long iRandomClearScreenSec;
    private static long iRandomResendSec;

    private AutoSendParaCount() {
    }

    public final int getIChannelSendSuc() {
        return iChannelSendSuc;
    }

    public final void setIChannelSendSuc(int i) {
        iChannelSendSuc = i;
    }

    public final int getICrcCount() {
        return iCrcCount;
    }

    public final void setICrcCount(int i) {
        iCrcCount = i;
    }

    public final int getIDataResend() {
        return iDataResend;
    }

    public final void setIDataResend(int i) {
        iDataResend = i;
    }

    public final int getIChannelSendFail() {
        return iChannelSendFail;
    }

    public final void setIChannelSendFail(int i) {
        iChannelSendFail = i;
    }

    public final long getIRandomClearScreenSec() {
        return iRandomClearScreenSec;
    }

    public final void setIRandomClearScreenSec(long j) {
        iRandomClearScreenSec = j;
    }

    public final long getIRandomResendSec() {
        return iRandomResendSec;
    }

    public final void setIRandomResendSec(long j) {
        iRandomResendSec = j;
    }

    public final void addChannelSendSuc() {
        iChannelSendSuc++;
    }

    public final void addCrcCount() {
        iCrcCount++;
    }

    public final void addDataResend() {
        iDataResend++;
    }

    public final void addChannelSendFail() {
        iChannelSendFail++;
    }

    public final void reset() {
        iChannelSendSuc = 0;
        iCrcCount = 0;
        iDataResend = 0;
        iChannelSendFail = 0;
        iRandomClearScreenSec = 0L;
        iRandomResendSec = 0L;
    }
}
