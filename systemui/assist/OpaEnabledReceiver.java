package com.google.android.systemui.assist;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.RemoteException;
import android.os.UserHandle;
import android.provider.Settings;
import android.util.Log;
import com.android.systemui.util.Assert;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class OpaEnabledReceiver {
    public int[] mAssistOverrideInvocationTypes;
    public final Executor mBgExecutor;
    public final Handler mBgHandler;
    public final OpaEnabledBroadcastReceiver mBroadcastReceiver = new OpaEnabledBroadcastReceiver();
    public final AssistantContentObserver mContentObserver;
    public final ContentResolver mContentResolver;
    public final Context mContext;
    public final Executor mFgExecutor;
    public boolean mIsAGSAAssistant;
    public boolean mIsLongPressHomeEnabled;
    public boolean mIsOpaEligible;
    public boolean mIsOpaEnabled;
    public final List mListeners = new ArrayList();
    public final OpaEnabledSettings mOpaEnabledSettings;

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public final class AssistantContentObserver extends ContentObserver {
        public AssistantContentObserver(Context context) {
            super(new Handler(context.getMainLooper()));
        }

        public final void onChange(boolean z, Uri uri) {
            OpaEnabledReceiver.this.updateOpaEnabledState(true, (BroadcastReceiver.PendingResult) null);
        }
    }

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public final class OpaEnabledBroadcastReceiver extends BroadcastReceiver {
        public OpaEnabledBroadcastReceiver() {
        }

        public final void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("com.google.android.systemui.OPA_ENABLED")) {
                boolean booleanExtra = intent.getBooleanExtra("OPA_ENABLED", false);
                OpaEnabledSettings opaEnabledSettings = OpaEnabledReceiver.this.mOpaEnabledSettings;
                opaEnabledSettings.getClass();
                Assert.isNotMainThread();
                Settings.Secure.putIntForUser(opaEnabledSettings.mContext.getContentResolver(), "systemui.google.opa_enabled", booleanExtra ? 1 : 0, ActivityManager.getCurrentUser());
            } else if (intent.getAction().equals("com.google.android.systemui.OPA_USER_ENABLED")) {
                boolean booleanExtra2 = intent.getBooleanExtra("OPA_USER_ENABLED", false);
                OpaEnabledSettings opaEnabledSettings2 = OpaEnabledReceiver.this.mOpaEnabledSettings;
                opaEnabledSettings2.getClass();
                Assert.isNotMainThread();
                try {
                    opaEnabledSettings2.mLockSettings.setBoolean("systemui.google.opa_user_enabled", booleanExtra2, ActivityManager.getCurrentUser());
                } catch (RemoteException e) {
                    Log.e("OpaEnabledSettings", "RemoteException on OPA_USER_ENABLED", e);
                }
            }
            OpaEnabledReceiver.this.updateOpaEnabledState(true, goAsync());
        }
    }

    public OpaEnabledReceiver(Context context, Executor executor, Executor executor2, Handler handler, OpaEnabledSettings opaEnabledSettings) {
        this.mContext = context;
        this.mContentResolver = context.getContentResolver();
        this.mContentObserver = new AssistantContentObserver(context);
        this.mFgExecutor = executor;
        this.mBgExecutor = executor2;
        this.mOpaEnabledSettings = opaEnabledSettings;
        this.mBgHandler = handler;
        updateOpaEnabledState(false, (BroadcastReceiver.PendingResult) null);
        registerContentObserver();
        registerEnabledReceiver(-2);
    }

    public final void dispatchOpaEnabledState(Context context) {
        Log.i("OpaEnabledReceiver", "Dispatching OPA eligble = " + this.mIsOpaEligible + "; AGSA = " + this.mIsAGSAAssistant + "; OPA enabled = " + this.mIsOpaEnabled);
        int i = 0;
        while (true) {
            List list = this.mListeners;
            if (i < ((ArrayList) list).size()) {
                ((OpaEnabledListener) ((ArrayList) list).get(i)).onOpaEnabledReceived(context, this.mIsOpaEligible, this.mIsAGSAAssistant, this.mIsOpaEnabled, this.mIsLongPressHomeEnabled);
                i++;
            } else {
                return;
            }
        }
    }

    public BroadcastReceiver getBroadcastReceiver() {
        return this.mBroadcastReceiver;
    }

    public final void registerContentObserver() {
        Uri uriFor = Settings.Secure.getUriFor("assistant");
        ContentResolver contentResolver = this.mContentResolver;
        AssistantContentObserver assistantContentObserver = this.mContentObserver;
        contentResolver.registerContentObserver(uriFor, false, assistantContentObserver, -2);
        contentResolver.registerContentObserver(Settings.Secure.getUriFor("assist_long_press_home_enabled"), false, assistantContentObserver, -2);
        contentResolver.registerContentObserver(Settings.Secure.getUriFor("search_long_press_home_enabled"), false, assistantContentObserver, -2);
    }

    public final void registerEnabledReceiver(int i) {
        UserHandle userHandle = new UserHandle(i);
        IntentFilter intentFilter = new IntentFilter("com.google.android.systemui.OPA_ENABLED");
        this.mContext.registerReceiverAsUser(this.mBroadcastReceiver, userHandle, intentFilter, "android.permission.CAPTURE_AUDIO_HOTWORD", this.mBgHandler, 2);
        UserHandle userHandle2 = new UserHandle(i);
        IntentFilter intentFilter2 = new IntentFilter("com.google.android.systemui.OPA_USER_ENABLED");
        this.mContext.registerReceiverAsUser(this.mBroadcastReceiver, userHandle2, intentFilter2, "android.permission.CAPTURE_AUDIO_HOTWORD", this.mBgHandler, 2);
    }

    public final void updateOpaEnabledState(boolean z, BroadcastReceiver.PendingResult pendingResult) {
        this.mBgExecutor.execute(new OpaEnabledReceiver$$ExternalSyntheticLambda0(this, z, pendingResult));
    }
}
