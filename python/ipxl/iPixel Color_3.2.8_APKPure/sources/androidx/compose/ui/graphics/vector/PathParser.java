package androidx.compose.ui.graphics.vector;

import androidx.camera.video.AudioStats;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.vector.PathNode;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.alibaba.fastjson2.internal.asm.Opcodes;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PathParser.kt */
@Metadata(d1 = {"\u0000Þ\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0002bcB\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0014\u0010\u0011\u001a\u00020\u00002\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0012JX\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u00172\u0006\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u001f\u001a\u00020\u0017H\u0002J\u0006\u0010 \u001a\u00020\fJ\u0010\u0010!\u001a\u00020\f2\u0006\u0010\"\u001a\u00020\u0015H\u0002J \u0010#\u001a\u00020\u00102\u0006\u0010$\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020%2\u0006\u0010&\u001a\u00020%H\u0002JX\u0010'\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010(\u001a\u00020\u00172\u0006\u0010)\u001a\u00020\u00172\u0006\u0010*\u001a\u00020\u00172\u0006\u0010+\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u00172\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020-H\u0002J \u0010/\u001a\u00020\f2\u0006\u00100\u001a\u0002012\u0006\u0010\u001e\u001a\u00020%2\u0006\u00102\u001a\u000203H\u0002J\u0010\u00104\u001a\u00020\u00102\u0006\u00100\u001a\u000201H\u0002J\u0018\u00105\u001a\u00020%2\u0006\u00100\u001a\u0002012\u0006\u0010&\u001a\u00020%H\u0002J\u000e\u00106\u001a\u00020\u00002\u0006\u00107\u001a\u000201J\f\u00108\u001a\b\u0012\u0004\u0012\u00020\b0\u0012J\u0010\u00109\u001a\u00020\u00152\b\b\u0002\u0010\"\u001a\u00020\u0015J\u0014\u0010:\u001a\u00020\f*\u00020;2\u0006\u0010\"\u001a\u00020\u0015H\u0002J\u0014\u0010<\u001a\u00020\f*\u00020=2\u0006\u0010\"\u001a\u00020\u0015H\u0002J\u0014\u0010>\u001a\u00020\f*\u00020?2\u0006\u0010\"\u001a\u00020\u0015H\u0002J\u0014\u0010@\u001a\u00020\f*\u00020A2\u0006\u0010\"\u001a\u00020\u0015H\u0002J\u0014\u0010B\u001a\u00020\f*\u00020C2\u0006\u0010\"\u001a\u00020\u0015H\u0002J\u0014\u0010D\u001a\u00020\f*\u00020E2\u0006\u0010\"\u001a\u00020\u0015H\u0002J\u001c\u0010F\u001a\u00020\f*\u00020G2\u0006\u0010H\u001a\u00020-2\u0006\u0010\"\u001a\u00020\u0015H\u0002J\u001c\u0010I\u001a\u00020\f*\u00020J2\u0006\u0010K\u001a\u00020-2\u0006\u0010\"\u001a\u00020\u0015H\u0002J\u0014\u0010L\u001a\u00020\f*\u00020M2\u0006\u0010\"\u001a\u00020\u0015H\u0002J\u0014\u0010N\u001a\u00020\f*\u00020O2\u0006\u0010\"\u001a\u00020\u0015H\u0002J\u0014\u0010P\u001a\u00020\f*\u00020Q2\u0006\u0010\"\u001a\u00020\u0015H\u0002J\u0014\u0010R\u001a\u00020\f*\u00020S2\u0006\u0010\"\u001a\u00020\u0015H\u0002J\u0014\u0010T\u001a\u00020\f*\u00020U2\u0006\u0010\"\u001a\u00020\u0015H\u0002J\u0014\u0010V\u001a\u00020\f*\u00020W2\u0006\u0010\"\u001a\u00020\u0015H\u0002J\u001c\u0010X\u001a\u00020\f*\u00020Y2\u0006\u0010H\u001a\u00020-2\u0006\u0010\"\u001a\u00020\u0015H\u0002J\u001c\u0010Z\u001a\u00020\f*\u00020[2\u0006\u0010K\u001a\u00020-2\u0006\u0010\"\u001a\u00020\u0015H\u0002J\u0014\u0010\\\u001a\u00020\f*\u00020]2\u0006\u0010\"\u001a\u00020\u0015H\u0002J\f\u0010^\u001a\u00020\u0017*\u00020\u0017H\u0002J\f\u0010^\u001a\u00020_*\u00020_H\u0002J\u0014\u0010`\u001a\u00020\f*\u00020a2\u0006\u0010\"\u001a\u00020\u0015H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006d"}, d2 = {"Landroidx/compose/ui/graphics/vector/PathParser;", "", "()V", "ctrlPoint", "Landroidx/compose/ui/graphics/vector/PathParser$PathPoint;", "currentPoint", "nodes", "", "Landroidx/compose/ui/graphics/vector/PathNode;", "reflectiveCtrlPoint", "segmentPoint", "addNode", "", "cmd", "", "args", "", "addPathNodes", "", "arcToBezier", "p", "Landroidx/compose/ui/graphics/Path;", "cx", "", "cy", "a", "b", "e1x", "e1y", "theta", "start", "sweep", "clear", "close", TypedValues.AttributesType.S_TARGET, "copyOfRange", "original", "", "end", "drawArc", "x0", "y0", "x1", "y1", "isMoreThanHalf", "", "isPositiveArc", "extract", "s", "", "result", "Landroidx/compose/ui/graphics/vector/PathParser$ExtractFloatResult;", "getFloats", "nextStart", "parsePathString", "pathData", "toNodes", "toPath", "arcTo", "Landroidx/compose/ui/graphics/vector/PathNode$ArcTo;", "curveTo", "Landroidx/compose/ui/graphics/vector/PathNode$CurveTo;", "horizontalTo", "Landroidx/compose/ui/graphics/vector/PathNode$HorizontalTo;", "lineTo", "Landroidx/compose/ui/graphics/vector/PathNode$LineTo;", "moveTo", "Landroidx/compose/ui/graphics/vector/PathNode$MoveTo;", "quadTo", "Landroidx/compose/ui/graphics/vector/PathNode$QuadTo;", "reflectiveCurveTo", "Landroidx/compose/ui/graphics/vector/PathNode$ReflectiveCurveTo;", "prevIsCurve", "reflectiveQuadTo", "Landroidx/compose/ui/graphics/vector/PathNode$ReflectiveQuadTo;", "prevIsQuad", "relativeArcTo", "Landroidx/compose/ui/graphics/vector/PathNode$RelativeArcTo;", "relativeCurveTo", "Landroidx/compose/ui/graphics/vector/PathNode$RelativeCurveTo;", "relativeHorizontalTo", "Landroidx/compose/ui/graphics/vector/PathNode$RelativeHorizontalTo;", "relativeLineTo", "Landroidx/compose/ui/graphics/vector/PathNode$RelativeLineTo;", "relativeMoveTo", "Landroidx/compose/ui/graphics/vector/PathNode$RelativeMoveTo;", "relativeQuadTo", "Landroidx/compose/ui/graphics/vector/PathNode$RelativeQuadTo;", "relativeReflectiveCurveTo", "Landroidx/compose/ui/graphics/vector/PathNode$RelativeReflectiveCurveTo;", "relativeReflectiveQuadTo", "Landroidx/compose/ui/graphics/vector/PathNode$RelativeReflectiveQuadTo;", "relativeVerticalTo", "Landroidx/compose/ui/graphics/vector/PathNode$RelativeVerticalTo;", "toRadians", "", "verticalTo", "Landroidx/compose/ui/graphics/vector/PathNode$VerticalTo;", "ExtractFloatResult", "PathPoint", "ui-graphics_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class PathParser {
    private final PathPoint ctrlPoint;
    private final PathPoint currentPoint;
    private final List<PathNode> nodes = new ArrayList();
    private final PathPoint reflectiveCtrlPoint;
    private final PathPoint segmentPoint;

    private final double toRadians(double d) {
        return (d / Opcodes.GETFIELD) * 3.141592653589793d;
    }

    private final float toRadians(float f) {
        return (f / 180.0f) * 3.1415927f;
    }

    public PathParser() {
        float f = 0.0f;
        int i = 3;
        DefaultConstructorMarker defaultConstructorMarker = null;
        this.currentPoint = new PathPoint(f, f, i, defaultConstructorMarker);
        this.ctrlPoint = new PathPoint(f, f, i, defaultConstructorMarker);
        this.segmentPoint = new PathPoint(f, f, i, defaultConstructorMarker);
        this.reflectiveCtrlPoint = new PathPoint(f, f, i, defaultConstructorMarker);
    }

    /* compiled from: PathParser.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\u0006\u0010\u0014\u001a\u00020\u0015J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\t¨\u0006\u0018"}, d2 = {"Landroidx/compose/ui/graphics/vector/PathParser$PathPoint;", "", "x", "", "y", "(FF)V", "getX", "()F", "setX", "(F)V", "getY", "setY", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "reset", "", "toString", "", "ui-graphics_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    private static final /* data */ class PathPoint {
        private float x;
        private float y;

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public PathPoint() {
            /*
                r3 = this;
                r0 = 3
                r1 = 0
                r2 = 0
                r3.<init>(r2, r2, r0, r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.graphics.vector.PathParser.PathPoint.<init>():void");
        }

        public static /* synthetic */ PathPoint copy$default(PathPoint pathPoint, float f, float f2, int i, Object obj) {
            if ((i & 1) != 0) {
                f = pathPoint.x;
            }
            if ((i & 2) != 0) {
                f2 = pathPoint.y;
            }
            return pathPoint.copy(f, f2);
        }

        /* renamed from: component1, reason: from getter */
        public final float getX() {
            return this.x;
        }

        /* renamed from: component2, reason: from getter */
        public final float getY() {
            return this.y;
        }

        public final PathPoint copy(float x, float y) {
            return new PathPoint(x, y);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PathPoint)) {
                return false;
            }
            PathPoint pathPoint = (PathPoint) other;
            return Intrinsics.areEqual((Object) Float.valueOf(this.x), (Object) Float.valueOf(pathPoint.x)) && Intrinsics.areEqual((Object) Float.valueOf(this.y), (Object) Float.valueOf(pathPoint.y));
        }

        public int hashCode() {
            return (Float.hashCode(this.x) * 31) + Float.hashCode(this.y);
        }

        public String toString() {
            return "PathPoint(x=" + this.x + ", y=" + this.y + ')';
        }

        public PathPoint(float f, float f2) {
            this.x = f;
            this.y = f2;
        }

        public /* synthetic */ PathPoint(float f, float f2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? 0.0f : f, (i & 2) != 0 ? 0.0f : f2);
        }

        public final float getX() {
            return this.x;
        }

        public final float getY() {
            return this.y;
        }

        public final void setX(float f) {
            this.x = f;
        }

        public final void setY(float f) {
            this.y = f;
        }

        public final void reset() {
            this.x = 0.0f;
            this.y = 0.0f;
        }
    }

    public final void clear() {
        this.nodes.clear();
    }

    public final PathParser parsePathString(String pathData) {
        Intrinsics.checkNotNullParameter(pathData, "pathData");
        this.nodes.clear();
        int i = 0;
        int i2 = 1;
        while (i2 < pathData.length()) {
            int nextStart = nextStart(pathData, i2);
            String substring = pathData.substring(i, nextStart);
            Intrinsics.checkNotNullExpressionValue(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            String str = substring;
            int length = str.length() - 1;
            int i3 = 0;
            boolean z = false;
            while (i3 <= length) {
                boolean z2 = Intrinsics.compare((int) str.charAt(!z ? i3 : length), 32) <= 0;
                if (z) {
                    if (!z2) {
                        break;
                    }
                    length--;
                } else if (z2) {
                    i3++;
                } else {
                    z = true;
                }
            }
            String obj = str.subSequence(i3, length + 1).toString();
            if (obj.length() > 0) {
                addNode(obj.charAt(0), getFloats(obj));
            }
            i = nextStart;
            i2 = nextStart + 1;
        }
        if (i2 - i == 1 && i < pathData.length()) {
            addNode(pathData.charAt(i), new float[0]);
        }
        return this;
    }

    public final PathParser addPathNodes(List<? extends PathNode> nodes) {
        Intrinsics.checkNotNullParameter(nodes, "nodes");
        this.nodes.addAll(nodes);
        return this;
    }

    public final List<PathNode> toNodes() {
        return this.nodes;
    }

    public static /* synthetic */ Path toPath$default(PathParser pathParser, Path path, int i, Object obj) {
        if ((i & 1) != 0) {
            path = AndroidPath_androidKt.Path();
        }
        return pathParser.toPath(path);
    }

    public final Path toPath(Path target) {
        Intrinsics.checkNotNullParameter(target, "target");
        target.reset();
        this.currentPoint.reset();
        this.ctrlPoint.reset();
        this.segmentPoint.reset();
        this.reflectiveCtrlPoint.reset();
        List<PathNode> list = this.nodes;
        int size = list.size() - 1;
        if (size >= 0) {
            PathNode pathNode = null;
            int i = 0;
            while (true) {
                int i2 = i + 1;
                PathNode pathNode2 = list.get(i);
                if (pathNode == null) {
                    pathNode = pathNode2;
                }
                if (pathNode2 instanceof PathNode.Close) {
                    close(target);
                } else if (pathNode2 instanceof PathNode.RelativeMoveTo) {
                    relativeMoveTo((PathNode.RelativeMoveTo) pathNode2, target);
                } else if (pathNode2 instanceof PathNode.MoveTo) {
                    moveTo((PathNode.MoveTo) pathNode2, target);
                } else if (pathNode2 instanceof PathNode.RelativeLineTo) {
                    relativeLineTo((PathNode.RelativeLineTo) pathNode2, target);
                } else if (pathNode2 instanceof PathNode.LineTo) {
                    lineTo((PathNode.LineTo) pathNode2, target);
                } else if (pathNode2 instanceof PathNode.RelativeHorizontalTo) {
                    relativeHorizontalTo((PathNode.RelativeHorizontalTo) pathNode2, target);
                } else if (pathNode2 instanceof PathNode.HorizontalTo) {
                    horizontalTo((PathNode.HorizontalTo) pathNode2, target);
                } else if (pathNode2 instanceof PathNode.RelativeVerticalTo) {
                    relativeVerticalTo((PathNode.RelativeVerticalTo) pathNode2, target);
                } else if (pathNode2 instanceof PathNode.VerticalTo) {
                    verticalTo((PathNode.VerticalTo) pathNode2, target);
                } else if (pathNode2 instanceof PathNode.RelativeCurveTo) {
                    relativeCurveTo((PathNode.RelativeCurveTo) pathNode2, target);
                } else if (pathNode2 instanceof PathNode.CurveTo) {
                    curveTo((PathNode.CurveTo) pathNode2, target);
                } else if (pathNode2 instanceof PathNode.RelativeReflectiveCurveTo) {
                    Intrinsics.checkNotNull(pathNode);
                    relativeReflectiveCurveTo((PathNode.RelativeReflectiveCurveTo) pathNode2, pathNode.getIsCurve(), target);
                } else if (pathNode2 instanceof PathNode.ReflectiveCurveTo) {
                    Intrinsics.checkNotNull(pathNode);
                    reflectiveCurveTo((PathNode.ReflectiveCurveTo) pathNode2, pathNode.getIsCurve(), target);
                } else if (pathNode2 instanceof PathNode.RelativeQuadTo) {
                    relativeQuadTo((PathNode.RelativeQuadTo) pathNode2, target);
                } else if (pathNode2 instanceof PathNode.QuadTo) {
                    quadTo((PathNode.QuadTo) pathNode2, target);
                } else if (pathNode2 instanceof PathNode.RelativeReflectiveQuadTo) {
                    Intrinsics.checkNotNull(pathNode);
                    relativeReflectiveQuadTo((PathNode.RelativeReflectiveQuadTo) pathNode2, pathNode.getIsQuad(), target);
                } else if (pathNode2 instanceof PathNode.ReflectiveQuadTo) {
                    Intrinsics.checkNotNull(pathNode);
                    reflectiveQuadTo((PathNode.ReflectiveQuadTo) pathNode2, pathNode.getIsQuad(), target);
                } else if (pathNode2 instanceof PathNode.RelativeArcTo) {
                    relativeArcTo((PathNode.RelativeArcTo) pathNode2, target);
                } else if (pathNode2 instanceof PathNode.ArcTo) {
                    arcTo((PathNode.ArcTo) pathNode2, target);
                }
                if (i2 > size) {
                    break;
                }
                pathNode = pathNode2;
                i = i2;
            }
        }
        return target;
    }

    private final void close(Path target) {
        this.currentPoint.setX(this.segmentPoint.getX());
        this.currentPoint.setY(this.segmentPoint.getY());
        this.ctrlPoint.setX(this.segmentPoint.getX());
        this.ctrlPoint.setY(this.segmentPoint.getY());
        target.close();
        target.moveTo(this.currentPoint.getX(), this.currentPoint.getY());
    }

    private final void relativeMoveTo(PathNode.RelativeMoveTo relativeMoveTo, Path path) {
        PathPoint pathPoint = this.currentPoint;
        pathPoint.setX(pathPoint.getX() + relativeMoveTo.getDx());
        PathPoint pathPoint2 = this.currentPoint;
        pathPoint2.setY(pathPoint2.getY() + relativeMoveTo.getDy());
        path.relativeMoveTo(relativeMoveTo.getDx(), relativeMoveTo.getDy());
        this.segmentPoint.setX(this.currentPoint.getX());
        this.segmentPoint.setY(this.currentPoint.getY());
    }

    private final void moveTo(PathNode.MoveTo moveTo, Path path) {
        this.currentPoint.setX(moveTo.getX());
        this.currentPoint.setY(moveTo.getY());
        path.moveTo(moveTo.getX(), moveTo.getY());
        this.segmentPoint.setX(this.currentPoint.getX());
        this.segmentPoint.setY(this.currentPoint.getY());
    }

    private final void relativeLineTo(PathNode.RelativeLineTo relativeLineTo, Path path) {
        path.relativeLineTo(relativeLineTo.getDx(), relativeLineTo.getDy());
        PathPoint pathPoint = this.currentPoint;
        pathPoint.setX(pathPoint.getX() + relativeLineTo.getDx());
        PathPoint pathPoint2 = this.currentPoint;
        pathPoint2.setY(pathPoint2.getY() + relativeLineTo.getDy());
    }

    private final void lineTo(PathNode.LineTo lineTo, Path path) {
        path.lineTo(lineTo.getX(), lineTo.getY());
        this.currentPoint.setX(lineTo.getX());
        this.currentPoint.setY(lineTo.getY());
    }

    private final void relativeHorizontalTo(PathNode.RelativeHorizontalTo relativeHorizontalTo, Path path) {
        path.relativeLineTo(relativeHorizontalTo.getDx(), 0.0f);
        PathPoint pathPoint = this.currentPoint;
        pathPoint.setX(pathPoint.getX() + relativeHorizontalTo.getDx());
    }

    private final void horizontalTo(PathNode.HorizontalTo horizontalTo, Path path) {
        path.lineTo(horizontalTo.getX(), this.currentPoint.getY());
        this.currentPoint.setX(horizontalTo.getX());
    }

    private final void relativeVerticalTo(PathNode.RelativeVerticalTo relativeVerticalTo, Path path) {
        path.relativeLineTo(0.0f, relativeVerticalTo.getDy());
        PathPoint pathPoint = this.currentPoint;
        pathPoint.setY(pathPoint.getY() + relativeVerticalTo.getDy());
    }

    private final void verticalTo(PathNode.VerticalTo verticalTo, Path path) {
        path.lineTo(this.currentPoint.getX(), verticalTo.getY());
        this.currentPoint.setY(verticalTo.getY());
    }

    private final void relativeCurveTo(PathNode.RelativeCurveTo relativeCurveTo, Path path) {
        path.relativeCubicTo(relativeCurveTo.getDx1(), relativeCurveTo.getDy1(), relativeCurveTo.getDx2(), relativeCurveTo.getDy2(), relativeCurveTo.getDx3(), relativeCurveTo.getDy3());
        this.ctrlPoint.setX(this.currentPoint.getX() + relativeCurveTo.getDx2());
        this.ctrlPoint.setY(this.currentPoint.getY() + relativeCurveTo.getDy2());
        PathPoint pathPoint = this.currentPoint;
        pathPoint.setX(pathPoint.getX() + relativeCurveTo.getDx3());
        PathPoint pathPoint2 = this.currentPoint;
        pathPoint2.setY(pathPoint2.getY() + relativeCurveTo.getDy3());
    }

    private final void curveTo(PathNode.CurveTo curveTo, Path path) {
        path.cubicTo(curveTo.getX1(), curveTo.getY1(), curveTo.getX2(), curveTo.getY2(), curveTo.getX3(), curveTo.getY3());
        this.ctrlPoint.setX(curveTo.getX2());
        this.ctrlPoint.setY(curveTo.getY2());
        this.currentPoint.setX(curveTo.getX3());
        this.currentPoint.setY(curveTo.getY3());
    }

    private final void relativeReflectiveCurveTo(PathNode.RelativeReflectiveCurveTo relativeReflectiveCurveTo, boolean z, Path path) {
        if (z) {
            this.reflectiveCtrlPoint.setX(this.currentPoint.getX() - this.ctrlPoint.getX());
            this.reflectiveCtrlPoint.setY(this.currentPoint.getY() - this.ctrlPoint.getY());
        } else {
            this.reflectiveCtrlPoint.reset();
        }
        path.relativeCubicTo(this.reflectiveCtrlPoint.getX(), this.reflectiveCtrlPoint.getY(), relativeReflectiveCurveTo.getDx1(), relativeReflectiveCurveTo.getDy1(), relativeReflectiveCurveTo.getDx2(), relativeReflectiveCurveTo.getDy2());
        this.ctrlPoint.setX(this.currentPoint.getX() + relativeReflectiveCurveTo.getDx1());
        this.ctrlPoint.setY(this.currentPoint.getY() + relativeReflectiveCurveTo.getDy1());
        PathPoint pathPoint = this.currentPoint;
        pathPoint.setX(pathPoint.getX() + relativeReflectiveCurveTo.getDx2());
        PathPoint pathPoint2 = this.currentPoint;
        pathPoint2.setY(pathPoint2.getY() + relativeReflectiveCurveTo.getDy2());
    }

    private final void reflectiveCurveTo(PathNode.ReflectiveCurveTo reflectiveCurveTo, boolean z, Path path) {
        if (z) {
            float f = 2;
            this.reflectiveCtrlPoint.setX((this.currentPoint.getX() * f) - this.ctrlPoint.getX());
            this.reflectiveCtrlPoint.setY((f * this.currentPoint.getY()) - this.ctrlPoint.getY());
        } else {
            this.reflectiveCtrlPoint.setX(this.currentPoint.getX());
            this.reflectiveCtrlPoint.setY(this.currentPoint.getY());
        }
        path.cubicTo(this.reflectiveCtrlPoint.getX(), this.reflectiveCtrlPoint.getY(), reflectiveCurveTo.getX1(), reflectiveCurveTo.getY1(), reflectiveCurveTo.getX2(), reflectiveCurveTo.getY2());
        this.ctrlPoint.setX(reflectiveCurveTo.getX1());
        this.ctrlPoint.setY(reflectiveCurveTo.getY1());
        this.currentPoint.setX(reflectiveCurveTo.getX2());
        this.currentPoint.setY(reflectiveCurveTo.getY2());
    }

    private final void relativeQuadTo(PathNode.RelativeQuadTo relativeQuadTo, Path path) {
        path.relativeQuadraticBezierTo(relativeQuadTo.getDx1(), relativeQuadTo.getDy1(), relativeQuadTo.getDx2(), relativeQuadTo.getDy2());
        this.ctrlPoint.setX(this.currentPoint.getX() + relativeQuadTo.getDx1());
        this.ctrlPoint.setY(this.currentPoint.getY() + relativeQuadTo.getDy1());
        PathPoint pathPoint = this.currentPoint;
        pathPoint.setX(pathPoint.getX() + relativeQuadTo.getDx1());
        PathPoint pathPoint2 = this.currentPoint;
        pathPoint2.setY(pathPoint2.getY() + relativeQuadTo.getDy1());
    }

    private final void quadTo(PathNode.QuadTo quadTo, Path path) {
        path.quadraticBezierTo(quadTo.getX1(), quadTo.getY1(), quadTo.getX2(), quadTo.getY2());
        this.ctrlPoint.setX(quadTo.getX1());
        this.ctrlPoint.setY(quadTo.getY1());
        this.currentPoint.setX(quadTo.getX2());
        this.currentPoint.setY(quadTo.getY2());
    }

    private final void relativeReflectiveQuadTo(PathNode.RelativeReflectiveQuadTo relativeReflectiveQuadTo, boolean z, Path path) {
        if (z) {
            this.reflectiveCtrlPoint.setX(this.currentPoint.getX() - this.ctrlPoint.getX());
            this.reflectiveCtrlPoint.setY(this.currentPoint.getY() - this.ctrlPoint.getY());
        } else {
            this.reflectiveCtrlPoint.reset();
        }
        path.relativeQuadraticBezierTo(this.reflectiveCtrlPoint.getX(), this.reflectiveCtrlPoint.getY(), relativeReflectiveQuadTo.getDx(), relativeReflectiveQuadTo.getDy());
        this.ctrlPoint.setX(this.currentPoint.getX() + this.reflectiveCtrlPoint.getX());
        this.ctrlPoint.setY(this.currentPoint.getY() + this.reflectiveCtrlPoint.getY());
        PathPoint pathPoint = this.currentPoint;
        pathPoint.setX(pathPoint.getX() + relativeReflectiveQuadTo.getDx());
        PathPoint pathPoint2 = this.currentPoint;
        pathPoint2.setY(pathPoint2.getY() + relativeReflectiveQuadTo.getDy());
    }

    private final void reflectiveQuadTo(PathNode.ReflectiveQuadTo reflectiveQuadTo, boolean z, Path path) {
        if (z) {
            float f = 2;
            this.reflectiveCtrlPoint.setX((this.currentPoint.getX() * f) - this.ctrlPoint.getX());
            this.reflectiveCtrlPoint.setY((f * this.currentPoint.getY()) - this.ctrlPoint.getY());
        } else {
            this.reflectiveCtrlPoint.setX(this.currentPoint.getX());
            this.reflectiveCtrlPoint.setY(this.currentPoint.getY());
        }
        path.quadraticBezierTo(this.reflectiveCtrlPoint.getX(), this.reflectiveCtrlPoint.getY(), reflectiveQuadTo.getX(), reflectiveQuadTo.getY());
        this.ctrlPoint.setX(this.reflectiveCtrlPoint.getX());
        this.ctrlPoint.setY(this.reflectiveCtrlPoint.getY());
        this.currentPoint.setX(reflectiveQuadTo.getX());
        this.currentPoint.setY(reflectiveQuadTo.getY());
    }

    private final void relativeArcTo(PathNode.RelativeArcTo relativeArcTo, Path path) {
        float arcStartDx = relativeArcTo.getArcStartDx() + this.currentPoint.getX();
        float arcStartDy = relativeArcTo.getArcStartDy() + this.currentPoint.getY();
        drawArc(path, this.currentPoint.getX(), this.currentPoint.getY(), arcStartDx, arcStartDy, relativeArcTo.getHorizontalEllipseRadius(), relativeArcTo.getVerticalEllipseRadius(), relativeArcTo.getTheta(), relativeArcTo.isMoreThanHalf(), relativeArcTo.isPositiveArc());
        this.currentPoint.setX(arcStartDx);
        this.currentPoint.setY(arcStartDy);
        this.ctrlPoint.setX(this.currentPoint.getX());
        this.ctrlPoint.setY(this.currentPoint.getY());
    }

    private final void arcTo(PathNode.ArcTo arcTo, Path path) {
        drawArc(path, this.currentPoint.getX(), this.currentPoint.getY(), arcTo.getArcStartX(), arcTo.getArcStartY(), arcTo.getHorizontalEllipseRadius(), arcTo.getVerticalEllipseRadius(), arcTo.getTheta(), arcTo.isMoreThanHalf(), arcTo.isPositiveArc());
        this.currentPoint.setX(arcTo.getArcStartX());
        this.currentPoint.setY(arcTo.getArcStartY());
        this.ctrlPoint.setX(this.currentPoint.getX());
        this.ctrlPoint.setY(this.currentPoint.getY());
    }

    private final void drawArc(Path p, double x0, double y0, double x1, double y1, double a, double b, double theta, boolean isMoreThanHalf, boolean isPositiveArc) {
        double d;
        double d2;
        double radians = toRadians(theta);
        double cos = Math.cos(radians);
        double sin = Math.sin(radians);
        double d3 = ((x0 * cos) + (y0 * sin)) / a;
        double d4 = (((-x0) * sin) + (y0 * cos)) / b;
        double d5 = ((x1 * cos) + (y1 * sin)) / a;
        double d6 = (((-x1) * sin) + (y1 * cos)) / b;
        double d7 = d3 - d5;
        double d8 = d4 - d6;
        double d9 = 2;
        double d10 = (d3 + d5) / d9;
        double d11 = (d4 + d6) / d9;
        double d12 = (d7 * d7) + (d8 * d8);
        if (d12 == AudioStats.AUDIO_AMPLITUDE_NONE) {
            return;
        }
        double d13 = (1.0d / d12) - 0.25d;
        if (d13 < AudioStats.AUDIO_AMPLITUDE_NONE) {
            double sqrt = (float) (Math.sqrt(d12) / 1.99999d);
            drawArc(p, x0, y0, x1, y1, a * sqrt, b * sqrt, theta, isMoreThanHalf, isPositiveArc);
            return;
        }
        double sqrt2 = Math.sqrt(d13);
        double d14 = d7 * sqrt2;
        double d15 = sqrt2 * d8;
        if (isMoreThanHalf == isPositiveArc) {
            d = d10 - d15;
            d2 = d11 + d14;
        } else {
            d = d10 + d15;
            d2 = d11 - d14;
        }
        double atan2 = Math.atan2(d4 - d2, d3 - d);
        double atan22 = Math.atan2(d6 - d2, d5 - d) - atan2;
        if (isPositiveArc != (atan22 >= AudioStats.AUDIO_AMPLITUDE_NONE)) {
            atan22 = atan22 > AudioStats.AUDIO_AMPLITUDE_NONE ? atan22 - 6.283185307179586d : atan22 + 6.283185307179586d;
        }
        double d16 = d * a;
        double d17 = d2 * b;
        arcToBezier(p, (d16 * cos) - (d17 * sin), (d16 * sin) + (d17 * cos), a, b, x0, y0, radians, atan2, atan22);
    }

    private final void arcToBezier(Path p, double cx, double cy, double a, double b, double e1x, double e1y, double theta, double start, double sweep) {
        double d = 4;
        int ceil = (int) Math.ceil(Math.abs((sweep * d) / 3.141592653589793d));
        double cos = Math.cos(theta);
        double sin = Math.sin(theta);
        double cos2 = Math.cos(start);
        double sin2 = Math.sin(start);
        double d2 = -a;
        double d3 = d2 * cos;
        double d4 = b * sin;
        double d5 = (d3 * sin2) - (d4 * cos2);
        double d6 = d2 * sin;
        double d7 = b * cos;
        double d8 = (sin2 * d6) + (cos2 * d7);
        double d9 = sweep / ceil;
        if (ceil <= 0) {
            return;
        }
        double d10 = d8;
        double d11 = d5;
        int i = 0;
        double d12 = e1x;
        double d13 = e1y;
        double d14 = start;
        while (true) {
            int i2 = i + 1;
            double d15 = d14 + d9;
            double sin3 = Math.sin(d15);
            double cos3 = Math.cos(d15);
            double d16 = (cx + ((a * cos) * cos3)) - (d4 * sin3);
            double d17 = d;
            double d18 = cy + (a * sin * cos3) + (d7 * sin3);
            double d19 = (d3 * sin3) - (d4 * cos3);
            double d20 = (sin3 * d6) + (cos3 * d7);
            double d21 = d15 - d14;
            double d22 = cos;
            double tan = Math.tan(d21 / 2);
            double sin4 = (Math.sin(d21) * (Math.sqrt(d17 + ((3.0d * tan) * tan)) - 1)) / 3;
            double d23 = sin;
            double d24 = d9;
            p.cubicTo((float) (d12 + (d11 * sin4)), (float) (d13 + (d10 * sin4)), (float) (d16 - (sin4 * d19)), (float) (d18 - (sin4 * d20)), (float) d16, (float) d18);
            if (i2 >= ceil) {
                return;
            }
            sin = d23;
            d9 = d24;
            d12 = d16;
            d13 = d18;
            d14 = d15;
            d10 = d20;
            d11 = d19;
            d = d17;
            i = i2;
            cos = d22;
        }
    }

    private final void addNode(char cmd, float[] args) {
        this.nodes.addAll(PathNodeKt.toPathNodes(cmd, args));
    }

    private final int nextStart(String s, int end) {
        while (end < s.length()) {
            char charAt = s.charAt(end);
            if (((charAt - 'A') * (charAt - 'Z') <= 0 || (charAt - 'a') * (charAt - 'z') <= 0) && charAt != 'e' && charAt != 'E') {
                break;
            }
            end++;
        }
        return end;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final float[] getFloats(String s) {
        int i = 0;
        Object[] objArr = 0;
        if (s.charAt(0) == 'z' || s.charAt(0) == 'Z') {
            return new float[0];
        }
        float[] fArr = new float[s.length()];
        ExtractFloatResult extractFloatResult = new ExtractFloatResult(i, objArr == true ? 1 : 0, 3, null);
        int length = s.length();
        int i2 = 1;
        int i3 = 0;
        while (i2 < length) {
            extract(s, i2, extractFloatResult);
            int endPosition = extractFloatResult.getEndPosition();
            if (i2 < endPosition) {
                int i4 = i3 + 1;
                if (s == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                }
                String substring = s.substring(i2, endPosition);
                Intrinsics.checkNotNullExpressionValue(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                fArr[i3] = Float.parseFloat(substring);
                i3 = i4;
            }
            i2 = extractFloatResult.getEndWithNegativeOrDot() ? endPosition : endPosition + 1;
        }
        return copyOfRange(fArr, 0, i3);
    }

    private final float[] copyOfRange(float[] original, int start, int end) {
        if (start > end) {
            throw new IllegalArgumentException();
        }
        int length = original.length;
        if (start < 0 || start > length) {
            throw new IndexOutOfBoundsException();
        }
        int i = end - start;
        int min = Math.min(i, length - start);
        float[] fArr = new float[i];
        ArraysKt.copyInto(original, fArr, 0, start, min + start);
        return fArr;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0047 A[LOOP:0: B:2:0x0008->B:15:0x0047, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x004a A[EDGE_INSN: B:16:0x004a->B:17:0x004a BREAK  A[LOOP:0: B:2:0x0008->B:15:0x0047], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void extract(java.lang.String r9, int r10, androidx.compose.ui.graphics.vector.PathParser.ExtractFloatResult r11) {
        /*
            r8 = this;
            r0 = 0
            r11.setEndWithNegativeOrDot(r0)
            r1 = r10
            r2 = r0
            r3 = r2
            r4 = r3
        L8:
            int r5 = r9.length()
            if (r1 >= r5) goto L4a
            char r5 = r9.charAt(r1)
            r6 = 32
            r7 = 1
            if (r5 != r6) goto L18
            goto L1c
        L18:
            r6 = 44
            if (r5 != r6) goto L1f
        L1c:
            r2 = r0
            r4 = r7
            goto L44
        L1f:
            r6 = 45
            if (r5 != r6) goto L2b
            if (r1 == r10) goto L43
            if (r2 != 0) goto L43
            r11.setEndWithNegativeOrDot(r7)
            goto L1c
        L2b:
            r2 = 46
            if (r5 != r2) goto L38
            if (r3 != 0) goto L34
            r2 = r0
            r3 = r7
            goto L44
        L34:
            r11.setEndWithNegativeOrDot(r7)
            goto L1c
        L38:
            r2 = 101(0x65, float:1.42E-43)
            if (r5 != r2) goto L3d
            goto L41
        L3d:
            r2 = 69
            if (r5 != r2) goto L43
        L41:
            r2 = r7
            goto L44
        L43:
            r2 = r0
        L44:
            if (r4 == 0) goto L47
            goto L4a
        L47:
            int r1 = r1 + 1
            goto L8
        L4a:
            r11.setEndPosition(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.graphics.vector.PathParser.extract(java.lang.String, int, androidx.compose.ui.graphics.vector.PathParser$ExtractFloatResult):void");
    }

    /* compiled from: PathParser.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00052\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0017"}, d2 = {"Landroidx/compose/ui/graphics/vector/PathParser$ExtractFloatResult;", "", "endPosition", "", "endWithNegativeOrDot", "", "(IZ)V", "getEndPosition", "()I", "setEndPosition", "(I)V", "getEndWithNegativeOrDot", "()Z", "setEndWithNegativeOrDot", "(Z)V", "component1", "component2", "copy", "equals", "other", "hashCode", "toString", "", "ui-graphics_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    private static final /* data */ class ExtractFloatResult {
        private int endPosition;
        private boolean endWithNegativeOrDot;

        /* JADX WARN: Multi-variable type inference failed */
        public ExtractFloatResult() {
            this(0, 0 == true ? 1 : 0, 3, null);
        }

        public static /* synthetic */ ExtractFloatResult copy$default(ExtractFloatResult extractFloatResult, int i, boolean z, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = extractFloatResult.endPosition;
            }
            if ((i2 & 2) != 0) {
                z = extractFloatResult.endWithNegativeOrDot;
            }
            return extractFloatResult.copy(i, z);
        }

        /* renamed from: component1, reason: from getter */
        public final int getEndPosition() {
            return this.endPosition;
        }

        /* renamed from: component2, reason: from getter */
        public final boolean getEndWithNegativeOrDot() {
            return this.endWithNegativeOrDot;
        }

        public final ExtractFloatResult copy(int endPosition, boolean endWithNegativeOrDot) {
            return new ExtractFloatResult(endPosition, endWithNegativeOrDot);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ExtractFloatResult)) {
                return false;
            }
            ExtractFloatResult extractFloatResult = (ExtractFloatResult) other;
            return this.endPosition == extractFloatResult.endPosition && this.endWithNegativeOrDot == extractFloatResult.endWithNegativeOrDot;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public int hashCode() {
            int hashCode = Integer.hashCode(this.endPosition) * 31;
            boolean z = this.endWithNegativeOrDot;
            int i = z;
            if (z != 0) {
                i = 1;
            }
            return hashCode + i;
        }

        public String toString() {
            return "ExtractFloatResult(endPosition=" + this.endPosition + ", endWithNegativeOrDot=" + this.endWithNegativeOrDot + ')';
        }

        public ExtractFloatResult(int i, boolean z) {
            this.endPosition = i;
            this.endWithNegativeOrDot = z;
        }

        public /* synthetic */ ExtractFloatResult(int i, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this((i2 & 1) != 0 ? 0 : i, (i2 & 2) != 0 ? false : z);
        }

        public final int getEndPosition() {
            return this.endPosition;
        }

        public final void setEndPosition(int i) {
            this.endPosition = i;
        }

        public final boolean getEndWithNegativeOrDot() {
            return this.endWithNegativeOrDot;
        }

        public final void setEndWithNegativeOrDot(boolean z) {
            this.endWithNegativeOrDot = z;
        }
    }
}
