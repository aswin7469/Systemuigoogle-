package com.google.android.systemui.assist.uihints;

import android.view.ViewGroup;
import javax.inject.Provider;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class ScrimController_Factory implements Provider {
    public static ScrimController newInstance(ViewGroup viewGroup, Object obj, Object obj2, TouchInsideHandler touchInsideHandler) {
        return new ScrimController(viewGroup, (OverlappedElementController) obj, (LightnessProvider) obj2, touchInsideHandler);
    }
}
