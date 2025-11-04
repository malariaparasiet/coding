package com.wifiled.ipixels.ui.text.adapter;

import android.content.Context;
import com.wifiled.baselib.base.recycleview.RecyclerAdapter;
import com.wifiled.baselib.base.recycleview.RecyclerViewHolder;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.event.TextEmojiBuilder;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ModeAdapter.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B%\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\b\u0010\u0015\u001a\u00020\bH\u0016J\u0018\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0002H\u0016R\u001a\u0010\u000b\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR\u001a\u0010\u0012\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\f\"\u0004\b\u0014\u0010\u000e¨\u0006\u001b"}, d2 = {"Lcom/wifiled/ipixels/ui/text/adapter/ModeAdapter;", "Lcom/wifiled/baselib/base/recycleview/RecyclerAdapter;", "", "context", "Landroid/content/Context;", "data", "", "fromType", "", "<init>", "(Landroid/content/Context;Ljava/util/List;I)V", "isSelect", "()I", "setSelect", "(I)V", "mLanguageType", "getMLanguageType", "setMLanguageType", "mFromType", "getMFromType", "setMFromType", "layoutId", "convert", "", "holder", "Lcom/wifiled/baselib/base/recycleview/RecyclerViewHolder;", "bean", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ModeAdapter extends RecyclerAdapter<String> {
    private int isSelect;
    private int mFromType;
    private int mLanguageType;

    @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter
    public int layoutId() {
        return R.layout.item_text_effect;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ModeAdapter(Context context, List<String> data, int i) {
        super(context, data);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(data, "data");
        this.mFromType = i;
    }

    /* renamed from: isSelect, reason: from getter */
    public final int getIsSelect() {
        return this.isSelect;
    }

    public final void setSelect(int i) {
        this.isSelect = i;
    }

    public final int getMLanguageType() {
        return this.mLanguageType;
    }

    public final void setMLanguageType(int i) {
        this.mLanguageType = i;
    }

    public final int getMFromType() {
        return this.mFromType;
    }

    public final void setMFromType(int i) {
        this.mFromType = i;
    }

    @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter
    public void convert(RecyclerViewHolder holder, String bean) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(bean, "bean");
        holder.setText(R.id.tv_effect, bean);
        boolean z = this.isSelect == getPosition(holder);
        if (z) {
            holder.getConvertView().setSelected(true);
        } else {
            if (z) {
                throw new NoWhenBranchMatchedException();
            }
            holder.getConvertView().setSelected(false);
        }
        int i = this.mLanguageType;
        if (i == 1) {
            if (getPosition(holder) == 2) {
                holder.setAlpha(R.id.cl_effect_frame, 1.0f);
                return;
            } else {
                holder.setAlpha(R.id.cl_effect_frame, 0.6f);
                return;
            }
        }
        if (i == 2) {
            if (getPosition(holder) == 1) {
                holder.setAlpha(R.id.cl_effect_frame, 1.0f);
                return;
            } else {
                holder.setAlpha(R.id.cl_effect_frame, 0.6f);
                return;
            }
        }
        if (AppConfig.INSTANCE.getLedType() == 6 || AppConfig.INSTANCE.getLedType() == 7 || AppConfig.INSTANCE.getLedType() == 8 || AppConfig.INSTANCE.getLedType() == 12 || AppConfig.INSTANCE.getLedType() == 13 || AppConfig.INSTANCE.getLedType() == 14 || AppConfig.INSTANCE.getLedType() == 15) {
            if (TextEmojiBuilder.INSTANCE.getEventText().getTextdirction() == 1) {
                int position = getPosition(holder);
                if (position == 1 || position == 2 || position == 7) {
                    holder.setAlpha(R.id.cl_effect_frame, 0.6f);
                } else {
                    holder.setAlpha(R.id.cl_effect_frame, 1.0f);
                }
            } else {
                int position2 = getPosition(holder);
                if (position2 == 3 || position2 == 4) {
                    holder.setAlpha(R.id.cl_effect_frame, 0.6f);
                } else {
                    holder.setAlpha(R.id.cl_effect_frame, 1.0f);
                }
            }
        } else if (AppConfig.INSTANCE.getLedType() != 2 && AppConfig.INSTANCE.getLedType() != 0) {
            if (TextEmojiBuilder.INSTANCE.getEventText().getTextdirction() == 1) {
                if (this.mFromType == 1002) {
                    int position3 = getPosition(holder);
                    if (position3 == 1 || position3 == 2 || position3 == 6 || position3 == 7) {
                        holder.setAlpha(R.id.cl_effect_frame, 0.6f);
                    } else {
                        holder.setAlpha(R.id.cl_effect_frame, 1.0f);
                    }
                } else {
                    int position4 = getPosition(holder);
                    if (position4 == 1 || position4 == 2 || position4 == 7) {
                        holder.setAlpha(R.id.cl_effect_frame, 0.6f);
                    } else {
                        holder.setAlpha(R.id.cl_effect_frame, 1.0f);
                    }
                }
            } else if (this.mFromType == 1002) {
                int position5 = getPosition(holder);
                if (position5 == 3 || position5 == 4 || position5 == 6) {
                    holder.setAlpha(R.id.cl_effect_frame, 0.6f);
                } else {
                    holder.setAlpha(R.id.cl_effect_frame, 1.0f);
                }
            } else {
                int position6 = getPosition(holder);
                if (position6 == 3 || position6 == 4) {
                    holder.setAlpha(R.id.cl_effect_frame, 0.6f);
                } else {
                    holder.setAlpha(R.id.cl_effect_frame, 1.0f);
                }
            }
        }
        Unit unit = Unit.INSTANCE;
    }
}
