package com.google.android.systemui.columbus.legacy;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import com.android.systemui.settings.UserTracker;
import java.util.concurrent.Executor;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class ColumbusContentObserver extends ContentObserver {
    public final Function1 callback;
    public final ContentResolverWrapper contentResolver;
    public final Executor executor;
    public final Uri settingsUri;
    public final UserTracker userTracker;
    public final ColumbusContentObserver$userTrackerCallback$1 userTrackerCallback = new ColumbusContentObserver$userTrackerCallback$1(this);

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public final class Factory {
        public final ContentResolverWrapper contentResolver;
        public final Executor executor;
        public final Handler handler;
        public final UserTracker userTracker;

        public Factory(ContentResolverWrapper contentResolverWrapper, UserTracker userTracker2, Handler handler2, Executor executor2) {
            this.contentResolver = contentResolverWrapper;
            this.userTracker = userTracker2;
            this.handler = handler2;
            this.executor = executor2;
        }
    }

    public ColumbusContentObserver(ContentResolverWrapper contentResolverWrapper, Uri uri, Function1 function1, UserTracker userTracker2, Executor executor2, Handler handler) {
        super(handler);
        this.contentResolver = contentResolverWrapper;
        this.settingsUri = uri;
        this.callback = function1;
        this.userTracker = userTracker2;
        this.executor = executor2;
    }

    public final void onChange(boolean z, Uri uri) {
        if (uri != null) {
            this.callback.invoke(uri);
        }
    }
}
