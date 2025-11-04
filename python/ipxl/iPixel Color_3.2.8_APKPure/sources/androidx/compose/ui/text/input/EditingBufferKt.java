package androidx.compose.ui.text.input;

import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;

/* compiled from: EditingBuffer.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a%\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0004\u0010\u0005\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006\u0006"}, d2 = {"updateRangeAfterDelete", "Landroidx/compose/ui/text/TextRange;", TypedValues.AttributesType.S_TARGET, "deleted", "updateRangeAfterDelete-pWDy79M", "(JJ)J", "ui-text_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class EditingBufferKt {
    /* renamed from: updateRangeAfterDelete-pWDy79M, reason: not valid java name */
    public static final long m2246updateRangeAfterDeletepWDy79M(long j, long j2) {
        int m2185getLengthimpl;
        int m2187getMinimpl = TextRange.m2187getMinimpl(j);
        int m2186getMaximpl = TextRange.m2186getMaximpl(j);
        if (TextRange.m2191intersects5zctL8(j2, j)) {
            if (TextRange.m2179contains5zctL8(j2, j)) {
                m2187getMinimpl = TextRange.m2187getMinimpl(j2);
                m2186getMaximpl = m2187getMinimpl;
            } else {
                if (TextRange.m2179contains5zctL8(j, j2)) {
                    m2185getLengthimpl = TextRange.m2185getLengthimpl(j2);
                } else if (TextRange.m2180containsimpl(j2, m2187getMinimpl)) {
                    m2187getMinimpl = TextRange.m2187getMinimpl(j2);
                    m2185getLengthimpl = TextRange.m2185getLengthimpl(j2);
                } else {
                    m2186getMaximpl = TextRange.m2187getMinimpl(j2);
                }
                m2186getMaximpl -= m2185getLengthimpl;
            }
        } else if (m2186getMaximpl > TextRange.m2187getMinimpl(j2)) {
            m2187getMinimpl -= TextRange.m2185getLengthimpl(j2);
            m2185getLengthimpl = TextRange.m2185getLengthimpl(j2);
            m2186getMaximpl -= m2185getLengthimpl;
        }
        return TextRangeKt.TextRange(m2187getMinimpl, m2186getMaximpl);
    }
}
