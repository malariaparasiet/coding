package com.wifiled.ipixels.ui.game;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.wifiled.baselib.base.BaseFragment;
import com.wifiled.baselib.base.recycleview.RecyclerAdapter;
import com.wifiled.baselib.base.recycleview.RecyclerViewHolder;
import com.wifiled.ipixels.Constants;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.vo.CategoryVO;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.EventBus;

/* compiled from: GameMainFragment.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0014J\b\u0010\u000b\u001a\u00020\fH\u0014J\b\u0010\r\u001a\u00020\fH\u0014J\b\u0010\u000e\u001a\u00020\fH\u0016J\b\u0010\u000f\u001a\u00020\fH\u0014J\b\u0010\u0010\u001a\u00020\fH\u0002R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/wifiled/ipixels/ui/game/GameMainFragment;", "Lcom/wifiled/baselib/base/BaseFragment;", "<init>", "()V", "layoutId", "", "adapter", "Lcom/wifiled/baselib/base/recycleview/RecyclerAdapter;", "Lcom/wifiled/ipixels/vo/CategoryVO;", "recyclerview", "Landroidx/recyclerview/widget/RecyclerView;", "initView", "", "bindData", "onDestroy", "bindListener", "initAdapter", "Companion", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class GameMainFragment extends BaseFragment {
    public static final int PONG = 0;
    public static final int SNAKE = 2;
    public static final int TERTRIS = 1;
    private RecyclerAdapter<CategoryVO> adapter;
    private RecyclerView recyclerview;

    @Override // com.wifiled.baselib.base.BaseFragment
    protected int layoutId() {
        return R.layout.fragment_game_mainpage;
    }

    @Override // com.wifiled.baselib.base.BaseFragment
    protected void initView() {
        super.initView();
        View findViewById = this.mRootView.findViewById(R.id.recyclerview);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.recyclerview = (RecyclerView) findViewById;
    }

    @Override // com.wifiled.baselib.base.BaseFragment
    protected void bindData() {
        ToolbarDataChange toolbarDataChange = new ToolbarDataChange();
        String string = requireContext().getString(R.string.title_game_center);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        toolbarDataChange.setStrTitle(string);
        toolbarDataChange.setShow(false);
        EventBus.getDefault().post(toolbarDataChange);
        initAdapter();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // com.wifiled.baselib.base.BaseFragment
    protected void bindListener() {
        super.bindListener();
        RecyclerAdapter<CategoryVO> recyclerAdapter = this.adapter;
        if (recyclerAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            recyclerAdapter = null;
        }
        recyclerAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() { // from class: com.wifiled.ipixels.ui.game.GameMainFragment$$ExternalSyntheticLambda0
            @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter.OnItemClickListener
            public final void onItemClick(ViewGroup viewGroup, View view, Object obj, int i) {
                GameMainFragment.bindListener$lambda$0(GameMainFragment.this, viewGroup, view, (CategoryVO) obj, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindListener$lambda$0(GameMainFragment gameMainFragment, ViewGroup viewGroup, View view, CategoryVO categoryVO, int i) {
        String string;
        ToolbarDataChange toolbarDataChange = new ToolbarDataChange();
        boolean z = false;
        if (i != 0) {
            if (i == 1) {
                string = gameMainFragment.requireContext().getString(R.string.title_game_tetris);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            } else if (i != 2) {
                string = "";
            } else {
                string = gameMainFragment.requireContext().getString(R.string.title_game_snake);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            }
            z = true;
        } else {
            string = gameMainFragment.requireContext().getString(R.string.title_game_pong);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        }
        toolbarDataChange.setStrTitle(string);
        toolbarDataChange.setShow(z);
        toolbarDataChange.setIndex(i);
        EventBus.getDefault().post(toolbarDataChange);
    }

    private final void initAdapter() {
        RecyclerView recyclerView = this.recyclerview;
        RecyclerAdapter<CategoryVO> recyclerAdapter = null;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerview");
            recyclerView = null;
        }
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        final Context context = getContext();
        Constants constants = Constants.INSTANCE;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext(...)");
        final List<CategoryVO> gameCategoryData = constants.getGameCategoryData(requireContext);
        this.adapter = new RecyclerAdapter<CategoryVO>(context, gameCategoryData) { // from class: com.wifiled.ipixels.ui.game.GameMainFragment$initAdapter$1
            @Override // com.wifiled.baselib.base.recycleview.RecyclerAdapter
            public void convert(RecyclerViewHolder holder, CategoryVO category) {
                Intrinsics.checkNotNullParameter(holder, "holder");
                Intrinsics.checkNotNullParameter(category, "category");
                holder.setText(R.id.tv_category, category.getName());
                holder.setTextColor(R.id.tv_category, -1);
                holder.setImageResource(R.id.iv_category, category.getResId());
                holder.itemView.setLayoutParams(new ViewGroup.LayoutParams(GameMainFragment.this.getResources().getDisplayMetrics().widthPixels / 3, -2));
            }
        };
        RecyclerView recyclerView2 = this.recyclerview;
        if (recyclerView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerview");
            recyclerView2 = null;
        }
        RecyclerAdapter<CategoryVO> recyclerAdapter2 = this.adapter;
        if (recyclerAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            recyclerAdapter = recyclerAdapter2;
        }
        recyclerView2.setAdapter(recyclerAdapter);
    }
}
