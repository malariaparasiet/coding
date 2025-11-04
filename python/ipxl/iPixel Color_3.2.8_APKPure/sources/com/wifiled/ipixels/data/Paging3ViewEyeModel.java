package com.wifiled.ipixels.data;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.paging.CachedPagingDataKt;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import com.wifiled.baselib.app.Constance;
import com.wifiled.baselib.data.Record;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import org.bouncycastle.jcajce.util.AnnotatedPrivateKey;

/* compiled from: Paging3ViewEyeModel.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003JJ\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u00072\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u000bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/wifiled/ipixels/data/Paging3ViewEyeModel;", "Landroidx/lifecycle/ViewModel;", "<init>", "()V", "PAGE_SIZE", "", "getPagingData", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/paging/PagingData;", "Lcom/wifiled/baselib/data/Record;", "type", "", AnnotatedPrivateKey.LABEL, "categoryName", "width", "height", Constance.SP.LANGUAGE, "filter_tags", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class Paging3ViewEyeModel extends ViewModel {
    private final int PAGE_SIZE = 15;

    public final Flow<PagingData<Record>> getPagingData(final String type, final String label, final String categoryName, final int width, final int height, final String language, final String filter_tags) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(label, "label");
        Intrinsics.checkNotNullParameter(categoryName, "categoryName");
        Intrinsics.checkNotNullParameter(language, "language");
        Intrinsics.checkNotNullParameter(filter_tags, "filter_tags");
        int i = this.PAGE_SIZE;
        return CachedPagingDataKt.cachedIn(new Pager(new PagingConfig(i, i, false, i, 0, 0, 48, null), null, new Function0() { // from class: com.wifiled.ipixels.data.Paging3ViewEyeModel$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                androidx.paging.PagingSource pagingData$lambda$0;
                pagingData$lambda$0 = Paging3ViewEyeModel.getPagingData$lambda$0(type, label, categoryName, width, height, language, filter_tags);
                return pagingData$lambda$0;
            }
        }, 2, null).getFlow(), ViewModelKt.getViewModelScope(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final androidx.paging.PagingSource getPagingData$lambda$0(String str, String str2, String str3, int i, int i2, String str4, String str5) {
        return new PagingSourceEye(str, str2, str3, i, i2, str4, str5);
    }
}
