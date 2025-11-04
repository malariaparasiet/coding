package com.wifiled.ipixels.core;

import com.blankj.utilcode.util.LogUtils;
import com.wifiled.blelibrary.ble.utils.ByteUtils;
import com.wifiled.ipixels.core.SendCore;
import com.wifiled.ipixels.core.send.SendResultCallback;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* compiled from: SendCore.kt */
@Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"com/wifiled/ipixels/core/SendCore$sendDataInner$3", "Lcom/wifiled/ipixels/core/send/SendResultCallback;", "onResult", "", "result", "", "onError", "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SendCore$sendDataInner$3 implements SendResultCallback {
    final /* synthetic */ byte[] $bgr_data;
    final /* synthetic */ Ref.ObjectRef<SendCore.CallbackBuilder> $callback;
    final /* synthetic */ Ref.IntRef $dataSendedSize;
    final /* synthetic */ Ref.ObjectRef<Disposable> $disposable;
    final /* synthetic */ byte[] $head;
    final /* synthetic */ Ref.IntRef $imagCount;
    final /* synthetic */ Ref.IntRef $imagIndex;
    final /* synthetic */ byte $isBulkSend;
    final /* synthetic */ boolean $isDown;
    final /* synthetic */ Ref.ObjectRef<byte[]> $payload;
    final /* synthetic */ List<byte[]> $perBlockData;
    final /* synthetic */ int $type;

    SendCore$sendDataInner$3(Ref.ObjectRef<Disposable> objectRef, Ref.IntRef intRef, Ref.ObjectRef<SendCore.CallbackBuilder> objectRef2, byte[] bArr, Ref.IntRef intRef2, List<byte[]> list, Ref.IntRef intRef3, Ref.ObjectRef<byte[]> objectRef3, int i, byte[] bArr2, byte b, boolean z) {
        this.$disposable = objectRef;
        this.$dataSendedSize = intRef;
        this.$callback = objectRef2;
        this.$bgr_data = bArr;
        this.$imagCount = intRef2;
        this.$perBlockData = list;
        this.$imagIndex = intRef3;
        this.$payload = objectRef3;
        this.$type = i;
        this.$head = bArr2;
        this.$isBulkSend = b;
        this.$isDown = z;
    }

    /* JADX WARN: Type inference failed for: r2v3, types: [T, byte[]] */
    /* JADX WARN: Type inference failed for: r3v6, types: [T, io.reactivex.disposables.Disposable] */
    @Override // com.wifiled.ipixels.core.send.SendResultCallback
    public void onResult(byte[] result) {
        boolean z;
        int i;
        int i2;
        Function0<Unit> completeAction$app_googleRelease;
        Disposable disposable;
        Intrinsics.checkNotNullParameter(result, "result");
        if (result.length == 5) {
            LogUtils.vTag("ruis", "sendCompat1 ------" + ByteUtils.toHexString(result));
        }
        Iterator<byte[]> it = SendCore.INSTANCE.getSendDataInnerArrAnswer().iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            } else if (Arrays.equals(result, it.next())) {
                Disposable disposable2 = this.$disposable.element;
                if (disposable2 != null) {
                    disposable2.dispose();
                }
                this.$disposable.element = null;
                z = true;
            }
        }
        if (!z) {
            this.$dataSendedSize.element += result.length;
            if (result.length == 5) {
                byte b = result[4];
                if (b == 0) {
                    LogUtils.i("SendCore>>>[onResult]:命令无效 ");
                    Function1<Integer, Unit> errorAction$app_googleRelease = this.$callback.element.getErrorAction$app_googleRelease();
                    if (errorAction$app_googleRelease != null) {
                        errorAction$app_googleRelease.invoke(10017);
                    }
                } else if (b == 2) {
                    LogUtils.i("SendCore>>>[onResult]:空间不足 ");
                    Function1<Integer, Unit> errorAction$app_googleRelease2 = this.$callback.element.getErrorAction$app_googleRelease();
                    if (errorAction$app_googleRelease2 != null) {
                        errorAction$app_googleRelease2.invoke(10016);
                    }
                }
            }
        } else {
            Function1<byte[], Unit> resultAction$app_googleRelease = this.$callback.element.getResultAction$app_googleRelease();
            if (resultAction$app_googleRelease != null) {
                resultAction$app_googleRelease.invoke(result);
            }
            if (result[result.length - 1] == 3) {
                Function0<Unit> completeAction$app_googleRelease2 = this.$callback.element.getCompleteAction$app_googleRelease();
                if (completeAction$app_googleRelease2 != null) {
                    completeAction$app_googleRelease2.invoke();
                    return;
                }
                return;
            }
        }
        int length = (this.$dataSendedSize.element * 100) / (this.$bgr_data.length + (this.$imagCount.element * 15));
        if (length > 100) {
            length = 100;
        }
        if (z && this.$perBlockData.size() > 0) {
            this.$imagIndex.element++;
            this.$payload.element = SendCore.INSTANCE.payloadChannel(this.$type, this.$perBlockData.get(this.$imagIndex.element), this.$bgr_data, this.$imagIndex.element > 0 ? 2 : 0, this.$head, this.$isBulkSend);
            if (!this.$isDown) {
                this.$payload.element[14] = 101;
            }
            Disposable disposable3 = this.$disposable.element;
            if (disposable3 != null && !disposable3.isDisposed() && (disposable = this.$disposable.element) != null) {
                disposable.dispose();
            }
            Ref.ObjectRef<Disposable> objectRef = this.$disposable;
            Observable<Long> observeOn = Observable.interval(8L, TimeUnit.SECONDS).take(1L).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            final Ref.ObjectRef<SendCore.CallbackBuilder> objectRef2 = this.$callback;
            final Function1 function1 = new Function1() { // from class: com.wifiled.ipixels.core.SendCore$sendDataInner$3$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Unit onResult$lambda$0;
                    onResult$lambda$0 = SendCore$sendDataInner$3.onResult$lambda$0(Ref.ObjectRef.this, (Long) obj);
                    return onResult$lambda$0;
                }
            };
            objectRef.element = observeOn.subscribe(new Consumer() { // from class: com.wifiled.ipixels.core.SendCore$sendDataInner$3$$ExternalSyntheticLambda1
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    Function1.this.invoke(obj);
                }
            });
            SendCore.INSTANCE.sendCompat(this.$payload.element, this);
        }
        i = SendCore.tProcess;
        if (i != length && !z) {
            SendCore sendCore = SendCore.INSTANCE;
            SendCore.tProcess = length;
            Function1<Integer, Unit> progressAction$app_googleRelease = this.$callback.element.getProgressAction$app_googleRelease();
            if (progressAction$app_googleRelease != null) {
                progressAction$app_googleRelease.invoke(Integer.valueOf(length));
                return;
            }
            return;
        }
        if (z) {
            i2 = SendCore.tProcess;
            if (i2 != 100 || (completeAction$app_googleRelease = this.$callback.element.getCompleteAction$app_googleRelease()) == null) {
                return;
            }
            completeAction$app_googleRelease.invoke();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Unit onResult$lambda$0(Ref.ObjectRef objectRef, Long l) {
        LogUtils.vTag("ruis", "发送超时回调，Observable.interval");
        Function1<Integer, Unit> errorAction$app_googleRelease = ((SendCore.CallbackBuilder) objectRef.element).getErrorAction$app_googleRelease();
        if (errorAction$app_googleRelease != null) {
            errorAction$app_googleRelease.invoke(10013);
        }
        return Unit.INSTANCE;
    }

    @Override // com.wifiled.ipixels.core.send.SendResultCallback
    public void onError(int result) {
        this.$dataSendedSize.element = 0;
        this.$imagIndex.element = 0;
        Function1<Integer, Unit> errorAction$app_googleRelease = this.$callback.element.getErrorAction$app_googleRelease();
        if (errorAction$app_googleRelease != null) {
            errorAction$app_googleRelease.invoke(Integer.valueOf(result));
        }
    }
}
