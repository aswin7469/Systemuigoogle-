package com.google.android.systemui.smartspace;

import android.app.smartspace.SmartspaceAction;
import android.app.smartspace.SmartspaceTarget;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.android.systemui.plugins.BcSmartspaceDataPlugin;
import com.google.android.systemui.smartspace.logging.BcSmartspaceCardLoggingInfo;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class BcNextAlarmData {
    public static final SmartspaceAction SHOW_ALARMS_ACTION = new SmartspaceAction.Builder("nextAlarmId", "Next alarm").setIntent(new Intent("android.intent.action.SHOW_ALARMS")).build();
    public String mDescription;
    public Drawable mImage;

    public static void setOnClickListener(DoubleShadowTextView doubleShadowTextView, DateSmartspaceView$$ExternalSyntheticLambda0 dateSmartspaceView$$ExternalSyntheticLambda0, int i) {
        BcSmartspaceCardLoggingInfo.Builder builder = new BcSmartspaceCardLoggingInfo.Builder();
        builder.mInstanceId = InstanceId.create("upcoming_alarm_card_94510_12684");
        builder.mFeatureType = 23;
        builder.mDisplaySurface = i;
        DoubleShadowTextView doubleShadowTextView2 = doubleShadowTextView;
        BcSmartSpaceUtil.setOnClickListener((View) doubleShadowTextView2, (SmartspaceTarget) null, SHOW_ALARMS_ACTION, (BcSmartspaceDataPlugin.SmartspaceEventNotifier) dateSmartspaceView$$ExternalSyntheticLambda0, "BcNextAlarmData", new BcSmartspaceCardLoggingInfo(builder), 0);
    }
}
