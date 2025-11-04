package com.wifiled.baselib.uicode.utils;

import android.app.Dialog;
import android.content.DialogInterface;
import com.wifiled.baselib.uicode.application.Core;
import java.util.concurrent.ConcurrentLinkedQueue;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* compiled from: DialogManager.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\r\u001a\u00020\u000eH\u0002J\"\u0010\u000f\u001a\u00020\u00002\b\u0010\u0010\u001a\u0004\u0018\u00010\u00072\u0010\b\u0002\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bJ\b\u0010\u0012\u001a\u00020\u0013H\u0002J\u0006\u0010\u0014\u001a\u00020\u0013J\b\u0010\u0015\u001a\u00020\u0013H\u0002J\b\u0010\u0016\u001a\u00020\u0013H\u0002R-\u0010\u0004\u001a\u001e\u0012\u001a\u0012\u0018\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\f\u0012\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b0\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0010\u0010\f\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/wifiled/baselib/uicode/utils/DialogManager;", "", "<init>", "()V", "queue", "Ljava/util/concurrent/ConcurrentLinkedQueue;", "Lkotlin/Pair;", "Landroid/app/Dialog;", "Lkotlin/Function0;", "", "getQueue", "()Ljava/util/concurrent/ConcurrentLinkedQueue;", "mDialog", "canShow", "", "pushToQueue", "dialog", "callback", "startNextIf", "", "clear", "removeTopTask", "nextTask", "baselib_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DialogManager {
    private static Dialog mDialog;
    public static final DialogManager INSTANCE = new DialogManager();
    private static final ConcurrentLinkedQueue<Pair<Dialog, Function0<Long>>> queue = new ConcurrentLinkedQueue<>();

    private DialogManager() {
    }

    public final ConcurrentLinkedQueue<Pair<Dialog, Function0<Long>>> getQueue() {
        return queue;
    }

    private final boolean canShow() {
        return queue.size() < 2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ DialogManager pushToQueue$default(DialogManager dialogManager, Dialog dialog, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            function0 = null;
        }
        return dialogManager.pushToQueue(dialog, function0);
    }

    public final DialogManager pushToQueue(Dialog dialog, Function0<Long> callback) {
        if (dialog == null) {
            return this;
        }
        synchronized (this) {
            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.wifiled.baselib.uicode.utils.DialogManager$$ExternalSyntheticLambda0
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    DialogManager.pushToQueue$lambda$1$lambda$0(dialogInterface);
                }
            });
            DialogManager dialogManager = INSTANCE;
            queue.add(TuplesKt.to(dialog, callback));
            if (dialogManager.canShow()) {
                dialogManager.startNextIf();
            }
            Unit unit = Unit.INSTANCE;
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void pushToQueue$lambda$1$lambda$0(DialogInterface dialogInterface) {
        INSTANCE.nextTask();
    }

    private final void startNextIf() {
        ConcurrentLinkedQueue<Pair<Dialog, Function0<Long>>> concurrentLinkedQueue = queue;
        if (concurrentLinkedQueue.isEmpty()) {
            return;
        }
        Pair<Dialog, Function0<Long>> element = concurrentLinkedQueue.element();
        mDialog = element.getFirst();
        Function0<Long> second = element.getSecond();
        if (second != null) {
            Core.INSTANCE.getHanlder().postDelayed(new Runnable() { // from class: com.wifiled.baselib.uicode.utils.DialogManager$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    DialogManager.startNextIf$lambda$3$lambda$2();
                }
            }, second.invoke().longValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startNextIf$lambda$3$lambda$2() {
        Dialog dialog = mDialog;
        if (dialog != null) {
            dialog.show();
        }
    }

    public final void clear() {
        queue.clear();
        Dialog dialog = mDialog;
        if (dialog != null) {
            dialog.dismiss();
        }
        mDialog = null;
    }

    private final void removeTopTask() {
        queue.poll();
    }

    private final void nextTask() {
        removeTopTask();
        startNextIf();
    }
}
