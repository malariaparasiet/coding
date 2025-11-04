package com.wifiled.ipixels.data;

import androidx.paging.PagingState;
import com.wifiled.baselib.app.Constance;
import com.wifiled.baselib.data.Record;
import com.wifiled.musiclib.player.constant.PlayerFinal;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.jcajce.util.AnnotatedPrivateKey;

/* compiled from: PagingSourceEye.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B?\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0005¢\u0006\u0004\b\f\u0010\rJ#\u0010\u0012\u001a\u0004\u0018\u00010\u00022\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0014H\u0016¢\u0006\u0002\u0010\u0015J(\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00020\u0019H\u0096@¢\u0006\u0002\u0010\u001aR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u0002X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0011¨\u0006\u001b"}, d2 = {"Lcom/wifiled/ipixels/data/PagingSourceEye;", "Landroidx/paging/PagingSource;", "", "Lcom/wifiled/baselib/data/Record;", "type", "", AnnotatedPrivateKey.LABEL, "categoryName", "width", "height", Constance.SP.LANGUAGE, "filter_tags", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IILjava/lang/String;Ljava/lang/String;)V", "sign", "", "chaos", "Ljava/lang/Integer;", "getRefreshKey", PlayerFinal.STATE, "Landroidx/paging/PagingState;", "(Landroidx/paging/PagingState;)Ljava/lang/Integer;", "load", "Landroidx/paging/PagingSource$LoadResult;", "params", "Landroidx/paging/PagingSource$LoadParams;", "(Landroidx/paging/PagingSource$LoadParams;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class PagingSourceEye extends androidx.paging.PagingSource<Integer, Record> {
    private final String categoryName;
    private Integer chaos;
    private final String filter_tags;
    private final int height;
    private final String label;
    private final String language;
    private final List<Integer> sign;
    private final String type;
    private final int width;

    public PagingSourceEye(String type, String label, String categoryName, int i, int i2, String language, String filter_tags) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(label, "label");
        Intrinsics.checkNotNullParameter(categoryName, "categoryName");
        Intrinsics.checkNotNullParameter(language, "language");
        Intrinsics.checkNotNullParameter(filter_tags, "filter_tags");
        this.type = type;
        this.label = label;
        this.categoryName = categoryName;
        this.width = i;
        this.height = i2;
        this.language = language;
        this.filter_tags = filter_tags;
        this.sign = new ArrayList();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.paging.PagingSource
    public Integer getRefreshKey(PagingState<Integer, Record> state) {
        Intrinsics.checkNotNullParameter(state, "state");
        this.sign.clear();
        this.chaos = null;
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0123 A[Catch: Exception -> 0x01b9, TryCatch #0 {Exception -> 0x01b9, blocks: (B:12:0x003e, B:13:0x010b, B:15:0x0123, B:17:0x0131, B:19:0x013c, B:23:0x0143, B:24:0x014b, B:26:0x0153, B:29:0x0172, B:31:0x018b, B:33:0x0198, B:35:0x01b1, B:39:0x016a, B:44:0x0057, B:46:0x00ce, B:51:0x0061, B:53:0x0069, B:54:0x006f, B:56:0x007e, B:58:0x0086, B:59:0x0096), top: B:7:0x0028 }] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0143 A[Catch: Exception -> 0x01b9, TryCatch #0 {Exception -> 0x01b9, blocks: (B:12:0x003e, B:13:0x010b, B:15:0x0123, B:17:0x0131, B:19:0x013c, B:23:0x0143, B:24:0x014b, B:26:0x0153, B:29:0x0172, B:31:0x018b, B:33:0x0198, B:35:0x01b1, B:39:0x016a, B:44:0x0057, B:46:0x00ce, B:51:0x0061, B:53:0x0069, B:54:0x006f, B:56:0x007e, B:58:0x0086, B:59:0x0096), top: B:7:0x0028 }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x018b A[Catch: Exception -> 0x01b9, TryCatch #0 {Exception -> 0x01b9, blocks: (B:12:0x003e, B:13:0x010b, B:15:0x0123, B:17:0x0131, B:19:0x013c, B:23:0x0143, B:24:0x014b, B:26:0x0153, B:29:0x0172, B:31:0x018b, B:33:0x0198, B:35:0x01b1, B:39:0x016a, B:44:0x0057, B:46:0x00ce, B:51:0x0061, B:53:0x0069, B:54:0x006f, B:56:0x007e, B:58:0x0086, B:59:0x0096), top: B:7:0x0028 }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x014a  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0107  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x002a  */
    @Override // androidx.paging.PagingSource
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object load(androidx.paging.PagingSource.LoadParams<java.lang.Integer> r20, kotlin.coroutines.Continuation<? super androidx.paging.PagingSource.LoadResult<java.lang.Integer, com.wifiled.baselib.data.Record>> r21) {
        /*
            Method dump skipped, instructions count: 455
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wifiled.ipixels.data.PagingSourceEye.load(androidx.paging.PagingSource$LoadParams, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
