package com.google.android.systemui.assist;

import android.content.Context;
import com.android.internal.app.AssistUtils;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.assist.AssistLogger;
import com.android.systemui.assist.PhoneStateMonitor;
import com.android.systemui.settings.UserTracker;
import com.google.android.systemui.assist.uihints.AssistantPresenceHandler;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class GoogleAssistLogger extends AssistLogger {
    public final AssistantPresenceHandler assistantPresenceHandler;

    public GoogleAssistLogger(Context context, UiEventLogger uiEventLogger, AssistUtils assistUtils, PhoneStateMonitor phoneStateMonitor, UserTracker userTracker, AssistantPresenceHandler assistantPresenceHandler2) {
        super(context, uiEventLogger, assistUtils, phoneStateMonitor, userTracker);
        this.assistantPresenceHandler = assistantPresenceHandler2;
    }
}
