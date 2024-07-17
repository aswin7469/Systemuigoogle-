package com.google.android.systemui.columbus.legacy.actions;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class UserAction extends Action {
    public boolean availableOnLockscreen() {
        return this instanceof LaunchApp;
    }

    public boolean availableOnScreenOff() {
        return this instanceof LaunchOpa;
    }
}
