package com.wifiled.ipixels.view.video_clip;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.wifiled.ipixels.R;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class VideoTrimmerAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<Bitmap> mBitmaps = new ArrayList();
    private LayoutInflater mInflater;

    public VideoTrimmerAdapter(Context context) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TrimmerViewHolder(this.mInflater.inflate(R.layout.video_thumb_item_layout, parent, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((TrimmerViewHolder) holder).thumbImageView.setImageBitmap(this.mBitmaps.get(position));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mBitmaps.size();
    }

    public void addBitmaps(Bitmap bitmap) {
        this.mBitmaps.add(bitmap);
        notifyDataSetChanged();
    }

    public void clear() {
        this.mBitmaps.clear();
    }

    private static final class TrimmerViewHolder extends RecyclerView.ViewHolder {
        ImageView thumbImageView;

        TrimmerViewHolder(View itemView) {
            super(itemView);
            ImageView imageView = (ImageView) itemView.findViewById(R.id.thumb);
            this.thumbImageView = imageView;
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) imageView.getLayoutParams();
            layoutParams.width = VideoTrimmerUtil.VIDEO_FRAMES_WIDTH / 10;
            this.thumbImageView.setLayoutParams(layoutParams);
        }
    }
}
