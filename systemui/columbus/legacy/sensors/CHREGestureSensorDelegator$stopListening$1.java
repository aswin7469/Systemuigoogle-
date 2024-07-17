package com.google.android.systemui.columbus.legacy.sensors;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
