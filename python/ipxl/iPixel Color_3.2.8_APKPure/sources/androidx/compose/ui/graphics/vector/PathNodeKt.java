package androidx.compose.ui.graphics.vector;

import androidx.autofill.HintConstants;
import androidx.compose.ui.graphics.vector.PathNode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;

/* compiled from: PathNode.kt */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\f\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0017\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aB\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\b2!\u0010%\u001a\u001d\u0012\u0013\u0012\u00110#¢\u0006\f\b'\u0012\b\b(\u0012\u0004\b\b()\u0012\u0004\u0012\u00020!0&H\u0082\b\u001a\u001a\u0010*\u001a\b\u0012\u0004\u0012\u00020!0 *\u00020\u00012\u0006\u0010\"\u001a\u00020#H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000e\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000f\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0010\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0011\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0012\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0013\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0014\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0015\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0016\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0017\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0018\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0019\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001a\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001b\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001c\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001d\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001e\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"ArcToKey", "", "CloseKey", "CurveToKey", "HorizontalToKey", "LineToKey", "MoveToKey", "NUM_ARC_TO_ARGS", "", "NUM_CURVE_TO_ARGS", "NUM_HORIZONTAL_TO_ARGS", "NUM_LINE_TO_ARGS", "NUM_MOVE_TO_ARGS", "NUM_QUAD_TO_ARGS", "NUM_REFLECTIVE_CURVE_TO_ARGS", "NUM_REFLECTIVE_QUAD_TO_ARGS", "NUM_VERTICAL_TO_ARGS", "QuadToKey", "ReflectiveCurveToKey", "ReflectiveQuadToKey", "RelativeArcToKey", "RelativeCloseKey", "RelativeCurveToKey", "RelativeHorizontalToKey", "RelativeLineToKey", "RelativeMoveToKey", "RelativeQuadToKey", "RelativeReflectiveCurveToKey", "RelativeReflectiveQuadToKey", "RelativeVerticalToKey", "VerticalToKey", "pathNodesFromArgs", "", "Landroidx/compose/ui/graphics/vector/PathNode;", "args", "", "numArgs", "nodeFor", "Lkotlin/Function1;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "subArray", "toPathNodes", "ui-graphics_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class PathNodeKt {
    private static final char ArcToKey = 'A';
    private static final char CloseKey = 'Z';
    private static final char CurveToKey = 'C';
    private static final char HorizontalToKey = 'H';
    private static final char LineToKey = 'L';
    private static final char MoveToKey = 'M';
    private static final int NUM_ARC_TO_ARGS = 7;
    private static final int NUM_CURVE_TO_ARGS = 6;
    private static final int NUM_HORIZONTAL_TO_ARGS = 1;
    private static final int NUM_LINE_TO_ARGS = 2;
    private static final int NUM_MOVE_TO_ARGS = 2;
    private static final int NUM_QUAD_TO_ARGS = 4;
    private static final int NUM_REFLECTIVE_CURVE_TO_ARGS = 4;
    private static final int NUM_REFLECTIVE_QUAD_TO_ARGS = 2;
    private static final int NUM_VERTICAL_TO_ARGS = 1;
    private static final char QuadToKey = 'Q';
    private static final char ReflectiveCurveToKey = 'S';
    private static final char ReflectiveQuadToKey = 'T';
    private static final char RelativeArcToKey = 'a';
    private static final char RelativeCloseKey = 'z';
    private static final char RelativeCurveToKey = 'c';
    private static final char RelativeHorizontalToKey = 'h';
    private static final char RelativeLineToKey = 'l';
    private static final char RelativeMoveToKey = 'm';
    private static final char RelativeQuadToKey = 'q';
    private static final char RelativeReflectiveCurveToKey = 's';
    private static final char RelativeReflectiveQuadToKey = 't';
    private static final char RelativeVerticalToKey = 'v';
    private static final char VerticalToKey = 'V';

    public static final List<PathNode> toPathNodes(char c, float[] args) {
        Intrinsics.checkNotNullParameter(args, "args");
        if (c == 'z' || c == 'Z') {
            return CollectionsKt.listOf(PathNode.Close.INSTANCE);
        }
        char c2 = 2;
        if (c == 'm') {
            IntProgression step = RangesKt.step(new IntRange(0, args.length - 2), 2);
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(step, 10));
            Iterator<Integer> it = step.iterator();
            while (it.hasNext()) {
                int nextInt = ((IntIterator) it).nextInt();
                float[] floatArray = CollectionsKt.toFloatArray(ArraysKt.slice(args, RangesKt.until(nextInt, nextInt + 2)));
                PathNode relativeMoveTo = new PathNode.RelativeMoveTo(floatArray[0], floatArray[1]);
                if ((relativeMoveTo instanceof PathNode.MoveTo) && nextInt > 0) {
                    relativeMoveTo = new PathNode.LineTo(floatArray[0], floatArray[1]);
                } else if (nextInt > 0) {
                    relativeMoveTo = new PathNode.RelativeLineTo(floatArray[0], floatArray[1]);
                }
                arrayList.add(relativeMoveTo);
            }
            return arrayList;
        }
        if (c == 'M') {
            IntProgression step2 = RangesKt.step(new IntRange(0, args.length - 2), 2);
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(step2, 10));
            Iterator<Integer> it2 = step2.iterator();
            while (it2.hasNext()) {
                int nextInt2 = ((IntIterator) it2).nextInt();
                float[] floatArray2 = CollectionsKt.toFloatArray(ArraysKt.slice(args, RangesKt.until(nextInt2, nextInt2 + 2)));
                PathNode moveTo = new PathNode.MoveTo(floatArray2[0], floatArray2[1]);
                if (nextInt2 > 0) {
                    moveTo = new PathNode.LineTo(floatArray2[0], floatArray2[1]);
                } else if ((moveTo instanceof PathNode.RelativeMoveTo) && nextInt2 > 0) {
                    moveTo = new PathNode.RelativeLineTo(floatArray2[0], floatArray2[1]);
                }
                arrayList2.add(moveTo);
            }
            return arrayList2;
        }
        if (c == 'l') {
            IntProgression step3 = RangesKt.step(new IntRange(0, args.length - 2), 2);
            ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(step3, 10));
            Iterator<Integer> it3 = step3.iterator();
            while (it3.hasNext()) {
                int nextInt3 = ((IntIterator) it3).nextInt();
                float[] floatArray3 = CollectionsKt.toFloatArray(ArraysKt.slice(args, RangesKt.until(nextInt3, nextInt3 + 2)));
                PathNode relativeLineTo = new PathNode.RelativeLineTo(floatArray3[0], floatArray3[1]);
                if ((relativeLineTo instanceof PathNode.MoveTo) && nextInt3 > 0) {
                    relativeLineTo = new PathNode.LineTo(floatArray3[0], floatArray3[1]);
                } else if ((relativeLineTo instanceof PathNode.RelativeMoveTo) && nextInt3 > 0) {
                    relativeLineTo = new PathNode.RelativeLineTo(floatArray3[0], floatArray3[1]);
                }
                arrayList3.add(relativeLineTo);
            }
            return arrayList3;
        }
        if (c == 'L') {
            IntProgression step4 = RangesKt.step(new IntRange(0, args.length - 2), 2);
            ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(step4, 10));
            Iterator<Integer> it4 = step4.iterator();
            while (it4.hasNext()) {
                int nextInt4 = ((IntIterator) it4).nextInt();
                float[] floatArray4 = CollectionsKt.toFloatArray(ArraysKt.slice(args, RangesKt.until(nextInt4, nextInt4 + 2)));
                PathNode lineTo = new PathNode.LineTo(floatArray4[0], floatArray4[1]);
                if ((lineTo instanceof PathNode.MoveTo) && nextInt4 > 0) {
                    lineTo = new PathNode.LineTo(floatArray4[0], floatArray4[1]);
                } else if ((lineTo instanceof PathNode.RelativeMoveTo) && nextInt4 > 0) {
                    lineTo = new PathNode.RelativeLineTo(floatArray4[0], floatArray4[1]);
                }
                arrayList4.add(lineTo);
            }
            return arrayList4;
        }
        if (c == 'h') {
            IntProgression step5 = RangesKt.step(new IntRange(0, args.length - 1), 1);
            ArrayList arrayList5 = new ArrayList(CollectionsKt.collectionSizeOrDefault(step5, 10));
            Iterator<Integer> it5 = step5.iterator();
            while (it5.hasNext()) {
                int nextInt5 = ((IntIterator) it5).nextInt();
                float[] floatArray5 = CollectionsKt.toFloatArray(ArraysKt.slice(args, RangesKt.until(nextInt5, nextInt5 + 1)));
                PathNode relativeHorizontalTo = new PathNode.RelativeHorizontalTo(floatArray5[0]);
                if ((relativeHorizontalTo instanceof PathNode.MoveTo) && nextInt5 > 0) {
                    relativeHorizontalTo = new PathNode.LineTo(floatArray5[0], floatArray5[1]);
                } else if ((relativeHorizontalTo instanceof PathNode.RelativeMoveTo) && nextInt5 > 0) {
                    relativeHorizontalTo = new PathNode.RelativeLineTo(floatArray5[0], floatArray5[1]);
                }
                arrayList5.add(relativeHorizontalTo);
            }
            return arrayList5;
        }
        if (c == 'H') {
            IntProgression step6 = RangesKt.step(new IntRange(0, args.length - 1), 1);
            ArrayList arrayList6 = new ArrayList(CollectionsKt.collectionSizeOrDefault(step6, 10));
            Iterator<Integer> it6 = step6.iterator();
            while (it6.hasNext()) {
                int nextInt6 = ((IntIterator) it6).nextInt();
                float[] floatArray6 = CollectionsKt.toFloatArray(ArraysKt.slice(args, RangesKt.until(nextInt6, nextInt6 + 1)));
                PathNode horizontalTo = new PathNode.HorizontalTo(floatArray6[0]);
                if ((horizontalTo instanceof PathNode.MoveTo) && nextInt6 > 0) {
                    horizontalTo = new PathNode.LineTo(floatArray6[0], floatArray6[1]);
                } else if ((horizontalTo instanceof PathNode.RelativeMoveTo) && nextInt6 > 0) {
                    horizontalTo = new PathNode.RelativeLineTo(floatArray6[0], floatArray6[1]);
                }
                arrayList6.add(horizontalTo);
            }
            return arrayList6;
        }
        if (c == 'v') {
            IntProgression step7 = RangesKt.step(new IntRange(0, args.length - 1), 1);
            ArrayList arrayList7 = new ArrayList(CollectionsKt.collectionSizeOrDefault(step7, 10));
            Iterator<Integer> it7 = step7.iterator();
            while (it7.hasNext()) {
                int nextInt7 = ((IntIterator) it7).nextInt();
                float[] floatArray7 = CollectionsKt.toFloatArray(ArraysKt.slice(args, RangesKt.until(nextInt7, nextInt7 + 1)));
                PathNode relativeVerticalTo = new PathNode.RelativeVerticalTo(floatArray7[0]);
                if ((relativeVerticalTo instanceof PathNode.MoveTo) && nextInt7 > 0) {
                    relativeVerticalTo = new PathNode.LineTo(floatArray7[0], floatArray7[1]);
                } else if ((relativeVerticalTo instanceof PathNode.RelativeMoveTo) && nextInt7 > 0) {
                    relativeVerticalTo = new PathNode.RelativeLineTo(floatArray7[0], floatArray7[1]);
                }
                arrayList7.add(relativeVerticalTo);
            }
            return arrayList7;
        }
        if (c == 'V') {
            IntProgression step8 = RangesKt.step(new IntRange(0, args.length - 1), 1);
            ArrayList arrayList8 = new ArrayList(CollectionsKt.collectionSizeOrDefault(step8, 10));
            Iterator<Integer> it8 = step8.iterator();
            while (it8.hasNext()) {
                int nextInt8 = ((IntIterator) it8).nextInt();
                float[] floatArray8 = CollectionsKt.toFloatArray(ArraysKt.slice(args, RangesKt.until(nextInt8, nextInt8 + 1)));
                PathNode verticalTo = new PathNode.VerticalTo(floatArray8[0]);
                if ((verticalTo instanceof PathNode.MoveTo) && nextInt8 > 0) {
                    verticalTo = new PathNode.LineTo(floatArray8[0], floatArray8[1]);
                } else if ((verticalTo instanceof PathNode.RelativeMoveTo) && nextInt8 > 0) {
                    verticalTo = new PathNode.RelativeLineTo(floatArray8[0], floatArray8[1]);
                }
                arrayList8.add(verticalTo);
            }
            return arrayList8;
        }
        if (c == 'c') {
            IntProgression step9 = RangesKt.step(new IntRange(0, args.length - 6), 6);
            ArrayList arrayList9 = new ArrayList(CollectionsKt.collectionSizeOrDefault(step9, 10));
            Iterator<Integer> it9 = step9.iterator();
            while (it9.hasNext()) {
                int nextInt9 = ((IntIterator) it9).nextInt();
                float[] floatArray9 = CollectionsKt.toFloatArray(ArraysKt.slice(args, RangesKt.until(nextInt9, nextInt9 + 6)));
                PathNode relativeCurveTo = new PathNode.RelativeCurveTo(floatArray9[0], floatArray9[1], floatArray9[2], floatArray9[3], floatArray9[4], floatArray9[5]);
                if ((relativeCurveTo instanceof PathNode.MoveTo) && nextInt9 > 0) {
                    relativeCurveTo = new PathNode.LineTo(floatArray9[0], floatArray9[1]);
                } else if ((relativeCurveTo instanceof PathNode.RelativeMoveTo) && nextInt9 > 0) {
                    relativeCurveTo = new PathNode.RelativeLineTo(floatArray9[0], floatArray9[1]);
                }
                arrayList9.add(relativeCurveTo);
            }
            return arrayList9;
        }
        if (c == 'C') {
            IntProgression step10 = RangesKt.step(new IntRange(0, args.length - 6), 6);
            ArrayList arrayList10 = new ArrayList(CollectionsKt.collectionSizeOrDefault(step10, 10));
            Iterator<Integer> it10 = step10.iterator();
            while (it10.hasNext()) {
                int nextInt10 = ((IntIterator) it10).nextInt();
                float[] floatArray10 = CollectionsKt.toFloatArray(ArraysKt.slice(args, RangesKt.until(nextInt10, nextInt10 + 6)));
                PathNode curveTo = new PathNode.CurveTo(floatArray10[0], floatArray10[1], floatArray10[2], floatArray10[3], floatArray10[4], floatArray10[5]);
                if ((curveTo instanceof PathNode.MoveTo) && nextInt10 > 0) {
                    curveTo = new PathNode.LineTo(floatArray10[0], floatArray10[1]);
                } else if ((curveTo instanceof PathNode.RelativeMoveTo) && nextInt10 > 0) {
                    curveTo = new PathNode.RelativeLineTo(floatArray10[0], floatArray10[1]);
                }
                arrayList10.add(curveTo);
            }
            return arrayList10;
        }
        if (c == 's') {
            IntProgression step11 = RangesKt.step(new IntRange(0, args.length - 4), 4);
            ArrayList arrayList11 = new ArrayList(CollectionsKt.collectionSizeOrDefault(step11, 10));
            Iterator<Integer> it11 = step11.iterator();
            while (it11.hasNext()) {
                int nextInt11 = ((IntIterator) it11).nextInt();
                float[] floatArray11 = CollectionsKt.toFloatArray(ArraysKt.slice(args, RangesKt.until(nextInt11, nextInt11 + 4)));
                PathNode relativeReflectiveCurveTo = new PathNode.RelativeReflectiveCurveTo(floatArray11[0], floatArray11[1], floatArray11[2], floatArray11[3]);
                if ((relativeReflectiveCurveTo instanceof PathNode.MoveTo) && nextInt11 > 0) {
                    relativeReflectiveCurveTo = new PathNode.LineTo(floatArray11[0], floatArray11[1]);
                } else if ((relativeReflectiveCurveTo instanceof PathNode.RelativeMoveTo) && nextInt11 > 0) {
                    relativeReflectiveCurveTo = new PathNode.RelativeLineTo(floatArray11[0], floatArray11[1]);
                }
                arrayList11.add(relativeReflectiveCurveTo);
            }
            return arrayList11;
        }
        if (c == 'S') {
            IntProgression step12 = RangesKt.step(new IntRange(0, args.length - 4), 4);
            ArrayList arrayList12 = new ArrayList(CollectionsKt.collectionSizeOrDefault(step12, 10));
            Iterator<Integer> it12 = step12.iterator();
            while (it12.hasNext()) {
                int nextInt12 = ((IntIterator) it12).nextInt();
                float[] floatArray12 = CollectionsKt.toFloatArray(ArraysKt.slice(args, RangesKt.until(nextInt12, nextInt12 + 4)));
                PathNode reflectiveCurveTo = new PathNode.ReflectiveCurveTo(floatArray12[0], floatArray12[1], floatArray12[2], floatArray12[3]);
                if ((reflectiveCurveTo instanceof PathNode.MoveTo) && nextInt12 > 0) {
                    reflectiveCurveTo = new PathNode.LineTo(floatArray12[0], floatArray12[1]);
                } else if ((reflectiveCurveTo instanceof PathNode.RelativeMoveTo) && nextInt12 > 0) {
                    reflectiveCurveTo = new PathNode.RelativeLineTo(floatArray12[0], floatArray12[1]);
                }
                arrayList12.add(reflectiveCurveTo);
            }
            return arrayList12;
        }
        if (c == 'q') {
            IntProgression step13 = RangesKt.step(new IntRange(0, args.length - 4), 4);
            ArrayList arrayList13 = new ArrayList(CollectionsKt.collectionSizeOrDefault(step13, 10));
            Iterator<Integer> it13 = step13.iterator();
            while (it13.hasNext()) {
                int nextInt13 = ((IntIterator) it13).nextInt();
                float[] floatArray13 = CollectionsKt.toFloatArray(ArraysKt.slice(args, RangesKt.until(nextInt13, nextInt13 + 4)));
                PathNode relativeQuadTo = new PathNode.RelativeQuadTo(floatArray13[0], floatArray13[1], floatArray13[2], floatArray13[3]);
                if ((relativeQuadTo instanceof PathNode.MoveTo) && nextInt13 > 0) {
                    relativeQuadTo = new PathNode.LineTo(floatArray13[0], floatArray13[1]);
                } else if ((relativeQuadTo instanceof PathNode.RelativeMoveTo) && nextInt13 > 0) {
                    relativeQuadTo = new PathNode.RelativeLineTo(floatArray13[0], floatArray13[1]);
                }
                arrayList13.add(relativeQuadTo);
            }
            return arrayList13;
        }
        if (c == 'Q') {
            IntProgression step14 = RangesKt.step(new IntRange(0, args.length - 4), 4);
            ArrayList arrayList14 = new ArrayList(CollectionsKt.collectionSizeOrDefault(step14, 10));
            Iterator<Integer> it14 = step14.iterator();
            while (it14.hasNext()) {
                int nextInt14 = ((IntIterator) it14).nextInt();
                float[] floatArray14 = CollectionsKt.toFloatArray(ArraysKt.slice(args, RangesKt.until(nextInt14, nextInt14 + 4)));
                PathNode quadTo = new PathNode.QuadTo(floatArray14[0], floatArray14[1], floatArray14[2], floatArray14[3]);
                if ((quadTo instanceof PathNode.MoveTo) && nextInt14 > 0) {
                    quadTo = new PathNode.LineTo(floatArray14[0], floatArray14[1]);
                } else if ((quadTo instanceof PathNode.RelativeMoveTo) && nextInt14 > 0) {
                    quadTo = new PathNode.RelativeLineTo(floatArray14[0], floatArray14[1]);
                }
                arrayList14.add(quadTo);
            }
            return arrayList14;
        }
        if (c == 't') {
            IntProgression step15 = RangesKt.step(new IntRange(0, args.length - 2), 2);
            ArrayList arrayList15 = new ArrayList(CollectionsKt.collectionSizeOrDefault(step15, 10));
            Iterator<Integer> it15 = step15.iterator();
            while (it15.hasNext()) {
                int nextInt15 = ((IntIterator) it15).nextInt();
                float[] floatArray15 = CollectionsKt.toFloatArray(ArraysKt.slice(args, RangesKt.until(nextInt15, nextInt15 + 2)));
                PathNode relativeReflectiveQuadTo = new PathNode.RelativeReflectiveQuadTo(floatArray15[0], floatArray15[1]);
                if ((relativeReflectiveQuadTo instanceof PathNode.MoveTo) && nextInt15 > 0) {
                    relativeReflectiveQuadTo = new PathNode.LineTo(floatArray15[0], floatArray15[1]);
                } else if ((relativeReflectiveQuadTo instanceof PathNode.RelativeMoveTo) && nextInt15 > 0) {
                    relativeReflectiveQuadTo = new PathNode.RelativeLineTo(floatArray15[0], floatArray15[1]);
                }
                arrayList15.add(relativeReflectiveQuadTo);
            }
            return arrayList15;
        }
        if (c == 'T') {
            IntProgression step16 = RangesKt.step(new IntRange(0, args.length - 2), 2);
            ArrayList arrayList16 = new ArrayList(CollectionsKt.collectionSizeOrDefault(step16, 10));
            Iterator<Integer> it16 = step16.iterator();
            while (it16.hasNext()) {
                int nextInt16 = ((IntIterator) it16).nextInt();
                float[] floatArray16 = CollectionsKt.toFloatArray(ArraysKt.slice(args, RangesKt.until(nextInt16, nextInt16 + 2)));
                PathNode reflectiveQuadTo = new PathNode.ReflectiveQuadTo(floatArray16[0], floatArray16[1]);
                if ((reflectiveQuadTo instanceof PathNode.MoveTo) && nextInt16 > 0) {
                    reflectiveQuadTo = new PathNode.LineTo(floatArray16[0], floatArray16[1]);
                } else if ((reflectiveQuadTo instanceof PathNode.RelativeMoveTo) && nextInt16 > 0) {
                    reflectiveQuadTo = new PathNode.RelativeLineTo(floatArray16[0], floatArray16[1]);
                }
                arrayList16.add(reflectiveQuadTo);
            }
            return arrayList16;
        }
        if (c == 'a') {
            IntProgression step17 = RangesKt.step(new IntRange(0, args.length - 7), 7);
            ArrayList arrayList17 = new ArrayList(CollectionsKt.collectionSizeOrDefault(step17, 10));
            Iterator<Integer> it17 = step17.iterator();
            while (it17.hasNext()) {
                int nextInt17 = ((IntIterator) it17).nextInt();
                float[] floatArray17 = CollectionsKt.toFloatArray(ArraysKt.slice(args, RangesKt.until(nextInt17, nextInt17 + 7)));
                char c3 = c2;
                PathNode relativeArcTo = new PathNode.RelativeArcTo(floatArray17[0], floatArray17[1], floatArray17[c2], Float.compare(floatArray17[3], 0.0f) != 0, Float.compare(floatArray17[4], 0.0f) != 0, floatArray17[5], floatArray17[6]);
                if ((relativeArcTo instanceof PathNode.MoveTo) && nextInt17 > 0) {
                    relativeArcTo = new PathNode.LineTo(floatArray17[0], floatArray17[1]);
                } else if ((relativeArcTo instanceof PathNode.RelativeMoveTo) && nextInt17 > 0) {
                    relativeArcTo = new PathNode.RelativeLineTo(floatArray17[0], floatArray17[1]);
                }
                arrayList17.add(relativeArcTo);
                c2 = c3;
            }
            return arrayList17;
        }
        if (c != 'A') {
            throw new IllegalArgumentException(Intrinsics.stringPlus("Unknown command for: ", Character.valueOf(c)));
        }
        IntProgression step18 = RangesKt.step(new IntRange(0, args.length - 7), 7);
        ArrayList arrayList18 = new ArrayList(CollectionsKt.collectionSizeOrDefault(step18, 10));
        Iterator<Integer> it18 = step18.iterator();
        while (it18.hasNext()) {
            int nextInt18 = ((IntIterator) it18).nextInt();
            float[] floatArray18 = CollectionsKt.toFloatArray(ArraysKt.slice(args, RangesKt.until(nextInt18, nextInt18 + 7)));
            PathNode arcTo = new PathNode.ArcTo(floatArray18[0], floatArray18[1], floatArray18[2], Float.compare(floatArray18[3], 0.0f) != 0, Float.compare(floatArray18[4], 0.0f) != 0, floatArray18[5], floatArray18[6]);
            if ((arcTo instanceof PathNode.MoveTo) && nextInt18 > 0) {
                arcTo = new PathNode.LineTo(floatArray18[0], floatArray18[1]);
            } else if ((arcTo instanceof PathNode.RelativeMoveTo) && nextInt18 > 0) {
                arcTo = new PathNode.RelativeLineTo(floatArray18[0], floatArray18[1]);
            }
            arrayList18.add(arcTo);
        }
        return arrayList18;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v4, types: [float[], java.lang.Object] */
    private static final List<PathNode> pathNodesFromArgs(float[] fArr, int i, Function1<? super float[], ? extends PathNode> function1) {
        IntProgression step = RangesKt.step(new IntRange(0, fArr.length - i), i);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(step, 10));
        Iterator<Integer> it = step.iterator();
        while (it.hasNext()) {
            int nextInt = ((IntIterator) it).nextInt();
            ?? floatArray = CollectionsKt.toFloatArray(ArraysKt.slice(fArr, RangesKt.until(nextInt, nextInt + i)));
            PathNode.RelativeLineTo invoke = function1.invoke(floatArray);
            if ((invoke instanceof PathNode.MoveTo) && nextInt > 0) {
                invoke = new PathNode.LineTo(floatArray[0], floatArray[1]);
            } else if ((invoke instanceof PathNode.RelativeMoveTo) && nextInt > 0) {
                invoke = new PathNode.RelativeLineTo(floatArray[0], floatArray[1]);
            }
            arrayList.add(invoke);
        }
        return arrayList;
    }
}
