package com.google.android.systemui.fingerprint;

import com.android.systemui.biometrics.FingerprintReEnrollNotification;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class FingerprintReEnrollNotificationGoogle implements FingerprintReEnrollNotification {
    public final boolean isFingerprintReEnrollRequired(int i) {
        if (i == 1040) {
            return true;
        }
        return false;
    }
}
