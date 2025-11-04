package com.wifiled.baselib.base.recycleview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.wifiled.baselib.utils.ViewUtils;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

/* loaded from: classes2.dex */
public abstract class RecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerViewHolder> {
    private LinkedHashSet<Integer> childClickViewIds;
    protected Context mContext;
    protected List<T> mData;
    protected LayoutInflater mInflater;
    protected int mLayoutId;
    private OnItemChildClickListener<T> mOnItemChildClickListener;
    private OnItemClickListener<T> mOnItemClickListener;
    private OnItemLongClickListener<T> mOnItemLongClickListener;
    public int select;

    public interface OnItemChildClickListener<T> {
        void onItemChildClick(ViewGroup viewGroup, View view, T t, int i);
    }

    public interface OnItemClickListener<T> {
        void onItemClick(ViewGroup viewGroup, View view, T t, int i);
    }

    public interface OnItemLongClickListener<T> {
        boolean onItemLongClick(ViewGroup viewGroup, View view, T t, int i);
    }

    public abstract void convert(RecyclerViewHolder recyclerViewHolder, T t);

    public int layoutId() {
        return 0;
    }

    public List<T> getMData() {
        return this.mData;
    }

    public void setOnItemClickListener(OnItemClickListener<T> onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener<T> onItemLongClickListener) {
        this.mOnItemLongClickListener = onItemLongClickListener;
    }

    public void setOnItemChildClickListener(OnItemChildClickListener<T> onItemChildClickListener) {
        this.mOnItemChildClickListener = onItemChildClickListener;
    }

    public void bindChildClickViewIds(int... iArr) {
        this.childClickViewIds = new LinkedHashSet<>();
        for (int i : iArr) {
            this.childClickViewIds.add(Integer.valueOf(i));
        }
    }

    public RecyclerAdapter(Context context, List<T> list, int i) {
        this.select = -1;
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mLayoutId = i;
        this.mData = list;
    }

    public RecyclerAdapter(Context context, List<T> list) {
        this.select = -1;
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mLayoutId = layoutId();
        this.mData = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        RecyclerViewHolder recyclerViewHolder = RecyclerViewHolder.get(this.mContext, null, viewGroup, this.mLayoutId, -1);
        setListener(viewGroup, recyclerViewHolder, i);
        return recyclerViewHolder;
    }

    protected int getPosition(RecyclerViewHolder recyclerViewHolder) {
        return recyclerViewHolder.getAbsoluteAdapterPosition();
    }

    protected void setListener(final ViewGroup viewGroup, final RecyclerViewHolder recyclerViewHolder, int i) {
        recyclerViewHolder.getConvertView().setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.baselib.base.recycleview.RecyclerAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ViewUtils.filter(500L) || RecyclerAdapter.this.getPosition(recyclerViewHolder) == -1 || RecyclerAdapter.this.mOnItemClickListener == null) {
                    return;
                }
                int position = RecyclerAdapter.this.getPosition(recyclerViewHolder);
                RecyclerAdapter.this.mOnItemClickListener.onItemClick(viewGroup, view, RecyclerAdapter.this.mData.get(position), position);
            }
        });
        recyclerViewHolder.getConvertView().setOnLongClickListener(new View.OnLongClickListener() { // from class: com.wifiled.baselib.base.recycleview.RecyclerAdapter.2
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                Log.v("ruis", "viewHolder.getConvertView().setOnLongClickListener");
                int position = RecyclerAdapter.this.getPosition(recyclerViewHolder);
                if (RecyclerAdapter.this.mOnItemLongClickListener == null || position == -1) {
                    return false;
                }
                return RecyclerAdapter.this.mOnItemLongClickListener.onItemLongClick(viewGroup, view, RecyclerAdapter.this.mData.get(position), position);
            }
        });
        LinkedHashSet<Integer> linkedHashSet = this.childClickViewIds;
        if (linkedHashSet == null || linkedHashSet.isEmpty()) {
            return;
        }
        Iterator<Integer> it = this.childClickViewIds.iterator();
        while (it.hasNext()) {
            recyclerViewHolder.getView(it.next().intValue()).setOnClickListener(new View.OnClickListener() { // from class: com.wifiled.baselib.base.recycleview.RecyclerAdapter.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (ViewUtils.filter(500L) || RecyclerAdapter.this.getPosition(recyclerViewHolder) == -1 || RecyclerAdapter.this.mOnItemChildClickListener == null) {
                        return;
                    }
                    int position = RecyclerAdapter.this.getPosition(recyclerViewHolder);
                    RecyclerAdapter.this.mOnItemChildClickListener.onItemChildClick(viewGroup, view, RecyclerAdapter.this.mData.get(position), position);
                }
            });
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerViewHolder recyclerViewHolder, int i) {
        recyclerViewHolder.updatePosition(i);
        convert(recyclerViewHolder, this.mData.get(i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (this.mData.size() != 0) {
            return this.mData.size();
        }
        return 0;
    }
}
