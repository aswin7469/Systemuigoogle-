package com.google.android.systemui.assist.uihints;

import android.content.Context;
import com.android.systemui.assist.AssistLogger;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.google.android.systemui.assist.uihints.edgelights.EdgeLightsController;
import dagger.Lazy;
import javax.inject.Provider;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class NgaUiController_Factory implements Provider {
    public static NgaUiController newInstance(Context context, Object obj, TouchInsideHandler touchInsideHandler, Object obj2, EdgeLightsController edgeLightsController, GlowController glowController, ScrimController scrimController, TranscriptionController transcriptionController, IconController iconController, Object obj3, StatusBarStateController statusBarStateController, Lazy lazy, Object obj4, AssistantWarmer assistantWarmer, NavBarFadeController navBarFadeController, AssistLogger assistLogger) {
        return new NgaUiController(context, (TimeoutManager) obj, touchInsideHandler, (OverlayUiHost) obj2, edgeLightsController, glowController, scrimController, transcriptionController, iconController, (LightnessProvider) obj3, statusBarStateController, lazy, (FlingVelocityWrapper) obj4, assistantWarmer, navBarFadeController, assistLogger);
    }
}
