package com.google.android.setupcompat.internal;

import androidx.appsearch.app.GenericDocument$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class Validations {
    public static void assertLengthInRange(String str, int i, int i2, String str2) {
        boolean z;
        Preconditions.checkNotNull(str, str2.concat(" cannot be null."));
        int length = str.length();
        if (length > i2 || length < i) {
            z = false;
        } else {
            z = true;
        }
        StringBuilder m = GenericDocument$$ExternalSyntheticOutline0.m("Length of ", str2, " should be in the range [", i, "-");
        m.append(i2);
        m.append("]");
        Preconditions.checkArgument(m.toString(), z);
    }
}
