package com.wifiled.ipixels.data;

import com.google.gson.Gson;
import com.wifiled.baselib.retrofit.CryptographicParsingToolKt;
import com.wifiled.baselib.retrofit.NetWorkManager;
import com.wifiled.baselib.retrofit.entity.CloudRes;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.bouncycastle.jcajce.util.AnnotatedPrivateKey;

/* compiled from: CloudManager.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcom/wifiled/baselib/retrofit/entity/CloudRes;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.wifiled.ipixels.data.CloudManager$getCloudMaterial$2", f = "CloudManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class CloudManager$getCloudMaterial$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super CloudRes>, Object> {
    final /* synthetic */ String $categoryName;
    final /* synthetic */ int $count;
    final /* synthetic */ String $filter_tags;
    final /* synthetic */ int $height;
    final /* synthetic */ String $label;
    final /* synthetic */ String $language;
    final /* synthetic */ int $page;
    final /* synthetic */ String $type;
    final /* synthetic */ int $width;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CloudManager$getCloudMaterial$2(int i, int i2, String str, String str2, String str3, int i3, int i4, String str4, String str5, Continuation<? super CloudManager$getCloudMaterial$2> continuation) {
        super(2, continuation);
        this.$page = i;
        this.$count = i2;
        this.$categoryName = str;
        this.$type = str2;
        this.$label = str3;
        this.$width = i3;
        this.$height = i4;
        this.$language = str4;
        this.$filter_tags = str5;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CloudManager$getCloudMaterial$2(this.$page, this.$count, this.$categoryName, this.$type, this.$label, this.$width, this.$height, this.$language, this.$filter_tags, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super CloudRes> continuation) {
        return ((CloudManager$getCloudMaterial$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        Map mapOf = MapsKt.mapOf(TuplesKt.to("appid", "137"), TuplesKt.to("sort", "1"), TuplesKt.to("page", String.valueOf(this.$page)), TuplesKt.to("count", String.valueOf(this.$count)), TuplesKt.to("category_name", this.$categoryName), TuplesKt.to("type", this.$type), TuplesKt.to(AnnotatedPrivateKey.LABEL, this.$label), TuplesKt.to("width", String.valueOf(this.$width)), TuplesKt.to("height", String.valueOf(this.$height)), TuplesKt.to("file_lang", this.$language), TuplesKt.to("filter_tags", this.$filter_tags));
        String valueOf = String.valueOf(System.currentTimeMillis() / 1000);
        String generateRandomString$default = CryptographicParsingToolKt.generateRandomString$default(0, 1, null);
        Response execute = NetWorkManager.INSTANCE.getClient().newCall(new Request.Builder().url("https://manage.heaton.com.cn/api/rm//getMaterialUnderCategory?sign=" + CryptographicParsingToolKt.generateSortedQueryString(mapOf, generateRandomString$default, valueOf) + "&timestamp=" + valueOf + "&random=" + generateRandomString$default).post(RequestBody.INSTANCE.create(CryptographicParsingToolKt.generateSortedQueryAESString(mapOf), MediaType.INSTANCE.get("text/plain; charset=utf-8"))).build()).execute();
        if (!execute.getIsSuccessful()) {
            return null;
        }
        ResponseBody body = execute.body();
        if (body == null || (str = body.string()) == null) {
            str = "";
        }
        return new Gson().fromJson(CryptographicParsingToolKt.decryptQueryAESString(str), CloudRes.class);
    }
}
