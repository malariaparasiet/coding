package com.tiro.jlotalibrary;

import android.widget.TextView;
import com.alibaba.fastjson2.internal.asm.Opcodes;
import com.wifiled.blelibrary.ble.queue.reconnect.DefaultReConnectHandler;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* compiled from: JLDialogActivity.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.tiro.jlotalibrary.JLDialogActivity$initOTA$5", f = "JLDialogActivity.kt", i = {}, l = {Opcodes.IF_ACMPNE, Opcodes.GOTO}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class JLDialogActivity$initOTA$5 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ JLDialogActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    JLDialogActivity$initOTA$5(JLDialogActivity jLDialogActivity, Continuation<? super JLDialogActivity$initOTA$5> continuation) {
        super(2, continuation);
        this.this$0 = jLDialogActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new JLDialogActivity$initOTA$5(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((JLDialogActivity$initOTA$5) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0048, code lost:
    
        if (kotlinx.coroutines.BuildersKt.withContext(kotlinx.coroutines.Dispatchers.getMain(), new com.tiro.jlotalibrary.JLDialogActivity$initOTA$5.AnonymousClass1(r5.this$0, null), r5) == r0) goto L18;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r6) {
        /*
            r5 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r5.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L1e
            if (r1 == r3) goto L1a
            if (r1 != r2) goto L12
            kotlin.ResultKt.throwOnFailure(r6)     // Catch: java.util.concurrent.CancellationException -> L4b
            goto L53
        L12:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L1a:
            kotlin.ResultKt.throwOnFailure(r6)     // Catch: java.util.concurrent.CancellationException -> L4b
            goto L2f
        L1e:
            kotlin.ResultKt.throwOnFailure(r6)
            r6 = r5
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6     // Catch: java.util.concurrent.CancellationException -> L4b
            r5.label = r3     // Catch: java.util.concurrent.CancellationException -> L4b
            r3 = 8000(0x1f40, double:3.9525E-320)
            java.lang.Object r6 = kotlinx.coroutines.DelayKt.delay(r3, r6)     // Catch: java.util.concurrent.CancellationException -> L4b
            if (r6 != r0) goto L2f
            goto L4a
        L2f:
            kotlinx.coroutines.MainCoroutineDispatcher r6 = kotlinx.coroutines.Dispatchers.getMain()     // Catch: java.util.concurrent.CancellationException -> L4b
            kotlin.coroutines.CoroutineContext r6 = (kotlin.coroutines.CoroutineContext) r6     // Catch: java.util.concurrent.CancellationException -> L4b
            com.tiro.jlotalibrary.JLDialogActivity$initOTA$5$1 r1 = new com.tiro.jlotalibrary.JLDialogActivity$initOTA$5$1     // Catch: java.util.concurrent.CancellationException -> L4b
            com.tiro.jlotalibrary.JLDialogActivity r3 = r5.this$0     // Catch: java.util.concurrent.CancellationException -> L4b
            r4 = 0
            r1.<init>(r3, r4)     // Catch: java.util.concurrent.CancellationException -> L4b
            kotlin.jvm.functions.Function2 r1 = (kotlin.jvm.functions.Function2) r1     // Catch: java.util.concurrent.CancellationException -> L4b
            r3 = r5
            kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3     // Catch: java.util.concurrent.CancellationException -> L4b
            r5.label = r2     // Catch: java.util.concurrent.CancellationException -> L4b
            java.lang.Object r6 = kotlinx.coroutines.BuildersKt.withContext(r6, r1, r3)     // Catch: java.util.concurrent.CancellationException -> L4b
            if (r6 != r0) goto L53
        L4a:
            return r0
        L4b:
            com.tiro.jlotalibrary.util.LogUtil r6 = com.tiro.jlotalibrary.util.LogUtil.INSTANCE
            java.lang.String r0 = "验证失败关闭窗口方法取消"
            r6.d(r0)
        L53:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tiro.jlotalibrary.JLDialogActivity$initOTA$5.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    /* compiled from: JLDialogActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.tiro.jlotalibrary.JLDialogActivity$initOTA$5$1", f = "JLDialogActivity.kt", i = {}, l = {Opcodes.RET}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.tiro.jlotalibrary.JLDialogActivity$initOTA$5$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        final /* synthetic */ JLDialogActivity this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(JLDialogActivity jLDialogActivity, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.this$0 = jLDialogActivity;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            TextView textView;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                textView = this.this$0.warn;
                if (textView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("warn");
                    textView = null;
                }
                textView.setText(this.this$0.getString(R.string.auth_failed));
                this.label = 1;
                if (DelayKt.delay(DefaultReConnectHandler.DEFAULT_CONNECT_DELAY, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            this.this$0.finish();
            return Unit.INSTANCE;
        }
    }
}
