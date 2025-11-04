package com.wifiled.ipixels.ui.imgtxt;

import android.graphics.Bitmap;
import android.graphics.Typeface;
import com.blankj.utilcode.util.LogUtils;
import com.wifiled.ipixels.AppConfig;
import com.wifiled.ipixels.core.text.CharacterUtilsKt;
import com.wifiled.ipixels.utils.FontUtils;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: TextAnimationActivity.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.wifiled.ipixels.ui.imgtxt.TextAnimationActivity$createGif$2", f = "TextAnimationActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class TextAnimationActivity$createGif$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Ref.IntRef $allTextLength;
    final /* synthetic */ boolean $isSave;
    final /* synthetic */ Ref.IntRef $maxLength;
    final /* synthetic */ int $position;
    final /* synthetic */ StringBuilder $str;
    final /* synthetic */ List<String> $strList;
    final /* synthetic */ String $text;
    final /* synthetic */ Ref.IntRef $width;
    int label;
    final /* synthetic */ TextAnimationActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TextAnimationActivity$createGif$2(String str, Ref.IntRef intRef, Ref.IntRef intRef2, StringBuilder sb, List<String> list, int i, TextAnimationActivity textAnimationActivity, Ref.IntRef intRef3, boolean z, Continuation<? super TextAnimationActivity$createGif$2> continuation) {
        super(2, continuation);
        this.$text = str;
        this.$allTextLength = intRef;
        this.$maxLength = intRef2;
        this.$str = sb;
        this.$strList = list;
        this.$position = i;
        this.this$0 = textAnimationActivity;
        this.$width = intRef3;
        this.$isSave = z;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TextAnimationActivity$createGif$2(this.$text, this.$allTextLength, this.$maxLength, this.$str, this.$strList, this.$position, this.this$0, this.$width, this.$isSave, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TextAnimationActivity$createGif$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        int i;
        int i2;
        int i3;
        int i4;
        Bitmap createScaledTextBitmapVertical;
        float f;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        Bitmap createScaledTextBitmapVertical2;
        float f2;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        Bitmap createScaledTextBitmapVertical3;
        float f3;
        int i19;
        int i20;
        int i21;
        int i22;
        int i23;
        int i24;
        int i25;
        Bitmap createScaledTextBitmapVertical4;
        float f4;
        int i26;
        int i27;
        int i28;
        Typeface typeface;
        int i29;
        int i30;
        int i31;
        int i32;
        Bitmap createRotateTextBitmapVertical;
        float f5;
        boolean add;
        int i33;
        int i34;
        int i35;
        Typeface typeface2;
        int i36;
        int i37;
        int i38;
        int i39;
        Bitmap createMarqueeTextBitmapVertical;
        float f6;
        boolean add2;
        int i40;
        int i41;
        int i42;
        Typeface typeface3;
        int i43;
        int i44;
        int i45;
        int i46;
        int i47;
        Bitmap createMarqueeTopTextBitmapVertical;
        float f7;
        boolean add3;
        int i48;
        int i49;
        int i50;
        Typeface typeface4;
        int i51;
        int i52;
        float f8;
        float f9;
        int i53;
        int i54;
        int i55;
        int i56;
        int i57;
        Bitmap createFlippedTextBitmapVertical;
        float f10;
        float f11;
        boolean add4;
        int i58;
        int i59;
        int i60;
        Typeface typeface5;
        int i61;
        int i62;
        int i63;
        int i64;
        int i65;
        int i66;
        int i67;
        Bitmap createTextTopPrintBitmapVertical;
        float f12;
        boolean add5;
        int i68;
        int i69;
        int i70;
        Typeface typeface6;
        int i71;
        int i72;
        int i73;
        int i74;
        Bitmap createTextcenterPrintBitmapVertical;
        float f13;
        boolean add6;
        int i75;
        int i76;
        int i77;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        int length = this.$text.length();
        int i78 = 0;
        int i79 = 0;
        while (true) {
            if (i79 >= length) {
                break;
            }
            int i80 = TextBitmapUtil.INSTANCE.getByteLength(String.valueOf(this.$text.charAt(i79))) != 1 ? 0 : 2;
            if (TextBitmapUtil.INSTANCE.getByteLength(String.valueOf(this.$text.charAt(i79))) == 3) {
                i80 += 3;
            }
            if (this.$allTextLength.element + i80 <= this.$maxLength.element) {
                this.$allTextLength.element += i80;
                this.$str.append(this.$text.charAt(i79));
                if (i79 == this.$text.length() - 1) {
                    List<String> list = this.$strList;
                    String sb = this.$str.toString();
                    Intrinsics.checkNotNullExpressionValue(sb, "toString(...)");
                    list.add(sb);
                }
            } else {
                List<String> list2 = this.$strList;
                String sb2 = this.$str.toString();
                Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
                list2.add(sb2);
                this.$allTextLength.element = 0;
                StringsKt.clear(this.$str);
                this.$allTextLength.element += i80;
                this.$str.append(this.$text.charAt(i79));
                if (i79 == this.$text.length() - 1) {
                    List<String> list3 = this.$strList;
                    String sb3 = this.$str.toString();
                    Intrinsics.checkNotNullExpressionValue(sb3, "toString(...)");
                    list3.add(sb3);
                }
            }
            i79++;
        }
        ArrayList arrayList = new ArrayList();
        int i81 = (AppConfig.INSTANCE.getLedSize().get(1).intValue() != 32 || AppConfig.INSTANCE.getLedType() == 2) ? 16 : 32;
        int i82 = (AppConfig.INSTANCE.getLedSize().get(1).intValue() != 32 || AppConfig.INSTANCE.getLedType() == 2) ? 20 : 32;
        LogUtils.vTag("ruis", "AppConfig.ledSize[0]-----" + AppConfig.INSTANCE.getLedSize().get(0));
        LogUtils.vTag("ruis", "AppConfig.ledSize[1]-----" + AppConfig.INSTANCE.getLedSize().get(1));
        switch (this.$position) {
            case 0:
                for (String str : this.$strList) {
                    if (CharacterUtilsKt.isChineseStr(str)) {
                        int i83 = 2;
                        while (i83 <= i81) {
                            i8 = this.this$0.mTextDirection;
                            if (i8 == 1) {
                                TextBitmapUtil textBitmapUtil = TextBitmapUtil.INSTANCE;
                                i13 = this.this$0.mTextColor;
                                i14 = this.this$0.mGradientColor;
                                Typeface DEFAULT = Typeface.DEFAULT;
                                Intrinsics.checkNotNullExpressionValue(DEFAULT, "DEFAULT");
                                i9 = i83;
                                createScaledTextBitmapVertical2 = textBitmapUtil.createScaledTextBitmap(str, i9, i13, i14, DEFAULT);
                            } else {
                                i9 = i83;
                                TextBitmapUtil textBitmapUtil2 = TextBitmapUtil.INSTANCE;
                                i10 = this.this$0.mTextColor;
                                i11 = this.this$0.mGradientColor;
                                Typeface DEFAULT2 = Typeface.DEFAULT;
                                Intrinsics.checkNotNullExpressionValue(DEFAULT2, "DEFAULT");
                                createScaledTextBitmapVertical2 = textBitmapUtil2.createScaledTextBitmapVertical(str, i9, i10, i11, DEFAULT2);
                            }
                            int i84 = i9;
                            TextAnimationActivity textAnimationActivity = this.this$0;
                            if (i84 == i81) {
                                i12 = textAnimationActivity.mPauseTimeGif;
                                arrayList.add(new SaveGifData(createScaledTextBitmapVertical2, i12));
                            } else {
                                f2 = textAnimationActivity.mAnimationSpeed;
                                arrayList.add(new SaveGifData(createScaledTextBitmapVertical2, ((int) f2) * 10));
                            }
                            i83 = i84 + 2;
                        }
                    } else {
                        int i85 = 2;
                        while (i85 <= i82) {
                            i = this.this$0.mTextDirection;
                            if (i == 1) {
                                TextBitmapUtil textBitmapUtil3 = TextBitmapUtil.INSTANCE;
                                i6 = this.this$0.mTextColor;
                                i7 = this.this$0.mGradientColor;
                                Typeface typeface7 = FontUtils.getTypeface(19);
                                Intrinsics.checkNotNullExpressionValue(typeface7, "getTypeface(...)");
                                i2 = i85;
                                createScaledTextBitmapVertical = textBitmapUtil3.createScaledTextBitmap(str, i2, i6, i7, typeface7);
                            } else {
                                i2 = i85;
                                TextBitmapUtil textBitmapUtil4 = TextBitmapUtil.INSTANCE;
                                i3 = this.this$0.mTextColor;
                                i4 = this.this$0.mGradientColor;
                                Typeface typeface8 = FontUtils.getTypeface(19);
                                Intrinsics.checkNotNullExpressionValue(typeface8, "getTypeface(...)");
                                createScaledTextBitmapVertical = textBitmapUtil4.createScaledTextBitmapVertical(str, i2, i3, i4, typeface8);
                            }
                            int i86 = i2;
                            TextAnimationActivity textAnimationActivity2 = this.this$0;
                            if (i86 == i82) {
                                i5 = textAnimationActivity2.mPauseTimeGif;
                                arrayList.add(new SaveGifData(createScaledTextBitmapVertical, i5));
                            } else {
                                f = textAnimationActivity2.mAnimationSpeed;
                                arrayList.add(new SaveGifData(createScaledTextBitmapVertical, ((int) f) * 10));
                            }
                            i85 = i86 + 2;
                        }
                    }
                }
                break;
            case 1:
                for (String str2 : this.$strList) {
                    if (CharacterUtilsKt.isChineseStr(str2)) {
                        int i87 = i81 * 2;
                        while (i87 >= i81) {
                            i22 = this.this$0.mTextDirection;
                            if (i22 == 1) {
                                TextBitmapUtil textBitmapUtil5 = TextBitmapUtil.INSTANCE;
                                i27 = this.this$0.mTextColor;
                                i28 = this.this$0.mGradientColor;
                                Typeface DEFAULT3 = Typeface.DEFAULT;
                                Intrinsics.checkNotNullExpressionValue(DEFAULT3, "DEFAULT");
                                i23 = i87;
                                createScaledTextBitmapVertical4 = textBitmapUtil5.createScaledTextBitmap(str2, i23, i27, i28, DEFAULT3);
                            } else {
                                i23 = i87;
                                TextBitmapUtil textBitmapUtil6 = TextBitmapUtil.INSTANCE;
                                i24 = this.this$0.mTextColor;
                                i25 = this.this$0.mGradientColor;
                                Typeface DEFAULT4 = Typeface.DEFAULT;
                                Intrinsics.checkNotNullExpressionValue(DEFAULT4, "DEFAULT");
                                createScaledTextBitmapVertical4 = textBitmapUtil6.createScaledTextBitmapVertical(str2, i23, i24, i25, DEFAULT4);
                            }
                            int i88 = i23;
                            TextAnimationActivity textAnimationActivity3 = this.this$0;
                            if (i88 == i81) {
                                i26 = textAnimationActivity3.mPauseTimeGif;
                                arrayList.add(new SaveGifData(createScaledTextBitmapVertical4, i26));
                            } else {
                                f4 = textAnimationActivity3.mAnimationSpeed;
                                arrayList.add(new SaveGifData(createScaledTextBitmapVertical4, ((int) f4) * 10));
                            }
                            i87 = i88 - 2;
                        }
                    } else {
                        int i89 = i82 * 2;
                        while (i89 >= i82) {
                            i15 = this.this$0.mTextDirection;
                            if (i15 == 1) {
                                TextBitmapUtil textBitmapUtil7 = TextBitmapUtil.INSTANCE;
                                i20 = this.this$0.mTextColor;
                                i21 = this.this$0.mGradientColor;
                                Typeface typeface9 = FontUtils.getTypeface(19);
                                Intrinsics.checkNotNullExpressionValue(typeface9, "getTypeface(...)");
                                i16 = i89;
                                createScaledTextBitmapVertical3 = textBitmapUtil7.createScaledTextBitmap(str2, i16, i20, i21, typeface9);
                            } else {
                                i16 = i89;
                                TextBitmapUtil textBitmapUtil8 = TextBitmapUtil.INSTANCE;
                                i17 = this.this$0.mTextColor;
                                i18 = this.this$0.mGradientColor;
                                Typeface typeface10 = FontUtils.getTypeface(19);
                                Intrinsics.checkNotNullExpressionValue(typeface10, "getTypeface(...)");
                                createScaledTextBitmapVertical3 = textBitmapUtil8.createScaledTextBitmapVertical(str2, i16, i17, i18, typeface10);
                            }
                            int i90 = i16;
                            TextAnimationActivity textAnimationActivity4 = this.this$0;
                            if (i90 == i82) {
                                i19 = textAnimationActivity4.mPauseTimeGif;
                                arrayList.add(new SaveGifData(createScaledTextBitmapVertical3, i19));
                            } else {
                                f3 = textAnimationActivity4.mAnimationSpeed;
                                arrayList.add(new SaveGifData(createScaledTextBitmapVertical3, ((int) f3) * 10));
                            }
                            i89 = i90 - 2;
                        }
                    }
                }
                break;
            case 2:
                for (String str3 : this.$strList) {
                    if (!CharacterUtilsKt.isChineseStr(str3)) {
                        typeface = FontUtils.getTypeface(19);
                        i29 = i82;
                    } else {
                        typeface = Typeface.DEFAULT;
                        i29 = i81;
                    }
                    Typeface typeface11 = typeface;
                    for (int i91 = 0; i91 <= 360; i91 += 20) {
                        i30 = this.this$0.mTextDirection;
                        if (i30 == 1) {
                            TextBitmapUtil textBitmapUtil9 = TextBitmapUtil.INSTANCE;
                            i34 = this.this$0.mTextColor;
                            i35 = this.this$0.mGradientColor;
                            Intrinsics.checkNotNull(typeface11);
                            createRotateTextBitmapVertical = textBitmapUtil9.createRotateTextBitmap(str3, i29, i34, i91, i35, typeface11);
                        } else {
                            TextBitmapUtil textBitmapUtil10 = TextBitmapUtil.INSTANCE;
                            i31 = this.this$0.mTextColor;
                            i32 = this.this$0.mGradientColor;
                            Intrinsics.checkNotNull(typeface11);
                            createRotateTextBitmapVertical = textBitmapUtil10.createRotateTextBitmapVertical(str3, i29, i31, i91, i32, typeface11);
                        }
                        if (createRotateTextBitmapVertical != null) {
                            TextAnimationActivity textAnimationActivity5 = this.this$0;
                            if (i91 == 360) {
                                i33 = textAnimationActivity5.mPauseTimeGif;
                                add = arrayList.add(new SaveGifData(createRotateTextBitmapVertical, i33));
                            } else {
                                f5 = textAnimationActivity5.mAnimationSpeed;
                                add = arrayList.add(new SaveGifData(createRotateTextBitmapVertical, ((int) f5) * 10));
                            }
                            Boxing.boxBoolean(add);
                        }
                    }
                }
                break;
            case 3:
                for (String str4 : this.$strList) {
                    if (!CharacterUtilsKt.isChineseStr(str4)) {
                        typeface2 = FontUtils.getTypeface(19);
                        i36 = i82;
                    } else {
                        typeface2 = Typeface.DEFAULT;
                        i36 = i81;
                    }
                    Typeface typeface12 = typeface2;
                    for (int i92 = 0; i92 <= this.$width.element; i92 += 4) {
                        i37 = this.this$0.mTextDirection;
                        if (i37 == 1) {
                            TextBitmapUtil textBitmapUtil11 = TextBitmapUtil.INSTANCE;
                            i41 = this.this$0.mTextColor;
                            i42 = this.this$0.mGradientColor;
                            Intrinsics.checkNotNull(typeface12);
                            createMarqueeTextBitmapVertical = textBitmapUtil11.createMarqueeTextBitmap(str4, i36, i41, i92, i42, typeface12);
                        } else {
                            TextBitmapUtil textBitmapUtil12 = TextBitmapUtil.INSTANCE;
                            i38 = this.this$0.mTextColor;
                            i39 = this.this$0.mGradientColor;
                            Intrinsics.checkNotNull(typeface12);
                            createMarqueeTextBitmapVertical = textBitmapUtil12.createMarqueeTextBitmapVertical(str4, i36, i38, i92, i39, typeface12);
                        }
                        if (createMarqueeTextBitmapVertical != null) {
                            Ref.IntRef intRef = this.$width;
                            TextAnimationActivity textAnimationActivity6 = this.this$0;
                            if (i92 == intRef.element) {
                                i40 = textAnimationActivity6.mPauseTimeGif;
                                add2 = arrayList.add(new SaveGifData(createMarqueeTextBitmapVertical, i40));
                            } else {
                                f6 = textAnimationActivity6.mAnimationSpeed;
                                add2 = arrayList.add(new SaveGifData(createMarqueeTextBitmapVertical, ((int) f6) * 10));
                            }
                            Boxing.boxBoolean(add2);
                        }
                    }
                }
                break;
            case 4:
                for (String str5 : this.$strList) {
                    if (!CharacterUtilsKt.isChineseStr(str5)) {
                        typeface3 = FontUtils.getTypeface(19);
                        i43 = i82;
                        i44 = 13;
                    } else {
                        typeface3 = Typeface.DEFAULT;
                        i43 = i81;
                        i44 = 14;
                    }
                    if (AppConfig.INSTANCE.getLedType() == 0) {
                        i44 = 38;
                    }
                    if (AppConfig.INSTANCE.getLedTextSize() == 32) {
                        i44 = 28;
                    }
                    for (int i93 = 0; i93 <= i44; i93++) {
                        i45 = this.this$0.mTextDirection;
                        if (i45 == 1) {
                            TextBitmapUtil textBitmapUtil13 = TextBitmapUtil.INSTANCE;
                            i49 = this.this$0.mTextColor;
                            i50 = this.this$0.mGradientColor;
                            Intrinsics.checkNotNull(typeface3);
                            createMarqueeTopTextBitmapVertical = textBitmapUtil13.createMarqueeTopTextBitmap(str5, i43, i49, i93, i50, typeface3);
                        } else {
                            TextBitmapUtil textBitmapUtil14 = TextBitmapUtil.INSTANCE;
                            i46 = this.this$0.mTextColor;
                            i47 = this.this$0.mGradientColor;
                            Intrinsics.checkNotNull(typeface3);
                            createMarqueeTopTextBitmapVertical = textBitmapUtil14.createMarqueeTopTextBitmapVertical(str5, i43, i46, i93, i47, typeface3);
                        }
                        if (createMarqueeTopTextBitmapVertical != null) {
                            TextAnimationActivity textAnimationActivity7 = this.this$0;
                            if (i93 == i44) {
                                i48 = textAnimationActivity7.mPauseTimeGif;
                                add3 = arrayList.add(new SaveGifData(createMarqueeTopTextBitmapVertical, i48));
                            } else {
                                f7 = textAnimationActivity7.mAnimationSpeed;
                                add3 = arrayList.add(new SaveGifData(createMarqueeTopTextBitmapVertical, ((int) f7) * 10));
                            }
                            Boxing.boxBoolean(add3);
                        }
                    }
                }
                break;
            case 5:
                DecimalFormat decimalFormat = new DecimalFormat("#.##");
                DecimalFormat decimalFormat2 = new DecimalFormat("#.#");
                for (String str6 : this.$strList) {
                    if (!CharacterUtilsKt.isChineseStr(str6)) {
                        typeface4 = FontUtils.getTypeface(19);
                        i51 = i82;
                        i52 = 13;
                    } else {
                        typeface4 = Typeface.DEFAULT;
                        i51 = i81;
                        i52 = 14;
                    }
                    if (AppConfig.INSTANCE.getLedType() == 0) {
                        f8 = 0.5f;
                        i52 = 38;
                    } else {
                        f8 = 0.1f;
                    }
                    if (AppConfig.INSTANCE.getLedTextSize() == 32) {
                        i54 = 0;
                        i53 = 28;
                        f9 = 0.1f;
                    } else {
                        f9 = f8;
                        i53 = i52;
                        i54 = 0;
                    }
                    while (i54 <= i53) {
                        i55 = this.this$0.mTextDirection;
                        if (i55 == 1) {
                            TextBitmapUtil textBitmapUtil15 = TextBitmapUtil.INSTANCE;
                            i59 = this.this$0.mTextColor;
                            i60 = this.this$0.mGradientColor;
                            Intrinsics.checkNotNull(typeface4);
                            createFlippedTextBitmapVertical = textBitmapUtil15.createFlippedTextBitmap(str6, i51, i59, f9, i54, i60, typeface4);
                        } else {
                            TextBitmapUtil textBitmapUtil16 = TextBitmapUtil.INSTANCE;
                            i56 = this.this$0.mTextColor;
                            i57 = this.this$0.mGradientColor;
                            Intrinsics.checkNotNull(typeface4);
                            createFlippedTextBitmapVertical = textBitmapUtil16.createFlippedTextBitmapVertical(str6, i51, i56, f9, i54, i57, typeface4);
                        }
                        if (createFlippedTextBitmapVertical != null) {
                            TextAnimationActivity textAnimationActivity8 = this.this$0;
                            if (i54 == i53) {
                                f10 = 0.1f;
                                i58 = textAnimationActivity8.mPauseTimeGif;
                                add4 = arrayList.add(new SaveGifData(createFlippedTextBitmapVertical, i58));
                            } else {
                                f10 = 0.1f;
                                f11 = textAnimationActivity8.mAnimationSpeed;
                                add4 = arrayList.add(new SaveGifData(createFlippedTextBitmapVertical, ((int) f11) * 10));
                            }
                            Boxing.boxBoolean(add4);
                        } else {
                            f10 = 0.1f;
                        }
                        int i94 = i54 + 1;
                        if (AppConfig.INSTANCE.getLedType() == 0) {
                            if (f9 < 1.0f) {
                                float f14 = f9 + 0.05f;
                                try {
                                    String format = decimalFormat.format(Boxing.boxFloat(f14));
                                    Intrinsics.checkNotNullExpressionValue(format, "format(...)");
                                    f9 = Float.parseFloat(format);
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                    f9 = f14 + 0.05f;
                                }
                            }
                        } else if (f9 < 1.0f) {
                            float f15 = f9 + f10;
                            try {
                                String format2 = decimalFormat2.format(Boxing.boxFloat(f15));
                                Intrinsics.checkNotNullExpressionValue(format2, "format(...)");
                                f9 = Float.parseFloat(format2);
                            } catch (NumberFormatException e2) {
                                e2.printStackTrace();
                                f9 = f15 + f10;
                            }
                        }
                        i54 = i94;
                    }
                }
                break;
            case 6:
                for (String str7 : this.$strList) {
                    if (!CharacterUtilsKt.isChineseStr(str7)) {
                        typeface5 = FontUtils.getTypeface(19);
                        i61 = i82;
                    } else {
                        typeface5 = Typeface.DEFAULT;
                        i61 = i81;
                    }
                    Typeface typeface13 = typeface5;
                    if (AppConfig.INSTANCE.getLedType() == 0) {
                        i62 = 24;
                        i63 = 64;
                    } else {
                        i62 = i78;
                        i63 = 16;
                    }
                    int i95 = 6;
                    if (AppConfig.INSTANCE.getLedType() == 6 || AppConfig.INSTANCE.getLedType() == 2 || AppConfig.INSTANCE.getLedType() == 12) {
                        i62 = 12;
                        i63 = 32;
                    }
                    if (AppConfig.INSTANCE.getLedType() == 10 || AppConfig.INSTANCE.getLedType() == 11 || AppConfig.INSTANCE.getLedType() == 13 || AppConfig.INSTANCE.getLedType() == 15 || AppConfig.INSTANCE.getLedType() == 14) {
                        i62 = 8;
                        i63 = 32;
                    }
                    if (AppConfig.INSTANCE.getLedType() == 16) {
                        i63 = 32;
                    } else {
                        i95 = i62;
                    }
                    if (AppConfig.INSTANCE.getLedType() == 18) {
                        i95 = 2;
                        i63 = 32;
                    }
                    if (AppConfig.INSTANCE.getLedType() == 19) {
                        i95 = 2;
                        i63 = 32;
                    }
                    if (AppConfig.INSTANCE.getLedType() == 17) {
                        i95 = 4;
                        i64 = 32;
                    } else {
                        i64 = i63;
                    }
                    while (i95 <= i64) {
                        i65 = this.this$0.mTextDirection;
                        if (i65 == 1) {
                            TextBitmapUtil textBitmapUtil17 = TextBitmapUtil.INSTANCE;
                            i69 = this.this$0.mTextColor;
                            i70 = this.this$0.mGradientColor;
                            Intrinsics.checkNotNull(typeface13);
                            createTextTopPrintBitmapVertical = textBitmapUtil17.createTextTopPrintBitmap(str7, i61, i69, i95, i70, typeface13);
                        } else {
                            TextBitmapUtil textBitmapUtil18 = TextBitmapUtil.INSTANCE;
                            i66 = this.this$0.mTextColor;
                            i67 = this.this$0.mGradientColor;
                            Intrinsics.checkNotNull(typeface13);
                            createTextTopPrintBitmapVertical = textBitmapUtil18.createTextTopPrintBitmapVertical(str7, i61, i66, i95, i67, typeface13);
                        }
                        if (createTextTopPrintBitmapVertical != null) {
                            TextAnimationActivity textAnimationActivity9 = this.this$0;
                            if (i95 == i64) {
                                i68 = textAnimationActivity9.mPauseTimeGif;
                                add5 = arrayList.add(new SaveGifData(createTextTopPrintBitmapVertical, i68));
                            } else {
                                f12 = textAnimationActivity9.mAnimationSpeed;
                                add5 = arrayList.add(new SaveGifData(createTextTopPrintBitmapVertical, ((int) f12) * 10));
                            }
                            Boxing.boxBoolean(add5);
                        }
                        i95++;
                    }
                    i78 = 0;
                }
                break;
            case 7:
                for (String str8 : this.$strList) {
                    if (!CharacterUtilsKt.isChineseStr(str8)) {
                        typeface6 = FontUtils.getTypeface(19);
                        i71 = i82;
                    } else {
                        typeface6 = Typeface.DEFAULT;
                        i71 = i81;
                    }
                    Typeface typeface14 = typeface6;
                    String valueOf = String.valueOf(str8.charAt(0));
                    int i96 = 0;
                    while (i96 < str8.length()) {
                        i72 = this.this$0.mTextDirection;
                        if (i72 == 1) {
                            TextBitmapUtil textBitmapUtil19 = TextBitmapUtil.INSTANCE;
                            i76 = this.this$0.mTextColor;
                            i77 = this.this$0.mGradientColor;
                            Intrinsics.checkNotNull(typeface14);
                            createTextcenterPrintBitmapVertical = textBitmapUtil19.createTextcenterPrintBitmap(valueOf, i71, i76, i77, typeface14);
                        } else {
                            TextBitmapUtil textBitmapUtil20 = TextBitmapUtil.INSTANCE;
                            i73 = this.this$0.mTextColor;
                            i74 = this.this$0.mGradientColor;
                            Intrinsics.checkNotNull(typeface14);
                            createTextcenterPrintBitmapVertical = textBitmapUtil20.createTextcenterPrintBitmapVertical(valueOf, i71, i73, i74, typeface14);
                        }
                        if (createTextcenterPrintBitmapVertical != null) {
                            TextAnimationActivity textAnimationActivity10 = this.this$0;
                            if (i96 == str8.length() - 1) {
                                i75 = textAnimationActivity10.mPauseTimeGif;
                                add6 = arrayList.add(new SaveGifData(createTextcenterPrintBitmapVertical, i75));
                            } else {
                                f13 = textAnimationActivity10.mAnimationSpeed;
                                add6 = arrayList.add(new SaveGifData(createTextcenterPrintBitmapVertical, ((int) f13) * 10));
                            }
                            Boxing.boxBoolean(add6);
                        }
                        i96++;
                        if (i96 < str8.length()) {
                            valueOf = valueOf + str8.charAt(i96);
                        }
                    }
                }
                break;
        }
        this.this$0.saveFramesAsGif2(arrayList, this.$isSave);
        return Unit.INSTANCE;
    }
}
