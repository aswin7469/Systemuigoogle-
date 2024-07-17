package com.google.android.systemui.reversecharging;

import android.text.TextUtils;
import android.util.Log;
import com.android.settingslib.Utils;
import com.android.systemui.statusbar.phone.StatusBarIconController;
import com.android.systemui.statusbar.phone.StatusBarIconControllerImpl;
import com.android.systemui.statusbar.policy.BatteryControllerImpl;
import com.google.android.systemui.ambientmusic.AmbientIndicationContainer;
import com.google.android.systemui.statusbar.KeyguardIndicationControllerGoogle;
import java.util.Objects;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
                String string = reverseChargingViewController.mContext.getResources().getString(2131953659, new Object[]{reverseChargingViewController.mName, Utils.formatPercentage(reverseChargingViewController.mLevel)});
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
        statusBarIconControllerImpl.setIcon(reverseChargingViewController.mContentDescription, reverseChargingViewController.mSlotReverseCharging, 2131232988);
        ((StatusBarIconControllerImpl) reverseChargingViewController.mStatusBarIconController).setIconVisibility(reverseChargingViewController.mSlotReverseCharging, reverseChargingViewController.mProvidingBattery);
    }
}
