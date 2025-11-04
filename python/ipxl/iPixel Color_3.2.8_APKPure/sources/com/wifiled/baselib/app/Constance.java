package com.wifiled.baselib.app;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes2.dex */
public class Constance {

    @Retention(RetentionPolicy.SOURCE)
    public @interface API {
        public static final String APP_LAST_UPDATE = "app/lastUpdate";
        public static final String APP_UPLOAD_CRASH = "App/add_app_crash";
        public static final String APP_UPLOAD_INSTALL = "app/count";
        public static final String APP_UPLOAD_STATUS_INFO = "App/add_app_status_info";
        public static final String BASE_URL = "http://api.e-toys.cn/api/";
        public static final String BASE_URL2 = "https://manage.heaton.com.cn/api/rm/";
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface APP {
        public static final String PLATFORM = "Android";
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface LANGUAGE {
        public static final int ID_DE = 4;
        public static final int ID_EN = 1;
        public static final int ID_FR = 3;
        public static final int ID_PT = 2;
        public static final int ID_ZN = 0;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface SP {
        public static final String FIRST_INSTALL = "first_install";
        public static final String LANGUAGE = "language";
    }
}
