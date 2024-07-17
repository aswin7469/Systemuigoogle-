package com.google.android.systemui.reversecharging;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import androidx.compose.foundation.text.ValidatingOffsetMapping$$ExternalSyntheticOutline0;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.statusbar.NotificationShadeWindowController;
import com.android.systemui.statusbar.phone.StatusBarIconController;
import com.android.systemui.statusbar.policy.BatteryController;
import com.google.android.systemui.ambientmusic.AmbientIndicationContainer;
import com.google.android.systemui.keyguard.domain.interactor.AmbientIndicationInteractor;
import com.google.android.systemui.statusbar.KeyguardIndicationControllerGoogle;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class ReverseChargingViewController extends BroadcastReceiver implements LifecycleOwner, BatteryController.BatteryStateChangeCallback {
    public static final boolean DEBUG = Log.isLoggable("ReverseChargingViewCtrl", 3);
    public AmbientIndicationContainer mAmbientIndicationContainer;
    public final BatteryController mBatteryController;
    public final BroadcastDispatcher mBroadcastDispatcher;
    public String mContentDescription;
    public final Context mContext;
    public final KeyguardIndicationControllerGoogle mKeyguardIndicationController;
    public int mLevel;
    public final LifecycleRegistry mLifecycle = new LifecycleRegistry(this);
    public final Executor mMainExecutor;
    public String mName;
    public final NotificationShadeWindowController mNotificationShadeWindowController;
    public boolean mProvidingBattery;
    public boolean mReverse;
    public String mReverseCharging;
    public String mSlotReverseCharging;
    public final StatusBarIconController mStatusBarIconController;

    public ReverseChargingViewController(Context context, BatteryController batteryController, NotificationShadeWindowController notificationShadeWindowController, StatusBarIconController statusBarIconController, BroadcastDispatcher broadcastDispatcher, Executor executor, KeyguardIndicationControllerGoogle keyguardIndicationControllerGoogle, AmbientIndicationInteractor ambientIndicationInteractor) {
        this.mBatteryController = batteryController;
        this.mStatusBarIconController = statusBarIconController;
        this.mNotificationShadeWindowController = notificationShadeWindowController;
        this.mContext = context;
        this.mBroadcastDispatcher = broadcastDispatcher;
        this.mMainExecutor = executor;
        this.mKeyguardIndicationController = keyguardIndicationControllerGoogle;
        this.mReverseCharging = context.getString(2131952190);
        this.mSlotReverseCharging = context.getString(2131953882);
        this.mContentDescription = context.getString(2131953660);
    }

    public final Lifecycle getLifecycle() {
        return this.mLifecycle;
    }

    public final void onBatteryLevelChanged(int i, boolean z, boolean z2) {
        this.mReverse = this.mBatteryController.isReverseOn();
        if (DEBUG) {
            Log.d("ReverseChargingViewCtrl", "onBatteryLevelChanged(): rtx=" + (this.mReverse ? 1 : 0) + " level=" + this.mLevel + " name=" + this.mName + " this=" + this);
        }
        this.mMainExecutor.execute(new ReverseChargingViewController$$ExternalSyntheticLambda0(this));
    }

    public final void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.LOCALE_CHANGED")) {
            this.mReverseCharging = this.mContext.getString(2131952190);
            this.mSlotReverseCharging = this.mContext.getString(2131953882);
            this.mContentDescription = this.mContext.getString(2131953660);
            if (DEBUG) {
                Log.d("ReverseChargingViewCtrl", "onReceive(): ACTION_LOCALE_CHANGED this=" + this);
            }
            this.mMainExecutor.execute(new ReverseChargingViewController$$ExternalSyntheticLambda0(this));
        }
    }

    public final void onReverseChanged(int i, String str, boolean z) {
        boolean z2;
        this.mReverse = z;
        this.mLevel = i;
        this.mName = str;
        if (!z || i < 0) {
            z2 = false;
        } else {
            z2 = true;
        }
        this.mProvidingBattery = z2;
        if (DEBUG) {
            StringBuilder m = ValidatingOffsetMapping$$ExternalSyntheticOutline0.m("onReverseChanged(): rtx=", z ? 1 : 0, " level=", i, " name=");
            m.append(str);
            m.append(" this=");
            m.append(this);
            Log.d("ReverseChargingViewCtrl", m.toString());
        }
        this.mMainExecutor.execute(new ReverseChargingViewController$$ExternalSyntheticLambda0(this));
    }
}
