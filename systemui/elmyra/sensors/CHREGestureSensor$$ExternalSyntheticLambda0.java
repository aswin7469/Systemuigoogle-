package com.google.android.systemui.elmyra.sensors;

import com.google.android.systemui.elmyra.proto.nano.ContextHubMessages$SensitivityUpdate;
import com.google.android.systemui.elmyra.sensors.config.GestureConfiguration;
import com.google.protobuf.nano.MessageNano;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class CHREGestureSensor$$ExternalSyntheticLambda0 implements GestureConfiguration.Listener {
    public final /* synthetic */ CHREGestureSensor f$0;

    public /* synthetic */ CHREGestureSensor$$ExternalSyntheticLambda0(CHREGestureSensor cHREGestureSensor) {
        this.f$0 = cHREGestureSensor;
    }

    public final void onGestureConfigurationChanged(GestureConfiguration gestureConfiguration) {
        CHREGestureSensor cHREGestureSensor = this.f$0;
        cHREGestureSensor.getClass();
        ContextHubMessages$SensitivityUpdate contextHubMessages$SensitivityUpdate = new ContextHubMessages$SensitivityUpdate();
        contextHubMessages$SensitivityUpdate.sensitivity = gestureConfiguration.getSensitivity();
        cHREGestureSensor.sendMessageToNanoApp(202, MessageNano.toByteArray(contextHubMessages$SensitivityUpdate));
    }
}
