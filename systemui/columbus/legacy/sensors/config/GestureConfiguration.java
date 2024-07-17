package com.google.android.systemui.columbus.legacy.sensors.config;

import android.util.Range;
import com.google.android.systemui.columbus.legacy.sensors.CHREGestureSensor;
import com.google.android.systemui.columbus.legacy.sensors.CHREGestureSensor$initialize$1;
import com.google.android.systemui.columbus.proto.nano.ColumbusProto$RecognizerStart;
import com.google.protobuf.nano.MessageNano;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class GestureConfiguration {
    public static final Range SENSITIVITY_RANGE = Range.create(Float.valueOf(0.0f), Float.valueOf(1.0f));
    public final Function1 adjustmentCallback = new GestureConfiguration$adjustmentCallback$1(this);
    public final List adjustments;
    public CHREGestureSensor$initialize$1 listener;
    public float sensitivity;
    public final SensorConfiguration sensorConfiguration;

    public GestureConfiguration(List list, SensorConfiguration sensorConfiguration2) {
        this.adjustments = list;
        this.sensorConfiguration = sensorConfiguration2;
        this.sensitivity = sensorConfiguration2.defaultSensitivityValue;
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ((LowSensitivitySettingAdjustment) it.next()).callback = this.adjustmentCallback;
        }
        updateSensitivity();
    }

    public final void updateSensitivity() {
        float f = this.sensorConfiguration.defaultSensitivityValue;
        for (LowSensitivitySettingAdjustment lowSensitivitySettingAdjustment : this.adjustments) {
            if (lowSensitivitySettingAdjustment.useLowSensitivity) {
                f = lowSensitivitySettingAdjustment.sensorConfiguration.lowSensitivityValue;
            }
            f = ((Number) SENSITIVITY_RANGE.clamp(Float.valueOf(f))).floatValue();
        }
        if (Math.abs(this.sensitivity - f) >= 0.05f) {
            this.sensitivity = f;
            CHREGestureSensor$initialize$1 cHREGestureSensor$initialize$1 = this.listener;
            if (cHREGestureSensor$initialize$1 != null) {
                CHREGestureSensor cHREGestureSensor = cHREGestureSensor$initialize$1.this$0;
                cHREGestureSensor.getClass();
                ColumbusProto$RecognizerStart columbusProto$RecognizerStart = new ColumbusProto$RecognizerStart(1);
                columbusProto$RecognizerStart.sensitivity = this.sensitivity;
                CHREGestureSensor.sendMessageToNanoApp$default(cHREGestureSensor, 200, MessageNano.toByteArray(columbusProto$RecognizerStart), (Function0) null, 12);
            }
        }
    }
}
