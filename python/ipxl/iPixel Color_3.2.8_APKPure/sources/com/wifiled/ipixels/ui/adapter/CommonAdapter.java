package com.wifiled.ipixels.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* loaded from: classes3.dex */
public abstract class CommonAdapter<T> extends BaseAdapter {
    private GetPos getPos;
    protected Context mContext;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;
    protected final int mItemLayoutId;
    private SetListener setListener;

    public interface GetPos {
        void getPos(int pos);
    }

    public interface SetListener {
        void onLastPosition(ViewHolder viewHolder);

        void onOtherPosition(ViewHolder viewHolder);
    }

    public abstract void convert(ViewHolder helper, T item);

    @Override // android.widget.Adapter
    public long getItemId(int position) {
        return position;
    }

    public CommonAdapter(Context context, List<T> mDatas, int itemLayoutId) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mDatas = mDatas;
        this.mItemLayoutId = itemLayoutId;
    }

    public CommonAdapter(Context context, int itemLayoutId) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mItemLayoutId = itemLayoutId;
        this.mDatas = new ArrayList();
    }

    public void refreshDatas(List<T> datas) {
        this.mDatas.clear();
        if (datas != null && !datas.isEmpty()) {
            this.mDatas.addAll(datas);
        }
        notifyDataSetChanged();
    }

    public void clearDatas() {
        this.mDatas.clear();
        notifyDataSetChanged();
    }

    public void removeData(int position) {
        this.mDatas.remove(position);
        notifyDataSetChanged();
    }

    public void addData(T data) {
        this.mDatas.add(data);
        notifyDataSetChanged();
    }

    public void moveData(int from, int to) {
        this.mDatas.add(to, this.mDatas.remove(from));
        notifyDataSetChanged();
    }

    public void addMoreDatas(List<T> datas) {
        if (datas != null && !datas.isEmpty()) {
            this.mDatas.addAll(datas);
        }
        notifyDataSetChanged();
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.mDatas.size();
    }

    @Override // android.widget.Adapter
    public T getItem(int position) {
        return this.mDatas.get(position);
    }

    @Override // android.widget.Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = getViewHolder(position, convertView, parent);
        GetPos getPos = this.getPos;
        if (getPos != null) {
            getPos.getPos(position);
        }
        convert(viewHolder, getItem(position), position);
        if (this.setListener != null) {
            if (position == this.mDatas.size() - 1) {
                this.setListener.onLastPosition(viewHolder);
            } else {
                this.setListener.onOtherPosition(viewHolder);
            }
        }
        return viewHolder.getConvertView();
    }

    public void setListener(SetListener setListener) {
        this.setListener = setListener;
    }

    public void convert(ViewHolder helper, T item, int position) {
        convert(helper, item);
    }

    public void addAll(Collection<? extends T> list) {
        this.mDatas.clear();
        this.mDatas.addAll(list);
        notifyDataSetChanged();
    }

    public void setGetPosListener(GetPos getPos) {
        this.getPos = getPos;
    }

    private ViewHolder getViewHolder(int position, View convertView, ViewGroup parent) {
        return ViewHolder.get(this.mContext, convertView, parent, this.mItemLayoutId, position);
    }

    public List<T> getList() {
        return this.mDatas;
    }
}
