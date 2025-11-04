package androidx.compose.ui.text;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MultiParagraph.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0000\u001a\u001e\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0001H\u0000\u001a\u001e\u0010\u0006\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0007\u001a\u00020\u0001H\u0000\u001a\u001e\u0010\b\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\t\u001a\u00020\nH\u0000¨\u0006\u000b"}, d2 = {"findParagraphByIndex", "", "paragraphInfoList", "", "Landroidx/compose/ui/text/ParagraphInfo;", "index", "findParagraphByLineIndex", "lineIndex", "findParagraphByY", "y", "", "ui-text_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class MultiParagraphKt {
    public static final int findParagraphByIndex(List<ParagraphInfo> paragraphInfoList, final int i) {
        Intrinsics.checkNotNullParameter(paragraphInfoList, "paragraphInfoList");
        return CollectionsKt.binarySearch$default(paragraphInfoList, 0, 0, new Function1<ParagraphInfo, Integer>() { // from class: androidx.compose.ui.text.MultiParagraphKt$findParagraphByIndex$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Integer invoke(ParagraphInfo paragraphInfo) {
                return Integer.valueOf(invoke2(paragraphInfo));
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final int invoke2(ParagraphInfo paragraphInfo) {
                Intrinsics.checkNotNullParameter(paragraphInfo, "paragraphInfo");
                if (paragraphInfo.getStartIndex() > i) {
                    return 1;
                }
                return paragraphInfo.getEndIndex() <= i ? -1 : 0;
            }
        }, 3, (Object) null);
    }

    public static final int findParagraphByY(List<ParagraphInfo> paragraphInfoList, final float f) {
        Intrinsics.checkNotNullParameter(paragraphInfoList, "paragraphInfoList");
        return CollectionsKt.binarySearch$default(paragraphInfoList, 0, 0, new Function1<ParagraphInfo, Integer>() { // from class: androidx.compose.ui.text.MultiParagraphKt$findParagraphByY$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Integer invoke(ParagraphInfo paragraphInfo) {
                return Integer.valueOf(invoke2(paragraphInfo));
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final int invoke2(ParagraphInfo paragraphInfo) {
                Intrinsics.checkNotNullParameter(paragraphInfo, "paragraphInfo");
                if (paragraphInfo.getTop() > f) {
                    return 1;
                }
                return paragraphInfo.getBottom() <= f ? -1 : 0;
            }
        }, 3, (Object) null);
    }

    public static final int findParagraphByLineIndex(List<ParagraphInfo> paragraphInfoList, final int i) {
        Intrinsics.checkNotNullParameter(paragraphInfoList, "paragraphInfoList");
        return CollectionsKt.binarySearch$default(paragraphInfoList, 0, 0, new Function1<ParagraphInfo, Integer>() { // from class: androidx.compose.ui.text.MultiParagraphKt$findParagraphByLineIndex$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Integer invoke(ParagraphInfo paragraphInfo) {
                return Integer.valueOf(invoke2(paragraphInfo));
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final int invoke2(ParagraphInfo paragraphInfo) {
                Intrinsics.checkNotNullParameter(paragraphInfo, "paragraphInfo");
                if (paragraphInfo.getStartLineIndex() > i) {
                    return 1;
                }
                return paragraphInfo.getEndLineIndex() <= i ? -1 : 0;
            }
        }, 3, (Object) null);
    }
}
