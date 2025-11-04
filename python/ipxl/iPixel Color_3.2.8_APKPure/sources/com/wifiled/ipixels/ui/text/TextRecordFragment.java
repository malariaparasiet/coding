package com.wifiled.ipixels.ui.text;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewGroupKt;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.LogUtils;
import com.wifiled.baselib.app.cache.ACache;
import com.wifiled.baselib.base.BaseFragment;
import com.wifiled.baselib.base.recycleview.RecyclerAdapter;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.UtilsExtensionKt;
import com.wifiled.ipixels.UtilsExtensionKt$appViewModels$1;
import com.wifiled.ipixels.UtilsExtensionKt$appViewModels$2;
import com.wifiled.ipixels.event.TextEmojiBuilder;
import com.wifiled.ipixels.ui.text.vo.CheckContent;
import com.wifiled.ipixels.ui.text.vo.TextEmojiVO;
import com.wifiled.ipixels.ui.text.vo.TextHistoryVO;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.greenrobot.eventbus.EventBus;

/* compiled from: TextRecordFragment.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0014J\b\u0010\u0014\u001a\u00020\u0015H\u0014J\b\u0010\u0016\u001a\u00020\u0015H\u0014J\u0010\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u0015H\u0016J\b\u0010\u001b\u001a\u00020\u0015H\u0002J\b\u0010\u001c\u001a\u00020\u0015H\u0002J\b\u0010\u001d\u001a\u00020\u0015H\u0014R\u001e\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\f\u001a\u00020\r8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/wifiled/ipixels/ui/text/TextRecordFragment;", "Lcom/wifiled/baselib/base/BaseFragment;", "<init>", "()V", "layoutId", "", "historyList", "Ljava/util/ArrayList;", "Lcom/wifiled/ipixels/ui/text/vo/TextHistoryVO;", "Lkotlin/collections/ArrayList;", "historyAdapter", "Lcom/wifiled/ipixels/ui/text/HistoryAdapter;", "textViewModel", "Lcom/wifiled/ipixels/ui/text/TextViewModel;", "getTextViewModel", "()Lcom/wifiled/ipixels/ui/text/TextViewModel;", "textViewModel$delegate", "Lkotlin/Lazy;", "recyclerviewHistory", "Landroidx/recyclerview/widget/RecyclerView;", "bindData", "", "initView", "onAttach", "context", "Landroid/content/Context;", "onDetach", "initHistoryData", "initEvent", "bindListener", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TextRecordFragment extends BaseFragment {
    private HistoryAdapter historyAdapter;
    private ArrayList<TextHistoryVO> historyList = new ArrayList<>();
    private RecyclerView recyclerviewHistory;

    /* renamed from: textViewModel$delegate, reason: from kotlin metadata */
    private final Lazy textViewModel;

    @Override // com.wifiled.baselib.base.BaseFragment
    protected int layoutId() {
        return R.layout.fragment_text_record;
    }

    public TextRecordFragment() {
        TextRecordFragment textRecordFragment = this;
        this.textViewModel = FragmentViewModelLazyKt.createViewModelLazy(textRecordFragment, Reflection.getOrCreateKotlinClass(TextViewModel.class), UtilsExtensionKt$appViewModels$1.INSTANCE, new UtilsExtensionKt$appViewModels$2(textRecordFragment), null);
    }

    private final TextViewModel getTextViewModel() {
        return (TextViewModel) this.textViewModel.getValue();
    }

    @Override // com.wifiled.baselib.base.BaseFragment
    protected void bindData() {
        LogUtils.i(this.TAG + ">>>[bindData]: ");
        FragmentActivity fragmentActivity = this.mWeakActivity.get();
        Intrinsics.checkNotNull(fragmentActivity);
        this.historyAdapter = new HistoryAdapter(fragmentActivity, this.historyList);
        RecyclerView recyclerView = this.recyclerviewHistory;
        RecyclerView recyclerView2 = null;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerviewHistory");
            recyclerView = null;
        }
        HistoryAdapter historyAdapter = this.historyAdapter;
        if (historyAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("historyAdapter");
            historyAdapter = null;
        }
        recyclerView.setAdapter(historyAdapter);
        RecyclerView recyclerView3 = this.recyclerviewHistory;
        if (recyclerView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerviewHistory");
        } else {
            recyclerView2 = recyclerView3;
        }
        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));
        initEvent();
        int size = CheckContent.INSTANCE.getCacheTextHistoryVOs().size();
        LogUtils.d("#akon# cacheTextHistoryVOs size: " + size);
        if (size > 0) {
            this.historyList.addAll(CheckContent.INSTANCE.getCacheTextHistoryVOs());
        } else {
            initHistoryData();
        }
    }

    @Override // com.wifiled.baselib.base.BaseFragment
    protected void initView() {
        super.initView();
        View findViewById = this.mRootView.findViewById(R.id.recyclerviewHistory);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.recyclerviewHistory = (RecyclerView) findViewById;
    }

    @Override // com.wifiled.baselib.base.BaseFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onAttach(context);
        LogUtils.d("#akon# run onAttach");
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        this.mActivity = null;
        LogUtils.d("#akon# run onDetach");
    }

    private final void initHistoryData() {
        ArrayList arrayList;
        try {
            arrayList = ACache.get(getContext()).getAsList("text_history", TextHistoryVO.class);
        } catch (Exception e) {
            e.printStackTrace();
            arrayList = null;
        }
        if (arrayList == null) {
            arrayList = new ArrayList();
        }
        this.historyList.addAll(arrayList);
    }

    private final void initEvent() {
        getTextViewModel().getTextRecordAddLiveData().observe(this, new TextRecordFragment$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: com.wifiled.ipixels.ui.text.TextRecordFragment$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit initEvent$lambda$0;
                initEvent$lambda$0 = TextRecordFragment.initEvent$lambda$0(TextRecordFragment.this, (TextHistoryVO) obj);
                return initEvent$lambda$0;
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initEvent$lambda$0(TextRecordFragment textRecordFragment, TextHistoryVO textHistoryVO) {
        textRecordFragment.historyList.clear();
        textRecordFragment.initHistoryData();
        Iterator<TextHistoryVO> it = textRecordFragment.historyList.iterator();
        Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
        boolean z = false;
        int i = -1;
        while (it.hasNext()) {
            i++;
            TextHistoryVO next = it.next();
            Intrinsics.checkNotNullExpressionValue(next, "next(...)");
            z = TextEmojiBuilder.INSTANCE.checkListEqual(next.getTextEmojiVOs(), textHistoryVO.getEventText().getTextEmojiVO());
            if (z) {
                textRecordFragment.historyList.set(i, textHistoryVO);
            }
        }
        if (!z) {
            textRecordFragment.historyList.add(textHistoryVO);
        }
        if (textRecordFragment.historyList.size() != 0) {
            HistoryAdapter historyAdapter = textRecordFragment.historyAdapter;
            if (historyAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("historyAdapter");
                historyAdapter = null;
            }
            historyAdapter.notifyItemChanged(textRecordFragment.historyList.size() - 1);
        }
        return Unit.INSTANCE;
    }

    @Override // com.wifiled.baselib.base.BaseFragment
    protected void bindListener() {
        super.bindListener();
        HistoryAdapter historyAdapter = this.historyAdapter;
        HistoryAdapter historyAdapter2 = null;
        if (historyAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("historyAdapter");
            historyAdapter = null;
        }
        historyAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() { // from class: com.wifiled.ipixels.ui.text.TextRecordFragment$$ExternalSyntheticLambda2
            @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter.OnItemClickListener
            public final void onItemClick(ViewGroup viewGroup, View view, Object obj, int i) {
                TextRecordFragment.bindListener$lambda$4(TextRecordFragment.this, viewGroup, view, (TextHistoryVO) obj, i);
            }
        });
        HistoryAdapter historyAdapter3 = this.historyAdapter;
        if (historyAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("historyAdapter");
        } else {
            historyAdapter2 = historyAdapter3;
        }
        historyAdapter2.setOnItemLongClickListener(new RecyclerAdapter.OnItemLongClickListener() { // from class: com.wifiled.ipixels.ui.text.TextRecordFragment$$ExternalSyntheticLambda3
            @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter.OnItemLongClickListener
            public final boolean onItemLongClick(ViewGroup viewGroup, View view, Object obj, int i) {
                boolean bindListener$lambda$6;
                bindListener$lambda$6 = TextRecordFragment.bindListener$lambda$6(TextRecordFragment.this, viewGroup, view, (TextHistoryVO) obj, i);
                return bindListener$lambda$6;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$4(TextRecordFragment textRecordFragment, ViewGroup viewGroup, View view, final TextHistoryVO textHistoryVO, int i) {
        LogUtils.file("TextActivity TextRecordFragment historyAdapter.setOnItemClickListener");
        Intrinsics.checkNotNull(viewGroup);
        Iterator<View> it = ViewGroupKt.iterator(viewGroup);
        while (it.hasNext()) {
            it.next().setSelected(false);
        }
        view.setSelected(true);
        HistoryAdapter historyAdapter = textRecordFragment.historyAdapter;
        HistoryAdapter historyAdapter2 = null;
        if (historyAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("historyAdapter");
            historyAdapter = null;
        }
        historyAdapter.setM_iSelect(i);
        HistoryAdapter historyAdapter3 = textRecordFragment.historyAdapter;
        if (historyAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("historyAdapter");
        } else {
            historyAdapter2 = historyAdapter3;
        }
        historyAdapter2.notifyItemChanged(i);
        String string = textRecordFragment.getString(R.string.run_input);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        StringBuilder sb = new StringBuilder();
        Intrinsics.checkNotNull(textHistoryVO, "null cannot be cast to non-null type com.wifiled.ipixels.ui.text.vo.TextHistoryVO");
        Iterator<T> it2 = textHistoryVO.getEventText().getTextEmojiVO().iterator();
        while (it2.hasNext()) {
            sb.append(((TextEmojiVO) it2.next()).getText());
        }
        if (Intrinsics.areEqual(string, sb.toString())) {
            Iterator<T> it3 = textHistoryVO.getEventText().getTextEmojiVO().iterator();
            while (it3.hasNext()) {
                ((TextEmojiVO) it3.next()).setType(0);
            }
        }
        UtilsExtensionKt.async(new Function0() { // from class: com.wifiled.ipixels.ui.text.TextRecordFragment$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit bindListener$lambda$4$lambda$3;
                bindListener$lambda$4$lambda$3 = TextRecordFragment.bindListener$lambda$4$lambda$3(TextHistoryVO.this);
                return bindListener$lambda$4$lambda$3;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit bindListener$lambda$4$lambda$3(TextHistoryVO textHistoryVO) {
        EventBus.getDefault().postSticky(textHistoryVO);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean bindListener$lambda$6(final TextRecordFragment textRecordFragment, ViewGroup viewGroup, View view, TextHistoryVO textHistoryVO, final int i) {
        LogUtils.file("TextActivity TextRecordFragment historyAdapter.setOnItemLongClickListener");
        String string = textRecordFragment.getString(R.string.is_delete);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        UtilsExtensionKt.showDialog$default(textRecordFragment, null, string, new Function0() { // from class: com.wifiled.ipixels.ui.text.TextRecordFragment$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit bindListener$lambda$6$lambda$5;
                bindListener$lambda$6$lambda$5 = TextRecordFragment.bindListener$lambda$6$lambda$5(TextRecordFragment.this, i);
                return bindListener$lambda$6$lambda$5;
            }
        }, 1, null);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit bindListener$lambda$6$lambda$5(TextRecordFragment textRecordFragment, int i) {
        textRecordFragment.historyList.remove(i);
        HistoryAdapter historyAdapter = textRecordFragment.historyAdapter;
        if (historyAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("historyAdapter");
            historyAdapter = null;
        }
        historyAdapter.notifyItemRemoved(i);
        ACache.get(textRecordFragment.getActivity()).put("text_history", textRecordFragment.historyList);
        return Unit.INSTANCE;
    }
}
