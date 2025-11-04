package com.wifiled.ipixels.ui.text.vo;

import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okio.Utf8;

/* compiled from: CheckContent.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fR*\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Lcom/wifiled/ipixels/ui/text/vo/CheckContent;", "", "<init>", "()V", "cacheTextHistoryVOs", "Ljava/util/ArrayList;", "Lcom/wifiled/ipixels/ui/text/vo/TextHistoryVO;", "Lkotlin/collections/ArrayList;", "getCacheTextHistoryVOs", "()Ljava/util/ArrayList;", "setCacheTextHistoryVOs", "(Ljava/util/ArrayList;)V", "containsEmoji", "", "source", "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CheckContent {
    public static final CheckContent INSTANCE = new CheckContent();
    private static ArrayList<TextHistoryVO> cacheTextHistoryVOs = new ArrayList<>();

    private CheckContent() {
    }

    public final ArrayList<TextHistoryVO> getCacheTextHistoryVOs() {
        return cacheTextHistoryVOs;
    }

    public final void setCacheTextHistoryVOs(ArrayList<TextHistoryVO> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        cacheTextHistoryVOs = arrayList;
    }

    public final boolean containsEmoji(String source) {
        int charAt;
        Intrinsics.checkNotNullParameter(source, "source");
        int length = source.length();
        for (int i = 0; i < length; i++) {
            char charAt2 = source.charAt(i);
            if (55296 <= charAt2 && charAt2 < 56320) {
                if (source.length() > 1 && 118784 <= (charAt = ((charAt2 - 55296) * 1024) + (source.charAt(i + 1) - Utf8.LOG_SURROGATE_HEADER) + 65536) && charAt < 128896) {
                    return true;
                }
            } else {
                if (8448 <= charAt2 && charAt2 < 10240 && charAt2 != 9787) {
                    return true;
                }
                if (11013 <= charAt2 && charAt2 < 11016) {
                    return true;
                }
                if (10548 <= charAt2 && charAt2 < 10550) {
                    return true;
                }
                if ((12951 <= charAt2 && charAt2 < 12954) || charAt2 == 169 || charAt2 == 174 || charAt2 == 12349 || charAt2 == 12336 || charAt2 == 11093 || charAt2 == 11036 || charAt2 == 11035 || charAt2 == 11088 || charAt2 == 8986) {
                    return true;
                }
                if (source.length() > 1 && i < source.length() - 1 && source.charAt(i + 1) == 8419) {
                    return true;
                }
            }
        }
        return false;
    }
}
