package com.wifiled.ipixels.utils;

import android.graphics.Typeface;
import com.wifiled.ipixels.App;
import java.util.HashMap;

/* loaded from: classes3.dex */
public class FontUtils {
    private static HashMap<Integer, Typeface> fontCache = new HashMap<>();

    public static Typeface getTypeface(int iType) {
        Typeface createFromAsset;
        Typeface typeface = fontCache.get(Integer.valueOf(iType));
        if (typeface != null) {
            return typeface;
        }
        try {
            if (iType == 16) {
                createFromAsset = Typeface.createFromAsset(App.context.getAssets(), "fonts/方正像素12.TTF");
            } else if (iType == 19) {
                createFromAsset = Typeface.createFromAsset(App.context.getAssets(), "fonts/cusong16_zitidi.com.ttf");
            } else {
                if (iType != 20) {
                    switch (iType) {
                        case 0:
                            createFromAsset = Typeface.createFromAsset(App.context.getAssets(), "fonts/simsun.ttc");
                            break;
                        case 1:
                            createFromAsset = Typeface.createFromAsset(App.context.getAssets(), "fonts/黑体.ttf");
                            break;
                        case 2:
                            createFromAsset = Typeface.createFromAsset(App.context.getAssets(), "fonts/楷体.ttf");
                            break;
                        case 3:
                            createFromAsset = Typeface.createFromAsset(App.context.getAssets(), "fonts/隶书.ttf");
                            break;
                        case 4:
                            createFromAsset = Typeface.createFromAsset(App.context.getAssets(), "fonts/微软雅黑.ttf");
                            break;
                        case 5:
                            createFromAsset = Typeface.createFromAsset(App.context.getAssets(), "fonts/幼圆.ttf");
                            break;
                        case 6:
                            createFromAsset = Typeface.createFromAsset(App.context.getAssets(), "fonts/站酷快乐体.ttf");
                            break;
                        case 7:
                            createFromAsset = Typeface.createFromAsset(App.context.getAssets(), "fonts/华文琥珀.ttf");
                            break;
                        default:
                            switch (iType) {
                                case 22:
                                    createFromAsset = Typeface.createFromAsset(App.context.getAssets(), "fonts/NotoSansKR-Medium.ttf");
                                    break;
                                case 23:
                                    createFromAsset = Typeface.createFromAsset(App.context.getAssets(), "fonts/PixeloidSans.ttf");
                                    break;
                                case 24:
                                    Typeface.createFromAsset(App.context.getAssets(), "fonts/HarmonyOS_Sans_Naskh_Arabic_Medium.ttf");
                                    createFromAsset = Typeface.createFromAsset(App.context.getAssets(), "fonts/ArialNova-Bold.ttf");
                                    break;
                                default:
                                    switch (iType) {
                                        case 26:
                                            createFromAsset = Typeface.createFromAsset(App.context.getAssets(), "fonts/ArialNova-Bold.ttf");
                                            break;
                                        case 27:
                                            createFromAsset = Typeface.createFromAsset(App.context.getAssets(), "fonts/ARIAL.TTF");
                                            break;
                                        case 28:
                                            createFromAsset = Typeface.createFromAsset(App.context.getAssets(), "fonts/NanumGothicBold.ttf");
                                            break;
                                        case 29:
                                            break;
                                        default:
                                            createFromAsset = Typeface.createFromAsset(App.context.getAssets(), "fonts/simsun.ttf");
                                            break;
                                    }
                            }
                    }
                } else {
                    Typeface.createFromAsset(App.context.getAssets(), "fonts/NotoSansDevanagari-Medium.ttf");
                }
                createFromAsset = Typeface.createFromAsset(App.context.getAssets(), "fonts/AnekTamil.ttf");
            }
            fontCache.put(Integer.valueOf(iType), createFromAsset);
            return createFromAsset;
        } catch (Exception unused) {
            return null;
        }
    }
}
