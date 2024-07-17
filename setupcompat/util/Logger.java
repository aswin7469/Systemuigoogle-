package com.google.android.setupcompat.util;

import android.util.Log;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class Logger {
    public final String prefix;

    public Logger(String str) {
        this.prefix = DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("[", str, "] ");
    }

    public final void atInfo(String str) {
        if (Log.isLoggable("SetupLibrary", 4)) {
            Log.i("SetupLibrary", this.prefix.concat(str));
        }
    }

    public final void e(String str) {
        Log.e("SetupLibrary", this.prefix.concat(str));
    }

    public final void w(String str) {
        Log.w("SetupLibrary", this.prefix.concat(str));
    }

    public final void e(String str, Throwable th) {
        Log.e("SetupLibrary", this.prefix.concat(str), th);
    }
}
