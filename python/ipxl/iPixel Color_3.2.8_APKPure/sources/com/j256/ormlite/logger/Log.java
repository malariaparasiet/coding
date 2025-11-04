package com.j256.ormlite.logger;

/* loaded from: classes2.dex */
public interface Log {
    boolean isLevelEnabled(Level level);

    void log(Level level, String str);

    void log(Level level, String str, Throwable th);

    public enum Level {
        TRACE(1),
        DEBUG(2),
        INFO(3),
        WARNING(4),
        ERROR(5),
        FATAL(6);

        private int level;

        Level(int i) {
            this.level = i;
        }

        public boolean isEnabled(Level level) {
            return this.level <= level.level;
        }
    }
}
