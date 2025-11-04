package com.wifiled.baselib.app.language;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/* loaded from: classes2.dex */
public class Language {
    private Locale defalutLocale;
    private boolean enable;
    private List<Locale> locales;
    private MODE mode;
    private static final List<Locale> DEFALUT_LOCALES = Arrays.asList(Locale.CHINA, Locale.ENGLISH);
    private static final Locale DEFALUT_LOCALE = Locale.ENGLISH;

    public enum MODE {
        AUTO,
        CUSTOM
    }

    public Language(MODE mode, Locale locale, List<Locale> list) {
        this.enable = true;
        this.mode = mode;
        locale = locale == null ? DEFALUT_LOCALE : locale;
        list = list == null ? DEFALUT_LOCALES : list;
        this.defalutLocale = locale;
        this.locales = list;
    }

    public Language(MODE mode, Locale locale) {
        this(mode, locale, null);
    }

    public Language(MODE mode) {
        this(mode, null, null);
    }

    public List<Locale> getLocales() {
        return this.locales;
    }

    public void setLocales(List<Locale> list) {
        this.locales = list;
    }

    public MODE getMode() {
        return this.mode;
    }

    public void setMode(MODE mode) {
        this.mode = mode;
    }

    public Locale getDefalutLocale() {
        return this.defalutLocale;
    }

    public void setDefalutLocale(Locale locale) {
        this.defalutLocale = locale;
    }

    public boolean isEnable() {
        return this.enable;
    }

    public void setEnable(boolean z) {
        this.enable = z;
    }
}
