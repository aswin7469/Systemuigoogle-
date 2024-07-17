package com.google.android.systemui.assist.uihints;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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
