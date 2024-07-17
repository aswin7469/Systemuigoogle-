package com.google.android.systemui.smartspace;

import android.app.smartspace.SmartspaceAction;
import android.app.smartspace.SmartspaceTarget;
import android.app.smartspace.SmartspaceTargetEvent;
import android.util.Log;
import android.view.View;
import com.android.systemui.plugins.BcSmartspaceDataPlugin;
import com.android.systemui.plugins.FalsingManager;
import com.google.android.systemui.smartspace.logging.BcSmartspaceCardLogger;
import com.google.android.systemui.smartspace.logging.BcSmartspaceCardLoggingInfo;
import com.google.android.systemui.smartspace.logging.BcSmartspaceSubcardLoggingInfo;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class BcSmartSpaceUtil$$ExternalSyntheticLambda1 implements View.OnClickListener {
    public final /* synthetic */ BcSmartspaceCardLoggingInfo f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ BcSmartspaceDataPlugin.IntentStarter f$3;
    public final /* synthetic */ SmartspaceAction f$4;
    public final /* synthetic */ boolean f$5;
    public final /* synthetic */ View.OnClickListener f$6 = null;
    public final /* synthetic */ BcSmartspaceDataPlugin.SmartspaceEventNotifier f$7;
    public final /* synthetic */ String f$8;
    public final /* synthetic */ SmartspaceTarget f$9;

    public /* synthetic */ BcSmartSpaceUtil$$ExternalSyntheticLambda1(BcSmartspaceCardLoggingInfo bcSmartspaceCardLoggingInfo, int i, boolean z, BcSmartspaceDataPlugin.IntentStarter intentStarter, SmartspaceAction smartspaceAction, boolean z2, BcSmartspaceDataPlugin.SmartspaceEventNotifier smartspaceEventNotifier, String str, SmartspaceTarget smartspaceTarget) {
        this.f$0 = bcSmartspaceCardLoggingInfo;
        this.f$1 = i;
        this.f$2 = z;
        this.f$3 = intentStarter;
        this.f$4 = smartspaceAction;
        this.f$5 = z2;
        this.f$7 = smartspaceEventNotifier;
        this.f$8 = str;
        this.f$9 = smartspaceTarget;
    }

    public final void onClick(View view) {
        BcSmartspaceCardLoggingInfo bcSmartspaceCardLoggingInfo = this.f$0;
        int i = this.f$1;
        boolean z = this.f$2;
        BcSmartspaceDataPlugin.IntentStarter intentStarter = this.f$3;
        SmartspaceAction smartspaceAction = this.f$4;
        boolean z2 = this.f$5;
        View.OnClickListener onClickListener = this.f$6;
        BcSmartspaceDataPlugin.SmartspaceEventNotifier smartspaceEventNotifier = this.f$7;
        String str = this.f$8;
        SmartspaceTarget smartspaceTarget = this.f$9;
        FalsingManager falsingManager = BcSmartSpaceUtil.sFalsingManager;
        if (falsingManager == null || !falsingManager.isFalseTap(1)) {
            if (bcSmartspaceCardLoggingInfo != null) {
                BcSmartspaceSubcardLoggingInfo bcSmartspaceSubcardLoggingInfo = bcSmartspaceCardLoggingInfo.mSubcardInfo;
                if (bcSmartspaceSubcardLoggingInfo != null) {
                    bcSmartspaceSubcardLoggingInfo.mClickedSubcardIndex = i;
                }
                BcSmartspaceCardLogger.log(BcSmartspaceEvent.SMARTSPACE_CARD_CLICK, bcSmartspaceCardLoggingInfo);
            }
            if (!z) {
                intentStarter.startFromAction(smartspaceAction, view, z2);
            }
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
            if (smartspaceEventNotifier == null) {
                Log.w(str, "Cannot notify target interaction smartspace event: event notifier null.");
            } else {
                smartspaceEventNotifier.notifySmartspaceEvent(new SmartspaceTargetEvent.Builder(1).setSmartspaceTarget(smartspaceTarget).setSmartspaceActionId(smartspaceAction.getId()).build());
            }
        }
    }
}
