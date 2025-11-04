package androidx.compose.ui.input.nestedscroll;

import androidx.compose.ui.geometry.Offset;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NestedScrollDelegatingWrapper.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0001¢\u0006\u0002\u0010\u0004J)\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH\u0096@ø\u0001\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000f\u0010\u0010J-\u0010\u0011\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020\u00122\u0006\u0010\u000e\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0015\u0010\u0016J!\u0010\u0017\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH\u0096@ø\u0001\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0018\u0010\u0019J%\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u000e\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001b\u0010\u001cR\u001a\u0010\u0002\u001a\u00020\u0001X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\u0003\u001a\u00020\u0001X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\u0006\"\u0004\b\n\u0010\b\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006\u001d"}, d2 = {"Landroidx/compose/ui/input/nestedscroll/ParentWrapperNestedScrollConnection;", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "parent", "self", "(Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;)V", "getParent", "()Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "setParent", "(Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;)V", "getSelf", "setSelf", "onPostFling", "Landroidx/compose/ui/unit/Velocity;", "consumed", "available", "onPostFling-RZ2iAVY", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onPostScroll", "Landroidx/compose/ui/geometry/Offset;", "source", "Landroidx/compose/ui/input/nestedscroll/NestedScrollSource;", "onPostScroll-DzOQY0M", "(JJI)J", "onPreFling", "onPreFling-QWom1Mo", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onPreScroll", "onPreScroll-OzD1aCk", "(JI)J", "ui_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
final class ParentWrapperNestedScrollConnection implements NestedScrollConnection {
    private NestedScrollConnection parent;
    private NestedScrollConnection self;

    public ParentWrapperNestedScrollConnection(NestedScrollConnection parent, NestedScrollConnection self) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        Intrinsics.checkNotNullParameter(self, "self");
        this.parent = parent;
        this.self = self;
    }

    public final NestedScrollConnection getParent() {
        return this.parent;
    }

    public final void setParent(NestedScrollConnection nestedScrollConnection) {
        Intrinsics.checkNotNullParameter(nestedScrollConnection, "<set-?>");
        this.parent = nestedScrollConnection;
    }

    public final NestedScrollConnection getSelf() {
        return this.self;
    }

    public final void setSelf(NestedScrollConnection nestedScrollConnection) {
        Intrinsics.checkNotNullParameter(nestedScrollConnection, "<set-?>");
        this.self = nestedScrollConnection;
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* renamed from: onPreScroll-OzD1aCk */
    public long mo1800onPreScrollOzD1aCk(long available, int source) {
        long mo1800onPreScrollOzD1aCk = this.parent.mo1800onPreScrollOzD1aCk(available, source);
        return Offset.m447plusMKHz9U(mo1800onPreScrollOzD1aCk, this.self.mo1800onPreScrollOzD1aCk(Offset.m446minusMKHz9U(available, mo1800onPreScrollOzD1aCk), source));
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* renamed from: onPostScroll-DzOQY0M */
    public long mo1798onPostScrollDzOQY0M(long consumed, long available, int source) {
        long mo1798onPostScrollDzOQY0M = this.self.mo1798onPostScrollDzOQY0M(consumed, available, source);
        return Offset.m447plusMKHz9U(mo1798onPostScrollDzOQY0M, this.parent.mo1798onPostScrollDzOQY0M(Offset.m447plusMKHz9U(consumed, mo1798onPostScrollDzOQY0M), Offset.m446minusMKHz9U(available, mo1798onPostScrollDzOQY0M), source));
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* renamed from: onPreFling-QWom1Mo */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object mo1799onPreFlingQWom1Mo(long r7, kotlin.coroutines.Continuation<? super androidx.compose.ui.unit.Velocity> r9) {
        /*
            r6 = this;
            boolean r0 = r9 instanceof androidx.compose.ui.input.nestedscroll.ParentWrapperNestedScrollConnection$onPreFling$1
            if (r0 == 0) goto L14
            r0 = r9
            androidx.compose.ui.input.nestedscroll.ParentWrapperNestedScrollConnection$onPreFling$1 r0 = (androidx.compose.ui.input.nestedscroll.ParentWrapperNestedScrollConnection$onPreFling$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L19
        L14:
            androidx.compose.ui.input.nestedscroll.ParentWrapperNestedScrollConnection$onPreFling$1 r0 = new androidx.compose.ui.input.nestedscroll.ParentWrapperNestedScrollConnection$onPreFling$1
            r0.<init>(r6, r9)
        L19:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L41
            if (r2 == r4) goto L37
            if (r2 != r3) goto L2f
            long r7 = r0.J$0
            kotlin.ResultKt.throwOnFailure(r9)
            goto L73
        L2f:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L37:
            long r7 = r0.J$0
            java.lang.Object r2 = r0.L$0
            androidx.compose.ui.input.nestedscroll.ParentWrapperNestedScrollConnection r2 = (androidx.compose.ui.input.nestedscroll.ParentWrapperNestedScrollConnection) r2
            kotlin.ResultKt.throwOnFailure(r9)
            goto L56
        L41:
            kotlin.ResultKt.throwOnFailure(r9)
            androidx.compose.ui.input.nestedscroll.NestedScrollConnection r9 = r6.getParent()
            r0.L$0 = r6
            r0.J$0 = r7
            r0.label = r4
            java.lang.Object r9 = r9.mo1799onPreFlingQWom1Mo(r7, r0)
            if (r9 != r1) goto L55
            goto L71
        L55:
            r2 = r6
        L56:
            androidx.compose.ui.unit.Velocity r9 = (androidx.compose.ui.unit.Velocity) r9
            long r4 = r9.getPackedValue()
            androidx.compose.ui.input.nestedscroll.NestedScrollConnection r9 = r2.getSelf()
            long r7 = androidx.compose.ui.unit.Velocity.m2618minusAH228Gc(r7, r4)
            r2 = 0
            r0.L$0 = r2
            r0.J$0 = r4
            r0.label = r3
            java.lang.Object r9 = r9.mo1799onPreFlingQWom1Mo(r7, r0)
            if (r9 != r1) goto L72
        L71:
            return r1
        L72:
            r7 = r4
        L73:
            androidx.compose.ui.unit.Velocity r9 = (androidx.compose.ui.unit.Velocity) r9
            long r0 = r9.getPackedValue()
            long r7 = androidx.compose.ui.unit.Velocity.m2619plusAH228Gc(r7, r0)
            androidx.compose.ui.unit.Velocity r7 = androidx.compose.ui.unit.Velocity.m2606boximpl(r7)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.input.nestedscroll.ParentWrapperNestedScrollConnection.mo1799onPreFlingQWom1Mo(long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* renamed from: onPostFling-RZ2iAVY */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object mo1797onPostFlingRZ2iAVY(long r11, long r13, kotlin.coroutines.Continuation<? super androidx.compose.ui.unit.Velocity> r15) {
        /*
            r10 = this;
            boolean r0 = r15 instanceof androidx.compose.ui.input.nestedscroll.ParentWrapperNestedScrollConnection$onPostFling$1
            if (r0 == 0) goto L14
            r0 = r15
            androidx.compose.ui.input.nestedscroll.ParentWrapperNestedScrollConnection$onPostFling$1 r0 = (androidx.compose.ui.input.nestedscroll.ParentWrapperNestedScrollConnection$onPostFling$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r15 = r0.label
            int r15 = r15 - r2
            r0.label = r15
            goto L19
        L14:
            androidx.compose.ui.input.nestedscroll.ParentWrapperNestedScrollConnection$onPostFling$1 r0 = new androidx.compose.ui.input.nestedscroll.ParentWrapperNestedScrollConnection$onPostFling$1
            r0.<init>(r10, r15)
        L19:
            r6 = r0
            java.lang.Object r15 = r6.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.label
            r7 = 2
            r2 = 1
            if (r1 == 0) goto L44
            if (r1 == r2) goto L38
            if (r1 != r7) goto L30
            long r11 = r6.J$0
            kotlin.ResultKt.throwOnFailure(r15)
            goto L80
        L30:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L38:
            long r13 = r6.J$1
            long r11 = r6.J$0
            java.lang.Object r1 = r6.L$0
            androidx.compose.ui.input.nestedscroll.ParentWrapperNestedScrollConnection r1 = (androidx.compose.ui.input.nestedscroll.ParentWrapperNestedScrollConnection) r1
            kotlin.ResultKt.throwOnFailure(r15)
            goto L5f
        L44:
            kotlin.ResultKt.throwOnFailure(r15)
            androidx.compose.ui.input.nestedscroll.NestedScrollConnection r1 = r10.getSelf()
            r6.L$0 = r10
            r6.J$0 = r11
            r6.J$1 = r13
            r6.label = r2
            r2 = r11
            r4 = r13
            java.lang.Object r15 = r1.mo1797onPostFlingRZ2iAVY(r2, r4, r6)
            if (r15 != r0) goto L5c
            goto L7e
        L5c:
            r1 = r10
            r11 = r2
            r13 = r4
        L5f:
            androidx.compose.ui.unit.Velocity r15 = (androidx.compose.ui.unit.Velocity) r15
            long r8 = r15.getPackedValue()
            androidx.compose.ui.input.nestedscroll.NestedScrollConnection r1 = r1.getParent()
            long r2 = androidx.compose.ui.unit.Velocity.m2619plusAH228Gc(r11, r8)
            long r4 = androidx.compose.ui.unit.Velocity.m2618minusAH228Gc(r13, r8)
            r11 = 0
            r6.L$0 = r11
            r6.J$0 = r8
            r6.label = r7
            java.lang.Object r15 = r1.mo1797onPostFlingRZ2iAVY(r2, r4, r6)
            if (r15 != r0) goto L7f
        L7e:
            return r0
        L7f:
            r11 = r8
        L80:
            androidx.compose.ui.unit.Velocity r15 = (androidx.compose.ui.unit.Velocity) r15
            long r13 = r15.getPackedValue()
            long r11 = androidx.compose.ui.unit.Velocity.m2619plusAH228Gc(r11, r13)
            androidx.compose.ui.unit.Velocity r11 = androidx.compose.ui.unit.Velocity.m2606boximpl(r11)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.input.nestedscroll.ParentWrapperNestedScrollConnection.mo1797onPostFlingRZ2iAVY(long, long, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
