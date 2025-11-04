package com.wifiled.ipixels.ui.text;

import android.graphics.PointF;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CreativeTextUtil.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b \n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BM\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0007¢\u0006\u0004\b\f\u0010\rJ\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0007HÆ\u0003J\t\u0010&\u001a\u00020\u0007HÆ\u0003J\t\u0010'\u001a\u00020\nHÆ\u0003J\t\u0010(\u001a\u00020\u0007HÆ\u0003JO\u0010)\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u0007HÆ\u0001J\u0013\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010-\u001a\u00020\u0007HÖ\u0001J\t\u0010.\u001a\u00020/HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000f\"\u0004\b\u0013\u0010\u0011R\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u000f\"\u0004\b\u0015\u0010\u0011R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\b\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0017\"\u0004\b\u001b\u0010\u0019R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001a\u0010\u000b\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0017\"\u0004\b!\u0010\u0019¨\u00060"}, d2 = {"Lcom/wifiled/ipixels/ui/text/TextParticle;", "", "startPosition", "Landroid/graphics/PointF;", "targetPosition", "endPosition", "assembleDelay", "", "dissolveDelay", "velocity", "", TypedValues.Custom.S_COLOR, "<init>", "(Landroid/graphics/PointF;Landroid/graphics/PointF;Landroid/graphics/PointF;IIFI)V", "getStartPosition", "()Landroid/graphics/PointF;", "setStartPosition", "(Landroid/graphics/PointF;)V", "getTargetPosition", "setTargetPosition", "getEndPosition", "setEndPosition", "getAssembleDelay", "()I", "setAssembleDelay", "(I)V", "getDissolveDelay", "setDissolveDelay", "getVelocity", "()F", "setVelocity", "(F)V", "getColor", "setColor", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toString", "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class TextParticle {
    private int assembleDelay;
    private int color;
    private int dissolveDelay;
    private PointF endPosition;
    private PointF startPosition;
    private PointF targetPosition;
    private float velocity;

    public TextParticle() {
        this(null, null, null, 0, 0, 0.0f, 0, 127, null);
    }

    public static /* synthetic */ TextParticle copy$default(TextParticle textParticle, PointF pointF, PointF pointF2, PointF pointF3, int i, int i2, float f, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            pointF = textParticle.startPosition;
        }
        if ((i4 & 2) != 0) {
            pointF2 = textParticle.targetPosition;
        }
        if ((i4 & 4) != 0) {
            pointF3 = textParticle.endPosition;
        }
        if ((i4 & 8) != 0) {
            i = textParticle.assembleDelay;
        }
        if ((i4 & 16) != 0) {
            i2 = textParticle.dissolveDelay;
        }
        if ((i4 & 32) != 0) {
            f = textParticle.velocity;
        }
        if ((i4 & 64) != 0) {
            i3 = textParticle.color;
        }
        float f2 = f;
        int i5 = i3;
        int i6 = i2;
        PointF pointF4 = pointF3;
        return textParticle.copy(pointF, pointF2, pointF4, i, i6, f2, i5);
    }

    /* renamed from: component1, reason: from getter */
    public final PointF getStartPosition() {
        return this.startPosition;
    }

    /* renamed from: component2, reason: from getter */
    public final PointF getTargetPosition() {
        return this.targetPosition;
    }

    /* renamed from: component3, reason: from getter */
    public final PointF getEndPosition() {
        return this.endPosition;
    }

    /* renamed from: component4, reason: from getter */
    public final int getAssembleDelay() {
        return this.assembleDelay;
    }

    /* renamed from: component5, reason: from getter */
    public final int getDissolveDelay() {
        return this.dissolveDelay;
    }

    /* renamed from: component6, reason: from getter */
    public final float getVelocity() {
        return this.velocity;
    }

    /* renamed from: component7, reason: from getter */
    public final int getColor() {
        return this.color;
    }

    public final TextParticle copy(PointF startPosition, PointF targetPosition, PointF endPosition, int assembleDelay, int dissolveDelay, float velocity, int color) {
        Intrinsics.checkNotNullParameter(startPosition, "startPosition");
        Intrinsics.checkNotNullParameter(targetPosition, "targetPosition");
        Intrinsics.checkNotNullParameter(endPosition, "endPosition");
        return new TextParticle(startPosition, targetPosition, endPosition, assembleDelay, dissolveDelay, velocity, color);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TextParticle)) {
            return false;
        }
        TextParticle textParticle = (TextParticle) other;
        return Intrinsics.areEqual(this.startPosition, textParticle.startPosition) && Intrinsics.areEqual(this.targetPosition, textParticle.targetPosition) && Intrinsics.areEqual(this.endPosition, textParticle.endPosition) && this.assembleDelay == textParticle.assembleDelay && this.dissolveDelay == textParticle.dissolveDelay && Float.compare(this.velocity, textParticle.velocity) == 0 && this.color == textParticle.color;
    }

    public int hashCode() {
        return (((((((((((this.startPosition.hashCode() * 31) + this.targetPosition.hashCode()) * 31) + this.endPosition.hashCode()) * 31) + Integer.hashCode(this.assembleDelay)) * 31) + Integer.hashCode(this.dissolveDelay)) * 31) + Float.hashCode(this.velocity)) * 31) + Integer.hashCode(this.color);
    }

    public String toString() {
        return "TextParticle(startPosition=" + this.startPosition + ", targetPosition=" + this.targetPosition + ", endPosition=" + this.endPosition + ", assembleDelay=" + this.assembleDelay + ", dissolveDelay=" + this.dissolveDelay + ", velocity=" + this.velocity + ", color=" + this.color + ")";
    }

    public TextParticle(PointF startPosition, PointF targetPosition, PointF endPosition, int i, int i2, float f, int i3) {
        Intrinsics.checkNotNullParameter(startPosition, "startPosition");
        Intrinsics.checkNotNullParameter(targetPosition, "targetPosition");
        Intrinsics.checkNotNullParameter(endPosition, "endPosition");
        this.startPosition = startPosition;
        this.targetPosition = targetPosition;
        this.endPosition = endPosition;
        this.assembleDelay = i;
        this.dissolveDelay = i2;
        this.velocity = f;
        this.color = i3;
    }

    public /* synthetic */ TextParticle(PointF pointF, PointF pointF2, PointF pointF3, int i, int i2, float f, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? new PointF() : pointF, (i4 & 2) != 0 ? new PointF() : pointF2, (i4 & 4) != 0 ? new PointF() : pointF3, (i4 & 8) != 0 ? 0 : i, (i4 & 16) != 0 ? 0 : i2, (i4 & 32) != 0 ? 1.0f : f, (i4 & 64) != 0 ? -1 : i3);
    }

    public final PointF getStartPosition() {
        return this.startPosition;
    }

    public final void setStartPosition(PointF pointF) {
        Intrinsics.checkNotNullParameter(pointF, "<set-?>");
        this.startPosition = pointF;
    }

    public final PointF getTargetPosition() {
        return this.targetPosition;
    }

    public final void setTargetPosition(PointF pointF) {
        Intrinsics.checkNotNullParameter(pointF, "<set-?>");
        this.targetPosition = pointF;
    }

    public final PointF getEndPosition() {
        return this.endPosition;
    }

    public final void setEndPosition(PointF pointF) {
        Intrinsics.checkNotNullParameter(pointF, "<set-?>");
        this.endPosition = pointF;
    }

    public final int getAssembleDelay() {
        return this.assembleDelay;
    }

    public final void setAssembleDelay(int i) {
        this.assembleDelay = i;
    }

    public final int getDissolveDelay() {
        return this.dissolveDelay;
    }

    public final void setDissolveDelay(int i) {
        this.dissolveDelay = i;
    }

    public final float getVelocity() {
        return this.velocity;
    }

    public final void setVelocity(float f) {
        this.velocity = f;
    }

    public final int getColor() {
        return this.color;
    }

    public final void setColor(int i) {
        this.color = i;
    }
}
