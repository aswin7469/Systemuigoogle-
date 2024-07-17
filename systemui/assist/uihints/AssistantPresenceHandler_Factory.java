package com.google.android.systemui.assist.uihints;

import android.content.Context;
import com.android.internal.app.AssistUtils;
import javax.inject.Provider;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class AssistantPresenceHandler_Factory implements Provider {
    public static AssistantPresenceHandler newInstance(Context context, AssistUtils assistUtils) {
        return new AssistantPresenceHandler(context, assistUtils);
    }
}
