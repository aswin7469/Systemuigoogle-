package com.google.android.systemui.assist.uihints;

import android.content.Context;
import com.google.android.systemui.assist.uihints.NgaMessageHandler;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class AssistantWarmer {
    public final Context context;
    public boolean primed;
    public NgaMessageHandler.WarmingRequest request;

    public AssistantWarmer(Context context2) {
        this.context = context2;
    }
}
