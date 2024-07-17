package com.google.android.systemui.columbus.legacy.sensors;

import android.util.Log;
import java.util.function.Consumer;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class CHREGestureSensorDelegator$startListening$1 implements Consumer {
    public final /* synthetic */ CHREGestureSensorDelegator this$0;

    /* renamed from: com.google.android.systemui.columbus.legacy.sensors.CHREGestureSensorDelegator$startListening$1$1  reason: invalid class name */
    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public final class AnonymousClass1 implements Runnable {
        public final /* synthetic */ int $r8$classId = 1;
        public final /* synthetic */ Object $statusCode;
        public final /* synthetic */ CHREGestureSensorDelegator this$0;

        public AnonymousClass1(CHREGestureSensorDelegator cHREGestureSensorDelegator, GestureController$gestureSensorListener$1 gestureController$gestureSensorListener$1) {
            this.this$0 = cHREGestureSensorDelegator;
            this.$statusCode = gestureController$gestureSensorListener$1;
        }

        public final void run() {
            switch (this.$r8$classId) {
                case 0:
                    Integer num = (Integer) this.$statusCode;
                    if (num != null && num.intValue() == 2) {
                        CHREGestureSensorDelegator cHREGestureSensorDelegator = this.this$0;
                        if (cHREGestureSensorDelegator.gestureSensor instanceof AiAiCHREGestureSensor) {
                            CHREGestureSensorDelegator.access$switchSensor(cHREGestureSensorDelegator, false);
                            return;
                        }
                    }
                    Integer num2 = (Integer) this.$statusCode;
                    if (num2 == null || num2.intValue() != 2) {
                        CHREGestureSensorDelegator cHREGestureSensorDelegator2 = this.this$0;
                        if (cHREGestureSensorDelegator2.gestureSensor instanceof CHREGestureSensor) {
                            CHREGestureSensorDelegator.access$switchSensor(cHREGestureSensorDelegator2, true);
                            return;
                        }
                        return;
                    }
                    return;
                default:
                    this.this$0.gestureSensor.setGestureListener((GestureController$gestureSensorListener$1) this.$statusCode);
                    return;
            }
        }

        public AnonymousClass1(Integer num, CHREGestureSensorDelegator cHREGestureSensorDelegator) {
            this.$statusCode = num;
            this.this$0 = cHREGestureSensorDelegator;
        }
    }

    public CHREGestureSensorDelegator$startListening$1(CHREGestureSensorDelegator cHREGestureSensorDelegator) {
        this.this$0 = cHREGestureSensorDelegator;
    }

    public final void accept(Object obj) {
        Integer num = (Integer) obj;
        Log.i("Columbus/GestureSensor", "CHREGestureSensorDelegator received statusCode = " + num);
        CHREGestureSensorDelegator cHREGestureSensorDelegator = this.this$0;
        cHREGestureSensorDelegator.bgHandler.post(new AnonymousClass1(num, cHREGestureSensorDelegator));
    }
}
