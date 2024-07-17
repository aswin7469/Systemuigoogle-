package com.google.android.systemui.assist;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.os.RemoteException;
import android.provider.Settings;
import android.util.Log;
import android.view.animation.Interpolator;
import com.android.internal.app.AssistUtils;
import com.android.systemui.util.Assert;
import java.util.Arrays;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class OpaEnabledReceiver$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ OpaEnabledReceiver f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ BroadcastReceiver.PendingResult f$2;

    public /* synthetic */ OpaEnabledReceiver$$ExternalSyntheticLambda0(OpaEnabledReceiver opaEnabledReceiver, boolean z, BroadcastReceiver.PendingResult pendingResult) {
        this.f$0 = opaEnabledReceiver;
        this.f$1 = z;
        this.f$2 = pendingResult;
    }

    public final void run() {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        int i;
        String str;
        boolean z5;
        OpaEnabledReceiver opaEnabledReceiver = this.f$0;
        boolean z6 = this.f$1;
        BroadcastReceiver.PendingResult pendingResult = this.f$2;
        OpaEnabledSettings opaEnabledSettings = opaEnabledReceiver.mOpaEnabledSettings;
        opaEnabledSettings.getClass();
        Assert.isNotMainThread();
        Context context = opaEnabledSettings.mContext;
        if (Settings.Secure.getIntForUser(context.getContentResolver(), "systemui.google.opa_enabled", 0, ActivityManager.getCurrentUser()) != 0) {
            z = true;
        } else {
            z = false;
        }
        opaEnabledReceiver.mIsOpaEligible = z;
        Assert.isNotMainThread();
        Interpolator interpolator = OpaUtils.INTERPOLATOR_40_40;
        ComponentName assistComponentForUser = new AssistUtils(context).getAssistComponentForUser(-2);
        if (assistComponentForUser == null || !"com.google.android.googlequicksearchbox/com.google.android.voiceinteraction.GsaVoiceInteractionService".equals(assistComponentForUser.flattenToString())) {
            z2 = false;
        } else {
            z2 = true;
        }
        opaEnabledReceiver.mIsAGSAAssistant = z2;
        Assert.isNotMainThread();
        try {
            z3 = opaEnabledSettings.mLockSettings.getBoolean("systemui.google.opa_user_enabled", false, ActivityManager.getCurrentUser());
        } catch (RemoteException e) {
            Log.e("OpaEnabledSettings", "isOpaEnabled RemoteException", e);
            z3 = false;
        }
        opaEnabledReceiver.mIsOpaEnabled = z3;
        int[] iArr = opaEnabledReceiver.mAssistOverrideInvocationTypes;
        if (iArr == null || !Arrays.stream(iArr).anyMatch(new OpaEnabledReceiver$$ExternalSyntheticLambda2())) {
            z4 = false;
        } else {
            z4 = true;
        }
        Assert.isNotMainThread();
        Resources resources = context.getResources();
        if (z4) {
            i = 17891834;
        } else {
            i = 17891380;
        }
        boolean z7 = resources.getBoolean(i);
        ContentResolver contentResolver = context.getContentResolver();
        if (z4) {
            str = "search_long_press_home_enabled";
        } else {
            str = "assist_long_press_home_enabled";
        }
        if (Settings.Secure.getInt(contentResolver, str, z7 ? 1 : 0) != 0) {
            z5 = true;
        } else {
            z5 = false;
        }
        opaEnabledReceiver.mIsLongPressHomeEnabled = z5;
        Executor executor = opaEnabledReceiver.mFgExecutor;
        if (z6) {
            executor.execute(new OpaEnabledReceiver$$ExternalSyntheticLambda1(0, opaEnabledReceiver));
        }
        if (pendingResult != null) {
            executor.execute(new OpaEnabledReceiver$$ExternalSyntheticLambda1(1, pendingResult));
        }
    }
}
