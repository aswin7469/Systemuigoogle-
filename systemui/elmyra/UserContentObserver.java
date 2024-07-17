package com.google.android.systemui.elmyra;

import android.app.ActivityManager;
import android.app.SynchronousUserSwitchObserver;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.RemoteException;
import android.util.Log;
import java.util.function.Consumer;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class UserContentObserver extends ContentObserver {
    public final Consumer mCallback;
    public final Context mContext;
    public final Uri mSettingsUri;
    public final AnonymousClass1 mUserSwitchCallback = new SynchronousUserSwitchObserver() {
        public final void onUserSwitching(int i) {
            UserContentObserver userContentObserver = UserContentObserver.this;
            userContentObserver.mContext.getContentResolver().unregisterContentObserver(userContentObserver);
            userContentObserver.mContext.getContentResolver().registerContentObserver(userContentObserver.mSettingsUri, false, userContentObserver, -2);
            UserContentObserver userContentObserver2 = UserContentObserver.this;
            userContentObserver2.mCallback.accept(userContentObserver2.mSettingsUri);
        }
    };

    public UserContentObserver(Context context, Uri uri, Consumer consumer, boolean z) {
        super(new Handler(context.getMainLooper()));
        this.mContext = context;
        this.mSettingsUri = uri;
        this.mCallback = consumer;
        if (z) {
            activate();
        }
    }

    public final void activate() {
        this.mContext.getContentResolver().unregisterContentObserver(this);
        this.mContext.getContentResolver().registerContentObserver(this.mSettingsUri, false, this, -2);
        try {
            ActivityManager.getService().registerUserSwitchObserver(this.mUserSwitchCallback, "Elmyra/UserContentObserver");
        } catch (RemoteException e) {
            Log.e("Elmyra/UserContentObserver", "Failed to register user switch observer", e);
        }
    }

    public final void deactivate() {
        this.mContext.getContentResolver().unregisterContentObserver(this);
        try {
            ActivityManager.getService().unregisterUserSwitchObserver(this.mUserSwitchCallback);
        } catch (RemoteException e) {
            Log.e("Elmyra/UserContentObserver", "Failed to unregister user switch observer", e);
        }
    }

    public final void onChange(boolean z, Uri uri) {
        this.mCallback.accept(uri);
    }
}
