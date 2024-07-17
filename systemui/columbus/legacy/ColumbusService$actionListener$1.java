package com.google.android.systemui.columbus.legacy;

import com.google.android.systemui.columbus.legacy.actions.Action;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class ColumbusService$actionListener$1 implements Action.Listener {
    public final /* synthetic */ ColumbusService this$0;

    public ColumbusService$actionListener$1(ColumbusService columbusService) {
        this.this$0 = columbusService;
    }

    public final void onActionAvailabilityChanged(Action action) {
        this.this$0.updateSensorListener();
    }
}
