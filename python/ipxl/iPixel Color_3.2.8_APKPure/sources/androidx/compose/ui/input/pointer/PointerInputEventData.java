package androidx.compose.ui.input.pointer;

import androidx.compose.ui.geometry.Offset;
import com.wifiled.musiclib.player.constant.PlayerFinal;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: InternalPointerInput.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B8\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\fø\u0001\u0000¢\u0006\u0002\u0010\rJ\u0019\u0010\u0019\u001a\u00020\u0003HÆ\u0003ø\u0001\u0000ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\b\u001a\u0010\u0011J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\u0019\u0010\u001c\u001a\u00020\u0007HÆ\u0003ø\u0001\u0000ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\b\u001d\u0010\u0011J\u0019\u0010\u001e\u001a\u00020\u0007HÆ\u0003ø\u0001\u0000ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\b\u001f\u0010\u0011J\t\u0010 \u001a\u00020\nHÆ\u0003J\u0019\u0010!\u001a\u00020\fHÆ\u0003ø\u0001\u0000ø\u0001\u0002ø\u0001\u0001¢\u0006\u0004\b\"\u0010\u0016JR\u0010#\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fHÆ\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b$\u0010%J\u0013\u0010&\u001a\u00020\n2\b\u0010'\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010(\u001a\u00020)HÖ\u0001J\t\u0010*\u001a\u00020+HÖ\u0001R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0010\u0010\u0011R\u001c\u0010\b\u001a\u00020\u0007ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0013\u0010\u0011R\u001c\u0010\u0006\u001a\u00020\u0007ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0014\u0010\u0011R\u001c\u0010\u000b\u001a\u00020\fø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0011\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006,"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputEventData;", "", "id", "Landroidx/compose/ui/input/pointer/PointerId;", "uptime", "", "positionOnScreen", "Landroidx/compose/ui/geometry/Offset;", PlayerFinal.PLAYER_POSITION, "down", "", "type", "Landroidx/compose/ui/input/pointer/PointerType;", "(JJJJZILkotlin/jvm/internal/DefaultConstructorMarker;)V", "getDown", "()Z", "getId-J3iCeTQ", "()J", "J", "getPosition-F1C5BW0", "getPositionOnScreen-F1C5BW0", "getType-T8wyACA", "()I", "I", "getUptime", "component1", "component1-J3iCeTQ", "component2", "component3", "component3-F1C5BW0", "component4", "component4-F1C5BW0", "component5", "component6", "component6-T8wyACA", "copy", "copy-1boDhkU", "(JJJJZI)Landroidx/compose/ui/input/pointer/PointerInputEventData;", "equals", "other", "hashCode", "", "toString", "", "ui_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final /* data */ class PointerInputEventData {
    private final boolean down;
    private final long id;
    private final long position;
    private final long positionOnScreen;
    private final int type;
    private final long uptime;

    public /* synthetic */ PointerInputEventData(long j, long j2, long j3, long j4, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, j2, j3, j4, z, i);
    }

    /* renamed from: copy-1boDhkU$default, reason: not valid java name */
    public static /* synthetic */ PointerInputEventData m1852copy1boDhkU$default(PointerInputEventData pointerInputEventData, long j, long j2, long j3, long j4, boolean z, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            j = pointerInputEventData.id;
        }
        long j5 = j;
        if ((i2 & 2) != 0) {
            j2 = pointerInputEventData.uptime;
        }
        return pointerInputEventData.m1857copy1boDhkU(j5, j2, (i2 & 4) != 0 ? pointerInputEventData.positionOnScreen : j3, (i2 & 8) != 0 ? pointerInputEventData.position : j4, (i2 & 16) != 0 ? pointerInputEventData.down : z, (i2 & 32) != 0 ? pointerInputEventData.type : i);
    }

    /* renamed from: component1-J3iCeTQ, reason: not valid java name and from getter */
    public final long getId() {
        return this.id;
    }

    /* renamed from: component2, reason: from getter */
    public final long getUptime() {
        return this.uptime;
    }

    /* renamed from: component3-F1C5BW0, reason: not valid java name and from getter */
    public final long getPositionOnScreen() {
        return this.positionOnScreen;
    }

    /* renamed from: component4-F1C5BW0, reason: not valid java name and from getter */
    public final long getPosition() {
        return this.position;
    }

    /* renamed from: component5, reason: from getter */
    public final boolean getDown() {
        return this.down;
    }

    /* renamed from: component6-T8wyACA, reason: not valid java name and from getter */
    public final int getType() {
        return this.type;
    }

    /* renamed from: copy-1boDhkU, reason: not valid java name */
    public final PointerInputEventData m1857copy1boDhkU(long id, long uptime, long positionOnScreen, long position, boolean down, int type) {
        return new PointerInputEventData(id, uptime, positionOnScreen, position, down, type, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PointerInputEventData)) {
            return false;
        }
        PointerInputEventData pointerInputEventData = (PointerInputEventData) other;
        return PointerId.m1841equalsimpl0(this.id, pointerInputEventData.id) && this.uptime == pointerInputEventData.uptime && Offset.m439equalsimpl0(this.positionOnScreen, pointerInputEventData.positionOnScreen) && Offset.m439equalsimpl0(this.position, pointerInputEventData.position) && this.down == pointerInputEventData.down && PointerType.m1890equalsimpl0(this.type, pointerInputEventData.type);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int m1842hashCodeimpl = ((((((PointerId.m1842hashCodeimpl(this.id) * 31) + Long.hashCode(this.uptime)) * 31) + Offset.m444hashCodeimpl(this.positionOnScreen)) * 31) + Offset.m444hashCodeimpl(this.position)) * 31;
        boolean z = this.down;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return ((m1842hashCodeimpl + i) * 31) + PointerType.m1891hashCodeimpl(this.type);
    }

    public String toString() {
        return "PointerInputEventData(id=" + ((Object) PointerId.m1843toStringimpl(this.id)) + ", uptime=" + this.uptime + ", positionOnScreen=" + ((Object) Offset.m450toStringimpl(this.positionOnScreen)) + ", position=" + ((Object) Offset.m450toStringimpl(this.position)) + ", down=" + this.down + ", type=" + ((Object) PointerType.m1892toStringimpl(this.type)) + ')';
    }

    private PointerInputEventData(long j, long j2, long j3, long j4, boolean z, int i) {
        this.id = j;
        this.uptime = j2;
        this.positionOnScreen = j3;
        this.position = j4;
        this.down = z;
        this.type = i;
    }

    /* renamed from: getId-J3iCeTQ, reason: not valid java name */
    public final long m1858getIdJ3iCeTQ() {
        return this.id;
    }

    public final long getUptime() {
        return this.uptime;
    }

    /* renamed from: getPositionOnScreen-F1C5BW0, reason: not valid java name */
    public final long m1860getPositionOnScreenF1C5BW0() {
        return this.positionOnScreen;
    }

    /* renamed from: getPosition-F1C5BW0, reason: not valid java name */
    public final long m1859getPositionF1C5BW0() {
        return this.position;
    }

    public final boolean getDown() {
        return this.down;
    }

    /* renamed from: getType-T8wyACA, reason: not valid java name */
    public final int m1861getTypeT8wyACA() {
        return this.type;
    }
}
