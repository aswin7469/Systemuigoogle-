package com.google.android.systemui.assist.uihints;

import android.content.Context;
import android.view.ViewGroup;
import com.android.systemui.navigationbar.NavigationModeController;
import javax.inject.Provider;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class GlowController_Factory implements Provider {
    public static GlowController newInstance(Context context, ViewGroup viewGroup, TouchInsideHandler touchInsideHandler, NavigationModeController navigationModeController) {
        return new GlowController(context, viewGroup, touchInsideHandler, navigationModeController);
    }
}
