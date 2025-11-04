package com.example.admin.balatetris;

import com.example.admin.balatetris.Tetromino;
import com.wifiled.gameview.balatetris.model.Cell;
import kotlin.Metadata;

/* compiled from: Tetromino.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/example/admin/balatetris/O;", "Lcom/example/admin/balatetris/Tetromino;", "<init>", "()V", "libgame_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class O extends Tetromino {
    public O() {
        getCells()[0] = new Cell(0, Cell.INSTANCE.getMIN_UNIT_SIZE() * 4, getBitmapO());
        getCells()[1] = new Cell(0, Cell.INSTANCE.getMIN_UNIT_SIZE() * 5, getBitmapO());
        getCells()[2] = new Cell(Cell.INSTANCE.getMIN_UNIT_SIZE(), Cell.INSTANCE.getMIN_UNIT_SIZE() * 4, getBitmapO());
        getCells()[3] = new Cell(Cell.INSTANCE.getMIN_UNIT_SIZE(), Cell.INSTANCE.getMIN_UNIT_SIZE() * 5, getBitmapO());
        setStates(new Tetromino.State[2]);
        O o = this;
        getStates()[0] = new Tetromino.State(0, 0, 0, Cell.INSTANCE.getMIN_UNIT_SIZE(), Cell.INSTANCE.getMIN_UNIT_SIZE(), 0, Cell.INSTANCE.getMIN_UNIT_SIZE(), Cell.INSTANCE.getMIN_UNIT_SIZE());
        getStates()[1] = new Tetromino.State(0, 0, 0, Cell.INSTANCE.getMIN_UNIT_SIZE(), Cell.INSTANCE.getMIN_UNIT_SIZE(), 0, Cell.INSTANCE.getMIN_UNIT_SIZE(), Cell.INSTANCE.getMIN_UNIT_SIZE());
    }
}
