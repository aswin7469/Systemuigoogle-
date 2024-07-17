package com.google.android.systemui.assist;

import android.content.ComponentName;
import com.android.systemui.assist.AssistManager;
import com.google.android.systemui.assist.uihints.AssistantPresenceHandler;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class AssistManagerGoogle$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ AssistManagerGoogle$$ExternalSyntheticLambda2(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    public final void run() {
        boolean z;
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                ((AssistManager.UiController) obj).hide();
                return;
            case 1:
                ((AssistManager.UiController) obj).hide();
                return;
            default:
                AssistManagerGoogle assistManagerGoogle = (AssistManagerGoogle) obj;
                AssistantPresenceHandler assistantPresenceHandler = assistManagerGoogle.mAssistantPresenceHandler;
                ComponentName assistComponentForUser = assistantPresenceHandler.mAssistUtils.getAssistComponentForUser(-2);
                if (assistComponentForUser == null || !"com.google.android.googlequicksearchbox/com.google.android.voiceinteraction.GsaVoiceInteractionService".equals(assistComponentForUser.flattenToString())) {
                    z = false;
                } else {
                    z = true;
                }
                assistantPresenceHandler.updateAssistantPresence(z, assistantPresenceHandler.mNgaIsAssistant, assistantPresenceHandler.mSysUiIsNgaUi);
                assistManagerGoogle.mCheckAssistantStatus = false;
                return;
        }
    }
}
