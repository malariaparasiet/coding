package com.wifiled.baselib;

import com.wifiled.baselib.app.language.Language;

/* loaded from: classes2.dex */
public class Configuration {
    public boolean langable;
    public Language language;
    public String logTag;
    public boolean loggable;

    public static Configuration defalut() {
        return new Builder().build();
    }

    private Configuration(Builder builder) {
        this.loggable = builder.loggable;
        this.logTag = builder.logTag;
        this.language = builder.language;
        this.langable = builder.langable;
    }

    public static final class Builder {
        private boolean loggable = true;
        private String logTag = "Heaton_LOGGER";
        private boolean langable = true;
        private Language language = new Language(Language.MODE.AUTO);

        public Builder loggable(boolean z) {
            this.loggable = z;
            return this;
        }

        public Builder logTag(String str) {
            this.logTag = str;
            return this;
        }

        public Builder language(Language language) {
            this.language = language;
            return this;
        }

        public Builder langable(boolean z) {
            this.langable = z;
            return this;
        }

        public Configuration build() {
            return new Configuration(this);
        }
    }
}
