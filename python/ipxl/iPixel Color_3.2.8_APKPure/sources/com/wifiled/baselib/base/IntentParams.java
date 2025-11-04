package com.wifiled.baselib.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import java.io.Serializable;

/* loaded from: classes2.dex */
public class IntentParams {
    private Activity activity;
    private Intent intent;

    IntentParams(Activity activity, Intent intent) {
        this.intent = intent;
        this.activity = activity;
    }

    public IntentParams put(String str, String str2) {
        this.intent.putExtra(str, str2);
        return this;
    }

    public IntentParams put(String str, int i) {
        this.intent.putExtra(str, i);
        return this;
    }

    public IntentParams put(String str, float f) {
        this.intent.putExtra(str, f);
        return this;
    }

    public IntentParams put(String str, long j) {
        this.intent.putExtra(str, j);
        return this;
    }

    public IntentParams put(String str, short s) {
        this.intent.putExtra(str, s);
        return this;
    }

    public IntentParams put(String str, Parcelable parcelable) {
        this.intent.putExtra(str, parcelable);
        return this;
    }

    public IntentParams put(String str, Serializable serializable) {
        this.intent.putExtra(str, serializable);
        return this;
    }

    public void start() {
        this.activity.startActivity(this.intent);
    }
}
