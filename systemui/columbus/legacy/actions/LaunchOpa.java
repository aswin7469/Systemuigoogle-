package com.google.android.systemui.columbus.legacy.actions;

import android.app.KeyguardManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.assist.AssistManager;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.shade.ShadeController;
import com.android.systemui.shade.ShadeControllerImpl;
import com.android.systemui.tuner.TunerService;
import com.google.android.systemui.assist.AssistManagerGoogle;
import com.google.android.systemui.columbus.ColumbusEvent;
import com.google.android.systemui.columbus.legacy.ColumbusContentObserver;
import com.google.android.systemui.columbus.legacy.sensors.GestureSensor;
import dagger.Lazy;
import java.util.Set;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class LaunchOpa extends UserAction {
    public final AssistManagerGoogle assistManager;
    public boolean enableForAnyAssistant;
    public boolean isGestureEnabled;
    public boolean isOpaEnabled;
    public final Lazy keyguardManager;
    public final LaunchOpa$opaEnabledListener$1 opaEnabledListener;
    public final ShadeController shadeController;
    public final String tag = "Columbus/LaunchOpa";
    public final LaunchOpa$tunable$1 tunable;
    public final UiEventLogger uiEventLogger;

    public LaunchOpa(Context context, ShadeController shadeController2, Set set, AssistManager assistManager2, Lazy lazy, TunerService tunerService, ColumbusContentObserver.Factory factory, UiEventLogger uiEventLogger2) {
        super(context, set);
        AssistManagerGoogle assistManagerGoogle;
        boolean z;
        this.shadeController = shadeController2;
        this.keyguardManager = lazy;
        this.uiEventLogger = uiEventLogger2;
        if (assistManager2 instanceof AssistManagerGoogle) {
            assistManagerGoogle = (AssistManagerGoogle) assistManager2;
        } else {
            assistManagerGoogle = null;
        }
        this.assistManager = assistManagerGoogle;
        LaunchOpa$opaEnabledListener$1 launchOpa$opaEnabledListener$1 = new LaunchOpa$opaEnabledListener$1(this);
        Uri uriFor = Settings.Secure.getUriFor("assist_gesture_enabled");
        LaunchOpa$settingsObserver$1 launchOpa$settingsObserver$1 = new LaunchOpa$settingsObserver$1(this);
        factory.getClass();
        ColumbusContentObserver columbusContentObserver = new ColumbusContentObserver(factory.contentResolver, uriFor, launchOpa$settingsObserver$1, factory.userTracker, factory.executor, factory.handler);
        LaunchOpa$tunable$1 launchOpa$tunable$1 = new LaunchOpa$tunable$1(this);
        boolean z2 = true;
        if (Settings.Secure.getIntForUser(context.getContentResolver(), "assist_gesture_enabled", 1, -2) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.isGestureEnabled = z;
        this.enableForAnyAssistant = Settings.Secure.getInt(context.getContentResolver(), "assist_gesture_any_assistant", 0) != 1 ? false : z2;
        ((UserTrackerImpl) columbusContentObserver.userTracker).addCallback(columbusContentObserver.userTrackerCallback, columbusContentObserver.executor);
        columbusContentObserver.contentResolver.contentResolver.unregisterContentObserver(columbusContentObserver);
        columbusContentObserver.contentResolver.contentResolver.registerContentObserver(columbusContentObserver.settingsUri, false, columbusContentObserver, ((UserTrackerImpl) columbusContentObserver.userTracker).getUserId());
        tunerService.addTunable(launchOpa$tunable$1, "assist_gesture_any_assistant");
        if (assistManagerGoogle != null) {
            assistManagerGoogle.addOpaEnabledListener(launchOpa$opaEnabledListener$1);
        }
        updateAvailable$5();
    }

    public final boolean availableOnLockscreen() {
        return true;
    }

    public final String getTag$vendor__unbundled_google__packages__SystemUIGoogle__android_common__sysuig() {
        return this.tag;
    }

    public final void onTrigger(GestureSensor.DetectionProperties detectionProperties) {
        long j;
        int i;
        this.uiEventLogger.log(ColumbusEvent.COLUMBUS_INVOKED_ASSISTANT);
        ((ShadeControllerImpl) this.shadeController).cancelExpansionAndCollapseShade();
        if (detectionProperties != null) {
            j = detectionProperties.actionId;
        } else {
            j = 0;
        }
        Bundle bundle = new Bundle();
        if (((KeyguardManager) this.keyguardManager.get()).isKeyguardLocked()) {
            i = 120;
        } else {
            i = 119;
        }
        bundle.putInt("triggered_by", i);
        bundle.putLong("latency_id", j);
        bundle.putInt("invocation_type", 2);
        AssistManagerGoogle assistManagerGoogle = this.assistManager;
        if (assistManagerGoogle != null) {
            assistManagerGoogle.startAssist(bundle);
        }
    }

    public final String toString() {
        String action = super.toString();
        boolean z = this.isGestureEnabled;
        boolean z2 = this.isOpaEnabled;
        return action + " [isGestureEnabled -> " + z + "; isOpaEnabled -> " + z2 + "]";
    }

    public final void updateAvailable$5() {
        boolean z;
        if (!this.isGestureEnabled || !this.isOpaEnabled) {
            z = false;
        } else {
            z = true;
        }
        setAvailable(z);
    }
}
