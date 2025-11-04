package androidx.compose.ui.text.android;

import android.graphics.Canvas;
import android.graphics.Path;
import android.text.BoringLayout;
import android.text.Layout;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextPaint;
import android.text.TextUtils;
import androidx.compose.ui.text.android.style.BaselineShiftSpan;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.i18n.TextBundle;

/* compiled from: TextLayout.kt */
@InternalPlatformTextApi
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b(\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B§\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\t\u0012\b\b\u0002\u0010\r\u001a\u00020\u0005\u0012\b\b\u0003\u0010\u000e\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0011\u001a\u00020\t\u0012\b\b\u0002\u0010\u0012\u001a\u00020\t\u0012\b\b\u0002\u0010\u0013\u001a\u00020\t\u0012\b\b\u0002\u0010\u0014\u001a\u00020\t\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0016\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0016\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0019¢\u0006\u0002\u0010\u001aJ\u000e\u00103\u001a\u00020\u00052\u0006\u00104\u001a\u00020\tJ\u000e\u00105\u001a\u00020\u00052\u0006\u00104\u001a\u00020\tJ\u000e\u00106\u001a\u00020\t2\u0006\u00107\u001a\u00020\tJ\u000e\u00108\u001a\u00020\t2\u0006\u00107\u001a\u00020\tJ\u000e\u00109\u001a\u00020\t2\u0006\u00107\u001a\u00020\tJ\u000e\u0010:\u001a\u00020\t2\u0006\u0010;\u001a\u00020\tJ\u000e\u0010<\u001a\u00020\t2\u0006\u0010=\u001a\u00020\tJ\u000e\u0010>\u001a\u00020\u00052\u0006\u00107\u001a\u00020\tJ\u000e\u0010?\u001a\u00020\u00052\u0006\u00107\u001a\u00020\tJ\u000e\u0010@\u001a\u00020\u00052\u0006\u00107\u001a\u00020\tJ\u000e\u0010A\u001a\u00020\t2\u0006\u00107\u001a\u00020\tJ\u000e\u0010B\u001a\u00020\u00052\u0006\u00104\u001a\u00020\tJ\u000e\u0010C\u001a\u00020\t2\u0006\u00107\u001a\u00020\tJ\u000e\u0010D\u001a\u00020\u00052\u0006\u00107\u001a\u00020\tJ\u0016\u0010E\u001a\u00020\t2\u0006\u00104\u001a\u00020\t2\u0006\u0010F\u001a\u00020\u0005J\u000e\u0010G\u001a\u00020\t2\u0006\u00104\u001a\u00020\tJ\u000e\u0010H\u001a\u00020\u00052\u0006\u0010;\u001a\u00020\tJ\u000e\u0010I\u001a\u00020\u00052\u0006\u0010;\u001a\u00020\tJ\u001e\u0010J\u001a\u00020K2\u0006\u0010L\u001a\u00020\t2\u0006\u0010M\u001a\u00020\t2\u0006\u0010N\u001a\u00020OJ\u000e\u0010P\u001a\u00020\u00102\u0006\u00107\u001a\u00020\tJ\u000e\u0010Q\u001a\u00020\u00102\u0006\u00107\u001a\u00020\tJ\u000e\u0010R\u001a\u00020\u00102\u0006\u0010;\u001a\u00020\tJ\u000e\u0010S\u001a\u00020K2\u0006\u0010T\u001a\u00020UR\u0011\u0010\u001b\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u001e\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u001c\u0010!\u001a\u00020\"8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b#\u0010$\u001a\u0004\b%\u0010&R\u0011\u0010\u0018\u001a\u00020\u0019¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0011\u0010)\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b*\u0010 R\u0011\u0010+\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b,\u0010-R\u0011\u0010.\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b/\u0010-R\u0011\u00100\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b1\u00102¨\u0006V"}, d2 = {"Landroidx/compose/ui/text/android/TextLayout;", "", "charSequence", "", "width", "", "textPaint", "Landroid/text/TextPaint;", "alignment", "", "ellipsize", "Landroid/text/TextUtils$TruncateAt;", "textDirectionHeuristic", "lineSpacingMultiplier", "lineSpacingExtra", "includePadding", "", "maxLines", "breakStrategy", "hyphenationFrequency", "justificationMode", "leftIndents", "", "rightIndents", "layoutIntrinsics", "Landroidx/compose/ui/text/android/LayoutIntrinsics;", "(Ljava/lang/CharSequence;FLandroid/text/TextPaint;ILandroid/text/TextUtils$TruncateAt;IFFZIIII[I[ILandroidx/compose/ui/text/android/LayoutIntrinsics;)V", "didExceedMaxLines", "getDidExceedMaxLines", "()Z", "height", "getHeight", "()I", "layout", "Landroid/text/Layout;", "getLayout$annotations", "()V", "getLayout", "()Landroid/text/Layout;", "getLayoutIntrinsics", "()Landroidx/compose/ui/text/android/LayoutIntrinsics;", "lineCount", "getLineCount", "maxIntrinsicWidth", "getMaxIntrinsicWidth", "()F", "minIntrinsicWidth", "getMinIntrinsicWidth", TextBundle.TEXT_ENTRY, "getText", "()Ljava/lang/CharSequence;", "getLineBaseline", "line", "getLineBottom", "getLineEllipsisCount", "lineIndex", "getLineEllipsisOffset", "getLineEnd", "getLineForOffset", TypedValues.CycleType.S_WAVE_OFFSET, "getLineForVertical", "vertical", "getLineHeight", "getLineLeft", "getLineRight", "getLineStart", "getLineTop", "getLineVisibleEnd", "getLineWidth", "getOffsetForHorizontal", "horizontal", "getParagraphDirection", "getPrimaryHorizontal", "getSecondaryHorizontal", "getSelectionPath", "", "start", "end", "dest", "Landroid/graphics/Path;", "isEllipsisApplied", "isLineEllipsized", "isRtlCharAt", "paint", "canvas", "Landroid/graphics/Canvas;", "ui-text_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class TextLayout {
    private final boolean didExceedMaxLines;
    private final Layout layout;
    private final LayoutIntrinsics layoutIntrinsics;
    private final int lineCount;

    public static /* synthetic */ void getLayout$annotations() {
    }

    public TextLayout(CharSequence charSequence, float f, TextPaint textPaint, int i, TextUtils.TruncateAt truncateAt, int i2, float f2, float f3, boolean z, int i3, int i4, int i5, int i6, int[] iArr, int[] iArr2, LayoutIntrinsics layoutIntrinsics) {
        int i7;
        StaticLayout create$default;
        Intrinsics.checkNotNullParameter(charSequence, "charSequence");
        Intrinsics.checkNotNullParameter(textPaint, "textPaint");
        Intrinsics.checkNotNullParameter(layoutIntrinsics, "layoutIntrinsics");
        this.layoutIntrinsics = layoutIntrinsics;
        int length = charSequence.length();
        TextDirectionHeuristic textDirectionHeuristic = TextLayoutKt.getTextDirectionHeuristic(i2);
        Layout.Alignment alignment = TextAlignmentAdapter.INSTANCE.get(i);
        boolean z2 = (charSequence instanceof Spanned) && ((Spanned) charSequence).nextSpanTransition(-1, length, BaselineShiftSpan.class) < length;
        BoringLayout.Metrics boringMetrics = layoutIntrinsics.getBoringMetrics();
        double d = f;
        int ceil = (int) Math.ceil(d);
        if (boringMetrics != null && layoutIntrinsics.getMaxIntrinsicWidth() <= f && !z2) {
            create$default = BoringLayoutFactory.INSTANCE.create(charSequence, textPaint, ceil, boringMetrics, alignment, z, truncateAt, ceil);
            i7 = i3;
        } else {
            i7 = i3;
            create$default = StaticLayoutFactory.create$default(StaticLayoutFactory.INSTANCE, charSequence, 0, charSequence.length(), textPaint, ceil, textDirectionHeuristic, alignment, i7, truncateAt, (int) Math.ceil(d), f2, f3, i6, z, false, i4, i5, iArr, iArr2, 16384, null);
        }
        this.layout = create$default;
        int min = Math.min(create$default.getLineCount(), i7);
        this.lineCount = min;
        this.didExceedMaxLines = min >= i7 && (create$default.getEllipsisCount(min + (-1)) > 0 || create$default.getLineEnd(min + (-1)) != charSequence.length());
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ TextLayout(java.lang.CharSequence r21, float r22, android.text.TextPaint r23, int r24, android.text.TextUtils.TruncateAt r25, int r26, float r27, float r28, boolean r29, int r30, int r31, int r32, int r33, int[] r34, int[] r35, androidx.compose.ui.text.android.LayoutIntrinsics r36, int r37, kotlin.jvm.internal.DefaultConstructorMarker r38) {
        /*
            r20 = this;
            r0 = r37
            r1 = r0 & 2
            r2 = 0
            if (r1 == 0) goto L9
            r5 = r2
            goto Lb
        L9:
            r5 = r22
        Lb:
            r1 = r0 & 8
            r3 = 0
            if (r1 == 0) goto L12
            r7 = r3
            goto L14
        L12:
            r7 = r24
        L14:
            r1 = r0 & 16
            r4 = 0
            if (r1 == 0) goto L1b
            r8 = r4
            goto L1d
        L1b:
            r8 = r25
        L1d:
            r1 = r0 & 32
            if (r1 == 0) goto L24
            r1 = 2
            r9 = r1
            goto L26
        L24:
            r9 = r26
        L26:
            r1 = r0 & 64
            if (r1 == 0) goto L2e
            r1 = 1065353216(0x3f800000, float:1.0)
            r10 = r1
            goto L30
        L2e:
            r10 = r27
        L30:
            r1 = r0 & 128(0x80, float:1.8E-43)
            if (r1 == 0) goto L36
            r11 = r2
            goto L38
        L36:
            r11 = r28
        L38:
            r1 = r0 & 256(0x100, float:3.59E-43)
            if (r1 == 0) goto L3f
            r1 = 1
            r12 = r1
            goto L41
        L3f:
            r12 = r29
        L41:
            r1 = r0 & 512(0x200, float:7.17E-43)
            if (r1 == 0) goto L4a
            r1 = 2147483647(0x7fffffff, float:NaN)
            r13 = r1
            goto L4c
        L4a:
            r13 = r30
        L4c:
            r1 = r0 & 1024(0x400, float:1.435E-42)
            if (r1 == 0) goto L52
            r14 = r3
            goto L54
        L52:
            r14 = r31
        L54:
            r1 = r0 & 2048(0x800, float:2.87E-42)
            if (r1 == 0) goto L5a
            r15 = r3
            goto L5c
        L5a:
            r15 = r32
        L5c:
            r1 = r0 & 4096(0x1000, float:5.74E-42)
            if (r1 == 0) goto L63
            r16 = r3
            goto L65
        L63:
            r16 = r33
        L65:
            r1 = r0 & 8192(0x2000, float:1.148E-41)
            if (r1 == 0) goto L6c
            r17 = r4
            goto L6e
        L6c:
            r17 = r34
        L6e:
            r1 = r0 & 16384(0x4000, float:2.2959E-41)
            if (r1 == 0) goto L75
            r18 = r4
            goto L77
        L75:
            r18 = r35
        L77:
            r1 = 32768(0x8000, float:4.5918E-41)
            r0 = r0 & r1
            if (r0 == 0) goto L89
            androidx.compose.ui.text.android.LayoutIntrinsics r0 = new androidx.compose.ui.text.android.LayoutIntrinsics
            r4 = r21
            r6 = r23
            r0.<init>(r4, r6, r9)
            r19 = r0
            goto L8f
        L89:
            r4 = r21
            r6 = r23
            r19 = r36
        L8f:
            r3 = r20
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.text.android.TextLayout.<init>(java.lang.CharSequence, float, android.text.TextPaint, int, android.text.TextUtils$TruncateAt, int, float, float, boolean, int, int, int, int, int[], int[], androidx.compose.ui.text.android.LayoutIntrinsics, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final LayoutIntrinsics getLayoutIntrinsics() {
        return this.layoutIntrinsics;
    }

    public final float getMaxIntrinsicWidth() {
        return this.layoutIntrinsics.getMaxIntrinsicWidth();
    }

    public final float getMinIntrinsicWidth() {
        return this.layoutIntrinsics.getMinIntrinsicWidth();
    }

    public final boolean getDidExceedMaxLines() {
        return this.didExceedMaxLines;
    }

    public final Layout getLayout() {
        return this.layout;
    }

    public final int getLineCount() {
        return this.lineCount;
    }

    public final CharSequence getText() {
        CharSequence text = this.layout.getText();
        Intrinsics.checkNotNullExpressionValue(text, "layout.text");
        return text;
    }

    public final int getHeight() {
        if (this.didExceedMaxLines) {
            return this.layout.getLineBottom(this.lineCount - 1);
        }
        return this.layout.getHeight();
    }

    public final float getLineLeft(int lineIndex) {
        return this.layout.getLineLeft(lineIndex);
    }

    public final float getLineRight(int lineIndex) {
        return this.layout.getLineRight(lineIndex);
    }

    public final float getLineTop(int line) {
        return this.layout.getLineTop(line);
    }

    public final float getLineBottom(int line) {
        return this.layout.getLineBottom(line);
    }

    public final float getLineBaseline(int line) {
        return this.layout.getLineBaseline(line);
    }

    public final float getLineHeight(int lineIndex) {
        return this.layout.getLineBottom(lineIndex) - this.layout.getLineTop(lineIndex);
    }

    public final float getLineWidth(int lineIndex) {
        return this.layout.getLineWidth(lineIndex);
    }

    public final int getLineStart(int lineIndex) {
        return this.layout.getLineStart(lineIndex);
    }

    public final int getLineEnd(int lineIndex) {
        if (this.layout.getEllipsisStart(lineIndex) == 0) {
            return this.layout.getLineEnd(lineIndex);
        }
        return this.layout.getText().length();
    }

    public final int getLineVisibleEnd(int lineIndex) {
        if (this.layout.getEllipsisStart(lineIndex) == 0) {
            return this.layout.getLineVisibleEnd(lineIndex);
        }
        return this.layout.getLineStart(lineIndex) + this.layout.getEllipsisStart(lineIndex);
    }

    public final boolean isLineEllipsized(int lineIndex) {
        return this.layout.getEllipsisStart(lineIndex) != 0;
    }

    public final int getLineEllipsisOffset(int lineIndex) {
        return this.layout.getEllipsisStart(lineIndex);
    }

    public final int getLineEllipsisCount(int lineIndex) {
        return this.layout.getEllipsisCount(lineIndex);
    }

    public final int getLineForVertical(int vertical) {
        return this.layout.getLineForVertical(vertical);
    }

    public final int getOffsetForHorizontal(int line, float horizontal) {
        return this.layout.getOffsetForHorizontal(line, horizontal);
    }

    public final float getPrimaryHorizontal(int offset) {
        return this.layout.getPrimaryHorizontal(offset);
    }

    public final float getSecondaryHorizontal(int offset) {
        return this.layout.getSecondaryHorizontal(offset);
    }

    public final int getLineForOffset(int offset) {
        return this.layout.getLineForOffset(offset);
    }

    public final boolean isRtlCharAt(int offset) {
        return this.layout.isRtlCharAt(offset);
    }

    public final int getParagraphDirection(int line) {
        return this.layout.getParagraphDirection(line);
    }

    public final void getSelectionPath(int start, int end, Path dest) {
        Intrinsics.checkNotNullParameter(dest, "dest");
        this.layout.getSelectionPath(start, end, dest);
    }

    public final boolean isEllipsisApplied(int lineIndex) {
        return this.layout.getEllipsisCount(lineIndex) > 0;
    }

    public final void paint(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        this.layout.draw(canvas);
    }
}
