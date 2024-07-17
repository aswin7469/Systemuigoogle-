package com.google.android.systemui.assist.uihints.edgelights.mode;

import com.android.internal.logging.UiEventLogger;
import kotlin.enums.EnumEntriesKt;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public enum AssistantModeChangeEvent implements UiEventLogger.UiEventEnum {
    ASSISTANT_SESSION_MODE_GONE(585),
    ASSISTANT_SESSION_MODE_FULL_LISTENING(587),
    ASSISTANT_SESSION_MODE_FULFILL_BOTTOM(588),
    ASSISTANT_SESSION_MODE_FULFILL_PERIMETER(589),
    ASSISTANT_SESSION_MODE_UNKNOWN(590);
    
    public static final Companion Companion = null;
    private final int id;

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class Companion {
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [java.lang.Object, com.google.android.systemui.assist.uihints.edgelights.mode.AssistantModeChangeEvent$Companion] */
    static {
        AssistantModeChangeEvent[] assistantModeChangeEventArr;
        EnumEntriesKt.enumEntries(assistantModeChangeEventArr);
        Companion = new Object();
    }

    /* access modifiers changed from: public */
    AssistantModeChangeEvent(int i) {
        this.id = i;
    }

    public final int getId() {
        return this.id;
    }
}
