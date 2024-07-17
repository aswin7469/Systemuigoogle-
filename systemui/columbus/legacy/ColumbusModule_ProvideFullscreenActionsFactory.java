package com.google.android.systemui.columbus.legacy;

import com.google.android.systemui.columbus.legacy.actions.DismissTimer;
import com.google.android.systemui.columbus.legacy.actions.SettingsAction;
import com.google.android.systemui.columbus.legacy.actions.SilenceCall;
import com.google.android.systemui.columbus.legacy.actions.SnoozeAlarm;
import dagger.internal.Preconditions;
import java.util.List;
import javax.inject.Provider;
import kotlin.collections.CollectionsKt__CollectionsKt;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class ColumbusModule_ProvideFullscreenActionsFactory implements Provider {
    public static List provideFullscreenActions(DismissTimer dismissTimer, SnoozeAlarm snoozeAlarm, SilenceCall silenceCall, SettingsAction settingsAction) {
        List listOf = CollectionsKt__CollectionsKt.listOf(dismissTimer, snoozeAlarm, silenceCall, settingsAction);
        Preconditions.checkNotNullFromProvides(listOf);
        return listOf;
    }
}
