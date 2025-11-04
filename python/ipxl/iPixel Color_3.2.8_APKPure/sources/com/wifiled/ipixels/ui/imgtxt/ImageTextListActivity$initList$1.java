package com.wifiled.ipixels.ui.imgtxt;

import android.content.Intent;
import android.view.View;
import com.blankj.utilcode.util.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.wifiled.baselib.utils.ToastUtil;
import com.wifiled.blelibrary.ble.model.BleDevice;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.UtilsExtensionKt;
import com.wifiled.ipixels.core.BleManager;
import com.wifiled.ipixels.core.BleManager2;
import com.wifiled.ipixels.ui.dialog.SelecrDeviceDialog;
import com.wifiled.musiclib.player.constant.PlayerFinal;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ImageTextListActivity.kt */
@Metadata(d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\n"}, d2 = {"com/wifiled/ipixels/ui/imgtxt/ImageTextListActivity$initList$1", "Lcom/chad/library/adapter/base/listener/OnItemClickListener;", "onItemClick", "", "adapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "view", "Landroid/view/View;", PlayerFinal.PLAYER_POSITION, "", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ImageTextListActivity$initList$1 implements OnItemClickListener {
    final /* synthetic */ ImageTextListActivity this$0;

    ImageTextListActivity$initList$1(ImageTextListActivity imageTextListActivity) {
        this.this$0 = imageTextListActivity;
    }

    @Override // com.chad.library.adapter.base.listener.OnItemClickListener
    public void onItemClick(BaseQuickAdapter<?, ?> adapter, View view, final int position) {
        List list;
        List list2;
        boolean z;
        List list3;
        List list4;
        SelecrDeviceDialog selecrDeviceDialog;
        SelecrDeviceDialog selecrDeviceDialog2;
        String str;
        SelecrDeviceDialog selecrDeviceDialog3;
        String bleName;
        int i;
        ImageTextListAdapter imageTextListAdapter;
        ImageTextListAdapter imageTextListAdapter2;
        ImageTextListAdapter imageTextListAdapter3;
        int i2;
        ImageTextListAdapter imageTextListAdapter4;
        int i3;
        ImageTextListAdapter imageTextListAdapter5;
        int i4;
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        Intrinsics.checkNotNullParameter(view, "view");
        list = this.this$0.mDatas;
        LogUtils.file("ImageTextListActivity  mImageTextListAdapter.setOnItemClickListener position= " + position + "    mDatas.size=" + list.size());
        list2 = this.this$0.mDatas;
        if (position != list2.size() - 1) {
            z = this.this$0.mIsEdit;
            if (z) {
                i = this.this$0.mEditSelectPosition;
                ImageTextListAdapter imageTextListAdapter6 = null;
                if (i != -1) {
                    imageTextListAdapter4 = this.this$0.mImageTextListAdapter;
                    if (imageTextListAdapter4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mImageTextListAdapter");
                        imageTextListAdapter4 = null;
                    }
                    List<ImageTextListData> data = imageTextListAdapter4.getData();
                    i3 = this.this$0.mEditSelectPosition;
                    data.get(i3).setEdit(false);
                    imageTextListAdapter5 = this.this$0.mImageTextListAdapter;
                    if (imageTextListAdapter5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mImageTextListAdapter");
                        imageTextListAdapter5 = null;
                    }
                    i4 = this.this$0.mEditSelectPosition;
                    imageTextListAdapter5.notifyItemChanged(i4);
                }
                imageTextListAdapter = this.this$0.mImageTextListAdapter;
                if (imageTextListAdapter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mImageTextListAdapter");
                    imageTextListAdapter = null;
                }
                ImageTextListData imageTextListData = imageTextListAdapter.getData().get(position);
                imageTextListAdapter2 = this.this$0.mImageTextListAdapter;
                if (imageTextListAdapter2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mImageTextListAdapter");
                    imageTextListAdapter2 = null;
                }
                imageTextListData.setEdit(!imageTextListAdapter2.getData().get(position).isEdit());
                this.this$0.mEditSelectPosition = position;
                imageTextListAdapter3 = this.this$0.mImageTextListAdapter;
                if (imageTextListAdapter3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mImageTextListAdapter");
                } else {
                    imageTextListAdapter6 = imageTextListAdapter3;
                }
                i2 = this.this$0.mEditSelectPosition;
                imageTextListAdapter6.notifyItemChanged(i2);
                return;
            }
            if (AppConfig.INSTANCE.getConnectType() == -1 && AppConfig.INSTANCE.getConnectType2() == -1) {
                final ImageTextListActivity imageTextListActivity = this.this$0;
                UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$initList$1$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Unit onItemClick$lambda$0;
                        onItemClick$lambda$0 = ImageTextListActivity$initList$1.onItemClick$lambda$0(ImageTextListActivity.this);
                        return onItemClick$lambda$0;
                    }
                });
                return;
            }
            if (BleManager.INSTANCE.get().getConnectedDevice() != null && BleManager2.INSTANCE.get().getConnectedDevice() != null) {
                selecrDeviceDialog = this.this$0.mSelectDeviceDialog;
                if (selecrDeviceDialog == null) {
                    ImageTextListActivity imageTextListActivity2 = this.this$0;
                    ImageTextListActivity imageTextListActivity3 = this.this$0;
                    BleDevice connectedDevice = BleManager.INSTANCE.get().getConnectedDevice();
                    String str2 = "";
                    if (connectedDevice == null || (str = connectedDevice.getBleName()) == null) {
                        str = "";
                    }
                    BleDevice connectedDevice2 = BleManager2.INSTANCE.get().getConnectedDevice();
                    if (connectedDevice2 != null && (bleName = connectedDevice2.getBleName()) != null) {
                        str2 = bleName;
                    }
                    imageTextListActivity2.mSelectDeviceDialog = new SelecrDeviceDialog(imageTextListActivity3, str, str2);
                    selecrDeviceDialog3 = this.this$0.mSelectDeviceDialog;
                    Intrinsics.checkNotNull(selecrDeviceDialog3);
                    final ImageTextListActivity imageTextListActivity4 = this.this$0;
                    selecrDeviceDialog3.setClickListener(new SelecrDeviceDialog.ClickListener() { // from class: com.wifiled.ipixels.ui.imgtxt.ImageTextListActivity$initList$1$onItemClick$2
                        @Override // com.wifiled.ipixels.ui.dialog.SelecrDeviceDialog.ClickListener
                        public void onCancel() {
                            SelecrDeviceDialog selecrDeviceDialog4;
                            selecrDeviceDialog4 = ImageTextListActivity.this.mSelectDeviceDialog;
                            Intrinsics.checkNotNull(selecrDeviceDialog4);
                            selecrDeviceDialog4.cancel();
                        }

                        @Override // com.wifiled.ipixels.ui.dialog.SelecrDeviceDialog.ClickListener
                        public void onOk(int num) {
                            SelecrDeviceDialog selecrDeviceDialog4;
                            List list5;
                            List list6;
                            selecrDeviceDialog4 = ImageTextListActivity.this.mSelectDeviceDialog;
                            Intrinsics.checkNotNull(selecrDeviceDialog4);
                            selecrDeviceDialog4.cancel();
                            if (num == 1) {
                                ImageTextListActivity imageTextListActivity5 = ImageTextListActivity.this;
                                list5 = imageTextListActivity5.mDatas;
                                imageTextListActivity5.AutoSendChannel((ImageTextListData) list5.get(position));
                            } else {
                                if (num != 2) {
                                    return;
                                }
                                ImageTextListActivity imageTextListActivity6 = ImageTextListActivity.this;
                                list6 = imageTextListActivity6.mDatas;
                                imageTextListActivity6.AutoSendChannel2((ImageTextListData) list6.get(position));
                            }
                        }
                    });
                }
                selecrDeviceDialog2 = this.this$0.mSelectDeviceDialog;
                if (selecrDeviceDialog2 != null) {
                    selecrDeviceDialog2.show();
                    return;
                }
                return;
            }
            if (BleManager.INSTANCE.get().getConnectedDevice() != null || AppConfig.INSTANCE.getConnectType() == 0) {
                ImageTextListActivity imageTextListActivity5 = this.this$0;
                list3 = imageTextListActivity5.mDatas;
                imageTextListActivity5.AutoSendChannel((ImageTextListData) list3.get(position));
                return;
            } else {
                if (BleManager2.INSTANCE.get().getConnectedDevice() != null) {
                    ImageTextListActivity imageTextListActivity6 = this.this$0;
                    list4 = imageTextListActivity6.mDatas;
                    imageTextListActivity6.AutoSendChannel2((ImageTextListData) list4.get(position));
                    return;
                }
                return;
            }
        }
        Intent intent = new Intent(this.this$0, (Class<?>) ImageTextActivity.class);
        intent.putExtra("isEdit", false);
        this.this$0.getMResult().launch(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onItemClick$lambda$0(ImageTextListActivity imageTextListActivity) {
        ToastUtil.show(imageTextListActivity.getString(R.string.msg_dev_connect_null));
        return Unit.INSTANCE;
    }
}
