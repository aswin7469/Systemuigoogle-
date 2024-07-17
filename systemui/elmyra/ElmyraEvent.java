package com.google.android.systemui.elmyra;

import com.android.internal.logging.UiEventLogger;
import kotlin.enums.EnumEntriesKt;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public enum ElmyraEvent implements UiEventLogger.UiEventEnum {
    ELMYRA_PRIMED(559),
    ELMYRA_RELEASED(560),
    ELMYRA_TRIGGERED_AP_SUSPENDED(561),
    ELMYRA_TRIGGERED_SCREEN_OFF(575),
    ELMYRA_TRIGGERED_SCREEN_ON(576);
    
    private final int id;

    static {
        ElmyraEvent[] elmyraEventArr;
        EnumEntriesKt.enumEntries(elmyraEventArr);
    }

    /* access modifiers changed from: public */
    ElmyraEvent(int i) {
        this.id = i;
    }

    public final int getId() {
        return this.id;
    }
}
