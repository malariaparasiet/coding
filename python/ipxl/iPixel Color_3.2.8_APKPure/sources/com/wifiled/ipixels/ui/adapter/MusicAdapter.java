package com.wifiled.ipixels.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;
import android.widget.TextView;
import com.wifiled.baselib.base.recycleview.RecyclerAdapter;
import com.wifiled.baselib.base.recycleview.RecyclerViewHolder;
import com.wifiled.baselib.utils.LogUtils;
import com.wifiled.ipixels.R;
import com.wifiled.musiclib.player.constant.PlayerFinal;
import com.wifiled.musiclib.vo.MusicVO;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MusicAdapter.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u001c\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0002H\u0016J\b\u0010\u0016\u001a\u00020\fH\u0016J\u000e\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\u0018\u001a\u00020\fJ\u000e\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\fR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/wifiled/ipixels/ui/adapter/MusicAdapter;", "Lcom/wifiled/baselib/base/recycleview/RecyclerAdapter;", "Lcom/wifiled/musiclib/vo/MusicVO;", "context", "Landroid/content/Context;", "datas", "", "<init>", "(Landroid/content/Context;Ljava/util/List;)V", "getContext", "()Landroid/content/Context;", "playPosition", "", "isPlaying", "", "animationDrawable", "Landroid/graphics/drawable/AnimationDrawable;", "convert", "", "holder", "Lcom/wifiled/baselib/base/recycleview/RecyclerViewHolder;", "musicVO", "layoutId", "setPlayPosition", "getPlayPosition", "onStateChange", PlayerFinal.STATE, "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MusicAdapter extends RecyclerAdapter<MusicVO> {
    private AnimationDrawable animationDrawable;
    private final Context context;
    private boolean isPlaying;
    private int playPosition;

    @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter
    public int layoutId() {
        return R.layout.adapter_music;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MusicAdapter(Context context, List<MusicVO> list) {
        super(context, list);
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.playPosition = -1;
    }

    public final Context getContext() {
        return this.context;
    }

    @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter
    public void convert(RecyclerViewHolder holder, MusicVO musicVO) {
        ImageView imageView = holder != null ? (ImageView) holder.getView(R.id.iv_music_state) : null;
        TextView textView = holder != null ? (TextView) holder.getView(R.id.tv_name) : null;
        if (imageView != null) {
            imageView.setImageResource(R.drawable.anim_musiclist_play);
        }
        this.animationDrawable = (AnimationDrawable) (imageView != null ? imageView.getDrawable() : null);
        if (getPosition(holder) == this.playPosition) {
            if (imageView != null) {
                imageView.setVisibility(0);
            }
            if (textView != null) {
                textView.setTextColor(Color.parseColor("#FF00FC"));
            }
            LogUtils.logi("MusicAdapter>>>[convert]: " + this.isPlaying, new Object[0]);
            if (this.isPlaying) {
                AnimationDrawable animationDrawable = this.animationDrawable;
                if (animationDrawable != null) {
                    animationDrawable.start();
                }
            } else {
                AnimationDrawable animationDrawable2 = this.animationDrawable;
                if (animationDrawable2 != null) {
                    animationDrawable2.stop();
                }
            }
        } else {
            if (imageView != null) {
                imageView.setVisibility(4);
            }
            if (textView != null) {
                textView.setTextColor(Color.parseColor("#FFFFFF"));
            }
        }
        if (textView != null) {
            textView.setText(musicVO != null ? musicVO.title : null);
        }
    }

    public final void setPlayPosition(int playPosition) {
        this.playPosition = playPosition;
    }

    public final int getPlayPosition() {
        return this.playPosition;
    }

    public final void onStateChange(int state) {
        boolean z = true;
        if (state != 4 && state != 1) {
            z = false;
        }
        this.isPlaying = z;
    }
}
