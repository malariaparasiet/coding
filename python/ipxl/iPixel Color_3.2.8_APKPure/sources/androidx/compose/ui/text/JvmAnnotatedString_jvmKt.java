package androidx.compose.ui.text;

import androidx.compose.ui.text.AnnotatedString;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* compiled from: JvmAnnotatedString.jvm.kt */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\u001a0\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0012\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00050\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0002\u001a,\u0010\t\u001a\u00020\n*\u00020\n2\u001e\u0010\t\u001a\u001a\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\f0\u000bH\u0000¨\u0006\r"}, d2 = {"collectRangeTransitions", "", ExifInterface.GPS_DIRECTION_TRUE, "ranges", "", "Landroidx/compose/ui/text/AnnotatedString$Range;", TypedValues.AttributesType.S_TARGET, "Ljava/util/SortedSet;", "", "transform", "Landroidx/compose/ui/text/AnnotatedString;", "Lkotlin/Function3;", "", "ui-text_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class JvmAnnotatedString_jvmKt {
    /* JADX WARN: Multi-variable type inference failed */
    public static final AnnotatedString transform(final AnnotatedString annotatedString, final Function3<? super String, ? super Integer, ? super Integer, String> transform) {
        Intrinsics.checkNotNullParameter(annotatedString, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        int i = 0;
        TreeSet sortedSetOf = SetsKt.sortedSetOf(0, Integer.valueOf(annotatedString.getText().length()));
        TreeSet treeSet = sortedSetOf;
        collectRangeTransitions(annotatedString.getSpanStyles(), treeSet);
        collectRangeTransitions(annotatedString.getParagraphStyles(), treeSet);
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "";
        final Map mutableMapOf = MapsKt.mutableMapOf(TuplesKt.to(0, 0));
        CollectionsKt.windowed$default(sortedSetOf, 2, 0, false, new Function1<List<? extends Integer>, Integer>() { // from class: androidx.compose.ui.text.JvmAnnotatedString_jvmKt$transform$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            /* JADX WARN: Type inference failed for: r0v7, types: [T, java.lang.String] */
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final Integer invoke2(List<Integer> dstr$start$end) {
                Intrinsics.checkNotNullParameter(dstr$start$end, "$dstr$start$end");
                int intValue = dstr$start$end.get(0).intValue();
                int intValue2 = dstr$start$end.get(1).intValue();
                Ref.ObjectRef<String> objectRef2 = objectRef;
                objectRef2.element = Intrinsics.stringPlus(objectRef2.element, transform.invoke(annotatedString.getText(), Integer.valueOf(intValue), Integer.valueOf(intValue2)));
                return mutableMapOf.put(Integer.valueOf(intValue2), Integer.valueOf(objectRef.element.length()));
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Integer invoke(List<? extends Integer> list) {
                return invoke2((List<Integer>) list);
            }
        }, 6, null);
        List<AnnotatedString.Range<SpanStyle>> spanStyles = annotatedString.getSpanStyles();
        ArrayList arrayList = new ArrayList(spanStyles.size());
        int size = spanStyles.size() - 1;
        if (size >= 0) {
            int i2 = 0;
            while (true) {
                int i3 = i2 + 1;
                AnnotatedString.Range<SpanStyle> range = spanStyles.get(i2);
                SpanStyle item = range.getItem();
                Object obj = mutableMapOf.get(Integer.valueOf(range.getStart()));
                Intrinsics.checkNotNull(obj);
                int intValue = ((Number) obj).intValue();
                Object obj2 = mutableMapOf.get(Integer.valueOf(range.getEnd()));
                Intrinsics.checkNotNull(obj2);
                arrayList.add(new AnnotatedString.Range(item, intValue, ((Number) obj2).intValue()));
                if (i3 > size) {
                    break;
                }
                i2 = i3;
            }
        }
        ArrayList arrayList2 = arrayList;
        List<AnnotatedString.Range<ParagraphStyle>> paragraphStyles = annotatedString.getParagraphStyles();
        ArrayList arrayList3 = new ArrayList(paragraphStyles.size());
        int size2 = paragraphStyles.size() - 1;
        if (size2 >= 0) {
            int i4 = 0;
            while (true) {
                int i5 = i4 + 1;
                AnnotatedString.Range<ParagraphStyle> range2 = paragraphStyles.get(i4);
                ParagraphStyle item2 = range2.getItem();
                Object obj3 = mutableMapOf.get(Integer.valueOf(range2.getStart()));
                Intrinsics.checkNotNull(obj3);
                int intValue2 = ((Number) obj3).intValue();
                Object obj4 = mutableMapOf.get(Integer.valueOf(range2.getEnd()));
                Intrinsics.checkNotNull(obj4);
                arrayList3.add(new AnnotatedString.Range(item2, intValue2, ((Number) obj4).intValue()));
                if (i5 > size2) {
                    break;
                }
                i4 = i5;
            }
        }
        ArrayList arrayList4 = arrayList3;
        List<AnnotatedString.Range<? extends Object>> annotations$ui_text_release = annotatedString.getAnnotations$ui_text_release();
        ArrayList arrayList5 = new ArrayList(annotations$ui_text_release.size());
        int size3 = annotations$ui_text_release.size() - 1;
        if (size3 >= 0) {
            while (true) {
                int i6 = i + 1;
                AnnotatedString.Range<? extends Object> range3 = annotations$ui_text_release.get(i);
                Object item3 = range3.getItem();
                Object obj5 = mutableMapOf.get(Integer.valueOf(range3.getStart()));
                Intrinsics.checkNotNull(obj5);
                int intValue3 = ((Number) obj5).intValue();
                Object obj6 = mutableMapOf.get(Integer.valueOf(range3.getEnd()));
                Intrinsics.checkNotNull(obj6);
                arrayList5.add(new AnnotatedString.Range(item3, intValue3, ((Number) obj6).intValue()));
                if (i6 > size3) {
                    break;
                }
                i = i6;
            }
        }
        return new AnnotatedString((String) objectRef.element, arrayList2, arrayList4, arrayList5);
    }

    private static final <T> void collectRangeTransitions(List<AnnotatedString.Range<T>> list, SortedSet<Integer> sortedSet) {
        int size = list.size() - 1;
        if (size < 0) {
            return;
        }
        int i = 0;
        while (true) {
            int i2 = i + 1;
            AnnotatedString.Range<T> range = list.get(i);
            sortedSet.add(Integer.valueOf(range.getStart()));
            sortedSet.add(Integer.valueOf(range.getEnd()));
            if (i2 > size) {
                return;
            } else {
                i = i2;
            }
        }
    }
}
