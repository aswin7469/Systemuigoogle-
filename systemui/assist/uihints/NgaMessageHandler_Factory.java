package com.google.android.systemui.assist.uihints;

import android.os.Handler;
import com.android.systemui.navigationbar.NavigationModeController;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class NgaMessageHandler_Factory implements Provider {
    public static NgaMessageHandler newInstance(NgaUiController ngaUiController, AssistantPresenceHandler assistantPresenceHandler, NavigationModeController navigationModeController, Set set, Set set2, Set set3, Set set4, Set set5, Handler handler) {
        return new NgaMessageHandler(ngaUiController, assistantPresenceHandler, navigationModeController, set, set2, set3, set4, set5, handler);
    }
}
