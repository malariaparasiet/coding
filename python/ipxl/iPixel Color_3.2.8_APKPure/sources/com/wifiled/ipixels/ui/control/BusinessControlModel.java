package com.wifiled.ipixels.ui.control;

import android.content.Context;
import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import com.blankj.utilcode.util.LogUtils;
import com.wifiled.baselib.uicode.viewmodel.BaseViewModel;
import com.wifiled.baselib.utils.SPUtils;
import com.wifiled.baselib.utils.ThreadUtils;
import com.wifiled.ipixels.App;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.R;
import com.wifiled.ipixels.UtilsExtensionKt;
import com.wifiled.ipixels.core.SendCore;
import com.wifiled.ipixels.ui.channel.ChannelListItem;
import com.wifiled.ipixels.utils.ByteUtils;
import com.wifiled.ipixels.utils.FileUtil;
import com.wifiled.ipixels.utils.ThreadPoolUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

/* compiled from: BusinessControlModel.kt */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0012\u001a\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000e0\rJ;\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00172\u0017\u0010\u0019\u001a\u0013\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u00130\u001a¢\u0006\u0002\b\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u001eH\u0002J;\u0010\u001f\u001a\u00020\u00132\u0006\u0010 \u001a\u00020\u00172\u0006\u0010\u0014\u001a\u00020\u00172\u0017\u0010\u0019\u001a\u0013\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u00130\u001a¢\u0006\u0002\b\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u001eH\u0002J;\u0010!\u001a\u00020\u00132\u0006\u0010 \u001a\u00020\u00172\u0006\u0010\u0014\u001a\u00020\u00172\u0017\u0010\u0019\u001a\u0013\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u00130\u001a¢\u0006\u0002\b\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u001eH\u0002J\u0016\u0010\"\u001a\u00020\u00132\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u0006J\u0016\u0010&\u001a\u00020\u00132\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u0006J\u0016\u0010'\u001a\u00020\u00132\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u0006J\u0016\u0010(\u001a\u00020\u00132\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u0006R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\bR\u001d\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\bR\u001d\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\b¨\u0006)"}, d2 = {"Lcom/wifiled/ipixels/ui/control/BusinessControlModel;", "Lcom/wifiled/baselib/uicode/viewmodel/BaseViewModel;", "<init>", "()V", "customDialogSuccessEvent", "Landroidx/lifecycle/MutableLiveData;", "", "getCustomDialogSuccessEvent", "()Landroidx/lifecycle/MutableLiveData;", "customDialogErrorEvent", "", "getCustomDialogErrorEvent", "customGetCNDataEvent", "", "Lcom/wifiled/ipixels/ui/control/BusinessRemoteData;", "getCustomGetCNDataEvent", "customGetENDataEvent", "getCustomGetENDataEvent", "autoSendChannel", "", "data", "sendTextData", "arrText", "", "arrTotal", "callbackBuilder", "Lkotlin/Function1;", "Lcom/wifiled/ipixels/core/SendCore$CallbackBuilder;", "Lkotlin/ExtensionFunctionType;", "isBulkSend", "", "sendImagData", "serial", "sendGifData", "getCnData", "context", "Landroid/content/Context;", "fileSize", "getEnData", "resetCnData", "resetEnData", "app_googleRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class BusinessControlModel extends BaseViewModel {
    private final MutableLiveData<String> customDialogSuccessEvent = new MutableLiveData<>();
    private final MutableLiveData<Integer> customDialogErrorEvent = new MutableLiveData<>();
    private final MutableLiveData<List<BusinessRemoteData>> customGetCNDataEvent = new MutableLiveData<>();
    private final MutableLiveData<List<BusinessRemoteData>> customGetENDataEvent = new MutableLiveData<>();

    public final MutableLiveData<String> getCustomDialogSuccessEvent() {
        return this.customDialogSuccessEvent;
    }

    public final MutableLiveData<Integer> getCustomDialogErrorEvent() {
        return this.customDialogErrorEvent;
    }

    public final MutableLiveData<List<BusinessRemoteData>> getCustomGetCNDataEvent() {
        return this.customGetCNDataEvent;
    }

    public final MutableLiveData<List<BusinessRemoteData>> getCustomGetENDataEvent() {
        return this.customGetENDataEvent;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void autoSendChannel(List<BusinessRemoteData> data) {
        Intrinsics.checkNotNullParameter(data, "data");
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        booleanRef.element = true;
        final Ref.BooleanRef booleanRef2 = new Ref.BooleanRef();
        final Ref.BooleanRef booleanRef3 = new Ref.BooleanRef();
        final Ref.BooleanRef booleanRef4 = new Ref.BooleanRef();
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = data;
        ThreadPoolUtil.execute(new Runnable() { // from class: com.wifiled.ipixels.ui.control.BusinessControlModel$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                BusinessControlModel.autoSendChannel$lambda$32(Ref.ObjectRef.this, booleanRef3, booleanRef4, booleanRef, this, booleanRef2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void autoSendChannel$lambda$32(Ref.ObjectRef objectRef, Ref.BooleanRef booleanRef, final Ref.BooleanRef booleanRef2, final Ref.BooleanRef booleanRef3, final BusinessControlModel businessControlModel, final Ref.BooleanRef booleanRef4) {
        byte[] arrTextData;
        final Ref.BooleanRef booleanRef5 = booleanRef2;
        final Ref.BooleanRef booleanRef6 = booleanRef3;
        final Ref.IntRef intRef = new Ref.IntRef();
        if (AppConfig.INSTANCE.getMcu() > 4) {
            ArrayList arrayList = new ArrayList();
            arrayList.add((byte) 0);
            arrayList.add((byte) 0);
            arrayList.add((byte) 8);
            arrayList.add(Byte.valueOf(ByteCompanionObject.MIN_VALUE));
            arrayList.add((byte) 0);
            arrayList.add((byte) 0);
            int i = 111;
            for (BusinessRemoteData businessRemoteData : (List) objectRef.element) {
                intRef.element++;
                businessRemoteData.setSerialNum(i);
                arrayList.add(Byte.valueOf((byte) businessRemoteData.getSerialNum()));
                i++;
            }
            arrayList.set(4, Byte.valueOf((byte) intRef.element));
            arrayList.set(0, Byte.valueOf((byte) arrayList.size()));
            SendCore.INSTANCE.sendCompat(CollectionsKt.toByteArray(arrayList), new BusinessControlModel$autoSendChannel$1$1(businessControlModel));
            Thread.sleep(1000L);
        } else {
            intRef.element = ((List) objectRef.element).size();
        }
        Iterator it = ((List) objectRef.element).iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            if (booleanRef.element) {
                booleanRef.element = false;
                break;
            }
            if (booleanRef5.element) {
                booleanRef5.element = false;
                break;
            }
            if (booleanRef6.element) {
                ThreadUtils.delay(300L);
                BusinessRemoteData businessRemoteData2 = (BusinessRemoteData) it.next();
                int editResourceType = businessRemoteData2.getEditResourceType();
                if (editResourceType != 1) {
                    if (editResourceType == 2) {
                        ArrayList arrayList2 = new ArrayList();
                        byte[] editByteData = businessRemoteData2.getEditByteData();
                        if (editByteData != null) {
                            arrayList2.addAll(ArraysKt.toMutableList(editByteData));
                        }
                        byte[] plus = ArraysKt.plus(new byte[0], (byte) businessRemoteData2.getSerialNum());
                        Log.v("ruis", "arrCurTextSerial --------------" + ((int) ((byte) businessRemoteData2.getSerialNum())));
                        booleanRef5 = booleanRef2;
                        businessControlModel.sendImagData(plus, CollectionsKt.toByteArray(arrayList2), new Function1() { // from class: com.wifiled.ipixels.ui.control.BusinessControlModel$$ExternalSyntheticLambda14
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                Unit autoSendChannel$lambda$32$lambda$21;
                                autoSendChannel$lambda$32$lambda$21 = BusinessControlModel.autoSendChannel$lambda$32$lambda$21(Ref.BooleanRef.this, intRef, booleanRef4, businessControlModel, booleanRef5, (SendCore.CallbackBuilder) obj);
                                return autoSendChannel$lambda$32$lambda$21;
                            }
                        }, (byte) businessRemoteData2.getSerialNum());
                    } else if (editResourceType == 3) {
                        ArrayList arrayList3 = new ArrayList();
                        ChannelListItem.TextEmojView textEmojiView = businessRemoteData2.getTextEmojiView();
                        if (textEmojiView != null && (arrTextData = textEmojiView.getArrTextData()) != null) {
                            arrayList3.addAll(ArraysKt.toMutableList(arrTextData));
                        }
                        byte[] plus2 = ArraysKt.plus(new byte[0], (byte) businessRemoteData2.getSerialNum());
                        Log.v("ruis", "arrCurTextSerial --------------" + ((int) ((byte) businessRemoteData2.getSerialNum())));
                        businessControlModel.sendTextData(plus2, CollectionsKt.toByteArray(arrayList3), new Function1() { // from class: com.wifiled.ipixels.ui.control.BusinessControlModel$$ExternalSyntheticLambda13
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                Unit autoSendChannel$lambda$32$lambda$10;
                                autoSendChannel$lambda$32$lambda$10 = BusinessControlModel.autoSendChannel$lambda$32$lambda$10(Ref.BooleanRef.this, intRef, booleanRef4, businessControlModel, booleanRef5, (SendCore.CallbackBuilder) obj);
                                return autoSendChannel$lambda$32$lambda$10;
                            }
                        }, (byte) businessRemoteData2.getSerialNum());
                        booleanRef5 = booleanRef2;
                    }
                    booleanRef6 = booleanRef3;
                } else {
                    ArrayList arrayList4 = new ArrayList();
                    byte[] editByteData2 = businessRemoteData2.getEditByteData();
                    if (editByteData2 != null) {
                        arrayList4.addAll(ArraysKt.toMutableList(editByteData2));
                    }
                    byte[] plus3 = ArraysKt.plus(new byte[0], (byte) businessRemoteData2.getSerialNum());
                    Log.v("ruis", "arrCurTextSerial --------------" + ((int) ((byte) businessRemoteData2.getSerialNum())));
                    booleanRef6 = booleanRef3;
                    businessControlModel.sendGifData(plus3, CollectionsKt.toByteArray(arrayList4), new Function1() { // from class: com.wifiled.ipixels.ui.control.BusinessControlModel$$ExternalSyntheticLambda15
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            Unit autoSendChannel$lambda$32$lambda$31;
                            autoSendChannel$lambda$32$lambda$31 = BusinessControlModel.autoSendChannel$lambda$32$lambda$31(Ref.BooleanRef.this, intRef, booleanRef4, businessControlModel, booleanRef2, (SendCore.CallbackBuilder) obj);
                            return autoSendChannel$lambda$32$lambda$31;
                        }
                    }, (byte) businessRemoteData2.getSerialNum());
                }
            }
            booleanRef5 = booleanRef2;
        }
        if (booleanRef6.element && AppConfig.INSTANCE.isCancel()) {
            AppConfig.INSTANCE.setCancel(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit autoSendChannel$lambda$32$lambda$10(final Ref.BooleanRef booleanRef, final Ref.IntRef intRef, final Ref.BooleanRef booleanRef2, final BusinessControlModel businessControlModel, final Ref.BooleanRef booleanRef3, SendCore.CallbackBuilder sendTextData) {
        Intrinsics.checkNotNullParameter(sendTextData, "$this$sendTextData");
        sendTextData.onStart(new Function0() { // from class: com.wifiled.ipixels.ui.control.BusinessControlModel$$ExternalSyntheticLambda25
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit autoSendChannel$lambda$32$lambda$10$lambda$1;
                autoSendChannel$lambda$32$lambda$10$lambda$1 = BusinessControlModel.autoSendChannel$lambda$32$lambda$10$lambda$1(Ref.BooleanRef.this);
                return autoSendChannel$lambda$32$lambda$10$lambda$1;
            }
        });
        sendTextData.onProgress(new Function1() { // from class: com.wifiled.ipixels.ui.control.BusinessControlModel$$ExternalSyntheticLambda26
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit autoSendChannel$lambda$32$lambda$10$lambda$2;
                autoSendChannel$lambda$32$lambda$10$lambda$2 = BusinessControlModel.autoSendChannel$lambda$32$lambda$10$lambda$2(((Integer) obj).intValue());
                return autoSendChannel$lambda$32$lambda$10$lambda$2;
            }
        });
        sendTextData.onCompleted(new Function0() { // from class: com.wifiled.ipixels.ui.control.BusinessControlModel$$ExternalSyntheticLambda27
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit autoSendChannel$lambda$32$lambda$10$lambda$4;
                autoSendChannel$lambda$32$lambda$10$lambda$4 = BusinessControlModel.autoSendChannel$lambda$32$lambda$10$lambda$4(Ref.BooleanRef.this, intRef, booleanRef2, businessControlModel);
                return autoSendChannel$lambda$32$lambda$10$lambda$4;
            }
        });
        sendTextData.onError(new Function1() { // from class: com.wifiled.ipixels.ui.control.BusinessControlModel$$ExternalSyntheticLambda28
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit autoSendChannel$lambda$32$lambda$10$lambda$6;
                autoSendChannel$lambda$32$lambda$10$lambda$6 = BusinessControlModel.autoSendChannel$lambda$32$lambda$10$lambda$6(BusinessControlModel.this, ((Integer) obj).intValue());
                return autoSendChannel$lambda$32$lambda$10$lambda$6;
            }
        });
        sendTextData.onResult(new Function1() { // from class: com.wifiled.ipixels.ui.control.BusinessControlModel$$ExternalSyntheticLambda29
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit autoSendChannel$lambda$32$lambda$10$lambda$9;
                autoSendChannel$lambda$32$lambda$10$lambda$9 = BusinessControlModel.autoSendChannel$lambda$32$lambda$10$lambda$9(Ref.BooleanRef.this, businessControlModel, (byte[]) obj);
                return autoSendChannel$lambda$32$lambda$10$lambda$9;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit autoSendChannel$lambda$32$lambda$10$lambda$1(Ref.BooleanRef booleanRef) {
        booleanRef.element = false;
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit autoSendChannel$lambda$32$lambda$10$lambda$2(int i) {
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit autoSendChannel$lambda$32$lambda$10$lambda$4(Ref.BooleanRef booleanRef, Ref.IntRef intRef, Ref.BooleanRef booleanRef2, final BusinessControlModel businessControlModel) {
        Log.v("ruis", "TextEmojView onCompleted isCurSendOver==" + booleanRef.element);
        if (!booleanRef.element) {
            booleanRef.element = true;
            intRef.element--;
            booleanRef2.element = intRef.element == 0;
            if (booleanRef2.element) {
                UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.control.BusinessControlModel$$ExternalSyntheticLambda5
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Unit autoSendChannel$lambda$32$lambda$10$lambda$4$lambda$3;
                        autoSendChannel$lambda$32$lambda$10$lambda$4$lambda$3 = BusinessControlModel.autoSendChannel$lambda$32$lambda$10$lambda$4$lambda$3(BusinessControlModel.this);
                        return autoSendChannel$lambda$32$lambda$10$lambda$4$lambda$3;
                    }
                });
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit autoSendChannel$lambda$32$lambda$10$lambda$4$lambda$3(BusinessControlModel businessControlModel) {
        businessControlModel.customDialogSuccessEvent.setValue("");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit autoSendChannel$lambda$32$lambda$10$lambda$6(final BusinessControlModel businessControlModel, int i) {
        if (i == 10016) {
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.control.BusinessControlModel$$ExternalSyntheticLambda22
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit autoSendChannel$lambda$32$lambda$10$lambda$6$lambda$5;
                    autoSendChannel$lambda$32$lambda$10$lambda$6$lambda$5 = BusinessControlModel.autoSendChannel$lambda$32$lambda$10$lambda$6$lambda$5(BusinessControlModel.this);
                    return autoSendChannel$lambda$32$lambda$10$lambda$6$lambda$5;
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit autoSendChannel$lambda$32$lambda$10$lambda$6$lambda$5(BusinessControlModel businessControlModel) {
        businessControlModel.customDialogErrorEvent.setValue(2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit autoSendChannel$lambda$32$lambda$10$lambda$9(Ref.BooleanRef booleanRef, final BusinessControlModel businessControlModel, byte[] it) {
        byte b;
        Intrinsics.checkNotNullParameter(it, "it");
        if (StringsKt.contains$default((CharSequence) new String(it, Charsets.UTF_8), (CharSequence) "retry_over_limit_no_answer", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) new String(it, Charsets.UTF_8), (CharSequence) "Socket connect fail", false, 2, (Object) null)) {
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.control.BusinessControlModel$$ExternalSyntheticLambda30
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit autoSendChannel$lambda$32$lambda$10$lambda$9$lambda$7;
                    autoSendChannel$lambda$32$lambda$10$lambda$9$lambda$7 = BusinessControlModel.autoSendChannel$lambda$32$lambda$10$lambda$9$lambda$7(BusinessControlModel.this);
                    return autoSendChannel$lambda$32$lambda$10$lambda$9$lambda$7;
                }
            });
            return Unit.INSTANCE;
        }
        if (it.length <= 4 || it[0] != 5 || (b = it[4]) == 1 || b == 3) {
            return Unit.INSTANCE;
        }
        if (b == 2) {
            booleanRef.element = true;
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.control.BusinessControlModel$$ExternalSyntheticLambda31
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit autoSendChannel$lambda$32$lambda$10$lambda$9$lambda$8;
                    autoSendChannel$lambda$32$lambda$10$lambda$9$lambda$8 = BusinessControlModel.autoSendChannel$lambda$32$lambda$10$lambda$9$lambda$8(BusinessControlModel.this);
                    return autoSendChannel$lambda$32$lambda$10$lambda$9$lambda$8;
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit autoSendChannel$lambda$32$lambda$10$lambda$9$lambda$7(BusinessControlModel businessControlModel) {
        businessControlModel.customDialogErrorEvent.setValue(1);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit autoSendChannel$lambda$32$lambda$10$lambda$9$lambda$8(BusinessControlModel businessControlModel) {
        businessControlModel.customDialogErrorEvent.setValue(2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit autoSendChannel$lambda$32$lambda$21(final Ref.BooleanRef booleanRef, final Ref.IntRef intRef, final Ref.BooleanRef booleanRef2, final BusinessControlModel businessControlModel, final Ref.BooleanRef booleanRef3, SendCore.CallbackBuilder sendImagData) {
        Intrinsics.checkNotNullParameter(sendImagData, "$this$sendImagData");
        sendImagData.onStart(new Function0() { // from class: com.wifiled.ipixels.ui.control.BusinessControlModel$$ExternalSyntheticLambda33
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit autoSendChannel$lambda$32$lambda$21$lambda$12;
                autoSendChannel$lambda$32$lambda$21$lambda$12 = BusinessControlModel.autoSendChannel$lambda$32$lambda$21$lambda$12(Ref.BooleanRef.this);
                return autoSendChannel$lambda$32$lambda$21$lambda$12;
            }
        });
        sendImagData.onProgress(new Function1() { // from class: com.wifiled.ipixels.ui.control.BusinessControlModel$$ExternalSyntheticLambda43
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit autoSendChannel$lambda$32$lambda$21$lambda$13;
                autoSendChannel$lambda$32$lambda$21$lambda$13 = BusinessControlModel.autoSendChannel$lambda$32$lambda$21$lambda$13(((Integer) obj).intValue());
                return autoSendChannel$lambda$32$lambda$21$lambda$13;
            }
        });
        sendImagData.onCompleted(new Function0() { // from class: com.wifiled.ipixels.ui.control.BusinessControlModel$$ExternalSyntheticLambda44
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit autoSendChannel$lambda$32$lambda$21$lambda$15;
                autoSendChannel$lambda$32$lambda$21$lambda$15 = BusinessControlModel.autoSendChannel$lambda$32$lambda$21$lambda$15(Ref.BooleanRef.this, intRef, booleanRef2, businessControlModel);
                return autoSendChannel$lambda$32$lambda$21$lambda$15;
            }
        });
        sendImagData.onError(new Function1() { // from class: com.wifiled.ipixels.ui.control.BusinessControlModel$$ExternalSyntheticLambda45
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit autoSendChannel$lambda$32$lambda$21$lambda$17;
                autoSendChannel$lambda$32$lambda$21$lambda$17 = BusinessControlModel.autoSendChannel$lambda$32$lambda$21$lambda$17(BusinessControlModel.this, ((Integer) obj).intValue());
                return autoSendChannel$lambda$32$lambda$21$lambda$17;
            }
        });
        sendImagData.onResult(new Function1() { // from class: com.wifiled.ipixels.ui.control.BusinessControlModel$$ExternalSyntheticLambda46
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit autoSendChannel$lambda$32$lambda$21$lambda$20;
                autoSendChannel$lambda$32$lambda$21$lambda$20 = BusinessControlModel.autoSendChannel$lambda$32$lambda$21$lambda$20(Ref.BooleanRef.this, businessControlModel, (byte[]) obj);
                return autoSendChannel$lambda$32$lambda$21$lambda$20;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit autoSendChannel$lambda$32$lambda$21$lambda$12(Ref.BooleanRef booleanRef) {
        booleanRef.element = false;
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit autoSendChannel$lambda$32$lambda$21$lambda$13(int i) {
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit autoSendChannel$lambda$32$lambda$21$lambda$15(Ref.BooleanRef booleanRef, Ref.IntRef intRef, Ref.BooleanRef booleanRef2, final BusinessControlModel businessControlModel) {
        Log.v("ruis", "ImagView onCompleted isCurSendOver==" + booleanRef.element);
        if (!booleanRef.element) {
            booleanRef.element = true;
            intRef.element--;
            booleanRef2.element = intRef.element == 0;
            if (booleanRef2.element) {
                UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.control.BusinessControlModel$$ExternalSyntheticLambda35
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Unit autoSendChannel$lambda$32$lambda$21$lambda$15$lambda$14;
                        autoSendChannel$lambda$32$lambda$21$lambda$15$lambda$14 = BusinessControlModel.autoSendChannel$lambda$32$lambda$21$lambda$15$lambda$14(BusinessControlModel.this);
                        return autoSendChannel$lambda$32$lambda$21$lambda$15$lambda$14;
                    }
                });
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit autoSendChannel$lambda$32$lambda$21$lambda$15$lambda$14(BusinessControlModel businessControlModel) {
        businessControlModel.customDialogSuccessEvent.setValue("");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit autoSendChannel$lambda$32$lambda$21$lambda$17(final BusinessControlModel businessControlModel, int i) {
        if (i == 10016) {
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.control.BusinessControlModel$$ExternalSyntheticLambda11
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit autoSendChannel$lambda$32$lambda$21$lambda$17$lambda$16;
                    autoSendChannel$lambda$32$lambda$21$lambda$17$lambda$16 = BusinessControlModel.autoSendChannel$lambda$32$lambda$21$lambda$17$lambda$16(BusinessControlModel.this);
                    return autoSendChannel$lambda$32$lambda$21$lambda$17$lambda$16;
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit autoSendChannel$lambda$32$lambda$21$lambda$17$lambda$16(BusinessControlModel businessControlModel) {
        businessControlModel.customDialogErrorEvent.setValue(2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit autoSendChannel$lambda$32$lambda$21$lambda$20(Ref.BooleanRef booleanRef, final BusinessControlModel businessControlModel, byte[] it) {
        byte b;
        Intrinsics.checkNotNullParameter(it, "it");
        if (StringsKt.contains$default((CharSequence) new String(it, Charsets.UTF_8), (CharSequence) "retry_over_limit_no_answer", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) new String(it, Charsets.UTF_8), (CharSequence) "Socket connect fail", false, 2, (Object) null)) {
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.control.BusinessControlModel$$ExternalSyntheticLambda36
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit autoSendChannel$lambda$32$lambda$21$lambda$20$lambda$18;
                    autoSendChannel$lambda$32$lambda$21$lambda$20$lambda$18 = BusinessControlModel.autoSendChannel$lambda$32$lambda$21$lambda$20$lambda$18(BusinessControlModel.this);
                    return autoSendChannel$lambda$32$lambda$21$lambda$20$lambda$18;
                }
            });
            return Unit.INSTANCE;
        }
        if (it.length <= 4 || it[0] != 5 || (b = it[4]) == 1 || b == 3) {
            return Unit.INSTANCE;
        }
        if (b == 2) {
            booleanRef.element = true;
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.control.BusinessControlModel$$ExternalSyntheticLambda37
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit autoSendChannel$lambda$32$lambda$21$lambda$20$lambda$19;
                    autoSendChannel$lambda$32$lambda$21$lambda$20$lambda$19 = BusinessControlModel.autoSendChannel$lambda$32$lambda$21$lambda$20$lambda$19(BusinessControlModel.this);
                    return autoSendChannel$lambda$32$lambda$21$lambda$20$lambda$19;
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit autoSendChannel$lambda$32$lambda$21$lambda$20$lambda$18(BusinessControlModel businessControlModel) {
        businessControlModel.customDialogErrorEvent.setValue(1);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit autoSendChannel$lambda$32$lambda$21$lambda$20$lambda$19(BusinessControlModel businessControlModel) {
        businessControlModel.customDialogErrorEvent.setValue(2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit autoSendChannel$lambda$32$lambda$31(final Ref.BooleanRef booleanRef, final Ref.IntRef intRef, final Ref.BooleanRef booleanRef2, final BusinessControlModel businessControlModel, final Ref.BooleanRef booleanRef3, SendCore.CallbackBuilder sendGifData) {
        Intrinsics.checkNotNullParameter(sendGifData, "$this$sendGifData");
        sendGifData.onStart(new Function0() { // from class: com.wifiled.ipixels.ui.control.BusinessControlModel$$ExternalSyntheticLambda47
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit autoSendChannel$lambda$32$lambda$31$lambda$23;
                autoSendChannel$lambda$32$lambda$31$lambda$23 = BusinessControlModel.autoSendChannel$lambda$32$lambda$31$lambda$23(Ref.BooleanRef.this);
                return autoSendChannel$lambda$32$lambda$31$lambda$23;
            }
        });
        sendGifData.onProgress(new Function1() { // from class: com.wifiled.ipixels.ui.control.BusinessControlModel$$ExternalSyntheticLambda48
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit autoSendChannel$lambda$32$lambda$31$lambda$24;
                autoSendChannel$lambda$32$lambda$31$lambda$24 = BusinessControlModel.autoSendChannel$lambda$32$lambda$31$lambda$24(((Integer) obj).intValue());
                return autoSendChannel$lambda$32$lambda$31$lambda$24;
            }
        });
        sendGifData.onCompleted(new Function0() { // from class: com.wifiled.ipixels.ui.control.BusinessControlModel$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit autoSendChannel$lambda$32$lambda$31$lambda$26;
                autoSendChannel$lambda$32$lambda$31$lambda$26 = BusinessControlModel.autoSendChannel$lambda$32$lambda$31$lambda$26(Ref.BooleanRef.this, intRef, booleanRef2, businessControlModel);
                return autoSendChannel$lambda$32$lambda$31$lambda$26;
            }
        });
        sendGifData.onError(new Function1() { // from class: com.wifiled.ipixels.ui.control.BusinessControlModel$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit autoSendChannel$lambda$32$lambda$31$lambda$28;
                autoSendChannel$lambda$32$lambda$31$lambda$28 = BusinessControlModel.autoSendChannel$lambda$32$lambda$31$lambda$28(BusinessControlModel.this, ((Integer) obj).intValue());
                return autoSendChannel$lambda$32$lambda$31$lambda$28;
            }
        });
        sendGifData.onResult(new Function1() { // from class: com.wifiled.ipixels.ui.control.BusinessControlModel$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit autoSendChannel$lambda$32$lambda$31$lambda$30;
                autoSendChannel$lambda$32$lambda$31$lambda$30 = BusinessControlModel.autoSendChannel$lambda$32$lambda$31$lambda$30(Ref.BooleanRef.this, businessControlModel, (byte[]) obj);
                return autoSendChannel$lambda$32$lambda$31$lambda$30;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit autoSendChannel$lambda$32$lambda$31$lambda$23(Ref.BooleanRef booleanRef) {
        booleanRef.element = false;
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit autoSendChannel$lambda$32$lambda$31$lambda$24(int i) {
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit autoSendChannel$lambda$32$lambda$31$lambda$26(Ref.BooleanRef booleanRef, Ref.IntRef intRef, Ref.BooleanRef booleanRef2, final BusinessControlModel businessControlModel) {
        Log.v("ruis", "GiftView onCompleted isCurSendOver==" + booleanRef.element);
        if (!booleanRef.element) {
            booleanRef.element = true;
            intRef.element--;
            booleanRef2.element = intRef.element == 0;
            Log.v("ruis", "GiftView onCompleted isCAllSendOver==" + booleanRef2.element);
            Log.v("ruis", "GiftView onCompleted iSelTotalCount==" + intRef.element);
            if (booleanRef2.element) {
                UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.control.BusinessControlModel$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Unit autoSendChannel$lambda$32$lambda$31$lambda$26$lambda$25;
                        autoSendChannel$lambda$32$lambda$31$lambda$26$lambda$25 = BusinessControlModel.autoSendChannel$lambda$32$lambda$31$lambda$26$lambda$25(BusinessControlModel.this);
                        return autoSendChannel$lambda$32$lambda$31$lambda$26$lambda$25;
                    }
                });
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit autoSendChannel$lambda$32$lambda$31$lambda$26$lambda$25(BusinessControlModel businessControlModel) {
        businessControlModel.customDialogSuccessEvent.setValue("");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit autoSendChannel$lambda$32$lambda$31$lambda$28(final BusinessControlModel businessControlModel, int i) {
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.control.BusinessControlModel$$ExternalSyntheticLambda16
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit autoSendChannel$lambda$32$lambda$31$lambda$28$lambda$27;
                autoSendChannel$lambda$32$lambda$31$lambda$28$lambda$27 = BusinessControlModel.autoSendChannel$lambda$32$lambda$31$lambda$28$lambda$27(BusinessControlModel.this);
                return autoSendChannel$lambda$32$lambda$31$lambda$28$lambda$27;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit autoSendChannel$lambda$32$lambda$31$lambda$28$lambda$27(BusinessControlModel businessControlModel) {
        businessControlModel.customDialogErrorEvent.setValue(1);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit autoSendChannel$lambda$32$lambda$31$lambda$30(Ref.BooleanRef booleanRef, final BusinessControlModel businessControlModel, byte[] it) {
        byte b;
        Intrinsics.checkNotNullParameter(it, "it");
        if (it.length <= 4 || it[0] != 5 || (b = it[4]) == 1 || b == 3) {
            Log.v("ruis", "onResult --------------" + ByteUtils.toString(it));
        }
        if (it[4] == 2) {
            booleanRef.element = true;
            UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.control.BusinessControlModel$$ExternalSyntheticLambda12
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit autoSendChannel$lambda$32$lambda$31$lambda$30$lambda$29;
                    autoSendChannel$lambda$32$lambda$31$lambda$30$lambda$29 = BusinessControlModel.autoSendChannel$lambda$32$lambda$31$lambda$30$lambda$29(BusinessControlModel.this);
                    return autoSendChannel$lambda$32$lambda$31$lambda$30$lambda$29;
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit autoSendChannel$lambda$32$lambda$31$lambda$30$lambda$29(BusinessControlModel businessControlModel) {
        businessControlModel.customDialogErrorEvent.setValue(2);
        return Unit.INSTANCE;
    }

    static /* synthetic */ void sendTextData$default(BusinessControlModel businessControlModel, byte[] bArr, byte[] bArr2, Function1 function1, byte b, int i, Object obj) {
        if ((i & 8) != 0) {
            b = 0;
        }
        businessControlModel.sendTextData(bArr, bArr2, function1, b);
    }

    private final void sendTextData(byte[] arrText, byte[] arrTotal, Function1<? super SendCore.CallbackBuilder, Unit> callbackBuilder, byte isBulkSend) {
        final SendCore.CallbackBuilder callbackBuilder2 = new SendCore.CallbackBuilder();
        callbackBuilder.invoke(callbackBuilder2);
        Function0<Unit> startAction$app_googleRelease = callbackBuilder2.getStartAction$app_googleRelease();
        if (startAction$app_googleRelease != null) {
            startAction$app_googleRelease.invoke();
        }
        SendCore.INSTANCE.sendTextDataInvokFun(true, arrText, arrTotal, new Function1() { // from class: com.wifiled.ipixels.ui.control.BusinessControlModel$$ExternalSyntheticLambda23
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendTextData$lambda$38;
                sendTextData$lambda$38 = BusinessControlModel.sendTextData$lambda$38(SendCore.CallbackBuilder.this, (SendCore.CallbackBuilder) obj);
                return sendTextData$lambda$38;
            }
        }, isBulkSend);
        SendCore.INSTANCE.sendTextDataInvokFun2(true, arrText, arrTotal, new Function1() { // from class: com.wifiled.ipixels.ui.control.BusinessControlModel$$ExternalSyntheticLambda24
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendTextData$lambda$44;
                sendTextData$lambda$44 = BusinessControlModel.sendTextData$lambda$44(SendCore.CallbackBuilder.this, (SendCore.CallbackBuilder) obj);
                return sendTextData$lambda$44;
            }
        }, isBulkSend);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendTextData$lambda$38(final SendCore.CallbackBuilder callbackBuilder, SendCore.CallbackBuilder sendTextDataInvokFun) {
        Intrinsics.checkNotNullParameter(sendTextDataInvokFun, "$this$sendTextDataInvokFun");
        sendTextDataInvokFun.onStart(new Function0() { // from class: com.wifiled.ipixels.ui.control.BusinessControlModel$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendTextData$lambda$38$lambda$33;
                sendTextData$lambda$38$lambda$33 = BusinessControlModel.sendTextData$lambda$38$lambda$33(SendCore.CallbackBuilder.this);
                return sendTextData$lambda$38$lambda$33;
            }
        });
        sendTextDataInvokFun.onProgress(new Function1() { // from class: com.wifiled.ipixels.ui.control.BusinessControlModel$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendTextData$lambda$38$lambda$34;
                sendTextData$lambda$38$lambda$34 = BusinessControlModel.sendTextData$lambda$38$lambda$34(SendCore.CallbackBuilder.this, ((Integer) obj).intValue());
                return sendTextData$lambda$38$lambda$34;
            }
        });
        sendTextDataInvokFun.onCompleted(new Function0() { // from class: com.wifiled.ipixels.ui.control.BusinessControlModel$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendTextData$lambda$38$lambda$35;
                sendTextData$lambda$38$lambda$35 = BusinessControlModel.sendTextData$lambda$38$lambda$35(SendCore.CallbackBuilder.this);
                return sendTextData$lambda$38$lambda$35;
            }
        });
        sendTextDataInvokFun.onError(new Function1() { // from class: com.wifiled.ipixels.ui.control.BusinessControlModel$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendTextData$lambda$38$lambda$36;
                sendTextData$lambda$38$lambda$36 = BusinessControlModel.sendTextData$lambda$38$lambda$36(((Integer) obj).intValue());
                return sendTextData$lambda$38$lambda$36;
            }
        });
        sendTextDataInvokFun.onResult(new Function1() { // from class: com.wifiled.ipixels.ui.control.BusinessControlModel$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendTextData$lambda$38$lambda$37;
                sendTextData$lambda$38$lambda$37 = BusinessControlModel.sendTextData$lambda$38$lambda$37(SendCore.CallbackBuilder.this, (byte[]) obj);
                return sendTextData$lambda$38$lambda$37;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendTextData$lambda$38$lambda$33(SendCore.CallbackBuilder callbackBuilder) {
        Function1<Integer, Unit> progressAction$app_googleRelease = callbackBuilder.getProgressAction$app_googleRelease();
        if (progressAction$app_googleRelease != null) {
            progressAction$app_googleRelease.invoke(0);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendTextData$lambda$38$lambda$34(SendCore.CallbackBuilder callbackBuilder, int i) {
        Function1<Integer, Unit> progressAction$app_googleRelease = callbackBuilder.getProgressAction$app_googleRelease();
        if (progressAction$app_googleRelease != null) {
            progressAction$app_googleRelease.invoke(Integer.valueOf(i));
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendTextData$lambda$38$lambda$35(SendCore.CallbackBuilder callbackBuilder) {
        Function0<Unit> completeAction$app_googleRelease = callbackBuilder.getCompleteAction$app_googleRelease();
        if (completeAction$app_googleRelease != null) {
            completeAction$app_googleRelease.invoke();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendTextData$lambda$38$lambda$36(int i) {
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendTextData$lambda$38$lambda$37(SendCore.CallbackBuilder callbackBuilder, byte[] it) {
        Intrinsics.checkNotNullParameter(it, "it");
        Function1<byte[], Unit> resultAction$app_googleRelease = callbackBuilder.getResultAction$app_googleRelease();
        if (resultAction$app_googleRelease != null) {
            resultAction$app_googleRelease.invoke(it);
        }
        if (it.length <= 4 || it[0] != 5) {
            return Unit.INSTANCE;
        }
        byte b = it[4];
        if (b == 2) {
            Function1<Integer, Unit> errorAction$app_googleRelease = callbackBuilder.getErrorAction$app_googleRelease();
            if (errorAction$app_googleRelease != null) {
                errorAction$app_googleRelease.invoke(10016);
            }
        } else if (b == 3) {
            LogUtils.i(">>>[sendTextData onResult]:保存文件成功 ");
            Function0<Unit> completeAction$app_googleRelease = callbackBuilder.getCompleteAction$app_googleRelease();
            if (completeAction$app_googleRelease != null) {
                completeAction$app_googleRelease.invoke();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendTextData$lambda$44(final SendCore.CallbackBuilder callbackBuilder, SendCore.CallbackBuilder sendTextDataInvokFun2) {
        Intrinsics.checkNotNullParameter(sendTextDataInvokFun2, "$this$sendTextDataInvokFun2");
        sendTextDataInvokFun2.onStart(new Function0() { // from class: com.wifiled.ipixels.ui.control.BusinessControlModel$$ExternalSyntheticLambda38
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendTextData$lambda$44$lambda$39;
                sendTextData$lambda$44$lambda$39 = BusinessControlModel.sendTextData$lambda$44$lambda$39(SendCore.CallbackBuilder.this);
                return sendTextData$lambda$44$lambda$39;
            }
        });
        sendTextDataInvokFun2.onProgress(new Function1() { // from class: com.wifiled.ipixels.ui.control.BusinessControlModel$$ExternalSyntheticLambda39
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendTextData$lambda$44$lambda$40;
                sendTextData$lambda$44$lambda$40 = BusinessControlModel.sendTextData$lambda$44$lambda$40(SendCore.CallbackBuilder.this, ((Integer) obj).intValue());
                return sendTextData$lambda$44$lambda$40;
            }
        });
        sendTextDataInvokFun2.onCompleted(new Function0() { // from class: com.wifiled.ipixels.ui.control.BusinessControlModel$$ExternalSyntheticLambda40
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendTextData$lambda$44$lambda$41;
                sendTextData$lambda$44$lambda$41 = BusinessControlModel.sendTextData$lambda$44$lambda$41(SendCore.CallbackBuilder.this);
                return sendTextData$lambda$44$lambda$41;
            }
        });
        sendTextDataInvokFun2.onError(new Function1() { // from class: com.wifiled.ipixels.ui.control.BusinessControlModel$$ExternalSyntheticLambda41
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendTextData$lambda$44$lambda$42;
                sendTextData$lambda$44$lambda$42 = BusinessControlModel.sendTextData$lambda$44$lambda$42(((Integer) obj).intValue());
                return sendTextData$lambda$44$lambda$42;
            }
        });
        sendTextDataInvokFun2.onResult(new Function1() { // from class: com.wifiled.ipixels.ui.control.BusinessControlModel$$ExternalSyntheticLambda42
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendTextData$lambda$44$lambda$43;
                sendTextData$lambda$44$lambda$43 = BusinessControlModel.sendTextData$lambda$44$lambda$43(SendCore.CallbackBuilder.this, (byte[]) obj);
                return sendTextData$lambda$44$lambda$43;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendTextData$lambda$44$lambda$39(SendCore.CallbackBuilder callbackBuilder) {
        Function1<Integer, Unit> progressAction$app_googleRelease = callbackBuilder.getProgressAction$app_googleRelease();
        if (progressAction$app_googleRelease != null) {
            progressAction$app_googleRelease.invoke(0);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendTextData$lambda$44$lambda$40(SendCore.CallbackBuilder callbackBuilder, int i) {
        Function1<Integer, Unit> progressAction$app_googleRelease = callbackBuilder.getProgressAction$app_googleRelease();
        if (progressAction$app_googleRelease != null) {
            progressAction$app_googleRelease.invoke(Integer.valueOf(i));
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendTextData$lambda$44$lambda$41(SendCore.CallbackBuilder callbackBuilder) {
        Function0<Unit> completeAction$app_googleRelease = callbackBuilder.getCompleteAction$app_googleRelease();
        if (completeAction$app_googleRelease != null) {
            completeAction$app_googleRelease.invoke();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendTextData$lambda$44$lambda$42(int i) {
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendTextData$lambda$44$lambda$43(SendCore.CallbackBuilder callbackBuilder, byte[] it) {
        Intrinsics.checkNotNullParameter(it, "it");
        Function1<byte[], Unit> resultAction$app_googleRelease = callbackBuilder.getResultAction$app_googleRelease();
        if (resultAction$app_googleRelease != null) {
            resultAction$app_googleRelease.invoke(it);
        }
        if (it.length <= 4 || it[0] != 5) {
            return Unit.INSTANCE;
        }
        byte b = it[4];
        if (b == 2) {
            Function1<Integer, Unit> errorAction$app_googleRelease = callbackBuilder.getErrorAction$app_googleRelease();
            if (errorAction$app_googleRelease != null) {
                errorAction$app_googleRelease.invoke(10016);
            }
        } else if (b == 3) {
            LogUtils.i(">>>[sendTextData onResult]:保存文件成功 ");
            Function0<Unit> completeAction$app_googleRelease = callbackBuilder.getCompleteAction$app_googleRelease();
            if (completeAction$app_googleRelease != null) {
                completeAction$app_googleRelease.invoke();
            }
        }
        return Unit.INSTANCE;
    }

    static /* synthetic */ void sendImagData$default(BusinessControlModel businessControlModel, byte[] bArr, byte[] bArr2, Function1 function1, byte b, int i, Object obj) {
        if ((i & 8) != 0) {
            b = 0;
        }
        businessControlModel.sendImagData(bArr, bArr2, function1, b);
    }

    private final void sendImagData(byte[] serial, byte[] data, Function1<? super SendCore.CallbackBuilder, Unit> callbackBuilder, byte isBulkSend) {
        SendCore.CallbackBuilder callbackBuilder2 = new SendCore.CallbackBuilder();
        callbackBuilder.invoke(callbackBuilder2);
        Function0<Unit> startAction$app_googleRelease = callbackBuilder2.getStartAction$app_googleRelease();
        if (startAction$app_googleRelease != null) {
            startAction$app_googleRelease.invoke();
        }
        SendCore.INSTANCE.sendChannelImageData(true, serial, data, callbackBuilder, isBulkSend);
    }

    static /* synthetic */ void sendGifData$default(BusinessControlModel businessControlModel, byte[] bArr, byte[] bArr2, Function1 function1, byte b, int i, Object obj) {
        if ((i & 8) != 0) {
            b = 0;
        }
        businessControlModel.sendGifData(bArr, bArr2, function1, b);
    }

    private final void sendGifData(byte[] serial, byte[] data, Function1<? super SendCore.CallbackBuilder, Unit> callbackBuilder, byte isBulkSend) {
        final SendCore.CallbackBuilder callbackBuilder2 = new SendCore.CallbackBuilder();
        callbackBuilder.invoke(callbackBuilder2);
        Function0<Unit> startAction$app_googleRelease = callbackBuilder2.getStartAction$app_googleRelease();
        if (startAction$app_googleRelease != null) {
            startAction$app_googleRelease.invoke();
        }
        if (AppConfig.INSTANCE.getConnectType() != -1 || AppConfig.INSTANCE.getConnectType2() != -1) {
            SendCore.INSTANCE.sendChannelGifData(serial, data, new Function1() { // from class: com.wifiled.ipixels.ui.control.BusinessControlModel$$ExternalSyntheticLambda32
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Unit sendGifData$lambda$51;
                    sendGifData$lambda$51 = BusinessControlModel.sendGifData$lambda$51(SendCore.CallbackBuilder.this, this, (SendCore.CallbackBuilder) obj);
                    return sendGifData$lambda$51;
                }
            }, isBulkSend);
            return;
        }
        Function1<Integer, Unit> errorAction$app_googleRelease = callbackBuilder2.getErrorAction$app_googleRelease();
        if (errorAction$app_googleRelease != null) {
            errorAction$app_googleRelease.invoke(100114);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendGifData$lambda$51(final SendCore.CallbackBuilder callbackBuilder, final BusinessControlModel businessControlModel, SendCore.CallbackBuilder sendChannelGifData) {
        Intrinsics.checkNotNullParameter(sendChannelGifData, "$this$sendChannelGifData");
        sendChannelGifData.onStart(new Function0() { // from class: com.wifiled.ipixels.ui.control.BusinessControlModel$$ExternalSyntheticLambda17
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendGifData$lambda$51$lambda$45;
                sendGifData$lambda$51$lambda$45 = BusinessControlModel.sendGifData$lambda$51$lambda$45(SendCore.CallbackBuilder.this);
                return sendGifData$lambda$51$lambda$45;
            }
        });
        sendChannelGifData.onProgress(new Function1() { // from class: com.wifiled.ipixels.ui.control.BusinessControlModel$$ExternalSyntheticLambda18
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendGifData$lambda$51$lambda$46;
                sendGifData$lambda$51$lambda$46 = BusinessControlModel.sendGifData$lambda$51$lambda$46(SendCore.CallbackBuilder.this, ((Integer) obj).intValue());
                return sendGifData$lambda$51$lambda$46;
            }
        });
        sendChannelGifData.onCompleted(new Function0() { // from class: com.wifiled.ipixels.ui.control.BusinessControlModel$$ExternalSyntheticLambda19
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendGifData$lambda$51$lambda$47;
                sendGifData$lambda$51$lambda$47 = BusinessControlModel.sendGifData$lambda$51$lambda$47(SendCore.CallbackBuilder.this);
                return sendGifData$lambda$51$lambda$47;
            }
        });
        sendChannelGifData.onError(new Function1() { // from class: com.wifiled.ipixels.ui.control.BusinessControlModel$$ExternalSyntheticLambda20
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendGifData$lambda$51$lambda$49;
                sendGifData$lambda$51$lambda$49 = BusinessControlModel.sendGifData$lambda$51$lambda$49(SendCore.CallbackBuilder.this, businessControlModel, ((Integer) obj).intValue());
                return sendGifData$lambda$51$lambda$49;
            }
        });
        sendChannelGifData.onResult(new Function1() { // from class: com.wifiled.ipixels.ui.control.BusinessControlModel$$ExternalSyntheticLambda21
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Unit sendGifData$lambda$51$lambda$50;
                sendGifData$lambda$51$lambda$50 = BusinessControlModel.sendGifData$lambda$51$lambda$50(SendCore.CallbackBuilder.this, (byte[]) obj);
                return sendGifData$lambda$51$lambda$50;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendGifData$lambda$51$lambda$45(SendCore.CallbackBuilder callbackBuilder) {
        Function1<Integer, Unit> progressAction$app_googleRelease = callbackBuilder.getProgressAction$app_googleRelease();
        if (progressAction$app_googleRelease != null) {
            progressAction$app_googleRelease.invoke(0);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendGifData$lambda$51$lambda$46(SendCore.CallbackBuilder callbackBuilder, int i) {
        Function1<Integer, Unit> progressAction$app_googleRelease = callbackBuilder.getProgressAction$app_googleRelease();
        if (progressAction$app_googleRelease != null) {
            progressAction$app_googleRelease.invoke(Integer.valueOf(i));
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendGifData$lambda$51$lambda$47(SendCore.CallbackBuilder callbackBuilder) {
        Function0<Unit> completeAction$app_googleRelease = callbackBuilder.getCompleteAction$app_googleRelease();
        if (completeAction$app_googleRelease != null) {
            completeAction$app_googleRelease.invoke();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendGifData$lambda$51$lambda$49(SendCore.CallbackBuilder callbackBuilder, final BusinessControlModel businessControlModel, int i) {
        Log.v("ruis", "sendGifData onError");
        UtilsExtensionKt.ui(new Function0() { // from class: com.wifiled.ipixels.ui.control.BusinessControlModel$$ExternalSyntheticLambda34
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit sendGifData$lambda$51$lambda$49$lambda$48;
                sendGifData$lambda$51$lambda$49$lambda$48 = BusinessControlModel.sendGifData$lambda$51$lambda$49$lambda$48(BusinessControlModel.this);
                return sendGifData$lambda$51$lambda$49$lambda$48;
            }
        });
        Function1<Integer, Unit> errorAction$app_googleRelease = callbackBuilder.getErrorAction$app_googleRelease();
        if (errorAction$app_googleRelease != null) {
            errorAction$app_googleRelease.invoke(Integer.valueOf(i));
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendGifData$lambda$51$lambda$49$lambda$48(BusinessControlModel businessControlModel) {
        businessControlModel.getShowToastStrEvent().setValue(App.INSTANCE.getContext().getString(R.string.msg_send_fail));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit sendGifData$lambda$51$lambda$50(SendCore.CallbackBuilder callbackBuilder, byte[] it) {
        Intrinsics.checkNotNullParameter(it, "it");
        Function1<byte[], Unit> resultAction$app_googleRelease = callbackBuilder.getResultAction$app_googleRelease();
        if (resultAction$app_googleRelease != null) {
            resultAction$app_googleRelease.invoke(it);
        }
        return Unit.INSTANCE;
    }

    public final void getCnData(Context context, String fileSize) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(fileSize, "fileSize");
        ArrayList arrayList = new ArrayList();
        if (SPUtils.contains(context, "remote_text_list_cn_" + AppConfig.INSTANCE.getLedType())) {
            if (SPUtils.get(context, "remote_text_list_cn_" + AppConfig.INSTANCE.getLedType(), new ArrayList()) != null) {
                Object obj = SPUtils.get(context, "remote_text_list_cn_" + AppConfig.INSTANCE.getLedType(), new ArrayList());
                Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
                arrayList = (List) obj;
                this.customGetCNDataEvent.setValue(arrayList);
            }
        }
        for (int i = 1; i < 10; i++) {
            arrayList.add(new BusinessRemoteData(true, "", 1, 0, FileUtil.assetFileDescriptorToByteArray(App.INSTANCE.getContext(), "remote/yk_anim_cn_" + fileSize + "_" + i + ".gif"), false, null, 104, null));
        }
        this.customGetCNDataEvent.setValue(arrayList);
    }

    public final void getEnData(Context context, String fileSize) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(fileSize, "fileSize");
        ArrayList arrayList = new ArrayList();
        if (SPUtils.contains(context, "remote_text_list_en_" + AppConfig.INSTANCE.getLedType())) {
            if (SPUtils.get(context, "remote_text_list_en_" + AppConfig.INSTANCE.getLedType(), new ArrayList()) != null) {
                Object obj = SPUtils.get(context, "remote_text_list_en_" + AppConfig.INSTANCE.getLedType(), new ArrayList());
                Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
                arrayList = (List) obj;
                this.customGetENDataEvent.setValue(arrayList);
            }
        }
        for (int i = 1; i < 10; i++) {
            arrayList.add(new BusinessRemoteData(true, "", 1, 0, FileUtil.assetFileDescriptorToByteArray(App.INSTANCE.getContext(), "remote/yk_anim_en_" + fileSize + "_" + i + ".gif"), false, null, 104, null));
        }
        this.customGetENDataEvent.setValue(arrayList);
    }

    public final void resetCnData(Context context, String fileSize) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(fileSize, "fileSize");
        ArrayList arrayList = new ArrayList();
        if (SPUtils.contains(context, "remote_text_list_cn_" + AppConfig.INSTANCE.getLedType())) {
            SPUtils.remove(context, "remote_text_list_cn_" + AppConfig.INSTANCE.getLedType());
        }
        for (int i = 1; i < 10; i++) {
            arrayList.add(new BusinessRemoteData(true, "", 1, 0, FileUtil.assetFileDescriptorToByteArray(App.INSTANCE.getContext(), "remote/yk_anim_cn_" + fileSize + "_" + i + ".gif"), false, null, 104, null));
        }
        this.customGetCNDataEvent.setValue(arrayList);
    }

    public final void resetEnData(Context context, String fileSize) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(fileSize, "fileSize");
        ArrayList arrayList = new ArrayList();
        if (SPUtils.contains(context, "remote_text_list_en_" + AppConfig.INSTANCE.getLedType())) {
            SPUtils.remove(context, "remote_text_list_en_" + AppConfig.INSTANCE.getLedType());
        }
        for (int i = 1; i < 10; i++) {
            arrayList.add(new BusinessRemoteData(true, "", 1, 0, FileUtil.assetFileDescriptorToByteArray(App.INSTANCE.getContext(), "remote/yk_anim_en_" + fileSize + "_" + i + ".gif"), false, null, 104, null));
        }
        this.customGetCNDataEvent.setValue(arrayList);
    }
}
