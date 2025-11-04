package androidx.compose.runtime;

import androidx.compose.runtime.snapshots.StateObject;
import androidx.exifinterface.media.ExifInterface;
import java.util.Set;
import kotlin.Metadata;

/* compiled from: SnapshotState.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\b`\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002R\u0012\u0010\u0003\u001a\u00028\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0018\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Landroidx/compose/runtime/DerivedState;", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/compose/runtime/State;", "currentValue", "getCurrentValue", "()Ljava/lang/Object;", "dependencies", "", "Landroidx/compose/runtime/snapshots/StateObject;", "getDependencies", "()Ljava/util/Set;", "runtime_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public interface DerivedState<T> extends State<T> {
    T getCurrentValue();

    Set<StateObject> getDependencies();
}
