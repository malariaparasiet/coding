package com.wifiled.ipixels.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.bumptech.glide.Glide;
import com.wifiled.baselib.utils.LogUtils;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.bean.RhythmImage;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/* loaded from: classes3.dex */
public class ViewpagerAdapter extends PagerAdapter {
    private static final String TAG = "ViewpagerAdapter";
    private Context mContext;
    private LayoutInflater mInflater;
    private List<RhythmImage> mList;
    private final int mMaxNumber;
    private View mShowView;
    private ViewGroup mVg;
    private ViewPager mVp;
    private boolean mIsShow = false;
    private int mCurIndex = -1;
    private boolean mbMediaState = false;
    private HashMap<Integer, View> mMapViews = new HashMap<>();

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public ViewpagerAdapter(Context context, ViewPager vp, List<RhythmImage> list) {
        this.mContext = context;
        this.mVp = vp;
        this.mList = list;
        this.mInflater = LayoutInflater.from(context);
        if (list.size() > 9) {
            this.mMaxNumber = 9;
        } else {
            this.mMaxNumber = list.size();
        }
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup container, final int position) {
        View inflate;
        LogUtils.logi("ViewpagerAdapter>>>[instantiateItem] position: " + position, new Object[0]);
        int i = position % this.mMaxNumber;
        LogUtils.logi("ViewpagerAdapter>>>[instantiateItem] index : " + i, new Object[0]);
        RhythmImage rhythmImage = this.mList.get(i);
        if (AppConfig.INSTANCE.getLedType() == 2 || AppConfig.INSTANCE.getLedType() == 0) {
            inflate = this.mInflater.inflate(R.layout.item_rhy_image, (ViewGroup) null);
        } else {
            inflate = this.mInflater.inflate(R.layout.item_rhy_image2, (ViewGroup) null);
        }
        ((ImageView) inflate.findViewById(R.id.iv_rhy_image)).setImageResource(rhythmImage.getImageRes());
        inflate.setTag(Integer.valueOf(position));
        container.addView(inflate);
        this.mVg = container;
        LogUtils.logi("ViewpagerAdapter>>>[instantiateItem] getChildCount: " + container.getChildCount(), new Object[0]);
        return inflate;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        String str;
        LogUtils.logi("ViewpagerAdapter>>>[setPrimaryItem]: " + container.getChildAt(position % this.mList.size()) + " position: " + (position % this.mList.size()) + "   object:" + object, new Object[0]);
        int size = position % this.mList.size();
        this.mCurIndex = size;
        this.mShowView = (View) object;
        this.mMapViews.put(Integer.valueOf(size), this.mShowView);
        ImageView imageView = (ImageView) this.mShowView.findViewById(R.id.iv_rhy_image);
        int i = this.mCurIndex;
        if (i >= 5 && this.mbMediaState) {
            if (i == 5) {
                if (AppConfig.INSTANCE.getLedType() == 2 || AppConfig.INSTANCE.getLedType() == 0) {
                    str = "file:///android_asset/gif/music_02.gif";
                } else {
                    str = "file:///android_asset/gif/rhythm_animation_1664_1.gif";
                }
                if (this.mMapViews.containsKey(6)) {
                    ((ImageView) ((View) Objects.requireNonNull(this.mMapViews.get(6))).findViewById(R.id.iv_rhy_image)).setImageResource(this.mList.get(6).getImageRes());
                }
            } else if (i != 6) {
                str = "";
            } else {
                if (AppConfig.INSTANCE.getLedType() == 2 || AppConfig.INSTANCE.getLedType() == 0) {
                    str = "file:///android_asset/gif/music_01.gif";
                } else {
                    str = "file:///android_asset/gif/rhythm_animation_1664_2.gif";
                }
                if (this.mMapViews.containsKey(5)) {
                    ((ImageView) ((View) Objects.requireNonNull(this.mMapViews.get(5))).findViewById(R.id.iv_rhy_image)).setImageResource(this.mList.get(5).getImageRes());
                }
            }
            Glide.with(this.mContext).asGif().load(str).into(imageView);
            LogUtils.logi("ViewpagerAdapter>>>[setPrimaryItem]: load gift：" + imageView, new Object[0]);
        } else {
            imageView.setImageResource(this.mList.get(i).getImageRes());
        }
        int i2 = this.mCurIndex;
        if (i2 == 0) {
            if (this.mMapViews.containsKey(6)) {
                ((ImageView) ((View) Objects.requireNonNull(this.mMapViews.get(6))).findViewById(R.id.iv_rhy_image)).setImageResource(this.mList.get(6).getImageRes());
            }
        } else if (i2 == 4 && this.mMapViews.containsKey(5)) {
            ((ImageView) ((View) Objects.requireNonNull(this.mMapViews.get(5))).findViewById(R.id.iv_rhy_image)).setImageResource(this.mList.get(5).getImageRes());
        }
    }

    public void setMediaState(boolean state) {
        this.mbMediaState = state;
        RhythmImage rhythmImage = this.mList.get(this.mCurIndex);
        ImageView imageView = (ImageView) ((View) Objects.requireNonNull(this.mMapViews.get(Integer.valueOf(this.mCurIndex)))).findViewById(R.id.iv_rhy_image);
        if (this.mMapViews.containsKey(Integer.valueOf(this.mCurIndex)) && !this.mbMediaState) {
            imageView.setImageResource(rhythmImage.getImageRes());
        }
        if (this.mbMediaState) {
            if (rhythmImage.getGif()) {
                LogUtils.logi("ViewpagerAdapter>>>[instantiateItem]: load gift", new Object[0]);
                Glide.with(this.mContext).asGif().load(rhythmImage.getGifPath()).into(imageView);
                return;
            } else {
                imageView.setImageResource(rhythmImage.getImageRes());
                return;
            }
        }
        imageView.setImageResource(rhythmImage.getImageRes());
    }
}
