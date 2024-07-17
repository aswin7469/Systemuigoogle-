package com.google.android.systemui.columbus.legacy.sensors;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class CHREGestureSensorDelegator$stopListening$1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ CHREGestureSensorDelegator this$0;

    public /* synthetic */ CHREGestureSensorDelegator$stopListening$1(CHREGestureSensorDelegator cHREGestureSensorDelegator, int i) {
        this.$r8$classId = i;
        this.this$0 = cHREGestureSensorDelegator;
    }

    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                this.this$0.gestureSensor.stopListening();
                return;
            default:
                this.this$0.gestureSensor.startListening();
                return;
        }
    }
}
