package com.easysocket.connection.dispatcher;

import com.easysocket.callback.SuperCallBack;
import com.easysocket.config.EasySocketOptions;
import com.easysocket.entity.OriginReadData;
import com.easysocket.entity.SocketAddress;
import com.easysocket.entity.basemsg.SuperCallbackSender;
import com.easysocket.entity.exception.RequestTimeOutException;
import com.easysocket.interfaces.conn.IConnectionManager;
import com.easysocket.interfaces.conn.SocketActionListener;
import com.easysocket.utils.LogUtil;
import com.easysocket.utils.Util;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class CallbackResponseDispatcher {
    IConnectionManager connectionManager;
    private EasySocketOptions socketOptions;
    private ExecutorService timeoutExecutor;
    private Map<String, SuperCallBack> callbacks = new HashMap();
    private DelayQueue<timeoutItem> timeoutQueue = new DelayQueue<>();
    private SocketActionListener socketActionListener = new SocketActionListener() { // from class: com.easysocket.connection.dispatcher.CallbackResponseDispatcher.2
        @Override // com.easysocket.interfaces.conn.SocketActionListener, com.easysocket.interfaces.conn.ISocketActionListener
        public void onSocketResponse(SocketAddress socketAddress, OriginReadData originReadData) {
            if (CallbackResponseDispatcher.this.callbacks.size() == 0 || CallbackResponseDispatcher.this.socketOptions.getCallbakcIdKeyFactory() == null) {
                return;
            }
            try {
                String callbackIdKey = CallbackResponseDispatcher.this.socketOptions.getCallbakcIdKeyFactory().getCallbackIdKey();
                JSONObject jSONObject = new JSONObject(originReadData.getBodyString());
                if (jSONObject.has(callbackIdKey)) {
                    String string = jSONObject.getString(callbackIdKey);
                    SuperCallBack superCallBack = (SuperCallBack) CallbackResponseDispatcher.this.callbacks.get(string);
                    if (superCallBack != null) {
                        superCallBack.onSuccess(originReadData.getBodyString());
                        CallbackResponseDispatcher.this.callbacks.remove(string);
                        LogUtil.d("移除的callbackId=" + string);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    public CallbackResponseDispatcher(IConnectionManager iConnectionManager) {
        this.connectionManager = iConnectionManager;
        this.socketOptions = iConnectionManager.getOptions();
        iConnectionManager.subscribeSocketAction(this.socketActionListener);
        engineThread();
    }

    public void setSocketOptions(EasySocketOptions easySocketOptions) {
        this.socketOptions = easySocketOptions;
    }

    public void engineThread() {
        ExecutorService executorService = this.timeoutExecutor;
        if (executorService == null || executorService.isShutdown()) {
            ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
            this.timeoutExecutor = newSingleThreadExecutor;
            newSingleThreadExecutor.execute(new Runnable() { // from class: com.easysocket.connection.dispatcher.CallbackResponseDispatcher.1
                @Override // java.lang.Runnable
                public void run() {
                    SuperCallBack superCallBack;
                    try {
                        timeoutItem timeoutitem = (timeoutItem) CallbackResponseDispatcher.this.timeoutQueue.take();
                        if (timeoutitem != null && (superCallBack = (SuperCallBack) CallbackResponseDispatcher.this.callbacks.remove(timeoutitem.callbackId)) != null) {
                            superCallBack.onError(new RequestTimeOutException("request timeout"));
                        }
                        if (CallbackResponseDispatcher.this.timeoutExecutor == null || CallbackResponseDispatcher.this.timeoutExecutor.isShutdown()) {
                            return;
                        }
                        run();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public void shutdownThread() {
        ExecutorService executorService = this.timeoutExecutor;
        if (executorService == null || executorService.isShutdown()) {
            return;
        }
        this.timeoutExecutor.shutdownNow();
        this.timeoutExecutor = null;
    }

    public void addSocketCallback(SuperCallBack superCallBack) {
        this.callbacks.put(superCallBack.getCallbackId(), superCallBack);
        EasySocketOptions easySocketOptions = this.socketOptions;
        if (easySocketOptions == null) {
            easySocketOptions = EasySocketOptions.getDefaultOptions();
        }
        this.timeoutQueue.add((DelayQueue<timeoutItem>) new timeoutItem(superCallBack.getCallbackId(), easySocketOptions.getRequestTimeout(), TimeUnit.MILLISECONDS));
    }

    class timeoutItem implements Delayed {
        String callbackId;
        long executeTime;

        public timeoutItem(String str, long j, TimeUnit timeUnit) {
            this.callbackId = str;
            this.executeTime = System.currentTimeMillis() + (j > 0 ? timeUnit.toMillis(j) : 0L);
        }

        @Override // java.util.concurrent.Delayed
        public long getDelay(TimeUnit timeUnit) {
            return this.executeTime - System.currentTimeMillis();
        }

        @Override // java.lang.Comparable
        public int compareTo(Delayed delayed) {
            return (int) (getDelay(TimeUnit.MILLISECONDS) - delayed.getDelay(TimeUnit.MILLISECONDS));
        }
    }

    public void checkCallbackSender(SuperCallbackSender superCallbackSender) {
        Util.checkNotNull(this.socketOptions.getCallbakcIdKeyFactory(), "要想实现EasySocket的回调功能，CallbackIdFactory不能为null，请实现一个CallbackIdFactory并在初始化的时候通过EasySocketOptions的setCallbackIdKeyFactory进行配置");
        if (this.callbacks.containsKey(superCallbackSender.getCallbackId())) {
            superCallbackSender.setCallbackId(superCallbackSender.generateCallbackId());
        }
    }
}
