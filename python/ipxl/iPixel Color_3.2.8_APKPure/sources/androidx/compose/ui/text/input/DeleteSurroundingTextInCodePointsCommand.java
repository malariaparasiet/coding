package androidx.compose.ui.text.input;

import kotlin.Metadata;

/* compiled from: EditCommand.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0096\u0002J\b\u0010\u0011\u001a\u00020\u0003H\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0014"}, d2 = {"Landroidx/compose/ui/text/input/DeleteSurroundingTextInCodePointsCommand;", "Landroidx/compose/ui/text/input/EditCommand;", "lengthBeforeCursor", "", "lengthAfterCursor", "(II)V", "getLengthAfterCursor", "()I", "getLengthBeforeCursor", "applyTo", "", "buffer", "Landroidx/compose/ui/text/input/EditingBuffer;", "equals", "", "other", "", "hashCode", "toString", "", "ui-text_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class DeleteSurroundingTextInCodePointsCommand implements EditCommand {
    private final int lengthAfterCursor;
    private final int lengthBeforeCursor;

    public DeleteSurroundingTextInCodePointsCommand(int i, int i2) {
        this.lengthBeforeCursor = i;
        this.lengthAfterCursor = i2;
    }

    public final int getLengthBeforeCursor() {
        return this.lengthBeforeCursor;
    }

    public final int getLengthAfterCursor() {
        return this.lengthAfterCursor;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x003b A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x007d A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x007f A[EDGE_INSN: B:27:0x007f->B:25:0x007f BREAK  A[LOOP:1: B:16:0x0044->B:26:?], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x003f A[EDGE_INSN: B:33:0x003f->B:13:0x003f BREAK  A[LOOP:0: B:4:0x000c->B:32:?], SYNTHETIC] */
    @Override // androidx.compose.ui.text.input.EditCommand
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void applyTo(androidx.compose.ui.text.input.EditingBuffer r8) {
        /*
            r7 = this;
            java.lang.String r0 = "buffer"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            int r0 = r7.lengthBeforeCursor
            r1 = 0
            if (r0 <= 0) goto L3e
            r2 = r1
            r3 = r2
        Lc:
            int r2 = r2 + 1
            int r4 = r3 + 1
            int r5 = r8.getSelectionStart()
            if (r5 <= r4) goto L33
            int r5 = r8.getSelectionStart()
            int r5 = r5 - r4
            int r5 = r5 + (-1)
            char r5 = r8.get$ui_text_release(r5)
            int r6 = r8.getSelectionStart()
            int r6 = r6 - r4
            char r6 = r8.get$ui_text_release(r6)
            boolean r5 = androidx.compose.ui.text.input.EditCommandKt.access$isSurrogatePair(r5, r6)
            if (r5 == 0) goto L33
            int r3 = r3 + 2
            goto L34
        L33:
            r3 = r4
        L34:
            int r4 = r8.getSelectionStart()
            if (r3 != r4) goto L3b
            goto L3f
        L3b:
            if (r2 < r0) goto Lc
            goto L3f
        L3e:
            r3 = r1
        L3f:
            int r0 = r7.lengthAfterCursor
            if (r0 <= 0) goto L80
            r2 = r1
        L44:
            int r1 = r1 + 1
            int r4 = r2 + 1
            int r5 = r8.getSelectionEnd()
            int r5 = r5 + r4
            int r6 = r8.getLength$ui_text_release()
            if (r5 >= r6) goto L70
            int r5 = r8.getSelectionEnd()
            int r5 = r5 + r4
            int r5 = r5 + (-1)
            char r5 = r8.get$ui_text_release(r5)
            int r6 = r8.getSelectionEnd()
            int r6 = r6 + r4
            char r6 = r8.get$ui_text_release(r6)
            boolean r5 = androidx.compose.ui.text.input.EditCommandKt.access$isSurrogatePair(r5, r6)
            if (r5 == 0) goto L70
            int r2 = r2 + 2
            goto L71
        L70:
            r2 = r4
        L71:
            int r4 = r8.getSelectionEnd()
            int r4 = r4 + r2
            int r5 = r8.getLength$ui_text_release()
            if (r4 != r5) goto L7d
            goto L7f
        L7d:
            if (r1 < r0) goto L44
        L7f:
            r1 = r2
        L80:
            int r0 = r8.getSelectionEnd()
            int r2 = r8.getSelectionEnd()
            int r2 = r2 + r1
            r8.delete$ui_text_release(r0, r2)
            int r0 = r8.getSelectionStart()
            int r0 = r0 - r3
            int r1 = r8.getSelectionStart()
            r8.delete$ui_text_release(r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.text.input.DeleteSurroundingTextInCodePointsCommand.applyTo(androidx.compose.ui.text.input.EditingBuffer):void");
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DeleteSurroundingTextInCodePointsCommand)) {
            return false;
        }
        DeleteSurroundingTextInCodePointsCommand deleteSurroundingTextInCodePointsCommand = (DeleteSurroundingTextInCodePointsCommand) other;
        return this.lengthBeforeCursor == deleteSurroundingTextInCodePointsCommand.lengthBeforeCursor && this.lengthAfterCursor == deleteSurroundingTextInCodePointsCommand.lengthAfterCursor;
    }

    public int hashCode() {
        return (this.lengthBeforeCursor * 31) + this.lengthAfterCursor;
    }

    public String toString() {
        return "DeleteSurroundingTextInCodePointsCommand(lengthBeforeCursor=" + this.lengthBeforeCursor + ", lengthAfterCursor=" + this.lengthAfterCursor + ')';
    }
}
