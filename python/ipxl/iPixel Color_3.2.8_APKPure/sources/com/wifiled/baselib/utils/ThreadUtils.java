package com.wifiled.baselib.utils;

import android.os.Handler;
import android.os.Looper;
import com.wifiled.baselib.callback.CallBack;
import com.wifiled.baselib.callback.CallBackUI;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/* loaded from: classes2.dex */
public class ThreadUtils {
    private static final Handler MAIN_HANDLER = new Handler(Looper.getMainLooper());

    public static <T> void asyncCallback(final CallBackUI<T> callBackUI) {
        Observable.create(new ObservableOnSubscribe<T>() { // from class: com.wifiled.baselib.utils.ThreadUtils.2
            /* JADX WARN: Multi-variable type inference failed */
            @Override // io.reactivex.ObservableOnSubscribe
            public void subscribe(ObservableEmitter<T> observableEmitter) throws Exception {
                observableEmitter.onNext(CallBackUI.this.execute());
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<T>() { // from class: com.wifiled.baselib.utils.ThreadUtils.1
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                CallBackUI.this.onPreExecute();
            }

            @Override // io.reactivex.Observer
            public void onNext(T t) {
                CallBackUI.this.callBackUI(t);
            }
        });
    }

    public static void async(final CallBack callBack) {
        TaskExecutor.executeTask(new Runnable() { // from class: com.wifiled.baselib.utils.ThreadUtils.3
            @Override // java.lang.Runnable
            public void run() {
                CallBack.this.execute();
            }
        });
    }

    public static void asyncDelay(final long j, final CallBack callBack) {
        TaskExecutor.executeTask(new Runnable() { // from class: com.wifiled.baselib.utils.ThreadUtils.4
            @Override // java.lang.Runnable
            public void run() {
                ThreadUtils.delay(j);
                callBack.execute();
            }
        });
    }

    public static void ui(Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        } else {
            MAIN_HANDLER.post(runnable);
        }
    }

    public static void uiDelay(Runnable runnable, long j) {
        MAIN_HANDLER.postDelayed(runnable, j);
    }

    public static void delay(long j) {
        try {
            Thread.sleep(j);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
