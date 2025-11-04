package com.wifiled.ipixels.common;

import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public class UndoRedoList<T> {
    private static final String TAG = "UndoRedoList";
    private final List<T> undoList = new ArrayList();
    private final List<T> redoList = new ArrayList();

    public void put(T data) {
        Log.v("ruis", "UndoRedoList put undoList.size()=" + this.undoList.size() + " redoList.isEmpty()=" + this.redoList.isEmpty());
        if (this.undoList.size() == 1 && !this.redoList.isEmpty()) {
            Log.v("ruis", "UndoRedoList put ifififi");
            Collections.reverse(this.redoList);
            ArrayList arrayList = new ArrayList(this.redoList);
            Collections.copy(arrayList, this.redoList);
            this.redoList.clear();
            this.undoList.addAll(arrayList);
        }
        this.undoList.add(data);
    }

    public void clear() {
        this.undoList.clear();
        this.redoList.clear();
    }

    public List<T> getUndoList() {
        return this.undoList;
    }

    public List<T> getRedoList() {
        return this.redoList;
    }

    public T undo() {
        Log.v("ruis", "undo----undoList.size()=" + this.undoList.size());
        if (this.undoList.size() <= 1) {
            return null;
        }
        List<T> list = this.undoList;
        this.redoList.add(list.remove(list.size() - 1));
        List<T> list2 = this.undoList;
        return list2.get(list2.size() - 1);
    }

    public T redo() {
        if (this.redoList.isEmpty()) {
            return null;
        }
        T remove = this.redoList.remove(r0.size() - 1);
        this.undoList.add(remove);
        return remove;
    }
}
