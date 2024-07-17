package com.google.android.systemui.fingerprint;

import com.android.systemui.biometrics.FingerprintReEnrollNotification;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class FingerprintReEnrollNotificationGoogle implements FingerprintReEnrollNotification {
    public final boolean isFingerprintReEnrollRequired(int i) {
        if (i == 1040) {
            return true;
        }
        return false;
    }
}
