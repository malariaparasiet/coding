package com.example.admin.balatetris;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;
import com.wifiled.gameview.balatetris.model.Cell;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Tetromino.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\u0002\n\u0002\b\u0007\b&\u0018\u0000 =2\u00020\u0001:\u0002<=B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u00104\u001a\u00020\u001dH\u0002J\b\u00105\u001a\u00020\u001bH\u0016J\u0006\u00106\u001a\u000207J\u0006\u00108\u001a\u000207J\u0006\u00109\u001a\u000207J\u0006\u0010:\u001a\u000207J\u0006\u0010;\u001a\u000207R$\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u000b\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR(\u0010\f\u001a\u000e\u0012\n\u0012\b\u0018\u00010\rR\u00020\u00000\u0005X\u0084\u000e¢\u0006\u0010\n\u0002\u0010\u0012\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0013\u001a\u00020\u0014X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u001c\u001a\u00020\u001d8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\u001e\u0010\u001fR\u001b\u0010\"\u001a\u00020\u001d8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b$\u0010!\u001a\u0004\b#\u0010\u001fR\u001b\u0010%\u001a\u00020\u001d8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b'\u0010!\u001a\u0004\b&\u0010\u001fR\u001b\u0010(\u001a\u00020\u001d8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b*\u0010!\u001a\u0004\b)\u0010\u001fR\u001b\u0010+\u001a\u00020\u001d8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b-\u0010!\u001a\u0004\b,\u0010\u001fR\u001b\u0010.\u001a\u00020\u001d8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b0\u0010!\u001a\u0004\b/\u0010\u001fR\u001b\u00101\u001a\u00020\u001d8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b3\u0010!\u001a\u0004\b2\u0010\u001f¨\u0006>"}, d2 = {"Lcom/example/admin/balatetris/Tetromino;", "", "<init>", "()V", "cells", "", "Lcom/wifiled/gameview/balatetris/model/Cell;", "getCells", "()[Lcom/wifiled/gameview/balatetris/model/Cell;", "setCells", "([Lcom/wifiled/gameview/balatetris/model/Cell;)V", "[Lcom/wifiled/gameview/balatetris/model/Cell;", "states", "Lcom/example/admin/balatetris/Tetromino$State;", "getStates", "()[Lcom/example/admin/balatetris/Tetromino$State;", "setStates", "([Lcom/example/admin/balatetris/Tetromino$State;)V", "[Lcom/example/admin/balatetris/Tetromino$State;", "index", "", "getIndex", "()I", "setIndex", "(I)V", "colorArray", "", "", "bitmapT", "Landroid/graphics/Bitmap;", "getBitmapT", "()Landroid/graphics/Bitmap;", "bitmapT$delegate", "Lkotlin/Lazy;", "bitmapI", "getBitmapI", "bitmapI$delegate", "bitmapS", "getBitmapS", "bitmapS$delegate", "bitmapZ", "getBitmapZ", "bitmapZ$delegate", "bitmapO", "getBitmapO", "bitmapO$delegate", "bitmapL", "getBitmapL", "bitmapL$delegate", "bitmapJ", "getBitmapJ", "bitmapJ$delegate", "creatTetrisBitmap", "toString", "moveLeft", "", "moveRight", "softDrop", "rotateRight", "rotateLeft", "State", "Companion", "libgame_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public abstract class Tetromino {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private Cell[] cells = new Cell[4];
    private State[] states = new State[0];
    private int index = 10000;
    private final List<String> colorArray = CollectionsKt.arrayListOf("#FF0000", "#00FF00", "#FFFF00", "#FF00FF", "#00FFFF", "#FF8000", "#0000FF");

    /* renamed from: bitmapT$delegate, reason: from kotlin metadata */
    private final Lazy bitmapT = LazyKt.lazy(new Function0() { // from class: com.example.admin.balatetris.Tetromino$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            Bitmap creatTetrisBitmap;
            creatTetrisBitmap = Tetromino.this.creatTetrisBitmap();
            return creatTetrisBitmap;
        }
    });

    /* renamed from: bitmapI$delegate, reason: from kotlin metadata */
    private final Lazy bitmapI = LazyKt.lazy(new Function0() { // from class: com.example.admin.balatetris.Tetromino$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            Bitmap creatTetrisBitmap;
            creatTetrisBitmap = Tetromino.this.creatTetrisBitmap();
            return creatTetrisBitmap;
        }
    });

    /* renamed from: bitmapS$delegate, reason: from kotlin metadata */
    private final Lazy bitmapS = LazyKt.lazy(new Function0() { // from class: com.example.admin.balatetris.Tetromino$$ExternalSyntheticLambda2
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            Bitmap creatTetrisBitmap;
            creatTetrisBitmap = Tetromino.this.creatTetrisBitmap();
            return creatTetrisBitmap;
        }
    });

    /* renamed from: bitmapZ$delegate, reason: from kotlin metadata */
    private final Lazy bitmapZ = LazyKt.lazy(new Function0() { // from class: com.example.admin.balatetris.Tetromino$$ExternalSyntheticLambda3
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            Bitmap creatTetrisBitmap;
            creatTetrisBitmap = Tetromino.this.creatTetrisBitmap();
            return creatTetrisBitmap;
        }
    });

    /* renamed from: bitmapO$delegate, reason: from kotlin metadata */
    private final Lazy bitmapO = LazyKt.lazy(new Function0() { // from class: com.example.admin.balatetris.Tetromino$$ExternalSyntheticLambda4
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            Bitmap creatTetrisBitmap;
            creatTetrisBitmap = Tetromino.this.creatTetrisBitmap();
            return creatTetrisBitmap;
        }
    });

    /* renamed from: bitmapL$delegate, reason: from kotlin metadata */
    private final Lazy bitmapL = LazyKt.lazy(new Function0() { // from class: com.example.admin.balatetris.Tetromino$$ExternalSyntheticLambda5
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            Bitmap creatTetrisBitmap;
            creatTetrisBitmap = Tetromino.this.creatTetrisBitmap();
            return creatTetrisBitmap;
        }
    });

    /* renamed from: bitmapJ$delegate, reason: from kotlin metadata */
    private final Lazy bitmapJ = LazyKt.lazy(new Function0() { // from class: com.example.admin.balatetris.Tetromino$$ExternalSyntheticLambda6
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            Bitmap creatTetrisBitmap;
            creatTetrisBitmap = Tetromino.this.creatTetrisBitmap();
            return creatTetrisBitmap;
        }
    });

    public final Cell[] getCells() {
        return this.cells;
    }

    public final void setCells(Cell[] cellArr) {
        Intrinsics.checkNotNullParameter(cellArr, "<set-?>");
        this.cells = cellArr;
    }

    protected final State[] getStates() {
        return this.states;
    }

    protected final void setStates(State[] stateArr) {
        Intrinsics.checkNotNullParameter(stateArr, "<set-?>");
        this.states = stateArr;
    }

    protected final int getIndex() {
        return this.index;
    }

    protected final void setIndex(int i) {
        this.index = i;
    }

    /* compiled from: Tetromino.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u001c\b\u0084\u0004\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003¢\u0006\u0004\b\u000b\u0010\fR\u001a\u0010\u0002\u001a\u00020\u0003X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0004\u001a\u00020\u0003X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u000e\"\u0004\b\u0012\u0010\u0010R\u001a\u0010\u0005\u001a\u00020\u0003X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000e\"\u0004\b\u0014\u0010\u0010R\u001a\u0010\u0006\u001a\u00020\u0003X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u000e\"\u0004\b\u0016\u0010\u0010R\u001a\u0010\u0007\u001a\u00020\u0003X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u000e\"\u0004\b\u0018\u0010\u0010R\u001a\u0010\b\u001a\u00020\u0003X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u000e\"\u0004\b\u001a\u0010\u0010R\u001a\u0010\t\u001a\u00020\u0003X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u000e\"\u0004\b\u001c\u0010\u0010R\u001a\u0010\n\u001a\u00020\u0003X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u000e\"\u0004\b\u001e\u0010\u0010¨\u0006\u001f"}, d2 = {"Lcom/example/admin/balatetris/Tetromino$State;", "", "row0", "", "col0", "row1", "col1", "row2", "col2", "row3", "col3", "<init>", "(Lcom/example/admin/balatetris/Tetromino;IIIIIIII)V", "getRow0$libgame_release", "()I", "setRow0$libgame_release", "(I)V", "getCol0$libgame_release", "setCol0$libgame_release", "getRow1$libgame_release", "setRow1$libgame_release", "getCol1$libgame_release", "setCol1$libgame_release", "getRow2$libgame_release", "setRow2$libgame_release", "getCol2$libgame_release", "setCol2$libgame_release", "getRow3$libgame_release", "setRow3$libgame_release", "getCol3$libgame_release", "setCol3$libgame_release", "libgame_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    protected final class State {
        private int col0;
        private int col1;
        private int col2;
        private int col3;
        private int row0;
        private int row1;
        private int row2;
        private int row3;

        public State(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            this.row0 = i;
            this.col0 = i2;
            this.row1 = i3;
            this.col1 = i4;
            this.row2 = i5;
            this.col2 = i6;
            this.row3 = i7;
            this.col3 = i8;
        }

        /* renamed from: getRow0$libgame_release, reason: from getter */
        public final int getRow0() {
            return this.row0;
        }

        public final void setRow0$libgame_release(int i) {
            this.row0 = i;
        }

        /* renamed from: getCol0$libgame_release, reason: from getter */
        public final int getCol0() {
            return this.col0;
        }

        public final void setCol0$libgame_release(int i) {
            this.col0 = i;
        }

        /* renamed from: getRow1$libgame_release, reason: from getter */
        public final int getRow1() {
            return this.row1;
        }

        public final void setRow1$libgame_release(int i) {
            this.row1 = i;
        }

        /* renamed from: getCol1$libgame_release, reason: from getter */
        public final int getCol1() {
            return this.col1;
        }

        public final void setCol1$libgame_release(int i) {
            this.col1 = i;
        }

        /* renamed from: getRow2$libgame_release, reason: from getter */
        public final int getRow2() {
            return this.row2;
        }

        public final void setRow2$libgame_release(int i) {
            this.row2 = i;
        }

        /* renamed from: getCol2$libgame_release, reason: from getter */
        public final int getCol2() {
            return this.col2;
        }

        public final void setCol2$libgame_release(int i) {
            this.col2 = i;
        }

        /* renamed from: getRow3$libgame_release, reason: from getter */
        public final int getRow3() {
            return this.row3;
        }

        public final void setRow3$libgame_release(int i) {
            this.row3 = i;
        }

        /* renamed from: getCol3$libgame_release, reason: from getter */
        public final int getCol3() {
            return this.col3;
        }

        public final void setCol3$libgame_release(int i) {
            this.col3 = i;
        }
    }

    public final Bitmap getBitmapT() {
        return (Bitmap) this.bitmapT.getValue();
    }

    public final Bitmap getBitmapI() {
        return (Bitmap) this.bitmapI.getValue();
    }

    public final Bitmap getBitmapS() {
        return (Bitmap) this.bitmapS.getValue();
    }

    public final Bitmap getBitmapZ() {
        return (Bitmap) this.bitmapZ.getValue();
    }

    public final Bitmap getBitmapO() {
        return (Bitmap) this.bitmapO.getValue();
    }

    public final Bitmap getBitmapL() {
        return (Bitmap) this.bitmapL.getValue();
    }

    public final Bitmap getBitmapJ() {
        return (Bitmap) this.bitmapJ.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Bitmap creatTetrisBitmap() {
        int nextInt = new Random().nextInt(7);
        Log.d(getClass().getSimpleName(), "color:[" + nextInt + "]");
        Bitmap createBitmap = Bitmap.createBitmap(TetrisView.INSTANCE.getItemWidth() * 4, TetrisView.INSTANCE.getItemHeight() * 4, Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
        createBitmap.eraseColor(Color.parseColor(this.colorArray.get(nextInt)));
        Bitmap createBitmap2 = Bitmap.createBitmap(createBitmap);
        Intrinsics.checkNotNullExpressionValue(createBitmap2, "createBitmap(...)");
        return createBitmap2;
    }

    public String toString() {
        String arrays = Arrays.toString(this.cells);
        Intrinsics.checkNotNullExpressionValue(arrays, "toString(...)");
        return arrays;
    }

    public final void moveLeft() {
        for (Cell cell : this.cells) {
            if (cell != null) {
                cell.moveLeft();
            }
        }
    }

    public final void moveRight() {
        for (Cell cell : this.cells) {
            if (cell != null) {
                cell.moveRight();
            }
        }
    }

    public final void softDrop() {
        for (Cell cell : this.cells) {
            if (cell != null) {
                cell.softDrop();
            }
        }
    }

    public final void rotateRight() {
        int i = this.index + 1;
        this.index = i;
        State[] stateArr = this.states;
        State state = stateArr[i % stateArr.length];
        Cell cell = this.cells[0];
        Integer valueOf = cell != null ? Integer.valueOf(cell.getRow()) : null;
        Integer valueOf2 = cell != null ? Integer.valueOf(cell.getCol()) : null;
        Cell cell2 = this.cells[1];
        if (cell2 != null) {
            Intrinsics.checkNotNull(valueOf);
            int intValue = valueOf.intValue();
            Intrinsics.checkNotNull(state);
            cell2.setRow(intValue + state.getRow1());
        }
        Cell cell3 = this.cells[1];
        if (cell3 != null) {
            Intrinsics.checkNotNull(valueOf2);
            int intValue2 = valueOf2.intValue();
            Intrinsics.checkNotNull(state);
            cell3.setCol(intValue2 + state.getCol1());
        }
        Cell cell4 = this.cells[2];
        if (cell4 != null) {
            Intrinsics.checkNotNull(valueOf);
            int intValue3 = valueOf.intValue();
            Intrinsics.checkNotNull(state);
            cell4.setRow(intValue3 + state.getRow2());
        }
        Cell cell5 = this.cells[2];
        if (cell5 != null) {
            Intrinsics.checkNotNull(valueOf2);
            int intValue4 = valueOf2.intValue();
            Intrinsics.checkNotNull(state);
            cell5.setCol(intValue4 + state.getCol2());
        }
        Cell cell6 = this.cells[3];
        if (cell6 != null) {
            Intrinsics.checkNotNull(valueOf);
            int intValue5 = valueOf.intValue();
            Intrinsics.checkNotNull(state);
            cell6.setRow(intValue5 + state.getRow3());
        }
        Cell cell7 = this.cells[3];
        if (cell7 != null) {
            Intrinsics.checkNotNull(valueOf2);
            int intValue6 = valueOf2.intValue();
            Intrinsics.checkNotNull(state);
            cell7.setCol(intValue6 + state.getCol3());
        }
    }

    public final void rotateLeft() {
        int i = this.index - 1;
        this.index = i;
        State[] stateArr = this.states;
        State state = stateArr[i % stateArr.length];
        Cell cell = this.cells[0];
        Integer valueOf = cell != null ? Integer.valueOf(cell.getRow()) : null;
        Integer valueOf2 = cell != null ? Integer.valueOf(cell.getCol()) : null;
        Cell cell2 = this.cells[1];
        if (cell2 != null) {
            Intrinsics.checkNotNull(valueOf);
            int intValue = valueOf.intValue();
            Intrinsics.checkNotNull(state);
            cell2.setRow(intValue + state.getRow1());
        }
        Cell cell3 = this.cells[1];
        if (cell3 != null) {
            Intrinsics.checkNotNull(valueOf2);
            int intValue2 = valueOf2.intValue();
            Intrinsics.checkNotNull(state);
            cell3.setCol(intValue2 + state.getCol1());
        }
        Cell cell4 = this.cells[2];
        if (cell4 != null) {
            Intrinsics.checkNotNull(valueOf);
            int intValue3 = valueOf.intValue();
            Intrinsics.checkNotNull(state);
            cell4.setRow(intValue3 + state.getRow2());
        }
        Cell cell5 = this.cells[2];
        if (cell5 != null) {
            Intrinsics.checkNotNull(valueOf2);
            int intValue4 = valueOf2.intValue();
            Intrinsics.checkNotNull(state);
            cell5.setCol(intValue4 + state.getCol2());
        }
        Cell cell6 = this.cells[3];
        if (cell6 != null) {
            Intrinsics.checkNotNull(valueOf);
            int intValue5 = valueOf.intValue();
            Intrinsics.checkNotNull(state);
            cell6.setRow(intValue5 + state.getRow3());
        }
        Cell cell7 = this.cells[3];
        if (cell7 != null) {
            Intrinsics.checkNotNull(valueOf2);
            int intValue6 = valueOf2.intValue();
            Intrinsics.checkNotNull(state);
            cell7.setCol(intValue6 + state.getCol3());
        }
    }

    /* compiled from: Tetromino.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¨\u0006\u0006"}, d2 = {"Lcom/example/admin/balatetris/Tetromino$Companion;", "", "<init>", "()V", "randomOne", "Lcom/example/admin/balatetris/Tetromino;", "libgame_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Tetromino randomOne() {
            switch (new Random().nextInt(7)) {
                case 0:
                    return new T();
                case 1:
                    return new I();
                case 2:
                    return new S();
                case 3:
                    return new Z();
                case 4:
                    return new J();
                case 5:
                    return new L();
                case 6:
                    return new O();
                default:
                    return null;
            }
        }
    }
}
