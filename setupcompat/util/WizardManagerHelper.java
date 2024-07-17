package com.google.android.setupcompat.util;

import android.content.Intent;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class WizardManagerHelper {
    public static final String ACTION_NEXT = "com.android.wizard.NEXT";
    static final String EXTRA_WIZARD_BUNDLE = "wizardBundle";

    public static boolean isAnySetupWizard(Intent intent) {
        if (intent == null) {
            return false;
        }
        return intent.getBooleanExtra("isSetupFlow", false);
    }
}
