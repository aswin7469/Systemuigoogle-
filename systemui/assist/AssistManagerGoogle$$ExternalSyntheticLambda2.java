package com.google.android.systemui.assist;

import android.content.ComponentName;
import com.android.systemui.assist.AssistManager$UiController;
import com.google.android.systemui.assist.uihints.AssistantPresenceHandler;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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
                AssistManagerGoogle assistManagerGoogle = (AssistManagerGoogle) obj;
                AssistantPresenceHandler assistantPresenceHandler = assistManagerGoogle.mAssistantPresenceHandler;
                ComponentName assistComponentForUser = assistantPresenceHandler.mAssistUtils.getAssistComponentForUser(-2);
                if (assistComponentForUser == null || !"com.google.android.googlequicksearchbox/com.google.android.voiceinteraction.GsaVoiceInteractionService".equals(assistComponentForUser.flattenToString())) {
                    z = false;
                } else {
                    z = true;
                }
                assistantPresenceHandler.updateAssistantPresence(z, assistantPresenceHandler.mNgaIsAssistant);
                assistManagerGoogle.mCheckAssistantStatus = false;
                return;
            default:
                ((AssistManager$UiController) obj).hide();
                return;
        }
    }
}
