package com.google.android.systemui.assist.uihints;

import android.view.ViewGroup;
import javax.inject.Provider;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class ScrimController_Factory implements Provider {
    public static ScrimController newInstance(ViewGroup viewGroup, Object obj, Object obj2, TouchInsideHandler touchInsideHandler) {
        return new ScrimController(viewGroup, (OverlappedElementController) obj, (LightnessProvider) obj2, touchInsideHandler);
    }
}
