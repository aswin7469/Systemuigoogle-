package com.google.android.setupdesign.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.view.View;
import com.google.android.setupcompat.PartnerCustomizationLayout;
import com.google.android.setupcompat.internal.TemplateLayout;
import com.google.android.setupcompat.partnerconfig.PartnerConfigHelper;
import com.google.android.setupcompat.util.Logger;
import com.google.android.setupcompat.util.WizardManagerHelper;
import com.google.android.setupdesign.GlifLayout;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class PartnerStyleHelper {
    public static TemplateLayout findLayoutFromActivity(Activity activity) {
        View findViewById;
        if (activity == null || (findViewById = activity.findViewById(2131363713)) == null) {
            return null;
        }
        return (TemplateLayout) findViewById.getParent();
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x003b  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0042 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int getLayoutGravity(android.content.Context r4) {
        /*
            com.google.android.setupcompat.partnerconfig.PartnerConfigHelper r0 = com.google.android.setupcompat.partnerconfig.PartnerConfigHelper.get(r4)
            com.google.android.setupcompat.partnerconfig.PartnerConfig r1 = com.google.android.setupcompat.partnerconfig.PartnerConfig.CONFIG_LAYOUT_GRAVITY
            java.lang.String r4 = r0.getString(r4, r1)
            r0 = 0
            if (r4 != 0) goto L_0x000e
            return r0
        L_0x000e:
            java.util.Locale r1 = java.util.Locale.ROOT
            java.lang.String r4 = r4.toLowerCase(r1)
            int r1 = r4.hashCode()
            r2 = -1364013995(0xffffffffaeb2cc55, float:-8.1307995E-11)
            r3 = 1
            if (r1 == r2) goto L_0x002e
            r2 = 109757538(0x68ac462, float:5.219839E-35)
            if (r1 == r2) goto L_0x0024
            goto L_0x0038
        L_0x0024:
            java.lang.String r1 = "start"
            boolean r4 = r4.equals(r1)
            if (r4 == 0) goto L_0x0038
            r4 = r3
            goto L_0x0039
        L_0x002e:
            java.lang.String r1 = "center"
            boolean r4 = r4.equals(r1)
            if (r4 == 0) goto L_0x0038
            r4 = r0
            goto L_0x0039
        L_0x0038:
            r4 = -1
        L_0x0039:
            if (r4 == 0) goto L_0x0042
            if (r4 == r3) goto L_0x003e
            return r0
        L_0x003e:
            r4 = 8388611(0x800003, float:1.1754948E-38)
            return r4
        L_0x0042:
            r4 = 17
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.setupdesign.util.PartnerStyleHelper.getLayoutGravity(android.content.Context):int");
    }

    public static boolean shouldApplyPartnerHeavyThemeResource(View view) {
        boolean z;
        boolean z2 = false;
        if (view == null) {
            return false;
        }
        if (view instanceof GlifLayout) {
            return ((GlifLayout) view).shouldApplyPartnerHeavyThemeResource();
        }
        Context context = view.getContext();
        try {
            Logger logger = PartnerCustomizationLayout.LOG;
            TemplateLayout findLayoutFromActivity = findLayoutFromActivity(PartnerConfigHelper.lookupActivityFromContext(context));
            if (findLayoutFromActivity instanceof GlifLayout) {
                return ((GlifLayout) findLayoutFromActivity).shouldApplyPartnerHeavyThemeResource();
            }
        } catch (ClassCastException | IllegalArgumentException unused) {
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(new int[]{2130970115});
        boolean z3 = obtainStyledAttributes.getBoolean(0, false);
        obtainStyledAttributes.recycle();
        if (z3 || PartnerConfigHelper.shouldApplyExtendedPartnerConfig(context)) {
            z = true;
        } else {
            z = false;
        }
        if (shouldApplyPartnerResource(context) && z) {
            z2 = true;
        }
        return z2;
    }

    public static boolean shouldApplyPartnerResource(View view) {
        if (view == null) {
            return false;
        }
        if (view instanceof PartnerCustomizationLayout) {
            return ((PartnerCustomizationLayout) view).shouldApplyPartnerResource();
        }
        return shouldApplyPartnerResource(view.getContext());
    }

    public static boolean shouldApplyPartnerResource(Context context) {
        if (!PartnerConfigHelper.get(context).isAvailable()) {
            return false;
        }
        Activity activity = null;
        try {
            Logger logger = PartnerCustomizationLayout.LOG;
            activity = PartnerConfigHelper.lookupActivityFromContext(context);
            if (activity != null) {
                TemplateLayout findLayoutFromActivity = findLayoutFromActivity(activity);
                if (findLayoutFromActivity instanceof PartnerCustomizationLayout) {
                    return ((PartnerCustomizationLayout) findLayoutFromActivity).shouldApplyPartnerResource();
                }
            }
        } catch (ClassCastException | IllegalArgumentException unused) {
        }
        boolean isAnySetupWizard = activity != null ? WizardManagerHelper.isAnySetupWizard(activity.getIntent()) : false;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(new int[]{2130970028});
        boolean z = obtainStyledAttributes.getBoolean(0, true);
        obtainStyledAttributes.recycle();
        if (isAnySetupWizard || z) {
            return true;
        }
        return false;
    }
}
