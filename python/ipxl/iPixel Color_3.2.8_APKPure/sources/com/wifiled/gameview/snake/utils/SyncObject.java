package com.wifiled.gameview.snake.utils;

import android.util.Log;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SyncObject.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0007\u0018\u0000 &2\u00020\u0001:\u0001&B'\b\u0002\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u0016\u001a\u00020\u000b2\b\b\u0002\u0010\u0017\u001a\u00020\u0011J\u0006\u0010\u0018\u001a\u00020\u0019J\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001J\u0010\u0010\u001c\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001J\u0006\u0010\u001d\u001a\u00020\u000bJ\u0010\u0010\u001e\u001a\u00020\u000b2\b\b\u0002\u0010\u001f\u001a\u00020 J\u0006\u0010!\u001a\u00020\u0019J\u0006\u0010\"\u001a\u00020\u0019J\u0006\u0010#\u001a\u00020\u0019J\u0006\u0010$\u001a\u00020\u000bJ\u0006\u0010%\u001a\u00020\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/wifiled/gameview/snake/utils/SyncObject;", "", "lock", "Ljava/util/concurrent/locks/ReentrantLock;", "condition", "Ljava/util/concurrent/locks/Condition;", "Object", "Ljava/lang/Object;", "<init>", "(Ljava/util/concurrent/locks/ReentrantLock;Ljava/util/concurrent/locks/Condition;Ljava/lang/Object;)V", "bRet", "", "getBRet", "()Z", "setBRet", "(Z)V", "delayTime", "", "getDelayTime", "()J", "setDelayTime", "(J)V", "aWait", "timeout", "aNotify", "", "userState", "getUserState", "setUserState", "tryLock", "wait", "milliseconds", "", "await", "signAll", "unlock", "isHeldByCurThread", "isLocked", "Companion", "libgame_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SyncObject {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static SyncObject instance;
    private final Object Object;
    private boolean bRet;
    private final Condition condition;
    private long delayTime;
    private final ReentrantLock lock;
    private Object userState;

    @JvmStatic
    public static final SyncObject get() {
        return INSTANCE.get();
    }

    private SyncObject(ReentrantLock reentrantLock, Condition condition, Object obj) {
        this.lock = reentrantLock;
        this.condition = condition;
        this.Object = obj;
        this.bRet = true;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    /* synthetic */ SyncObject(java.util.concurrent.locks.ReentrantLock r1, java.util.concurrent.locks.Condition r2, java.lang.Object r3, int r4, kotlin.jvm.internal.DefaultConstructorMarker r5) {
        /*
            r0 = this;
            r5 = r4 & 1
            if (r5 == 0) goto L9
            java.util.concurrent.locks.ReentrantLock r1 = new java.util.concurrent.locks.ReentrantLock
            r1.<init>()
        L9:
            r5 = r4 & 2
            if (r5 == 0) goto L16
            java.util.concurrent.locks.Condition r2 = r1.newCondition()
            java.lang.String r5 = "newCondition(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r5)
        L16:
            r4 = r4 & 4
            if (r4 == 0) goto L1f
            java.lang.Object r3 = new java.lang.Object
            r3.<init>()
        L1f:
            r0.<init>(r1, r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wifiled.gameview.snake.utils.SyncObject.<init>(java.util.concurrent.locks.ReentrantLock, java.util.concurrent.locks.Condition, java.lang.Object, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    /* compiled from: SyncObject.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0007R\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00058BX\u0082\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/wifiled/gameview/snake/utils/SyncObject$Companion;", "", "<init>", "()V", "get", "Lcom/wifiled/gameview/snake/utils/SyncObject;", "instance", "getInstance", "()Lcom/wifiled/gameview/snake/utils/SyncObject;", "libgame_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final SyncObject get() {
            SyncObject companion = getInstance();
            Intrinsics.checkNotNull(companion);
            return companion;
        }

        private final SyncObject getInstance() {
            if (SyncObject.instance == null) {
                SyncObject.instance = new SyncObject(null, null, null, 7, null);
            }
            return SyncObject.instance;
        }
    }

    public final boolean getBRet() {
        return this.bRet;
    }

    public final void setBRet(boolean z) {
        this.bRet = z;
    }

    public final long getDelayTime() {
        return this.delayTime;
    }

    public final void setDelayTime(long j) {
        this.delayTime = j;
    }

    public static /* synthetic */ boolean aWait$default(SyncObject syncObject, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = syncObject.delayTime;
        }
        return syncObject.aWait(j);
    }

    public final boolean aWait(long timeout) {
        this.bRet = true;
        Log.d("akon", "timeout:" + timeout + " delayTime:" + this.delayTime);
        if (0 == timeout) {
            this.bRet = false;
            return false;
        }
        synchronized (this.Object) {
            try {
                this.Object.wait(timeout);
                Unit unit = Unit.INSTANCE;
            } catch (Exception e) {
                Integer.valueOf(Log.d("akon", "aWait Exception: " + e));
            }
        }
        Log.d("akon", "aWait: " + this.bRet);
        return this.bRet;
    }

    public final void aNotify() {
        synchronized (this.Object) {
            try {
                this.Object.notify();
                this.bRet = false;
                Integer.valueOf(Log.d("akon", "aNotify: false"));
            } catch (Exception unused) {
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    public final Object getUserState() {
        return this.userState;
    }

    public final void setUserState(Object userState) {
        this.userState = userState;
    }

    public final boolean tryLock() {
        try {
            this.lock.lock();
            return true;
        } catch (Exception unused) {
            this.lock.unlock();
            Log.d("akon", "#1.0.2#  unlock: ");
            return false;
        }
    }

    public static /* synthetic */ boolean wait$default(SyncObject syncObject, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = (int) syncObject.delayTime;
        }
        return syncObject.wait(i);
    }

    public final boolean wait(int milliseconds) {
        try {
            return this.condition.await(milliseconds, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    public final void await() {
        try {
            this.condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public final void signAll() {
        try {
            this.condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void unlock() {
        try {
            this.lock.unlock();
            Log.d("akon", "#1.0.3#  unlock: ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final boolean isHeldByCurThread() {
        return this.lock.isHeldByCurrentThread();
    }

    public final boolean isLocked() {
        return this.lock.isLocked();
    }
}
