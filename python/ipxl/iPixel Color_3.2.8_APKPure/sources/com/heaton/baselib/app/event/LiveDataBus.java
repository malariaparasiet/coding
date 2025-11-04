package com.heaton.baselib.app.event;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public final class LiveDataBus {
    private final Map<String, BusLiveEvent<Object>> bus;

    public interface Observable<T> {
        void observe(LifecycleOwner lifecycleOwner, Observer<T> observer);

        void observeForever(Observer<T> observer);

        void observeSticky(LifecycleOwner lifecycleOwner, Observer<T> observer);

        void observeStickyForever(Observer<T> observer);

        void postValue(T t);

        void removeObserver(Observer<T> observer);

        void setValue(T t);
    }

    private LiveDataBus() {
        this.bus = new HashMap();
    }

    private static class SingletonHolder {
        private static final LiveDataBus DEFAULT_BUS = new LiveDataBus();

        private SingletonHolder() {
        }
    }

    public static LiveDataBus get() {
        return SingletonHolder.DEFAULT_BUS;
    }

    public synchronized <T> Observable<T> with(String str, Class<T> cls) {
        if (!this.bus.containsKey(str)) {
            this.bus.put(str, new BusLiveEvent<>());
        }
        return this.bus.get(str);
    }

    public Observable<Object> with(String str) {
        return with(str, Object.class);
    }

    private static class BusLiveEvent<T> extends LiveEvent<T> implements Observable<T> {
        @Override // com.heaton.baselib.app.event.LiveEvent, com.heaton.baselib.app.event.LiveDataBus.Observable
        public void observe(LifecycleOwner lifecycleOwner, Observer<T> observer) {
        }

        @Override // com.heaton.baselib.app.event.LiveEvent, com.heaton.baselib.app.event.LiveDataBus.Observable
        public void observeSticky(LifecycleOwner lifecycleOwner, Observer<T> observer) {
        }

        private BusLiveEvent() {
        }
    }
}
