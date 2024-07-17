package com.google.android.setupcompat.util;

import android.content.Intent;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class WizardManagerHelper {
    public static final String ACTION_NEXT = "com.android.wizard.NEXT";
    static final String EXTRA_ACTION_ID = "actionId";
    static final String EXTRA_SCRIPT_URI = "scriptUri";
    static final String EXTRA_WIZARD_BUNDLE = "wizardBundle";

    public static boolean isAnySetupWizard(Intent intent) {
        if (intent == null) {
            return false;
        }
        return intent.getBooleanExtra("isSetupFlow", false);
    }
}
