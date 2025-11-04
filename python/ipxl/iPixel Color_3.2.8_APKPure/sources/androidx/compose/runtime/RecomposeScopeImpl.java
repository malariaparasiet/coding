package androidx.compose.runtime;

import androidx.compose.runtime.collection.IdentityArrayIntMap;
import androidx.compose.runtime.collection.IdentityArrayMap;
import androidx.compose.runtime.collection.IdentityArraySet;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RecomposeScopeImpl.kt */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0005J\u000e\u00104\u001a\u00020\u00102\u0006\u00105\u001a\u00020\u000eJ\u001c\u00106\u001a\u0010\u0012\u0004\u0012\u000208\u0012\u0004\u0012\u00020\u0010\u0018\u0001072\u0006\u00109\u001a\u00020\u000fJ\b\u0010:\u001a\u00020\u0010H\u0016J\u0010\u0010;\u001a\u00020<2\b\u0010\u0015\u001a\u0004\u0018\u00010,J\u0016\u0010=\u001a\u00020\u00162\u000e\u0010>\u001a\n\u0012\u0004\u0012\u00020,\u0018\u00010?J\u000e\u0010@\u001a\u00020\u00102\u0006\u0010A\u001a\u00020,J\u0006\u0010B\u001a\u00020\u0010J\u0006\u0010C\u001a\u00020\u0010J\u000e\u0010D\u001a\u00020\u00102\u0006\u00109\u001a\u00020\u000fJ\"\u0010E\u001a\u00020\u00102\u0018\u0010\f\u001a\u0014\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\rH\u0016R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\"\u0010\f\u001a\u0016\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0010\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0005R\u000e\u0010\u0014\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u00168F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR$\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u00168F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u001d\u0010\u0019\"\u0004\b\u001e\u0010\u001bR\u000e\u0010\u001f\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010 \u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u00168F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b!\u0010\u0019\"\u0004\b\"\u0010\u001bR$\u0010#\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u00168B@BX\u0082\u000e¢\u0006\f\u001a\u0004\b$\u0010\u0019\"\u0004\b%\u0010\u001bR$\u0010&\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u00168@@BX\u0080\u000e¢\u0006\f\u001a\u0004\b'\u0010\u0019\"\u0004\b(\u0010\u001bR\"\u0010)\u001a\u0016\u0012\b\u0012\u0006\u0012\u0002\b\u00030+\u0012\u0006\u0012\u0004\u0018\u00010,\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010-\u001a\u0004\u0018\u00010.X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010/\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u00168F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b0\u0010\u0019\"\u0004\b1\u0010\u001bR\u0011\u00102\u001a\u00020\u00168F¢\u0006\u0006\u001a\u0004\b3\u0010\u0019¨\u0006F"}, d2 = {"Landroidx/compose/runtime/RecomposeScopeImpl;", "Landroidx/compose/runtime/ScopeUpdateScope;", "Landroidx/compose/runtime/RecomposeScope;", "composition", "Landroidx/compose/runtime/CompositionImpl;", "(Landroidx/compose/runtime/CompositionImpl;)V", "anchor", "Landroidx/compose/runtime/Anchor;", "getAnchor", "()Landroidx/compose/runtime/Anchor;", "setAnchor", "(Landroidx/compose/runtime/Anchor;)V", "block", "Lkotlin/Function2;", "Landroidx/compose/runtime/Composer;", "", "", "getComposition", "()Landroidx/compose/runtime/CompositionImpl;", "setComposition", "currentToken", "value", "", "defaultsInScope", "getDefaultsInScope", "()Z", "setDefaultsInScope", "(Z)V", "defaultsInvalid", "getDefaultsInvalid", "setDefaultsInvalid", "flags", "requiresRecompose", "getRequiresRecompose", "setRequiresRecompose", "rereading", "getRereading", "setRereading", "skipped", "getSkipped$runtime_release", "setSkipped", "trackedDependencies", "Landroidx/compose/runtime/collection/IdentityArrayMap;", "Landroidx/compose/runtime/DerivedState;", "", "trackedInstances", "Landroidx/compose/runtime/collection/IdentityArrayIntMap;", "used", "getUsed", "setUsed", "valid", "getValid", "compose", "composer", "end", "Lkotlin/Function1;", "Landroidx/compose/runtime/Composition;", "token", "invalidate", "invalidateForResult", "Landroidx/compose/runtime/InvalidationResult;", "isInvalidFor", "instances", "Landroidx/compose/runtime/collection/IdentityArraySet;", "recordRead", "instance", "rereadTrackedInstances", "scopeSkipped", "start", "updateScope", "runtime_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class RecomposeScopeImpl implements ScopeUpdateScope, RecomposeScope {
    private Anchor anchor;
    private Function2<? super Composer, ? super Integer, Unit> block;
    private CompositionImpl composition;
    private int currentToken;
    private int flags;
    private IdentityArrayMap<DerivedState<?>, Object> trackedDependencies;
    private IdentityArrayIntMap trackedInstances;

    public RecomposeScopeImpl(CompositionImpl compositionImpl) {
        this.composition = compositionImpl;
    }

    public final CompositionImpl getComposition() {
        return this.composition;
    }

    public final void setComposition(CompositionImpl compositionImpl) {
        this.composition = compositionImpl;
    }

    public final Anchor getAnchor() {
        return this.anchor;
    }

    public final void setAnchor(Anchor anchor) {
        this.anchor = anchor;
    }

    public final boolean getValid() {
        if (this.composition != null) {
            Anchor anchor = this.anchor;
            if (anchor == null ? false : anchor.getValid()) {
                return true;
            }
        }
        return false;
    }

    public final boolean getUsed() {
        return (this.flags & 1) != 0;
    }

    public final void setUsed(boolean z) {
        if (z) {
            this.flags |= 1;
        } else {
            this.flags &= -2;
        }
    }

    public final boolean getDefaultsInScope() {
        return (this.flags & 2) != 0;
    }

    public final void setDefaultsInScope(boolean z) {
        if (z) {
            this.flags |= 2;
        } else {
            this.flags &= -3;
        }
    }

    public final boolean getDefaultsInvalid() {
        return (this.flags & 4) != 0;
    }

    public final void setDefaultsInvalid(boolean z) {
        if (z) {
            this.flags |= 4;
        } else {
            this.flags &= -5;
        }
    }

    public final boolean getRequiresRecompose() {
        return (this.flags & 8) != 0;
    }

    public final void setRequiresRecompose(boolean z) {
        if (z) {
            this.flags |= 8;
        } else {
            this.flags &= -9;
        }
    }

    public final void compose(Composer composer) {
        Unit unit;
        Intrinsics.checkNotNullParameter(composer, "composer");
        Function2<? super Composer, ? super Integer, Unit> function2 = this.block;
        if (function2 == null) {
            unit = null;
        } else {
            function2.invoke(composer, 1);
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            throw new IllegalStateException("Invalid restart scope".toString());
        }
    }

    public final InvalidationResult invalidateForResult(Object value) {
        CompositionImpl compositionImpl = this.composition;
        InvalidationResult invalidate = compositionImpl == null ? null : compositionImpl.invalidate(this, value);
        return invalidate == null ? InvalidationResult.IGNORED : invalidate;
    }

    @Override // androidx.compose.runtime.RecomposeScope
    public void invalidate() {
        CompositionImpl compositionImpl = this.composition;
        if (compositionImpl == null) {
            return;
        }
        compositionImpl.invalidate(this, null);
    }

    @Override // androidx.compose.runtime.ScopeUpdateScope
    public void updateScope(Function2<? super Composer, ? super Integer, Unit> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        this.block = block;
    }

    private final boolean getRereading() {
        return (this.flags & 32) != 0;
    }

    private final void setRereading(boolean z) {
        if (z) {
            this.flags |= 32;
        } else {
            this.flags &= -33;
        }
    }

    public final boolean getSkipped$runtime_release() {
        return (this.flags & 16) != 0;
    }

    private final void setSkipped(boolean z) {
        if (z) {
            this.flags |= 16;
        } else {
            this.flags &= -17;
        }
    }

    public final void start(int token) {
        this.currentToken = token;
        setSkipped(false);
    }

    public final void scopeSkipped() {
        setSkipped(true);
    }

    public final void recordRead(Object instance) {
        Intrinsics.checkNotNullParameter(instance, "instance");
        if (getRereading()) {
            return;
        }
        IdentityArrayIntMap identityArrayIntMap = this.trackedInstances;
        if (identityArrayIntMap == null) {
            identityArrayIntMap = new IdentityArrayIntMap();
            this.trackedInstances = identityArrayIntMap;
        }
        identityArrayIntMap.add(instance, this.currentToken);
        if (instance instanceof DerivedState) {
            IdentityArrayMap<DerivedState<?>, Object> identityArrayMap = this.trackedDependencies;
            if (identityArrayMap == null) {
                identityArrayMap = new IdentityArrayMap<>(0, 1, null);
                this.trackedDependencies = identityArrayMap;
            }
            identityArrayMap.set(instance, ((DerivedState) instance).getCurrentValue());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean isInvalidFor(IdentityArraySet<Object> instances) {
        IdentityArrayMap<DerivedState<?>, Object> identityArrayMap;
        if (instances != null && (identityArrayMap = this.trackedDependencies) != 0 && instances.isNotEmpty()) {
            IdentityArraySet<Object> identityArraySet = instances;
            if ((identityArraySet instanceof Collection) && identityArraySet.isEmpty()) {
                return false;
            }
            for (Object obj : identityArraySet) {
                if ((obj instanceof DerivedState) && Intrinsics.areEqual(identityArrayMap.get(obj), ((DerivedState) obj).getValue())) {
                }
            }
            return false;
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0035, code lost:
    
        throw new java.lang.NullPointerException("null cannot be cast to non-null type kotlin.Any");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void rereadTrackedInstances() {
        /*
            r8 = this;
            androidx.compose.runtime.CompositionImpl r0 = r8.composition
            if (r0 != 0) goto L5
            goto L9
        L5:
            androidx.compose.runtime.collection.IdentityArrayIntMap r1 = r8.trackedInstances
            if (r1 != 0) goto La
        L9:
            return
        La:
            r2 = 1
            r8.setRereading(r2)
            r2 = 0
            int r3 = r1.getSize()     // Catch: java.lang.Throwable -> L3a
            if (r3 <= 0) goto L36
            r4 = r2
        L16:
            int r5 = r4 + 1
            java.lang.Object[] r6 = r1.getKeys()     // Catch: java.lang.Throwable -> L3a
            r6 = r6[r4]     // Catch: java.lang.Throwable -> L3a
            if (r6 == 0) goto L2e
            int[] r7 = r1.getValues()     // Catch: java.lang.Throwable -> L3a
            r4 = r7[r4]     // Catch: java.lang.Throwable -> L3a
            r0.recordReadOf(r6)     // Catch: java.lang.Throwable -> L3a
            if (r5 < r3) goto L2c
            goto L36
        L2c:
            r4 = r5
            goto L16
        L2e:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException     // Catch: java.lang.Throwable -> L3a
            java.lang.String r1 = "null cannot be cast to non-null type kotlin.Any"
            r0.<init>(r1)     // Catch: java.lang.Throwable -> L3a
            throw r0     // Catch: java.lang.Throwable -> L3a
        L36:
            r8.setRereading(r2)
            return
        L3a:
            r0 = move-exception
            r8.setRereading(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.RecomposeScopeImpl.rereadTrackedInstances():void");
    }

    public final Function1<Composition, Unit> end(final int token) {
        int size;
        final IdentityArrayIntMap identityArrayIntMap = this.trackedInstances;
        if (identityArrayIntMap != null && !getSkipped$runtime_release() && (size = identityArrayIntMap.getSize()) > 0) {
            int i = 0;
            while (true) {
                int i2 = i + 1;
                if (identityArrayIntMap.getKeys()[i] == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Any");
                }
                if (identityArrayIntMap.getValues()[i] != token) {
                    return new Function1<Composition, Unit>() { // from class: androidx.compose.runtime.RecomposeScopeImpl$end$1$2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Composition composition) {
                            invoke2(composition);
                            return Unit.INSTANCE;
                        }

                        /* JADX WARN: Code restructure failed: missing block: B:20:0x005a, code lost:
                        
                            r13 = r2.trackedDependencies;
                         */
                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        /*
                            Code decompiled incorrectly, please refer to instructions dump.
                            To view partially-correct add '--show-bad-code' argument
                        */
                        public final void invoke2(androidx.compose.runtime.Composition r15) {
                            /*
                                r14 = this;
                                java.lang.String r0 = "composition"
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r15, r0)
                                androidx.compose.runtime.RecomposeScopeImpl r0 = androidx.compose.runtime.RecomposeScopeImpl.this
                                int r0 = androidx.compose.runtime.RecomposeScopeImpl.access$getCurrentToken$p(r0)
                                int r1 = r2
                                if (r0 != r1) goto Lb1
                                androidx.compose.runtime.collection.IdentityArrayIntMap r0 = r3
                                androidx.compose.runtime.RecomposeScopeImpl r1 = androidx.compose.runtime.RecomposeScopeImpl.this
                                androidx.compose.runtime.collection.IdentityArrayIntMap r1 = androidx.compose.runtime.RecomposeScopeImpl.access$getTrackedInstances$p(r1)
                                boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
                                if (r0 == 0) goto Lb1
                                boolean r0 = r15 instanceof androidx.compose.runtime.CompositionImpl
                                if (r0 == 0) goto Lb1
                                androidx.compose.runtime.collection.IdentityArrayIntMap r0 = r3
                                int r1 = r2
                                androidx.compose.runtime.RecomposeScopeImpl r2 = androidx.compose.runtime.RecomposeScopeImpl.this
                                int r3 = r0.getSize()
                                r4 = 0
                                r5 = 0
                                if (r3 <= 0) goto L8d
                                r6 = r4
                                r7 = r6
                            L31:
                                int r8 = r6 + 1
                                java.lang.Object[] r9 = r0.getKeys()
                                r9 = r9[r6]
                                if (r9 == 0) goto L85
                                int[] r10 = r0.getValues()
                                r10 = r10[r6]
                                if (r10 == r1) goto L45
                                r11 = 1
                                goto L46
                            L45:
                                r11 = r4
                            L46:
                                if (r11 == 0) goto L6d
                                r12 = r15
                                androidx.compose.runtime.CompositionImpl r12 = (androidx.compose.runtime.CompositionImpl) r12
                                r12.removeObservation$runtime_release(r9, r2)
                                boolean r12 = r9 instanceof androidx.compose.runtime.DerivedState
                                if (r12 == 0) goto L56
                                r12 = r9
                                androidx.compose.runtime.DerivedState r12 = (androidx.compose.runtime.DerivedState) r12
                                goto L57
                            L56:
                                r12 = r5
                            L57:
                                if (r12 != 0) goto L5a
                                goto L6d
                            L5a:
                                androidx.compose.runtime.collection.IdentityArrayMap r13 = androidx.compose.runtime.RecomposeScopeImpl.access$getTrackedDependencies$p(r2)
                                if (r13 != 0) goto L61
                                goto L6d
                            L61:
                                r13.remove(r12)
                                int r12 = r13.getSize()
                                if (r12 != 0) goto L6d
                                androidx.compose.runtime.RecomposeScopeImpl.access$setTrackedDependencies$p(r2, r5)
                            L6d:
                                if (r11 != 0) goto L7f
                                if (r7 == r6) goto L7d
                                java.lang.Object[] r6 = r0.getKeys()
                                r6[r7] = r9
                                int[] r6 = r0.getValues()
                                r6[r7] = r10
                            L7d:
                                int r7 = r7 + 1
                            L7f:
                                if (r8 < r3) goto L83
                                r4 = r7
                                goto L8d
                            L83:
                                r6 = r8
                                goto L31
                            L85:
                                java.lang.NullPointerException r15 = new java.lang.NullPointerException
                                java.lang.String r0 = "null cannot be cast to non-null type kotlin.Any"
                                r15.<init>(r0)
                                throw r15
                            L8d:
                                int r15 = r0.getSize()
                                if (r4 >= r15) goto La1
                                r1 = r4
                            L94:
                                int r2 = r1 + 1
                                java.lang.Object[] r3 = r0.getKeys()
                                r3[r1] = r5
                                if (r2 < r15) goto L9f
                                goto La1
                            L9f:
                                r1 = r2
                                goto L94
                            La1:
                                r0.setSize(r4)
                                androidx.compose.runtime.collection.IdentityArrayIntMap r15 = r3
                                int r15 = r15.getSize()
                                if (r15 != 0) goto Lb1
                                androidx.compose.runtime.RecomposeScopeImpl r15 = androidx.compose.runtime.RecomposeScopeImpl.this
                                androidx.compose.runtime.RecomposeScopeImpl.access$setTrackedInstances$p(r15, r5)
                            Lb1:
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.RecomposeScopeImpl$end$1$2.invoke2(androidx.compose.runtime.Composition):void");
                        }
                    };
                }
                if (i2 >= size) {
                    break;
                }
                i = i2;
            }
        }
        return null;
    }
}
