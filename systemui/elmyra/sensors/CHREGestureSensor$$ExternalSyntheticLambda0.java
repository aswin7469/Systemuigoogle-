package com.google.android.systemui.elmyra.sensors;

import com.google.android.systemui.elmyra.proto.nano.ContextHubMessages$SensitivityUpdate;
import com.google.android.systemui.elmyra.sensors.config.GestureConfiguration;
import com.google.protobuf.nano.MessageNano;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class CHREGestureSensor$$ExternalSyntheticLambda0 implements GestureConfiguration.Listener {
    public final /* synthetic */ CHREGestureSensor f$0;

    public /* synthetic */ CHREGestureSensor$$ExternalSyntheticLambda0(CHREGestureSensor cHREGestureSensor) {
        this.f$0 = cHREGestureSensor;
    }

    public void onGestureConfigurationChanged(GestureConfiguration gestureConfiguration) {
        CHREGestureSensor cHREGestureSensor = this.f$0;
        cHREGestureSensor.getClass();
        ContextHubMessages$SensitivityUpdate contextHubMessages$SensitivityUpdate = new ContextHubMessages$SensitivityUpdate();
        contextHubMessages$SensitivityUpdate.sensitivity = gestureConfiguration.getSensitivity();
        cHREGestureSensor.sendMessageToNanoApp(202, MessageNano.toByteArray(contextHubMessages$SensitivityUpdate));
    }
}
