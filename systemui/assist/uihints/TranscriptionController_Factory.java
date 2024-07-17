package com.google.android.systemui.assist.uihints;

import android.view.ViewGroup;
import com.android.systemui.statusbar.policy.ConfigurationController;
import javax.inject.Provider;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class TranscriptionController_Factory implements Provider {
    public static TranscriptionController newInstance(ViewGroup viewGroup, TouchInsideHandler touchInsideHandler, Object obj, ConfigurationController configurationController) {
        return new TranscriptionController(viewGroup, touchInsideHandler, (FlingVelocityWrapper) obj, configurationController);
    }
}
