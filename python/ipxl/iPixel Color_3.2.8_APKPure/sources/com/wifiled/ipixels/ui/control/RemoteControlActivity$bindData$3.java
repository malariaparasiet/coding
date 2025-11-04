package com.wifiled.ipixels.ui.control;

import androidx.core.app.NotificationCompat;
import com.blankj.utilcode.util.LogUtils;
import com.google.gson.Gson;
import com.wifiled.baselib.base.recycleview.RecyclerAdapter;
import com.wifiled.baselib.data.Record;
import com.wifiled.baselib.retrofit.CryptographicParsingToolKt;
import com.wifiled.baselib.retrofit.entity.CloudRes;
import com.wifiled.baselib.utils.ToastUtil;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.UtilsExtensionKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/* compiled from: RemoteControlActivity.kt */
@Metadata(d1 = {"\u0000)\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J$\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\bH\u0016J\u001e\u0010\t\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\f"}, d2 = {"com/wifiled/ipixels/ui/control/RemoteControlActivity$bindData$3", "Lretrofit2/Callback;", "", "onResponse", "", NotificationCompat.CATEGORY_CALL, "Lretrofit2/Call;", "response", "Lretrofit2/Response;", "onFailure", "t", "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class RemoteControlActivity$bindData$3 implements Callback<String> {
    final /* synthetic */ List<Record> $repoResponse;
    final /* synthetic */ RemoteControlActivity this$0;

    RemoteControlActivity$bindData$3(List<Record> list, RemoteControlActivity remoteControlActivity) {
        this.$repoResponse = list;
        this.this$0 = remoteControlActivity;
    }

    @Override // retrofit2.Callback
    public void onResponse(Call<String> call, Response<String> response) {
        RecyclerAdapter recyclerAdapter;
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(response, "response");
        String body = response.body();
        if (body == null) {
            body = "";
        }
        String decryptQueryAESString = CryptographicParsingToolKt.decryptQueryAESString(body);
        LogUtils.vTag("ruis", "json ----" + decryptQueryAESString);
        CloudRes cloudRes = (CloudRes) new Gson().fromJson(decryptQueryAESString, CloudRes.class);
        this.$repoResponse.clear();
        if (cloudRes.getStatus() == 0) {
            List<Record> list = this.$repoResponse;
            if (cloudRes.getResData().getRecords() != null) {
                list.addAll(cloudRes.getResData().getRecords());
            }
            recyclerAdapter = this.this$0.localAdapter;
            if (recyclerAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("localAdapter");
                recyclerAdapter = null;
            }
            recyclerAdapter.notifyDataSetChanged();
        }
    }

    @Override // retrofit2.Callback
    public void onFailure(Call<String> call, Throwable t) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(t, "t");
        final RemoteControlActivity remoteControlActivity = this.this$0;
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.control.RemoteControlActivity$bindData$3$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit onFailure$lambda$1;
                onFailure$lambda$1 = RemoteControlActivity$bindData$3.onFailure$lambda$1(RemoteControlActivity.this);
                return onFailure$lambda$1;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onFailure$lambda$1(RemoteControlActivity remoteControlActivity) {
        ToastUtil.show(remoteControlActivity.getString(R.string.msg_search_hd_info_fail));
        return Unit.INSTANCE;
    }
}
