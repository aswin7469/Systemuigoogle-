package com.google.android.systemui.assist.uihints;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class NavBarFadeController$onTimeout$1 implements Runnable {
    public final /* synthetic */ NavBarFadeController this$0;

    public NavBarFadeController$onTimeout$1(NavBarFadeController navBarFadeController) {
        this.this$0 = navBarFadeController;
    }

    public final void run() {
        NavBarFadeController navBarFadeController = this.this$0;
        navBarFadeController.systemVisible = true;
        navBarFadeController.ngaVisible = true;
        navBarFadeController.update();
    }
}
