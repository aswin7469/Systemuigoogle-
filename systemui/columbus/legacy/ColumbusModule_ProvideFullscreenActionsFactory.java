package com.google.android.systemui.columbus.legacy;

import com.google.android.systemui.columbus.legacy.actions.DismissTimer;
import com.google.android.systemui.columbus.legacy.actions.SettingsAction;
import com.google.android.systemui.columbus.legacy.actions.SilenceCall;
import com.google.android.systemui.columbus.legacy.actions.SnoozeAlarm;
import dagger.internal.Preconditions;
import java.util.List;
import javax.inject.Provider;
import kotlin.collections.CollectionsKt__CollectionsKt;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class ColumbusModule_ProvideFullscreenActionsFactory implements Provider {
    public static List provideFullscreenActions(DismissTimer dismissTimer, SnoozeAlarm snoozeAlarm, SilenceCall silenceCall, SettingsAction settingsAction) {
        List listOf = CollectionsKt__CollectionsKt.listOf(dismissTimer, snoozeAlarm, silenceCall, settingsAction);
        Preconditions.checkNotNullFromProvides(listOf);
        return listOf;
    }
}
