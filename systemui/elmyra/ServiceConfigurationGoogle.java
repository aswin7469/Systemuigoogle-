package com.google.android.systemui.elmyra;

import com.google.android.systemui.elmyra.sensors.GestureSensor;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class ServiceConfigurationGoogle {
    public final List mActions;
    public final List mFeedbackEffects;
    public final List mGates;
    public final GestureSensor mGestureSensor;

    public ServiceConfigurationGoogle(Set set, Set set2, Set set3, GestureSensor gestureSensor) {
        this.mActions = new ArrayList(set2);
        this.mFeedbackEffects = new ArrayList(set3);
        this.mGates = new ArrayList(set);
        this.mGestureSensor = gestureSensor;
    }
}
