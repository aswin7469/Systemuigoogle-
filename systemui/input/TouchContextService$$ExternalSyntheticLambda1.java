package com.google.android.systemui.input;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class TouchContextService$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ TouchContextService f$0;

    public /* synthetic */ TouchContextService$$ExternalSyntheticLambda1(TouchContextService touchContextService) {
        this.f$0 = touchContextService;
    }

    public final void run() {
        this.f$0.mDisplayListener.onDisplayChanged(0);
    }
}
