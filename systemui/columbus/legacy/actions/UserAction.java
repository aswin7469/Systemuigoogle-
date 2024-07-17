package com.google.android.systemui.columbus.legacy.actions;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class UserAction extends Action {
    public boolean availableOnLockscreen() {
        return this instanceof LaunchApp;
    }

    public boolean availableOnScreenOff() {
        return this instanceof LaunchOpa;
    }
}
