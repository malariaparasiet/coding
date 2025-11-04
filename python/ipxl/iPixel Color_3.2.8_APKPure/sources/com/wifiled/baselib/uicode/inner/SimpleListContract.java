package com.wifiled.baselib.uicode.inner;

import androidx.exifinterface.media.ExifInterface;
import java.util.List;
import kotlin.Metadata;

/* compiled from: SimpleListContract.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0002\u0002\u0003¨\u0006\u0004À\u0006\u0003"}, d2 = {"Lcom/wifiled/baselib/uicode/inner/SimpleListContract;", "", "SimpleListView", "ISimpleListViewModel", "baselib_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface SimpleListContract {

    /* compiled from: SimpleListContract.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006À\u0006\u0003"}, d2 = {"Lcom/wifiled/baselib/uicode/inner/SimpleListContract$ISimpleListViewModel;", "", "getData", "", "isRefresh", "", "baselib_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface ISimpleListViewModel {
        void getData(boolean isRefresh);
    }

    /* compiled from: SimpleListContract.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0004\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006H&J\b\u0010\u0007\u001a\u00020\u0004H&J\b\u0010\b\u001a\u00020\u0004H&J\b\u0010\t\u001a\u00020\u0004H&¨\u0006\nÀ\u0006\u0003"}, d2 = {"Lcom/wifiled/baselib/uicode/inner/SimpleListContract$SimpleListView;", ExifInterface.GPS_DIRECTION_TRUE, "", "pushData", "", "list", "", "showEmpty", "showError", "showNoMore", "baselib_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface SimpleListView<T> {
        void pushData(List<T> list);

        void showEmpty();

        void showError();

        void showNoMore();
    }
}
