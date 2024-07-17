package com.google.android.systemui.input;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class TouchContextService$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ TouchContextService f$0;

    public /* synthetic */ TouchContextService$$ExternalSyntheticLambda1(TouchContextService touchContextService) {
        this.f$0 = touchContextService;
    }

    public final void run() {
        this.f$0.mDisplayListener.onDisplayChanged(0);
    }
}
