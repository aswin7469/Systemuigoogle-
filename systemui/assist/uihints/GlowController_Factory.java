package com.google.android.systemui.assist.uihints;

import android.content.Context;
import android.view.ViewGroup;
import com.android.systemui.navigationbar.NavigationModeController;
import javax.inject.Provider;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class GlowController_Factory implements Provider {
    public static GlowController newInstance(Context context, ViewGroup viewGroup, TouchInsideHandler touchInsideHandler, NavigationModeController navigationModeController) {
        return new GlowController(context, viewGroup, touchInsideHandler, navigationModeController);
    }
}
