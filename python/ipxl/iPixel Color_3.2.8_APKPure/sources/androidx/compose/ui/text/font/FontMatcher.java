package androidx.compose.ui.text.font;

import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FontMatcher.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0002\b\u0002\b\u0010\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J-\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000b\u0010\fJ-\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000b\u0010\u000eJ3\u0010\u0003\u001a\u00020\u00042\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u00102\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000b\u0010\u0011\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006\u0012"}, d2 = {"Landroidx/compose/ui/text/font/FontMatcher;", "", "()V", "matchFont", "Landroidx/compose/ui/text/font/Font;", "fontFamily", "Landroidx/compose/ui/text/font/FontFamily;", "fontWeight", "Landroidx/compose/ui/text/font/FontWeight;", "fontStyle", "Landroidx/compose/ui/text/font/FontStyle;", "matchFont-RetOiIg", "(Landroidx/compose/ui/text/font/FontFamily;Landroidx/compose/ui/text/font/FontWeight;I)Landroidx/compose/ui/text/font/Font;", "Landroidx/compose/ui/text/font/FontListFontFamily;", "(Landroidx/compose/ui/text/font/FontListFontFamily;Landroidx/compose/ui/text/font/FontWeight;I)Landroidx/compose/ui/text/font/Font;", "fontList", "", "(Ljava/lang/Iterable;Landroidx/compose/ui/text/font/FontWeight;I)Landroidx/compose/ui/text/font/Font;", "ui-text_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public class FontMatcher {
    /* renamed from: matchFont-RetOiIg, reason: not valid java name */
    public Font m2219matchFontRetOiIg(FontFamily fontFamily, FontWeight fontWeight, int fontStyle) {
        Intrinsics.checkNotNullParameter(fontFamily, "fontFamily");
        Intrinsics.checkNotNullParameter(fontWeight, "fontWeight");
        if (!(fontFamily instanceof FontListFontFamily)) {
            throw new IllegalArgumentException("Only FontFamily instances that presents a list of Fonts can be used");
        }
        return m2220matchFontRetOiIg((FontListFontFamily) fontFamily, fontWeight, fontStyle);
    }

    /* renamed from: matchFont-RetOiIg, reason: not valid java name */
    public Font m2220matchFontRetOiIg(FontListFontFamily fontFamily, FontWeight fontWeight, int fontStyle) {
        Intrinsics.checkNotNullParameter(fontFamily, "fontFamily");
        Intrinsics.checkNotNullParameter(fontWeight, "fontWeight");
        return m2221matchFontRetOiIg(fontFamily.getFonts(), fontWeight, fontStyle);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v11, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r12v12 */
    /* JADX WARN: Type inference failed for: r12v14 */
    /* JADX WARN: Type inference failed for: r12v15 */
    /* JADX WARN: Type inference failed for: r12v19, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r12v20 */
    /* JADX WARN: Type inference failed for: r12v22 */
    /* JADX WARN: Type inference failed for: r12v23 */
    /* JADX WARN: Type inference failed for: r12v30 */
    /* JADX WARN: Type inference failed for: r12v33 */
    /* JADX WARN: Type inference failed for: r12v36 */
    /* JADX WARN: Type inference failed for: r12v4, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r12v5 */
    /* JADX WARN: Type inference failed for: r12v7 */
    /* JADX WARN: Type inference failed for: r12v8 */
    /* JADX WARN: Type inference failed for: r13v10 */
    /* JADX WARN: Type inference failed for: r13v2, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r13v28 */
    /* JADX WARN: Type inference failed for: r13v4 */
    /* JADX WARN: Type inference failed for: r13v5 */
    /* JADX WARN: Type inference failed for: r13v6 */
    /* renamed from: matchFont-RetOiIg, reason: not valid java name */
    public Font m2221matchFontRetOiIg(Iterable<? extends Font> fontList, FontWeight fontWeight, int fontStyle) {
        Object obj;
        Font font;
        Font font2;
        Object obj2;
        Object obj3;
        Intrinsics.checkNotNullParameter(fontList, "fontList");
        Intrinsics.checkNotNullParameter(fontWeight, "fontWeight");
        ArrayList arrayList = new ArrayList();
        for (Font font3 : fontList) {
            Font font4 = font3;
            if (Intrinsics.areEqual(font4.getWeight(), fontWeight) && FontStyle.m2225equalsimpl0(font4.getStyle(), fontStyle)) {
                arrayList.add(font3);
            }
        }
        ArrayList arrayList2 = arrayList;
        if (!arrayList2.isEmpty()) {
            return (Font) arrayList2.get(0);
        }
        Unit unit = Unit.INSTANCE;
        ArrayList arrayList3 = new ArrayList();
        for (Font font5 : fontList) {
            if (FontStyle.m2225equalsimpl0(font5.getStyle(), fontStyle)) {
                arrayList3.add(font5);
            }
        }
        ArrayList arrayList4 = arrayList3;
        if (!arrayList4.isEmpty()) {
            fontList = arrayList4;
        }
        Font font6 = null;
        int i = 1;
        if (fontWeight.compareTo(FontWeight.INSTANCE.getW400()) < 0) {
            ArrayList arrayList5 = new ArrayList();
            for (Font font7 : fontList) {
                if (font7.getWeight().compareTo(fontWeight) <= 0) {
                    arrayList5.add(font7);
                }
            }
            ArrayList arrayList6 = arrayList5;
            if (arrayList6.isEmpty()) {
                obj3 = null;
            } else {
                obj3 = arrayList6.get(0);
                FontWeight weight = ((Font) obj3).getWeight();
                int lastIndex = CollectionsKt.getLastIndex(arrayList6);
                if (1 <= lastIndex) {
                    int i2 = 1;
                    while (true) {
                        int i3 = i2 + 1;
                        Object obj4 = arrayList6.get(i2);
                        FontWeight weight2 = ((Font) obj4).getWeight();
                        if (weight.compareTo(weight2) < 0) {
                            obj3 = obj4;
                            weight = weight2;
                        }
                        if (i2 == lastIndex) {
                            break;
                        }
                        i2 = i3;
                    }
                }
            }
            font = (Font) obj3;
            if (font == null) {
                ArrayList arrayList7 = new ArrayList();
                for (Font font8 : fontList) {
                    if (font8.getWeight().compareTo(fontWeight) > 0) {
                        arrayList7.add(font8);
                    }
                }
                ArrayList arrayList8 = arrayList7;
                if (!arrayList8.isEmpty()) {
                    ?? r12 = arrayList8.get(0);
                    FontWeight weight3 = ((Font) r12).getWeight();
                    int lastIndex2 = CollectionsKt.getLastIndex(arrayList8);
                    boolean z = r12;
                    if (1 <= lastIndex2) {
                        while (true) {
                            int i4 = i + 1;
                            Object obj5 = arrayList8.get(i);
                            FontWeight weight4 = ((Font) obj5).getWeight();
                            r12 = z;
                            if (weight3.compareTo(weight4) > 0) {
                                r12 = obj5;
                                weight3 = weight4;
                            }
                            if (i == lastIndex2) {
                                break;
                            }
                            i = i4;
                            z = r12;
                        }
                    }
                    font6 = r12;
                }
                font = font6;
            }
        } else if (fontWeight.compareTo(FontWeight.INSTANCE.getW500()) > 0) {
            ArrayList arrayList9 = new ArrayList();
            for (Font font9 : fontList) {
                if (font9.getWeight().compareTo(fontWeight) >= 0) {
                    arrayList9.add(font9);
                }
            }
            ArrayList arrayList10 = arrayList9;
            if (arrayList10.isEmpty()) {
                obj2 = null;
            } else {
                obj2 = arrayList10.get(0);
                FontWeight weight5 = ((Font) obj2).getWeight();
                int lastIndex3 = CollectionsKt.getLastIndex(arrayList10);
                if (1 <= lastIndex3) {
                    int i5 = 1;
                    while (true) {
                        int i6 = i5 + 1;
                        Object obj6 = arrayList10.get(i5);
                        FontWeight weight6 = ((Font) obj6).getWeight();
                        if (weight5.compareTo(weight6) > 0) {
                            obj2 = obj6;
                            weight5 = weight6;
                        }
                        if (i5 == lastIndex3) {
                            break;
                        }
                        i5 = i6;
                    }
                }
            }
            font = (Font) obj2;
            if (font == null) {
                ArrayList arrayList11 = new ArrayList();
                for (Font font10 : fontList) {
                    if (font10.getWeight().compareTo(fontWeight) < 0) {
                        arrayList11.add(font10);
                    }
                }
                ArrayList arrayList12 = arrayList11;
                if (!arrayList12.isEmpty()) {
                    ?? r122 = arrayList12.get(0);
                    FontWeight weight7 = ((Font) r122).getWeight();
                    int lastIndex4 = CollectionsKt.getLastIndex(arrayList12);
                    boolean z2 = r122;
                    if (1 <= lastIndex4) {
                        while (true) {
                            int i7 = i + 1;
                            Object obj7 = arrayList12.get(i);
                            FontWeight weight8 = ((Font) obj7).getWeight();
                            r122 = z2;
                            if (weight7.compareTo(weight8) < 0) {
                                r122 = obj7;
                                weight7 = weight8;
                            }
                            if (i == lastIndex4) {
                                break;
                            }
                            i = i7;
                            z2 = r122;
                        }
                    }
                    font6 = r122;
                }
                font = font6;
            }
        } else {
            ArrayList arrayList13 = new ArrayList();
            for (Font font11 : fontList) {
                Font font12 = font11;
                if (font12.getWeight().compareTo(fontWeight) >= 0 && font12.getWeight().compareTo(FontWeight.INSTANCE.getW500()) <= 0) {
                    arrayList13.add(font11);
                }
            }
            ArrayList arrayList14 = arrayList13;
            if (arrayList14.isEmpty()) {
                obj = null;
            } else {
                obj = arrayList14.get(0);
                FontWeight weight9 = ((Font) obj).getWeight();
                int lastIndex5 = CollectionsKt.getLastIndex(arrayList14);
                if (1 <= lastIndex5) {
                    int i8 = 1;
                    while (true) {
                        int i9 = i8 + 1;
                        Object obj8 = arrayList14.get(i8);
                        FontWeight weight10 = ((Font) obj8).getWeight();
                        if (weight9.compareTo(weight10) > 0) {
                            obj = obj8;
                            weight9 = weight10;
                        }
                        if (i8 == lastIndex5) {
                            break;
                        }
                        i8 = i9;
                    }
                }
            }
            font = (Font) obj;
            if (font == null) {
                ArrayList arrayList15 = new ArrayList();
                for (Font font13 : fontList) {
                    if (font13.getWeight().compareTo(fontWeight) < 0) {
                        arrayList15.add(font13);
                    }
                }
                ArrayList arrayList16 = arrayList15;
                if (arrayList16.isEmpty()) {
                    font2 = 0;
                } else {
                    font2 = arrayList16.get(0);
                    FontWeight weight11 = ((Font) font2).getWeight();
                    int lastIndex6 = CollectionsKt.getLastIndex(arrayList16);
                    if (1 <= lastIndex6) {
                        int i10 = 1;
                        boolean z3 = font2;
                        while (true) {
                            int i11 = i10 + 1;
                            Object obj9 = arrayList16.get(i10);
                            FontWeight weight12 = ((Font) obj9).getWeight();
                            font2 = z3;
                            if (weight11.compareTo(weight12) < 0) {
                                font2 = obj9;
                                weight11 = weight12;
                            }
                            if (i10 == lastIndex6) {
                                break;
                            }
                            i10 = i11;
                            z3 = font2;
                        }
                    }
                }
                font = font2;
                if (font == null) {
                    ArrayList arrayList17 = new ArrayList();
                    for (Font font14 : fontList) {
                        if (font14.getWeight().compareTo(FontWeight.INSTANCE.getW500()) > 0) {
                            arrayList17.add(font14);
                        }
                    }
                    ArrayList arrayList18 = arrayList17;
                    if (!arrayList18.isEmpty()) {
                        ?? r123 = arrayList18.get(0);
                        FontWeight weight13 = ((Font) r123).getWeight();
                        int lastIndex7 = CollectionsKt.getLastIndex(arrayList18);
                        boolean z4 = r123;
                        if (1 <= lastIndex7) {
                            while (true) {
                                int i12 = i + 1;
                                Object obj10 = arrayList18.get(i);
                                FontWeight weight14 = ((Font) obj10).getWeight();
                                r123 = z4;
                                if (weight13.compareTo(weight14) > 0) {
                                    r123 = obj10;
                                    weight13 = weight14;
                                }
                                if (i == lastIndex7) {
                                    break;
                                }
                                i = i12;
                                z4 = r123;
                            }
                        }
                        font6 = r123;
                    }
                    font = font6;
                }
            }
        }
        if (font != null) {
            return font;
        }
        throw new IllegalStateException("Cannot match any font");
    }
}
