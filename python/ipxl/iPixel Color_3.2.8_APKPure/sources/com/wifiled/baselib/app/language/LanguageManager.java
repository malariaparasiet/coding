package com.wifiled.baselib.app.language;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.LocaleList;
import android.text.TextUtils;
import com.wifiled.baselib.app.Constance;
import com.wifiled.baselib.app.language.Language;
import com.wifiled.baselib.utils.LogUtils;
import com.wifiled.baselib.utils.SPUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes2.dex */
public class LanguageManager {
    private static Language mLanguage = new Language(Language.MODE.AUTO);
    private static Map<String, Locale> mSupportLanguages;

    public static void init(Context context, boolean z, Language language) {
        mLanguage = language;
        language.setEnable(z);
        List<Locale> locales = language.getLocales();
        mSupportLanguages = new HashMap<String, Locale>(locales.size(), locales) { // from class: com.wifiled.baselib.app.language.LanguageManager.1
            final /* synthetic */ List val$locales;

            {
                this.val$locales = locales;
                Iterator it = locales.iterator();
                while (it.hasNext()) {
                    Locale locale = (Locale) it.next();
                    put(locale.getLanguage(), locale);
                }
            }
        };
    }

    public static Context attachBaseContext(Context context) {
        if (!mLanguage.isEnable()) {
            return context;
        }
        String saveLanguage = getSaveLanguage(context);
        LogUtils.logi("当前语言:>>>" + saveLanguage, new Object[0]);
        return createConfigurationResources(context, saveLanguage);
    }

    public static String getSaveLanguage(Context context) {
        String str = (String) SPUtils.get(context, Constance.SP.LANGUAGE, "");
        return !TextUtils.isEmpty(str) ? str : getPreferredLanguage().getLanguage();
    }

    public static void setSaveLanguage(Context context, String str) {
        SPUtils.put(context, Constance.SP.LANGUAGE, str);
    }

    private static Context createConfigurationResources(Context context, String str) {
        Configuration configuration = context.getResources().getConfiguration();
        configuration.setLocale(new Locale(str));
        return context.createConfigurationContext(configuration);
    }

    public static void applyLanguage(Context context, String str) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = new Locale(str);
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }

    private static boolean isSupportLanguage(String str) {
        return mSupportLanguages.containsKey(str);
    }

    public static Locale getPreferredLanguage() {
        if (mLanguage.getMode() == Language.MODE.AUTO) {
            Locale locale = LocaleList.getDefault().get(0);
            return isSupportLanguage(locale.getLanguage()) ? locale : mLanguage.getDefalutLocale();
        }
        return mLanguage.getDefalutLocale();
    }
}
