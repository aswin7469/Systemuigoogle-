package com.google.android.systemui.assist.uihints;

import android.content.Context;
import com.google.android.systemui.assist.uihints.NgaMessageHandler;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class AssistantWarmer {
    public final Context context;
    public boolean primed;
    public NgaMessageHandler.WarmingRequest request;

    public AssistantWarmer(Context context2) {
        this.context = context2;
    }
}
