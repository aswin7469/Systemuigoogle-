package com.google.android.systemui.reversecharging;

import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;
import com.android.settingslib.Utils;
import com.android.systemui.statusbar.phone.StatusBarIconController;
import com.android.systemui.statusbar.phone.StatusBarIconControllerImpl;
import com.android.systemui.statusbar.policy.BatteryControllerImpl;
import com.google.android.systemui.ambientmusic.AmbientIndicationContainer;
import com.google.android.systemui.statusbar.KeyguardIndicationControllerGoogle;
import java.text.NumberFormat;
import java.util.Objects;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class ReverseChargingViewController$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ ReverseChargingViewController f$0;

    public /* synthetic */ ReverseChargingViewController$$ExternalSyntheticLambda0(ReverseChargingViewController reverseChargingViewController) {
        this.f$0 = reverseChargingViewController;
    }

    public final void run() {
        String str;
        String str2;
        ReverseChargingViewController reverseChargingViewController = this.f$0;
        if (reverseChargingViewController.mAmbientIndicationContainer != null) {
            if (reverseChargingViewController.mReverse || !((BatteryControllerImpl) reverseChargingViewController.mBatteryController).mWirelessCharging || TextUtils.isEmpty(reverseChargingViewController.mName)) {
                AmbientIndicationContainer ambientIndicationContainer = reverseChargingViewController.mAmbientIndicationContainer;
                String str3 = "";
                if (reverseChargingViewController.mProvidingBattery) {
                    str = reverseChargingViewController.mReverseCharging;
                } else {
                    str = str3;
                }
                if (!Objects.equals(ambientIndicationContainer.mReverseChargingMessage, str) || ambientIndicationContainer.mWirelessChargingMessage != null) {
                    ambientIndicationContainer.mWirelessChargingMessage = null;
                    ambientIndicationContainer.mReverseChargingMessage = str;
                    ambientIndicationContainer.updatePill();
                }
                KeyguardIndicationControllerGoogle keyguardIndicationControllerGoogle = reverseChargingViewController.mKeyguardIndicationController;
                if (reverseChargingViewController.mProvidingBattery) {
                    str2 = reverseChargingViewController.mReverseCharging;
                } else {
                    str2 = str3;
                }
                keyguardIndicationControllerGoogle.setReverseChargingMessage(str2);
                if (ReverseChargingViewController.DEBUG) {
                    StringBuilder sb = new StringBuilder("updateMessage(): rtx=");
                    sb.append(reverseChargingViewController.mReverse ? 1 : 0);
                    sb.append(" rtxString=");
                    if (reverseChargingViewController.mProvidingBattery) {
                        str3 = reverseChargingViewController.mReverseCharging;
                    }
                    sb.append(str3);
                    Log.d("ReverseChargingViewCtrl", sb.toString());
                }
            } else {
                Resources resources = reverseChargingViewController.mContext.getResources();
                String str4 = reverseChargingViewController.mName;
                int i = reverseChargingViewController.mLevel;
                int i2 = Utils.$r8$clinit;
                String string = resources.getString(2131953700, new Object[]{str4, NumberFormat.getPercentInstance().format(((double) i) / 100.0d)});
                if (ReverseChargingViewController.DEBUG) {
                    Log.d("ReverseChargingViewCtrl", "updateMessage(): rtx=" + (reverseChargingViewController.mReverse ? 1 : 0) + " wlcString=" + string);
                }
                AmbientIndicationContainer ambientIndicationContainer2 = reverseChargingViewController.mAmbientIndicationContainer;
                if (!Objects.equals(ambientIndicationContainer2.mWirelessChargingMessage, string) || ambientIndicationContainer2.mReverseChargingMessage != null) {
                    ambientIndicationContainer2.mWirelessChargingMessage = string;
                    ambientIndicationContainer2.mReverseChargingMessage = null;
                    ambientIndicationContainer2.updatePill();
                }
                reverseChargingViewController.mKeyguardIndicationController.setReverseChargingMessage(string);
            }
        }
        StatusBarIconController statusBarIconController = reverseChargingViewController.mStatusBarIconController;
        StatusBarIconControllerImpl statusBarIconControllerImpl = (StatusBarIconControllerImpl) statusBarIconController;
        statusBarIconControllerImpl.setIcon(reverseChargingViewController.mContentDescription, reverseChargingViewController.mSlotReverseCharging, 2131233030);
        ((StatusBarIconControllerImpl) reverseChargingViewController.mStatusBarIconController).setIconVisibility(reverseChargingViewController.mSlotReverseCharging, reverseChargingViewController.mProvidingBattery);
    }
}
