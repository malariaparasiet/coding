package com.wifiled.gameview.balatetris.model;

import android.graphics.Bitmap;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: Cell.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\u0013\u001a\u00020\u0014H\u0016J\u0006\u0010\u0015\u001a\u00020\u0016J\u0006\u0010\u0017\u001a\u00020\u0016J\u0006\u0010\u0018\u001a\u00020\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\n\"\u0004\b\u000e\u0010\fR\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006\u001a"}, d2 = {"Lcom/wifiled/gameview/balatetris/model/Cell;", "", "row", "", "col", "image", "Landroid/graphics/Bitmap;", "<init>", "(IILandroid/graphics/Bitmap;)V", "getRow", "()I", "setRow", "(I)V", "getCol", "setCol", "getImage", "()Landroid/graphics/Bitmap;", "setImage", "(Landroid/graphics/Bitmap;)V", "toString", "", "moveRight", "", "moveLeft", "softDrop", "Companion", "libgame_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class Cell {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int MIN_UNIT_SIZE = 4;
    private int col;
    private Bitmap image;
    private int row;

    public Cell(int i, int i2, Bitmap bitmap) {
        this.row = i;
        this.col = i2;
        this.image = bitmap;
    }

    public final int getCol() {
        return this.col;
    }

    public final int getRow() {
        return this.row;
    }

    public final void setCol(int i) {
        this.col = i;
    }

    public final void setRow(int i) {
        this.row = i;
    }

    public final Bitmap getImage() {
        return this.image;
    }

    public final void setImage(Bitmap bitmap) {
        this.image = bitmap;
    }

    /* compiled from: Cell.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\u00020\u0005X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/wifiled/gameview/balatetris/model/Cell$Companion;", "", "<init>", "()V", "MIN_UNIT_SIZE", "", "getMIN_UNIT_SIZE", "()I", "libgame_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final int getMIN_UNIT_SIZE() {
            return Cell.MIN_UNIT_SIZE;
        }
    }

    public String toString() {
        return this.row + "," + this.col;
    }

    public final void moveRight() {
        this.col += MIN_UNIT_SIZE;
    }

    public final void moveLeft() {
        this.col -= MIN_UNIT_SIZE;
    }

    public final void softDrop() {
        this.row += MIN_UNIT_SIZE;
    }
}
