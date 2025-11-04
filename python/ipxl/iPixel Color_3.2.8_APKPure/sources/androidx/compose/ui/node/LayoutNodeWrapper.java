package androidx.compose.ui.node;

import androidx.compose.ui.focus.FocusOrder;
import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.geometry.MutableRect;
import androidx.compose.ui.geometry.MutableRectKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.Paint;
import androidx.compose.ui.graphics.ReusableGraphicsLayerScope;
import androidx.compose.ui.input.nestedscroll.NestedScrollDelegatingWrapper;
import androidx.compose.ui.input.pointer.PointerInputFilter;
import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.VerticalAlignmentLine;
import androidx.compose.ui.semantics.SemanticsWrapper;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.wifiled.musiclib.player.constant.PlayerFinal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.crypto.CryptoServicesPermission;

/* compiled from: LayoutNodeWrapper.kt */
@Metadata(d1 = {"\u0000\u0082\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\b \u0018\u0000 Æ\u00012\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005:\u0002Æ\u0001B\r\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ \u0010a\u001a\u00020\u00072\u0006\u0010b\u001a\u00020\u00002\u0006\u0010c\u001a\u00020\u00102\u0006\u0010d\u001a\u00020\fH\u0002J%\u0010a\u001a\u00020e2\u0006\u0010b\u001a\u00020\u00002\u0006\u0010f\u001a\u00020eH\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bg\u0010hJ\b\u0010i\u001a\u00020\u0007H\u0016J\u0010\u0010j\u001a\u00020;2\u0006\u0010k\u001a\u00020:H&J\b\u0010l\u001a\u00020\u0007H\u0016J\u000e\u0010m\u001a\u00020\u00072\u0006\u0010n\u001a\u00020\u0006J\u0018\u0010o\u001a\u00020\u00072\u0006\u0010n\u001a\u00020\u00062\u0006\u0010p\u001a\u00020qH\u0004J\u0015\u0010r\u001a\u00020\u00002\u0006\u0010s\u001a\u00020\u0000H\u0000¢\u0006\u0002\btJ\n\u0010u\u001a\u0004\u0018\u00010vH&J\n\u0010w\u001a\u0004\u0018\u00010xH&J\n\u0010y\u001a\u0004\u0018\u00010vH&J\n\u0010z\u001a\u0004\u0018\u00010xH&J\n\u0010{\u001a\u0004\u0018\u00010|H&J\u000f\u0010}\u001a\u0004\u0018\u00010vH\u0000¢\u0006\u0002\b~J\u0010\u0010\u007f\u001a\u0004\u0018\u00010xH\u0000¢\u0006\u0003\b\u0080\u0001J\u000b\u0010\u0081\u0001\u001a\u0004\u0018\u00010vH&J\u000b\u0010\u0082\u0001\u001a\u0004\u0018\u00010xH&J\u000b\u0010\u0083\u0001\u001a\u0004\u0018\u00010|H&J \u0010\u0084\u0001\u001a\u00020e2\u0006\u0010B\u001a\u00020eH\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\b\u0085\u0001\u0010\u0086\u0001J\u001a\u0010\u0087\u0001\u001a\u00020\u00072\u0007\u0010\u0088\u0001\u001a\u00020\u00102\u0006\u0010d\u001a\u00020\fH\u0002J\u0012\u0010\u0089\u0001\u001a\u00020;2\u0006\u0010k\u001a\u00020:H\u0086\u0002J\u000b\u0010\u008a\u0001\u001a\u0004\u0018\u00010\u0003H\u0014J2\u0010\u008b\u0001\u001a\u00020\u00072\u0007\u0010\u008c\u0001\u001a\u00020e2\u000f\u0010\u008d\u0001\u001a\n\u0012\u0005\u0012\u00030\u008f\u00010\u008e\u0001H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\b\u0090\u0001\u0010\u0091\u0001J2\u0010\u0092\u0001\u001a\u00020\u00072\u0007\u0010\u008c\u0001\u001a\u00020e2\u000f\u0010\u0093\u0001\u001a\n\u0012\u0005\u0012\u00030\u0094\u00010\u008e\u0001H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\b\u0095\u0001\u0010\u0091\u0001J\t\u0010\u0096\u0001\u001a\u00020\u0007H\u0016J\u0012\u0010\u0097\u0001\u001a\u00020\u00072\u0006\u0010n\u001a\u00020\u0006H\u0096\u0002J!\u0010\u0098\u0001\u001a\u00020\f2\u0007\u0010\u008c\u0001\u001a\u00020eH\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\b\u0099\u0001\u0010\u009a\u0001J\u001b\u0010\u009b\u0001\u001a\u00030\u009c\u00012\u0007\u0010\u009d\u0001\u001a\u00020\u00032\u0006\u0010d\u001a\u00020\fH\u0016J*\u0010\u009e\u0001\u001a\u00020e2\u0007\u0010\u009d\u0001\u001a\u00020\u00032\u0007\u0010\u009f\u0001\u001a\u00020eH\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\b \u0001\u0010¡\u0001J!\u0010¢\u0001\u001a\u00020e2\u0007\u0010£\u0001\u001a\u00020eH\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\b¤\u0001\u0010\u0086\u0001J!\u0010¥\u0001\u001a\u00020e2\u0007\u0010£\u0001\u001a\u00020eH\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\b¦\u0001\u0010\u0086\u0001J\"\u0010§\u0001\u001a\u00020\u00072\u0019\u0010%\u001a\u0015\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0005¢\u0006\u0002\b$J\u001b\u0010¨\u0001\u001a\u00020\u00072\u0007\u0010©\u0001\u001a\u00020;2\u0007\u0010ª\u0001\u001a\u00020;H\u0014J\t\u0010«\u0001\u001a\u00020\u0007H\u0016J\u0011\u0010¬\u0001\u001a\u00020\u00072\u0006\u0010n\u001a\u00020\u0006H$J5\u0010\u00ad\u0001\u001a\u00020\u00012\b\u0010®\u0001\u001a\u00030¯\u00012\r\u0010°\u0001\u001a\b\u0012\u0004\u0012\u00020\u00010\u0015H\u0084\bø\u0001\u0000ø\u0001\u0003ø\u0001\u0001¢\u0006\u0006\b±\u0001\u0010²\u0001JC\u0010³\u0001\u001a\u00020\u00072\u0006\u0010B\u001a\u00020A2\u0006\u0010\\\u001a\u00020[2\u0019\u0010%\u001a\u0015\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0005¢\u0006\u0002\b$H\u0014ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\b´\u0001\u0010µ\u0001J\u0013\u0010¶\u0001\u001a\u00020\u00072\b\u0010·\u0001\u001a\u00030¸\u0001H\u0016J\u0013\u0010¹\u0001\u001a\u00020\u00072\b\u0010º\u0001\u001a\u00030»\u0001H\u0016J\u001a\u0010¼\u0001\u001a\u00020\u00072\u0007\u0010\u0088\u0001\u001a\u00020\u00102\u0006\u0010d\u001a\u00020\fH\u0002J \u0010½\u0001\u001a\u00020e2\u0006\u0010B\u001a\u00020eH\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\b¾\u0001\u0010\u0086\u0001J\t\u0010¿\u0001\u001a\u00020\u0007H\u0002J!\u0010À\u0001\u001a\u00020e2\u0007\u0010Á\u0001\u001a\u00020eH\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\bÂ\u0001\u0010\u0086\u0001J*\u0010Ã\u0001\u001a\u00020\u00072\u0006\u0010n\u001a\u00020\u00062\u0013\u0010°\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005H\u0084\bø\u0001\u0003J!\u0010Ä\u0001\u001a\u00020\f2\u0007\u0010\u008c\u0001\u001a\u00020eH\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\bÅ\u0001\u0010\u009a\u0001R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\u00020\f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00070\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0016\u001a\u00020\f8F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0013R\u000e\u0010\u0017\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0018\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0013\"\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0013R\u001e\u0010\u001d\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\f@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0013R\"\u0010 \u001a\u0004\u0018\u00010\u001f2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001f@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"RD\u0010%\u001a\u0015\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0005¢\u0006\u0002\b$2\u0019\u0010\u001c\u001a\u0015\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0005¢\u0006\u0002\b$@BX\u0084\u000e¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u000e\u0010(\u001a\u00020)X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020+X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\tX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-R$\u0010/\u001a\u00020\u000e2\u0006\u0010.\u001a\u00020\u000e8F@@X\u0086\u000e¢\u0006\f\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\u0012\u00104\u001a\u000205X¦\u0004¢\u0006\u0006\u001a\u0004\b6\u00107R\u001c\u00108\u001a\u0010\u0012\u0004\u0012\u00020:\u0012\u0004\u0012\u00020;\u0018\u000109X\u0082\u000e¢\u0006\u0002\n\u0000R\u0013\u0010<\u001a\u0004\u0018\u00010\u00038F¢\u0006\u0006\u001a\u0004\b=\u0010>R\u0013\u0010?\u001a\u0004\u0018\u00010\u00038F¢\u0006\u0006\u001a\u0004\b@\u0010>R)\u0010B\u001a\u00020A2\u0006\u0010\u001c\u001a\u00020A@BX\u0086\u000eø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010E\u001a\u0004\bC\u0010DR\u001a\u0010F\u001a\b\u0012\u0004\u0012\u00020:0G8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bH\u0010IR\u0014\u0010J\u001a\u00020\u00108BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bK\u0010LR\u001a\u0010M\u001a\u00020N8Fø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0006\u001a\u0004\bO\u0010DR\u0014\u0010P\u001a\u00020Q8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bR\u0010SR\u0016\u0010T\u001a\u0004\u0018\u00010\u00008PX\u0090\u0004¢\u0006\u0006\u001a\u0004\bU\u0010VR\u001c\u0010W\u001a\u0004\u0018\u00010\u0000X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bX\u0010V\"\u0004\bY\u0010ZR$\u0010\\\u001a\u00020[2\u0006\u0010\u001c\u001a\u00020[@DX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b]\u0010^\"\u0004\b_\u0010`\u0082\u0002\u0016\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!\n\u0005\b\u009920\u0001¨\u0006Ç\u0001"}, d2 = {"Landroidx/compose/ui/node/LayoutNodeWrapper;", "Landroidx/compose/ui/layout/Placeable;", "Landroidx/compose/ui/layout/Measurable;", "Landroidx/compose/ui/layout/LayoutCoordinates;", "Landroidx/compose/ui/node/OwnerScope;", "Lkotlin/Function1;", "Landroidx/compose/ui/graphics/Canvas;", "", "layoutNode", "Landroidx/compose/ui/node/LayoutNode;", "(Landroidx/compose/ui/node/LayoutNode;)V", "_isAttached", "", "_measureResult", "Landroidx/compose/ui/layout/MeasureResult;", "_rectCache", "Landroidx/compose/ui/geometry/MutableRect;", "hasMeasureResult", "getHasMeasureResult", "()Z", "invalidateParentLayer", "Lkotlin/Function0;", "isAttached", "isClipping", "isShallowPlacing", "setShallowPlacing", "(Z)V", "isValid", "<set-?>", "lastLayerDrawingWasSkipped", "getLastLayerDrawingWasSkipped$ui_release", "Landroidx/compose/ui/node/OwnedLayer;", "layer", "getLayer", "()Landroidx/compose/ui/node/OwnedLayer;", "Landroidx/compose/ui/graphics/GraphicsLayerScope;", "Lkotlin/ExtensionFunctionType;", "layerBlock", "getLayerBlock", "()Lkotlin/jvm/functions/Function1;", "layerDensity", "Landroidx/compose/ui/unit/Density;", "layerLayoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "getLayoutNode$ui_release", "()Landroidx/compose/ui/node/LayoutNode;", "value", "measureResult", "getMeasureResult", "()Landroidx/compose/ui/layout/MeasureResult;", "setMeasureResult$ui_release", "(Landroidx/compose/ui/layout/MeasureResult;)V", "measureScope", "Landroidx/compose/ui/layout/MeasureScope;", "getMeasureScope", "()Landroidx/compose/ui/layout/MeasureScope;", "oldAlignmentLines", "", "Landroidx/compose/ui/layout/AlignmentLine;", "", "parentCoordinates", "getParentCoordinates", "()Landroidx/compose/ui/layout/LayoutCoordinates;", "parentLayoutCoordinates", "getParentLayoutCoordinates", "Landroidx/compose/ui/unit/IntOffset;", PlayerFinal.PLAYER_POSITION, "getPosition-nOcc-ac", "()J", "J", "providedAlignmentLines", "", "getProvidedAlignmentLines", "()Ljava/util/Set;", "rectCache", "getRectCache", "()Landroidx/compose/ui/geometry/MutableRect;", "size", "Landroidx/compose/ui/unit/IntSize;", "getSize-YbymL2g", "snapshotObserver", "Landroidx/compose/ui/node/OwnerSnapshotObserver;", "getSnapshotObserver", "()Landroidx/compose/ui/node/OwnerSnapshotObserver;", "wrapped", "getWrapped$ui_release", "()Landroidx/compose/ui/node/LayoutNodeWrapper;", "wrappedBy", "getWrappedBy$ui_release", "setWrappedBy$ui_release", "(Landroidx/compose/ui/node/LayoutNodeWrapper;)V", "", "zIndex", "getZIndex", "()F", "setZIndex", "(F)V", "ancestorToLocal", "ancestor", "rect", "clipBounds", "Landroidx/compose/ui/geometry/Offset;", TypedValues.CycleType.S_WAVE_OFFSET, "ancestorToLocal-R5De75A", "(Landroidx/compose/ui/node/LayoutNodeWrapper;J)J", "attach", "calculateAlignmentLine", "alignmentLine", "detach", "draw", "canvas", "drawBorder", "paint", "Landroidx/compose/ui/graphics/Paint;", "findCommonAncestor", "other", "findCommonAncestor$ui_release", "findLastFocusWrapper", "Landroidx/compose/ui/node/ModifiedFocusNode;", "findLastKeyInputWrapper", "Landroidx/compose/ui/node/ModifiedKeyInputNode;", "findNextFocusWrapper", "findNextKeyInputWrapper", "findNextNestedScrollWrapper", "Landroidx/compose/ui/input/nestedscroll/NestedScrollDelegatingWrapper;", "findParentFocusNode", "findParentFocusNode$ui_release", "findParentKeyInputNode", "findParentKeyInputNode$ui_release", "findPreviousFocusWrapper", "findPreviousKeyInputWrapper", "findPreviousNestedScrollWrapper", "fromParentPosition", "fromParentPosition-MK-Hz9U", "(J)J", "fromParentRect", "bounds", "get", "getWrappedByCoordinates", "hitTest", "pointerPosition", "hitPointerInputFilters", "", "Landroidx/compose/ui/input/pointer/PointerInputFilter;", "hitTest-3MmeM6k", "(JLjava/util/List;)V", "hitTestSemantics", "hitSemanticsWrappers", "Landroidx/compose/ui/semantics/SemanticsWrapper;", "hitTestSemantics-3MmeM6k", "invalidateLayer", "invoke", "isPointerInBounds", "isPointerInBounds-k-4lQ0M", "(J)Z", "localBoundingBoxOf", "Landroidx/compose/ui/geometry/Rect;", "sourceCoordinates", "localPositionOf", "relativeToSource", "localPositionOf-R5De75A", "(Landroidx/compose/ui/layout/LayoutCoordinates;J)J", "localToRoot", "relativeToLocal", "localToRoot-MK-Hz9U", "localToWindow", "localToWindow-MK-Hz9U", "onLayerBlockUpdated", "onMeasureResultChanged", "width", "height", "onModifierChanged", "performDraw", "performingMeasure", CryptoServicesPermission.CONSTRAINTS, "Landroidx/compose/ui/unit/Constraints;", "block", "performingMeasure-K40F9xA", "(JLkotlin/jvm/functions/Function0;)Landroidx/compose/ui/layout/Placeable;", "placeAt", "placeAt-f8xVGno", "(JFLkotlin/jvm/functions/Function1;)V", "populateFocusOrder", "focusOrder", "Landroidx/compose/ui/focus/FocusOrder;", "propagateFocusEvent", "focusState", "Landroidx/compose/ui/focus/FocusState;", "rectInParent", "toParentPosition", "toParentPosition-MK-Hz9U", "updateLayerParameters", "windowToLocal", "relativeToWindow", "windowToLocal-MK-Hz9U", "withPositionTranslation", "withinLayerBounds", "withinLayerBounds-k-4lQ0M", "Companion", "ui_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public abstract class LayoutNodeWrapper extends Placeable implements Measurable, LayoutCoordinates, OwnerScope, Function1<Canvas, Unit> {
    public static final String ExpectAttachedLayoutCoordinates = "LayoutCoordinate operations are only valid when isAttached is true";
    public static final String UnmeasuredError = "Asking for measurement result of unmeasured layout modifier";
    private boolean _isAttached;
    private MeasureResult _measureResult;
    private MutableRect _rectCache;
    private final Function0<Unit> invalidateParentLayer;
    private boolean isClipping;
    private boolean isShallowPlacing;
    private boolean lastLayerDrawingWasSkipped;
    private OwnedLayer layer;
    private Function1<? super GraphicsLayerScope, Unit> layerBlock;
    private Density layerDensity;
    private LayoutDirection layerLayoutDirection;
    private final LayoutNode layoutNode;
    private Map<AlignmentLine, Integer> oldAlignmentLines;
    private long position;
    private LayoutNodeWrapper wrappedBy;
    private float zIndex;
    private static final Function1<LayoutNodeWrapper, Unit> onCommitAffectingLayerParams = new Function1<LayoutNodeWrapper, Unit>() { // from class: androidx.compose.ui.node.LayoutNodeWrapper$Companion$onCommitAffectingLayerParams$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(LayoutNodeWrapper layoutNodeWrapper) {
            invoke2(layoutNodeWrapper);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(LayoutNodeWrapper wrapper) {
            Intrinsics.checkNotNullParameter(wrapper, "wrapper");
            if (wrapper.isValid()) {
                wrapper.updateLayerParameters();
            }
        }
    };
    private static final Function1<LayoutNodeWrapper, Unit> onCommitAffectingLayer = new Function1<LayoutNodeWrapper, Unit>() { // from class: androidx.compose.ui.node.LayoutNodeWrapper$Companion$onCommitAffectingLayer$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(LayoutNodeWrapper layoutNodeWrapper) {
            invoke2(layoutNodeWrapper);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(LayoutNodeWrapper wrapper) {
            Intrinsics.checkNotNullParameter(wrapper, "wrapper");
            OwnedLayer layer = wrapper.getLayer();
            if (layer == null) {
                return;
            }
            layer.invalidate();
        }
    };
    private static final ReusableGraphicsLayerScope graphicsLayerScope = new ReusableGraphicsLayerScope();

    public abstract int calculateAlignmentLine(AlignmentLine alignmentLine);

    public abstract ModifiedFocusNode findLastFocusWrapper();

    public abstract ModifiedKeyInputNode findLastKeyInputWrapper();

    public abstract ModifiedFocusNode findNextFocusWrapper();

    public abstract ModifiedKeyInputNode findNextKeyInputWrapper();

    public abstract NestedScrollDelegatingWrapper findNextNestedScrollWrapper();

    public abstract ModifiedFocusNode findPreviousFocusWrapper();

    public abstract ModifiedKeyInputNode findPreviousKeyInputWrapper();

    public abstract NestedScrollDelegatingWrapper findPreviousNestedScrollWrapper();

    public abstract MeasureScope getMeasureScope();

    /* renamed from: getWrapped$ui_release */
    public LayoutNodeWrapper getWrapped() {
        return null;
    }

    /* renamed from: hitTest-3MmeM6k */
    public abstract void mo2018hitTest3MmeM6k(long pointerPosition, List<PointerInputFilter> hitPointerInputFilters);

    /* renamed from: hitTestSemantics-3MmeM6k */
    public abstract void mo2019hitTestSemantics3MmeM6k(long pointerPosition, List<SemanticsWrapper> hitSemanticsWrappers);

    protected abstract void performDraw(Canvas canvas);

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Canvas canvas) {
        invoke2(canvas);
        return Unit.INSTANCE;
    }

    /* renamed from: getLayoutNode$ui_release, reason: from getter */
    public final LayoutNode getLayoutNode() {
        return this.layoutNode;
    }

    public LayoutNodeWrapper(LayoutNode layoutNode) {
        Intrinsics.checkNotNullParameter(layoutNode, "layoutNode");
        this.layoutNode = layoutNode;
        this.layerDensity = layoutNode.getDensity();
        this.layerLayoutDirection = layoutNode.getLayoutDirection();
        this.position = IntOffset.INSTANCE.m2518getZeronOccac();
        this.invalidateParentLayer = new Function0<Unit>() { // from class: androidx.compose.ui.node.LayoutNodeWrapper$invalidateParentLayer$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                LayoutNodeWrapper wrappedBy = LayoutNodeWrapper.this.getWrappedBy();
                if (wrappedBy == null) {
                    return;
                }
                wrappedBy.invalidateLayer();
            }
        };
    }

    /* renamed from: getWrappedBy$ui_release, reason: from getter */
    public final LayoutNodeWrapper getWrappedBy() {
        return this.wrappedBy;
    }

    public final void setWrappedBy$ui_release(LayoutNodeWrapper layoutNodeWrapper) {
        this.wrappedBy = layoutNodeWrapper;
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* renamed from: getSize-YbymL2g */
    public final long mo1944getSizeYbymL2g() {
        return getMeasuredSize();
    }

    protected final Function1<GraphicsLayerScope, Unit> getLayerBlock() {
        return this.layerBlock;
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public final boolean isAttached() {
        if (!this._isAttached || this.layoutNode.isAttached()) {
            return this._isAttached;
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }

    public final MeasureResult getMeasureResult() {
        MeasureResult measureResult = this._measureResult;
        if (measureResult != null) {
            return measureResult;
        }
        throw new IllegalStateException(UnmeasuredError.toString());
    }

    public final void setMeasureResult$ui_release(MeasureResult value) {
        LayoutNode parent$ui_release;
        Intrinsics.checkNotNullParameter(value, "value");
        MeasureResult measureResult = this._measureResult;
        if (value != measureResult) {
            this._measureResult = value;
            if (measureResult == null || value.getWidth() != measureResult.getWidth() || value.getHeight() != measureResult.getHeight()) {
                onMeasureResultChanged(value.getWidth(), value.getHeight());
            }
            Map<AlignmentLine, Integer> map = this.oldAlignmentLines;
            if (((map == null || map.isEmpty()) && value.getAlignmentLines().isEmpty()) || Intrinsics.areEqual(value.getAlignmentLines(), this.oldAlignmentLines)) {
                return;
            }
            LayoutNodeWrapper wrapped = getWrapped();
            if (Intrinsics.areEqual(wrapped == null ? null : wrapped.layoutNode, this.layoutNode)) {
                LayoutNode parent$ui_release2 = this.layoutNode.getParent$ui_release();
                if (parent$ui_release2 != null) {
                    parent$ui_release2.onAlignmentsChanged$ui_release();
                }
                if (this.layoutNode.getAlignmentLines().getUsedDuringParentMeasurement()) {
                    LayoutNode parent$ui_release3 = this.layoutNode.getParent$ui_release();
                    if (parent$ui_release3 != null) {
                        parent$ui_release3.requestRemeasure$ui_release();
                    }
                } else if (this.layoutNode.getAlignmentLines().getUsedDuringParentLayout() && (parent$ui_release = this.layoutNode.getParent$ui_release()) != null) {
                    parent$ui_release.requestRelayout$ui_release();
                }
            } else {
                this.layoutNode.onAlignmentsChanged$ui_release();
            }
            this.layoutNode.getAlignmentLines().setDirty$ui_release(true);
            LinkedHashMap linkedHashMap = this.oldAlignmentLines;
            if (linkedHashMap == null) {
                linkedHashMap = new LinkedHashMap();
                this.oldAlignmentLines = linkedHashMap;
            }
            linkedHashMap.clear();
            linkedHashMap.putAll(value.getAlignmentLines());
        }
    }

    private final boolean getHasMeasureResult() {
        return this._measureResult != null;
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public Set<AlignmentLine> getProvidedAlignmentLines() {
        Map<AlignmentLine, Integer> alignmentLines;
        MeasureResult measureResult = this._measureResult;
        Set<AlignmentLine> set = null;
        if (measureResult != null && (alignmentLines = measureResult.getAlignmentLines()) != null) {
            set = alignmentLines.keySet();
        }
        return set == null ? SetsKt.emptySet() : set;
    }

    protected void onMeasureResultChanged(int width, int height) {
        OwnedLayer ownedLayer = this.layer;
        if (ownedLayer != null) {
            ownedLayer.mo2052resizeozmzZPI(IntSizeKt.IntSize(width, height));
        } else {
            LayoutNodeWrapper layoutNodeWrapper = this.wrappedBy;
            if (layoutNodeWrapper != null) {
                layoutNodeWrapper.invalidateLayer();
            }
        }
        Owner owner = this.layoutNode.getOwner();
        if (owner != null) {
            owner.onLayoutChange(this.layoutNode);
        }
        m1969setMeasuredSizeozmzZPI(IntSizeKt.IntSize(width, height));
    }

    /* renamed from: getPosition-nOcc-ac, reason: not valid java name and from getter */
    public final long getPosition() {
        return this.position;
    }

    public final float getZIndex() {
        return this.zIndex;
    }

    protected final void setZIndex(float f) {
        this.zIndex = f;
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public final LayoutCoordinates getParentLayoutCoordinates() {
        if (!isAttached()) {
            throw new IllegalStateException(ExpectAttachedLayoutCoordinates.toString());
        }
        return this.layoutNode.getOuterLayoutNodeWrapper$ui_release().wrappedBy;
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public final LayoutCoordinates getParentCoordinates() {
        if (!isAttached()) {
            throw new IllegalStateException(ExpectAttachedLayoutCoordinates.toString());
        }
        LayoutNodeWrapper layoutNodeWrapper = this.wrappedBy;
        if (layoutNodeWrapper == null) {
            return null;
        }
        return layoutNodeWrapper.getWrappedByCoordinates();
    }

    protected LayoutCoordinates getWrappedByCoordinates() {
        LayoutNodeWrapper layoutNodeWrapper = this.wrappedBy;
        if (layoutNodeWrapper == null) {
            return null;
        }
        return layoutNodeWrapper.getWrappedByCoordinates();
    }

    /* renamed from: isShallowPlacing, reason: from getter */
    public final boolean getIsShallowPlacing() {
        return this.isShallowPlacing;
    }

    public final void setShallowPlacing(boolean z) {
        this.isShallowPlacing = z;
    }

    private final MutableRect getRectCache() {
        MutableRect mutableRect = this._rectCache;
        if (mutableRect != null) {
            return mutableRect;
        }
        MutableRect mutableRect2 = new MutableRect(0.0f, 0.0f, 0.0f, 0.0f);
        this._rectCache = mutableRect2;
        return mutableRect2;
    }

    private final OwnerSnapshotObserver getSnapshotObserver() {
        return LayoutNodeKt.requireOwner(this.layoutNode).getSnapshotObserver();
    }

    /* renamed from: performingMeasure-K40F9xA, reason: not valid java name */
    protected final Placeable m2038performingMeasureK40F9xA(long constraints, Function0<? extends Placeable> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        m1970setMeasurementConstraintsBRTryo0(constraints);
        Placeable invoke = block.invoke();
        OwnedLayer layer = getLayer();
        if (layer == null) {
            return invoke;
        }
        layer.mo2052resizeozmzZPI(getMeasuredSize());
        return invoke;
    }

    @Override // androidx.compose.ui.layout.Measured
    public final int get(AlignmentLine alignmentLine) {
        int calculateAlignmentLine;
        int m2509getYimpl;
        Intrinsics.checkNotNullParameter(alignmentLine, "alignmentLine");
        if (!getHasMeasureResult() || (calculateAlignmentLine = calculateAlignmentLine(alignmentLine)) == Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        if (alignmentLine instanceof VerticalAlignmentLine) {
            m2509getYimpl = IntOffset.m2508getXimpl(m1966getApparentToRealOffsetnOccac());
        } else {
            m2509getYimpl = IntOffset.m2509getYimpl(m1966getApparentToRealOffsetnOccac());
        }
        return calculateAlignmentLine + m2509getYimpl;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.compose.ui.layout.Placeable
    /* renamed from: placeAt-f8xVGno */
    public void mo1933placeAtf8xVGno(long position, float zIndex, Function1<? super GraphicsLayerScope, Unit> layerBlock) {
        onLayerBlockUpdated(layerBlock);
        if (!IntOffset.m2507equalsimpl0(getPosition(), position)) {
            this.position = position;
            OwnedLayer ownedLayer = this.layer;
            if (ownedLayer != null) {
                ownedLayer.mo2051movegyyYBs(position);
            } else {
                LayoutNodeWrapper layoutNodeWrapper = this.wrappedBy;
                if (layoutNodeWrapper != null) {
                    layoutNodeWrapper.invalidateLayer();
                }
            }
            LayoutNodeWrapper wrapped = getWrapped();
            if (!Intrinsics.areEqual(wrapped == null ? null : wrapped.layoutNode, this.layoutNode)) {
                this.layoutNode.onAlignmentsChanged$ui_release();
            } else {
                LayoutNode parent$ui_release = this.layoutNode.getParent$ui_release();
                if (parent$ui_release != null) {
                    parent$ui_release.onAlignmentsChanged$ui_release();
                }
            }
            Owner owner = this.layoutNode.getOwner();
            if (owner != null) {
                owner.onLayoutChange(this.layoutNode);
            }
        }
        this.zIndex = zIndex;
    }

    public final void draw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        OwnedLayer ownedLayer = this.layer;
        if (ownedLayer != null) {
            ownedLayer.drawLayer(canvas);
            return;
        }
        float m2508getXimpl = IntOffset.m2508getXimpl(getPosition());
        float m2509getYimpl = IntOffset.m2509getYimpl(getPosition());
        canvas.translate(m2508getXimpl, m2509getYimpl);
        performDraw(canvas);
        canvas.translate(-m2508getXimpl, -m2509getYimpl);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public void invoke2(final Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        if (this.layoutNode.getIsPlaced()) {
            getSnapshotObserver().observeReads$ui_release(this, onCommitAffectingLayer, new Function0<Unit>() { // from class: androidx.compose.ui.node.LayoutNodeWrapper$invoke$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    LayoutNodeWrapper.this.performDraw(canvas);
                }
            });
            this.lastLayerDrawingWasSkipped = false;
        } else {
            this.lastLayerDrawingWasSkipped = true;
        }
    }

    public final void onLayerBlockUpdated(Function1<? super GraphicsLayerScope, Unit> layerBlock) {
        Owner owner;
        boolean z = (this.layerBlock == layerBlock && Intrinsics.areEqual(this.layerDensity, this.layoutNode.getDensity()) && this.layerLayoutDirection == this.layoutNode.getLayoutDirection()) ? false : true;
        this.layerBlock = layerBlock;
        this.layerDensity = this.layoutNode.getDensity();
        this.layerLayoutDirection = this.layoutNode.getLayoutDirection();
        if (isAttached() && layerBlock != null) {
            if (this.layer != null) {
                if (z) {
                    updateLayerParameters();
                    return;
                }
                return;
            }
            OwnedLayer createLayer = LayoutNodeKt.requireOwner(this.layoutNode).createLayer(this, this.invalidateParentLayer);
            createLayer.mo2052resizeozmzZPI(getMeasuredSize());
            createLayer.mo2051movegyyYBs(getPosition());
            Unit unit = Unit.INSTANCE;
            this.layer = createLayer;
            updateLayerParameters();
            this.layoutNode.setInnerLayerWrapperIsDirty$ui_release(true);
            this.invalidateParentLayer.invoke();
            return;
        }
        OwnedLayer ownedLayer = this.layer;
        if (ownedLayer != null) {
            ownedLayer.destroy();
            getLayoutNode().setInnerLayerWrapperIsDirty$ui_release(true);
            this.invalidateParentLayer.invoke();
            if (isAttached() && (owner = getLayoutNode().getOwner()) != null) {
                owner.onLayoutChange(getLayoutNode());
            }
        }
        this.layer = null;
        this.lastLayerDrawingWasSkipped = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateLayerParameters() {
        OwnedLayer ownedLayer = this.layer;
        if (ownedLayer != null) {
            final Function1<? super GraphicsLayerScope, Unit> function1 = this.layerBlock;
            if (function1 == null) {
                throw new IllegalArgumentException("Required value was null.".toString());
            }
            ReusableGraphicsLayerScope reusableGraphicsLayerScope = graphicsLayerScope;
            reusableGraphicsLayerScope.reset();
            reusableGraphicsLayerScope.setGraphicsDensity$ui_release(this.layoutNode.getDensity());
            getSnapshotObserver().observeReads$ui_release(this, onCommitAffectingLayerParams, new Function0<Unit>() { // from class: androidx.compose.ui.node.LayoutNodeWrapper$updateLayerParameters$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    ReusableGraphicsLayerScope reusableGraphicsLayerScope2;
                    Function1<GraphicsLayerScope, Unit> function12 = function1;
                    reusableGraphicsLayerScope2 = LayoutNodeWrapper.graphicsLayerScope;
                    function12.invoke(reusableGraphicsLayerScope2);
                }
            });
            ownedLayer.mo2053updateLayerPropertiesdRfWZ4U(reusableGraphicsLayerScope.getScaleX(), reusableGraphicsLayerScope.getScaleY(), reusableGraphicsLayerScope.getAlpha(), reusableGraphicsLayerScope.getTranslationX(), reusableGraphicsLayerScope.getTranslationY(), reusableGraphicsLayerScope.getShadowElevation(), reusableGraphicsLayerScope.getRotationX(), reusableGraphicsLayerScope.getRotationY(), reusableGraphicsLayerScope.getRotationZ(), reusableGraphicsLayerScope.getCameraDistance(), reusableGraphicsLayerScope.getTransformOrigin(), reusableGraphicsLayerScope.getShape(), reusableGraphicsLayerScope.getClip(), this.layoutNode.getLayoutDirection(), this.layoutNode.getDensity());
            this.isClipping = reusableGraphicsLayerScope.getClip();
        } else {
            if (!(this.layerBlock == null)) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
        }
        Owner owner = this.layoutNode.getOwner();
        if (owner == null) {
            return;
        }
        owner.onLayoutChange(this.layoutNode);
    }

    /* renamed from: getLastLayerDrawingWasSkipped$ui_release, reason: from getter */
    public final boolean getLastLayerDrawingWasSkipped() {
        return this.lastLayerDrawingWasSkipped;
    }

    public final OwnedLayer getLayer() {
        return this.layer;
    }

    @Override // androidx.compose.ui.node.OwnerScope
    public boolean isValid() {
        return this.layer != null;
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* renamed from: windowToLocal-MK-Hz9U */
    public long mo1948windowToLocalMKHz9U(long relativeToWindow) {
        if (!isAttached()) {
            throw new IllegalStateException(ExpectAttachedLayoutCoordinates.toString());
        }
        LayoutCoordinates findRoot = LayoutCoordinatesKt.findRoot(this);
        return mo1945localPositionOfR5De75A(findRoot, Offset.m446minusMKHz9U(LayoutNodeKt.requireOwner(this.layoutNode).mo2054calculateLocalPositionMKHz9U(relativeToWindow), LayoutCoordinatesKt.positionInRoot(findRoot)));
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* renamed from: localToWindow-MK-Hz9U */
    public long mo1947localToWindowMKHz9U(long relativeToLocal) {
        return LayoutNodeKt.requireOwner(this.layoutNode).mo2055calculatePositionInWindowMKHz9U(mo1946localToRootMKHz9U(relativeToLocal));
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* renamed from: localPositionOf-R5De75A */
    public long mo1945localPositionOfR5De75A(LayoutCoordinates sourceCoordinates, long relativeToSource) {
        Intrinsics.checkNotNullParameter(sourceCoordinates, "sourceCoordinates");
        LayoutNodeWrapper layoutNodeWrapper = (LayoutNodeWrapper) sourceCoordinates;
        LayoutNodeWrapper findCommonAncestor$ui_release = findCommonAncestor$ui_release(layoutNodeWrapper);
        while (layoutNodeWrapper != findCommonAncestor$ui_release) {
            relativeToSource = layoutNodeWrapper.m2039toParentPositionMKHz9U(relativeToSource);
            layoutNodeWrapper = layoutNodeWrapper.wrappedBy;
            Intrinsics.checkNotNull(layoutNodeWrapper);
        }
        return m2034ancestorToLocalR5De75A(findCommonAncestor$ui_release, relativeToSource);
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public Rect localBoundingBoxOf(LayoutCoordinates sourceCoordinates, boolean clipBounds) {
        Intrinsics.checkNotNullParameter(sourceCoordinates, "sourceCoordinates");
        if (!isAttached()) {
            throw new IllegalStateException(ExpectAttachedLayoutCoordinates.toString());
        }
        if (!sourceCoordinates.isAttached()) {
            throw new IllegalStateException(("LayoutCoordinates " + sourceCoordinates + " is not attached!").toString());
        }
        LayoutNodeWrapper layoutNodeWrapper = (LayoutNodeWrapper) sourceCoordinates;
        LayoutNodeWrapper findCommonAncestor$ui_release = findCommonAncestor$ui_release(layoutNodeWrapper);
        MutableRect rectCache = getRectCache();
        rectCache.setLeft(0.0f);
        rectCache.setTop(0.0f);
        rectCache.setRight(IntSize.m2550getWidthimpl(sourceCoordinates.mo1944getSizeYbymL2g()));
        rectCache.setBottom(IntSize.m2549getHeightimpl(sourceCoordinates.mo1944getSizeYbymL2g()));
        while (layoutNodeWrapper != findCommonAncestor$ui_release) {
            layoutNodeWrapper.rectInParent(rectCache, clipBounds);
            if (rectCache.isEmpty()) {
                return Rect.INSTANCE.getZero();
            }
            layoutNodeWrapper = layoutNodeWrapper.wrappedBy;
            Intrinsics.checkNotNull(layoutNodeWrapper);
        }
        ancestorToLocal(findCommonAncestor$ui_release, rectCache, clipBounds);
        return MutableRectKt.toRect(rectCache);
    }

    /* renamed from: ancestorToLocal-R5De75A, reason: not valid java name */
    private final long m2034ancestorToLocalR5De75A(LayoutNodeWrapper ancestor, long offset) {
        if (ancestor == this) {
            return offset;
        }
        LayoutNodeWrapper layoutNodeWrapper = this.wrappedBy;
        if (layoutNodeWrapper == null || Intrinsics.areEqual(ancestor, layoutNodeWrapper)) {
            return m2035fromParentPositionMKHz9U(offset);
        }
        return m2035fromParentPositionMKHz9U(layoutNodeWrapper.m2034ancestorToLocalR5De75A(ancestor, offset));
    }

    private final void ancestorToLocal(LayoutNodeWrapper ancestor, MutableRect rect, boolean clipBounds) {
        if (ancestor == this) {
            return;
        }
        LayoutNodeWrapper layoutNodeWrapper = this.wrappedBy;
        if (layoutNodeWrapper != null) {
            layoutNodeWrapper.ancestorToLocal(ancestor, rect, clipBounds);
        }
        fromParentRect(rect, clipBounds);
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* renamed from: localToRoot-MK-Hz9U */
    public long mo1946localToRootMKHz9U(long relativeToLocal) {
        if (!isAttached()) {
            throw new IllegalStateException(ExpectAttachedLayoutCoordinates.toString());
        }
        for (LayoutNodeWrapper layoutNodeWrapper = this; layoutNodeWrapper != null; layoutNodeWrapper = layoutNodeWrapper.wrappedBy) {
            relativeToLocal = layoutNodeWrapper.m2039toParentPositionMKHz9U(relativeToLocal);
        }
        return relativeToLocal;
    }

    protected final void withPositionTranslation(Canvas canvas, Function1<? super Canvas, Unit> block) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        Intrinsics.checkNotNullParameter(block, "block");
        float m2508getXimpl = IntOffset.m2508getXimpl(getPosition());
        float m2509getYimpl = IntOffset.m2509getYimpl(getPosition());
        canvas.translate(m2508getXimpl, m2509getYimpl);
        block.invoke(canvas);
        canvas.translate(-m2508getXimpl, -m2509getYimpl);
    }

    /* renamed from: toParentPosition-MK-Hz9U, reason: not valid java name */
    public long m2039toParentPositionMKHz9U(long position) {
        OwnedLayer ownedLayer = this.layer;
        if (ownedLayer != null) {
            position = ownedLayer.mo2050mapOffset8S9VItk(position, false);
        }
        return IntOffsetKt.m2522plusNvtHpc(position, getPosition());
    }

    /* renamed from: fromParentPosition-MK-Hz9U, reason: not valid java name */
    public long m2035fromParentPositionMKHz9U(long position) {
        long m2520minusNvtHpc = IntOffsetKt.m2520minusNvtHpc(position, getPosition());
        OwnedLayer ownedLayer = this.layer;
        return ownedLayer == null ? m2520minusNvtHpc : ownedLayer.mo2050mapOffset8S9VItk(m2520minusNvtHpc, true);
    }

    protected final void drawBorder(Canvas canvas, Paint paint) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        Intrinsics.checkNotNullParameter(paint, "paint");
        canvas.drawRect(new Rect(0.5f, 0.5f, IntSize.m2550getWidthimpl(getMeasuredSize()) - 0.5f, IntSize.m2549getHeightimpl(getMeasuredSize()) - 0.5f), paint);
    }

    public void attach() {
        this._isAttached = true;
        onLayerBlockUpdated(this.layerBlock);
    }

    public void detach() {
        this._isAttached = false;
        onLayerBlockUpdated(this.layerBlock);
        LayoutNode parent$ui_release = this.layoutNode.getParent$ui_release();
        if (parent$ui_release == null) {
            return;
        }
        parent$ui_release.invalidateLayer$ui_release();
    }

    private final void rectInParent(MutableRect bounds, boolean clipBounds) {
        OwnedLayer ownedLayer = this.layer;
        if (ownedLayer != null) {
            if (this.isClipping && clipBounds) {
                bounds.intersect(0.0f, 0.0f, IntSize.m2550getWidthimpl(mo1944getSizeYbymL2g()), IntSize.m2549getHeightimpl(mo1944getSizeYbymL2g()));
                if (bounds.isEmpty()) {
                    return;
                }
            }
            ownedLayer.mapBounds(bounds, false);
        }
        float m2508getXimpl = IntOffset.m2508getXimpl(getPosition());
        bounds.setLeft(bounds.getLeft() + m2508getXimpl);
        bounds.setRight(bounds.getRight() + m2508getXimpl);
        float m2509getYimpl = IntOffset.m2509getYimpl(getPosition());
        bounds.setTop(bounds.getTop() + m2509getYimpl);
        bounds.setBottom(bounds.getBottom() + m2509getYimpl);
    }

    private final void fromParentRect(MutableRect bounds, boolean clipBounds) {
        float m2508getXimpl = IntOffset.m2508getXimpl(getPosition());
        bounds.setLeft(bounds.getLeft() - m2508getXimpl);
        bounds.setRight(bounds.getRight() - m2508getXimpl);
        float m2509getYimpl = IntOffset.m2509getYimpl(getPosition());
        bounds.setTop(bounds.getTop() - m2509getYimpl);
        bounds.setBottom(bounds.getBottom() - m2509getYimpl);
        OwnedLayer ownedLayer = this.layer;
        if (ownedLayer != null) {
            ownedLayer.mapBounds(bounds, true);
            if (this.isClipping && clipBounds) {
                bounds.intersect(0.0f, 0.0f, IntSize.m2550getWidthimpl(mo1944getSizeYbymL2g()), IntSize.m2549getHeightimpl(mo1944getSizeYbymL2g()));
                bounds.isEmpty();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: withinLayerBounds-k-4lQ0M, reason: not valid java name */
    public final boolean m2040withinLayerBoundsk4lQ0M(long pointerPosition) {
        OwnedLayer ownedLayer = this.layer;
        if (ownedLayer == null || !this.isClipping) {
            return true;
        }
        return ownedLayer.mo2049isInLayerk4lQ0M(pointerPosition);
    }

    /* renamed from: isPointerInBounds-k-4lQ0M, reason: not valid java name */
    protected final boolean m2037isPointerInBoundsk4lQ0M(long pointerPosition) {
        float m442getXimpl = Offset.m442getXimpl(pointerPosition);
        float m443getYimpl = Offset.m443getYimpl(pointerPosition);
        return m442getXimpl >= 0.0f && m443getYimpl >= 0.0f && m442getXimpl < ((float) getMeasuredWidth()) && m443getYimpl < ((float) getMeasuredHeight());
    }

    public void invalidateLayer() {
        OwnedLayer ownedLayer = this.layer;
        if (ownedLayer != null) {
            ownedLayer.invalidate();
            return;
        }
        LayoutNodeWrapper layoutNodeWrapper = this.wrappedBy;
        if (layoutNodeWrapper == null) {
            return;
        }
        layoutNodeWrapper.invalidateLayer();
    }

    public void propagateFocusEvent(FocusState focusState) {
        Intrinsics.checkNotNullParameter(focusState, "focusState");
        LayoutNodeWrapper layoutNodeWrapper = this.wrappedBy;
        if (layoutNodeWrapper == null) {
            return;
        }
        layoutNodeWrapper.propagateFocusEvent(focusState);
    }

    public void populateFocusOrder(FocusOrder focusOrder) {
        Intrinsics.checkNotNullParameter(focusOrder, "focusOrder");
        LayoutNodeWrapper layoutNodeWrapper = this.wrappedBy;
        if (layoutNodeWrapper == null) {
            return;
        }
        layoutNodeWrapper.populateFocusOrder(focusOrder);
    }

    public final ModifiedFocusNode findParentFocusNode$ui_release() {
        LayoutNodeWrapper layoutNodeWrapper = this.wrappedBy;
        ModifiedFocusNode findPreviousFocusWrapper = layoutNodeWrapper == null ? null : layoutNodeWrapper.findPreviousFocusWrapper();
        if (findPreviousFocusWrapper != null) {
            return findPreviousFocusWrapper;
        }
        for (LayoutNode parent$ui_release = this.layoutNode.getParent$ui_release(); parent$ui_release != null; parent$ui_release = parent$ui_release.getParent$ui_release()) {
            ModifiedFocusNode findLastFocusWrapper = parent$ui_release.getOuterLayoutNodeWrapper$ui_release().findLastFocusWrapper();
            if (findLastFocusWrapper != null) {
                return findLastFocusWrapper;
            }
        }
        return null;
    }

    public final ModifiedKeyInputNode findParentKeyInputNode$ui_release() {
        LayoutNodeWrapper layoutNodeWrapper = this.wrappedBy;
        ModifiedKeyInputNode findPreviousKeyInputWrapper = layoutNodeWrapper == null ? null : layoutNodeWrapper.findPreviousKeyInputWrapper();
        if (findPreviousKeyInputWrapper != null) {
            return findPreviousKeyInputWrapper;
        }
        for (LayoutNode parent$ui_release = this.layoutNode.getParent$ui_release(); parent$ui_release != null; parent$ui_release = parent$ui_release.getParent$ui_release()) {
            ModifiedKeyInputNode findLastKeyInputWrapper = parent$ui_release.getOuterLayoutNodeWrapper$ui_release().findLastKeyInputWrapper();
            if (findLastKeyInputWrapper != null) {
                return findLastKeyInputWrapper;
            }
        }
        return null;
    }

    public void onModifierChanged() {
        OwnedLayer ownedLayer = this.layer;
        if (ownedLayer == null) {
            return;
        }
        ownedLayer.invalidate();
    }

    public final LayoutNodeWrapper findCommonAncestor$ui_release(LayoutNodeWrapper other) {
        Intrinsics.checkNotNullParameter(other, "other");
        LayoutNode layoutNode = other.layoutNode;
        LayoutNode layoutNode2 = this.layoutNode;
        if (layoutNode == layoutNode2) {
            LayoutNodeWrapper outerLayoutNodeWrapper$ui_release = layoutNode2.getOuterLayoutNodeWrapper$ui_release();
            LayoutNodeWrapper layoutNodeWrapper = this;
            while (layoutNodeWrapper != outerLayoutNodeWrapper$ui_release && layoutNodeWrapper != other) {
                layoutNodeWrapper = layoutNodeWrapper.wrappedBy;
                Intrinsics.checkNotNull(layoutNodeWrapper);
            }
            return layoutNodeWrapper == other ? other : this;
        }
        while (layoutNode.getDepth() > layoutNode2.getDepth()) {
            layoutNode = layoutNode.getParent$ui_release();
            Intrinsics.checkNotNull(layoutNode);
        }
        while (layoutNode2.getDepth() > layoutNode.getDepth()) {
            layoutNode2 = layoutNode2.getParent$ui_release();
            Intrinsics.checkNotNull(layoutNode2);
        }
        while (layoutNode != layoutNode2) {
            layoutNode = layoutNode.getParent$ui_release();
            layoutNode2 = layoutNode2.getParent$ui_release();
            if (layoutNode == null || layoutNode2 == null) {
                throw new IllegalArgumentException("layouts are not part of the same hierarchy");
            }
        }
        if (layoutNode2 != this.layoutNode) {
            if (layoutNode != other.layoutNode) {
                return layoutNode.getInnerLayoutNodeWrapper();
            }
        }
    }
}
