package com.wifiled.ipixels.ui.channel.text;

import android.animation.ObjectAnimator;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TextAnimRecycleView.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, d2 = {"Lcom/wifiled/ipixels/ui/channel/text/TextAnimRecycleView;", "Ljava/io/Serializable;", "animUtils", "Lcom/wifiled/ipixels/ui/channel/text/TextAnimUtils;", "ojAnimator", "Landroid/animation/ObjectAnimator;", "<init>", "(Lcom/wifiled/ipixels/ui/channel/text/TextAnimUtils;Landroid/animation/ObjectAnimator;)V", "getAnimUtils", "()Lcom/wifiled/ipixels/ui/channel/text/TextAnimUtils;", "setAnimUtils", "(Lcom/wifiled/ipixels/ui/channel/text/TextAnimUtils;)V", "getOjAnimator", "()Landroid/animation/ObjectAnimator;", "setOjAnimator", "(Landroid/animation/ObjectAnimator;)V", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class TextAnimRecycleView implements Serializable {
    private TextAnimUtils animUtils;
    private ObjectAnimator ojAnimator;

    public static /* synthetic */ TextAnimRecycleView copy$default(TextAnimRecycleView textAnimRecycleView, TextAnimUtils textAnimUtils, ObjectAnimator objectAnimator, int i, Object obj) {
        if ((i & 1) != 0) {
            textAnimUtils = textAnimRecycleView.animUtils;
        }
        if ((i & 2) != 0) {
            objectAnimator = textAnimRecycleView.ojAnimator;
        }
        return textAnimRecycleView.copy(textAnimUtils, objectAnimator);
    }

    /* renamed from: component1, reason: from getter */
    public final TextAnimUtils getAnimUtils() {
        return this.animUtils;
    }

    /* renamed from: component2, reason: from getter */
    public final ObjectAnimator getOjAnimator() {
        return this.ojAnimator;
    }

    public final TextAnimRecycleView copy(TextAnimUtils animUtils, ObjectAnimator ojAnimator) {
        Intrinsics.checkNotNullParameter(animUtils, "animUtils");
        return new TextAnimRecycleView(animUtils, ojAnimator);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TextAnimRecycleView)) {
            return false;
        }
        TextAnimRecycleView textAnimRecycleView = (TextAnimRecycleView) other;
        return Intrinsics.areEqual(this.animUtils, textAnimRecycleView.animUtils) && Intrinsics.areEqual(this.ojAnimator, textAnimRecycleView.ojAnimator);
    }

    public int hashCode() {
        int hashCode = this.animUtils.hashCode() * 31;
        ObjectAnimator objectAnimator = this.ojAnimator;
        return hashCode + (objectAnimator == null ? 0 : objectAnimator.hashCode());
    }

    public String toString() {
        return "TextAnimRecycleView(animUtils=" + this.animUtils + ", ojAnimator=" + this.ojAnimator + ")";
    }

    public TextAnimRecycleView(TextAnimUtils animUtils, ObjectAnimator objectAnimator) {
        Intrinsics.checkNotNullParameter(animUtils, "animUtils");
        this.animUtils = animUtils;
        this.ojAnimator = objectAnimator;
    }

    public final TextAnimUtils getAnimUtils() {
        return this.animUtils;
    }

    public final ObjectAnimator getOjAnimator() {
        return this.ojAnimator;
    }

    public final void setAnimUtils(TextAnimUtils textAnimUtils) {
        Intrinsics.checkNotNullParameter(textAnimUtils, "<set-?>");
        this.animUtils = textAnimUtils;
    }

    public final void setOjAnimator(ObjectAnimator objectAnimator) {
        this.ojAnimator = objectAnimator;
    }
}
