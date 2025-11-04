package com.wifiled.ipixels;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import org.bouncycastle.i18n.TextBundle;

/* compiled from: AppConfig.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0005H\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/wifiled/ipixels/DataType;", "", "<init>", "()V", "TEXT", "", "IMAGE", "VIDEO", "GIF", "getPrefixByType", "", "type", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DataType {
    public static final int GIF = 3;
    public static final int IMAGE = 1;
    public static final DataType INSTANCE = new DataType();
    public static final int TEXT = 0;
    public static final int VIDEO = 2;

    private DataType() {
    }

    @JvmStatic
    public static final String getPrefixByType(int type) {
        if (type == 0) {
            return TextBundle.TEXT_ENTRY;
        }
        if (type == 1) {
            return "image";
        }
        if (type == 2) {
            return "video";
        }
        if (type == 3) {
            return "gif";
        }
        return "error";
    }
}
