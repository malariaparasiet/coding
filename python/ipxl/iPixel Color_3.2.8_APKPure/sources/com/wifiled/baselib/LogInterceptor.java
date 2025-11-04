package com.wifiled.baselib;

import android.util.Log;
import com.j256.ormlite.stmt.query.SimpleComparison;
import java.io.IOException;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/* loaded from: classes2.dex */
public class LogInterceptor implements Interceptor {
    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        long nanoTime = System.nanoTime();
        String method = request.method();
        StringBuilder sb = new StringBuilder();
        if ("POST".equals(method) && (request.body() instanceof FormBody)) {
            FormBody formBody = (FormBody) request.body();
            for (int i = 0; i < formBody.size(); i++) {
                sb.append(formBody.encodedName(i) + SimpleComparison.EQUAL_TO_OPERATION + formBody.encodedValue(i) + ",");
            }
            sb.delete(sb.length() - 1, sb.length());
        }
        Response proceed = chain.proceed(request);
        long nanoTime2 = System.nanoTime();
        Log.i("LogInterceptor", String.format("接收响应: [%s] %n返回json:【%s】 %.1fms%n%s", proceed.request().url(), proceed.peekBody(1048576L).string(), Double.valueOf((nanoTime2 - nanoTime) / 1000000.0d), proceed.headers()));
        return proceed;
    }
}
