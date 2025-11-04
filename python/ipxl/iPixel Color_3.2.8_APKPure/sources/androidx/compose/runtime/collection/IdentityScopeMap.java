package androidx.compose.runtime.collection;

import androidx.autofill.HintConstants;
import androidx.compose.runtime.ActualJvm_jvmKt;
import androidx.exifinterface.media.ExifInterface;
import java.util.Arrays;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: IdentityScopeMap.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0015\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u001b\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u00022\u0006\u0010%\u001a\u00028\u0000¢\u0006\u0002\u0010&J\u0006\u0010'\u001a\u00020(J\u0011\u0010)\u001a\u00020#2\u0006\u0010*\u001a\u00020\u0002H\u0086\u0002J\u0012\u0010+\u001a\u00020\u000e2\b\u0010$\u001a\u0004\u0018\u00010\u0002H\u0002J\"\u0010,\u001a\u00020\u000e2\u0006\u0010-\u001a\u00020\u000e2\b\u0010$\u001a\u0004\u0018\u00010\u00022\u0006\u0010.\u001a\u00020\u000eH\u0002J7\u0010/\u001a\u00020(2\u0006\u0010$\u001a\u00020\u00022!\u00100\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b2\u0012\b\b3\u0012\u0004\b\b(%\u0012\u0004\u0012\u00020(01H\u0086\bø\u0001\u0000J\u0016\u00104\u001a\b\u0012\u0004\u0012\u00028\u00000\u00062\u0006\u0010$\u001a\u00020\u0002H\u0002J\u001b\u00105\u001a\u00020#2\u0006\u0010$\u001a\u00020\u00022\u0006\u0010%\u001a\u00028\u0000¢\u0006\u0002\u0010&J/\u00106\u001a\u00020(2!\u00107\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b2\u0012\b\b3\u0012\u0004\b\b(%\u0012\u0004\u0012\u00020#01H\u0086\bø\u0001\u0000J\u0016\u00108\u001a\b\u0012\u0004\u0012\u00028\u00000\u00062\u0006\u00109\u001a\u00020\u000eH\u0002J\u0011\u0010:\u001a\u00020\u00022\u0006\u00109\u001a\u00020\u000eH\u0082\bR4\u0010\u0004\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00060\u00058\u0000@\u0000X\u0081\u000e¢\u0006\u0016\n\u0002\u0010\f\u0012\u0004\b\u0007\u0010\u0003\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR$\u0010\r\u001a\u00020\u000e8\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u000f\u0010\u0003\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R$\u0010\u0014\u001a\u00020\u00158\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0016\u0010\u0003\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR.\u0010\u001b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00058\u0000@\u0000X\u0081\u000e¢\u0006\u0016\n\u0002\u0010!\u0012\u0004\b\u001c\u0010\u0003\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 \u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006;"}, d2 = {"Landroidx/compose/runtime/collection/IdentityScopeMap;", ExifInterface.GPS_DIRECTION_TRUE, "", "()V", "scopeSets", "", "Landroidx/compose/runtime/collection/IdentityArraySet;", "getScopeSets$annotations", "getScopeSets", "()[Landroidx/compose/runtime/collection/IdentityArraySet;", "setScopeSets", "([Landroidx/compose/runtime/collection/IdentityArraySet;)V", "[Landroidx/compose/runtime/collection/IdentityArraySet;", "size", "", "getSize$annotations", "getSize", "()I", "setSize", "(I)V", "valueOrder", "", "getValueOrder$annotations", "getValueOrder", "()[I", "setValueOrder", "([I)V", "values", "getValues$annotations", "getValues", "()[Ljava/lang/Object;", "setValues", "([Ljava/lang/Object;)V", "[Ljava/lang/Object;", "add", "", "value", "scope", "(Ljava/lang/Object;Ljava/lang/Object;)Z", "clear", "", "contains", "element", "find", "findExactIndex", "midIndex", "valueHash", "forEachScopeOf", "block", "Lkotlin/Function1;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "getOrCreateIdentitySet", "remove", "removeValueIf", "predicate", "scopeSetAt", "index", "valueAt", "runtime_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class IdentityScopeMap<T> {
    private IdentityArraySet<T>[] scopeSets;
    private int size;
    private int[] valueOrder;
    private Object[] values;

    public static /* synthetic */ void getScopeSets$annotations() {
    }

    public static /* synthetic */ void getSize$annotations() {
    }

    public static /* synthetic */ void getValueOrder$annotations() {
    }

    public static /* synthetic */ void getValues$annotations() {
    }

    public IdentityScopeMap() {
        int[] iArr = new int[50];
        for (int i = 0; i < 50; i++) {
            iArr[i] = i;
        }
        this.valueOrder = iArr;
        this.values = new Object[50];
        this.scopeSets = new IdentityArraySet[50];
    }

    public final int[] getValueOrder() {
        return this.valueOrder;
    }

    public final void setValueOrder(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<set-?>");
        this.valueOrder = iArr;
    }

    public final Object[] getValues() {
        return this.values;
    }

    public final void setValues(Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "<set-?>");
        this.values = objArr;
    }

    public final IdentityArraySet<T>[] getScopeSets() {
        return this.scopeSets;
    }

    public final void setScopeSets(IdentityArraySet<T>[] identityArraySetArr) {
        Intrinsics.checkNotNullParameter(identityArraySetArr, "<set-?>");
        this.scopeSets = identityArraySetArr;
    }

    public final int getSize() {
        return this.size;
    }

    public final void setSize(int i) {
        this.size = i;
    }

    private final Object valueAt(int index) {
        Object obj = getValues()[getValueOrder()[index]];
        Intrinsics.checkNotNull(obj);
        return obj;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final IdentityArraySet<T> scopeSetAt(int index) {
        IdentityArraySet<T> identityArraySet = this.scopeSets[this.valueOrder[index]];
        Intrinsics.checkNotNull(identityArraySet);
        return identityArraySet;
    }

    public final boolean add(Object value, T scope) {
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(scope, "scope");
        return getOrCreateIdentitySet(value).add(scope);
    }

    public final boolean contains(Object element) {
        Intrinsics.checkNotNullParameter(element, "element");
        return find(element) >= 0;
    }

    public final void forEachScopeOf(Object value, Function1<? super T, Unit> block) {
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(block, "block");
        int find = find(value);
        if (find >= 0) {
            Iterator<T> it = scopeSetAt(find).iterator();
            while (it.hasNext()) {
                block.invoke(it.next());
            }
        }
    }

    private final IdentityArraySet<T> getOrCreateIdentitySet(Object value) {
        int i;
        if (this.size > 0) {
            i = find(value);
            if (i >= 0) {
                return scopeSetAt(i);
            }
        } else {
            i = -1;
        }
        int i2 = -(i + 1);
        int i3 = this.size;
        int[] iArr = this.valueOrder;
        if (i3 < iArr.length) {
            int i4 = iArr[i3];
            this.values[i4] = value;
            IdentityArraySet<T> identityArraySet = this.scopeSets[i4];
            if (identityArraySet == null) {
                identityArraySet = new IdentityArraySet<>();
                getScopeSets()[i4] = identityArraySet;
            }
            int i5 = this.size;
            if (i2 < i5) {
                int[] iArr2 = this.valueOrder;
                ArraysKt.copyInto(iArr2, iArr2, i2 + 1, i2, i5);
            }
            this.valueOrder[i2] = i4;
            this.size++;
            return identityArraySet;
        }
        int length = iArr.length * 2;
        Object[] copyOf = Arrays.copyOf(this.scopeSets, length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "java.util.Arrays.copyOf(this, newSize)");
        this.scopeSets = (IdentityArraySet[]) copyOf;
        IdentityArraySet<T> identityArraySet2 = new IdentityArraySet<>();
        this.scopeSets[i3] = identityArraySet2;
        Object[] copyOf2 = Arrays.copyOf(this.values, length);
        Intrinsics.checkNotNullExpressionValue(copyOf2, "java.util.Arrays.copyOf(this, newSize)");
        this.values = copyOf2;
        copyOf2[i3] = value;
        int[] iArr3 = new int[length];
        int i6 = this.size + 1;
        if (i6 < length) {
            while (true) {
                int i7 = i6 + 1;
                iArr3[i6] = i6;
                if (i7 >= length) {
                    break;
                }
                i6 = i7;
            }
        }
        int i8 = this.size;
        if (i2 < i8) {
            ArraysKt.copyInto(this.valueOrder, iArr3, i2 + 1, i2, i8);
        }
        iArr3[i2] = i3;
        if (i2 > 0) {
            ArraysKt.copyInto$default(this.valueOrder, iArr3, 0, 0, i2, 6, (Object) null);
        }
        this.valueOrder = iArr3;
        this.size++;
        return identityArraySet2;
    }

    public final void clear() {
        int length = this.scopeSets.length;
        if (length > 0) {
            int i = 0;
            while (true) {
                int i2 = i + 1;
                IdentityArraySet<T> identityArraySet = this.scopeSets[i];
                if (identityArraySet != null) {
                    identityArraySet.clear();
                }
                this.valueOrder[i] = i;
                this.values[i] = null;
                if (i2 >= length) {
                    break;
                } else {
                    i = i2;
                }
            }
        }
        this.size = 0;
    }

    public final boolean remove(Object value, T scope) {
        int i;
        IdentityArraySet<T> identityArraySet;
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(scope, "scope");
        int find = find(value);
        if (find < 0 || (identityArraySet = this.scopeSets[(i = this.valueOrder[find])]) == null) {
            return false;
        }
        boolean remove = identityArraySet.remove(scope);
        if (identityArraySet.size() == 0) {
            int i2 = find + 1;
            int i3 = this.size;
            if (i2 < i3) {
                int[] iArr = this.valueOrder;
                ArraysKt.copyInto(iArr, iArr, find, i2, i3);
            }
            int[] iArr2 = this.valueOrder;
            int i4 = this.size;
            iArr2[i4 - 1] = i;
            this.values[i] = null;
            this.size = i4 - 1;
        }
        return remove;
    }

    public final void removeValueIf(Function1<? super T, Boolean> predicate) {
        int i;
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int size = getSize();
        int i2 = 0;
        if (size > 0) {
            int i3 = 0;
            int i4 = 0;
            while (true) {
                int i5 = i3 + 1;
                int i6 = getValueOrder()[i3];
                IdentityArraySet<T> identityArraySet = getScopeSets()[i6];
                Intrinsics.checkNotNull(identityArraySet);
                int size2 = identityArraySet.size();
                if (size2 > 0) {
                    int i7 = 0;
                    i = 0;
                    while (true) {
                        int i8 = i7 + 1;
                        Object obj = identityArraySet.getValues()[i7];
                        if (obj == null) {
                            throw new NullPointerException("null cannot be cast to non-null type T of androidx.compose.runtime.collection.IdentityArraySet");
                        }
                        if (!predicate.invoke(obj).booleanValue()) {
                            if (i != i7) {
                                identityArraySet.getValues()[i] = obj;
                            }
                            i++;
                        }
                        if (i8 >= size2) {
                            break;
                        } else {
                            i7 = i8;
                        }
                    }
                } else {
                    i = 0;
                }
                int size3 = identityArraySet.size();
                if (i < size3) {
                    int i9 = i;
                    while (true) {
                        int i10 = i9 + 1;
                        identityArraySet.getValues()[i9] = null;
                        if (i10 >= size3) {
                            break;
                        } else {
                            i9 = i10;
                        }
                    }
                }
                identityArraySet.setSize(i);
                if (identityArraySet.size() > 0) {
                    if (i4 != i3) {
                        int i11 = getValueOrder()[i4];
                        getValueOrder()[i4] = i6;
                        getValueOrder()[i3] = i11;
                    }
                    i4++;
                }
                if (i5 >= size) {
                    i2 = i4;
                    break;
                }
                i3 = i5;
            }
        }
        int size4 = getSize();
        if (i2 < size4) {
            int i12 = i2;
            while (true) {
                int i13 = i12 + 1;
                getValues()[getValueOrder()[i12]] = null;
                if (i13 >= size4) {
                    break;
                } else {
                    i12 = i13;
                }
            }
        }
        setSize(i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int find(Object value) {
        int identityHashCode = ActualJvm_jvmKt.identityHashCode(value);
        int i = this.size - 1;
        int i2 = 0;
        while (i2 <= i) {
            int i3 = (i2 + i) >>> 1;
            Object obj = getValues()[getValueOrder()[i3]];
            Intrinsics.checkNotNull(obj);
            int identityHashCode2 = ActualJvm_jvmKt.identityHashCode(obj) - identityHashCode;
            if (identityHashCode2 < 0) {
                i2 = i3 + 1;
            } else {
                if (identityHashCode2 <= 0) {
                    return value == obj ? i3 : findExactIndex(i3, value, identityHashCode);
                }
                i = i3 - 1;
            }
        }
        return -(i2 + 1);
    }

    private final int findExactIndex(int midIndex, Object value, int valueHash) {
        int i = midIndex - 1;
        if (i >= 0) {
            while (true) {
                int i2 = i - 1;
                Object obj = getValues()[getValueOrder()[i]];
                Intrinsics.checkNotNull(obj);
                if (obj != value) {
                    if (ActualJvm_jvmKt.identityHashCode(obj) != valueHash || i2 < 0) {
                        break;
                    }
                    i = i2;
                } else {
                    return i;
                }
            }
        }
        int i3 = midIndex + 1;
        int i4 = this.size;
        if (i3 < i4) {
            while (true) {
                int i5 = i3 + 1;
                Object obj2 = getValues()[getValueOrder()[i3]];
                Intrinsics.checkNotNull(obj2);
                if (obj2 == value) {
                    return i3;
                }
                if (ActualJvm_jvmKt.identityHashCode(obj2) != valueHash) {
                    return -i5;
                }
                if (i5 >= i4) {
                    break;
                }
                i3 = i5;
            }
        }
        return -(this.size + 1);
    }
}
