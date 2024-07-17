package com.google.android.systemui.columbus.legacy;

import com.google.android.systemui.columbus.legacy.actions.LaunchApp;
import com.google.android.systemui.columbus.legacy.actions.LaunchOpa;
import com.google.android.systemui.columbus.legacy.actions.LaunchOverview;
import com.google.android.systemui.columbus.legacy.actions.ManageMedia;
import com.google.android.systemui.columbus.legacy.actions.OpenNotificationShade;
import com.google.android.systemui.columbus.legacy.actions.TakeScreenshot;
import com.google.android.systemui.columbus.legacy.actions.ToggleFlashlight;
import java.util.Map;
import javax.inject.Provider;
import kotlin.Pair;
import kotlin.collections.MapsKt;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class ColumbusModule_ProvideUserSelectedActionsFactory implements Provider {
    public static Map provideUserSelectedActions(LaunchOpa launchOpa, ManageMedia manageMedia, TakeScreenshot takeScreenshot, LaunchOverview launchOverview, OpenNotificationShade openNotificationShade, LaunchApp launchApp, ToggleFlashlight toggleFlashlight) {
        return MapsKt.mapOf(new Pair("assistant", launchOpa), new Pair("media", manageMedia), new Pair("screenshot", takeScreenshot), new Pair("overview", launchOverview), new Pair("notifications", openNotificationShade), new Pair("launch", launchApp), new Pair("flashlight", toggleFlashlight));
    }
}
