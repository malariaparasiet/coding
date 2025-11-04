package com.wifiled.ipixels.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.wifiled.ipixels.ui.text.vo.TextEmojiVO;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TextEmojiView.kt */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\u0012\u0010\u0013\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\u0012\u0010\u0014\u001a\u00020\u00102\b\u0010\u0015\u001a\u0004\u0018\u00010\u0012H\u0016J\u0018\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\n2\b\b\u0002\u0010\u0019\u001a\u00020\u001aJ\u0006\u0010\u001b\u001a\u00020\u0017J\u0014\u0010\u001c\u001a\u00020\u00172\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\n0\u001eJ\u0006\u0010\u001f\u001a\u00020\u0017J\u0006\u0010 \u001a\u00020\u0017R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/wifiled/ipixels/view/TextEmojiView;", "Landroidx/recyclerview/widget/RecyclerView;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "data", "", "Lcom/wifiled/ipixels/ui/text/vo/TextEmojiVO;", "getData", "()Ljava/util/List;", "textEmojiAdapter", "Lcom/wifiled/ipixels/view/TextEmojiAdapter;", "onTouchEvent", "", "e", "Landroid/view/MotionEvent;", "onInterceptTouchEvent", "dispatchTouchEvent", "ev", "addTextEmoji", "", "textEmojiVO", "page", "", "clear", "addTextEmojis", "textEmojiVOs", "", "deleteTextEmoji", "removeAllTextEmojis", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TextEmojiView extends RecyclerView {
    private final List<TextEmojiVO> data;
    private TextEmojiAdapter textEmojiAdapter;

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent e) {
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    public boolean onTouchEvent(MotionEvent e) {
        return false;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TextEmojiView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        ArrayList arrayList = new ArrayList();
        this.data = arrayList;
        this.textEmojiAdapter = new TextEmojiAdapter(context, arrayList);
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(context);
        flexboxLayoutManager.setFlexDirection(0);
        flexboxLayoutManager.setJustifyContent(0);
        setLayoutManager(flexboxLayoutManager);
        setAdapter(this.textEmojiAdapter);
    }

    public final List<TextEmojiVO> getData() {
        return this.data;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    public static /* synthetic */ void addTextEmoji$default(TextEmojiView textEmojiView, TextEmojiVO textEmojiVO, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        textEmojiView.addTextEmoji(textEmojiVO, i);
    }

    public final void addTextEmoji(TextEmojiVO textEmojiVO, int page) {
        Intrinsics.checkNotNullParameter(textEmojiVO, "textEmojiVO");
        this.data.add(textEmojiVO);
        this.textEmojiAdapter.setMItemCount(0);
        this.textEmojiAdapter.setMToatalDp(0.0f);
        this.textEmojiAdapter.notifyDataSetChanged();
    }

    public final void clear() {
        this.data.clear();
    }

    public final void addTextEmojis(List<TextEmojiVO> textEmojiVOs) {
        Intrinsics.checkNotNullParameter(textEmojiVOs, "textEmojiVOs");
        Iterator<T> it = textEmojiVOs.iterator();
        while (it.hasNext()) {
            this.data.add((TextEmojiVO) it.next());
        }
        this.textEmojiAdapter.setMItemCount(0);
        this.textEmojiAdapter.setMToatalDp(0.0f);
        this.textEmojiAdapter.notifyDataSetChanged();
    }

    public final void deleteTextEmoji() {
        if (this.data.isEmpty()) {
            return;
        }
        int lastIndex = CollectionsKt.getLastIndex(this.data);
        this.data.remove(lastIndex);
        this.textEmojiAdapter.setMItemCount(0);
        this.textEmojiAdapter.setMToatalDp(0.0f);
        this.textEmojiAdapter.notifyItemRemoved(lastIndex);
    }

    public final void removeAllTextEmojis() {
        if (this.data.isEmpty()) {
            return;
        }
        this.data.clear();
        this.textEmojiAdapter.setMItemCount(0);
        this.textEmojiAdapter.setMToatalDp(0.0f);
        this.textEmojiAdapter.notifyDataSetChanged();
    }
}
