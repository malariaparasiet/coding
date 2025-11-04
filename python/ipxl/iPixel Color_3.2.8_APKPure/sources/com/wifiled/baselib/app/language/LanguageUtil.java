package com.wifiled.baselib.app.language;

import android.content.Context;
import com.wifiled.baselib.app.Constance;
import com.wifiled.baselib.utils.SPUtils;
import java.util.Locale;

/* loaded from: classes2.dex */
public class LanguageUtil {
    public static String getSaveLanguage(Context context) {
        return (String) SPUtils.get(context, Constance.SP.LANGUAGE, Locale.getDefault().getLanguage());
    }
}
