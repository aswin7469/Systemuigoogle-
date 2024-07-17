package com.google.android.systemui.assist.uihints;

import android.view.ViewGroup;
import com.android.systemui.statusbar.policy.ConfigurationController;
import javax.inject.Provider;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class TranscriptionController_Factory implements Provider {
    public static TranscriptionController newInstance(ViewGroup viewGroup, TouchInsideHandler touchInsideHandler, Object obj, ConfigurationController configurationController) {
        FlingVelocityWrapper flingVelocityWrapper = (FlingVelocityWrapper) obj;
        return new TranscriptionController(viewGroup, touchInsideHandler, configurationController);
    }
}
