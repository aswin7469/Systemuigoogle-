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
import kotlin.collections.MapsKt___MapsJvmKt;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class ColumbusModule_ProvideUserSelectedActionsFactory implements Provider {
    public static Map provideUserSelectedActions(LaunchOpa launchOpa, ManageMedia manageMedia, TakeScreenshot takeScreenshot, LaunchOverview launchOverview, OpenNotificationShade openNotificationShade, LaunchApp launchApp, ToggleFlashlight toggleFlashlight) {
        return MapsKt___MapsJvmKt.mapOf(new Pair("assistant", launchOpa), new Pair("media", manageMedia), new Pair("screenshot", takeScreenshot), new Pair("overview", launchOverview), new Pair("notifications", openNotificationShade), new Pair("launch", launchApp), new Pair("flashlight", toggleFlashlight));
    }
}
