package androidx.compose.ui.node;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.DrawModifier;
import androidx.compose.ui.focus.FocusEventModifier;
import androidx.compose.ui.focus.FocusModifier;
import androidx.compose.ui.focus.FocusOrderModifier;
import androidx.compose.ui.focus.FocusRequesterModifier;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.input.key.KeyInputModifier;
import androidx.compose.ui.input.nestedscroll.NestedScrollDelegatingWrapper;
import androidx.compose.ui.input.nestedscroll.NestedScrollModifier;
import androidx.compose.ui.input.pointer.PointerInputFilter;
import androidx.compose.ui.input.pointer.PointerInputModifier;
import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutInfo;
import androidx.compose.ui.layout.LayoutModifier;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.ModifierInfo;
import androidx.compose.ui.layout.OnGloballyPositionedModifier;
import androidx.compose.ui.layout.OnRemeasuredModifier;
import androidx.compose.ui.layout.ParentDataModifier;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.Remeasurement;
import androidx.compose.ui.layout.RemeasurementModifier;
import androidx.compose.ui.platform.JvmActuals_jvmKt;
import androidx.compose.ui.semantics.SemanticsModifier;
import androidx.compose.ui.semantics.SemanticsNodeKt;
import androidx.compose.ui.semantics.SemanticsWrapper;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DensityKt;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.crypto.CryptoServicesPermission;

/* compiled from: LayoutNode.kt */
@Metadata(d1 = {"\u0000º\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b$\n\u0002\u0018\u0002\n\u0002\b\n\b\u0000\u0018\u0000 \u008c\u00022\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u0005:\b\u008c\u0002\u008d\u0002\u008e\u0002\u008f\u0002B\u0007\b\u0010¢\u0006\u0002\u0010\u0006B\u000f\b\u0010\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\t\u0010¢\u0001\u001a\u00020vH\u0002J\u0018\u0010£\u0001\u001a\u00020v2\u0007\u0010\u0084\u0001\u001a\u00020uH\u0000¢\u0006\u0003\b¤\u0001J\u001d\u0010¥\u0001\u001a\u0010\u0012\u0005\u0012\u00030§\u0001\u0012\u0004\u0012\u00020/0¦\u0001H\u0000¢\u0006\u0003\b¨\u0001J\t\u0010©\u0001\u001a\u00020vH\u0002J\u0014\u0010ª\u0001\u001a\u00030«\u00012\b\b\u0002\u0010.\u001a\u00020/H\u0002J\u000f\u0010¬\u0001\u001a\u00020vH\u0000¢\u0006\u0003\b\u00ad\u0001J\u000f\u0010®\u0001\u001a\u00020vH\u0000¢\u0006\u0003\b¯\u0001J\u0019\u0010°\u0001\u001a\u00020v2\b\u0010±\u0001\u001a\u00030²\u0001H\u0000¢\u0006\u0003\b³\u0001J\u001f\u0010´\u0001\u001a\u00020v2\u0013\u0010µ\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020v0tH\u0082\bJ\u001f\u0010¶\u0001\u001a\u00020v2\u0013\u0010µ\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020v0tH\u0082\bJ\t\u0010·\u0001\u001a\u00020vH\u0016J\u0010\u0010¸\u0001\u001a\t\u0012\u0005\u0012\u00030¹\u00010 H\u0016J\u000f\u0010º\u0001\u001a\b\u0012\u0004\u0012\u00020\u007f0\rH\u0002J\u0019\u0010»\u0001\u001a\u00020v2\b\u0010¼\u0001\u001a\u00030½\u0001H\u0000¢\u0006\u0003\b¾\u0001J\t\u0010¿\u0001\u001a\u00020\bH\u0002J3\u0010À\u0001\u001a\u00020v2\b\u0010Á\u0001\u001a\u00030Â\u00012\u000f\u0010Ã\u0001\u001a\n\u0012\u0005\u0012\u00030Å\u00010Ä\u0001H\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\bÆ\u0001\u0010Ç\u0001J3\u0010È\u0001\u001a\u00020v2\b\u0010Á\u0001\u001a\u00030Â\u00012\u000f\u0010É\u0001\u001a\n\u0012\u0005\u0012\u00030Ê\u00010Ä\u0001H\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\bË\u0001\u0010Ç\u0001J\"\u00108\u001a\u00020v2\u000e\u0010µ\u0001\u001a\t\u0012\u0004\u0012\u00020v0Ì\u0001H\u0080\bø\u0001\u0002¢\u0006\u0003\bÍ\u0001J!\u0010Î\u0001\u001a\u00020v2\u0007\u0010Ï\u0001\u001a\u00020/2\u0007\u0010Ð\u0001\u001a\u00020\u0000H\u0000¢\u0006\u0003\bÑ\u0001J\u000f\u0010Ò\u0001\u001a\u00020vH\u0000¢\u0006\u0003\bÓ\u0001J\u000f\u0010Ô\u0001\u001a\u00020vH\u0000¢\u0006\u0003\bÕ\u0001J\t\u0010Ö\u0001\u001a\u00020vH\u0002J\u000f\u0010×\u0001\u001a\u00020vH\u0000¢\u0006\u0003\bØ\u0001J\t\u0010Ù\u0001\u001a\u00020vH\u0002J\u0011\u0010Ú\u0001\u001a\u00020v2\u0006\u0010j\u001a\u00020iH\u0002J\t\u0010Û\u0001\u001a\u00020vH\u0002J\u0012\u0010Ü\u0001\u001a\u00020/2\u0007\u0010\u0098\u0001\u001a\u00020/H\u0016J\u0011\u0010Ý\u0001\u001a\u00020/2\u0006\u00106\u001a\u00020/H\u0016J#\u0010Þ\u0001\u001a\u00030ß\u00012\b\u0010à\u0001\u001a\u00030á\u0001H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\bâ\u0001\u0010ã\u0001J\u0012\u0010ä\u0001\u001a\u00020/2\u0007\u0010\u0098\u0001\u001a\u00020/H\u0016J\u0011\u0010å\u0001\u001a\u00020/2\u0006\u00106\u001a\u00020/H\u0016J*\u0010æ\u0001\u001a\u00020v2\u0007\u0010ç\u0001\u001a\u00020/2\u0007\u0010è\u0001\u001a\u00020/2\u0007\u0010é\u0001\u001a\u00020/H\u0000¢\u0006\u0003\bê\u0001J\u000f\u0010ë\u0001\u001a\u00020vH\u0000¢\u0006\u0003\bì\u0001J\t\u0010í\u0001\u001a\u00020vH\u0002J\t\u0010î\u0001\u001a\u00020vH\u0002J\u000f\u0010ï\u0001\u001a\u00020vH\u0000¢\u0006\u0003\bð\u0001J\t\u0010ñ\u0001\u001a\u00020vH\u0002J!\u0010ò\u0001\u001a\u00020v2\u0007\u0010ó\u0001\u001a\u00020/2\u0007\u0010ô\u0001\u001a\u00020/H\u0000¢\u0006\u0003\bõ\u0001J\t\u0010ö\u0001\u001a\u00020vH\u0002J#\u0010÷\u0001\u001a\u00020\b2\f\b\u0002\u0010à\u0001\u001a\u0005\u0018\u00010á\u0001H\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0003\bø\u0001J\u000f\u0010ù\u0001\u001a\u00020vH\u0000¢\u0006\u0003\bú\u0001J!\u0010û\u0001\u001a\u00020v2\u0007\u0010Ï\u0001\u001a\u00020/2\u0007\u0010é\u0001\u001a\u00020/H\u0000¢\u0006\u0003\bü\u0001J\u000f\u0010ý\u0001\u001a\u00020vH\u0000¢\u0006\u0003\bþ\u0001J\u000f\u0010ÿ\u0001\u001a\u00020vH\u0000¢\u0006\u0003\b\u0080\u0002J\u000f\u0010\u0081\u0002\u001a\u00020vH\u0000¢\u0006\u0003\b\u0082\u0002J\u0012\u0010\u0083\u0002\u001a\u00020v2\u0007\u0010\u0084\u0002\u001a\u00020\u0000H\u0002J\"\u0010\u0085\u0002\u001a\t\u0012\u0002\b\u0003\u0018\u00010\u009b\u00012\u0007\u0010j\u001a\u00030\u0086\u00022\u0007\u0010\u0087\u0002\u001a\u00020\u0013H\u0002J\t\u0010\u0088\u0002\u001a\u00020\bH\u0002J\n\u0010\u0089\u0002\u001a\u00030«\u0001H\u0016J\u001f\u0010\u008a\u0002\u001a\u00020v2\u000e\u0010µ\u0001\u001a\t\u0012\u0004\u0012\u00020v0Ì\u0001H\u0000¢\u0006\u0003\b\u008b\u0002R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00000\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00000\r8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00000\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0000\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00000\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\u00020\u0017X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R$\u0010\u001a\u001a\u00020\b8\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u001b\u0010\u0006\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\tR\u001a\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00000 8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\"R\u0014\u0010#\u001a\u00020$8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b%\u0010&R$\u0010)\u001a\u00020(2\u0006\u0010'\u001a\u00020(@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u001a\u0010.\u001a\u00020/X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\u001a\u00104\u001a\b\u0012\u0004\u0012\u00020\u00000 8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b5\u0010\"R\u0014\u00106\u001a\u00020/8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b7\u00101R\u000e\u00108\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u00109\u001a\u0004\u0018\u00010\u00138@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b:\u0010;R\u001a\u0010<\u001a\u00020\bX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010\u001d\"\u0004\b>\u0010\tR\u0014\u0010?\u001a\u00020\u0013X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b@\u0010;R\u0014\u0010A\u001a\u00020BX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bC\u0010DR\u0014\u0010E\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bE\u0010\u001dR\u001e\u0010G\u001a\u00020\b2\u0006\u0010F\u001a\u00020\b@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\bG\u0010\u001dR\u0014\u0010H\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bH\u0010\u001dR\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010J\u001a\u00020I2\u0006\u0010'\u001a\u00020I@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bK\u0010L\"\u0004\bM\u0010NR\u001a\u0010O\u001a\u00020PX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bQ\u0010R\"\u0004\bS\u0010TR\u0014\u0010U\u001a\u00020VX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bW\u0010XR$\u0010Z\u001a\u00020Y2\u0006\u0010'\u001a\u00020Y@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b[\u0010\\\"\u0004\b]\u0010^R\u0014\u0010_\u001a\u00020`X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\ba\u0010bR\u001a\u0010c\u001a\u00020dX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\be\u0010f\"\u0004\bg\u0010hR$\u0010j\u001a\u00020i2\u0006\u0010'\u001a\u00020i@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bk\u0010l\"\u0004\bm\u0010nR\u001a\u0010o\u001a\u00020\bX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bp\u0010\u001d\"\u0004\bq\u0010\tR\u000e\u0010r\u001a\u00020/X\u0082\u000e¢\u0006\u0002\n\u0000R(\u0010s\u001a\u0010\u0012\u0004\u0012\u00020u\u0012\u0004\u0012\u00020v\u0018\u00010tX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bw\u0010x\"\u0004\by\u0010zR(\u0010{\u001a\u0010\u0012\u0004\u0012\u00020u\u0012\u0004\u0012\u00020v\u0018\u00010tX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b|\u0010x\"\u0004\b}\u0010zR\u0016\u0010~\u001a\n\u0012\u0004\u0012\u00020\u007f\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0080\u0001\u001a\u00020\u00138@X\u0080\u0004¢\u0006\u0007\u001a\u0005\b\u0081\u0001\u0010;R\u0010\u0010\u0082\u0001\u001a\u00030\u0083\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R%\u0010\u0084\u0001\u001a\u0004\u0018\u00010u2\b\u0010F\u001a\u0004\u0018\u00010u@BX\u0080\u000e¢\u0006\n\n\u0000\u001a\u0006\b\u0085\u0001\u0010\u0086\u0001R\u0019\u0010\u0087\u0001\u001a\u0004\u0018\u00010\u00008@X\u0080\u0004¢\u0006\b\u001a\u0006\b\u0088\u0001\u0010\u0089\u0001R\u001a\u0010\u008a\u0001\u001a\u0005\u0018\u00010\u008b\u00018VX\u0096\u0004¢\u0006\b\u001a\u0006\b\u008c\u0001\u0010\u008d\u0001R\u0019\u0010\u008e\u0001\u001a\u0004\u0018\u00010\u00048VX\u0096\u0004¢\u0006\b\u001a\u0006\b\u008f\u0001\u0010\u0090\u0001R \u0010\u0091\u0001\u001a\u00020/2\u0006\u0010F\u001a\u00020/@BX\u0080\u000e¢\u0006\t\n\u0000\u001a\u0005\b\u0092\u0001\u00101R\u000f\u0010\u0093\u0001\u001a\u00020/X\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u0094\u0001\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u0095\u0001\u001a\u00020/X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0096\u0001\u001a\u00020\b8@X\u0080\u0004¢\u0006\u0007\u001a\u0005\b\u0097\u0001\u0010\u001dR\u0016\u0010\u0098\u0001\u001a\u00020/8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b\u0099\u0001\u00101R\u001a\u0010\u009a\u0001\u001a\r\u0012\t\u0012\u0007\u0012\u0002\b\u00030\u009b\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u009c\u0001\u001a\u00030\u009d\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R#\u0010\u009e\u0001\u001a\b\u0012\u0004\u0012\u00020\u00000\r8@X\u0081\u0004¢\u0006\u000e\u0012\u0005\b\u009f\u0001\u0010\u0006\u001a\u0005\b \u0001\u0010\u000fR\u000f\u0010¡\u0001\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0012\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0005\b\u009920\u0001¨\u0006\u0090\u0002"}, d2 = {"Landroidx/compose/ui/node/LayoutNode;", "Landroidx/compose/ui/layout/Measurable;", "Landroidx/compose/ui/layout/Remeasurement;", "Landroidx/compose/ui/node/OwnerScope;", "Landroidx/compose/ui/layout/LayoutInfo;", "Landroidx/compose/ui/node/ComposeUiNode;", "()V", "isVirtual", "", "(Z)V", "ZComparator", "Ljava/util/Comparator;", "_children", "Landroidx/compose/runtime/collection/MutableVector;", "get_children$ui_release", "()Landroidx/compose/runtime/collection/MutableVector;", "_foldedChildren", "_foldedParent", "_innerLayerWrapper", "Landroidx/compose/ui/node/LayoutNodeWrapper;", "_unfoldedChildren", "_zSortedChildren", "alignmentLines", "Landroidx/compose/ui/node/LayoutNodeAlignmentLines;", "getAlignmentLines$ui_release", "()Landroidx/compose/ui/node/LayoutNodeAlignmentLines;", "canMultiMeasure", "getCanMultiMeasure$ui_release$annotations", "getCanMultiMeasure$ui_release", "()Z", "setCanMultiMeasure$ui_release", "children", "", "getChildren$ui_release", "()Ljava/util/List;", "coordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "getCoordinates", "()Landroidx/compose/ui/layout/LayoutCoordinates;", "value", "Landroidx/compose/ui/unit/Density;", "density", "getDensity", "()Landroidx/compose/ui/unit/Density;", "setDensity", "(Landroidx/compose/ui/unit/Density;)V", "depth", "", "getDepth$ui_release", "()I", "setDepth$ui_release", "(I)V", "foldedChildren", "getFoldedChildren$ui_release", "height", "getHeight", "ignoreRemeasureRequests", "innerLayerWrapper", "getInnerLayerWrapper$ui_release", "()Landroidx/compose/ui/node/LayoutNodeWrapper;", "innerLayerWrapperIsDirty", "getInnerLayerWrapperIsDirty$ui_release", "setInnerLayerWrapperIsDirty$ui_release", "innerLayoutNodeWrapper", "getInnerLayoutNodeWrapper$ui_release", "intrinsicsPolicy", "Landroidx/compose/ui/node/IntrinsicsPolicy;", "getIntrinsicsPolicy$ui_release", "()Landroidx/compose/ui/node/IntrinsicsPolicy;", "isAttached", "<set-?>", "isPlaced", "isValid", "Landroidx/compose/ui/unit/LayoutDirection;", "layoutDirection", "getLayoutDirection", "()Landroidx/compose/ui/unit/LayoutDirection;", "setLayoutDirection", "(Landroidx/compose/ui/unit/LayoutDirection;)V", "layoutState", "Landroidx/compose/ui/node/LayoutNode$LayoutState;", "getLayoutState$ui_release", "()Landroidx/compose/ui/node/LayoutNode$LayoutState;", "setLayoutState$ui_release", "(Landroidx/compose/ui/node/LayoutNode$LayoutState;)V", "mDrawScope", "Landroidx/compose/ui/node/LayoutNodeDrawScope;", "getMDrawScope$ui_release", "()Landroidx/compose/ui/node/LayoutNodeDrawScope;", "Landroidx/compose/ui/layout/MeasurePolicy;", "measurePolicy", "getMeasurePolicy", "()Landroidx/compose/ui/layout/MeasurePolicy;", "setMeasurePolicy", "(Landroidx/compose/ui/layout/MeasurePolicy;)V", "measureScope", "Landroidx/compose/ui/layout/MeasureScope;", "getMeasureScope$ui_release", "()Landroidx/compose/ui/layout/MeasureScope;", "measuredByParent", "Landroidx/compose/ui/node/LayoutNode$UsageByParent;", "getMeasuredByParent$ui_release", "()Landroidx/compose/ui/node/LayoutNode$UsageByParent;", "setMeasuredByParent$ui_release", "(Landroidx/compose/ui/node/LayoutNode$UsageByParent;)V", "Landroidx/compose/ui/Modifier;", "modifier", "getModifier", "()Landroidx/compose/ui/Modifier;", "setModifier", "(Landroidx/compose/ui/Modifier;)V", "needsOnPositionedDispatch", "getNeedsOnPositionedDispatch$ui_release", "setNeedsOnPositionedDispatch$ui_release", "nextChildPlaceOrder", "onAttach", "Lkotlin/Function1;", "Landroidx/compose/ui/node/Owner;", "", "getOnAttach$ui_release", "()Lkotlin/jvm/functions/Function1;", "setOnAttach$ui_release", "(Lkotlin/jvm/functions/Function1;)V", "onDetach", "getOnDetach$ui_release", "setOnDetach$ui_release", "onPositionedCallbacks", "Landroidx/compose/ui/node/OnGloballyPositionedModifierWrapper;", "outerLayoutNodeWrapper", "getOuterLayoutNodeWrapper$ui_release", "outerMeasurablePlaceable", "Landroidx/compose/ui/node/OuterMeasurablePlaceable;", "owner", "getOwner$ui_release", "()Landroidx/compose/ui/node/Owner;", "parent", "getParent$ui_release", "()Landroidx/compose/ui/node/LayoutNode;", "parentData", "", "getParentData", "()Ljava/lang/Object;", "parentInfo", "getParentInfo", "()Landroidx/compose/ui/layout/LayoutInfo;", "placeOrder", "getPlaceOrder$ui_release", "previousPlaceOrder", "unfoldedVirtualChildrenListDirty", "virtualChildrenCount", "wasMeasuredDuringThisIteration", "getWasMeasuredDuringThisIteration$ui_release", "width", "getWidth", "wrapperCache", "Landroidx/compose/ui/node/DelegatingLayoutNodeWrapper;", "zIndex", "", "zSortedChildren", "getZSortedChildren$annotations", "getZSortedChildren", "zSortedChildrenInvalidated", "alignmentLinesQueriedByModifier", "attach", "attach$ui_release", "calculateAlignmentLines", "", "Landroidx/compose/ui/layout/AlignmentLine;", "calculateAlignmentLines$ui_release", "copyWrappersToCache", "debugTreeToString", "", "detach", "detach$ui_release", "dispatchOnPositionedCallbacks", "dispatchOnPositionedCallbacks$ui_release", "draw", "canvas", "Landroidx/compose/ui/graphics/Canvas;", "draw$ui_release", "forEachDelegate", "block", "forEachDelegateIncludingInner", "forceRemeasure", "getModifierInfo", "Landroidx/compose/ui/layout/ModifierInfo;", "getOrCreateOnPositionedCallbacks", "handleMeasureResult", "measureResult", "Landroidx/compose/ui/layout/MeasureResult;", "handleMeasureResult$ui_release", "hasNewPositioningCallback", "hitTest", "pointerPosition", "Landroidx/compose/ui/geometry/Offset;", "hitPointerInputFilters", "", "Landroidx/compose/ui/input/pointer/PointerInputFilter;", "hitTest-3MmeM6k$ui_release", "(JLjava/util/List;)V", "hitTestSemantics", "hitSemanticsWrappers", "Landroidx/compose/ui/semantics/SemanticsWrapper;", "hitTestSemantics-3MmeM6k$ui_release", "Lkotlin/Function0;", "ignoreRemeasureRequests$ui_release", "insertAt", "index", "instance", "insertAt$ui_release", "invalidateLayer", "invalidateLayer$ui_release", "invalidateLayers", "invalidateLayers$ui_release", "invalidateUnfoldedVirtualChildren", "layoutChildren", "layoutChildren$ui_release", "markNodeAndSubtreeAsPlaced", "markReusedModifiers", "markSubtreeAsNotPlaced", "maxIntrinsicHeight", "maxIntrinsicWidth", "measure", "Landroidx/compose/ui/layout/Placeable;", CryptoServicesPermission.CONSTRAINTS, "Landroidx/compose/ui/unit/Constraints;", "measure-BRTryo0", "(J)Landroidx/compose/ui/layout/Placeable;", "minIntrinsicHeight", "minIntrinsicWidth", "move", TypedValues.TransitionType.S_FROM, TypedValues.TransitionType.S_TO, "count", "move$ui_release", "onAlignmentsChanged", "onAlignmentsChanged$ui_release", "onBeforeLayoutChildren", "onDensityOrLayoutDirectionChanged", "onNodePlaced", "onNodePlaced$ui_release", "onZSortedChildrenInvalidated", "place", "x", "y", "place$ui_release", "recreateUnfoldedChildrenIfDirty", "remeasure", "remeasure-_Sx5XlM$ui_release", "removeAll", "removeAll$ui_release", "removeAt", "removeAt$ui_release", "replace", "replace$ui_release", "requestRelayout", "requestRelayout$ui_release", "requestRemeasure", "requestRemeasure$ui_release", "rescheduleRemeasureOrRelayout", "it", "reuseLayoutNodeWrapper", "Landroidx/compose/ui/Modifier$Element;", "wrapper", "shouldInvalidateParentLayer", "toString", "withNoSnapshotReadObservation", "withNoSnapshotReadObservation$ui_release", "Companion", "LayoutState", "NoIntrinsicsMeasurePolicy", "UsageByParent", "ui_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class LayoutNode implements Measurable, Remeasurement, OwnerScope, LayoutInfo, ComposeUiNode {
    public static final int NotPlacedPlaceOrder = Integer.MAX_VALUE;
    private final Comparator<LayoutNode> ZComparator;
    private final MutableVector<LayoutNode> _foldedChildren;
    private LayoutNode _foldedParent;
    private LayoutNodeWrapper _innerLayerWrapper;
    private MutableVector<LayoutNode> _unfoldedChildren;
    private final MutableVector<LayoutNode> _zSortedChildren;
    private final LayoutNodeAlignmentLines alignmentLines;
    private boolean canMultiMeasure;
    private Density density;
    private int depth;
    private boolean ignoreRemeasureRequests;
    private boolean innerLayerWrapperIsDirty;
    private final LayoutNodeWrapper innerLayoutNodeWrapper;
    private final IntrinsicsPolicy intrinsicsPolicy;
    private boolean isPlaced;
    private final boolean isVirtual;
    private LayoutDirection layoutDirection;
    private LayoutState layoutState;
    private final LayoutNodeDrawScope mDrawScope;
    private MeasurePolicy measurePolicy;
    private final MeasureScope measureScope;
    private UsageByParent measuredByParent;
    private Modifier modifier;
    private boolean needsOnPositionedDispatch;
    private int nextChildPlaceOrder;
    private Function1<? super Owner, Unit> onAttach;
    private Function1<? super Owner, Unit> onDetach;
    private MutableVector<OnGloballyPositionedModifierWrapper> onPositionedCallbacks;
    private final OuterMeasurablePlaceable outerMeasurablePlaceable;
    private Owner owner;
    private int placeOrder;
    private int previousPlaceOrder;
    private boolean unfoldedVirtualChildrenListDirty;
    private int virtualChildrenCount;
    private MutableVector<DelegatingLayoutNodeWrapper<?>> wrapperCache;
    private float zIndex;
    private boolean zSortedChildrenInvalidated;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final NoIntrinsicsMeasurePolicy ErrorMeasurePolicy = new NoIntrinsicsMeasurePolicy() { // from class: androidx.compose.ui.node.LayoutNode$Companion$ErrorMeasurePolicy$1
        @Override // androidx.compose.ui.layout.MeasurePolicy
        /* renamed from: measure-3p2s80s */
        public /* bridge */ /* synthetic */ MeasureResult mo1949measure3p2s80s(MeasureScope measureScope, List list, long j) {
            m2026measure3p2s80s(measureScope, (List<? extends Measurable>) list, j);
            throw new KotlinNothingValueException();
        }

        /* renamed from: measure-3p2s80s, reason: not valid java name */
        public Void m2026measure3p2s80s(MeasureScope receiver, List<? extends Measurable> measurables, long j) {
            Intrinsics.checkNotNullParameter(receiver, "$receiver");
            Intrinsics.checkNotNullParameter(measurables, "measurables");
            throw new IllegalStateException("Undefined measure and it is required".toString());
        }
    };
    private static final Function0<LayoutNode> Constructor = new Function0<LayoutNode>() { // from class: androidx.compose.ui.node.LayoutNode$Companion$Constructor$1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final LayoutNode invoke() {
            return new LayoutNode();
        }
    };

    /* compiled from: LayoutNode.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0080\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Landroidx/compose/ui/node/LayoutNode$LayoutState;", "", "(Ljava/lang/String;I)V", "NeedsRemeasure", "Measuring", "NeedsRelayout", "LayingOut", "Ready", "ui_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public enum LayoutState {
        NeedsRemeasure,
        Measuring,
        NeedsRelayout,
        LayingOut,
        Ready
    }

    /* compiled from: LayoutNode.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0080\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Landroidx/compose/ui/node/LayoutNode$UsageByParent;", "", "(Ljava/lang/String;I)V", "InMeasureBlock", "InLayoutBlock", "NotUsed", "ui_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public enum UsageByParent {
        InMeasureBlock,
        InLayoutBlock,
        NotUsed
    }

    /* compiled from: LayoutNode.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LayoutState.values().length];
            iArr[LayoutState.NeedsRemeasure.ordinal()] = 1;
            iArr[LayoutState.NeedsRelayout.ordinal()] = 2;
            iArr[LayoutState.Ready.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Deprecated(message = "Temporary API to support ConstraintLayout prototyping.")
    public static /* synthetic */ void getCanMultiMeasure$ui_release$annotations() {
    }

    public static /* synthetic */ void getZSortedChildren$annotations() {
    }

    public LayoutNode() {
        this(false);
    }

    public LayoutNode(boolean z) {
        this._foldedChildren = new MutableVector<>(new LayoutNode[16], 0);
        this.layoutState = LayoutState.Ready;
        this.wrapperCache = new MutableVector<>(new DelegatingLayoutNodeWrapper[16], 0);
        this._zSortedChildren = new MutableVector<>(new LayoutNode[16], 0);
        this.zSortedChildrenInvalidated = true;
        this.measurePolicy = ErrorMeasurePolicy;
        this.intrinsicsPolicy = new IntrinsicsPolicy(this);
        this.density = DensityKt.Density$default(1.0f, 0.0f, 2, null);
        this.measureScope = new LayoutNode$measureScope$1(this);
        this.layoutDirection = LayoutDirection.Ltr;
        this.alignmentLines = new LayoutNodeAlignmentLines(this);
        this.mDrawScope = LayoutNodeKt.getSharedDrawScope();
        this.placeOrder = Integer.MAX_VALUE;
        this.previousPlaceOrder = Integer.MAX_VALUE;
        this.measuredByParent = UsageByParent.NotUsed;
        InnerPlaceable innerPlaceable = new InnerPlaceable(this);
        this.innerLayoutNodeWrapper = innerPlaceable;
        this.outerMeasurablePlaceable = new OuterMeasurablePlaceable(this, innerPlaceable);
        this.innerLayerWrapperIsDirty = true;
        this.modifier = Modifier.INSTANCE;
        this.ZComparator = new Comparator() { // from class: androidx.compose.ui.node.LayoutNode$ZComparator$1
            @Override // java.util.Comparator
            public final int compare(LayoutNode node1, LayoutNode node2) {
                float f;
                float f2;
                float f3;
                float f4;
                Intrinsics.checkNotNullExpressionValue(node1, "node1");
                f = node1.zIndex;
                Intrinsics.checkNotNullExpressionValue(node2, "node2");
                f2 = node2.zIndex;
                if (f != f2) {
                    f3 = node1.zIndex;
                    f4 = node2.zIndex;
                    return Float.compare(f3, f4);
                }
                return Intrinsics.compare(node1.getPlaceOrder(), node2.getPlaceOrder());
            }
        };
        this.isVirtual = z;
    }

    public final List<LayoutNode> getFoldedChildren$ui_release() {
        return this._foldedChildren.asMutableList();
    }

    private final void recreateUnfoldedChildrenIfDirty() {
        if (this.unfoldedVirtualChildrenListDirty) {
            int i = 0;
            this.unfoldedVirtualChildrenListDirty = false;
            MutableVector<LayoutNode> mutableVector = this._unfoldedChildren;
            if (mutableVector == null) {
                mutableVector = new MutableVector<>(new LayoutNode[16], 0);
                this._unfoldedChildren = mutableVector;
            }
            mutableVector.clear();
            MutableVector<LayoutNode> mutableVector2 = this._foldedChildren;
            int size = mutableVector2.getSize();
            if (size > 0) {
                LayoutNode[] content = mutableVector2.getContent();
                do {
                    LayoutNode layoutNode = content[i];
                    if (layoutNode.isVirtual) {
                        mutableVector.addAll(mutableVector.getSize(), layoutNode.get_children$ui_release());
                    } else {
                        mutableVector.add(layoutNode);
                    }
                    i++;
                } while (i < size);
            }
        }
    }

    private final void invalidateUnfoldedVirtualChildren() {
        LayoutNode parent$ui_release;
        if (this.virtualChildrenCount > 0) {
            this.unfoldedVirtualChildrenListDirty = true;
        }
        if (!this.isVirtual || (parent$ui_release = getParent$ui_release()) == null) {
            return;
        }
        parent$ui_release.unfoldedVirtualChildrenListDirty = true;
    }

    public final MutableVector<LayoutNode> get_children$ui_release() {
        if (this.virtualChildrenCount == 0) {
            return this._foldedChildren;
        }
        recreateUnfoldedChildrenIfDirty();
        MutableVector<LayoutNode> mutableVector = this._unfoldedChildren;
        Intrinsics.checkNotNull(mutableVector);
        return mutableVector;
    }

    public final List<LayoutNode> getChildren$ui_release() {
        return get_children$ui_release().asMutableList();
    }

    public final LayoutNode getParent$ui_release() {
        LayoutNode layoutNode = this._foldedParent;
        if (layoutNode == null || !layoutNode.isVirtual) {
            return layoutNode;
        }
        if (layoutNode == null) {
            return null;
        }
        return layoutNode.getParent$ui_release();
    }

    /* renamed from: getOwner$ui_release, reason: from getter */
    public final Owner getOwner() {
        return this.owner;
    }

    @Override // androidx.compose.ui.layout.LayoutInfo
    public boolean isAttached() {
        return this.owner != null;
    }

    /* renamed from: getDepth$ui_release, reason: from getter */
    public final int getDepth() {
        return this.depth;
    }

    public final void setDepth$ui_release(int i) {
        this.depth = i;
    }

    /* renamed from: getLayoutState$ui_release, reason: from getter */
    public final LayoutState getLayoutState() {
        return this.layoutState;
    }

    public final void setLayoutState$ui_release(LayoutState layoutState) {
        Intrinsics.checkNotNullParameter(layoutState, "<set-?>");
        this.layoutState = layoutState;
    }

    public final boolean getWasMeasuredDuringThisIteration$ui_release() {
        return LayoutNodeKt.requireOwner(this).getMeasureIteration() == this.outerMeasurablePlaceable.getMeasureIteration();
    }

    public final void insertAt$ui_release(int index, LayoutNode instance) {
        Intrinsics.checkNotNullParameter(instance, "instance");
        if (!(instance._foldedParent == null)) {
            StringBuilder append = new StringBuilder("Cannot insert ").append(instance).append(" because it already has a parent. This tree: ").append(debugTreeToString$default(this, 0, 1, null)).append(" Other tree: ");
            LayoutNode layoutNode = instance._foldedParent;
            throw new IllegalStateException(append.append((Object) (layoutNode != null ? debugTreeToString$default(layoutNode, 0, 1, null) : null)).toString().toString());
        }
        if (!(instance.owner == null)) {
            throw new IllegalStateException(("Cannot insert " + instance + " because it already has an owner. This tree: " + debugTreeToString$default(this, 0, 1, null) + " Other tree: " + debugTreeToString$default(instance, 0, 1, null)).toString());
        }
        instance._foldedParent = this;
        this._foldedChildren.add(index, instance);
        onZSortedChildrenInvalidated();
        if (instance.isVirtual) {
            if (this.isVirtual) {
                throw new IllegalArgumentException("Virtual LayoutNode can't be added into a virtual parent".toString());
            }
            this.virtualChildrenCount++;
        }
        invalidateUnfoldedVirtualChildren();
        instance.getOuterLayoutNodeWrapper$ui_release().setWrappedBy$ui_release(this.innerLayoutNodeWrapper);
        Owner owner = this.owner;
        if (owner != null) {
            instance.attach$ui_release(owner);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onZSortedChildrenInvalidated() {
        if (this.isVirtual) {
            LayoutNode parent$ui_release = getParent$ui_release();
            if (parent$ui_release == null) {
                return;
            }
            parent$ui_release.onZSortedChildrenInvalidated();
            return;
        }
        this.zSortedChildrenInvalidated = true;
    }

    public final void removeAt$ui_release(int index, int count) {
        if (!(count >= 0)) {
            throw new IllegalArgumentException(("count (" + count + ") must be greater than 0").toString());
        }
        boolean z = this.owner != null;
        int i = (count + index) - 1;
        if (index > i) {
            return;
        }
        while (true) {
            int i2 = i - 1;
            LayoutNode removeAt = this._foldedChildren.removeAt(i);
            onZSortedChildrenInvalidated();
            if (z) {
                removeAt.detach$ui_release();
            }
            removeAt._foldedParent = null;
            if (removeAt.isVirtual) {
                this.virtualChildrenCount--;
            }
            invalidateUnfoldedVirtualChildren();
            if (i == index) {
                return;
            } else {
                i = i2;
            }
        }
    }

    public final void removeAll$ui_release() {
        boolean z = this.owner != null;
        int size = this._foldedChildren.getSize() - 1;
        if (size >= 0) {
            while (true) {
                int i = size - 1;
                LayoutNode layoutNode = this._foldedChildren.getContent()[size];
                if (z) {
                    layoutNode.detach$ui_release();
                }
                layoutNode._foldedParent = null;
                if (i < 0) {
                    break;
                } else {
                    size = i;
                }
            }
        }
        this._foldedChildren.clear();
        onZSortedChildrenInvalidated();
        this.virtualChildrenCount = 0;
        invalidateUnfoldedVirtualChildren();
    }

    public final void move$ui_release(int from, int to, int count) {
        if (from == to) {
            return;
        }
        if (count > 0) {
            int i = 0;
            while (true) {
                int i2 = i + 1;
                this._foldedChildren.add(from > to ? i + to : (to + count) - 2, this._foldedChildren.removeAt(from > to ? from + i : from));
                if (i2 >= count) {
                    break;
                } else {
                    i = i2;
                }
            }
        }
        onZSortedChildrenInvalidated();
        invalidateUnfoldedVirtualChildren();
        requestRemeasure$ui_release();
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0079  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void attach$ui_release(androidx.compose.ui.node.Owner r7) {
        /*
            Method dump skipped, instructions count: 263
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.node.LayoutNode.attach$ui_release(androidx.compose.ui.node.Owner):void");
    }

    public final void detach$ui_release() {
        Owner owner = this.owner;
        if (owner == null) {
            LayoutNode parent$ui_release = getParent$ui_release();
            throw new IllegalStateException(Intrinsics.stringPlus("Cannot detach node that is already detached!  Tree: ", parent$ui_release != null ? debugTreeToString$default(parent$ui_release, 0, 1, null) : null).toString());
        }
        LayoutNode parent$ui_release2 = getParent$ui_release();
        if (parent$ui_release2 != null) {
            parent$ui_release2.invalidateLayer$ui_release();
            parent$ui_release2.requestRemeasure$ui_release();
        }
        this.alignmentLines.reset$ui_release();
        Function1<? super Owner, Unit> function1 = this.onDetach;
        if (function1 != null) {
            function1.invoke(owner);
        }
        LayoutNodeWrapper outerLayoutNodeWrapper$ui_release = getOuterLayoutNodeWrapper$ui_release();
        LayoutNodeWrapper innerLayoutNodeWrapper = getInnerLayoutNodeWrapper();
        while (!Intrinsics.areEqual(outerLayoutNodeWrapper$ui_release, innerLayoutNodeWrapper)) {
            outerLayoutNodeWrapper$ui_release.detach();
            outerLayoutNodeWrapper$ui_release = outerLayoutNodeWrapper$ui_release.getWrapped();
            Intrinsics.checkNotNull(outerLayoutNodeWrapper$ui_release);
        }
        this.innerLayoutNodeWrapper.detach();
        if (SemanticsNodeKt.getOuterSemantics(this) != null) {
            owner.onSemanticsChange();
        }
        owner.onDetach(this);
        this.owner = null;
        this.depth = 0;
        MutableVector<LayoutNode> mutableVector = this._foldedChildren;
        int size = mutableVector.getSize();
        if (size > 0) {
            LayoutNode[] content = mutableVector.getContent();
            int i = 0;
            do {
                content[i].detach$ui_release();
                i++;
            } while (i < size);
        }
        this.placeOrder = Integer.MAX_VALUE;
        this.previousPlaceOrder = Integer.MAX_VALUE;
        this.isPlaced = false;
    }

    public final MutableVector<LayoutNode> getZSortedChildren() {
        if (this.zSortedChildrenInvalidated) {
            this._zSortedChildren.clear();
            MutableVector<LayoutNode> mutableVector = this._zSortedChildren;
            mutableVector.addAll(mutableVector.getSize(), get_children$ui_release());
            this._zSortedChildren.sortWith(this.ZComparator);
            this.zSortedChildrenInvalidated = false;
        }
        return this._zSortedChildren;
    }

    @Override // androidx.compose.ui.node.OwnerScope
    public boolean isValid() {
        return isAttached();
    }

    public String toString() {
        return JvmActuals_jvmKt.simpleIdentityToString(this, null) + " children: " + getChildren$ui_release().size() + " measurePolicy: " + getMeasurePolicy();
    }

    static /* synthetic */ String debugTreeToString$default(LayoutNode layoutNode, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        return layoutNode.debugTreeToString(i);
    }

    private final String debugTreeToString(int depth) {
        StringBuilder sb = new StringBuilder();
        if (depth > 0) {
            int i = 0;
            do {
                i++;
                sb.append("  ");
            } while (i < depth);
        }
        sb.append("|-");
        sb.append(toString());
        sb.append('\n');
        MutableVector<LayoutNode> mutableVector = get_children$ui_release();
        int size = mutableVector.getSize();
        if (size > 0) {
            LayoutNode[] content = mutableVector.getContent();
            int i2 = 0;
            do {
                sb.append(content[i2].debugTreeToString(depth + 1));
                i2++;
            } while (i2 < size);
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "tree.toString()");
        if (depth != 0) {
            return sb2;
        }
        String substring = sb2.substring(0, sb2.length() - 1);
        Intrinsics.checkNotNullExpressionValue(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return substring;
    }

    /* compiled from: LayoutNode.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b \u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\"\u0010\u0005\u001a\u00020\u0006*\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\"\u0010\r\u001a\u00020\u0006*\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000e\u001a\u00020\fH\u0016J\"\u0010\u000f\u001a\u00020\u0006*\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\"\u0010\u0010\u001a\u00020\u0006*\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000e\u001a\u00020\fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Landroidx/compose/ui/node/LayoutNode$NoIntrinsicsMeasurePolicy;", "Landroidx/compose/ui/layout/MeasurePolicy;", "error", "", "(Ljava/lang/String;)V", "maxIntrinsicHeight", "", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "measurables", "", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "width", "", "maxIntrinsicWidth", "height", "minIntrinsicHeight", "minIntrinsicWidth", "ui_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static abstract class NoIntrinsicsMeasurePolicy implements MeasurePolicy {
        private final String error;

        public NoIntrinsicsMeasurePolicy(String error) {
            Intrinsics.checkNotNullParameter(error, "error");
            this.error = error;
        }

        @Override // androidx.compose.ui.layout.MeasurePolicy
        public /* bridge */ /* synthetic */ int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
            return ((Number) m2027maxIntrinsicHeight(intrinsicMeasureScope, (List<? extends IntrinsicMeasurable>) list, i)).intValue();
        }

        @Override // androidx.compose.ui.layout.MeasurePolicy
        public /* bridge */ /* synthetic */ int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
            return ((Number) m2028maxIntrinsicWidth(intrinsicMeasureScope, (List<? extends IntrinsicMeasurable>) list, i)).intValue();
        }

        @Override // androidx.compose.ui.layout.MeasurePolicy
        public /* bridge */ /* synthetic */ int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
            return ((Number) m2029minIntrinsicHeight(intrinsicMeasureScope, (List<? extends IntrinsicMeasurable>) list, i)).intValue();
        }

        @Override // androidx.compose.ui.layout.MeasurePolicy
        public /* bridge */ /* synthetic */ int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
            return ((Number) m2030minIntrinsicWidth(intrinsicMeasureScope, (List<? extends IntrinsicMeasurable>) list, i)).intValue();
        }

        /* renamed from: minIntrinsicWidth, reason: collision with other method in class */
        public Void m2030minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> measurables, int i) {
            Intrinsics.checkNotNullParameter(intrinsicMeasureScope, "<this>");
            Intrinsics.checkNotNullParameter(measurables, "measurables");
            throw new IllegalStateException(this.error.toString());
        }

        /* renamed from: minIntrinsicHeight, reason: collision with other method in class */
        public Void m2029minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> measurables, int i) {
            Intrinsics.checkNotNullParameter(intrinsicMeasureScope, "<this>");
            Intrinsics.checkNotNullParameter(measurables, "measurables");
            throw new IllegalStateException(this.error.toString());
        }

        /* renamed from: maxIntrinsicWidth, reason: collision with other method in class */
        public Void m2028maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> measurables, int i) {
            Intrinsics.checkNotNullParameter(intrinsicMeasureScope, "<this>");
            Intrinsics.checkNotNullParameter(measurables, "measurables");
            throw new IllegalStateException(this.error.toString());
        }

        /* renamed from: maxIntrinsicHeight, reason: collision with other method in class */
        public Void m2027maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> measurables, int i) {
            Intrinsics.checkNotNullParameter(intrinsicMeasureScope, "<this>");
            Intrinsics.checkNotNullParameter(measurables, "measurables");
            throw new IllegalStateException(this.error.toString());
        }
    }

    @Override // androidx.compose.ui.node.ComposeUiNode
    public MeasurePolicy getMeasurePolicy() {
        return this.measurePolicy;
    }

    @Override // androidx.compose.ui.node.ComposeUiNode
    public void setMeasurePolicy(MeasurePolicy value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (Intrinsics.areEqual(this.measurePolicy, value)) {
            return;
        }
        this.measurePolicy = value;
        this.intrinsicsPolicy.updateFrom(getMeasurePolicy());
        requestRemeasure$ui_release();
    }

    /* renamed from: getIntrinsicsPolicy$ui_release, reason: from getter */
    public final IntrinsicsPolicy getIntrinsicsPolicy() {
        return this.intrinsicsPolicy;
    }

    @Override // androidx.compose.ui.node.ComposeUiNode
    public Density getDensity() {
        return this.density;
    }

    @Override // androidx.compose.ui.node.ComposeUiNode
    public void setDensity(Density value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (Intrinsics.areEqual(this.density, value)) {
            return;
        }
        this.density = value;
        onDensityOrLayoutDirectionChanged();
    }

    /* renamed from: getMeasureScope$ui_release, reason: from getter */
    public final MeasureScope getMeasureScope() {
        return this.measureScope;
    }

    @Override // androidx.compose.ui.node.ComposeUiNode
    public LayoutDirection getLayoutDirection() {
        return this.layoutDirection;
    }

    @Override // androidx.compose.ui.node.ComposeUiNode
    public void setLayoutDirection(LayoutDirection value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (this.layoutDirection != value) {
            this.layoutDirection = value;
            onDensityOrLayoutDirectionChanged();
        }
    }

    private final void onDensityOrLayoutDirectionChanged() {
        requestRemeasure$ui_release();
        LayoutNode parent$ui_release = getParent$ui_release();
        if (parent$ui_release != null) {
            parent$ui_release.invalidateLayer$ui_release();
        }
        invalidateLayers$ui_release();
    }

    @Override // androidx.compose.ui.layout.LayoutInfo
    public int getWidth() {
        return this.outerMeasurablePlaceable.getWidth();
    }

    @Override // androidx.compose.ui.layout.LayoutInfo
    public int getHeight() {
        return this.outerMeasurablePlaceable.getHeight();
    }

    /* renamed from: getAlignmentLines$ui_release, reason: from getter */
    public final LayoutNodeAlignmentLines getAlignmentLines() {
        return this.alignmentLines;
    }

    /* renamed from: getMDrawScope$ui_release, reason: from getter */
    public final LayoutNodeDrawScope getMDrawScope() {
        return this.mDrawScope;
    }

    @Override // androidx.compose.ui.layout.LayoutInfo
    /* renamed from: isPlaced, reason: from getter */
    public boolean getIsPlaced() {
        return this.isPlaced;
    }

    /* renamed from: getPlaceOrder$ui_release, reason: from getter */
    public final int getPlaceOrder() {
        return this.placeOrder;
    }

    /* renamed from: getMeasuredByParent$ui_release, reason: from getter */
    public final UsageByParent getMeasuredByParent() {
        return this.measuredByParent;
    }

    public final void setMeasuredByParent$ui_release(UsageByParent usageByParent) {
        Intrinsics.checkNotNullParameter(usageByParent, "<set-?>");
        this.measuredByParent = usageByParent;
    }

    /* renamed from: getCanMultiMeasure$ui_release, reason: from getter */
    public final boolean getCanMultiMeasure() {
        return this.canMultiMeasure;
    }

    public final void setCanMultiMeasure$ui_release(boolean z) {
        this.canMultiMeasure = z;
    }

    /* renamed from: getInnerLayoutNodeWrapper$ui_release, reason: from getter */
    public final LayoutNodeWrapper getInnerLayoutNodeWrapper() {
        return this.innerLayoutNodeWrapper;
    }

    public final LayoutNodeWrapper getOuterLayoutNodeWrapper$ui_release() {
        return this.outerMeasurablePlaceable.getOuterWrapper();
    }

    /* renamed from: getInnerLayerWrapperIsDirty$ui_release, reason: from getter */
    public final boolean getInnerLayerWrapperIsDirty() {
        return this.innerLayerWrapperIsDirty;
    }

    public final void setInnerLayerWrapperIsDirty$ui_release(boolean z) {
        this.innerLayerWrapperIsDirty = z;
    }

    public final LayoutNodeWrapper getInnerLayerWrapper$ui_release() {
        if (this.innerLayerWrapperIsDirty) {
            LayoutNodeWrapper layoutNodeWrapper = this.innerLayoutNodeWrapper;
            LayoutNodeWrapper wrappedBy = getOuterLayoutNodeWrapper$ui_release().getWrappedBy();
            this._innerLayerWrapper = null;
            while (true) {
                if (Intrinsics.areEqual(layoutNodeWrapper, wrappedBy)) {
                    break;
                }
                if ((layoutNodeWrapper == null ? null : layoutNodeWrapper.getLayer()) != null) {
                    this._innerLayerWrapper = layoutNodeWrapper;
                    break;
                }
                layoutNodeWrapper = layoutNodeWrapper == null ? null : layoutNodeWrapper.getWrappedBy();
            }
        }
        LayoutNodeWrapper layoutNodeWrapper2 = this._innerLayerWrapper;
        if (layoutNodeWrapper2 == null || layoutNodeWrapper2.getLayer() != null) {
            return layoutNodeWrapper2;
        }
        throw new IllegalArgumentException("Required value was null.".toString());
    }

    public final void invalidateLayer$ui_release() {
        LayoutNodeWrapper innerLayerWrapper$ui_release = getInnerLayerWrapper$ui_release();
        if (innerLayerWrapper$ui_release != null) {
            innerLayerWrapper$ui_release.invalidateLayer();
            return;
        }
        LayoutNode parent$ui_release = getParent$ui_release();
        if (parent$ui_release == null) {
            return;
        }
        parent$ui_release.invalidateLayer$ui_release();
    }

    @Override // androidx.compose.ui.node.ComposeUiNode
    public Modifier getModifier() {
        return this.modifier;
    }

    @Override // androidx.compose.ui.node.ComposeUiNode
    public void setModifier(Modifier value) {
        LayoutNode parent$ui_release;
        LayoutNode parent$ui_release2;
        Intrinsics.checkNotNullParameter(value, "value");
        if (Intrinsics.areEqual(value, this.modifier)) {
            return;
        }
        if (!Intrinsics.areEqual(getModifier(), Modifier.INSTANCE) && this.isVirtual) {
            throw new IllegalArgumentException("Modifiers are not supported on virtual LayoutNodes".toString());
        }
        this.modifier = value;
        boolean shouldInvalidateParentLayer = shouldInvalidateParentLayer();
        copyWrappersToCache();
        markReusedModifiers(value);
        LayoutNodeWrapper outerWrapper = this.outerMeasurablePlaceable.getOuterWrapper();
        if (SemanticsNodeKt.getOuterSemantics(this) != null && isAttached()) {
            Owner owner = this.owner;
            Intrinsics.checkNotNull(owner);
            owner.onSemanticsChange();
        }
        boolean hasNewPositioningCallback = hasNewPositioningCallback();
        MutableVector<OnGloballyPositionedModifierWrapper> mutableVector = this.onPositionedCallbacks;
        if (mutableVector != null) {
            mutableVector.clear();
        }
        LayoutNodeWrapper layoutNodeWrapper = (LayoutNodeWrapper) getModifier().foldOut(this.innerLayoutNodeWrapper, new Function2<Modifier.Element, LayoutNodeWrapper, LayoutNodeWrapper>() { // from class: androidx.compose.ui.node.LayoutNode$modifier$outerWrapper$1
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public final LayoutNodeWrapper invoke(Modifier.Element mod, LayoutNodeWrapper toWrap) {
                DelegatingLayoutNodeWrapper reuseLayoutNodeWrapper;
                MutableVector orCreateOnPositionedCallbacks;
                MutableVector orCreateOnPositionedCallbacks2;
                Intrinsics.checkNotNullParameter(mod, "mod");
                Intrinsics.checkNotNullParameter(toWrap, "toWrap");
                if (mod instanceof RemeasurementModifier) {
                    ((RemeasurementModifier) mod).onRemeasurementAvailable(LayoutNode.this);
                }
                reuseLayoutNodeWrapper = LayoutNode.this.reuseLayoutNodeWrapper(mod, toWrap);
                if (reuseLayoutNodeWrapper != null) {
                    if (reuseLayoutNodeWrapper instanceof OnGloballyPositionedModifierWrapper) {
                        orCreateOnPositionedCallbacks2 = LayoutNode.this.getOrCreateOnPositionedCallbacks();
                        orCreateOnPositionedCallbacks2.add(reuseLayoutNodeWrapper);
                    }
                    return reuseLayoutNodeWrapper;
                }
                RemeasureModifierWrapper modifiedDrawNode = mod instanceof DrawModifier ? new ModifiedDrawNode(toWrap, (DrawModifier) mod) : toWrap;
                if (mod instanceof FocusModifier) {
                    ModifiedFocusNode modifiedFocusNode = new ModifiedFocusNode(modifiedDrawNode, (FocusModifier) mod);
                    if (toWrap != modifiedFocusNode.getWrapped()) {
                        ((DelegatingLayoutNodeWrapper) modifiedFocusNode.getWrapped()).setChained(true);
                    }
                    modifiedDrawNode = modifiedFocusNode;
                }
                if (mod instanceof FocusEventModifier) {
                    ModifiedFocusEventNode modifiedFocusEventNode = new ModifiedFocusEventNode(modifiedDrawNode, (FocusEventModifier) mod);
                    if (toWrap != modifiedFocusEventNode.getWrapped()) {
                        ((DelegatingLayoutNodeWrapper) modifiedFocusEventNode.getWrapped()).setChained(true);
                    }
                    modifiedDrawNode = modifiedFocusEventNode;
                }
                if (mod instanceof FocusRequesterModifier) {
                    ModifiedFocusRequesterNode modifiedFocusRequesterNode = new ModifiedFocusRequesterNode(modifiedDrawNode, (FocusRequesterModifier) mod);
                    if (toWrap != modifiedFocusRequesterNode.getWrapped()) {
                        ((DelegatingLayoutNodeWrapper) modifiedFocusRequesterNode.getWrapped()).setChained(true);
                    }
                    modifiedDrawNode = modifiedFocusRequesterNode;
                }
                if (mod instanceof FocusOrderModifier) {
                    ModifiedFocusOrderNode modifiedFocusOrderNode = new ModifiedFocusOrderNode(modifiedDrawNode, (FocusOrderModifier) mod);
                    if (toWrap != modifiedFocusOrderNode.getWrapped()) {
                        ((DelegatingLayoutNodeWrapper) modifiedFocusOrderNode.getWrapped()).setChained(true);
                    }
                    modifiedDrawNode = modifiedFocusOrderNode;
                }
                if (mod instanceof KeyInputModifier) {
                    ModifiedKeyInputNode modifiedKeyInputNode = new ModifiedKeyInputNode(modifiedDrawNode, (KeyInputModifier) mod);
                    if (toWrap != modifiedKeyInputNode.getWrapped()) {
                        ((DelegatingLayoutNodeWrapper) modifiedKeyInputNode.getWrapped()).setChained(true);
                    }
                    modifiedDrawNode = modifiedKeyInputNode;
                }
                if (mod instanceof PointerInputModifier) {
                    PointerInputDelegatingWrapper pointerInputDelegatingWrapper = new PointerInputDelegatingWrapper(modifiedDrawNode, (PointerInputModifier) mod);
                    if (toWrap != pointerInputDelegatingWrapper.getWrapped()) {
                        ((DelegatingLayoutNodeWrapper) pointerInputDelegatingWrapper.getWrapped()).setChained(true);
                    }
                    modifiedDrawNode = pointerInputDelegatingWrapper;
                }
                if (mod instanceof NestedScrollModifier) {
                    NestedScrollDelegatingWrapper nestedScrollDelegatingWrapper = new NestedScrollDelegatingWrapper(modifiedDrawNode, (NestedScrollModifier) mod);
                    if (toWrap != nestedScrollDelegatingWrapper.getWrapped()) {
                        ((DelegatingLayoutNodeWrapper) nestedScrollDelegatingWrapper.getWrapped()).setChained(true);
                    }
                    modifiedDrawNode = nestedScrollDelegatingWrapper;
                }
                if (mod instanceof LayoutModifier) {
                    ModifiedLayoutNode modifiedLayoutNode = new ModifiedLayoutNode(modifiedDrawNode, (LayoutModifier) mod);
                    if (toWrap != modifiedLayoutNode.getWrapped()) {
                        ((DelegatingLayoutNodeWrapper) modifiedLayoutNode.getWrapped()).setChained(true);
                    }
                    modifiedDrawNode = modifiedLayoutNode;
                }
                if (mod instanceof ParentDataModifier) {
                    ModifiedParentDataNode modifiedParentDataNode = new ModifiedParentDataNode(modifiedDrawNode, (ParentDataModifier) mod);
                    if (toWrap != modifiedParentDataNode.getWrapped()) {
                        ((DelegatingLayoutNodeWrapper) modifiedParentDataNode.getWrapped()).setChained(true);
                    }
                    modifiedDrawNode = modifiedParentDataNode;
                }
                if (mod instanceof SemanticsModifier) {
                    SemanticsWrapper semanticsWrapper = new SemanticsWrapper(modifiedDrawNode, (SemanticsModifier) mod);
                    if (toWrap != semanticsWrapper.getWrapped()) {
                        ((DelegatingLayoutNodeWrapper) semanticsWrapper.getWrapped()).setChained(true);
                    }
                    modifiedDrawNode = semanticsWrapper;
                }
                if (mod instanceof OnRemeasuredModifier) {
                    RemeasureModifierWrapper remeasureModifierWrapper = new RemeasureModifierWrapper(modifiedDrawNode, (OnRemeasuredModifier) mod);
                    if (toWrap != remeasureModifierWrapper.getWrapped()) {
                        ((DelegatingLayoutNodeWrapper) remeasureModifierWrapper.getWrapped()).setChained(true);
                    }
                    modifiedDrawNode = remeasureModifierWrapper;
                }
                if (!(mod instanceof OnGloballyPositionedModifier)) {
                    return modifiedDrawNode;
                }
                OnGloballyPositionedModifierWrapper onGloballyPositionedModifierWrapper = new OnGloballyPositionedModifierWrapper(modifiedDrawNode, (OnGloballyPositionedModifier) mod);
                if (toWrap != onGloballyPositionedModifierWrapper.getWrapped()) {
                    ((DelegatingLayoutNodeWrapper) onGloballyPositionedModifierWrapper.getWrapped()).setChained(true);
                }
                OnGloballyPositionedModifierWrapper onGloballyPositionedModifierWrapper2 = onGloballyPositionedModifierWrapper;
                orCreateOnPositionedCallbacks = LayoutNode.this.getOrCreateOnPositionedCallbacks();
                orCreateOnPositionedCallbacks.add(onGloballyPositionedModifierWrapper2);
                return onGloballyPositionedModifierWrapper2;
            }
        });
        LayoutNode parent$ui_release3 = getParent$ui_release();
        layoutNodeWrapper.setWrappedBy$ui_release(parent$ui_release3 == null ? null : parent$ui_release3.innerLayoutNodeWrapper);
        this.outerMeasurablePlaceable.setOuterWrapper(layoutNodeWrapper);
        if (isAttached()) {
            MutableVector<DelegatingLayoutNodeWrapper<?>> mutableVector2 = this.wrapperCache;
            int size = mutableVector2.getSize();
            if (size > 0) {
                DelegatingLayoutNodeWrapper<?>[] content = mutableVector2.getContent();
                int i = 0;
                do {
                    content[i].detach();
                    i++;
                } while (i < size);
            }
            LayoutNodeWrapper outerLayoutNodeWrapper$ui_release = getOuterLayoutNodeWrapper$ui_release();
            LayoutNodeWrapper innerLayoutNodeWrapper = getInnerLayoutNodeWrapper();
            while (!Intrinsics.areEqual(outerLayoutNodeWrapper$ui_release, innerLayoutNodeWrapper)) {
                if (!outerLayoutNodeWrapper$ui_release.isAttached()) {
                    outerLayoutNodeWrapper$ui_release.attach();
                }
                outerLayoutNodeWrapper$ui_release = outerLayoutNodeWrapper$ui_release.getWrapped();
                Intrinsics.checkNotNull(outerLayoutNodeWrapper$ui_release);
            }
        }
        this.wrapperCache.clear();
        LayoutNodeWrapper outerLayoutNodeWrapper$ui_release2 = getOuterLayoutNodeWrapper$ui_release();
        LayoutNodeWrapper innerLayoutNodeWrapper2 = getInnerLayoutNodeWrapper();
        while (!Intrinsics.areEqual(outerLayoutNodeWrapper$ui_release2, innerLayoutNodeWrapper2)) {
            outerLayoutNodeWrapper$ui_release2.onModifierChanged();
            outerLayoutNodeWrapper$ui_release2 = outerLayoutNodeWrapper$ui_release2.getWrapped();
            Intrinsics.checkNotNull(outerLayoutNodeWrapper$ui_release2);
        }
        if (!Intrinsics.areEqual(outerWrapper, this.innerLayoutNodeWrapper) || !Intrinsics.areEqual(layoutNodeWrapper, this.innerLayoutNodeWrapper)) {
            requestRemeasure$ui_release();
            LayoutNode parent$ui_release4 = getParent$ui_release();
            if (parent$ui_release4 != null) {
                parent$ui_release4.requestRelayout$ui_release();
            }
        } else if (this.layoutState == LayoutState.Ready && hasNewPositioningCallback) {
            requestRemeasure$ui_release();
        }
        Object parentData = getParentData();
        this.outerMeasurablePlaceable.recalculateParentData();
        if (!Intrinsics.areEqual(parentData, getParentData()) && (parent$ui_release2 = getParent$ui_release()) != null) {
            parent$ui_release2.requestRemeasure$ui_release();
        }
        if ((shouldInvalidateParentLayer || shouldInvalidateParentLayer()) && (parent$ui_release = getParent$ui_release()) != null) {
            parent$ui_release.invalidateLayer$ui_release();
        }
    }

    @Override // androidx.compose.ui.layout.LayoutInfo
    public LayoutCoordinates getCoordinates() {
        return this.innerLayoutNodeWrapper;
    }

    public final Function1<Owner, Unit> getOnAttach$ui_release() {
        return this.onAttach;
    }

    public final void setOnAttach$ui_release(Function1<? super Owner, Unit> function1) {
        this.onAttach = function1;
    }

    public final Function1<Owner, Unit> getOnDetach$ui_release() {
        return this.onDetach;
    }

    public final void setOnDetach$ui_release(Function1<? super Owner, Unit> function1) {
        this.onDetach = function1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MutableVector<OnGloballyPositionedModifierWrapper> getOrCreateOnPositionedCallbacks() {
        MutableVector<OnGloballyPositionedModifierWrapper> mutableVector = this.onPositionedCallbacks;
        if (mutableVector != null) {
            return mutableVector;
        }
        MutableVector<OnGloballyPositionedModifierWrapper> mutableVector2 = new MutableVector<>(new OnGloballyPositionedModifierWrapper[16], 0);
        this.onPositionedCallbacks = mutableVector2;
        return mutableVector2;
    }

    /* renamed from: getNeedsOnPositionedDispatch$ui_release, reason: from getter */
    public final boolean getNeedsOnPositionedDispatch() {
        return this.needsOnPositionedDispatch;
    }

    public final void setNeedsOnPositionedDispatch$ui_release(boolean z) {
        this.needsOnPositionedDispatch = z;
    }

    public final void place$ui_release(int x, int y) {
        Placeable.PlacementScope.Companion companion = Placeable.PlacementScope.INSTANCE;
        int measuredWidth = this.outerMeasurablePlaceable.getMeasuredWidth();
        LayoutDirection layoutDirection = getLayoutDirection();
        int parentWidth = Placeable.PlacementScope.INSTANCE.getParentWidth();
        LayoutDirection parentLayoutDirection = Placeable.PlacementScope.INSTANCE.getParentLayoutDirection();
        Placeable.PlacementScope.Companion companion2 = Placeable.PlacementScope.INSTANCE;
        Placeable.PlacementScope.parentWidth = measuredWidth;
        Placeable.PlacementScope.Companion companion3 = Placeable.PlacementScope.INSTANCE;
        Placeable.PlacementScope.parentLayoutDirection = layoutDirection;
        Placeable.PlacementScope.placeRelative$default(companion, this.outerMeasurablePlaceable, x, y, 0.0f, 4, null);
        Placeable.PlacementScope.Companion companion4 = Placeable.PlacementScope.INSTANCE;
        Placeable.PlacementScope.parentWidth = parentWidth;
        Placeable.PlacementScope.Companion companion5 = Placeable.PlacementScope.INSTANCE;
        Placeable.PlacementScope.parentLayoutDirection = parentLayoutDirection;
    }

    public final void replace$ui_release() {
        this.outerMeasurablePlaceable.replace();
    }

    public final void draw$ui_release(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        getOuterLayoutNodeWrapper$ui_release().draw(canvas);
    }

    /* renamed from: hitTest-3MmeM6k$ui_release, reason: not valid java name */
    public final void m2023hitTest3MmeM6k$ui_release(long pointerPosition, List<PointerInputFilter> hitPointerInputFilters) {
        Intrinsics.checkNotNullParameter(hitPointerInputFilters, "hitPointerInputFilters");
        getOuterLayoutNodeWrapper$ui_release().mo2018hitTest3MmeM6k(getOuterLayoutNodeWrapper$ui_release().m2035fromParentPositionMKHz9U(pointerPosition), hitPointerInputFilters);
    }

    /* renamed from: hitTestSemantics-3MmeM6k$ui_release, reason: not valid java name */
    public final void m2024hitTestSemantics3MmeM6k$ui_release(long pointerPosition, List<SemanticsWrapper> hitSemanticsWrappers) {
        Intrinsics.checkNotNullParameter(hitSemanticsWrappers, "hitSemanticsWrappers");
        getOuterLayoutNodeWrapper$ui_release().mo2019hitTestSemantics3MmeM6k(getOuterLayoutNodeWrapper$ui_release().m2035fromParentPositionMKHz9U(pointerPosition), hitSemanticsWrappers);
    }

    private final boolean hasNewPositioningCallback() {
        final MutableVector<OnGloballyPositionedModifierWrapper> mutableVector = this.onPositionedCallbacks;
        return ((Boolean) getModifier().foldOut(false, new Function2<Modifier.Element, Boolean, Boolean>() { // from class: androidx.compose.ui.node.LayoutNode$hasNewPositioningCallback$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Boolean invoke(Modifier.Element element, Boolean bool) {
                return Boolean.valueOf(invoke(element, bool.booleanValue()));
            }

            public final boolean invoke(Modifier.Element mod, boolean z) {
                Intrinsics.checkNotNullParameter(mod, "mod");
                if (z) {
                    return true;
                }
                if (mod instanceof OnGloballyPositionedModifier) {
                    MutableVector<OnGloballyPositionedModifierWrapper> mutableVector2 = mutableVector;
                    OnGloballyPositionedModifierWrapper onGloballyPositionedModifierWrapper = null;
                    if (mutableVector2 != null) {
                        int size = mutableVector2.getSize();
                        if (size > 0) {
                            OnGloballyPositionedModifierWrapper[] content = mutableVector2.getContent();
                            int i = 0;
                            while (true) {
                                OnGloballyPositionedModifierWrapper onGloballyPositionedModifierWrapper2 = content[i];
                                if (Intrinsics.areEqual(mod, onGloballyPositionedModifierWrapper2.getModifier())) {
                                    onGloballyPositionedModifierWrapper = onGloballyPositionedModifierWrapper2;
                                    break;
                                }
                                i++;
                                if (i >= size) {
                                    break;
                                }
                            }
                        }
                        onGloballyPositionedModifierWrapper = onGloballyPositionedModifierWrapper;
                    }
                    if (onGloballyPositionedModifierWrapper == null) {
                        return true;
                    }
                }
                return false;
            }
        })).booleanValue();
    }

    public final void onNodePlaced$ui_release() {
        LayoutNode parent$ui_release = getParent$ui_release();
        float zIndex = this.innerLayoutNodeWrapper.getZIndex();
        LayoutNodeWrapper outerLayoutNodeWrapper$ui_release = getOuterLayoutNodeWrapper$ui_release();
        LayoutNodeWrapper innerLayoutNodeWrapper = getInnerLayoutNodeWrapper();
        while (!Intrinsics.areEqual(outerLayoutNodeWrapper$ui_release, innerLayoutNodeWrapper)) {
            zIndex += outerLayoutNodeWrapper$ui_release.getZIndex();
            outerLayoutNodeWrapper$ui_release = outerLayoutNodeWrapper$ui_release.getWrapped();
            Intrinsics.checkNotNull(outerLayoutNodeWrapper$ui_release);
        }
        if (zIndex != this.zIndex) {
            this.zIndex = zIndex;
            if (parent$ui_release != null) {
                parent$ui_release.onZSortedChildrenInvalidated();
            }
            if (parent$ui_release != null) {
                parent$ui_release.invalidateLayer$ui_release();
            }
        }
        if (!getIsPlaced()) {
            if (parent$ui_release != null) {
                parent$ui_release.invalidateLayer$ui_release();
            }
            markNodeAndSubtreeAsPlaced();
        }
        if (parent$ui_release != null) {
            if (parent$ui_release.layoutState == LayoutState.LayingOut) {
                if (!(this.placeOrder == Integer.MAX_VALUE)) {
                    throw new IllegalStateException("Place was called on a node which was placed already".toString());
                }
                int i = parent$ui_release.nextChildPlaceOrder;
                this.placeOrder = i;
                parent$ui_release.nextChildPlaceOrder = i + 1;
            }
        } else {
            this.placeOrder = 0;
        }
        layoutChildren$ui_release();
    }

    public final void layoutChildren$ui_release() {
        this.alignmentLines.recalculateQueryOwner$ui_release();
        if (this.layoutState == LayoutState.NeedsRelayout) {
            onBeforeLayoutChildren();
        }
        if (this.layoutState == LayoutState.NeedsRelayout) {
            this.layoutState = LayoutState.LayingOut;
            LayoutNodeKt.requireOwner(this).getSnapshotObserver().observeLayoutSnapshotReads$ui_release(this, new Function0<Unit>() { // from class: androidx.compose.ui.node.LayoutNode$layoutChildren$1
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
                    int i;
                    int i2 = 0;
                    LayoutNode.this.nextChildPlaceOrder = 0;
                    MutableVector<LayoutNode> mutableVector = LayoutNode.this.get_children$ui_release();
                    int size = mutableVector.getSize();
                    if (size > 0) {
                        LayoutNode[] content = mutableVector.getContent();
                        int i3 = 0;
                        do {
                            LayoutNode layoutNode = content[i3];
                            layoutNode.previousPlaceOrder = layoutNode.getPlaceOrder();
                            layoutNode.placeOrder = Integer.MAX_VALUE;
                            layoutNode.getAlignmentLines().setUsedDuringParentLayout$ui_release(false);
                            i3++;
                        } while (i3 < size);
                    }
                    LayoutNode.this.getInnerLayoutNodeWrapper().getMeasureResult().placeChildren();
                    MutableVector<LayoutNode> mutableVector2 = LayoutNode.this.get_children$ui_release();
                    LayoutNode layoutNode2 = LayoutNode.this;
                    int size2 = mutableVector2.getSize();
                    if (size2 > 0) {
                        LayoutNode[] content2 = mutableVector2.getContent();
                        do {
                            LayoutNode layoutNode3 = content2[i2];
                            i = layoutNode3.previousPlaceOrder;
                            if (i != layoutNode3.getPlaceOrder()) {
                                layoutNode2.onZSortedChildrenInvalidated();
                                layoutNode2.invalidateLayer$ui_release();
                                if (layoutNode3.getPlaceOrder() == Integer.MAX_VALUE) {
                                    layoutNode3.markSubtreeAsNotPlaced();
                                }
                            }
                            layoutNode3.getAlignmentLines().setPreviousUsedDuringParentLayout$ui_release(layoutNode3.getAlignmentLines().getUsedDuringParentLayout());
                            i2++;
                        } while (i2 < size2);
                    }
                }
            });
            this.layoutState = LayoutState.Ready;
        }
        if (this.alignmentLines.getUsedDuringParentLayout()) {
            this.alignmentLines.setPreviousUsedDuringParentLayout$ui_release(true);
        }
        if (this.alignmentLines.getDirty() && this.alignmentLines.getRequired$ui_release()) {
            this.alignmentLines.recalculate();
        }
    }

    private final void markNodeAndSubtreeAsPlaced() {
        this.isPlaced = true;
        LayoutNodeWrapper wrapped = getInnerLayoutNodeWrapper().getWrapped();
        for (LayoutNodeWrapper outerLayoutNodeWrapper$ui_release = getOuterLayoutNodeWrapper$ui_release(); !Intrinsics.areEqual(outerLayoutNodeWrapper$ui_release, wrapped) && outerLayoutNodeWrapper$ui_release != null; outerLayoutNodeWrapper$ui_release = outerLayoutNodeWrapper$ui_release.getWrapped()) {
            if (outerLayoutNodeWrapper$ui_release.getLastLayerDrawingWasSkipped()) {
                outerLayoutNodeWrapper$ui_release.invalidateLayer();
            }
        }
        MutableVector<LayoutNode> mutableVector = get_children$ui_release();
        int size = mutableVector.getSize();
        if (size > 0) {
            LayoutNode[] content = mutableVector.getContent();
            int i = 0;
            do {
                LayoutNode layoutNode = content[i];
                if (layoutNode.getPlaceOrder() != Integer.MAX_VALUE) {
                    layoutNode.markNodeAndSubtreeAsPlaced();
                    rescheduleRemeasureOrRelayout(layoutNode);
                }
                i++;
            } while (i < size);
        }
    }

    private final void rescheduleRemeasureOrRelayout(LayoutNode it) {
        int i = WhenMappings.$EnumSwitchMapping$0[it.layoutState.ordinal()];
        if (i != 1 && i != 2) {
            if (i != 3) {
                throw new IllegalStateException(Intrinsics.stringPlus("Unexpected state ", it.layoutState));
            }
            return;
        }
        it.layoutState = LayoutState.Ready;
        if (i == 1) {
            it.requestRemeasure$ui_release();
        } else {
            it.requestRelayout$ui_release();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void markSubtreeAsNotPlaced() {
        if (getIsPlaced()) {
            int i = 0;
            this.isPlaced = false;
            MutableVector<LayoutNode> mutableVector = get_children$ui_release();
            int size = mutableVector.getSize();
            if (size > 0) {
                LayoutNode[] content = mutableVector.getContent();
                do {
                    content[i].markSubtreeAsNotPlaced();
                    i++;
                } while (i < size);
            }
        }
    }

    private final void onBeforeLayoutChildren() {
        MutableVector<LayoutNode> mutableVector = get_children$ui_release();
        int size = mutableVector.getSize();
        if (size > 0) {
            LayoutNode[] content = mutableVector.getContent();
            int i = 0;
            do {
                LayoutNode layoutNode = content[i];
                if (layoutNode.getLayoutState() == LayoutState.NeedsRemeasure && layoutNode.getMeasuredByParent() == UsageByParent.InMeasureBlock && m2022remeasure_Sx5XlM$ui_release$default(layoutNode, null, 1, null)) {
                    requestRemeasure$ui_release();
                }
                i++;
            } while (i < size);
        }
    }

    public final void onAlignmentsChanged$ui_release() {
        if (this.alignmentLines.getDirty()) {
            return;
        }
        this.alignmentLines.setDirty$ui_release(true);
        LayoutNode parent$ui_release = getParent$ui_release();
        if (parent$ui_release == null) {
            return;
        }
        if (this.alignmentLines.getUsedDuringParentMeasurement()) {
            parent$ui_release.requestRemeasure$ui_release();
        } else if (this.alignmentLines.getPreviousUsedDuringParentLayout()) {
            parent$ui_release.requestRelayout$ui_release();
        }
        if (this.alignmentLines.getUsedByModifierMeasurement()) {
            requestRemeasure$ui_release();
        }
        if (this.alignmentLines.getUsedByModifierLayout()) {
            parent$ui_release.requestRelayout$ui_release();
        }
        parent$ui_release.onAlignmentsChanged$ui_release();
    }

    public final Map<AlignmentLine, Integer> calculateAlignmentLines$ui_release() {
        if (!this.outerMeasurablePlaceable.getDuringAlignmentLinesQuery()) {
            alignmentLinesQueriedByModifier();
        }
        layoutChildren$ui_release();
        return this.alignmentLines.getLastCalculation();
    }

    private final void alignmentLinesQueriedByModifier() {
        if (this.layoutState == LayoutState.Measuring) {
            this.alignmentLines.setUsedByModifierMeasurement$ui_release(true);
            if (this.alignmentLines.getDirty()) {
                this.layoutState = LayoutState.NeedsRelayout;
                return;
            }
            return;
        }
        this.alignmentLines.setUsedByModifierLayout$ui_release(true);
    }

    public final void handleMeasureResult$ui_release(MeasureResult measureResult) {
        Intrinsics.checkNotNullParameter(measureResult, "measureResult");
        this.innerLayoutNodeWrapper.setMeasureResult$ui_release(measureResult);
    }

    public final void requestRemeasure$ui_release() {
        Owner owner = this.owner;
        if (owner == null || this.ignoreRemeasureRequests || this.isVirtual) {
            return;
        }
        owner.onRequestMeasure(this);
    }

    public final void ignoreRemeasureRequests$ui_release(Function0<Unit> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        this.ignoreRemeasureRequests = true;
        block.invoke();
        this.ignoreRemeasureRequests = false;
    }

    public final void requestRelayout$ui_release() {
        Owner owner;
        if (this.isVirtual || (owner = this.owner) == null) {
            return;
        }
        owner.onRequestRelayout(this);
    }

    public final void withNoSnapshotReadObservation$ui_release(Function0<Unit> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        LayoutNodeKt.requireOwner(this).getSnapshotObserver().withNoSnapshotReadObservation$ui_release(block);
    }

    public final void dispatchOnPositionedCallbacks$ui_release() {
        MutableVector<OnGloballyPositionedModifierWrapper> mutableVector;
        int size;
        if (this.layoutState == LayoutState.Ready && getIsPlaced() && (mutableVector = this.onPositionedCallbacks) != null && (size = mutableVector.getSize()) > 0) {
            OnGloballyPositionedModifierWrapper[] content = mutableVector.getContent();
            int i = 0;
            do {
                OnGloballyPositionedModifierWrapper onGloballyPositionedModifierWrapper = content[i];
                onGloballyPositionedModifierWrapper.getModifier().onGloballyPositioned(onGloballyPositionedModifierWrapper);
                i++;
            } while (i < size);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DelegatingLayoutNodeWrapper<?> reuseLayoutNodeWrapper(Modifier.Element modifier, LayoutNodeWrapper wrapper) {
        int i;
        if (this.wrapperCache.isEmpty()) {
            return null;
        }
        MutableVector<DelegatingLayoutNodeWrapper<?>> mutableVector = this.wrapperCache;
        int size = mutableVector.getSize();
        int i2 = -1;
        if (size > 0) {
            i = size - 1;
            DelegatingLayoutNodeWrapper<?>[] content = mutableVector.getContent();
            do {
                DelegatingLayoutNodeWrapper<?> delegatingLayoutNodeWrapper = content[i];
                if (delegatingLayoutNodeWrapper.getToBeReusedForSameModifier() && delegatingLayoutNodeWrapper.getModifier() == modifier) {
                    break;
                }
                i--;
            } while (i >= 0);
        }
        i = -1;
        if (i < 0) {
            MutableVector<DelegatingLayoutNodeWrapper<?>> mutableVector2 = this.wrapperCache;
            int size2 = mutableVector2.getSize();
            if (size2 > 0) {
                int i3 = size2 - 1;
                DelegatingLayoutNodeWrapper<?>[] content2 = mutableVector2.getContent();
                while (true) {
                    DelegatingLayoutNodeWrapper<?> delegatingLayoutNodeWrapper2 = content2[i3];
                    if (!delegatingLayoutNodeWrapper2.getToBeReusedForSameModifier() && Intrinsics.areEqual(JvmActuals_jvmKt.nativeClass(delegatingLayoutNodeWrapper2.getModifier()), JvmActuals_jvmKt.nativeClass(modifier))) {
                        i2 = i3;
                        break;
                    }
                    i3--;
                    if (i3 < 0) {
                        break;
                    }
                }
            }
            i = i2;
        }
        if (i < 0) {
            return null;
        }
        DelegatingLayoutNodeWrapper<?> delegatingLayoutNodeWrapper3 = this.wrapperCache.getContent()[i];
        delegatingLayoutNodeWrapper3.setModifierTo(modifier);
        DelegatingLayoutNodeWrapper<?> delegatingLayoutNodeWrapper4 = delegatingLayoutNodeWrapper3;
        int i4 = i;
        while (delegatingLayoutNodeWrapper4.getIsChained()) {
            i4--;
            delegatingLayoutNodeWrapper4 = this.wrapperCache.getContent()[i4];
            delegatingLayoutNodeWrapper4.setModifierTo(modifier);
        }
        this.wrapperCache.removeRange(i4, i + 1);
        delegatingLayoutNodeWrapper3.setWrapped(wrapper);
        wrapper.setWrappedBy$ui_release(delegatingLayoutNodeWrapper3);
        return delegatingLayoutNodeWrapper4;
    }

    private final void markReusedModifiers(Modifier modifier) {
        MutableVector<DelegatingLayoutNodeWrapper<?>> mutableVector = this.wrapperCache;
        int size = mutableVector.getSize();
        if (size > 0) {
            DelegatingLayoutNodeWrapper<?>[] content = mutableVector.getContent();
            int i = 0;
            do {
                content[i].setToBeReusedForSameModifier(false);
                i++;
            } while (i < size);
        }
        modifier.foldIn(Unit.INSTANCE, new Function2<Unit, Modifier.Element, Unit>() { // from class: androidx.compose.ui.node.LayoutNode$markReusedModifiers$2
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Unit unit, Modifier.Element element) {
                invoke2(unit, element);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Unit noName_0, Modifier.Element mod) {
                MutableVector mutableVector2;
                Object obj;
                Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
                Intrinsics.checkNotNullParameter(mod, "mod");
                mutableVector2 = LayoutNode.this.wrapperCache;
                int size2 = mutableVector2.getSize();
                if (size2 > 0) {
                    int i2 = size2 - 1;
                    Object[] content2 = mutableVector2.getContent();
                    do {
                        obj = content2[i2];
                        DelegatingLayoutNodeWrapper delegatingLayoutNodeWrapper = (DelegatingLayoutNodeWrapper) obj;
                        if (delegatingLayoutNodeWrapper.getModifier() == mod && !delegatingLayoutNodeWrapper.getToBeReusedForSameModifier()) {
                            break;
                        } else {
                            i2--;
                        }
                    } while (i2 >= 0);
                }
                obj = null;
                DelegatingLayoutNodeWrapper delegatingLayoutNodeWrapper2 = (DelegatingLayoutNodeWrapper) obj;
                while (delegatingLayoutNodeWrapper2 != null) {
                    delegatingLayoutNodeWrapper2.setToBeReusedForSameModifier(true);
                    if (delegatingLayoutNodeWrapper2.getIsChained()) {
                        LayoutNodeWrapper wrappedBy$ui_release = delegatingLayoutNodeWrapper2.getWrappedBy();
                        if (wrappedBy$ui_release instanceof DelegatingLayoutNodeWrapper) {
                            delegatingLayoutNodeWrapper2 = (DelegatingLayoutNodeWrapper) wrappedBy$ui_release;
                        }
                    }
                    delegatingLayoutNodeWrapper2 = null;
                }
            }
        });
    }

    @Override // androidx.compose.ui.layout.Measurable
    /* renamed from: measure-BRTryo0 */
    public Placeable mo1932measureBRTryo0(long constraints) {
        return this.outerMeasurablePlaceable.mo1932measureBRTryo0(constraints);
    }

    /* renamed from: remeasure-_Sx5XlM$ui_release$default, reason: not valid java name */
    public static /* synthetic */ boolean m2022remeasure_Sx5XlM$ui_release$default(LayoutNode layoutNode, Constraints constraints, int i, Object obj) {
        if ((i & 1) != 0) {
            constraints = layoutNode.outerMeasurablePlaceable.m2047getLastConstraintsDWUhwKw();
        }
        return layoutNode.m2025remeasure_Sx5XlM$ui_release(constraints);
    }

    /* renamed from: remeasure-_Sx5XlM$ui_release, reason: not valid java name */
    public final boolean m2025remeasure_Sx5XlM$ui_release(Constraints constraints) {
        if (constraints != null) {
            return this.outerMeasurablePlaceable.m2048remeasureBRTryo0(constraints.getValue());
        }
        return false;
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasurable
    public Object getParentData() {
        return this.outerMeasurablePlaceable.getParentData();
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasurable
    public int minIntrinsicWidth(int height) {
        return this.outerMeasurablePlaceable.minIntrinsicWidth(height);
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasurable
    public int maxIntrinsicWidth(int height) {
        return this.outerMeasurablePlaceable.maxIntrinsicWidth(height);
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasurable
    public int minIntrinsicHeight(int width) {
        return this.outerMeasurablePlaceable.minIntrinsicHeight(width);
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasurable
    public int maxIntrinsicHeight(int width) {
        return this.outerMeasurablePlaceable.maxIntrinsicHeight(width);
    }

    @Override // androidx.compose.ui.layout.Remeasurement
    public void forceRemeasure() {
        requestRemeasure$ui_release();
        Owner owner = this.owner;
        if (owner == null) {
            return;
        }
        owner.measureAndLayout();
    }

    private final void forEachDelegate(Function1<? super LayoutNodeWrapper, Unit> block) {
        LayoutNodeWrapper outerLayoutNodeWrapper$ui_release = getOuterLayoutNodeWrapper$ui_release();
        LayoutNodeWrapper innerLayoutNodeWrapper = getInnerLayoutNodeWrapper();
        while (!Intrinsics.areEqual(outerLayoutNodeWrapper$ui_release, innerLayoutNodeWrapper)) {
            block.invoke(outerLayoutNodeWrapper$ui_release);
            outerLayoutNodeWrapper$ui_release = outerLayoutNodeWrapper$ui_release.getWrapped();
            Intrinsics.checkNotNull(outerLayoutNodeWrapper$ui_release);
        }
    }

    private final void forEachDelegateIncludingInner(Function1<? super LayoutNodeWrapper, Unit> block) {
        LayoutNodeWrapper wrapped = getInnerLayoutNodeWrapper().getWrapped();
        for (LayoutNodeWrapper outerLayoutNodeWrapper$ui_release = getOuterLayoutNodeWrapper$ui_release(); !Intrinsics.areEqual(outerLayoutNodeWrapper$ui_release, wrapped) && outerLayoutNodeWrapper$ui_release != null; outerLayoutNodeWrapper$ui_release = outerLayoutNodeWrapper$ui_release.getWrapped()) {
            block.invoke(outerLayoutNodeWrapper$ui_release);
        }
    }

    @Override // androidx.compose.ui.layout.LayoutInfo
    public LayoutInfo getParentInfo() {
        return getParent$ui_release();
    }

    /* compiled from: LayoutNode.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0080T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Landroidx/compose/ui/node/LayoutNode$Companion;", "", "()V", "Constructor", "Lkotlin/Function0;", "Landroidx/compose/ui/node/LayoutNode;", "getConstructor$ui_release", "()Lkotlin/jvm/functions/Function0;", "ErrorMeasurePolicy", "Landroidx/compose/ui/node/LayoutNode$NoIntrinsicsMeasurePolicy;", "NotPlacedPlaceOrder", "", "ui_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Function0<LayoutNode> getConstructor$ui_release() {
            return LayoutNode.Constructor;
        }
    }

    @Override // androidx.compose.ui.layout.LayoutInfo
    public List<ModifierInfo> getModifierInfo() {
        MutableVector mutableVector = new MutableVector(new ModifierInfo[16], 0);
        LayoutNodeWrapper outerLayoutNodeWrapper$ui_release = getOuterLayoutNodeWrapper$ui_release();
        LayoutNodeWrapper innerLayoutNodeWrapper = getInnerLayoutNodeWrapper();
        while (!Intrinsics.areEqual(outerLayoutNodeWrapper$ui_release, innerLayoutNodeWrapper)) {
            mutableVector.add(new ModifierInfo(((DelegatingLayoutNodeWrapper) outerLayoutNodeWrapper$ui_release).getModifier(), outerLayoutNodeWrapper$ui_release, outerLayoutNodeWrapper$ui_release.getLayer()));
            outerLayoutNodeWrapper$ui_release = outerLayoutNodeWrapper$ui_release.getWrapped();
            Intrinsics.checkNotNull(outerLayoutNodeWrapper$ui_release);
        }
        return mutableVector.asMutableList();
    }

    public final void invalidateLayers$ui_release() {
        LayoutNodeWrapper outerLayoutNodeWrapper$ui_release = getOuterLayoutNodeWrapper$ui_release();
        LayoutNodeWrapper innerLayoutNodeWrapper = getInnerLayoutNodeWrapper();
        while (!Intrinsics.areEqual(outerLayoutNodeWrapper$ui_release, innerLayoutNodeWrapper)) {
            OwnedLayer layer = outerLayoutNodeWrapper$ui_release.getLayer();
            if (layer != null) {
                layer.invalidate();
            }
            outerLayoutNodeWrapper$ui_release = outerLayoutNodeWrapper$ui_release.getWrapped();
            Intrinsics.checkNotNull(outerLayoutNodeWrapper$ui_release);
        }
        OwnedLayer layer2 = this.innerLayoutNodeWrapper.getLayer();
        if (layer2 == null) {
            return;
        }
        layer2.invalidate();
    }

    private final void copyWrappersToCache() {
        LayoutNodeWrapper outerLayoutNodeWrapper$ui_release = getOuterLayoutNodeWrapper$ui_release();
        LayoutNodeWrapper innerLayoutNodeWrapper = getInnerLayoutNodeWrapper();
        while (!Intrinsics.areEqual(outerLayoutNodeWrapper$ui_release, innerLayoutNodeWrapper)) {
            this.wrapperCache.add((DelegatingLayoutNodeWrapper) outerLayoutNodeWrapper$ui_release);
            outerLayoutNodeWrapper$ui_release = outerLayoutNodeWrapper$ui_release.getWrapped();
            Intrinsics.checkNotNull(outerLayoutNodeWrapper$ui_release);
        }
    }

    private final boolean shouldInvalidateParentLayer() {
        LayoutNodeWrapper wrapped = getInnerLayoutNodeWrapper().getWrapped();
        for (LayoutNodeWrapper outerLayoutNodeWrapper$ui_release = getOuterLayoutNodeWrapper$ui_release(); !Intrinsics.areEqual(outerLayoutNodeWrapper$ui_release, wrapped) && outerLayoutNodeWrapper$ui_release != null; outerLayoutNodeWrapper$ui_release = outerLayoutNodeWrapper$ui_release.getWrapped()) {
            if (outerLayoutNodeWrapper$ui_release.getLayer() != null) {
                return false;
            }
            if (outerLayoutNodeWrapper$ui_release instanceof ModifiedDrawNode) {
                return true;
            }
        }
        return true;
    }
}
