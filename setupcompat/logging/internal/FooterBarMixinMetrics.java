package com.google.android.setupcompat.logging.internal;

import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class FooterBarMixinMetrics {
    public static final String EXTRA_PRIMARY_BUTTON_VISIBILITY = "PrimaryButtonVisibility";
    public static final String EXTRA_SECONDARY_BUTTON_VISIBILITY = "SecondaryButtonVisibility";
    public String primaryButtonVisibility;
    public String secondaryButtonVisibility;

    @Retention(RetentionPolicy.SOURCE)
    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public @interface FooterButtonVisibility {
    }

    public static String updateButtonVisibilityState(String str, boolean z) {
        if (!"VisibleUsingXml".equals(str) && !"Visible".equals(str) && !"Invisible".equals(str)) {
            throw new IllegalStateException(AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Illegal visibility state: ", str));
        } else if (z && "Invisible".equals(str)) {
            return "Invisible_to_Visible";
        } else {
            if (z) {
                return str;
            }
            if ("VisibleUsingXml".equals(str)) {
                return "VisibleUsingXml_to_Invisible";
            }
            if ("Visible".equals(str)) {
                return "Visible_to_Invisible";
            }
            return str;
        }
    }
}
