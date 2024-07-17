package com.google.android.systemui.columbus;

import com.android.internal.logging.UiEventLogger;
import kotlin.enums.EnumEntriesKt;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public enum ColumbusEvent implements UiEventLogger.UiEventEnum {
    COLUMBUS_DOUBLE_TAP_DETECTED(628),
    COLUMBUS_INVOKED_ASSISTANT(629),
    COLUMBUS_INVOKED_SCREENSHOT(630),
    COLUMBUS_INVOKED_PLAY_MEDIA(631),
    COLUMBUS_INVOKED_PAUSE_MEDIA(639),
    COLUMBUS_INVOKED_OVERVIEW(632),
    COLUMBUS_INVOKED_NOTIFICATION_SHADE_OPEN(633),
    COLUMBUS_INVOKED_NOTIFICATION_SHADE_CLOSE(634),
    COLUMBUS_INVOKED_LAUNCH_APP(815),
    COLUMBUS_INVOKED_LAUNCH_SHORTCUT(816),
    COLUMBUS_INVOKED_LAUNCH_APP_SECURE(898),
    COLUMBUS_INVOKED_FLASHLIGHT_TOGGLE(932),
    COLUMBUS_INVOKED_ON_SETTINGS(817),
    COLUMBUS_MODE_LOW_POWER_ACTIVE(635),
    COLUMBUS_MODE_HIGH_POWER_ACTIVE(636),
    COLUMBUS_MODE_INACTIVE(637),
    COLUMBUS_RETARGET_DIALOG_SHOWN(899),
    COLUMBUS_RETARGET_APPROVED(900),
    COLUMBUS_RETARGET_NOT_APPROVED(901),
    COLUMBUS_RETARGET_FOLLOW_ON_APPROVED(902),
    COLUMBUS_RETARGET_FOLLOW_ON_NOT_APPROVED(903);
    
    private final int id;

    static {
        ColumbusEvent[] columbusEventArr;
        EnumEntriesKt.enumEntries(columbusEventArr);
    }

    /* access modifiers changed from: public */
    ColumbusEvent(int i) {
        this.id = i;
    }

    public final int getId() {
        return this.id;
    }
}
