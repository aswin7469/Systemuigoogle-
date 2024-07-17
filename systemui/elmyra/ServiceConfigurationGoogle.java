package com.google.android.systemui.elmyra;

import com.google.android.systemui.elmyra.sensors.GestureSensor;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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
